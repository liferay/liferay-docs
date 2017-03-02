# Customizing Liferay Services (Service Wrappers) [](id=customizing-liferay-services-service-wrappers)

Why might you need to customize Liferay services? Perhaps you've added a custom
field to Liferay's `User` object and you want its value to be saved whenever the
`addUser` or `updateUser` methods of Liferay's API are called. Or maybe you
want to add some additional logging functionality to some of Liferay's APIs.
Whatever your case may be, Liferay's service wrappers provide easy-to-use
extension points for customizing Liferay's services.

To create a module that overrides one of Liferay's services, follow the 
[Using the Service Wrapper Template](/develop/tutorials/-/knowledge_base/7-0/using-the-service-wrapper-template)
tutorial to create a `servicewrapper` project type.

As an example, here's the `UserLocalServiceOverride` class that's 
generated in the Service Wrapper Template tutorial:

    package com.liferay.docs.serviceoverride;

    import com.liferay.portal.kernel.service.UserLocalServiceWrapper;
    import com.liferay.portal.kernel.service.ServiceWrapper;
    import org.osgi.service.component.annotations.Component;

    @Component(
        immediate = true,
        property = {
        },
        service = ServiceWrapper.class
    )
    public class UserLocalServiceOverride extends UserLocalServiceWrapper {

        public UserLocalServiceOverride() {
            super(null);
        }

    }

Notice that you must specify the fully qualified class name of the service
wrapper class that you want to extend. The `service` argument was used in full
in this import statement:

    import com.liferay.portal.service.UserLocalServiceWrapper

This import statement, in turn, allowed the short form of the service wrapper
class name to be used in the class declaration of your component class:

    public class UserLocalServiceOverride extends UserLocalServiceWrapper

The bottom line is that when using `blade create` to create a service wrapper
project, you must specify a fully qualified class name as the `service`
argument. (This is also true when using `blade create` to create a service
project.) For information about creating service projects, please see the
[Business Logic and Data Access](/develop/tutorials/-/knowledge_base/7-0/business-logic-and-data-access)
tutorial.

The generated `UserLocalServiceOverride` class does not actually customize any
Liferay service. Before you can test that your service wrapper module actually
works, you need to override at least one service method.

Open your `UserLocalServiceOverride` class and add the following methods:

    @Override
    public int authenticateByEmailAddress(long companyId, String emailAddress,
            String password, Map<String, String[]> headerMap,
            Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
        throws PortalException {

        System.out.println(
            "Authenticating user by email address " + emailAddress);
        return super.authenticateByEmailAddress(companyId, emailAddress, password,
            headerMap, parameterMap, resultsMap);
    }

    @Override
    public User getUser(long userId) throws PortalException {
        System.out.println("Getting user by id " + userId);
        return super.getUser(userId);
    }

Each of these methods overrides a Liferay service method. These implementations
merely add a few print statements that are executed before the original service
implementations are invoked.

Now you're ready to build your project. Navigate to your project's root folder
and run `../../gradlew build`. The JAR file representing your portlet module is
produced in your project's `build/libs` directory.

To deploy your project, run this command from your project's root directory:

    blade deploy

Blade CLI detects your locally running Liferay instance and deploys the
specified module to Liferay's module framework. After running the `blade deploy`
command, you should see a message like this:

    Installed or updated bundle 334

Use the Gogo shell to confirm that your module was installed: Run `blade sh lb`
at the prompt. If your module was installed, you'll see an entry like this:

    335|Active     |    1|com.liferay.docs.serviceoverride (1.0.0.201502122109)

Finally, log into your portal as an administrator. Navigate to the Users section
of the Control Panel. Confirm that your customizations of Liferay's user service
methods have taken effect by checking Liferay's log for the print statements
that you added. Congratulations! You've created and deployed a @product-ver@ service
wrapper module!

## Related Topics [](id=related-topics)

[Installing Blade CLI](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-0/creating-modules-with-blade-cli)
