---
header-id: creating-the-ui
---

# UIを作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screens for Androidでは、スクリーンレットUIはビューと呼ばれます。 すべてのスクリーンレットには、少なくとも1つのビューが必要です。 ビューは、次のコンポーネントで構成されています。

  - **ビューモデルインターフェイス：** ビューがUIを更新するために必要なメソッドを定義します。

  - **レイアウトXMLファイル：** ビューがエンドユーザーに提示するUIコンポーネントを定義します。

  - **Viewクラス：** UIをレンダリングし、ユーザーインタラクションを処理し、Screenletクラスと通信します。 Viewクラスは、View Modelインターフェイスを実装します。

  - **Screenletクラス：** 技術的にはビューの一部ですが、Screenletクラスは他のすべてのScreenletコンポーネントに依存しています。 したがって、このチュートリアルの最後までScreenletクラスを作成しません。

## スクリーンレットのビューモデルとレイアウトの作成

スクリーンレットのビュー用に作成する最初の項目は、そのビューモデルインターフェイスとレイアウトです。 次の手順でその方法を説明します。

1.  すべてのScreenletのViewクラスが実装する必要があるメソッドを定義するために、Screensは [`BaseViewModel` インターフェイス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java)提供します。 View Modelインターフェースは、 `BaseViewModel` を拡張して、スクリーンレットに必要な追加メソッドを定義する必要があります。 これには、使用する属性のゲッターとセッターが含まれます。

    たとえば、ブックマークの追加スクリーンレットには、各ブックマークのURLとタイトルの属性が必要です。 そのため、ビューモデルインターフェイス `AddBookmarkViewModel`、これらの属性のゲッターとセッターを定義します。
   
        public interface AddBookmarkViewModel extends BaseViewModel {
            String getURL();
       
            void setURL(String value);
       
            String getTitle();
       
            void setTitle(String value);
        }

2.  標準のAndroidレイアウトXMLファイルを記述して、スクリーンレットのUIを定義します。 レイアウトのルート要素は、ScreenletのViewクラスの完全修飾クラス名でなければなりません。 次の手順でそのクラスを作成しますが、ここで名前を決定し、レイアウトのルート要素に名前を付けます。 最後に、ビューに必要なUI要素を追加します。

    たとえば、ブックマークの追加スクリーンレットのレイアウトには、ブックマークのURLを入力するためのテキストフィールドと、タイトルを入力するためのテキストフィールドの2つが必要です。 レイアウトには、ブックマークを保存するためのボタンも必要です。 スクリーンレットは、このUIを [`bookmark_default.xml` レイアウトファイル](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/res/layout/bookmark_default.xml)で定義します。
   
        <?xml version="1.0" encoding="utf-8"?>
        <com.your.package.AddBookmarkView 
            xmlns:android="http://schemas.android.com/apk/res/android"
            style="@style/default_screenlet">
       
            <EditText
                android:id="@+id/url"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:hint="URL Address"
                android:inputType="textUri"/>
       
            <EditText
                android:id="@+id/title"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginBottom="15dp"
                android:hint="Title"/>
       
            <Button
                android:id="@+id/add_button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:text="Add Bookmark"/>
       
        </com.your.package.AddBookmarkView>

![図1：ブックマークの追加スクリーンレットのレイアウトには、2つのテキストフィールドとボタンが含まれています。](../../../../images/screens-android-add-bookmark-view.png)

次に、スクリーンレットのビュークラスを作成します。

## スクリーンレットのビュークラスの作成

Screenletには、作成したレイアウトをサポートするためにViewクラスが必要です。 このクラスは、Androidのレイアウトクラス（例えば拡張する必要があります `のLinearLayout`、 `リストビュー`）を、あなたのビューモデルのインターフェースを実装し、ユーザーアクションを処理するための別のリスナー・インターフェースを実装します。 このViewクラスを作成するには、次の手順に従います。

1.  スクリーンレットのUIに適したAndroidレイアウトクラスを拡張するViewクラスを作成します。 たとえば、Add Bookmark ScreenletはそのUIコンポーネントを単一の列にレンダリングするため、 [はViewクラス（`AddBookmarkView`）](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java) は [Androidの `LinearLayout`](https://developer.android.com/reference/android/widget/LinearLayout.html)拡張します。 Viewクラスのコンストラクターは、親レイアウトクラスのコンストラクターを呼び出す必要があります。 たとえば、 [`AddBookmarkView`のコンストラクター](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java#L20-L30) は `LinearLayout`のコンストラクターを呼び出します。
   
        public AddBookmarkView(Context context) {
            super(context);
        }
       
        public AddBookmarkView(Context context, AttributeSet attributes) {
            super(context, attributes);
        }
       
        public AddBookmarkView(Context context, AttributeSet attributes, int defaultStyle) {
            super(context, attributes, defaultStyle);
        }

2.  ビューモデルの属性と `BaseScreenlet`のインスタンス変数を追加します。 たとえば、ブックマークスクリーンレットの追加には、ブックマークのURLとタイトルのインスタンス変数が必要です。 すべてのScreenletクラスは [`BaseScreenlet` クラス](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/BaseScreenlet.java)拡張するため、Viewクラスの `BaseScreenlet` 変数により、Viewが常にScreenletへの参照を持つようになります。 たとえば、 `AddBookmarkView`のインスタンス変数は次のとおりです。
   
        private EditText urlText;
        private EditText titleText;
        private BaseScreenlet screenlet;

3.  View Modelインターフェースを実装します。 View Modelのgetterおよびsetterメソッドを実装して、それぞれ各コンポーネントの内部値を取得および設定します。 たとえば、 `AddBookmarkView`の `AddBookmarkViewModel`の実装は次のとおりです。
   
        public String getURL() {
            return urlText.getText().toString();
        }
       
        public void setURL(String value) {
            urlText.setText(value);
        }
       
        public String getTitle() {
            return titleText.getText().toString();
        }
       
        public void setTitle(String value) {
            titleText.setText(value);
        }

4.  スクリーンレットでユーザーアクションを処理するリスナーインターフェイスを実装します。 たとえば、ブックマークの追加スクリーンレットは、ユーザーが[保存]ボタンを押したときに検出する必要があります。 `AddBookmarkView` クラスは、 [Android `View.OnClickListener` インターフェース](https://developer.android.com/reference/android/view/View.OnClickListener.html)実装することでこれを可能にします。これは、1つのメソッド `onClick`を定義します。 Screenletの `onClick` 実装は、Screenletへの参照を取得し、その `performUserAction（）` メソッドを呼び出します（Screenletクラスで `performUserAction（）` をすぐに作成します）。
   
        public void onClick(View v) {
            AddBookmarkScreenlet screenlet = (AddBookmarkScreenlet) getParent();
       
            screenlet.performUserAction();
        }

    `onFinishInflate（）` メソッドを実装することにより、リスナーを適切なUI要素に設定できます。 このメソッドは、レイアウトから他のUI要素を取得して割り当てる必要もあります。 たとえば、 `AddBookmarkView` の `onFinishInflate（）` 実装は、レイアウトからURL属性とタイトル属性を取得し、それぞれ `urlText` および `titleText` 変数に設定します。 次に、このメソッドはレイアウトからボタンを取得し、このViewクラスをボタンのクリックリスナーとして設定します。
   
        protected void onFinishInflate() {
            super.onFinishInflate();
       
            urlText = (EditText) findViewById(R.id.url);
            titleText = (EditText) findViewById(R.id.title_bookmark);
       
            Button addButton = (Button) findViewById(R.id.add_button);
            addButton.setOnClickListener(this);
        }

5.  実施 [ `BaseViewModel` インターフェース](https://github.com/liferay/liferay-screens/blob/master/android/library/src/main/java/com/liferay/mobile/screens/base/view/BaseViewModel.java)の方法： `showStartOperation`、 `showFinishOperation`、 `showFailedOperation`、 `getScreenlet`、及び `setScreenlet`。 `show * Operation` メソッドでは、サーバー操作の開始時、正常終了時、または失敗時にそれぞれスクリーンレットで何が起こるかを記録できます。 `getScreenlet` および `setScreenlet` メソッドでは、それぞれ `BaseScreenlet` 変数を取得および設定する必要があります。 これにより、ビューに常にスクリーンレット参照が設定されます。 たとえば、Add Bookmark Screenletはこれらのメソッドを次のように実装します。
   
        @Override
        public void showStartOperation(String actionName) {
       
        }
       
        @Override
        public void showFinishOperation(String actionName) {
            LiferayLogger.i("Add bookmark successful");
        }
       
        @Override
        public void showFailedOperation(String actionName, Exception e) {
            LiferayLogger.e("Could not add bookmark", e);
        }
       
        @Override
        public BaseScreenlet getScreenlet() {
            return screenlet;
        }
       
        @Override
        public void setScreenlet(BaseScreenlet screenlet) {
            this.screenlet = screenlet;
        }

    `show[something]Operation` メソッドを実装する必要がありますが、特定のアクションを実行する必要がない場合は、実装を空のままにしておくことができます。

[完全な例 `AddBookmarkView` クラスを表示するには、](https://github.com/liferay/liferay-screens/blob/master/android/samples/addbookmarkscreenlet/src/main/java/com/liferay/mobile/screens/bookmark/view/AddBookmarkView.java) をクリックしてください。

すばらしいです\！ Viewクラスが完成しました。 これで、スクリーンレットのインタラクタークラスを作成する準備ができました。

## 関連トピック

[インタラクターの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-interactor)

[属性の定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-the-attributes)

[スクリーンレットクラスの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-the-screenlet-class)

[スクリーンレットのパッケージ化](/docs/7-1/tutorials/-/knowledge_base/t/packaging-your-screenlets)
