---
header-id: theming-portlets
---

# テーマポートレット

[TOC levels=1-4]

テーマは、アプリに追加のスタイルを提供できます。 テーマの `portlet.ftl` ファイルを変更することにより、ポートレットコンテナのマークアップを変更できます。

このチュートリアルでは、テーマを使用してポートレットのスタイルを設定する方法を示します。

## ポートレットFTL

テーマのCSSまたはポートレットの [ルックアンドフィール設定](/docs/7-1/user/-/knowledge_base/u/look-and-feel-configuration) メニューを使用して個別にポートレットのスタイルを設定できますが、サイト内のすべてのポートレットのデフォルトのルックアンドフィールを変更することもできます。 ポートレットのテンプレート（コンテナ、CSSクラス、および全体的なHTMLマークアップ）は、テーマの `portlet.ftl` ファイルを介して定義されます。 すべてのポートレットにカスタムスタイルを提供するには、さまざまなコンテナ要素に対してこのファイルのCSSクラスを使用し、ポートレットデコレータと組み合わせて、目的のルックアンドフィールを実現します。 注意してください： `portlet.ftl` に変更すると、テーマが適用されるときにサイト内のすべてのポートレットに影響します。

ポートレットのテンプレートを変更する際に役立つように、@product-ver@のデフォルトテーマに含まれている [`portlet.ftl`](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-classic/src/templates/portlet.ftl) ファイルの概要を以下に示します。

    <#assign
        portlet_display = portletDisplay
        portlet_back_url = htmlUtil.escapeHREF(portlet_display.getURLBack())
        portlet_content_css_class = "portlet-content"
        portlet_display_name = htmlUtil.escape(portlet_display.getPortletDisplayName())
        portlet_display_root_portlet_id = htmlUtil.escapeAttribute(portlet_display.getRootPortletId())
        portlet_id = htmlUtil.escapeAttribute(portlet_display.getId())
        portlet_title = htmlUtil.escape(portlet_display.getTitle())
    />

上記の変数はテンプレート全体で使用されるため、ファイルを変更する前にそれらを理解することが重要です。

  - `portletDisplay`： `themeDisplay` オブジェクトから取得され、ポートレットに関する情報が含まれます。
  - `portlet_back_url`：ポートレット `WindowState` が最大化されたときに前のページに戻るURL。
  - `portlet_display_name`：GUIに表示されるポートレットの「フレンドリ」名。
  - `portlet_display_root_portlet_id`：ポートレットのルートポートレットID。
  - `portlet_id`：ポートレットのID（ポートレット名前空間とは異なります）
  - `portlet_title`：ポートレットJavaクラスで設定されたポートレット名（通常は `Keys.java` クラスから）。

次に、ポートレットヘッダーを表示する必要があるかどうかの条件がチェックされます。 ポートレットにポートレットツールバー（構成、パーミッション、ルックアンドフィール）がある場合、条件はtrueでヘッダーが表示されます。

    <#if portlet_display.isPortletDecorate() && !portlet_display.isStateMax() 
    && portlet_display.getPortletConfigurationIconMenu()?? 
    && portlet_display.getPortletToolbar()??>

ポートレットのUIの一部を動的に表示する場合は、同様のパターンを使用できます。

次に、ポートレットのタイトルメニューを定義します。 これらは、リソース（Webコンテンツ表示、メディアギャラリー、ドキュメント、およびメディア）を追加できるポートレットで使用されます。

    portlet_title_menus = portlet_toolbar.getPortletTitleMenus(portlet_display_root_portlet_id, renderRequest, renderResponse)

以下の構成には、構成メニューの情報（構成、アクセス許可、ルックアンドフィール）が含まれています。

    portlet_configuration_icons = portlet_configuration_icon_menu.getPortletConfigurationIcons(portlet_display_root_portlet_id, renderRequest, renderResponse)

ファイルの残りには、ポートレットトッパーとポートレットコンテンツのHTMLマークアップが含まれています。 このセクションは、 `portlet.ftl` ファイルの表面をほとんど傷つけません。 `portlet.ftl` ファイルを自分で調べて、テーマとサイト用に更新する必要がある要素とクラスを決定する必要があります。

テーマの `portlet.ftl` ファイルに精通したので、ポートレットの全体的なルックアンドフィールでPortlet Decoratorsが果たす役割を学習できます。

## Portlet Decorators

ポートレットデコレータを使用すると、アプリケーションラッパーのスタイルをカスタマイズできます。 テーマは、その中の3つのデフォルトのポートレットのデコレータにバンドル `Liferayのルック・アンド・feel.xml`：

  - `ベアボーン`：このデコレータは、ベアアプリケーションのコンテンツを表示し、ラッピングボックスもカスタムアプリケーションタイトルも表示しません。

  - `Borderless`：このデコレータはタイトルを上部に表示しますが、ラッピングボックスは表示しません。

  - `飾る`：これは、クラシックテーマを使用する場合のデフォルトのポートレットデコレータです。 アプリケーションをボーダー付きの白いボックスで囲み、タイトルを上部に表示します。

これで、ポートレットをスタイリッシュにする方法がわかりました！ デフォルトのデコレータはあなたのための十分なスタイリッシュされていない場合でも、\ [EDITOR：私たちのデフォルトは\常にスタイリッシュです\！]にアクセスしてください [ポートレットのデコレータ](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers) あなた自身を適用する方法メイクと学ぶこと。

## 関連トピック

[ルックアンドフィールの構成](/docs/7-1/user/-/knowledge_base/u/look-and-feel-configuration)

[ポートレットラッパーの構成可能なスタイルの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-configurable-styles-for-portlet-wrappers)

[テーマとレイアウトテンプレート](/docs/7-1/tutorials/-/knowledge_base/t/themes-and-layout-templates)
