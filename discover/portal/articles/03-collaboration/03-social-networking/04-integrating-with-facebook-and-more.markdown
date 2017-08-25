# Integrating with Facebook, Twitter, and More [](id=integrating-with-facebook-twitter-and-more)

Social media is a powerful tool that allows people to communicate and share 
thoughts and ideas. Integrating social media features into your site is a no 
brainer. Liferay offers several different ways for you to integrate with social 
media.

## Integrating with Facebook [](id=integrating-with-facebook)

Facebook is currently the number one social network in the world with somewhere
over 1 billion active users. If you're trying to build a community on your site, 
you don't want to neglect a bridge to that many possible users. With that in 
mind, Liferay provides a few easy ways for you to integrate your site with 
Facebook.

### Facebook Sign On [](id=facebook-sign-on)

Like many websites you may visit, any site running on Liferay can be set up to 
use Facebook for sign in. This makes it easier for users to sign in to your 
site, since they won't need to remember another user name and password. See the
[article](/discover/deployment/-/knowledge_base/7-0/facebook-connect-single-sign-on-authentication) 
in the Discover/Deployment section for information on using Facebook to sign 
into Liferay. 

### Using Your Application as Facebook Applications [](id=using-your-portlets-as-facebook-applications)

You can add any Liferay portlet as an application on Facebook. To do this, you
must first get a developer key. A link for doing this is provided to you in the
Facebook tab in any application's Configuration screen. You will have to create 
the application on Facebook and get the key and canvas page URL from Facebook. 
Once you've done this, you can copy and paste their values into the Facebook 
tab. Once you do that, your application is available on Facebook.

This integration enables you to make things like Message Boards, Calendars,
Wikis, and other content on your website available to a much larger audience
(unless you already have a billion users on your site, in which case, kudos to
you). If you're a developer, you could implement your game or other application
on Liferay and then publish it to Facebook.

## Integrating with Twitter [](id=integrating-with-twitter)

Liferay also provides integration with Twitter via the Twitter app on [Liferay
Marketplace](http://liferay.com/marketplace). **Place the Twitter app on your 
private dashboard page.** Once you register your Twitter username with the app, 
your tweets are shown in your Activities application along with your activity 
feed. Make sure that you get the appropriate version of the app for your Liferay 
instance.

There are two ways to register your Twitter username with the app after you 
install it. The first is through the Twitter portlet after you add it to your 
dashboard page. Click the link in the portlet and then enter your Twitter 
username in the Twitter field listed in the *Identification* tab under the 
*Social Network* heading. The second way to register your username is by 
accessing your account settings directly and following the same steps.

![Figure 1: Register your Twitter account.](../../../images/social-network-accounts.png)

It's important to note that your **tweets are displayed only in an Activities 
application.** To view your tweets, **add the Activities application to your 
user public profile page.** Users can then view your profile to see your tweets.
 
The Twitter feed is updated every five minutes by default, so you need to wait a 
few minutes after registering your Twitter username for your tweets to show up.
This value can be changed through System Settings. Go to *Control Panel* &rarr; 
*System Settings*, select the *Other* tab, and click *Twitter Service*. The
default value for the *Twitter synchronization interval* is *5* (5 minutes). Set
this value to *1* to refresh the Twitter feed every minute.

Keep in mind that your tweets are shown in order according to the date and time 
that you originally made them. Therefore, if you haven't tweeted in a while, 
then your tweets might be pushed off the Activities feed by more recent 
activity. You can change the number of items shown in the Activities feed in its 
Configuration.

## Using Social Bookmarks [](id=using-social-bookmarks)

So far, you've learned how to integrate your site with Facebook, but what about 
the other social networks? There's an app for that: Social Bookmarks. When this 
feature is enabled, icons are displayed in a menu or below the content that 
allows users to share their content to Facebook, Twitter, and Google+. This 
feature is enabled by default in the Blogs portlet, and can be enabled for other 
asset-enabled portlets through the *configuration* menu of the portlet or in 
System Settings.

![Figure 1: Social Bookmarks are enabled by default in the Blogs portlet.](../../../images/social-bookmarks.png)

If you want more social bookmarks than come out of the box, be sure to install 
the Social Bookmarks app from the Marketplace (available for [CE](https://web.liferay.com/marketplace/-/mp/application/15194315) and 
[EE](https://web.liferay.com/marketplace/-/mp/application/15188453)). 
The Social Bookmarks app adds the following bookmarks to the default set of 
social bookmarks:

- AddThis (containing 199+ social media sites)
- Compartir
- Delicious
- Digg
- Evernote
- LinkedIn
- Newsvine
- Reddit
- Slashdot
- StumbleUpon
- Tuenti

+$$$

**Note:** The Blinklist and Technorati bookmark links are no longer active, and 
have therefore been removed from the Social Bookmarks app.

$$$
