---
header-id: invoking-liferay-services
---

# Liferayサービスの呼び出し

[TOC levels=1-4]

@product@は、すぐに使用できる多くのWebサービスを提供しています。使用可能なWebサービスの総合リストを確認するには、`http://localhost:8080/api/jsonws`にアクセスしてください（localhostがポート8080で実行されていると仮定しています）。Service Builderで生成された独自のJSON Webサービスをデプロイしている場合は、[これらのガイドラインに従って](/docs/7-1/tutorials/-/knowledge_base/t/registering-json-web-services#mapping-and-naming-conventions)呼び出してください。これらのサービスは、単一ページのアプリケーションを作成するのに役立ち、@product@でカスタムのフロントエンドを作成するためにも使用できます。

このチュートリアルでは、JavaScriptを使用してこれらのWebサービスを呼び出す方法を説明します。

## JavaScriptを介したWebサービスの呼び出し

@product-ver@には、多くの便利なユーティリティを備えた`Liferay`と呼ばれるグローバルJavaScriptオブジェクトが含まれています。メソッドの1つが`Liferay.Service`で、このメソッドはJSON Webサービスを呼び出します。

`Liferay.Service`メソッドは、4つの可能な引数を取ります。

**service {string|object}**：呼び出すサービスとしてのキーと、サービス構成オブジェクトとしての値を持つとサービス名またはオブジェクトを指定します。
（必須）

**data {object|node|string}**：サービスに送信するデータを指定します。渡されたオブジェクトがフォームまたはフォーム要素のIDである場合、フォームフィールドはシリアル化され、データとして使用されます。

**successCallback {function}**：サーバーが応答を返したときに実行する関数。JSONオブジェクトを最初のパラメーターとして受け取ります。

**exceptionCallback {function}**：サーバーからの応答にサービス例外が含まれている場合に実行する関数。例外メッセージを最初のパラメーターとして受け取ります。

標準のAJAX要求を使用することと比較した`Liferay.Service`メソッドを使用することの利点の1つは、認証処理が行われることです。

以下に、`Liferay.Service`メソッドの構成例を示します。

    Liferay.Service(
    '/user/get-user-by-email-address',
    {
    companyId: Liferay.ThemeDisplay.getCompanyId(),
    emailAddress: 'test@liferay.com'
    },
    function(obj) {
    console.log(obj);
    }
    );

上記の例は、`companyId`と`emailAddress`を渡すことにより、ユーザーに関する情報を取得します。応答データは、次のJSONオブジェクトに似ています。

    {
    "agreedToTermsOfUse": true,
    "comments": "",
    "companyId": "20116",
    "contactId": "20157",
    "createDate": 1471990639779,
    "defaultUser": false,
    "emailAddress": "test@liferay.com",
    "emailAddressVerified": true,
    "facebookId": "0",
    "failedLoginAttempts": 0,
    "firstName": "Test",
    "googleUserId": "",
    "graceLoginCount": 0,
    "greeting": "Welcome Test Test!",
    "jobTitle": "",
    "languageId": "en_US",
    "lastFailedLoginDate": null,
    "lastLoginDate": 1471996720765,
    "lastLoginIP": "127.0.0.1",
    "lastName": "Test",
    "ldapServerId": "-1",
    "lockout": false,
    "lockoutDate": null,
    "loginDate": 1472077523149,
    "loginIP": "127.0.0.1",
    "middleName": "",
    "modifiedDate": 1472077523149,
    "mvccVersion": "7",
    "openId": "",
    "portraitId": "0",
    "reminderQueryAnswer": "test",
    "reminderQueryQuestion": "what-is-your-father's-middle-name",
    "screenName": "test",
    "status": 0,
    "timeZoneId": "UTC",
    "userId": "20156",
    "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f"
    }

個々の要求を送信する方法がわかったので、バッチ要求を実行する準備ができました。

## 要求のバッチ処理

`Liferay.Service`メソッドを呼び出すもう1つの方法は、呼び出すサービスのキーとサービス構成オブジェクトの値を持つオブジェクトを渡すことです。

以下に、バッチ要求の構成例を示します。

    Liferay.Service(
    {
    '/user/get-user-by-email-address': {
    companyId: Liferay.ThemeDisplay.getCompanyId(),
    emailAddress: 'test@liferay.com'
    }
    },
    function(obj) {
    console.log(obj);
    }
    );

サービスオブジェクトの配列を渡すことにより、同じ要求で複数のサービスを呼び出すことができます。 次に例を示します。

    Liferay.Service(
    [
    {
    '/user/get-user-by-email-address': {
    companyId: Liferay.ThemeDisplay.getCompanyId(),
    emailAddress: 'test@liferay.com'
    }
    },
    {
    '/role/get-user-roles': {
    userId: Liferay.ThemeDisplay.getUserId()
    }
    }
    ],
    function(obj) {
    // obj is now an array of response objects
    // obj[0] == /user/get-user-by-email-address data
    // obj[1] == /role/get-user-roles data
    
    console.log(obj);
    }
    );

次に、要求をネストする方法を学習します。

## 要求のネスト

ネストされたサービス呼び出しは、関連するオブジェクトからの情報をJSONオブジェクトにバインドします。同じHTTP要求で他のサービスを呼び出して、返されたオブジェクトをネストすることもできます。

変数を使用して、サービス呼び出しから返されたオブジェクトを参照できます。変数名はドル記号（`$`）で始まる必要があります。

このセクションの例では、`/user/get-user-by-id`でユーザーデータを取得し、次にそのサービスから返された`contactId`を使用して、同じ要求で`/contact/get-contact`を呼び出します。

**注**：既存の変数から値を取るパラメーターにフラグを立てる必要があります。パラメーターにフラグを付けるには、パラメーター名の前に`@`プレフィックスを挿入します。

以下に、これらの概念を示す構成例を示します。

    Liferay.Service(
    {
    "$user = /user/get-user-by-id": {
    "userId": Liferay.ThemeDisplay.getUserId(),
    "$contact = /contact/get-contact": {
    "@contactId": "$user.contactId"
    }
    }
    },
    function(obj) {
    console.log(obj);
    }
    );

上記の要求に対する応答データは次のようになります。

    {
    "agreedToTermsOfUse": true,
    "comments": "",
    "companyId": "20116",
    "contactId": "20157",
    "createDate": 1471990639779,
    "defaultUser": false,
    "emailAddress": "test@liferay.com",
    "emailAddressVerified": true,
    "facebookId": "0",
    "failedLoginAttempts": 0,
    "firstName": "Test",
    "googleUserId": "",
    "graceLoginCount": 0,
    "greeting": "Welcome Test Test!",
    "jobTitle": "",
    "languageId": "en_US",
    "lastFailedLoginDate": null,
    "lastLoginDate": 1472231639378,
    "lastLoginIP": "127.0.0.1",
    [...]
    "screenName": "test",
    "status": 0,
    "timeZoneId": "UTC",
    "userId": "20156",
    "uuid": "c641a7c9-5acb-aa68-b3ea-5575e1845d2f",
    "contact": {
    "accountId": "20118",
    "birthday": 0,
    [...]
    "createDate": 1471990639779,
    "emailAddress": "test@liferay.com",
    "employeeNumber": "",
    "employeeStatusId": "",
    "facebookSn": "",
    "firstName": "Test",
    "lastName": "Test",
    "male": true,
    "middleName": "",
    "modifiedDate": 1471990639779,
    [...]
    "userName": ""
    }
    }

要求の処理方法がわかったので、次に結果をフィルタリングする方法を学習します。

## 結果のフィルタリング

サービスによってすべてのプロパティが返されないようにする場合は、プロパティのホワイトリストを定義できます。これによって、オブジェクト内の要求した特定のプロパティのみが返されます。

以下に、プロパティをホワイトリスト化した例を示します。

    Liferay.Service(
    {
    '$user[emailAddress,firstName] = /user/get-user-by-id': {
    userId: Liferay.ThemeDisplay.getUserId()
    }
    },
    function(obj) {
    console.log(obj);
    }
    );

ホワイトリストプロパティを指定するには、変数名の直後にプロパティを角かっこ（例：`[whiteList]`）で囲んで配置します。上記の例は、ユーザーの`emailAddress`と`firstName`のみを要求しています。

以下に、フィルター処理された応答を示します。

    {
    "firstName": "Test",
    "emailAddress": "test@liferay.com"
    }

次に、要求の内部パラメーターに値を入力する方法を学習します。

## 内部パラメーター

オブジェクトパラメーターを渡す場合は、多くの場合、その内部パラメーター（つまり、フィールド）に値を入力する必要があります。

タイプが`ServiceContext`のデフォルトパラメーター`serviceContext`を検討します。 JSON Webサービスへの適切な呼び出しを行うには、場合によっては以下に示すように、`scopeGroupId`などの`serviceContext`フィールドを設定する必要があります。

    Liferay.Service(
    '/example/some-web-service',
    {
    serviceContext: {
    scopeGroupId: 123
    }
    },
    function(obj) {
    console.log(obj);
    }
    );

これで、Liferayサービスを呼び出す方法がわかりました。

## 関連トピック

[@product@ JavaScript Utilities](/docs/7-1/tutorials/-/knowledge_base/t/javascript-utilities)

[Accessing ThemeDisplay Information](/docs/7-1/tutorials/-/knowledge_base/t/liferay-themedisplay)
