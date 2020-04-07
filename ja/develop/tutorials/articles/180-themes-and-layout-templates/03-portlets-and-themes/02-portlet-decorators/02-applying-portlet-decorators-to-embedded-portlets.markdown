---
header-id: applying-portlet-decorators-to-embedded-portlets
---

# 埋め込みポートレットへのポートレットデコレータの適用

[TOC levels=1-4]

ポートレットデコレータを含むテーマをインストールすると、サイト管理者は[ルックアンドフィール設定]ダイアログで[アプリケーションデコレータ]を選択することで、それらをポートレットインスタンスに適用できます。 テーマに埋め込みポートレットが含まれている場合、そのプリファレンスを設定することにより、デフォルト以外のApplication Decoratorを適用することもできます。

このチュートリアルでは、テーマの埋め込みポートレットにポートレットデコレータを適用する方法を示します。

## アプリケーションデコレータの設定

テーマの埋め込みポートレットのデフォルトのアプリケーションデコレータを定義するには、ポートレットプリファレンスでデフォルトのデコレータを設定する必要があります。

たとえば、 [クラシックテーマ](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/frontend-theme/frontend-theme-classic/src/templates/portal_normal.ftl) 、Id `ベアボーン` アプリケーションデコレータを宣言し、 `portal_normal.ftl`埋め込み検索ポートレットに適用します。

    <div class="navbar navbar-top navigation-bar-secondary">
      <div class="container user-personal-bar">
        <#assign preferences = 
        freeMarkerPortletPreferences.getPreferences(
          "portletSetupPortletDecoratorId", "barebone"
        ) />
    
        <#if show_header_search>
          <div class="ml-auto mr-4 navbar-form" role="search">
            <@liferay.search default_preferences="${preferences}" />
          </div>
        </#if>
    
        <@liferay.user_personal_bar />
      </div>
    </div>

埋め込みポートレットのデフォルトのデコレータを設定するには、次の手順を実行します。

1.  `portletSetupPortletDecoratorId` の値を、使用するアプリケーションデコレータのIDに設定します。 次の例では、 `barebone` デコレータを `preference` 変数に割り当てています。
   
        <#assign preferences = freeMarkerPortletPreferences.getPreferences(
          "portletSetupPortletDecoratorId", "barebone"
        ) />

2.  次に、ポートレットのタグの `default_preferences` 属性を、先ほど定義した変数に設定します（最後のステップで`設定`）：
   
        <@liferay.search default_preferences= "${preferences}" />

これで、埋め込みポートレットにカスタムのデフォルトのApplication Decorator \！が追加されました。

## 関連トピック

[テーマへのポートレットの埋め込み](/docs/7-1/tutorials/-/knowledge_base/t/embedding-portlets-in-themes)

[テーマポートレット](/docs/7-1/tutorials/-/knowledge_base/t/theming-portlets)
