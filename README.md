# esign

> 对接易签宝v3接口的java sdk

## 一、安装

#### LatestVersion

[![Maven Central](https://img.shields.io/maven-central/v/com.seepine/esign.svg)](https://search.maven.org/search?q=g:com.seepine%20a:esign)

#### Maven

```xml

<dependency>
  <groupId>com.seepine</groupId>
  <artifactId>esign</artifactId>
  <version>${latestVersion}</version>
</dependency>
```

#### Gradle

```gradle
implementation("com.seepine:esign:${lastVersion}")
```

## 二、基础用法

### 创建实例

```java
public class OcrIdCardTest {
  public static void main(String[] args) {
    ESignClient eSignClient =
      new ESignClient(
        ESignProperties.builder()
          // 替换为真实参数
          .endpoint("https://smlopenapi.esign.cn")
          .accessKeyId("7438987***")
          .accessKeySecret("677627c4caf44b63405bb496bfd7****")
          .build());
  }
}

```

### 发起请求

> 已ocr接口为例，其他接口替换为对应的 `req` 和 `res` 类即可

```java
public class OcrIdCardTest {
  public static void main(String[] args) {
    //    ESignClient eSignClient =
    //      new ESignClient(
    //        ESignProperties.builder()
    //          // 替换为真实参数
    //          .endpoint("https://smlopenapi.esign.cn")
    //          .accessKeyId("7438987***")
    //          .accessKeySecret("677627c4caf44b63405bb496bfd7****")
    //          .build());

    // 替换为真实身份证人像面
    String img = "data:image/jpeg;base64,/9j/4AAQS********KYH//2Q==";

    OcrIdCardRes res = eSignClient.execute(OcrIdCardReq.builder().infoImg(img).build());
    System.out.println(res.isSuccess());
    System.out.println(res);
  }
}

```

## 二、签约相关

> 在 module.sign 目录下

### 1. 快捷发起签署

> 以企业和个人签约为例，若是其他复杂场景，可自行构造请求

```java
// 此处可前往查看测试代码
public class SignFlowCreateByFileTest {

  public static void main(String[] args) {
    // 模拟获取合同文件
    ByteArrayOutputStream contract = getFile();

    // 快速创建合同，若想自由控制，可进入 SignFlowUtil.create 参考
    SignFlowUtil.SignFlowRes res =
      SignFlowUtil.create(
        eSignClient,
        SignFlowDto.builder()
          .contractName("自由职业者协议.docx")
          .contractFile(contract)
          .orgSignKeyword("甲方：")
          .psnSignKeyword("乙方（自由职业者）：")
          .orgSealId("c889d40b-5125-4dd5-9533-fbe139d536a1")
          .orgSealPagingAll(true)
          .psnFullName("黄**")
          .psnPhone("15080***360")
          .psnSealStyles("0") // 只能手写
          .build());
    // 返回流程id：signFlowId，签约链接：url
    System.out.println(res);
  }
}

```

### 2.快捷查询结果

```java
public class SignFlowQueryDetailTest {
  public static void main(String[] args) {
    // 流程id
    String signFlowId = "ab9ad219faf44e79b918*******fdabdc";

    // 快速查询流程，若想查询更多信息，可进入 SignFlowUtil.query 参考
    SignFlowUtil.SignFlowQuery res = SignFlowUtil.query(eSignClient, signFlowId);
    System.out.println(res);
  }
}

```


## 三、认证相关接口

> 在 module.identity 目录下

- auth 用户认证
  - auth.individual 个人用户认证
  - auth.organization 企业用户认证
- verify 信息比对能力
  - verify.individual 个人信息比对
  - verify.organization 企业信息比对
- flow 通用认证流程查询
