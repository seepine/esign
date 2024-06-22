package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignClient;
import com.seepine.esign.common.ESignException;
import com.seepine.http.Request;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import com.seepine.tool.secure.digest.DigestAlgorithm;
import com.seepine.tool.secure.digest.Digests;
import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.util.Objects;
import com.seepine.tool.util.Validate;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class SignFileUploadUtil {
  private static final Integer SUCCESS = 0;

  @Nonnull
  public static synchronized String uploadV3(
      ESignClient client, String fileName, @Nonnull ByteArrayOutputStream contract)
      throws ESignException {
    return uploadV3(client, SignFileUploadUrlReq.builder().fileName(fileName).build(), contract);
  }

  @Nonnull
  public static synchronized String uploadV3(
      ESignClient client, SignFileUploadUrlReq req, @Nonnull ByteArrayOutputStream contract)
      throws ESignException {
    if (Objects.isBlank(req.getFileName())) {
      throw new ESignException("文件名fileName不能为空");
    }
    String fileMd5 =
        Base64.encode(Digests.digest(DigestAlgorithm.MD5).digest(contract.toByteArray()));
    String contentType = req.getContentType();
    if (contentType == null) {
      contentType = "application/octet-stream";
    }
    SignFileUploadUrlRes res =
        client.execute(
            req.toBuilder()
                .convertToPDF(!req.getFileName().endsWith(".pdf"))
                .contentMd5(fileMd5)
                .fileSize((long) contract.size())
                .contentType(contentType)
                .build());
    if (!res.isSuccess()) {
      throw new ESignException(res.toString());
    }
    Map<String, String> uploadHeader = new HashMap<>();
    uploadHeader.put("Content-MD5", fileMd5);
    uploadHeader.put("Content-Type", contentType);
    // 计算请求签名值
    RequestBody body = RequestBody.create(contract.toByteArray(), MediaType.parse(contentType));
    String result =
        Request.put(res.fileUploadUrl).body(body).addHeader(uploadHeader).execute().bodyStr();
    JsonObject jsonObjectData = Json.parseObj(result);
    Validate.isTrue(SUCCESS.equals(jsonObjectData.getInt("errCode")), "文件上传失败");
    int i = 1;
    while (true) {
      try {
        Thread.sleep(200L * i);
      } catch (InterruptedException e) {
        throw new ESignException(e);
      }
      SignFileUploadStatusRes statusRes =
          client.execute(SignFileUploadStatusReq.builder().fileId(res.getFileId()).build());
      Validate.isTrue(statusRes.isSuccess(), "文件上传失败");
      if (Integer.valueOf(2).equals(statusRes.getFileStatus())
          || Integer.valueOf(5).equals(statusRes.getFileStatus())) {
        break;
      }
      if (i > 5) {
        throw new ESignException("查询状态超时");
      }
      i++;
    }
    return res.getFileId();
  }
}
