---
header-id: updating-css-code
---

# CSSコードの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product-ver@のUIの改善には、これらのCSS関連の変更が必要でした。

  - CSSファイルの名前変更
  - クラス変数の変更
  - コアインポートの更新

テーマのアップグレードプロセスには、これらの変更への準拠が含まれます。 これらの変更を反映するために、テーマのCSSファイルを更新します。 CSSファイル名の更新から始めます。

## 粘土のCSSファイル名の更新

CSSファイル名の一部は、Clay（以前のLexicon CSS）の導入を反映して変更されました。 スタイルが設定されていないテーマのファイル名の変更を以下に示します。 予想されるテーマCSSファイルの完全なリストについては、 [テーマリファレンスガイド](/docs/7-1/reference/-/knowledge_base/r/theme-reference-guide) を参照してください。

古い7.0 auiファイル名を以下に示します。

  - `css/`
      - `_aui_custom.scss`
      - `_aui_variables.scss`
      - `aui.scss`

auiファイルの名前を、以下に示す更新された7.1クレイファイル名に一致するように変更します。

  - `css/`
      - `_clay_custom.scss`
      - `_clay_variables.scss`
      - `clay.scss`

次に、Bootstrap 4およびLexiconの削除および廃止された変数とミックスインを確認できます。

## クラス変数の変更

@product-ver@は、Bootstrap 4のCSSルール構文を使用します。 新しい構文により、開発者はBootstrap 4の機能と改善を活用できます。 テーマでLiferay Theme Generatorを使用していない場合、CSSルールをBootstrap 4に更新する方法については、 [v4への移行ガイド](https://getbootstrap.com/docs/4.0/migration/) を参照してください。

テーマでLiferay Theme Generatorを使用している場合、 `gulp upgrade` タスクは手動更新を提案します。 たとえば、7.0 Westeros Bankテーマのタスクログの一部は次のとおりです。

``` 
    ----------------------------------------------------------------
     Lexicon Upgrade (1.0 to 2.0)
    ----------------------------------------------------------------

    File: _variables_custom.scss
        $brand-default was deprecated in Lexicon CSS 1.x.x and has been removed 
            in the new Clay 2.x.x version
```

ログには、削除された変数や廃止された変数がリストされ、可能な変更が提案されます。 実行または推奨される更新ごとに、タスクはファイル名を報告します。 参考のために、変数の変更の完全なリストを以下に示します。

次の変数は、ブートストラップ4で削除されました。

``` 
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
```

変更の完全なリストについては、 [ブートストラップ移行ガイド](https://getbootstrap.com/docs/4.0/migration/) を参照してください。

次のレキシコン変数は、レキシコンCSS 1.xxで廃止され、新しいClay 2.xxバージョンで削除されました。

``` 
    $atlas-theme
    $box-shadow-default
    $box-shadow-default-bg
    $box-shadow-default-blur
    $box-shadow-default-spread
    $box-shadow-default-x
    $box-shadow-default-y
    $brand-danger-active
    $brand-danger-hover
    $brand-default
    $brand-default-active
    $brand-default-hover
    $brand-info-active
    $brand-info-hover
    $brand-primary-active
    $brand-primary-hover
    $brand-success-active
    $brand-success-hover
    $brand-warning-active
    $brand-warning-hover
    $inverse-active-bg
    $inverse-active-border
    $inverse-active-color
    $inverse-bg
    $inverse-border
    $inverse-color
    $inverse-disabled-color
    $inverse-header-bg
    $inverse-header-border
    $inverse-header-color
    $inverse-hover-bg
    $inverse-hover-border
    $inverse-hover-color
    $inverse-link-color
    $inverse-link-hover-color
    $state-danger-bg
    $state-danger-border
    $state-danger-text
    $state-default-bg
    $state-default-border
    $state-default-text
    $state-info-bg
    $state-info-border
    $state-info-text
    $state-primary-bg
    $state-primary-border
    $state-primary-text
    $state-success-bg
    $state-success-border
    $state-success-text
    $state-warning-bg
    $state-warning-border
    $state-warning-text
```

Clay 2.xxでは、次のレキシコンミックスインが削除されました。

`@mixin color-placeholder`：

レキシコンの `色のプレースホルダ（$element、 $color： $input-カラー-プレースホルダ）` ミックスインは、v1.0.9のように推奨されておらず、V2.0.0で除去しました。代わりに `placeholder（$color： $input-color-placeholder）` 使用してください。

`@mixin select-box-icon`：

Lexiconの `select-box-icon（$color）` mixinはv1.0.10で廃止され、v2.0.0で削除されました。使用してください `背景画像を：LX-アイコン（$name、 $color）、` 代わり。

場合ことを注意 `一気アップグレード` タスクは、以前LexiconCSSバージョンから粘土で削除されたあなたのテーマのいずれかの変数を検出し、それが追加されます `_variables_deprecated.scss` 必ずテーマコンパイルしてに作るために、変数を使用して、テーマにファイルを将来のアップグレードから切り離してください。 このファイルを手動で含める場合、参照用のコンテンツは次のとおりです。

    @warn "You're using deprecated variables. Please refer to the update guides 
    to remove its usage";
    
    // Deprecated `$brand-*-*` as of v1.0.24, removed in v2.0.0
    $brand-default: #869CAD !default; // Atlas
    $brand-default-active: darken($brand-default, 12%) !default; // Atlas
    $brand-default-hover: darken($brand-default, 8%) !default; // Atlas
    
    $brand-primary: #65B6F0 !default;
    $brand-primary-active: darken($brand-primary, 12%) !default; // Atlas
    $brand-primary-hover: darken($brand-primary, 8%) !default; // Atlas
    
    $brand-success: #76BD4A !default;
    $brand-success-active: darken($brand-success, 12%) !default; // Atlas
    $brand-success-hover: darken($brand-success, 8%) !default; // Atlas
    
    $brand-info: #1E5EC8 !default;
    $brand-info-active: darken($brand-info, 12%) !default; // Atlas
    $brand-info-hover: darken($brand-info, 8%) !default; // Atlas
    
    $brand-warning: #F5984C !default;
    $brand-warning-active: darken($brand-warning, 12%) !default; // Atlas
    $brand-warning-hover: darken($brand-warning, 8%) !default; // Atlas
    
    $brand-danger: #C67 !default;
    $brand-danger-active: darken($brand-danger, 12%) !default; // Atlas
    $brand-danger-hover: darken($brand-danger, 8%) !default; // Atlas
    
    // Box Shadow
    // Defines global box-shadows
    
    // Deprecated `$box-shadow-default-*` as of v1.0.24, removed in v2.0.0
    $box-shadow-default-x: 0 !default; // Atlas
    $box-shadow-default-y: 0 !default; // Atlas
    $box-shadow-default-blur: 3px !default; // Atlas
    $box-shadow-default-spread: 1px !default; // Atlas
    $box-shadow-default-bg: rgba(0, 0, 0, 0.2) !default; // Atlas
    $box-shadow-default: $box-shadow-default-x $box-shadow-default-y $box-shadow-default-blur $box-shadow-default-spread $box-shadow-default-bg !default; // Atlas
    
    // Inverse Colors
    
    // Deprecated `$inverse-*` as of v1.0.24, removed in v2.0.0
    $inverse-bg: #1F282E !default; // Atlas
    $inverse-border: darken($inverse-bg, 10%) !default; // Atlas
    $inverse-color: #869CAD !default; // Atlas
    
    $inverse-active-bg: #3A4D5A !default; // Atlas
    $inverse-active-border: darken($inverse-active-bg, 1%) !default; // Atlas
    $inverse-active-color: #FFF !default; // Atlas
    
    $inverse-disabled-color: #394956 !default; // Atlas
    
    $inverse-hover-bg: #1C252C !default; // Atlas
    $inverse-hover-border: darken($inverse-hover-bg, 1%) !default; // Atlas
    $inverse-hover-color: $inverse-color !default; // Atlas
    
    $inverse-header-bg: #1B2228 !default; // Atlas
    $inverse-header-border: darken($inverse-header-bg, 1%) !default; // Atlas
    $inverse-header-color: $inverse-color !default; // Atlas
    
    $inverse-link-color: #FFF !default; // Atlas
    $inverse-link-hover-color: $inverse-link-color !default; // Atlas
    
    // States for Form and alert
    
    // Deprecated `$state-*-*` as of v1.0.24, removed in v2.0.0
    $state-default-text: $brand-default !default; // Atlas
    $state-default-bg: lighten($state-default-text, 34%) !default; // Atlas
    $state-default-border: $state-default-text !default; // Atlas
    
    $state-primary-text: $brand-primary !default; // Atlas
    $state-primary-bg: lighten($state-primary-text, 12%) !default; // Atlas
    $state-primary-border: $state-primary-text !default; // Atlas
    
    $state-success-text: $brand-success !default;
    
    $state-success-bg: lighten($state-success-text, 34%) !default;
    $state-success-border: $state-success-text !default;
    
    $state-info-text: $brand-info !default;
    
    $state-info-bg: lighten($state-info-text, 34%) !default;
    $state-info-border: $state-info-text !default;
    
    $state-warning-text: $brand-warning !default;
    
    $state-warning-bg: #F8F4D5 !default;
    $state-warning-border: $state-warning-text !default;
    
    $state-danger-text: $brand-danger !default;
    
    $state-danger-bg: lighten($state-danger-text, 34%) !default;
    $state-danger-border: $state-danger-text !default;

テーマのCSS変数とミックスインを更新した後、Font Awesome Iconのインポートを更新する必要があります。

## Font Awesome Icon Importsの更新

Liferay Portal CE 7.0およびLiferay DXPでは、Font Awesomeアイコンが `_aui_variables.scss` インポートされました（現在は `_clay_variables.scss`名前が変更されてい` ）。 Font Awesomeアイコンは、後に <a href="/docs/7-1/tutorials/-/knowledge_base/t/using-the-bootstrap-3-lexicon-css-compatibility-layer">互換性レイヤー</a>に移動しました。 この移動の前に7.0テーマが作成され、 <code>_aui_variables.scss`を変更した場合、以下に示すFont Awesomeインポートを削除する必要があります。

``` 
    // Icon paths
    $FontAwesomePath: "aui/lexicon/fonts/alloy-font-awesome/font";
    $font-awesome-path: "aui/lexicon/fonts/alloy-font-awesome/font";
    $icon-font-path: "aui/lexicon/fonts/";
```

次に、コアのインポートを更新できます。

## コアインポートの更新

下の表に示すように、代わりにクレイパスを使用するように、古いauiレキシコンパスを更新します。

| パターン                                           | 置換                                |
| ---------------------------------------------- | --------------------------------- |
| `@import "/aui/lexicon/bootstrap/mixins/";`    | 削除された                             |
| `@import "/aui/lexicon/lexicon-base/mixins/";` | 削除された                             |
| `@import "/aui/lexicon/atlas-theme/mixins/";`  | 削除された                             |
| `@import "aui/lexicon/atlas-variables";`       | `@import "clay/atlas-variables";` |
| `@import "aui/lexicon/atlas";`                 | `@import "clay/atlas";`           |

## 関連トピック

[テーマテンプレートの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-theme-templates)

[既存のテーマのファイルをコピーする](/docs/7-1/tutorials/-/knowledge_base/t/copying-an-existing-themes-files)
