---
header-id: configuring-asset-publisher-subscriptions
---

# Asset Publisherサブスクリプションの構成

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

Asset Publisherアプリケーションは、RSSサブスクリプションと電子メールサブスクリプションの2種類のサブスクリプションをサポートしています。 サブスクリプションを有効にするには、Asset Publisherのオプションアイコンをクリックし、[ *構成*]を選択します。 設定ウィンドウで、[セットアップ]タブの[サブスクリプション]タブを開きます。 2つのオプションがあります： *RSSサブスクリプションを有効にする* および *電子メールサブスクリプションを有効にする*。

RSSサブスクリプションを有効にすると、Asset Publisherが表示するように構成されているすべてのアセットへのリンクを含むRSSフィードが作成されます。 このRSSフィードへのリンクは、Asset Publisherアプリケーションの下部に表示されます。 このオプションは、 *Dynamic* Asset Selectionが構成されている場合にのみ使用できます。

![図1：Asset PublisherアプリケーションでRSSサブスクリプションが有効になっている場合、Asset PublisherのRSSフィードへのリンクが表示されます。 ユーザーは、好みのRSSリーダーを使用してAsset PublisherのRSSフィードを購読できます。](../../../../images/asset-publisher-rss.png)

メール購読を有効にすると、Asset Publisherに *購読* リンクが追加されます。 新しく公開されたアセットの通知を希望するユーザーは、このリンクをクリックしてサブスクリプションリストに追加できます。 @product@は定期的に新しいアセットをチェックし、サブスクライブしたユーザーに新しいアセットについて通知するメールを送信します。 デフォルトでは、Liferayはこのチェックを24時間ごとに実行します。
