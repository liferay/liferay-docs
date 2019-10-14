---
header-id: installing-liferay-enterprise-search-security
---

# Liferay Enterprise Searchセキュリティのインストール

[TOC levels=1-4]

X-Packをインストールしたら、組み込みのユーザーパスワードを設定してElasticsearchの保護を開始します。



## X-Packセキュリティの有効化

まず、X-Packセキュリティを有効化します。以下の設定を`elasticsearch.yml`に追加します：



    xpack.security.enabled: true

これでX-Packユーザーを設定できます。

## X-Packユーザーの設定


以下の組み込みX-Packユーザーは、X-PackセキュリティとX-Packモニタリングを使用するシステムにとって重要です：



- `kibana`
- `elastic`

すべてのX-Packの[built-in users](https://www.elastic.co/guide/en/x-pack/6.5/setting-up-authentication.html#built-in-users)のパスワードを設定してください 。
`setup-passwords`コマンドは、組み込みユーザーの初回使用パスワードを初めて設定する最も簡単な方法です。その後パスワードを更新するには、KibanaのUIまたは[Change Password API](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/security-api-change-password.html)を使用してください。



`Interactive`引数は、すべての組み込みユーザーのパスワードを設定します。これらの記事に示されている設定方法では、すべてのパスワードが*liferay*に設定してあることを前提としています 。しかし、これはプロダクションシステムにはお勧めできません。



    ./bin/elasticsearch-setup-passwords interactive

Elasticの[setup-passwords command](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/setup-passwords.html)のドキュメンテーションに追加のオプションが記載されています。



Elasticsearchを保護しているので、`elastic`ユーザーのパスワードを忘れないでください。



各ノードでトランスポート層セキュリティを有効化します。

## トランスポート層セキュリティの有効化

TLSを有効にするための以下の手順では、パスワードが必要な時はいつも`liferay`をパスワードとして使用しています。
インストール時には独自のパスワードを使用してください。



| **注：** Elasticsearchと@product@はSSLを設定するにあたってキーと証明書を共有する必要があります。それらをサーバー間でコピーして、対応する設定ファイルにあるローカルコピーを指します。

### ノード証明書を生成する

各ノードに対して[Generate a node certificate](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#node-certificates)。認証局を使用してノード証明書を取得することもできます。



1. [X-Pack`の`certutil](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/certutil.html)コマンドを使用して認証局を作成します 。



       ./bin/elasticsearch-certutil ca --pem --ca-dn CN=localhost
   
   これをすることによりZIPファイルを生成します。
内容を安全な場所に解凍します。


2. ステップ1のCAを使用してX.509の証明書と秘密鍵を生成します。


       ./bin/elasticsearch-certutil cert --pem --ca-cert /path/to/ca.crt --ca-key /path/to/ca.key --dns localhost --ip 127.0.0.1 --name localhost
   
   これをすることにより、別のZIPファイルを生成します。`[Elasticsearch Home]/config`フォルダ内のどこかにコンテンツを抽出します。

| ** 注：**`Certutil`コマンドは証明書を生成するにあたり、デフォルトで*PKSC＃12*形式を使用するようになっています。KibanaはPKSC＃12証明書では機能しないため、X-Pack monitoringを使用している場合`--pem`オプション（PEM形式で証明書を生成する）が重要です。

### TLSを有効化する

`elasticsearch.yml`を使って各ノードのTLSを有効化します。（詳細：[Enable TLS](https://www.elastic.co/guide/en/elasticsearch/reference/6.5/configuring-tls.html#enable-ssl)）


1. 証明書、キー、および認証局のパスを各ノードの`elasticsearch.yml`に追加します。



       xpack.ssl.certificate: /path/to/[Elasticsearch Home]/config/localhost.crt
       xpack.ssl.key: /path/to/[Elasticsearch Home]/config/localhost.key
       xpack.ssl.certificate_authorities: ["/path/to/ca.crt"]
   
   上記のパス例は証明書を`Elasticsearch Home/config/`に追加したことを仮定としています。



2. トランスポート層TLSを以下の設定で`elasticsearch.yml`内で有効にします。



       xpack.security.transport.ssl.enabled: true
       xpack.security.transport.ssl.verification_mode: certificate
   
3. クライアント通信を暗号化するためにHTTPレイヤ上でTLSを有効にします。



       xpack.security.http.ssl.enabled: true
   
X-PackをインストールしてTLSを有効にしたら、@product@でX-Packセキュリティアダプターを設定します。

## X-PackセキュリティにLiferayコネクタのインストールと設定を行う

Liferay Enterprise Search Premiumを購読している場合は 、Liferay ConnectorをX-Packセキュリティ[[Elastic Stack 6.x]]に[download](https://web.liferay.com/group/customer/dxp/downloads/enterprise-search)してください。LPKGファイルを`Liferay Home/deploy`フォルダにコピーしてインストールします。


X-Packアダプターを設定するには、*[Control Panel]* >*[Configuration]* > *[System Settings]*に行きます。*[Search]*カテゴリを見つけて、*X-Pack Security*エントリをクリックします。
ここに直接プロパティ値を入力できますが、 `[Liferay Home]/osgi/configs`にデプロイしてある [configuration file](/docs/7-1/user/-/knowledge_base/u/understanding-system-configuration-files)を使用する方が一般的 です。X-Packアダプターの場合は、以下のファイルを作成します：

    com.liferay.portal.search.elasticsearch6.xpack.security.internal.configuration.XPackSecurityConfiguration.config

ファイルの正確な内容は、X-Packの設定によって異なります。ここに記載されているElasticsearchセットアップに従ってアダプターを設定するには、以下のようにファイルを入力します：

    sslKeyPath="/path/to/[Elasticsearch Home]/config/localhost.key"
    sslCertificatePath="/path/to/[Elasticsearch Home]/config/localhost.crt"
    certificateFormat="PEM"
    requiresAuthentication="true"
    username="elastic"
    password="liferay"
    sslCertificateAuthoritiesPaths="/path/to/[Elasticsearch Home]/config/ca.crt"
    transportSSLVerificationMode="certificate"
    transportSSLEnabled="true"

ここで使用している`password`は上記のX-Packのパスワードを設定した時に使用したものと同じものを使ってください。


認証を有効化するために、`requires Authentication`で`true`を選択し、Elasticsearchユーザーに資格情報を提供してください。SSLの場合は、トランスポートSSLを有効にし、証明書検証モードと証明書フォーマットを設定した後、証明書、キー、および認証局へのパスを提供します。しかし、正確な値はX-Packの設定によって異なります。


以下はX-Pack Connectorの設定オプションの完全なリストで：

- `sslKeyPath`
- `sslCertificatePath`
- `sslCertificateAuthoritiesPaths`
- `certificateFormat`
- `requiresAuthentication`
- `username`
- `password`
- `transportSSLVerificationMode`
- `transportSSLEnabled`
- `sslKeystorePath`
- `sslKeyStorePassword`
- `sslTruststorePath`
- `sslTruststorePassword`

X-Packセキュリティの設定が終了したら、Elasticsearchを再起動してください。これらの手順では、クラスタ全体を再起動する必要があります。
