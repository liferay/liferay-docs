## Script Engine 

Liferay provides a robust script engine that can be used to interpret scripts in Beanshell, Javascript, Groovy, Python, and Ruby.  The script engine came out of Liferay's involvement with the Romulus project (http://www.ict-romulus.eu).  It was originally developed to support non Java-based portlets.  With the introduction of Liferay's workflow framework in version 6.0, the script engine was used to support the execution of scripts from within a workflow module.  Version 6.0 also saw the inclusion of a script console in the Server Administration portlet in the Control Panel.   The script console allows system administrators an easy way to execute scripts.  Some of the tasks that you might use the script console to perform might include user maintenance operations, bulk manipulations using the Liferay API to ensure consistency, or even system level operations.

### Accessing Liferay Services

In many cases, you'll want to interact with one of Liferay's many services.  This is possible from all of the scripting languages supported, but the syntax is a little different for each language.  

To illustrate the correct syntax for interacting with Liferay services, let's take a look at a simple example that uses the UserLocalService to retrieve a list of users and then prints their names to the log file.  We'll initially implement the example in Java pseudo-code.  

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;
				.
				.
				.
				
	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	for (User user:users){
		System.out.println("User Name: " + user.getFullName());
	}
	
				.
				.
				.
				
#### Beanshell

Beanshell is a Java scripting language that's designed to run Java code with little or no changes.  In this example, we only have one small change to make because Beanshell doesn't support the use of Java Generics. 

import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import java.util.List;

int userCount = UserLocalServiceUtil.getUsersCount();
List users = UserLocalServiceUtil.getUsers(0, userCount);
for (User user:users){
		System.out.println("User Name: " + user.getFullName());
}

#### Groovy

Groovy is also based on Java and is perhaps a little easier than Beanshell because literally any code written in Java will also run in Groovy.  This means we can execute the exact same code from our Java example without any changes.   

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;

	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	for (User user:users){
		System.out.println("User Name: " + user.getFullName());
	}  
	
Of course, we could make this a lot Groovier by simplyfing the program as follows:  

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)
	for ( user in users){
		System.out.println("User Name: " + user.getFullName())
	}

#### Javascript

Liferay uses the Rhino Javascript Engine to provide Javascript support in the script engine.  The following code provides a Javascript version of our original Java program.    

	userCount = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 

	users = new Packages.java.util.ArrayList;

	users = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);

	user = Packages.com.liferay.portal.service.UserLocalServiceUtil.createUser(0);

	for (i=0;i<users.size();i++)
	{
     Packages.java.lang.System.out.println(users.get(i).getFullName());
	}


#### Ruby

Ruby is supported throgh the use of JRuby and our previous example could be implemented in Ruby as follows:  

	userCount = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount();
	users = com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	users.each{ |user| print user.getFullName() + "\n"}

#### Python

Lastly, Liferay provides Python support based on Jython and the previous example could be implemented with the following code.  

	from com.liferay.portal.service import UserLocalServiceUtil
	from com.liferay.portal.model import User

	userCount = UserLocalServiceUtil().getUsersCount()
	users = UserLocalServiceUtil().getUsers(0,userCount)

	for user in users:
		print user.getFullName()

### Running Scripts from the Control Panel.  

To see a very simple example of the script console in action, log into the portal as an administrator and navigate to the Control Panel &rarr; Server Administration &rarr; Script. Change the script type to Groovy and modify the current code to look like the following:
 
	number = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
	out.println(number);  

Click the execute button and check the console or the log for your output.  
 
Let's implement a more realistic example where we will retrieve some user information from the database, make some changes, and then update the database with our changes.  Our company has updated the terms of use and requires that everyone be presented with the updated terms of use the next time they log in.  When users agree to the terms of use, a boolean attribute called *agreedToTermsOfUse* is set in their user record.  As long as the boolean is true, Liferay will not present the user with the terms of use. However, if we set this flag to 

We'll again use Groovy so ensure the script type is set to Groovy and execute the following code to check the status of the agreedToTermsOfUse attribute:

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for ( user in users){
		println("User Name: " + user.getFullName() + " -- " + user. getAgreedToTermsOfUse())
	}
	
Now we'll actually update each user in the system to set his or her agreedToTermsOfUse attribute to false.  We'll be sure to skip the default user as the default user is not required to agree to the Terms of Use.  We'll also skip the admin user that we're currently using.  If you're logged in as somoene other than test@liferay.com, be sure to update the following script before running it.  
	
	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for ( user in users){
  	if(!user.isDefaultUser() && !user.getEmailAddress().equalsIgnoreCase("test@liferay.com")){     
			user.setAgreedToTermsOfUse(false)
			UserLocalServiceUtil.updateUser(user)
		}
	}
	
To verify the script has updated the records, run the first script again and you should see that all users (except the default user and test@liferay.com use) have been updated.  


That's all that's needed to run scripts and to access the Liferay service layer.  However, there are some things to keep in mind when working with the script console: 
* There is no undo
* There is no preview
* When using Local Services,  no permissions checking is enforced
* Scripts are executed synchronously, so be careful with scripts that might take a long time to execute. 

### Leveraging the Script Engine in Workflow

--TO DO - Stephen?  

### Script Based Portlets

-- TO DO - Jonathon?

### Custom Java Tools in the Script Engine

-- To DO - Jeff