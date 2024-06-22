package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignReq;
import com.seepine.http.Method;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class SignFileUploadUrlReq implements ESignReq<SignFileUploadUrlRes> {
  /**
   * 必填 文件的Content-MD5值。
   *
   * <p>先获取文件MD5的128位二进制数组，再对此二进制进行Base64编码。
   *
   * <p>（1）可参考Content-MD5计算说明及代码示例计算。
   *
   * <p>（2）开发调试时可通过【获取文件哈希值小工具】计算。
   */
  String contentMd5;

  /**
   * 必填 目标文件的MIME类型
   *
   * <p>可填写 application/octet-stream 或 application/pdf
   *
   * <p>补充说明：步骤二 上传文件流时请求头中的Content-Type参数值要与此参数值一致，否则上传文件流时会出现403报错。
   */
  String contentType;
  /**
   * 必填 文件名称
   *
   * <p>必须带上文件扩展名，不然会导致后续发起流程校验过不去。示例：合同名1.pdf 、合同名2.docx
   *
   * <p>补充说明：
   *
   * <p>（1）该字段的文件后缀名称和真实的文件后缀需要一致。比如上传的文件类型是word文件，那该参数需要传“xxx.docx”，不能是“xxx.pdf”。
   *
   * <p>（2）该字段建议直接传入pdf文件，其他类型文件建议本地自行转换成pdf，避免通过接口格式转换引起的格式错误、耗时久等问题。
   *
   * <p>（3）文件名称不可含有以下9个特殊字符：/ \ : * " < > | ？以及所有emoji表情
   */
  String fileName;
  /** 必填 文件大小，单位byte */
  Long fileSize;

  /**
   * 是否需要转换成PDF文档，默认值 false。
   *
   * <p>true - 需要转换成PDF
   *
   * <p>false - 无需转换成PDF
   *
   * <p>补充说明：
   *
   * <p>（1）若文件已是PDF格式，此参数可不传或传 false 。
   *
   * <p>（2）若文件非PDF格式，此参数值必须传 true。
   *
   * <p>（3）当文件用于制作HTML模板，此参数可不传或参数值必须传 false。
   */
  Boolean convertToPDF;
  /**
   * 是否需要转换成HTML文档，默认值 false。
   *
   * <p>true - 需要转换成HTML
   *
   * <p>false - 无需转换成HTML
   *
   * <p>补充说明：
   *
   * <p>（1）上传 .doc 和 .docx 格式文件时才支持转成HTML。（建议用Office制作文件，WPS可能会导致格式错乱）
   *
   * <p>（2）若上传的文件用于制作HTML模板，此参数值必须传 true。（不支持直接上传HTML格式的文件）
   *
   * <p>（3）当文件用于制作PDF模板，此参数可不传或参数值必须传 false。
   */
  Boolean convertToHTML;
  /**
   * 专属云项目ID
   *
   * <p>补充说明：
   *
   * <p>（1）专属云：文件需要存储在开发者本地系统，购买了专属云服务时使用；
   *
   * <p>（2）专属云项目ID获取方式：请先联系对接群内技术获取；
   *
   * <p>（3）专属云文件暂不支持模板相关功能；如使用模板功能，模板填充后生成的文件会存储到e签宝云端服务器，变成公有云文件。
   */
  String dedicatedCloudId;

  @Override
  public String url() {
    return "/v3/files/file-upload-url";
  }

  @Override
  public Method method() {
    return Method.POST;
  }

  @Override
  public Class<SignFileUploadUrlRes> clazz() {
    return SignFileUploadUrlRes.class;
  }
}
