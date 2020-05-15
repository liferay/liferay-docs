---
header-id: clay-form-elements
---

# Clayフォーム要素

[TOC levels=1-4]

Liferay Clayタグライブラリには、フォーム要素を作成するためのタグがいくつか用意されています。
各タグの例を以下に示します。

## チェックボックス

チェックボックスは、trueまたはfalseの入力をユーザに提供します。

    <clay:checkbox
    checked="<%= true %>"
    hideLabel="<%= true %>"
    label="My Input"
    name="name"
    />

属性：

**checked：**チェックボックスがオンになっているかどうか

**disabled：**チェックボックスが有効になっているかどうか

**hideLabel：**チェックボックスラベルを表示するかどうか

**indeterminate：**複数選択のチェックボックス変数

**label：**チェックボックスのラベル

**name：**チェックボックスの名前

![図1：Clay taglibはチェックボックスを提供します。](../../../images/clay-taglib-form-checkbox.png)

## ラジオ

ラジオボタンを使用すると、ユーザーはフォームのオプションセットから1つの選択肢を選択できます。

    <clay:radio
    checked="<%= true %>"
    hideLabel="<%= true %>"
    label="My Input"
    name="name"
    />

属性：

**checked：**ラジオボタンがオンになっているかどうか

**hideLabel：**ラジオボタンのラベルを表示するかどうか

**disabled：**ラジオボタンが有効になっているかどうか

**label：**ラジオボタンのラベル

**name：**ラジオボタンの名前

![図2：Clay taglibはラジオボタンを提供します。](../../../images/clay-taglib-form-radio-button.png)

## セレクター

セレクターは、選択するオプションのセットを含む選択ボックスをユーザーに提供します。

以下のJavaスクリプレットは、セレクターに8つのダミーオプションを作成します。

    <%
    List<Map<String, Object>> options = new ArrayList<>();
    
    for (int i = 0; i < 8; i++) {
    Map<String, Object> option = new HashMap<>();
    
    option.put("label", "Sample " + i);
    option.put("value", i);
    
    options.add(option);
    }
    %>
    
    <clay:select
    label="Regular Select Element"
    name="name"
    options="<%= options %>"
    />

![図3：Clay taglibは選択ボックスを提供します。](../../../images/clay-taglib-form-selector.png)

ユーザーが複数のオプションを一度に選択できるようにするには、`multiple`属性を`true`に設定します。

    <clay:select
    label="Multiple Select Element"
    multiple="<%= true %>"
    name="name"
    options="<%= options %>"
    />

![図4：ユーザーが選択メニューから複数のオプションを選択できるようにすることができます。](../../../images/clay-taglib-form-selector-multiple.png)

属性：

**disabled：**セレクタ―が有効になっているかどうか
**label：**セレクタ―のラベル
**multiple：**複数のオプションを選択できるかどうか
**name：**セレクターの名前

これで、Clay taglibを使用して一般的なフォーム要素をアプリに追加する方法がわかりました。

## 関連トピック

[Clay Buttons](/docs/7-1/tutorials/-/knowledge_base/t/clay-buttons)

[Clay Icons](/docs/7-1/tutorials/-/knowledge_base/t/clay-icons)

[Clay Labels and Links](/docs/7-1/tutorials/-/knowledge_base/t/clay-labels-and-links)
