---
header-id: data-scopes
---

# Data Scopes

[TOC levels=1-4]

Apps can restrict their data to specific scopes. Scopes provide a context for
the application's data.

*Global:* One data set throughout a portal instance. 

*Site:* Separate data sets for each Site it's added to.

*Page:* Separate data sets for each page it's added to. 

For example, a Site-scoped app can display its data across a single Site. For a
detailed explanation of scopes, see the user guide article 
[Widget Scope](/docs/7-1/user/-/knowledge_base/u/widget-scope). 
To give your applications scope, you must manually add support for it. This
tutorial shows you how. 

## Scoping Your Entities

In your service layer, your entities must have a `companyId` attribute of type 
`long` to enable scoping by portal instance and a `groupId` attribute of type 
`long` to enable scoping by Site. Using 
[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder) 
is the simplest way to do this. The 
[Service Builder Persistence](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-persistence) 
and 
[Business Logic with Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder)
tutorials show you how. 

## Enabling Scoping

To enable scoping in your app, set the property 
`"com.liferay.portlet.scopeable=true"` in your portlet class's `@Component` 
annotation. For example, the
[Web Content Display Portlet's portlet class](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/journal/journal-content-web/src/main/java/com/liferay/journal/content/web/internal/portlet/JournalContentPortlet.java)
sets this component property: 

    @Component(
        immediate = true,
        property = {
            ...
            "com.liferay.portlet.scopeable=true",
            ...,
        },
        service = Portlet.class
    )
    public class JournalContentPortlet extends MVCPortlet {...

That's it! Next, you'll access your app's scope in your code. 

## Accessing Your App's Scope

Users can typically set an app's scope to a page, a Site, or the entire portal. 
To handle your app's data, you must access it in its current scope. Your app's
scope is available in these ways: 

1.  Via the `scopeGroupId` variable that is injected in your JSPs that 
    use the `<liferay-theme:defineObjects />` tag. This variable contains your
    app's current scope. For example, the Liferay Bookmarks app's
    [`view.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/bookmarks/bookmarks-web/src/main/resources/META-INF/resources/bookmarks/view.jsp#L122-L125) 
    uses its `scopeGroupId` to retrieve the bookmarks and total number of
    bookmarks in the current scope: 

        ...
        total = BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId);
        
        bookmarksSearchContainer.setTotal(total);
        bookmarksSearchContainer.setResults(BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, bookmarksSearchContainer.getStart(), bookmarksSearchContainer.getEnd()));
        ...

2.  By calling the `getScopeGroupId()` method on the request's 
    [`ThemeDisplay` instance](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    This method returns your app's current scope. For example, the Liferay Blogs
    app's
    [`EditEntryMVCActionCommand` class](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/EditEntryMVCActionCommand.java#L371-L383) 
    does this in its `subscribe` and `unsubscribe` methods: 

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

    If you know your app always needs the portal instance ID, use
    `themeDisplay.getCompanyId()`. 

3.  By calling the `getScopeGroupId()` method on a `ServiceContext` object. The
    tutorial
    [Understanding `ServiceContext`](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext)
    provides an example and details. 
    If you know your app always needs the portal instance ID, use the 
    `ServiceContext` object's `getCompanyId()` method. 

Awesome! Now you know how to get your app's scope. Next, you'll learn about
a special use case: getting the Site scope for entities that belong to
a different app. 

## Accessing the Site Scope Across Apps

There may be times when you must access a different app's Site-scoped data from
your app that is scoped to a page or the portal. For example, web content
articles can be created in the page, Site, or portal scope. 
[Structures and Templates](/docs/7-1/user/-/knowledge_base/u/designing-uniform-content) for
such articles, however, exist only in the Site scope. The above techniques
return the app's scope, which might not be the Site scope. What a pickle! Never
fear, the `ThemeDisplay` method `getSiteGroupId()` is here! This method always
gets the Site scope, no matter your app's current scope. For example, 
[the Web Content app's `edit_feed.jsp`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/modules/apps/journal/journal-web/src/main/resources/META-INF/resources/edit_feed.jsp#L40)
uses this method to get the Site ID needed to retrieve Structures: 

    ...

    ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(themeDisplay.getSiteGroupId(), 
        PortalUtil.getClassNameId(JournalArticle.class), ddmStructureKey, true);

    ...

Great! Now you know how to scope your apps, access their scope, and even get the
Site scope of entities that belong to other apps. 

## Related Topics

[Widget Scope](/docs/7-1/user/-/knowledge_base/u/widget-scope)

[Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/service-builder)

[Service Builder Persistence](/docs/7-1/tutorials/-/knowledge_base/t/service-builder-persistence)

[Business Logic with Service Builder](/docs/7-1/tutorials/-/knowledge_base/t/business-logic-with-service-builder)
