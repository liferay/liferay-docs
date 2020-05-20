---
header-id: creating-the-screenlet-class-0
---

# スクリーンレットクラスの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

リコール [基本Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class) Screenletクラスは、あなたのScreenletの焦点となっていること。 Screenletの動作を管理し、アプリ開発者が対話する主要なコンポーネントです。 リスト以外のスクリーンレットと同様に、アプリ開発者が利用できるようにするXML属性を最初に定義する必要があります。 たとえば、ブックマークリストスクリーンレットは次の属性を定義します。

  - `groupId`：ブックマークポートレットを含むサイトのID
  - `folderId`：ブックマークを取得するブックマークポートレットフォルダーのID
  - `コンパレーター`：ブックマークのソートに使用するコンパレーターの名前

スクリーンレットは、 `res / values / bookmark_attrs.xml` ファイルでこれらの属性を定義します。

    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <declare-styleable name="BookmarkListScreenlet">
            <attr name="groupId"/>
            <attr name="folderId"/>
            <attr format="string" name="comparator"/>
        </declare-styleable>
    </resources>

これで、Screenletクラスを作成する準備ができました。 [ `BaseListScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenlet.java) は、ページネーションやその他のデフォルトの動作のメソッドなど、リストScreenletのすべてのScreenletクラスに基本機能を提供するため、Screenletクラスは、モデルクラスとInteractorを型引数として `BaseListScreenlet` を拡張する必要があります。

たとえば、Bookmark List ScreenletのScreenletクラス`BookmarkListScreenlet`--extends `BaseListScreenlet` 、 `Bookmark` および `BookmarkListInteractor`パラメーター化されています。

    public class BookmarkListScreenlet 
        extends BaseListScreenlet<Bookmark, BookmarkListInteractor> {...

Interactorに渡すXML属性のインスタンス変数も作成する必要があります。 例えば、リコールのリクエストメソッドこと `BookmarkListInteractor` 2つの受け取る `オブジェクト` のフォルダIDと比較：引数。 したがって、 `BookmarkListScreenlet` クラスには、これらのパラメーターの変数を含める必要があります。そうすると、Interactorに渡すことができます。

    private long folderId;
    private String comparator;

コンストラクターの場合、スーパークラスコンストラクターを活用します。 たとえば、 `BookmarkListScreenlet`のコンストラクタは次のとおりです。

    public BookmarkListScreenlet(Context context) {
        super(context);
    }
    
    public BookmarkListScreenlet(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    public BookmarkListScreenlet(Context context, AttributeSet attrs, int defStyleAttr, 
        int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
    }

ここで、 `エラー` メソッドを実装する必要があります。 これは、Screenletフレームワークのリスナーを使用して、例外、およびサービス呼び出し中に発生する例外を生成したユーザーアクションを伝播する定型的なメソッドです。 この方法は、リスナーをチェックし、その呼び出すことによってこれを行い `エラー` と方法 `の例外` 及び `userAction`：

    @Override
    public void error(Exception e, String userAction) {
        if (getListener() != null) {
            getListener().error(e, userAction);
        }
    }

次に、 `createScreenletView` メソッドをオーバーライドして、前に定義したXML属性の値を読み取り、スクリーンレットのビューを作成します。 [から基本的なスクリーンレット作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class) を思い出してください

このメソッドは、対応するインスタンス変数に属性値を割り当てます。 たとえば、 `BookmarkListScreenlet` の `createScreenletView` メソッドは、 `folderId` および `コンパレータ` 属性値を同じ名前の変数に割り当てます。 このメソッドは、ローカル変数 `groupId`も設定します。 Screensフレームワークがこの変数をInteractorに伝播することを思い出してください。 スーパークラスの `createScreenletView` メソッドを呼び出して、 `createScreenletView` メソッドを終了し` 。 これにより、ビューがインスタンス化されます。</p>

<pre><code>@Override
protected View createScreenletView(Context context, AttributeSet attributes) {
    TypedArray typedArray = context.getTheme().obtainStyledAttributes(attributes, 
        R.styleable.BookmarkListScreenlet, 0, 0);
    groupId = typedArray.getInt(R.styleable.BookmarkListScreenlet_groupId, 
        (int) LiferayServerContext.getGroupId());
    folderId = typedArray.getInt(R.styleable.BookmarkListScreenlet_folderId, 0);
    comparator = typedArray.getString(R.styleable.BookmarkListScreenlet_comparator);
    typedArray.recycle();

    return super.createScreenletView(context, attributes);
}
`</pre> 

次に、 `loadRows` メソッドをオーバーライドして、Interactorを起動し、サーバーからリスト行を取得します。 このメソッドは、Interactorのインスタンスを引数として受け取り、これを使用してInteractorの `start` メソッドを呼び出します。 Interactorは `BaseListInteractor`から `start` を継承することに注意してください。 `loadRows` メソッドを使用して、Interactorの起動時に実行する他のコードを実行することもできます。 たとえば、 `BookmarkListScreenlet` の `loadRows` メソッドは最初にリスナーインスタンスを取得し、リスナーの `interactorCalled` メソッドを呼び出すことができます。 次に、Interactorの `start` メソッドを `folderId` と `コンパレーター`呼び出して、サーバー操作を開始してリスト行を取得します。

    @Override
    protected void loadRows(BookmarkListInteractor interactor) {
    
        ((BookmarkListListener) getListener()).interactorCalled();
    
        interactor.start(folderId, comparator);
    }
    

Interactorが引数を必要としない場合、 `start` メソッド `0` または `null`を渡すことができます。 ただし、引数なしで `start` を呼び出すと、サーバー呼び出しが失敗します。

最後に、 `createInteractor` メソッドをオーバーライドして、Interactorをインスタンス化します。 このメソッドが行う必要があるのはそれだけなので、Interactorのコンストラクターを呼び出して、新しいインスタンスを返します。 たとえば、 `BookmarkListScreenlet`の `createInteractor` メソッドは、新しい `BookmarkListInteractor`返します。

    @Override
    protected BookmarkListInteractor createInteractor(String actionName) {
        return new BookmarkListInteractor();
    }
    

完了です！ スクリーンレットは、アプリで使用できるすぐに使えるコンポーネントです。 Screenlet</a> を パッケージ化してScreensプロジェクトに提供したり、Maven CentralまたはjCenterで配布したりすることもできます。</p> 



## 関連トピック

[モデルクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class)

[ビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-view)

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0)
