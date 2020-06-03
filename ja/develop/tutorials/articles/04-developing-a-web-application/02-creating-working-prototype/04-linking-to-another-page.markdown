---
header-id: linking-to-another-page
---

# 異なるページへのリンク付け

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

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
