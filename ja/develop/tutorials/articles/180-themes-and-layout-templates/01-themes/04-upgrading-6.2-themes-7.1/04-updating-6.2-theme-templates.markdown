---
header-id: updating-6-2-theme-templates
---

# 6.2テーマテンプレートの更新

[TOC levels=1-4]

@product-ver@テーマテンプレートは、Liferay Portal 6.2テーマテンプレートと本質的に同じです。 主な変更点は次のとおりです。

  - VelocityテンプレートはLiferay Portal CE 7.0で非推奨になりましたが、@product@のFreeMarkerテンプレートを優先して削除されました。 この移行の主な理由は次のとおりです。

      - FreeMarkerは定期的に開発および保守されていますが、Velocityはもはや積極的に開発されていません。

      - FreeMarkerはより高速で、より洗練されたマクロをサポートします。

      - FreeMarkerは、タグライブラリを表すメソッドを必要とせずに、タグライブラリを直接使用することをサポートしています。 あなたはそれらにボディコンテンツ、パラメータなどを渡すことができます。

  - Dockbarは置き換えられ、3つの異なるメニューのセットに再編成されました。

      - *製品メニュー*：現在のサイトのサイトおよびページナビゲーション、コンテンツ、設定、ページを管理し、ユーザーアカウント設定などに移動します。

      - *コントロールメニュー*：コンテンツを構成してページに追加し、シミュレーションウィンドウでページを表示します。

      - *ユーザーパーソナルバー*：通知とユーザーのアバターと名前を表示します。

    ![図1：Dockbarは@product-ver@で削除されたため、新しいコントロールメニューに置き換える必要があります。](../../../../images/upgrading-themes-dockbar.png)

VelocityテーマテンプレートをFreeMarkerに変換することから始めます。 ヘルプについては、Apacheの [FreeMarkerドキュメント](https://freemarker.apache.org/docs/ref.html) を参照してください。 一般的な@product@ FreeMarker変数とマクロは、 [`FTL_liferay.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/portal-template/portal-template-freemarker/src/main/resources/FTL_liferay.ftl)

[Gulp `upgrade` タスク](running-the-upgrade-task-for-6.2-themes)を使用した場合、必要なテーマテンプレートの変更がログに記録されます。 たとえば、Lunar Resortテーマの6.2から7.0へのアップグレードログと7.0から7.1へのアップグレードログは次のとおりです。

    ----------------------------------------------------------------
     Liferay Upgrade (6.2 to 7)
    ----------------------------------------------------------------
    
    File: portal_normal.ftl
        Warning: <@liferay.dockbar /> is deprecated, replace with 
        <@liferay.control_menu /> for new admin controls.
        Warning: not all admin controls will be visible without 
        <@liferay.control_menu />
        Warning: ${theme} variable is no longer available in Freemarker 
        templates, see https://goo.gl/9fXzYt for more information.
    [18:57:23] Finished 'upgrade:log-changes' after 5.61 ms
    [18:57:23] Finished 'upgrade' after 19 s
    
    ----------------------------------------------------------------
     Liferay Upgrade (7.0 to 7.1)
    ----------------------------------------------------------------
    
    Renamed aui.scss to clay.scss
    [19:16:54] Finished 'upgrade:log-changes' after 2.53 ms
    [19:16:54] Finished 'upgrade' after 16 min

ログは、削除され廃止されたコードについて警告し、該当する場合は置換を提案します。

Next, you'll learn how to update various theme templates to @product-ver@. テーマテンプレートを変更しなかった場合は、これらのセクションをスキップできます。

## ポータル標準FTLの更新

`portal_normal.ftl`カスタマイズしなかった場合、このセクションはスキップできます。 `portal_normal.ftl`を更新するには、次の手順に従います。

1.  変更された `portal_normal.ftl` ファイルを開き、次の6.2ディレクティブを更新された構文に置き換えます。 この変更は、 [7.0 Breaking Changes](/docs/7-0/reference/-/knowledge_base/r/breaking-changes#taglibs-are-no-longer-accessible-via-the-theme-variable-in-freemarker) リファレンスドキュメントに記載されています。

    | 6.2                                                    | 更新しました                                                                                                                                                                          |
    | ------------------------------------------------------ |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
    | `${theme.include(top_head_include)}`                   | `<@liferay_util["include"] page=top_head_include />`                                                                                                                      |
    | `${theme.include(body_top_include)}`                   | `<@liferay_util["include"] page=body_top_include />`                                                                                                                      |
    | `${theme.include(content_include)}`                    | `<@liferay_util["include"] page=content_include />`                                                                                                                       |
    | `${theme.wrapPortlet("portlet.ftl", content_include)}` | `<@liferay_theme["wrap-portlet"] page="portlet.ftl"> <@liferay_util["include"] page=content_include /> </@`                                                      |
    | `${theme.include(body_bottom_include)}`                | `<@liferay_util["include"] page=body_bottom_include />`                                                                                                                   |
    | `${theme.include(bottom_include)}`                     | `<@liferay_util["include"] page=bottom_include />`                                                                                                                        |
    | `${theme_settings["my-theme-setting"]}`                | `${themeDisplay.getThemeSetting("my-theme-setting")}`                                                                                                                           |
    | `${theme.runtime("56", "articleId=" + my_article_id)}` | `<@liferay_portlet ["runtime"] portletName =`<br/>`"com_liferay_journal_content_web_portlet_JournalContentPortlet"` <br/>`queryString = "articleId =" + my_article_id />` |


2.  必要に応じて、パンくずリストとページタイトルコードを削除します。
<nav id="breadcrumbs"> <@liferay.breadcrumbs /> </nav> ...
        <h2 class="page-title">
            <span>${the_title}</span>
        </h2>

3.  Liferay Portal 6.2テーマで分割ドックバーを使用した場合、 `body` 要素の `class` 値から `dockbar-split` 削除して、以下のマークアップと一致するようにします。
   
        <body class="${css_class}">

4.  `<a href="#main-content" id="skip-to-content"><@ liferay.language key = "skip-to-content" /></a>` 要素を見つけ、以下に示す更新されたLiferay UIクイックアクセスマクロに置き換えます。
   
        <@liferay_ui["quick-access"] contentId="#main-content" />

5.  `<@ liferay.dockbar />` マクロを更新されたコントロールメニューマクロに置き換えます。
   
        <@liferay.control_menu />

6.  `navigation.ftl` テーマテンプレートに `<#if ...></＃if>` ラッパーを追加します。
   
        <#if has_navigation && is_setup_complete>
            <#include "${full_templates_path}/navigation.ftl" />
        </#if>

7.  `コンテンツ` `<div>` をHTML 5 `セクション` 要素に置き換えます。 `セクション` 要素はより正確で、スクリーンリーダーのアクセシビリティが向上します。
   
        <section id="content">

8.  `コンテンツ` `<section>` すぐ内側に `<h1 class="hide-accessible">${the_title}</h1>` ヘッダー要素を追加して、アクセシビリティをサポートします。

テーマのナビゲーションテンプレートを変更した場合は、次のセクションの手順に従います。

## ナビゲーションFTLの更新

次の手順に従って、変更した `navigation.ftl` ファイルを更新します。

1.  `<nav class="${nav_css_class}" id="navigation" role="navigation">` 要素の下に、アクセシビリティスクリーンリーダーの次の非表示見出しを追加します。 <h1 class="hide-accessible">
            <@liferay.language key="navigation" />
        </h1>

2.  レイアウトにアクセスするには、 `<#assign nav_item_css_class = "" />` 変数宣言の下に次の変数宣言を追加します。
   
        <#assign nav_item_layout = nav_item.getLayout() />

3.  `<a aria-labelledby="layout_${nav_item.getLayoutId()}"...</a>` アンカーの `$ {nav_item.icon（）}`変数を次の要素に置き換えます。
   
        <@liferay_theme["layout-icon"] layout=nav_item_layout />

ナビゲーションテンプレートが更新されます。 次に、 `portlet.ftl` を更新できます。

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

ポートレットテンプレートが更新されます。 次に `init_custom.ftl` を更新できます。

## InitカスタムFTLの更新

テーマが [構成可能なテーマ設定](/docs/7-1/tutorials/-/knowledge_base/t/making-configurable-theme-settings)を使用している場合、以下のパターンに従って、新しい構文を使用するように設定を更新します。

元の構文：

    <#assign theme_setting_variable = 
    getterUtil.getBoolean(theme_settings["theme-setting-key"])>
    
    <#assign theme_setting_variable = 
    getterUtil.getString(theme_settings["theme-setting-key"])>

更新された構文：

    <#assign theme_setting_variable =
    getterUtil.getBoolean(themeDisplay.getThemeSetting("theme-setting-key"))/>
    
    <#assign theme_setting_variable = 
    themeDisplay.getThemeSetting("theme-setting-key")/>

たとえば、Lunar Resortテーマの更新されたテーマ設定は次のとおりです。

    <#assign show_breadcrumbs = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-breadcrumbs"))/>
    
    <#assign show_page_title = 
    getterUtil.getBoolean(themeDisplay.getThemeSetting("show-page-title"))/>

テーマテンプレートの変更のすべてではありませんが、ほとんどをカバーしています。 他のFreeMarkerテーマテンプレートを変更した場合は、それらを [`_unstyled` テーマ](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/frontend-theme/frontend-theme-unstyled/src/main/resources/META-INF/resources/_unstyled/templates)テンプレートと比較してください。 テーマでLiferay JS Theme Toolkitを使用している場合、Gulp `upgrade` タスクが報告する提案された変更を参照してください。

## 関連トピック

[CSSコードの更新](/docs/7-1/tutorials/-/knowledge_base/t/updating-6-2-css-code)

[構成可能なテーマ設定を行う](/docs/7-1/tutorials/-/knowledge_base/t/making-configurable-theme-settings)
