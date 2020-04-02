---
header-id: json-web-services-invoker
---

# JSON Web Services Invoker

[TOC levels=1-4]

JSON Webサービスでは、パラメータ付きのサービスメソッドにリクエストを送信し、結果をJSONオブジェクトとして受け取ります。この作りを変更することができます。このチュートリアルでは、その方法について説明します。

例えば、`User`とそれに対応する`Contact`という2つの関連オブジェクトで作業しているとします。通常、最初にユーザーサービスを呼び出してユーザーオブジェクトを取得し、そのオブジェクトのコンタクトIDを使用してコンタクトサービスを呼び出します。ここでは、2つのHTTPリクエストを送信して、2つの異なるJSONオブジェクトを取得しています。ユーザーオブジェクトには、コンタクト情報がないからです（つまり、`user.contact`がありません）。このアプローチでは、パフォーマンス（2つのHTTP呼び出しの送信）と使いやすさ（2つのオブジェクト間の関係をマニュアルで管理する）が最適な状態ではありません。この非効率な状況を改善するためのツールがJSON Web Service Invokerです。

LiferayのJSON Web Service Invokerは、JSON Webサービスの使用を最適化するのに役立ちます。

## Invokerでの単純なサービスの呼び出し

Invokerは、以下の固定アドレスからアクセスできます。

    http://[address]:[port]/api/jsonws/invoke

ここでは、Invokerのコマンドである`cmd`リクエストパラメータのみが受け入れられます。コマンドリクエストパラメータが欠落している場合、リクエストの本文がコマンドとして使用されます。そのため、リクエストパラメータの`cmd`またはリクエスト本文を使用して、コマンドが指定できます。

Invokerコマンドは、JSON Webサービスの呼び出し方法と結果の管理方法を記述するプレーンなJSONマップです。以下は、Invokerを使用したシンプルなサービスを呼び出す方法の例です。

    {
    "/user/get-user-by-id": {
    "userId": 123,
    "param1": null
    }
    }

サービスコールは、JSONマップとして定義されます。キーはサービスURL（呼び出されるサービスメソッド）を指定し、キーの値はサービスパラメータ名（`userId`および`param1`）とそれらの値のマップを指定します。上記の例では、取得されたユーザーはJSONオブジェクトとして返されます。コマンドはJSON文字列であるため、キーワードの`null`を明示的に使用するか、パラメータ名の前にダッシュを置いて値を空のままにする（例: `"-param1": ''`）ことで、null値を指定できます。

上記の例のInvokerは、以下の標準のJSON Webサービスの呼び出しとまったく同じ方法で、関数を呼び出します。

    /user/get-user-by-id?userId=123&-param1

`8080`番ポートでLiferayをローカルで実行している場合、JSON Webサービスを呼び出す方法は以下のとおりです。

1. 資格情報を収集します。以下に例を示します。

   - Eメール：`test@liferay.com`
   - ユーザーID：`20127`
   - 認証トークン：`htXjvt5d`

2. 以下で、サービスを呼び出します。

       http://localhost:8080/api/jsonws/invoke?cmd={%22/user/get-user-by-id%22:{%22userId%22:20172}}&p_auth=htXjvt5d
   
このURLは、下記のJSONマップを使用します。`cmd`URLパラメータを使用して、URLで提供されることに注意してください。

    {
    "/user/get-user-by-id": {
    "userId": 20172
    }
    }

このURLでは、二重引用符はURLエンコードされています。ユーザーIDを見つけるには、*[アカウント]* → *[アカウント設定]*にある[ユーザーメニュー]を確認します。`p_auth`認証トークンを見つけるには、[LiferayのJSON WebサービスAPIページ](http://localhost:8080/api/jsonws)に移動し、リスト内の任意のメソッドをクリックします。`p_auth`トークンの値は、選択されたAPIメソッドのその他のパラメータとともに、[実行]ヘッダーの下に表示されます。

JSON構文を使用して、オブジェクトおよび配列の値をパラメータとして提供します。オブジェクトの値を指定するには、中括弧（`{`および`}`）を使用します。配列の値を指定するには、角括弧（`[`および`]`）を使用します。

以下は、上記と同じクレデンシャルトークンを使用して、配列をパラメータとして渡したい場合の例です。以下の例では、`20783`および`20784`という語彙IDを持つ2つの語彙を使用しています。

    http://localhost:8080/api/jsonws/invoke?cmd={%22/assetvocabulary/get-vocabularies%22:{%22vocabularyIds%22:[20783,20784]}}&p_auth=htXjvt5d

このURLでは、以下のJSONマップを使用しています。

    {
    "/assetvocabulary/get-vocabularies": {
    "vocabularyIds": [20783,20784]
    }
    }

前の例と同様に、URL内の二重引用符はURLエンコードされます。また、`vocabularyIds`パラメータは配列であるため、その値はJSON配列として提供されます。

最後に、以下は配列をパラメータとして含むオブジェクトを渡す方法を示すLiferayのJSON Web Service Invokerの例です。

    http://localhost:8080/api/jsonws/invoke?cmd={%22/user/add-user%22:{%22companyId%22:20127,%22autoPassword%22:false,%22password1%22:%22test%22,%22password2%22:%22test%22,%22autoScreenName%22:false,%22screenName%22:%22joe.bloggs%22,%22emailAddress%22:%22joe.bloggs@liferay.com%22,%22facebookId%22:0,%22openId%22:%22%22,%22locale%22:%22en_US%22,%22firstName%22:%22Joe%22,%22middleName%22:%22T%22,%22lastName%22:%22Bloggs%22,%22prefixId%22:0,%22suffixId%22:0,%22male%22:true,%22birthdayMonth%22:1,%22birthdayDay%22:1,%22birthdayYear%22:1970,%22jobTitle%22:%22Tester%22,%22groupIds%22:null,%22organizationIds%22:null,%22roleIds%22:null,%22userGroupIds%22:null,%22sendEmail%22:false,%22serviceContext%22:{%22assetTagNames%22:[%22test%22]}}}&p_auth=htXjvt5d

このURLでは、以下のJSONマップを使用しています。

    {
    "/user/add-user": {
    "companyId": 20127,
    "autoPassword": false,
    "password1": "test",
    "password2": "test",
    "autoScreenName": false,
    "screenName": "joe.bloggs",
    "emailAddress": "joe.bloggs@liferay.com",
    "facebookId": 0,
    "openId": "",
    "locale": "en_US",
    "firstName": "Joe",
    "middleName": "T",
    "lastName": "Bloggs",
    "prefixId": 0,
    "suffixId": 0,
    "male": true,
    "birthdayMonth": 1,
    "birthdayDay": 1,
    "birthdayYear": 1970,
    "jobTitle": "Tester",
    "groupIds": null,
    "organizationIds": null,
    "roleIds": null,
    "userGroupIds": null,
    "sendEmail": false,
    "serviceContext": {"assetTagNames":["test"]}
    }
    }

この例では、`serviceContext`は配列を含むオブジェクトであり、`assetTagNames`という配列が含まれています。

もちろん、JSON Web Service Invokerは以下のようにプラグインメソッドへの呼び出しも処理します。

    {
    "/suprasurf/hello-world": {
    "worldName": "Mavericks"
    }
    }

上記のコードは、（架空の）SupraSurfアプリケーションのリモートサービスを呼び出します。

変数を使用して、サービス呼び出しから返されたオブジェクトを参照できます。変数名はドル記号（`$`）で始める必要があります。前の例では、サービスコールは変数に割り当てることができるユーザーオブジェクトを返しました。

    {
    "$user = /user/get-user-by-id": {
    "userId": 123,
    }
    }

`$user`変数は、返されたユーザーオブジェクトを保持しています。`$user.contactId`構文を使用して、ユーザーのコンタクトIDを参照できます。

次に、ネストされたサービスコールを使用して、2つの関連オブジェクトからの情報を結合する方法を確認します。

## サービスコールのネスティング

ネストされたサービスコールを使用すると、関連するオブジェクトからの情報をJSONオブジェクトにバインドできます。同じHTTPリクエスト内で他のサービスを呼び出し、返されたオブジェクトを便利な方法でネストできます。以下は、ネストされたサービスコールの動作です。

    {
    "$user = /user/get-user-by-id": {
    "userId": 123,
    "$contact = /contact/get-contact-by-id": {
    "@contactId": "$user.contactId"
    }
    }
    }

このコマンドは、2つのサービスコールを定義します。2番目のサービスコールから返されるコンタクトオブジェクトは、`contact`という名前のプロパティとして、ユーザーオブジェクトにネストされました（つまり、挿入されました）。

以上が、単一のHTTPリクエストを使用して、前述のネストされたサービスコールを行うときに、Invokerがバックグラウンドで実行する処理の詳細です。

- まとめると、まず最初に、Invokerは`/user/get-user-by-id`にマップされたJavaサービスを呼び出し、`userId`パラメータの値を渡します。
- 次に、結果のユーザーオブジェクトが変数`$user`に割り当てられます。
- そして、ネストされたサービスコールが呼び出されます。
- Invokerは、`contactId`パラメータと、オブジェクト`$user`の`$user.contactId`値を使用して、`/contact/get-contact-by-id`にマップされたJavaサービスを呼び出します。
- 結果のコンタクトオブジェクトは、`$contact`変数に割り当てられます。
- 最後に、Invokerは`$contact`によって参照されるコンタクトオブジェクトを`contact`という名前のユーザーオブジェクトのプロパティに挿入します。

| ** 注：**既存の変数から値を取得するパラメータに、フラグを立てる必要があります。パラメータに| フラグを立てるには、パラメータ名の前に`@`という接頭辞を挿入します。

次は、サービスを呼び出す際に必要なプロパティのみが返されるように、オブジェクトプロパティのフィルタリングについて説明します。

## 結果のフィルタリング

Liferayのモデルオブジェクトの多くは、プロパティが豊富にあります。ビジネスロジックに必要なオブジェクトのプロパティが少ししかない場合は、オブジェクトのすべてのプロパティを返すWebサービスの呼び出しを行うと、ネットワークの帯域幅が無駄になります。JSON Web Service Invokerを使用すると、プロパティのホワイトリストを定義できるので、オブジェクトでリクエストする特定のプロパティのみがWebサービスの呼び出しから返されます。必要なプロパティをホワイトリストに登録する方法は以下のとおりです。

    {
    "$user[firstName,emailAddress] = /user/get-user-by-id": {
    "userId": 123,
    "$contact = /contact/get-contact-by-id": {
    "@contactId": "$user.contactId"
    }
    }
    }

この例では、返されたユーザーオブジェクトは`firstName`と`emailAddress`プロパティのみです（`contact`プロパティもまだあります）。ホワイトリストのプロパティを指定するには、変数名の直後に角括弧（例: `[whiteList]`）でプロパティを配置します。

次に、バッチ呼び出しを行う方法について説明します。

## バッチ呼び出しを行う

サービスコールをネストする場合、1つのHTTPリクエストで複数のサービスを呼び出します。これは、サービスコールの結果から関連情報を収集するのに役立ちますが、単一のリクエストを使用して、サービスコールをまとめてバッチ処理することで複数の無関係なサービスコールを呼び出してパフォーマンスを向上させることもできます。これを行うには、コマンドのJSON配列を渡します。

    [
    {/* first command */},
    {/* second command */}
    ]

結果は、各コマンドの結果が入力されたJSON配列です。コマンドは、単一のHTTPリクエストで次々にまとめて呼び出されます。

 これで、LiferayのJSON Web Service Invokerを使用して、LiferayへのJSONの呼び出しを簡素化する方法は以上です。

## 関連トピック

[Invoking Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-remote-services)

[Invoking JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-json-web-services)
