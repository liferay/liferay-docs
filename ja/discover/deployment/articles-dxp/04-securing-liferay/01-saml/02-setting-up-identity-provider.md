---
header-id: setting-up-liferay-as-a-saml-identity-provider
---

# SAML Identity Providerとして@product@を設定する

[TOC levels=1-4]

Identity Providerは、ユーザーが他のWebサイトにアクセスするためのシングルサインオンを提供する、信頼できるプロバイダです。Service Providerは、アプリケーションをホストするウェブサイトで、適切な資格情報を持つ識別されたユーザーにのみアクセスを許可します。
Liferay Portal 6.1 EE以降のバージョンは、<x3/>Liferay Connector to SAML 2.0<x4/>を通じてLiferay SAML 2.0の統合をサポートしています。Liferayマーケットプレイスから提供されており、@product@をSAML 2.0 Identity ProviderまたはService Providerとして機能させることができます。 

**重要：** @product@をIdentity ProviderまたはService Providerとして設定できます。それぞれの単一の@product@インスタンスは、Identity ProviderまたはService Providerのどちらかとして機能できますが、<x7/>両方<x8/>は機能できません。この記事では両方の設定方法について説明します。

## キーストアの保存

最初のステップは、キーストアの保存場所を決定することです。次の2つのオプションがあります。

- ファイルシステム
- ドキュメントとメディアライブラリ内

ファイルシステムキーストアマネージャーがデフォルトで使用され、デフォルトの場所は`[Liferay Home]/data`ディレクトリです（システム設定→SSO→SAML設定→キーストアパスで場所を変更できます）。ファイルシステムストレージの代わりにドキュメントおよびメディアライブラリストレージをキーストアに使用するには、コントロールパネル → システム設定 → セキュリティ → SSO → SAML KeyStoreManager Implementation Configurationに進みます。Filesystem Keystore ManagerまたはDocument Library Keystore Managerの2つのオプションから選択します。

ドキュメントライブラリストレージを使用する場合、任意の数のバックエンドファイルストアを使用できます。
これらは、キーが保存されているシステムだけでなく、Liferay DXPの許可システムによっても保護されています。

## Liferay DXPをSAML IDプロバイダーとして構成する

1. SAMLのAdminインターフェースにアクセスするには、*[Control Panel]*>*[Configuration]* へ行き、 *[SAML Admin]*をクリックします。

2. SAMLを使用するように@product@の設定を開始するには、@product@のSAMLロールを選択してエンティティIDを選択します。

   [図1：LiferayのSAMLロールを選択し、エンティティIDを入力します](../../images-dxp/saml-initial-config.png)

   *Identity Provider*のSAMLロールを選択します。独自のエンティティIDを入力。そしたら*[Save]*をクリックします。新しいCertificateとPrivate Key のセクションが表示されます。

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

   そしたら*[Save]*をクリックします。

   証明書とプライベートキーを作成するときに、キーストアがない場合それも作成できます。前述のように、このキーストアには、ファイルシステムストレージ（デフォルト）とDocumentとMedia storageの2つのストレージオプションがあります。

デフォルトでは、証明書は` SHA256 `アルゴリズムを使用して暗号化され、RSAおよび`SHA256`を介してフィンガープリントおよび自己署名されます。

4. *[Save]*をクリックした後、*[Replace Certificate]*をクリックして、有効期限が切れていたり、キーのパスワードを変更したい場合に新しい証明書に置き換えることができます。



   [図2：SAML管理ポートレットの[全般]タブには、現在の証明書と秘密キーに関する情報が表示され、管理者は証明書をダウンロードしたり、証明書を置き換えたりできます。](../../images-dxp/saml-keystore-info.png)

   さらに3つのタブが表示されます。

   **General**：このタブはSAML IdPを有効または無効にし、必要なキーストアを管理します。

   **IDプロバイダー：**SSLを有効にするかどうかなどのIdPオプションが含まれます。
SSLが有効になっている場合、SAMLリクエストも暗号化されていない限り承認されません。

   **Service Provider Connections**：このタブは、この@product@インスタンスに接続しているService Providerを管理します。

   Identity ProviderおよびService Provider接続タブの詳細については、以下を参照してください。



5. 証明書とプライベートキーの情報を保存したら、Generalタブの一番上にある*Enabled*のボックスをチェックして*[Save]*をクリックします。
これで@product@をSAML Identity Providerとして設定できました。

## Identity Provider設定の変更

@product@のSAML Identity Provider設定を設定するには、SAML Admin Control Panelエントリーの*Identity Provider*に移動します。



*Identity Provider*タブには、次のオプションがあります：



**Sign Metadata?:** ：このボックスをチェックすると、作成されたMetadata XMLファイルに署名が付けられます。

**SSL Required:** このボックスをチェックすると、送信されていないSAMLメッセージを拒否するにはチェックを入れます。これは、生成されたメタデータ内のURLに影響します。

**Require Authn Request Signature?:** このボックスをチェックすると、各Authn
Requestは送信側のService Providerによって署名されなければなりません。ほとんどの場合、これは有効にしておくべきです。

**Session Maximum Age:** SAML SSOセッションの最大期間を秒単位で指定します。このプロパティが設定されていない、または`0`に設定されている場合、SSOセッションの期間は無制限です。SSOセッションの最大期間は、ポータルセッションの最大期間より長くすることができます。SSOセッションが期限切れになる前にポータルセッションが期限切れになると、ユーザーは自動的に@product@にログインし直されます。SSOセッションの有効期限が切れても、すべてのService Providerからのシングルログアウトは引き起こされません。
たとえば、セッションの最長有効期間を使用して、一定期間後にユーザーに再度サインインを強制することができます。



**Session Timeout:** SAML SSOセッションの最大アイドル時間を指定する。


セッションの最大経過時間が無制限であっても、ユーザーのアイドル時間がセッションタイムアウトプロパティで設定された制限に達すると、SSOセッションは期限切れになります。

## チェックポイント:

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



IDプロバイダの設定が完了しました。次は、サービスプロバイダの登録です。
