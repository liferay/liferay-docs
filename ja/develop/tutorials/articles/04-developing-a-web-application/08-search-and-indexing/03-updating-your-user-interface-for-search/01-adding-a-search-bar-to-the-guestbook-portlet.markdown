---
header-id: adding-a-search-bar-to-the-guestbook-portlet
---

# ゲストブックポートレットへの検索バーの追加

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">検索用のUIの更新</p><p>手順1/2</p>
</div>

ゲストブックポートレットの検索バーUIを作成します。

1.  `guestbook-web`で、ファイル `src / main / resources / META-INF / resources / guestbookwebportlet / view.jsp`開きます。 リクエストから `guestbookId` を取得するスクリプトレットの直後に、ファイルの上部近くにレンダリングURLを追加します。
   
        <portlet:renderURL var="searchURL">
            <portlet:param name="mvcPath" 
            value="/guestbookwebportlet/view_search.jsp" />
        </portlet:renderURL>

    レンダリングURLは、 `/guestbookwebportlet/view_search.jsp` （次の手順で作成）を指します。 最初にURLを作成して、ユーザーが検索クエリを送信したときの動作を指定します。

2.  レンダリングURLの直後に、検索キーワードの入力フィールドと、 `searchURL`マッピングされるフォームアクションを実行する *Submit* ボタンを追加するAUIフォームを作成します。
   
        <aui:form action="${searchURL}" name="fm"><div class="row">
                <div class="col-md-8">
                    <aui:input inlineLabel="left" label="" name="keywords" placeholder="search-entries" size="256" />
                </div>
        
                <div class="col-md-4">
                    <aui:button type="submit" value="search" />
                </div>
            </div>
        </aui:form>

    The body of the search form consists of a `<div>` with one row containing two fields: an input field, named `keywords` and a *Submit* button. Its `name="keywords"` attribute specifies the name of the URL parameter that contains the search query. The `<aui:button>` tag defines the search button. The `type="submit"` attribute specifies that when the button is clicked (or the *Enter* key is pressed), the AUI form is submitted. The `value="search"` attribute specifies the name that appears on the button.

検索フォームにあるのはこれだけです！ フォームが送信されると、 `view_search.jsp` 指す `mvcPath` パラメーターが、検索クエリを含む `キーワード` パラメーターとともにURLに含まれます。 次に、 `view_search.jsp` ファイルを作成して、検索結果を表示します。
