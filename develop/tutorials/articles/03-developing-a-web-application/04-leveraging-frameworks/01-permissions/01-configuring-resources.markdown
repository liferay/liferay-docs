# Implementing Resources

The first thing you need to do to begin defining your permissions is point your application at the configuration file you'll use to define those permissions.

1. In your application's src folder, create a file called portlet.properties. 
    This file lets you define properties for your application in key/value 
	pairs.

2. In this file, place the following property:

    resource.actions.configs=resource-actions/default.xml

This property defines the name and location of your permissions definition 
file. You can, of course, call it anything you want. By convention, Liferay 
calls this file and places it in a folder called resource-actions inside the 
src folder of the plugin project, so by doing it this way, you're just 
following the Liferay convention.

Next, you'll create the file itself:

1. Create a folder called `resource-actions` in your 'guestbook-web` src folder.

2. Create a new file in this folder called default.xml.

3. Add the following DOCTYPE declaration to the top of the file:

    <?xml version="1.0"?>
	<!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
		Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">


IMAGE

In the image above, you can see three areas where you might want to protect 
data with a security check. Two of them are outlined in red, because they have 
to do with Guestbooks, while the Add Entry button is outlined in blue, because 
it has to do with Entry entities. What are the security rules you want for 
these functions?

Certainly, you want only people with elevated permissions to access the 
functionality for adding a guestbook. The owner(s) of the site should really be 
the only person/people adding guestbooks. Similarly, site owners may want to 
make certain guestbooks available only to a certain group of users. That means 
when you're building the tab bar, you'll have to check each guestbook to make 
sure the current user has permission to view it.

Finally, the Add Entry button deserves some consideration. When site 
administrators place the Guestbook portlet on a page, by default, who should 
have permission to add entries? You can go one of two ways on this: you can 
allow anybody, even guest users, to add entries, or you can make it so that a 
user must be logged in to add entries. For the purposes of this Learning Path, 
you'll configure the permissions to allow site members to post, but not guests. 
Once you see how easy this is to configure, you can change it later if you want 
it to work the other way around.

To summarize:

The Add Guestbook button should be available only to administrators.

The Guestbook tabs should be filtered by permissions in case administrators 
have limited who can see them.

The Add Entry button should be available only to site members.

Now you're ready to create your permissions configuration. The configuration 
file is divided into two sections: portlet permissions and various model 
permissions sections, depending on the entities you have in your application. 
The easiest one to configure is the portlet permissions, so you'll start with 
that.

1. Place the following wrapper tags into your default.xml file:

    <resource-action-mapping>

    </resource-action-mapping>

All the rest of the permissions configuration is placed inside these tags.

2. Next, place the portlet permissions between the `<resource-action-mapping/>` 
    tags:

	<portlet-resource>
	    <portlet-name>guestbook</portlet-name>
	    <permissions>
	        <supports>
	            <action-key>ADD_TO_PAGE</action-key>
	            <action-key>CONFIGURATION</action-key>
	            <action-key>VIEW</action-key>
	        </supports>
	        <site-member-defaults>
	            <action-key>VIEW</action-key>
	        </site-member-defaults>
	        <guest-defaults>
	            <action-key>VIEW</action-key>
	        </guest-defaults>
	        <guest-unsupported />
	    </permissions>
	</portlet-resource>
    

This configuration defines the default permissions for the portlet itself. This 
is the application window in which the Guestbook appears. You're defining three 
different permissions: ADD_TO_PAGE, CONFIGURATION, and VIEW. Below that, you 
give site members and guests the VIEW permission by default. As you can see, 
this is an easy and straightforward way to define permissions.

3. Place the model package permissions in the file next:

	<model-resource>
	    <model-name>com.liferay.docs.guestbook.model</model-name>
	    <portlet-ref>
	        <portlet-name>guestbook</portlet-name>
	    </portlet-ref>
	    <permissions>
	        <supports>
	            <action-key>ADD_GUESTBOOK</action-key>
	            <action-key>ADD_ENTRY</action-key>
	        </supports>
	        <site-member-defaults>
	            <action-key>ADD_ENTRY</action-key>
	        </site-member-defaults>
	        <guest-defaults />
	        <guest-unsupported>
	            <action-key>ADD_GUESTBOOK</action-key>
	            <action-key>ADD_ENTRY</action-key>
	        </guest-unsupported>
	    </permissions>
	</model-resource> 
    
This configuration defines the default permissions at a global level for your 
entities. You create two permissions: ADD_GUESTBOOK and ADD_ENTRY. Site members 
can add entries by default, while guests can't do anything. They can, however, 
view the application (and thus the data it is displaying), because you gave 
them that permission in the <portlet-resource/> section above.

4. Next, place the permissions for the Guestbook entity in the file:

	<model-resource>
	    <model-name>com.liferay.docs.guestbook.model.Guestbook</model-name>
	    <portlet-ref>
	        <portlet-name>guestbook</portlet-name>
	    </portlet-ref>
	    <permissions>
	        <supports>
	            <action-key>ADD_ENTRY</action-key>
	            <action-key>DELETE</action-key>
	            <action-key>PERMISSIONS</action-key>
	            <action-key>UPDATE</action-key>
	            <action-key>VIEW</action-key>
	        </supports>
	        <site-member-defaults>
	            <action-key>ADD_ENTRY</action-key>
	            <action-key>VIEW</action-key>
	        </site-member-defaults>
	        <guest-defaults>
	            <action-key>VIEW</action-key>
	        </guest-defaults>
	        <guest-unsupported>
	            <action-key>UPDATE</action-key>
	        </guest-unsupported>
	    </permissions>
	</model-resource>
    

This is the most complicated permission scheme so far. You've defined five 
permissions, most of which are pretty straightforward, such as DELETE 
(permission to delete an entity), UPDATE (permission to modify an entity), 
PERMISSIONS (permission to change permissions of an entity), and VIEW 
(permission to view an entity). The ADD_ENTRY permission, however, is not so 
straightforward, at least at first. But think about it: do you have to have 
permission to view a Guestbook in order to add an entry to it? Yes. Therefore, 
the default permission configuration should reflect that.

5. Finally, place the permissions for the Entry entity in the file:

	<model-resource>
	     <model-name>com.liferay.docs.guestbook.model.Entry</model-name>
	     <portlet-ref>
	         <portlet-name>guestbook</portlet-name>
	     </portlet-ref>
	     <permissions>
	         <supports>
	             <action-key>DELETE</action-key>
	             <action-key>PERMISSIONS</action-key>
	             <action-key>UPDATE</action-key>
	             <action-key>VIEW</action-key>
	         </supports>
	         <site-member-defaults>
	             <action-key>VIEW</action-key>
	         </site-member-defaults>
	         <guest-defaults>
	             <action-key>VIEW</action-key>
	         </guest-defaults>
	         <guest-unsupported>
	             <action-key>UPDATE</action-key>
	         </guest-unsupported>
	     </permissions>
	 </model-resource>
    
These permissions are the same as for the Guestbook entity, except for the 
ADD_ENTRY permission. This completes your permission configuration.

Great job! You've now successfully designed and implemented a permissions 
scheme for your application. In the next part, you'll implement supporting Java 
code: code to support permissions (called resources on the back end) and helper 
classes for checking permissions.