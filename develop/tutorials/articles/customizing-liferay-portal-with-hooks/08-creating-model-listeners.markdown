# Creating Model Listeners [](id=creating-model-listeners)

Model Listeners are used to listen for events on models and do something
in response. They're similar in concept to [custom action hooks](develop/tutorials/-/knowledge_base/6-2/performing-a-custom-action-using-a-hook),
which perform actions in response to portal events (user login, for
example). Model listeners implement the
[`ModelListener`](https://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portal/model/ModelListener.html)
interface.

There are a few steps required to create a model listener for a model included in
Liferay's core:

- Create a hook plugin project.
- Write a `-ModelListener` class that implements the `ModelListener` interface
- Specify which model the `-ModelListener` should listen for in a
  `portal.properties` file
- Add the `portal.properties` designation in `liferay-hook.xml`

The remainder of the tutorial shows these steps, using the portal's `User`
entity for illustrative purposes.

## Creating a Hook Plugin Project [](id=creating-a-hook-plugin-project)

First, create a hook plugin project in a [Liferay Plugins SDK](develop/tutorials/-/knowledge_base/6-2/creating-a-hook-project-in-the-plugins-sdk)
or with 
[Maven](develop/tutorials/-/knowledge_base/6-2/developing-liferay-hook-plugins-with-maven).

Once you have a hook plugin, start developing your model listener.

## Creating a `-ModelListener` Class [](id=creating-a-modellistener-class)

Create a `-ModelListener` class under the hook's `docroot/WEB-INF/src`
directory that extends `BaseModelListener`. You can implement the
`ModelListener` interface directly, but then you need to provide
implementations for all its methods. By extending `BaseModelListener` (an
abstract class containing default implementations for the interface methods),
you just need to worry about the methods you're customizing.


    package com.liferay.samples.hooks;

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

The `ModelListener` interface provides lots of opportunity to listen for model
events:

    onAfterAddAssociation
    onAfterCreate
    onAfterRemove
    onAfterRemoveAssociation
    onAfterUpdate
    onBeforeAddAssociation
    onBeforeCreate
    onBeforeRemove
    onBeforeRemoveAssociation
    onBeforeUpdate

After writing your listener class, there are some Liferay-specific actions you
need to take so the portal runs your code.

## Modifying `liferay-hook.xml` [](id=modifying-liferay-hook-xml)

Open `docroot/WEB-INF/liferay-hook.xml` and add a `<portal-properties>`
element inside the `<hook>` tags: 

    <portal-properties>portal.properties</portal-properties>

The last step is to add your listener class name to the correct property in a
`portal.properties` file.

## Adding the Model Listener in `portal.properties` [](id=adding-the-model-listener-in-portal-properties)

You have your class created, but Liferay doesn't know it exists yet. If you
don't yet have one, create a `portal.properties` file under
`docroot/WEB-INF/src`. Specify which model to listen for in a
`value.object.listener` property, using this pattern:

    value.object.listener.fully.qualified.class.Name=fully.qualified.MyModelListener

The property for the `MyUserListener` model listener class illustrated above
looks like this: 

    value.object.listener.com.liferay.portal.model.User=com.liferay.samples.hooks.MyUserListener

Your model listener is ready to be deployed and tested. There are a few
additional points for consideration.

## Additional Model Listener Considerations [](id=additional-model-listener-considerations)

If you look into the *Value Object* section of Liferay's
[`portal.propertties`](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Value%20Object)
file, you'll see that there's already a `UserListener` class. Among other
things, it includes logic for exporting users to an LDAP directory. Adding
another model listener on the `User` entity doesn't override the portal's
`UserListener`. Instead, it runs your code after the portal's `UserListener`
runs. The same is true for any of the other models listed in
`portal.properties`. To override one of the portal's model listeners, set the
model's `value.object.listener` property to be blank in the portal's
`portal-ext.properties` file and create a model listener hook as described in
this tutorial. If you were to clear it out, here's how the
`portal-ext.properties` entry would look for the `User` entity:

    value.object.listener.com.liferay.portal.model.User=

You can create model listeners for any of the portal's entities, using the
same process outlined above. If there is no existing listener on the model, your
model listener is the only one that runs.

+$$$

**Important:** Since portal properties like `value.object.listener` accept
multiple values, you must append your values to the existing values. You can
repeatedly modify the properties from additional hooks, but the order in which
the listeners run cannot be controlled. 

Only modify a portal property that accepts a single value from a single hook
plugin. If the property's value is overridden from multiple plugins, Liferay
won't know which value to use. 

$$$

Listening for events on Liferay's entities is just one of the many interesting
ways to customize Liferay's core functionality with hooks. Keep reading in 
[this section](develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal) to
learn about more of Liferay's extension points. 

## Related Topics [](id=related-topics)

[Overriding a Portal Service Using a Hook](develop/tutorials/-/knowledge_base/6-2/overriding-a-portal-service-using-a-hook)

[Performing a Custom Action Using a Hook](develop/tutorials/-/knowledge_base/6-2/performing-a-custom-action-using-a-hook)

[Service Builder and Services](develop/tutorials/-/knowledge_base/6-2/service-builder)
