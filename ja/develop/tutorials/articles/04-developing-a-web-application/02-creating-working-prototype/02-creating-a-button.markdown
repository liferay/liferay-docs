---
header-id: creating-an-add-guestbook-button
---

# 追加エントリボタンの作成

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順2/8</p>
</div>

ゲストブックアプリケーションはいたってシンプルです。 ユーザーはサイトへ訪問し、名前と簡単なメッセージを投稿します。 他のユーザーはこれらのエントリを読み、さらに投稿できます。

プロジェクトを作成した時、プロジェクトの`src/main/resources/META-INF/resources`フォルダに、`view.jsp`という名前のファイルが生成されました。 このファイルには、ポートレットがページに追加されたときのユーザーのデフォルトビューが含まれています。 それには現在、サンプルコンテンツが含まれています。

    <%@ include file="/init.jsp" %>
    
    <p>
        <b><liferay-ui:message key="guestbook-web.caption"/></b>
    </p>

まず、 `view.jsp`は`init.jsp`をインポートします。 慣例により、インポートおよびタグライブラリ宣言は`init.jsp`ファイルにあります。 アプリケーションの他のJSPファイルは、`init.jsp`をインポートします。 これにより、JSPの依存関係をを1つのファイルで管理できます。

さらに、`init.jsp`、`view.jsp`のインポートは言語キーで定義されたメッセージを表示します。 このキーとその値は、プロジェクトの`src/main/resources/content/Language.properties`ファイルで宣言されています。

ゲストブックアプリケーションの開発を開始します。 まず、ユーザーはゲストブックにエントリを追加する方法が必要です。 `view.jsp`で、次の手順に従ってこのボタンを追加します。

1.  `init.jsp`のインクルード以下をすべて削除します。

2.  インクルードの下に、次の[AlloyUI](http://alloyui.com/)タグを追加して、ボタン行内に「エントリーを追加」ボタンを表示します。
   
        <aui:button-row>
            <aui:button value="Add Entry"></aui:button>
        </aui:button-row>

`init.jsp`はデフォルトでAlloyUIタグライブラリ（およびその他の重要なインポートとタグ）を宣言するため、`view.jsp`で`aui`タグを使用できます。

``` 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    <%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
    <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
    <%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

    <portlet:defineObjects />

    <liferay-theme:defineObjects />
```

アプリケーションはメッセージではなくボタンを表示するようになりましたが、ボタンは何も行いません。 次に、ボタン用URLを作成します。

![図1：新しいボタンはまだ機能していません。](../../../images/guestbook-new-button.png)
