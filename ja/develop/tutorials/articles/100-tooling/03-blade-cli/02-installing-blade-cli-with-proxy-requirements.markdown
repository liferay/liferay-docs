---
header-id: setting-blade-cli-proxy-requirments
---

# プロキシ要件を持つBlade CLIのインストール

[TOC levels=1-4]

プロキシサーバーの要件があり、Blade CLIを使用する場合は、JPMを使用してhttp(s)プロキシを設定する必要があります。作業を始める前に、Liferay Project SDKインストーラーを使用して、JPMとBlade CLIをインストールしたことを確認してください。詳細については、[Installing Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli)のチュートリアルを参照してください。

Blade CLIとJPMをインストールしたら、以下のコマンドを実行してBlade CLIのプロキシ要件を設定します。

    jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm

 これで、JPMを使用してプロキシ設定でBlade CLIが設定できました。
