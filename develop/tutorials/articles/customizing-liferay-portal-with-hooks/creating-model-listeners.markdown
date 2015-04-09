# Listening for Events on Service Builder Models

Model Listeners are used to *listen* for events on *models*, and do something
in response. They're similar in concept to 
[custom action hooks](develop/tutorials/-/knowledge_base/6-2/performing-a-custom-action-using-a-hook),
which perform an action in response to a portal event (user login, for
example). Object models built by Service Builder are called *entities*, and
their data lives in a database table. Model listeners implement the
[`ModelListener`](https://docs.liferay.com/portal/6.2/javadocs-all/com/liferay/portal/model/ModelListener.html)
interface, which provides lots of opportunity to listen for model events.

The remainder of the tutorial shows the steps necessary to create model
listener hooks, using the portal's `User` entity for illustrative purposes.

## Developing the Model Listener

First, create a hook plugin project in a 
[Liferay Plugins SDK](develop/tutorials/-/knowledge_base/6-2/creating-a-hook-project-in-the-plugins-sdk)
, or with 
[Maven](develop/tutorials/-/knowledge_base/6-2/developing-liferay-hook-plugins-with-maven).

Once you have a hook plugin, there are a few steps involved with creating a
model listener hook:

- Writing a `-ModelListener` class that implements the `ModelListener` interface
- Specifying which model the `-ModelListener` should listen for in a
  `portal.properties` file
- Adding the `portal.properties` designation in `liferay-hook.xml`

Open `docroot/WEB-INF/liferay-hook.xml`, and add a `<portal-properties>`
element inside the `<hook>` tags: 

    <portal-properties>portal.properties</portal-properties>

Create a `-ModelListener` class that extends `BaseModelListener`. You can
implement the `ModelListener` interface directly, but then you need to provide
implementations for all its methods. By extending `BaseModelListener`, an
abstract class containing implementations for the interface methods, you just
need to worry about the methods you're customizing.


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
the idea. You have the ability to respond to actions occurring on portal
entities by creating model listners.

The `ModelListener` interface provides lots of opportunity to listen for model
events:

`onAfterAddAssociation`
`onAfterCreate`
`onAfterRemove`
`onAfterRemoveAssociation`
`onAfterUpdate`
`onBeforeAddAssociation`
`onBeforeCreate`
`onBeforeRemove`
`onBeforeRemoveAssociation`
`onBeforeUpdate`

You have your class created, but Liferay doesn't yet know what you want to do
with it. Next, create a `portal.properties` file under `docroot/WEB-INF/src`.
Specify which model to create a listener on in a `value.object.listener` property,
using this pattern:

    value.object.listener.fully.qualified.class.Name=fully.qualified.MyModelListener

The property Specifying the `MyUserListener` model listener class illustrated
above looks like this: 

    value.object.listener.com.liferay.portal.model.User=com.liferay.samples.hooks.MyUserListener

## Additional Model Listener Considerations

If you look into the *Value Object* section of Liferay's
[`portal.propertties`](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Value%20Object)
file, you'll see that there's already a `UserListener` class. Among other
things, it includes logic for exporting users to LDAP. Adding another model
listener on the `User` entity won't override the portal's `UserListener`.
Instead, it will run your code after the portal's `UserListener` is run. The
same is true for any of the other models listed in `portal.properties`.
To override one of the portal's model listeners, set the object model's
`value.object.listener` property to be blank in the portal's
`portal-ext.properties` file. Here's how it looks for the `User` entity:

    value.object.listener.com.liferay.portal.model.User=

You can create model listeners for any of the portal's object models, using the
same process outlined above. If there is no existing listener on the model, your
model listener will simply be the only one that is run.

+$$$

**Important:** Since portal properties like `value.object.listener` accept
*multiple* values, you must append your values to the existing values. You can
repeatedly modify the properties from additional hooks, but the order in which
the listeners run cannot be controlled. 

Take care to modify a portal property that accepts a single value from a single
hook plugin. If the property's value is overridden from multiple plugins,
Liferay won't know which value to use. 

$$$


<!--If you
want to do something additional after listening for an event on the `User`
entity, reserve the current listener capabilities, include your own
`-UserListener` in addition to the current one, rather than overriding it
outright. If you need to, you can implement your own `-UserListener` in place
of the portal's. In the portal.properties file of your hook, just sepcify the
values of the property in a comma separated list. CONFIRM THIS BOLOGNA -->

to take 
Model Listener hooks are created by modifying `liferay-hook.xml`, then creating
a `portal.properties` file and a `-ModelListener` class with the code you want
or run in response to the model event.

Steps for creating a Model Listener in Liferay:

What is a Model Listener Hook? Model Listener Hooks let you hook into a Service
Builder Model's events and do something.

There are several methods you can implement from the `ModelListener` Interface,
and your code can run before the Model event or after. Here's the list of
methods:

 
    public interface ModelListener<T> {

        public void onAfterAddAssociation(
                Object classPK, String associationClassName,
                Object associationClassPK)
            throws ModelListenerException;

        public void onAfterCreate(T model) throws ModelListenerException;

        public void onAfterRemove(T model) throws ModelListenerException;

        public void onAfterRemoveAssociation(
                Object classPK, String associationClassName,
                Object associationClassPK)
            throws ModelListenerException;

        public void onAfterUpdate(T model) throws ModelListenerException;

        public void onBeforeAddAssociation(
                Object classPK, String associationClassName,
                Object associationClassPK)
            throws ModelListenerException;

        public void onBeforeCreate(T model) throws ModelListenerException;

        public void onBeforeRemove(T model) throws ModelListenerException;

        public void onBeforeRemoveAssociation(
                Object classPK, String associationClassName,
                Object associationClassPK)
            throws ModelListenerException;

        public void onBeforeUpdate(T model) throws ModelListenerException;

    }


There's already a `UserListener` class. Among other things, it includes logic
for exporting Users to LDAP. Adding another model listener on the `User` entity
won't override the portal's `UserListener`.  Instead, it will run your code
after the portal's `UserListener` is run. If you want to do something
additional after listening for an event on the `User` entity, reserve the
current listener capabilities, include your own `-UserListener` in addition to
the current one, rather than overriding it outright. If you need to, you can
implement your own `-UserListener` in place of the portal's. In the
portal.properties file of your hook, just sepcify the values of the property in
a comma separated list.

public class UserListener extends BaseModelListener<User> {

	@Override
	public void onAfterAddAssociation(
			Object classPK, String associationClassName,
			Object associationClassPK)
		throws ModelListenerException {

		try {
			if (associationClassName.equals(Group.class.getName())) {
				long userId = ((Long)classPK).longValue();
				long groupId = ((Long)associationClassPK).longValue();

				updateMembershipRequestStatus(userId, groupId);
			}
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterCreate(User user) throws ModelListenerException {
		try {
			exportToLDAP(user);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onAfterUpdate(User user) throws ModelListenerException {
		try {
			exportToLDAP(user);
		}
		catch (Exception e) {
			throw new ModelListenerException(e);
		}
	}

	@Override
	public void onBeforeUpdate(User user) {
		UserModelImpl userModelImpl = (UserModelImpl)user;

		LDAPUserTransactionThreadLocal.setOriginalEmailAddress(
			userModelImpl.getOriginalEmailAddress());
	}

	protected void exportToLDAP(User user) throws Exception {
		if (user.isDefaultUser() ||
			LDAPUserTransactionThreadLocal.isOriginatesFromLDAP()) {

			return;
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Map<String, Serializable> expandoBridgeAttributes = null;

		if (serviceContext != null) {
			expandoBridgeAttributes =
				serviceContext.getExpandoBridgeAttributes();
		}

		PortalLDAPExporterUtil.exportToLDAP(user, expandoBridgeAttributes);
	}

	protected void updateMembershipRequestStatus(long userId, long groupId)
		throws Exception {

		long principalUserId = GetterUtil.getLong(
			PrincipalThreadLocal.getName());

		User user = UserLocalServiceUtil.getUser(userId);

		List<MembershipRequest> membershipRequests =
			MembershipRequestLocalServiceUtil.getMembershipRequests(
				userId, groupId, MembershipRequestConstants.STATUS_PENDING);

		for (MembershipRequest membershipRequest : membershipRequests) {
			MembershipRequestLocalServiceUtil.updateStatus(
				principalUserId, membershipRequest.getMembershipRequestId(),
				LanguageUtil.get(
					user.getLocale(), "your-membership-has-been-approved"),
				MembershipRequestConstants.STATUS_APPROVED, false,
				new ServiceContext());
		}
	}

}

1. Create a new hook project, with a *Portal properties* hook configuration, in IDE
2. Under *Specify properties to override:*, use the property selector to choose
the listener you want to override (or add?), such as
`value.object.listener.*`
3. Make sure you specify the exact listener you want to override:
`value.object.listener.com.liferay.portal.model.user`
4. Provide the fully qualified class name for your listener class:
`com.liferay.sample.hooks.MyUserListener`
5. Create the package and class under `docroot/WEB-INF/src`
6. The class should look something like this:

    package com.liferay.samples.hooks;

    import com.liferay.portal.ModelListenerException;
    import com.liferay.portal.model.BaseModelListener;
    import com.liferay.portal.model.User;

    public class MyUserListener extends BaseModelListener<User> {
        
        @Override
        public void onAfterCreate(User user) throws ModelListenerException {
            System.out.println(user.getFullName());
        }
        
        @Override
        public void onAfterUpdate(User user) throws ModelListenerException {
            System.out.println(user.getFullName());
        }
    }

7. Deploy and add a user.

