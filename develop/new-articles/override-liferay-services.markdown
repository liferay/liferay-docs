# Overriding Liferay's Services

Liferay Portal and its core modules offer a host of services that you can
programmatically invoke from local and remote clients. One only needs to look
at Portal’s service API or the APIs of its portlet bundles, like the Document
Library services API, to see that Liferay is chock-full of useful access
points. But you may encounter situations in which you want to modify the
behavior of these services.

To do this, you may be tempted to extend a service interface directly. But
there are problems inherent with this approach. Fix packs later added to the
product may modify the interface (e.g., adding a new method to the service). If
you’ve implemented the API directly, your implementation may not account for
the modified interface. As a result, the patch could break your customization
plugin. There's a much safer way to customize Liferay's services.

All the functionality provided by Liferay is enclosed in a layer of services
that are accessed by the controller layer in its portlets; this architecture
lets you change how a Liferay core portlet behaves without changing the portlet
itself. Liferay generates dummy wrapper classes for all its service interfaces.
For example, `UserLocalServiceWrapper` is created as a wrapper for
`UserLocalService`, a service interface for adding, removing, and retrieving user
accounts. If you extend the wrapper class, you can alter the service’s
behavior, and your customization can be safeguarded from being broken by any
future patches to the interface.

This tutorial shows you how to use a basic module to modify a portal service. There are only a few basic steps:

-Create a module
-Create a Declarative Services Component Class
-Override Liferay's Service
-Manage the Bundle's Dependencies and Metadata

Check out the tutorial on [creating modules for Liferay](URL TO JESSE'S
TUTORIAL) if you don't know how.

## Create a Declaratice Services Component Class

The `@Component` annotation is all that's necessary to mark a class as a Declarative Services Component. Following the annotation, specify the properties of the class. Here's how the annotation looks when you want your class to override a Liferay service:

    @Component(service = ServiceWrapper.class)

All that's necessary is to specify that this class publishes a `ServiceWrapper`
service. For Liferay, you'll always need to declare the type of service you're publishing
in a `service=` property in your Component class.

You'll notice that we specified `ServiceWrapper`, and not `Service`. While we
are indeed modifying a Liferay Service, we're doing so via its wrapper class, a
more stable approach than modifying the interface itself.

## Writing the Class

Here's an example of a class,
`UserlocalserviceModifier`, that includes overridden service methods from
`UserLocalServiceWrapper`:


    package blade.service.hook.user;

    import java.util.Map;

    import javax.mail.internet.AddressException;
    import javax.mail.internet.InternetAddress;

    import org.osgi.service.component.annotations.Component;

    import com.liferay.mail.service.MailServiceUtil;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.kernel.mail.MailMessage;
    import com.liferay.portal.model.User;
    import com.liferay.portal.service.ServiceWrapper;
    import com.liferay.portal.service.UserLocalServiceWrapper;

    @Component(service = ServiceWrapper.class)

    public class UserLocalServiceModifier extends UserLocalServiceWrapper {

        public UserLocalServiceHook() {
            super(null);
        }

        @Override
        public int authenticateByEmailAddress(long companyId, String emailAddress, String password,
                Map<String, String[]> headerMap, Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
                        throws PortalException {

            System.out.println("Authenticating user by email address " + emailAddress);
            return super.authenticateByEmailAddress(companyId, emailAddress, password, headerMap, parameterMap, resultsMap);
        }

        @Override
        public User getUser(long userId) throws PortalException {
            System.out.println("Getting user by id " + userId);
            return super.getUser(userId);
        }

        @Override
        public User deleteUser(long userId) throws PortalException, SystemException {

            User user = getUser(userId);

            String name = user.getScreenName();

            System.out.println("Deleting user " + name);

            String body = "The User with the email address " + user.getEmailAddress() + " has been deleted";

            try {
                MailMessage mailMessage = new MailMessage(new InternetAddress("test@liferay.com"),
                        new InternetAddress("test@liferay.com"), "Deleted", body, true);
                MailServiceUtil.sendEmail(mailMessage);
            } catch (AddressException addressException) {

            }

            return super.deleteUser(userId);
        }
    }

The Java packages and their classes in your bundle live in a `src` directory,
at a minimum (that's how the tutorial on creating a simple bundle have the
bundle structured). If you create your bundle using Eclipse's Gradle plugin,
*Eclipse Buildship*, you have a a `src/main/java` folder for your packages. The
class above is in the package `com.liferay.docs.serviceoverride`, in the
Eclipse *Source* folder.

[IMAGE OF ECLIPSE SOURCE FOLDER, src/main/java]

By extending the wrapper, you now are able to override any methods from
`UserLocalService`. The above example overrides `authenticateByEmailAddress`,
`getUser`, and `deleteUser`, printing messages in the log any time the methods
are called, and in the case of the `deleteUser` method, sending an email to the
email address `test@liferay.com` whenever the method is invoked. Obviously, do
what you want with these methods, as your needs dictate. Just note that if the
default method is still useful, you can call it in your override method by
specifying `super.originalMethod(parameter)`. Fro example, the override method
`deleteUser` still returns `super.deleteUser(userId)` after printing messages
to the log and sending an email.

## Manage Dependencies and Metadata

The bundle should have a `bnd.bnd` file in its root, that's used for populating
the `MANIFEST.MF` file of the bundle. This is extremely important, because it
dictates what's going to be included in your bundle. For the example provided
in this tutorial, it would look like this:

    -dsannotations: *
    -sources: true
    Bundle-Name: User Service Override Bundle
    Bundle-Version: 1.0.0.${tstamp}
    Service-Component: *
    Import-Package: \
        com.liferay.portal.model;version='[7.0,7.1)',\
        com.liferay.portal.service;version='[7.0,7.1)',\
        *

The `Bundle-Name` gives your bundle a more descriptive name, and
`Bundle-Version` will change each time the module is built, because of the
`${tstamp}`. Here's the output you might get after building the project,
deploying it to Liferay, and running `lb` in the GoGo Shell:

    User Service Override Bundle (1.0.0.201508111548)

The `-dsannotations` and `Service-Component` entries allow you to use OSGI
annotations and bnd annotations, respectively. There's no problem using both. 

`Import-Package` lists any packages that are required for the contained package. 


