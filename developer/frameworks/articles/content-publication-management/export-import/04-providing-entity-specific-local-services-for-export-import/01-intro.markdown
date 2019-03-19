---
header-id: providing-entity-specific-local-services-for-export-import
---

# Providing Entity-Specific Local Services for Export/Import

Data handlers often must leverage your app's local services to perform
Export/Import-related tasks for its entities. When the Export/Import framework
operates on entities (i.e., staged models), it often cannot manage important
information from the entity's local services alone. The *Staged Model
Repository* framework removes this barrier by linking an app's staged model to a
local service. This gives you access to entity-specific methods tailored
specifically for the staged model data you're handling.

What kind of *entity-specific* methods are we talking about here? Your data
handlers only expose a specific set of actions, like export and import methods.
The Staged Model Repository framework provides CRUD operations for a specific
staged model that are not exposed using local services.

The staged model repository does not avoid using your app's local services. It
only provides an additional layer that provides Export/Import-specific
functionality. So how does this work? A brief Staging process is outlined below:

- `*StagedModelDataHandler` de-serializes the provided
  [LAR file's](/develop/tutorials/-/knowledge_base/7-1/understanding-data-handlers#liferay-archive-lar-file)
  XML into a model.
- `*StagedModelRepository` updates the model based on the environment and
  business logic, providing entity-specific CRUD operations for Staging purposes
  (e.g., UUID manipulation).
- Local services are called from the `*StagedModelRepository` and handles the
  remainder of the process.

Pretty cool, right? Read on to learn how to implement and use the Staged Model
Repository framework in your app.
