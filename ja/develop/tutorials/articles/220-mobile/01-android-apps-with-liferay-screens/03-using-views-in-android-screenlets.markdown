---
header-id: using-views-in-android-screenlets
---

# Androidスクリーンレットでビューを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screens *View* を使用して、スクリーンレットのコア機能に依存しないスクリーンレットのルックアンドフィールを設定できます。 Liferayのスクリーンレットにはいくつかのビューがあり、Liferayとコミュニティによってさらに多くのビューが開発されています。 [Screenletリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-android) は、Screensに含まれる各Screenletで利用可能なビューがリストされています。 このチュートリアルでは、Androidスクリーンレットでビューを使用する方法を示します。 それは簡単です。すぐにビューを使用してマスターできます\！

## ビューとビューセット

ビューを使用する前に、ビューを構成するコンポーネントを知っておく必要があります。 以下は、さまざまなビューの使用方法を学習するのに十分な簡単な説明です。 Liferay ScreensのViewレイヤーの詳細な説明については、チュートリアル [AndroidのLiferay Screensのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)参照してください。

ビューは次のアイテムで構成されます。

**Screenletクラス：** Screenletの機能を調整および実装するJavaクラス。 Screenletクラスには、Screenletの動作を構成するための属性、ScreenletのViewクラスへの参照、サーバー操作を呼び出すためのメソッドなどが含まれています。

**ビュークラス：** ビューの動作を実装するJavaクラス。 このクラスは通常、UIコンポーネントのイベントをリッスンします。

**レイアウト：** ビューのUIコンポーネントを定義するXMLファイル。 通常、Viewクラスはこのファイルのルート要素です。 ビューを使用するには、スクリーンレットXMLでそのレイアウトを指定する必要があります（この例をすぐに表示します）。

ビューにはScreenletクラスと特定のUIコンポーネントのセットが含まれているため、Viewは1つの特定のScreenletでのみ使用できることに注意してください。 たとえば、ログインスクリーンレットのデフォルトビューは、ログインスクリーンレットでのみ使用できます。 複数のスクリーンレットの複数のビューを組み合わせて、 *ビューセット*にすることができます。 通常、ビューセットは、いくつかのスクリーンレットに対して同様のルックアンドフィールを実装します。 これにより、アプリはビューセットを使用して、まとまりのあるルックアンドフィールを表示できます。 たとえば、 [のBank of Westerosサンプルアプリ](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) は、 [Westerosビューセットの](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main) ビューといくつかのスクリーンレットを使用して、Google Play</a>

を見ることができる赤と白のモチーフを提示します。 Liferay Screens for Androidにはデフォルトのビューセットが付属していますが、LiferayにはjCenterで追加のビューセット（たとえば、Material、Lexicon、Westeros）が用意されています。 誰でもビューセットを作成し、Maven CentralやjCenterなどのパブリックリポジトリで公開できます。</p> 

デフォルトのほかにビューセットをインストールするには、それらをプロジェクトの依存関係として追加します。 ここの `build.gradle` ファイルコードスニペットは、依存関係としてマテリアル、レキシコン、およびWesterosビューセットを指定します。

    dependencies {
        ...
        implementation 'com.liferay.mobile:liferay-material-viewset:+'
        implementation 'com.liferay.mobile:liferay-lexicon-viewset:+'   
        implementation 'com.liferay.mobile:liferay-westeros-viewset:+'
        ...
    }
    

Liferayが現在スクリーンに提供しているビューセットは次のとおりです。

**デフォルト：** スクリーンレットが標準装備されています。 レイアウトIDが指定されていない場合、またはレイアウトIDを持つビューが見つからない場合、スクリーンレットによって使用されます。 デフォルトビューは、カスタムビューの親ビューとして使用できます。 詳細については、 [アーキテクチャチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android) を参照してください。

**素材：** ゼロから構築されたビューを示します。 Googleの [Material Design](https://developer.android.com/design/material/index.html) ガイドラインに従っています。 独自のビューを作成する手順については、 [ビュー作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views) を参照してください。

**レキシコン：** スクラッチから構築されたビューを示します。 Liferayの [Lexicon Design](https://lexicondesign.io/) ガイドラインに準拠しています。

**Westeros：** [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) デモアプリの動作と外観をカスタマイズします。

ビューとビューセットについて理解したので、これらを使用する準備が整いました！



## ビューを使用する

スクリーンレットでビューを使用するには、アクティビティまたはフラグメントレイアウトにスクリーンレットXMLを挿入するときに、ビューのレイアウトを `liferay：layoutId` 属性の値として指定します。 たとえば、マテリアルビューでログインスクリーンレットを使用するには、 `liferay：layoutId` を `@ layout / login_material`設定してスクリーンレットのXMLを挿入します。

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        liferay:layoutId="@layout/login_material"
        />
    

次のリンクは、各ビューセットで使用可能なビューレイアウトを示しています。

  - [デフォルト](https://github.com/liferay/liferay-screens/tree/master/android/library/src/main/res/layout)
  - [材料](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material/src/main/res/layout)
  - [Lexicon(レキシコン)](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/lexicon/src/main/res/layout)
  - [ウェステロス](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros/src/main/res/layout)

使用するビューがビューセットの一部である場合、アプリまたはアクティビティのテーマは、そのビューセットのスタイルを定義するテーマも継承する必要があります。 たとえば、アプリの `res / values / styles.xml` の次のコードは、 `AppTheme.NoActionBar` に、マテリアルビューセットを親テーマとして使用するように指示します。

    <resources>
    
        <style name="AppTheme.NoActionBar" parent="material_theme">
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>
    
            <item name="windowActionBar">false</item>
            <item name="windowNoTitle">true</item>
        </style>
        ...
    </resources>
    

デフォルトを使用するには、レキシコン、またはWesterosビューセット、継承 `default_theme`、 `lexicon_theme` または `westeros_theme`それぞれ。

それでおしまい\！ すばらしいです\！ これで、ビューを使用してAndroidスクリーンレットをスプルースする方法がわかりました。 これにより、 [独自のビューを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)など、可能性の世界が開かれます。



## 関連トピック

[Liferay画面用のAndroidプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-android-projects-for-liferay-screens)

[Androidアプリでスクリーンレットを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-android-apps)

[Androidビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-android-views)

[Android向けLiferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-android)

[iOSスクリーンレットでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)
