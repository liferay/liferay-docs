---
header-id: adding-comments-to-your-app
---

# アプリへのコメントの追加

[TOC levels=1-4]

ユーザーがコンテンツにコメントできるようにすることで、アプリが活気づきます。 Taglibは、この機能を追加するために必要なマークアップを提供します。 このチュートリアルでは、これらのtaglibを使用してコメントを有効にする方法を示します。

これらの手順では、サンプルのゲストブックアプリを例として使用します。

1.  エンティティが [アセット有効](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)ことを確認してください。

2.  コメントを有効にするエンティティの読み取り専用ビューを選択します。 アプリのビューにコメントコンポーネントを表示できます [アセットレンダリング](/docs/7-1/tutorials/-/knowledge_base/t/rendering-an-asset) を実装していない場合は、Asset Publisherアプリの完全なコンテンツビューに表示できます。

3.  含める `のLiferay-UI`、 `のLiferay-コメント`、および `ポートレット` あなたのJSP内のtaglib宣言を：
   
        <%@ taglib prefix="liferay-ui" uri="http://liferay.com/tld/ui" %>
        <%@ taglib prefix="liferay-comment" uri="http://liferay.com/tld/comment" %>
        <%@ taglib prefix="portlet" uri="http://java.sun.com/portlet_2_0" %>

4.  レンダリング要求からエンティティのIDを取得するには、 `ParamUtil` を使用します。 次に、 `-LocalServiceUtil` クラスを使用してエンティティオブジェクトを作成します。 Guestbookアプリの例のゲストブックエントリに対してこれを行う例は次のとおりです。
   
        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
        entry = EntryLocalServiceUtil.getEntry(entryId);
        %>

5.  `liferay-ui：panel-container` および `liferay-ui：panel` タグを使用して、コメント用の折りたたみ可能なパネルを作成します。 これにより、ユーザーはディスカッションエリアを非表示にできます。
   
        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

6.  `portlet：actionURL` タグを使用して、ディスカッションのURLを作成します。
   
        <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />

7.  `liferay-comment：discussion` タグを使用して、ディスカッションを追加します。 ユーザーがコメントを作成した後にJSPに戻るようにするには、タグの `リダイレクト` 属性を現在のURLに設定します。 `PortalUtil.getCurrentURL（（renderRequest））` を使用して、 `リクエスト` オブジェクトから現在のURLを取得できます。 この例では、現在のURLは以前に `currentURL` 変数に設定されていました`
<pre><code>        <liferay-comment:discussion className="<%=Entry.class.getName()%>"
          classPK="<%=entry.getEntryId()%>"
          formAction="<%=discussionURL%>" formName="fm2"
          ratingsEnabled="<%=true%>" redirect="<%=currentURL%>"
          userId="<%=entry.getUserId()%>" />

      </liferay-ui:panel>
    </liferay-ui:panel-container>
`</pre>

あなたはすでにあなたのエンティティのJSPへのポートレットのビューを接続していない場合は、 [設定JSPテンプレートのチュートリアルを参照](/docs/7-1/tutorials/-/knowledge_base/t/configuring-jsp-templates-for-an-asset-renderer)。

すばらしいです\！ これで、ユーザーがアセット対応ポートレットのコンテンツにコメントできるようにする方法がわかりました。

## 関連トピック

[資産フレームワーク](/docs/7-1/tutorials/-/knowledge_base/t/asset-framework)

[資産の評価](/docs/7-1/tutorials/-/knowledge_base/t/rating-assets)
