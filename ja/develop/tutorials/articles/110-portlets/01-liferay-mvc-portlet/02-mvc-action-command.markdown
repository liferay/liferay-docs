---
header-id: mvc-action-command
---

# MVCアクションコマンド

[TOC levels=1-4]

LiferayのMVCフレームワークでは、ポートレットの [アクションメソッド](/docs/7-1/tutorials/-/knowledge_base/t/writing-controller-code#action-methods) を個別のクラスに分割できます。 これは、多くのアクションがあるポートレットで非常に役立ちます。 必要に応じて、ポートレットのJSPの各アクションURLが適切なアクションクラスを呼び出します。

最初に、ビューレイヤー</a> を

設定し、 [`<portlet:actionURL>` タグ](@platform-ref@/7.1-latest/taglibs/util-taglib/portlet/actionURL.html) を使用してJSPにアクションURLを作成します。 たとえば、ブログアプリの [`edit_entry.jsp` ファイル](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-web/src/main/resources/META-INF/resources/blogs/edit_entry.jsp) は、ブログエントリを編集するための次のアクションURLを定義します。</p> 

    <portlet:actionURL name="/blogs/edit_entry" var="editEntryURL" />
    

`name` 属性は、ポートレットアクションURLオブジェクトを保持する変数を宣言します。 その変数を、ボタンやアイコンなどのUIコンポーネントに割り当てます。 ユーザーがUIコンポーネントをトリガーすると、アクションURLに一致する `* MVCActionCommand` クラスがアクション要求と応答を処理します。 [`MVCActionCommand` インターフェース](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.html)実装するか、 [`BaseMVCActionCommand` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html)拡張して、アクションクラスを作成します。 後者はすでに `MVCActionCommand`実装しているため、時間を節約できます。

実行するアクションの後に `* MVCActionCommand` クラスに名前を付けることは良い習慣です。 あなたのアクションクラスは、エントリのいくつかの種類を編集する場合たとえば、あなたはそのクラス名可能性が `EditEntryMVCActionCommand`。 アプリケーションに複数のMVCコマンドクラスがある場合、このように名前を付けると、それらを区別するのに役立ちます。

`* MVCActionCommand` クラスには、次の例のように `@Component` アノテーションも必要です。 プロパティ `javax.portlet.name` をポートレットの内部IDに設定します。 プロパティ `mvc.command.name` を、JSPの一致する `actionURL`の `name` プロパティの値に設定します。 OSGiコンテナー内のコンポーネントを `MVCActionCommand` サービスとして登録するには、 `サービス` プロパティを `MVCActionCommand.class`ます。

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=your_portlet_name_YourPortlet",
            "mvc.command.name=/your/jsp/action/url"
        },
        service = MVCActionCommand.class
    )
    
    public class YourMVCActionCommand extends BaseMVCActionCommand {
        // implement your action
    }
    

ブログアプリの `EditEntryMVCActionCommand` クラスは、 `* MVCActionCommand` クラスの実際の例です。

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS_AGGREGATOR,
            "mvc.command.name=/blogs/edit_entry"
        },
        service = MVCActionCommand.class
    )
    public class EditEntryMVCActionCommand extends BaseMVCActionCommand {
    
            @Override
            protected void doProcessAction(
                    ActionRequest actionRequest, ActionResponse actionResponse)
                throws Exception {
    
                String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
    
                try {
                    BlogsEntry entry = null;
    
                    UploadException uploadException =
                        (UploadException)actionRequest.getAttribute(
                            WebKeys.UPLOAD_EXCEPTION);
    
                    if (uploadException != null) {
                        Throwable cause = uploadException.getCause();
    
                        if (uploadException.isExceededFileSizeLimit()) {
                            throw new FileSizeException(cause);
                        }
    
                        if (uploadException.isExceededLiferayFileItemSizeLimit()) {
                            throw new LiferayFileItemException(cause);
                        }
    
                        if (uploadException.isExceededUploadRequestSizeLimit()) {
                            throw new UploadRequestSizeException(cause);
                        }
    
                        throw new PortalException(cause);
                    }
                    else if (cmd.equals(Constants.ADD) ||
                             cmd.equals(Constants.UPDATE)) {
    
                        Callable<BlogsEntry> updateEntryCallable =
                            new UpdateEntryCallable(actionRequest);
    
                        entry = TransactionInvokerUtil.invoke(
                            _transactionConfig, updateEntryCallable);
                    }
                    else if (cmd.equals(Constants.DELETE)) {
                        deleteEntries(actionRequest, false);
                    }
                    else if (cmd.equals(Constants.MOVE_TO_TRASH)) {
                        deleteEntries(actionRequest, true);
                    }
                    else if (cmd.equals(Constants.RESTORE)) {
                        restoreTrashEntries(actionRequest);
                    }
                    else if (cmd.equals(Constants.SUBSCRIBE)) {
                        subscribe(actionRequest);
                    }
                    else if (cmd.equals(Constants.UNSUBSCRIBE)) {
                        unsubscribe(actionRequest);
                    }
    
                    ... do more action processing
                }
    
                ... handle exceptions
        }
    }
    

`@Component`の複数の `javax.portlet.name` プロパティ値により、この `* MVCActionCommand` クラスがそれらのポートレットでサービスコンポーネントとして使用可能になります。 `mvc.command.name` プロパティ設定 `/ブログ/ edit_entry` マッチ `actionURL`の `名` 属性が以前示されており、 `サービス` に対してプロパティセット `MVCActionCommand.class` クラスを作る `MVCActionCommand` サービスコンポーネント。

`EditEntryMVCActionCommand` クラスは `BaseMVCActionCommand` を拡張し、 `doProcessAction` メソッドをオーバーライドします。 同様に、 `MVCActionCommand` 直接実装する `* MVCActionCommand` クラスは、 `processAction` メソッドを実装する必要があります。 どちらのメソッドも、それぞれ `javax.portlet.ActionRequest` および `javax.portlet.ActionResponse` パラメーターを介してリソース要求と応答を処理します。

`EditEntryMVCActionCommand`の `doProcessAction` メソッドは、 `ActionRequest`から [定数 `Constants.CMD`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/Constants.html) という名前のコマンドパラメーターの値を取得します。

    String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
    

次に、 `doProcessAction` メソッドは、エントリ関連のアップロードが発生したかどうかを確認するか、アップロードがスローする例外を処理します。 アクション要求からアクセスされたコマンド（ `cmd`格納されている）に基づいて、次のアクションのいずれかが実行されます。

  - エントリを追加または更新します
  - エントリーを削除する
  - エントリをごみ箱に移動する
  - ごみ箱からエントリを復元する
  - ユーザーをブログに登録する
  - ブログからユーザーを退会する

`EditEntryMVCActionCommand`の `doProcessAction` メソッドはさらに処理を続行し、ポートレットを適切なビューにリダイレクトする準備をします。 これは、ポートレットのアクションを処理するために必要なだけできることを示しています。

| **注**： [Liferay Bladeサンプル `action-command-portlet`](https://github.com/liferay/liferay-blade-samples/tree/7.1/gradle/apps/action-command-portlet) | `MVCActionCommand` 直接実装する方法を示します。

LiferayのMVCフレームワークを使用するアプリケーションで、独自のアクションURLと `* MVCActionCommand` クラスを作成できるようになりました。 `* MVCActionCommand`は、必要なことは何でもできます。



## 関連トピック

[MVCポートレットの作成](/docs/7-1/tutorials/-/knowledge_base/t/creating-an-mvc-portlet)

[MVCレンダリングコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-render-command)

[MVCリソースコマンド](/docs/7-1/tutorials/-/knowledge_base/t/mvc-resource-command)

[MVCコマンドのオーバーライド](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvc-commands)
