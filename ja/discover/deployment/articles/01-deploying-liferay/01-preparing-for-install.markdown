---
header-id: preparing-for-install
---

# インストールの準備

[TOC levels=1-4]

@product@ のインストールは簡単です。ただし始める前に、下記を検討する必要があります。

- どのバージョンのLiferayをインストール予定かですか。
- どのアプリケーションサーバー上に@product@をインストールする予定ですか。
- @product@バンドルをインストール予定か？または、既存のアプリケーションサーバー上に@product@のみをインストールする必要がありますか。
- どのデータベースを使用する予定ですか。
- データをどのように保管する予定ですか。
- ご利用のネットワークは@product@をサポートしていますか。

次に、これらの問いに応じて、@product@のインストールの基本的な手順を見ていきます。


## @product@の取得

[liferay.com](https://www.liferay.com)から、どなたでもLiferay DXPをダウンロードできます。
[Product] &rarr; *[Downloads]*から、@product@のオープンソース版またはエンタープライズ版の試用版を、いくつかの異なる形式でダウンロードできます。これには、アプリケーションサーバーにLiferay DXPをインストールする便利なバンドル、`.war `ファイルが含まれます。

Liferayエンタープライズのお客様は、[カスタマーポータル](https://www.liferay.com/group/customer)からLiferay DXPをダウンロードできます。

 @product@Liferay DXPバンドルは、@product@がプリインストールされたアプリケーションサーバーです。Liferay DXP 7.1 GA1およびLiferay Portal CE 7.1 GA1の後のバンドルは、7-Zip（`.7z`）およびgzipped tar（`.tar.gz`）圧縮ファイルアーカイブ形式でリリースされています。バンドルは、@product@をインストールする最も簡単な方法です。@product@ は多くのアプリケーションサーバーにバンドルされています。ニーズに最も適したものを選んでください。現在アプリケーションサーバーの設定がない場合は、Tomcatバンドルから始めることを検討してください。[fuzzy]Tomcatは、最も軽量で簡単なバンドルの1つです。[fuzzy]オープンソースのアプリケーションサーバーの設定がある場合は、使用可能なLiferay DXPバンドルから好みのサーバーを選択します。@product@を起動する前に、JDK（Java Development Kit）がインストールされている必要があります。

これらのサーバーのライセンスでは再配布できないため、@product@は、WebLogicやWebSphereなどの独自のアプリケーションサーバーにアプリケーションサーバーバンドルを提供できないことに注意してください。しかし、@product@の商用製品は、他のアプリケーションサーバーと同様に、これらのアプリケーションサーバーでも同様に動作します。独自のアプリケーションサーバーに@product@をインストールするには、`.war`ファイルを使用してマニュアルインストール手順に従ってください。

<!-- Reinstate once PACL is supported. Jim
Once you have @product@, you can plan out your installation. First, determine if
you need @product@ Security turned on. Second, prepare your database. Third, install @product@. Fourth, configure your network. Fifth, configure search.
-->
@product@を入手したら、インストールの計画を立てることができます。まず、データベースを準備します。次に、@product@をインストールしてください。第三に、ネットワークを設定します。
第四に、検索を設定します。@product@ は、バンドルを使用してインストールすることも、既存のアプリケーションサーバーに手動でインストールすることもできます。次に、@product@のインストールに必要な手順を説明します。

## @product@ のインストール手順

@product@のインストールを始める前に、以下の基本的なインストール手順を確認してください。

1. @product@ と共に使用するデータベースサーバーを選び、新規のデータベースを作成してください。
Liferay DXPによってデータベース接続を管理するか、アプリケーションサーバーによってデータベース接続を管理するかを決めてください。@product@によってデータベース接続を管理することを推奨します。@product@ は、複数のオープンソースまたは法人レベルのドキュメントリポジトリと接続することができます。

2. Eメール通知をユーザーに送信するためのメール資格情報を収集してください。Liferay DXPによってメールセッションを管理するか、アプリケーションサーバーによってメールセッションを管理するかを決めてください。@product@には内蔵のメールセッションがありますが、JNDIメールセッションもサポートしています。@product@によってメールセッションを管理することを推奨します。

3.  @product@バンドルをインストールするか、既存のアプリケーションサーバー上に @product@をインストールしてください (詳細な指示は下記参照)。

4. IPv4またはIPv6を選んでください。どのアドレス形式がご利用のネットワークにとって最適かを決めてください (詳細は下記参照)。

5. 検索エンジンのElasticsearchの環境設定をどのように行うかを決めてください。@product@にデフォルトで組み込まれている環境設定は、本番運用を想定したものではありません。そのためElasticsearchを、同一のインフラ上またはそれ自体のインフラ上に、個別にインストールする必要があります。
   <!--6. Determine whether you'll use Liferay Marketplace or other third party
    applications. If you will, you should enable Liferay's Plugin Access Control
    List (PACL) security feature.-->

7. ポートを設定します（オプション）。Liferayのアプリケーションサーバ（TomcatやWildflyなど）は、インバウンドHTTPリクエスト、HTTPSリクエスト、AJPリクエストなどの処理のために、特定のポートを使用します。アプリケーションサーバをデバッグモードで起動すると、接続するデバッガを待機するポートがあります。必要に応じて、これらのポートを設定できます。デフォルトポートとその設定方法については、アプリケーションサーバのマニュアルを参照してください。

   Liferayはまた、設定可能なポートを介してOSGiフレームワークへのアクセスを提供します。

   `module.framework.properties.osgi.console=localhost:11311`

   上の行を`LIFERAY_HOME/portal-ext.properties`ファイルにコピーして、ポート番号を調整することで、デフォルトのプロパティを上書きすることができます。

以下の手順に沿って設定します。

## 手順 1: データベースサーバーを選んで新規のデータベースを作成する

@product@ のインストールを始める前に、以下の基本的なインストール手順を確認してください。
必要な手順は2つだけです。

1. CUTF-8の文字セットでエンコードされた空白のデータベースを作成してください。@product@は多言語アプリケーションであり、サポートされている文字セットをすべて表示するにはUTF-8のエンコーディングが必要です。

   | **注意**:あるデータベースベンダーから別のベンダーに移行する場合は、[データベースのデフォルトクエリの並び替え順が、Liferay DXPのエンティティの表示順と一致するように設定します](/docs/7-0/tutorials/-/knowledge_base/t/sort-order-changed-with-a-different-database)。

2. このデータベースにアクセスするためのデータベースユーザーを作成します。このデータベースにアクセスするためのデータベースユーザーを作成します。

空のLiferay DXPデータベースに、このデータベースユーザにテーブルの作成および削除権限を含むすべての権限を与えます。最初の起動中、 @product@は先ほど作成したデータベースに必要な表を作成します。
これは自動的に行われ、インデックスも完備されます。

@product@をセットアップする際はこの方法を推奨します。 @product@ は、アップグレード中、または独自のデータベーステーブルを作成するさまざまな@product@プラグインがインストールされているときに、自動的にデータベースを維持できます。この方法は、 @product@データベースをセットアップする有力な方法です。

| **警告**: Oracleデータベースを使用している場合は `ojdbc8.jar`、少なくともOracle 12.2.0.1.0 JDBC 4.2バージョンを使用してドライバライブラリを使用してください。これは、[データ切り捨ての問題](https://issues.liferay.com/browse/LPS-79229)がCLOB列からデータを読み取って検出されたためです。

このセクションで説明されている推奨された権限で@product@のデータベースを設定する場合は、次のセクションに進んでください。

| **警告**:下記の指示は、Liferay DXPのインストールにおいて推奨しません。この手順は、制約が比較的大きい基準を持つ法人が、比較的厳しい (しかしながら準最適な) データベース設定でLiferay DXPをインストールできるようにするために記されたものです。もし可能であれば、下記の手順ではなく前のセクションに記載される方法を用いることを推奨します。

@product@は自動的にデータベースを作成することができますが、法人によってはアプリケーションサーバー内に環境設定されるデータベースユーザーがLiferay DXP、およびプラグインの表を維持するためのLiferay DXPプラグインに必要なデータベース許可を有することを*希望しない*場合があります。そのような組織では、選択、挿入、更新、削除のみが許可として認められています。そのため、このセクションではデータベースを手動でセットアップする方法を説明します。ご自身の組織がLiferay DXPデータベースユーザーにデータベース内で表を作成およびドロップする許可を*付与する (これが推奨環境設定です) ことを望んでいる場合*、前のセクションに記載される推奨環境設定を使用するだけで構いません。

1. @product@用の新しい、空のデータベースを用意します。.

2. @product@ データベースにあらゆることを行う完全な権利を、@product@ データベースユーザーに付与してください。


3. @product@をインストールし、起動してください。データベースへの自動入力が行われます。

4. データベースに@product@の表の内容が入力されたら、表を作成およびドロップする許可を@product@データベースユーザーから削除してください。

Liferay DXPをこのように動作させることについて、いくつか注意事項があります。多くの@product@プラグインは、実装時に新規の表を作成します。Additionally, @product@has an automatic database upgrade function that runs when Liferay DXP is upgraded.
さらに@product@には、Liferay DXPがアップグレードされると動作する自動データベースアップグレード機能があります。@product@データベースユーザーにデータベース内で表を作成・修正・ドロップするための十分な権利がない場合、いずれかのプラグインを実装さらに、開発者によってはプラグイン自体の表が必要なプラグインを作成する場合があります。
表が作成されるかアップグレードが完了すると、次回のデプロイまたはアップグレードまでこれらの権限を削除できます。
加えて、開発者自身の票を作成する必要があるプラグインを作成するかもしれません。<segment 0742>このようなプラグインは、同じことを行う@product@のプラグインとよく似ており、@product@がデータベース表を作成できない場合はインストールできません。このようなプラグインをインストールしたい場合、インストールを試みる前に毎回、データベース内で表を作成する権利を付与する必要があります。

@product@には、`dl.store.impl=`プロパティを設定することでドキュメントやメディアのファイルを保管することのできる環境設定が複数存在します。利用できるオプションは、Simple File System Store (シンプルファイルシステム保管)、Advanced File System Store (高度ファイルシステム保管)、CMIS Store、DBStore、JCRStore、Amazon S3Storeです。さらに、@product@は様々なオープンソースおよび法人レベルのドキュメントリポジトリに接続することができます。
enterprise-level document repositories. すべてのリポジトリは、Liferay Marketplaceで入手可能なフックを通じて@product@に接続することができます (下記参照)。

データベースとドキュメントリポジトリの準備ができたら、サーバー上に@product@をインストールすることができます。

## 手順 2: メール資格情報を収集する

@product@は、メールサーバーを使用してEメール通知を送信します。そのため、インストールの一環として、@product@がご利用のメールサーバーに接続するのに使用する資格情報を有する必要があります。
特に、以下の情報を有する必要があります。

- 着信POPサーバーおよびポート
- POP ユーザー名
- POPパスワード
- 発信SMTPサーバーおよびポート
- SMTPユーザー名
- SMTP パスワード
- デフォルトの環境設定に優先し得るJavaMailプロパティの一覧

この情報を収集したら、次の手順に進む準備は完了です。

## 手順 3: インストールする

次の手順は、 @product@をインストールすることです。アプリケーションサーバーとバンドルになっている [@product@をインストールする](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay)か、[既存のアプリケーションサーバー上に@product@を手動でインストールする](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay-manually)かの2つの方法のいずれかでこれを行うことができます。
各@product@のインストーラの[Liferay Homeにはいくつかのファルダ](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)が含まれています。

@product@をインストールする、最も簡単な方法なのはバンドルを利用することです。すぐに本番運用できる@product@ インスタンスにとても簡単に変えることができます。

## 手順 4: ネットワーク環境設定方法

@product@は、IPv4および IPv6アドレスをサポートしているので、どちらかを[選択します](/docs/7-0/deploy/-/knowledge_base/d/choosing-ipv4-or-ipv6)。
デフォルトでは、Liferay DXPはIPv4アドレスを使用します。IPv6を使用する場合は、@product@を設定する必要があります。2つのシンプルな手順があります。

1. アプリケーションサーバーの環境設定で、`-Djava.net.preferIPv4Stack=false`を設定してください。

2. ポータルのポータルの[ Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home) フォルダに内に`portal-ext.properties`ファイルを (既存のファイルがない場合は) 作成し、対象となる許可したいホスト (例: _0:0:0:0:0:0:0:1_) に, `tunnel.servlet.hosts.allowed`プロパティを設定してください。

## 手順 5: Elasticsearchの環境設定を行う


@product@ は、デフォルトではElasticsearchの組み込み版がある状態で出荷されます。この環境設定はデモ目的では使用可能ですが、本番運用には推奨致しません。@product@をインストールした後、@product@を独立したElasticsearchサーバーまたはクラスタに接続するように環境設定を行う必要があります。インストールのサイズに応じて、このElasticsearchの独立したインスタンスは@product@があるのと同じマシン上か、別のマシン上に存在させることができますがパフォーマンスのため、[別のマシン上にインストールする](/docs/7-1/deploy/-/knowledge_base/d/installing-elasticsearch)ことをお勧めします。

## 手順 6: Liferay Marketplaceおよびポータルセキュリティ

 Liferay Marketplaceを利用するためにはMarketplaceプラグインをインストールする必要があります。Marketplaceプラグインによって、オンライン上のLiferay Marketplaceにアクセスするだけに留まらない数多くの機能が有効となります。Marketplaceプラグインによって有効となる主要な機能には以下のようなものがあります。

- Liferay Marketplace:オンライン上のマーケットプレイスへの直接アクセス

- App Manager:アプリをインストール、アンインストール、アップデートすることが可能

- App Manager: アプリをインストール、アンインストール、アップデートすることが可能

- Developer Apps:開発中のアプリを管理することが可能

- License Manager: @product@およびアプリ用の効率化されたライセンス管理


ポータルインストールプロセスは、Marketplaceプラグインを自動的に実装および登録します。
これが、可能ではない環境内に@product@ をインストールする場合、複数ある回避策のいずれかを実行する必要があります。

これで、@product@をインストールすることができます。バンドルを使用して@product@をインストールする場合は、次のセクションに進みます。@product@を手動でインストールする場合は、選択したアプリケーションサーバーのセクションに進んでください。@product@を手動またはバンドル経由でインストールするばあいは、[@product@のSetup Wizardの使用](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay##using-liferays-setup-wizard)、 [メールの設定（英語）](/discover/deployment/-/knowledge_base/7-1/installing-liferay#configuring-mail)、[Elasticsearchのインストール（英語）](/discover/deployment/-/knowledge_base/7-1/installing-elasticsearch)に進んでください。
