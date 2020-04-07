---
header-id: creating-the-interactor-0
---

# インタラクターの作成

[TOC levels=1-4]

Interactorsがサーバー呼び出しの結果を取得して処理する基本的なスクリーンレット作成チュートリアル</a> を

から思い出してください。 また、次のコンポーネントがインタラクターを構成することを思い出してください。</p> 

1.  イベント
2.  リスナー
3.  インタラクタークラス

これらのコンポーネントは、非リストスクリーンレットと同じ基本機能をリストスクリーンレットで実行します。 ただし、それらの作成は少し異なります。 以下の各セクションでは、これらのコンポーネントの1つを作成する方法を示します。 最初に、イベントを作成します。



## イベントを作成する

Screensは、 [EventBus](http://greenrobot.org/eventbus/) ライブラリを使用して、スクリーンレット内の通信を処理します。 したがって、スクリーンレットコンポーネントは、サーバー呼び出しの結果を含むイベントクラスを使用して相互に通信します。 あなたのリストScreenletのイベントクラスを拡張する必要があります [ `ListEvent` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/ListEvent.java) モデルクラスでパラメータを。 イベントクラスには、モデルクラスのプライベートインスタンス変数、この変数を設定するコンストラクター、スーパークラスコンストラクターを呼び出す引数なしのコンストラクターも含める必要があります。 たとえば、ブックマークリストスクリーンレットのイベントクラス（`BookmarkEvent`）は、 `ブックマーク` オブジェクトを通信します。 したがって、型引数として `ブックマーク` を使用して `ListEvent` を拡張し、その `BookmarkEvent（Bookmark bookmark）` コンストラクターが設定するプライベート `Bookmark` 変数を定義します。

    public class BookmarkEvent extends ListEvent<Bookmark> {
    
          private Bookmark bookmark;
    
          public BookmarkEvent() {
                super();
          }
    
          public BookmarkEvent(Bookmark bookmark) {
              this.bookmark = bookmark;
          }
        ...
    

また、イベントクラスに `ListEvent`の抽象メソッドを実装する必要があります。 これらのメソッドは [オフラインモード](/docs/7-1/tutorials/-/knowledge_base/t/using-offline-mode-in-android)サポートすることに注意してください。 ここではこれらの方法について簡単に説明しますが、スクリーンレットでのオフラインモードのサポートについては、別のチュートリアルで詳しく説明します。

  - `getListKey`：キャッシュのIDを返します。 このIDは通常、各リスト行に表示されるデータです。 たとえば、 `BookmarkEvent` の `getListKey` メソッドは、ブックマークのURLを返します。 
    
    

    ``` 
      @Override
      public String getListKey() {
          return bookmark.getUrl();
      }
    ```


  - `getModel`：モデルクラスインスタンスを返すことにより、モデルエンティティをキャッシュにラップ解除します。 たとえば、 `BookmarkEvent` メソッドの `getModel` メソッドは、ブックマークを返します。 
    
    

    ``` 
      @Override
      public Bookmark getModel() {
          return bookmark;
      }
    ```


次に、スクリーンレットのリスナーを作成します。



## リスナーの作成

アプリ開発者は、リスナーを使用して、スクリーンレットで発生するイベントに応答できることを思い出してください。 たとえば、アクティビティでLogin Screenletを使用するアプリ開発者は、ログインの成功または失敗に応答するために、そのアクティビティに `LoginListener` を実装する必要があります。 ただし、リストScreenletを作成する場合、別個のリスナーを作成する必要はありません。 開発者が実装することによって、活性またはそのフラグメントにリストScreenletを使用することができ [ `BaseListListener` インターフェース](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListListener.java) モデルクラスにパラメータ化。 たとえば、アクティビティでブックマークリストスクリーンレットを使用する場合、アプリ開発者のアクティビティ宣言は次のようになります。

    public class BookmarkListActivity extends AppCompatActivity 
        implements BaseListListener<Bookmark> {...
    

`BaseListListener` インターフェイスは、アプリ開発者がアクティビティまたはフラグメントに実装できる次のメソッドを定義します。

  - `void onListPageFailed（int startRow、Exception e）`：サーバーからエンティティを取得するスクリーンレットの失敗に応答します。

  - `void onListPageReceived（int startRow、int endRow、List<E> エントリ、int rowCount）`：サーバーからエンティティを取得するスクリーンレットの成功に応答します。

  - `void onListItemSelected（E element、View view）`：リスト内のユーザー選択に応答します。

これらのメソッドがリストスクリーンレットのニーズを満たしている場合、このチュートリアルの次のセクションに進むことができます。 ただし、アプリ開発者がより多くのアクションに応答できるようにする場合は、モデルクラスでパラメーター化された `BaseListListener` を拡張する独自のリスナーを作成する必要があります。 たとえば、ブックマークリストスクリーンレットには次のようなリスナーが含まれています： `BookmarkListListener`。 このリスナーは、Interactorが呼び出されたときにアプリ開発者に通知する単一のメソッドを定義します。

    public interface BookmarkListListener extends BaseListListener<Bookmark> {
        void interactorCalled();
    }
    

次に、Interactorクラスを作成します。



## インタラクタークラスの作成

Interactorクラスは、Interactorの中心的なコンポーネントとして、@product@からエンティティを取得するサービス呼び出しを行い、その呼び出しの結果を処理することを思い出してください。 リストScreenletフレームワークの [`BaseListInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/interactor/BaseListInteractor.java) は、リストScreenletのInteractorクラスが必要とするほとんどの機能を提供します。 ただし、 `BaseListInteractor` を拡張して、サービスを呼び出し、モデルおよびイベントクラスを介して結果を処理する必要があります。 したがって、Interactorクラスは、 `BaseListInteractorListener<YourModelClass>` とイベントクラスでパラメーター化された `BaseListInteractor`拡張する必要があります。 たとえば、ブックマークリストスクリーンレットのInteractorクラス `BookmarkListInteractor`、 `BaseListInteractorListener<Bookmark>` および `BookmarkEvent`パラメーター化された `BaseListInteractor` 拡張します。

    public class BookmarkListInteractor extends 
        BaseListInteractor<BaseListInteractorListener<Bookmark>, BookmarkEvent> {...
    

Interactorは、サーバーを呼び出して結果を処理するために必要なメソッドもオーバーライドする必要があります。

  - `getPageRowsRequest`：指定されたエンティティのページを取得します。 例 `BookmarkListInteractor`では、このメソッドは最初に `args` パラメーターを使用して、ブックマークを取得するフォルダーのIDを取得します。 次に、アプリ開発者がフラグメントまたはアクティビティにScreenlet XMLを挿入するときにコンパレーターを設定する場合、コンパレーターを設定します（これについては後ほど説明します）。 `getPageRowsRequest` メソッドは、 `BookmarksEntryService`の `getEntries` メソッドを呼び出してブックマークのページを取得することで終了します。 サービスコールは、中にサービスコールのようなことを注意 [基本Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor)、使用して `LiferayServerContext.isLiferay7を（）` 必ず正しいサービスインスタンスが使用されていることを確認するためのポータルのバージョンを確認します。 1つのポータルバージョンでのみスクリーンレットを使用する場合、これは必要ありません。 また、サービス呼び出しを行うために使用される `groupId` 変数は、 `getPageRowsRequest` または `BookmarkListInteractor`どこにも設定されていないことに注意してください。 `BookmarkListInteractor`ように、 `BaseListInteractor`を拡張するインタラクターは、Screensフレームワークを介してこの変数を継承します。 Screenletクラスを作成するときに設定します。 以下は `BookmarkListInteractor`の完全な `getPageRowsRequest` メソッドです。 
    
    

    ``` 
      @Override
      protected JSONArray getPageRowsRequest(Query query, Object... args) throws Exception {
          long folderId = (long) args[0];

          if (args[1] != null) {
              query.setComparator((String) args[1]);
          }

          if (LiferayServerContext.isLiferay7()) {
              return new BookmarksEntryService(getSession()).getEntries(groupId, folderId, 
                  query.getStartRow(), query.getEndRow(), query.getComparatorJSONWrapper());
          } else {
              return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                  getSession()).getEntries(groupId, folderId, query.getStartRow(), 
                  query.getEndRow(), query.getComparatorJSONWrapper());
          }
      }
    ```


コンパレータとは何かを自問しているかもしれません。 コンパレータは、ポートレットのエンティティをソートする@product@インスタンス内のクラスです。 たとえば、ブックマークポートレットには、さまざまな基準でエンティティをソートできる複数のコンパレータが含まれています。 [これらのコンパレータを表示するには、](https://github.com/liferay/liferay-portal/tree/master/modules/apps/collaboration/bookmarks/bookmarks-api/src/main/java/com/liferay/bookmarks/util/comparator) をクリックしてください。 必須ではありませんが、リストスクリーンレットを作成して、コンパレータを使用してエンティティを並べ替えることができます。 ブックマークリストスクリーンレットはコンパレータをサポートしているため、このチュートリアルを進めていくと、さらに多くのことがわかります。

  - `getPageRowCountRequest`：エンティティの数を取得して、ページネーションを有効にします。 例 `BookmarkListInteractor`では、このメソッドは最初に `args` パラメーターを使用して、ブックマークをカウントするフォルダーのIDを取得します。 `BookmarksEntryService`の `getEntriesCount` メソッドを呼び出して、ブックマークの数を取得します。 
    
    

    ``` 
      @Override
      protected Integer getPageRowCountRequest(Object... args) throws Exception {
          long folderId = (long) args[0];

          if (LiferayServerContext.isLiferay7()) {
              return new BookmarksEntryService(getSession()).getEntriesCount(groupId, folderId);
          } else {
              return new com.liferay.mobile.android.v62.bookmarksentry.BookmarksEntryService(
                  getSession()).getEntriesCount(groupId, folderId);
          }
      }
    ```


  - `createEntity`：サーバー呼び出しの結果を含むイベントのインスタンスを返します。 このメソッドは、結果を `Map<String, Object>`として受け取り、モデルクラスのインスタンス化に使用します。 次に、このモデルインスタンスを使用してイベントオブジェクトを作成します。 例 `BookmarkListInteractor`では、このメソッドは `Map<String, Object>` を `Bookmark` コンストラクターに渡します。 次に、結果の `Bookmark` を使用して、 `BookmarkEvent`を作成して返します。 
    
    

    ``` 
      @Override
      protected BookmarkEvent createEntity(Map<String, Object> stringObjectMap) {
          Bookmark bookmark = new Bookmark(stringObjectMap);
          return new BookmarkEvent(bookmark);
      }
    ```


  - `getIdFromArgs`：最初のオブジェクト引数の値を文字列として返すボイラープレートメソッド。 これは、 [オフラインモード](/docs/7-1/tutorials/-/knowledge_base/t/using-offline-mode-in-android)キャッシュキーとして機能します。 
    
    

    ``` 
      @Override
      protected String getIdFromArgs(Object... args) {
          return String.valueOf(args[0]);
      }
    ```


Screenletでオフラインモードをサポートする予定がない場合でも、このメソッドを実装する必要があります。 Interactorクラスにこのメソッドがあると、後でオフラインモード機能を簡単に追加できます。 スクリーンレットでのオフラインモードのサポートについては、別のチュートリアルで詳しく説明します。

完全な `BookmarkListInteractor` クラスを表示するには、 [ここをクリック](https://github.com/liferay/liferay-screens/blob/master/android/samples/listbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/listbookmark/BookmarkListInteractor.java)。

次に、Screenletクラスを作成します。



## 関連トピック

[モデルクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class)

[ビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-view)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class-0)
