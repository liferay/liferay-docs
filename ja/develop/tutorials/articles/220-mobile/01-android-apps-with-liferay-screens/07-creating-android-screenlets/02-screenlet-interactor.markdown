---
header-id: creating-the-interactor
---

# インタラクターの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletのインターアクターは、Liferayインスタンスから必要なデータを取得するためのサービス呼び出しを行います。 インタラクターは、いくつかのコンポーネントで構成されています。

1.  イベントクラス。 このクラスを使用すると、サーバー呼び出しの結果を含むイベントオブジェクトを介して、スクリーンレットのコンポーネント間の通信を処理できます。 Screensは、このために [EventBus](https://greenrobot.github.io/EventBus/) ライブラリを使用し

 。 Screensは、Screenlets内で `JSONObject` と `JSONArray` 結果をそれぞれ通信するために、 [`BasicEvent` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/event/BasicEvent.java) および [`BaseListEvent` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListEvent.java) を提供します。 `BasicEvent`拡張することにより、独自のイベントクラスを作成できます。 `JSONObject` または `JSONArray`以外のオブジェクトを通信する必要がある場合は、独自のイベントクラスを作成する必要があります。 例のブックマークスクリーンレットの追加では、 `JSONObject` インスタンスのみを通信する必要があるため、 `BasicEvent`使用します。</p></li> 
   
   2  リスナーインターフェイス。 これにより、アプリ開発者がスクリーンレットの動作に応答する必要があるメソッドが定義されます。 たとえば、 [Login Screenletのリスナー](/docs/7-1/reference/-/knowledge_base/r/loginscreenlet-for-android#listener) は、 `onLoginSuccess` と `onLoginFailure` メソッドを定義します。 画面は、それぞれログインが成功または失敗したときにこれらのメソッドを呼び出します。 Screenletを含むアクティビティクラスまたはフラグメントクラスにこれらのメソッドを実装することにより、アプリ開発者はログインの成功と失敗に対応できます。 同様に、Add Bookmark Screenletのリスナーインターフェイス</a> の例 では、2つのメソッドが定義されています。 
  
  

    ``` 
     public interface AddBookmarkListener {

         void onAddBookmarkFailure(Exception exception);

         void onAddBookmarkSuccess();
     }
    ```
</p></li> 

3  Interactorクラス。 このクラスは、リスナーとイベントをタイプ引数として、 [Screensの `BaseRemoteInteractor`](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseRemoteInteractor.java) を拡張する必要があります。 リスナーを使用すると、Interactorクラスは、サーバーコールの結果をリスナーを実装するクラスに送信できます。 サーバーの呼び出しを行うメソッドの実装では、 `実行さ` の方法を、あなたがしなければならない [非同期サービス呼び出しにするために、モバイルSDKを使用する](/docs/7-1/tutorials/-/knowledge_base/t/invoking-services-asynchronously-from-your-android-app)。 つまり、セッションを取得してからサーバー呼び出しを行う必要があります。 必要なLiferayサービスを呼び出すことができるMobile SDKサービス（ `BookmarksEntryService`）のインスタンスを作成してサーバー呼び出しを行い、呼び出しを行います。 Interactorクラスは、呼び出しの結果を含むイベントオブジェクトも処理し、それらの結果をリスナーに通知する必要があります。 これを行うには、 `onSuccess` および `onFailure` メソッドを実装して、対応する `getListener（）` メソッドを呼び出します。
  
  たとえば、 [`AddBookmarkInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/interactor/AddBookmarkInteractor.java) は、ブックマークスクリーンレットの追加のインタラクタークラスです。 このクラスは、 `execute` メソッドを実装し`このメソッドは、Liferayインスタンスのブックマークポートレット内のフォルダーにブックマークを追加します。 このメソッドは、最初にブックマークのURLとフォルダーを検証します。 次に、 <code>getJSONObject` メソッドを呼び出してブックマークを追加し、 `JSONObject`から作成された新しい `BasicEvent` オブジェクトを返すことで終了します。 `getJSONObject` メソッドの `if` ステートメントは、Liferayバージョンをチェックして、サーバー呼び出しに必要な適切な `BookmarksEntryService` インスタンスを作成できるようにします。 Liferayのバージョンに関係なく、 `getSession（）` メソッドは、ログインに成功すると、Login Screenletによって作成された既存のセッションを取得します。 セッションの `addEntry` メソッドがサーバー呼び出しを行います。 スクリーンレットは、 `onSuccess` または `onFailure` メソッドを呼び出して、それぞれサーバー呼び出しの成功または失敗をリスナーに通知します。 どちらの場合でも、 `BasicEvent` オブジェクトにはサーバー呼び出しの結果が含まれます。 ただし、このScreenletはサーバーから何も取得しないため、 `onSuccess` メソッドで `BasicEvent` オブジェクトを処理する必要はありません。リスナーの `onAddBookmarkSuccess` メソッドを呼び出すだけで十分です。 `AddBookmarkInteractor`完全なコードは次のとおりです。 
  
  

    ``` 
     public class AddBookmarkInteractor extends BaseRemoteInteractor<AddBookmarkListener, BasicEvent> {

         @Override
         public BasicEvent execute(Object[] args) throws Exception {
             String url = (String) args[0];
             String title = (String) args[1];
             long folderId = (long) args[2];

             validate(url, folderId);

             JSONObject jsonObject = getJSONObject(url, title, folderId);
             return new BasicEvent(jsonObject);
         }

         @Override
         public void onSuccess(BasicEvent event) throws Exception {
             getListener().onAddBookmarkSuccess();
         }

         @Override
         public void onFailure(BasicEvent event) {
             getListener().onAddBookmarkFailure(event.getException());
         }

         private void validate(String url, long folderId) {
             if (url == null || url.isEmpty() || !URLUtil.isValidUrl(url)) {
                 throw new IllegalArgumentException("Invalid url");
             } else if (folderId == 0) {
                 throw new IllegalArgumentException("folderId not set");
             }
         }

         @NonNull
         private JSONObject getJSONObject(String url, String title, long folderId) throws Exception {
             if (LiferayServerContext.isLiferay7()) {
                 return new BookmarksEntryService(getSession()).addEntry(LiferayServerContext.getGroupId(), 
                     folderId, title, url, "", null);
             } else {
                 return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                     getSession()).addEntry(LiferayServerContext.getGroupId(), folderId, title, url, "", null);
             }
         }
     }
    ```
</ol> 

甘味\！ スクリーンレットのインタラクターが完成しました。 次に、Screenletクラスを作成します。



## 関連トピック

[UIを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui)

[属性の定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-attributes)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class)

[スクリーンレットのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets)
