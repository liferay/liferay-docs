# Developing and Publishing Apps [](id=developing-and-publishing-apps)

Let's jump right in with an example. In this section, we'll walk you through the
creation and publication steps (but you won't actually publish the app on the
Marketplace, since this example app isn't very useful!). After this, you should
understand typical Marketplace app development.

## Develop a Sample App

Before you can publish anything, you first have to create (develop) an app!
Since apps are nothing more than collections of individual plugins, your first
step in developing a Marketplace app is to develop the functionality in the form
of one or more Liferay plugins. To create a sample app that contains a single
portlet, you can follow the detailed instructions from the learning path
[Beginning Liferay Development](/develop/learning-paths/-/knowledge_base/6-2/beginning-liferay-development).
After creating and deploying your sample app, return here to continue.

In the real world, apps usually consist of multiple components (e.g., multiple
`.war` file plugins), are spread across multiple plugin types, and present
non-trivial functionality which in many cases requires some configuration. How
these advanced tasks are dealt with is out of scope for this section, but some
tips and considerations for Marketplace development can be found in the sections
that follow.

## Specify App Packaging Directives

When publishing your app, each plugin you upload is packaged into one or more
*packages* for each Liferay release you intend to support. When you upload your
plugins to the Liferay Marketplace, your app is scanned, and the embedded
packaging directives you have specified are extracted and used to create
different downloadable *packages* of your app for different Liferay releases.
You must insert this information into each plugin in your app before you can
publish it to the Marketplace.

The packaging directives are related to the Liferay releases with which your app
is compatible. In order to specify which release of Liferay your app is
compatible with (and therefore which packages should be created for eventual
download on the Marketplace), you first need to understand how Liferay releases
are named and how they relate to the underlying Liferay release version. Details
can be found in 
[Understanding Liferay's Releases](/discover/portal/-/knowledge_base/6-2/understanding-liferays-releases). 
Accordingly, Liferay 6.2 CE GA1 is designated as version `6.2.0`. CE GA2 is then
`6.2.1`, and so on. Liferay 6.2 EE GA1 is designated as `6.2.10`. EE versions 
follow a slightly different policy given the presence of fix packs and service
packs, so 6.2 EE GA2 will be `6.2.20`.

For each plugin that makes up your app, packaging directives must be placed in
the `liferay-plugin-package.properties` file (in the `WEB-INF/` directory of
your plugin's `.war` file). In this file, you must specify a comma-separated
list of Liferay releases with which your app is compatible and for which
packages should be generated using the `liferay-versions` keyword. Marketplace
creates packages that contain your plugins based on these packaging
directives (and groups them together as each plugin is uploaded). You should
specify CE versions first, followed by EE versions, using this form:
`liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE` are replaced with
the corresponding Liferay Releases with which your app is compatible).

+$$$

**Note:** If your app is compatible
 with both CE and EE, you must specify a set of versions for both CE and EE
 releases. If you only specify compatibility with CE, then your app will not be
 compatible with (and will fail to deploy to) any EE release.

$$$
 
For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA3 (and later), and 6.1 EE GA3 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.2+,6.1.30+

This means that the app works with any 6.1 CE release starting with CE GA3
and 6.1 EE releases starting with EE GA3. Marketplace creates two packages:
one that is compatible with the 6.1 CE GA3 release and *later*, and another that
is compatible with 6.1 EE GA3 release and *later*.

+$$$

**Note:** Any CE or EE versions you
 include in your packaging directives *must* be terminated with a version using
 the `+` symbol.  This ensures that your app will be deployable onto future
 versions of Liferay (but does not guarantee your app will work in future
 versions). So, `liferay-versions=6.1.1,6.1.2` doesn't work, but
 `liferay-versions=6.1.1,6.1.2+` does. Similarly,
 `liferay-versions=6.1.2+,6.1.30,6.1.31` doesn't work (as the EE versions are
 not properly terminated), but `liferay=versions=6.1.2+,6.1.30,6.1.31+` does.

$$$

Here are some additional examples:

    # works with Liferay 6.1 CE and EE GA3 and later (NOT compatible with 6.1
    # CE or EE GA2).  This is most likely what you want to use.
    liferay-versions=6.1.2+,6.1.30+

    # works with Liferay 6.1 CE GA2, GA3, and GA5 (but not GA4), and EE GA2
    # and later
    liferay-versions=6.1.1,6.1.2,6.1.4+,6.1.20+
    
    # works with Liferay 6.1 EE GA3 and later (NOT compatible with CE)
    liferay-versions=6.1.30+

You may find it advantageous to implement one of your app's plugins in multiple
ways, customizing that plugin for different Liferay releases. We'll illustrate
this with an example. 

### Example App: Using Different Versions of a Hook

Suppose your app consists of two plugins: a portlet and a hook. The portlet uses
standard API calls that work on all Liferay 6.1 releases. Your hook, on the
other hand, needs to interact with EE GA3 differently than it does with CE GA3,
because you want the hook to take advantage of an exclusive EE feature. For your
app, how do you provide one version of your hook plugin for EE and another
version of it for CE, while applying your portlet plugin to both EE and CE? 

It's easy. In this case, you'd specify versions
`liferay-versions=6.1.2+,6.1.30+` for your portlet plugin, indicating that it is
compatible with CE GA3 and later, as well as EE GA3 and later. As for your hook
plugin, you'd create and build *two* versions of it, one version of the hook to
use with Liferay EE and the other version of the hook to use with Liferay CE.
You'd specify `liferay-versions=6.1.30+` for your EE hook and
`liferay-versions=6.1.2+` for your CE hook. The EE hook would work exclusively
with EE GA3 and later, while the CE hook would work exclusively with CE GA3 and
later. You might think that it's difficult to arrange the packaging for an app
that has plugins targeted to different Liferay releases, but it's easy.
Marketplace takes care of it based on the `liferay-versions` values you
specified for each plugin. We'll talk about that next.

### Marketplace Packages Your App's Plugins

When you upload your app's plugins as demonstrated later, you'll notice that
Marketplace groups them into separate packages based on the respective releases
each plugin supports. Marketplace copies a plugin into each of the release
packages corresponding to its list of `liferay-versions` values.  If Marketplace
cannot verify the version of Liferay the plugin supports, it rejects the plugin.
For example, if you specify `liferay-versions=1.0.0+` for your plugin--perhaps
because you are confident it can work on any Liferay release--Marketplace
rejects it, because it doesn't know of a `1.0` release of Liferay. So take care
to specify the Liferay version information correctly for each your app's plugins. 

Now that you've developed your app and specified its packaging directives, it's
time to get it to the Marketplace! 

## Establish a Marketplace Account

Before you can publish anything to the Marketplace, you must first have an
account on [liferay.com](http://liferay.com). If you do not have an account,
visit [http://liferay.com](http://liferay.com) and click *Register* in the
upper-right corner of the screen. After you've registered, you can visit the
Marketplace at <http://liferay.com/marketplace>. The Marketplace home page is
shown below:

![Figure 1: The Marketplace home page is where users go to find new and interesting apps. ](../../images/marketplace-homepage.png) 

This where users go to find new and interesting apps. Since you'll visit here
often during the course of development, you may want to bookmark it now. 

You can publish Marketplace apps as an individual or as part of a company.
Before you can submit apps to the Marketplace, you must register yourself as an
app developer. It's easy. Click
[*Become a Developer*](https://www.liferay.com/marketplace/become-a-developer) in the
*MARKETPLACE* column on the left. You're now in the Marketplace registration
wizard. If you're registering for a company and the company is already
registered, you can search for it from these Marketplace registration screens
and request to *join* the company in publishing apps to the Marketplace. On
completing the Marketplace registration, Liferay sends you an email confirming
your acceptance as a Marketplace Developer--congratulations!

Now that you're a Marketplace Developer, options for adding new apps and viewing
your published apps are available to you from your User Profile. Let's go there
now. In the upper right corner on <http://www.liferay.com> select your picture
&rarr; *User Profile*. 

![Figure 2: Select your picture &rarr; *User Profile* on liferay.com to access your app development pages.](../../images/liferay-dot-com-user-profile.png) 

In the left side navigation panel of your profile page, there are links to pages
related to using apps and developing apps. Links to *Apps* and *App Metrics*
are listed in the *Development* section of the navigation panel. You'll use
these links heavily during development, so you may want to bookmark this page
too. Click *Apps* from the *Development* section to access your app development
page. 

![Figure 3: Your app development page lists the apps you've developed and enables you to add new apps for publishing to the Marketplace.](../../images/marketplace-my-app-manager.png) 

Now that you know how to get to your app development page, let's publish an app! 

## Upload (Publish) Your App

To begin the process of publishing your app, click *Add New App*. A form
appears, allowing you to fill in your app's details.

### Initial App Details

The first step is to enter the basic details about your app. 

![Figure 4: The app wizard lets you add details about your app, an icon, and screen shots. Scroll down further to see options for specifying relevant URLs, adding tags, and specifying the editions of Liferay that your app supports.](../../images/marketplace-add-app-details.png) 

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either
yourself (personal) or a company. If you'd like to submit on behalf of a
company, click *Create a company* to go to the *Join A Company* page. From this
page, search to see if your company already exists in the Liferay Marketplace.
If you wish to publish your app on behalf of your company, but your company does
not yet have a Marketplace profile, register your company by clicking *Register
My Company*, filling out the registration form, and submitting the form. 

Publishing on behalf of yourself is the default. When you publish on behalf of
yourself, your name appears in the Marketplace as the Publisher/Author. You are
the only one who can manage your personal app (add new releases to it, add new
versions of it, edit its details, etc.). 

Publishing on behalf of a company effectively hands the keys over to the
administrators of the company. The app only appears on the company's Marketplace
app development page. Company admins have the same permission you have to manage
this app (add new releases, new versions, edit details, etc). The app appears to
be authored/developed by the company, not you personally. It also appears on the
company's public profile page under its list of apps.

**App Pricing:** Choose whether you want the app to be free or paid. If you
choose paid, you'll have the option to specify pricing and licensing details
later in the submission process. Once your app is published to the Marketplace,
you cannot change this option. If you wish to have both free and paid licenses
for your app, you must submit one version of your app for free licenses and
another version of your app for paid licenses. 

**Title:** The name of your application. Arguably the most important detail of
your app, the name of your app should be a good title that conveys the function
of the app but is not overly wordy or misleading. Choosing a good name for your
app is key to its success, so choose wisely! Do not include versions in the
title unless it is a vital part of the name, for example *Angry Birds 2: More
Anger*. Each app on the Marketplace must have a unique name.

**Description:** Like the name says, this is a description of your app. You can
put anything you want here, but a good guideline is no more than 4-5 paragraphs.
This field does not allow any markup tags or other textual adornments--it's just
text.

**Icon:** The icon is a small image representation of the app. See the
*Marketplace Basics* section of this chapter for detailed requirements for
icons.

**Screen Captures:** You can supply multiple screenshots of your app in action.
The screenshots you upload here are displayed in a carousel of rotating images
when your app is viewed on the Marketplace. See the *Marketplace Basics*
section of this chapter for detailed requirements for screen captures.

**Category:** Choose the Marketplace category that most accurately describes
what your app does. Users looking for specific types of apps often browse
categories by clicking on a specific category name in the main Marketplace home
page. Having your app listed under the appropriate category helps them find
your app.

**Developer Website URL:** This is a URL that should reference the web site
associated with the development of the app. For open source apps, this typically
points at the project site where the source is maintained. For others, links to
a home page of information about this app would be appropriate.

**Support URL:** This is a URL that should reference a location where
purchasers or users of the app can get support for the app.

**Documentation URL:** What better way to showcase the amazing capabilities of
your app than to have a live, running version of it for potential buyers to see
and use? This field can house a URL pointing to exactly that.

**Source Code URL:** If you'd to provide a link to the source code of your app,
do so here.

**Labs:** You can denote an app as experimental by checking the appropriate box.

**Security:** If your app does *not* use Liferay's PACL Security Manager, check
the appropriate box. Otherwise, make sure to enable the security manager in your
app by including the setting `security-manager-enabled=true` in your
[`liferay-plugin-package.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
file. 

**Tags:** A set of descriptive words that categorize your app. These tags are
free-form and can help potential purchasers find your app through keyword
searches, tag clouds, and other search mechanisms. You can click on
*Suggestions* to let Marketplace suggest some tags for you based on the data
you've already entered. Click *Select* to select from existing tags, or you can
manually type in new tags. See the *Marketplace Basics* section of this chapter
for detailed requirements for tags.

**EULA:** You can use the default end user license agreement or provide your
own. There's a link to the minimum terms which custom EULAs must satisfy.

**Liferay Only:** If you're publishing a CE-only or EE-only app, select *CE
Only* or *EE Only* in the *Product Type* selector. If you're providing an app
that runs on both CE and EE, just flag the *Liferay EE Plugin* check box. If
you're uploading a bug fix or would like to replace a previous version of your
app, specify the app entry IDs of the apps to be replaced by the new app in the
*Supercedes App Entry IDs* field.

Make up some sample data to use during this example and enter it into the form.
Once you have entered all your app's details, click *Next* to move on to the
next screen.

### Upload Files (Plugins) for your App

On this screen, you must specify the version of your app and upload its plugin
files. Review the previous section *What is a Version?*, to decide on a good
version specifier and enter it here. For our example, since this is the first
version, enter `1.0`. 

Then upload the different sets of plugin files (variations) to support different
Liferay versions you're targeting. You must upload at least one plugin file
before advancing beyond this screen, so click the *Browse* button and select
the plugins that make up your app. Each time you add plugins to the list, they
begin uploading automatically, and their compatibility information is scanned
(read the previous sections in this chapter to understand what compatibility
information is read from your plugins). 

![Figure 5: Specify a set of files for each version of Liferay Portal you wish to support.](../../images/marketplace-app-version-and-upload-files.png)

As a more complicated example, consider an app that consists of a hook and
a portlet. The portlet works across all Liferay releases, but the hook is built
separately for CE and EE. In this case, you would upload 3 plugins that make up
the app: one portlet plugin for all releases, one hook plugin for CE, and one
hook plugin for EE. Once the files are uploaded, a check mark appears next to
each plugin, and the plugins are displayed based on their compatibility
information. This indicates that the files were successfully uploaded. The
portlet plugin is automatically copied for use in both the EE and CE variations,
even though you only uploaded the portlet plugin once. 

If you selected *Free* for your app pricing, click *Next* to advance to the
final screen. If you selected *Paid*, you'll be presented with additional
options for licensing and pricing your app. 

### Creating your licensing and pricing model

Carefully consider which licensing structure best meets your needs. Once your
app has been approved, these options, except for price updates, cannot be
changed. 

**Choose a license term:**

![Figure 6: Choosing license terms for Marketplace apps is easy.](../../images/marketplace-configure-app-license.png)

Choosing *Perpetual* allows the app to continue running without expiration.
Choosing *Non-Perpetual** expires the app's license one year from the
purchase date. Perpetual License also allows you to offer Support Services,
which the customer must renew annually to maintain access to app updates and
support. If you choose not to offer Support Services with a Perpetual License,
customers will be provided with app updates only, whenever updates are
available. You cannot change your app's license terms once the app is approved. 

**Creating license options:**

![Figure 7: You can create multiple license options for your Marketpalce apps.](../../images/marketplace-create-license-types.png)

Creating license options allows you to design license bundles and to specify
discounts for customers who purchase more Liferay Instances for your app (a
Liferay Instance or Instance refers to a single installation of the Liferay
Portal). Also you can designate different pricing for Standard Licenses vs.
Developer Licenses. Developer Licenses are limited to 10 unique IP addresses.
You must specify at least one license option, but no more than 10 options per
type. You'll price these options on the next page. 

**Paid support:** You can offer additional paid Support Services for your app.
If you select this option, customers can contact you with support requests and
are entitled to regular updates. 

**Offer a trial:** You can offer a free 30-day trial of your app, restricted to
one Instance and 25 users. 

When you're finished selecting all the options for your license, proceed to the
next page to determine the app's pricing and availability.

**Pricing:**

![Figure 8: Liferay makes it easy to price your app's license types and specify their availability to countries around the world.](../../images/marketplace-app-pricing.png) 

Based on your selections from the previous page, you'll have price fields for
each license option and for any support option you offered. 

Choose the currency to use with the pricing options and then fill in the price
fields accordingly. Fill in the renewal cost for any support services you
offered. The support services price is per instance, so if you entered $100 USD
and the customer is running 10 instances, their annual support services renewal
cost would be $1000. Note: This only applies to perpetual licenses. For
non-perpetual licenses, you should include any support services cost in the
annual license price. 

Once you've specified the prices, you can add the desired countries to this box.
If you wanted to specify different prices for different regions, add a new table
and complete the fields as desired. 

Although Liferay Marketplace supports major currencies and a broad list of
countries, not all currencies and countries are currently available. Additional
currencies and countries may become available at a later time. 

When you have completed your app's pricing and availability, click *Next* to
advance to the final screen. 

### Preview and Submit Your App

Whenever you make a change (app details, adding files, adding new versions), you
always wind up at the *App Preview* screen. This allows you to preview your app
as it will appear in the Marketplace, so you can confirm your changes.

![Figure 9: Always preview your app before submitting it. You may see changes here that you want to make before you submit it.](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information. Is it as you expect? If not, click
*Edit* to go back and continue making changes until you are satisfied.

Once you are satisfied, click *Submit for Review*. If you're walking through
this example on Liferay's Marketplace, don't do it, since this is only an
example app. The next section describes what happens when you submit apps or app
changes.

## The Review Process

When you submit apps to the Marketplace, they are reviewed by Liferay
Marketplace staff to ensure that your app meets the minimum standards described
in the previous section *What Are the Requirements for Publishing Apps?*

Each of the following changes requires a review by Marketplace staff before the
change is published to the Marketplace:

- Submitting a new app
- Changing details of an app (for example, changing the description or the
  screenshots)
- Adding a new package (set of files) to an existing app, in order to support
  more Liferay releases
- Adding a new version of an existing app

While your submitted change is under review, you can view the status of your
change by visiting *Home* &rarr; *Apps*. You can also cancel your submission by
clicking *Cancel Submission* on the *App Preview* screen for each app.

Once your app is approved by Marketplace staff, congratulations! You will
receive an email confirmation and at that moment, your app is available on
Marketplace. The app is also shown on your public Profile page, which lists all
apps that you have personally developed and published.

If your app is rejected, an email is sent to the email address associated with
the app, along with a note explaining the reasons for rejection. At that point,
you can make the requested changes, and re-submit the app for approval.

Now that you have successfully published your first app, you'll likely get all
kinds of feedback from users and yourself about what's right and wrong with it.
In the next section, we'll explore how to make changes once you have published
your app.

