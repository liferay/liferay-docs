---
header-id: updating-liferay-workspace
---

# Liferay Workspaceのアップデート

[TOC levels=1-4]

Liferay Workspaceは、新しい機能と共に継続的にアップデートされています。ワークスペースを作成してからしばらく時間が経っている場合、Liferayでの開発エクスペリエンスの向上につながる最新機能を見逃している可能性があります。Liferay Workspaceのアップデートは簡単です。

1. Liferay Workspaceの最新バージョンを見つけます。これを行うには、[Liferay Gradle Plugins Workspace Change Log](https://github.com/liferay/liferay-portal/blob/master/modules/sdk/gradle-plugins-workspace/CHANGELOG.markdown)を開き、アップグレードするバージョンをコピーします。変更ログを参照して、各バージョンに関連付けられているアップデートと新機能を見つけることもできます。

2. Liferay Workspaceの`settings.gradle`ファイルを開きます。このファイルは、ワークスペースのルートフォルダにあります。

3. `dependencies`ブロック内に、以下のようなコードがあるはずです。

       dependencies {
       classpath group: "com.liferay", name: "com.liferay.gradle.plugins.workspace", version: "[WORKSPACE_VERSION]"
       }
   
   手順1で変更ログからコピーしたバージョン番号に`com.liferay.gradle.plugins.workspace`依存関係の`version`をアップデートします。

4. Gradleコマンドを実行して、ワークスペースのアップデートプロセスを開始します（例: `blade gw tasks`）。

| **注：**GradleベースのLiferay Workspaceで提供されるGradleラッパーは、| バージョン1.10.14以前のワークスペースから| 利用可能な最新のバージョンに移行する場合は、アップデートする必要があります。Gradleラッパーをアップデートするには、次を実行します。|
|     ./gradlew wrapper --gradle-version=4.10.2

 Liferay Workspaceの最新バージョンを確認する場所、Workspaceをそのバージョンにアップデートする方法、およびアップデートプロセスを開始する方法については、これで以上です。
