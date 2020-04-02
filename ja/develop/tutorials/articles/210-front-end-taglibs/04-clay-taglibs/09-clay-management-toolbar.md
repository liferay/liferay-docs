---
header-id: clay-management-toolbar
---

# Clay管理ツールバー

[TOC levels=1-4]

管理ツールバーを使用することで、管理者はアプリの検索コンテナの結果を管理できます。検索結果の表示タイプをフィルター処理、並べ替え、選択できるため、ドキュメント、Webコンテンツ、アセットエントリなど、探しているものをすばやく特定できます。管理ツールバーは完全にカスタマイズ可能であるため、すべてのコントロールを実装することも、アプリに必要なコントロールのみを実装することもできます。

![図1：管理ツールバーを使用して、ユーザーはアプリでコンテンツを表示する方法をカスタマイズできます。](../../../images/clay-taglib-management-toolbar.png)

管理ツールバーを作成するには、`clay:management-toolbar` taglibを使用します。ツールバーにはいくつかの重要なセクションが含まれています。各セクションは、異なる属性を使用してグループ化および構成されます。これらの属性については、以下で詳しく説明します。

## 表示コンテキストを使用して管理ツールバーを構成する

表示コンテキスト（管理ツールバーの表示オプションを設定するための個別のクラス）を使用してツールバーのすべてまたは一部の設定オプションを定義する場合、`displayContext`属性で表示コンテキストを指定できます 。以下に例を示します。

    <clay:management-toolbar
    displayContext="<%= viewUADEntitiesManagementToolbarDisplayContext %>"
    />

[Filtering and Sorting Items with the Management Toolbarのチュートリアル](/docs/7-1/tutorials/-/knowledge_base/t/filtering-and-sorting-items-with-the-management-toolbar)で、表示コンテキストのユースケースの例を確認でき ます。
管理ツールバーの構成に表示コンテキストは必要ありません。表示コンテキストを使用して、管理ツールバーの構成オプションを必要な数だけ指定できます。

## チェックボックスとアクション

`actionItems`、`searchContainerId`、`selectable`、および`totalItems`属性を使用すると、すべての検索コンテナの結果を選択し、それらに対して一括操作を実行するチェックボックスをツールバーに追加できます。個々の結果がオンになっている場合、またはツールバーのマスターチェックボックスがオンになっている場合は、アクションと合計アイテム数が表示されます。

`actionItems`：1つの結果がオンになっている場合、または管理ツールバーのマスターチェックボックスがオンになっている場合に表示するドロップダウンアイテムのリスト。ページ間で複数の結果を選択できます。管理ツールバーは、選択された結果の数を追跡します。

`searchContainerId`：管理ツールバーに接続された検索コンテナのID

`selectable`：管理ツールバーにチェックボックスを含めるかどうか

`totalItems`：ページネーション全体のアイテムの総数。この数字は、1つまたは複数のアイテムが選択された場合に表示されます。

構成例を以下に示します。

    actionItems="<%=
    new JSPDropdownItemList(pageContext) {
    {
    add(
    dropdownItem -> {
    dropdownItem.setHref("#edit");
    dropdownItem.setLabel("Edit");
    });
    
    add(
    dropdownItem -> {
    dropdownItem.setHref("#download");
    dropdownItem.setIcon("download");
    dropdownItem.setLabel("Download");
    dropdownItem.setQuickAction(true);
    });
    
    add(
    dropdownItem -> {
    dropdownItem.setHref("#delete");
    dropdownItem.setLabel("Delete");
    dropdownItem.setIcon("trash");
    dropdownItem.setQuickAction(true);
    });
    }
    }
    %>"

アクションアイテムは、ページネーション全体で選択されたアイテムの数とともに、[Actions]メニューにリストされます。

![図2：1つのアイテム、複数のアイテム、またはマスターチェックボックスがオンになっている場合、アクションは管理ツールバーのドロップダウンメニューにもリストされます。](../../../images/clay-taglib-management-toolbar-actions.png)

上記の例の削除アクションやダウンロードアクションなど、アクションにアイコンが指定されている場合、アイコンはアクションメニューの横にも表示されます。

![図3：管理ツールバーは、選択された結果を追跡し、それらに対して実行するアクションを表示します。](../../../images/clay-taglib-management-toolbar-selectable.png)

## 検索結果のフィルタリングと並べ替え

`filterItems`、`sortingOrder`、および`sortingURL`属性を使用すると、検索コンテナの結果をフィルタリングしたり並べ替えたりできます。フィルタリングと並べ替えは、1つの便利なドロップダウンメニューにまとめられています。

`filterItems`：検索コンテナのフィルタリングオプションを設定します。このフィルターは、すべてのコントロールパネルアプリケーションに含める必要があります。フィルタリングオプションには、並べ替え条件、並べ替え順序などを含めることができます。

`sortingOrder`：現在の並べ替え順序（昇順または降順）。

`sortingURL`：並べ替え順序を変更するURL

以下の例では、2つのフィルターオプションと2つの並べ替えオプションを追加します。

    filterItems="<%=
    new DropdownItemList(_request) {
    {
    addGroup(
    dropdownGroupItem -> {
    dropdownGroupItem.setDropdownItemList(
    new DropdownItemList(_request) {
    {
    add(
    dropdownItem -> {
    dropdownItem.setHref("#1");
    dropdownItem.setLabel("Filter 1");
    });
    
    add(
    dropdownItem -> {
    dropdownItem.setHref("#2");
    dropdownItem.setLabel("Filter 2");
    });
    }
    }
    );
    dropdownGroupItem.setLabel("Filter By");
    });
    
    addGroup(
    dropdownGroupItem -> {
    dropdownGroupItem.setDropdownItemList(
    new DropdownItemList(_request) {
    {
    add(
    dropdownItem -> {
    dropdownItem.setHref("#3");
    dropdownItem.setLabel("Order 1");
    });
    
    add(
    dropdownItem -> {
    dropdownItem.setHref("#4");
    dropdownItem.setLabel("Order 2");
    });
    }
    }
    );
    dropdownGroupItem.setLabel("Order By");
    });
    }
    }
    %>"

![図4：検索コンテナの結果を並べ替えたりフィルタリングすることもできます。](../../../images/clay-taglib-management-toolbar-filter-and-sort.png)

## 検索フォーム

`clearResultsURL`、`searchActionURL`、`searchFormName`、`searchInputName`、および`searchValue` 属性を使用すると、検索フォームを設定できます。管理ツールバーの主要部分は、検索フォーム用に予約されています。

`clearResultsURL`：検索をリセットするURL

`searchActionURL`：検索フォームを送信するアクションURL

`searchFormName`：検索フォームの名前

`searchInputName`：検索入力の名前

`searchValue`：検索入力の値

構成例を以下に示します。

    <clay:management-toolbar
    clearResultsURL="<%= searchURL %>"
    disabled="<%= isDisabled %>"
    namespace="<%= renderResponse.getNamespace() %>"
    searchActionURL="<%= searchURL %>"
    searchFormName="fm"
    searchInputName="<%= DisplayTerms.KEYWORDS %>"
    searchValue="<%= ParamUtil.getString(request, searchInputName) %>"
    selectable="<%= false %>"
    totalItems="<%= totalItems %>"
    />

![図5：検索フォームはほとんどの管理ツールバーで構成されており、ユーザーは検索コンテナの結果全体を検索できます。](../../../images/clay-taglib-management-toolbar-search-form.png)

## 情報パネル

`infoPanelId`および`showInfoButton`属性を使用すると、検索コンテナの結果に関連する追加情報を表示する格納可能なサイドバーパネルを追加できます。

`infoPanelId`：切り替える情報パネルのID

`showInfoButton`：情報ボタンを表示するかどうか

以下の構成例では、`showInfoButton` 属性が（`displayContext`属性で指定されている）表示コンテキストで指定されており、`infoPanelId`がJSPで明示的に設定されています。

    <clay:management-toolbar
    displayContext="<%= journalDisplayContext %>"
    infoPanelId="infoPanelId"
    namespace="<%= renderResponse.getNamespace() %>"
    searchContainerId="<=% searchContainerId %>"
    />

![図6：情報パネルにより、UIが整理されます。.](../../../images/clay-taglib-management-toolbar-info-panel.png)

## 表示タイプ

`viewTypes`属性は、検索コンテナの結果の表示オプションを指定します。次の3つの表示オプションから選択できます。

**カード：**検索結果の列を横型または縦型カードに表示します。

![図7：管理ツールバーのアイコン表示ビューには、コンテンツの説明とステータスの概要が表示されます。](../../../images/clay-taglib-management-toolbar-view-type-card.png)

**リスト：**検索結果の列の要約された詳細とともに詳細な説明を表示します。

![図8：管理ツールバーのリストビュータイプは、コンテンツの完全な説明を提供します。](../../../images/clay-taglib-management-toolbar-view-type-list.png)

**テーブル：** デフォルトのビュー。検索結果の列を左から右にリストします。

![図9：管理ツールバーのテーブルビュータイプには、コンテンツの情報が個々の列に一覧表示されます。](../../../images/clay-taglib-management-toolbar-view-type-table.png)

構成例を以下に示します。

    viewTypes="<%=
    new JSPViewTypeItemList(pageContext, baseURL, selectedType) {
    {
    addCardViewTypeItem(
    viewTypeItem -> {
    viewTypeItem.setActive(true);
    viewTypeItem.setLabel("Card");
    });
    
    addListViewTypeItem(
    viewTypeItem -> {
    viewTypeItem.setLabel("List");
    });
    
    addTableViewTypeItem(
    viewTypeItem -> {
    viewTypeItem.setLabel("Table");
    });
    }
    }
    %>"

上記の例はJSPでビュータイプを設定する方法を示していますが、[各ビュータイプをいつ使用するかを指定する](/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-view-types)必要もあります。



![図10：管理ツールバーには、3つの表示タイプオプションがあります。](../../../images/clay-taglib-management-toolbar-view-types.png)

## 作成メニュー

`creationMenu`属性は、1つまたは複数のアイテムにメニューの追加ボタンを作成します。新しいエンティティ（新しいブログエントリなど）の作成に使用されます。

`addPrimaryDropdownItem()`メソッドを使用してトップレベルアイテムをドロップダウンメニューに追加するか、`addFavoriteDropdownItem()`メソッドを使用してセカンダリアイテムをドロップダウンメニューに追加します。

以下の構成例では、2つのプライマリ作成メニュー項目と2つのセカンダリ作成メニュー項目を追加します。

    creationMenu="<%=
    new JSPCreationMenu(pageContext) {
    {
    addPrimaryDropdownItem(
    dropdownItem -> {
    dropdownItem.setHref("#1");
    dropdownItem.setLabel("Sample 1");
    });
    
    addPrimaryDropdownItem(
    dropdownItem -> {
    dropdownItem.setHref("#2");
    dropdownItem.setLabel("Sample 2");
    });
    
    addFavoriteDropdownItem(
    dropdownItem -> {
    dropdownItem.setHref("#3");
    dropdownItem.setLabel("Favorite 1");
    });
    
    addFavoriteDropdownItem(
    dropdownItem -> {
    dropdownItem.setHref("#4");
    dropdownItem.setLabel("Other item");
    });
    }
    };
    %>"

![図11：管理ツールバーでは、オプションで、新しいエンティティを作成するための作成メニューを追加できます。](../../../images/clay-taglib-management-toolbar-creation-menu.png)

## 関連トピック

[Clay Dropdown Menus and Action Menus](/docs/7-1/tutorials/-/knowledge_base/t/clay-dropdown-menus-and-action-menus)

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Clay Navigation Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-navigation-bars)
