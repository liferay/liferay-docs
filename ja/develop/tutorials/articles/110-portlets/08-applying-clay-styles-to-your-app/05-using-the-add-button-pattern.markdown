---
header-id: applying-the-add-button-pattern
---

# ボタンの追加パターンを適用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

粘土の追加ボタンパターンは、エンティティを追加するアクション用です（たとえば、新しいブログエントリボタン）。 クリーンで最小限のUIを提供します。 アプリのどの画面でも使用できます。 次の手順に従って、アプリにプラスボタンを追加します。

1.  `liferay-frontend` taglib宣言をポートレットの `init.jsp`追加します。

    ``` markup
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    ```

2.  [`追加メニュー` タグ](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/add-menu.html) をポートレットのビューに追加します。

    ``` html
    <liferay-frontend:add-menu>
    </liferay-frontend:add-menu>
    ```

3.  持っているすべてのメニュー項目に [`<liferay-frontend:add-menu-item>`](@app-ref@/frontend-taglib/latest/taglibdocs/liferay-frontend/add-menu-item.html) タグをネストします。 以下に、単一の項目を使用した追加ボタンパターンの例を示します。

    ``` html
    <liferay-frontend:add-menu>
        <liferay-frontend:add-menu-item 
          title='<%= LanguageUtil.get(request,"titleName") %>' 
          url="<%= nameURL.toString() %>" 
        />
    </liferay-frontend:add-menu>
    ```

    アイテムが1つしかない場合、プラスアイコンはそのアイテムをトリガーするボタンとして機能します。 複数のアイテムがある場合、プラスアイコンをクリックすると、それらを含むメニューが表示されます。

    ![図1：追加ボタンパターンは、 <code>add-menu</code> タグと少なくとも1つの <code>add-menu-item</code> タグで構成されます。](../../../images/add-button-diagram.png)

`com.liferay.mobile.device.rules.web` モジュールの追加メニューを以下に示します。

``` html
<liferay-frontend:add-menu
  inline="<%= true %>"
>
  <liferay-frontend:add-menu-item
    title='<%= LanguageUtil.get(resourceBundle, "add-device-family") %>'
    url="<%= addRuleGroupURL %>"
  />
</liferay-frontend:add-menu>
```

そこにあります！ これで、追加ボタンパターンの使用方法がわかりました。

## 関連トピック

  - [空の結果メッセージの設定](/docs/7-1/tutorials/-/knowledge_base/t/setting-empty-results-messages)
  - [管理ツールバーの実装](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-management-toolbar)
