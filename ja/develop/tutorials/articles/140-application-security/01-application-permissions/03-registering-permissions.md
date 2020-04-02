---
header-id: registering-permissions
---

# 権限の登録

[TOC levels=1-4]

権限を定義することが最初のステップでした。定義した権限を登録する準備ができています。データベースとOSGiコンテナーで実行されている権限サービスの両方にエンティティを登録する必要があります。

## データベースへの権限リソースの登録

このタスクを実行するには、サービスレイヤーでLiferayのリソースサービスを呼び出すだけです。Service Builderを使用している場合、これは非常に簡単です。

1. `-LocalServiceImpl`クラスを開きます。

2. エンティティを追加するメソッドで、エンティティを含むリソースを追加する呼び出しを追加します。たとえば、Liferayのブログアプリケーションは次のようにリソースを追加します。

       resourceLocalService.addResources(
       entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
       BlogsEntry.class.getName(), entry.getEntryId(), false,
       addGroupPermissions, addGuestPermissions);
   
   このメソッドでは、会社ID、グループID、ユーザーID、エンティティのクラス名、エンティティのプライマリキー、およびブール値の設定の一部を渡す必要があります。順番に、これらの設定は以下について定義します。

   - 権限がポートレットリソースかどうか
   - `default.xml`で定義されたデフォルトのグループ権限を追加する必要があるかどうか
   - `default.xml`で定義されたデフォルトのゲスト権限を追加する必要があるかどうか

リソースローカルサービスは、Service Builderで生成されたサービスに自動的に挿入されることに注意してください。

Service Builderを使用していないが、アプリケーションにOSGiモジュールを使用している場合は、`@Reference`アノテーションを使用してリソースサービスを挿入できます。WARスタイルのプラグインを作成している場合は、サービスにアクセスするための[サービストラッカー](/docs/7-1/tutorials/-/knowledge_base/t/service-trackers)が必要です。モデルクラスはLiferayの`ClassedModel`インターフェイスも実装する必要があることに注意してください。

同様に、エンティティを削除するときは、関連するリソースも削除する必要があります。以下に、ブログアプリケーションが`deleteEntry()`メソッドで行う処理を示します。

    resourceLocalService.deleteResource(
    entry.getCompanyId(), BlogsEntry.class.getName(),
    ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

リソースの追加と同様に、メソッドはエンティティの会社ID、クラス、およびプライマリキーを把握する必要があります。ほとんどの場合、その範囲はあなたが選択した個々のエンティティです。定数として使用できる他の範囲は、会社、グループ、またはグループテンプレート（サイトテンプレート）用です。これらはオブジェクトに対して内部的に使用されるため、それらを作成および削除する機能をカスタマイズする場合にのみ使用します。

これで、エンティティを権限サービスに登録する準備が整いました。

## 権限サービスへのエンティティの登録

実行している権限サービスは、エンティティとそれらの権限を確認する方法について把握している必要があります。これには、権限レジストラクラスを作成する必要があります。

1. サービスバンドルで、慣例上`internal.security.permission.resource`で終わるパッケージを作成しますたとえば、ブログアプリケーションのパッケージは`com.liferay.blogs.internal.security.permission.resource`という名前になります。

2. このパッケージに`[Entity
Name]ModelResourcePermissionRegistrar`というクラスを作成しますたとえば、ブログアプリケーションのクラスの名前は`BlogsEntryModelResourcePermissionRegistrar`です。

3. このクラスは、エンティティに必要な権限ロジックを登録するために`activate`メソッドをオーバーライドする必要があるコンポーネントクラスです。たとえば、ブログアプリケーションは次のようにして権限を登録します。

       @Component(immediate = true)
       public class BlogsEntryModelResourcePermissionRegistrar {
       
       @Activate
       public void activate(BundleContext bundleContext) {
       Dictionary<String, Object> properties = new HashMapDictionary<>();
       
       properties.put("model.class.name", BlogsEntry.class.getName());
       
       _serviceRegistration = bundleContext.registerService(
       ModelResourcePermission.class,
       ModelResourcePermissionFactory.create(
       BlogsEntry.class, BlogsEntry::getEntryId,
       _blogsEntryLocalService::getEntry, _portletResourcePermission,
       (modelResourcePermission, consumer) -> {
       consumer.accept(
       new StagedModelPermissionLogic<>(
       _stagingPermission, BlogsPortletKeys.BLOGS,
       BlogsEntry::getEntryId));
       consumer.accept(
       new WorkflowedModelPermissionLogic<>(
       _workflowPermission, modelResourcePermission,
       BlogsEntry::getEntryId));
       }),
       properties);
       }
       
       @Deactivate
       public void deactivate() {
       _serviceRegistration.unregister();
       }
       
       @Reference
       private BlogsEntryLocalService _blogsEntryLocalService;
       
       @Reference(target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")")
       private PortletResourcePermission _portletResourcePermission;
       
       private ServiceRegistration<ModelResourcePermission> _serviceRegistration;
       
       @Reference
       private StagingPermission _stagingPermission;
       
       @Reference
       private WorkflowPermission _workflowPermission;
       
       }
   
クラスの仕事は`ModelResourcePermission`を設定、登録、および登録解除することであるため、これらのタイプのクラスをレジストラと呼びます

1. `model.class.name`はプロパティで設定されるため、他のモジュールのサービストラッカーは、必要に応じてそのタイプ別にこのモデルリソースの権限を見つけることができます。
Liferayには、モデルリソースの権限をチェックするいくつかのサービストラッカーがあります。
`service.ranking`プロパティは、ゼロよりも大きい値に設定して、他のモジュールのモデルリソース権限を上書きすることもできます。

2. このレジストラは、ステージングとワークフローに2つのポータルカーネル権限ロジッククラスを使用します。`ModelResourcePermissionLogic`は`@FunctionalInterface`であるため、カスタムロジッククラスは再利用またはインラインで構成できます。権限ロジッククラスは、`Consumer`で受け入れられた順に実行されます。

3. `ModelResourcePermissionLogic`クラスは、ユーザーがアクションに対する権限を持っている場合は`true`を返し、アクションに対する権限を拒否されている場合は`false`を返し、次の権限ロジックに責任を委任する場合は`null`を返します。すべての権限ロジックがnullを返す場合、`PermissionChecker.hasPermission`メソッドが呼び出され、アクションがそのユーザーに許可されているかどうかが判断されます。

このクラスは、`@Reference`とターゲットフィルターを使用して、適切な`PortletResourcePermission`を挿入します。`BlogsConstants.RESOURCE_NAME`は、以前に作成した`default.xml`で定義されている`com.liferay.blogs`に対し評価されます。
この`ModelResourcePermission`を参照していた場合は、`activate`メソッドで設定された`model.class.name`プロパティに一致するターゲットフィルターを使用します。

エンティティ固有の権限オブジェクトを作成できるように、エンティティのクラス、プライマリキー、およびエンティティ自体をファクトリに指定してください。

 権限を登録して*DRAC*のステップ2を完了しました。
これで、権限をリソースに関連付けるためのインターフェイスをユーザーに提供する準備が整いました。

