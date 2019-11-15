---
header-id: changing-the-settings-for-service-provider-and-identity-provider-connection
---

# SAMLService Provider設定の変更

[TOC levels=1-4]

SAMLサービスプロバイダーの設定を変更するには、[サービスプロバイダー]タブに移動します

Service Providerタブには、次のオプションがあります:

**Require Assertion Signature?:**このボックスをオンにすると、SAMLメッセージ全体に加え、SAMLアサーションに個別に署名する必要があります。

**注：** SAMLレスポンス自体が署名されていれば、個々のアサーションに署名する必要はありません。[fuzzy]トランスポートレベルで暗号化されるよう、SPとIdPは常に`https`上で通信する必要があります。
中間者攻撃が可能であると思われる場合は、SAMLレスポンスは署名できます。アサーションに署名する唯一の理由は、SAMLレスポンスが署名されていない場合です。この場合、アサーションは署名されるだけでなく暗号化されるべきです。

**Clock Skew**：Clock Skewは、アイデンティティプロバイダのクロックとサービスプロバイダ間の時間差を緩和するサービスプロバイダーで使用されるミリ秒単位の許容範囲です。これは通常、アサーションが非常に早く期限切れになるようにされているときにだけ問題になります。 

**LDAP Import Enabled：**解決されたNameIDに基づいて設定されたLDAP接続からユーザー情報がインポートするにはこのボックスにチェックを入れます。[fuzzy]LDAP接続は Instance Settingsから設定できます。

**Sign Authn Requests:** Identity Provider のメタデータが署名が不要でもAuthnRequestに署名するには、このボックスにチェックを入れます。

**Sign Metadata:**メタデータXMLファイルに署名するには、このボックスにチェックを入れます。

**SSL Required:** このボックスをチェックすると、送信されていないSAMLメッセージを拒否するにはチェックを入れます。これはURLの生成方法には影響しません。

## SAML Identity Provider接続設定の変更

@product@のSAML Identity Provider設定を設定するには、SAML AdminポートレットのIdentity Provider Connectionに移動し、設定するIdPの*Edit*アクションボタンをクリックします。

**Name:**接続先のIdentity Providerの名前。



**Entity ID:**  Identity ProviderのエンティティID。この値は、Identity Providerメタデータで宣言されているエンティティIDと一致する必要があります。



**Enabled**:このボックスにチェックを入れて IdPを有効にします。

**Clock Skew**：Clock Skewは、アイデンティティプロバイダのクロックとサービスプロバイダ間の時間差を緩和するサービスプロバイダーで使用されるミリ秒単位の許容範囲です。これは通常、アサーションが非常に早く期限切れになるようにされているときにだけ問題になります。 

**Force Authn:**チェックされている場合、Service ProviderはIdentity Providerに、ユーザを確認する前にユーザの再認証をリクエストします。

**Metadata:** Identity Provider のメタデータXMLファイルへURLを提供するか、マニュアルでアップロードすることもできます。URLを指定した場合、XMLファイルは自動的に取得され、定期的に更新についてポーリングされます。[System Settings]で[Runtime Metadata Refresh Interval]プロパティを変更することで、更新間隔を変更できます 。URLによるメタデータXMLファイルの取得に失敗すると、アイデンティティプロバイダ接続を有効にできません。メタデータがURL経由でアクセスできない場合は、XMLファイルをマニュアルでアップロードできます。この場合、メタデータXMLファイルは自動的には更新されません。

**Name Identifier Format:** SAML Responseで使用されるName Identifier Format を選択します。これはService Providerが何を受け取るのかによって設定してください。
LiferayService Providerの場合、メールアドレス以外の選択は、Name Identifierが画面名を指すことを示します。フォーマットは、Liferay Identity Providerにとって特別な意味はありません。Name Identifier属性<x3 /> NameID <x4 />値を定義します。

**Attribute Mapping:** Attribute mappingは、attribute名またはSAML Response内のフレンドリ名から@product@属性名で行われます。例えば、`mail`とい属性名を@product@属性の`emailAddress`にマッピングしたい場合は、次のマッピングを入力します。

    mail=emailAddress

利用可能な@product@ 属性は`emailAddress`、`screenName`、`firstName`、 `lastName`、`modifiedDate`、と`uuid`です。

**Keep Alive URL:** ユーザーが@product@ IdPを介して複数の@product@ SPインスタンスにログインしている場合、そのうちの１つのブラウザウィンドウを開いている限り、セッションをアクティブに保つことができます。

IdPが@product@の場合にのみ設定してください。URLは`https://[IdP host name]/c/portal/saml/keep_alive`です。@product@ IdPで、このURLを同じように設定しますが、SPを示すようにしてください。



@product@インスタンスをService Providerとして設定し終わったら、変更を保存してください。サーバーを再起動する必要はなく、変更はすぐに適用されます。

上記の設定は、SAMLコントロールパネルインターフェースを使用して行い、
プロパティ経由ではありません。Liferay Connector to SAML 2.0アプリの一部の機能はプロパティとして利用可能です。

**制限事項：** Liferay SAMLアプリは単一の仮想環境ホストでのみ使用できます。Technically, this means that in the SAML metadata for @product@, only one
| binding can be added in this form:
|
| ```xml
| <md:EntityDescriptor>
| ...
| <md:SPSSODescriptor>
| ...
| <md:AssertionConsumerService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="https://portal.domain.com/c/portal/saml/acs" index="1" isDefault="true" />
| ...
| </md:SPSSODescriptor>
| </md:EntityDescriptor>

