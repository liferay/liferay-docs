---
header-id: clay-dropdown-menus-and-action-menus
---

# Clayドロップダウンメニューとアクションメニュー

[TOC levels=1-4]

 `clay:dropdown-menu`および`clay:actions-menu` taglibを使用して、アプリにドロップダウンメニューを追加できます。Clay taglibには、選択可能なメニューのバリエーションがいくつか用意されています。両方のtaglibといくつかの例を以下に示します。

## ドロップダウンメニュー

基本的なドロップダウンメニュー：

    <clay:dropdown-menu
    items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    label="Default"
    />

![図1：Clay taglibは、アプリにドロップダウンメニューを追加するために必要なものすべてを提供します。](../../../images/clay-taglib-dropdown-basic.png)

この場合、ドロップダウンメニューの項目は、Javaクラス`dropdownDisplayContext`で定義されています。メニュー項目は`NavigationItem`オブジェクトです。`setDisabled(true)`メソッドを使用してメニュー項目を無効にし、`setActive(true)`メソッドを使用してメニュー項目をアクティブに することができます`href`属性は`setHref()`メソッドを使用して設定され、ラベルは`setLabel()`メソッドを使用して定義されます。`dropdownDisplayContext`クラスの実装例を次に示します。

    if (_defaultDropdownItems != null) {
    return _defaultDropdownItems;
    }
    
    _defaultDropdownItems = new ArrayList<>();
    
    for (int i = 0; i < 4; i++) {
    NavigationItem navigationItem = new NavigationItem();
    
    if (i == 1) {
    navigationItem.setDisabled(true);
    }
    else if (i == 2) {
    navigationItem.setActive(true);
    }
    
    navigationItem.setHref("#" + i);
    navigationItem.setLabel("Option " + i);
    
    _defaultDropdownItems.add(navigationItem);
    }
    
    return _defaultDropdownItems;
    }

`NavigationItem`のタイプを`TYPE_GROUP`に設定し、項目を個別の`ArrayList`にネストすることで、メニュー項目をグループに整理できます。`setSeparator(true)`メソッドを使用して、グループを視覚的に分離するための横型セパレーターを追加でき ます。以下は、`dropdownsDisplayContext`クラスからのコードスニペット です。

    group1NavigationItem.setSeparator(true);
    group1NavigationItem.setType(NavigationItem.TYPE_GROUP);

対応するtaglib：

    <clay:dropdown-menu
    items="<%= dropdownsDisplayContext.getGroupDropdownItems() %>"
    label="Dividers"
    />

![図2：ドロップダウンメニュー項目をグループに整理できます。](../../../images/clay-taglib-dropdown-group.png)

ドロップダウンメニューに入力を追加することもできます。入力をドロップダウンメニューに追加するには、`setType()`メソッドを使用して入力のタイプを設定し（例：`NavigationItem.TYPE_CHECKBOX`）、`setInputName()`メソッドを使用してその名前を、`setInputValue()`メソッドを使用してその値をそれぞれ設定します。以下に実装例を示します。

    navigationItem.setInputName("checkbox" + i);
    navigationItem.setInputValue("checkboxValue" + i);
    navigationItem.setLabel("Group 1 - Option " + i);
    navigationItem.setType(NavigationItem.TYPE_CHECKBOX);

対応するtaglib：

    <clay:dropdown-menu
    buttonLabel="Done"
    items="<%= dropdownsDisplayContext.getInputDropdownItems() %>"
    label="Inputs"
    searchable="<%= true %>"
    />

![図3：入力はドロップダウンメニューに含めることができます。](../../../images/clay-taglib-dropdown-input.png)

メニュー項目にはアイコンを含めることもできます。メニュー項目にアイコンを追加するには、`setIcon()`メソッドを使用します。以下に例を示します。

    navigationItem.setIcon("check-circle-full")

対応するtaglib：

    <clay:dropdown-menu
    items="<%= dropdownsDisplayContext.getIconDropdownItems() %>"
    itemsIconAlignment="left"
    label="Icons"
    />

![図4：アイコンはドロップダウンメニューに含めることができます。](../../../images/clay-taglib-dropdown-icons.png)

## アクションメニュー

基本アクションメニュー：

    <clay:dropdown-actions
    items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    />

![図5：Clay taglibを使用してアクションメニューを作成することもできます。](../../../images/clay-taglib-dropdown-actions.png)

アクションメニューは、ユーザーへのヘルプテキストを表示することもできます。

    <clay:dropdown-actions
    buttonLabel="More"
    buttonStyle="secondary"
    caption="Showing 4 of 32 Options"
    helpText="You can customize this menu or see all you have by pressing \"more\"."
    items="<%= dropdownsDisplayContext.getDefaultDropdownItems() %>"
    />

![図6：アクションメニューにヘルプテキストを指定できます。](../../../images/clay-taglib-dropdown-actions-help.png)

Clay taglibを使用すると、アプリにドロップダウンメニューとアクションメニューを簡単に追加できます。

## 関連トピック

[Clay Form Elements](/docs/7-1/tutorials/-/knowledge_base/t/clay-form-elements)

[Clay Navigation Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-navigation-bars)

[Clay Progress Bars](/docs/7-1/tutorials/-/knowledge_base/t/clay-progress-bars)
