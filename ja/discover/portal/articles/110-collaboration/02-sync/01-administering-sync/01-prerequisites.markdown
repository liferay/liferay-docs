---
header-id: installing-liferay-syncs-prerequisites
---

# Liferay Syncの前提条件のインストール

[TOC levels=1-4]

Liferay Syncには、 [Liferay Marketplace](https://web.liferay.com/marketplace)の *Liferay CE Sync Connector* アプリが必要です。 このアプリは、@ product @インスタンスで同期を有効にして構成します。 たとえば、インスタンス全体またはサイトごとに同期を無効にすることができます。 同期はすべてのサイトでデフォルトで有効になっていることに注意してください。

Marketplaceアプリをインストールする手順については、参照 [のLiferay Marketplaceの文書](/docs/7-1/user/-/knowledge_base/u/using-the-liferay-marketplace)。

| **注：** Syncに必要なLiferay Sync Securityモジュールが含まれており、| @ product @ではデフォルトで有効になっています。 |を確認することで、これを確認できます。 `SYNC_DEFAULT` および `SYNC_TOKEN` エントリが *コントロールパネルで有効になっている* →| *構成* → *サービスアクセスポリシー*。

Sync Connectorのデフォルト設定を使用したいが、すべてのサイトでSyncが有効になっている場合は、同期の設定に関する以下の記事をスキップできます。 ただし、インストールして同期デスクトップとモバイルクライアントを設定するには、あなたのユーザーを誘導する前に、 **ことを確認してください読むには** 誤ってファイルの削除を防止し、同期のセキュリティを確保するには、このガイドの記事を。 また、必要があります **ユーザーに警告** 偶発的なデータ損失の可能性について。
