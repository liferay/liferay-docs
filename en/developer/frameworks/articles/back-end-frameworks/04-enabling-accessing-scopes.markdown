---
header-id: enabling-and-accessing-data-scopes
---

# Enabling and Accessing Data Scopes

[TOC levels=1-4]

Apps can restrict their data to specific scopes (e.g., Global, Site, Page). 
Here, you'll learn how to 

-   [Enable Scoping](#enabling-scoping) 
-   [Access Your App's Scope](#accessing-your-apps-scope)
-   [Access the Site Scope](#accessing-the-site-scope)

For more detailed information about scoping, see 
[Data Scopes](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#data-scopes). 

## Enabling Scoping

1.  Scope your app's entities. In your service layer, your entities must have a 
    `companyId` attribute of type `long` to enable scoping by portal instance, 
    and a `groupId` attribute of type `long` to enable scoping by Site. Using 
    [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder) 
    is the simplest way to do this. For instructions on this, see 
    [Service Builder Persistence](/docs/7-2/appdev/-/knowledge_base/a/creating-a-service-builder-project) 
    and 
    [Business Logic with Service Builder](/docs/7-2/appdev/-/knowledge_base/a/business-logic-with-service-builder). 

2.  To enable scoping in your app, set the property 
    `"com.liferay.portlet.scopeable=true"` in your portlet class's `@Component` 
    annotation. For example, the
    [Web Content Display Portlet's portlet class](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/journal/journal-content-web/src/main/java/com/liferay/journal/content/web/internal/portlet/JournalContentPortlet.java) 
    sets this component property: 

    ```java
    @Component(
        immediate = true,
        property = {
            ...
            "com.liferay.portlet.scopeable=true",
            ...,
        },
        service = Portlet.class
    )
    public class JournalContentPortlet extends MVCPortlet {
        ...
    }
    ```

## Accessing Your App's Scope

Users can typically set an app's scope to a page, a Site, or the entire portal. 
To handle your app's data, you must access it in its current scope. Your app's
scope is available in these ways: 

1.  Via the `scopeGroupId` variable injected in JSPs that use the
    `<liferay-theme:defineObjects />` tag. This variable contains your app's
    current scope. For example, the Liferay Bookmarks app's
    [`view.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/bookmarks/bookmarks-web/src/main/resources/META-INF/resources/bookmarks/view.jsp#L122-L125) 
    uses its `scopeGroupId` to retrieve the bookmarks and total number of 
    bookmarks in the current scope: 

    ```markup
    ...
    total = BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId);

    bookmarksSearchContainer.setTotal(total);
    bookmarksSearchContainer.setResults(BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, bookmarksSearchContainer.getStart(), bookmarksSearchContainer.getEnd()));
    ...
    ```

2.  By calling the `getScopeGroupId()` method on the request's 
    [`ThemeDisplay`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    This method returns your app's current scope. For example, the Liferay Blogs
    app's
    [`EditEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/EditEntryMVCActionCommand.java#L350-L362) 
    class does this in its `subscribe` and `unsubscribe` methods: 

    ```java
    protected void subscribe(ActionRequest actionRequest) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        _blogsEntryService.subscribe(themeDisplay.getScopeGroupId());
    }

    protected void unsubscribe(ActionRequest actionRequest) throws Exception {
        ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
            WebKeys.THEME_DISPLAY);

        _blogsEntryService.unsubscribe(themeDisplay.getScopeGroupId());
    }
    ```

    If you know your app always needs the portal instance ID, use 
    `themeDisplay.getCompanyId()`. 

3.  By calling the `getScopeGroupId()` method on a 
    [`ServiceContext`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html) 
    object. See 
    [Understanding Service Context](/docs/7-2/frameworks/-/knowledge_base/f/understanding-servicecontext)
    for an example and more details. If you know your app always needs the 
    portal instance ID, use the `ServiceContext` object's `getCompanyId()` 
    method. 

## Accessing the Site Scope

To access the Site scope regardless of your app's current scope, use the 
[`ThemeDisplay`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html) 
method `getSiteGroupId()`. For more information on this use case, see 
[Accessing the Site Scope Across Apps](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#accessing-the-site-scope-across-apps). 

For example, the Web Content app's 
[`edit_feed.jsp`](https://github.com/liferay/liferay-portal/blob/7.2.0-ga1/modules/apps/journal/journal-web/src/main/resources/META-INF/resources/edit_feed.jsp#L40)
uses the `getSiteGroupId()` method to get the Site ID, which is required to 
retrieve Structures: 

```java
ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(themeDisplay.getSiteGroupId(), 
    PortalUtil.getClassNameId(JournalArticle.class), ddmStructureKey, true);
```

## Related Topics

[Data Scopes](/docs/7-2/frameworks/-/knowledge_base/f/back-end-frameworks#data-scopes)

[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)

[Service Builder Project](/docs/7-2/appdev/-/knowledge_base/a/creating-a-service-builder-project)

[Business Logic with Service Builder](/docs/7-2/appdev/-/knowledge_base/a/business-logic-with-service-builder)
