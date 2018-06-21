# Customizing Personal Sites [](id=customizing-personal-sites)

By default, newly created users are each granted a personal site.

-  Each user functions as the site administrator of his or her personal site.

-  Personal sites are fully customizable but cannot have more than one member. 

-  Users can have publicly available content on the Public Pages of their Site.
    This is often used for a user blog.
   
-  Users can also have Private Pages where they can keep personal information
    or use Documents and Media to have their own private file repository.

You can disable personal sites for your Liferay instance by adding the
following properties to your `portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false

+$$$

**Note:** The public and private page sets of personal sites are handled
separately. You can leave one page set enabled while disabling the other.

$$$

If you initially had user personal sites enabled for your instance but then
disabled them, each existing user's personal site will remain on your Liferay
instance until the next time they log in, at which point it's removed.

You can allow users to create personal sites but not have them automatically
created for new users. To do this, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false

If the properties `layout.user.public.layouts.enabled`,
`layout.user.private.layouts.enabled`, `layout.user.public.layouts.auto.create`,
and `layout.user.private.layouts.auto.create` are all set to `true`, which is
the default, then users will have personal sites and public and private pages
will be automatically created for new users.

There are a number of portal properties you can use to customize the 
automatically created pages. You can customize the names of the default pages, 
the applications that appear on the pages, the themes and layout templates of 
the default pages, and more. Please refer to the [Default User Public Layouts](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html#Default%20User%20Public%20Layouts)
and [Default User Private Layouts](https://docs.liferay.com/portal/7.1/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts)
sections of the `portal.properties` file for details.

+$$$

**Note:** By default, users are able to modify the pages and applications of
their personal sites. Administrators, however, can customize the modifiable
portions of personal sites through @product@'s permissions system by removing
permissions from roles. To disallow all Liferay users from modifying something,
remove the relevant permission from the User role.

$$$

Personal sites are a dynamic feature of @product@. They allow users to manage
and customize their own pages and content on your Liferay instance.
