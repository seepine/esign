package com.seepine.esign.module.sign.file;

import com.seepine.esign.common.ESignRes;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
public class SignFileUploadStatusRes extends ESignRes {
  /** 文件ID（请注意保管好文件ID，可用于后续制作模板、签署相关操作） */
  String fileId;
  /** 文件名称 */
  String fileName;
  /** 文件大小（预留字段，暂时不会返回任何值，开发者可忽略） */
  Integer fileSize;
  /**
   * 文件状态
   *
   * <p>0 - 文件未上传
   *
   * <p>1 - 文件上传中
   *
   * <p>2 - 文件上传已完成 或 文件已转换（HTML）
   *
   * <p>3 - 文件上传失败
   *
   * <p>4 - 文件等待转换（PDF）
   *
   * <p>5 - 文件已转换（PDF）
   *
   * <p>6 - 加水印中
   *
   * <p>7 - 加水印完毕
   *
   * <p>8 - 文件转化中（PDF）
   *
   * <p>9 - 文件转换失败（PDF）
   *
   * <p>10 - 文件等待转换（HTML）
   *
   * <p>11 - 文件转换中（HTML）
   *
   * <p>12 - 文件转换失败（HTML）
   *
   * <p>【注】文件添加水印功能仅e签宝SaaS高级版支持，具体功能如何接入请联系对接技术指导
   */
  Integer fileStatus;

  /** 文件下载地址（有效期为60分钟，过期后可以重新调用接口获取新的下载地址） */
  String fileDownloadUrl;

  /** pdf文件总页数 */
  Integer fileTotalPageCount;
  /**
   * 首页宽度，单位：像素（px）
   *
   * <p>【注】pageSize传true才返回具体值
   */
  Double pageWidth;
  /**
   * 首页高度，单位：像素（px）
   *
   * <p>【注】pageSize传true才返回具体值
   */
  Double pageHeight;
}
