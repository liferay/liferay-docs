---
header-id: checking-permissions
---

# 権限の確認

[TOC levels=1-4]

権限を定義し、データベースおよびOSGiコンテナーにリソースを登録し、ユーザーが権限をリソースに関連付けることができるようになったので、アプリケーションの適切な場所に権限チェックを追加する準備ができました。3つのステップがあります。

1. サービス呼び出しに権限チェックを追加します。

2. Webモジュールに権限ヘルパークラスを作成します。

3. Webアプリケーションに権限チェックを追加します。

以下で詳しく説明していきます。

## サービス呼び出しに権限チェックを追加する

ベストプラクティスは、`-LocalServiceImpl`クラス内で同じメソッドを呼び出すメソッドを`-ServiceImpl`クラス内に作成し、それらの呼び出しを権限チェックにラップすることです。サービスをWebサービスとして公開する場合、それらのサービスを呼び出すクライアントには、サービスを呼び出す権限が必要です。このようにして、ビジネスロジック（`-LocalServiceImpl`クラスに含まれている）を権限ロジック（`-ServiceImpl`クラスに含まれている）から分離します。

1. エンティティの`-ServiceImpl`クラスを開きます。

2. `ModelResourcePermissionFactory`および`PortletResourcePermissionFactory`を使用して、`default.xml`で定義したとおりに権限を確認できる権限チェッカーを参照します。以下に、ブログポートレットが行う処理を示します。

       private static volatile ModelResourcePermission<BlogsEntry>
       _blogsEntryFolderModelResourcePermission =
       ModelResourcePermissionFactory.getInstance(
       BlogsEntryServiceImpl.class,
       "_blogsEntryFolderModelResourcePermission", BlogsEntry.class);
       private static volatile PortletResourcePermission
       _portletResourcePermission =
       PortletResourcePermissionFactory.getInstance(
       BlogsEntryServiceImpl.class, "_portletResourcePermission",
       BlogsConstants.RESOURCE_NAME);
   
   クラス、変数を宣言し、ポートレットリソースの場合は、`default.xml`からリソース名を宣言します。このブログアプリケーションでは、`BlogsConstants.RESOURCE_NAME`は値が`com.liferay.blogs`の`String` です。

   Service BuilderはSpringに接続されており`@Reference`を使用できないため、サービス内で`ModelResourcePermissionFactory.getInstance()`を使用する必要があります。
サービスの登録時にリフレクションが設定されるため、必ず正しいサービスクラスと設定中のフィールドの名前（この場合は`"_blogsEntryFolderModelResourcePermission"`）を使用してください。フィールドを間違えると、間違って設定されます。このフィールドは`static`および`volatile`である必要があり、`-ServiceImpl`クラスの外部では使用しないでください。

3. 適切な場所で権限を確認します。たとえば、ブログエントリを追加するには`ADD_ENTRY`権限が必要なため、ブログアプリケーションは次のように処理を行います。

       @Override
       public BlogsEntry addEntry(
       String title, String subtitle, String description, String content,
       int displayDateMonth, int displayDateDay, int displayDateYear,
       int displayDateHour, int displayDateMinute, boolean allowPingbacks,
       boolean allowTrackbacks, String[] trackbacks,
       String coverImageCaption, ImageSelector coverImageImageSelector,
       ImageSelector smallImageImageSelector,
       ServiceContext serviceContext)
       throws PortalException {
       
       _portletResourcePermission.check(
       getPermissionChecker(), serviceContext.getScopeGroupId(),
       ActionKeys.ADD_ENTRY);
       
       return blogsEntryLocalService.addEntry(
       getUserId(), title, subtitle, description, content,
       displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
       displayDateMinute, allowPingbacks, allowTrackbacks, trackbacks,
       coverImageCaption, coverImageImageSelector, smallImageImageSelector,
       serviceContext);
       }
   
   チェックが失敗した場合、例外がスローされ、エントリを追加するローカルサービスの呼び出しが回避されます。Liferayが使用する規則では、`default.xml`のアクションキーが`ActionKeys`クラスに定数として配置されます。`ActionKeys`にアプリケーションに適したアクションキーがない場合は、Liferayのクラスを拡張し、独自のキーを追加します。

サービスレベルでアプリケーションの機能を保護するために、必要に応じて権限チェックを追加します。次に、Webモジュールの権限ヘルパークラスを作成する方法を学びます。

## Webモジュールに権限ヘルパークラスを作成する

ヘルパークラスを使用すると、ポートレットアプリケーションの権限を簡単に確認できます。ポートレットの権限とモデルの権限の両方に対しヘルパークラスを作成できます。ポートレット権限ヘルパーを作成する方法は次のとおりです。

1. サフィックス`web.internal.security.permission.resource`を使用してパッケージを作成します。たとえば、ブログアプリケーションにはパッケージ`com.liferay.blogs.web.internal.security.permission.resource`が含まれています。

2. 権限を確認するための静的メソッドを少なくとも1つ含むコンポーネントクラスを作成します。 例として、以下に`BlogsPermission`クラスを示します。

       @Component(immediate = true)
       public class BlogsPermission {
       
       public static boolean contains(
       PermissionChecker permissionChecker, long groupId, String actionId) {
       
       return _portletResourcePermission.contains(
       permissionChecker, groupId, actionId);
       }
       
       @Reference(
       target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")",
       unbind = "-"
       )
       protected void setPortletResourcePermission(
       PortletResourcePermission portletResourcePermission) {
       
       _portletResourcePermission = portletResourcePermission;
       }
       
       private static PortletResourcePermission _portletResourcePermission;
       
       }
   
   OSGiコンテナーに、以前に作成した権限レジストラを介してオブジェクトを提供するよう指示する`@Reference`アノテーションに注目してください。`_portletResourcePermission`フィールドは静的ですが、セッターメソッドはインスタンスメソッドです。これは、LiferayがJSPにサービス参照を持つことを回避する方法です。

モデル権限ヘルパーを作成する手順も同様です。

1. 同じパッケージで、権限を確認するための静的メソッドを少なくとも1つ含むコンポーネントクラスを作成します。 例として、`BlogsEntryPermission`クラスを以下に示します。

       @Component(immediate = true)
       public class BlogsEntryPermission {
       
       public static boolean contains(
       PermissionChecker permissionChecker, BlogsEntry entry,
       String actionId)
       throws PortalException {
       
       return _blogsEntryFolderModelResourcePermission.contains(
       permissionChecker, entry, actionId);
       }
       
       public static boolean contains(
       PermissionChecker permissionChecker, long entryId, String actionId)
       throws PortalException {
       
       return _blogsEntryFolderModelResourcePermission.contains(
       permissionChecker, entryId, actionId);
       }
       
       @Reference(
       target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)",
       unbind = "-"
       )
       protected void setEntryModelPermission(
       ModelResourcePermission<BlogsEntry> modelResourcePermission) {
       
       _blogsEntryFolderModelResourcePermission = modelResourcePermission;
       }
       
       private static ModelResourcePermission<BlogsEntry>
       _blogsEntryFolderModelResourcePermission;
       
       }
   
   見てとおり、このクラスはポートレット権限クラスとほぼ同じです。実際の違いは`@Reference`アノテーションにあり、`default.xml`のリソース名ではなく、モデルの完全修飾クラス名を指定しています。

2. 両方のファイルを保存します。

これで、これらのヘルパークラスを使用して、Webモジュールの権限を確認する準備が整いました。

## Webアプリケーションに権限チェックを追加する

権限ヘルパークラスを使用して、UI要素を表示する前に権限を確認できます。要素が表示されない場合、ユーザーは要素にアクセスできません（ただし、前述したとおりサービスも保護する必要があります）。その方法は次のとおりです。

1. 保護する関数がある場合は、権限ヘルパークラスを使用する`if`ステートメントにラップします。たとえば、ブログアプリケーションには、`ADD_ENTRY`や`SUBSCRIBE`など、権限によって保護された多くの関数があります。ブログエントリを追加できるのはブログの所有者のみであることは明らかです。
したがって、このボタンは、ユーザーがエントリを追加する権限を持っている場合にのみ表示されます。

       <c:if test="<%= BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) %>">
       <div class="button-holder">
       <portlet:renderURL var="editEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
       <portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
       <portlet:param name="redirect" value="<%= currentURL %>" />
       </portlet:renderURL>
       
       <aui:button href="<%= editEntryURL %>" icon="icon-plus" value="add-blog-entry" />
       </div>
       </c:if>
   
2. 任意の関数に対してこれを行います。 たとえば、[ステップ3](/docs/7-1/tutorials/-/knowledge_base/t/associating-permissions-with-resources)で追加した権限関数は、権限によって確実に保護する必要があります。

       <c:if test="<%= BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.PERMISSIONS) %>">
       <liferay-security:permissionsURL
       modelResource="<%= BlogsEntry.class.getName() %>"
       modelResourceDescription="<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>"
       resourceGroupId="<%= String.valueOf(entry.getGroupId()) %>"
       resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
       var="permissionsEntryURL"
       windowState="<%= LiferayWindowState.POP_UP.toString() %>"
       />
       
       <liferay-ui:icon
       label="<%= true %>"
       message="permissions"
       method="get"
       url="<%= permissionsEntryURL %>"
       useDialog="<%= true %>"
       />
       </c:if>
   
   これにより、権限を設定する権限のないユーザーには、権限ボタンが表示されなくなります。

 これで、*DRAC*のすべての手順を学習しました。

1. 権限の定義

2. 権限の登録

3. 権限とリソースの関連付け

4. 権限の確認

これらのステップに従うことで、アプリケーションはLiferayの十分にテスト済みの統合型アクセス許可システムを利用できます。

