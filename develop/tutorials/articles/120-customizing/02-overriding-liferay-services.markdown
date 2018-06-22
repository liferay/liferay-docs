# Overriding Liferay Services (Service Wrappers) [](id=customizing-liferay-services-service-wrappers)

Why might you need to customize Liferay services? Perhaps you've added a new
field to Liferay's `User` object and you want its value to be saved whenever the
`addUser` or `updateUser` methods of Liferay's API are called. Or maybe you want
to add some additional logging functionality to some Liferay APIs or other
services built using 
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder).
Whatever your case may be, Liferay's service wrappers provide easy-to-use
extension points for customizing Liferay's services.

To create a module that overrides one of Liferay's services, follow the 
[Service Wrapper Template](/develop/reference/-/knowledge_base/7-1/using-the-service-wrapper-template)
reference article to create a `servicewrapper` project type.

As an example, here's the `UserLocalServiceOverride` class that's  generated in
the Service Wrapper Template tutorial:

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
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)
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
merely execute a few print statements that before executing the original service
implementations.

[Build and deploy your module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#building-and-deploying-a-module).
Congratulations! You've created and deployed a Liferay service wrapper!

## Related Topics [](id=related-topics)

[Upgrading Service Wrappers](/develop/tutorials/-/knowledge_base/7-1/upgrading-service-wrappers)

[Installing Blade CLI](/develop/tutorials/-/knowledge_base/7-1/installing-blade-cli)

[Creating Modules with Blade CLI](/develop/tutorials/-/knowledge_base/7-1/creating-modules-with-blade-cli)
