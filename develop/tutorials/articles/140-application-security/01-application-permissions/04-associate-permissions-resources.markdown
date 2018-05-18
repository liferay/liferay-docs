# Associating Permissions with Resources [](id=associating-permissions-with-resources)

Now that you've defined and registered permissions, you must expose the
permissions interface so users can set permissions. 

To allow permissions to be configured for model resources, you must add the
permissions interface to the UI. Add these two Liferay UI tags to your JSP:

1.  `<liferay-security:permissionsURL>`: Returns a URL to the permission
    settings configuration page. 

2.  `<liferay-ui:icon>`: Shows an icon to the user. These are defined in the
    theme and one of them (see below) is used for permissions. 

The Blogs application uses these tags like this: 

        <liferay-security:permissionsURL
                    modelResource="<%= BlogsEntry.class.getName() %>"
                    modelResourceDescription="<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>"
                    resourceGroupId="<%= String.valueOf(entry.getGroupId()) %>"
                    resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                    var="permissionsEntryURL"
                    windowState="<%= LiferayWindowState.POP_UP.toString() %>"
                />

                <liferay-ui:icon
                    label="<%= true %>"
                    message="permissions"
                    method="get"
                    url="<%= permissionsEntryURL %>"
                    useDialog="<%= true %>"
                />

For the `<liferay-security:permissionsURL />` tag, specify these attributes: 

`modelResource`: The fully qualified class name of the entity class. This
class name gets translated into a more readable name as specified in
`Language.properties`. 

`Language.properties`: The entity class in the example above is the Blogs entry
class for which the fully qualified class name is
`com.liferay.blogs.model.BlogsEntry`.

`modelResourceDescription`: You can enter anything that best describes this
model instance. In the example above, the Blog title is used for the model
resource description. 

`resourcePrimKey`: Your entity's primary key. 

`var`: The name of the variable to which the resulting URL string is assigned.
The variable is then passed to the `<liferay-ui:icon>` tag so the permission
icon has the proper URL link.

There's an optional attribute called `redirect` that's available if you want to
override the default behavior of the upper right arrow link. That's it; now your
users can configure the permission settings for model resources! 

You've completed step 3 in *DRAC*. Your next step is to check for permissions in
the appropriate areas of your application. 
