# Data Scopes [](id=data-scopes)

Apps in @product@ can restrict their data to specific 
[scopes](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/scope).
Scopes provide a context for the application's data. For example, a site-scoped
app can display its data across a single site. For a detailed explanation of
scopes, see the user guide article 
[Application Scope](/discover/portal/-/knowledge_base/7-0/application-scope). 
To give your applications scope, you must manually add support for it. This
tutorial shows you how. 

## Scoping Your Entities [](id=scoping-your-entities)

In your service layer, your entities must have a `companyId` attribute of type 
`long` to enable scoping by portal instance and a `groupId` attribute of type 
`long` to enable scoping by site. Using 
[Service Builder](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder) 
is the simplest way to do this. For instructions on this, see the tutorial 
series 
[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence) 
and 
[Business Logic with Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-with-service-builder). 

## Enabling Scoping [](id=enabling-scoping)

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

## Accessing Your App's Scope [](id=accessing-your-apps-scope)

Users can typically set an app's scope to a page, a site, or the entire portal. 
To handle your app's data, you must access it in its current scope. @product@ 
gives you techniques to do this. Your app's scope is available: 

1. Via the `scopeGroupId` variable that is injected in your JSPs whenever you 
   use the `<liferay-theme:defineObjects />` tag. This variable contains your 
   app's current scope. For example, Liferay's Bookmarks app uses `scopeGroupId` 
   [in its `view.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/collaboration/bookmarks/bookmarks-web/src/main/resources/META-INF/resources/bookmarks/view.jsp) 
   to retrieve the bookmarks and total number of bookmarks in the current scope: 

        ...
        total = BookmarksEntryServiceUtil.getGroupEntriesCount(scopeGroupId, groupEntriesUserId);
        
        bookmarksSearchContainer.setTotal(total);
        bookmarksSearchContainer.setResults(BookmarksEntryServiceUtil.getGroupEntries(scopeGroupId, groupEntriesUserId, bookmarksSearchContainer.getStart(), bookmarksSearchContainer.getEnd()));
        ...

2. By calling the `getScopeGroupId()` method on the request's 
   [`ThemeDisplay` instance](https://docs.liferay.com/portal/7.0-ga3/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
   This method returns your app's current scope. For example, the 
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

    If you know your app always needs the portal instance ID, use 
    `themeDisplay.getCompanyId()`. 

3. By calling the `getScopeGroupId()` method on a `ServiceContext` object. You 
   can find more information on this, and an example, in the tutorial 
   [Understanding `ServiceContext`](/develop/tutorials/-/knowledge_base/7-0/understanding-servicecontext). 
   If you know your app always needs the portal instance ID, use the 
   `getCompanyId()` method on a `ServiceContext` object. 

Awesome! Now you know how to get your app's scope. Next, you'll learn about a 
special use case: getting the site scope for entities that belong to a different 
app. 

## Accessing the Site Scope Across Apps [](id=accessing-the-site-scope-across-apps)

There may be times when you need to access a different app's site-scoped data, 
even though your app may be scoped to a page or the portal. For example, web 
content articles can be created in the page, site, or portal scope. 
[Structures and Templates](/discover/portal/-/knowledge_base/7-0/designing-uniform-content) 
for such articles, however, can only exist in the site scope. If you use the 
above techniques to retrieve the app's current scope, you won't always get the 
site scope required for Structures and Templates. You might get the page or 
portal scope instead, if that's how the user configured your app. What a pickle! 
Never fear, the `ThemeDisplay` method `getSiteGroupId()` is here! This method 
always gets the site scope, no matter your app's current scope. For example, 
[the Web Content app's `edit_feed.jsp`](https://github.com/liferay/liferay-portal/blob/7.0.x/modules/apps/web-experience/journal/journal-web/src/main/resources/META-INF/resources/edit_feed.jsp#L40) 
uses this method to get the site ID needed to retrieve Structures: 

    ...

    ddmStructure = DDMStructureLocalServiceUtil.fetchStructure(themeDisplay.getSiteGroupId(), 
        PortalUtil.getClassNameId(JournalArticle.class), ddmStructureKey, true);

    ...

Great! Now you know how to scope your apps, access their scope, and even get the 
site scope of entities that belong to other apps. Now that's minty-fresh breath! 

## Related Topics [](id=related-topics)

[Application Scope](/discover/portal/-/knowledge_base/7-0/application-scope)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Service Builder Persistence](/develop/tutorials/-/knowledge_base/7-0/service-builder-persistence)

[Business Logic with Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-with-service-builder)
