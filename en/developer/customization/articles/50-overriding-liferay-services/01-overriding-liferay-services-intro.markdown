---
header-id: overriding-service-builder-services-service-wrappers
---

# Overriding Liferay Services (Service Wrappers)

[TOC levels=1-4]

Why might you need to customize Liferay services? Perhaps you've added a new 
field to Liferay's `User` object and you want its value to be saved whenever the 
`addUser` or `updateUser` methods of Liferay's API are called. Or maybe you want 
to add some additional logging functionality to some Liferay APIs or other 
services built using [Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder). 
Whatever your case may be, Liferay's service wrappers provide easy-to-use 
extension points for customizing Liferay's services. 

To create a module that overrides one of Liferay's services, use [Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) 
to create a `servicewrapper` project type with the command below (replace the 
class and package names with your own):

```bash
blade create -t service-wrapper -p com.liferay.docs.serviceoverride 
-c UserLocalServiceOverride -s 
com.liferay.portal.kernel.service.UserLocalServiceWrapper service-override
``` 

As an example, here's the `UserLocalServiceOverride` class that's generated with 
the Service Wrapper Template:

```java
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
```

Notice that you must specify the fully qualified class name of the service 
wrapper class that you want to extend. The `service` argument was used in full 
in this import statement:

```java
import com.liferay.portal.service.UserLocalServiceWrapper;
```

This import statement, in turn, allowed the short form of the service wrapper 
class name to be used in the class declaration of your component class:

```java
public class UserLocalServiceOverride extends UserLocalServiceWrapper {...}
```

The bottom line is that when using `blade create` to create a service wrapper 
project, you must specify a fully qualified class name as the `service` 
argument. (This is also true when using `blade create` to create a service 
project.) For information about creating service projects, please see 
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder). 

The generated `UserLocalServiceOverride` class does not actually customize any 
Liferay service. Before you can test that your service wrapper module actually 
works, you need to override at least one service method. 

Open your `UserLocalServiceOverride` class and add the following methods:

```java
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
```

Each of these methods overrides a Liferay service method. These implementations 
merely execute a few print statements that before executing the original service 
implementations. 

Lastly, you must add the following method to the bottom of your service wrapper 
so it can find the appropriate service it's overriding on deployment. 

```java
@Reference(unbind = "-")
private void serviceSetter(UserLocalService userLocalService) {
    setWrappedService(userLocalService);
}
```

[Build and deploy your module](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project). 
Congratulations! You've created and deployed a Liferay service wrapper! 

## Related Topics

- [Upgrading Service Wrappers](/docs/7-2/tutorials/-/knowledge_base/t/upgrading-service-wrapper-hooks)
- [Installing Blade CLI](/docs/7-2/reference/-/knowledge_base/r/installing-blade-cli)
- [Creating Projects with Blade CLI](/docs/7-1/tutorials/-/knowledge_base/t/creating-projects-with-blade-cli)
