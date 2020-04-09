---
header-id: invoking-liferay-services-in-your-ios-app
---

# iOSアプリでLiferayサービスを呼び出す

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

iOSプロジェクトで適切なMobile SDKがセットアップされると、アプリで@productサービスにアクセスして呼び出すことができます。このチュートリアルでは、これらのサービスを呼び出すために従う必要がある手順を説明します。

1.  セッションを作成します。
2.  呼び出す必要がある@productサービスをインポートします。
3.  サービスオブジェクトを作成し、サービスメソッドを呼び出します。

一部のサービスコールには特別な処理が必要なため、このチュートリアルではそれらの処理方法も示します。 このチュートリアルのコードスニペットは、Objective-Cで記述されていることに注意してください。

最初に、ポータルで@product@のJSON Webサービスを保護する方法を学びます。

## JSON Webサービスの保護

Liferay Mobile SDKは、デフォルトで有効になっている@product@のJSON Webサービスを呼び出します。 Mobile SDKを介して呼び出すWebサービスは、それらの呼び出しが機能するように有効にしておく必要があります。 ただし、呼び出す必要のないWebサービスを無効にすることは可能です。 この手順については、チュートリアル [JSON Webサービスの設定](/docs/7-1/tutorials/-/knowledge_base/t/configuring-json-web-services)参照してください。 [サービスアクセスポリシー](/docs/7-1/tutorials/-/knowledge_base/t/service-access-policies) を使用して、アクセス可能なサービスをより詳細に制御することもできます。

## ステップ1：セッションを作成する

セッションは、クライアントとサーバー間の変換状態であり、2つの間の複数の要求と応答で構成されます。 アプリとMobile SDKの間でリクエストを渡すにはセッションが必要です。 ほとんどの場合、ユーザー認証を使用してセッションを作成する必要があります。 セッションの作成に必要なインポートとコードは次のとおりです。

    #import "LRBasicAuthentication.h"
    #import "LRSession.h"
    
    LRSession *session = [[LRSession alloc] initWithServer:@"http://localhost:8080" 
        authentication:[[LRBasicAuthentication alloc] initWithUsername:@"test@example.com" password:@"test"]];

`LRSession` オブジェクトは、接続するLiferayインスタンスと認証するユーザーの資格情報を指定する初期化子で作成されます。 `initWithServer` パラメーターは、接続しているLiferayインスタンスのURLを設定します。 この場合、Liferayインスタンスは `http：// localhost：8080`実行されています。 iOSエミュレータも同じマシンで実行されています。 次に、 `authentication` パラメーターは、認証するユーザーの資格情報を持つ `LRBasicAuthentication` インスタンスを取得します。 Liferayインスタンスで使用される認証方法に応じて、ユーザーの電子メールアドレス、スクリーン名、またはユーザーIDを `initWithUsername` パラメーターに提供する必要があります。 また、ユーザーのパスワードを `パスワード` パラメーターに指定する必要があります。

`LRBasicAuthentication` を使用すると、基本認証で各サービスコールを認証するようにセッションに指示します。 Mobile SDKはOAuth 2認証もサポートしています。 この手順については、チュートリアル [iOS Mobile SDK](/docs/7-1/tutorials/-/knowledge_base/t/using-oauth-2-in-the-ios-mobile-sdk)OAuth 2の使用を参照してください。

| **警告：** 本番環境で管理者の資格情報を使用する場合は注意してください|ポータルインスタンス。サービスを呼び出す権限があります。 確認しないでください|誤ってデータを変更するため。 もちろん、デフォルトの管理者資格情報|本番ポータルインスタンスでは無効にする必要があります。

アプリのサインインビューを作成している場合、 `LRSignIn` ユーティリティクラスを使用して、ユーザーが指定した資格情報が有効かどうかを確認できます。

    #import "LRSignIn.h"
    
    [session
        onSuccess:^(id result) {
            user = result;
            [monitor signal];
        }
        onFailure:^(NSError *e) {
            error = e;
            [monitor signal];
        }
    ];
    
    [LRSignIn signInWithSession:session callback:session.callback error:&error];

Mobile SDKは、サーバーとの永続的な接続またはセッションを保持しません。 各リクエストはユーザーの資格情報とともに送信されます（OAuthを使用する場合を除く）。 ただし、 `SignIn` クラスは、サインインが成功した後にユーザー情報を返す方法を提供します。

`LRCredentialStorage`資格情報を保持できます。 キーチェーンにユーザー名とパスワードを安全に保存します：

    [LRCredentialStorage storeCredentialForServer:@"http://localhost:8080" 
        username:@"test@example.com" password:@"test"];

資格情報が保存されたら、次の方法で取得できます。

    NSURLCredential *credential = [LRCredentialStorage getCredential];

または、次を使用して `LRSession` インスタンスを直接作成できます。

    LRSession *session = [LRCredentialStorage getSession];

この例については、 [`CredentialStorageTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/CredentialStorageTest.m)参照してください。

次に、これが可能な限られたケースで認証されていないセッションを作成する方法を示します。

### 認証されていないセッションの作成

場合によっては、ユーザー資格情報なしで `LRSession` インスタンスを作成することが可能です。 ただし、ほとんどのLiferayリモートメソッドは、認証されていないリモート呼び出しを受け入れません。 認証されていないセッションで呼び出しを行うと、ほとんどの場合、 `認証アクセスが必要な` 例外が生成されます。

認証されていないサービス呼び出しは、Liferayインスタンスまたはプラグインのリモートメソッドに `@AccessControlled` 注釈がある場合にのみ機能します。 これは、架空のクラス `FooServiceImpl` とそのメソッド `bar`に対して示されています。

    import com.liferay.portal.security.ac.AccessControlled;
    ...
    public class FooServiceImpl extends FooServiceBaseImpl {
    ...
        @AccessControlled(guestAccessEnabled = true)
        public void bar() { ... }
    ...

このような呼び出しを行うには、サーバーURLのみを受け入れるコンストラクターを使用する必要があります。

    LRSession *session = [[LRSession alloc] initWithServer:@"http://localhost:8080"];

ファンタスティック\！ セッションができたので、それを使用してLiferayのサービスを呼び出すことができます。

## ステップ2：サービスをインポートする

最初に、呼び出す必要のあるLiferayサービスを決定する必要があります。 [http：// localhost：8080 / api / jsonws](http://localhost:8080/api/jsonws)で利用可能なサービスを見つけることができます。 このURLの `http：// localhost：8080` をサーバーのアドレスに置き換えてください。

呼び出す必要があるサービスを決定したら、それらのインポートを追加します。 たとえば、ブログアプリを作成している場合、 `LRBlogsEntryService`インポートできます。

    #import "LRBlogsEntryService_v62.h"

Liferayバージョン（`_v62`）がサービスクラスの名前で使用されていることに注意してください。 これは、互換性のあるLiferayバージョンに対応しています。 この例では、 `_v62` が使用されています。つまり、このMobile SDKクラスはLiferay 6.2と互換性があります。 Liferay 7.0と互換性のあるモバイルSDKクラスは、代わりに `_v7` 使用します。 サービスクラス名には互換性のあるLiferayバージョンが含まれているため、複数のMobile SDKを同時に使用して、同じアプリで異なるLiferayバージョンをサポートできます。

## ステップ3：サービスの呼び出し

セッションが完了し、サービスクラスをインポートしたら、サービス呼び出しを行う準備が整います。 これは、呼び出したいサービスのサービスオブジェクトを作成し、そのサービスメソッドを呼び出すことで実行されます。 たとえば、ブログアプリを作成している場合、 `LRBlogsEntryService` を使用して、サイトからすべてのブログエントリを取得する必要があります。 これは、次のコードで示されています。

    LRBlogsEntryService_v62 *service = [[LRBlogsEntryService_v62 alloc] initWithSession:session];
    
    NSError *error;
    NSArray *entries = [service getGroupEntriesWithGroupId:10184 status:0 start:-1 end:-1 error:&error];

これにより、 *ゲスト* サイトからすべてのブログエントリが取得されます。 この例では、 *ゲスト* サイトの `groupId` は `10184`です。 多くのサービスメソッドでは、パラメータとして `groupId` 必要であることに注意してください。 `[LRGroupService_v62 getUserSites：&error]`呼び出すことにより、ユーザーのグループを取得できます。

サービスメソッドの戻り型とすることができる `ボイド`、 `NSStringの`、 `にNSArray`、 `NSDictionaryの`、 `のNSNumber`、及び `BOOL`。

この `LRBlogsEntryService` 呼び出しは、同期サービス呼び出しの基本的な例です。 同期サービス呼び出しのメソッドは、要求が終了した後にのみ戻ります。

## 非原始的議論

サービスメソッドの引数がプリミティブではない特別なケースがいくつかあります。 これらの場合、 `LRJSONObjectWrapper`を使用する必要があります。 たとえば、

    LRJSONObjectWrapper *wrapper = [[LRJSONObjectWrapper alloc] 
        initWithJSONObject:[NSDictionary dictionary]];

オブジェクトのプロパティとその値を含む辞書を渡す必要があります。 サーバー側では、オブジェクトがインスタンス化され、各プロパティのセッターが辞書の値で呼び出されます。

サービスメソッドが引数としてインターフェイスまたは抽象クラスを必要とする他のいくつかのケースがあります。 SDKが使用する実装を推測することは不可能であるため、クラス名で `LRJSONObjectWrapper` を初期化する必要があります。 たとえば、

    LRJSONObjectWrapper *wrapper = [[LRJSONObjectWrapper alloc] 
        initWithClassName:@"com.example.MyClass" jsonObject:[NSDictionary dictionary]];

サーバーはクラスパスでクラス名を探し、オブジェクトをインスタンス化します。 次に、前の例のようにセッターを呼び出します。 抽象クラス `OrderByComparator` は、この良い例です。 これについて次に説明します。

### OrderByComparator

サーバー側では、 `OrderByComparator` は抽象クラスです。 したがって、それを実装するクラスの名前を渡す必要があります。 たとえば、

    NSString *className = @"com.liferay.portlet.bookmarks.util.comparator.EntryNameComparator";
    
    LRJSONObjectWrapper *orderByComparator = [[LRJSONObjectWrapper alloc] initWithClassName:className jsonObject:[NSDictionary dictionary]];

呼び出しているサービスがコンパレータ引数として `null` を受け入れる場合、 `nil` をサービス呼び出しに渡します。

コンパレータの昇順プロパティを設定できます。 残念ながら、Liferay 6.2の時点で、ほとんどのLiferay `OrderByComparator` 実装にはこのプロパティのセッターがなく、Mobile SDKから設定することはできません。 Liferayの将来のバージョンではこれに対処する可能性があります。 ただし、昇順用のセッターを持つカスタム `OrderByComparator` があります。 この場合、次のコードを使用できます。

    NSString *className = @"com.example.MyOrderByComparator";
    
    NSDictionary *jsonObject = @{
        @"ascending": @(YES)
    };
    
    LRJSONObjectWrapper *orderByComparator = [[LRJSONObjectWrapper alloc] 
        initWithClassName:className jsonObject:jsonObject];

その他の例については、テストケース [`OrderByComparatorTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/OrderByComparatorTest.m)参照してください。

### ServiceContext

別の非プリミティブ引数は `ServiceContext`です。 Liferayのほとんどのサービス方法では、特別な注意が必要です。 ただし、SDKに渡す必要はありません。代わりに `nil` を渡すことができます。 サーバーは、デフォルト値を使用して `ServiceContext` インスタンスを作成します。

`ServiceContext`プロパティを設定する必要がある場合は、新しい `NSDictionary` 追加し、 `ServiceContext` 引数として渡すことで設定できます。

    NSDictionary *jsonObject = @{
        @"addGroupPermissions": @(YES),
        @"addGuestPermissions": @(YES)
    };
    
    LRJSONObjectWrapper *serviceContext = [[LRJSONObjectWrapper alloc] initWithJSONObject:jsonObject];

その他の例については、テストケース [`ServiceContextTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/ServiceContextTest.m)参照してください。

### バイナリ

一部のLiferayサービスでは、 `NSData` または `LRUploadData`などのバイナリ引数タイプが必要です。 Mobile SDKは、POST要求を送信する前に、 `NSData` インスタンスを `NSString` に変換します。 たとえば、 `[@ "hello" dataUsingEncoding：NSUTF8StringEncoding]` は、 `"[104,101,108,108,111]"`などのJSON配列になります。 Mobile SDKがこれを自動的に行うため、心配する必要はありません。 `NSData` インスタンスをメソッドに渡すだけです。

ただし、このような方法を使用する場合は注意が必要です。 これは、 `NSData`全体にメモリを割り当てているためです。コンテンツが大きい場合、メモリの問題が発生する可能性があります。

他のLiferayサービスメソッドでは、引数として `java.io.File` 必要です。 これらの場合、Mobile SDKは代わりに `LRUploadData` 必要とします。 `個のLRUploadData` インスタンスを作成する2つの例を次に示します。

    LRUploadData *upload = [[LRUploadData alloc] 
        initWithData:data fileName:@"file.png" mimeType:@"image/png"];
    
    LRUploadData *upload = [[LRUploadData alloc] 
        initWithInputStream:is length:length fileName:@"file.png" mimeType:@"image/png"];

最初のコンストラクタは `NSData` 引数を受け入れ、2番目のコンストラクタは `NSInputStream`受け入れます。 ご覧のとおり、ファイルのMIMEタイプと名前も渡す必要があります。 `の長さ` コンテンツのビーイングて送信のバイト単位のサイズです。 SDKは、マルチパートフォームリクエストをLiferayインスタンスに送信します。 サーバー側では、 `File` インスタンスが作成され、呼び出しているサービスメソッドに送信されます。

Liferayにデータをアップロードするサービスコールを監視することもできます。 アップロードの進行状況をリッスンして進行状況バーを作成する場合、 `LRProgressDelegate` デリゲートを作成し、 `LRUploadData` オブジェクトに設定できます。 その `onProgressBytes` メソッドは、送信される各バイトチャンクに対して呼び出されます。 送信されたバイト、これまでに送信された合計バイト数、および合計要求サイズを渡します。 たとえば、

    @interface ProgressDelegate : NSObject <LRProgressDelegate>
    
    @end
    
    @implementation ProgressDelegate

    - (void)onProgressBytes:(NSUInteger)bytes sent:(long long)sent total:(long long)total {
      
            // bytes contains the byte values that were sent.
            // sent will contain the total number of bytes sent.
            // total will contain the total size of the request in bytes.

    }

    @end

この他の例については、テストケース [`FileUploadTest.m`](https://github.com/liferay/liferay-mobile-sdk/blob/ios-6.2.0.17/ios/Test/FileUploadTest.m)を参照してください。

## 関連トピック

[モバイルSDKの構築](/docs/7-1/tutorials/-/knowledge_base/t/building-mobile-sdks)

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
