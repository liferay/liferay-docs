---
header-id: invoking-services-asynchronously-from-your-ios-app
---

# iOSアプリからのサービスの非同期呼び出し

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

アプリからの同期リクエストを使用する主な欠点は、各リクエストを終了してから別のリクエストを開始できることです。 多数の同期要求を送信している場合、それぞれが処理されるのを待っている間にボトルネックが形成されるため、パフォーマンスが低下します。 幸いなことに、LiferayのiOS SDKは、非同期</em> HTTP要求を *許可します。 そのためには、セッションオブジェクトにコールバックを設定する必要があります。 同期要求を再度作成する場合は、コールバックを `nil`設定できます。</p>

このチュートリアルでは、次の手順を使用して、iOSアプリに非同期リクエストを実装する方法を示します。

1.  コールバッククラスを実装します。
2.  コールバッククラスをインスタンス化し、セッションに設定します。
3.  Liferayサービスを呼び出します。

Objective-Cは、次のコードスニペットで使用されます。 要求を始めましょう！

## コールバッククラスの実装

非同期要求を構成するには、最初に `LRCallback` プロトコルに準拠するクラスを作成します。 このコールバッククラスを実装する場合、その `onFailure` および `onSuccess` メソッドを実装する必要があります。 これらのメソッドはそれぞれ、リクエストが失敗または成功したときのアプリの動作を決定します。 要求中にサーバー側の例外または接続エラーが発生した場合、エラーに関する情報を含む `NSError` インスタンスで `onFailure` メソッドが呼び出されます。 `onSuccess` 結果パラメータには特定のタイプがないことに注意してください。 キャスト先を決定するときは、サービスメソッドシグネチャのタイプを確認する必要があります。

このサンプルコードは、ブログポートレットからブログエントリを取得するアプリのコールバッククラスを実装しています。 この呼び出しのサービスメソッドは `getGroupEntriesWithGroupId`で、 `NSArray` インスタンスを返します。 したがって、 `onSuccess` メソッドの結果パラメーターは、このタイプにキャストされます。

    #import "LRCallback.h"
    
    @interface BlogsEntriesCallback : NSObject <LRCallback>
    
    @end
    
    
    #import "BlogsEntriesCallback.h"
    
    @implementation BlogsEntriesCallback

    - （void）onFailure：（NSError *）error { //エラー処理コード 実装する}

    - （void）onSuccess：（id）result { //リクエストが正常に終了した後に呼び出される NSArray * entries =（NSArray *）result; }

    @終わり

驚くばかり\！ これで、セッションで使用できるコールバッククラスができました。

## セッションへのコールバックを設定します

次に、このコールバックのインスタンスを作成し、セッションに設定します。 セッションをまだ作成していない場合は、ここで作成してください。 iOSアプリ</a> でLiferayサービスを呼び出すチュートリアル

、セッションの作成方法を示します。 これで、セッションへのコールバックを設定する準備が整いました。 例えば、これは `BlogsEntriesCallback`に対してここで行われます：</p> 

    BlogsEntriesCallback *callback = [[BlogsEntriesCallback alloc] init];
    
    [session setCallback:callback];
    

ものすごく単純\！ これで、サービスコールを行う準備ができました。



## サービスコール

最後になりますが、間違いなく、サービスコールを行ってください。 これは、他のサービスを呼び出すのと同じように行われます。セッションからサービスオブジェクトを作成し、それを使用してサービスを呼び出します。 これは、チュートリアル [iOSアプリでのLiferayサービスの呼び出し](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)でも説明されています。 ここでは、サイトのブログポートレットからすべてのブログエントリを取得するサービス呼び出しの例を示します。

    [service getGroupEntriesWithGroupId:10184 status:0 start:-1 end:-1 error:&error];
    

要求は非同期であるため、 `getGroupEntriesWithGroupId` すぐに `nil`返します。 要求が正常に終了すると、コールバックの `onSuccess` メソッドが呼び出され、メインUIスレッドに結果が表示されます。

すばらしいです\！ これで、iOSアプリで非同期リクエストを行う方法がわかりました。 ただし、同じことを達成する別の方法があります。 これについて次に説明します。



## コールバックとしてのブロックの使用

別のコールバッククラスを実装する代わりに、Objective-Cブロックをコールバックとして使用できます。 ユーザーのサイトを取得する非同期呼び出しの例を次に示します。 これには、呼び出しを行うために必要なすべてのコードが含まれていることに注意してください。

    LRSession *session = [[LRSession alloc] 
        initWithServer:@"http://localhost:8080" username:@"test@example.com" password:@"test"];
    
    [session
        onSuccess:^(id result) {
            // Called after request has finished successfully
        }
        onFailure:^(NSError *e) {
            // Implement error handling code
        }
    ];
    
    LRGroupService_v62 *service = [[LRGroupService_v62 alloc] initWithSession:session];
    
    NSError *error;
    [service getUserSites:&error];
    

ブロックをコールバックとして使用する場合、 `LRCallback` インスタンスもセッションに設定しないように注意してください。 実行すると、上書きされます。 それ以外の場合、ブロックのサポートは、前のセクションで説明したのと同じように機能します。

スーパー\！ これで、iOSアプリで非同期サービスリクエストを行う2つの異なる方法がわかりました。



## 関連トピック

[iOSアプリでLiferayサービスを呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/invoking-liferay-services-in-your-ios-app)

[Mobile SDKを使用するAndroidアプリの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-apps-that-use-the-mobile-sdk)
