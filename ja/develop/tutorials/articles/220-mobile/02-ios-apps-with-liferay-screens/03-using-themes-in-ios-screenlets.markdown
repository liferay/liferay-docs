---
header-id: using-themes-in-ios-screenlets
---

# iOSスクリーンレットでテーマを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screens *Theme*を使用して、スクリーンレットのUIコンポーネント、スタイル、および動作を設定できます。 コア機能を心配することなく、ScreenletのUIとUXに集中できます。 Liferayのスクリーンレットにはいくつかのテーマがあり、Liferayとコミュニティによってさらに多くのテーマが開発されています。 Liferay Screenletのテーマは、そのリファレンスドキュメント [指定されています](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)。 このチュートリアルでは、iOSスクリーンレットでテーマを使用する方法を示します。

iOSアプリのスクリーンレットにテーマをインストールするには、テーマの公開方法に応じて2つのオプションがあります。

1.  テーマがCocoaPodsポッドの依存関係としてパッケージ化されている場合、Podfileに行を追加してインストールできます。
   
        pod 'LiferayScreensThemeName'

    `LiferayScreensThemeName` をテーマのCocoaPodsプロジェクト名に置き換えてください。

2.  CocoaPodsでテーマを利用できない場合は、テーマのフォルダーをプロジェクトにドラッグアンドドロップできます。 Liferay Screensは新しいクラスを検出し、ランタイムおよびInterface Builderで新しいデザインを適用します。

![図1：テーマをXcodeプロジェクトにインストールするには、テーマのフォルダーをドラッグアンドドロップします。](../../../images/screens-ios-xcode-install-theme.png)

インストールされたテーマを使用するには、Interface Builderの *ベーススクリーンレット* の *テーマ名* プロパティフィールドに名前を指定します。 各スクリーンレットのテーマの名前は、スクリーンレットの [リファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/screenlets-in-liferay-screens-for-ios)の *テーマ* セクションにリストされてい* 。 テーマ名プロパティを空白のままにするか、見つからないテーマの名前を入力すると、スクリーンレットのデフォルトテーマが使用されます。</p>

![図2：Interface Builderでは、*テーマ名*フィールドに名前を入力してスクリーンレットのテーマを指定します。これにより、スクリーンレットの <code>themeName</code> プロパティが設定されます。](../../../images/screens-ios-themes-property.png)

Liferay Screens for iOSの最初のリリースには、スクリーンレット用の次のテーマが含まれています。

  - *デフォルト*：スクリーンレットが標準装備されています。 テーマ名が指定されていない場合、または指定されたテーマが見つからない場合、スクリーンレットによって使用されます。 デフォルトテーマは、カスタムテーマの親テーマとして使用できます。 詳細については、 [アーキテクチャチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios) を参照してください。
  - *Flat7*：最初から作成したテーマを示します。 独自のテーマを作成する手順については、 [テーマ作成チュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes) を参照してください。
  - *Westeros*： [Westeros Bank](https://github.com/liferay/liferay-screens/tree/master/ios/Samples/WesterosBank) デモアプリの動作と外観をカスタマイズします。

これで完了です。 すばらしいです\！ これで、テーマを使用してiOSアプリでスクリーンレットをドレスアップする方法がわかりました。これは、可能性の世界を開きます-のように [独自のテーマ書い](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)。

**関連トピック**

[Liferayスクリーン用のiOSプロジェクトの準備](/docs/7-1/tutorials/-/knowledge_base/t/preparing-ios-projects-for-liferay-screens)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[iOS用Liferayスクリーンのアーキテクチャ](/docs/7-1/tutorials/-/knowledge_base/t/architecture-of-liferay-screens-for-ios)

[Androidスクリーンレットでビューを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-views-in-android-screenlets)
