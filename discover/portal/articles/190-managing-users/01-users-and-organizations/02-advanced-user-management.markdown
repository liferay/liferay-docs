# User Management: Additional Topics [](id=user-management-additional-topics)

The article on Adding and Editing users is short and to the point. It covers
where you find the basic functionality for the more basic user management tasks.
This article isn't complicated, it just collects additional important topics
that go beyond the most basic tasks an administrator must perform. It should be
considered must reading for any portal administrator hoping to get their portal
into production.

## Password Resets [](id=password-resets)

The Add User functionality includes a *Require Password Reset* checkbox at the
bottom of the Password form. The default password policy is set so that even
you, the administrator, cannot deselect this option. As the administrator, however, you
do have the ability to modify the default password policy so that this box
becomes usable. 

1.  Navigate to *Password Policies* in Control Panel &rarr; Users.

2.  Click on the *Default Password Policy*.

3.  Deselect the *Change Required* switcher in the Password Changes section. Now
    you can decide whether each user you add will need to reset their password. 

See the article on Password Policies for more information on editing the default
policy or creating your own.

## Adding an Administrative User [](id=adding-an-administrative-user)

If you're setting up a portal for the first time, you're likely to be
using the default administrator account, the account of one of those famous
Liferay Administrators, *Test Test* or her cousin, *Joe Bloggs*. Set up your own
administrator account for the portal. Add a user with your information, then
give your user account the same administrative rights as the default
administrator's account: 

1.  Click the *Roles* link in the left navigation pane (in the General tab).
    This page of the form shows the roles to which your account is currently
    assigned. No roles appear by default (the user role does not appear since it
    can't be removed). 

2.  Click *Select under Regular Roles and assign the Administrator role to your
    user account. A dialog box pops up with a list of all the regular
    (portal-scoped) roles in the portal. Select the Administrator role from the
    list (click *Choose*). The dialog box disappears and the role is added to
    the list of roles associated with your account. You are now a portal
    administrator. Log out of the portal and then log back in with your own user
    account. 

+$$$

**Power Users:** Users are not assigned the Power User role by default. The
Power User role grants more permissions than the User role. If the User role is
sufficient for you, ignore the Power User role. Alternatively, use it to extend
provide a second level of user permissions and assign it to those users. If
there are certain custom permissions that you'd like all of your portal users to
have, you can grant these custom permissions to the User role. You can also
customize the default roles a new user receives via *Default User Associations*.
This is covered in the article on Instance Settings.

$$$

In production, you should always delete or disable the default administrator
account to secure your portal.

## User Profile Pictures [](id=user-profile-pictures)

Users in @product@ have profile pictures. Administrative users can upload images
in the Edit User form, and any user can update their account information,
including their image, from their personal site (*My account &rarr; Account
Settings*). 

<!-- REPLACE [Figure 5: Upload images for user avatars in the Edit User form.](../../images/users-ray-avatar.png) -->

If no image is explicitly uploaded for a user's profile picture, a default user
icon is assigned as the user avatar. By default the initials of the user are
displayed (First Name then Last Name) over a random color.

![Figure 1: The default user profile picture is an icon with the user initials over a randomly colored bubble.](../../../images/users-default-user-image.png)

If the initials-based approach for generating user profile pictures isn't
suitable for your portal, disable the inclusion of users' initials in the
default icons: 

1.  Navigate to Control Panel &rarr; Configuration &rarr; System Settings

2.  Find the Users category. Click *User Images*.

3.  Deselect *Use Initials for Default User Portrait*.

Now, instead of the default icon, which is a colorful circle containing the
user's initials, the icon is a gray circle containing the approximate shape of a
human being.

![Figure 2: If you disable the default initials-based profile picture, this icon is used instead.](../../../images/user-image-not-initials.png)

This is just the default. To override it with your own default image

1.  Create at least one image that is a 100x100 px square. Place it somewhere on
    the application server's classpath.

2.  Override the values of these 
[portal properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html):
     
        image.default.user.female.portrait=com/liferay/portal/dependencies/user_female_portrait.png
        image.default.user.male.portrait=com/liferay/portal/dependencies/user_male_portrait.png

3.  Restart the application server.

To make the above work, you must place the new image on the classpath of the
application server. For example, in Tomcat you could place images in the
`tomcat/webapps/ROOT/WEB-INF/classes` folder and specify 

    image.default.user.female.portrait=user_female_portrait-override.png
    image.default.user.male.portrait=user_male_portrait-override.png

in a `portal-ext.properties` file. Once you restart Tomcat, the new default
images are used in place of the old ones.

+$$$

**Note:** There's a way to adjust which initials are displayed and in what
order, so you can make the default user icon (with the user initials) work
for your locale. These settings are configured in a 
[Language Settings module](/develop/tutorials/-/knowledge_base/7-1/using-liferays-language-settings), 
so kidnap a friendly developer, give them a cup of coffee, and tell them what
settings you're interested in changing:

`lang.user.default.portrait=initials` sets the type of icon to use for user
avatars. The default value is *initials*. If set to initials, the next property
configures which initials to display, and in what order. Alternatively, specify
*image*, which gives you the same non-initials default image shown above.

`lang.user.initials.fields=first-name,last-name` determines which initials
appear in the user portrait and in what order. The setting here only matters if
`lang.user.default.portrait` is set to *initials*.  Valid values are first name,
middle name, last name, with first and last name as the defaults.

$$$

## Numeric Screen Names [](id=numeric-screen-names)

In prior versions, the ability to use numeric user screen names was disabled out
of the box via the default portal property

    users.screen.name.allow.numeric=false

Other user management systems (LDAP, for example) did not have the same
restriction, which made importing users into @product@ more difficult.
Administrators first had to set the  above property to `true` before importing,
and hope that no screen names conflicted with site IDs. In @product-ver@, this
property defaults to `true` and there's no danger of numeric screen names
conflicting with site IDs:

    users.screen.name.allow.numeric=true

This means you're free to set a user screen name to *97854*, or whatever other
number you can think of, and imports from systems that allow numeric screen
names will go more smoothly. That's everything you need to know to take
advantage of this feature. Keep reading to understand what enabled the change.

Because users have personal sites, the URL to user *97854*'s personal site is

    http://localhost:8080/web/97854

Meanwhile, a default site URL to cleverly named *Test Site* is

    http://localhost:8080/web/test-site

There's no conflict here, but two conditions could easily lead to one:

1.  *Test Site*'s group ID matches the number chosen for the user's screen
    name. Each site has a unique numeric identifier in the database and backend
    code, called group ID. There's nothing stopping it from matching the user's
    numeric screen name, so it could easily be `97854` just like the
    hypothetical user above.

2.  A site administrator comes along and changes the site's friendly URL to
    match its `groupId`. Hello, URL conflict! Now the site's URL matches the
    user's URL:

        http://localhost:8080/web/97854

This conflict is no longer possible. In @product-ver@, a site's friendly URL
is not allowed to be numeric. See for yourself:

1.  Navigate to the site's Configuration &rarr; Site Settings
&rarr; Site URL section.

2.  In the Friendly URL section, enter *97854* and save the form. You'll see
    this failure message if you don't have a user with the matching screen
    name:

        The friendly URL may conflict with another page.

    You'll see this failure message if there's an existing conflict with a user
    screen name:

        Please enter a unique friendly URL. Site [user-first-name user-last-name] has the same friendly URL.

Next, learn about collecting users in organizations.
