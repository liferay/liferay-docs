---
header-id: setting-up-liferay-as-a-saml-service-provider
---

# SAMLService Providerとして@product@を設定する

[TOC levels=1-4]

これらの手順の多くは、@product@をSAML Identity Providerとして設定する手順と似ています。単一の@product@インストールをSAML Identify Provider*または* SAML Service Providerとして設定できますが、両方を設定することはできないのでその点に注意してください。SAML Identity Providerとして1つの@product@インストールをすでに設定している場合は、SAMLService Providerとは*別の*@product@インストールを使用してください。


**注：** SPを@product@としてサードパーティIdPを使用している場合は、IdPから来る全てのメッセージは署名されていなければなりません。そうでない場合は、エラーメッセージが表示され、IdPと@product@間の通信が失敗します。

1. Liferay Connector to SAML 2.0 アプリをインストールします。
アプリが正常にデプロイされたことを確認するには、コントロールパネルのConfigurationセクションで*SAML Admin*エントリーを探します。

2. SAMLを使用するように@product@の設定を開始するには、@product@のSAMLロールを選択し、エンティティIDを選択する必要があります。*Service Provider*SAMLロールを選択します。
独自のエンティティIDを選択してください。次に *[Save]*をクリックすると、CertificateとPrivate Keyという新しいセクションが表示されます。


3. CertificateとPrivate Keyセクションは、SAML用のキーストアを作成するためのものです。
*Create Certificate*をクリックして、以下の情報を入力します。

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

   キーストアには、ファイルシステムストレージ（デフォルト）およびDocuments and Mediaストレージがあります。デフォルトでは、証明書は暗号化のためのSHA256アルゴリズムであり、MD5およびSHA1を介したフィンガープリントおよび自己署名を使用します。必要な情報をすべて入力したら、*[Save]*をクリックしてください。

4.  *[Save]*をクリックした後、証明書に関する情報を表示できるか、証明書をダウンロードできるかを確認してください。できた場合、キーストアが正常に作成されたということです。キーストアを作成した後、追加のオプションが表示されます。3つのタブがあります。



   **General**：SAML IdPを有効または無効にし、必要なキーストアを管理します。

   **Service Provider**：このタブは、SPの基本設定と詳細設定を管理します。

   **Identity Provider Connection**：このタブはIdPへの接続を管理します。
IdP接続は複数ある場合もあります。

5. 暗号化証明書を生成することもできます。これは
暗号化されたアサーションです。暗号化されたアサーションを使用する場合は、キーを生成する必要があります。この手順は、上記の手順3の証明書の生成手順と同じです。

5. 次に、 Identity Provider接続を設定する必要があります。*Identity Provider Connection*タブをクリックし ます。Identity Providerの名前を入力し、そのエンティティIDを入力し、そのメタデータURLを入力します。前の手順を既に実行し、別の@product@インストールをIdentifyプロバイダとして設定している場合は、次の情報を入力します。



   - 名前：*Liferay IdP*

   - Entity ID: [IdPのID]
   - Clock Skew: SPとIdP間の許容値をミリ秒単位で設定します。
   - Force Authn: コンテクストに関係なくIdPが再認証を強制するかどうか。
   - メタデータURL：http://localhost:8080/c/portal/saml/metadata（最初にこのURLをテストしてください）

   - Name Identifier Format: 設定を参照
   - Attribute Mapping: 設定を参照
   - Keep Alive URL: 設定を参照
   **重要**：Liferay Connector to SAML 2.0 アプリを使用してサポートしているのはSAML IdPがメタデータ・ファイルへのURL*または*実際の（アップロード）SAMLメタデータXMLファイルの*どちらか*のみです。*メタデータURL*フィールドに入力された値入力は、入力されたメタデータURLが1つあり、指定されたメタデータXMLファイルがない場合にのみ、データベースに永続化されます。それ以外の場合、@product@は元のメタデータURLをデータベースに保持します。これにより、一度メタデータURLが指定されると、常にメタデータURLがデータベースに保存されるようになります。こうすることで、ポータル管理者が以前に入力したメタデータURLまたはその形式を忘れた場合、表示されているメタデータURLを見て、表示されているメタデータURLを変更するか、メタデータXMLファイルを指定して以前保存したメタデータURLを上書きするかを選択できます。

6. 最後に、証明書とプライベートキーの情報を保存して Identity Provider接続を設定したら、Generalタブの上部にある*Enabled* チェックボックスをオンにして*[Save]*をクリックします。これで@product@がSAMLService Providerになりました！

SAMLService Providerセッションは、アプリケーションサーバー上の通常のセッションに関連付けられています。アプリケーションサーバーでのセッションの有効期限が切れた場合、Service Providerーでのセッションは終了しますが、シングルログアウトは開始されません。

複数のIdP接続を追加できます。別のIDプロバイダーを追加するには、もう一度* Add Identity Provider *をクリックして、他のプロバイダーの詳細を入力しますユーザーがログインすると、IDプロバイダーを選択するように求められるため、ユーザーが認識できるようにプロバイダーに名前を付けてください。

## チェックポイント:

1. SAMLキーストアが生成されている。

2. IdPへの接続を確認する。

   a.  *Name*：IdPの総称名。

   b.  *Entity ID*：IdPの同じ名前。IdPが別の@product@インスタンスである場合、それは上記の例と同じ名前である。

   c. 
*Metadata URL*：URLまたはXMLファイルとしてのIdPのメタデータ。



   d. IdPが別の@productインスタンスである場合は、このSPに対応するService Provider Connectionが有効になっていることを確認する。



3. *General* タブで、*Enabled*チェックボックスがオンになっている。



4. *Enabled* チェックボックスをオンにすると、Service Providerのメタデータが利用可能になります：

   ```bash
   [host]:[port]/c/portal/saml/metadata
   ```

## クラスタ環境でのSAML Service Providerとしての@product@の設定

Liferay Connector to SAML 2.0アプリをクラスタ環境の @product@のSSOソリューションとして使用できます。ロードバランサーの背後でマルチノードクラスタを実行している場合は、次の手順に従ってすべてのノードをSPとして有効にし、keystore managerを共有している必要があります。


Filesystem Keystore Managerを使用する場合（デフォルト）：

1. 上記の指示に従って、[@product@クラスタ](/docs/7-1/deploy/-/knowledge_base/d/liferay-clustering)の各ノードをSAMLService Providerとして設定します。

2. keystoreを（デフォルトの`[Liferay Home]/data/keystore.jks`）最初の@product@ノードから残りのノードにコピーします。このファイルは、SAML Providerアプリによって作成されたJavaキーストアです。keystoreには、SAML Provider アプリによって管理されている有効な証明書または自己署名証明書が含まれています。

3. Service ProviderのメタデータがURLまたはXMLファイルとして使用されるように生成されていることを確認してください。データベースのバックエンドが同じであるため、メタデータはすべてのノードで同じです。IdPのリクエストはロードバランサーを通過します。



4. この時点で、すべてのノードは同じSAML SP設定を持ち、それぞれがWebリクエストにレスポンスしてSAMLプロトコルを処理できるはずです。SSOソリューションをテストするには、ロードバランサーを介して@product@にサインインし、いくつかの異なるサイトのページに移動してログアウトします。

Document Library Keystore Managerを使用している場合は、Keystoreファイルがすべてのノードで共有されているデータベースに格納されているため、手順2は省略して大丈夫です。

以上が@product@をSAML Identity ProviderまたはService Providerとして設定する方法です。これで、クラスタ環境でSAMLを設定することもできるはずです。
