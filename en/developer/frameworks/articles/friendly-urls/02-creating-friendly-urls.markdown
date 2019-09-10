---
header-id: creating-friendly-urls
---

# Friendly URLs

[TOC levels=1-4]

Follow these steps to create friendly URLs:

1.  Create friendly URL routes. Create a `routes.xml` file in your application's 
    web module. Liferay's pattern puts it in a 
    `src/main/resources/META-INF/friendly-url-routes/` folder. 

2.  Add friendly URL routes, using as many `<route>` tags as you need friendly 
    URLs, like this:

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 7.2.0//EN" "http://www.liferay.com/dtd/liferay-friendly-url-routes_7_2_0.dtd">

    <routes>
        <route>
            <pattern></pattern>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">normal</implicit-parameter>
        </route>
        <route>
            <pattern>/maximized</pattern>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">maximized</implicit-parameter>
        </route>
        <route>
            <pattern>/{entryId:\d+}</pattern>
            <implicit-parameter name="categoryId"></implicit-parameter>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view_entry</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">normal</implicit-parameter>
            <implicit-parameter name="tag"></implicit-parameter>
        </route>
        ...
    </routes>
    ```

    Use `<pattern>` tags to define placeholder values for the parameters that 
    normally appear in the generated URL. This is just a mask. The beastly URL  
    still lurks beneath it. 

    The `pattern` value `/{entryId:\d+}` matches a `/` followed by an `entryId` 
    variable that matches the 
    [Java regular expression](https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html) 
    `\d+`---one or more numeric digits. For example, a URL `/entryId`, where the 
    `entryId` value is `123` results in a URL value `/123`, which matches the 
    pattern. 

    | **Warning:** Make sure your `pattern` values don't end in a slash `/`. A
    | trailing slash character prevents the request from identifying the correct
    | route.

    **Important:** If your portlet is instanceable, you must use a variant of 
    the `instanceId` in the `pattern` value. If the starting value is 
    `render-it`, for example, use one of these patterns:

    ```xml
    <pattern>/{userIdAndInstanceId}/render-it</pattern>
    ```
    
    or

    ```xml
    <pattern>/{instanceId}/render-it</pattern>
    ```
    
    or

    ```xml
    <pattern>/{p_p_id}/render-it</pattern>
    ```
    
    Use `<implicit-parameter>` tags to define parameters that are always the 
    same for the URL. For example, for a render URL, you can be certain that the 
    `p_p_lifecycle` parameter is always `0`. You don't have to define these 
    types of implicit parameters, but it's a best practice because if you don't, 
    they still appear in your URL. 

    The implicit parameters with the name `mvcRenderCommandName` are very 
    important. If you're using an `MVCPortlet` with `MVCRenderCommand` classes, 
    that parameter comes from the `mvc.command.name` property in the 
    `@Component` of your `MVCRenderCommand` implementation. This determines the 
    page that's rendered (for example, `view.jsp`). 

    <!--Add link back for 'using an `MVCPortlet` with `MVCRenderCommand` classes' 
    once mvc-render-command article is available-->

    ```java
    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS, "mvc.command.name=/",
            "mvc.command.name=/blogs/view"
        },
        service = MVCRenderCommand.class
    )
    ```

    The [DTD file](@platform-ref@/7.2-latest/definitions/liferay-friendly-url-routes_7_2_0.dtd.html)
    completely defines the `routes.xml` file. 

3.  Provide an implementation of the [`FriendlyURLMapper` service](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/FriendlyURLMapper.html). 
    Create a component that specifies a `FriendlyURLMapper` service, with two 
    properties:

    -  A `com.liferay.portlet.friendly-url-routes` property sets the path to 
       your `routes.xml` file.

    -  A `javax.portlet.name` property, which you probably have already, 
       specifies your portlet's name.

    ```java
    @Component(
        property = {
            "com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS
        },
        service = FriendlyURLMapper.class
    )
    ```

4.  Implement the `FriendlyURLMapper` service. For your convenience, the 
    [`DefaultFriendlyURLMapper` class](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/DefaultFriendlyURLMapper.html) 
    provides a default implementation. If you extend `DefaultFriendlyURLMapper` 
    you must only override one method, `getMapping()`. Return a String that 
    defines the first part of your Friendly URLs. It's smart to name it after 
    your application. Here's what it looks like for Liferay's Blogs application:

    ```java
    public class BlogsFriendlyURLMapper extends DefaultFriendlyURLMapper {

        @Override
        public String getMapping() {
            return _MAPPING;
        }

        private static final String _MAPPING = "blogs";

    }
    ```

All friendly URLs in Blogs begin with the String set here (`blogs`). Let's look 
at one of these Friendly URLs in action. Add a blog entry and then click on the 
entry's title. Look at the URL:

```html
http://localhost:8080/web/guest/home/-/blogs/lunar-scavenger-hunt
```

As specified in the friendly URL mapper class, `blogs` is the first part of the
friendly URL that comes after the Liferay part of the URL. The next part is
determined by a specific URL route in `routes.xml`:

```xml
<route>
    <pattern>/{urlTitle}</pattern>
    <implicit-parameter name="categoryId"></implicit-parameter>
    <implicit-parameter name="mvcRenderCommandName">/blogs/view_entry</implicit-parameter>
    <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
    <implicit-parameter name="p_p_state">normal</implicit-parameter>
    <implicit-parameter name="tag"></implicit-parameter>
</route>
```

The `urlTitle` is generated from the blog post's title. Since it's already
a parameter in the URL (see below), it's available for use in the friendly URL.

```markup
<portlet:renderURL var="viewEntryURL">
    <portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
    <portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
</portlet:renderURL>
```

When the render URL is invoked, the String defined in the friendly URL mapper
teams up with the `pattern` tag in your friendly URL routes file, and you get
a very friendly URL indeed, instead of some nasty, conceited, unfriendly URL
that's despised by users and SEO services alike.

Great! Now you know how to make your URLS friendly. 

## Related Topics

- [Dependency Injection](/docs/7-2/frameworks/-/knowledge_base/f/dependency-injection)
- [Localization](/docs/7-2/frameworks/-/knowledge_base/f/localization)
- [Asset Framework](/docs/7-2/frameworks/-/knowledge_base/f/asset-framework)
