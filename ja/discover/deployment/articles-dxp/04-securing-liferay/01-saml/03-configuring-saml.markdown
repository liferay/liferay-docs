---
header-id: configuring-saml
---

# SAMLの設定方法

[TOC levels=1-4]

前の設定方法で説明されていたように、SP接続の設定に関することはすべて、設定がLiferayのデータベースに保存されているSAML管理UIを介して行う必要があります。SP接続は、3.1.0より前のバージョンの時のように、プロパティーファイルを介して行うことができなくなりました。



| **注意：** OSGi`.config`ファイルまたは@product@のSystem Settings Control
| Panel アプリをSAMLプロバイダー（IdPまたはSP）を設定するために使用しないでください。System Settings UIは自動生成され、上級管理者向けです。管理者が無効な設定を作成できるように、SAML Admin UIが実行するフィールドの検証を強化は実行されません。
| 
| 

これはOSGi Configuration Adminで管理できるポータルインスタンススコープの設定です。影響を受けるプロパティは`SAMLProviderConfiguration`メタタイプ内ものです。

    - `saml.keystore.credential.password`
    - `saml.sp.assertion.signature.required`
    - `saml.idp.authn.request.signature.required`
    - `saml.sp.clock.skew`
    - `saml.default.assertion.lifetime`
    - `saml.sp.default.idp.entity.id`
    - `saml.enabled`
    - `saml.entity.id`
    - `saml.sp.ldap.import.enabled`
    - `saml.role`
    - `saml.idp.session.maximum.age`
    - `saml.idp.session.timeout`
    - `saml.sp.sign.authn.request`
    - `saml.sign.metadata`
    - `saml.ssl.required`
    - `saml.idp.metadata.name.id.attribute`

SAML Admin UIは、ポータルインスタンススコープの設定インスタンスを作成するための場所です。



`SamlConfiguration`メタタイプで表されるシステム全体の設定もあります。



Liferay 6.2を使用していた場合は、以下のシステム全体のプロパティが削除されています。

    `saml.metadata.paths` (served no purpose after removal of SP connection defaults)
    `saml.runtime.metadata.max.refresh.delay`
    `saml.runtime.metadata.min.refresh.delay`

後者の2つのプロパティはこちらの単一のプロパティに置き換えられました：`saml.runtime.metadata.refresh.interval`。



 また、*[Control Panel]*>*[System Settings]*内に*SAML KeyStoreManager Implementation
Configuration*が新しく追加されています。この設定のオプションについては、@product@をSAML Identity Providerとして設定するセクションで説明しています。
