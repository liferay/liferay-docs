# Configuring Your Permissions Scheme [](id=configuring-your-permissions-scheme)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 1 of 4</p>
</div>

@product@'s permissions framework is configured declaratively, like Service 
Builder. You define all your permissions in an XML file that by convention is 
called `default.xml` (but you could really call it whatever you want). Then you 
implement permissions checks in the following places in your code: 

- In the view layer, when showing links or buttons to protected functionality
- In the actions, before performing a protected action
- Later, in your service, before calling the local service

You should first define the permissions you want. To get started, think of your 
application's use cases and how access to that functionality should be 
controlled: 

- The Add Guestbook button should be available only to administrators. 

- The Guestbook tabs should be filtered by permissions so administrators can 
  control who can see them. 

- To prevent anonymous users from spamming the guestbook, the Add Entry button 
  should be available only to site members. 

- Users should be able to set permissions on their own entries. 

Now you're ready to create the permissions configuration. Objects in your 
application (such as `Guestbook` and `Entry`) are defined as *resources*, and 
*resource actions* manage how users can interact with those resources. There are 
therefore two kinds of permissions: portlet permissions and resource (or model) 
permissions. Portlet permissions protect access to global functions, such as 
*Add Entry*. If users don't have permission to access that global function, 
they're missing a portlet permission. Resource permissions protect access to 
objects, such as `Guestbook` and `Entry`. A user may have permission to view one 
`Entry`, view and edit another `Entry`, and may not be able to access another 
`Entry` at all. This is due to a resource permission. 

![Figure 1: Portlet permissions and resource permissions cover different parts of the application.](../../../images/permission-types.png)

The first thing you must do is tell the framework where your permissions are 
defined. You'll define resource and model permissions in the module where your 
model is defined: 

1.  In `guestbook-service`'s `src/main/resources` folder, create a file called
    `portlet.properties`. 
 
2.  In this file, place the following property: 

        resource.actions.configs=META-INF/resource-actions/default.xml

This property defines the name and location of your permissions definition file. 

Next, create the permissions file: 

1.  In the `META-INF` folder, create a subfolder called `resource-actions`.

2.  Create a new file in this folder called `default.xml`.

3.  Click the *Source* tab. Add the following `DOCTYPE` declaration to the top
    of the file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

4.  Place the following wrapper tags into your `default.xml` file, below the 
    `DOCTYPE` declaration: 

        <resource-action-mapping>

        </resource-action-mapping>

    You'll define your resource and model permissions inside these tags. 

5.  Next, place the permissions for your `com.liferay.docs.guestbook` 
    package between the `<resource-action-mapping>` tags: 

        <model-resource>
            <model-name>com.liferay.docs.guestbook</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
            </portlet-ref>
            <root>true</root>
            <permissions>
                <supports>
                    <action-key>ADD_GUESTBOOK</action-key>
                    <action-key>ADD_ENTRY</action-key>
				    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>ADD_ENTRY</action-key>
                </site-member-defaults>
                <guest-defaults>
				    <action-key>VIEW</action-key>
			    </guest-defaults>
                <guest-unsupported>
                    <action-key>ADD_GUESTBOOK</action-key>
                    <action-key>ADD_ENTRY</action-key>
                </guest-unsupported>
            </permissions>
        </model-resource>

    This defines the baseline configuration for the `Guestbook` and `Entry` 
    entities. The supported actions are `ADD_GUESTBOOK` and `ADD_ENTRY`. Site 
    members can `ADD_ENTRY` by default, while guests can't perform either 
    action (but they can view). 

6.  Below that, but above the closing `</resource-action-mapping>`, place the
    `Guestbook` model permissions: 

        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Guestbook</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
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
 
    This defines the `Guestbook` specific actions, including adding, deleting, 
    updating, and viewing. By default, site members and guests can view 
    guestbooks, but guests can't update them. 

7.  Below the `Guestbook` model permissions, but still above the closing 
    `</resource-action-mapping>`, place the `Entry` model permissions: 

        <model-resource>
            <model-name>com.liferay.docs.guestbook.model.Entry</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
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

    This defines `Entry` specific actions. By default, a site member can add or
    view an entry, and a guest can only view an entry. 

8.  Save the file. 

This defines permissions at the model level, but you must also define portlet 
permissions. These are managed in the `guestbook-web` module, which contains the 
portlet class. Follow these steps to add the portlet permissions in the 
`guestbook-web` module: 

1.  In `guestbook-web`'s `src/main/resources` folder, create a file called 
    `portlet.properties`. 
   
2.  In this file, place the following property: 

        resource.actions.configs=META-INF/resource-actions/default.xml

3.  Create a subfolder called `resource-actions` in the
    `src/main/resources/META-INF` folder. 

4.  Create a new file in this folder called `default.xml`.

5.  Add the following `DOCTYPE` declaration to the top of the file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

6.  Below the `DOCTYPE` declaration, add the following `resource-action-mapping` 
    tags: 

        <resource-action-mapping>

        </resource-action-mapping>

    You'll define your portlet permissions inside these tags.  

7.  Insert this block of code inside the `resource-action-mapping` tags:

        <portlet-resource>
            <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
            <permissions>
                <supports>
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
                    <action-key>CONFIGURATION</action-key>
                    <action-key>VIEW</action-key>
                </supports>
                <site-member-defaults>
                    <action-key>VIEW</action-key>
                </site-member-defaults>
                <guest-defaults>
                    <action-key>VIEW</action-key>
                </guest-defaults>
                <guest-unsupported>
                    <action-key>ACCESS_IN_CONTROL_PANEL</action-key>
                    <action-key>CONFIGURATION</action-key>
                </guest-unsupported>
            </permissions>
        </portlet-resource>

    This defines the default permissions for the Guestbook Admin portlet. It 
    supports the actions `ACCESS_IN_CONTROL_PANEL`, `CONFIGURATION`, and `VIEW`. 
    While anyone can view the app, guests and site members can't configure it or 
    access it in the Control Panel. Since it's a Control Panel portlet, this 
    effectively means that only administrators are able to access it. 

8.  Below the Guestbook Admin permissions, insert this block of code: 

        <portlet-resource>
            <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
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

    This defines permissions for the Guestbook portlet. It supports the actions 
    `ADD_TO_PAGE`, `CONFIGURATION`, and `VIEW`. Site members and guests get the 
    `VIEW` permission by default. 

9.  Save the file. 

Great job! You've now successfully designed and implemented a permissions scheme 
for your application. Next, you'll create the Java code to support permissions 
in the service layer. 
