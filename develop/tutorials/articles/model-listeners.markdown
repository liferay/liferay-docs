# Model Listeners

Model Listeners implement the
[`ModelListener`](https://docs.liferay.com/portal/7.0/javadocs/portal-kernel/com/liferay/portal/kernel/model/ModelListener.html)
interface. They are used to listen for persistence events on models and do
something in response (either bfore or after the event).

Model listeners were designed to perform lightweight actions in response to a
`create`, `remove`, or `update` attempt on an entity's database table or a
mapping table (for example, `users_roles`). Here are some supported use cases:

-  Audit Listener: In a separate database, record information about updates to
   an entity's database table.
-  Cache Clearing Listener: Clear caches that you've added to improve the performance of custom code.
-  Validation Listener: Perform additional validation on a model's attribute
   values before they are persisted to the database.
-  Entity Update Listener: Do some additional processing when an entity table is
   updated. For example, notify users when changes are made to their account.

There are also use cases that are not recommended, since they're likely to break
unpredictably and give you headaches:

-  Setting a model's attributes in an `onBeforeUpdate` call. If some other
   database table has already been updated with the values before your model
listener is invoked, your data will get out of sync. To change how an entity's
attributes are set, consider using a [service wrapper](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services-service-wrappers)
instead.
-  Wrapping a model. Model listeners are not called when fetching records from
   the database.
-  Creating worker threads to do parallel processing and querying data you
   updated via your listener. Model listeners are called *before* the database
transaction is complete (even the `onAfter...` methods), so the queries could be
executed before the database transaction is completed.

If there is no existing listener on the model, your model listener is the only
one that runs. However, there can be multiple listeners on a single model, and
the order in which the listeners run cannot be controlled. 

You can create a model listener in a module by doing two simple things:

-  Implement `ModelListener` 
-  Register the service in Liferay's OSGi runtime

## Creating a Model Listener Class [](id=creating-a-modellistener-class)

Create a `-ModelListener` class that extends `BaseModelListener`. 

    package com.liferay.docs.modellistener;

    import com.liferay.portal.ModelListenerException;
    import com.liferay.portal.model.BaseModelListener;
    import com.liferay.portal.model.User;

    public class MyUserListener extends BaseModelListener<User> {
        
        @Override
        public void onAfterCreate(User user) throws ModelListenerException {
            System.out.println(user.getFullName());
        }
        
        public void onAfterUpdate(User user) throws ModelListenerException {
            System.out.println(user.getFullName());
        }
    }

You'll probably want to do something more interesting than print the user's
full name in your console after the user is added or updated, but this gives you
the idea. You can respond to actions occurring on portal entities by creating
model listeners.

## Register the Model Listener Service

Register the service with Liferay's OSGi runtime, setting `service=
ModelListener.class` and `immediate=true` in the Component.

    @Component(
        immediate = true,
        service = ModelListener.class
    )

That's all there is to preparing a model listener. Now learn what model events
you can respond to.

## Listening For Persistence Events

The `ModelListener` interface provides lots of opportunity to listen for model
events:

-  **`onAfterAddAssociation`:** If there's an association between two models (if
   they have a mapping table), use this method to do something after an
association record is added.
-  **`onAfterCreate`:** Use this method to do something after the persistence
   layer's `create` method is called.
-  **`onAfterRemove`:** Use this method to do something after the persistence
   layer's `remove` method is called.
-  **`onAfterRemoveAssociation`:** If there's an association between two models
   (if they have a mapping table), do something after an association record is
removed.
-  **`onAfterUpdate`:** Use this method to do something after the persistence
   layer's `update` method is called.
-  **`onBeforeAddAssociation`:** If there's an association between two models (if
   they have a mapping table), do something before an addition to the mapping
table.
-  **`onBeforeCreate`:** Use this method to do something before the persistence
   layer's `create` method is called.
-  **`onBeforeRemove`:** Use this method to do something before the persistence
   layer's `remove` method is called.
-  **`onBeforeRemoveAssociation`:** If there's an association between two models (if
   they have a mapping table), do something before a removal from the mapping
table.
-  **`onBeforeUpdate`:** Use this method to do something before the persistence
   layer's `update` method is called.

Look in Liferay's `BasePersistenceImpl`, particularly the `remove` and `update` methods,
and you'll see how model listeners are accounted for before (for the `onBefore...` case)
and after (for the `onAfter...` case) the model persistence event.

Now that you know how to create model listeners, keep in mind that they're
useful as standalone projects or inside of your application. What if your
application needs to do something (like add a custom entity) every time a User
is added in Liferay? You can include the model listener inside your application.
