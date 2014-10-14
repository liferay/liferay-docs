# Service Context [](id=service-context)

The `ServiceContext` class is a parameter class used for passing contextual
information for a service. Using a parameter class lets you consolidate many
different methods with different sets of optional parameters into a single,
easier-to-use method. The class also aggregates information necessary for
transversal features, including permissioning, tagging, categorization, and
more. 

<!-- What's a transversal feature? I don't understand that term, and I'm
thinking we shouldn't just use it like this without explaining what it is. -Rich
-->

In this section we'll look at the Service Context fields, learn how to create
and populate a Service Context, and learn to access Service Context data.

First we'll look at the fields of the `ServiceContext` class.

## Service Context Fields [](id=service-context-fields)

The `ServiceContext` class has many fields. The best field descriptions are
found in the Javadoc: 

[http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html](http://docs.liferay.com/portal/6.1/javadocs-all/com/liferay/portal/service/ServiceContext.html).

Here we'll give you a helpful categorical listing of the fields: 

- Actions:
    - `_command`
    - `_workflowAction`
- Attributes:
    - `_attributes`
    - `_expandoBridgeAttributes`
- Classification: 
    - `_assetCategoryIds`
    - `_assetTagNames`
- IDs and Scope:
    - `_companyId`
    - `_portletPreferencesIds`
    - `_plid`
    - `_scopeGroupId`
    - `_userId`
    - `_uuid`
- Language:
    - `_languageId`
- Miscellaneous:
    - `_headers`
    - `_signedIn`
- Permissions:
    - `_addGroupPermissions`
    - `_addGuestPermissions`
    - `_deriveDefaultPermissions`
    - `_groupPermissions`
    - `_guestPermissions`
- Resources:
    - `_assetEntryVisible`
    - `_assetLinkEntryIds`
    - `_createDate`
    - `_indexingEnabled`
    - `_modifiedDate`
- URLs, paths and addresses:
    - `_currentURL`
    - `_layoutFullURL`
    - `_layoutURL`
    - `_pathMain`
    - `_portalURL`
    - `_remoteAddr`
    - `_remoteHost`
    - `_userDisplayURL`

Are you wondering how the `ServiceContext` fields get populated? Good! We'll
show you that next.

### Creating and Populating a Service Context  [](id=lp-6-1-dgen08-creating-and-populating-a-service-context--0)

Although all the `ServiceContext` class fields are optional, services that
store any type of content need the scope group ID specified, at least. Here's a
simple example of creating a `ServiceContext` instance and passing it as a
parameter to a service API using Java: 

        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setScopeGroupId(myGroupId);
        ...
        BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you invoke the service from a servlet, a Struts action or any other front
end class which has access to the `PortletRequest`, use one of the
`ServiceContextFactory.getInstance(...)` methods. These methods create the
`ServiceContext` object and automatically fill it with all necessary values.
The above example looks different if you invoke the service from a servlet: 

        ServiceContext serviceContext =
                ServiceContextFactory.getInstance(BlogsEntry.class.getName(),
                portletRequest);
        BlogsEntryServiceUtil.addEntry(..., serviceContext);

You can see an example of populating a `ServiceContext` with information from a
request object in the code of the `ServiceContextFactory.getInstance(...)`
methods. The methods demonstrate how to set parameters like *scope group ID*,
*company ID*, *language ID*, and more; they also demonstrate how to access and
populate more complex context parameters like *tags*, *categories*, *asset
links*, *headers*, and the *attributes* parameter. By calling
`ServiceContextFactory.getInstance(String className, PortletRequest
portletRequest)`, you can assure your expando bridge attributes are set on the
`ServiceContext`. 

Next let's see an example of accessing information from a `ServiceContext`. 

## Accessing Service Context data [](id=accessing-service-context-data)

We'll use code snippets from `BlogsEntryLocalServiceImpl.addEntry(...,
ServiceContext)` to show you how to access information from a `ServiceContext`
and comment on how the context information is being used. 

As we mentioned, your service needs a scope group ID from your `ServiceContext`.
The same holds true for the blogs entry service because the scope group ID
provides the scope of the blogs entry (the entity being persisted). For the
blogs entry, the scope group ID is used in the following way: 

- It's used as the groupId for the `BlogsEntry` entity.
- It's used to generate a unique URL for the blog entry.
- It's used to set the scope for comments on the blog entry.

Here are the corresponding code snippets:

        long groupId = serviceContext.getScopeGroupId();
        ...
        entry.setGroupId(groupId);
        ...
        entry.setUrlTitle(getUniqueUrlTitle(entryId, groupId, title));
        ...

        // Message boards

        if (PropsValues.BLOGS_ENTRY_COMMENTS_ENABLED) {
            mbMessageLocalService.addDiscussionMessage(
                userId, entry.getUserName(), groupId,
                BlogsEntry.class.getName(), entryId,
                WorkflowConstants.ACTION_PUBLISH);
        }

Can `ServiceContext` be used to access the UUID of the blog entry? Absolutely!
Can you use `ServiceContext` to set the time the blog entry was added? Sure you
can. See here: 

        entry.setUuid(serviceContext.getUuid());
        ...
        entry.setCreateDate(serviceContext.getCreateDate(now));

Can `ServiceContext` be used in setting permissions on resources? You bet! When
adding a blog entry, you can add new permissions or apply existing permissions
for the entry, like this: 

        // Resources

        if (serviceContext.isAddGroupPermissions() ||
            serviceContext.isAddGuestPermissions()) {

            addEntryResources(
                entry, serviceContext.isAddGroupPermissions(),
                serviceContext.isAddGuestPermissions());
        }
        else {
            addEntryResources(
                entry, serviceContext.getGroupPermissions(),
                serviceContext.getGuestPermissions());
        }

`ServiceContext` helps apply categories, tag names, and the link entry IDs to
asset entries too. 

        // Asset

        updateAsset(
            userId, entry, serviceContext.getAssetCategoryIds(),
            serviceContext.getAssetTagNames(),
            serviceContext.getAssetLinkEntryIds());

Does `ServiceContext` also play a role in starting a workflow instance for the
blogs entry? Must you Ask? 

        // Workflow

        if ((trackbacks != null) && (trackbacks.length > 0)) {
            serviceContext.setAttribute("trackbacks", trackbacks);
        }
        else {
            serviceContext.setAttribute("trackbacks", null);
        }

        WorkflowHandlerRegistryUtil.startWorkflowInstance(
            user.getCompanyId(), groupId, userId, BlogsEntry.class.getName(),
            entry.getEntryId(), entry, serviceContext);

The snippet above also demonstrates the `trackbacks` attribute, a standard
attribute for the blogs entry service. There may be cases where you need to pass
in custom attributes to your blogs entry service. Use Expando attributes to
carry custom attributes along in your `ServiceContext`. Expando attributes are
set on the added blogs entry like this: 

        entry.setExpandoBridgeAttributes(serviceContext);

You can see that the `ServiceContext` can be used to transfer lots of useful
information for your services. 

Let's look at Message Bus next. 
