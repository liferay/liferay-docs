---
header-id: overriding-mvcactioncommand
---

# MVCActionCommandのオーバーライド

[TOC levels=1-4]

Liferay MVC actionコマンドに追加したい場合は、追加できます。OSGiフレームワークを使用すると、[MVCコマンドにロジックを追加](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)する手順に従って、MVC actionコマンドをオーバーライドできます。
[カスタムMVC actionコマンドを、オリジナルと同じプロパティを持つが、サービスランキングが高いOSGiコンポーネントとして登録](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands#step-2-publish-as-a-component)します。

通常、カスタムMVC actionコマンドは[`BaseMVCActionCommand` クラス](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.html)を拡張し、`void`を返す`doProcessAction`メソッドをオーバーライドします。[元のサービスへの参照を取得](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands#step-3-refer-to-the-original-implementation),し、[独自のロジックの後に呼び出す](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands#step-4-add-the-logic)ことで、アクションメソッドの元の動作にロジックを追加します。
たとえば、次の`MVCActionCommand`オーバーライドは、元の処理を続行する前に、ブログエントリで`delete`アクションが呼び出されるかどうかを確認し、ログにメッセージを出力します。

    @Component(
    property = {
    "javax.portlet.name=" + BlogsPortletKeys.BLOGS_ADMIN,
    "mvc.command.name=/blogs/edit_entry",
    "service.ranking:Integer=100"
    },
    service = MVCActionCommand.class
    )
    public class CustomBlogsMVCActionCommand extends BaseMVCActionCommand {
    
    @Override
    protected void doProcessAction
    (ActionRequest actionRequest, ActionResponse actionResponse)
    throws Exception {
    
    String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
    
    if (cmd.equals(Constants.DELETE)) {
    System.out.println("Deleting a Blog Entry");
    }
    
    mvcActionCommand.processAction(actionRequest, actionResponse);
    }
    
    @Reference(
    target = "(component.name=com.liferay.blogs.web.internal.portlet.action.EditEntryMVCActionCommand)")
    protected MVCActionCommand mvcActionCommand;
    
    }

既存のロジックの前にMVC actionコマンドロジックを追加するのは簡単で、新しいコードと古いコードの間の疎結合を維持します。

## 関連トピック

[MVC Action Command](/docs/7-1/tutorials/-/knowledge_base/t/mvc-action-command)

[Adding Logic to MVC Commands](/docs/7-1/tutorials/-/knowledge_base/t/adding-logic-to-mvc-commands)

[Overriding MVCRenderCommands](/docs/7-1/tutorials/-/knowledge_base/t/overriding-mvcrendercommand)

[Converting StrutsActionWrappers to MVCCommands](/docs/7-1/tutorials/-/knowledge_base/t/converting-strutsactionwrappers-to-mvccommands)
