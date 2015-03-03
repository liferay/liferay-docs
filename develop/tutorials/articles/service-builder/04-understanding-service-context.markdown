# ServiceContext

The `ServiceContext` class is a parameter class used for passing contextual
information for a service. Using a parameter class lets you consolidate many
different methods with different sets of optional parameters into a single,
easier-to-use method. The class also aggregates information necessary for
features used throughout Liferay's core portlets, such as permissions, tagging,
categorization, and more. 

In this section, you'll examine the Service Context fields, learn how to create
and populate a Service Context, and learn to access Service Context data. First,
let's look at the fields of the `ServiceContext` class.

## Service Context Fields

The `ServiceContext` class has many fields. The best field descriptions are
found in the Javadoc: 

[http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ServiceContext.html](http://docs.liferay.com/portal/6.2/javadocs/com/liferay/portal/service/ServiceContext.html).

Here, you can review a categorical listing of the fields: 

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

## Creating and Populating a Service Context

Although all the `ServiceContext` class fields are optional, services that store
any kind of scopeable data need to at least specify the scope group ID. Here's a
simple example of creating a `ServiceContext` instance and passing it as a
parameter to a Liferay service API using Java: 

    ServiceContext serviceContext = new ServiceContext();
    serviceContext.setScopeGroupId(myGroupId);

    ...

    BlogsEntryServiceUtil.addEntry(..., serviceContext);

If you invoke the service from a servlet, a Struts action, or any other
front-end end class which has access to the `PortletRequest`, use one of the
`ServiceContextFactory.getInstance(...)` methods. These methods create a
`ServiceContext` object from the request and automatically populate its fields
with all the values specified in the request. The above example looks different
if you invoke the service from a servlet: 

    ServiceContext serviceContext =
        ServiceContextFactory.getInstance(BlogsEntry.class.getName(), portletRequest);
    
    ...

    BlogsEntryServiceUtil.addEntry(..., serviceContext);

You can see an example of populating a `ServiceContext` with information from a
request object in the code of the `ServiceContextFactory.getInstance(...)`
methods. The methods demonstrate how to set parameters like *scope group ID*,
*company ID*, *language ID*, and more. They also demonstrate how to access and
populate more complex context parameters like *tags*, *categories*, *asset
links*, *headers*, and the *attributes* parameter. By calling
`ServiceContextFactory.getInstance(String className, PortletRequest
portletRequest)`, you can assure that your Expando bridge attributes are set on
the `ServiceContext`. Expandos are the back-end implementation of custom fields
for entities in Liferay.

Next, you'll learn how to access information from a `ServiceContext`. 

## Accessing Service Context Data

In this section, you'll find code snippets from
`BlogsEntryLocalServiceImpl.addEntry(..., ServiceContext)`. This code
demonstrates how to access information from a `ServiceContext` and provides an
example of how the context information can be used. 

As mentioned above, services for scopeable entities need to get a scope group ID
from the `ServiceContext` object. This is true for the Blogs entry service
because the scope group ID provides the scope of the Blogs entry (the entity
being persisted). For the Blogs entry, the scope group ID is used in the
following way: 

- It's used as the `groupId` for the `BlogsEntry` entity.
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
Can you use `ServiceContext` to set the time the blog entry was added? You sure
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
asset entries too. Asset links are the back-end term for related assets in
Liferay.

    // Asset

    updateAsset(
        userId, entry, serviceContext.getAssetCategoryIds(),
        serviceContext.getAssetTagNames(),
        serviceContext.getAssetLinkEntryIds());

Does `ServiceContext` also play a role in starting a workflow instance for the
blogs entry? Must you ask? 

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
information for your services. Understanding how `ServiceContext` is used in
Liferay helps you determine when and how to use `ServiceContext` in your own
Liferay application development.
