---
header-id: setting-proxy-requirements-for-liferay-ide
---

# Liferay Dev Studioのプロキシ要件の設定

[TOC levels=1-4]

プロキシサーバーの要件があり、Liferay Dev Studioと連携するようにhttp(s)プロキシを設定する場合は、以下の手順に従ってください。

1. Eclipseの*[ウィンドウ]* → *[設定]* → *[一般]* → *[ネットワーク接続]*メニューに移動します。

2. *[Active Provider]*のドロップダウンセレクターを*[手動]*に設定します。

3. *[プロキシエントリ]*にあるフィールドをクリックして、*[編集]*ボタンを選択し、プロキシHTTPとHTTPSの両方を設定します。

   ![図1：Dev Studioの[ネットワーク接続]メニューでプロキシを設定できます。](../../../images/ide-network-connections.png)

4. 各スキーマ（HTTPおよびHTTPS）に対して、プロキシサーバーのホスト、ポート、および認証設定を入力します（必要な場合）。

   **注：**プロキシホストまたはポート設定の末尾にホワイトスペースを入れないでください。

5. プロキシエントリを設定したら、*[OK]* → *[OK]*とクリックします。

Dev StudioでLiferay Workspaceを使用している場合は、その環境のプロキシ設定も行う必要があります。詳細については、[Setting Proxy Requirements for Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/setting-proxy-requirements-for-liferay-workspace)を参照してください。

 これで、Dev Studioのプロキシ設定が正常に完了しました。

## 追加のプロキシ設定

*[ウィンドウ]* → *[設定]* → *[一般]* → *[ネットワーク接続]*を経由してプロキシ設定をした場合、Eclipseプラグインの中には`core.net`プロキシのインフラストラクチャを適切にチェックしないものがあります。したがって、追加のプロキシ設定をする必要がある場合があります。

これを行うには、Eclipseインストールに関連付けられている`eclipse.ini`ファイルを開き、次のエントリを追加します。

    -vmargs
    -Dhttp.proxyHost=www.somehost.com
    -Dhttp.proxyPort=1080
    -Dhttp.proxyUser=userId
    -Dhttp.proxyPassword=somePassword
    -Dhttps.proxyHost=www.somehost.com
    -Dhttps.proxyPort=1080
    -Dhttps.proxyUser=userId
    -Dhttps.proxyPassword=somePassword

ファイルを保存したら、Eclipseを再起動します。これで、追加のプロキシ設定が適用されます。
