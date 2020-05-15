---
header-id: installing-liferay-syncs-prerequisites
---

# Liferay Syncの前提条件のインストール

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Liferay Syncには、 [Liferay Marketplace](https://web.liferay.com/marketplace)の *Liferay CE Sync Connector* アプリが必要です。 このアプリは、@product@インスタンスで同期を有効にして構成します。 たとえば、インスタンス全体またはサイトごとに同期を無効にすることができます。 同期はすべてのサイトでデフォルトで有効になっていることに注意してください。

Marketplaceアプリをインストールする手順については、参照 [のLiferay Marketplaceの文書](/docs/7-1/user/-/knowledge_base/u/using-the-liferay-marketplace)。

| **注：** Syncに必要なLiferay Sync Securityモジュールが含まれており、| @product@ではデフォルトで有効になっています。 |を確認することで、これを確認できます。 `SYNC_DEFAULT` および `SYNC_TOKEN` エントリが *コントロールパネルで有効になっている* →| *構成* → *サービスアクセスポリシー*。

Sync Connectorのデフォルト設定を使用したいが、すべてのサイトでSyncが有効になっている場合は、同期の設定に関する以下の記事をスキップできます。 ただし、インストールして同期デスクトップとモバイルクライアントを設定するには、あなたのユーザーを誘導する前に、 **ことを確認してください読むには** 誤ってファイルの削除を防止し、同期のセキュリティを確保するには、このガイドの記事を。 また、必要があります **ユーザーに警告** 偶発的なデータ損失の可能性について。
