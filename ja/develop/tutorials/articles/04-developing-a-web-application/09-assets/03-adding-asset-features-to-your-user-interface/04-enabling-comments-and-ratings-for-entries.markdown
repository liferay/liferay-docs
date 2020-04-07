---
header-id: enabling-comments-and-ratings-for-guestbook-entries
---

# ゲストブックエントリのコメントと評価を有効にする

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">UIへのアセット機能の追加</p><p>手順5/5</p>
</div>

アセットフレームワークにより、ユーザーはアセットにコメントして評価できます。 タグ、カテゴリ、および関連するアセットと同様に、これらの機能を公開するにはユーザーインターフェイスを更新する必要があります。 アプリケーションを適切に設計するには、ユーザーがアセットを評価およびコメントできる[表示]ページが必要です。 ゲストブックエントリでコメントと評価を有効にするには、次の手順に従います。

1.  `guestbook-web` モジュールプロジェクトの `/ guestbookwebportlet` フォルダーに `view_entry.jsp` という新しいファイルを作成します。

2.  作成したファイルにJavaスクリプトレットを追加します。 このスクリプトレットでは、 `entryId` リクエスト属性を使用してエントリオブジェクトを取得します。 セキュリティ上の理由から、このオブジェクトを前述の手順 [Asset Asset](/docs/7.1/tutorials/-/knowledge_base/t/creating-jsps-for-displaying-custom-assets-in-the-asset-publisher)でカスタムアセットを表示するためのJSPを作成するで説明したエスケープモデルに変換します。
   
        <%@ include file="../init.jsp"%>
       
        <%
          long entryId = ParamUtil.getLong(renderRequest, "entryId");
       
          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
       
          Entry entry = null;
       
          if (entryId > 0) {
            entry = EntryLocalServiceUtil.getEntry(entryId);
       
            entryId = entry.getEntryId();
          }
       
          entry = EntryLocalServiceUtil.getEntry(entryId);
          entry = entry.toEscapedModel();
       
          AssetEntry assetEntry = 
          AssetEntryLocalServiceUtil.getEntry(Entry.class.getName(), 
          entry.getEntryId());

3.  次に、現在のエントリの名前でブレッドクラムエントリを更新します。
   
        String currentURL = PortalUtil.getCurrentURL(request);
        PortalUtil.addPortletBreadcrumbEntry(request, entry.getMessage(),
        currentURL);

4.  スクリプトレットの最後に、現在のエントリの既存のアセットタグの名前をキーワードとしてポータルページに追加します。 これらのタグ名は、ポータルページの `<head>` セクションの `<meta content="[tag names here]" lang="en-US" name="keywords" />` 要素に表示されます。 これらのキーワードは、検索エンジンがページを見つけてインデックスを作成するのに役立ちます。

    ``` 
      PortalUtil.setPageSubtitle(entry.getMessage(), request);
      PortalUtil.setPageDescription(entry.getMessage(), request);

      List<AssetTag> assetTags = 
      AssetTagLocalServiceUtil.getTags(Entry.class.getName(), 
      entry.getEntryId());
      PortalUtil.setPageKeywords(ListUtil.toString(assetTags, "name"), 
      request);
    %>
    ```

5.  スクリプトレットの後に、ページおよびバックリンクのURLを指定します。
   
        <liferay-portlet:renderURL varImpl="viewEntryURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view_entry.jsp" />
          <portlet:param name="entryId" value="<%=String.valueOf(entryId)%>" />
        </liferay-portlet:renderURL>
       
        <liferay-portlet:renderURL varImpl="viewURL">
          <portlet:param name="mvcPath"
            value="/guestbookwebportlet/view.jsp" />
        </liferay-portlet:renderURL>
       
        <liferay-ui:header backURL="<%=viewURL.toString()%>"
          title="<%=entry.getName()%>" 
        />

6.  次に、ページのメインコンテンツを定義します。 ゲストブックの名前を表示し、エントリの名前とメッセージと `<dl>`、 `<dt>`、及び `<dd>` タグ： <dl>
          <dt>ゲストブック</dt>
          <dd><%=GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName()%></dd>
          <dt>名前</dt>
          <dd><%=entry.getName()%></dd>
          <dt>メッセージ</dt>
          <dd><%=entry.getMessage()%></dd>
        </dl>

    This is the same way you defined the page's main content in `/guestbookwebportlet/full_content.jsp`.

7.  次に、 `<liferay-ui:panel-container>` タグを使用してパネルコンテナを作成します。 このタグ内で、 `<liferay-ui:panel>` タグを使用して、コメントと評価コンポーネントを表示するパネルを作成します。
   
        <liferay-ui:panel-container extended="<%=false%>"
          id="guestbookCollaborationPanelContainer" persistState="<%=true%>">
          <liferay-ui:panel collapsible="<%=true%>" extended="<%=true%>"
            id="guestbookCollaborationPanel" persistState="<%=true%>"
            title="Collaboration">

8.  `<liferay-ui:ratings>` タグで評価コンポーネントを追加します。
   
        <liferay-ui:ratings className="<%=Entry.class.getName()%>"
          classPK="<%=entry.getEntryId()%>" type="stars" />
       
        <br />

9.  次に、コメントディスカッションオブジェクトを取得するスクリプトレットを追加する必要があります。
   
        <% Discussion discussion = 
        CommentManagerUtil.getDiscussion(user.getUserId(), 
        scopeGroupId, Entry.class.getName(), 
        entry.getEntryId(), new ServiceContextFunction(request));
        %>

10. その下に、コメントの数を追跡するためのタグを追加します。
    \<c:if test="\<%= discussion \!= null %\>"> <strong>\<liferay-ui:message arguments="\<%= discussion.getDiscussionCommentsCount() %\>"key = '\ <％=（discussion.getDiscussionCommentsCount（）== 1）？ "x-comment"： "x-comments"％\> '></p></li> 
    
    <li>
      <p spaces-before="0">
        <code>liferay-comment：discussion</code> タグを作成します。これにより、コメントフォーム、 <em x-id="3">返信</em> ボタンが作成され、ディスカッションコンテンツが取得されます。 また、ポートレットアクションURLを作成することなく、コメントを投稿するフォームアクションを処理します。 
        
        <pre><code>      &lt;liferay-comment:discussion
        className="&lt;%= Entry.class.getName() %&gt;"
        classPK ="&lt;％= entry.getEntryId（）％&gt;"
        Discussion ="&lt;％=ディスカッション％&gt;"
        formName =" fm2 "
        ratingsEnabled =" true "
        redirect ="&lt;％= currentURL ％&gt;"
        はuserId ="&lt;％= entry.getUserId（）％&gt;"
        /&gt;

      &lt;/liferay-ui:panel&gt;
    &lt;/liferay-ui:panel-container&gt;
</code></pre>
      </p>
    </li>
    
    <li>
      <p spaces-before="0">
        ログインしているユーザーへのコメントや評価のアクセスを制限するために、パネル全体の容器をラップで <code>&lt;c:if&gt;</code> 式テストタグ <code>）themeDisplay.isSignedInを（</code>：
      </p>
      <pre><code> &lt;c:if test="&lt;%= themeDisplay.isSignedIn() %&gt;"&gt;
... パネルコンテナ...
 &lt;/c:if&gt;
</code></pre>
      <p spaces-before="4">
        終了 <code>&lt;/liferay-ui:panel-container&gt;</code> タグの後に必ず終了 <code>&lt;/c:if&gt;</code> タグを追加してください。
      </p>
    </li></ol> 
    
    <p spaces-before="0">
      | <strong x-id="1">注：</strong> ディスカッション（コメント）は、メッセージボードメッセージとして実装されます。 で| <code>MBMessage</code> テーブルには、 <code>classPK</code> 列があります。 この <code>classPK</code> 表します|ゲストブックエントリの <code>entryId</code>、コメントをゲストブックにリンクします。 評価| <code>RatingsEntry</code> テーブルに保存されます。 同様に、 <code>RatingsEntry</code> テーブル|ゲストブックのエントリを評価にリンクする <code>classPK</code> 列が含まれています。 使用する|別の主キーを表す1つのテーブルの <code>classPK</code> 外部キー|テーブルは@product@全体で共通のパターンです。
    </p>
    
    <p spaces-before="0">
      次に、新しいビューを使用するようにゲストブックアクションを更新します。
    </p>

<h2 spaces-before="0">
  エントリアクションの更新JSP
</h2>

<p spaces-before="0">
  <code>view_entry.jsp</code> ページは現在孤立しています。 これを修正するには、[アクション]メニューに[ <em x-id="3">ビュー</em> ]オプションを追加します。 開く <code>/guestbookwebportlet/entry_actions.jsp</code> ：次の行と検索
</p>

<pre><code>&lt;liferay-ui:icon-menu&gt;
</code></pre>

<p spaces-before="0">
  その下に次の行を追加：
</p>

<pre><code>&lt;portlet:renderURL var="viewEntryURL"&gt;
  &lt;portlet:param name="entryId"
    value="&lt;%= String.valueOf(entry.getEntryId()) %&gt;"/&gt;
  &lt;portlet:param name="mvcPath"
    value="/guestbookwebportlet/view_entry.jsp" /&gt;
&lt;/portlet:renderURL&gt;

&lt;liferay-ui:icon message="View" url="&lt;%= viewEntryURL.toString() %&gt;" /&gt;
</code></pre>

<p spaces-before="0">
  ここでは、 <code>view_entry.jsp</code>を指すURLを作成します。 ゲストブックエントリの[アクション]メニューで[ <em x-id="3">ビュー</em> ]オプションを選択して、このリンクをテストします。 次に、コメントと評価をテストします。
</p>

<p spaces-before="0">
  すばらしい\！ ゲストブックおよびゲストブックエントリエンティティをアセット対応にし、両方のエンティティのタグ、カテゴリ、および関連アセットを有効にしました。 ゲストブックエントリエンティティのコメントと評価も有効にしました\！ よくやった\！
</p>

<p spaces-before="0">
  <img src="../../../../images/asset-publisher-full-content-finished.png" alt="Figure 1: Now you can see comments, rating, and the full range of asset features." />
</p>

<p spaces-before="0">
  次のタスクはWebサービスの生成です。 これにより、Guestbookアプリケーション用に他のクライアント（モバイルアプリケーションなど）を作成できます。
</p>
