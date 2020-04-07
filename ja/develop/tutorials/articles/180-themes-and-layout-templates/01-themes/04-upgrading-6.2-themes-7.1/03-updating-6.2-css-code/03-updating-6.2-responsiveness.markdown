---
header-id: updating-the-6-2-responsiveness
---

# 応答性の更新

[TOC levels=1-4]

Bootstrap 4の明示的なメディアクエリは、CSSの応答性のためにBootstrap 2 `応答` ミックスインを置き換えました。 次の手順に従って、テーマの応答性を更新します。

1.  `_custom.scss` ファイルを開きます。

2.  すべての `応答` ミックスインを、以下に示す対応するメディアクエリに置き換えます。 一部の寸法はわずかに異なることに注意してください。

    **メディアクエリの置換**

    | Liferay Portal 6.2 Mixin                            | @product-ver@メディアクエリ                                       |
    | --------------------------------------------------- |:---------------------------------------------------------- |
    | `@include respond-to（phone）` （最大幅：767px）            | `@include media-breakpoint-down（sm）` （最大幅：767px）           |
    | `@include respond-to（tablet）` （最小幅：768px、最大幅：979px） | `@include media-breakpoint-only（md）` （最小幅：768px、最大幅：991px） |
    | `@include respond-to（phone、tablet）` （最大幅：979px）     | `@include media-breakpoint-down（md）` （最大幅：991px）           |
    | `@include respond-to（デスクトップ、タブレット）` （最小幅：768px）     | `@include media-breakpoint-up（md）` （最小幅：768px）             |
    | `@include respond-to（desktop）` （最小幅：980px）          | `@include media-breakpoint-up（lg）` （最小幅：992px）             |


たとえば、Lunar Resortテーマの元の構文と更新された構文の比較は次のとおりです。

元の：

    @include respond-to(phone, tablet) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

更新しました：

    @include media-breakpoint-down(md) {
        html #wrapper #banner #navigation {
        ...
        }
        ...
    }

## 関連トピック

[6.2テーマテンプレートの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-theme-templates)

[ClayとSassのCSSファイル名の更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-css-file-names-for-clay-and-sass)
