# Submitting Your App [](id=submitting-your-app)

## Establish a Marketplace Account [](id=establish-a-marketplace-account)

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

## Upload (Publish) Your App [](id=upload-publish-your-app)

To begin the process of publishing your app, click *Add New App*. A form
appears, allowing you to fill in your app's details.

### Initial App Details [](id=initial-app-details)

The first step is to enter the basic details about your app. 

![Figure 4: The app wizard lets you add details about your app, an icon, and screen shots. Scroll down further to see options for specifying relevant URLs, adding tags, and specifying the editions of Liferay that your app supports.](../../images/marketplace-add-app-details.png) 

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either
yourself (personal) or a company. If you'd like to submit on behalf of a
company, click *Create a company* to go to the *Join A Company* page. From this
page, search to see if your company already exists in the Liferay Marketplace.
If you wish to publish your app on behalf of your company, but your company does
not yet have a Marketplace profile, register your company by clicking *Register
My Company*, filling out the registration form, and submitting the form. 

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

### Upload Files (Plugins) for your App [](id=upload-files-plugins-for-your-app)

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

### Creating your licensing and pricing model [](id=creating-your-licensing-and-pricing-model)

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

### Preview and Submit Your App [](id=preview-and-submit-your-app)

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

## The Review Process [](id=the-review-process)

When you submit apps to the Marketplace, they are reviewed by Liferay
Marketplace staff to ensure that your app meets the minimum standards described
in the previous section *What Are the Requirements for Publishing Apps?*

Liferay ensures that apps meet a minimum set of requirements by performing the
following activities:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Doing basic sanity checking of functionality (e.g., deployment testing, etc.)

Liferay does not do source code reviews and will not ask for your source code.
Further, Liferay is not responsible for the behavior (or misbehavior) of apps on
the Marketplace. For details regarding this, consult the *Liferay Marketplace
User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual
*End User License Agreements* associated with each app.

Note, each of the following changes requires a review by Marketplace staff
before the change is published to the Marketplace:

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

