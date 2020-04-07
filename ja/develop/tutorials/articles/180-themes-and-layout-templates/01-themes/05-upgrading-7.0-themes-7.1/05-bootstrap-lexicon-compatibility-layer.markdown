---
header-id: using-the-bootstrap-3-lexicon-css-compatibility-layer
---

# Bootstrap 3 Lexicon CSS互換性レイヤーの使用

[TOC levels=1-4]

デフォルトでは、@product@にはすぐに使えるBootstrap 4が含まれています。 ブートストラップ4は完全に書き直され、したがって、いくつか含まれている [顕著な変化を](https://getbootstrap.com/docs/4.1/migration/) と [の互換性アップデート](http://getbootstrap.com/docs/4.1/getting-started/browsers-devices/) あなたのテーマは、ブートストラップ3またはレキシコンCSSを使用している場合は懸念の原因である可能性があります。 でも心配しないで。 アップグレードを円滑に実行するために、@product@には互換性レイヤーが含まれているため、新しいBootstrap 4およびClay CSSとともにBootstrap 3マークアップおよびLexicon CSSマークアップを使用できます。 バンドルされたアイコンフォント（Font Awesome v3.2.1およびGlyphicons 3）も、互換性レイヤーの [`_components.scss`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/compat/_components.scss#L3-L4) ファイルに移動されました。 テーマが [Styledベーステーマ](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-styled)拡張する場合、この互換性レイヤーはデフォルトで含まれます。

| **注：** 互換性レイヤーは、以下を確実にするための短期的なソリューションとして意図されています。 Bootstrap 3とLexicon CSSコンポーネントが壊れていないことを確認してください。 [Bootstrap 4](https://getbootstrap.com/docs/4.3/migration/) を使用するようにテーマを更新します|および [Clay CSS](https://clayui.com/docs/css-framework/scss.html)。 なります|将来のリリースでは無効になります。 Bootstrap 4とClayを使用するようにテーマを移行します|できるようになり次第、CSS。

マークアップを更新するには、次のガイドラインに従ってください。

1.  互換性レイヤーを有効にした場合のテーマの外観を確認してください（デフォルトで有効になっています）。

2.  不要な互換性レイヤーのコンポーネントを個別に無効にします。 これらは、 [`css / compat / _variables.scss`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-styled/src/main/resources/META-INF/resources/_styled/css/compat/_variables.scss) ファイルにリストされています。 便宜上、コンポーネントは次のとおりです。
   
        // Compatibility layer components config
       
        $compat-alerts: true !default;
        $compat-basic_search: true !default;
        $compat-breadcrumbs: true !default;
        $compat-button_groups: true !default;
        $compat-buttons: true !default;
        $compat-cards: true !default;
        $compat-component_animations: true !default;
        $compat-dropdowns: true !default;
        $compat-figures: true !default;
        $compat-form_validation: true !default;
        $compat-forms: true !default;
        $compat-grid: true !default;
        $compat-icons: true !default;
        $compat-labels: true !default;
        $compat-liferay: true !default;
        $compat-list_groups: true !default;
        $compat-management_bar: true !default;
        $compat-modals: true !default;
        $compat-nav_tabs: true !default;
        $compat-navbar: true !default;
        $compat-navs: true !default;
        $compat-pager: true !default;
        $compat-pagination: true !default;
        $compat-panels: true !default;
        $compat-progress_bars: true !default;
        $compat-responsive_utilities: true !default;
        $compat-sidebar: true !default;
        $compat-simple_flexbox_grid: true !default;
        $compat-stickers: true !default;
        $compat-tables: true !default;
        $compat-toggle_card: true !default;
        $compat-toggle_switch: true !default;
        $compat-toolbar: true !default;
        $compat-user_icons: true !default;
        $compat-utilities: true !default;

    コンポーネントを無効にするには、互換性を削除するコンポーネントを `/src/css/_clay_custom.scss` に追加し（このファイルが存在しない場合は作成します）、その値を `false`設定します。 以下の例は、アラートとカードの互換性を削除します。
   
        $compat-alerts: false !default;
        $compat-cards: false !default;

    | **注：** 一部の@product@コンポーネントはBootstrap 4に移行されていません。 |特定のコンポーネントを無効にすると、UIの一部が破損する可能性があります。 |したがって、マークアップをアップグレードした後、すべてのマークアップを再度有効にすることをお勧めします。無効にするコンポーネント。 注意して進めてください。

3.  結果に満足するまで、マークアップをBootstrap 4とClay CSSに更新します。

4.  `/src/css/_clay_custom.scss`falseに設定したコンポーネントを削除して、互換性レイヤーで無効にしたコンポーネントを再度有効にします。 これにより、@product@のUIが破損しないことが保証されます。

これで、Bootstrap 3およびLexicon CSS互換性レイヤーを使用して、テーマのアップグレード中にスムーズな移行を提供する方法がわかりました。

## 関連トピック

[CSSコードの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-css-code)

[プロジェクトメタデータの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-project-metadata)
