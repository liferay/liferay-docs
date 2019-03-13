# Adapting Your Business Logic to Build Staged Models [](id=adapting-your-business-logic-to-build-staged-models)

What if you don't want to extend your model with special attributes that may not
be needed in your business logic? In this case, you should adapt your business
logic to meet the Staging framework's needs. Liferay provides the
[ModelAdapterBuilder](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/model/adapter/builder/ModelAdapterBuilder.html)
framework, which lets you adapt your model classes to staged models.

As an example, assume you have an app that is fully developed and you want to
configure it to work with Staging. Your app, however, does not require a UUID
for any of its entities, and therefore, does not provide them. Instead of
configuring your app to handle UUIDs just for the sake of generating staged
models, you can leverage the Model Adapter Builder to build your staged models.

Another example for building staged models from scratch is for applications that
use REST services to access their attributes instead of the database. Since this
kind of app is developed to pull its attributes from a remote system, it would
be more convenient to build your staged models yourself instead of relying on
Service Builder, which is database driven.

To adapt your model classes to staged models, follow the steps outlined below:

1.  Create a `Staged[Entity]` interface, which extends the model specific
    interface (e.g., `[Entity]`) and the appropriate staged model interface
    (e.g., `StagedModel`). This class serves as the Staged Model Adapter.

2.  Create a `Staged[Entity]Impl` class that implements the `Staged[Entity]`
    interface and provides necessary logic for your entity model to be
    recognized as a staged model.

3.  Create a `Staged[Entity]ModelAdapterBuilder` class that implements
    `ModelAdapterBuilder<[Entity], Staged[Entity]>`. This class adapts the
    original model to the newly created Staged Model Adapter.

4.  Adapt your existing model and call one of the provided APIs to export or
    import the entity automatically.

![Figure 1: The Staged Model Adapter class extends your entity and staged model interfaces.](../../images/staged-model-adapter-diagram.png)

![Figure 2: The Model Adapter Builder gets an instance of the model and outputs a staged model.](../../images/model-adapter-builder-diagram.png)

To step through the process for leveraging the Model Adapter Builder for an
existing app, visit the
[Creating Staged Models Manually](/develop/tutorials/-/knowledge_base/7-1/creating-staged-models-manually)
tutorial.
