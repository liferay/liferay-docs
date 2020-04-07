---
header-id: implementing-the-view-types
---

# ビュータイプの実装

[TOC levels=1-4]

管理ツールバーには、アプリの検索コンテナー結果用に3つの定義済みビュータイプがあります。 各スタイルは、わずかに異なるルックアンドフィールを提供します。 アプリでこれらのビュータイプを提供するには、検索結果の列を更新する必要があります。 提供するビュータイプを定義することから始めます。

## ビュータイプの定義

管理ツールバーには3つのビュータイプがあります。

  - **カード：** は、水平または垂直のカードに検索結果列を表示します

  - **リスト：** は、検索結果列の要約された詳細とともに詳細な説明を表示します

  - **テーブル：** デフォルトビュー。左から右に検索結果の列をリストします。

以下の手順に従って、管理ツールバーのビュータイプを定義します。

1.  `ViewTypeItemList` ユーティリティクラスをインポートして、アクションアイテムモデルを作成します。
   
        <%@ page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.JSPViewTypeItemList" %>

2.  `frontend.taglib.clay` および `frontend.taglib.soy` モジュールの依存関係をアプリの `build.gradle` ファイルに追加します。
   
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.soy", 
        version: "1.0.10"
       
        compileOnly group: "com.liferay", name: "com.liferay.frontend.taglib.clay", 
        version: "1.0.0"

3.  アプリのメインビューで、参照用に `displayStyle` を取得します。 各表示タイプは表示スタイルに対応しています。 これは、選択されたビュータイプに対して表示する適切なコンテンツ構成を決定するために使用されます。
   
        <%
        String displayStyle = ParamUtil.getString(request, "displayStyle");
        %>

4.  管理ツールバーをアプリのメインビューに追加し、以下に示すように表示ボタンを構成します。 この例では3つのビュータイプをすべて実装していますが、必要なビュータイプは1つだけです。 デフォルトまたはアクティブなビュータイプは、 `viewTypeItem.setActive（true）` をビュータイプに追加することによって設定されます。
   
        <clay:management-toolbar
            disabled=<%= assetTagsDisplayContext.isDisabledTagsManagementBar() %>
            namespace="<%= renderResponse.getNamespace() %>"
            searchContainerId="assetTags"
            selectable="<%= true %>"
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
        />

    `viewTypes`：利用可能なビュータイプ

    `portletURL`：ビュータイプパラメーターが含まれる現在のURL。

5.  ビュータイプを確認する条件ブロックを作成します。 ビュータイプが1つしかない場合は、この手順をスキップできます。
   
        <c:choose>
            <%-- view type configuration goes here --%>
        </c:choose>

ビュータイプが定義されたので、構成できます。

## カードビューの実装

カードビューには、画像の名前、ワークフローステータス、要約説明などのコンテンツの詳細とともに、画像、ユーザープロファイル、またはコンテンツの種類を表すアイコンが付いた縦型または横型のカードにエントリの情報が表示されます。

各カードの例と使用例については、 [Liferay Frontend Cards](/docs/7-1/tutorials/-/knowledge_base/t/liferay-frontend-cards) チュートリアルを参照してください。

![図1：管理ツールバーのカードビューには、コンテンツの説明とステータスの概要が表示されます。](../../../../images/clay-taglib-management-toolbar-view-type-card.png)

以下の手順に従って、カードビューを作成します。

1.  `<c:choose>` 条件ブロック内に、アイコンの表示スタイル（カードビュータイプ）の条件を追加します。
   
        <c:when test='<%= Objects.equals(displayStyle, "icon") %>'>
            <%-- card view type configuration goes here --%>
        </c:when>

2.  適切なJavaスクリプレットを追加して、カードビューがさまざまなデバイスに反応するようにします。

    縦型カードには次のパターンを使用します。
   
        <%
        row.setCssClass("col-md-2 col-sm-4 col-xs-6");
        %>

    水平カードの場合、次のパターンを使用します。
   
        <%
        row.setCssClass("col-md-3 col-sm-4 col-xs-12");
        %>

3.  カードを含む検索コンテナ列のテキストを追加します。 カードには、エントリのアクション（該当する場合）、画像、アイコン、またはユーザープロファイル、エントリのタイトルを含める必要があります。 構成例を以下に示します。
   
        <liferay-frontend:icon-vertical-card
          actionJsp='<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
          "/image_gallery_display/image_action.jsp" : StringPool.BLANK %>'
          actionJspServletContext="<%= application %>"
          cssClass="entry-display-style"
          icon="documents-and-media"
          resultRow="<%= row %>"
          title="<%= dlPortletInstanceSettingsHelper.isShowActions() ? 
          fileEntry.getTitle() : StringPool.BLANK %>"
        />

## リストビューの実装

リストビューには、コンテンツタイプの小さなアイコンとその名前とともに、エントリの完全な説明が表示されます。

![図2：管理ツールバーのリストビューには、コンテンツの完全な説明が表示されます。](../../../../images/clay-taglib-management-toolbar-view-type-list.png)

`<c:choose>` 条件ブロック内に、説明的な表示スタイル（リストビュータイプ）の条件を追加します。

    <c:when test='<%= Objects.equals(displayStyle, "descriptive") %>'>
        <%-- list view type configuration goes here --%>
    </c:when>

リストビューには、次の表に示す内容の3つの列が必要です。

<table spaces-before="0" line-breaks-before="2">
  <tr>
    <th>
      カラム
    </th>
    
    <th>
      コンテンツオプション
    </th>
    
    <th>
      例
    </th>
  </tr>
  
  <tr>
    <td>
      1
    </td>
    
    <td>
      アイコン
    </td>
    
    <td>
      \<liferay-ui:search-container-column-icon> </tr> 
      
      <tr>
        <td>
        </td>
        
        <td>
          画像
        </td>
        
        <td>
          \<liferay-ui:search-container-column-image> </tr> 
          
          <tr>
            <td>
            </td>
            
            <td>
              ユーザーの肖像
            </td>
            
            <td>
              \<liferay-ui:search-container-column-text\><br/>\<liferay-ui:user-portrait>\</liferay-ui:search-container-column-text\>
            </td>
          </tr>
          
          <tr>
            <td>
              2
            </td>
            
            <td>
              説明
            </td>
            
            <td>
              \<liferay-ui:search-container-column-text <br>colspan = "\ <％= 2％\>" <br/>\><br/>\<h5\\>\ <％= userGroup.getName（）％\> \</h5\> <br/>\
<h6 class="text-default">
  <br/>\<span\>\ <％= userGroup.getDescription（）％\> \</span\> <br/>\</h6\> <br/>\
<h6 class="text-default">
  <br/>\<span\> <br/>\<liferay-ui:message arguments="\<%= usersCount%\>" key="x-users">\</span\> <br/>\</h6\> <br/>\</liferay-ui:search-container-column-text\></td> </tr> 
  
  <tr>
    <td>
      3
    </td>
    
    <td>
      行動
    </td>
    
    <td>
      <liferay-ui:search-container-column-jsp<br /> path = "/ edit \ _team \ _assignments \ _user \ _groups \ _action.jsp"<br/>/ \>
    </td>
  </tr></tbody> </table>

<h2 spaces-before="0">
  テーブルビューの実装
</h2>

<p spaces-before="0">
  テーブルビューには、検索コンテナの列が左から右に一覧表示されます。
</p>

<p spaces-before="0">
  <img src="../../../../images/clay-taglib-management-toolbar-view-type-table.png" alt="図3：管理ツールバーのテーブルビューには、コンテンツの情報が個々の列に一覧表示されます。" />
</p>

<p spaces-before="0">
  <code>&lt;c:choose&gt;</code> 条件ブロック内に、リスト表示スタイル（テーブルビュータイプ）の条件を追加します。
</p>

<pre><code>&lt;c:when test='&lt;%= Objects.equals(displayStyle, "list") %&gt;'&gt;
    &lt;%-- table view type configuration goes here --%&gt;
&lt;/c:when&gt;
</code></pre>

<p spaces-before="0">
  列には、少なくとも次の表に示す情報が含まれている必要があります。
</p>

<table spaces-before="0" line-breaks-before="2">
  <tr>
    <th>
      カラム
    </th>
    
    <th>
      コンテンツオプション
    </th>
    
    <th>
      例
    </th>
  </tr>
  
  <tr>
    <td>
      1
    </td>
    
    <td>
      名
    </td>
    
    <td>
      \<liferay-ui:search-container-column-text <br>cssClass = "content-column name-column title-column" <br/>name = "name" <br/>truncate = "\ <％= true％\>" <br/>value = "\ <％= rule.getName（locale）％\>" <br/>/ \>
    </td>
  </tr>
  
  <tr>
    <td>
      2
    </td>
    
    <td>
      説明
    </td>
    
    <td>
      \<liferay-ui:search-container-column-text <br>cssClass = "content-column description-column" <br/>name = "description" <br/>truncate = "\ <％= true％\>" <br/>value = "\ <％= rule.getDescription（locale）％\>" <br/>/ \ >
    </td>
  </tr>
  
  <tr>
    <td>
      3
    </td>
    
    <td>
      日付を作成します
    </td>
    
    <td>
      \<liferay-ui:search-container-column-date <br>cssClass = "create-date-column text-column" <br/>name = "create-date" <br/>property = "createDate" <br/>/ \>
    </td>
  </tr>
  
  <tr>
    <td>
      4
    </td>
    
    <td>
      行動
    </td>
    
    <td>
      \<liferay-ui:search-container-column-jsp <br>cssClass = "entry-action-column" <br/>path = "/ rule \ _actions.jsp" <br/>/ \>
    </td>
  </tr>
</table>

<h2 spaces-before="0">
  検索イテレーターの更新
</h2>

<p spaces-before="0">
  ビュータイプの表示スタイルを定義したら、検索イテレータを更新して、選択したビュータイプを表示する必要があります。 管理ツールバーに表示タイプが1つしかない場合は、 <code>displayStyle</code> 属性を定義したスタイルに設定できます。それ以外の場合は、以下のパターンに従います。
</p>

<pre><code>&lt;liferay-ui:search-iterator
    displayStyle="&lt;%= displayStyle %&gt;"
    markupView="lexicon"
    searchContainer="&lt;%= searchContainer %&gt;"
/&gt;
</code></pre>

<p spaces-before="0">
  <code>displayStyle</code>の値は、 <a href="/docs/7-1/tutorials/-/knowledge_base/t/implementing-the-view-types#defining-the-view-types">現在のビュータイプ</a>設定されます。
</p>

<h2 spaces-before="0">
  関連トピック
</h2>

<p spaces-before="0">
  <a href="/docs/7-1/tutorials/-/knowledge_base/t/clay-management-toolbar">Clay Management Toolbar Taglibの構成</a>
</p>

<p spaces-before="0">
  <a href="/docs/7-1/tutorials/-/knowledge_base/t/filtering-and-sorting-items-with-the-management-toolbar">管理ツールバーを使用したアイテムのフィルタリングと並べ替え</a>
</p>
