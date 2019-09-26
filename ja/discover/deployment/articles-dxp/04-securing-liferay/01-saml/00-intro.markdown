---
header-id: what-is-saml
---

# SAMLとは？

[TOC levels=1-4]

SAML（Security Assertion Markup Language）アダプタを使用すると、デプロイでSingle Sign On（SSO）とSingle Log Off（SLO）を実行できます。各@product@インスタンスは、Service Provider（SP）またはIdentity Provider（IdP）のいずれかとして機能します。この記事では、@product@のSSOソリューションの概念的な枠組みを説明します。



- Single Sign On
   - Identity ProviderがSSOを開始した

   - Service ProviderがSSOを開始した


- Single Log Off


   - Identity ProviderがSLOを開始した

   - Service ProviderがSLOを開始した

| **注：**単一の@product@インスタンスはSSOセットアップのSPまたはIdPの *どちらか* です；両方ということはありません。しかし、別々のインスタンスを両方の目的に合わせて使用することもできます。（たとえば、1つのインスタンスがSPで、もう1つのインスタンスがIdP）
| 
| 

以下はSAMLのしくみの背景です。設定をすぐに始めたい場合は、[Setting Up SAML as an Identity Provider](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-identity-provider-ja)、または[Setting Up SAML as a Service Provider](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-service-provider-ja)の記事を参照し、[SAML adapter](https://web.liferay.com/marketplace/-/mp/application/15188711)の使い方について読んでください

| ** 注：**3.1.0バージョン以前のLiferay SAMLアダプターから移行する場合、プロパティは自動的に設定に移行されます。設定の詳細については[Configuring SAML](/discover/deployment/-/knowledge_base/7-1/configuring-saml-ja)の記事を参照してください。

## 重要なSAML URL

参考までに、以下はいくつかの重要なSAML URLです。



このURLは、メタデータXMLファイルのデフォルトのロケーションです：


    [host]:[port]/c/portal/saml/metadata

SAMLを設定するときは、SAML証明書のインポートは不要です。
@product@はSAMLメタデータXMLファイルから証明書を読み取ります。SalesforceなどのサードパーティーアプリケーションにLiferay SAML証明書を読み取らせる場合は、キーストアから@product@証明書をエクスポートできます。デフォルトのキーストアファイルは`[Liferay Home]/data/keystore.jks`です。エクスポートされた証明書は、Salesforceなどのサードパーティーアプリケーションによってインポートできます。


## Single Sign On

IdPとSP、両方ともSingle Sign Onプロセスを開始でき、SSOフローはそれぞれによって異なります。開始方法に関係なく、SSOはSPとIdPの間のHTTPS用に設定されているため、すべてのトランスポートレベルの通信は暗号化されています。SAMLのリクエストは、[SAML 2.0 specification](http://saml.xml.org/saml-specifications)で定義されているSAML WebブラウザSSOプロファイル上で、@product@で設定された証明書を使用して署名されます。



IdPが最初にSSOを開始したとします。



### Identity Providerが開始したSSO


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

認証が成功すると、IdPはSAMLレスポンスを構築します。指定されたService Provider Connection(SPC)で設定された属性ステートメントも含まれています。（@product@のSAML adapter上での設定方法についてはこちら[next article](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-identity-provider-ja)を参照してください。)

IdPはHTTP-POSTまたはHTTPリダイレクトを使用してAssertation Consumer Service URLにレスポンスを送信します。HTTP-POSTが推奨されているのは、URLがブラウザで処理するには長すぎるというリスクを軽減するためです。HTTP-POSTを使用すると、リクエストには2つのパラメータが含まれています。

    SAMLResponse

そして

    RelayState

| ** 注：** SAMLレスポンス（HTTP-Postなど）を送信する方法とAssertion Consumer ServiceのURLは通常、SPによって提供されるメタデータXMLのSAMLの一部としてインポートされます。@product@では、SPのメタデータをSAMLアダプタのーのService Provider Connectionsタブ|にインポートします。
| 

#### SPがSSOレスポンスを処理する

SPはSAMLレスポンスを検証して処理します。@product@のSAMLソリューションでは、`SAMLResponse`メッセージに署名する必要があります。この署名プロセスはIdPの正しい識別を保証し、潜在的なSAMLレスポンスのなりすましを防ぎます。

- 一方の@product@インスタンスがIdPでもう一方がSPの場合は、SPにインポートされたSAMLメタデータXMLファイルにIdPの証明書が含まれていることを確認してください。

- @product@がIdPで別のアプリケーションがSPの場合は、IdPから証明書をエクスポートしてSPの証明書ストアにインポートします。


SAMLレスポンスに`RelayState`が含まれている場合、ユーザーはそこにリダイレクトされます。そうでなければ、SPのホームページが提供されます。

### Service Providerが開始したSSO

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

認証後、SAMLレスポンスが構築され、SPのAssertion Consumer Service URLに送信されて検証されます。

@product@がIdPとして設定されている場合、Service
Provider Connectionで設定されているすべての属性は、属性ステートメントとしてレスポンスに含まれます。Assertion Consumer Service URLは、SPのSAMLメタデータXMLから検索されます。レスポンスはHTTP-PostまたはHTTPリダイレクトバインディングを使用して送信されます。IdPは自動的にSPメタデータに基づいてこの選択を行います。利用可能であれば、HTTP-Postバインディングが優先され、使用されます。多くの場合、署名と含まれているアサーションはブラウザにとってURLが長すぎるため、HTTP-Redirectバインディングは脆いです。

@product@がSPとして設定されている場合、すべてのレスポンスおよびアサーションの署名が検証されます。@product@は送信者の認証をリクエストします。これは、発行IdPからのメッセージ全体の署名を介して行われます。署名がないレスポンスはすべて認証されていないと見なされ、レスポンスは拒否されます。レスポンスは、HTTP-PostバインディングまたはHTTPリダイレクトバインディングを介して受信できます。
前のセクションで説明した理由から、HTTP-Postバインディングが推奨されています。@product@ SPまたはIdP以外のベンダーの場合は、ベンダーのドキュメンテーションを参照してください。

ユーザーは、リクエストされたリソースまたは`RelayState`パラメーターに含まれているURL （例えば、ユーザーがSSOを開始する前にアクセスした最後のページ）にリダイレクトされます 。

## Single Log Off

Single Log OffリクエストはユーザーのブラウザからIdPまたはSPに送信され、SLOフローはそれぞれの場合で異なります。まずIdPがSLOを開始したとします。

### Identity Provider が開始したSLO

![図３: Identity Providerが開始したSLO](../../../images-dxp/saml-idp-initiated-slo.png)

#### IdPへのSLOリクエスト

IdPが開始したSLOリクエストは、ユーザーのブラウザによってIdPに直接送信されたSLOリクエストです。@product@がIdPとして機能する場合、IdPで開始されたSSO URLは次のようにURLパスを指定する必要があります：

`/c/portal/logout`

ユーザが設定済みのSPにサインオンしている場合は、SAMLプラグインがログアウトプロセスを引き継ぎ、すべてのサインオンサービスを表示します。シングルログアウト画面には、各SPの認証ステータス、およびログアウトできないかSPがあるかどうかが表示されます（たとえば、SPが停止している場合やSLOをサポートしていない場合）。@product@ IdP以外（Siteminder、ADFSなど）については、IdPで開始されたSLO URLの構築に関するベンダーのドキュメンテーションを参照してください。

IdPはSAML`LogoutRequest`をSPに送信します。

- @product@がIdPとして設定されている場合、HTTP-Post、HTTP-Redirect、またはSOAPバインディングのいずれかを使用して、`LogoutRequest`が送信されます。HTTP-Postバインディングが優先されますが、ない場合は、サポートされているバインディングを持つ最初の使用可能なSLOエンドポイントが選択されます。

- @product@がSPとして設定されている場合、`LogoutRequest`でサポートされているバインディングはHTTP-Post、HTTP-Redirect、またはSOAPです。

- 他のIdPまたはSPについては、ベンダーのドキュメンテーションを参照してください。

#### SPからのSLOレスポンス

SPはIdPに`LogoutResponse`を配信します。@product@がSPとして設定されている場合、`LogoutResponse`はHTTP-Post、HTTP-Redirect、またはSOAPリクエストへの直接レスポンスのいずれかを使用して配信されます。HTTP-Postバインディングが優先されますが、ない場合はHTTP-Redirectが使用されます。SOAPは`LogoutRequest` へのレスポンスにのみSOAPバインティングの代わりに使用されます。

IdPはSAML`LogoutRequest`をHTTP-Post、HTTP-Redirect、またはSOAPバインディングのいずれかを使用して、2番目のSPに送信します。


次に2番目のSPは`LogoutResponse`をHTTP-Post、HTTP-Redirect、またはSOAPリクエストへの直接レスポンスを使用してIdPに配信します。ユーザーがログインしているすべてのSPに対してこのプロセスが繰り返されます。@product@がIdPの場合、@product@は最後のSPが配信した`LogoutResponse`またはタイムアウトした後にユーザーをログアウトさせます。

### Service Providerが開始したSLO

![図 4:Service Providerが開始したSLO](../../../images-dxp/saml-sp-initiated-slo.png)

#### SPへのSLOリクエスト

SPによって開始されたSLOでは、ユーザーのブラウザはログアウトリクエストを直接SPに送信します。@product@がSPとして設定されている場合、SLOは次のログアウトURLをリクエストすることによって開始されます。

    /c/portal/logout

他のSPについては、SLOの開始に関するベンダーのドキュメンテーションを参照してください。

SAML`LogoutRequest`はIdPのSingle Log OutサービスURLに送信されます。

- @product@がSPとして機能する場合、`LogoutRequest`はSAMLプロバイダーのIdP Connectionタブで設定されたIdP（IdP接続を設定するためには次の記事を参照してください：[next article](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-identity-provider-ja)）およびSAMLメタデータで定義されたSLOサービスURLに送信されます。リクエストはHTTP-POSTまたはHTTPリダイレクトバインディングを使用して送信されます。



- @product@がIdPの場合、ユーザーが他のSPにログオンしていると、各SPログアウトの状態を示す単一のログアウト画面が現れ、ログアウトできないものも表示されます。（いくつかのSPはSLOをサポートしていないか、または現在停止中のものがある場合もあります） ログアウトするSPが他にない場合、SAMLセッションは終了し、IdPはそのセッションを破棄します。

#### SPからのSLOレスポンス

ユーザーが追加のSPにログインしている場合（開始SPだけでなく）、IdPはSAML `LogoutRequest`を各SPに送信します。@product@がIdPの場合は、 `LogoutResponse`はHTTP-Post、HTTP-Redirect、またはSOAPバインディングのいずれかを使用して送信されます。

各SPはそれぞれの`LogoutResponse`をIdPに配信します。@product@がSPの場合は、 `LogoutResponse`はHTTP-Post、HTTP-Redirect、またはSOAPリクエストへの直接レスポンスのいずれかを使用して送信されます。

全ての追加のSPがそれぞれの`LogoutResponse`をIdPに配信した後、IdPはそのSSOセッションを破棄します。@product@がIdPの場合、最後のSPが`LogoutResponse`を配信した時点、またはタイムアウトした時点でIdPは@product@セッションを破棄し、ユーザーをログアウトさせます。



最後に、IdPはSLOを開始したSPに`LogoutResponse`を送信します。開始したSPはSAMLセッションを終了し、ユーザーをログアウトさせます。



## 関連トピック

- [Setting Up SAML as an Identity Provider](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-identity-provider-ja)
- [Setting Up SAML as a Service Provider](/discover/deployment/-/knowledge_base/7-1/setting-up-liferay-as-a-saml-service-provider-ja)
- [Token-Based SSO Authentication](/discover/deployment/-/knowledge_base/7-1/token-based-single-sign-on-authentication-ja)
