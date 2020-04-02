---
header-id: liferays-workflow-framework
---

# Liferayのワークフローフレームワーク

[TOC levels=1-4]

アプリケーションのエンティティーがワークフローをサポートできるようにするのは非常に簡単です。ワークフロー対応のエンティティには、次の2つが必要です。

- Liferayのワークフローバックエンドおよびエンティティのサービスレイヤーと相互作用するワークフローハンドラーのクラス。

- ステータスを追跡するのに役立つデータベーステーブル内のいくつかの追加フィールドと、それらを更新するサービスレイヤー内のメソッド。

したがって、ワークフローを有効にするには2つのタスクがあります。

1. アプリケーションに`WorkflowHandler`クラスを作成します。

2. サービスレイヤーを更新して、ワークフローと統合します。

それでは始めましょう。

## ワークフローハンドラーの作成

ワークフローハンドラーは、サービス実装を含むモジュールに配置する必要があります。バックエンドコードをビューレイヤーやコントローラー（MVCパターン内）から分離しておくと便利です。

1. コンポーネントクラスを作成します。`WorkflowHandler<T>`サービスのデフォルトの実装を提供する抽象クラスである`BaseWorkflowHandler<T>`を拡張する必要があります。モデルのインターフェイスをクラスの型パラメーターとして渡します。

       public class FooEntityWorkflowHandler extends BaseWorkflowHandler<FooEntity>
   
2. OSGiランタイムで使用されるサービスを公開しているため、ワークフローハンドラーのクラスを登録する必要があります。Declarative Servicesを使用している場合は、`@Component`アノテーションを使用してコンポーネントクラスにします。

       @Component(
       property = {"model.class.name=com.my.app.package.model.FooEntity"},
       service = WorkflowHandler.class
       )
   
   型パラメーターとして渡したクラスの完全修飾クラス名に`model.class.name`を設定するには、1つのプロパティが必要です。また、実装するサービスのタイプを宣言する必要があります（`WorkflowHandler.class`）。

3. ワークフローハンドラーでオーバーライドするメソッドは3つあり、最初の2つは定型的なメソッドです。

       @Override
       public String getClassName() {
       return FooEntity.class.getName();
       }
   
   `getClassName`は、モデルクラスの完全修飾クラス名（例：`com.my.app.package.model.FooEntity`）を返します。

       @Override
       public String getType(Locale locale) {
       return ResourceActionsUtil.getModelResource(locale, getClassName());
       }
   
   `getType`は、モデルリソース名（例：`model.resource.com.my.app.package.model.FooEntity`）を返します。

       @Override
       public FooEntity updateStatus(int status, Map<String, Serializable> workflowContext) {
   
   手間のかかる作業のほとんどは`updateStatus`メソッドにあります。このメソッドは同じ名前のローカルサービスメソッドへの呼び出し（例：`FooEntityLocalService.updateStatus`）を返すので、ワークフローバックエンドから返されたステータスは、データベースのエンティティテーブルに保持できます。

4. `updateStatus`メソッドには、ユーザーID、クラスの主キー（例：`fooEntityId`）、ワークフローの状態、サービスコンテキスト、およびワークフローコンテキストが必要です。ステータスとワークフローコンテキストは、ワークフローバックエンドから取得できます。他のパラメーターは、ワークフローコンテキストから取得できます。

       @Override
       public FooEntity updateStatus(
       int status, Map<String, Serializable> workflowContext)
       throws PortalException {
       
       long userId = GetterUtil.getLong(
       (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
       long classPK = GetterUtil.getLong(
       (String)workflowContext.get(
       WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));
       
       ServiceContext serviceContext = (ServiceContext)workflowContext.get(
       "serviceContext");
       
       return _fooEntityLocalService.updateStatus(
       userId, classPK, status, serviceContext, workflowContext);
       }
   
Liferayのワークフローフレームワークでエンティティを処理できるようになりました。次に、ワークフローの状態が反映されるようにサービスメソッドを更新して、データベース内のエンティティのステータスを更新する新しいメソッドを追加します。

## サービスレイヤーの更新

ほとんどのLiferayアプリケーションでは、 [Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)を使用してデータベースフィールドを作成します。最初に、サービスレイヤーを更新する必要があります。

- エンティティがデータベースに追加されると、サービスレイヤーは新しいフィールドに値を入力する必要があります。

- サービスレイヤーは、Liferayのワークフローを介してエンティティを送信する必要があり、エンティティがワークフローによって返されたときにそのワークフローの状態を処理する必要があります。

- サービスレイヤーには、ワークフローの状態（通常は*承認済み*）によってエンティティを返すゲッターが必要です。

これが完了すると、表示されているエンティティのワークフローの状態がビューレイヤーに反映されます。

1. エンティティのデータベーステーブルに`status`、`statusByUserId`、`statusByUserName`、および`statusDate`フィールドがあることを確認します。Service Builderを使用している場合は、それを`service.xml`に追加します（まだ追加していない場合）。

       <column name="status" type="int" />
       <column name="statusByUserId" type="long" />
       <column name="statusByUserName" type="String" />
       <column name="statusDate" type="Date" />
   
2. 永続化コードで他のデータベースフィールドを設定する場合は、ワークフローの状態をドラフトとして設定し、他のフィールドを設定します。

       fooEntity.setStatus(WorkflowConstants.STATUS_DRAFT);
       fooEntity.setStatusByUserId(userId);
       fooEntity.setStatusByUserName(user.getFullName());
       fooEntity.setStatusDate(serviceContext.getModifiedDate(null));
   
Service Builder駆動のLiferayアプリケーションでは、これはローカルサービスの実装クラス（`-LocalServiceImpl`）にあります。

エンティティがデータベースに追加されると、アプリケーションはワークフローが有効かどうかを検出します。有効でない場合は、エンティティが承認済みとして自動的にマークされ、UIに表示されます。それ以外の場合は、ドラフトステータスのままになり、ワークフローバックエンドが処理します。便利なことに、このプロセス全体は、`WorkflowHandlerRegistryUtil.startWorkflowInstance`を1回呼び出すだけで簡単に実行できます。 

1. データベースに新しいエンティティを追加するメソッドの最後で、ワークフローサービスを呼び出します。

       WorkflowHandlerRegistryUtil.startWorkflowInstance(fooEntity.getCompanyId(),
       fooEntity.getGroupId(), fooEntity.getUserId(), FooEntity.class.getName(),
       fooEntity.getPrimaryKey(), fooEntity, serviceContext);
   
2. ワークフローの状態のデータベースフィールドを設定し、ワークフローインスタンスを開始したら、ワークフローハンドラーで呼び出す`updateStatus`メソッドを実装します。ワークフローハンドラーは、ワークフローバックエンドからエンティティのステータスを取得し、それをサービスレイヤーに渡します。サービスレイヤーは、更新されたエンティティをデータベースに保持します。

       fooEntity.setStatus(status);
       fooEntity.setStatusByUserId(user.getUserId());
       fooEntity.setStatusByUserName(user.getFullName());
       fooEntity.setStatusDate(serviceContext.getModifiedDate(now));
       
       fooEntityPersistence.update(fooEntity);
   
3. エンティティのワークフローフィールドを設定したら、状況の詳細と、このメソッドに追加のロジックを追加する必要があるかどうかを検討します。たとえば、エンティティがすでにLiferayアセットである場合、ワークフローの状態に応じてアセットの可視性を変更する必要があります。そのため、Asset Publisherはワークフロープロセスでまだ承認されていないエンティティを表示しません。

       if (status == WorkflowConstants.STATUS_APPROVED) {
       
       assetEntryLocalService.updateEntry(
       FooEntity.class.getName(), fooEntityId, fooEntity.getDisplayDate(),
       null, true, true);
       }
       
       else {
       
       assetEntryLocalService.updateVisible(
       fooEntity.class.getName(), entryId, false);
       }
   
   承認された場合、ワークフローは公開日、 `listable`ブール値、および`visible`ブール値でアセットを更新し、アセットの現在の状態が反映されます。ワークフローの状態が承認済み以外の場合、その可視性は`false`に設定されます。


4. サービスレイヤーを離れる前に、`deleteEntity`メソッドで`deleteWorkflowInstanceLinks`への呼び出しを追加します。次のようになります。

       WorkflowHandlerRegistryUtil.startWorkflowInstance(fooEntity.getCompanyId(),
       fooEntity.getGroupId(), fooEntity.getUserId(), FooEntity.class.getName(),
       fooEntity.getPrimaryKey(), fooEntity, serviceContext);
    workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
       fooEntity.getCompanyId(), fooEntity.getGroupId(),
       FooEntity.class.getName(), fooEntity.getFooEntityId());
   
   `startWorkflowInstance`呼び出しを介してワークフローフレームワークにエンティティを送信すると、`workflowinstancelink`データベーステーブルにエントリが作成されます。この`delete`呼び出しは、`workflowinstancelinks`テーブルに孤立したエントリがないことを確認します。

5. `*LocalServiceBaseImpl`に`WorkflowInstanceLocalService`を挿入して、`LocalServiceImpl`でメソッドを呼び出すには、以下を`service.xml`のエンティティ宣言に追加します。 

       <reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />
   
完全に実装された`updateStatus`メソッドの例については、`portal-impl`の`com.liferay.portlet.blogs.service.impl.BlogsEntryLocalServiceImpl`クラスを参照してください。 

作業を保存して、Service Builderを実行します。サービスレイヤーでワークフローの状態を反映したら、あとはユーザーインタフェイスを更新するだけです。

## ワークフローの状態とビューレイヤー

データベースエンティティを使用するアプリケーションがある場合は、それらが表示されていると思われます。ワークフローを有効にしたら、あとは承認されたエンティティをエンドユーザーに表示するだけです。

手順は次のとおりです。

- データベーステーブルの`status`フィールドを反映する*ファインダー*をエンティティに作成します。

- サービスレイヤーの*getter*メソッドでファインダーを公開します。

- ビューレイヤーを更新して、エンティティの表示に新しいゲッターを使用します（検索コンテナーなど）。

1. Service Builderを使用している場合は、アプリケーションの`service.xml`でファインダーを定義し、 Service Builderにそれを生成させます。

       <finder name="G_S" return-type="Collection">
       <finder-column name="groupId"></finder-column>
       <finder-column name="status"></finder-column>
       </finder>
   
2. サービスレイヤーに新しいファインダーを使用するゲッターがあることを確認します。

       public List<FooEntity> getFooEntities(long groupId, int status)
       throws SystemException {
       return fooEntityPersistence.findByG_S(groupId,
       WorkflowConstants.STATUS_APPROVED);
       }
   
3. 最後に、適切なゲッターを使用するようにJSPを更新します。

       <liferay-ui:search-container-results
       results="<%=FooEntityLocalServiceUtil.getFooEntities(scopeGroupId,
       fooEntityId(), Workflowconstants.STATUS_APPROVED, searchContainer.getStart(),
       searchContainer.getEnd())%>"
       ...
   
*管理*タイプのアプリケーション（つまり、サイトメニューの*[Content]* セクションに表示されるアプリケーション）では、すべてのエンティティを現在のワークフローの状態で表示することができます。 （たとえば、ワークフローの状態を検索コンテナーの列として含める）。これを行うには、`<aui:worklfow-status>`タグを使用します。

    <aui:workflow-status markupView="lexicon" showIcon="<%= false %>" showLabel="<%= false %>" status="<%= fooEntity.getStatus() %>" />

<!--UPDATE WHEN UI IS STABILIZED [Figure 1: You can display the workflow status of your entities. This is useful in administrative applications.](../../images/workflow-status-list-view.png) -->

 新しいクラスを1つ作成し、`add`メソッドを更新し、サービスレイヤーに新しいメソッドを1つ追加して、ビューを更新しました。ワークフローは完全に実装され、Liferayアプリケーションですぐに使用できます。
