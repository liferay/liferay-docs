---
header-id: creating-a-search-results-jsp-for-the-guestbook-portlet
---

# ゲストブックポートレットの検索結果JSPの作成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">検索用のUIの更新</p><p>手順2/2</p>
</div>

検索結果JSPに実装する設計目標はいくつかあります。

  - 検索コンテナを使用して、検索クエリに一致するゲストブックエントリを表示します。
  - メインビューの検索コンテナにあるように、結果の各ゲストブックエントリで[アクション]ボタンを使用可能にします。
  - ユーザーが戻るリンクをクリックしてポートレットのデフォルトビューに移動することなく、クエリを編集および再送信できるように、検索バーを含めます。

![図1：検索結果が検索コンテナーに表示され、各エントリの[アクション]ボタンが表示されます。 検索バーも表示されるはずです。](../../../../images/guestbook-portlet-search-results.png)

以下の手順に従って、検索結果JSPを作成します。

1.  `guestbook-web` モジュールの `/ guestbookwebportlet` フォルダーに `view_search.jsp` という名前の新しいファイルを作成します。 このファイルに、 `init.jsp`含めます。
   
        <%@include file="../init.jsp"%>

2.  リクエストから `キーワード` および `guestbookId` パラメーターを抽出します。 `キーワード` パラメーターには検索クエリが含まれ、 `guestbookId` パラメーターには検索されるゲストブックのIDが含まれます。
   
        <%
          String keywords = ParamUtil.getString(request, "keywords");
          long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
        %>

3.  `searchURL` および `viewURL` を `renderURL`として定義し` 。 どちらも、Liferay MVCポートレットで使用可能な <code>mvcPath` パラメーターを使用します。
   
        <portlet:renderURL var="searchURL">
                <portlet:param name="mvcPath" 
                value="/guestbookwebportlet/view_search.jsp" />
        </portlet:renderURL>
       
        <portlet:renderURL var="viewURL">
            <portlet:param 
                name="mvcPath" 
                value="/guestbookwebportlet/view.jsp" 
            />
        </portlet:renderURL>

    `searchURL` は現在のJSPを指します： `view_search.jsp`。 `viewURL` ゲストブックポートレットのメインビューを指します。 これらのURLは、次に作成するAUIフォームで使用されます。

4.  このAUIフォームを追加します。
   
        <aui:form action="${searchURL}" name="fm">
       
            <liferay-ui:header backURL="${viewURL}" title="back" /><div class="row">
                <div class="col-md-8">
                    <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
                </div>
        
                <div class="col-md-4">
                    <aui:button type="submit" value="search" />
                </div>
            </div>
        </aui:form>

    This form is identical to the one that you added to the Guestbook portlet's `view.jsp`, except that this one contains a `<liferay-ui:header>` tag that displays the Back icon next to the word *Back*. The `backURL` attribute in the header uses the `viewURL` defined above. Submitting the form invokes the `searchURL` with the user's search query added to the URL in the `keywords` parameter.

5.  スクリプトレットを起動して検索コンテキストを取得し、その中にいくつかの属性を設定します。
   
        <%
            SearchContext searchContext = SearchContextFactory.getInstance(request);
       
            searchContext.setKeywords(keywords);
            searchContext.setAttribute("paginationType", "more");
            searchContext.setStart(0);
            searchContext.setEnd(10);

    検索を実行するには、 `SearchContext` オブジェクトが必要です。 `SearchContextFactory` は、要求オブジェクトから `SearchContext` 作成します。 `キーワード` URLパラメーターを `setKeywords` メソッドに渡すことにより、ユーザーの検索クエリを `SearchContext` 追加します。 次に、ページネーションに関する詳細と検索結果の表示方法を指定します。

6.  スクリプトレットで、 `インデクサー` を取得して検索を実行します。 インデクサーのクラスまたはクラス名を渡すことにより、@product@のインデクサーレジストリ内のマップからエントリインデクサーを取得します。
   
        Indexer indexer = IndexerRegistryUtil.getIndexer(Entry.class);

7.  同じスクリプトレットで、インデクサーと検索コンテキストを使用して検索を実行します。
   
        Hits hits = indexer.search(searchContext);
       
        List<Entry> entries = new ArrayList<Entry>();
       
            for (int i = 0; i < hits.getDocs().length; i++) {
                    Document doc = hits.doc(i);
       
                    long entryId = GetterUtil
                    .getLong(doc.get(Field.ENTRY_CLASS_PK));
       
                    Entry entry = null;
       
                    try {
                            entry = EntryLocalServiceUtil.getEntry(entryId);
                    } catch (PortalException pe) {
                            _log.error(pe.getLocalizedMessage());
                    } catch (SystemException se) {
                            _log.error(se.getLocalizedMessage());
                    }
       
                    entries.add(entry);
            }

    検索結果は、ゲストブックエントリに対応するドキュメントへのポインターを含む `ヒット` オブジェクトとして返されます。 次に、ヒットしたドキュメントをループして、対応するゲストブックエントリを取得し、リストに追加します。

8.  現在のサイトに存在するすべてのゲストブックのリストを取得して、スクリプトレットを終了します。 ゲストブックIDとゲストブック名の間にマップを作成します。

    ``` 
            List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

            Map<String, String> guestbookMap = new HashMap<String, String>();

            for (Guestbook guestbook : guestbooks) {
                    guestbookMap.put(Long.toString(guestbook.getGuestbookId()), guestbook.getName());
            }
    %>
    ```

    この単一のサービス呼び出しを行ってマップを作成する方が、ゲストブックごとに個別のサービス呼び出しを行うよりも効率的です。

9.  検索コンテナーに検索結果を表示します。
   
        <liferay-ui:search-container delta="10" 
            emptyResultsMessage="no-entries-were-found" 
            total="<%= entries.size() %>">
                <liferay-ui:search-container-results
                        results="<%= entries %>"
        />

    これは、 `<liferay-ui:search-container>` タグの3つの属性を指定します。

      - `delta = "10"`：ページごとに最大10個のエントリを表示できることを指定します。
      - `emptyResultsMessage`：結果がないことを示すメッセージを指定します。
      - `合計`：検索結果の数を指定します。

    タグ `<liferay-ui:search-container-results>` の `results` 属性は、検索結果を指定します。 `エントリ` リストに検索結果のエントリを保存したため、これは簡単です。

10. `<liferay-ui:search-container-row>` タグを使用して、各行にプロパティが表示されるクラスの名前を設定します。

        <liferay-ui:search-container-row className="com.liferay.docs.guestbook.model.Entry" keyProperty="entryId" modelVar="entry" escapedModel="<%=true%>">

    これは、クラス名に `className` 属性を使用し、 `keyProperty` 属性でエンティティの主キー属性を指定します。 `modelVar` プロパティは、各検索コンテナ行で使用可能な `エントリ` 変数の名前を指定します。 `Entry` 変数の各フィールドをエスケープ（サニタイズ）するために、 `escapedModel` は `true`です。 これにより、たとえば、ユーザーが[ゲストブックの追加]フォームに悪意のあるコードを送信した場合に発生する可能性のあるハッキングを防ぎます。

11. `<liferay-ui:search-container-row>` タグ内で、表示する4つの列（ゲストブックエントリのゲストブック名、メッセージ、エントリ名、アクションJSP）を指定します。 ゲストブック名は、スクリプトレットで作成されたマップから取得されます。

    ``` 
        <liferay-ui:search-container-column-text name="guestbook"
            value="<%=guestbookMap.get(Long.toString(entry.getGuestbookId()))%>" />

        <liferay-ui:search-container-column-text property="message" />

        <liferay-ui:search-container-column-text property="name" />

        <liferay-ui:search-container-column-jsp
            path="/guestbookwebportlet/entry_actions.jsp"
            align="right" />
    </liferay-ui:search-container-row>
    ```

12. `<liferay-ui:search-iterator>` タグを使用して、検索結果を繰り返し処理し、ページネーションを処理します。 検索コンテナタグを閉じます。

    ``` 
            <liferay-ui:search-iterator />
    </liferay-ui:search-container>
    ```

13. `view_search.jsp`下部で、 `Log` オブジェクトを宣言します。 このログは、 `EntryLocalServiceUtil.getEntry` メソッドを呼び出してゲストブックエントリを取得する `try` 句の `catch` 句で使用しました。 このサービス呼び出しが例外をスローした場合、サーバー管理者が何が問題なのかを判断できるようにエラーをログに記録するのが最善です。 @product@の規則は、ファイルの下部で個々のクラスまたはJSPのカスタムログを宣言することです。

        <%! private static Log _log = LogFactoryUtil.getLog("html.guestbookwebportlet.view_search_jsp"); %>

14. 最後に、 `view_search.jsp` は追加のインポートが必要です。 `init.jsp`次のインポートを追加します。

        <%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %> <%@ page import="com.liferay.portal.kernel.exception.PortalException" %> <%@ page import="com.liferay.portal.kernel.exception.SystemException" %> <%@ page import="com.liferay.portal.kernel.language.LanguageUtil" %> <%@ page import="com.liferay.portal.kernel.log.Log" %> <%@ page import="com.liferay.portal.kernel.log.LogFactoryUtil" %> <%@ page import="com.liferay.portal.kernel.search.Indexer" %> <%@ page import="com.liferay.portal.kernel.search.IndexerRegistryUtil" %> <%@ page import="com.liferay.portal.kernel.search.SearchContext" %> <%@ page import="com.liferay.portal.kernel.search.SearchContextFactory" %> <%@ page import="com.liferay.portal.kernel.search.Hits" %> <%@ page import="com.liferay.portal.kernel.search.Document" %> <%@ page import="com.liferay.portal.kernel.search.Field" %> <%@ page import="com.liferay.portal.kernel.util.StringPool" %> <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %> <%@ page import="com.liferay.portal.kernel.util.Validator" %> <%@ page import="com.liferay.portal.kernel.util.PortalUtil" %>

        <%@ page import="java.util.ArrayList" %> <%@ page import="java.util.Map" %> <%@ page import="java.util.HashMap" %>

        <%@ page import="javax.portlet.PortletURL" %>

よくできました\！ ゲストブックポートレットが検索をサポートするようになりました！ これで、ユーザーは探していたゲストブックエントリを見つけることができます。

![図2：ゲストブックアプリケーションは、インデックス付きのゲストブックエントリの検索をサポートするようになりました。](../../../../images/guestbook-portlet-search-conclusion.png)

インデックスを作成したら、アセットフレームワークをゲストブックアプリケーションに追加できます。 ブログ投稿、掲示板投稿、Wiki記事など、さまざまなタイプのコンテンツで共有される機能を提供します。 これは、@product@の開発プラットフォームとの統合の中心です。
