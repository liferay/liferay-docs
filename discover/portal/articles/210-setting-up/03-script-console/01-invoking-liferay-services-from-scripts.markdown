# Invoking Liferay Services From Scripts [](id=invoking-liferay-services-from-scripts)

Many scripting scenarios require invoking Liferay services.
[Liferay `*ServiceUtil` classes](https://docs.liferay.com/ce/portal/7.1-latest/javadocs/portal-kernel/)
are the fastest and most convenient way to invoke Liferay
services in the
[script console](/discover/portal/-/knowledge_base/7-1/running-scripts-from-the-script-console).
You can use Groovy to invoke Liferay services the same way you would use Java.
Furthermore, Groovy's syntax facilitates writing concise elegant scripts. 

This first example illustrates correct syntax for interacting with Liferay
services. It uses `UserLocalServiceUtil` to retrieve a list of users and print
their names to Liferay's log. To accomplish this, you could create and deploy a
module that has a Java class that has code like this:

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

Or you could use Groovy---which is based on Java---and do the whole thing right
from the
[script console](/discover/portal/-/knowledge_base/7-1/running-scripts-from-the-script-console)
with the same code:

    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.UserLocalServiceUtil;
    import java.util.List;

    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    } 
 
You can even make the code somewhat Groovier:

    import com.liferay.portal.kernel.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)
    for (user in users){
        System.out.println("User Name: " + user.getFullName())
    }

Groovy scripts that invoke Liferay services are easy to write and execute in the
script console. The script console is next. 

## Related Topics [](id=related-topics)

[Running Scripts From the Script Console](/discover/portal/-/knowledge_base/7-1/running-scripts-from-the-script-console)

[Leveraging the Script Engine in Workflow](/discover/portal/-/knowledge_base/7-1/leveraging-the-script-engine-in-workflow)

[Script Examples](/discover/portal/-/knowledge_base/7-1/script-examples)
