# sign

存放合同签署相关接口

## files 文件类API

```java
class Test{
  public static void main(String[] args) {
    // 读合同文件
    FileInputStream is =
      new FileInputStream(
        Objects.requireNonNull(CreateFlowOneSignTest.class.getResource("/contract.docx"))
          .getFile());
    ByteArrayOutputStream contract = new ByteArrayOutputStream();
    byte[] buffer = new byte[4096];
    int bytesRead;
    while ((bytesRead = is.read(buffer)) != -1) {
      contract.write(buffer, 0, bytesRead);
    }
    is.close();

    // 上传获得 fileId
    String fileId =
      SignFileUploadUtil.uploadV3(
        ESignClientHelp.build(),
        SignFileUploadUrlReq.builder().fileName("contract.docx").build(),
        contract);
    System.out.println(fileId);

    // 可不需要，查询关键词坐标，例如自动匹配盖章
    SignFileKeywordPositionsRes res =
      ESignClientHelp.build()
        .execute(
          SignFileKeywordPositionsReq.builder()
            .fileId(fileId)
            .keywords(Arrays.asList("甲方：", "乙方（自由职业者）："))
            .build());
    if (res.isSuccess()) {
      System.out.println("查询成功，" + res.getKeywordPositions());
    } else {
      System.out.println("查询失败，" + res);
    }
  }
}   
```

## flow 签署流程

### 1. 基于文件发起签署 SignFlowCreateByFileReq

### 2. 获取签署页面链接 SignFlowSignUrlReq

若跳转小程序方式可能不需要

### 3. 查询签署流程详情 SignFlowQueryDetailReq

### 4. 下载已签署文件及附属材料 SignFlowFileDownloadUrlReq

### 5.