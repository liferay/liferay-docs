---
header-id: overriding-mvcresourcecommand
---

# Overriding MVCResourceCommands

[TOC levels=1-4]

If you need to add functionality to a Liferay MVC resource command, you can. The 
Liferay MVC command framework supports customizing MVC resource commands. It 
follows the process for [adding logic to MVC commands](/docs/7-2/customization/-/knowledge_base/c/adding-logic-to-mvc-commands) 
and it is similar to the ones described for `MVCRenderCommand` and 
`MVCActionCommand`. There's a couple things to keep in mind:

-  The service to specify in your component is `MVCResourceCommand.class`

-  As with overriding `MVCRenderCommand`, there's no base implementation class 
   to extend. Implement the [`MVCResourceCommand` interface](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.html) 
   yourself. 

-  Keep your code decoupled from the original code by adding your logic to the 
   original `MVCResourceCommand`'s logic by getting a reference to the original 
   and returning a call to its `serveResource` method:

```java
return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);
```

The following example overrides the behavior of 
`com.liferay.login.web.portlet.action.CaptchaMVCResourceCommand`, from the 
Liferay's Login portlet's `login-web` module. It simply prints a line in the 
console and then executes the original logic: returning the Captcha image for 
the account creation screen. 

```java
@Component(
    property = { 
        "javax.portlet.name=" + LoginPortletKeys.LOGIN,
        "mvc.command.name=/login/captcha"
    }, 
    service = MVCResourceCommand.class
)
public class CustomCaptchaMVCResourceCommand implements MVCResourceCommand {

    @Override
    public boolean serveResource
        (ResourceRequest resourceRequest, ResourceResponse resourceResponse) {

        System.out.println("Serving login captcha image");

        return mvcResourceCommand.serveResource(resourceRequest, resourceResponse);
    }

    @Reference(target = 
        "(component.name=com.liferay.login.web.internal.portlet.action.CaptchaMVCResourceCommand)")
    protected MVCResourceCommand mvcResourceCommand;
}
```

And that, as they say, is that. Even if you don't own the source code of an 
application, you can [override its MVC commands](/docs/7-2/customization/-/knowledge_base/c/overriding-liferay-mvc-commands) 
just by knowing the component class name. 

## Related Topics

- [Adding Logic to MVC Commands](/docs/7-2/customization/-/knowledge_base/c/adding-logic-to-mvc-commands)
- [Overriding MVCRenderCommands](/docs/7-2/customization/-/knowledge_base/c/overriding-mvcrendercommand)
