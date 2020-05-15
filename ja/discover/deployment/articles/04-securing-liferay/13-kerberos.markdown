---
header-id: authenticating-with-kerberos
---

# Kerberosを使用した認証

[TOC levels=1-4]

Kerberosを使用して、Liferay DXPでMicrosoft Windows™アカウントを認証できます。これは、Liferay DXPのLDAPサポートとKerberosプロトコルをサポートするWebサーバーの組み合わせを使用することにより、設定を通じて完全に行われます。

セキュリティの脆弱性が持続するため、この構成はNTLMよりも優先されることに注意してください。

Windows &tradeサーバーでKerberosとActive Directoryをセットアップする方法を説明することはこの記事の範囲を超えていますが、Liferay認証をセットアップするための最小限の前提条件を説明できます。

1. ADサーバーと@product@がネットワーク上で相互に解決できるようにActive DirectoryとDNSが設定されたWindows &tradeサーバー、言い換えれば、*名前*でお互いにpingできる必要があります。

2. AD Liferay DXPの管理ユーザーは、ADにバインドするために使用できます。

3. @product@サーバーがADにバインドする必要がある暗号化情報を含む`ktpass`コマンドを介してエクスポートされたKerberosキータブファイル。

4. Apache、NGNIX、IISなど、Kerberosをサポートする@product@の前のWebサーバー。Webサーバーは、@product@構成でトークンとして使用されるヘッダーの挿入もサポートする必要があります（以下を参照）。

5. もちろん、他のサーバーの名前でも解決できる@product@ インストールが必要です。Active Directoryサーバーで実行しないでください。

これらのすべての前提条件が整ったら、Kerberos認証を構成する準備が整います。

## Kerberos認証の仕組み

前提条件から、SSOがKerberosでどのように機能するかにいくつかの可動部分があると推測できる場合があります。

![図1：Kerberos認証では、Liferay DXPサーバーの前にWebサーバーが必要です。図1：Kerberos認証では、Liferay DXPサーバーの前にWebサーバーが必要です。

まず、適切に構成されたWebブラウザーが、暗号化されたWindowsユーザーデータを使用してネゴシエート要求を送信します。これを設定するには、ブラウザがサイトを信頼済みサイトとして認識する必要があります（以下で説明します）。WebサーバーのKerberosモジュールは、keytabファイルを使用して、Kerberosプロトコルを介してADにバインドし、ユーザー情報を確認します。すべて問題なければ、ADサーバーは有効な応答で接続を確認します。

選択するWebサーバーは、Kerberosプロトコルと、@product@が後で読み取れる要求へのカスタムヘッダーの挿入の両方をサポートする必要があります。
Webサーバーがリクエストを@product@に転送すると、ヘッダーを読み取ってユーザーデータを取得し、ユーザーを認証します。

次に、このすべてを機能させる方法を学びます。

## Kerberos認証の構成

構成する4つのコンポーネントがあります。ActiveDirectoryのユーザーキータブ、アプリケーションサーバーの前にあるWebサーバー、Liferay DXP、およびWindows™クライアントです。

### ユーザーキータブの作成

1. Liferay DXPがActive Directoryにバインドできるようにユーザーを作成します。

2. `ktpass`を使用してKerberosキータブファイルを生成します。

       ktpass -princ HTTP/[web server host name]@[domain] -mapuser [user name]@[domain] -crypto ALL -ptype KRB5_NT_PRINCIPAL -pass [password] -out c:\kerberos.keytab

   例：

       ktpass -princ HTTP/mywebserver.intdomain.local@INTDOMAIN.LOCAL -mapuser Marta@INTDOMAIN.LOCAL -crypto ALL -ptype KRB5_NT_PRINCIPAL -pass password-for-Marta -out c:\kerberos.keytab

3. ADドメインコントローラーとWebサーバーが、DNS構成または`hosts`ファイルを介してネットワーク上で相互に認識できることを確認します。

### WEBサーバーの構成

1. erberos認証の構成Linuxでは、krb5 Active Directory用にすでに構成されているレルムに一致するようにインストールおよび構成する必要があります。上記の手順2で構成したユーザーのサンプルドメインは次のようになります。

       [libdefaults]
       default_realm = INTDOMAIN.LOCAL
       
       [domain_realm]
       mywebserver.intdomain.local = INTDOMAIN.LOCAL
       intdomain.local = INTDOMAIN.LOCAL
       .intdomain.local = INTDOMAIN.LOCAL
       
       [realms]
       INTDOMAIN.LOCAL = {
       admin_server = winserver.intdomain.local
       kdc = winserver.intdomain.local
       }

2. ADサーバーで生成したキータブファイルをWebサーバーにコピーします。

3. Webサーバーを構成し、正しいサーバー名、Kerberosサービス名、Kerberos認証レルム、およびキータブファイルへのパスを設定していることを確認します。
たとえば、Apache HTTPサーバーを使用している場合、構成は次のようになります。


       LoadModule headers_module /usr/lib/apache2/modules/mod_headers.so
       LoadModule rewrite_module /usr/lib/apache2/modules/mod_rewrite.so
       LoadModule proxy_module /usr/lib/apache2/modules/mod_proxy.so
       LoadModule proxy_http_module /usr/lib/apache2/modules/mod_proxy_http.so
       LoadModule proxy_ajp_module /usr/lib/apache2/modules/mod_proxy_ajp.so
       LoadModule auth_kerb_module /usr/lib/apache2/modules/mod_auth_kerb.so
       
       <VirtualHost *:10080>
       <Proxy *>
       Order deny,allow
       Allow from all
       </Proxy>
       ProxyRequests     Off
       ProxyPreserveHost On
       ProxyPass / ajp://localhost:8009/
       ProxyPassReverse / ajp://localhost:8009/
       ServerName mywebserver.intdomain.local
       <Location />
       Order allow,deny
       Allow from all
       AuthType Kerberos
       KrbServiceName HTTP/mywebserver.intdomain.local@INTDOMAIN.LOCAL
       AuthName "Domain login"
       KrbAuthRealms INTDOMAIN.LOCAL
       Krb5KeyTab /etc/apache2/kerberos.keytab
       require valid-user
       KrbMethodNegotiate  On
       KrbMethodK5Passwd   Off
       #KrbLocalUserMapping On
       
       # Below directives put logon name of authenticated user into http header X-User-Global-ID
       RequestHeader unset X-User-Global-ID
       RewriteEngine On
       RewriteCond   %{LA-U:REMOTE_USER} (.+)
       RewriteRule   /.* - [E=RU:%1,L,NS]
       RequestHeader set X-User-Global-ID %{RU}e
       
       # Remove domain suffix to get the simple logon name
       #RequestHeader edit X-User-Global-ID "@INTLAND.LOCAL$" ""
       
       </Location>
       </VirtualHost>
       Listen 10080

### @product@ をLDAP経由でACTIVE DIRECTORYに接続する

1. 最後に、LDAPプロトコルを介してActive DirectoryにアクセスするようにLiferay DXPを構成します。[設定]→[インスタンス設定]→[認証]→[一般]で選択して、スクリーン名による認証に変更します。

2. 構成→インスタンス設定→認証→LDAPに進み、LDAPサーバーを追加して、Liferay DXPをLDAP経由でADに接続します。
インストールに適切な情報を提供します。

   **基本プロバイダーURL：**適切なポート上のADサーバー。

   **ベースDN：**ドメイン構成。上記の例は次のようになります` DC=INTDOMAIN.DC=LOCAL`。

   **Principal/Credentials:**： keytabファイルにエクスポートされたユーザーのクレデンシャルを提供します。

   **Authentication Search Filter:**ユーザーオブジェクトを返すための適切な検索フィルターを提供します。例えば、`(&(objectCategory=person)(sAMAccountName=*))`

   **UUID**：`sAMAccountName`など、ユーザーを一意に識別するものを指定します。

   **スクリーン名：** @product@のスクリーン名フィールド、`sAMAccountName`などにマッピングするフィールドを指定します。

   **パスワード：**`userPassword`などのユーザーのパスワードを含むフィールドを指定します 。

3. 接続をテストし、構成を保存して有効にしてください。

4. 最後に、構成→システム設定→セキュリティ→SSO→トークンベースSSOでシングルサインオン用のトークンを構成します。ユーザートークン名がWebサーバーで設定したトークンと*正確に*一致することを確認してください。有効とLDAPからのインポートのチェックボックスをクリックし、保存をクリックします。

 サーバーを構成しました。あとは、クライアントを構成するだけです。

### クライアントの構成

2つのことを行う必要があります。コンピューターをドメインにログインさせ、@product@サーバーを信頼できるインターネットサイトとして構成します。

1. コンピューターをドメインに参加させます。上記の例を維持するために、コンピューターを`INTDOMAIN.LOCAL`ドメインのメンバーにします。

2. そのドメインのユーザーとしてログインします。

3. Internet Explorer、Edge、およびChromeは、信頼済みサイトにWindows&trade設定を使用します。これらのブラウザーを使用する場合は、インターネットオプション→セキュリティ→ローカルイントラネットサイトに移動し、@product@サーバーのURLを追加します。
例えば、 `http://mywebserver.intdomain.local:10080`を追加します。

4. Firefoxは`about:config`をアドレスバーに入力することで構成できます。以下の2つの設定を検索し、Liferay DXPサーバーのURLを両方の値として追加します。

   - `network.negotiate-auth.delegation-uris`
   - `network.negotiate-auth.trusted-uris`

これらを設定した後、WebサーバーのURLからLiferay DXPにアクセスして、設定をテストします。クライアントマシンにすでにログインしているため、ユーザー/パスワードのプロンプトなしで@product@に自動的にログインする必要があります。

これで設定の完了です。
