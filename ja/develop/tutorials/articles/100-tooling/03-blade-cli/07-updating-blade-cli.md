---
header-id: updating-blade-cli
---

# Blade CLIのアップデート

[TOC levels=1-4]

Blade CLIのバージョンが古い場合は、以下のコマンドを実行して、最新のバージョンを自動的にダウンロードしてインストールすることができます。

    blade update

| **注：**Blade CLI 3.3.0以前のバージョンを使っているWindowsユーザーの場合、`blade update`| コマンドは機能しません。これは、Windowsが現在| 存在するファイルをアップデートできないためです。この問題を回避するには、JPMを使用してBlade CLIのバージョンをアップデートします。|
|     jpm install -f https://releases.liferay.com/tools/blade-cli/latest/blade.jar
| Blade CLI 3.4.1以降のWindowsユーザー向けの| `blade update`コマンドは、| 機能します。

Blade CLIは頻繁にアップデートされるため、新しい機能のためにもBlade CLI環境をアップデートすることをお勧めしますNexusでリリースされたBlade CLIのバージョンは、[com.liferay.blade.cli](https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/blade/com.liferay.blade.cli/)アーティファクトを調べることで確認できます。
`blade version`を実行すると、現在インストールされているバージョンを確認できます。`blade version`を実行すると、利用可能な新しいBlade CLIバージョンがあるかどうかが通知されます。

| **注：**アップデート後に`blade version`を実行しても、| 期待されているバージョンが表示されない場合は、2つの別個のBlade CLIインストールが| お使いのマシンにインストールされている可能性があります。この状況は、通常| Blade CLIの以前のバージョンをインストールした後に| [Liferay Project SDKインストーラー](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli) |（以前のバージョンのもの）を旧バージョンのBlade CLIインスタンスをアップデートするために使用した場合に発生します。これは、| お勧めできません。これを行うと、Blade CLIが| マシンのグローバルおよびお使いのユーザーホームフォルダにインストールされます。最新のBlade CLIのアップデートプロセスがお使いのユーザーホーム| フォルダにインストールされるので、| 存在する場合は、グローバルフォルダ内のレガシーBladeファイルを削除する必要があります。これを行うには、`GLOBAL_FOLDER/JPM4J`フォルダに移動して、| | - `/bin/blade` | -`/commands/blade` | を削除します。| お使いのユーザーホームフォルダ内の最新のBlade CLIインストールが認識され、| 利用できるようになります。

Blade CLIは頻繁にリリースされますが、最新の機能をまだ利用できないようにしておきたい場合は、以下を使って、最新のスナップショットバージョンをインストールできます。

    blade update -s

これにより、Blade CLIの最新のスナップショットバージョンが取得され、ローカルマシンにインストールされます。`blade version`をスナップショットのインストール後に実行すると、以下のような出力が表示されます。

    blade version 3.3.1.SNAPSHOT201811301746

スナップショットバージョンは不安定であり、試験的な目的でのみ使用するように注意してください。

 Blade CLIのアップデート方法は、これで以上です。
