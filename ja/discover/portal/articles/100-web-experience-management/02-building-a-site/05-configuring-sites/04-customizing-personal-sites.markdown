---
header-id: customizing-personal-sites
---

# 個人用サイトのカスタマイズ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

デフォルトでは、新しく作成されたユーザーには個人用サイトが付与されます。

  - ユーザーは、個人サイトのサイト管理者として機能します。

  - 個人用サイトは完全にカスタマイズ可能ですが、複数のメンバーを持つことはできません。

  - ユーザーは、自分のサイトのパブリックページに公開されているコンテンツを公開できます。 これは、ユーザーのブログでよく使用されます。

  - ユーザーは、個人情報を保持したり、ドキュメントとメディアを使用して独自のプライベートファイルリポジトリを作成したりできるプライベートページを持つこともできます。

次のプロパティを `portal-ext.properties` ファイルに追加することにより、個人用サイトを無効にすることができます。

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false

| **注：** 個人用サイトのパブリックページセットとプライベートページセットが処理されます。別々に。 1つのページセットを有効にしたまま、他のページセットを無効にすることができます。

インスタンスでユーザー個人用サイトを最初に有効にしてから無効にした場合、既存の個人用サイトは、次回ユーザーがログインするまでLiferayインスタンスに残り、その時点で削除されます。

ユーザーに個人用サイトの作成を許可し、新しいユーザー用に自動的に作成させることはできません。 これを行うには、次のプロパティを `portal-ext.properties` ファイルに追加します。

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false

プロパティ場合 `layout.user.public.layouts.enabled`、 `layout.user.private.layouts.enabled`、 `layout.user.public.layouts.auto.create`、及び `layout.user.private.layouts .auto.create` はすべて `true`設定されています。これはデフォルトです。ユーザーには個人用サイトがあり、新しいユーザー用にパブリックページとプライベートページが自動的に作成されます。

自動的に作成されるページをカスタマイズするために使用できるポータルプロパティがいくつかあります。 デフォルトページの名前、ページに表示されるアプリケーション、デフォルトページのテーマとレイアウトテンプレートなどをカスタマイズできます。 詳細については、 `portal.properties` ファイルの [デフォルトユーザーパブリックレイアウト](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Default%20User%20Public%20Layouts) および [デフォルトユーザープライベートレイアウト](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts) セクションを参照してください。

| **注：** デフォルトでは、ユーザーは自分のページとアプリケーションを変更できます。個人サイト。 ただし、管理者は変更可能な部分をカスタマイズできます。を削除することによる@product@の権限システムを介した個人サイトの保護|役割から。 すべてのLiferayユーザーが何かを変更できないようにするには、|を削除します。ユーザー役割からの関連する権限。
