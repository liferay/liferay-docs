---
header-id: linking-to-another-page
---

# 異なるページへのリンク付け

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">初めてのポートレット開発</p><p>手順4/8</p>
</div>

`view.jsp`と同じフォルダに、`edit_entry.jsp`ファイルを作成します。

1.  プロジェクトの`src/main/resources/META-INF/resources`フォルダを右クリックし、*New* → *File*を選択します。

2.  ファイルに`edit_entry.jsp`という名前を付け、*Finish*をクリックします。

3.  次の行をファイルの先頭に追加します。
   
        <%@ include file="init.jsp" %>

    アプリケーションの他のJSPファイルによってインポートされる単一のファイルに、すべてのJSPインポートとタグライブラリ宣言を追加することをお勧めします。 `edit_entry.jsp`の場合、URLを作成するポートレットタグとフォームを作成するAlloyタグにアクセスするには、これらのインポートが必要です。

4.  2つのURLを作成します。1つはフォームを送信し、もう1つは`view.jsp`へ戻ります。 `view.jsp`へ戻るURLを作成するには、追加した最初の行の下に次のタグを追加します。
   
        <portlet:renderURL var="viewURL">
            <portlet:param name="mvcPath" value="/view.jsp"></portlet:param>
        </portlet:renderURL>

次に、フォームを送信する新しいURLを作成します。 その前に、ポートレットの操作について学習する必要があります。
