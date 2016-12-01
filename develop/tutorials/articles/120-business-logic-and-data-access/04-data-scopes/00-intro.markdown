# Data Scopes

Apps in @product@ can restrict their data to specific 
[scopes](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/scope).
For example, a site-scoped app can only display its data across a single site. 
For a detailed explanation of scopes, see the user guide article 
[Application Scope](/discover/portal/-/knowledge_base/7-0/application-scope). 

Scoping, however, isn't available for custom apps by default. You have to 
manually add support for it. This tutorial shows you how.

## Scoping Your Entities

In your service layer, your entities must have a `groupId` attribute to enable 
scoping by site. Using 
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder) 
is the simplest way to do this. For instructions on this, see the tutorial 
[Defining an Object-Relational Map with Service Builder](/develop/tutorials/-/knowledge_base/7-0/defining-an-object-relational-map-with-service-builder). 

## Enabling Scoping

To enable scoping in your app, set the property 
`"com.liferay.portlet.scopeable=true"` in your portlet class's `@Component` 
annotation. For example, this property is set to `true` in the `@Component` 
annotation of 
[Web Content Display Portlet's portlet class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/journal/journal-content-web/src/main/java/com/liferay/journal/content/web/internal/portlet/JournalContentPortlet.java): 

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

That's it! You can now access your app's scope in your code. The next section 
shows you how. 

## Accessing Your App's Scope

Your app's current scope matches the `groupId` of the site the user places your 
app on. You can access this several ways. 

1. Via the `scopeGroupId` variable in your JSPs. This variable is injected in 
   your JSPs whenever you use the `<liferay-theme:defineObjects />` tag. For 
   example, the Bookmarks app in @product@ uses `scopeGroupId` in 
   [its `view.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-web/src/main/resources/META-INF/resources/bookmarks/view.jsp) 
   to retrieve the current site's bookmarks and total number of bookmarks:
   
        ...
        total = BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId);
        
        bookmarksSearchContainer.setTotal(total);
        bookmarksSearchContainer.setResults(BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, bookmarksSearchContainer.getStart(), bookmarksSearchContainer.getEnd()));
        ...

2. By calling the `getScopeGroupId()` method on the 
   [`ThemeDisplay` instance](https://docs.liferay.com/portal/7.0-ga3/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html) 
   in the request. For example, the 
   [`EditEntryMVCActionCommand` class](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/blogs/blogs-web/src/main/java/com/liferay/blogs/web/internal/portlet/action/EditEntryMVCActionCommand.java) 
   in Liferay's Blogs app does this in its `subscribe` and `unsubscribe` 
   methods: 

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

    Note that if your app is scoped to a page, you can use 
    `themeDisplay.getSiteGroupId()` to get the parent site's `groupId`. 

3. By calling the `getScopeGroupId()` method on a `ServiceContext` object. You 
   can find more information on this, and an example, in the tutorial 
   [Understanding `ServiceContext`](/develop/tutorials/-/knowledge_base/7-0/understanding-servicecontext). 

Great! Now you know how to scope your apps. 

## Related Topics

[Application Scope](/discover/portal/-/knowledge_base/7-0/application-scope)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)

[Business Logic with Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-with-service-builder)
