# Custom Fields [](id=custom-fields)

Custom fields appear beneath Portal Settings under the Configuration heading of
the Control Panel. Custom fields are a way to add attributes to many types of
assets and resources in the portal. For example, if you're using Liferay Portal
to create a site for rating books, you might create a custom field called
*Favorite Books* for User resource. If you're using the Wiki for book reviews,
you might add fields called *Book Title* and *Book Author*.

It's possible to add custom fields to following kinds of portal resources:

- Blogs Entry
- Bookmarks Entry
- Bookmarks Folder
- Calendar Booking
- Document
- Documents Folder
- Message Boards Category
- Message Boards Message
- Organization
- Page
- Role
- Site
- User
- User Group
- Web Content Article
- Wiki Page

The ability to add custom fields to any of these resources affords flexibility
to portal developers. For example, suppose you'd like to define a limitation on
the number of users that can be assigned to certain roles. A portal
administrator can create a custom field called *max-users* for the Role
resource. Then a portal developer can create a hook plugin that checks this
field upon user assignment to roles to make sure that there aren't too many
users assigned to the role.

To add a custom field, click on the *Custom Fields* link in the Control Panel.
Then choose a resource, click on the *Edit* link next to it and select *Add
Custom Field*.

![Figure 3.8: You can add custom fields to these portal resources.](../../images/server-configuration-custom-fields-resource-list.png)

From here you need to add the custom field key. The key appears as the label for
the field on the form. For some portal resources (like the User), custom fields
are a separate section of the form. For others, as can be seen above, custom
fields are integrated with the default fields on the form. Additionally,
developers can access custom fields programatically through the
`<liferay-ui:custom-attribute />` tag.

![Figure 3.9: The `book-author` and `book-title` custom fields are integrated with the rest of the form for editing a Wiki page.](../../images/server-configuration-custom-fields-integrated.png)

You can create fields of many different types: text fields (indexed or secret),
integers, selection of multiple values and more. Once you've created a field,
you cannot change its type. However, you can delete custom fields and create new
ones. Next, let's look at how to apply server configurations.
