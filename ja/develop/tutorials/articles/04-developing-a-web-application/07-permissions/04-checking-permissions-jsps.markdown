---
header-id: checking-for-permission-in-jsps
---

# JSPでの許可の確認

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">許可の実装</p><p>手順4/4</p>
</div>

ユーザーインターフェイスコンポーネントをアクセス許可チェックで簡単にラップする方法は既に説明しました。 このステップでは、残りを実装します。

## UIで権限を確認する

アプリケーションの3つの領域へのアクセスを制限したいことを思い出してください。

  - アプリケーションの上部にあるゲストブックタブ
  - [ゲストブックの追加]ボタン
  - エントリの追加ボタン

最初に、ゲストブックのタブを作成し、それらのアクセス許可を確認します。

1.  `/guestbookwebportlet/view.jsp` を開き、リクエストから `guestbookId` を取得するスクリプトレットを見つけます。 このすぐ下に、次のコードを追加します。
   
        <aui:nav cssClass="nav-tabs">
       
            <%
                List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);
       
                    for (int i = 0; i < guestbooks.size(); i++) {
       
                        Guestbook curGuestbook = guestbooks.get(i);
                        String cssClass = StringPool.BLANK;
       
                        if (curGuestbook.getGuestbookId() == guestbookId) {
                            cssClass = "active";
                        }
       
                        if (GuestbookModelPermission.contains(
                            permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
       
            %>
       
            <portlet:renderURL var="viewPageURL">
                <portlet:param name="mvcPath" value="/guestbookwebportlet/view.jsp" />
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
            </portlet:renderURL>
       
       
            <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
                label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />
       
            <%  
                        }
       
                    }
            %>
       
        </aui:nav>

    このコードは、データベースからゲストブックのリストを取得し、それらを反復処理し、現在のユーザーのロールに対してそれぞれの許可を確認し、ユーザーがアクセスできるゲストブックをタブのリストに追加します。

    これで、最初のアクセス許可チェックが実装されました。 ご覧のとおり、ヘルパークラスの静的メソッドのおかげで比較的簡単です。 上記のコードは、現在のユーザーがゲストブックに対して `VIEW` 権限を持っている場合にのみタブを表示します。

    次に、[エントリの追加]ボタンにアクセス許可チェックを追加します。

2.  `<aui:button-row cssClass="guestbook-buttons">`という行までスクロールします。 この行のすぐ下に、次のコード行を追加して、 `ADD_ENTRY` 許可を確認します。
   
        <c:if test='<%= GuestbookPermission.contains(permissionChecker, scopeGroupId, "ADD_ENTRY") %>'>

3.  この後、 `addEntryURL` と[エントリの追加]ボタンを作成するコードがあります。 `aui：button` タグの後、 `</aui:button-row>` タグの上に、 `<c:if>` ステートメントの終了タグを追加します。
   
        </c:if>

    JSTLタグを使用して、[エントリの追加]ボタンのアクセス許可チェックを実装しました。

次に、Guestbook Adminポートレットのエントリによく似た `entry_actions.jsp` を実装します。 これにより、ポートレットのアクションメニューを表示できるログインユーザーに表示されるオプションが決まります。 前と同じように、使用可能なアクションに対してアクセス許可をチェックする `if` ステートメントで各 `renderURL` ラップします。 これを行うには、次の手順を実行します。

1.  `src / main / resources / META-INF / resources / guestbookwebportlet`、 `entry_actions.jsp`というファイルを作成します。

2.  このファイルに、次のコードを追加します。
   
        <%@include file="../init.jsp"%>
       
            <%
            String mvcPath = ParamUtil.getString(request, "mvcPath");
       
            ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
       
            Entry entry = (Entry)row.getObject(); 
            %>
       
            <liferay-ui:icon-menu>
       
                <c:if
                    test="<%= GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
                    <portlet:renderURL var="editURL">
                        <portlet:param name="entryId"
                            value="<%= String.valueOf(entry.getEntryId()) %>" />
                        <portlet:param name="mvcPath" value="/guestbookwebportlet/edit_entry.jsp" />
                    </portlet:renderURL>
       
                    <liferay-ui:icon image="edit" message="Edit"
                        url="<%=editURL.toString() %>" />
                </c:if>
       
                <c:if
                test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">
       
                    <liferay-security:permissionsURL
                        modelResource="<%= Entry.class.getName() %>"
                        modelResourceDescription="<%= entry.getMessage() %>"
                        resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                        var="permissionsURL" />
       
                    <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
       
                </c:if>
       
                <c:if
                    test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">
       
                    <portlet:actionURL name="deleteEntry" var="deleteURL">
                        <portlet:param name="entryId"
                            value="<%= String.valueOf(entry.getEntryId()) %>" />
                        <portlet:param name="guestbookId"
                            value="<%= String.valueOf(entry.getGuestbookId()) %>" />
                    </portlet:actionURL>
       
                    <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
                </c:if>
       
            </liferay-ui:icon-menu>

    このコードは、エンティティの更新、権限の設定、および削除を行うアクションボタンを定義します。 各ボタンは、権限チェックによって保護されています。 現在のユーザーが指定されたアクションを実行できない場合、アクションは表示されません。

3.  最後に、 `view.jsp`で、検索コンテナの最後の列として `entry_actions.jsp` を追加する必要があります。 検索コンテナ行を定義する行を見つけます。 次のようになります。
   
        <liferay-ui:search-container-row
            className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

    その行の下には2つの列があります。 2番目の列の後に、3番目を追加します。
   
        <liferay-ui:search-container-column-jsp path="/guestbookwebportlet/entry_actions.jsp" align="right" />

4.  すべてのJSPファイルを保存します。

優れた\！ これで、ゲストブックポートレットのすべての権限チェックが実装されました。

アプリケーションをテストするときは、リソースなしで作成したゲストブックエントリはアクセス許可では機能しないことに注意してください。 新しいゲストブックとエントリを追加して、さまざまなユーザーでアプリケーションをテストします。 管理ユーザーにはすべてのボタンが表示され、一般ユーザーには[エントリの追加]ボタンが表示され、ゲストにはボタンがまったく表示されません（ただし、ナビゲートできます）。

| **注：** ゲストブックポートレットが|に表示されないエラーが表示される場合があります。すべて、ログに次のエラーが表示されます：| |誰かが許可チェッカーを回避しようとしている可能性があります。 | |これは、Guestbookアプリケーションに現在あるデータがすべてないためです。リソースがあります。 この場合、データベースを削除して再作成する必要があります。 すること|これは、ファイルシステムでLiferayワークスペースを見つけます（Eclipseワークスペース内にあるはずです）。 `bundles / data` フォルダー内には、 `極超音速` フォルダーがあります。 | @product@をシャットダウンし、このフォルダーからすべてを削除してから再起動します。 後|ゲストブックをページに追加すると、ポートレットは正常に機能します。

Guestbook Adminポートレットでも同じことができるかどうかを確認します。 できない場合でも心配しないでください。この学習パスの最後に、完成したプロジェクトへのリンクがあり、調査することができます。

すばらしいです\！ 次のステップは、検索とインデックス作成をアプリケーションに統合することです。 これは、はるかに強力なものが来るための前提条件です。
