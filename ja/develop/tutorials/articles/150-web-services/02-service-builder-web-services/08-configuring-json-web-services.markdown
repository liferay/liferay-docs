---
header-id: configuring-json-web-services
---

# JSON Webサービスの設定方法

[TOC levels=1-4]

JSON Webサービスは、Liferayではデフォルトで有効になっています。それらを無効にする必要がある場合は、このポータルプロパティ設定を`portal-ext.properties`ファイルで以下のように指定します。

    json.web.service.enabled=false

このチュートリアルでは、LiferayインスタンスでJSON Webサービスがどのように機能するかを正確に調整するために使用することができる、その他のプロパティを紹介します。これらのプロパティとその他のプロパティは 、[ポータルプロパティのリファレンスドキュメンテーション](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)にあります。
前述のプロパティと同様に、`portal-ext.properties`ファイルでポータルプロパティを設定する必要があります。

まずは、JSON WebサービスをAPIページで検出可能にするかどうかを設定する方法について説明します。

## 発見可能性

デフォルトでは、JSON WebサービスはAPIページ（`http://[address]:[port]/api/jsonws`）を介して検出されます。これを無効にするには、以下のプロパティを設定します。

    jsonws.web.service.api.discoverable=false

次に、HTTPメソッドを無効にする方法を説明します。

## HTTPメソッドの無効化

StrictのHTTPメソッドモードが有効な場合、サービスで使用されるHTTPメソッドに基づいてWebサービスアクセスをフィルタリングすることができます。たとえば、以下のようにしてLiferayインスタンスのJSON Webサービスを`GET`以外のHTTPメソッドを無効にし、読み取り専用モードで動作するように設定します。

    jsonws.web.service.invalid.http.methods=DELETE,POST,PUT

この設定では、HTTPメソッドの`DELETE`、`POST`、または`PUT` を使用するすべてのリクエストが無視されます。

次に、公開されたJSON APIへの公開アクセスを制限する方法について説明します。

## StrictモードのHTTPメソッド

すべてのJSON Webサービスは、`GET`あるいは`POST`のHTTPメソッドにマッピングされます。サービスメソッド名が`get`、`is`または`has`で始まる場合、サービスは読み取り専用であることが想定され、`GET`メソッドにバインドされます。それ以外の場合は、`POST`にバインドされます。

デフォルトでは、Liferayはサービスコールを呼び出すときに`HTTP`メソッドをチェックしません。サービスコールは、あらゆるHTTPメソッドを使用してサービスを呼び出すことができるnon-strictのHTTPメソッドモードで動作するためです。Strictモードが必要な場合は、以下のように設定します。

    jsonws.web.service.strict.http.method=true

Strictモードを使用する場合、正しいHTTPメソッドを使用してサービスメソッドを呼び出す必要があります。Strictモードが有効になっている場合でも、HTTPメソッドは無効にする必要があります。これに関して、次で詳しく説明します。

## 公開アクセスの制御

各サービスメソッドは、指定したユーザーが選択したアクションを呼び出す権限を持っているかどうかを把握しています。セキュリティが心配な場合は、特定のJSON Webサービスパスを明示的に許可、または制限することにより、公開されたJSON APIへのアクセスを制限できます。

`jsonws.web.service.paths.includes`プロパティは、許可されているJSON Webサービスのアクションパスのパターンを示します。サービスアクションパスを許可するには、空白のパターンを設定します。

`jsonws.web.service.paths.excludes`プロパティは、`jsonws.web.service.paths.includes`に設定されたパターンのいずれかに一致していても許可されていないJSON Webサービスアクションパスのパターンを示します。

これらのプロパティはワイルドカードをサポートしていることに注意してください。たとえば、`jsonws.web.service.paths.includes=get*,has*,is*`を設定すると、Liferayはすべての読み取り専用のJSONメソッドを公開アクセス可能にし、他のすべてのJSONメソッドは無効になっています。公開されているすべてのメソッドへのアクセスを無効にするには、`=`記号の右側を空のままにします。すべての公開メソッドへのアクセスを有効にするには、`*`を指定します。パスが`jsonws.web.service.paths.includes`プロパティと`jsonws.web.service.paths.excludes`プロパティの両方に一致する場合は、`jsonws.web.service.paths.excludes`プロパティが優先されることに注意してください。

## 関連トピック

[Registering JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/registering-json-web-services)

[Creating Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)

[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)
