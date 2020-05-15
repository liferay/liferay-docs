---
header-id: liferay-ui-icon-menus
---

# Liferay UIアイコンメニュー

[TOC levels=1-4]

`liferay-ui:icon-menu`タグを使用して、ポップアップナビゲーションメニューをアプリに追加できます。アイコンメニューには、必要に応じてメニューオプションが表示され、必要ない場合は折りたたみ式メニューに格納されます。これにより、UIがきれいに整頓されます。アイコンリストと同様に、ナビゲーション項目ごとに[アイコン](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icons)をネストします。個人用サイトポートレットのサイトアクションメニュー内のアイコンメニューの例を以下に示します。

![図1：アイコンメニューの設定は簡単です。](../../../images/liferay-ui-taglib-icon-menu.png)

JSP構成の例：

    <liferay-ui:icon-menu
    direction="left-side"
    icon="<%= StringPool.BLANK %>"
    markupView="lexicon"
    message="<%= StringPool.BLANK %>"
    showWhenSingleIcon="<%= true %>"
    >
    
    <liferay-ui:icon
    message="go-to-public-pages"
    target="_blank"
    url="<%= group.getDisplayURL(themeDisplay, false) %>"
    />
    
    <liferay-ui:icon
    message="leave"
    url="<%= leaveURL %>"
    />
    
    </liferay-ui:icon-menu>

この`url`属性は、アイコンを適切にレンダリングするために必要です。属性の全リストについては、[Icon Menu taglibdocs](@platform-ref@/7.1-latest/taglibs/util-taglib/liferay-ui/icon-menu.html)を参照してください。

## 関連トピック

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Liferay UI Icon Lists](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icon-lists)

[Liferay UI Icons](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ui-icons)
