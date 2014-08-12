# Accessing Liferay Services [](id=accessing-liferay-services)

In many cases, you'll want to interact with one of Liferay's many services. This
is possible from all of the scripting languages supported, but the syntax is a
little different for each language. 

To illustrate the correct syntax for interacting with Liferay services, let's
look at a simple example that uses the `UserLocalService` to retrieve a list of
users and then prints their names to the log file. We'll initially implement the
example in Java pseudo-code. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;
				.
				.
				.
				
	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	}
	
				.
				.
				.
				
Let's see first how this would work in Beanshell, which is very similar to Java. 
				
## Beanshell

Beanshell is a Java scripting language that's designed to run Java code with
little or no changes. In this example, we only have one small change to make
because Beanshell doesn't support the use of Java Generics. 

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

## Groovy

Groovy is also based on Java and is perhaps a little easier than Beanshell
because literally any code written in Java also runs in Groovy. This means we
can execute the exact same code from our Java example without any changes. 

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
	
The script engine supports more than just Java-like languages. Despite the name,
you should be aware that Javascript bears little resemblance to Java, but you
can still use it in Liferay's script engine. 

## Javascript

Liferay uses the Rhino Javascript Engine to provide Javascript support in the
script engine. The following code provides a Javascript version of our original
Java program. 

	userCount = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
	users = new Packages.java.util.ArrayList;
	users = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	user = Packages.com.liferay.portal.service.UserLocalServiceUtil.createUser(0);

	for (i=0;i<users.size();i++) {
		Packages.java.lang.System.out.println(users.get(i).getFullName());
	}
	
You can see the Javascript example is compact. Ruby is even more compact. 

## Ruby

Ruby is supported through the use of JRuby and our previous example could be
implemented in Ruby as follows: 

	userCount = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount();
	users = com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	users.each{ |user| print user.getFullName() + "\n"}
	
Python users aren't left out either. 

## Python

Lastly, Liferay provides Python support based on Jython and the previous example
could be implemented with the following code. 

	from com.liferay.portal.service import UserLocalServiceUtil
	from com.liferay.portal.model import User

	userCount = UserLocalServiceUtil().getUsersCount()
	users = UserLocalServiceUtil().getUsers(0,userCount)

	for user in users:
		print user.getFullName()

As you can see, Liferay's services can be accessed from any of these languages.
Let's look at some practical examples of how you'd use this.
