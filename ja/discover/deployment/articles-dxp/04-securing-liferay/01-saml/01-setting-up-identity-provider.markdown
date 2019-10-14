---
header-id: setting-up-liferay-as-a-saml-identity-provider
---

# SAML Identity Providerとして@product@を設定する

[TOC levels=1-4]

Identity Providerは、ユーザーが他のWebサイトにアクセスするためのシングルサインオンを提供する、信頼できるプロバイダです。Service Providerは、アプリケーションをホストするウェブサイトで、適切な資格情報を持つ識別されたユーザーにのみアクセスを許可します。
SAMLは[OASIS Security Services Technical Committee]（https://www.oasis-open.org/ committees/security/）によって管理されています。
Liferay Portal 6.1 EE以降のバージョンは、[Liferay SAML 2.0 Provider](https://web.liferay.com/marketplace/-/mp/application/15188711)アプリケーションを通じてLiferay SAML 2.0の統合をサポートしています。Liferayマーケットプレイスから提供されており、@product@をSAML 2.0 Identity ProviderまたはService Providerとして機能させることができます。 
**重要：**@product@をIdentity ProviderまたはService Providerとして設定できます。それぞれの単一の@product@インスタンスは、Identity ProviderまたはService Providerのどちらかとして機能できますが、**両方**は機能できません。この記事では両方の設定方法について説明します。

SAML Identity Providerとして機能するように@product@ upを設定するには、以下の手順に従ってください。
先に進む前にまず、以下の手順3でSAMLのためにキーストアを生成するということに注目してください。
このキーストアには2つのストレージオプションがあります。

    - In the file system
    - In the Documents and Media library

ファイルシステムキーストアマネージャがデフォルトで使用され、デフォルトのロケーションは`[Liferay Home]/data`ディレクトリです。ファイルシステムストレージの代わりにDocumentおよびMedia Libraryストレージをキーストアに使用するには、document library
keystore managerを使用します。

キーストアマネージャを選択するには、*[Control Panel]*>*[System Settings]*>*[SAML KeyStoreManager Implementation Configuration]*に行きます。そこでのオプションは*Filesystem Keystore Manager*と*Document Library Keystore Manager*です。



 Document Libraryストレージを使用する利点は、[back end file stores](/docs/7-0/deploy/-/knowledge_base/d/document-repository-configuration)をいくつでも使用できることです。
これらはキーを保存しているシステム上だけでなく@product@のパーミッションシステムによっても保護されています。

SAML Identity Providerとして機能するように@product@を設定する手順は次のとおりです。



1. Liferay SAML 2.0 Providerアプリをインストールします。
SAMLのAdminインターフェースにアクセスするには、*[Control Panel]*>*[Configuration]* へ行き、 *[SAML Admin]*をクリックします。



2. SAMLを使用するように@product@の設定を開始するには、@product@のSAMLロールを選択してエンティティIDを選択します。



   <!-- [Figure 1: Select a SAML role for Liferay and enter an entity ID.](../../../images-dxp/saml-initial-config.png) -->

   *Identity Provider*のSAMLロールを選択します。模範の@product@インスタンスを設定する場合は、*liferaysamlidp*を入力してください。あるいは、独自のエンティティIDを選択してください。そしたら*[Save]*をクリックします。新しいCertificateとPrivate Key のセクションが表示されます。



3. CertificateとPrivate Keyのセクションでは、SAML用のキーストアを作成できます。
*Create Certificate*をクリックして、以下の情報を入力してください。

   - ご自身のお名前（姓名）

   - 組織名

   - 組織内の部署名

   - 街や地区名

   - 都道府県名

   - 国名

   - キーストアが有効である日数（キーストアの有効期限が切れるまでの期間）

   - キーアルゴリズム（RSAがデフォルト）

   - キーの長さ（ビット単位）（2048がデフォルト）

   - キーパスワード

   必要な情報をすべて入力したら、*[Save]*をクリックしてください。



   証明書とプライベートキーを作成するときに、キーストアがない場合それも作成できます。前述のように、このキーストアには、ファイルシステムストレージ（デフォルト）とDocumentとMedia storageの2つのストレージオプションがあります。



4. *[Save]*をクリックした後、*[Replace Certificate]*をクリックして、有効期限が切れていたり、キーのパスワードを変更したい場合に新しい証明書に置き換えることができます。



   <!-- [Figure 2: The General tab of the SAML Admin portlet displays information about the current certificate and private key and allows administrators to download the certificate or replace the certificate.](../../../images-dxp/saml-keystore-info.png) -->

   さらに3つのタブが表示されます。

   - *General*：このタブでは、SAML IdPを有効または無効にしたり、必要なキーストアを管理したりできます。



   - *Identity Provider*：このタブには、SSLを有効にするかどうかなど、その他の必要な設定が含まれています。SSLが有効になっている場合、SAMLリクエストも暗号化されていない限り承認されません。



   - *Service Provider Connections*：このタブは、この@product@インスタンスに接続しているService Providerを管理します。



      Identity ProviderおよびService Provider接続タブの詳細については、以下を参照してください。



5. 証明書とプライベートキーの情報を保存したら、Generalタブの一番上にある*Enabled*のボックスをチェックして*[Save]*をクリックします。
これで@product@をSAML Identity Providerとして設定できました。

## Identity Provider設定の変更

@product@のSAML Identity Provider設定を設定するには、SAML Admin Control Panelエントリーの*Identity Provider*に移動します。



*Identity Provider*タブには、次のオプションがあります：



**Sign Metadata?:** ：このボックスをチェックすると、作成されたMetadata XMLファイルに署名が付けられます。



**SSL Required:** このボックスをチェックすると、 SSL経由で送信されて*いない*SAMLメッセージはすべて拒否されます。これは、生成されたメタデータ内のURLに影響します。



**Require Authn Request Signature?:** このボックスをチェックすると、各Authn
Requestは送信側のService Providerによって署名されなければなりません。ほとんどの場合、これは有効にしておくべきです。

**Session Maximum Age:** SAML SSOセッションの最大期間を秒単位で指定します。このプロパティが設定されていない、または`0`に設定されている場合、SSOセッションの期間は無制限です。SSOセッションの最大期間は、ポータルセッションの最大期間より長くすることができます。SSOセッションが期限切れになる前にポータルセッションが期限切れになると、ユーザーは自動的に@product@にログインし直されます。SSOセッションの有効期限が切れても、すべてのService Providerからのシングルログアウトは引き起こされません。
たとえば、セッションの最長有効期間を使用して、一定期間後にユーザーに再度サインインを強制することができます。



**Session Timeout:** SAML SSOセッションの最大アイドル時間を指定する。


セッションの最大経過時間が無制限であっても、ユーザーのアイドル時間がセッションタイムアウトプロパティで設定された制限に達すると、SSOセッションは期限切れになります。

## チェックポイント

Service Provider（SP）を追加する前に、次の作業が完了していることを確認してください：



1. SAMLキーストアが生成されている。`data`フォルダまたはDocumentとMedia libraryの2つの場所のいずれかに格納できます。



2. *Identity Provider*タブで、次の設定が設定されている：



   a. **Sign Metadata** がチェックされている。



   b. **SSL Required**  - 他の場所でSSLがアクティブになっている場合チェックされている。SSLはデフォルトで無効になっている。



   c. **Authn Request Signature Required:** がチェックされている。



   d. **Session Maximum Age:** が設定されている。`0`に設定されている場合、SSOの期間は無制限です。


   e. **Session Timeout:** SAML SSOセッションの最大アイドル時間を指定する。



3. *Enabled* チェックボックスをオンにすると、IdPが有効になり、必要なメタデータを生成できます。このURLは、@product@のメタデータXMLファイルのデフォルトの場所です。

       [host]:[port]/c/portal/saml/metadata
   
このURLが正しく表示されない場合は、SAMLインスタンスが有効になっていない証拠です。
URLを使用するか、ブラウザで*Save* をクリックして実際の`XML`ファイルを生成します。



## SAMLService Providerの追加

@product@のSAML Identity Providerとしての設定は、1つ以上のSAMLService Providerに接続できる場合にのみ役立ちます。SAML Admin Control PanelエントリのService
Provider Connectionsタブに移動し、*Add Service Provider*のボタンをクリックしてSAML Service Providerを追加します。



新しいService Providerページには、次のオプションがあります。



**Name:** 接続するService Providerの名前。名前は何でもかまいません。



**Entity ID:** Service ProviderのエンティティID。この値は、Service Providerのメタデータで宣言されているエンティティIDと一致する必要があります。


**Enabled:** このボックスをチェックすると、Service Provider接続が有効になります。



**Assertion Lifetime:** Identity Provider によって発行されたSAMLアサーションが期限切れと見なされるまでの秒数を定義します。



**Metadata:**Service ProviderメタデータXMLファイルへURLを提供するか、またはService ProviderメタデータXMLファイルをマニュアルでアップロードすることができます。URLを指定した場合、XMLファイルは自動的に取得され、定期的に更新についてポーリングされます。更新間隔は秒数を指定するプロパティ`saml.metadata.refresh.interval`を使用して[System Settings]で設定できます。URLによるメタデータXMLファイルの取得に失敗すると、Service Provider接続を有効にできません。Identity ProviderサーバがURLを介してメタデータにアクセスできない場合は、XMLファイルをマニュアルでアップロードできます。
この場合、メタデータXMLファイルは自動的には更新されません。

**Name Identifier Format:** SAML Responseで使用されるName Identifier Format を選択します。これはService Providerが何を受け取るのかによって設定してください。LiferayService Providerの場合、メールアドレス以外の選択は、Name Identifierが画面名を指すことを示します。フォーマットは、Liferay Identity Providerにとって特別な意味はありません。NameID値は、Name Identifierの属性によって定義されます。次のオプションを見てください。



**Name Identifier Attribute Name:** これは、@product@`User`オブジェクトのどの属性をNameID値として使用するかを指定します。可能な値は`emailAddress`、`screenName`および`uuid`です。また、名前の前に`static:`または`expando:`を付けることもできます。接頭辞 `static`を使用すると、値は`static:`の後に続くものになります。接頭辞`expando`を使用した場合、値は`expando:`の後に指定されたカスタムフィールドになります。例えば `expando:SSN`は`SSN`と名付けられた`User`カスタムフィールドを検索します。



**Attributes Enabled:** アサーション属性を含めて解決します。



**Attributes Namespace Enabled:** このボックスをチェックすると、属性名は次のような名前空間となります：

    urn:liferay:user:expando:
    urn:liferay:user:
    urn:liferay:groups:
    urn:liferay:organizationRole:
    urn:liferay:organization:
    urn:liferay:roles:
    urn:liferay:siteRole:
    urn:liferay:userGroupRole:
    urn:liferay:userGroups:

**Attributes:** アサーションに含める属性のリストを1行に1つずつ入力します。

各行は解析される式です。

例：

    organizations
    organizationRoles
    roles
    siteRoles
    userGroups
    static:[attributeName]=[attributeValue]
    expando:[userCustomFieldName]

名前空間は属性名に依存するということに注意してください。属性の名前空間はとても便利です。異なる名前空間からの属性名が競合する可能性がある場合に使用してください。例えば、`expando:user`対`urn:liferay:roles:user`。



**Keep Alive URL:**ユーザーが@product@ IdPを介して複数の@product@ SPインスタンスにログインしている場合、ブラウザウィンドウを開いている限り、セッションをアクティブに保つことができます。これはSPが@product@の場合にのみ設定してください。
URLは`https://[SP host name]/c/portal/saml/keep_alive`です。

### チェックポイント

@product@ベースのIdPを最初のSPに接続するときに設定が正しいことを確認してください。SPは1つのIdPにのみ接続するため、最初のIdPが機能しない場合、残りのIDPも機能しません。



1. SPの一般名を入力してください。



2. `Entity ID` 名は、Service Providerのメタデータで宣言されている名前と同じである必要があります。



3. *Enabled* チェックボックスをオンにします。



4. *Assertion Lifetime*に値を設定します。



5. SPのメタデータがURLとして提供されているか、XMLファイルがアップロードされているかどうか確認してください。



6.  *Name Identifier Format* と*Name Identifier Attribute Name* が設定されていることを確認してください。



7. *Attributes Namespace Enabled* が設定されていることを確認してください。



今追加するService Providerを持っていないのであれば、それで結構です。次のセクションでは、@product@をSAMLService Providerとして設定する方法を学びます。別の@product@インスタンスをService Providerとして設定した後、この@product@インストールに戻ってService Providerを追加します：*[Control Panel]*
>*[SAML Admin]*>*[Service Provider Connections]* >*[Add Service
Provider]*
