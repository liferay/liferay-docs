---
header-id: creating-the-screenlet-class
---

# スクリーンレットクラスの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Screenletクラスは、Screenletの中心的なハブです。 これには、スクリーンレットの動作を構成するための属性、スクリーンレットのビューへの参照、Interactor操作を呼び出すためのメソッドなどが含まれています。 Screenletを使用する場合、アプリ開発者は主にScreenletクラスと対話します。 つまり、Screenletが自己認識になると、そのScreenletクラスで発生します（これは発生しないと合理的に確信していますが）。

これをすべて可能にするには、ScreenletクラスがInteractorのリスナーインターフェイスを実装し、View ScreenインターフェイスとInteractorクラスを型引数として使用して、 [Screensの `BaseScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java) を拡張する必要があります。 Screenletクラスには、リスナーとアプリ開発者がアクセスする必要があるその他の属性のインスタンス変数とそれに付随するゲッターとセッターも含める必要があります。 コンストラクターの場合、 [`BaseScreenlet`のコンストラクター](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java#L45-L61)呼び出すことができます。

たとえば、 [Add Bookmark ScreenletのScreenletクラス](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/AddBookmarkScreenlet.java) は `BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>` を拡張し、 `AddBookmarkListener`を実装します。 また、 `AddBookmarkListener` インスタンス変数とブックマークのフォルダーID、およびこれらの変数のゲッターとセッターが含まれています。 また、コンストラクターが `BaseScreenlet`のコンストラクターを呼び出すことに注意してください。

    public class AddBookmarkScreenlet extends 
        BaseScreenlet<AddBookmarkViewModel, AddBookmarkInteractor>
        implements AddBookmarkListener {
    
        private long folderId;
        private AddBookmarkListener listener;
    
        public AddBookmarkScreenlet(Context context) {
            super(context);
        }
    
        public AddBookmarkScreenlet(Context context, AttributeSet attributes) {
            super(context, attributes);
        }
    
        public AddBookmarkScreenlet(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }
    
        public long getFolderId() {
            return folderId;
        }
    
        public void setFolderId(long folderId) {
            this.folderId = folderId;
        }
    
        public AddBookmarkListener getListener() {
            return listener;
        }
    
        public void setListener(AddBookmarkListener listener) {
            this.listener = listener;
        }
    
        ...

次に、Screenletのリスナーメソッドを実装します。 これにより、Screenletクラスはサーバー呼び出しの結果を受け取り、リスナーとして機能します。 これらのメソッドは、サーバーコールの結果をビュー（ビューモデル経由）およびその他のリスナーインスタンス（Screenletクラスのリスナーインスタンス経由）に伝達する必要があります。 たとえば、ブックマークスクリーンレットを追加するリスナーメソッドの実装は次のとおりです。

    public void onAddBookmarkSuccess() {
        getViewModel().showFinishOperation(null);
    
        if (listener != null) {
            listener.onAddBookmarkSuccess();
        }
    }
    
    public void onAddBookmarkFailure(Exception e) {
        getViewModel().showFailedOperation(null, e);
    
        if (listener != null) {
            listener.onAddBookmarkFailure(e);
        }
    }

これらのメソッドは、サーバー呼び出しがそれぞれ成功または失敗したときに呼び出されます。 最初に `getViewModel（）` を使用してView Modelインスタンスを取得し、 `BaseViewModel` メソッド `showFinishOperation` および `showFailedOperation` を呼び出してサーバー呼び出しの結果をViewに送信します。 `showFinishOperation` 呼び出しは `null` 送信します。ブックマークを追加するサーバー呼び出しが成功してもオブジェクトが返されないためです。 Screenletで成功したサーバー呼び出しが表示する必要のあるオブジェクトを返す場合、この `showFinishOperation` 呼び出しでそれらを送信する必要があります。 `showFailedOperation` 呼び出しは、ビューへのサーバー呼び出しの失敗から生じる `例外` を送信します。 これにより、情報エラーをユーザーに表示できます。 `onAddBookmarkSuccess` および `onAddBookmarkFailure` 実装は、同じ名前のリスナーインスタンスのメソッドを呼び出します。 これにより、サーバーコールの結果が、スクリーンレットを使用するアクティビティやフラグメントなど、リスナーインターフェイスを実装する他のクラスに送信されます。

次に、 `BaseScreenlet`の抽象メソッドを実装する必要があります。

  - `createScreenletView`：アプリ開発者のスクリーンレット属性設定を読み取り、ビューを拡大します。 [Android `TypedArray`](https://developer.android.com/reference/android/content/res/TypedArray.html) を使用して、属性設定を取得します。 属性値を適切な変数に設定し、ビューモデル参照を介して表示する必要があるデフォルト値を設定する必要があります。

    たとえば、Add Bookmark Screenletの `createScreenletView` メソッドは、 `TypedArray`介してアプリ開発者の属性設定を取得します。 これは、 `layoutId`、 `defaultTitle`、及び `フォルダID` 属性。 `layoutId` は、View参照（`view`）をインフレートするために使用され、View Modelインスタンス（`viewModel`）にキャストされます。 次に、View Modelインスタンスの `setTitle` メソッドが `defaultTitle` 呼び出され、ブックマークのデフォルトタイトルを設定します。 メソッドは、View参照を返すことで終了します。

    ``` 
      @Override
      protected View createScreenletView(Context context, AttributeSet attributes) {
          TypedArray typedArray = context.getTheme()
              .obtainStyledAttributes(attributes, R.styleable.AddBookmarkScreenlet, 0, 0);

          int layoutId = typedArray.getResourceId(R.styleable.AddBookmarkScreenlet_layoutId, 0);

          View view = LayoutInflater.from(context).inflate(layoutId, null);

          String defaultTitle = typedArray.getString(R.styleable.AddBookmarkScreenlet_defaultTitle);

          folderId = castToLong(typedArray.getString(R.styleable.AddBookmarkScreenlet_folderId));

          typedArray.recycle();

          AddBookmarkViewModel viewModel = (AddBookmarkViewModel) view;
          viewModel.setTitle(defaultTitle);

          return view;
      }
    ```

  - `createInteractor`：スクリーンレットのインタラクターをインスタンス化します。 たとえば、Add Bookmark Screenletの `createInteractor` メソッドは `AddBookmarkInteractor` コンストラクターを呼び出して、このInteractorの新しいインスタンスを作成します。

    ``` 
      @Override
      protected AddBookmarkInteractor createInteractor(String actionName) {
          return new AddBookmarkInteractor(getScreenletId());
      }
    ```

  - `onUserAction`：ビューにユーザーが入力したデータを取得し、Interactorインスタンスを介してスクリーンレットのサーバー操作を開始します。 Screenletがユーザー入力を受け取らない場合、このメソッドは後者を行うだけです。

    サンプルのブックマークスクリーンレットの追加は、ユーザー入力（ブックマークのURLとタイトル）を取得するため、 `onUserAction` メソッドがこのデータを取得する必要があります。 このメソッドは、 `getViewModel（）` メソッドで取得するView Modelインスタンスを介してそうします。 `onUserAction` メソッドは、ユーザー入力でInteractorの `start` メソッドを呼び出すことにより、サーバー操作を開始します。 Interactorは、 [`BaseInteractor` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/interactor/BaseInteractor.java)から `start` メソッドを継承することに注意してください。 `start` メソッドを呼び出すと、Interactorの `execute` メソッドがバックグラウンドスレッドで実行されます。

    ``` 
      @Override
      protected void onUserAction(String userActionName, AddBookmarkInteractor interactor, Object... args) {
          AddBookmarkViewModel viewModel = getViewModel();
          String url = viewModel.getURL();
          String title = viewModel.getTitle();

          interactor.start(url, title, folderId);
      }
    ```

いいね！ スクリーンレットが完成しました\！ これで、他の</a>と同じ方法で

使用できます。 あなたは、独自のプロジェクトであなたのScreenletを作成した場合は、することもできます [のパッケージと配布](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets) スクリーンプロジェクト、JCenter、またはMavenの中央を経由して、それを。</p> 

ブックマークスクリーンレットの追加の例を完了するために、次のセクションではこのスクリーンレットの使用方法を示します。 また、アプリの `server_context.xml` ファイルにデフォルトの属性値を設定する方法も示しています。 スクリーンレットを使用する際にこれを行う必要はないかもしれませんが、スクリーンレットのマスターになるために役立つかもしれません。



## 関連トピック

[UIを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui)

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor)

[属性の定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-attributes)

[スクリーンレットのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets)
