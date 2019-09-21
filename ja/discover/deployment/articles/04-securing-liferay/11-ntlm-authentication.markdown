---
header-id: ntlm-single-sign-on-authentication
---

# NTLMシングルサインオン認証

NTLM (NT LAN Manager)は認証、整合性、ユーザー情報の機密性の保持を提供するMicrosoftプロトコルのスイートです。
Microsoftは、最新版のWindowsサーバーにKerberosを採用していますが、ワークグループの認証に現在もNTLMが使用されています。 @product@ はNTLM v2認証もサポートしています。 NTLM v2はより安全性が高く、認証プロセスがNTLMv1より強化されています。


+$$$

**注**:NTLMは@product-ver@では推奨されておらず、将来のバージョンでは削除される可能性があります。

$$$

NTLM SSOを使用するには、@product@のポータルインスタンスの認証タイプをスクリーン名に設定することが条件です。


+$$$

**注意**: @product@で NTLMを使用するには、ブラウザーの設定が必要です。ブラウザー提供元の資料で設定の詳細を確認してください。

$$$

一番重要なのは、Active Directory(以下AD)サーバーから全てのユーザーが*インポートされる必要がある*という点です。
NTLM (およびKerberos)はユーザーがAD上にあるときのみ使用できます。


そうでない場合は、Liferay DXPが開始したSSO要求が正常に処理されません。NTLM設定は、システムレベルまたはポータルインスタンスレベルで適用できます。NTLM SSOモジュールをシステムレベルで設定するには[コントロールパネル] > [設定] > [システム設定 ] > [セキュリティ] > [SSO] > [NTLM]へ行きます。そこで設定した値は、デフォルト値は全てのポータルインスタンスで使用されます。Javaプリミティブ型をリテラル値で開始する時と同じフォーマットで値を入力します。

| プロパティーレベル | プロパティーキー | 説明 | タイプ |
---- | ---- | ---- | ----
| **Enabled** | `enabled` | NTLN SSO認証を有効にするには、このボックスにチェックを入れます。 @product@の認証タイプがスクリーン名に設定されている時のみ、NTLMが作動するので注意してください。 | `boolean` |
| **Domain Controller** | `domainController` | ドメインコントローラのIPアドレスを入力します。これは@product@で使うユーザーアカウントを含むサーバです。 | `String` |
| **Domain Controller Name** | `domainControllerName` | ドメインコントローラの NetBIOS名を指定します。 | `String` |
| **Domain** | `domain` | ドメイン／ワークグループ名を入力 | `String` |
| **Service Account** | `serviceAccount` | NTLM用にサービスアカウントを作成する必要があります。 このアカウントはユーザーアカウントではなくて、コンピューターアカウントになります。 | `String` |
| **Service Password** | `serviceAccount` | サービスアカウントのパスワードを入力します。 | `String` |
| **Negotiate Flags** | `negotiateFlags` | システムレベルでのみ使用可能です。クライアントの要求する範囲とサーバーのServerCapabilitiesに合わせて設定します。詳細は[こちら](http://msdn.microsoft.com/en-us/library/cc717152%28v=PROT.10%29.aspx) | `String` |


AD名とIPアドレスは` domainControllerName`と`domainController`設定に対応していることに注意してください。
`Service Account`は NTMLアカウント用で（_NTLM_に登録されています)、@product@ユーザーアカウント用ではありません。

システムデフォルトを特定のプロパティーポータル用に上書きするには[コントロールパネル] > [設定] > [インスタンス設定] > [認証] > [NTLM]をクリックします。


## まとめ

NTLM認証は、IT部署がクライアントのデバイスでどのソフトウェアが使用されているかコントロールするようなシナリオでは、NTLMの互換性は大きな利点です。Active Directoryベースのネットワーク／ドメインでは、NTLMが可能にするユーザーエクスペリエンス以上に優れているものは多くありません。


NTLM SSOを使用するには、@product@インスタンスの認証タイプが、スクリーン名に指定され、全ユーザーがActive Directoryにインポートされているか確認してください。この方法が @product@実装に適していない場合は、他の SSO 方法 (CASなど)を使い、ポータルとNTLM認証プロセス間のブローカーとして使用できます。
