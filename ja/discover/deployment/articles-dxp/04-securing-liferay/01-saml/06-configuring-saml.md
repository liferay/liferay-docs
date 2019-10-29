---
header-id: configuring-saml
---

# SAMLの設定方法<x1/><x2/>

[TOC levels=1-4]

UIの外部でSAMLプラグインを構成する方法がいくつかあります。これは、OSGi構成ファイルを介して行われ、メタデータXMLをアップロードして接続のネゴシエーション方法を構成します。

## OSGi構成プロパティ

前の設定方法で説明されていたように、SP接続の設定に関することはすべて、設定がLiferayのデータベースに保存されているSAML管理UIを介して行う必要があります。SP接続は、3.1.0より前のバージョンの時のように、プロパティーファイルを介して行うことができなくなりました。



**注意：** OSGi`.config`ファイルまたは@product@のSystem Settings Control Panel アプリをSAMLプロバイダー（IdPまたはSP）を設定するために使用しないでください。System Settings UIは自動生成され、上級管理者向けです。管理者が無効な設定を作成できるように、SAML Admin UIが実行するフィールドの検証を強化は実行されません。

これはOSGi Configuration Adminで管理できるポータルインスタンススコープの設定です。影響を受けるプロパティは`SAMLProviderConfiguration`メタタイプ内ものです。

    - `keyStoreCredentialPassword()`
    - `keyStoreEncryptionCredentialPassword()`
    - `assertionSignatureRequired()`
    - `authnRequestSignatureRequired()`
    - `clockSkew()`
    - `defaultAssertionLifetime()`
    - `entityId()`
    - `enabled()`
    - `ldapImportEnabled`
    - `role()`
    - `sessionMaximumAge`
    - `sessionTimeout()`
    - `signAuthnRequest()`
    - `signMetadata()`
    - `sslRequired()`
    - `allowShowingTheLoginPortlet()`

SAML Admin UIは、ポータルインスタンススコープの設定インスタンスを作成するための場所です。



`SamlConfiguration`メタタイプで表されるシステム全体の設定もあります。



Liferay 6.2を使用していた場合は、以下のシステム全体のプロパティが削除されています。

    `saml.metadata.paths` (served no purpose after removal of SP connection defaults)
    `saml.runtime.metadata.max.refresh.delay`
    `saml.runtime.metadata.min.refresh.delay`

後者の2つのプロパティはこちらの単一のプロパティに置き換えられました：`saml.runtime.metadata.refresh.interval`。

 また[コントロールパネル] → [システム設定 ]→[セキュリティ]→[SSO]で、SAML KeyStoreManager実装設定の導入しています。この設定のオプションについては、@product@をSAML Identity Providerとして設定するセクションで説明しています。

プラグインの最新バージョンでは、`SHA256`アルゴリズムは設定で使用され、キーを生成するためのデフォルトの暗号化アルゴリズムです。デフォルトの設定は`SHA1`にフォールバックする前に`SHA256`、その後`SHA384`、`SHA512`を試行します。SHA1潜在的に脆弱であるため、このプロパティを使用してブラックリストに登録できます。

```properties
blacklisted.algorithms=["blacklisted_algorithm_url", "another_blacklisted_algorithm_url"]
```

したがって、`SHA1`をブラックリストに登録するには、次の構成が必要です。

```properties
blacklisted.algorithms=["http://www.w3.org/2000/09/xmldsig#sha1"]
```

これらを以下の名前の設定ファイルに配置します。

```bash
com.liferay.saml.opensaml.integration.internal.bootstrap.SecurityConfigurationBootstrap.config
```

メタデータXMLを構成する場合、接続がネゴシエートされる方法にはさらに多くの細分性があります。

## metadata.xmlを介したネゴシエーションの構成

デフォルトのネゴシエーション構成が機能しない場合は、独自の構成を作成してアップロードできます。これを行う前に、ホストのメタデータURLにアクセスし、後で必要に応じて構成のコピーを保存します。

    http://[hostname]/c/portal/saml/metadata

たとえば、<x1 /> SHA1 <x2 />のみをサポートするレガシIdPへの接続が停止している場合、他のアルゴリズムを無効にする構成をアップロードできます。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<md:EntityDescriptor xmlns:md="urn:oasis:names:tc:SAML:2.0:metadata" entityID="samlidp">
  <md:IDPSSODescriptor WantAuthnRequestsSigned="true" protocolSupportEnumeration="urn:oasis:names:tc:SAML:2.0:protocol">
    <md:Extensions>
      <alg:SigningMethod xmlns:alg="urn:oasis:names:tc:SAML:metadata:algsupport" Algorithm="http://www.w3.org/2000/09/xmldsig#rsa-sha1"/>
    </md:Extensions>
    <md:KeyDescriptor use="signing">
      <ds:KeyInfo xmlns:ds="http://www.w3.org/2000/09/xmldsig#">
        <ds:X509Data>
          <ds:X509Certificate>... omitted ...</ds:X509Certificate>
        </ds:X509Data>
      </ds:KeyInfo>
    </md:KeyDescriptor>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleLogoutService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/slo"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-Redirect" Location="http://localhost:8080/c/portal/saml/sso"/>
    <md:SingleSignOnService Binding="urn:oasis:names:tc:SAML:2.0:bindings:HTTP-POST" Location="http://localhost:8080/c/portal/saml/sso"/>
  </md:IDPSSODescriptor>
</md:EntityDescriptor>
```

上記の構成では、<md:Extensions>ブロックには署名アルゴリズムが1つしかありませんSHA1。

**注：**デフォルトの設定は`SHA1`にフォールバックするため、レガシーシステムがフォールバックメカニズムを介してネゴシエートできない場合を除き、これを行う必要はありません。また、`SHA1`をブラックリストに登録した場合、これは機能しません。[SHA1`の脆弱性のため、可能な限り完全に使用しないことをお勧めします。

メタデータ構成を変更した場合、変更を行う前に保存していた場合、デフォルト構成に戻すことができます。そうでない場合は、アップロードされたXMLファイルの代わりに、ピアのメタデータ構成の1つにURLを提供できます。
