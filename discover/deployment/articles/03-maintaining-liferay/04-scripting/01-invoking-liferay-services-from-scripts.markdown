# Invoking Liferay Services From Scripts [](id=invoking-liferay-services-from-scripts)

In many cases, you'll want to invoke one or more of Liferay's many services.
This is possible from each of the supported scripting languages. Of course, the
syntax is different for each language. 

To illustrate the correct syntax for interacting with Liferay services, let's
look at a simple example that uses Liferay's `UserLocalService` API to retrieve
a list of users and then prints their names to Liferay's log file. We'll
initially implement the example in Java pseudo-code:

    import com.liferay.portal.model.User;
    import com.liferay.portal.service.UserLocalServiceUtil;
    import java.util.List;

    ...
                
    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    }

    ...

Remember that @product@'s script engine only supports Groovy by default. If you
want to try out the non-Groovy examples below, you need to install the
appropriate modules:

- `portal-scripting-groovy` (installed by default)
- `portal-scripting-beanshell` (available from Liferay Marketplace)
- `portal-scripting-javascript` (available from Liferay Marketplace)
- `portal-scripting-python` (available from Liferay Marketplace)
- `portal-scripting-ruby` (available from Liferay Marketplace)

These modules are all freely available on
[Liferay Marketplace](www.liferay.com/marketplace).

First, let's see how this would work in Beanshell, which is very similar to
Java.
 
## Beanshell [](id=beanshell)

Beanshell is a Java scripting language that's designed to run Java code with
little or no changes. In this example, we only have one small change to make
because Beanshell doesn't support the use of Java Generics:

    import com.liferay.portal.model.User;
    import com.liferay.portal.service.UserLocalServiceUtil;
    import java.util.List;

    int userCount = UserLocalServiceUtil.getUsersCount();
    List users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    }
 
Next, we'll show the same thing in Groovy, another scripting language designed
to be similar to Java. 

## Groovy [](id=groovy)

Groovy is also based on Java. It's even easier than Beanshell because any code
written in Java also runs in Groovy. This means we can execute the exact same
code from our Java example without any changes:

    import com.liferay.portal.model.User;
    import com.liferay.portal.service.UserLocalServiceUtil;
    import java.util.List;

    int userCount = UserLocalServiceUtil.getUsersCount();
    List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
    
    for (User user:users) {
        System.out.println("User Name: " + user.getFullName());
    } 
 
Of course, we could make this somewhat Groovier by simplifying the program as
follows: 

    import com.liferay.portal.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)
    for (user in users){
        System.out.println("User Name: " + user.getFullName())
    }
 
Liferay's script engine supports more than just Java-like languages. Despite the
name, you should be aware that JavaScript bears little resemblance to Java, but
you can still use it in Liferay's script engine. 

## JavaScript [](id=javascript)

@product@ uses the Rhino JavaScript Engine to provide JavaScript support in the
script engine. The following code provides a JavaScript version of our original
Java program:

    userCount = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
    users = new Packages.java.util.ArrayList;
    users = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
    user = Packages.com.liferay.portal.service.UserLocalServiceUtil.createUser(0);

    for (i=0;i<users.size();i++) {
        Packages.java.lang.System.out.println(users.get(i).getFullName());
    }
 
You can see that the JavaScript example is compact. Ruby is even more compact. 

## Ruby [](id=ruby)

Ruby is supported through the use of JRuby. Our example could be implemented in
Ruby as follows: 

    userCount = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount();
    users = com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
    users.each{ |user| print user.getFullName() + "\n"}
 
Python users aren't left out either. 

## Python [](id=python)

@product@ provides Python support based on Jython. Our example could be
implemented with the following code:

    from com.liferay.portal.service import UserLocalServiceUtil
    from com.liferay.portal.model import User

    userCount = UserLocalServiceUtil().getUsersCount()
    users = UserLocalServiceUtil().getUsers(0,userCount)

    for user in users:
        print user.getFullName()

As you can see, Liferay's services can be accessed from any of these languages.
Next, let's look at some practical examples of how you can use Liferay's script
engine.

## Related Topics [](id=related-topics)

[Running Scripts From the Script Console](/discover/deployment/-/knowledge_base/7-0/running-scripts-from-the-script-console)

[Leveraging the Script Engine in Workflow](/discover/deployment/-/knowledge_base/7-0/leveraging-the-script-engine-in-workflow)

[Using Custom Java Tools in the Script Engine](/discover/deployment/-/knowledge_base/7-0/using-custom-java-tools-in-the-script-engine)
