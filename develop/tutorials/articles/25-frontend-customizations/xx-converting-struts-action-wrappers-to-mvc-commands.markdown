# Converting StrutsActionWrappers to MVCCommands

Since Liferay 6.1, developers could customize the Portal and Portlet Struts 
Actions using a Hook and StrutsActionWrappers. For example, take a look at the 
`liferay-hook.xml` file for a hook that overrode the login portlet's login 
action:

    <struts-action>
        <struts-action-path>/login/login</struts-action-path>
        <struts-action-impl>
        com.liferay.sample.hook.action.ExampleStrutsPortletAction
        </struts-action-impl>
    </struts-action>
    
The `liferay-hook.xml` contains the struts mapping and the new class that 
overrides the default login action.    

The wrapper could extend either `BaseStrutsAction` or `BaseStrutsPortletAction`, 
depending on whether the struts action was a portal or portlet action 
respectively.

Starting in Liferay 7.0 and Liferay DXP, this mechanism no longer applies for 
most of the portal portlets because they are no longer using Struts Actions, but 
instead use MVCCommands.

This tutorial demonstrates the following:

- How to convert your existing StrutsActionWrappers to MVCCommands

Go ahead and get started.

## Coverting Your old wrapper to MVCCommands

Converting StrutsActionWrappers to MVCCommands is easier than you may think.

As a review, legacy StrutsActionWrappers needed to implement all the methods,
`processAction`, `render`, and `serveResource`, even if only one method was 
being customized. Each of these methods can now be customized independently, 
using different classes, making the logic simpler and easier to maintain. 
Depending on which method you customized in your StrutsActionWrapper, you will 
need to use the matching MVCCommand shown below:

-   processAction ->  MVCActionCommand
-   render -> MVCRenderCommand
-   serveResource -> MVCResourceCommand

<!--

Can you show the code for this, using the beginning example? What action would
the login example use, and what are some other examples using the other actions?

-->

Next you'll need to determine the associated mapping that is used by the 
MVCCommand.

For most cases, the MCVCCommand mapping is the same mapping defined in the 
legacy struts-action.

Using the beginning login example once again, the `struts-action-path` mapping, 
`/login/login`, remains the same for the MVCCommand mapping in Liferay 7.

Some of the portlets mapping may have changed, so it’s best to check Liferay's 
source code to determine the correct mapping.

Depending on the URL it will be a different param:

-   RenderURLs will contain a parameter named `mvcRenderCommandName`
-   ActionURLs will be contained in the attribute `name` of the taglib
    or with the parameter `ActionRequest.ACTION_NAME`
-   RenderURLs will be contained as the attribute `id`.<!-- Is this suppose to be the RenderURLs again, or something else? -->

<!--

Can you show the code for this, using the beginning example? In which cases
would you use each param?

-->

Once you have this information you should be able to override the
MVCCommand following these instructions:

<!--

What would code up to this point look like? What should I have at this point to
move onto the tutorials shown below? Is this all done in a `liferay-hook.xml`?

I'm guessing not. So what file are these code changes being made in?

A legacy code example and a finished converted code(using the same example)
would be good to have here.

Thanks!

-->

-   Overriding MVCActionCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcactioncommand](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23overriding-mvcactioncommand&sa=D&ust=1465574286572000&usg=AFQjCNFj76Baao2rzL9816gJ7xJqXPs0jg)
-   Overriding MVCResourceCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#overriding-mvcresourcecommand](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23overriding-mvcresourcecommand&sa=D&ust=1465574286572000&usg=AFQjCNHlMx3_IqcbbJNZc2exlFBDPdOHsw)
-   Overriding MVCRenderCommands:
    [https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands#adding-logic-to-an-existing-mvc-render-command](https://www.google.com/url?q=https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/overriding-mvc-commands%23adding-logic-to-an-existing-mvc-render-command&sa=D&ust=1465574286573000&usg=AFQjCNG-ZgG5BDZgqVkow8ThU-Iq0jTLqA)


