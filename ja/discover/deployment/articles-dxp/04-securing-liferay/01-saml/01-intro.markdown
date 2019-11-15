---
header-id: authenticating-using-saml
---

# SAMLを使用した認証

[TOC levels=1-4]

SAML（セキュリティアサーションマークアップ言語）アダプターは、展開でシングルサインオン（SSO）とシングルログオフ（SLO）を提供します。各@product@インスタンスは、Service Provider（SP）またはIdentity Provider（IdP）のいずれかとして機能します。Identity Providerは、ユーザーが他のWebサイトにアクセスするためのシングルサインオンを提供する、信頼できるプロバイダです。Service Providerは、アプリケーションをホストするウェブサイトで、適切な資格情報を持つ識別されたユーザーにのみアクセスを許可します。

**注：**単一の@product@インスタンスはSSOセットアップのSPまたはIdPの *どちらか* です；両方ということはありません。しかし、別々のインスタンスを両方の目的に合わせて使用することもできます。（たとえば、1つのインスタンスがSPで、もう1つのインスタンスがIdP）

以下はSAMLのしくみの背景です。設定をすぐに始めたい場合は、[Setting Up SAML as an Identity Provider](/docs/7-1/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-identity-provider)、または[Setting Up SAML as a Service Provider](/docs/7-1/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-service-provider)の記事を参照し、[SAML adapter](https://web.liferay.com/marketplace/-/mp/application/15188711)の使い方について読んでください
これらの手順は、マーケットプレイスで入手可能な4.1.0バージョン用なのでご注意ください。

## Liferay Connector to SAML 2.0の新機能

@product@の4.1.0のアプリケーションのバージョンは、待望の改善をもたらします。

* サービスプロバイダー（SP）として機能する@product@は、複数のIDプロバイダー（IdP）に接続できるようになりました。
* 開発者には、ユーザーのサインインに使用できるアイデンティティプロバイダーをカスタマイズするための拡張ポイントがあります。
* 他の署名アルゴリズムのサポート（`SHA-256など`）
* 署名メソッドアルゴリズムのURLの今（`SHA-1`無効：`http://www.w3.org/2000/09/xmldsig#rsa-sha1`、メタデータからブラックリストにすることができます。）


**注**：バージョン3.1.0より前のLiferay SAMLアダプターから移行する場合、ポータルプロパティは自動的にシステム設定構成に移行されます。設定の詳細については[Configuring SAML](/docs/7-1/deploy/-/knowledge_base/d/configuring-saml)の記事を参照してください。

## 重要なSAMLパス

参考までに、以下はいくつかの重要なSAML パスです。

このURLは、メタデータXMLファイルのデフォルトのロケーションです：


```bash
[host]:[port]/c/portal/saml/metadata
```

SAMLを設定するときは、SAML証明書のインポートは不要です。
@product@はSAMLメタデータXMLファイルから証明書を読み取ります。SalesforceなどのサードパーティーアプリケーションにLiferay SAML証明書を読み取らせる場合は、キーストアから@product@証明書をエクスポートできます。デフォルのキーストアファイルは

```bash
[Liferay Home]/data/keystore.jks
```

このパスは、システム設定→SSO→SAML設定→キーストアパスで変更できます。



## シングルサインオン

IdPとSP、両方ともSingle Sign Onプロセスを開始でき、SSOフローはそれぞれによって異なります。開始方法に関係なく、SSOはSPとIdPの間のHTTPS用に設定されているため、すべてのトランスポートレベルの通信は暗号化されています。SAMLのリクエストは、[SAML 2.0 specification](http://saml.xml.org/saml-specifications)で定義されているSAML WebブラウザSSOプロファイル上で、@product@で設定された証明書を使用して署名されます。

すべての場合において、応答はHTTP-POSTまたはHTTP-Redirectを使用して送信されます。HTTP-POSTが推奨されているのは、URLがブラウザで処理するには長すぎるというリスクを軽減するためです。

IdPが最初にSSOを開始したとします。



### IDプロバイダーが開始したSSO


ユーザーがブラウザからIdPに直接リクエストを送信してSSOサイクルに入ることがあります。

![図1: Identity Providerが開始したSSO](../../../images-dxp/saml-idp-initiated-sso.png)

#### IdPへのSSOリクエスト

@product@がIdPの場合、IdPがSSO URLを開始しました

- パスを`/c/portal/saml/sso`として指定する必要があります。

- 以前に設定されたService Provider Connection（SPC）への識別子である`entityId`パラメータを含める必要があります。

- 認証が成功したときにユーザーがリダイレクトされるURLエンコード値を含む`RelayState`パラメーターを含めることができます。このURLは、希望するSPC上のロケーションを示すはずです。（ [SAML 2.0 standards section 3.4.3](https://docs.oasis-open.org/security/saml/v2.0/saml-bindings-2.0-os.pdf)によると、この値は長さ80バイトを超えては*いけません*。）SSOの実行後、ランディングページを指定しておくと便利です。


（Siteminder、ADFSなど）@product@ IdP以外については、IdPで開始されたSSO URLの構築に関するベンダのドキュメントを参照してください。



IdPがユーザーが認証されていないと判断した場合、IdPはユーザーに適切なログイン画面を表示します。

#### IdPからのSSOレスポンス

認証が成功すると、IdPはSAMLレスポンスを構築します。指定されたService Provider Connection(SPC)で設定された属性ステートメントも含まれています。（@product@のSAML adapter上での設定方法については[こちら](/docs/7-1/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-identity-provider)を参照してください。)

IdPは、Assertion Consumer Service URLに応答を送信します。リクエストは、2つのパラメータ`SAMLResponse`と`RelayState`が含まれています。

** 注：** SAMLレスポンス（HTTP-Postなど）を送信する方法とAssertion Consumer ServiceのURLは通常、SPによって提供されるメタデータXMLのSAMLの一部としてインポートされます。@product@では、SPのメタデータをSAMLアダプタのーのService Provider Connectionsタブ|にインポートします。


#### SPがSSOレスポンスを処理

SPはSAMLレスポンスを検証して処理します。@product@のSAMLソリューションには署名付きSAMLResponseメッセージが必要です。この署名プロセスはIdPの正しい識別を保証し、潜在的なSAMLレスポンスのなりすましを防ぎます。

- 一方の@product@インスタンスがIdPでもう一方がSPの場合は、SPにインポートされたSAMLメタデータXMLファイルにIdPの証明書が含まれていることを確認してください。

- @product@がIdPで別のアプリケーションがSPの場合は、IdPから証明書をエクスポートしてSPの証明書ストアにインポートします。


SAMLレスポンスに`RelayState`が含まれている場合、ユーザーはそこにリダイレクトされます。そうでなければ、SPのホームページが提供されます。

### Service ProviderがSSOを開始

ほとんどの場合、認証要求はサービスプロバイダーから送信されます。

![図2: Service Providerが開始したSSO](../../../images-dxp/saml-sp-initiated-sso.png)

#### SPへのSSOリクエスト

ユーザーのブラウザがSP上に保護されたリソースまたはログインURLをリクエストすると、SPによって開始されたSSOプロセスが起動されます。@product@がSAML SPの場合、SSOは`/c/portal/login`URLまたは認証を必要とする保護されたリソース（たとえば、ゲストとして表示できない文書）をリクエストすることによって開始されます。ユーザーが保護されたリソースをリクエストした場合、そのURLは`RelayState`パラメータに記録されます。ユーザーが`/c/portal/login`をリクエストした場合は、`redirect`パラメータを提供することで` RelayState`を設定することができます。
それ以外の場合、[portal property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html)
`auth.forward.by.last.path`が`true`に設定されている、最後にアクセスしたパスは`RelayState`に設定されます。@product@ SP以外の場合は、SSOの開始に関するベンダーのドキュメンテーションを参照してください。

#### IdPへの認証リクエスト

SPはIdPのSingle Sign OnサービスURLを検索して`AuthnRequest`を送信します。@product@がSPの場合、設定されたSAMLdentity
Provider Connectionを検索し、SAML`AuthnRequest`を、SAMLメタデータXMLドキュメントで定義されているIdPのSingle Sign OnサービスURLに送信します。@product@は、HTTP-PostバインディングまたはHTTP-Redirectバインディングを使って`AuthnRequest`の送受信をサポートしています。HTTP-Postが推奨されています。

ユーザーがアクティブなセッションを持っていない場合、またはSPから`ForceAuthnSP`がリクエストされた場合、ユーザーは自分の資格情報を提供して認証を受ける必要があります。@product@がIdPの場合、Login Portletで認証が行われます。@product@はユーザーに認証リクエストする前に、`AuthnRequest`をデコードおよび検証します。

#### IdPからのSSOレスポンス

認証後、SAMLレスポンスが構築され、SPのAssertion Consumer Service URLに送信されて検証されます。IdPは自動的にSPメタデータに基づいてこの選択を行います。

@product@がIdPとして設定されている場合、Service
Provider Connectionで設定されているすべての属性は、属性ステートメントとしてレスポンスに含まれます。Assertion Consumer Service URLは、SPのSAMLメタデータXMLから検索されます。

@product@がSPとして設定されている場合、すべてのレスポンスおよびアサーションの署名が検証されます。@product@は送信者の認証をリクエストします。これは、発行IdPからのメッセージ全体の署名を介して行われます。署名がないレスポンスはすべて認証されていないと見なされ、レスポンスは拒否されます。@product@ SPまたはIdP以外のベンダーの場合は、ベンダーのドキュメンテーションを参照してください。

ユーザーは、リクエストされたリソースまたは`RelayState`パラメーターに含まれているURL （例えば、ユーザーがSSOを開始する前にアクセスした最後のページ）にリダイレクトされます 。

## シングルログオフ

Single Log OffリクエストはユーザーのブラウザからIdPまたはSPに送信され、SLOフローはそれぞれの場合で異なります。まずIdPがSLOを開始したとします。

### Identity ProviderがSLOを開始した


![図３: Identity Providerが開始したSLO](../../../images-dxp/saml-idp-initiated-slo.png)

#### IdPへのSLOリクエスト<x1/><x2/>

IdPが開始したSLO要求は、ユーザーのブラウザーによってIdPに直接送信されます。
@product@がIdPとして機能する場合、IdPで開始されたSSO URLは次のようにURLパスを指定する必要があります：

`/c/portal/logout`

ユーザが設定済みのSPにサインオンしている場合は、SAMLプラグインがログアウトプロセスを引き継ぎ、すべてのサインオンサービスを表示します。シングルログアウト画面には、各SPの認証ステータス、およびログアウトできないかSPがあるかどうかが表示されます（たとえば、SPが停止している場合やSLOをサポートしていない場合）。@product@ IdP以外（Siteminder、ADFSなど）については、IdPで開始されたSLO URLの構築に関するベンダーのドキュメンテーションを参照してください。

IdPはSAML`LogoutRequest`をSPに送信します。

- @product@がIdPとして設定されている場合、HTTP-Post、HTTP-Redirect、またはSOAPバインディングのいずれかを使用して、`LogoutRequest`が送信されます。HTTP-Postバインディングが優先されますが、ない場合は、サポートされているバインディングを持つ最初の使用可能なSLOエンドポイントが選択されます。

- @product@がSPとして設定されている場合、`LogoutRequest`でサポートされているバインディングはHTTP-Post、HTTP-Redirect、またはSOAPです。

- 他のIdPまたはSPについては、ベンダーのドキュメンテーションを参照してください。

#### SPからのSLOレスポンス<x1/><x2/>

SPはIdPに`LogoutResponse`を配信します。

IdPはSAML`LogoutRequest`をSPに送信します。

SPはIdPに`LogoutResponse`を配信します。ユーザーがログインしているすべてのSPに対してこのプロセスが繰り返されます。@product@がIdPの場合、@product@は最後のSPが配信した`LogoutResponse`またはタイムアウトした後にユーザーをログアウトさせます。

### Service ProviderがSLOを開始

![図 4:Service Providerが開始したSLO](../../../images-dxp/saml-sp-initiated-slo.png)

#### SPへのSLOリクエスト<x1/><x2/>

SPによって開始されたSLOでは、ユーザーのブラウザはログアウトリクエストを直接SPに送信します。@product@がSPとして設定されている場合、SLOは次のログアウトURLをリクエストすることによって開始されます。

    /c/portal/logout

他のSPについては、SLOの開始に関するベンダーのドキュメンテーションを参照してください。

SAML`LogoutRequest`はIdPのSingle Log OutサービスURLに送信されます。

- @product@がSPとして機能する場合、`LogoutRequest`はSAMLプロバイダーのIdP Connectionタブで設定されたIdP（IdP接続を設定するためには次の記事を参照してください：[next article](/docs/7-2/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-identity-provider)）およびSAMLメタデータで定義されたSLOサービスURLに送信されます。

- @product@がIdPの場合、ユーザーが他のSPにログオンしていると、各SPログアウトの状態を示す単一のログアウト画面が現れ、ログアウトできないものも表示されます。（いくつかのSPはSLOをサポートしていないか、または現在停止中のものがある場合もあります）ログアウトするSPが他にない場合、SAMLセッションは終了し、IdPはそのセッションを破棄します。

#### SPからのSLOレスポンス<x1/><x2/>

ユーザーが追加のSPにログインしている場合（開始SPだけでなく）、IdPはSAML `LogoutRequest`を各SPに送信します。@product@がIdPの場合は、 `LogoutResponse`はHTTP-Post、HTTP-Redirect、またはSOAPバインディングのいずれかを使用して送信されます。

各SPはそれぞれの`LogoutResponse`をIdPに配信します。@product@がSPの場合は、 `LogoutResponse`はHTTP-Post、HTTP-Redirect、またはSOAPリクエストへの直接レスポンスのいずれかを使用して送信されます。

全ての追加のSPがそれぞれの`LogoutResponse`をIdPに配信した後、IdPはそのSSOセッションを破棄します。@product@がIdPの場合、最後のSPが`LogoutResponse`を配信した時点、またはタイムアウトした時点でIdPは@product@セッションを破棄し、ユーザーをログアウトさせます。



最後に、IdPはSLOを開始したSPに`LogoutResponse`を送信します。開始したSPはSAMLセッションを終了し、ユーザーをログアウトさせます。



## 関連トピック

[Setting Up SAML as an Identity Provider](/docs/7-1/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-identity-provider)

[Setting Up SAML as a Service Provider](/docs/7-1/deploy/-/knowledge_base/d/setting-up-liferay-as-a-saml-service-provider)

[Token-Based SSO Authentication](/docs/7-1/deploy/-/knowledge_base/d/token-based-single-sign-on-authentication)
