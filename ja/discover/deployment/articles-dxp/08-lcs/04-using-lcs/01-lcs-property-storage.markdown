# LCSがLiferay DXPサーバーに保存していること [](id=what-lcs-stores-about-your-liferay-dxp-servers)

ユーザーのプライバシーを保護するために、LCSはシステム固有のデータのみを保存します。LCSはユーザーのデータを収集または保存しません。



デフォルトでは、LCSはサーバーに関する以下の情報を保存します。

- ポータルのビルド番号とエディション

- パッチ適用ツールのバージョン

- LCSクライアントビルド番号

- アプリケーションサーバー名

- データベース名

- ファイルエンコーディング

- OS名とバージョン

- タイムゾーン

- IPアドレス

- JavaのバージョンとJavaのオプション

- プロセッサコア数

- ファイルシステムの使用量

- メモリ使用量


LCSが保存するその他のデータは、環境トークンで有効にしたサービスによって異なります。これに関する詳細については、[Registering Servers with LCS](/discover/deployment/-/knowledge_base/7-1/activating-your-liferay-dxp-server-with-lcs)を参照してください 。
次のサービスを有効にすると、LCSはそれぞれにリストされたデータを収集して保存します。

- **ポータル分析:**

   - ポータルとポートレットメトリクス

   - JVMメトリクス

   - キャッシュとサーバーメトリクス


- **フィックスパック管理:**

   - サーバーにインストールされているパッチ


- **ポータルプロパティ分析:**

   - `portal.properties` (機密情報以外)

機密データは、ユーザー名またはパスワードを含むキーと値のペアです。例えば、以下のプロパティには機密データが含まれているから、LCSはそのプロパティを保存しません。

    omniadmin.users
    ldap.security.credentials.0, ldap.security.credentials.1, ldap.security.credentials.2 ...
    facebook.connect.app.secret
    auth.token.shared.secret
    auth.mac.shared.key
    captcha.engine.recaptcha.key.private
    amazon.secret.access.key
    tunneling.servlet.shared.secret
    microsoft.translator.client.secret
    dl.store.s3.secret.key
    auto.deploy.glassfish.jee.dm.passwd

LCSは以下の機密ではないプロパティ以外は、末尾に`.password`があるプロパティを保存しません。

    portal.jaas.plain.password
    portal.jaas.strict.password
    login.create.account.allow.custom.password

LCSでは、選択した特定のプロパティを分析できないようにすることもできます。これに関する詳細は、[Registering Your Liferay DXP Server with LCS](/discover/deployment/-/knowledge_base/7-1/activating-your-liferay-dxp-server-with-lcs)を参照してください 。
