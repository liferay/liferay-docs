---
header-id: rendering-web-content-in-your-ios-app
---

# iOSアプリでのWebコンテンツのレンダリング

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Screensは、アプリで [Webコンテンツ](/docs/7-1/user/-/knowledge_base/u/creating-web-content) をレンダリングするいくつかの方法を提供します。歴史的な理由から、Webコンテンツの記事はLiferayの `JournalArticle` エンティティです。 Webコンテンツ表示スクリーンレットの使用は、アプリの `JournalArticle` からHTMLを表示するためのシンプルで強力な方法です。ニーズに合わせて、このスクリーンレットはいくつかのユースケースをサポートしています。 このチュートリアルではそれらについて説明します。

## 基本的なWebコンテンツの取得

Webコンテンツ表示スクリーンレットの最も簡単な使用例は、Webコンテンツ記事のHTMLを取得し、それを [`UIWebView`](https://developer.apple.com/library/ios/documentation/UIKit/Reference/UIWebView_Class/)レンダリングすることです。 これを行うには、Interface Builderの *Article Id* 属性を介してWebコンテンツ記事のIDを提供します。 スクリーンレットが残りを処理します。 これには、モバイルデバイスに合わせたコンテンツのレンダリング、必要なキャッシュの実行などが含まれます。

コンテンツレンダリングするには *正確に* 、それはあなたのモバイルサイトに表示されるように、しかし、あなたはCSSをインラインで提供したり、テンプレートを使用する必要があります。 返されるHTMLは、LiferayインスタンスのグローバルCSSを認識しません。

[Webコンテンツ表示スクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/webcontentdisplayscreenlet-for-ios)説明されているように、デリゲートを使用してレンダリングされたHTMLを変更することもできます。

ご覧のとおり、これは非常に簡単です。 何がおかしいのでしょうか？ 有名な最後の言葉。 よくある間違いは、Webコンテンツの記事を含むサイトのIDではなく、デフォルトのサイトID（`groupId`）を使用することです。 アプリでデフォルトの `groupId` を引き続き使用し、Webコンテンツ表示スクリーンレットに別のグループIDを使用するには、Interface Builderでスクリーンレットの *グループID* プロパティを割り当てます。

## テンプレートを使用する

Webコンテンツ表示スクリーンレットは、 [テンプレート](/docs/7-1/user/-/knowledge_base/u/designing-web-content-with-templates) を使用してWebコンテンツ記事をレンダリングすることもできます。 たとえば、Liferayインスタンスには、モバイルデバイスでコンテンツを表示するために特別に設計されたカスタムテンプレートがある場合があります。 テンプレートを使用するには、テンプレートのIDをスクリーンレットの `templateId` プロパティ（Interface Builderの*Template Id* ）として設定します。

Liferayの構造化されたWebコンテンツには、多くのテンプレートを含めることができます。 アプリにWebコンテンツを表示するのに適したテンプレートがない場合は、独自のテンプレートを作成できます。

## 構造化Webコンテンツのレンダリング

Webコンテンツ表示スクリーンレットで [構造化されたWebコンテンツ](/docs/7-1/user/-/knowledge_base/u/creating-structured-web-content) をレンダリングするには、それが可能なカスタムテーマを作成する必要があります。 また、アプリに表示する構造ごとにカスタムテーマを作成する必要があります。この場合、単一の親テーマ内に各テーマを作成し、複合命名を使用してこの関係を示すと便利です。 あなたが呼ばれるあなたのLiferayインスタンス内の構造を持っている場合たとえば、 *ブック*、 *、従業員*、および *会議*、あなたはそれぞれのカスタムテーマを作成する必要があります。 あなたはこれらのテーマを作成する場合は、別のカスタムテーマの子どもたちが呼ばれると *mytheme*、あなたは彼らに名前を付けることができ *mytheme.book*、 *mytheme.employee*、及び *mytheme.meeting*。

テーマを作成する場所や名前に関係なく、次の手順を使用してテーマを作成します。

1.  [Webコンテンツをレンダリングするテーマ](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes) を作成します。 すでに独自のテーマを作成している場合は、この手順をスキップできます。

2.  あなたのテーマでは、新しいクラスを作成すると呼ばれる `WebContentDisplayView_themeName`から延びる、 `BaseScreenletView`。 このクラスは、WebコンテンツのUIに関連付けられたアウトレットとアクションを保持します。

3.  `WebContentDisplayView_themeName.xib` ファイルにUIを作成します。 このファイルには、Webコンテンツの構造フィールドをレンダリングするために必要なコンポーネントを含む `UIView` が必要です。 たとえば、構造化されたWebコンテンツに `緯度` および `経度` フィールドが含まれる場合、 `MKMapView` コンポーネントを使用してマップポイントをレンダリングできます。

4.  コンポーネントの準備ができたら、ルートビューのクラスを `WebContentDisplayView_themeName` （最初のステップで作成したクラス）に変更し、UIコンポーネントの管理に必要なアウトレットとアクションを作成します。

5.  `WebContentDisplayView_themeName` クラスの `WebContentDisplayViewModel` プロトコルを適合させ` 。 このプロトコルでは、 <code>htmlContent` および `recordContent` プロパティを追加する必要があります。 `htmlContent` プロパティは、HTML Webコンテンツを対象としています。これはテーマの使用例ではありません。 テーマには、構造化されたWebコンテンツを表示する必要があります。このコンテンツには `recordContent` プロパティを使用します。 このプロパティでは、構造フィールドの値を対応するアウトレットの値として設定します。 たとえば、

    ``` 
     public var htmlContent: String? {
         get {
             return nil
         }
         set {
             // not used for structured Web Contents
         }
     }

     public var recordContent: DDLRecord? {
         didSet {
             // set the outlets with record's values
             set.myOutlet.myProperty = recordContent?["my_field_name"]?.currentValueAsLabel
         }
     }
    ```

次に、アプリのWebコンテンツ記事のリストを表示する方法を学びます。

## Webコンテンツ記事のリストを表示する

上記の例は、Webコンテンツ表示スクリーンレットを使用して、アプリで単一のWebコンテンツ記事のコンテンツを表示する方法を示しています。しかし、代わりに記事のリストを表示したい場合はどうでしょうか？ 問題ない\！ これを行うには、 [Webコンテンツリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-content-list-screenlet-for-ios)または [アセットリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios)ます。

最初に、Webコンテンツリストスクリーンレットの使用方法を学習します。

### Webコンテンツリストスクリーンレットの使用

Webコンテンツリストスクリーンレットを使用すると、WebコンテンツフォルダーからWebコンテンツ記事のリストを取得して表示できます。 スクリーンレットを使用するには、次の手順に従います。

  - View ControllerにWebコンテンツリストスクリーンレットを挿入します。

  - Interface Builderで *グループID* および *フォルダーID* プロパティを設定します。 フォルダーIDは、記事を表示するWebコンテンツフォルダーのIDです。 ルートフォルダーを使用するには、フォルダーIDに `0` を使用します。

  - リストに関連するイベントを受信するには、 `WebContentListScreenletDelegate`適合させ` 。 イベントには、 <code>WebContent` オブジェクトが含まれます。

スクリーンレットとそのサポートされている機能の詳細については、 [Webコンテンツリストスクリーンレットリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/web-content-list-screenlet-for-ios)参照してください。

### アセットリストスクリーンレットの使用

アセットリストスクリーンレットは、Liferayインスタンスのアイテムのリストを表示できるという点で、Webコンテンツ表示スクリーンレットに似ています。 ただし、アセットリストスクリーンレットには、アセットのリストが表示されます。 Webコンテンツはアセットであるため、Asset List Screenletを使用してWebコンテンツ記事のリストを表示できます。 これを行うときは、次のことを考慮してください。

  - デリゲートでは、 `screenlet：onAssetListResponse` は、 `WebContent` オブジェクトを表す `Asset` オブジェクトの配列を取得します。 `WebContent` は `Asset`子であるため、 `Asset` オブジェクトを `WebContent`キャストできます。 各 `のWebContent` オブジェクトが有する `HTML`、 `構造`、又は `structuredRecord` プロパティを。

  - `WebContent` オブジェクトでアセットリストスクリーンレットをレンダリングするには、独自のテーマを作成する必要があります。 `AssetListView_default`を拡張するクラスをテーマに作成し、 `doFillLoadedCell` メソッドをオーバーライドします。 この方法では、鋳造 `対象物` としてパラメータ `のWebContent` 、次いでウェブコンテンツのフィールドから値を取り出す `structuredRecord` プロパティ。 カスタムセルが必要な場合は、 `doRegisterCellNibs` および `doCreateCell` メソッドをオーバーライドすることもできます。 アセットリストのカスタマイズの詳細については、 [Asset List Screenletリファレンスドキュメント](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios) を参照してください。

## 関連トピック

[iOSアプリでのスクリーンレットの使用](/docs/7-1/tutorials/-/knowledge_base/t/using-screenlets-in-ios-apps)

[iOSスクリーンレットでテーマを使用する](/docs/7-1/tutorials/-/knowledge_base/t/using-themes-in-ios-screenlets)

[iOSテーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-ios-themes)

[iOS用のWebコンテンツ表示スクリーンレット](/docs/7-1/reference/-/knowledge_base/r/webcontentdisplayscreenlet-for-ios)

[iOS用のWebコンテンツリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/web-content-list-screenlet-for-ios)

[iOS用のアセットリストスクリーンレット](/docs/7-1/reference/-/knowledge_base/r/assetlistscreenlet-for-ios)
