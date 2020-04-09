---
header-id: generating-portlet-urls
---

# ポートレットのURLを生成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順3/8</p>
</div>

前の手順で、ユーザーは複数のポートレットを1つのページに配置できると説明しましたが、 他のポートレットがあなたのページとページを共有する可能性があることは開発者によく知られていません。 これは、アプリケーションのさまざまな機能のURLを、他の方法では定義できないことを意味します。

たとえば、ユーザーが、ブログアプリケーションと同じページに配置するカレンダーアプリケーションを例に考えてみましょう。 それぞれのアプリケーションで、カレンダーイベントとブログエントリを削除する機能を実装するには、両方のアプリケーション開発者がURLに`del`パラメーターを追加し、アプリケーションがカレンダーイベントまたはブログエントリを検索して削除できるようにプライマリーキー値を指定します。 そうすると、両方のアプリケーションがこのパラメータを読み取るので、それらの削除機能は競合してしまいます。

システムで生成されたURLはこれを防ぎます。 システムが各機能に対して一意のURLを生成する場合、複数のアプリケーションが完全に調和して共存できます。

`view.jsp`で、次の手順に従ってシステム生成URLをポートレットに作成します。

1.  これらのタグを、`<%@ include file="/init.jsp" %>`の下、ただし`<aui:button-row>`タグの上に追加します。
   
        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>

2.  `value="Add Entry"`の前に、この属性を`<aui:button>`タグに追加します。
   
        onClick="<%= addEntryURL.toString() %>"

    `view.jsp`ページは次のようになります。
   
        <%@ include file="/init.jsp" %>
       
        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/edit_entry.jsp"></portlet:param>
        </portlet:renderURL>
       
        <aui:button-row>
            <aui:button onClick="<%= addEntryURL.toString() %>" value="Add Entry"></aui:button>
        </aui:button-row>

The `<portlet:renderURL>`タグの`var`属性は、システム生成URLを保持する`addEntryURL`変数を作成します。 `<portlet:param>`タグは、URLに追加するURLパラメーターを定義します。 この例では、値が`/edit_entry.jsp`の`mvcPath`という名前のURLパラメーターがURLに追加されます。

注: (`guestbook-web`モジュールの`com.liferay.docs.guestbook.portlet`パッケージにある) `GuestbookPortlet`クラスは、Liferayの`MVCPortlet`クラスを拡張します。 [Liferay MVCポートレット](/docs/7-1/tutorials/-/knowledge_base/t/liferay-mvc-portlet)では、 `mvcPath`URLパラメーターは、ポートレット内のページを示します。 ポートレット内の別のページに移動するには、パラメーター`mvcPath`とともにポータルURLを使用して、特定のページにリンクします。

上記の例では、まだ作成していないアプリケーションの`edit_entry.jsp`ページを指す `renderURL`を作成しました。 注: AlloyUIボタンを使用して、生成URLをたどる必要はありません。 リンクを含む、いかなるHTML構造でも利用できます。 ユーザーはボタンをクリックして、アプリケーションの`edit_entry.jsp`ページにアクセスできます。 `edit_entry.jsp`がまだ存在しないため、これは現在エラーを生成します。 次のステップは、`edit_entry.jsp`を作成することです。
