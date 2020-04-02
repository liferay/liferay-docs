---
header-id: installing-a-server-in-intellij-idea
---

# IntelliJ IDEAでのサーバーのインストール

[TOC levels=1-4]

IntelliJにLiferayサーバーをインストールするのは簡単です。ほんの数ステップで、サーバーを稼働させることができます。

| **注：**TomcatおよびWildflyは、IntelliJにインストールできる| Liferayアプリケーションサーバーのバンドルのみをサポートしています。

サーバーをインストールするには、以下の手順に従ってください。

1. Liferay Workspaceを右クリックして、*[Liferay]* → *[InitBundle]*を選択します。

   これにより、ワークスペースの`gradle.properties`ファイルで指定された@product@バンドルがダウンロードされます。`liferay.workspace.bundle.url`プロパティをアップデートすることにより、デフォルトのバンドルを変更できます。たとえば、これはデフォルトのバンドルバージョンやタイプ（例: Wildfly）をアップデートするために必要です。ダウンロードしたバンドルは、ワークスペースの`bundles`フォルダに保存されます。

2. 画面右上にある[設定]のドロップダウンメニューに移動し、*[設定の編集]*を選択します。ここから、サーバーの実行およびデバッグ設定を設定できます。

   ![図1：サーバーのドロップダウンメニューから選択できるオプションがいくつかあります。](../../../images/intellij-server-dropdown.png)

3. *[新しい設定の追加]*というボタン（![Add Config](../../../images/icon-intellij-add-config.png)）をクリックし、*[Liferayサーバー]*を選択します。

4. サーバーに適切な名前を付け、必要に応じて他の設定も変更します。そして、*[OK]*を選択します。

   ![図2：[Run/Debug Configurations]メニューで、Liferayサーバーの設定を設定します。](../../../images/intellij-run-debug-wizard.png)

これで、サーバーがIntelliJで利用可能になりました。設定ボタン（以下参照）を実行する前に、[設定]のドロップダウンで必ず[OK]を選択するようにしてください。

参考までに、IntelliJの設定ボタンが@product@インスタンスでどのように機能するかを以下に示します。

- *開始*（![Start Server](../../../images/icon-intellij-start-server.png)）：サーバーを開始します。
- *停止*（![Stop Server](../../../images/icon-intellij-stop-server.png)）：サーバーを停止します。
- *デバッグ*（![Debug Server](../../../images/icon-intellij-debug-server.png)）：デバッグモードでサーバーを起動します。IntelliJでのデバッグの詳細については、[IntelliJ Debugging](https://www.jetbrains.com/help/idea/debugging-code.html)の記事を参照してください。

これで、サーバーをIntelliJで使用する準備が完了しました。
