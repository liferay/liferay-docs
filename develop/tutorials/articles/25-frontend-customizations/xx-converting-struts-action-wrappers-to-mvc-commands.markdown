Converting StrutsActionWrappers to MVCCommands

Starting on Liferay 6.1, developers could customize the Portal and
Portlet Struts Action using a Hook and a StrutsActionWrappers. This hook
should have a liferay-hook.xml file containing the struts mapping and
the new class that would override the default action as follows:

<struts-action>
    <struts-action-path>/login/login</struts-action-path>
    <struts-action-impl>
    com.liferay.sample.hook.action.ExampleStrutsPortletAction
    </struts-action-impl>
</struts-action>

Then the new wrapper would extend either BaseStrutsAction or
BaseStrutsPortletAction depending on whether the struts action was a
portal or portlet action respectively.

You can see more information about it here:
[https://dev.liferay.com/develop/tutorials/-/knowledge\_base/6-1/overriding-and-adding-struts-actions](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-1/overriding-and-adding-struts-actions&sa=D&ust=1465574286567000&usg=AFQjCNEWr1EsS_KdeKTRNSToDzaHkgQREQ)

This mechanism no longer applies for most of the portal portlets
starting on Liferay 7 because they are no longer using Struts Actions
but MVCCommands. However, converting StrutsActionWrappers to MVCCommands
is not complicated.

Legacy StrutsActionWrappers would need to implement all the methods
processAction, render, and serveResource even if we only needed to
customize one of them. Each of this methods now can be customized
independently using differnet class, making the logic simpler and easier
to maintain. Depending on which method you customized, you will need to
use a specific MVCCommand:

-   processAction ->  MVCActionCommand
-   render -> MVCRenderCommand
-   serveResource -> MVCResourceCommand

Once you know which MVCCommand you will need to know which is the
associated mapping that is used by that MVCCommand.

For most of the cases this will be a trivial task because it’s the same
value defined in the legacy struts-action. In the first example, the
mapping was  /login/login and this value remains the same in Liferay 7.
However, this might have changed in some of the portlets, so it’s better
to double check it.

This mapping can be found in Liferay source code. Depending on the url
it will be a different param:

-   RenderURLs will contain a parameter named “mvcRenderCommandName”
-   ActionURLs will be contained in the attribute “name“ of the taglib
    or with the parameter ActionRequest.ACTION_NAME
-   RenderURLs will be contained as the attribute “id”.

Once you have this information you should be able to override the
MVCCommand following these instructions:

-   Overriding MVCActionCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23overriding-mvcactioncommand&sa=D&ust=1465574286572000&usg=AFQjCNFj76Baao2rzL9816gJ7xJqXPs0jg)
-   Overriding MVCResourceCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23overriding-mvcresourcecommand&sa=D&ust=1465574286572000&usg=AFQjCNHlMx3_IqcbbJNZc2exlFBDPdOHsw)
-   Overriding MVCRenderCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#adding-logic-to-an-existing-mvc-render-command](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23adding-logic-to-an-existing-mvc-render-command&sa=D&ust=1465574286573000&usg=AFQjCNG-ZgG5BDZgqVkow8ThU-Iq0jTLqA)


