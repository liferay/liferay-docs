---
header-id: injecting-additional-context-variables-and-functionality-into-your-theme-te
---

# Injecting Additional Context Variables and Functionality into Your Theme Templates

[TOC levels=1-4]

JSPs are native to Java EE and therefore have access to all the contextual 
objects inherit to the platform, like the request and session. Through these 
objects, developers can obtain @product@-specific context information by 
accessing container objects like `themeDisplay` or `serviceContext`. This, 
however, is not the case for FreeMarker templates. To access this information in 
FreeMarker templates, you must inject it yourself into the template's context. 
@product@ gives you a head start by injecting several common objects into the 
template's context and exposing them as 
[FreeMarker macros](/developer/frameworks/-/knowledge_base/7-2/product-freemarker-macros). 
To inject other objects into the FreeMarker template's context, you must create 
a *Context Contributor*. 

You can create a Context Contributor to use non-JSP templating languages for 
themes, ADTs, and any other templates used in @product@. For example, suppose 
you want your theme to change color based on the user's organization. You could 
create a Context Contributor to inject the user's organization into your theme's 
context, and then determine the theme's color based on that information. 

Follow the steps below to create a context contributor:

1.  Create an OSGi module using your favorite third party tool, or use 
    [Blade CLI](/developer/reference/-/knowledge_base/7-2/blade-cli).

2.  Create a component class that implements the `TemplateContextContributor` 
    service, and set the `type` property to the type of context you're injecting 
    into. Set it to `TYPE_THEME` to inject context-specific variables for your 
    theme, or set it to `TYPE_GLOBAL` to inject it into every context execution 
    in @product@, like themes, ADTs, DDM templates, etc, as defined in 
    [TemplateContextContributor](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html). 
    To follow naming conventions, begin the class name with the entity you want 
    to inject context-specific variables for, followed by 
    *TemplateContextContributor* (e.g., `ProductMenuTemplateContextContributor`):

    ```java
    @Component(
        immediate = true,
        property = {"type=" + TemplateContextContributor.TYPE_THEME},
        service = TemplateContextContributor.class
    )
    ```

3.  Implement the 
    [TemplateContextContributor](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/template/TemplateContextContributor.html) 
    interface in your `*TemplateContextContributor` class, and overwrite the 
    `prepare(Map<String,Object>, HttpServletRequest)` method to inject new or 
    modified variables into the `contextObjects` map. This is your template's 
    context that was described earlier. 

The `ProductMenuTemplateContextContributor`'s class is shown as an example 
below. It overwrites the `prepare(...)` method to inject a modified 
`bodyCssClass` variable and a new `liferay_product_menu_state` variable into the 
theme context for the Product Menu. Specifically, the `cssClass` variable 
provides styling for the Product Menu and the `productMenuState` variable 
determines whether the visible Product Menu should be open or closed:

```java
@Override
public void prepare(
    Map<String, Object> contextObjects, HttpServletRequest request) {

    if (!isShowProductMenu(request)) {
        return;
    }

    String cssClass = GetterUtil.getString(
        contextObjects.get("bodyCssClass"));
    String productMenuState = SessionClicks.get(
        request,
        ProductNavigationProductMenuWebKeys.
            PRODUCT_NAVIGATION_PRODUCT_MENU_STATE,
        "closed");

    contextObjects.put(
        "bodyCssClass", cssClass + StringPool.SPACE + productMenuState);

    contextObjects.put("liferay_product_menu_state", productMenuState);
}
```

The `ProductMenuTemplateContextContributor` provides an easy way to inject 
variables into @product@'s theme directly related to the Product Menu. You can 
do the same with your custom context contributor. With the power to inject 
additional variables into any context in @product@, you're free to fully harness 
the power of your chosen templating language. 

## Related Topics

- [Customizing the Product Menu](/developer/frameworks/-/knowledge_base/7-2/customizing-the-product-menu)
- [Developing Themes](/developer/frameworks/-/knowledge_base/7-2/developing-themes)
- [Theme Contributors](/developer/frameworks/-/knowledge_base/7-2/packaging-independent-ui-resources-for-your-site)
