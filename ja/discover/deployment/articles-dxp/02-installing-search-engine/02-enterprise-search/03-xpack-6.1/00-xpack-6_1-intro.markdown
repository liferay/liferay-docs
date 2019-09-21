---
header-id: installing-x-pack-6-1
---

# X-Packをインストールする（6.1）

X-Packは、 Elasticsearchクラスターを保護および監視するための[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/setup-xpack.html)です。Elasticsearchを使用する場合は、X-Packで保護する必要があります。X-Packのセキュリティ機能には、Elasticsearchクラスターのデータへのアクセスの認証、Elasticsearchの内部および外部通信の暗号化などがあります。

ほとんどのプロダクションシステムはこれらの機能を必要とします。Liferay Enterprise Search Premiumのサブスクリプションを使用すると、@product@用に2つのX-Packコネクタ（監視とセキュリティ）にアクセスできます。Liferay Enterprise Search Standardサブスクリプションを使用すると、モニタリング統合が可能になります。詳細はこちらまでお問い合わせください：[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales)

X-Packを使用して、Elasticsearchでインデックス付けされたデータを保護する方法の概要は次のとおりです：



1. まず、エンタープライズサーチサブスクリプションを入手してください。



2. X-PackをElasticsearchにインストールし、（参照：[Install X-Pack into Elasticsearch](https://www.elastic.co/guide/en/x-pack/6.1/installing-xpack.html))認証と[encryption](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-tls.html#configuring-tls)を必要とするように設定します。



3. [Liferay Connector to X-Pack Security](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search) をダウンロードしてインストールします。


   [Elastic Stack 6.x].

4. 適切な資格情報と暗号化情報を使用してX-Packコネクターを設定してください。

5. Elasticsearchを再起動します。

**重要：**これらの手順では、クラスタ全体の再起動が必要です。

これらのステップを完了すると、Elasticsearchインストールは@product@と自由に通信できるようになります。


+$$$

[Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/configuring-security.html)はX-Packアーキテクチャー、追加の設定オプションと特徴について説明します。

$$$
