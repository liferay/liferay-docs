---
header-id: displaying-messages-and-errors
---

# メッセージとエラーの表示

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

ユーザーがアプリケーションを操作するとき、ユーザーは物事の保存や編集など、アプリケーションが定義するタスクを実行します。 Guestbookアプリケーションも同様です。 また、アプリケーションはこれらの操作に関するフィードバックを提供して、ユーザーが機能したかどうかを確認できるようにする必要があります。 これまで、この情報は管理者のみがアクセスできるログに記録してきました。 これらのメッセージをユーザーに表示する方が良いと思いませんか？

![図1：LiferayのAPIを使用して、役立つメッセージを表示できます。](../../../images/guestbook-status-message.png)

それがまさに次の3つのステップで行うことです。

1.  メッセージの言語キーを作成します。
2.  アクションメソッドにエラーメッセージを追加します。
3.  JSPでこれらのエラーメッセージを報告します。

始める準備はできましたか？

<a class="go-link btn btn-primary" href="/docs/7-1/tutorials/-/knowledge_base/t/creating-language-keys">行こう\！<span class="icon-circle-arrow-right"></span></a>

