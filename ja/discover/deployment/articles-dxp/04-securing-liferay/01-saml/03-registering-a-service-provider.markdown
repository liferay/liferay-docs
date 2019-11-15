---
header-id: registering-a-saml-service-provider
---

# SAMLサービスプロバイダーの登録

[TOC levels=1-4]

@product@のSAML Identity Providerとしての設定は、1つ以上のSAMLService Providerに接続できる場合にのみ役立ちます。SAML Admin Control PanelエントリのService
Provider Connectionsタブに移動し、*Add Service Provider*のボタンをクリックしてSAML Service Providerを追加します。



新しいService Providerページには、次のオプションがあります。



**Name:** 接続するService Providerの名前。名前は何でもかまいません。



**Entity ID:** Service ProviderのエンティティID。この値は、Service Providerのメタデータで宣言されているエンティティIDと一致する必要があります。


**Enabled：**このボックスをオンにすると、サービスプロバイダー接続がアクティブになります。

**Assertion Lifetime:** Identity Provider によって発行されたSAMLアサーションが期限切れと見なされるまでの秒数を定義します。



**Force Encryption:** SPがアサーションを暗号化するための公開キーを提供しない場合、シングルサインオンを中止します。

**Metadata:**Service ProviderメタデータXMLファイルへURLを提供するか、またはService ProviderメタデータXMLファイルをマニュアルでアップロードすることができます。URLを指定した場合、XMLファイルは自動的に取得され、定期的に更新についてポーリングされます。更新間隔は、システム設定で、秒数を指定するランタイムメタデータリフレッシュ間隔（`config`を使用する場合、`saml.metadata.refresh.interval`）プロパティで構成できます。URLによるメタデータXMLファイルの取得に失敗すると、Service Provider接続を有効にできません。Identity ProviderサーバがURLを介してメタデータにアクセスできない場合は、XMLファイルをマニュアルでアップロードできます。この場合、メタデータXMLファイルは自動的には更新されません。

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

## チェックポイント:

@product@ベースのIdPを最初のSPに接続するときに設定が正しいことを確認してください。SPは1つのIdPにのみ接続するため、最初のIdPが機能しない場合、残りのIDPも機能しません。



1. SPの一般名を入力してください。



2. `Entity ID` 名は、Service Providerのメタデータで宣言されている名前と同じである必要があります。



3. *Enabled* チェックボックスをオンにします。



4. *Assertion Lifetime*に値を設定します。



5. 暗号化が必要かどうかを選択します（推奨）。

6. SPのメタデータがURLとして提供されているか、XMLファイルがアップロードされているかどうか確認してください。



7.  *Name Identifier Format* と*Name Identifier Attribute Name* が設定されていることを確認してください。



8. *Attributes Namespace Enabled* が設定されていることを確認してください。



今追加するService Providerを持っていないのであれば、それで結構です。次のセクションでは、@product@をSAMLService Providerとして設定する方法を学びます。同じインスタンスを両方にすることはできませんが、別の @product@ インスタンスをサービスプロバイダーとして設定した後、このインスタンスに戻り、上記の手順に従ってサービスプロバイダーを追加します。
