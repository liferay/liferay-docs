---
header-id: registering-json-web-services
---

# JSON Webサービスの登録

[TOC levels=1-4]

Liferayの開発者は、*Service Builder*というツールを使用してサービスを構築します。
Service Builderでサービスを構築すると、すべてのリモート対応サービス（つまり、`remote-service="true"`のプロパティを持つ`service.xml`エンティティ）がJSON Webサービスとして公開されます。リモート対応のサービス用に、それぞれの`*Service.java`インターフェイスが作成されると、クラスレベルで`@JSONWebService`アノテーションがインターフェイスに追加されます。そして、インターフェイスのパブリックメソッドがすべて登録され、JSON Webサービスとして利用可能になります。

Liferayは、`@Component`アノテーションまたはリモートサービスの`*BundleActivator`クラスに登録されているすべてのOSGiバンドルをスキャンします。これにより、`@JSONWebService`アノテーションを使用する各クラスが調べられ、メソッドがJSON WebサービスAPIを介して公開されます。

| ** 注：**Liferayの開発者は、*Service Builder*を使用して| JSONから自動的にサービスを公開しています。これまでにService Builderを使用したことがない場合は、| [Service Builderのチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/what-is-service-builder)| を参照してください。

次に、アプリケーションのリモートサービスをJSON Webサービスとして登録する方法について説明します。Liferayはこの方法と同じメカニズムを使用しているため、LiferayのリモートサービスはすぐにJSON Webサービスとして公開されます。

## アプリのJSON Webサービスの登録

たとえば、`SupraSurf`という名前のアプリケーションに、リモートサービスとして公開したいサービスがあるとします。`SurfBoard`エンティティで`remote-service`属性を有効にした後、サービスを再構築します。Service Builderは`SurfBoardService`インターフェースを再作成し、`@JSONWebService`アノテーションにそれを追加します。
このアノテーションは、インターフェイスの公開メソッドがJSON Webサービスとして公開されることをLiferayに伝え、アプリケーションのJSON APIの一部にします。そして、Liferayインスタンスを起動してから、アプリをLiferayにデプロイします。

Liferayインスタンスからアプリケーションのサービス登録に関するフィードバックを取得するには、アプリケーションの情報メッセージ（つまり、`INFO ...`メッセージ）をログに記録するようにインスタンスを設定します。詳細については、Liferayの[logging system](/docs/7-1/user/-/knowledge_base/u/server-administration)に関するチュートリアルを参照してください。

LiferayのJSON Webサービスの登録プロセスをテストするには、アプリケーションのサービスに簡単なメソッドを追加します。`*ServiceImpl`クラスを編集して、以下のメソッドを追加してください。

    public String helloWorld(String worldName) {
    return "Hello world: " + worldName;
    }

サービスを再構築し、アプリのモジュールを再デプロイします。これで、JSONを介してこのサービスメソッドを呼び出すことができます。これを行う手順については、[こちら](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-web-services)にリストされているJSON呼び出しのチュートリアルを参照してください。

同じメカニズムによって、Liferay独自のサービスも登録されます。サービスはデフォルトで有効になっているため、設定する必要はありません。

次に、リモートサービス用にマップされたURLを作成して、思い通りにアクセスできるようにする方法を説明します。

## マッピングおよび命名規則

以下の命名規則に従って、公開されたサービスのマッピングされたURLを作成することができます。

    http://[server]:[port]/api/jsonws/[context-path].[service-class-name]/[service-method-name]

最後の3つの括弧で囲まれた項目をより詳しく見る必要があります。

- `context-name`は、アプリのコンテキスト名（例: `suprasurf`）です。その値は、`@OSGiBeanProperties`アノテーションの`json.web.service.context.path`プロパティを介して指定されます。たとえば、Liferay Webコンテンツの記事の場合、Liferayの`JournalArticleService`クラスにはこの注釈（など）が含まれます。

       @OSGiBeanProperties(property =  {
       "json.web.service.context.name=journal", "json.web.service.context.path=JournalArticle"}, service = JournalArticleService.class)
   
- `service-class-name`は小文字のサービスのクラス名から、`Service`または`ServiceImpl`の接尾辞を除いて作成されます。たとえば、`surfboard`を`SurfBoardService`クラスの`app-context-name`として指定すると、
- `service-method-name`は、キャメルケースを小文字に変換し、ダッシュ（`-`）を使用して単語を区切ることにより、サービスのメソッド名から作成されます。

以下の例では、カスタムサービスとLiferayサービスの両方の命名規則を使用してサービスメソッドのURLをマッピングすることにより、これらの命名規則を示しています。

カスタムサービスメソッドの場合、URLは以下のようになります。

    http://localhost:8080/api/jsonws/suprasurf.surfboard/hello-world

URLのコンテキスト名の部分に注意してください。Liferayの場合も同様です。Liferayのサービスメソッドは以下のとおりです。

    @JSONWebService
    public interface UserService {
    public com.liferay.portal.model.User getUserById(long userId) {...}

そして、LiferayサービスメソッドのURLは、以下のとおりです。

    http://localhost:8080/api/jsonws/user/get-user-by-id

各サービスメソッドは、1つのHTTPメソッドタイプにバインドされます。`get`、`is`、または`has`で始まる名前の任意のメソッドは読み取り専用メソッドで、デフォルトでは*GET HTTP*メソッドとしてマッピングされています。その他のすべてのメソッドは、*POST HTTP*メソッドとしてマッピングされます。

[http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws)のJSON Webサービスリストでメソッドを選択すると、`http://[server]:[port]/api/jsonws`に続くHTTPメソッドURLの一部が画面の上部に表示されます。

リモートサービスリクエストではユーザーの現在のセッションに関連付けられた認証資格情報が使用できるため、便利です。

次は、メソッドがサービスとして公開されるのを防ぐ方法について説明します。

## メソッドを無視する

メソッドがサービスとして公開されないようにするには、以下のオプションを使用してメソッドにアノテーションを付けます。

    @JSONWebService(mode = JSONWebServiceMode.IGNORE)

このアノテーションを持つメソッドは、JSON WebサービスAPIの一部にはなりません。
次は、カスタムHTTPメソッドとURL名の定義方法を説明します。

## HTTPメソッドとURL名

メソッドレベルでは、カスタムHTTPメソッド名とURL名を定義できます。以下のようなアノテーションを使用するだけでできます。

    @JSONWebService(value = "add-board-wow", method = "PUT")
    public boolean addBoard(

この例では、アプリケーションのサービスメソッドである`addBoard`は`add-board-wow`という名前のURLメソッドにマッピングされます。完全なURLは`http://localhost:8080/api/jsonws/suprasurf.surfboard/add-board-wow`であり、HTTP PUTメソッドを使用してアクセスすることができます。

JSON WebサービスアノテーションのURLメソッド名がスラッシュ（`/`）で始まる場合は、サービスURLの作成には以下のようにメソッド名のみが使用され、クラス名は無視されます。

    @JSONWebService("/add-something-very-specific")
    public boolean addBoard(

同様に、以下のようにクラスレベルのアノテーションに値を設定することにより、URLのクラス名の部分を変更することができます。

    @JSONWebService("sbs")
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl {

これにより、サービスのすべてのメソッドは、デフォルトのクラス名の`surfboard`ではなく、URLクラス名の`sbs`にマッピングされます。

次に、マニュアルでの登録でメソッドを公開する別のアプローチを説明します。

## マニュアル登録モード

これまでの方法では、特定のメソッドを隠しつつ、ほとんどのサービスメソッドを公開する場合を前提としていました（これを*ブラックリスト*アプローチといいます）。逆に、公開するメソッドのみを明示的に指定する場合もあります（*ホワイトリスト*アプローチといいます）。このアプローチは、*マニュアルモード*でクラスレベルのアノテーションを指定することで可能になります。そして、公開したいメソッドのみにアノテーションを付けます。

    @JSONWebService(mode = JSONWebServiceMode.MANUAL)
    public class SurfBoardServiceImpl extends SurfBoardServiceBaseImpl{
    ...
    @JSONWebService
    public boolean addBoard(

上記では、`addBoard`メソッドと`@JSONWebService`でアノテーションが付けられているその他のメソッドのみがJSON WebサービスAPIの一部になりました。つまり、これら以外のメソッドは、すべてAPIから除外されます。

## 関連トピック

[Invoking JSON Web Services](/docs/7-1/tutorials/-/knowledge_base/t/invoking-json-web-services)

[JSON Web Services Invoker](/docs/7-1/tutorials/-/knowledge_base/t/json-web-services-invoker)
