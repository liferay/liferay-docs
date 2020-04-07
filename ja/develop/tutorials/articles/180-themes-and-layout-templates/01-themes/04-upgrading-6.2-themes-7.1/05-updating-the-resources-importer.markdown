---
header-id: updating-the-resources-importer
---

# リソースインポーターの更新

[TOC levels=1-4]

Resources Importerは、LiferayのWeb ExperienceアプリケーションスイートのOSGiモジュールになりました。 スイートは@product@にバンドルされているため、Resources Importerを個別にダウンロードする必要はありません。 以下のコンポーネントが更新されており、このチュートリアルの焦点となっています。

  - プラグインのプロパティ
  - Webコンテンツの記事ファイルとフォルダー構造
  - サイトマップ

| **注：** ページおよび記事のインポート順序により、ページにリンクする記事|サイトのレイアウトで、nullポインタ例外が発生します| [号](https://issues.liferay.com/browse/LPS-64859) |これらのリンクは、例のLunar ResortテーマのWebコンテンツから削除されました|この問題を回避するための記事。

Resources Importerのプラグインプロパティの更新を開始します。

## liferay-plugin-package.propertiesの更新

このセクションの手順に従って、プラグインSDKテーマをアップグレードします。 他のすべてのテーマについては、次のセクションにスキップしてください。

1.  `src \ WEB-INF \ liferay-plugin-package.properties` ファイルを開き、 `required-deployment-contexts` プロパティを削除します。 Resources Importerは@product@にバンドルされているため、これは必要ありません。

2.  グループモデルクラスの完全修飾クラス名が変更されました。 `resources-importer-target-class-name` プロパティの値を以下の更新された値に置き換えます。
   
        com.liferay.portal.kernel.model.Group

`liferay-plugin-package.properties` が更新されたので、テーマのWebコンテンツを更新できます。

## Webコンテンツの更新

すべてのWebコンテンツ記事はXMLで記述され、記事作成用の構造とレンダリング用のテンプレートを持っている必要があります。

| **注：** Lunar Resortテーマの更新されたXML記事の例は、ZIP |ファイルの `/ resources-importer / journal / articles / Basic Web Content /` フォルダー|参照。

Webコンテンツを更新するには、次の手順に従います。

1.  `/ resources-importer / journal / articles /` フォルダーにサブフォルダー、たとえば `BASIC_WEB_CONTENT`を作成し、すべての基本的なHTML記事（以前に構造やテンプレートを必要としなかった記事）をそこに移動します。

2.  `/ resources-importer / journal / templates /` フォルダーに、手順1で作成したフォルダーと同じ名前のサブフォルダーを作成します。 Webコンテンツを適切にインポートするには、記事とテンプレートのフォルダー名が一致する必要があります。

3.  XML記事の構造はJSONで記述されるようになりました。 `/ resources-importer / journal / structures /` フォルダーにファイル（例： `BASIC_WEB_CONTENT.json`）を作成します。 構造名 **は、前の手順で作成したフォルダー名と** に一致する必要があります。 以前に構造とテンプレートを使用したWebコンテンツ記事の構文が正しいことを確認するには、@product@で構造とテンプレートを再作成することをお勧めします。

4.  作成したJSONファイルに、以下のパターンに従う基本的なWebコンテンツのJSON構造を追加します。
   
        {
            "availableLanguageIds": [
                "en_US"
            ],
            "defaultLanguageId": "en_US",
            "fields": [
                {
                    "label": {
                        "en_US": "Content"
                    },
                    "predefinedValue": {
                        "en_US": ""
                    },
                    "style": {
                        "en_US": ""
                    },
                    "tip": {
                        "en_US": ""
                    },
                    "dataType": "html",
                    "fieldNamespace": "ddm",
                    "indexType": "keyword",
                    "localizable": true,
                    "name": "content",
                    "readOnly": false,
                    "repeatable": false,
                    "required": false,
                    "showLabel": true,
                    "type": "ddm-text-html"
                }
            ]
        }

5.  基本的なWebコンテンツの場合、手順2で作成したテンプレートサブフォルダーにFreeMarkerテンプレートファイル（ `[template-folder-name].ftl`）を作成し、以下のメソッドを追加して記事のデータを取得します。
   
        ${content.getData()}

6.  この構文を使用して、基本的なWebコンテンツ記事をHTMLからXMLに移行します。 ** `.html` ファイル拡張子を `.xml`に変更するには、** を覚えておいてください。
   
        <?xml version="1.0"?>
       <root available-locales="en_US" default-locale="en_US"> <dynamic-element name="content" type="text_area" index-type="keyword" index="0"> <dynamic-content language-id="en_US">  ORIGINAL HTML CONTENT GOES HERE </dynamic-content> </dynamic-element> </root>

7.  @product-ver@の更新されたBootstrapでは、すべての `span[number]` クラスを以下の更新された構文に置き換える必要があります。
   
        col-[device-size]-[number]

    `[device-size]` とすることができる `XS`、 `SM`、 `MD`、又は `LG`。 `md` はほとんどの場合に機能します。 Lunar Resortの `2列のdescription.xml` 記事の元のクラスと更新されたクラスは、参照用に以下に示されています。

    元の：
   
        <?xml version="1.0"?>
       
        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
                            <div class="span4" id="columnLeft">
                                Out of This World
                            </div>
                            <div class="span8" id="columnRight">
                                Come to the Lunar Resort...
                            </div>
                        </div>
                    ]]>
                </dynamic-content>
            </dynamic-element>
        </root>

    更新しました：
   
        <?xml version="1.0"?>
       
        <root available-locales="en_US" default-locale="en_US">
            <dynamic-element name="content" type="text_area"
            index-type="keyword" index="0">
                <dynamic-content language-id="en_US">
                    <![CDATA[
                        <div class="container-fluid">
                            <div class="col-md-4" id="columnLeft">
                                Out of This World
                            </div>
                            <div class="col-md-8" id="columnRight">
                                Come to the Lunar Resort...
                            </div>
                        </div>
                    ]]>
                </dynamic-content>
            </dynamic-element>
        </root>

    Bootstrapの [ドキュメント](https://getbootstrap.com/docs/4.0/layout/grid/) は、更新されたグリッドシステムについて説明しています。

次に、テーマのサイトマップファイルを更新する必要があります。

## サイトマップの更新

Liferay Portal 6.2では、ポートレットIDは増分番号でした。 @product-ver@では、明示的なクラス名です。 `sitemap.json` ファイルを新しいポートレットIDで更新します。

いくつかの一般的なポートレットIDは、 [Resources Importer](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-sitemap-for-the-resources-importer) サイトマップの作成チュートリアルの `sitemap.json` 例で指定されてい` 。</p>

<p spaces-before="0">UIからポートレットのIDを取得することもできます。</p>

<ol start="1">
<li><p spaces-before="0"> ポートレットの <em x-id="3">オプション</em> メニューで、 <em x-id="3">ルックアンドフィール設定</em>選択します。</p>

<p spaces-before="4"><img src="../../../../images/upgrading-themes-look-and-feel-menu.png" alt="図1：[ルックアンドフィール設定]メニューでポートレットIDを見つけることができます。" /></p></li>
<li><p spaces-before="0"> <em x-id="3">Advanced Styling</em> タブを選択します。</p>

<p spaces-before="4"><code>ポートレットID` 値が青いボックスに表示されます。</li> </ol>

![図2：ポートレットIDは、* Advanced Styling *タブの青いボックス内に表示されます。](../../../../images/upgrading-themes-portlet-id.png)

[ポートレットIDクイックリファレンスガイド](/docs/7-1/reference/-/knowledge_base/r/fully-qualified-portlet-ids) は、すべてのデフォルトポートレットIDを示しています。 6.2のポートレットID番号について `liferay-portlet.xml` を確認し、クイックリファレンスガイドの更新されたIDに置き換えます。

| **Webコンテンツの記事に更新された `.xml` 拡張子を使用するには** を覚えておいてください|サイトマップで。

## 関連トピック

[6.2 CSS コードのアップデート手順](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-css-code)

[6.2テーマへの粘土デザインパターンの適用](/docs/7-1/tutorials/-/knowledge_base/t/applying-clay-design-patterns-to-6-2-themes)
