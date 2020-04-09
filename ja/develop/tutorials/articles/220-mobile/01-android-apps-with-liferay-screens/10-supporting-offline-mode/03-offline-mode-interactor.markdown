---
header-id: update-the-interactor-class
---

# インタラクタークラスを更新する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

基本的なスクリーンレット作成チュートリアルから、Interactorクラスは、リスナーとイベントをタイプ引数として `BaseRemoteInteractor` を拡張することを思い出してください。 オフラインモードをサポートするには、Interactorクラスが代わりに次のクラスのいずれかを拡張する必要があります。 Interactorがサーバーにデータを書き込むか、サーバーからデータを読み取るかによって異なります。

  - `BaseCacheWriteInteractor`：サーバーにデータを書き込みます。 スクリーンレットが書き込みスクリーンレットである場合、このクラスを拡張します。 [このクラスを表示するには、](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseCacheWriteInteractor.java) をクリックしてください。
  - `BaseCacheReadInteractor`：サーバーからデータを読み取ります。 スクリーンレットが読み取りスクリーンレットの場合、このクラスを拡張します。 [このクラスを表示するには、](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseCacheReadInteractor.java) をクリックしてください。

どちらの場合でも、タイプ引数は同じです：リスナーとイベント。 ただし、上記のように、イベントは `CacheEvent` を拡張する必要があることに注意してください。 たとえば、Add Bookmark Screenletは書き込みScreenletであるため、オフラインモードをサポートするには、Interactorクラスが型引数として `AddBookmarkListener` および `AddBookmarkEvent` `BaseCacheWriteInteractor` を拡張する必要があります。

    public class AddBookmarkInteractor extends
        BaseCacheWriteInteractor<AddBookmarkListener, BookmarkEvent> {...

Screenletが書き込みScreenletの場合、Interactorの `execute` メソッドを変更して、var argsの代わりにイベントを取得する必要があります（読み取りScreenletでは、このメソッドは引き続きvar argsを取得できます）。 その後、イベントから必要なデータを取得できます。 たとえば、オフラインモードをサポートするには、 `AddBookmarkInteractor` `execute` メソッドが `BookmarkEvent` を引数として受け取ります。 ブックマークのURL、タイトル、およびフォルダーIDは、サーバー呼び出しを行う `getJSONObject` メソッドで使用するためにイベントから取得されます。 `execute` メソッドは、結果の `JSONObject` をイベントに設定し、イベントを返すことで終了します。

    @Override 
    public BookmarkEvent execute(BookmarkEvent bookmarkEvent) throws Exception {
    
        validate(bookmarkEvent.getUrl(), bookmarkEvent.getFolderId());
    
        JSONObject jsonObject = getJSONObject(bookmarkEvent.getUrl(), bookmarkEvent.getTitle(), 
            bookmarkEvent.getFolderId());
        bookmarkEvent.setJSONObject(jsonObject); 
        return bookmarkEvent; 
    }

Screenletが読み取りScreenletである場合、 `BaseCacheReadInteractor``getIdFromArgs` メソッドも実装する必要があります。 このメソッドは、Interactorに渡されるvar argsを取得するため、エンティティを識別する引数を返すことができます。 このメソッドでは `String`を返す必要があるため、多くの場合、 `String.valueOf` を使用して、文字列以外の引数を文字列として返します。 たとえば、コメント表示スクリーンレットの `CommentLoadInteractor` の `getIdFromArgs` 実装は、最初の引数からコメントID（ `long`）を取得し、それを `String`として返します。

``` 
 @Override 
 protected String getIdFromArgs(Object... args) { 
     long commentId = (long) args[0]; 
     return String.valueOf(commentId); 
 }
```

また、 `onSuccess` メソッドを変更して、 `BasicEvent`代わりにイベントクラスのインスタンスを取得する必要があります。 これは、このメソッドに対して行う必要がある唯一の変更です。 たとえば、 `AddBookmarkInteractor` の `onSuccess` メソッドは、 `BasicEvent`代わりに `BookmarkEvent` を取ることでオフラインモードをサポートします。

    @Override 
    public void onSuccess(BookmarkEvent event) {
        getListener().onAddBookmarkSuccess(); 
    }

`onFailure` メソッドにも同じ変更を加えますが、リスナーの失敗メソッド呼び出しを、 `BaseCacheListener` から継承した `エラー` メソッドの呼び出しに置き換えます（このメソッドの説明については、上記のリスナーのセクションを参照してください）。 `エラー` メソッドの引数の場合、イベントから例外を取得し、ユーザーアクションとして使用する文字列を定義できます。 たとえば、オフラインモードをサポートするために、 `AddBookmarkInteractor` の `onFailure` メソッドは、 `BasicEvent`代わりに `BookmarkEvent` を受け取ります。 また、メソッドの `エラー` 呼び出しは、「ADD \ _BOOKMARK」文字列を定義して、サーバーにブックマークを追加しようとしたときにエラーが発生したことを示します。

    @Override public void onFailure(BookmarkEvent event) {
        getListener().error(event.getException(), "ADD_BOOKMARK"); 
    }

## 関連トピック

[イベントクラスを作成または更新する](/docs/7-1/tutorials/-/knowledge_base/t/create-or-update-the-event-class)

[リスナーを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-listener)

[スクリーンレットクラスを更新する](/docs/7-1/tutorials/-/knowledge_base/t/update-the-screenlet-class)

[キャッシュをサーバーと同期する](/docs/7-1/tutorials/-/knowledge_base/t/sync-the-cache-with-the-server)
