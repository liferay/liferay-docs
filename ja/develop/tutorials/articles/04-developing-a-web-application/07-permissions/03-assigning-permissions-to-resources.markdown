---
header-id: assigning-permissions-to-resources
---

# リソースへのアクセス許可の割り当て

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ファストトラック記事は、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">許可の実装</p><p>手順3/4</p>
</div>

これで、権限を定義し、コンテナとデータベースに登録して、権限を確認できるようになりました。 次のステップで権限を簡単に確認できるように、ヘルパークラスとともに権限を割り当てるユーザー用のUIを作成します。

仕組みは次のとおりです。 `ADD_ENTRY`などの許可と、 `Guestbook`などのリソースがあります。 ユーザーがエントリをゲストブックに追加するには、そのユーザーがそのゲストブックに対して `ADD_ENTRY` 権限を持っているかどうかを確認する必要があります。 ヘルパークラスを使用すると、権限を簡単に確認できます。

1.  `guestbook-web` モジュールを右クリックして、 *New* → *Package*を選択します。 Liferayの慣習に従うには、パッケージに `com.liferay.docs.guestbook.web.internal.security.permission.resource`という名前を付けます。 これは、ヘルパークラスを配置する場所です。

2.  新しいパッケージを右クリックし、 *新規* → *クラス*を選択します。 クラスに `GuestbookPermission`という名前を</code>ます。

3.  このクラスのコンテンツを次のコードに置き換えます。
   
        package com.liferay.docs.guestbook.web.internal.security.permission.resource;
       
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;
       
        import com.liferay.docs.guestbook.constants.GuestbookConstants;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
       
        @Component(immediate=true)
        public class GuestbookPermission {
       
            public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) {
       
                return _portletResourcePermission.contains(permissionChecker, groupId, actionId);
       
            }
       
            @Reference(
                    target="(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")", 
                    unbind="-"
                    )
            protected void setPortletResourcePermission(PortletResourcePermission portletResourcePermission) {
       
                _portletResourcePermission = portletResourcePermission;
            }
       
            private static PortletResourcePermission _portletResourcePermission;
       
        }

このクラスは、アクセス許可を確認するモデルをカプセル化する1つの静的メソッドを定義するコンポーネントです（したがって、クラスをインスタンス化する必要はありません）。 Liferayの `PermissionChecker` クラスはほとんどの作業を行います： `ADD_ENTRY`などの適切なリソースとアクションを与え、許可が存在するかどうかを返します。

メソッドは1つしかありません。ユーザーがアクセス許可を持っていない場合に例外をスローする `チェック` メソッドです。

次に、2つのエンティティのヘルパーを作成します。

1.  `GuestbookModelPermission.java`という同じパッケージにクラスを作成します。

2.  このクラスのコンテンツを次のコードに置き換えます。

    ``` 
        package com.liferay.docs.guestbook.web.internal.security.permission.resource;

        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

        @Component(immediate = true)
        public class GuestbookModelPermission {

            public static boolean contains(
                    PermissionChecker permissionChecker, Guestbook guestbook, String actionId) throws PortalException {

                return _guestbookModelResourcePermission.contains(permissionChecker, guestbook, actionId);
            }

            public static boolean contains(
                    PermissionChecker permissionChecker, long guestbookId, String actionId) throws PortalException {

                return _guestbookModelResourcePermission.contains(permissionChecker, guestbookId, actionId);
            }

            @Reference(
                    target = "(model.class.name=com.liferay.docs.guestbook.model.Guestbook)", 
                    unbind = "-")
            protected void setEntryModelPermission(ModelResourcePermission<Guestbook> modelResourcePermission) {

                _guestbookModelResourcePermission = modelResourcePermission;
            }

            private static ModelResourcePermission<Guestbook>_guestbookModelResourcePermission;

        }
    ```

ご覧のとおり、このクラスは `GuestbookPermission`似てい` 。 違いは、 <code>GuestbookModelPermission` はモデル/リソースのアクセス許可であるため、エンティティまたはそのプライマリキー（`guestbookId`）を指定することです。

最終的なクラスは `GuestbookModelPermission`とほぼ同じですが、 `Entry` エンティティ用です。 次の手順に従って作成してください。

1.  `GuestbookEntryPermission.java`という名前の同じパッケージにクラスを作成します。

2.  このクラスのコンテンツを次のコードに置き換えます。
   
        package com.liferay.docs.guestbook.web.internal.security.permission.resource;
       
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;
       
        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
       
        @Component(immediate = true)
        public class GuestbookEntryPermission {
       
            public static boolean contains(
                    PermissionChecker permissionChecker, Entry entry, String actionId) throws PortalException {
       
                return _guestbookEntryModelResourcePermission.contains(permissionChecker, entry, actionId);
            }
       
            public static boolean contains(
                    PermissionChecker permissionChecker, long entryId, String actionId) throws PortalException {
       
                return _guestbookEntryModelResourcePermission.contains(permissionChecker, entryId, actionId);
            }
       
            @Reference(
                    target = "(model.class.name=com.liferay.docs.guestbook.model.Entry)", 
                    unbind = "-")
            protected void setEntryModelPermission(ModelResourcePermission<Entry> modelResourcePermission) {
       
                _guestbookEntryModelResourcePermission = modelResourcePermission;
            }
       
            private static ModelResourcePermission<Entry>_guestbookEntryModelResourcePermission;
       
        }

このクラスは `GuestbookModelPermission`とほぼ同じです。 唯一の違いは、 `GuestbookEntryPermission` が `Entry` エンティティ用であることです。

これで、ユーザーに権限UIを公開して、ユーザーが権限を割り当てることができます。

1.  `guestbook-web` プロジェクトの `init.jsp` します。 次のインポートをファイルに追加します。
   
        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookModelPermission" %>
        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookPermission" %>
        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookEntryPermission" %>
        <%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
        <%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys" %>

    最初の3つは、作成したアクセス許可ヘルパークラスです。

2.  `guestbook_actions.jsp`開き` 。 <code><liferay-ui:icon-delete>` タグの直後に次のコードを追加します。

    ``` 
        <c:if
        test="<%=GuestbookModelPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.PERMISSIONS) %>">

            <liferay-security:permissionsURL
                modelResource="<%= Guestbook.class.getName() %>"
                modelResourceDescription="<%= guestbook.getName() %>"
                resourcePrimKey="<%= String.valueOf(guestbook.getGuestbookId()) %>"
                var="permissionsURL" />

            <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

        </c:if>
    ```

3.  ファイルを保存します。

ゲストブックのLiferayの権限UIを表示するアクションボタンを追加しました。 さらに、作成したアクセス許可ヘルパーを使用して、ユーザーがアクションボタンを表示できるかどうかをテストしました。 ユーザーに *許可* 許可がある場合にのみ表示されます。

次のステップで、これをゲストブックエントリに実装します。

おめでとうございます！ これで、アクセス許可のヘルパークラスが作成され、ユーザーがアクセス許可をリソースに関連付けることができるようになりました。 残っているのは、アプリケーションのビューレイヤーに権限チェックを実装することだけです。 次にこれを行います。
