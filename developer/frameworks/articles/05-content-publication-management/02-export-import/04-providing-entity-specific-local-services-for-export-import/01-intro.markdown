---
header-id: providing-entity-specific-local-services-for-export-import
---

# Providing Entity-Specific Local Services for Export/Import

[TOC levels=1-4]

Data handlers must often call your app's local services to perform
Export/Import-related tasks for its entities. When the Export/Import framework
operates on entities (i.e., staged models), it often cannot manage important
information from the entity's local services alone. The *Staged Model
Repository* framework removes this barrier by linking an app's staged model to a
local service. This gives you access to entity-specific methods tailored
specifically for the staged model data you're handling.

What kind of *entity-specific* methods are we talking about here? Your data
handlers only expose a specific set of actions, like export and import methods.
The Staged Model Repository framework provides CRUD operations for a specific
staged model that local services don't expose.

The staged model repository does not avoid using your app's local services. It
only provides an additional layer that provides Export/Import-specific
functionality. Here's how this works: 

- `*StagedModelDataHandler`: de-serializes the provided
  [LAR file's](/docs/7-2/reference/-/knowledge_base/r/liferay-archive-file)
  XML into a model.
- `*StagedModelRepository`: updates the model based on the environment and
  business logic, providing entity-specific CRUD operations for Staging purposes
  (e.g., UUID manipulation).
- Local services are called from the `*StagedModelRepository` and handle the
  remainder of the process.

Pretty cool, right? The first thing you must do is implement the
`StagedModelRepository` interface. You'll explore this next.

## Understanding the `StagedModelRepository` Interface

Providing specialized local services for your app's staging functionality lets
you abstract the additional staging-specific information away from your data
handlers. Before you can begin using the Staged Model Repository framework in
your app, you must implement it.

The first important task is adding an `@Component` annotation section above the
implementation class's declaration. This registers the class as a staged model
repository in the OSGi service registry. There are a few annotation attributes
you should set:

- `immediate`: activates the component immediately once its provided module has
  started.
- `property`: sets various properties for the component service. You must
  associate the model class you wish to handle with this service so it's
  recognized by the data handlers leveraging it. This property must set the
  fully qualified model class name like this: `property =
  "model.class.name=FULLY_QUALIFIED_MODEL_CLASS"`.
- `service`: points to the `StagedModelRepository.class` interface.

Next, you must implement the `StagedModelRepository` interface. Implementations
vary greatly based on the app you're implementing it for. There are two common
cases you'll experience when implementing its methods:

- Methods that require additional Export/Import information injected before
  calling the local service.
- Methods that can call the local service directly.

The
[`BookmarksEntryStagedModelRepository.addStagedModel(...)`](https://github.com/liferay/liferay-portal/blob/7.2.0-a1/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/internal/exportimport/staged/model/repository/BookmarksEntryStagedModelRepository.java#L51-L71)
method is an example where only calling the local service would not satisfy the
staged model data handler's needs (i.e., its UUID requirement). With the staged
model repository layer, however, you can add export/import specific requirements
on top of the present local services to serve your data handlers' needs.

The method does this: 

- Sets the user ID and service context based on the
  [`PortletDataContext`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/exportimport/kernel/lar/PortletDataContext.html)
  (used to populate the LAR file with your application's data during the
  export process).
- Sets the UUID, which is required to differentiate staged content between
  Sites.
- Calls the entity's local service.

Not every method implementation requires additional export/import information.
For example, deleting Bookmarks Entries and deleting Bookmarks Entry staged
models are functionally the same, so your staged model repository's method would
call the local service directly (e.g.,
[`BookmarksEntryStagedModelRepository.deleteStagedModel(...)`](https://github.com/liferay/liferay-portal/blob/7.2.0-a1/modules/apps/bookmarks/bookmarks-service/src/main/java/com/liferay/bookmarks/internal/exportimport/staged/model/repository/BookmarksEntryStagedModelRepository.java#L73-L78)).

Next you'll learn about using a Staged Model Repository.

## Using a Staged Model Repository

You can leverage a staged model repository by

1.  Creating a getter and setter method to make a `StagedModelRepository` object
    available to your entity.

2.  Calling the `StagedModelRepository` object to leverage its specialized
    export/import logic.

The getter and setter methods instantiate a `StagedModelRepository` object that
the staged model data handler can use to access your entity's CRUD operations.
The setter method should have an `@Reference` annotation listed above its method
signature. This injects the component service of the `*StagedModelRepository`
into the staged model repository object. The component service was created when
you set the `@Component` annotation in the implementation class.

Once you have access to the `StagedModelRepository` object, call it to use its
specialized export/import logic. Now that you have access to CRUD operations via
the `StagedModelRepository` object, you can skip the headache of providing
a slew of parameters and additional functionality in the local service to do
simple things like add a Bookmarks entry. The staged model repository abstracts
these requirements away from the data handler.

Continue in the section to learn how to develop staged model repositories for
your app.
