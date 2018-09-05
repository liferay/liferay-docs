# Providing Entity-Specific Local Services for Staging

When creating your data handlers, you must leverage your app's local services to
perform Staging-related tasks for its entities. When the Staging framework
operates on entities (i.e., staged models), it often cannot manage important
information from the entity's local services alone; instead, you're forced to
reinvent basic functionality so the Staging framework can access it. This is
caused by services not sharing a common ancestor (i.e., interface or base
class).

The *Staged Model Repository* framework removes this barrier by linking an app's
staged model to a local service. This lets the Staging framework call a
staged model repository independently based on which entity is currently being
processed. This gives you access to entity-specific methods tailored
specifically for the staged model data you're handling.

![Figure 1: Staged Model Repositories provide a Staging-specific layer of functionality for your local services.](../../../images/staged-model-repository.png)

So what kind of *entity-specific* methods are we talking about here? Your data
handlers only expose a specific set of actions, like export and import methods.
The Staged Model Repository framework provides CRUD operations for a specific
staged model that are not exposed using local services.

Pretty cool, right? Read on to learn how to implement and use the Staged Model
Repository framework in your app.