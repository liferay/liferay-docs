---
header-id: defining-portlets-in-a-sitemap
---

# サイトマップでのポートレットの定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

定義したページのサイトマップにポートレットを埋め込むことができます。 これらをデフォルト設定で埋め込んだり、ポートレット設定を指定して、よりカスタムのルックアンドフィールを作成したりできます。 このチュートリアルでは、これらの両方のオプションについて説明します。

次の手順を実行します：

1.  ポートレットのIDに注意してください。 これは、ポートレット仕様の `javax.portlet.name` 属性です。 便宜上、すぐに使用可能なポートレットのIDは、 [完全修飾ポートレットID](/docs/7-1/reference/-/knowledge_base/r/fully-qualified-portlet-ids) リファレンスガイドにリストされています。 カスタムポートレットの場合、このプロパティは、 `javax.portlet.name =` サービスプロパティとしてポートレットクラスにリストされます。

2.  ポートレットを表示するレイアウトの列にポートレットIDをリストします。 構成例を以下に示します。
   
        {
            "layoutTemplateId": "2_columns_ii",
            "publicPages": [
                {
                    "columns": [
                        [
                            {
                                "portletId": "com_liferay_login_web_portlet_LoginPortlet"
                            },
                            {
                                "portletId": 
                                "com_liferay_site_navigation_menu_web_portlet_SiteNavigationMenuPortlet"
                            }
                        ],
                        [
                            {
                                "portletId": "com_liferay_hello_world_web_portlet_HelloWorldPortlet"
                            }
                        ]
                    ],
                    "friendlyURL": "/home",
                    "nameMap": {
                        "en_US": "Welcome",
                        "fr_FR": "Bienvenue"
                    },
                    "title": "Welcome"
                }
            ]
        }

    このアプローチは、デフォルト設定でポートレットを埋め込みます。 ポートレットをカスタマイズするには、次の手順で説明するように、ポートレットの設定を構成する必要があります。

3.  オプションで、 `portletPreferences` キーを使用して、ポートレットのポートレットプリファレンスを指定します。 以下は、Webコンテンツ表示ポートレットの例です。
   
        {
            "portletId": "com_liferay_journal_content_web_portlet_JournalContentPortlet",
            "portletPreferences": {
                "articleId": "Custom Title.xml",
                "groupId": "${groupId}",
                "portletSetupPortletDecoratorId": "decorate",
                "portletSetupTitle_en_US": "Web Content Display with Custom Title",
                "portletSetupUseCustomTitle": "true"
            }
        }

    **portletSetupPortletDecoratorId：** ポートレットに使用するポートレットデコレータを指定します（`ボーダーレス` || `ベアボーン` || `デコレート`）。 詳細については、「埋め込みポートレットへの [ポートレットデコレータの適用](/docs/7-1/tutorials/-/knowledge_base/t/applying-portlet-decorators-to-embedded-portlets) 」チュートリアルを参照してください。

| **ヒント：** |を指定できます。 [アプリケーション表示テンプレート](/docs/7-1/user/-/knowledge_base/u/styling-apps-and-assets) | （ADT） `sitemap.json` ファイル内のポートレットに対して、 `displayStyle` および| `displayStyleGroupId` ポートレットプリファレンス。以下の例に示すとおり。 | "portletId"： "com \ _liferay \ _asset \ _publisher \ _web \ _portlet \ _AssetPublisherPortlet"、| 「portletPreferences」：{| "displayStyleGroupId"： "10197"、| "displayStyle"： "ddmTemplate \ _6fe4851b-53bc-4ca7-868a-c836982836f4" | }

ポートレットプリファレンスは各ポートレットに固有であるため、最初に構成するプリファレンスを決定する必要があります。 ポートレットプリファレンスの適切なキー/値のペアを決定するには、2つの方法があります。 最初の方法は、ポートレットプリファレンスを手動で設定し、データベースの `portletPreferences.preferences` 列の値を確認して、 `sitemap.json`で何を構成するかのヒントにします。 たとえば、次の構成を使用して、指定した資産タグに一致する資産を表示するようにAsset Publisherを構成できます。

    "queryName0": "assetTags",
    "queryValues0": "MyAssetTagName"

別のアプローチは、バンドル内の各アプリでキーワード `プリファレンスを検索することです`。 これにより、ポートレット用に定義されたポートレットプリファレンスを持つアプリのJSPの一部が返されます。

| **注：** |などの既存の構成を必要とするポートレット設定。タグまたはカテゴリ、グローバルサイトで構成を作成する必要があります|最初に、Resources Importerが|と共にデプロイされたときに一致を見つけるようにします。テーマ。

## 関連トピック

[Resources Importer用のWebコンテンツの準備と整理](/docs/7-1/tutorials/-/knowledge_base/t/preparing-and-organizing-web-content-for-the-resources-importer)

[サイトマップ内のページの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-pages-in-a-sitemap)

[テーマのリソースをインポートする場所を指定する](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources)
