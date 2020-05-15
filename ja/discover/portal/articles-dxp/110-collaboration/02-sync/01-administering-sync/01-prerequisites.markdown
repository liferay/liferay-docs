---
header-id: installation-liferay-syncs-prerequisites
---

# Liferay Syncの前提条件のインストール

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOCレベル= 1-4]

Liferay Syncでは、Liferay Marketplaceから次のアプリをインストールする必要があります。 必ず次の順序でインストールしてください。

1.  [Liferay Connector to OAuth 1.0a](https://web.liferay.com/marketplace/-/mp/application/45261909)：@product@インストールでOAuth 1を有効にします（SyncはOAuth 2と互換性がないことに注意してください）。 あなたは **必見** 、このアプリのインストール **前** インストール *のLiferay Syncのコネクタ* アプリを。そうしないと、ポータルを開始できません。

2.  [Liferay同期コネクタ](https://web.liferay.com/marketplace/-/mp/application/31709100)：@product@インスタンスで同期を有効にして構成できます。 たとえば、インスタンス全体またはサイトごとに同期を無効にすることができます。 @product@インスタンスのすべてのサイトで、デフォルトで同期が有効になっていることに注意してください。 あなたは **必見** 、このアプリのインストール **後** インストール *のOAuth 1.0aのにLiferayのコネクタを* アプリを。そうしないと、ポータルを開始できません。

| **警告：** インストールする前に *Liferay Sync Connector* をインストールする場合| *Liferay Connector to OAuth 1.0a*、ポータルを開始できません。

Marketplaceアプリをインストールする手順については、参照 [のLiferay Marketplaceの文書](/docs/7-1/user/-/knowledge_base/u/using-the-liferay-marketplace)。

| **注：** Syncに必要なLiferay Sync Securityモジュールが含まれており、|デフォルトで有効になっています。 これを確認するには、 `SYNC_DEFAULT` および| `SYNC_TOKEN` エントリが *コントロールパネルで有効になっています* → *構成* | → *サービスアクセスポリシー*。

Sync Connectorのデフォルト設定を使用する必要があり、すべてのサイトでSyncを有効にして問題がない場合は、同期の設定に関する以下の記事をスキップできます。 ただし、インストールして同期デスクトップとモバイルクライアントを設定するには、あなたのユーザーを誘導する前に、 **ことを確認してください読むには** 誤ってファイルの削除を防止し、同期のセキュリティを確保するには、このガイドの記事を。 また、必要があります **ユーザーに警告** 偶発的なデータ損失の可能性について。

## SSOを使用するための同期の構成

SSO（シングルサインオン）ソリューションを使用する場合、同期がSSOサーバーにリダイレクトされずに次のURLにアクセスできることを確認する必要があります。 これらのURLに直接アクセスしないと、同期は機能しません。 したがって、次のURLをホワイトリストに登録する必要があります。

    http（s）：//<portal-address>/ c / portal / oauth / *
    http（s）：//<portal-address>/api/jsonws/sync-web.*
    http（s）：//<portal-address>/ sync-web / *

たとえば、インストールのアドレスが `https://www.joesblog.com`場合、次のURLをホワイトリストに登録する必要があります。

    https://www.joesblog.com/c/portal/oauth/*
    https://www.joesblog.com/api/jsonws/sync-web.*
    https://www.joesblog.com/sync-ウェブ/*

Syncは、OAuthを介した通信に最初のURLで指定されたパスを使用し、残りのURLで指定されたパスを@product@インストールとの通常の通信に使用します。

また、同期コネクタアプリでOAuthを有効にする必要があります。次の記事では、これと、同期の構成方法に関するその他の情報について説明します。
