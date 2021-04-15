---
header-id: invoking-liferay-services-from-scripts
---

# Invoking Liferay Services From Scripts

[TOC levels=1-4]

Many scripting scenarios require invoking @product@'s services.

To illustrate the correct syntax for interacting with Liferay services, consider
a simple example that uses the `UserLocalService` API to retrieve a list of
users and print their names to Liferay's log file. We'll initially
implement the example in Java pseudo-code:

    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.UserLocalServiceUtil;
    import java.util.List;

    ...
                
    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    }

    ...

@product@'s script engine only supports Groovy by default. In later versions,
support may be added for other scripting languages. 

## Groovy

Groovy is based on Java, and code written in Java also runs in Groovy. This
means we can execute the exact same code from our Java example without any
changes:

    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.UserLocalServiceUtil;
    import java.util.List;

    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    } 
 
Of course, we could make this somewhat Groovier by simplifying the program as
follows: 

    import com.liferay.portal.kernel.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)
    for (user in users){
        System.out.println("User Name: " + user.getFullName())
    }
 
Liferay's services can be easily accessed from the script console. Next, let's
look at some practical uses for @product@'s script engine.

## Related Topics

[Running Scripts From the Script Console](/docs/7-0/user/-/knowledge_base/u/running-scripts-from-the-script-console)

[Leveraging the Script Engine in Workflow](/docs/7-0/user/-/knowledge_base/u/leveraging-the-script-engine-in-workflow)

[Using Liferay's Script Engine](/docs/7-0/user/-/knowledge_base/u/using-liferays-script-engine)
