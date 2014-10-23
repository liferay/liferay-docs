# Initial Administration

Now that you have Liferay Portal running, you can begin setting up your
Intranet. On the left side of your screen is the Sign In portlet. Log in
with the following credentials:

**Login:**test@liferay.com

**Password:**test

The first time you log in, you will be presented with the Terms of Use
page. Scroll down to the bottom of your screen and click the *I Agree*
button. The Terms of Use screen can be disabled or customized later if
you wish. Once you click the button, you will be presented with the
administrative user's personal home page.

This page is accessible only to the Administrator, because that account
has the Power User role. You will proceed first to customize this page
in order to create an account for yourself and make yourself an
Administrator.

All of the square boxes on the page are known as *portlets*. Portlets
are web applications that can be added to a page to provide
functionality. Since Liferay is a portal server, all of the
functionality it provides has been implemented as portlets. The portlets
that are on the administrator's page by default are good for exploring
some of Liferay's functionality, but they are not very useful to
administer Liferay. So we will remove these portlets and add
administrative portlets to the page instead.

Click the red X icon in the top right corner of each portlet window
until you have cleared the page completely. Then move your mouse cursor
up to the Dock, which is in the top right of your browser window,
displaying the message “Welcome Test Test!” When your mouse touches it,
it will expand to show more links. Click the one labeled *Layout
Template*. Choose the *2 Columns 70/30* layout and click *Save*.

Next, go back up to the Dock and click *Add Application*. A categorized
list of applications (implemented as portlets) will appear. Click on the
Admin category to expand it. You will see a list of portlets that can be
added to the page. Drag the *Enterprise Admin* portlet off of the list
and onto the left column of your page and drop it there.

![image](../../images/02-initial-administration_html_5953319e.png)\
*Illustration 1: Dragging and dropping the Enterprise Admin portlet onto
your page.*

Next, drag the *Admin* portlet into the right hand (smaller) column and
drop it there. Finally, open the Community category and drag and drop
the *Communities* portlet into the left column underneath the Enterprise
Admin portlet.

These are all the portlets you will need to do your initial
administration of your new Liferay Portal server. Close the Add
Application window by clicking on the red X in the top right corner. You
should now have a screen that looks like this:

![image](../../images/02-initial-administration_html_9467d4c.png)\
*Illustration 2: Completed page of administrative portlets.*

You are now ready to begin creating your portal!

Let's create the organization whose portal this is. For the purposes of
this document, we'll call it **My Company**. Click the *Organizations*
tab in the Enterprise Admin portlet, and then click the *Add
Organization* button.

![image](../../images/02-initial-administration_html_15fbc90f.png)\
*Illustration 3: Creating My Company.*

Give your organization a name of *My Company* and choose a country and
region for the company. Click *Save*.

You will see that the form has now been expanded to allow you to add all
kinds of information about the company, including email addresses,
mailing addresses, web sites, and more. For now, we won't enter anything
else, but you can always come back and add more data about your company
here.

Click the *Back* tab to go back to the list of organizations. You will
now see the My Company organization in the list. On the right side of
the screen is a button labeled *Actions*. Click this button and then
click the *Add User* link from the list that appears.

A new form will appear allowing you to enter new user information. Use
this form to create an account for yourself in the portal. At a minimum,
you will need to give yourself a screen name, an email address, a first
name, and a last name. Click *Save*. As before, the form will reappear
with more fields, allowing you to enter a lot more data about yourself,
including a picture to use for your avatar. If you have a picture handy,
go ahead and add it.

![image](../../images/02-initial-administration_html_5b0001a3.png)\
*Illustration 4: Adding a new user.*

Most importantly, if you were running Liferay on a server which also was
host to a mail server, you would have received a password from the
system in your inbox. Since it is likely that you don't have a mail
server on the same machine upon which you've installed Liferay, you will
have to change your password so you can log in.

Click the *Password* tab and put the password you want to use in the two
fields, and then click *Save*.

The next thing we want to do is give your account the same
administrative rights that the default administrator account has. Once
we've done that, we can change the password on the default administrator
account so that it is not at the default setting and log out. When we
log back in, we'll log in with your account and build the portal using
your user ID.

Click the *Back* tab and then click the *Roles* tab. Click the *Actions*
button next to the **Administrator** role and click *Assign Members*.
You will see a list of people who are currently in the role (just the
default administrator, as that is the only account in the system). Click
the *Available* tab. Your name will be in the list. Check it and click
the *Update Associations* button. Congratulations! You are now a portal
administrator.

Now we need to change the default administrator's password. Go up to the
Dock and click the *My Account* link. You will see the same user form
you filled out earlier. Click the *Password* tab and change the password
to something other than the default. Once you have done that, you can go
ahead and log out of the portal.

Log back in using your new account. Remember, your login is your email
address, and the password is whatever you set. You will again need to
agree to the terms of use, and then you will be brought to your custom
home page. You are the only person who can access this page. This time,
leave it alone; you probably will want to remove some of the portlets on
this page, but you may also want to keep want some of them (such as the
Calendar). You can decide that later.

Instead, click the *Add Page* link in the top navigation. Name your page
*Admin* and press Enter. The new page will be created. Click on the link
and you will see your new, blank Admin page. As before, use the *Layout
Template* link in the Dock to choose the *2 Columns 70/30* layout, and
add the Enterprise Admin, Communities, and Admin portlets to the page.

You have a few more administrative tasks to do before we can start
building your Intranet. First, we need to make your account a member of
the Guest community. The Guest community ships with Liferay as the home
page of your portal. You will need to be a member of the community for
its links to show up in the Dock when you are logged in. On the
Communities portlet, click the *All Communities* tab. Click the
*Actions* button next to the Guest community and click *Join*.

Now we want to make it so that all newly registered users automatically
become members of the Guest community. To do this, we need to go back to
the Enterprise Admin portlet. Click the *Return to full page* link in
the top right corner of the Communities portlet and then click the arrow
tab in the Enterprise Admin portlet. Next, click the *Settings* tab. On
this page, we can change some global settings for your portal. Change
the Name to mycompany.com and change the Mail Domain to mycompany.com.
Change the Time Zone to your time zone, and then click the *Save*
button.

Next, click the *Default User Associations* tab. In the field on the top
(default community names), put *Guest*and then click the *Save* button.
Now when you register anyone new (or if they register themselves), they
will automatically become members of the Guest community. This allows
them to view both the public and private pages of the community, and
gives them default permissions in the community to do such things as
post to message boards, add documents to a document library, etc. You
can always customize these permissions later.

You are now ready to begin building your Intranet.
