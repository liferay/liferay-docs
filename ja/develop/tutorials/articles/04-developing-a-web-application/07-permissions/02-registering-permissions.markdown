---
header-id: registering-your-defined-permissions
---

# 定義済みの権限を登録する

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">許可の実装</p><p>手順2/4</p>
</div>

最後のステップでは、 *リソース*という概念を導入しました。 リソースは、エンティティへのアクセス方法を定義するエンティティとともに保存されるデータです。 たとえば、 `default.xml` ファイルの構成がデータベース内のアプリケーションのエンティティに適用されると、リソースが作成されます。 これらのリソースは、@product@の権限システムと組み合わせて使用され、エンティティに対して誰が何を実行できるかを決定します。

これらのリソースを使用するには、@product@がそれらについて知る必要があります。 これを行うには、データベース内とOSGiコンテナー内の実行許可システムの両方で、システムにリソースを *登録* します。

## データベースへの許可の登録

@product@は、Service Builderと統合されたリソースを管理するための完全なAPIを提供します。 このAPIは、実装クラスに自動的に注入されます。 リソースを管理するには、サービスのaddおよびdeleteメソッドでAPIを呼び出すだけです。 アプリケーションでこれを行うには、次の手順に従います。

1.  `guestbook-service` モジュールで、 `com.liferay.docs.guestbook.service.impl` パッケージから `GuestbookLocalServiceImpl.java` を開きます。

2.  `addGuestbook` メソッドの `return` ステートメントの直前に、次のコードを追加します。
   
        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Guestbook.class.getName(), guestbookId, false, true, true);

    `resourceLocalService` オブジェクトが既に存在し、使用する準備ができていることに注意してください。 これは、Service Builderによって自動的に挿入されるいくつかのユーティリティの1つです。 残りは今後表示されます。

    このコードは、エンティティに対応するリソースを@product@のデータベースに追加します（ `guestbookId` が呼び出しに含まれていることに注意してください）。 最後の3つのブール値は設定です。 1つ目は、ポートレットアクションのアクセス許可を追加するかどうかです。 これは、許可がポートレットリソースに対するものである場合にのみ、 `true` なければなりません。 この許可はモデルリソース（エンティティ）に対するものであるため、 `false`です。 他の2つは、グループとゲストのアクセス許可を追加するための設定です。 これらを `true`に設定すると、前の手順で権限構成ファイル（`default.xml`）で定義したデフォルトの権限を追加します。 間違いなくこれを実行したいので、これらのブール値は `true`設定されます。

3.  次に、 `updateGuestbook` メソッドに進みます。 `間に同様のコードを追加し<code> guestbookPersistence.update（guestbook）;` および `` ステートメントを返します。
   
        resourceLocalService.updateResources(serviceContext.getCompanyId(),
                        serviceContext.getScopeGroupId(), 
                        Guestbook.class.getName(), guestbookId,
                        serviceContext.getGroupPermissions(),
                        serviceContext.getGuestPermissions());

4.  `deleteGuestbook`についても同じことを行います。 `間にこのコードを追加しますguestbook = deleteGuestbook（guestbook）;` および `` ステートメントを返します。
   
        resourceLocalService.deleteResource(serviceContext.getCompanyId(),
                        Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
                        guestbookId);

5.  \ [CTRL \] + \ [SHIFT \] + Oを押してインポートを整理し、ファイルを保存します。

6.  次に、 `エントリ` エンティティのリソースを追加します。 オープン `EntryLocalServiceImpl.java` と同じパッケージから。 `addEntry`場合、returnステートメントの直前に、このエンティティのリソースを追加するコード行を追加します。
   
        resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
            Entry.class.getName(), entryId, false, true, true);

7.  `deleteEntry`場合、 `return` ステートメントの直前に次のコードを追加します。
   
        resourceLocalService.deleteResource(
                       serviceContext.getCompanyId(), Entry.class.getName(),
                       ResourceConstants.SCOPE_INDIVIDUAL, entryId);

8.  最後に、 `updateEntry` を見つけて、リソースアクションを追加します`これは、 <code>return` ステートメントの直前でもあります。
   
        resourceLocalService.updateResources(
              user.getCompanyId(), serviceContext.getScopeGroupId(), 
              Entry.class.getName(), entryId, serviceContext.getGroupPermissions(),
              serviceContext.getGuestPermissions());

データベースに権限リソースを追加するのに必要なことはそれだけです。 データベースに追加される将来のエンティティは、完全に権限が有効になっています。 ただし、ポータルのゲストブックアプリケーションに既に追加したエンティティにはリソースがないため、権限で保護することはできません。 このセクションの最後でこれを修正します。 ここで、許可システムに許可を登録する必要があります。そのため、許可を確認する方法がわかります。

## 権限サービスへのエンティティの登録

実行中のサービスは権限をチェックしますが、ゲストブックポートレット、ゲストブック、およびゲストブックエントリはシステムにとって新しいため、それらについて学習する必要があります。 これを行うには、権限レジストラークラスを作成します。 これらは、 `default.xml`行ったことに従います。1つは、ポートレットの許可用に1つ、各エンティティーに</code>つ必要です。 まず、少し再編成する必要があります。

1.  APIモジュールで、 `com.liferay.docs.guestbook.constants`という新しいパッケージに `GuestbookConstants` クラスを作成します。
   
        package com.liferay.docs.guestbook.constants;
       
        public class GuestbookConstants {
       
            public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";
       
        }

    `RESOURCE_NAME` 文字列は、 `default.xml`リソース名と正確に一致する必要があります。 理由はすぐにわかります。

2.  Webモジュールに `GuestbookPortletKeys` クラスがあります。 これらのキーはすべてのモジュールからアクセスできるようになっている必要があるため、このクラスをWebモジュールからドラッグし、APIモジュールの新しい `com.liferay.docs.guestbook.constants` パッケージにドロップします。

これで、アクセス許可レジストラクラスを作成する準備ができました。

3.  サービスバンドルで、慣例により `internal.security.permission.resource`終わるパッケージを作成します。

4.  このパッケージに `GuestbookModelResourcePermissionRegistrar` という名前のクラスを作成し、次の内容を追加します。
   
        package com.liferay.docs.guestbook.internal.security.permission.resource;
       
        import java.util.Dictionary;
       
        import org.osgi.framework.BundleContext;
        import org.osgi.framework.ServiceRegistration;
        import org.osgi.service.component.annotations.Activate;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Deactivate;
        import org.osgi.service.component.annotations.Reference;
       
        import com.liferay.docs.guestbook.constants.GuestbookConstants;
        import com.liferay.docs.guestbook.constants.GuestbookPortletKeys;
        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.GuestbookLocalService;
        import com.liferay.exportimport.kernel.staging.permission.StagingPermission;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
        import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
        import com.liferay.portal.kernel.security.permission.resource.StagedModelPermissionLogic;
        import com.liferay.portal.kernel.security.permission.resource.WorkflowedModelPermissionLogic;
        import com.liferay.portal.kernel.service.GroupLocalService;
        import com.liferay.portal.kernel.util.HashMapDictionary;
        import com.liferay.portal.kernel.workflow.permission.WorkflowPermission;
       
        @Component (immediate=true)
        public class GuestbookModelResourcePermissionRegistrar {
       
         @Activate
            public void activate(BundleContext bundleContext) {
                Dictionary<String, Object> properties = new HashMapDictionary<>();
       
                properties.put("model.class.name", Guestbook.class.getName());
       
                _serviceRegistration = bundleContext.registerService(
                    ModelResourcePermission.class,
                    ModelResourcePermissionFactory.create(
                        Guestbook.class, Guestbook::getGuestbookId,
                        _guestbookLocalService::getGuestbook, _portletResourcePermission,
                        (modelResourcePermission, consumer) -> {
                            consumer.accept(
                                new StagedModelPermissionLogic<>(
                                    _stagingPermission, GuestbookPortletKeys.GUESTBOOK,
                                    Guestbook::getGuestbookId));
                            consumer.accept(
                                new WorkflowedModelPermissionLogic<>(
                                        _workflowPermission, modelResourcePermission,
                                        _groupLocalService, Guestbook::getGuestbookId));
                        }),
                    properties);
            }
       
            @Deactivate
            public void deactivate() {
                _serviceRegistration.unregister();
            }
       
            @Reference
            private GuestbookLocalService _guestbookLocalService;
       
            @Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
            private PortletResourcePermission _portletResourcePermission;
       
            private ServiceRegistration<ModelResourcePermission> _serviceRegistration;
       
            @Reference
            private StagingPermission _stagingPermission;
       
            @Reference
            private WorkflowPermission _workflowPermission;
       
            @Reference
            private GroupLocalService _groupLocalService;
        }

このクラスは、ゲストブックエンティティのアクセス許可を確認するためのアクセス許可ロジッククラスのチェーンを登録します。 この機能はすべてのエンティティで同じであるため、ステージングとワークフロー用の標準のLiferayに加えて、あなたのエンティティを指定するだけです。 イントロスペクションは、必要な許可サービスを作成するために、ファクトリーによってエンティティーで行われます。 `default.xml`定義したリソースモデル名を指定できるように、定数クラスを実装しました。 `model.class.name` が設定されているため、このサービスを必要とするモジュールは、そのタイプごとにこのモデルリソース許可を見つけることができます。

次に、 `エントリ` エンティティのレジストラを作成します。

1.  `GuestbookEntryModelResourcePermissionRegistrar`という同じパッケージにクラスを作成します。

2.  このクラスと上記のクラスの唯一の違いは、 `ゲストブック` エンティティではなく `エントリ` エンティティで動作することです（インポートは以下のスニペットで省略されています）。
   
        @Component(immediate = true)
        public class GuestbookEntryModelResourcePermissionRegistrar {
       
         @Activate
            public void activate(BundleContext bundleContext) {
                Dictionary<String, Object> properties = new HashMapDictionary<>();
       
                properties.put("model.class.name", Entry.class.getName());
       
                _serviceRegistration = bundleContext.registerService(
                    ModelResourcePermission.class,
                    ModelResourcePermissionFactory.create(
                        Entry.class, Entry::getEntryId,
                        _entryLocalService::getEntry, _portletResourcePermission,
                        (modelResourcePermission, consumer) -> {
                            consumer.accept(
                                new StagedModelPermissionLogic<>(
                                    _stagingPermission, GuestbookPortletKeys.GUESTBOOK,
                                    Entry::getEntryId));
                            consumer.accept(
                                new WorkflowedModelPermissionLogic<>(
                                        _workflowPermission, modelResourcePermission,
                                        _groupLocalService, Entry::getEntryId));
                        }),
                    properties);
            }
       
            @Deactivate
            public void deactivate() {
                _serviceRegistration.unregister();
            }
       
            @Reference
            private EntryLocalService _entryLocalService;
       
            @Reference(target = "(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")")
            private PortletResourcePermission _portletResourcePermission;
       
            private ServiceRegistration<ModelResourcePermission> _serviceRegistration;
       
            @Reference
            private StagingPermission _stagingPermission;
       
            @Reference
            private WorkflowPermission _workflowPermission;
       
            @Reference
            private GroupLocalService _groupLocalService;
        }

最後に、ポートレットの権限のレジストラを作成します。

1.  `GuestbookPortletResourcePermissionRegistrar`という名前の同じパッケージにクラスを作成します。

2.  このクラスは、エンティティから主キーを取得する方法を指示する必要がないため、より簡単です。
   
        @Component (immediate = true)
        public class GuestbookPortletResourcePermissionRegistrar {
       
                @Activate
            public void activate(BundleContext bundleContext) {
                Dictionary<String, Object> properties = new HashMapDictionary<>();
       
                properties.put("resource.name", GuestbookConstants.RESOURCE_NAME);
       
                _serviceRegistration = bundleContext.registerService(
                    PortletResourcePermission.class,
                    PortletResourcePermissionFactory.create(
                        GuestbookConstants.RESOURCE_NAME,
                        new StagedPortletPermissionLogic(
                            _stagingPermission, GuestbookPortletKeys.Guestbook)),
                    properties);
            }
       
            @Deactivate
            public void deactivate() {
                _serviceRegistration.unregister();
            }
       
            private ServiceRegistration<PortletResourcePermission> _serviceRegistration;
       
            @Reference
            private StagingPermission _stagingPermission;
       
        }

これで、ステップ2を完了しました：DRACのR：権限の登録。 次に、ユーザーがアクセス許可をリソースに関連付けることができるようにします。
