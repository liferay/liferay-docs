# Upgrading Service Builder

@product-ver@ continues to use 
[Service Builder](/docs/appdev/7-2/-/knowledge_base/a/service-builder), so
you can focus on your application's business logic instead of its persistence
details. It still generates model classes, local and remote services, and
persistence. 

Upgrading most Service Builder portlets involves these steps: 

1.  [Adapt the code to @product-ver@'s API](step-1-adapt-the-code-to-product-vers-api)
2.  [Resolve dependencies](step-2-resolve-dependencies)
3.  [Build the services](step-3-build-the-services)

Start by adapting the code. 

## Step 1: Adapt the Code to @product-ver@'s API

Adapt the portlet to @product-ver@'s API using the Code Upgrade Planner. When
running the planner's *Fix Upgrade Problems* step, many of the existing issues
are autocorrected. For remaining issues, the planner identifies code affected by
the new API and ways to adapt it.

For example, consider an example portlet with the following compilation error:

```bash
/html/guestbook/view.jsp(58,1) PWC6131: Attribute total invalid for tag search-container-results according to TLD
```

The `view.jsp` file specifies a tag library attribute `total` that doesn't exist
in @product-ver@'s `liferay-ui` tag library. Notice the second attribute
`total`. 

```
<liferay-ui:search-container-results
    results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                    guestbookId, searchContainer.getStart(),
                    searchContainer.getEnd())%>"
    total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                    guestbookId)%>" />
```

Remove the `total` attribute assignment to make the tag like this:

```
<liferay-ui:search-container-results
    results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                    guestbookId, searchContainer.getStart(),
                    searchContainer.getEnd())%>" />
```

Resolve these error types and others until your code is adapted to the new API. 

## Step 2: Resolve Dependencies

To adapt your app's dependencies, refer to the
[Resolving a Project's Dependencies](/docs/tutorials/7-2/-/knowledge_base/t/resolving-a-projects-dependencies)
tutorial. Once your dependencies are upgraded, rebuild your services!

## Step 3: Build the Services

To rebuild your portlet's services, see the
[Running Service Builder](/docs/appdev/7-2/-/knowledge_base/a/running-service-builder)
article.

An example change where upgrading legacy Service Builder code can produce
differing results is explained below.

A Liferay Portal 6.2 portlet's `service.xml` file specifies exception class
names in `exception` elements like this:

```xml
<service-builder package-path="com.liferay.docs.guestbook">
    ...
    <exceptions>
        <exception>GuestbookName</exception>
        <exception>EntryName</exception>
        <exception>EntryMessage</exception>
        <exception>EntryEmail</exception>
    </exceptions>
</service-builder>
```

In Liferay Portal 6.2, Service Builder generates exception classes to the path
attribute `package-path` specifies. In @product-ver@, Service Builder generates
them to `[package-path]/exception`. 

Old path:

```
[package-path]
```

New path:

```
[package-path]/exception 
```

For example, the example portlet's package path is
`com.liferay.docs.guestbook`. Its exception class for `exception` element
`GuestbookName` is generated to
`docroot/WEB-INF/service/com/liferay/docs/guestbook/exception`. Classes that use
the exception must import
`com.liferay.docs.guestbook.exception.GuestbookNameException`.

Update references to your portlet's exception classes. 

Once your Service Builder portlet is upgraded,
[deploy it](/docs/reference/7-2/-/knowledge_base/r/deploying-a-project).

| **Note:** Service Builder portlets automatically migrated to Liferay Workspace
| using the Code Upgrade Planner or Blade CLI's `convert` command automatically
| has its Service Builder logic converted to API and implementation modules.
| This is a best practice for @product-ver@.

The portlet is now available on @product@. Congratulations on upgrading a
portlet that uses Service Builder!
