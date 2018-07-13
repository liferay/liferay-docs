# Invoking Liferay Services From Scripts [](id=invoking-liferay-services-from-scripts)

Many scripting scenarios require invoking @product@'s services.

To illustrate the correct syntax for interacting with Liferay services, consider
a simple example that uses the `UserLocalService` API to retrieve a list of
users and print their names to Liferay's log file. You could create and deploy 
a module written in Java to do this. Your module might look something like this:

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

Or you could use Groovy---which is based on Java---and do the whole thing right from the script console with the same code:

    import com.liferay.portal.kernel.model.User;
    import com.liferay.portal.kernel.service.UserLocalServiceUtil;
    import java.util.List;

    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    } 
 
You can even make this somewhat Groovier and simplify the program:

    import com.liferay.portal.kernel.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)
    for (user in users){
        System.out.println("User Name: " + user.getFullName())
    }
 
Liferay's services can be easily accessed from the script console. Next, let's
look at some practical uses for @product@'s script engine.

## Related Topics [](id=related-topics)

[Running Scripts From the Script Console](/discover/portal/-/knowledge_base/7-0/running-scripts-from-the-script-console)

[Leveraging the Script Engine in Workflow](/discover/portal/-/knowledge_base/7-0/leveraging-the-script-engine-in-workflow)

[Using Liferay's Script Engine](/discover/portal/-/knowledge_base/7-0/using-liferays-script-engine)
