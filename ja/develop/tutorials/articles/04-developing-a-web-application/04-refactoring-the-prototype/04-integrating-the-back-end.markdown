---
header-id: integrating-the-new-back-end
---

# 新しいバックエンドの統合

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">プロトタイプのリファクタリング</p><p>手順4/6</p>
</div>

概念実証として機能するプロトタイプから開始することを推奨しますが、最終的にはそのプロトタイプを実際のアプリケーションに変換する必要があります。 これまでで、それを行うための準備はすべて完了しました。今度は、プロトタイプバックエンドを、Service Builderで作成したデータベース駆動型の実際のバックエンドに置き換えます。

プロトタイプでは、アプリケーションのモデルをマニュアルで作成しました。 Service Builderは新しいものを生成したため、最初に行うことはそれを削除することです。

1.  `guestbook-web`モジュールで`com.liferay.docs.guestbook.model`パッケージを見つけます。

2.  それを削除してください。 プロジェクトにエラーが表示されますが、それはまだモデルを交換していないためです。

これで、いくつかの依存関係管理を行うことができます。 生成されたサービスにWebモジュールがアクセスするには、APIおよびサービスモジュールを認識させる必要があります。 次に`GuestbookPortlet`の`addEntry`メソッドをアップデートして、新しいサービスを使用できます。

1.  まず、`guestbook-web`の`build.gradle`ファイルを開き、これらの依存関係を追加します。
   
        compileOnly project(":modules:guestbook:guestbook-api")
        compileOnly project(":modules:guestbook:guestbook-service")

2.  `guestbook-web`プロジェクトを右クリックして、*Gradle* → *Refresh Gradle Project*を選択します。

3.  ここで、必要なService Builderサービスに*references*を追加する必要があります。 これを行うには、セッターメソッドで`@Reference`アノテーションを付けてクラス変数として追加します。 `GuestbookPortlet`を開き、これらの参照をファイルの下部に追加します。

    ``` 
        @Reference(unbind = "-")
        protected void setEntryService(EntryLocalService entryLocalService) {
            _entryLocalService = entryLocalService;
        }

        @Reference(unbind = "-")
        protected void setGuestbookService(GuestbookLocalService guestbookLocalService) {
            _guestbookLocalService = guestbookLocalService;
        }

        private EntryLocalService _entryLocalService;
        private GuestbookLocalService _guestbookLocalService;
    ```

    この方法でクラス変数を追加するには、Liferayのコードスタイルでなければならないことに注意してください。 セッターの`@Reference`アノテーションにより、LiferayのOSGiコンテナーは生成されたサービスへ参照を挿入して、それらを使用できるようにします。 `unbind`パラメーターは、これらのサービスのバインドを解除する方法がないことをコンテナーに通知します。参照が不要になった場合、ガベージコレクション中に参照がクラスと共に消滅する可能性があります。

4.  `addEntry`メソッドを変更して、これらのサービス参照を使用できるようになりました。
   
        public void addEntry(ActionRequest request, ActionResponse response)
                    throws PortalException {
       
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);
       
                String userName = ParamUtil.getString(request, "name");
                String email = ParamUtil.getString(request, "email");
                String message = ParamUtil.getString(request, "message");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
                long entryId = ParamUtil.getLong(request, "entryId");
       
            if (entryId > 0) {
       
                try {
       
                    _entryLocalService.updateEntry(
                        serviceContext.getUserId(), guestbookId, entryId, userName,
                        email, message, serviceContext);
       
                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));
       
                }
                catch (Exception e) {
                    System.out.println(e);
       
                    PortalUtil.copyRequestParameters(request, response);
       
                    response.setRenderParameter(
                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                }
       
            }
            else {
       
                try {
                    _entryLocalService.addEntry(
                        serviceContext.getUserId(), guestbookId, userName, email,
                        message, serviceContext);
       
                    SessionMessages.add(request, "entryAdded");
       
                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));
       
                }
                catch (Exception e) {
                    SessionErrors.add(request, e.getClass().getName());
       
                    PortalUtil.copyRequestParameters(request, response);
       
                    response.setRenderParameter(
                        "mvcPath", "/guestbookwebportlet/edit_entry.jsp");
                }
            }
        }

    この`addEntry`メソッドは、ユーザーがJSPで送信する名前、メッセージ、および電子メールフィールドを取得し、それらをサービスに渡してエントリデータとして保存します。 `if-else`ロジックは、既存の`entryId`があるかどうかをチェックします。 もし存在する場合は`update`サービスメソッドが呼び出され、存在しない場合は`add`サービスメソッドが呼び出されます どちらの場合も、ゲストブックIDを使用してレンダーパラメーターを設定し、アプリケーションがゲストブックのエントリを追加した後にゲストブックのエントリを表示できるようにします。 これはすべて、`try...catch`ステートメントで行われます。

5.  次に、以前は持っていなかった`deleteEntry`を追加します。
   
        public void deleteEntry(ActionRequest request, ActionResponse response) throws PortalException {
                long entryId = ParamUtil.getLong(request, "entryId");
                long guestbookId = ParamUtil.getLong(request, "guestbookId");
       
                ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    Entry.class.getName(), request);
       
                try {
       
                    response.setRenderParameter(
                        "guestbookId", Long.toString(guestbookId));
       
                    _entryLocalService.deleteEntry(entryId, serviceContext);
                }
       
                catch (Exception e) {
                    Logger.getLogger(GuestbookPortlet.class.getName()).log(
                        Level.SEVERE, null, e);
                }
        }

    このメソッドは、（リクエストからのIDを使用して）エントリオブジェクトを取得し、サービスを呼び出して削除します。

6.  次に、`render`メソッドを置き換える必要があります。
   
        @Override
        public void render(RenderRequest renderRequest, RenderResponse renderResponse)
                throws IOException, PortletException {
       
                try {
                    ServiceContext serviceContext = ServiceContextFactory.getInstance(
                        Guestbook.class.getName(), renderRequest);
       
                    long groupId = serviceContext.getScopeGroupId();
       
                    long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
       
                    List<Guestbook> guestbooks = _guestbookLocalService.getGuestbooks(
                        groupId);
       
                    if (guestbooks.isEmpty()) {
                        Guestbook guestbook = _guestbookLocalService.addGuestbook(
                            serviceContext.getUserId(), "Main", serviceContext);
       
                        guestbookId = guestbook.getGuestbookId();
                    }
       
                    if (guestbookId == 0) {
                        guestbookId = guestbooks.get(0).getGuestbookId();
                    }
       
                    renderRequest.setAttribute("guestbookId", guestbookId);
                }
                catch (Exception e) {
                    throw new PortletException(e);
                }
       
                super.render(renderRequest, renderResponse);
        }

    この新しい`render`メソッドは、現在のサイトのゲストブックをチェックします。 もし存在しない場合は作成します。 いずれにしても、最初のゲストブックを取得して、ビューレイヤーでそのエントリを表示できるようにします。

7.  `parseEntries`メソッドを削除します。 それはプロトタイプアプリケーションの残りです。

8.  Ctrl-Shift-O を押して、インポートを整理します。

ここでは、サービスを使用するためにコントローラーをアップデートしました。 次は、ビューについて見ていきます。
