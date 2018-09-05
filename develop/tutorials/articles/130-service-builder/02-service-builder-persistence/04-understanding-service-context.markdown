# Understanding ServiceContext [](id=understanding-servicecontext)

The `ServiceContext` class holds contextual information for a service. It
aggregates information necessary for features used throughout Liferay's
portlets, such as permissions, tagging, categorization, and more. This tutorial
covers the following `ServiceContext` class topics:

- [Service Context Fields](#service-context-fields)
- [Creating and Populating a Service Context in Java](#creating-and-populating-a-service-context)
- [Creating and Populating a Service Context in JavaScript](#creating-and-populating-a-service-context-in-javascript)
- [Accessing Service Context Data](#accessing-service-context-data)

The `ServiceContext` fields are first.

## Service Context Fields [](id=service-context-fields)

The `ServiceContext` class has many fields. The
[`ServiceContext` class Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html)
describes them. 

Here's a categorical listing of some commonly used Service Context fields: 

- Actions:
    - `_command`
    - `_workflowAction`
- Attributes:
    - `_attributes`
    - `_expandoBridgeAttributes`
- Classification: 
    - `_assetCategoryIds`
    - `_assetTagNames`
- Exception
    - `_failOnPortalException`
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
    - `_modelPermissions`
- Request
    - `_request`
- Resources:
    - `_assetEntryVisible`
    - `_assetLinkEntryIds`
    - `_assetPriority`
    - `_createDate`
    - `_formDate`
    - `_indexingEnabled`
    - `_modifiedDate`
    - `_timeZone`
- URLs, paths and addresses:
    - `_currentURL`
    - `_layoutFullURL`
    - `_layoutURL`
    - `_pathMain`
    - `_pathFriendlyURLPrivateGroup`
    - `_pathFriendlyURLPrivateUser`
    - `_pathFriendlyURLPublic`
    - `_portalURL`
    - `_remoteAddr`
    - `_remoteHost`
    - `_userDisplayURL`

Are you wondering how the `ServiceContext` fields get populated? Good! You'll
learn about that next. 

## Creating and Populating a Service Context [](id=creating-and-populating-a-service-context)

Although all the `ServiceContext` class fields are optional, services that store
data with scope must at least specify the scope group ID. Here's an example of
creating a `ServiceContext` instance and passing it as a parameter to a Liferay
service API: 

    ServiceContext serviceContext = new ServiceContext();
    serviceContext.setScopeGroupId(myGroupId);

    ...

    _blogsEntryService.addEntry(..., serviceContext);

If you invoke the service from a servlet, a Struts action, or any other
front-end class with access to the `PortletRequest`, use one of the
`ServiceContextFactory.getInstance(...)` methods. These methods create a
`ServiceContext` object from the request and automatically populate its fields
with all the values specified in the request. The above example looks different
if you invoke the service from a servlet: 

    ServiceContext serviceContext =
        ServiceContextFactory.getInstance(BlogsEntry.class.getName(), portletRequest);

    ...

    _blogsEntryService.addEntry(..., serviceContext);

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

## Creating and Populating a Service Context in JavaScript [](id=creating-and-populating-a-service-context-in-javascript)

Liferay's API can be invoked in languages other than Java. Some methods require
or allow a `ServiceContext` parameter. If you're invoking such a method via
Liferay's JSON web services, you might want to create and populate
a `ServiceContext` object in JavaScript. Creating a `ServiceContext` object in
JavaScript is no different from creating any other object in JavaScript.

Before examining a JSON web service invocation that uses a `ServiceContext`
object, it helps to see a simple JSON web service example in JavaScript:

    Liferay.Service(
        '/user/get-user-by-email-address`,
        {
            companyId: Liferay.ThemeDisplay.getCompanyId(),
            emailAddress: 'test@liferay.com`
        },
        function(obj) {
            console.log(obj);
        }
    );

If you run this code, the *test@liferay.com* user (JSON object) is logged to the
JavaScript console.

The `Liferay.Service(...)` function takes three arguments:

1. A string representing the service being invoked

2. A parameters object

3. A callback function

The callback function takes the result of the service invocation as an argument.

The Liferay JSON web services page (its URL is
[localhost:8080/api/jsonws](localhost:8080/api/jsonws) if you're running Liferay
locally on port 8080) generates example code for invoking web services. To see
the generated code for a particular service, click on the name of the service,
enter the required parameters, and click *Invoke*. The JSON result of your
service invocation appears. There are multiple ways to invoke Liferay's JSON web
services: click on *JavaScript Example* to see how to invoke the web service via
JavaScript, click on *curl Example* to see how to invoke the web service via
curl, or click on *URL example* to see how to invoke the web service via a URL.

![Figure 1: When you invoke a service from Liferay's JSON web services page, you can view the result of your service invocation as well as example code for invoking the service via JavaScript, curl, or URL.](../../../images/jsonws-simple-example.png) 

To learn more about Liferay's JSON web services, see the
[JSON Web Services](/develop/tutorials/-/knowledge_base/7-1/registering-json-web-services)
tutorial.

Next, you'll learn how to access information from a `ServiceContext` object. 

## Accessing Service Context Data [](id=accessing-service-context-data)

In this section, you'll find code snippets from
`BlogsEntryLocalServiceImpl.addEntry(..., ServiceContext)`. This code
demonstrates how to access information from a `ServiceContext` and provides an
example of how the context information can be used. 

As mentioned above, services for entities with scope must get a scope group ID
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

    _workflowHandlerRegistry.startWorkflowInstance(
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

## Related Topics [](id=related-topics)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-1/creating-local-services)

[Invoking Local Services](/develop/tutorials/-/knowledge_base/7-1/invoking-local-services)
