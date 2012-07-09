# Using Scripting for Advanced Flexibility [](id=lp-6-1-ugen18-using-scripting-for-advanced-flexibility-0)

Liferay provides a robust script engine that can be used to interpret scripts in Beanshell, Javascript, Groovy, Python and Ruby. The script engine came out of Liferay's involvement with the Romulus project (http://www.ict-romulus.eu). It was originally developed to support non Java-based portlets, but has now been extended to handle a lot more. For example, when Liferay's workflow framework was introduced, the script engine was leveraged to support the execution of scripts from within a workflow module. A script console is now included in the Server Administration portlet in the control panel. It allows system administrators an easy way to execute scripts to perform repetitive user maintenance operations, bulk manipulations using the Liferay API to ensure consistency, or even system level operations.

This chapter helps you to understand Liferay's script engine and covers the following topics: 

- Accessing Liferay's service layer from a script
- Running scripts from the script console
- Using the script engine with workflow
- Leveraging custom Java tools in the script engine

The most common thing you'll want to do is access Liferay's services. If you have any familiarity with Liferay's developer tools and API, this will be a snap for you. 

## Accessing Liferay Services [](id=accessing-liferay-services)

In many cases, you'll want to interact with one of Liferay's many services. This is possible from all of the scripting languages supported, but the syntax is a little different for each language. 

To illustrate the correct syntax for interacting with Liferay services, let's look at a simple example that uses the `UserLocalService` to retrieve a list of users and then prints their names to the log file. We'll initially implement the example in Java pseudo-code. 

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
				
### Beanshell [](id=lp-6-1-ugen13-beanshell-0)

Beanshell is a Java scripting language that's designed to run Java code with little or no changes. In this example, we only have one small change to make because Beanshell doesn't support the use of Java Generics. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;

	int userCount = UserLocalServiceUtil.getUsersCount();
	List users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	}
	
Next, we'll show the same thing in Groovy, another scripting language designed to be similar to Java. 

### Groovy [](id=lp-6-1-ugen13-groovy-0)

Groovy is also based on Java and is perhaps a little easier than Beanshell because literally any code written in Java also runs in Groovy. This means we can execute the exact same code from our Java example without any changes. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;

	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	} 
	
Of course, we could make this somewhat Groovier by simplyfing the program as follows: 

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)
	for ( user in users){
		System.out.println("User Name: " + user.getFullName())
	}
	
The script engine supports more than just Java-like languages. Despite the name, you should be aware that Javascript bears little resemblance to Java, but you can still use it in Liferay's script engine. 

### Javascript [](id=lp-6-1-ugen13-javascript-0)

Liferay uses the Rhino Javascript Engine to provide Javascript support in the script engine. The following code provides a Javascript version of our original Java program.  

	userCount = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
	users = new Packages.java.util.ArrayList;
	users = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	user = Packages.com.liferay.portal.service.UserLocalServiceUtil.createUser(0);

	for (i=0;i<users.size();i++) {
		Packages.java.lang.System.out.println(users.get(i).getFullName());
	}
	
You can see the Javascript example is compact. Ruby is even more compact. 

### Ruby [](id=lp-6-1-ugen13-ruby-0)

Ruby is supported throgh the use of JRuby and our previous example could be implemented in Ruby as follows: 

	userCount = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount();
	users = com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	users.each{ |user| print user.getFullName() + "\n"}
	
Python users aren't left out either. 

### Python [](id=lp-6-1-ugen13-python-0)

Lastly, Liferay provides Python support based on Jython and the previous example could be implemented with the following code. 

	from com.liferay.portal.service import UserLocalServiceUtil
	from com.liferay.portal.model import User

	userCount = UserLocalServiceUtil().getUsersCount()
	users = UserLocalServiceUtil().getUsers(0,userCount)

	for user in users:
		print user.getFullName()

As you can see, Liferay's services can be accessed from any of these languages. Let's look at some practical examples of how you'd use this. 
		
## Running scripts from the control panel [](id=running-scripts-from-the-control-panel)

To see a very simple example of the script console in action, log into the portal as an administrator and navigate to the control panel &rarr; Server Administration &rarr; Script. Change the script type to Groovy and modify the current code to look like the following:
 
	number = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
	out.println(number); 

Click the execute button and check the console or the log for your output.  
 
Let's implement a more realistic example. We'll retrieve some user information from the database, make some changes and then update the database with our changes. Our company has updated the terms of use and requires that everyone be presented with the updated terms of use on the next log in. When users agree to the terms of use, a boolean attribute called `agreedToTermsOfUse` is set in their user records. As long as the boolean is `true`, Liferay will not present the user with the terms of use. However, if we set this flag to `false` for everyone, all users will have to agree to it again to use the site. 

We'll again use Groovy, so ensure the script type is set to Groovy and execute the following code to check the status of the `agreedToTermsOfUse` attribute:

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users) {
		println("User Name: " + user.getFullName() + " -- " + user.getAgreedToTermsOfUse())
	}
	
Now we'll actually update each user in the system to set his or her `agreedToTermsOfUse` attribute to false. We'll be sure to skip the default user as the default user is not required to agree to the Terms of Use. We'll also skip the admin user that's currently logged in and running the script. If you're logged in as somoene other than test@liferay.com, be sure to update the following script before running it. 
	
	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users){
	
		if(!user.isDefaultUser() && 
			!user.getEmailAddress().equalsIgnoreCase("test@liferay.com")) {
			
				user.setAgreedToTermsOfUse(false)
				UserLocalServiceUtil.updateUser(user)
		
		}
		
	}
	
To verify the script has updated the records, run the first script again and you should see all users (except the default user and your ID) have been updated. 

That's all that's needed to run scripts and to access the Liferay service layer. There are, however, some things to keep in mind when working with the script console: 

* There is no undo
* There is no preview
* When using Local Services, no permissions checking is enforced
* Scripts are executed synchronously, so be careful with scripts that might take a long time to execute. 

For these reasons, you want to use the script console with care, and test run your scripts on non-production systems before you run them on production. 

Of course, the script engine has uses beyond the script console. One of the main uses of it is in designing workflows. 

## Leveraging the Script Engine in Workflow [](id=leveraging-the-script-engine-in-workflow)

Liferay's Kaleo workflow engine provides a robust system for reviewing and approving content in an enterprise environment. Just with the standard feature set, it is a powerful and robust workflow solution. Adding scripting features brings it to the next level.

The default workflow definition included with Kaleo gives you a quick look into how the feature works. The final step in the workflow runs a script that makes content available for use. As you can see in the snippet below, it uses Javascript to access the Java class associated with the workflow to set the status of the content to *approved*.

	<script>
	<![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus
		(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"),workflowContext);]]>
	</script>
	<script-language>javascript</script-language>

At virtually any point in a workflow, you can use Liferay's scripting engine to access workflow APIs or other APIs outside of workflow. There are a lot of different ways you could use this, but some practical ones might be getting a list of users with a specific workflow-related role; sending an email to the designated content approver with a list of people to contact if he is unable to review the content; or creating an alert to be displayed in the Alerts portlet for any user assigned to approve content.

Of course, before you try any of this, you might want to know what the appropriate syntax is for inserting a script into the workflow. In an XML workflow definition, a script can be used in any XML type that can contain an *<actions>* tag - those types being `<state>`, `<task>`, `<fork>` and `<join>`. Inside of one of those types, format your script like so:

	<actions>
		<action>
			<script>
				<![CDATA[*the contents of your script*]]>
			</script>
			<script-language>*your scripting language of choice*</script-language>
		</action>
		...
	</actions>
  
Here's an example of a workflow script created in Groovy. This one is designed to be used with a `Condition` statement in Kaleo. It accesses Liferay's Asset Framework to determine the category of an asset in the workflow so the correct approval process can be automatically determined. 
  
	<script>
			<![CDATA[
				import com.liferay.portal.kernel.util.GetterUtil;
				import com.liferay.portal.kernel.workflow.WorkflowConstants;
				import com.liferay.portal.kernel.workflow.WorkflowHandler;
				import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
				import com.liferay.portlet.asset.model.AssetCategory;
				import com.liferay.portlet.asset.model.AssetEntry;
				import com.liferay.portlet.asset.model.AssetRenderer;
				import com.liferay.portlet.asset.model.AssetRendererFactory;
				import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

				import java.util.List;

				String className = (String)workflowContext.get(
					WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

				WorkflowHandler workflowHandler = 
					WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

				AssetRendererFactory assetRendererFactory = 
					workflowHandler.getAssetRendererFactory();

				long classPK =
					GetterUtil.getLong((String)workflowContext.get
						(WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

				AssetRenderer assetRenderer = 
					workflowHandler.getAssetRenderer(classPK);

				AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
					assetRendererFactory.getClassName(), assetRenderer.getClassPK());

				List<AssetCategory> assetCategories = assetEntry.getCategories();

				returnValue = "Content Review";

				for (AssetCategory assetCategory : assetCategories) {
					String categoryName = assetCategory.getName();

					if (categoryName.equals("legal")) {
						returnValue = "Legal Review";

						return;
					}
				}
			]]>
		</script>
		<script-language>groovy</script-language>

Within a workflow, the next task or state is chosen based on the what the method returns.

The combination of Liferay's scripting and workflow engines is incredibly powerful, but as it provides users with the ability to execute code, it can also be very dangerous. When configuring your permissions, be aware of the potential consequences of poorly, or maliciously, written scripts inside of a workflow definition. For more information on creating definitions with Kaleo Workflow see *Chapter 6: Workflow with Kaleo*.

<!--  check chapter reference  -->
  
<!-- | TODO

## Script Based Portlets [](id=lp-6-1-ugen13-script-based-portlets-0)

-- TO DO - Jonathon?

| -->

## Custom Java Tools in the Script Engine [](id=custom-java-tools-in-the-script-engine)

There are several challenges when working with the Script Engine, including debugging and logging. One approach to overcome these challenges is to develop custom Java utilities that can be called from your scripts. These utilities can write to a custom log file or the Liferay log file. You can also place breakpoints in your utility code and step through it using your favorite debugger. 

Liferay's use of Spring and `PortletBeanLocatorUtil` makes calling these Java utilities from your script easy, regardless of the scripting language you're using. 

Let's begin by creating a Liferay Hook project. If you're using Liferay IDE or Liferay Developer Studio, select *File* &rarr; *New* &rarr; *Liferay Project*. Name the project *script-utils* and accept the display name generated by the wizard. Be sure to select *Hook* for the Plugin Type and then select *Finish*. 

![Figure 18.1: Creating a new utilities project is easy if you use Liferay IDE or Liferay Developer Studio.](../../images/13-new-liferay-project.png)

You're using a Liferay Hook Plugin to deploy your utility, but you're not using any of the typical hook features. You just need a way to make your code available to the portal and the Hook Plugin is the least obtrusive way to do this. This means you don't need to add anything to the `liferay-hook.xml` file. Instead, you'll begin by adding your utility code. 

You'll be following the Dependency Injection design pattern so begin by creating the interface. Right click on the `docroot/WEB-INF/src` folder and select *New* &rarr; *Interface*. You'll create your interface in the `com.liferay.sample` package. Name it `ScriptUtil`. 

![Figure 18.2: Create a new Java Interface which you'll later implement.](../../images/13-new-interface.png)

Next, add two methods to the interface. 

	package com.liferay.samples;

	public interface ScriptUtil {

		public String operationOne(); 
	
		public String operationTwo(String name); 

	}

Next, create the implementation class. Right click on the `docroot/WEB-INF/src` folder and select *New* &rarr; *Class*. Create the interface in the `com.liferay.sample` package and name it `ScriptUtilImpl`. Be sure to select `com.liferay.sample.ScripUtil` as the Interface. 

![Figure 18.3: Create a new Java Class that implements the interface you created earlier.](../../images/13-new-class.png)

Next, add implementations for the two methods. 

	package com.liferay.samples;

	import com.liferay.portal.kernel.log.Log;
	import com.liferay.portal.kernel.log.LogFactoryUtil;

	public class ScriptUtilImpl implements ScriptUtil {

		@Override
		public String operationOne() { 
		
			return "Hello out there!"; 
		} 
	
		@Override
		public String operationTwo(String name) { 
			
			_log.debug("Inside of Operation Two");
			
			return "Hello " + name + "!"; 
		}
		
		private static Log _log = LogFactoryUtil.getLog(ScriptUtilImpl.class); 

	}
	
Liferay makes extensive use of the Spring Framework and you'll be using it here to inject your implementation class into the application. Spring needs a bean definition which you'll declare in an XML file named `applicationContext.xml`. Create this file in the `docroot/WEB-INF/` directory and add the following code: 

	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN" "http://www.springframework.org/dtd/spring-beans.dtd">

	<beans>
		<bean id="com.liferay.sample.ScriptUtil" class="com.liferay.sample.ScriptUtilImpl" />
	</beans>

Upon deployment, you'll need the portal to create a `BeanLocator` for your plugin. The `BeanLocator` reads the bean definitions you provided.

If you're adding your utility to a Service Builder enabled plugin, then you'll already have a `BeanLocator` and you can skip this step. Since this Hook plugin is not already using Service Builder, you'll need to define a context loader listener in our Hook to provide a `BeanLocator`. Open the `docroot/WEB-INF/web.xml` file and replace its contents with the following code: 

	<?xml version="1.0"?>
	<!DOCTYPE web-app PUBLIC "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN" "http://java.sun.com/dtd/web-app_2_3.dtd">

	<web-app>
		<listener>
			<listener-class>com.liferay.portal.kernel.spring.context.PortletContextLoaderListener</listener-class>
		</listener>
	</web-app>
	
Save all of the changes you've made and deploy the hook. Once the hook has been deployed successfully, the `ScriptUtil` can be used in your script engine code. 

To see the `ScriptUtil` code in action, navigate back to the *control panel* &rarr; *Server Administration* &rarr; *Script*. Change the script type to Groovy and enter the following script: 

	myUtil = 
		com.liferay.portal.kernel.bean.PortletBeanLocatorUtil.locate("script-utils-hook",
		"com.liferay.samples.ScriptUtil")

	println(myUtil.operationOne())

	println(myUtil.operationTwo("Joe Bloggs"))
	
You should see the results of your script displayed right under the script. 

## Summary [](id=summ-34)

In this chapter we saw how Liferay's script engine opens up many exciting posibilities for working with Liferay regardless of your language of choice. We learned how you can leverage Liferay's Services Oriented Architecture (SOA) from any of the popular scripting languages Liferay supports. We then saw how those scripts could be used to simplify administrative tasks by leveraging the Administrator Script Console. Next, we discovered how you could enhance workflow by using the power of scripts. Lastly, we saw how you could overcome some of the limitations of running scripts in Liferay by creating custom Java utilities that could be executed from within your scripts. 

As you can see, Liferay's script engine opens up many exciting posibilities for working with Liferay regardless of your language of choice.
