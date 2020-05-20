---
header-id: adding-messages-to-jsps
---

# JSPへのメッセージの追加

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">メッセージとエラーの表示</p><p>[fuzzy]手順3/3</p>
</div>

ユーザーに表示されるメッセージは、 `SessionMessages` または `SessionErrors`いずれかに保存されるようになりました。 次に、これらのメッセージをJSPに表示します。

1.  `guestbook-web` モジュールで、 `guestbookwebportlet / view.jsp`開きます。 次の成功メッセージのブロックをファイルの先頭、 `init.jsp` includeステートメントのすぐ下に追加します。
   
        <liferay-ui:success key="entryAdded" message="entry-added" />
        <liferay-ui:success key="entryDeleted" message="entry-deleted" />

    このタグは、 `SessionMessages`保存されているものにアクセスします。 2つの属性があります。 最初は、 `GuestbookPortlet.java` クラスの追加および削除メソッドで提供した `SessionMessages` キーです。 2番目は、 `Language.properties` ファイルで指定されたキーを検索します。 ここでハードコードされたメッセージを指定することもできますが、ローカライズされたキーを提供する方がはるかに優れています。

2.  `guestbookadminportlet / view.jsp`開き` 。 次の成功メッセージのブロックを、インクルードの下の同じ場所に追加します。</p>

<pre><code> <liferay-ui:success key="guestbookAdded" message="guestbook-added" />
 <liferay-ui:success key="guestbookUpdated" message="guestbook-updated" />
 <liferay-ui:success key="guestbookDeleted" message="guestbook-deleted" />
`</pre></li> </ol>

![図1：メッセージには、 <code>Language.properties</code>指定した値が表示されます。](../../../images/message-complete.png)

おめでとうございます！ アプリケーションの操作に役立つフィードバックを追加しました。

アプリケーションは整形されていますが、別の重要な機能である許可が欠落しています。 次に、ゲストブックとエントリのアクセス許可チェックを追加します。
