# アップグレード前に - プロセスのスピードアップのために[](id=pre-upgrade-speed-up-the-process)

@product@をアップグレードする際の最も複雑な手順は、データベースを古いバージョンから新しいバージョンにアップグレードするプロセスを実行することです。データを新しい形式に再構成するには、時間がかかります。

本番環境をアップグレードする前にいくつかの手順を実行することで、このプロセスを短縮できます。手順の概要は以下のとおりです。

1. [最新の完全バックアップ](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)を、本番環境から本番以外の環境にコピーします。 この環境では、残りの手順で説明しているように、データベースを分析してアップグレードをテストできます。
2. [データベースを調べます。](#analyzing-your-database-step-2)
3. [未使用のコンテンツを削除するには、LiferayのAPIを使用してください](#remove-unused-data-from-the-database-using-liferays-api-step-3)。
4. [本番以外の環境でアップグレードプロセスを実行します。](#execute-the-upgrade-process-step-4)
5. 最も時間がかかったプロセスのアップグレードログを確認します。
6. 最も時間がかかったアップグレードプロセスから未使用のコンテンツを削除します。潜在的な問題が見つかった場合は、それを分析し、支援が必要な場合はコミュニティに連絡してください。エンタープライズサブスクリプションをお持ちの場合は、サポートチケットを開いて、Liferayに分析結果の確認を依頼してください。
7. 必要に応じて手順4、5、および6を繰り返します。
8. [未使用のコンテンツをプロダクションから削除します](#remove-unused-objects-from-production-step-8)。

以下のセクションでは、上記のより詳細な手順について説明します。

## データベースの調査（手順2）[](id=analyzing-your-database-step-2)

本番以外の環境にある本番環境のデータベースのコピーを調べるにあたって、最も重要なことは次のとおりです。

- テーブルごとのレコード
- テーブルあたりのサイズ（オプション）

これらの値が大きいほど、アップグレードプロセスにかかる時間が長くなります。

データベースエンジンはこの情報をさまざまな方法で表示します。バックアップデータを本番以外のデータベースにインポートした結果、各テーブルのサイズと行数（レコード）が表示されることがあります。

たとえば、一般的なデータベースインポートからの出力は次のようになります。

    Processing object type SCHEMA\_EXPORT/TABLE/TABLE\_DATA
    
    imported "LIFERAY"."JOURNALARTICLE" 13.33 GB 126687 rows
    
    imported "LIFERAY"."RESOURCEPERMISSION" 160.9 MB 1907698 rows
    
    imported "LIFERAY"."PORTLETPREFERENCES" 78.13 MB 432285 rows
    
    imported "LIFERAY"."LAYOUT" 52.05 MB 124507 rows
    
    imported "LIFERAY"."ASSETENTRY" 29.11 MB 198809 rows
    
    imported "LIFERAY"."MBMESSAGE" 24.80 MB 126185 rows
    
    imported "LIFERAY"."PORTALPREFERENCES" 4.091 MB 62202 rows
    
    imported "LIFERAY"."USER\_" 17.32 MB 62214 rows
    
    imported "LIFERAY"."USERS\_ROLES" 15.63 MB 1117225 rows
    
    imported "LIFERAY"."LAYOUTSET" 11.50 MB 124442 rows
    
    imported "LIFERAY"."MBTHREAD" 11.99 MB 126184 rows
    
    imported "LIFERAY"."COUNTER" 6.161 MB 123699 rows
    
    imported "LIFERAY"."CONTACT\_" 5.233 MB 62214 rows
    
    imported "LIFERAY"."GROUP\_" 3.772 MB 62221 rows
    
    imported "LIFERAY"."ASSETTAGPROPERTY" 1.460 MB 21417 rows
    
    imported "LIFERAY"."MBDISCUSSION" 3.138 MB 126179 rows
    
    imported "LIFERAY"."JOURNALARTICLEIMAGE" 1.015 MB 22523 rows
    
    imported "LIFERAY"."USERS\_GROUPS" 897.1 KB 62218 rows
    
    imported "LIFERAY"."IMAGE" 480.9 KB 13492 rows
    
    imported "LIFERAY"."JOURNALSTRUCTURE" 135.9 KB 12 rows
    
    imported "LIFERAY"."JOURNALTEMPLATE" 230.6 KB 44 rows
    
    imported "LIFERAY"."DLFILEENTRY" 195.3 KB 1028 rows
    
    imported "LIFERAY"."DLFILEVERSION" 196.8 KB 1350 rows
    
    imported "LIFERAY"."JOURNALARTICLERESOURCE" 272.6 KB 4665 rows
    
    imported "LIFERAY"."JOURNALCONTENTSEARCH" 107.1 KB 2038 rows
    
    imported "LIFERAY"."ASSETENTRIES\_ASSETTAGS" 90.89 KB 6118 rows
    
    imported "LIFERAY"."COUNTRY" 15.33 KB 227 rows
    
    imported "LIFERAY"."DLFILERANK" 12.83 KB 135 rows
    
    imported "LIFERAY"."QUARTZ\_JOB\_DETAILS" 19.70 KB 2 rows
    
    imported "LIFERAY"."QUARTZ\_TRIGGERS" 11.64 KB 1 rows
    
    imported "LIFERAY"."TICKET" 147.9 KB 1666 rows
    
    imported "LIFERAY"."ASSETTAG" 74.31 KB 908 rows
    
    imported "LIFERAY"."CLASSNAME\_" 13.36 KB 148 rows
    
    imported "LIFERAY"."COMPANY" 9.210 KB 1 rows
    
    imported "LIFERAY"."RATINGSSTATS" 71.67 KB 2566 rows
    
    imported "LIFERAY"."ACCOUNT\_" 11.35 KB 1 rows
    
    imported "LIFERAY"."ANNOUNCEMENTSDELIVERY" 13.85 KB 192 rows
    
    imported "LIFERAY"."ASSETCATEGORY" 11.17 KB 2 rows
    
    imported "LIFERAY"."ASSETENTRIES\_ASSETCATEGORIES" 5.539 KB 1 rows
    
    imported "LIFERAY"."ASSETTAGSTATS" 20.82 KB 666 rows
    
    imported "LIFERAY"."ASSETVOCABULARY" 11.03 KB 6 rows
    
    imported "LIFERAY"."DLFOLDER" 35.81 KB 195 rows
    
    imported "LIFERAY"."LAYOUTPROTOTYPE" 7.359 KB 1 rows
    
    imported "LIFERAY"."QUARTZ\_CRON\_TRIGGERS" 6.367 KB 1 rows
    
    imported "LIFERAY"."RESOURCEACTION" 42.84 KB 806 rows
    
    imported "LIFERAY"."SERVICECOMPONENT" 25.28 KB 1 rows
    
    imported "LIFERAY"."SOCIALACTIVITY" 45.42 KB 734 rows
    
    imported "LIFERAY"."WEBDAVPROPS" 7.820 KB 2 rows
    
    imported "LIFERAY"."WIKIPAGE" 14.68 KB 1 rows
    
    imported "LIFERAY"."EXPANDOTABLE" 6.656 KB 9 rows
    
    imported "LIFERAY"."IGFOLDER" 10.17 KB 8 rows
    
    imported "LIFERAY"."IGIMAGE" 18.61 KB 61 rows
    
    imported "LIFERAY"."JOURNALFEED" 24.22 KB 38 rows
    
    imported "LIFERAY"."LISTTYPE" 9.554 KB 63 rows
    
    imported "LIFERAY"."MBCATEGORY" 10.75 KB 1 rows
    
    imported "LIFERAY"."MBMAILINGLIST" 15.96 KB 1 rows
    
    imported "LIFERAY"."MBMESSAGEFLAG" 7.304 KB 1 rows
    
    imported "LIFERAY"."PASSWORDPOLICY" 19.06 KB 1 rows
    
    imported "LIFERAY"."PORTLET" 11.15 KB 136 rows
    
    imported "LIFERAY"."QUARTZ\_LOCKS" 5.117 KB 5 rows
    
    imported "LIFERAY"."QUARTZ\_SCHEDULER\_STATE" 6 KB 1 rows
    
    imported "LIFERAY"."REGION" 15.27 KB 316 rows
    
    imported "LIFERAY"."RELEASE\_" 8.281 KB 1 rows
    
    imported "LIFERAY"."ROLE\_" 17.17 KB 115 rows
    
    imported "LIFERAY"."SHARD" 6.382 KB 1 rows
    
    imported "LIFERAY"."SUBSCRIPTION" 8.648 KB 2 rows
    
    imported "LIFERAY"."USERGROUPROLE" 5.992 KB 2 rows
    
    imported "LIFERAY"."USERS\_ORGS" 5.531 KB 2 rows
    
    imported "LIFERAY"."VIRTUALHOST" 6.398 KB 1 rows
    
    imported "LIFERAY"."WIKINODE" 9.453 KB 2 rows
    
    imported "LIFERAY"."WIKIPAGERESOURCE" 6.382 KB 1 rows

このデータベースインポートの例では、以下の項目が際立っています。

- *JOURNALARTICLE*のテーブルがデータベースのサイズの98％を占めていること。
- 多くの*RESOURCEPERMISSION*レコードがあること。
- 多くの*PORTLETPREFERENCES*レコードがあること。

テーブルレコードは@product@オブジェクトを反映しています。APIを使用して不要になったオブジェクトを削除すると、各オブジェクトのデータレコードが削除されるだけでなく、関連の不要なオブジェクト（およびそれらのデータレコード）も削除されます。たとえば、不要な`グループ`オブジェクトを削除すると、関連する不要なレイアウト、ジャーナル記事なども削除されます。これにより、アップグレードに必要なレコード数が減り、アップグレードが高速化されます。

## LiferayのAPIを使用した未使用オブジェクトの削除（手順3）[](id=remove-unused-data-from-the-database-using-liferays-api-step-3)

データベースを分析したところ、大規模な、または大量のレコードを含むテーブルが明らかになりました。これらのテーブルに関連付けられているオブジェクトを調べることで、削除できる不要なオブジェクトを見つけることをお勧めします。また、不要なオブジェクトを探すためにいくつかの共通領域（下記）があります。

+$$$

**重要**：オブジェクトは、LiferayのAPI（[コアAPI](@platform-ref@/7.1-latest/javadocs/)と[アプリAPI](@app-ref@)）のみを使用してを削除してください。これらのAPIアカウントは@product@オブジェクト間の関係を考慮しているためです。コントロールパネルのスクリプトコンソールまたは作成したポートレットを介して、APIを呼び出すことができます。

レコードを削除するために、データベースで直接SQLを実行しないでください。SQLがオブジェクトの関係を見逃して、孤立したオブジェクトやパフォーマンスの問題を引き起こす可能性があります。

$$$

不要なオブジェクトを見つけるための共通領域は次のとおりです。

- **サイト**：不要なサイトを削除してください。サイトを削除したら、その関連データを削除します。

   - レイアウト

   - ポートレットプリファレンス

   - ファイルエントリ（ドキュメントライブラリオブジェクト）

   - アセットエントリ

   - タグ

   - 語彙とカテゴリー

   - Expandoフィールドとその値

   - `ResourcePermission`オブジェクト

   - （そして、その他すべて）

- **インスタンス**：未使用のインスタンスはまれですが、階層の最上位オブジェクトなので、それらのオブジェクトを削除するとアップグレードを大幅に最適化できます。

   - サイト（およびそのすべての関連コンテンツ）

   - ユーザー

   - 役割

   - 組織

   - グローバル`ResourcePermission`オブジェクト

- **中間Webコンテンツバージョン：**@product@は、変更（翻訳を含む）の後に新しいWebコンテンツバージョンを生成します。不要なバージョンを削除することを検討してください。たとえば、Journal Articleを削除すると、コンテンツの一部である画像ファイル（`JournalArticleImage`）などの関連オブジェクトも削除されます。不要なイメージファイルを削除すると、データベースとファイルシステムのスペースが解放されます。

- **ドキュメントバージョン**：Journal Articlesと同様に、中間のドキュメントバージョンが必要ない場合は削除します。これにより、データベースとファイルシステムの両方のスペースを節約でき、スペースをアップグレードする必要がなくなります。

- **レイアウト：**レイアウトはサイトページのことで、ポートレットプリファレンス、権限、アセット、レイティングなどの他のエンティティに関連するため、アップグレードのパフォーマンスに影響します。不要なレイアウトは削除してください。

- **役割**：不要な役割を削除します。それらを削除すると、関連する`ResourceBlockPermission`オブジェクトおよび`ResourcePermission`オブジェクトも削除されます。

- **ユーザー：**もうアクティブではないユーザーがいる場合は、削除します。

- **語彙**：未使用の語彙を削除します。語彙を削除すると、そのカテゴリーも削除されます。

- **孤立したデータ**：何にも接続されていない未使用のオブジェクトを確認します。以下に、いくつかの例を示します。

   - ファイルシステムデータのない`DLFileEntries`。

   - すでに存在しない役割、レイアウト、ユーザー、ポートレットインスタンスなどに関連付けられた`ResourcePermission`オブジェクト。

   - もう存在しないポートレットまたはレイアウトに関連付けられている`PortletPreference`オブジェクト。多数の組み込みポートレットがある環境では一般的です。これらのポートレットインスタンスはライフサイクルが異なり、ポートレットがテンプレートから削除されても削除されません。

不要なオブジェクトを削除したら、変更内容をテストします。

## アップグレード処理の実行（手順4）[](id=execute-the-upgrade-process-step-4)

本番以外の環境をアップグレードして、どのアップグレードプロセスに最も時間がかかるかを確認します。それぞれの@product@のアップグレードプロセスは、所要時間を記録します。アップグレードログは下記のようになります。

    21:42:45,422 INFO \[main\]\[UpgradeProcess:83\] Upgrading com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings
    
    21:42:45,423 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry
    
    21:42:47,154 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsEntry in 1731 ms
    
    21:42:47,154 INFO \[main\]\[LoggingTimer:70\] Starting com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats
    
    21:44:10,069 INFO \[main\]\[LoggingTimer:38\] Completed com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings\#upgradeRatingsStats in 82915 ms
    
    21:44:10,070 INFO \[main\]\[UpgradeProcess:98\] Completed upgrade process com.liferay.portal.upgrade.v7\_0\_0.UpgradeRatings in 84648ms

継続時間（ミリ秒）によって、最も時間がかかるプロセスを見つけやすくなります。長いアップグレードプロセスに関連する不要なオブジェクトを検索することを検討します。もう一度、LiferayのAPIを使ってそれらを削除し、変更をテストします。

**注**：[アップグレードプロセスがどのように作成されるか](/develop/tutorials/-/knowledge_base/7-1/creating-an-upgrade-process-for-your-app)を学ぶことで、データをよりよく理解することができます。

## 未使用のオブジェクトを本番環境から削除する（手順8）[](id=remove-unused-objects-from-production-step-8)

本番以外の環境から未使用のオブジェクトを削除して変更をテストしたので、LiferayのAPIを使用して本番環境から同じオブジェクトを削除できます。アップグレード前にオブジェクトを本番環境から削除して変更をテストすることで、変更がアップグレードプロセスと関連していないことが分かるため、アップグレードで生じた問題をより簡単にトラブルシューティングできます。アップグレードを実行している段階でもこの手順を行うことのもう1つの利点は、本番環境のパフォーマンスが向上し、維持がしやすくなることです。

## 結論[](id=conclusion)

@product@から使用されていないオブジェクトを削除することで、アップグレード時間を短縮し、新しいバージョンでのサーバーのパフォーマンスを向上させることができます。

アップグレード前にインストールを最適化するために時間を割くことで、時間を節約し、インストールを円滑に実行できます。
