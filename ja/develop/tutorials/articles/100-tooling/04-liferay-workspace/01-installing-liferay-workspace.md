---
header-id: installing-liferay-workspace
---

# Liferay Workspaceのインストール

[TOC levels=1-4]

Liferay Project SDKインストーラーを使用して、Liferay Workspaceをインストールすることができます。これにより、JPMと[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)がユーザーホームフォルダにインストールされ、オプションでLiferay Workspaceフォルダが初期化されます。これは、Blade CLIのインストールに使用したインストーラと同じで、[Installing Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/installing-blade-cli)のチュートリアルで説明されています。

Liferay Workspaceをダウンロードしてインストールするには、以下の手順に従ってください。

1. 使用しているオペレーティングシステム（例: Windows、MacOS、またはLinux）に対応する最新の[Liferay Project SDKインストーラー](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/)をダウンロードします。Project SDKインストーラーは*[Liferay IDE]*の下にリストされており、フォルダのバージョンはIDEリリースに基づいています。@ide@の使用を予定していない場合は、@ide@を含まないインストーラーを選択できます。Project SDKインストーラーは、バージョン3.2.0以降で使用できます。大きな緑色のダウンロードボタンを**選択しないでください**。ボタンを押してしまうと、代わりにLiferay Portalがダウンロードされてしまいます。

2. インストーラーを実行します。*[次へ]*をクリックして、インストーラーの概要を確認します。

3. Liferay Workspaceを初期化するディレクトリを設定します。

   ![図1：Liferay Workspaceの場所を決定します。](../../../images/blade-installer-workspace-init.png)

   それから、*[次へ]*をクリックします。

4. ワークスペースで使用するLiferayの製品タイプを選択します。そして、*[次へ]*をクリックします。

   ![図2：Liferay Workspaceで使用する製品バージョンを選択します。](../../../images/installer-workspace-type.png)

   | **注：**Liferay DXPバンドルをダウンロードする前に、| liferay.comのユーザー名とパスワードの入力を求められます。資格情報は| ローカルに保存されません。トークンとして`~/.liferay`フォルダに保存されます。トークンは| DXPバンドルの再ダウンロードを決定した場合に、ワークスペースで使用されます。
| さらに、ワークスペースにダウンロードされたバンドルも| お使いの`~/.liferay/bundles`フォルダにコピーされます。もし、同じバージョンの別の| @product@インスタンスを初期化することにしても、バンドルは再ダウンロードされません。このトピックの詳細については、| | [Adding a Liferay Bundle to a Workspace](/docs/7-1/tutorials/-/knowledge_base/t/configuring-a-liferay-workspace#adding-a-liferay-bundle-to-a-workspace) | を参照してください。

5. *[次へ]*をクリックして、マシンへのLiferay Workspaceのインストールを開始します。

 これで、Liferay Workspaceがマシンにインストールされました。
