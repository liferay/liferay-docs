---
header-id: get-started-discover-the-api
---

# はじめに：APIの検出

[TOC levels=1-4]

Webサービスの使用を開始するには、まずサービスの場所（例: サービスカタログ）、呼び出すことができる操作、および呼び出し方法を把握しておく必要があります。
@product@のヘッドレスREST APIでは[OpenAPI](https://en.wikipedia.org/wiki/OpenAPI_Specification)（元々は、Swaggerとして知られていました）を活用するため、サービスカタログは必要ありません。APIの残りの部分を検出する、OpenAPIプロファイルさえ分かっていたら大丈夫です。

@product@のヘッドレスAPIは、SwaggerHub（[`https://app.swaggerhub.com/organizations/liferayinc`](https://app.swaggerhub.com/organizations/liferayinc)）で入手できます。
各APIには、SwaggerHub内に独自のURLがあります。たとえば、[`https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0`](https://app.swaggerhub.com/apis/liferayinc/headless-delivery/v1.0)では配信APIの定義にアクセスできます。

また、各OpenAPIのプロファイルは、以下のスキーマに従って、ポータルインスタンスにも動的にデプロイされます。

    http://[host]:[port]/o/[insert-headless-api]/[version]/openapi.yaml

たとえば、`8080`番ポートで@product@をローカルで実行している場合、ヘッドレス配信APIを検出するためのホームURLは以下のとおりです。

    http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml

このURLにアクセスするには、ログインする必要があります。あるいは、Basic認証およびブラウザを使用したり、[Postman](https://www.getpostman.com)、[Advanced REST Client](https://install.advancedrestclient.com/install)などのその他ツール、またはシステムコンソールの`curl`コマンドを使用する必要があります。

分かりやすくするために、このドキュメンテーションの例では`curl`コマンドを使用し、`8080`番ポートでローカルに実行されている@product@インスタンスに要求を送信します。

以下の`curl`コマンドを実行して、ホームURLにアクセスします。

    curl http://localhost:8080/o/headless-delivery/v1.0/openapi.yaml -u test@liferay.com:test

すると、以下のような応答が返されます。

```yaml
openapi: 3.0.1
info:
  title: Headless Delivery
  version: v1.0
paths:
  /v1.0/blog-posting-images/{blogPostingImageId}:
    get:
      tags:
      - BlogPostingImage
      operationId: getBlogPostingImage
      parameters:
      - name: blogPostingImageId
        in: path
        required: true
        schema:
          type: integer
          format: int64
      responses:
        default:
          description: default response
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/BlogPostingImage'
(...)
```

この応答はOpenAPI 3.0の構文に従って、返されるAPIとスキーマのエンドポイント（URL）を指定しています。また、[Swagger Editor](https://editor.swagger.io)などのOpenAPIエディタでOpenAPIのプロファイルを開くこともできます。
このエディタを使用して、ドキュメンテーションとパラメータを検査し、APIにリクエストを送信できます。

他にも、クライアントジェネレーターや、バリデータ、パーサなど、OpenAPIをサポートするツールは多数あります。ツールのリストについては、[OpenAPI.Tools](https://openapi.tools/)を参照してください 。OpenAPIを活用することで、標準サポート、豊富な[ドキュメンテーション](https://swagger.io/docs/)、および業界全体の規則が提供されます。

## 関連トピック

[Get Started: Invoke a Service](/docs/7-1/tutorials/-/knowledge_base/t/get-started-invoke-a-service)
