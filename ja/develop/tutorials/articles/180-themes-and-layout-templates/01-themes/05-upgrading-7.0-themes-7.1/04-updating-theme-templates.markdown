---
header-id: updating-theme-templates
---

# テーマテンプレートの更新

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product-ver@テーマテンプレートは、Liferay Portal 7.0テーマテンプレートと本質的に同じです。 主な変更点は次のとおりです。

  - VelocityテンプレートはLiferay Portal CE 7.0で非推奨になりましたが、@product@のFreeMarkerテンプレートを優先して削除されました。

FreeMarkerテンプレートを使用し、Velocityテンプレートを削除する主な理由は次のとおりです。

  - FreeMarkerは定期的に開発および保守されていますが、Velocityはもはや積極的に開発されていません。

  - FreeMarkerはより高速で、より洗練されたマクロをサポートします。

  - FreeMarkerは、タグライブラリを表すメソッドを必要とせずに、タグライブラリを直接使用することをサポートしています。 あなたはそれらにボディコンテンツ、パラメータなどを渡すことができます。

Velocityテーマテンプレートに取り組むことから始める必要があります。 Velocityテーマテンプレートはサポートされなくなったため、 **VelocityテーマテンプレートをFreeMarker**変換する必要があります。

Liferay Theme Generatorを使用している場合、 `gulp upgrade` コマンドは、必要なテーマテンプレートの変更をログに報告します。

たとえば、Westeros Bankテーマの `gulpアップグレード` ログは次のとおりです。

    ----------------------------------------------------------------
     Liferay Upgrade (7.0 to 7.1)
    ----------------------------------------------------------------
    
    Renamed aui.scss to clay.scss
    File: footer.ftl
        Warning: .container-fluid-1280 has been deprecated. Please use 
        .container-fluid.container-fluid-max-xl instead.
    File: portal_normal.ftl
        Warning: .navbar-header has been removed. This container should be 
        removed in most cases. Please, use your own container if necessary.

ログは、削除され廃止されたコードについて警告し、該当する場合は置換を提案します。 参考のために、主な変更点を以下に示します。

  - `list-inline` class

コンテナ内のリストアイテムには、 `</a> list-inline-item` クラスが必要になりました。</p></li> 
    
      - `container-fluid-1280` クラスは廃止されました。 代わりに `container-fluid container-fluid-max-xl` 使用してください。

  - レスポンシブnavbar動作 [が、必要な `navbar-expand-{breakpoint}` クラスを介して `navbar` クラスに](https://getbootstrap.com/docs/4.0/migration/#navbar) に適用されるようになりました。

  - `navbar-toggle` クラスは `navbar-toggler` なり、 [は異なる内部マークアップ](https://getbootstrap.com/docs/4.0/migration/#navbar)持ちます。

  - `navbar-header` クラスは削除されました。 ほとんどの場合、このコンテナは削除する必要があります。 必要に応じて、独自のコンテナを使用してください。</ul> 

Next, you'll learn how to update various theme templates to @product-ver@. テーマテンプレートを変更しなかった場合は、これらのセクションをスキップできます。



## ポータル標準FTLの更新

最初に更新するのは、 `portal_normal.ftl` テーマテンプレートです。 `portal_normal.ftl`カスタマイズしなかった場合、このセクションはスキップできます。 以下の手順に従って、 `portal_normal.ftl`を更新します。

1.  変更した `portal_normal.ftl` ファイルを開き、パンくずリストを削除します。<nav id="breadcrumbs"> <@liferay.breadcrumbs /> </nav> 

2.  `body` タグから `id = "main-surface"` を削除し` 。 これは、SPAが正常に機能するために必要ではありません。</p>

<pre><code> <body class="${css_class}" id="main-surface">
`</pre></li> </ol> 
   
   テーマのポートレットテンプレートを変更した場合は、次のセクションの手順に従います。
   
   

## ポートレットFTLの更新

次の手順に従って、変更した `portlet.ftl` ファイルを更新します。

1.  `<a class="icon-monospaced portlet-icon-back text-default" href="${portlet_back_url}" title="<@liferay.language key="return-to-full-page" />">` 要素を見つけて、 `リストスタイルのない` クラスを追加します。
   
        <a 
          class="icon-monospaced list-unstyled portlet-icon-back text-default" 
          href="${portlet_back_url}" 
          title="<@liferay.language key="return-to-full-page" />"
        >
       

2.  `<div class="autofit-float autofit-row">` 要素を見つけて、 `ポートレットヘッダー` クラスを追加します。
   
        <div class="autofit-float autofit-row portlet-header">
       

ポートレットテンプレートが更新されます。 これは、必要なテーマテンプレートの変更のすべてではないにしても、ほとんどをカバーしています。 他のFreeMarkerテーマテンプレートを変更した場合、それらを [`_unstyled` テーマ](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates)テンプレートと比較できます。 テーマがLiferay Theme Generatorを使用している場合、 `gulp upgrade` タスクが報告する提案された変更を参照してください。



## 関連トピック

[CSSコードの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-css-code)

[構成可能なテーマ設定を行う](/docs/7-1/tutorials/-/knowledge_base/t/making-configurable-theme-settings)
