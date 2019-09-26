---
header-id: installing-x-pack
---

# Liferay Enterprise Searchのインストール

[TOC levels=1-4]

X-Packは、 Elasticsearchクラスターを保護および監視するための[Elasticsearch extension](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-xpack.html)です。Elasticsearchを使用する場合は、X-Packで保護する必要があります。X-Packのセキュリティ機能には、Elasticsearchクラスターのデータへのアクセスの認証、Elasticsearchの内部および外部通信の暗号化などがあります。これらはほとんどのプロダクションシステムに必要なセキュリティ機能です。
Liferay Enterprise Search Premiumのサブスクリプションを使用すると、@product@用の2つのX-Packコネクタ（監視とセキュリティ用）にアクセスできます。Liferay Enterprise Search Standardサブスクリプションを使用すると、モニタリング統合が可能になります。詳細はこちらまでお問い合わせください：[Liferay's Sales department for more information](https://www.liferay.com/contact-us#contact-sales)

以下は@product@でX-Packを使用するための概要です：



1. まず、[Enterprise Search subscription](https://help.liferay.com/hc/en-us/articles/360014400932)を入手してください。



2. 購入したX-Packコネクタをダウンロードしてインストールします。購読レベルに対応するコネクタにアクセスします：



   [Enterprise Search Standard](https://customer.liferay.com/group/customer/downloads?_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_formDate=1543422323440&p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_product=enterpriseSearchStandard&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileType=product)

   [Enterprise Search Premium](https://customer.liferay.com/group/customer/downloads?_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_formDate=1543422331595&p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_product=enterpriseSearchPremium&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileType=product)

3. 適切な資格情報と暗号化情報を使用してコネクタを設定します。



4. Elasticsearchを再起動します。これらの手順では、クラスタ全体を再起動する必要があります。



これらの指示に従うと、@product@と自由に通信するElasticsearchの実用的なインストール環境が得られます。Elasticのドキュメントでは、[X-Pack](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-security.html)のその他の設定オプション、機能、およびアーキテクチャについて説明しています 。



必要に応じて、セキュリティや監視を設定できます。
