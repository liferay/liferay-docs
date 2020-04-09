---
header-id: creating-layout-templates-manually
---

# レイアウトテンプレートを手動で作成する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

あなたがすることができますが、 [Liferayのテーマジェネレータでレイアウトテンプレートを生成](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)、 作成または手動で独自のツールを使用してそれらを変更することもできます。 このチュートリアルでは、@product@のレイアウトテンプレートの構造を学習するので、レイアウトテンプレートを手動で作成したり、既存のテンプレートを変更したりできます。

## 解剖学を理解する

レイアウトテンプレートは、行と列で構成されています。 作成するデザインは、ユーザーがページ上のポートレットを配置できる場所を指定します。 例の行のHTMLマークアップを以下に示します。

    <div class="portlet-layout row">
            <div class="col-md-4 col-sm-6 portlet-column portlet-column-first" 
            id="column-1">
                    ${processor.processColumn("column-1", 
                    "portlet-column-content portlet-column-content-first")}
            </div>
            <div class="col-md-8 col-sm-6 portlet-column portlet-column-last" 
            id="column-2">
                    ${processor.processColumn("column-2", 
                    "portlet-column-content portlet-column-content-last")}
            </div>
    </div>

各行には `div`があり、クラス `ポートレットレイアウト` および `行`、各列に子 `div`が含まれています。 各列は、クラス `ポートレット列`で示され、最初の列（`ポートレット列の最初`）、最後（`ポートレット列の最後`）、または列のみ行（`ポートレット列のみ`）。

列は [ブートストラップグリッドシステム](https://getbootstrap.com/docs/4.0/layout/grid/) を使用するため、幅は1〜12の範囲で指定できます。 サイズは、 `col-[breakpoint]` クラスプレフィックスに続く番号で示されます（例： `col-md-6`）。 素子のパーセンテージベース幅とメディアクエリブレークポイント（これらは2つのことを指定`XS`、 `、SM`、 `MD`、又は `LG`）用の場合、100％の幅にこの要素が膨張します。 12は最大量であるため、 `col-md-6` は `6/12` 幅、つまり `50％`ます。 上記のように、これらのクラスを組み合わせて、より高度なレイアウトを実現することもできます。 この例では、中サイズのビューポートは幅33.33％で `列1` 、幅66.66％で `列2` を表示しますが、小さいサイズのビューポートでは `列1` と `列2` 両方が50％幅です。

プロセッサー（`$ {processor.processColumn（）}`）は、各列のコンテンツを処理し、2つの引数を取ります：列の `id`、およびクラス `portlet-column-content` および `portlet-column-content-[case]` （ ）該当する場合、ここで、 `[case]` を意味 `第`、 `最後の`、又は `のみ` 行の列。

| **Note:** Velocity layout templates are supported, but deprecated as of | @product-ver@. Velocityレイアウトテンプレートを|に変換することをお勧めします。できるだけ早くFreeMarker。

レイアウトテンプレートの構造を理解したので、独自の@product@レイアウトテンプレートを作成できます！

## 関連トピック

[Liferayテーマジェネレーターを使用したレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/creating-layout-templates-with-the-themes-generator)

[テーマの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-themes)

[カスタムレイアウトテンプレートのサムネイルプレビューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-layout-template-thumbnail-previews)
