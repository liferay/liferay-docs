---
header-id: using-amazon-simple-storage-service
---

# Amazon Simple Storage Serviceの使用

[TOC levels=1-4]

AmazonのSimple Storage Service（S3）は、ドキュメントとメディアに使用できるクラウドベースのストレージソリューションです。必要なのはアカウントだけで、ドキュメントをすべてのノードからクラウドにシームレスに保存できます。

サービスにサインアップするとき、Amazonからアカウントにリンクする固有のキーが割り当てられます。Amazonのインターフェースでは、リージョン別に最適化されたデータの「バケット」が作成できます。

ファイルストレージでS3アカウントを使用するように@product@を設定する手順は次のとおりです。

1. Amazon S3が作動するには、アプリケーションサーバーからの`SAXParser`が必要です。Apache Tomcatのようなアプリケーションサーバーを使用している場合は、このプロパティを`system-ext.properties`ファイルに含める必要があります。

       org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser
   
2. `system-ext.properties`ファイルを@product@インストールのクラスパスにあるフォルダに置いてください（例：`/WEB-INF/classes/`）。

3. [Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)フォルダの`portal-ext.properties`ファイルに、以下のプロパティを設定します。

       dl.store.impl=com.liferay.portal.store.s3.S3Store
   
4. @product@を再起動します。

5. コントロールパネルから、*[設定]* → *[システム設定]* → *[ファイルストレージ]*へ移動します。

6. *S3ストア設定*画面で、ストアを設定します。

@product@インスタンスは、Amazon S3ストアを使用しています。

クラスター内でS3ストアを使用するには、次の手順に従います。

1. *S3ストア設定*画面から[`.config`ファイル](/discover/portal/-/knowledge_base/7-1/understanding-system-configuration-files)に設定をエクスポートします。

2. `.config`ファイルを各ノードの`[Liferay Home]/osgi/configs`フォルダにコピーします。

3. `portal-ext.properties`を各ノードの[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home)フォルダにコピーします。

4. `system-ext.properties`（使用している場合は）を各ノードのアプリケーションサーバークラスパス内にあるフォルダにコピーします。

5. ノードで@product@を再起動します。

@product@は、クラスター全体でAmazon S3ストアを使用しています。

| **警告：**ファイルシステムベースのストアを使用するドキュメントライブラリでデータベーストランザクションのロールバックが発生した場合、トランザクションの開始以降に発生したファイルシステムの変更は元に戻されません。ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に矛盾が生じる可能性があり、手動での同期が必要になる場合があります。DBStoreを除くすべてのストアは、この制限に対して脆弱です。

Amazonのサービスの使用に関する追加情報については、Amazon Simple Storageのドキュメントを参照してください。
