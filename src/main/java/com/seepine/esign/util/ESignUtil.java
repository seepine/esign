package com.seepine.esign.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.seepine.esign.common.ESignException;
import com.seepine.esign.common.ESignProperties;
import com.seepine.esign.common.ESignReq;
import com.seepine.esign.common.ESignRes;
import com.seepine.http.ContentType;
import com.seepine.http.Method;
import com.seepine.http.Request;
import com.seepine.http.Response;
import com.seepine.json.Json;
import com.seepine.json.JsonObject;
import com.seepine.json.exception.JsonException;
import com.seepine.tool.R;
import com.seepine.tool.secure.digest.DigestAlgorithm;
import com.seepine.tool.secure.digest.Digests;
import com.seepine.tool.secure.digest.HmacAlgorithm;
import com.seepine.tool.secure.symmetric.Base64;
import com.seepine.tool.time.CurrentTimeMillis;
import com.seepine.tool.util.Maps;
import com.seepine.tool.util.Objects;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Nonnull;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class ESignUtil {
  public static Map<String, String> buildHeader(
      ESignProperties properties, Method method, String api, String bodyJson) {
    String reqBodyData = Optional.ofNullable(bodyJson).orElse("");
    String contentMD5 =
        Objects.isBlank(reqBodyData)
            ? ""
            : Base64.encode(Digests.digest(DigestAlgorithm.MD5).digest(reqBodyData));
    // 构建待签名字符串
    String methodName = method.name();
    String accept = ContentType.JSON.toString(StandardCharsets.UTF_8);
    String contentType = ContentType.JSON.toString(StandardCharsets.UTF_8);
    String date = "";
    // 构建参与请求签名计算的明文
    String plaintext =
        methodName
            + "\n"
            + accept
            + "\n"
            + contentMD5
            + "\n"
            + contentType
            + "\n"
            + date
            + "\n"
            + api;
    // 计算请求签名值
    String reqSignature =
        Base64.encode(
            Digests.hmac(HmacAlgorithm.HmacSHA256, properties.getAccessKeySecret())
                .digest(plaintext));
    return Maps.<String, String>linkedHashMap()
        .put("X-Tsign-Open-App-Id", properties.getAccessKeyId())
        .put("X-Tsign-Open-Auth-Mode", "Signature")
        .put("X-Tsign-Open-Ca-Timestamp", String.valueOf(CurrentTimeMillis.now()))
        .put("Accept", accept)
        .put("Content-Type", contentType)
        .put("X-Tsign-Open-Ca-Signature", reqSignature)
        .put("Content-MD5", contentMD5)
        .build();
  }

  /**
   * POST请求
   *
   * @param properties 属性
   * @param req 接口
   * @return 返回参数
   */
  @Nonnull
  public static <Q extends ESignReq<R>, R extends ESignRes> R request(
      ESignProperties properties, Q req) {
    String url = req.url();
    String bodyJsonStr = "";

    try {
      bodyJsonStr = Json.toJsonIgnoreNull(req);
    } catch (JsonException ignored) {
    }

    if (req.method().equals(Method.GET)) {
      try {
        Map<String, String> map = Json.convert(req, new TypeReference<Map<String, String>>() {});
        if (map != null && map.size() > 0) {
          String params =
              map.entrySet().stream()
                  .filter((e) -> e.getKey() != null && e.getValue() != null)
                  .map(e -> e.getKey() + "=" + URLEncoder.encode(e.getValue()))
                  .collect(Collectors.joining("&"));
          url += "?" + params;
        }
      } catch (JsonException ignore) {
      }
    }

    Request request =
        Request.url(properties.getEndpoint() + url, req.method())
            .addHeader(buildHeader(properties, req.method(), url, bodyJsonStr));
    if (req.method() != Method.GET) {
      request.body(bodyJsonStr);
    }
    Response response = request.execute();
    if (response.status() != 200) {
      throw new ESignException(response.getResponse().toString());
    }
    String result = response.bodyStr();
    JsonObject r = Json.parseObj(result);
    JsonObject data = null;
    JsonNode dataJsonNode = Optional.ofNullable(r.get("data")).orElse(Json.parse("{}"));
    if (dataJsonNode.isObject()) {
      data = new JsonObject(dataJsonNode);
    }
    R res = Json.parse(data == null ? "{}" : data.toString(), req.clazz());
    res.setCode(r.getInt("code"));
    res.setMessage(r.getStr("message"));
    return res;
  }

  /**
   * 请求签名鉴权-GET请求
   *
   * @param appId appId
   * @param appKey appKey
   * @param host host
   * @param accountsApi accountsApi
   * @param reqBodyObj reqBodyObj
   * @return JsonObject
   */
  @Nonnull
  public static JsonObject get(
      String appId, String appKey, String host, String accountsApi, JsonObject reqBodyObj) {
    // 个人创建账号接口请求地址
    String accountsApiUrl = host + accountsApi;
    // 请求Body体数据
    String reqBodyData = reqBodyObj.toString();
    // 对请求Body体内的数据计算ContentMD5
    String contentMD5 = Base64.encode(Digests.digest(DigestAlgorithm.MD5).digest(reqBodyData));
    // 构建待签名字符串
    String method = Method.GET.name();
    String accept = "*/*";
    String contentType = ContentType.JSON.toString(StandardCharsets.UTF_8);
    String date = "";
    // 构建参与请求签名计算的明文
    String plaintext =
        method
            + "\n"
            + accept
            + "\n"
            + contentMD5
            + "\n"
            + contentType
            + "\n"
            + date
            + "\n"
            + accountsApi;
    // 计算请求签名值
    String reqSignature =
        Base64.encode(Digests.hmac(HmacAlgorithm.HmacSHA256, appKey).digest(plaintext));
    // 获取时间戳(精确到毫秒)
    long timeStamp = CurrentTimeMillis.now();
    // 构建请求头
    Map<String, String> header =
        Maps.<String, String>linkedHashMap()
            .put("X-Tsign-Open-App-Id", appId)
            .put("X-Tsign-Open-Auth-Mode", "Signature")
            .put("X-Tsign-Open-Ca-Timestamp", String.valueOf(timeStamp))
            .put("Accept", accept)
            .put("Content-Type", contentType)
            .put("X-Tsign-Open-Ca-Signature", reqSignature)
            .put("Content-MD5", contentMD5)
            .build();
    String result = Request.get(accountsApiUrl).addHeader(header).execute().bodyStr();
    return Json.parseObj(result);
  }
  /**
   * 请求签名鉴权-POST请求
   *
   * @param appId appId
   * @param appKey appKey
   * @param host host
   * @param accountsApi accountsApi
   * @param reqBodyObj reqBodyObj
   * @return JsonObject
   */
  @Nonnull
  public static JsonObject post(
      String appId, String appKey, String host, String accountsApi, JsonObject reqBodyObj) {
    // 个人创建账号接口请求地址
    String accountsApiUrl = host + accountsApi;
    // 请求Body体数据
    String reqBodyData = reqBodyObj.toString();
    // 对请求Body体内的数据计算ContentMD5
    String contentMD5 = Base64.encode(Digests.digest(DigestAlgorithm.MD5).digest(reqBodyData));
    // 构建待签名字符串
    String method = Method.POST.name();
    String accept = "*/*";
    String contentType = ContentType.JSON.toString(StandardCharsets.UTF_8);
    String date = "";
    // 构建参与请求签名计算的明文
    String plaintext =
        method
            + "\n"
            + accept
            + "\n"
            + contentMD5
            + "\n"
            + contentType
            + "\n"
            + date
            + "\n"
            + accountsApi;
    // 计算请求签名值
    String reqSignature =
        Base64.encode(Digests.hmac(HmacAlgorithm.HmacSHA256, appKey).digest(plaintext));

    // 获取时间戳(精确到毫秒)
    long timeStamp = CurrentTimeMillis.now();
    // 构建请求头
    Map<String, String> header =
        Maps.<String, String>linkedHashMap()
            .put("X-Tsign-Open-App-Id", appId)
            .put("X-Tsign-Open-Auth-Mode", "Signature")
            .put("X-Tsign-Open-Ca-Timestamp", String.valueOf(timeStamp))
            .put("Accept", accept)
            .put("Content-Type", contentType)
            .put("X-Tsign-Open-Ca-Signature", reqSignature)
            .put("Content-MD5", contentMD5)
            .build();
    String result =
        Request.post(accountsApiUrl).body(reqBodyData).addHeader(header).execute().bodyStr();
    return Json.parseObj(result);
  }
  /**
   * 请求签名鉴权-PUT请求
   *
   * @param httpUrl httpUrl
   * @param fileContentMd5 fileContentMd5
   * @param param param
   * @return JsonObject
   */
  @Nonnull
  public static JsonObject put(String httpUrl, String fileContentMd5, byte[] param) {
    Map<String, String> uploadHeader = new HashMap<>();
    uploadHeader.put("Content-MD5", fileContentMd5);
    uploadHeader.put("Content-Type", "application/octet-stream");
    // 计算请求签名值
    RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream"), param);
    String result = Request.put(httpUrl).body(body).addHeader(uploadHeader).execute().bodyStr();
    return Json.parseObj(result);
  }
}
