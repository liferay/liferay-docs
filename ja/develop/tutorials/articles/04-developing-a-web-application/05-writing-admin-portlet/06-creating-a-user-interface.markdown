---
header-id: creating-a-user-interface
---

# ユーザーインターフェイスの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Guestbook Adminアプリの作成</p><p>手順5/5</p>
</div>

Guestbook Adminポートレットのユーザーインターフェイスを作成します。 ポートレットのデフォルトビューには、新しいゲストブックを追加するためのボタンがあります。 また、既存のゲストブックも表示する必要があります。

各ゲストブックの名前は、[アクション]ボタンとともに表示されます。 [アクション]ボタンをクリックすると、ゲストブックの編集、アクセス許可の構成、または削除のオプションが表示されます。

## ゲストブック管理ポートレットのユーザーインターフェイス用のJSPの作成

ゲストブック管理ポートレットのユーザーインターフェースは、デフォルトビュー、アクションボタン、ゲストブックを追加または編集するためのフォームの3つのJSPで構成されています。

最初にデフォルトビューを作成します。

1.  Guestbook AdminポートレットのJSP用のフォルダーを作成します。 `src / main / resources / META-INF / resources`、 `guestbookadminportlet`というフォルダーを作成します。

2.  このフォルダーに `view.jsp` というファイルを作成し、次のコードを入力します。
   
        <%@include file="../init.jsp"%>
       
        <liferay-ui:search-container
            total="<%= GuestbookLocalServiceUtil.getGuestbooksCount(scopeGroupId) %>">
            <liferay-ui:search-container-results
                results="<%= GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId, 
                    searchContainer.getStart(), searchContainer.getEnd()) %>" />
       
            <liferay-ui:search-container-row
                className="com.liferay.docs.guestbook.model.Guestbook" modelVar="guestbook">
       
                <liferay-ui:search-container-column-text property="name" />
       
                <liferay-ui:search-container-column-jsp
                    align="right" 
                    path="/guestbookadminportlet/guestbook_actions.jsp" />
       
            </liferay-ui:search-container-row>
       
            <liferay-ui:search-iterator />
        </liferay-ui:search-container>
       
        <aui:button-row cssClass="guestbook-admin-buttons">
            <portlet:renderURL var="addGuestbookURL">
                <portlet:param name="mvcPath"
                    value="/guestbookadminportlet/edit_guestbook.jsp" />
                <portlet:param name="redirect" value="<%= "currentURL" %>" />
            </portlet:renderURL>
       
            <aui:button onClick="<%= addGuestbookURL.toString() %>"
                value="Add Guestbook" />
        </aui:button-row>

    1つ目は、インポートにアクセスするための標準の `init.jsp` インクルードです。

    次は、新しいゲストブックを追加するためのボタンが&lt;aui:button-row cssClass="guestbook-admin-buttons"&gt;つあるボタン行です： `<aui:button-row cssClass="guestbook-admin-buttons">`。 `cssClass` 属性を使用すると、追加のスタイリング用のカスタムCSSクラスを指定できます。 `<portlet:renderURL>` タグは、 `edit_guestbook.jsp`を指すURLを構築します。 このJSPはまだ作成していませんが、新しいゲストブックの追加と既存のゲストブックの編集に使用します。

    最後に、Liferay検索コンテナーを使用して、ゲストブックのリストを表示します。 検索コンテナを定義する3つのサブタグ：

      - `<liferay-ui:search-container-results>`
      - `<liferay-ui:search-container-row>`
      - `<liferay-ui:search-iterator>`

    `<liferay-ui:search-container-results>` タグの `結果` 属性は、サービスコールを使用してスコープ内のゲストブックを取得します。 `合計` 属性は、ゲストブックの数を取得するには、別のサービスコールを使用しています。

    `<liferay-ui:search-container-row>` タグは、行に含まれる内容を定義します。 この場合、 `className` 属性は `com.liferay.docs.guestbook.model.Guestbook`定義します。 `modelVar` 属性は、現在反復されているゲストブックの変数として `ゲストブック` を定義します。 検索コンテナ行では、2つの列が定義されています。 `<liferay-ui:search-container-column-text property="name" />` タグは、最初の列を指定します。 このタグはテキストを表示します。 `property = "name"` 属性は、表示されるテキストが現在のゲストブックオブジェクトの `name` 属性であることを指定します。 タグ `<liferay-ui:search-container-column-jsp` `path="/guestbookadminportlet/guestbook_actions.jsp" align="right" />` は、2番目（および最後）の列を指定します。 このタグには、検索コンテナ列内の別のJSPファイルが含まれます。 `path` 属性は、表示されるJSPファイルへのパスを指定します： `guestbook_actions.jsp`。

    最後に、 `<liferay-ui:search-iterator />` タグが繰り返し処理され、ゲストブックのリストが表示されます。 Liferayの検索コンテナを使用すると、Guestbook Adminポートレットはネイティブの@product@ポートレットのように見えます。 また、ビルトインページネーションも提供するため、ポートレットは1つのサイトに多数のゲストブックを自動的に表示できます。

    次のステップは、ゲストブックごとに可能なアクションのリストを表示する `guestbook_actions.jsp` ファイルを追加することです。

3.  プロジェクトの `/ guestbookadminportlet` フォルダーに `guestbook_actions.jsp` という新しいファイルを作成します。 このコードを貼り付けます：

        <%@include file="../init.jsp"%>

        <％ 文字列mvcPath = ParamUtil.getString（request、 "mvcPath"）;
   
          ResultRow row = (ResultRow) request
                          .getAttribute("SEARCH_CONTAINER_RESULT_ROW");
       
          Guestbook guestbook = (Guestbook) row.getObject();
        ％>

        <liferay-ui:icon-menu>
   
          <portlet:renderURL var="editURL">
              <portlet:param name="guestbookId"
                  value="<%=String.valueOf(guestbook.getGuestbookId()) %>" />
              <portlet:param name="mvcPath"
                  value="/guestbookadminportlet/edit_guestbook.jsp" />
          </portlet:renderURL>
       
          <liferay-ui:icon image="edit" message="Edit"
                  url="<%=editURL.toString() %>" />
       
          <portlet:actionURL name="deleteGuestbook" var="deleteURL">
                  <portlet:param name="guestbookId"
                      value="<%= String.valueOf(guestbook.getGuestbookId()) %>" />
          </portlet:actionURL>
       
          <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

        </liferay-ui:icon-menu>

    このJSPは、ユーザーがゲストブックで実行できるアクション（編集または削除）を示すポップアップアクションメニューで構成されています。 最初に、すべてのJSPインポートが含まれているため、 `init.jsp` が含まれています。 `guestbook_actions.jsp` がすべての検索コンテナー行に含まれているため、現在の反復でゲストブックを取得します。 スクリプトレットはそのゲストブックを取得して、そのIDをメニュータグに提供できるようにします。

    `<liferay-ui:icon-menu>` タグは `guestbook_actions.jsp`支配します。 これはメニュー項目のコンテナであり、現在は2つしかありません（後で追加します）。 [編集]メニュー項目には、[編集]アイコンとメッセージ *編集*]が表示されます。

        <liferay-ui:icon image="edit" message="Edit" url="<%=editURL.toString() %>" />

    `editURL` 変数は、 `guestパラメーターが <code>guestbookId` および `mvcPath`2つのパラメーターを持つ`<portlet:renderURL var="editURL">` タグから取得されます。 `guestbookId` パラメーターは、編集するゲストブック（選択した検索コンテナーの結果行からのもの）を指定し、 `mvcPath` パラメーターは、ゲストブックの編集フォームのパスを指定します。

    [削除]メニュー項目には、削除アイコンとデフォルトメッセージ *削除*表示されます。

        <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />

    `edit_guestbook.jsp`リンクするレンダリングURLである `editURL`とは異なり、 `deleteURL` は、ポートレットの `deleteGuestbook` アクションを呼び出すアクションURLです。 タグ `<portlet:actionURL name="deleteGuestbook" var="deleteURL">` はこのアクションURLを作成します。このアクションURLは、削除するゲストブックの `guestbookId` という1つのパラメーターのみを取ります。

    ここで、作成するJSPファイルがもう1つ残っています `edit_guestbook.jsp` には、新しいゲストブックを追加し、既存のゲストブックを編集するためのフォームが含まれています。

4.  プロジェクトの `/ guestbookadminportlet` ディレクトリに `edit_guestbook.jsp` という新しいファイルを作成します。 次に、次のコードを追加します。

        <%@include file = "../init.jsp" %>

        <％ long guestbookId = ParamUtil.getLong（request、 "guestbookId"）;
   
              Guestbook guestbook = null;
       
              if (guestbookId > 0) {
                      guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
              }
        ％>

        <portlet:renderURL var="viewURL"> <portlet:param name="mvcPath" value="/guestbookadminportlet/view.jsp" /> </portlet:renderURL>

        <portlet:actionURL name='<%= guestbook == null ? "addGuestbook" : "updateGuestbook" %>' var="editGuestbookURL" />

        <aui:form action="<%= editGuestbookURL %>" name="fm">
   
              <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />
       
              <aui:input type="hidden" name="guestbookId"
                  value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' />
       
              <aui:fieldset>
                   <aui:input name="name" />
              </aui:fieldset>
       
              <aui:button-row>
                   <aui:button type="submit" />
                   <aui:button onClick="<%= viewURL %>" type="cancel"  />
              </aui:button-row>
        </aui:form>

    `init.jsp` インポートの後、 `null` ゲストブック変数を宣言します。 要求に `guestbookId` パラメーターがある場合、既存のゲストブックを編集していることがわかり、 `guestbookId` を使用して、サービス呼び出しを介して対応するゲストブックを取得します。 それ以外の場合は、新しいゲストブックを追加していることがわかります。

    次は、Guestbook Adminポートレットのデフォルトビューを指すビューURLです。 このURLは、ユーザーが[ゲストブックの追加]または[ゲストブックの編集]フォームで *キャンセル* をクリックすると呼び出されます。 その後、あなたはアクションURLを作成することを呼び出すのいずれかゲストブック管理ポートレットの `addGuestbook` の方法またはその `updateGuestbook` かどうかに依存する方法、 `ゲストブック` 変数がnullです。

    ゲストブックが編集されている場合、現在のゲストブックの名前がフォームの名前フィールドに表示されます。 以下のタグを使用して、AlloyUIフォームで使用できるゲストブックのモデルを定義します。

        <aui:model-context bean="<%= guestbook %>" model="<%= Guestbook.class %>" />

    フォーム自体は、次のタグで作成されます。

        <aui:form action="<%= editGuestbookURL %>" name="<portlet:namespace />fm">

    フォームが送信されると、 `editGuestbookURL` が呼び出され、上記のように、Guestbook Adminポートレットの `addGuestbook` または `updateGuestbook` メソッドが呼び出されます。

    `ゲストブック` は、送信できるようにフォームに表示される必要があります。 ただし、ユーザーは表示する必要はありません。 したがって、 `type = "hidden"`を指定します`</p>

<p spaces-before="8"><aui:input type="hidden" name="guestbookId"
           value='<%= guestbook == null ? "" : guestbook.getGuestbookId() %>' /></p>

<p spaces-before="4">もちろん、名前は非表示にならないようにユーザーが編集できる必要があります。</p>

<p spaces-before="4">フォームの最後の項目は、2つのボタンがあるボタン行です。 <em x-id="3">Submit</em> ボタンはフォームを送信し、 <code>editGuestbookURL` を呼び出します。2editGuestbookURL </code>は、 `addGuestbook` または `updateGuestbook` メソッドを呼び出します。 *Cancel* ボタンは、デフォルトビューを表示する `viewURL` を呼び出します。

優れた\！ これで、Guestbook AdminポートレットのUIの作成が完了しました。 これで、次の図と一致するはずです。

![図1：ゲストブック管理ポートレットを使用すると、管理者はゲストブックを追加または編集したり、アクセス許可を構成したり、ゲストブックを削除したりできます。](../../../images/admin-app-start.png)

Guestbook Adminポートレットをテストしてください！ ゲストブックを追加、編集、削除してみてください。

これで、Guestbookアプリケーションのすべての主要機能が機能します。 ただし、不足している機能はまだ多くあります。 たとえば、エラーが発生した場合、ユーザーはそれを見ることはありません。これまでに作成されたすべてのコードは、ログにメッセージを出力するだけです。 次に、これらのエラーをユーザーに表示する方法を学習します。
