# Exporting Portal Applications as Widgets and OpenSocial Gadgets [](id=exporting-portal-applications-as-widgets-and-opensocial-gadgets)

OpenSocial is a framework designed for the creation of socially themed
application programming interfaces (APIs). OpenSocial applications, called
*gadgets*, can be used in any web-based application that supports them. They are
characterized as simple, widely available, and easy to deploy. Gadgets are
especially popular on social networking sites. They can, however, be used in
many different ways throughout your site. 

Liferay allows any OpenSocial gadget to be used on a page. An OpenSocial gadget
is specified in an XML document and consists of embedded HTML and JavaScript.
Liferay allows gadgets to communicate with each other and with portlets. This
allows your gadgets to run seamlessly without your having to constantly check or
update their content. They automatically update based on their connections with
other applications. OpenSocial gadgets support numerous open web technologies
such as *OAuth*, which we'll discuss in more detail later in the chapter.

Gadgets are socially aware and can share data across a community of users. You
can define your own groups and create gadgets to communicate information based
on pages (community/team pages), applications (gadgets/widgets/portlets), data,
users, roles and authorization, and policies. In short, you can develop gadgets
to allow individuals to access and share data within their social networks.

## Adding OpenSocial Gadgets [](id=adding-opensocial-gadgets)

The OpenSocial plugin can be installed through Liferay Marketplace for both
Liferay CE and EE. Installing the OpenSocial plugin enables you to add
OpenSocial gadgets to pages, just like you'd add portlets. There are two types
of gadgets: 

- "Adhoc" gadgets that users can add to a page via URL
- Gadgets published by the Control Panel that are available portal-wide

First, we'll go through steps to add an Adhoc gadget to a page.

### Adding Adhoc Gadgets [](id=adding-adhoc-gadgets)

This method is a quick way to add a gadget to a single page. To do this, go to
the *Add* &rarr; *More* menu and add *OpenSocial Gadget* to the page. The
portlet displays a link to pick a gadget for display:

![Figure 8.12: Configure a gadget to display in your
portlet.](../../images/15-opensocial-1.png)

Click the configure link and a configuration window opens. Next, you need to
insert a URL to an OpenSocial gadget. We'll insert the URL for a colorful
calculator which is:

    http://www.labpixies.com/campaigns/calc/calc.xml

![Figure 8.13: Configuring an adhoc gadget with your portlet is as easy as
pasting the gadget's URL.](../../images/15-opensocial-37.png)

After pasting the URL into the text field, click *Save* and your new gadget is
visible on your page.

![Figure 8.14: The calculator gadget displays seemlessly on your
page.](../../images/15-opensocial-2.png)

This particular gadget allows you to change its "skins" to fit your needs.
Likewise, there are many other user-friendly interactive gadgets that give you
flexibility to fit them into your themed sites. As you find gadgets that would
work nicely throughout your portal, you can publish them for portal-wide use.
You'll learn that next.

### Adding Gadgets for Portal-Wide Use [](id=adding-gadgets-for-portal-wide-use)

You can easily make gadgets available for adding to pages as you would any other
application. We'll demonstrate this by adding a *To-Do List* gadget for
portal-wide use.

1. Go to the Control Panel and select *OpenSocial Gadget Publisher* under the
   *Portal* heading

2. Click *Publish Gadget*

3. Insert the URL for the *To-Do List* gadget:
   `http://www.labpixies.com/campaigns/todo/todo.xml`

4. Select an appropriate category for your gadget

5. Click *Save*

![Figure 8.15: Configure new gadgets with
ease.](../../images/15-opensocial-38.png)

Your *OpenSocial Gadget Publisher* should now look like this:

![Figure 8.16: Publish gadgets for portal-wide use via the OpenSocial Gadget
Publisher.](../../images/15-opensocial-3.png)

Clicking *Actions* next to the gadget enables you to edit, refresh, change
permissions on, or delete the gadget. Here is a brief listing of what these four
buttons do:

*Edit:* allows you to change the URL or category. 

*Refresh:* manually refreshes the gadget cache to reflect changes that have been
made to the gadget that may not currently be displayed in the portlet. 

*Permissions:* gives you the basic *View*, *Update*, *Delete*, and *Permissions*
options for each role on your site. 

*Delete:* removes the listing for the gadget. 

If you navigate to *Add* &rarr; *More* &rarr; *Gadgets*, you should see the
*To-Do List* gadget.

![Figure 8.17: You can conveniently list your gadgets within the *Gadgets*
category.](../../images/15-opensocial-4.png)

In the next section, we will demonstrate how to share OpenSocial gadgets with
other sites.

## Sharing OpenSocial Gadgets [](id=sharing-opensocial-gadgets)

OpenSocial consists of a set of APIs for social networking. Liferay implements
the OpenSocial standard, so you can be assured that your gadgets run on Liferay.
That also means gadgets hosted by a Liferay Portal instance can be deployed and
run in any standard OpenSocial container. It may be beneficial for you to share
gadgets from your Liferay server with other sites, such as iGoogle. Google's
iGoogle lets users customize their own page and add gadgets to their page. Your
Liferay Portal users can share their portlets and other OpenSocial gadgets on
iGoogle or any other OpenSocial-compatible site. Let's try this now.

<!-- Since Google has discontinued iGoogle (see
http://googlesystem.blogspot.com/2012/07/igoogle-will-be-discontinued.html),
this isn't a good example. Can you find another example? -->

For our example, we'll share Liferay's *Loan Calculator* on iGoogle.

1. Add the *Loan Calculator* portlet onto your Liferay page

2. Click the wrench icon in the upper right corner of the portlet and select
   *Configuration*

    ![Figure 8.18: Select the *Configuration*
    button.](../../images/15-opensocial-6.png)

3. Select the *Sharing* tab and the *OpenSocial Gadget* sub-tab

4. Check the box labeled *Allow users to add Loan Calculator to iGoogle*. Also,
   replace "localhost:8080" with the name of your public domain and port.

    ![Figure 8.19: Allow users to add your portlet as an OpenSocial Gadget in
    iGoogle.](../../images/15-opensocial-7.png)

5. Click Save

6. Close out the window and navigate back to the wrench icon in the upper right
   corner of your portlet. There is a new option named *Add to iGoogle*
   available. Click on this button to add your portlet to your iGoogle page.

    ![Figure 8.20: Users simply click the *Add to iGoogle* button to add your
    portlet to their iGoogle page.](../../images/15-opensocial-8.png)

Your portlet is now available on your iGoogle page!

By going through this process, Liferay shared the URL of your portlet to
iGoogle. The URL you provided is unique to your specific instance of the
portlet. This allows you to share multiple instances of the same portlet as
different Google Gadgets.

You could use this sharing capability to let users view what's happening on your
portal at a glance. As you can imagine, you can share all kinds of information
from your portal gadgets and portlets with your various circles of friends,
colleagues, and fellow community members.

## Gadget Personalization [](id=gadget-personalization)

Liferay allows gadgets on your site to be personalized with data from
third-party applications. Some of the third-party sites that authorize users to
access application data include Evernote, Facebook, Google, Netflix,
Photobucket, and Yahoo. Of course, many users feel uncomfortable giving away
their private credentials to access these applications. Fortunately, Liferay
allows you to use *OAuth* to protect your credentials while you access resources
on these sites.

Keep these concepts in mind when going through the OAuth sections:

**Service Provider:** a web application that uses OAuth for access **Protected
Resources:** data controlled by the service provider, which can be accessed by
the gadget through authentication **Consumer Key:** a value used by the gadget
to identify itself to the service provider **Consumer Secret:** a secret the
gadget uses to establish ownership of the consumer key **Request Token:** a
value the gadget uses to obtain user authorization, which is exchanged for an
access token **Access Token:** a value the gadget uses to gain access to the
protected resources on behalf of the user, instead of using the user's service
provider credentials

OAuth is an open standard that authorizes third-party applications to interact
with a user's resources. Users can share their private resources from one site
with another site without supplying typical credentials, such as their user name
and password. OAuth uses request and access tokens as well as a token secret to
authenticate the users of your gadget.

A popular characterization for the OAuth client is the "valet key for your web
services." Let's say you're hosting Liferay Portal and have users and customers
coming to your web site. You want them to have access to a third party resource,
like Twitter, and be able to access their accounts from your site. In the past,
they would have to provide their Twitter user names and passwords, but not if
you use OAuth. OAuth is a "handshake mechanism" where, instead of requiring
personal information, Liferay redirects users to Twitter, where they can tell
Twitter to allow Liferay limited access to their accounts. This example is
similar to our earlier "valet key" characterization. You wouldn't want a valet
driver opening your glove box, storage spaces, hood, and other personal
compartments within your vehicle. You would only want him or her to access
things he or she needs to park your car. OAuth is based on this same idea: it
allows a site just enough information to do what it needs and nothing more. This
assures the user that his personal information is safe but gives him freedom to
take advantage of valuable resources he typically uses from the service
provider's site.

### OAuth Admin Configuration [](id=oauth-admin-configuration)

OpenSocial defines a specification that allows gadgets to incorporate OAuth to
access protected resources from service providers. A brief example is provided
to demonstrate how easy it is to leverage OAuth within gadgets on your site. 

For this example, we'll set up a demo Twitter account gadget using OAuth. First
we must configure your gadget. Follow the steps below to acquire the consumer
key and secret given by the service provider.

1. Similar to previous examples, add the Twitter Demo gadget to your page. Go to
   the Control Panel and click on *OpenSocial Gadget Publisher* under the
   *Portal* heading. Click *Publish Gadget* and insert the Twitter Demo URL:

        http://opensocialdeju.googlecode.com/svn-history/r15/Twitter/TwitterDemo.xml

2. Click *Save*

3. For OAuth-enabled gadgets, you can select the *Manage OAuth* button from the
   *Actions* tab. Select *Manage OAuth* for your Twitter gadget. As shown below,
   you have several options under "twitter" that you must fill in to configure
   your gadget. You must also register your gadget with Twitter to access the
   Consumer Key and Consumer Secret.

    ![Figure 8.21: Twitter allows you to manage OAuth for your Twitter
    gadget.](../../images/15-opensocial-9.png)

4. Go to [https://www.twitter.com](https://www.twitter.com) and, before logging
   in, scroll to the bottom of the page and select *Developers*. Then click
   *Create an app* to begin registering your gadget.

    ![Figure 8.22: Select the *Developers* tab on
    Twitter.](../../images/15-opensocial-10.png)

    ![Figure 8.23: Select *Create an app* from within the *Developers*
    page.](../../images/15-opensocial-11.png)

5. Fill in the *Name*, *Description*, and *Website* fields with what you prefer.

    a. For the *Callback URL* field, enter Liferay's default callback URL:

            http://myLiferayServer/opensocial-portlet/gadgets/oauthcallback

        Replace "myLiferayServer" with an appropriate value -- for this
        demonstration, we'll use 127.0.0.1:8080. 

    b. Finally, select the *Create your Twitter application* tab at the bottom
    of the page.

    ![Figure 8.24: Fill in *Application Details* to setup connectivity between
    your Twitter gadget and your portal.](../../images/15-opensocial-12.png)

6. You are given the OAuth setting that you need to configure your gadget on
   Liferay. Copy the Consumer Key and Consumer Secret to your clipboard.

    ![Figure 8.25: Here are the *Consumer Key* and *Consumer Secret* (values are
    blacked out for security).](../../images/15-opensocial-13.png)

7. Enter your Consumer Key and Consumer Secret under the *Manage OAuth* that you
   navigated to earlier. Also, select `HMAC_SYMMETRIC` for the *Key Type* and
   then click *Save*.

    Note: Liferay offers `PLAINTEXT` and `RSA_PRIVATE` as alternative key types.
    HMAC symmetric and RSA private are commonly used production key types,
    whereas plain text should never be used in real-world settings. 

8. Navigate to the *Settings* tab

    ![Figure 8.26: Configure the settings for your Twitter
    gadget.](../../images/15-opensocial-18.png)

9. Under *Application Type*, select *Read and Write*. Then click *Update this
   Twitter application's settings* at the bottom of the page.

    ![Figure 8.27: Select the *Read and Write* option to enable two way
    communication.](../../images/15-opensocial-19.png)

Congratulations! Your Twitter gadget is now configured with OAuth.

Next, we'll configure the gadget within Liferay Portal.

## Incorporating OAuth Within Your Site [](id=incorporating-oauth-within-your-site)

Now that your gadget is registered with Twitter and is configured with OAuth,
you can add it to your Liferay Portal. The OAuth client you configured in the
previous section allows users to protect their credentials while accessing
resources on your site. For this section, we'll demonstrate how to add the
OAuth-configured gadget to your page.

1. Navigate to *My Private Pages* and click *Add* &rarr; *More...* &rarr;
   *Twitter Gadget*. If your gadget is configured correctly, it should appear
   like this:

    ![Figure 8.28: Your OAuth configured Twitter gadget awaits personalization
    with your Twitter account.](../../images/15-opensocial-14.png)

2. Click on *Personalize this gadget* to be redirected to the service provider.

3. Fill in your Twitter user name and password and select *Authorize app*

    ![Figure 8.29: Authorizing your OpenSocial application to use your account
    is straightforward.](../../images/15-opensocial-15.png)

4. Your Twitter Gadget should now show your last 20 tweets from your timeline.
   Your gadget should look similar to the snapshot below:

    ![Figure 8.30: Check out your Twitter gadget
    timeline!](../../images/15-opensocial-16.png)

5. Using this gadget, you can tweet your current status and have it display on
   your Liferay site and Twitter page. To change the amount of tweets displayed,
   click on the wrench icon in the upper right corner and select
   *Configuration*.  Under the *Setup* tab, you can type the number of tweets to
   display.

    ![Figure 8.31: Configure the number of Tweets to
    display.](../../images/15-opensocial-17.png)

6. Lastly, you can tweet and view your Twitter timeline. The snapshot below
   displays what the Twitter Gadget looks like when tweeting.

    ![Figure 8.32: Here is your Twitter gadget just the way you like
    it!](../../images/15-opensocial-20.png)

As you can see, OAuth is easy to configure and offers users the freedom to
securely add valuable data from third-party sites.

## Creating and Editing OpenSocial Gadgets [](id=creating-and-editing-opensocial-gadgets)

OpenSocial gadgets are XML documents, so as part of Liferay's OpenSocial
integration, a gadget editor is included. The gadget editor is a complete
development environment for gadgets providing syntax highlighting, a preview
function, undo/redo options, and built in tabs for working on multiple gadgets
at once. You can also organize and manage gadgets through a simple file manager
embedded into the portlet. To access the gadget editor, go to the control panel
and click on *OpenSocial Gadget Editor* in the *Content* section.

Once you have created and saved a gadget using the editor, click on the wrench
next to the file to rename, delete, publish or get the URL for your gadget. If
you want to display your gadget somewhere, click *Publish* to choose a category
and display your gadget in the application menu or click *Show URL* to get a URL
to display your gadget on any site that supports OpenSocial.

In addition to the social interactions that you can create on your portal,
Liferay can integrate with some other popular social networks. This enables you
to leverage their power and popularity for your portal's content.
