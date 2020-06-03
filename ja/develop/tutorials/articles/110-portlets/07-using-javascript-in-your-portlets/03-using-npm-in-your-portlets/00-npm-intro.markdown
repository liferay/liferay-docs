---
header-id: using-npm-in-your-portlets
---

# ポートレットでnpmを使用する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

npmは強力なツールであり、フロントエンド開発にはほとんど必要です。 通常の日常のワークフローでポートレットを開発しながら、npmをJavaScriptパッケージマネージャーツールとして（npmおよびnpmパッケージを含む）使用できます。

デプロイされたポートレットは、 [Liferay AMD Loader](/docs/7-1/tutorials/-/knowledge_base/t/loading-amd-modules-in-liferay) を活用してJavaScriptモジュールを共有し、同じページ上のポートレット間でモジュールを解決する際にセマンティックバージョニングを利用します。 liferay-npm-bundlerは、Liferay AMD Loader用のnpmモジュールの準備に役立ちます。

チュートリアルのこのセクションでは、npmベースのポートレットプロジェクトをセットアップする方法について説明します。
