---
header-id: managing-module-projects-with-blade-cli
---

# Blade CLIを使用したLiferayサーバーの管理

[TOC levels=1-4]

このチュートリアルでは、Blade CLIを使用してLiferayサーバーを管理する方法を説明します。
たとえば、Blade CLIを使用すると、Liferayサーバーのインストール、起動、停止、検査、および変更ができます。

自分でBlade CLIコマンドをテストする前に、Liferay Workspaceに入っていること、ワークスペースにバンドルがインストールされて、設定ができていることを確認してください。
Liferay WorkspaceへのLiferayサーバーのインストールの詳細については、[Creating a Liferay Workspace with Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/creating-a-liferay-workspace-with-liferay-ide)のセクションを参照してください。このサブセクションでは、以下のBlade CLIコマンドについて説明します。

- `server`
- `sh`

サーバーと対話する際に、まず必要となるのがサーバーのオン/オフの切り替えです。`server`サブコマンドを使用すると、これを実行することができます。Liferayサーバー（TomcatまたはWildfly/JBoss）をオンにするには、以下を実行します。

    blade server start

これにより、サーバーがバックグラウンドで起動します。`-t`フラグを追加することにより、ログの監視ができます。フォアグラウンドでサーバーを起動する場合は、`blade server run`を実行します。さらに、デバッグモードでサーバーを起動する場合は、`-d`フラグを追加します。

デバッグモードをカスタマイズするには、`-p`タグを追加してカスタムリモートデバッグポート（デフォルトはTomcatで`8000`、Wildflyで`8787`）を設定するか、ブール値の`-s`タグを追加するか、またはその両方を行って、デバッガーが接続されるまでの間、起動済みのサーバーを一時停止するかどうかを設定します。

サーバーを起動したら、Felix Gogoシェルを使用してサーバーへのアクセスを提供する`sh`コマンドを使用することで、サーバーのOSGiコンテナを調べることができます。
たとえば、前のセクションからアプリケーションを正常にデプロイしたかどうかを確認するには、以下を実行できます。

    blade sh lb

出力には、サーバーのOSGiコンテナでアクティブ/インストールされているモジュールの長いリストが表示されます。

![図1：Blade CLIは、Gogoシェルスクリプトにアクセスして`lb`コマンドを実行します。](../../../images/blade-sh.png)

`blade sh`を使用して、どんなGogoコマンドも実行することができます。このコマンドでは、[開発者モード](/docs/7-1/tutorials/-/knowledge_base/t/using-developer-mode-with-themes#setting-developer-mode-for-your-server-using-portal-developerproperties)を有効にする必要があります。開発者モードは、 デフォルトでは[Liferay Workspace](/docs/7-1/tutorials/-/knowledge_base/t/liferay-workspace)で有効になっています。このツールの詳細については 、[Using the Felix Gogo Shell](/docs/7-1/reference/-/knowledge_base/r/using-the-felix-gogo-shell)のセクションを参照してください。

サーバーの電源を切るには、以下を実行します。

    blade server stop

 Blade CLIを使用して@product@と対話する方法は、これで以上です。
