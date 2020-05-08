---
header-id: integrating-with-facebook
---

# Facebookとの統合

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

@product@は、ポータルとそのコンテンツをFacebookと統合するためのツールを提供します。 たとえば、認証にFacebookを使用し、ウィジェットをFacebookアプリケーションとしてエクスポートすることもできます。 この記事ではその方法を示します。

## Facebookサインオン

アクセスする多くのWebサイトと同様に、@product@で実行されているどのサイトでもサインインにFacebookを使用できます。 これにより、ユーザーは別のユーザー名とパスワードを覚える必要がないため、サイトへのサインインが容易になります。 Facebookを使用して@product@にサインインする方法については、 [配備ドキュメント](/docs/7-1/deploy/-/knowledge_base/d/facebook-connect-single-sign-on-authentication) を参照してください。

## ウィジェットをFacebookアプリケーションとして使用する

@product@ウィジェットをFacebookのアプリとして追加できます。 これを行うには、まず開発者キーを取得する必要があります。 これを行うためのリンクは、ウィジェットの設定画面のFacebookタブに表示されます。 Facebookでアプリを作成し、FacebookからキーとキャンバスページのURLを取得する必要があります。 次に、それらの値をコピーしてFacebookタブに貼り付けます。 これを実行すると、ウィジェットがFacebookで使用できるようになります。

この統合により、メッセージボード、カレンダー、Wiki、およびその他のコンテンツなどのWebサイトのコンテンツを、より多くのユーザーが利用できるようになります（サイトに既に10億人のユーザーがいる場合を除きます）。 開発者であれば、@product@にウィジェットを実装して、それをFacebookに公開できます。
