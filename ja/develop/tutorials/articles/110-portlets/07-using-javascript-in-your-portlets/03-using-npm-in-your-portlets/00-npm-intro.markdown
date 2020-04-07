---
header-id: using-npm-in-your-portlets
---

# ポートレットでnpmを使用する

[TOC levels=1-4]

npmは強力なツールであり、フロントエンド開発にはほとんど必要です。 通常の日常のワークフローでポートレットを開発しながら、npmをJavaScriptパッケージマネージャーツールとして（npmおよびnpmパッケージを含む）使用できます。

デプロイされたポートレットは、 [Liferay AMD Loader](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay) を活用してJavaScriptモジュールを共有し、同じページ上のポートレット間でモジュールを解決する際にセマンティックバージョニングを利用します。 liferay-npm-bundlerは、Liferay AMD Loader用のnpmモジュールの準備に役立ちます。

チュートリアルのこのセクションでは、npmベースのポートレットプロジェクトをセットアップする方法について説明します。
