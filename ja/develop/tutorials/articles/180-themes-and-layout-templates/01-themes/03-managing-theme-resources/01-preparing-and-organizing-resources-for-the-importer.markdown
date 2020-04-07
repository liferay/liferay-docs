---
header-id: preparing-and-organizing-web-content-for-the-resources-importer
---

# Resources Importer用のWebコンテンツの準備と整理

[TOC levels=1-4]

テーマとともにインポートするリソースを作成する必要があります。 リソースを最初から作成したり、作成済みのリソースを取り込むことができます。 このチュートリアルでは、Resources Importerのリソースを準備および整理する方法について説明します。

最初に、Resources Importer用のWebコンテンツを準備する必要があります。

## Webコンテンツの準備

Resource Importerを使用して、HTML（基本的なWebコンテンツ）、JSON（構造）、またはVMまたはFTL（テンプレート）ファイルを活用できます。 すべてのWebコンテンツの記事には、構造とテンプレートが必要です。 一部の記事は同じ構造を共有し、おそらく同じテンプレートを共有する場合があることに注意してください---これはすべての基本的なWebコンテンツ記事の場合です。 以下の手順に従って、Webコンテンツの記事を準備します。

1.  記事のオプションメニューから[ *編集* ]を選択し、ページの右上にある[ *オプション* ]アイコンをクリックして、[ *ソースの表示*]を選択します。 記事の未加工のXMLをローカルでXMLファイルにコピーします。 `resources-importer / journal / articles /` 下に記事のフォルダーを作成し、必要に応じて名前を変更します。 Webコンテンツの記事のXMLは、構造に必要なデータを埋めます。 Webコンテンツ記事のXMLの例を以下に示します。
   
        <?xml version="1.0"?>
       <root available-locales="en_US" default-locale="en_US"> <dynamic-element name="content" type="text_area" index-type="keyword" index="0"> <dynamic-content language-id="en_US">  <center> <p><img alt="" src="[$FILE=space-program-history.jpg$]" /></p> </center> <p>20世紀半ば、歴史上最も激しい 戦争のうちの2つが起きた後、人類は星を見つめる ました。 人がお互いに挑戦し続ける代わりに、 人の男が私たちが縛っていた 限界に挑戦することを選びます。 なので、グレートスペースレースが始まりました。</p> <p>最初、レースは宇宙に到達することでした。地球の大気圏外に 取得し、 に到達したら、月に向けて撃ちました。 男性を月に、ロボットを 火星に、そして私たちの 太陽系の範囲を超えて探査した後、残り は残っていないようでした。</p> <p>宇宙計画はそれを変えることを目指しています。 国境を越えて、何を超えて 誰もが私たちにできることを想像することができます。 空 は制限ではありません。</p> </dynamic-content> </dynamic-element> </root>

2.  Webコンテンツの記事の構造をダウンロードします。 構造を開き、[ *ソース* ]タブをクリックして、構造のファイルを表示します。 その内容をコピーして、 `resources-importer / journal / structures /` フォルダー内の新しいJSONファイルに貼り付けます。 JSONの構造は、記事のデータのワイヤーフレームまたは設計図を設定します。 基本的なWebコンテンツの記事を保存している場合は、以下の構造をコピーできます（ `en_US` を言語に置き換えてください）：
   
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
                    "indexType": "text",
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

3.  構造の一致するテンプレートをダウンロードします。 構造の[アクション]メニューを開き、[ *テンプレートの管理* ]を選択して、それを使用するテンプレートを表示します。 `resources-importer / journal / templates /` 下にテンプレートのフォルダーを作成し、その内容をコピーして新しいFTLファイルに貼り付けます。 テンプレートは、データの表示方法を定義します。 基本的なWebコンテンツの記事を保存する場合、以下のFreeMarkerテンプレートをコピーできます。
   
        ${content.getData()}

所有している各Webコンテンツ記事について上記の手順を繰り返します。 一部のWebコンテンツ記事は同じ構造とテンプレートを共有する場合があることに注意してください。これらの場合、それらを使用するすべてのWebコンテンツ記事に必要な構造とテンプレートのコピーは1つだけです。 Webコンテンツの記事を保存したら、適切なフォルダー構造に配置できます。

## リソースを整理する

`[theme-name]/ src / WEB-INF / src / resources-importer` フォルダーとそのサブフォルダーの下にリソースを追加します。 以下に説明するフォルダーにリソースを配置します。

  - `[theme-name]/src/WEB-INF/src/resources-importer/`
      - `sitemap.json` ページ、レイアウトテンプレート、およびポートレットを定義します
      - `asset.json` （オプション）資産の詳細を指定します
      - `document_library/`
          - `ドキュメント/` ドキュメントとメディアファイル（アセット）を含む
      - `journal/`
          - `記事/` -Webコンテンツ（HTML）およびテンプレートによってWebコンテンツ記事（XML）をグループ化するフォルダーが含まれます。 各フォルダー名は、対応するテンプレートのファイル名と一致する必要があります。 フォルダたとえば、作成 `テンプレート1/` テンプレートファイルに基づいて、物品を保持するために `テンプレート1.ftl`。
          - `構造/` 構造（JSON）および子構造のフォルダーが含まれます。 各フォルダー名は、対応する親構造のファイル名と一致する必要があります。 例えば、フォルダ作成 `構造1/` 構造ファイルの子を保持するために `構造1.json`。
          - `テンプレート/` テンプレート（VMまたはFTL）を構造ごとにフォルダーにグループ化します。 各フォルダー名は、対応する構造のファイル名と一致する必要があります。 例えば、フォルダ作成 `構造1/` 構造ファイルのテンプレート保持する `構造1.json`。

## 関連トピック

[Resources Importerのサイトマップを作成する](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-sitemap-for-the-resources-importer)

[リソースインポーターのアセットの定義](/docs/7-1/tutorials/-/knowledge_base/t/defining-assets-for-the-resources-importer)

[テーマのリソースをインポートする場所を指定する](/docs/7-1/tutorials/-/knowledge_base/t/specifying-where-to-import-your-themes-resources)
