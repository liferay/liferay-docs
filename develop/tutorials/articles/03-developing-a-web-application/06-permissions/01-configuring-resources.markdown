# Configuring Your Permissions Scheme

First you need to define your permissions scheme. You create this scheme in the
`default.xml` file. Objects in your application are defined as resources and
resource actions are defined to manage how users can interact with those
resources. But before you can define your permissions you need to point your 
application at the configuration file you'll use to define those permissions.

1. In your `guestbook-service` `src/main/resources` folder, create a file called portlet.properties. 
   
2. In this file, place the following property:

        resource.actions.configs=META-INF/resource-actions/default.xml

This property defines the name and location of your permissions definition 
file. You can, of course, call it anything you want, but this setup follows the
Liferay convention.

Next, create the file itself:

1. In the `META-INF` folder, create a subfolder called `resource-actions`.

2. Create a new file in this folder called `default.xml`.

3. Add the following `DOCTYPE` declaration to the top of the file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

Now the question is how you're going to define your permissions.

IMAGE

Look at your application and think of your use cases. For this app, define it
like this:

- The Add Guestbook button should be available only to administrators.

- The Guestbook tabs should be filtered by permissions in case administrators 
    have limited who can see them.

- The Add Entry button should be available only to site members.

Now you're ready to create your permissions configuration. The configuration 
file is divided into two sections: portlet permissions and various model 
permissions sections, depending on the entities you have in your application. 
First do the portlet permissions.


1. Place the following wrapper tags into your default.xml file:

        <resource-action-mapping>

        </resource-action-mapping>

All the rest of the permissions configuration is placed inside these tags.

2. Next, place the portlet permissions between the `<resource-action-mapping/>` 
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
    
This configuration defines the baseline configuration for the Guestbook and 
Entry entities. The supported actions are `ADD_GUESTBOOK` and `ADD_ENTRY`. Site
member's can `ADD_ENTRY` by default, and guests cannot perform either action.

3. Below that, but above the closing `</resource-action-mapping>` place the model package permissions in the file:

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

4. Under that in the file, still above the closing `</resource-action-mapping>` 
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

This defines Entry specific actions. You have a similar list of actions as 
Guestbook. By default a site member can add or view an entry, and a guest can 
view an entry.

5. Make sure every lines up and save your files.

These are the permission definitions for the entities, but you'll also need to
define permissions for the portlet in particular. These are managed in the 
`guestbook-web` module where the portlet class resides.

1. In your `guestbook-web` `src/main/resources` folder, create a file called 
    portlet.properties. 
   
2. Just like in the `guestbook-service` version, in this file, place the following property:

        resource.actions.configs=META-INF/resource-actions/default.xml

3. Create a subfolder called `resource-actions` in the same folder.

4. Create a new file in this folder called `default.xml`.

5. Add the following `DOCTYPE` declaration to the top of the file:

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action  
        Mapping 7.0.0//EN" "http://www.liferay.com/dtd/liferay-resource-action-mapping_7_0_0.dtd">

6. Place the following wrapper tags into your default.xml file:

        <resource-action-mapping>

        </resource-action-mapping>

    Just like in the other `default.xml` you'll place a few blocks of tags 
    defining your permissions. The service version defined entity permissions.
    These will define portlet permissions.
    
7. First insert this block:

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
While viewing the app is not restricted, accessing it in the Control Panel, or
configuring it is never available to "guests" and not available to site members
by default.

8. Below that, insert this one:

        <portlet-resource>
        <portlet-name>com_liferay_docs_guestbook_portlet_GuestbookWebPortlet</portlet-name>
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


This defines the permissions for the regular Guestbook portlet. You're defining 
three different permissions: `ADD_TO_PAGE`, `CONFIGURATION`, and `VIEW`. Below 
that, you give site members and guests the `VIEW` permission by default. As you 
can see, this is an easy and straightforward way to define permissions.

9. Again, make sure everything lines up and then save your file.

Great job! You've now successfully designed and implemented a permissions 
scheme for your application. In the next part, you'll implement supporting Java 
code: code to support permissions (called resources on the back end) and helper 
classes for checking permissions.
