---
header-id: updating-css-file-names-for-clay-and-sass
---

# ClayとSassのCSSファイル名の更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

SassはLiferay Portal 6.2で使用できましたが、Sassの命名規則に従ったSass部分ファイルのみ（ファイルサフィックス `.scss`）。 @product-ver@テーマでは、すべてのSassファイルは `scss`で終わる必要があり、 `aui` ファイル名プレフィックスは `Clay` に置き換えられ、 [Clay](https://clayui.com/docs/getting-started/clay.html) （以前のLexicon CSS）の導入を反映しています。

| **注**：| [Gulp `アップグレード` タスク](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes) | CSSファイルの名前を自動的に変更します。

次の手順に従って、CSSファイル名を手動で更新します。

1.  テーマの各CSSファイル名のサフィックスを `.css` から `.scss`、 `main.scss` および `aui.scss`を除くすべてのSass部分ファイル名にアンダースコア（`_`）を追加します。 Lunar Resortの更新されたファイルを以下に示します。
   
        - `css/`
          - `_aui_variables.scss`
          - `_custom.scss`

2.  テーマで名前 `aui` を使用するCSSファイルの名前を、代わりに名前 `clay` を使用するように変更します。 以下は、Lunar Resortの更新されたCSSファイル名です。

    ``` 
    - `css/`
      - `_clay_variables.scss`
      - `_custom.scss` 
    ```

参照用に、名前を変更するコアファイルの完全なリストを以下に示します。 あなたは下の表に示されているCSSファイル、変更された場合は **、あなたはしなければならない** 、その名前を更新します。 ファイルを変更しなかった場合、アクションは不要です。

**CSSファイル名の更新**

| 元のCSSファイル名        | 更新されたCSSファイル名       |
| ----------------- |:------------------- |
| `application.css` | `_application.scss` |
| `aui.css`         | `clay.scss`         |
| `base.css`        | `_base.scss`        |
| `custom.css`      | `_custom.scss`      |
| `dockbar.css`     | 削除された               |
| `extras.css`      | `_extras.scss`      |
| `layout.css`      | `_layout.scss`      |
| `main.css`        | `main.scss`         |
| `navigation.css`  | `_navigation.scss`  |
| `portal.css`      | `_portal.scss`      |
| `portlet.css`     | `_portlet.scss`     |
| `taglib.css`      | `_taglib.scss`      |

## 関連トピック

[6.2テーマのGulpアップグレードタスクの実行](/docs/7-1/tutorials/-/knowledge_base/t/running-the-upgrade-task-for-6-2-themes)

[6.2 CSSルールの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-css-rules-and-imports)
