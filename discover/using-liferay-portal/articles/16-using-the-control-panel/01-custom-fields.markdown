# Custom Fields [](id=custom-fields)

Custom fields appear beneath Portal Settings in the Portal section of the
control panel. Custom fields are a way to add attributes to many types of assets
in the portal. For example, if you're using Liferay Portal to create a site for
rating books, you might assign the User object a custom field called Favorite
Books. If you're using the wiki for book reviews, you might add fields for Book
Title and Book Author.

To add a custom field, click on the *Custom Fields* link in the control panel.
Then choose a resource, click on the *Edit* link next to it and select *Add
Custom Field*.

![Figure 16.4: Custom Fields Resource
List](../../images/server-configuration-custom-fields-resource-list.png)

From here you will need to add the custom field key. The key appears as the
label for the field on the form. For some portal assets (like the User), custom
fields are a separate section of the form. For others, as can be seen above,
custom fields are integrated with the default fields on the form. Additionally,
developers can access custom fields programatically through the
`<liferay-ui:custom-attribute />` tag.

![Figure 16.5: Custom Fields Integrated with the Rest of the Fields on the Wiki
Form](../../images/server-configuration-custom-fields-integrated.png)

You can create fields of many different types: text fields (indexed or secret),
integers, selection of multiple values and more. Once you've created a field,
you cannot change its type.
