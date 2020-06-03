---
header-id: defining-portlet-actions
---

# ポートレットアクションの定義

<p class="alert alert-info"><span class="wysiwyg-color-blue120">ご覧のページは、お客様の利便性のために一部機械翻訳されています。また、ドキュメントは頻繁に更新が加えられており、翻訳は未完成の部分が含まれることをご了承ください。最新情報は都度公開されておりますため、必ず英語版をご参照ください。翻訳に問題がある場合は、<a href="mailto:support-content-jp@liferay.com">こちら</a>までご連絡ください。</span></p>

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Guestbook Adminアプリの作成</p><p>手順4/5</p>
</div>

Guestbook Adminポートレットには、ゲストブックを追加、更新、削除するためのアクションメソッドが必要になりました。 ゲストブックポートレットと同様に、アクションメソッドは対応するサービスメソッドを呼び出します。 サービスとアプリケーションはすべて同じコンテナで実行されているため、どのアプリケーションでもゲストブックサービスを呼び出すことができます。 これは、@product@のOSGiベースのアーキテクチャの利点です。異なるアプリケーションまたはモジュールは、他のモジュールによって公開されたサービスを呼び出すことができます。 サービスが公開されている場合、 `@Reference`介して使用できます。 ここでは、Guestbook Adminポートレットでこれを利用して、Guestbookポートレットと同じサービスの1つ（ `addGuestbook` サービス）を使用します。

## 3つのポートレットアクションを追加する

Guestbook Adminポートレットでは、管理者が `Guestbook` オブジェクトを追加、更新、削除できるようにする必要があります。 これらの要件を満たすために、ポートレットアクションを作成します。 `GuestbookAdminPortlet.java` を開き、次の手順に従います。

1.  新しいゲストブックの追加に必要な次のアクションメソッドとインスタンス変数を追加します。
   
        public void addGuestbook(ActionRequest request, ActionResponse response)
            throws PortalException {
       
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), request);
       
            String name = ParamUtil.getString(request, "name");
       
            try {
                _guestbookLocalService.addGuestbook(
                    serviceContext.getUserId(), name, serviceContext);
            }
            catch (PortalException pe) {
       
                Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                    Level.SEVERE, null, pe);
       
                response.setRenderParameter(
                    "mvcPath", "/guestbookadminportlet/edit_guestbook.jsp");
            }
        }
       
        private GuestbookLocalService _guestbookLocalService;
       
        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookLocalService guestbookLocalService) {
            _guestbookLocalService = guestbookLocalService;
        }

    `addGuestbook` はポートレットアクションメソッドであるため、 `ActionRequest` および `ActionResponse` パラメーターが必要です。 サービスコールを行って新しいゲストブックを追加するには、リクエストからゲストブックの名前を取得する必要があります。 `serviceContext` もリクエストから取得し、サービス呼び出しの引数として渡す必要があります。 例外がスローされた場合、デフォルトビューではなく、ゲストブックの追加フォームを表示する必要があります。 そのため、この行を `catch` ブロックに追加します。
   
        response.setRenderParameter("mvcPath",
                "/guestbookadminportlet/edit_guestbook.jsp");

    後で、フィールドの検証とユーザーへのエラーメッセージの表示にこれを使用します。 `/guestbookadminportlet/edit_guestbook.jsp` はまだ存在しないことに注意してください。 Guestbook Adminポートレットのユーザーインターフェイスを設計するときに、次のセクションで作成します。

2.  既存のゲストブックを更新するための次のアクションメソッドを追加します。
   
        public void updateGuestbook(ActionRequest request, ActionResponse response)
            throws PortalException {
       
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), request);
       
            String name = ParamUtil.getString(request, "name");
            long guestbookId = ParamUtil.getLong(request, "guestbookId");
       
            try {
                _guestbookLocalService.updateGuestbook(
                    serviceContext.getUserId(), guestbookId, name, serviceContext);
       
            } catch (PortalException pe) {
       
                Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                    Level.SEVERE, null, pe);
       
                response.setRenderParameter(
                    "mvcPath", "/guestbookadminportlet/edit_guestbook.jsp");
            }
        }

    このメソッドは、ゲストブック名、ID、およびリクエストから `serviceContext` を取得します。 `updateGuestbook` サービスコールは、ゲストブックのIDを使用して、更新するゲストブックを識別します。 サービス呼び出しに問題がある場合、ゲストブック管理ポートレットはゲストブックの編集フォームを再度表示し、ユーザーがフォームを編集して再送信できるようにします。
   
        response.setRenderParameter("mvcPath",
                "/guestbookadminportlet/edit_guestbook.jsp");

    [ゲストブックの編集]フォームは、[ゲストブックの追加]フォームと同じJSPを使用して、コードの重複を避けることに注意してください。

3.  ゲストブックを削除するための次のアクションメソッドを追加します。
   
        public void deleteGuestbook(ActionRequest request, ActionResponse response)
            throws PortalException {
       
            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                Guestbook.class.getName(), request);
       
            long guestbookId = ParamUtil.getLong(request, "guestbookId");
       
            try {
                _guestbookLocalService.deleteGuestbook(guestbookId, serviceContext);
            }
            catch (PortalException pe) {
       
                Logger.getLogger(GuestbookAdminPortlet.class.getName()).log(
                    Level.SEVERE, null, pe);
            }
        }

    このメソッドは、サービスレイヤーを使用して、そのIDでゲストブックを削除します。 `deleteGuestbook` アクションはGuestbook Adminポートレットのデフォルトビューから呼び出されるため、 `deleteGuestbook` サービス呼び出しで問題が発生した場合、特定のJSPを指すように `mvcPath` レンダリングパラメーターを設定する必要はありません。

4.  \ [CTRL \] + \ [SHIFT \] + Oを押してインポートを整理します。 ファイルを保存します。

これで、サービスメソッドとポートレットアクションメソッドが準備できました。 最後のタスクは、Guestbook Adminポートレットのユーザーインターフェイスを実装することです。
