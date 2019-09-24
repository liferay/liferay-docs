---
header-id: cas-central-authentication-service-single-sign-on-authentication-ja
---

# CAS (Central Authentication Service) シングルサインオン認証

[TOC levels=1-4]


CASはYale大学によって開発された認証システムです。オープンソースのシングルサインオンとして、広く使われています。また、@product@がSSO製品として初めてサポートした製品でもあります。Liferay DXPのCASモジュールは、CASクライアントも含んでいるので、個別にインストールする必要がありません。

| **注**: @product@は CAS 3.3.x.をサポートしています。

CASサーバーアプリケーションは、お使いのサーバーで、正しく設定されたSecure Socket Layer (SSL)証明書を必要とします。証明書を自分で生成する場合は、JDKに付いてくる`keytool`ユーティリティーを使用します。まずは、キーを生成し、キーをファイルにエクスポートします。次にキーをローカルJavaキーストアにインポートします。パブリック（インターネットベースの本番環境）の場合、証明済みのキーを認知された認証局から購入するか、認知された認証局にサインさせる必要があります。例えば、IT部門の設定前ユーザーのブラウザーを使用して、証明書を許可します。こうすることで、認証に関する警告が表示されません。

キーを生成するには下記のコマンドを使用します:

    keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

例のパスワード (`changeit`)の代わりに、覚えられるパスワードを使用します。
Tomcatを使用していない場合は、別のエイリアスを使用することも可能です。
姓と名の記入は、 `localhost`またはサーバーのホスト名を使用します。IPアドレスは使用できません。

キーをファイルにエクスポートするには、下記のコマンドを使用します:

    keytool -export -alias tomcat -keypass changeit -file server.cert

最後に、キーをJavaキーストアにインポートするには、下記のコマンドを使用します:

    keytool -import -alias tomcat -file server.cert -keypass changeit -keystore $JAVA_HOME/jre/lib/security/cacerts

Windowsシステムを使用している場合は、上記の`$JAVA_HOME`を`%JAVA_HOME%`に変更します。
この作業は、CASが稼働しているシステム上で行う必要があります。


CASサーバーを再起動後、その認証情報を使用するように@product@を設定します。
CAS設定はシステムレベルまたはポータルレベルで適用できます。システムレベルでOpenID SSOモジュールを設定するには、 [コントロールパネル]へ行き、[設定] > [システム設定] > [セキュリティ]  > [SSO] の順にクリックします。ここで入力した値は、ポータルインスタンスに対するデフォルト値となります。CAS認証を有効にし、CASサーバーに指すようにURLプロパティーを調整します。

**Enabled**: CAS シングルサインオンを有効にするチェックボックスです。

**Import from LDAP**: CASに認証されているユーザーで、Liferay DXPに未登録の場合があります。その場合、このチェックボックスを選択し、自動的にユーザーをLDAPからインポートします。この機能を使用するにはLDAPが有効になっていないといけません。

その他の設定はデフォルト値を含む様々なURLです。デフォルト値の*localhost*を変更しCASサーバーに指すように設定ます。完了したら、*保存*をクリックします。その後、ユーザーが*[ログイン]*リンクをクリックすると、CASサーバーにダイレクトされ、@product@にサインインします。

場合によっては、ディスク上のファイルを使用してシステム構成を指定するほうが便利かもしれません。これを行うには。次のファイルを作成します:

    {LIFERAY_HOME}/osgi/configs/com.liferay.portal.security.sso.cas.configuration.CASConfiguration.cfg

このファイルのフォーマットは、プロパティーファイルと同様です。以下の表は各プロパティに使用するキー一覧です。リテラル値でJavaのプリミティブタイプを開始する時と同様の値を入力します。Javaプリミティブ型をリテラル値で開始する時と同じフォーマットで値を入力します。

| プロパティーレベル | プロパティーキー | 説明 | タイプ |
----- | ----- | ----- | -----
| **Enabled** | `enabled` | CAS SSO 認証を有効にするにはこのチェックボックスを選択します。 | `boolean` |
| **Import from LDAP** | `importFromLDAP` | CASで認証済みで、@product@に登録されていないユーザーがLDAPからインポートされます。LDAPが個別に有効にされている必要があります。 | `boolean` |
| **Login URL** | `loginURL` | CASサーバーのログインを設定します。 | `String` |
| **Logout on session expiration** | `logoutOnSessionExpiration` | チェックされていると、期限切れのブラウザーはCASログアウトURLにリダイレクトされます。 | `boolean` |
| **Logout URL** | `logoutURL` | CASサーバーのログインを設定します。これは、CASサーバーログアウトURLです。@product@のログアウト機能がCASログアウトをトリガーする場合に設定します。
 | `String` |
| **Server Name** | `serverName` | @product@インスタンスの名前（例：`liferay.com`）です。ここで、表示されている名前がプロトコルを含む場合は（例：`https://`）、 `/c/portal/login`パスと共に使用され、CASサーバーがチケットを提供するURLを構成します。スキームが無い場合は、@product@のログインページにアクセスするスキームが使用されますス。 | `String` |
| **Server URL** | `serviceURL` | このURLが提供されている場合、CASサーバーがチケットを提供するURLとして使用されます。これは上記のサーバー名に基づいて構築されたURLを上書きします。 | `String` |
| **No Such User Redirect URL** | `noSuchUserRedirectURL` | ユーザーがCASで認証されるが、@product@に登録されていない場合、リダイレクトをするURLを設定します。LDAPからのインポートが有効の場合、ユーザーが見つからない場合、またはLDAPからインポート出来ない場合に、ユーザーはリダイレクトさレます。 | `String` |

特定のポータルインスタンスのシステムのデフォルトを上書きするには、[コントロールパネル] > [インスタンス設定] > [インスタンス設定] へ行き、画面右の [認証] > [CAS]へ行きます。

