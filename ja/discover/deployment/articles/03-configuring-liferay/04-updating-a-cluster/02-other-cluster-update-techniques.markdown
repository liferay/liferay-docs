---
header-id: other-cluster-update-techniques-ja
---

# ブルーグリーンデプロイ

[TOC levels=1-4]

ブルーグリーンは、運用環境（*青い*環境）を複製 し、ソフトウェアとデータを変更してその複製（*緑色*の環境）を変更する配置手法です。グリーン環境のテストに成功したら、ユーザーをブルー環境からグリーン環境に切り替えます。ブルーグリーンでシステムの停止時間を削除できます。

データスキーマとデータ変更には特別な注意が必要です。既存のコードとの互換性を損なうカスタムプラグイン/モジュールデータスキーマの変更は、複数のリリースにわたって導入する必要があります。これにより、古い列が不要になるまで、古い列と新しい列にデータを移行して維持します。

データとスキーマの変更には、次の手順が必要です。

1. 新しい列を作成します。

2. データを新しい列にコピーします。

3. 古い列がどのクラスタノードでも使用されなくなるまで、両方の列を維持します。

4. 次のリリースで列を削除します。

詳細については、以下のブルーグリーンデプロイに関する記事を参照してください。

- [BlueGreenDeployment](http://martinfowler.com/bliki/BlueGreenDeployment.html)

- [Implementing Blue-Green Deployments with AWS](https://www.thoughtworks.com/insights/blog/implementing-blue-green-deployments-aws)

## 関連トピック

[Rolling Restarts](/discover/deployment/-/knowledge_base/7-1/using-rolling-restarts-ja)

[@product@ Clustering](/discover/deployment/-/knowledge_base/7-1/liferay-clustering-ja)

[Maintaining @product@](/discover/deployment/-/knowledge_base/7-1/maintaining-liferay-ja)

[Implementing Data Upgrades](/develop/tutorials/-/knowledge_base/7-1/data-upgrades)
