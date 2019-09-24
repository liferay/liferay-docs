---
header-id: maintaining-liferay-ja
---

# @product@の維持

[TOC levels=1-4]

@product@をインストールしたら、円滑に動作させるためにしなければならないことがいくつかあります。ハードウェア障害に備えてインストールをバックアップすることによって、データを保護し、システムをすぐに正常な状態に戻すのに役立ちます。DXPをご利用の場合、システムに定期的にパッチをあてることで、実行中のインスタンスに最新のバグ修正が提供されます。

| @product-ver@を新しいGAバージョンにアップグレードするには、[データアップグレード](/discover/deployment/-/knowledge_base/7-1/upgrading-to-liferay-71-ja)が必要です。
| 必要なデータのアップグレードをすべて実行するまで（ある場合）、@product@の起動に失敗し、以下のようなメッセージが表示されます。
| 
|     2018-11-05 17:22:35.025 INFO  [main][StartupHelper:72] There are no patches installed
|     You must first upgrade to @product@ 7101
|     2018-11-05 17:22:35.098 ERROR [main][MainServlet:277] java.lang.RuntimeException: You must first upgrade to @product@ 7101

次の記事では、システムの適切な動作環境を維持する方法について説明します。
