---
header-id: updating-6-2-css-rules-and-imports
---

# 6.2 CSSルールとインポートの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product-ver@は、Bootstrap 4のCSSルール構文を使用します。 Font Awesomeアイコンも移動されており、インポートの変更が必要です。 テーマでLiferay JS Theme Toolkitを使用している場合、Gulp `upgrade` タスクは自動CSS更新を報告し、手動更新を提案します。 たとえば、次はLunar Resortテーマの6.2から7.0へのアップグレードのタスクログの一部です。実行および推奨される更新ごとに、タスクはファイル名と行番号の範囲を報告します。

    ----------------------------------------------------------------
     Bootstrap Upgrade (2 to 3)
    ----------------------------------------------------------------
    
    File: src/css/_aui_variables.scss
        Line 5: "$white" has been removed
        Line 11: "$baseBorderRadius" has changed to "$border-radius-base"
        Line 15: "$btnBackground" has changed to "$btn-default-bg"
        Line 16: "$btnBackgroundHighlight" has been removed
        Line 17: "$btnBorder" has changed to "$btn-default-border"
        ...
    File: src/css/custom.css
        Line 201: Padding no longer affects width or height, you may need to
        change your rule (lines 201-227)
        Line 207: Padding no longer affects width or height, you may need to
        change your rule (lines 207-226)
        Line 212: You would change height from "62px" to "82px"
        ...

## CSSルールの手動更新

テーマのCSSルールを手動で更新するには、次の手順に従います。

1.  Bootstrap 3はすべての要素と疑似要素（例： `：before` および `：after`）に `box-sizing：border-box` プロパティを採用したため、パディングは寸法に影響しなくなりました。 [ブートストラップのドキュメント](https://getbootstrap.com/docs/3.3/css/#less-mixins-box-sizing) は、ボックスのサイズ変更について説明しています。 パディングを使用するすべてのCSSルールで、幅と高さを必ず更新してください。 たとえば、Lunar Resortテーマの `_custom.scss` ファイルのこのCSSルールで、高さ `値` 変化を調べます。

    元の：
   
        #reserveBtn {
            background-color: #00C4FB;
            border-radius: 10px;
            color: #FFF;
            font-size: 1.5em;
            height: 62px;
            margin: 30px;
            padding: 10px 0;
            ...
        }

    更新しました：
   
        #reserveBtn {
            background-color: #00C4FB;
            border-radius: 10px;
            color: #FFF;
            font-size: 1.5em;
            height: 82px;
            margin: 30px;
            padding: 10px 0;
            ...
        }

    | **注：** 個々の要素について、|を上書きできます。 `box-sizing：border-box` ルールと `box-sizing：content-box`

2.  次の変数は、ブートストラップ4で削除されます。 テーマで使用されている場合は、これらのいずれかを削除します。
   
        $line-height-computed
        $padding-base-horizontal
        $padding-base-vertical
        $padding-large-horizontal
        $padding-large-vertical
        $padding-small-horizontal
        $padding-small-vertical
        $padding-xs-horizontal
        $padding-xs-vertical
        $gray-base
        $gray-darker
        $gray-dark
        $gray
        $gray-light
        $gray-lighter
        $brand-primary
        $brand-success
        $brand-info
        $brand-warning
        $brand-danger
        $state-success-text
        $state-success-bg
        $state-success-border
        $state-info-text
        $state-info-bg
        $state-info-border
        $state-warning-text
        $state-warning-bg
        $state-warning-border
        $state-danger-text
        $state-danger-bg
        $state-danger-border

Bootstrap 3で変更されたCSSルールについては、「 [から2.xへの移行3.0ガイド](http://getbootstrap.com/migration/#migrating-from-2x-to-30) 」を参照してください。 同様に、CSSルールをブートストラップ4に更新する方法については、 [v1への移行ガイド](https://getbootstrap.com/docs/4.0/migration/) を参照してください。

## Font Awesome Importsの更新

IE9 CSSセレクターの制限を回避するため、Font Awesomeアイコンは独自のファイル（`font-awesome.scss`）に移動されました。 これらのインポートを `_custom.scss` ファイルに含める場合、それらを削除する必要があります。

    @import "aui/alloy-font-awesome/scss/mixins-alloy";
    @import "aui/alloy-font-awesome/scss/variables";

## 関連トピック

[6.2 CSS応答性の更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-the-6-2-responsiveness)

[既存のテーマのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)
