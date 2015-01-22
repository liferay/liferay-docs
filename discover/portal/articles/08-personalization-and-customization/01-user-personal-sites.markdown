# User Personal Sites [](id=user-personal-sites)

By default, newly created users in Liferay are each granted a personal site.
Each user functions as the site administrator of his or her personal site.
Personal sites are fully customizable but cannot have more than one member. The
public pages of personal sites provide a space for users to add content and
applications that they'd like to make accessible to anyone, including guests.
User blogs are often placed on public personal site pages. Content and
applications that users would like to reserve for personal use are often placed
on the private pages of personal sites. For example, each user can add a
Documents and Media portlet to his or her private pages and use it as an online
private file repository.

If you'd like to disable personal sites for your portal, just add the following
properties to your `portal-ext.properties` file:

    layout.user.public.layouts.enabled=false
    layout.user.private.layouts.enabled=false
    
+$$$

**Note:** The public and private page sets of personal sites are handled
separately. You can leave one page set enabled while disabling the other.

$$$

What if you initially had user personal sites enabled for your portal but then
disabled them? Each existing user's personal site remains on your portal until
the next time they log in, at which point it's removed.

You can allow users to create personal sites but not have them automatically
created for new users. To do this, first make sure that
`layout.user.public.layouts.enabled` and `layout.user.private.layouts.enabled`
are not set to `false`. You don't need to explicitly set them to `true`--`true`
is the default. Then add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.auto.create=false
    layout.user.private.layouts.auto.create=false
    
If the properties `layout.user.public.layouts.enabled`,
`layout.user.private.layouts.enabled`, `layout.user.public.layouts.auto.create`,
and `layout.user.private.layouts.auto.create` are all set to `true`, which is
the default, then users will have personal sites and public and private pages
will be automatically created for new users. There are number of portal
properties you can use to customize the automatically created pages. You can
customize the names of the default pages, the portlets that appear on the pages,
the themes and layout templates of the default pages, and more. Please refer to
the Default User Public Layouts and Default User Private Layouts sections of
the `portal.properties` file for details. You can find an HTML version of the
this file here: [http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).

+$$$

**Note:** Prior to Liferay 6.1, administrators could disallow users from being
able to modify the pages and portlets of their personal sites by setting the
following properties:

`layout.user.public.layouts.modifiable=true`
`layout.user.private.layouts.modifiable=true`
    
As of Liferay 6.1, this property is obsolete. However, you can customize the
modifiable portions of personal sites through Liferay's permissions system by
removing permissions from roles. To disallow all portal users from modifying
something, remove the relevant permission from the User role.

$$$

Historically (prior to Liferay 5.1), only power users received personal sites.
Back then, they were called personal communities. If you'd like only power users
to receive personal sites, add the following properties to your
`portal-ext.properties` file:

    layout.user.public.layouts.power.user.required=true
    layout.user.private.layouts.power.user.required=true
    
Personal sites are a dynamic feature of Liferay Portal. They allow users to
manage and customize their own pages and content on your portal. Next, let's
look at how users can customize applications.
