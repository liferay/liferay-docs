---
header-id: selecting-entities-using-the-item-selector
---

# アイテムセレクターを使用したエンティティの選択

[TOC levels=1-4]

アイテムセレクタを使用すると、ユーザーは画像、ビデオ、ドキュメント、サイトなどのエンティティを選択できます。 アプリでアイテムセレクターを使用して、ユーザーがそのようなエンティティを選択できるようにします。 このチュートリアルでは、次の手順でこれを行う方法を示します。

1.  **アイテムセレクタの基準を決定します** 最初に、アイテムセレクタでユーザーが選択できるエンティティを定義する必要があります。

2.  **基準のアイテムセレクターの取得：** 基準が画像などの場合、この手順では、画像を選択できるアイテムセレクターを取得します。

3.  **アイテムセレクターダイアログを使用します** UIにアイテムセレクターを表示します。

![図1：アイテムセレクターにより、エンティティの選択が簡単になります。](../../../images/item-selector-dialog-01.png)

## アイテム基準の決定

最初のステップは、アイテムセレクターから選択するエンティティタイプと、それらから期待するデータを決定することです。 どの種類のエンティティを選択しますか？ ユーザー、画像、ビデオなどを選択しますか？

必要なエンティティがわかったら、アイテムセレクタでそれらを表すために *基準* クラスが必要です。 Criterionクラスは、 [`ItemSelectorCriterion` インターフェイス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)実装する必要があります。 [Item Selector Criterion and Return Types](/docs/7-1/reference/-/knowledge_base/r/item-selector-criterion-and-return-types) リファレンスには、Liferayのアプリとアプリスイートが提供する基準クラスがリストされています。

エンティティに基準クラスがない場合は、独自の [`ItemSelectorCriterion` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)作成できます。

次に、ユーザーがエンティティを選択したときにエンティティに期待する情報の種類（戻り値の種類）を決定します。 URLを期待していますか？ Universally Unique Identifier（UUID）？ 主キー？ 各戻り値の型は、 [`ItemSelectorReturnType` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)実装によって表される必要があります。 [Item Selector Criterion and Return Types](/docs/7-1/reference/-/knowledge_base/r/item-selector-criterion-and-return-types) リファレンスには、@product@のアプリとアプリスイートが提供する戻り値の型クラスもリストされています。

ニーズを満たす戻り値型クラスがない場合は、独自の [`ItemSelectorReturnType` クラス](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)実装できます。

| **注**：各基準には、少なくとも1 `ItemSelectorReturnType` （return | type）が関連付けられている必要があります。

たとえば、ユーザーに画像の選択を許可し、画像のURLを返したい場合、 `ImageItemSelectorCriterion` 基準クラスと `URLItemSelectorReturnType` 戻り値型を使用できます。

基準と戻り値のタイプは、アイテムセレクターの *基準*と総称されます。 アイテムセレクタはこれを使用して、表示する選択ビュー（アイテムのタブ）を決定します。

条件を定義したら、アイテムセレクタを取得して使用できます。

## 基準の項目セレクターの取得

条件でアイテムセレクタを使用するには、そのアイテムセレクタのURLを取得する必要があります。 UIは、アイテムセレクターダイアログを開くために必要です。 このURLを取得するには、 `ItemSelector` 参照を取得し、次のパラメーターで [`getItemSelectorURL` メソッド](@app-ref@/collaboration/latest/javadocs/com/liferay/item/selector/ItemSelector.html#getItemSelectorURL-com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory-java.lang.String-com.liferay.item.selector.ItemSelectorCriterion...-) を呼び出す必要があります。

  - `RequestBackedPortletURLFactory`：ポートレットURLを作成するファクトリー。
  - `ItemSelectedEventName`：要素が選択されたときにアイテムセレクターがトリガーする一意の任意のJavaScriptイベント名。
  - `ItemSelectorCriterion`：Item Selectorで使用可能にする要素のタイプを指定する基準（または基準オブジェクトの配列）。

次のコードは、画像の基準で構成されたアイテムセレクターへのURLの取得を示しています。

1.  宣言型サービスを使用して、 `ItemSelector` OSGiサービスコンポーネントを取得します。
   
        import com.liferay.item.selector.ItemSelector;
        import org.osgi.service.component.annotations.Reference;
       
        ...
       
        @Reference
        private ItemSelector _itemSelector

    コンポーネントの注釈は、 [`org.osgi.service.component.annotations` モジュール](http://mvnrepository.com/artifact/org.osgi/org.osgi.service.component.annotations)利用できます。

2.  URLの作成に使用するファクトリを作成します。 これを行うには、現在のリクエストオブジェクトで `RequestBackedPortletURLFactoryUtil.create` メソッドを呼び出します。 リクエストは `HttpServletRequest` または `PortletRequest`ます。
   
        RequestBackedPortletURLFactory requestBackedPortletURLFactory =
            RequestBackedPortletURLFactoryUtil.create(request);

3.  画像エンティティに期待される戻り値のタイプのリストを作成します。 戻りタイプリストは、URL戻りタイプ `URLItemSelectorReturnType`構成されます。
   
        List<ItemSelectorReturnType> desiredItemSelectorReturnTypes =
            new ArrayList<>();
        desiredItemSelectorReturnTypes.add(new URLItemSelectorReturnType());

4.  画像の基準オブジェクトを作成します（`ImageItemSelectorCriterion`）：
   
        ImageItemSelectorCriterion imageItemSelectorCriterion =
            new ImageItemSelectorCriterion();

5.  基準の `setDesiredItemSelectorReturnTypes` メソッドを使用して、ステップ3からの戻り値タイプリストを基準に設定します。
   
        imageItemSelectorCriterion.setDesiredItemSelectorReturnTypes(
            desiredItemSelectorReturnTypes);

6.  Item Selectorの `getItemSelectorURL` メソッドを呼び出して、基準に基づいてItem Selector URLを取得します。 このメソッドには、URLファクトリ、任意のイベント名、および一連の基準（この場合は1つ）が必要です。
   
        PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
            requestBackedPortletURLFactory, "sampleTestSelectItem",
            imageItemSelectorCriterion);

アイテムセレクタのURLを取得する際に留意すべき点がいくつかあります。

  - URLオブジェクトの `toString` メソッドを呼び出して、その値を取得できます。

  - 任意の数の基準を使用するようにアイテムセレクターを構成できます。 基準では、任意の数の戻り型を使用できます。

  - アイテムセレクタの条件の順序により、選択ビューの順序が決まります。 たとえば、アイテムセレクターに `ImageItemSelectorCriterion` を渡し、その後に `VideoItemSelectorCriterion`渡すと、アイテムセレクターは最初に画像選択ビューを表示します。

  - 戻り型の順序も重要です。 ビューは、各基準の戻り値型リストからサポートする最初の戻り値型を使用します。

アイテムセレクターへのURLを取得したので、UIでそのアイテムセレクターの使用を開始できます。

## アイテムセレクターダイアログの使用

UIでアイテムセレクターを開くには、JavaScriptコンポーネント `LiferayItemSelectorDialog` [AlloyUIの](http://alloyui.com/) `liferay-item-selector-dialog` モジュール）を使用する必要があります。 コンポーネントは、アイテムセレクタURLに指定したアイテム選択イベントをリッスンします。 イベントは、選択された要素の情報を戻り値の型に応じて返します。

JSPで[アイテムセレクタ]ダイアログを使用する手順は次のとおりです。

1.  AUIタグライブラリを宣言します。
   
        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

2.  [アイテムセレクタ]ダイアログを開くために使用するUI要素を定義します。 たとえば、ID `chooseImage`*Choose* ボタンを作成します。
   
        <aui:button name="chooseImage" value="Choose" />

3.  アイテムセレクターのURLを取得します。
   
        <%
        String itemSelectorURL = GetterUtil.getString(request.getAttribute("itemSelectorURL"));
        %>

4.  `<aui:script>` タグを追加し、 `liferay-item-selector-dialog` モジュールを使用するように設定します。
   
        <aui:script use="liferay-item-selector-dialog">
       
        </aui:script>

5.  `<aui:script>` タグ内で、ステップ2で作成したUI要素にイベントハンドラーをアタッチします。 たとえば、クリックイベントと関数を *Choose* ボタンに添付します。
   
        <aui:script use="liferay-item-selector-dialog">
       
            $('#<portlet:namespace />chooseImage').on(
            'click',
              function(event) {
                <!-- function logic goes here -->
              }
            );
       
        </aui:script>

    関数内で、 `LiferayItemSelectorDialog` AlloyUIコンポーネントの新しいインスタンスを作成し、アイテムセレクターを使用するように構成する必要があります。 次の手順でこれを説明します。

6.  ここで、関数ロジックを作成する必要があります。 最初に、Liferayアイテムセレクターダイアログの新しいインスタンスを作成します。
   
        var itemSelectorDialog = new A.LiferayItemSelectorDialog(  
            {
                ...
            }
        );

7.  `LiferayItemSelectorDialog` コンストラクターの中括弧内で、最初に `eventName` 属性を設定します。 これにより、ダイアログはアイテム選択イベントをリッスンします。 イベント名は、Javaコード（アイテムセレクターURLを取得するコード）で指定したアイテムセレクターのイベント名です。
   
        eventName: 'ItemSelectedEventName',

8.  `eventName` 設定の直後に、 `on` 属性を設定して、選択したアイテムの変更を操作する機能を実装します。 たとえば、この関数は新しく選択されたアイテムの変数を設定します。 解析可能な情報は、設定された戻り値のタイプによって異なります。 コメントが示すように、選択した要素を使用するためのロジックを追加する必要があります。
   
        on: {
                selectedItemChange: function(event) {
                    var selectedItem = event.newVal;
       
                    if (selectedItem) {
                        var itemValue = JSON.parse(
                        selectedItem.value
                        );
                        itemSrc = itemValue.url;
        
                        <!-- use item as needed -->
                    }
                }
        },

9.  `on` 設定の直後に、ダイアログのタイトルに `タイトル` 属性を設定します。
   
        title: '<liferay-ui:message key="select-image" />',

10. `タイトル` 設定の直後に、 `url` 属性を以前に取得したアイテムセレクターURLに設定します。 これにより、 `LiferayItemSelectorDialog` コンストラクター内の属性設定が終了します。
    
         url: '<%= itemSelectorURL.toString() %>'

11. 手順4の関数のロジックを終了するには、 `open` メソッドを呼び出して、アイテムセレクターダイアログを開きます。
    
         itemSelectorDialog.open();

これらの手順の完全なサンプルコードを次に示します。

    <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>
    
    <aui:button name="chooseImage" value="Choose" />
    
    <%
    String itemSelectorURL = GetterUtil.getString(request.getAttribute("itemSelectorURL"));
    %>
    
    <aui:script use="liferay-item-selector-dialog">
    
        $('#<portlet:namespace />chooseImage').on(
            'click', 
            function(event) {
                var itemSelectorDialog = new A.LiferayItemSelectorDialog(  
                    {
                        eventName: 'ItemSelectedEventName',
                        on: {
                                selectedItemChange: function(event) {
                                    var selectedItem = event.newVal;
    
                                    if (selectedItem) {
                                        var itemValue = JSON.parse(
                                        selectedItem.value
                                        );
                                        itemSrc = itemValue.url;
    
                                        <!-- use item as needed -->
                                    }
                                }
                        },
                        title: '<liferay-ui:message key="select-image" />',
                        url: '<%= itemSelectorURL.toString() %>'
                    }
                );
                itemSelectorDialog.open();
            }
        );
    </aui:script>

ユーザーが[ *Choose* ]ボタンをクリックすると、新しいダイアログが開き、設定された基準と戻り値の型をサポートするビューでアイテムセレクターがレンダリングされます。

すばらしいです\！ これで、アイテムセレクターを使用してエンティティを選択する方法がわかりました。 Item Selector APIを使用すると、アプリのユーザーに選択の力を与えることができます\！

## 関連記事

[Item Selector APIのコンポーネントについて](/docs/7-1/tutorials/-/knowledge_base/t/understanding-the-item-selector-apis-components)

[カスタムアイテムセレクタービューの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-views)

[カスタムアイテムセレクタエンティティの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-custom-item-selector-entities)

[フロントエンドTaglib](/docs/7-1/tutorials/-/knowledge_base/t/front-end-taglibs)
