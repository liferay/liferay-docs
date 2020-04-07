---
header-id: invoking-liferay-services-in-your-android-app
---

# AndroidアプリでLiferayサービスを呼び出す

[TOC levels=1-4]

Androidプロジェクトで適切なMobile SDKを設定したら、アプリで@product@サービスにアクセスして呼び出すことができます。このチュートリアルでは、これらのサービスを呼び出すために従う必要がある手順を説明します。

1.  セッションを作成します。
2.  呼び出す必要がある@product@サービスをインポートします。
3.  サービスオブジェクトを作成し、サービスメソッドを呼び出します。

一部のサービスコールには特別な処理が必要なため、このチュートリアルではそれらの処理方法も示します。 しかし、最初に、ポータルで@product@のJSON Webサービスを保護する方法を学びます。

## JSON Webサービスの保護

Liferay Mobile SDKは、デフォルトで有効になっている@product@のJSON Webサービスを呼び出します。 Mobile SDKを介して呼び出すWebサービスは、それらの呼び出しが機能するように有効にしておく必要があります。 ただし、呼び出す必要のないWebサービスを無効にすることは可能です。 この手順については、チュートリアル [JSON Webサービスの設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-json-web-services)参照してください。 [サービスアクセスポリシー](/docs/7-1/tutorials/-/knowledge_base/t/service-access-policies) を使用して、アクセス可能なサービスをより詳細に制御することもできます。

## ステップ1：セッションを作成する

セッションは、クライアントとサーバー間の変換状態であり、2つの間の複数の要求と応答で構成されます。 アプリとMobile SDKの間でリクエストを渡すにはセッションが必要です。 ほとんどの場合、ユーザー認証を使用してセッションを作成する必要があります。 セッションの作成に必要なインポートとコードは次のとおりです。

    import com.liferay.mobile.android.auth.basic.BasicAuthentication;
    import com.liferay.mobile.android.service.Session;
    import com.liferay.mobile.android.service.SessionImpl;
    ...
    Session session = new SessionImpl("http://10.0.2.2:8080", 
        new BasicAuthentication("test@example.com", "test"));

`SessionImpl` の引数は、セッションを作成するために使用されます。 最初のパラメーターは、接続しているLiferayインスタンスのURLです。 Android Studioのエミュレーターでアプリを実行している場合、 `http://10.0.2.2:8080` は `http：// localhost：8080`と同等です。 これをサーバーの正しいアドレスに置き換えてください。

| **警告：** 本番環境で管理者の資格情報を使用する場合は注意してください| Liferayインスタンス。サービスを呼び出す権限があります。 確認しないでください|誤ってデータを変更するため。 もちろん、デフォルトの管理者資格情報|本番Liferayインスタンスでは無効にする必要があります。

2番目のパラメーターは、ユーザーの資格情報を含む新しい `BasicAuthentication` オブジェクトを作成します。 Liferayインスタンスで使用される認証方法に応じて、ユーザーの電子メールアドレス、スクリーン名、またはユーザーIDを提供する必要があります。 ユーザーのパスワードも入力する必要があります。 `BasicAuthentication` オブジェクトは、基本認証を使用して各サービスコールを認証するようセッションに指示します。 OAuthプロバイダーポートレットがLiferayインスタンスにデプロイされている限り、Mobile SDKはOAuth認証もサポートします。 Mobile SDKでOAuth認証を使用する方法については、 [OAuthサンプルアプリ](https://github.com/brunofarache/liferay-android-sdk-oauth)ご覧ください。 また、OAuthプロバイダーポートレットは、アクティブなLiferayサブスクリプションをお持ちのお客様のみが利用できることに注意してください。

アプリのサインインビューを作成する場合は、 `SignIn` ユーティリティクラスを使用して、ユーザーが指定した資格情報が有効かどうかを確認できます。

    import com.liferay.mobile.android.auth.SignIn;
    ...
    SignIn.signIn(session, new JSONObjectAsyncTaskCallback() {
    
        @Override
        public void onSuccess(JSONObject userJSONObject) {
            System.out.println("Successful sign-in, user details: " + userJSONObject)
        }
    
        @Override
        public void onFailure(Exception e) {
            e.printStackTrace();
        }
    
    });

Mobile SDKはサーバーとの永続的な接続またはセッションを保持しないことに注意してください。 各リクエストはユーザーの資格情報とともに送信されます（OAuthを使用する場合を除く）。 ただし、 `SignIn` クラスは、サインインが成功した後にユーザー情報を返す方法を提供します。

次に、これが可能な限られたケースで認証されていないセッションを作成する方法を示します。

### 認証されていないセッションの作成

場合によっては、ユーザーの資格情報なしで `セッション` インスタンスを作成することが可能です。 ただし、ほとんどのLiferayリモートメソッドは、認証されていないリモート呼び出しを受け入れません。 認証されていないセッションで呼び出しを行うと、ほとんどの場合、 `認証アクセスが必要な` 例外が生成されます。

認証されていないサービス呼び出しは、Liferayインスタンスまたはプラグインのリモートメソッドに `@AccessControlled` 注釈がある場合にのみ機能します。 これは、架空のクラス `FooServiceImpl` とそのメソッド `bar`に対して示されています。

    import com.liferay.portal.security.ac.AccessControlled;
    ...
    public class FooServiceImpl extends FooServiceBaseImpl {
    ...
        @AccessControlled(guestAccessEnabled = true)
        public void bar() { ... }
    ...

このような呼び出しを行うには、サーバーURLのみを受け入れるコンストラクターを使用する必要があります。

    Session session = new SessionImpl("http://10.0.2.2:8080");

ファンタスティック\！ セッションができたので、それを使用してLiferayのサービスを呼び出すことができます。

## ステップ2：必要なLiferayサービスをインポートする

最初に、呼び出す必要のあるLiferayサービスを決定する必要があります。 [http：// localhost：8080 / api / jsonws](http://localhost:8080/api/jsonws)で利用可能なサービスを見つけることができます。 このURLの `http：// localhost：8080` をサーバーのアドレスに置き換えてください。

呼び出す必要があるサービスのインポートを追加します。 たとえば、ブログアプリを作成している場合、 `BlogsEntryService`インポートできます。

    import com.liferay.mobile.android.v62.blogsentry.BlogsEntryService;

Liferayバージョン（`.v62`）がパッケージ名前空間で使用されていることに注意してください。 Mobile SDKは特定のLiferayバージョン用に構築されているため、異なるLiferayバージョンのサービスクラスはパッケージ名で区切られています。 この例では、Mobile SDKクラスは `.v62` パッケージを使用してい`つまり、このMobile SDKはLiferay 6.2と互換性があります。 Liferay 7.0と互換性のあるモバイルSDKクラスは、 <code>v7` パッケージを使用します。 この名前空間により、アプリは複数のLiferayバージョンをサポートできます。

## ステップ3：サービスオブジェクトを作成し、そのサービスメソッドを呼び出す

セッションと必要なインポートが完了したら、サービスを呼び出す準備が整います。 これは、呼び出したいサービスのサービスオブジェクトを作成し、そのサービスメソッドを呼び出すことで実行されます。 たとえば、ブログアプリを作成している場合、 `BlogsEntryService` を使用して、サイトからすべてのブログエントリを取得する必要があります。 これは、次のコードで示されています。

    BlogsEntryService service = new BlogsEntryService(session);
    
    JSONArray jsonArray = service.getGroupEntries(10184, 0, 0, -1, -1);

これにより、 *ゲスト* サイトからすべてのブログエントリが取得されます。 この例では、 *ゲスト* サイトの `groupId` は `10184`です。 多くのサービスメソッドでは、パラメータとして `groupId` 必要であることに注意してください。 `GroupService`から `getUserSites（）` メソッドを呼び出すことにより、ユーザーのグループを取得できます。

サービスメソッドの戻り型とすることができる `ボイド`、 `列`、 `JSONArray`、又は `JSONObject`。 プリミティブ型ラッパーとすることができる `ブール`、 `整数`、 `ロング`、又は `ダブル`。

この `BlogsEntryService` 呼び出しは、同期サービス呼び出しの基本的な例です。このメソッドは、リクエストが終了した後にのみ戻ります。 ただし、AndroidはアプリのメインUIスレッドからのネットワーク通信を許可しません。 メインUIスレッドから発行されるサービス呼び出しは非同期である必要があります。 これを行う方法については、チュートリアルを参照 [Androidアプリから呼び出すサービスの非同期を](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-android-app)。

すばらしいです\！ これで、Mobile SDKを介してLiferayサービスにアクセスする基本を理解できました。 ただし、アプリからサービスを呼び出すときに遭遇する可能性がある特別なケースがいくつかあります。これらについては、次のセクションで説明します。

## 非原始的議論

サービスメソッドの引数がプリミティブではない特別なケースがいくつかあります。 これらの場合、 `JSONObjectWrapper`を使用する必要があります。 たとえば、

    JSONObjectWrapper wrapper = new JSONObjectWrapper(new JSONObject());

オブジェクトのプロパティとその値を含むJSONを渡す必要があります。 サーバー側では、オブジェクトがインスタンス化され、渡されたJSONの値を使用して各プロパティのセッターが呼び出されます。

サービスメソッドが引数としてインターフェイスまたは抽象クラスを必要とする他のケースがあります。 SDKが使用する実装を推測することは不可能であるため、クラス名で `JSONObjectWrapper` を初期化する必要があります。 たとえば、

    JSONObjectWrapper wrapper = new JSONObjectWrapper(className, new JSONObject());

サーバーはクラスパスでクラス名を探し、オブジェクトをインスタンス化します。 次に、前の例のようにセッターを呼び出します。 抽象クラス `OrderByComparator` は、この良い例です。 これについて次に説明します。

### OrderByComparator

サーバー側では、 `OrderByComparator` は抽象クラスです。 したがって、それを実装するクラスの名前を渡す必要があります。 たとえば、

    String className = "com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator";
    
    JSONObjectWrapper orderByComparator = new JSONObjectWrapper(className, new JSONObject());

呼び出しているサービスがコンパレータ引数として `null` を受け入れる場合、 `null` をサービス呼び出しに渡します。

コンパレータの昇順プロパティを設定できます。 残念ながら、Liferay 6.2の時点で、ほとんどのLiferay `OrderByComparator` 実装にはこのプロパティのセッターがなく、Mobile SDKから設定することはできません。 Liferayの将来のバージョンではこれに対処する可能性があります。 ただし、昇順用のセッターを持つカスタム `OrderByComparator` があります。 この場合、次のコードを使用できます。

    String className = "com.example.MyOrderByComparator";
    
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("ascending", true);
    
    JSONObjectWrapper orderByComparator = new JSONObjectWrapper(className, jsonObject);

その他の例については、テストケース [`OrderByComparatorTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/OrderByComparatorTest.java)参照してください。

### ServiceContext

別の非プリミティブ引数は `ServiceContext`です。 Liferayのほとんどのサービス方法では、特別な注意が必要です。 ただし、SDKに渡す必要はありません。代わりに `null` を渡すことができます。 サーバーは、デフォルト値を使用して `ServiceContext` インスタンスを作成します。

`ServiceContext`プロパティを設定する必要がある場合は、新しい `JSONObject` 追加し、 `ServiceContext` 引数として渡すことで設定できます。

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("addGroupPermissions", true);
    jsonObject.put("addGuestPermissions", true);
    
    JSONObjectWrapper serviceContext = new JSONObjectWrapper(jsonObject);

その他の例については、テストケース [`ServiceContextTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/ServiceContextTest.java)参照してください。

### バイナリ

一部のLiferayサービスでは、バイト配列（`byte []`）やファイル（`java.io.File`）などの引数タイプが必要です。

Mobile SDKは、POST要求を送信する前にバイト配列を文字列に変換します。 たとえば、 `"hello" .getBytes（ "UTF-8"）` は `"[104,101,108,108,111]"`ようなJSON配列になります。 Mobile SDKはこれを自動的に行うため、心配する必要はありません。メソッドにバイト配列を渡すだけです。

ただし、このような方法を使用する場合は注意が必要です。 これは、バイト配列全体にメモリを割り当てているためです。コンテンツが大きい場合、メモリの問題が発生する可能性があります。

他のLiferayサービスメソッドでは、引数として `java.io.File` 必要です。 これらの場合、Mobile SDKは代わりに `InputStreamBody` 必要とします。 これに対応するには、 `InputStream` を作成し、ファイルのMIMEタイプと名前とともに `InputStreamBody` コンストラクターに渡す必要があります。 たとえば、

    InputStream is = context.getAssets().open("file.png");
    InputStreamBody file = new InputStreamBody(is, "image/png", "file.png");

Mobile SDKは、マルチパートフォームリクエストをLiferayインスタンスに送信します。 サーバー側では、 `File` インスタンスが作成され、呼び出しているサービスメソッドに送信されます。

Liferayにデータをアップロードするサービスコールをキャンセルまたは監視することもできます。 データをアップロードするすべてのサービスは、 `AsyncTask` インスタンスを返します。 必要に応じて、これを使用してアップロードをキャンセルできます。 アップロードの進行状況をリッスンして進行状況バーを作成する場合は、 `UploadProgressAsyncTaskCallback` コールバックを作成し、現在の `セッション` オブジェクトに設定できます。 その `onProgress` メソッドは、送信されるバイトチャンクごとに呼び出されます。 これまでにアップロードされたバイトの総数を渡します。 たとえば、

    session.setCallback(
    
        new UploadProgressAsyncTaskCallback<JSONObject>() {
    
            (...)
    
            public void onProgress(int totalBytes) {
                // This method will be called for each byte chunk sent.
                // The totalBytes argument will contain the total number
                // of uploaded bytes, from 0 to the total size of the
                // request.
            }
    
            (...)
    
        }
    );

このテーマのその他の例については、 [`DLAppServiceTest.java`](https://github.com/liferay/liferay-mobile-sdk/blob/master/android/src/test/java/com/liferay/mobile/android/DLAppServiceTest.java)の `addFileEntry *` メソッドを参照してください。

ご覧のとおり、特別なサービスメソッドの引数が必要な場合でも、Mobile SDKは多くの作業を行います。

## 関連トピック

[Androidアプリからのサービスの非同期呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-android-app)

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Mobile SDKを使用するiOSアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-apps-that-use-the-mobile-sdk)
