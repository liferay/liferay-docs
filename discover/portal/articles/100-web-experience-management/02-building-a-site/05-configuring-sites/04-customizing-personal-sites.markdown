# Customizing Personal Sites [](id=customizing-personal-sites)

By default, newly created users are granted a personal Site.

-  Users function as Site administrators of their personal Sites.

-  Personal Sites are fully customizable but cannot have more than one member. 

-  Users can have publicly available content on their Site's Public Pages.
   This is often used for a user blog.
 
-  Users can also have Private Pages where they can keep personal information
   or use Documents and Media to have their own private file repositories.

You can disable personal Sites by adding the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false

+$$$

**Note:** The public and private page sets of personal Sites are handled
separately. You can leave one page set enabled while disabling the other.

$$$

If you initially had user personal Sites enabled for your instance but then
disabled them, existing personal Sites remain on your Liferay instance until the
next time users log in, at which point they're removed.

You can allow users to create personal Sites but not have them automatically
created for new users. To do this, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false

If the properties `layout.user.public.layouts.enabled`,
`layout.user.private.layouts.enabled`, `layout.user.public.layouts.auto.create`,
and `layout.user.private.layouts.auto.create` are all set to `true`, which is
the default, users have personal Sites and public and private pages are
created automatically for new users.

There are a number of portal properties you can use to customize the 
automatically created pages. You can customize the names of the default pages, 
the applications that appear on the pages, the themes and layout templates of 
the default pages, and more. Please refer to the 
[Default User Public Layouts](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Default%20User%20Public%20Layouts) 
and 
[Default User Private Layouts](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts)
sections of the `portal.properties` file for details.

+$$$

**Note:** By default, users can modify the pages and applications on their
personal Sites. Administrators, however, can customize the modifiable portions
of personal Sites through @product@'s permissions system by removing permissions
from Roles. To disallow all Liferay users from modifying something, remove the
relevant permission from the User Role.

$$$

