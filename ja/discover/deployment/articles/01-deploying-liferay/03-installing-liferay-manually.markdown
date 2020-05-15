---
header-id: installing-liferay-manually
---

# 手動で@product@をインストールする

[TOC levels=1-4]

@product@をインストールする最も簡単な方法は[バンドルを 使う](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay)ことです。
しかし、これは常に可能というわけではありません。組織によっては、@product@をインストールする必要がある既存のインフラストラクチャがあります。また、特定のアプリケーションサーバーで標準化している組織もあります。@product@は、多くの主要なアプリケーションサーバーとうまく連携します。インストールを始める前に、@product@のデータソースとメールセッションを管理するための2つの異なるアプローチがあることに注意してください。これらのトピックはすべて網羅されています。

- [データソースを使用する](#using-data-sources)
- [メールセッションを使用する](#using-mail-sessions)
- [データソースとメールセッションの手動設定](#manual-configuration)

データソースから始めます。

## データソースを使用すること

@product@には、データソースを設定するためには2つの方法があります。

- @product@の組み込みデータソースを使用する
- ご利用のアプリケーションサーバーのJNDIデータソースを使用する

組み込みデータソースの使用をお勧めします。@product@のデータソースは、プロパティファイルで設定されているプロパティによって設定されています。デフォルトでは、 @product@の初回起動時に表示される[基本設定ページ](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#using-the-setup-wizard)にデータベースの接続情報を入力できます。セットアップウィザードは入力された情報を[Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)フォルダにある`portal-setup-wizard.properties`という名前の設定ファイルに保管します。組み込みデータソースはこの情報を使用してデータベースに接続します。

組み込みデータソースを使用することをお勧めしますが、それ以外の方法もあります。アプリケーションサーバーが提供するデータソースを使用することもできます。この場合、JNDIルックアップはデータソースへのハンドルを提供し、アプリケーションサーバーは接続プールを管理します。

アプリケーションサーバーのデータソースを設定するには、独自の設定ファイルを作成してセットアップウィザードをスキップする必要があります。ウィザードの*後に*このファイルを作成することになるので 、いずれにしても大したことではありません。以下の[手動設定](#manual-configuration)のセクションでは、JNDIデータソースの設定について説明します。

メールセッションもデータソースと同じように設定されているので、それを次に紹介します。

## Using Mail Sessions

@product@はSMTPを使用してメールを送信します。データベースと同様に、メールサーバーを設定するには2つの方法があります。

- @product@の組み込みメールセッションを使用する
- アプリケーションサーバーのメールセッションを使用する

組み込みメールセッションを使用することをお勧めします。@product@を起動した後は、[Controle Panel]からメールサーバーを設定できます。デフォルト設定は、@product@を実行している同じマシン上のメールサーバです。これが自身の設定ではない場合は、デフォルトを変更する必要があります。これを行うには、Liferay Homeフォルダ内の、`portal-ext.properties`ファイルを使用します（下記参照）。

アプリケーションサーバーのメールセッションを使用するには、それをアプリケーションサーバー内に作成する必要があります。メールセッションを作成したら、`portal-ext.properties`ファイルまたは[Control Panel]から@product@をつなげることができます。

データベース接続とメールセッションの両方を管理するために@product@を使用する場合は、@product@の最初の起動時に基本設定ページにデータベース接続情報を入力してから、[Control Panel]からメールサーバー情報を入力します。

自身のアプリケーションサーバでデータベース接続、またはメールサーバーを管理する予定の場合、@product@のセットアップウィザードを使うことができません：以下の[手動設定](#manual-configuration)セクションの指示に従う必要があります。

各アプリケーションサーバーのインストール記事には、データベース接続とメールサーバーを管理するためにアプリケーションサーバーを設定するための手順も含まれています。

## Manual Configuration

アプリケーションサーバーにデータベース接続またはメールサーバー（あるいはその両方）を管理させるには、この設定を手動で作成する必要があります。Liferay Homeフォルダに`portal-ext.properties`という名前のテキストファイルを作成します。このファイルはデフォルトのプロパティを上書きします。

アプリケーションサーバーのデータソースを使用するには、データベースとつなげる接続プールをアプリケーションサーバー内に作成します。この接続プールを`jdbc/LiferayPool`と名付けます。以上は、各アプリケーションサーバーの記事で詳しく説明しています。`jdbc/LiferayPool`接続プールを使用するように@product@に指示するには、`portal-ext.properties`ファイルに次のディレクティブを追加します。

    jdbc.default.jndi.name=jdbc/LiferayPool

次に、アプリケーションサーバーの記事に従って@product@をインストールします。
インストールしたら、メール設定をセットアップできます。

メール設定を作成するために、コントロールパネルを使用する必要があります。[コントロールパネル]→[設定]→[サーバ管理]→[メール]へ行き、メールセッション設定のための設定を入力します。

これを`portal-ext.properties`ファイルで設定することもできます。これにより、設定を1回実行してから設定ファイルを複数のマシンにコピーできます。組み込みメールセッションを使用するには、以下のプロパティを使用して、環境に合わせてそれらの値をカスタマイズします。

    mail.session.mail.pop3.host=localhost
    mail.session.mail.pop3.password=
    mail.session.mail.pop3.port=110
    mail.session.mail.pop3.user=
    mail.session.mail.smtp.auth=false
    mail.session.mail.smtp.host=localhost
    mail.session.mail.smtp.password=
    mail.session.mail.smtp.port=25
    mail.session.mail.smtp.user=
    mail.session.mail.store.protocol=pop3
    mail.session.mail.transport.protocol=smtp

アプリケーションサーバーのメールセッションを使用するには、まずメールセッションを作成します。それから、それを`portal-ext.properties`ファイルに指定します。

    mail.session.jndi.name=mail/MailSession

完了したら、ファイルを保存します。

## Logging

@product@をデプロイした後、`PhaseOptimizer`を含む以下のような過剰な警告とログメッセージが表示される場合があります。これらは良性なので無視することができます。このようなログメッセージを回避するために、必ずアプリケーションサーバーのログレベルまたはログフィルターを調整してください。

    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass gatherExternProperties
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    WARNING: Skipping pass checkControlFlow
    May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
    INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
    current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
