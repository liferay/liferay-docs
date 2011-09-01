## Drools

Liferay Portal Enterprise Edition provides an implementation of a JSR-94 compliant rules engine.  This rules engine is provided as a Web Plugin and is based on the popular open source Drools project. 

In most applications, complex rule processing often takes the form of nested if-else blocks of code which can be very difficult to decipher and to maintain.  A rules engine provides a means to separate the rules or logic of an application from the remaining code.  Separating these rules allows for changes to be made to the rules without affecting the rest of the application.  A rule engine allows for a more declarative style of programming where the rules define what is happening, without describing how it is happening.  

### Installation

The Drools Web Plugin is available to Liferay Enterprise Edition customers through the customer portal.  In can also be downloaded and installed through the built-in plugin repository.  The name is `drools-web`{.western}, and you'll find it in the list of web plugins. 

### Working with the Drools Web Plugin

The Drools Web Plugin provides a rules engine implementation, but by itself it does not provide any observable changes to the portal user interface or any additional functionality.  To see the rules engine in action, you can download and install a Sample Drools Portlet that contains two rule definitions that illustrate how to leverage the rules engine in your custom code.     The Sample Drools Portlet is available through the Customer Portal.   

#### Configuring the Sample Drools Portlet

Begin by downloading and installing the Sample Drools Portlet.  The Sample Drools Portlet is available to Liferay Enterprise Edition customers through the customer portal.  In can also be downloaded and installed through the built-in plugin repository.  The name is `sample-drools-portlet`{.western}, and you'll find it in the list of web plugins.

After installation is complete, add the portlet to a page in the default (liferay.com) community.  Initially, the portlet will indicate the name of the currently logged in user and a message that says there are no results.  To see results in the portlet we will need to create and tag assets in the liferay.com community.  

Log in as an administrative user and navigate to the Control Panel.   Once in the Control Panel, add a new Web Content entry to the default liferay.com community. Before publishing the Web Content entry, tag the article with *west coast symposium*.  While still in the control panel, navigate to *My Account* and select the Address link on the right side of the screen. Enter a Canadian, Mexican, or US based address and save the record.  Now, navigate back to the liferay.com community and the Web Content should be displayed in the Sample Drools Portlet.

The default rule that is being evaluated will display a list of assets based on the current user's address.  For example, if the current user's country is set to Canada, Mexico, or the United States, the Sample Drools Portlet will display a list of assets that have been tagged with the *west coast symposium* tag. 

The Sample Drools Portlet plugin also contains a second rule that will return personalized content based on the user's networth set in the My Account - Custom Fields section of the Control Panel.  To see this rule in action, add a second instance of the Sample Drools Portlet to a page in the liferay.com.  Once added to the page, select the *Options* icon (*the wrench*) and then select *Configuration*. We will need to replace the rules defined in the *Rules* section of the Configuration screen with contents of the *rules_user_custom_attribute_content.drl* file.  The rule file can be found in the deployed portlet at sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/rules_user_custom_attribute_content.drl.  In the same Configuration screen, add *networth* to the user-custom-attribute-names field.  Save your changes and close the pop-up window.  Navigate to the Control Panel and add a Custom Field on the User object with the Key *networth*.  Navigate to *My Account* and select the Custom Fields link on the right side of the screen.  Enter a networth of 150000 and save the record.  While still in the Control Panel, add a new Web Content entry to the default liferay.com community. Before publishing the Web Content entry, tag the article with *high net worth* and then save the entry.   Now, navigate back to the liferay.com community and the Web Content should be displayed in the second Sample Drools Portlet added to the page.  

#### Rules Definitions

Rule definitions are written using Drools' declarative language.  Rule files are text files that often have a .drl extension.  Each rule file can contain multiple rules.  To see examples of a rules definition file, access the following directory in the Sample Drools Portlet sample-drools-portlet/WEB-INF/src/com/liferay/sampledrools/dependencies/.  For additional documentation on the Drools rules language, please see the official Drools documentation at http://www.jboss.org/drools.  

As you can see from the Sample Rules Portlet, using a rules engine can be a very powerful way to decouple the rules of our appliation from the front-end and back-end code.  These rules are written in a declarative language that business users could read and verify.  Additionally, rule definitions can be modified without modifying the underlying Java code, re-compiling, or redeploying your applications. 

