---
header-id: enabling-code-assist-features-in-your-project
---

# プロジェクトでコードアシスト機能を有効にする

[TOC levels=1-4]

Liferay Dev Studioの[Tern](http://ternjs.net/)の統合は、コードの推論と補完のための貴重なフロントエンドおよびバックエンド開発ツールを多数提供しています。このチュートリアルでは、プロジェクトでTern機能を有効にする方法について説明します。

このチュートリアルを開始する前に、Dev Studioインスタンスに必要な開発ツールとTern統合がインストールされていることを確認してください。これを行うには、*[ヘルプ]* → *[Eclipseについて]* → *[インストールの詳細]*に移動し、*[インストール済みソフトウェア]*にある*[Liferay IDE AlloyUI]*を見つけます。インストールが済んでいる場合は、*[Tern機能の設定]*セクションに進みます。まだの場合は、以下の手順に従ってインストールする必要があります。

1. *[ヘルプ]* → *[新規ソフトウェアのインストール...]*に移動します。

2. 以下のリンクを*[作業]*フィールドに貼り付けます。

       http://releases.liferay.com/tools/ide/latest/stable/
   
3. *[Liferay IDE AlloyUI]*オプションがチェックされていることを確認し、インストールプロセスを完了します。

   ![図1：*[Liferay IDE AlloyUI]*オプションは、実際には*[Liferay IDE]*オプション内にリストされているサブオプションです。](../../../images/alloyui-ide-feature.png)

必要な機能がインストールされたので、以下で説明する手順に従って、プロジェクトでTernのコードアシスト機能を有効にします。

## Tern機能の設定

Tern機能はプロジェクトごとに有効になります。デフォルトでは、TernはPlugins SDKを使用して作成されたLiferay WARスタイルのポートレットに対してすでに有効になっています。
他のすべてのプロジェクトタイプ（Liferay Module Projectなど）については、以下の手順に従う必要があります。

1. プロジェクトを右クリックして、*[設定]* → *[Ternプロジェクトに変換]*を選択します。

   これで、プロジェクトがTernを使用するように設定されました。プロジェクトを設定したら、プロジェクトに使用するモジュールを有効にする必要があります。

2. インストールされているTernプラグインをリストにしたメニューが表示されます。たとえば、AlloyUI機能を使用するには、*AlloyUI*、*Browser*、 *JSCS*、*Liferay*、および*YUI Library*モジュールを有効にする必要があります。下の図は、[Tern Modules]メニューを示しています。

   ![図2：これらのTernモジュールを選択すると、プロジェクトでAlloyUIコードアシスト機能が使用できます。](../../../images/tern-modules.png)

   インストールされているTernプラグインのこのリストを参照する必要がある場合は、プロジェクトを右クリックして*[プロパティ]*を選択します。次に、*[Tern]* → *[モジュール]*を選択します。

3. プロジェクトで使用する追加モジュールを確認し、*[OK]*をクリックします。

これで、プロジェクトがDev StudioのTern機能を使用できるように設定されました。

## 関連トピック

[Using Front-End Code Assist Features in Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/using-front-end-code-assist-features-in-ide)

[Creating Modules with Liferay Dev Studio](/docs/7-1/tutorials/-/knowledge_base/t/creating-modules-with-liferay-ide)

[Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/blade-cli)
