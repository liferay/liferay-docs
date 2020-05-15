---
header-id: patching-dxp-source-code
---

# DXPソースコードのパッチ

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

自動車整備士、愛好家、および将来の所有者は、車について「何が隠されているのか」と尋ねます。その質問をする一般的な理由は次のとおりです。

  - 問題への懸念
  - 車の能力と内部構造に関する好奇心
  - 車を改善またはカスタマイズしたい

「 *DXPの* フードの下には何がありますか？」また、DXP Digital Enterprise（DXP）のソースコードにアクセスできるので、デバッガーを接続して実際の動作を確認できます\！ ローカルでコードを設定することは、DXPの調査、問題の調査、改善とカスタマイズのチケットです。

方法は次のとおりです。

1.  DXP、DXPソースコード、およびパッチをダウンロードする

2.  DXPを準備する

3.  DXPソースコードにパッチを適用する

## ステップ1：DXP、DXPソースコード、およびパッチをダウンロードする

1.  [カスタマーポータル](https://web.liferay.com/group/customer/dxp/downloads/7-1)から、DXPバンドル（またはDXP JAR）と使用しているバージョンのDXPソースコードをダウンロードします。

2.  フィックスパックとそのソース・コードを [からダウンロードします](https://web.liferay.com/group/customer/dxp/downloads/7-1)。 `-src.zip` で終わるフィックスパックのZIPファイルには、フィックスパックとソースコードが含まれています。

次に、DXPをインストールして構成します。 DXPのパッチツールを使用すると、フィックスパックとフィックスパックのソースコードをインストールできます。 パッチが適用されたDXPインストールがすでにあり、それを使用する場合は、DXPソースコードのパッチに関する以下のセクションにスキップしてください。

## ステップ2：DXPを準備する

DXPをローカルで準備するには、DXPのインストール、構成、およびパッチ適用が必要です。

### DXPのインストールと構成

DXPをインストールして構成する方法は次のとおりです。

1.  [ローカルにDXP](/docs/7-1/deploy/-/knowledge_base/d/deploying-product) をインストールして展開します。

2.  DXPを起動します。

3.  データベースを使用するようにDXPを構成します。

4.  DXPを停止します。

次に、必要なDXPパッチを適用します。

### パッチDXP

DXPにパッチを適用する方法は次のとおりです。

1.  必要なすべてのパッチZIPファイルを `[LIFERAY_HOME]/ patching-tool / patches`コピーします。 `-src.zip` フィックスパックファイルは、フィックスパックバイナリとソースコードの両方が含まれているため、使用するのが最適です。

2.  コマンドラインを開き、 `[LIFERAY_HOME]/ patching-tool`アクセスします。

3.  コマンド `patching-tool.sh auto-discovery` を実行して、 `default.properties`と呼ばれるデフォルトのパッチ適用プロファイルを生成します。 プロファイルのプロパティがDXPインストールを参照していることを確認してください。 詳細については、 [パッチツールのドキュメント](/docs/7-1/deploy/-/knowledge_base/d/patching-tool) を参照してください。

    以下はプロファイルの例です。
   
        patching.mode=binary
        war.path=../tomcat-9.0.6/webapps/ROOT/
        global.lib.path=../tomcat-9.0.6/lib/ext/
        liferay.home=../

4.  `[LIFERAY_HOME]/ patching-tool / patches`で使用可能なすべてのパッチファイルを一覧表示するには、次のコマンドを実行します。
   
        patching-tool.sh info

5.  次のコマンドを実行して、パッチをインストールします。
   
        patching-tool.sh install

    [パッチツールのドキュメント](/docs/7-1/deploy/-/knowledge_base/d/patching-tool) は、データベースインデックスの作成など、状況に適用できる追加の手順を説明しています。

DXPソースコードとパッチソースコードを準備する時が来ました。

## ステップ3：DXPソースコードにパッチを適用する

DXPソースコードを作業したい場所に解凍します。

次に、DXPソースコード用のパッチツールプロファイルを作成します。

### ソースコードのパッチツールプロファイルを作成する

ソースコードを参照するプロファイルを作成する方法は次のとおりです。

1.  以下のコマンドを実行してプロファイルを作成します。 `[profile]` をプロファイルの名前に置き換えます。
   
        patching-tool.sh auto-discovery [profile]

2.  前の手順で生成されたプロファイルプロパティファイルで、 `patching.mode` プロパティを `source` に設定し、 `source.path` プロパティをソースコードパスに設定します。
   
        patching.mode=source
        source.path=[DXP source code path]

先ほどダウンロードしたDXPパッチを適用する時が来ました。

## ソースコードにパッチを当てる

DXPのパッチツールは安全で使いやすいです。 パッチのインストール以外にも、次の機能があります。

  - パッチのコード変更をリストする
  - パッチで修正された問題をリストします（[LPS / LPEチケット](https://issues.liferay.com)）。
  - パッチを元に戻す

詳細については、次のパッチツールのドキュメントを参照してください。

  - [パッチレベルの比較](/docs/7-1/deploy/-/knowledge_base/d/working-with-patches#comparing-patch-levels)
  - [パッチの削除または復帰](/docs/7-1/deploy/-/knowledge_base/d/working-with-patches#uninstalling-patches)

|パッチツールを使用してDXPソースコードを管理することに加えて、次のことができます。 |などのバージョン管理システムでオプションで管理する [Git](https://git-scm.com/)。 | | GitでDXPソースコードを設定するためのコマンドを次に示します。 | cd \ [ソースコードのルートフォルダーへのパス\] | git init | git add。 | git commit -a | |コマンドの説明は次のとおりです。| | `init` は、現在のフォルダー（つまり、ルート|フォルダー）とそのすべてのコンテンツのGitリポジトリを作成します。 | `はルートフォルダーとその内容を` ステージ追加します。 | `はステージングされたファイルの` チェックをコミットします。 | |コードの変更（DXPパッチなど）をGitリポジトリにコミットできます。

パッチツールは、 `[LIFERAY_HOME]/ patching-tool / patches`あるZIPファイルからすべてのパッチとパッチソースコードをインストールします。 パッチツールがパッチを適用するには、すべてのパッチが `パッチ` フォルダーにある必要があります。

1.  すべてのパッチソースZIPファイルをまだコピーしていない場合は、 `[LIFERAY_HOME]/ patching-tool / patches` コピーします。

2.  `info` コマンドを実行して、パッチがリストされていることを確認します。 パッチがリストにない場合は、そのZIPファイルを `patchs` フォルダーにコピーします。 `[profile]` をDXPソースコードプロファイル名に置き換えます。
   
        patching-tool.sh [profile] info

3.  プロファイルで `install` コマンドを実行して、パッチを適用します。
   
        patching-tool.sh [profile] install

DXPのインストールとソースコードにパッチが適用され、デバッグの準備ができました\！

お気に入りのデバッガーをDXPインスタンスに接続し、サーバーを起動します。 構成の詳細については、デバッガーのドキュメントを参照してください。

おめでとう\！ DXPの内外を自由に探索できます\！

## 関連トピック

[トラブルシューティングFAQ](/docs/7-1/tutorials/-/knowledge_base/t/troubleshooting-faq)

[Liferay @ide@](/docs/7-1/tutorials/-/knowledge_base/t/liferay-ide)
