---
header-id: updating-the-view
---

# ビューをアップデートする

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順5/6</p>
</div>

アップデートによって、エントリ作成の背後にあるメカニズムがアップデートされただけではなく、メソッドと構造が完全に変更されました。 したがって、UIもアップデートする必要があります。 そのためには、ゲストブックを管理するための新しいJSPを作成し、既存のJSPをアップデートする必要があります。

1.  まず、依存関係をアップデートする必要があります。 `guestbook-web`モジュールで、`/src/main/resources/META-INF/resources/`から`init.jsp`を開きます。 このファイルに、次のような追加の依存関係を追加します。

    ``` 
    <%@ taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
    <%@ page import="java.util.List" %>
    <%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
    <%@ page import="com.liferay.portal.kernel.util.HtmlUtil" %>
    <%@ page import="com.liferay.petra.string.StringPool" %>
    <%@ page import="com.liferay.portal.kernel.model.PersistedModel" %>
    <%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
    <%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
    <%@ page import="com.liferay.docs.guestbook.model.Guestbook" %>
    <%@ page import="com.liferay.docs.guestbook.service.EntryLocalServiceUtil" %>
    <%@ page import="com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil" %>
    <%@ page import="com.liferay.docs.guestbook.model.Entry" %> 
    ```

2.  `/resources/META-INF/resources/guestbookwebportlet`にある、`view.jsp`ファイルを開きます。 このファイルの内容を次のコードに置き換えます。
   
        <%@include file="../init.jsp"%>
       
        <%
        long guestbookId = Long.valueOf((Long) renderRequest
                .getAttribute("guestbookId"));
        %>
       
        <aui:button-row cssClass="guestbook-buttons">
       
            <portlet:renderURL var="addEntryURL">
                <portlet:param name="mvcPath" value="/guestbookwebportlet/edit_entry.jsp" />
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(guestbookId)%>" />
            </portlet:renderURL>
       
            <aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>
       
        </aui:button-row>
       
        <liferay-ui:search-container total="<%=EntryLocalServiceUtil.getEntriesCount()%>">
        <liferay-ui:search-container-results
            results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId.longValue(),
                            guestbookId, searchContainer.getStart(),
                            searchContainer.getEnd())%>" />
       
        <liferay-ui:search-container-row
            className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">
       
            <liferay-ui:search-container-column-text property="message" />
       
            <liferay-ui:search-container-column-text property="name" />
       
        </liferay-ui:search-container-row>
       
        <liferay-ui:search-iterator />
       
        </liferay-ui:search-container>

この`view.jsp`は、`render`メソッドから取得したゲストブックからエントリを取得するようになりました。 *Search Container*と呼ばれる@product@コンストラクト内でこれを行います。 これは、行と列にデータを簡単に表示できるようにするフロントエンドコンポーネントです。 `EntryLocalServiceUtil`の呼び出しは、新しいService Builderベースのバックエンドからデータを取得します。 それ以外の場合、このJSPはほぼ同じです。対応するURLを含む*Add Entry*ボタンがまだあります。

次に、`edit_entry.jsp`を編集する必要があります。

1.  `edit_entry.jsp`を開き、既存のコードを次のように置き換えます。
   
        <%@include file="../init.jsp" %>
       
        <% 
       
        long entryId = ParamUtil.getLong(renderRequest, "entryId");
       
        Entry entry = null;
        if (entryId > 0) {
          entry = EntryLocalServiceUtil.getEntry(entryId);
        }
       
        long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
       
        %>
       
        <portlet:renderURL var="viewURL">
       
        <portlet:param name="mvcPath" value="/guestbookwebportlet/view.jsp"></portlet:param>
       
        </portlet:renderURL>
       
        <portlet:actionURL name="addEntry" var="addEntryURL"></portlet:actionURL>
       
        <aui:form action="<%= addEntryURL %>" name="<portlet:namespace />fm">
       
        <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />
       
            <aui:fieldset>
       
                <aui:input name="name" />
                <aui:input name="email" />
                <aui:input name="message" />
                <aui:input name="entryId" type="hidden" />
                <aui:input name="guestbookId" type="hidden" value='<%= entry == null ? guestbookId : entry.getGuestbookId() %>'/>
       
            </aui:fieldset>
       
            <aui:button-row>
       
                <aui:button type="submit"></aui:button>
                <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
       
            </aui:button-row>
        </aui:form>

    現在はさらにフィールドがありますが、これはほぼ同じ形式です。 AlloyUIタグを使用して、フォームは`Entry`エンティティにリンクされます。 2つの非表示フィールドには、新しいエントリが属するゲストブックの、新しい`entryId`と`guestbookId`が含まれます。 送信ボタンは、コントローラ（ポートレットクラス）で`addEntry`メソッドを実行する`ActionURL`です。

これで、プロトタイプのバックエンドを実際のデータベース駆動型のバックエンドに正常に置き換えることができました。 次に、アプリケーションを簡単に確認してデプロイします。
