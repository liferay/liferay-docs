---
header-id: creating-the-view
---

# ビューを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

リコール [基本Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui) ビューScreenletのUIを定義していること。 そのリストに対応するために、リストスクリーンレットのビューは、非リストスクリーンレットのビューとは少し異なります。 リストスクリーンレットのビューを作成するには、次のコンポーネントを作成します。

1.  行レイアウト：各リスト行のレイアウト。
2.  アダプタクラス：各リスト行にデータを入力する [Androidアダプタクラス](http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews)。
3.  ビュークラス：ビューを制御するクラス。 このクラスは、リストScreenletで非リストScreenletと同じ目的を果たします。
4.  メインレイアウト：リスト全体のレイアウト。 これは、個々の行のUIを定義する行レイアウトとは異なることに注意してください。

最初に、行レイアウトを作成します。

## 行レイアウトの作成

ビューの残りの部分を作成する前に、リストの各行に使用するレイアウトを最初に定義する必要があります。 たとえば、ブックマークリストスクリーンレットでは、各行にブックマークを表示する必要があります。 したがって、行レイアウト（`res / layout / bookmark_row.xml`）は、ブックマークのURLを表示する単一の `TextView` を含む `LinearLayout` です。

    <?xml version="1.0" encoding="utf-8"?>
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">
    
        <TextView
            android:id="@+id/bookmark_url"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>
    
    </LinearLayout>

ご覧のとおり、この例は非常に単純です。 ただし、行レイアウトは、コンテンツを表示するために必要なだけ単純または複雑にすることができます。

次に、アダプタークラスを作成します。

## アダプタークラスの作成

[Androidアダプター](https://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews) はレイアウトをコンテンツで埋めます。 ブックマークリストスクリーンレットの例では、レイアウトは行レイアウト（`bookmark_row.xml`）であり、コンテンツは各リストアイテム（URL）です。 リストのスクロールをスムーズにするには、アダプタークラスで [Androidビューホルダー](https://developer.android.com/training/improving-layouts/smooth-scrolling.html#ViewHolder)使用する必要があります。 これを簡単にするために、モデルクラスとビューホルダーを型引数として使用して、リストScreenletフレームワークの [`BaseListAdapter` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListAdapter.java) を拡張できます。 `BaseListAdapter`拡張することにより、アダプターは2つのメソッドのみを必要とします。

  - `createViewHolder`：ビューホルダーをインスタンス化します
  - `fillHolder`：各行のビューホルダーを塗りつぶします

ビューホルダーには、各行を表示する必要があるデータの変数も含める必要があります。 ビューホルダーは、これらの変数を対応する行レイアウト要素に割り当て、適切なデータを設定する必要があります。

たとえば、ブックマークリストスクリーンレットのアダプタークラス（`BookmarkAdapter`）は、 `BaseListAdapter` を `ブックマーク` および `BookmarkAdapter.BookmarkViewHolder` を型引数として拡張します。 このクラスのビューホルダーは、 `BaseListAdapter`のビューホルダーを拡張する内部クラスです。 ブックマークリストスクリーンレットは各行にURLを表示するだけでよいため、ビューホルダーに必要な変数は `url`です。 ビューホルダーのコンストラクターは、 `bookmark_row.xml` から `TextView` をこの変数に割り当てます。 `bind` メソッドは、ブックマークのURLを `TextView`のテキストとして設定します。 `BookmarkAdapter` の他のメソッドは、ビューホルダーを活用します。 `createViewHolder` メソッドは、 `BookmarkViewHolder`インスタンス化します。 `fillHolder` メソッドは、ビューホルダーの `bind` メソッドを呼び出して、ブックマークのURLを `url` 変数のテキストとして設定します。

    public class BookmarkAdapter extends BaseListAdapter<Bookmark, BookmarkAdapter.BookmarkViewHolder> {
    
        public BookmarkAdapter(int layoutId, int progressLayoutId, BaseListAdapterListener listener) {
            super(layoutId, progressLayoutId, listener);
        }
    
        @NonNull
        @Override
        public BookmarkViewHolder createViewHolder(View view, BaseListAdapterListener listener) {
            return new BookmarkAdapter.BookmarkViewHolder(view, listener);
        }
    
        @Override
        protected void fillHolder(Bookmark entry, BookmarkViewHolder holder) {
            holder.bind(entry);
        }
    
        public class BookmarkViewHolder extends BaseListAdapter.ViewHolder {
    
            private final TextView url;
    
            public BookmarkViewHolder(View view, BaseListAdapterListener listener) {
                super(view, listener);
    
                url = (TextView) view.findViewById(R.id.bookmark_url);
            }
    
            public void bind(Bookmark entry) {
                url.setText(entry.getUrl());
            }
        }
    }

すばらしいです\！ アダプタクラスが完成しました。 次に、Viewクラスを作成します。

## ビュークラスの作成

アダプターが存在するので、リストScreenletのViewクラスを作成できます。 リコール [基本Screenlet作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-ui) Viewクラスは、任意のScreenletのUIの中央ハブであること。 UIをレンダリングし、ユーザーインタラクションを処理し、Screenletクラスと通信します。 リストScreenletフレームワークは経由してあなたのために、この機能のほとんどを提供します [ `BaseListScreenletView` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/list/BaseListScreenletView.java)。 Viewクラスは、このクラスを拡張して、行レイアウトIDとアダプターのインスタンスを提供する必要があります。 これを行うには、 `BaseListScreenletView`の `getItemLayoutId` および `createListAdapter` メソッドをオーバーライドします。 多くの場合、これはViewクラスに必要な唯一のカスタム機能であることに注意してください。 さらに必要な場合は、新しいメソッドを作成するか、他の `BaseListScreenletView` メソッドをオーバーライドして提供できます。

型引数としてモデルクラス、ビューホルダー、およびアダプターを使用して `BaseListScreenletView` を拡張することにより、Viewクラスを作成します。 これは、Viewクラスがアダプター内のビューホルダーでモデルオブジェクトを表すために必要です。 たとえば、ブックマークリストスクリーンレットのビュークラス（`BookmarkListView`）は、 `BookmarkAdapter`内の `BookmarkViewHolder` 内の `Bookmark` インスタンスを表す必要があります。 `BookmarkListView` クラスは、したがって、拡張する必要が `BaseListScreenletView` 用いてパラメータ化 `ブックマーク`、 `BookmarkAdapter.BookmarkViewHolder`、及び `BookmarkAdapter`。 `createListAdapter` をオーバーライドして `BookmarkAdapter` インスタンスを返すことに加えて、このViewクラスがサポートする必要がある他の機能は、リスト内の各行のレイアウトを取得することだけです。 オーバーライドされた `getItemLayoutId` メソッドは、行レイアウト `bookmark_row`返すことでこれを行います。

    import android.content.Context;
    import android.util.AttributeSet;
    
    import com.liferay.mobile.screens.base.list.BaseListScreenletView;
    
    public class BookmarkListView
        extends BaseListScreenletView<Bookmark, BookmarkAdapter.BookmarkViewHolder, BookmarkAdapter> {
    
        public BookmarkListView(Context context) {
            super(context);
        }
    
        public BookmarkListView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }
    
        public BookmarkListView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }
    
        @Override
        protected BookmarkAdapter createListAdapter(int itemLayoutId, int itemProgressLayoutId) {
            return new BookmarkAdapter(itemLayoutId, itemProgressLayoutId, this);
        }
    
        @Override
        protected int getItemLayoutId() {
            return R.layout.bookmark_row;
        }
    }

次に、ビューのメインレイアウトを作成します。

## ビューのメインレイアウトの作成

リスト行のレイアウトはすでに作成していますが、リスト全体を定義するにはレイアウトを作成する必要があります。 このレイアウトには以下が含まれている必要があります。

  - レイアウトの最初の要素としてのViewクラスの完全修飾名。
  - [アンドロイド `RecyclerView`](http://developer.android.com/training/material/lists-cards.html#RecyclerView) アプリケーションが効率的にアイテムの潜在的に大きなリストをスクロールできるようにします。
  - [Android `ProgressBar`](https://developer.android.com/reference/android/widget/ProgressBar.html) リストをロードするときの進行状況を示します。

Viewクラスとスタイリングを除き、このレイアウトのコードはすべてのリストスクリーンレットで同じです。 たとえば、ブックマークリストスクリーンレットのレイアウト `res / layout / list_bookmarks.xml`次のとおりです。

    <com.liferay.mobile.screens.listbookmark.BookmarkListView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/liferay_list_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    
        <ProgressBar
            android:id="@+id/liferay_progress"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="center"
            android:visibility="gone"/>
    
        <android.support.v7.widget.RecyclerView
            android:id="@+id/liferay_recycler_list"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:visibility="gone"/>
    </com.liferay.mobile.screens.listbookmark.BookmarkListView>

| **警告：** `アンドロイド：ID` 、あなたのビューのレイアウトXMLの必須の値 **正確に** |ここに示されているものと一致します。 これらの値はScreensフレームワークにハードコーディングされています|それらを変更すると、アプリがクラッシュします。

よくやった\！ ビューが完成しました。 次に、スクリーンレットのインタラクターを作成します。

## 関連トピック

[モデルクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-model-class)

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor-0)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class-0)
