---
header-id: service-access-policies
---

# サービスアクセスポリシー

[TOC levels=1-4]

サービスアクセスポリシーは、リモートサービスに対するユーザー認証を超えるWebサービスセキュリティを提供します。[アクセス許可](/docs/7-1/tutorials/-/knowledge_base/t/defining-application-permissions)と合わせて、サービスアクセスポリシーは、リモートクライアントアプリケーションによるリモートサービスアクセスを制限します。これにより、不正なアクセスや変更からユーザーデータを保護する追加のセキュリティレイヤーが形成されます。

Webサービスに接続するには、リモートクライアントはそのインスタンスの資格情報を使用して認証する必要があります。これにより、@product@のインストール時にこれらの資格情報に割り当てられた権限がリモートクライアントに付与されます。さらに、サービスアクセスポリシーは、ポリシーで指定されたサービスへのリモートクライアントのアクセスを制限します。
このようなポリシーがなければ、認証されたリモートクライアントはユーザーのように扱われます。認証されたユーザーに代わって、リモートAPIを呼び出してデータを読み取ったり変更したりできます。リモートクライアントは特定のユースケースを対象としている場合が多いため、リモートクライアントにユーザーがアクセス権を持っているすべてのものへのアクセスを許可すると、セキュリティリスクが生じます。

たとえば、Liferayカレンダーアプリからユーザーの予定を表示するモバイルアプリ（クライアント）を考えます。このクライアントアプリは、ユーザープロファイルを更新するAPIにアクセスする必要はなく、ユーザーがサーバー上でそのような権限を持っている場合も同様です。クライアントアプリは、予定を作成、更新、削除するCalendar APIメソッドにアクセスする必要さえありません。予定を検索および取得するためのリモートサービスメソッドへのアクセスのみが必要です。サーバー上のサービスアクセスポリシーは、クライアントのアクセスをこれらのサービスメソッドのみに制限できます。
クライアントは他の操作を実行しないため、モバイルデバイスを紛失または盗難した場合、またはクライアントアプリが攻撃者によって侵害された場合、それらにアクセスできることはセキュリティリスクです。

## サービスアクセスポリシーの仕組み

Webサービスに対するリモートクライアントの要求には、ユーザーの資格情報または認証トークンが含まれています。認証モジュールは、資格情報/トークンに基づいてクライアントを認識し、要求に適切なサービスアクセスポリシーを付与します。次に、サービスアクセスポリシー承認レイヤーは、付与されたすべてのポリシーを処理し、ポリシーで許可されたリモートサービスに要求がアクセスできるようにします。

![承認モジュールは、資格情報またはトークンを適切なサービスアクセスポリシーにマップします。](../../images/service-access-policies-arch.png)

サービスアクセスポリシーは、管理者がコントロールパネルで作成します。自分でポリシーの作成を開始する場合は、UIでポリシーを作成する方法を説明している[サービスアクセスポリシーに関する記事](/docs/7-1/deploy/-/knowledge_base/d/service-access-policies)を参照してください。

ただし、サーバー側のLiferayアプリがサービスアクセスポリシーAPIを使用する必要がある場合があります。

- [カスタムリモートAPI認証](/docs/7-1/tutorials/-/knowledge_base/t/auto-login)（トークン）を使用し、トークンを使用するクライアントが特定のサービスを使用できる必要があります。

- 認証は不要で、ゲストユーザーがサービスを利用できる必要があります。

- 付与された特権に基づいてリモートサービスへのアクセスを促進する必要がある[リモートサービス承認レイヤー](/docs/7-1/tutorials/-/knowledge_base/t/password-based-authentication-pipelines)が含まれています。

## APIの概要

独自のポリシーを展開したくない場合、Liferayではインターフェイスと`ThreadLocal`を提供しています。低レベルにしたい場合は、Liferay自体が[Liferay Sync](/docs/7-1/user/-/knowledge_base/u/administering-liferay-sync)の実装に使用しているAPIが提供されます。

1. インターフェイスおよび`ThreadLocal`は、[パッケージ`com.liferay.portal.kernel.security.service.access.policy`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/package-summary.html)で利用可能です。
このパッケージは、ポリシーへの基本的なアクセスのためのクラスを提供します。たとえば、[シングルトン
`ServiceAccessPolicyManagerUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyManagerUtil.html)を使用して、システムで構成されたサービスアクセスポリシーを取得できます。[`ServiceAccessPolicyThreadLocal`クラス](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/security/service/access/policy/ServiceAccessPolicyThreadLocal.html)を使用して、現在の要求スレッドに付与されたサービスアクセスポリシーを設定および取得することもできます。

   このレベルでは、設定されたポリシーのリストを取得して、アプリ/クライアントがサービスにアクセスするためのポリシーを選択できるようにすることができます。また、OAuthのようなアプリは、OAuthワークフローの承認ステップ中に利用可能なポリシーのリストを提供し、ユーザーがリモートアプリケーションに割り当てるポリシーを選択できるようにします。現在の要求スレッドにポリシーを付与することもできます。リモートクライアントがAPIにアクセスするときは、この呼び出しにどのポリシーが割り当てられているかをLiferayインスタンスに通知する何かが必要です。この何かとはほとんどの場合、[`AuthVerifier`の実装](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/security/auth/verifier/AuthVerifier.html)です。
たとえば、OAuthアプリの場合、`AuthVerifier`の実装により、承認ステップでユーザーが選択したポリシーが割り当てられます。

2. APIは、次のOSGiモジュールとして製品に付属しています。

- `com.liferay.portal.security.service.access.policy.api.jar`
- `com.liferay.portal.security.service.access.policy.service.jar`
- `com.liferay.portal.security.service.access.policy.web.jar`

   これらのOSGiモジュールはデフォルトでアクティブになっており、それらを使用して、サービスアクセスポリシーをプログラムで管理できます。それらのソースコードは[こちらからGitHub](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-security)で確認できます。
各モジュールは、他のOSGiモジュールが使用できるパッケージとサービスのリストを公開しています。

両方のツールを使用して、アプリで使用するトークン検証モジュール（リモートクライアントの承認に使用するカスタムセキュリティトークン検証を実装するモジュール）を開発できます。たとえば、このモジュールには、@product@のリモートAPI用のJSON Webトークン実装を含めることができます。カスタムトークン検証モジュールは、リモートAPI /Webサービスの呼び出し中にサービスアクセスポリシーAPIを使用して、要求中に関連するポリシーを付与する必要があります。モジュールは、

- `com.liferay.portal.security.service.access.policy.api.jar`および`com.liferay.portal.security.service.access.policy.service.jar`を使用して、ポリシーをプログラムで作成できます。

- メソッド`ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName()`を使用して、Webサービス要求中に関連するポリシーを付与する必要があります。

- `ServiceAccessPolicyManagerUtil`を使用して、リモートアプリケーションの承認時にサポートされているポリシーのリストを表示し、トークンを既存のポリシーに関連付けることができます。

## サービスアクセスポリシーの例

[Liferay Sync](https://www.liferay.com/supporting-products/liferay-sync)の`sync-security`モジュールは、サービスアクセスポリシーモジュールです。このモジュールは、`com.liferay.portal.security.service.access.policy.service`を使用して`SYNC_DEFAULT`および`SYNC_TOKEN`ポリシーをプログラムで作成します。SyncのリモートAPIへのサービス呼び出しの場合、これらのポリシーはSyncの`com.liferay.sync.service.SyncDLObjectService#getSyncContext`および`com.liferay.sync.service.*`へのアクセスをそれぞれ付与します。これらのポリシーを定義および作成する`sync-security`モジュールのコードは次のとおりです。

    @Component(immediate = true)
    public class SyncSAPEntryActivator {
    
    // Define the policies
    public static final Object[][] SAP_ENTRY_OBJECT_ARRAYS = new Object[][] {
    {
    "SYNC_DEFAULT",
    "com.liferay.sync.service.SyncDLObjectService#getSyncContext", true
    },
    {"SYNC_TOKEN", "com.liferay.sync.service.*", false}
    };
    
    ...
    
    // Create the policies
    protected void addSAPEntry(long companyId) throws PortalException {
    for (Object[] sapEntryObjectArray : SAP_ENTRY_OBJECT_ARRAYS) {
    String name = String.valueOf(sapEntryObjectArray[0]);
    String allowedServiceSignatures = String.valueOf(
    sapEntryObjectArray[1]);
    boolean defaultSAPEntry = GetterUtil.getBoolean(
    sapEntryObjectArray[2]);
    
    SAPEntry sapEntry = _sapEntryLocalService.fetchSAPEntry(
    companyId, name);
    
    if (sapEntry != null) {
    continue;
    }
    
    Map<Locale, String> map = new HashMap<>();
    
    map.put(LocaleUtil.getDefault(), name);
    
    _sapEntryLocalService.addSAPEntry(
    _userLocalService.getDefaultUserId(companyId),
    allowedServiceSignatures, defaultSAPEntry, true, name, map,
    new ServiceContext());
    }
    }
    
    ...
    
    }

このクラスは、モジュールの起動時にポリシーを作成します。このモジュールはデフォルトで含まれており、有効になっていることに注意してください。これらのポリシーおよびその他のポリシーには、*[コントロールパネル]* &rarr; *[設定]* &rarr; *[Service Access Polic]*からアクセスできます。

`sync-security`モジュールは、必要なときに適切なポリシーを付与する必要があります。 Liferay SyncのリモートAPIへの認証済みの呼び出しはすべて`com.liferay.sync.service.*`へのアクセスを必要とするため、モジュールはそのような呼び出しに`SYNC_TOKEN`ポリシーを付与する必要があります。次のコードスニペットに示すように、モジュールは`ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName`メソッドを使用してこれを行います。

    if ((permissionChecker != null) && permissionChecker.isSignedIn()) {
    ServiceAccessPolicyThreadLocal.addActiveServiceAccessPolicyName(
    String.valueOf(
    SyncSAPEntryActivator.SAP_ENTRY_OBJECT_ARRAYS[1][0]));
    }

これで、認証方法に関係なく、SyncのリモートAPIへのすべての認証済みの呼び出しは、`com.liferay.sync.service.*`にアクセスできます。完全なコード例を参照するには、[ここをクリック](https://github.com/liferay/liferay-portal/blob/7.1.x/modules/apps/sync/sync-security/src/main/java/com/liferay/sync/security/servlet/filter/SyncAuthFilter.java)してください。

 これで、アプリをサービスアクセスポリシーと統合する方法がわかりました。
