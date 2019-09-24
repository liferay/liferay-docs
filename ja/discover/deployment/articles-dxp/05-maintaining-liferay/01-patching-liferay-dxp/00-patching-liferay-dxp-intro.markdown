---
header-id: patching-liferay-ja
---

# @ product @へのパッチ適用

[TOC levels=1-4]

私たちは常に、何も問題のない、完璧な状態ですべての@ product @をリリースできるよう努力していますが、現実は目標としていたほど完璧なリリースができないこともあります。しかし、それに対する解決策も用意してあります。すべての@ product @バンドルには、フィックスパックとホットフィックスという、2種類のパッチのインストールを処理するパッチングツールが含まれています。

| **重要：**定期的に必ず@product@のインストールとデータベースをバックアップするようにしてください。（特にパッチを適用する前に）（パックアップに関する詳細はこちら：[back up your @product@ installation and database](/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation)）パッチングツールはコード変更をインストールし、そしてこれらのいくつかは（必要ならば）立ち上げ時に自動的にデータを変更します。
| 
|  特定のフィックスパック（サービスパック）には、データ/スキーマの[micro changes](/develop/tutorials/-/knowledge_base/7-1/meaningful-schema-versioning#micro-change-examples)を含めることができます。しかしこれらは オプションで元に戻すことができます。モジュールのアップグレードとそれらに含まれるあらゆる小さな変更は、デフォルトでサーバーの立ち上げ時に適用されます。もしくはマニュアルで[autoUpgrade`property`をdisable](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade#configuring-module-upgrades)できます。
| サーバーの立ち上げ時に、コアの小さな変更のすべてがスキップされます。その代わりに、[upgrade tool](/discover/deployment/-/knowledge_base/7-1/upgrading-to-liferay-71)を使ってサーバの立ち上げ前にそれを適用できます。
| 

| ** 注：**[Patching a cluster](/discover/deployment/-/knowledge_base/7-1/updating-a-cluster)は他に考慮しなければならない点がいくつかあります。
