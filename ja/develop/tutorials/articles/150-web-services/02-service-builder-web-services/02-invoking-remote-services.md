---
header-id: invoking-remote-services
---

# リモートサービスの呼び出し

[TOC levels=1-4]

インストールされているLiferayアプリケーションのリモートサービスは、ローカルサービスを呼び出す場合と同じ方法で呼び出すことができます。これを「ローカルでリモートサービスを呼び出す」と表現することもできます。対応するローカルサービスの代わりにリモートサービスを呼び出す理由は、リモートサービスの権限チェックを利用するためです。ここでは、以下のよくあるシナリオについて考えてみます。

- 特定のサービスに対して、ローカルサービス実装とリモートサービス実装の両方が作成されたとします。
- リモートサービスは権限チェックを実行してから、対応するローカルサービスを呼び出します。

上記のシナリオでは、ローカルサービスの代わりにリモートサービスを呼び出すのがベストプラクティスです。こうすることで、パーミッションチェックコードを複製する必要がなくなるからです。また、これは[LiferayのWebコンテンツアプリ](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/journal/journal-service/src/main/java/com/liferay/journal/service/impl/JournalArticleServiceImpl.java)のサービスにおけるプラクティスでもあります。
`addArticle`メソッドは、権限チェックの後で`journalArticleLocalService.addArticle`を起動することに注意してください。

もちろん、リモートサービスを作成する主な理由は、リモートサービスを呼び出すためです。Service Builderは、JSON APIとSOAPの両方を介して、プロジェクトのリモートWebサービスを公開することができます。デフォルトでは、エンティティの`remote-service`を`true`に設定してService Builderを実行すると、プロジェクトのJSON WebサービスのAPIが作成されます。使いやすいWebインターフェースを介して、プロジェクトのJSONベースのRESTfulサービスにアクセスできます。

## Liferayサービスをリモートで呼び出す

デフォルトのLiferayサービスの多くは、Webサービスとして利用可能です。Liferayでは、SOAPおよびJSON Webサービスを介してWebサービスを公開しています。8080番ポートでLiferayをローカルで実行している場合は、以下のURLにアクセスして、LiferayのデフォルトのSOAP Webサービスを参照してください。

    http://localhost:8080/api/axis

LiferayのデフォルトのJSON Webサービスを参照するには、以下のURLにアクセスしてください。

    http://localhost:8080/api/jsonws/

デフォルトでは、コンテキストパスは`/`に設定されています。つまり、コアのLiferayサービスがリストされるようになっています。*http://localhost:8080/api/jsonws/*のページでは、`portal`コンテキスト内のJSON Webサービスがデフォルトで表示されます。*[Context Name]*というセレクターメニューで、別のコンテキストを選択できます。たとえば、*[Context Name]*で`journal`を選択すると、LiferayのWebコンテンツアプリにあるJSON Webサービスが表示されます（このアプリのエンティティは、すべて`Journal*`で始まります）。また、ダイレクトURLを介してコンテキストのJSON Webサービスにアクセスすることもできます。たとえば、WebコンテンツアプリのJSON WebサービスのURLは、[http://localhost:8080/api/jsonws?contextName=journal](http://localhost:8080/api/jsonws?contextName=journal)です。

| **重要：**Liferayサービスをリモートで呼び出すには、Liferayインスタンスが| リモートWebサービスのアクセスを許可するように設定されている必要があります。詳細については、| [Understanding Liferay's Service Security Model](/docs/7-1/tutorials/-/knowledge_base/t/service-security-layers)| のチュートリアルを参照してください。

各エンティティの利用可能なサービスメソッドは、JSON Webサービスのページ左側にある列に表示されるので、サービスメソッドの詳細を表示するには、そこをクリックします。サービスの`*Impl`クラスへの完全なパッケージパスが、メソッドのパラメータ、戻り値の型、および考えられる例外とともに表示されます。また、そのページからサービスを呼び出すこともできます。たとえば、`portal`コンテキストで`AnnouncementsEntry`エンティティの`get-entry`メソッドをクリックすると、サービスメソッドの詳細ページが表示され、サービスを呼び出すこともできます。

![図1：エンティティのリモートサービスメソッドのJSON Webサービスページでも、対象のサービスを呼び出すことができます。](../../../images/jsonws-details.png)

`get-entry`メソッドを呼び出すために唯一必要となるパラメータは、`entryId`です。このWebサービスを呼び出すには、`entryId`フィールドにアナウンスメントエントリのIDを入力し、*[呼び出し]*をクリックします。Liferayは、それぞれの呼び出しからサービス呼び出しが成功したか失敗したかなどを示すフィードバックを返します。この方法でリモートサービスを呼び出すことは、アプリのリモートサービスをテストするのに適しています。

また、Service Builderは、Apache Axisを使用することで、SOAPを介してプロジェクトのWebサービスを利用可能にすることもできます。`*-service`プロジェクトのWSDD（Webサービスデプロイメント記述子）を構築し、プロジェクトのモジュールをデプロイすると、Liferayサーバーでそのサービスを使用できるようになります。[Creating Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)のチュートリアルで説明されているように、ブラウザを使用して、LiferayおよびLiferayアプリケーションのSOAPサービスを表示することができます。

ブラウザでSOAPサービスを表示すると、Liferayはすべてのエンティティで利用可能なサービスをリストし、WSDLドキュメントへのリンクを提供します。たとえば、ユーザーサービスのWSDLリンクをクリックすると、以下のURLに移動します。

    http://localhost:8080/api/axis/Portal_UserService?wsdl

このWSDLドキュメントには、エンティティのSOAP Webサービスがリストされています。WebサービスのWSDLが使用できるようになると、SOAP WebサービスクライアントならどれでもそのWSDLにアクセスできるようになります。SOAP Webサービスのクライアント実装の例は、[SOAP Web Services](/docs/7-1/tutorials/-/knowledge_base/t/soap-web-services)のチュートリアルを参照してください。

LiferayのWebサービスは、クライアントアプリケーションによって呼び出されるように設計されています。
そして、LiferayのWebサービスAPIは、非ポートレットクライアントや非Javaクライアントを含む、さまざまな種類のクライアントからアクセスできます。LiferayのJSON Webサービスにアクセスできるクライアントアプリケーションの開発方法については、[Invoking JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-json-web-services)のチュートリアルを参照してください。そして、LiferayのSOAP Webサービスにアクセスするクライアントアプリケーションの開発方法については、[SOAP Web Services](/docs/7-1/tutorials/-/knowledge_base/t/soap-web-services)のチュートリアルを参照してください。また、独自のアプリケーション用のリモートWebサービスを作成する方法については、[Creating Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)のチュートリアルを参照してください。

Liferayサービスの詳細については、Liferay Portal CE Javadocs（[@platform-ref@/7.0-latest/javadocs/](@platform-ref@/7.1-latest/javadocs/)）を参照してください。

## 関連トピック

[Invoking JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-json-web-services)

[JSON Web Services Invoker](/docs/7-1/tutorials/-/knowledge_base/t/json-web-services-invoker)

[SOAP Web Services](/docs/7-1/tutorials/-/knowledge_base/t/soap-web-services)

[Creating Remote Services](/docs/7-1/tutorials/-/knowledge_base/t/creating-remote-services)
