# Configuring Your Permissions Scheme

@product@'s permissions framework is configured, like Service Builder,
declaratively. You define all your permissions, and then you check for those
permissions in the appropriate places in your code: 

- In the view layer, when showing links or buttons to protected functionality
- In the actions, before performing a protected action
- Later, in your service, before calling the local service

First you must define your permissions scheme. You create this scheme in an XML
file that by convention is called `default.xml` (but you could really call it
whatever you want). Objects in your application (such as `Guestbook`s and
`Entry`s) are defined as *resources*, and *resource actions* manage how users
can interact with those resources. 

You should define the permissions you want first. Look at your application and
think of your use cases. For this app, define it like this:

- The Add Guestbook button should be available only to administrators.

- The Guestbook tabs should be filtered by permissions in case administrators 
  have limited who can see them.

- The Add Entry button should be available only to site members to prevent
  anonymous users from spamming the guestbook. 

- Users should be able to set permissions on their own entries. 

Now you're ready to create the permissions configuration. There are two kinds of
permissions: portlet permissions and resource (or model) permissions. Portlet
permissions protect access to global functions, such as *Add Entry*. If a user
doesn't have permission to access that global function, he or she is missing a
portlet permission. Resource permissions protect access to objects, such as
`Guestbook`s and `Entry`s. A user may have permission to view one `Entry` and to
view and edit another `Entry`, and may not be able to access another `Entry` at
all. This is a resource permission. 

![Figure x: Portlet permissions and resource permissions cover different parts of the application. ](../../images/permission-types.png)

The first thing you must do is tell the framework how to find where your
permissions are defined. You'll first define resource/model permissions in the
module where your model is defined: 

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

4.  Place the following wrapper tags into your default.xml file:

        <resource-action-mapping>

        </resource-action-mapping>

All the rest of the permissions configuration is placed inside these tags.

5.  Next, place the portlet permissions between the `<resource-action-mapping/>` 
    tags:
 
        <model-resource>
            <model-name>com.liferay.docs.guestbook.model</model-name>
            <portlet-ref>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookPortlet</portlet-name>
                <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookAdminPortlet</portlet-name>
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

    This defines the baseline configuration for the Guestbook and Entry entities.
    The supported actions are `ADD_GUESTBOOK` and `ADD_ENTRY`. Site members can
    `ADD_ENTRY` by default, and guests cannot perform either action.

6.  Below that, but above the closing `</resource-action-mapping>` place the
    model package permissions in the file:

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
 
    This defines the Guestbook specific actions, including adding, deleting, 
    updating, viewing, and updating. By default site members and guests can view 
    Guestbooks, but guests cannot update them.

7.  Under that in the file, still above the closing `</resource-action-mapping>` 
    place the permissions for the Entry entity:

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

    This defines Entry specific actions. By default a site member can add or
    view an entry, and a guest can view an entry.

8.  Save the file. 

This defines permissions at the model level, but you also must define
permissions in the portlet module. These are managed in the `guestbook-web`
module where the portlet class resides.

1.  In `guestbook-web`'s `src/main/resources` folder, create a file called 
    `portlet.properties`. 
   
2.  As in the `guestbook-service` project, place in the following property:

        resource.actions.configs=META-INF/resource-actions/default.xml

3.  Create a subfolder called `resource-actions` in the
    `src/main/resources/META-INF` folder. 

4.  Create a new file in this folder called `default.xml`.

5.  Add the following `DOCTYPE` declaration to the top of the file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

6.  Paste in the following wrapper tags:

        <resource-action-mapping>

        </resource-action-mapping>

    Just like in the other `default.xml`, you'll place a few blocks of tags 
    defining your permissions. The service version defined entity permissions.
    These defines portlet permissions.
    
7.  First insert this block:

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

This configuration defines the default permissions for the admin portlet. It
supports the actions `ACCESS_IN_CONTROL_PANEL`, `CONFIGURATION`, and `VIEW`.
While viewing the app is not restricted, accessing it in the Control Panel or
configuring it is never available to guests or site members by default.

8.  Below that, insert this block:

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

This defines permissions for the Guestbook portlet:  `ADD_TO_PAGE`,
`CONFIGURATION`, and `VIEW`. Below that, site members and guests get the `VIEW`
permission by default. 

9.  Save the file.

Great job! You've now successfully designed and implemented a permissions 
scheme for your application. In the next part, you'll create supporting Java 
code: code to support permissions (called resources on the back-end) and helper 
classes for checking permissions.
