---
header-id: liferay-ui-tabs
---

# Liferay UIタブ

[TOC levels=1-4]

タブは、コンテンツを個々のセクションに整理する仕切りを作成します。コンテンツは、別のJSPから埋め込んだり含めたりできます。

アプリにタブを追加するには、`<liferay-ui:tabs>`タグを使用し、各タブの名前を`names`属性のコンマ区切りリストとして指定します。たとえば、`tab1`、`tab2`、`tab3`と名前が付けられた3つのタグは、JSPで次のように表示されます。

    <liferay-ui:tabs names="tab1,tab2,tab3">
    
    </liferay-ui:tabs>

各タブには、コンテンツを表示するための対応するセクションが必要です。タブごとに`liferay-ui:section`タグをネストします。各セクション内で、HTMLコンテンツを追加したり、別のJSPから（`<%@ includefile="filepath"%>`ディレクティブ経由で）コンテンツを含めることで間接的にコンテンツを追加できます。以下のスニペットの例は、予定表ポートレットの`configuration.jsp`からのものです。

    <liferay-ui:tabs
    names='<%= "user-settings,display-settings,rss" %>'
    param="tabs2"
    refresh="<%= false %>"
    type="tabs nav-tabs-default"
    >
    <liferay-ui:section>
    <%@ include file="/configuration/user_settings.jspf" %>
    </liferay-ui:section>
    
    <liferay-ui:section>
    <%@ include file="/configuration/display_settings.jspf" %>
    </liferay-ui:section>
    
    <liferay-ui:section>
    <%@ include file="/configuration/rss.jspf" %>
    </liferay-ui:section>
    </liferay-ui:tabs>

![図1：タブは、構成オプションを同じUI内の個々のセクションに整理する便利な方法です。](../../../images/liferay-ui-taglib-tabs.png)

上記の例では、タブで使用可能な属性の一部を使用しています。 属性の全リストについては、[Tabs taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/tabs.html)を参照してください。

## 関連トピック

[Clay Navigation Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-navigation-bars)

[Clay Dropdown Menus and Action Menus](/docs/7-1/tutorials/-/knowledge_base/t/clay-dropdown-menus-and-action-menus)

[Liferay UI Icon Help](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-help)
