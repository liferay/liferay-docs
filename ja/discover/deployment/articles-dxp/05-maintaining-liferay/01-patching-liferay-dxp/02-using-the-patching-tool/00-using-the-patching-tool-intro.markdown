---
header-id: patching-tool
---

# パッチングツールの使用

パッチングツールは、@ product @パッチをインストール、削除、比較、および準備します。
これは@ product @バンドルに事前インストールされており、@ product @に簡単にマニュアルインストールでき、アップデートするのも簡単です。パッチングツールの実行可能スクリプトにより、パッチ適用を促進します。



パッチングツールを使い始めるために必要なのは以下のとおりです：



- [Installing the Patching Tool](#installing-the-patching-tool)（マニュアルでのインストールのみ）
- [Executables](#executables)

## パッチングツールのインストール

@ product @バンドルには、パッチングツールが事前インストールされていて（`[Liferay Home]/patching-tool`に）、デフォルト設定で事前設定されています。バンドルを使用している場合はこのセクションを飛ばしてください。



ただし、@ product @をマニュアルでインストールした場合は、パッチングツールもマニュアルでインストールする必要があります。



1. [Customer Portal](https://web.liferay.com/group/customer/dxp/downloads/7-1)からパッチングツールをダウンロードします 。



2. パッチングツールを[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-liferay#liferay-home) フォルダ（推奨）、または別のフォルダに解凍します。



パッチングツールをインストールしたら、@ product @インストールを使用するためにパッチングツールを設定する必要があります。（詳細：[configure it to use your @product@ installation](/discover/deployment/-/knowledge_base/7-1/configuring-the-patching-tool)）
パッチングツールZIPファイルから抽出した`patching-tool`フォルダには、実行可能なスクリプトを含んだパッチングツールが含まれています。

## 実行ファイル

パッチングツールとともに配布されているUnixシェルおよびWindowsバッチスクリプトは、パッチングツールを使いやすくしています。Unixシステムでは以下を実行し、

    ./patching-tool.sh parameters

Windowsでは、以下を実行します。

    patching-tool parameters

Windows commandの`patching-tool`は、例で使われています。
UNIXでは、コマンドを実行する前に実行可能ファイルの名前を置き換えてください。


次は、パッチをインストールします。
