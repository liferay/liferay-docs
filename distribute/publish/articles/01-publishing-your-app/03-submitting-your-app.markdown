# Submitting Your App [](id=submitting-your-app)

Now that you have everything in order for publishing your app, you can start the
Marketplace app submission process. 

Go to your *Account Home* on 
[liferay.com](http://www.liferay.com). 
In the left side navigation panel of your profile page, there are links to pages 
related to using apps and developing apps. Links to *Apps* and *App Metrics* are 
listed in the *Development* section of the navigation panel. You'll use these 
links heavily during development, so you may want to bookmark this page too. 
Click *Apps* from the *Development* section to access your app development page. 

![Figure 1: Your app development page lists the apps you've developed and enables you to add new apps for publishing to the Marketplace.](../../images/marketplace-my-app-manager.png) 

Now that you know how to get to your app development page, you can submit your
app! 

To begin the process of publishing your app, click *Add New App*. The app wizard
appears, so that you can fill in your app's details.

## Specify Your App's Initial Details [](id=specify-your-apps-initial-details)

From this screen you enter your app's basic details. Previous articles
[Planning Your App's Distribution](/distribute/how-to-publish/-/knowledge_base/how-to-publish/planning-your-apps-distribution)
and
[Preparing Your App](/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app)
helped prepare you for filling in these details. 

The top portion of the app wizard's initial screen is shown in the figure below.

![Figure 2: The app wizard lets you add details about your app, an icon, and screen shots. Scroll down further to see options for specifying relevant URLs, adding tags, and specifying the editions of Liferay that your app supports.](../../images/marketplace-add-app-details.png) 

Here are descriptions of this screen's fields:

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either
yourself (personal) or a company. If you'd like to submit on behalf of a
company, click *Create a company* to go to the *Join A Company* page. From this
page, search to see if your company already exists in the Liferay Marketplace.
If you wish to publish your app on behalf of your company, but your company does
not yet have a Marketplace profile, register your company by clicking *Register
My Company*, filling out the registration form, and submitting the form. 

**App Pricing:** Choose whether you want the app to be free or paid. If you
choose paid, you'll have the option to specify pricing and licensing details
later in the submission process. 

Importantly, you can't change the app from free to paid or from paid to free
once the app is published to the Marketplace. In order to offer the app in the
other license type, you must submit another app under a different name (title).
If you wish to have both free and paid licenses for your app, you must submit
the app under one name for free licenses and submit it under another name for
paid licenses. Make sure to select the license type (i.e., free or paid) that's
best for your app. 

**Title:** Name of your application. 

**Description:** Like the name says, this is a description of your app. You can
put anything you want here, but a good guideline is no more than 4-5 paragraphs.
This field does not allow any markup tags or other textual adornments--it's just
text.

**Icon:** Upload an icon image to represent your app.

**Screen Captures:** Upload one or more screenshots of your app. The screenshots
you upload here are displayed in a carousel of rotating images when your app is
viewed on the Marketplace. 

**Category:** Choose the Marketplace category that most accurately describes
what your app does. Users looking for specific types of apps often browse
categories by clicking on a specific category name in the main Marketplace home
page. Having your app listed under the appropriate category helps them find
your app.

**Developer Website URL:** This is a URL that should reference the web site
associated with the development of the app. For open source apps, this typically
points at the project site where the source is maintained. For others, links to
a home page of information about this app would be appropriate.

**Support URL:** This is a URL that should reference a location where your app's
purchasers or users can get support.

**Documentation URL:** What better way to showcase the amazing capabilities of
your app than to have a live, running version of it for potential buyers to see
and use? This field can house a URL pointing to something as exciting as that
and/or documentation for using your app. 

**Source Code URL:** If you'd to provide a link to the source code of your app,
do so here.

**Labs:** You can denote an app as experimental by checking the appropriate box.

**Security:** If your app does *not* use Liferay's PACL Security Manager, check
the appropriate box. Otherwise, make sure to enable the security manager in your
app by including the setting `security-manager-enabled=true` in the
[`liferay-plugin-package.properties`](http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html)
file in your WAR files and specify a `OSGI-INF/permissions.perm` file in your
module JAR files. 

**Tags:** A set of descriptive words that categorize your app. These tags are
free-form and can help potential purchasers find your app through keyword
searches, tag clouds, and other search mechanisms. You can click on
*Suggestions* to let Marketplace suggest some tags for you based on the data
you've already entered. Click *Select* to select from existing tags, or you can
manually type in new tags. See the *Marketplace Basics* section of this chapter
for detailed requirements for tags.

**EULA:** You can use the default end user license agreement (EULA) or provide
your own. There's a link to the minimum terms which custom EULAs must satisfy.

Remember to visit the articles
[Planning Your App's Distribution](/distribute/how-to-publish/-/knowledge_base/how-to-publish/planning-your-apps-distribution)
and
[Preparing Your App](/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app)
for information to help you decide how best to fill in many of these fields. 

Once you've entered all your app's details, click *Next* to get to the screen
for uploading your app's files. 

## Upload Your App's Plugin Files [](id=upload-your-apps-plugin-files)

On this screen, you must specify your app's version and upload your app's files. 
Note that the article 
[Planning Your App's Distribution](/distribute/how-to-publish/-/knowledge_base/how-to-publish/planning-your-apps-distribution) 
helps you plan your app's versioning scheme. Likewise, the article 
[Preparing Your App](/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app) 
helps you determine which files to upload. 

To start uploading, click *Browse* and select the files that make up your app. 
Each time you add a file, it automatically begins to upload and its 
compatibility information is scanned. You must upload at least one file before 
advancing beyond this screen. Once the files are successfully uploaded, a check 
mark appears next to each plugin, and the plugins are displayed based on their 
compatibility information. 

![Figure 3: Specify a set of files for each Liferay version you wish to support.](../../images/marketplace-app-version-and-upload-files.png) 

If you selected *Free* for your app pricing, click *Next* to advance to the 
final screen. If you selected *Paid*, you'll be presented with additional 
options for licensing and pricing your app. 

## Creating Your Licensing and Pricing Model [](id=creating-your-licensing-and-pricing-model)

Carefully consider which licensing structure best meets your needs. Once your
app has been approved, these options, except for price updates, cannot be
changed. 

**Choose a license term:**

![Figure 4: Choosing license terms for Marketplace apps is easy.](../../images/marketplace-configure-app-license.png)

Choosing *Perpetual* allows the app to continue running without expiration.
Choosing *Non-Perpetual* expires the app's license one year from the purchase
date. Perpetual License also allows you to offer Support Services, which the
customer must renew annually to maintain access to app updates and support. If
you choose not to offer Support Services with a Perpetual License, customers
will be provided with app updates only, whenever updates are available. 

Importantly, you can't change your app's license terms (perpetual or
non-perpetual) once the app is approved. In order to release an approved app
under a different license term you must submit another app under a new name
(title). So make sure you think through the license term that makes the most
sense for your app. 

**Creating license options:**

![Figure 5: You can create multiple license options for your Marketplace apps.](../../images/marketplace-create-license-types.png)

Creating license options allows you to design license bundles and to specify
discounts for customers who purchase more Liferay Instances for your app (a
Liferay Instance or Instance refers to a single installation of the Liferay
Portal). Also you can designate different pricing for Standard Licenses vs.
Developer Licenses. You must specify at least one license option, but no more
than 10 options per type. You'll price these options on the next page. 

You can add or remove bundles (quantities) of your app's license type even after
the app is approved. You must however, always honor any support agreements you
have with current customers that are using bundles you've removed.

**Paid support:** You can offer additional paid support services for your app.
If you select this option, customers can contact you with support requests and
are entitled to regular updates. 

Once an app that offers a support subscription offering is approved, you can't
remove that support subscription offering. You can however, add a support
subscription offering to an approved app. 

**Offer a trial:** You can offer a free 30-day trial of your app, restricted to
one Instance and 25 users. 

When you're finished selecting all the options for your license, proceed to the
next page to determine the app's pricing and availability.

**Pricing:**

![Figure 6: Liferay makes it easy to price your app's license types and specify their availability to countries around the world.](../../images/marketplace-app-pricing.png) 

Based on your selections from the previous page, you'll have price fields for
each license option and for any support option you offered. 

When you have completed your app's pricing and availability, click *Next* to
advance to the app preview screen. 

## Preview and Submit Your App [](id=preview-and-submit-your-app)

The *App Preview* screen lets you preview your app as it will appear on the
Marketplace. If you want to change things before finally submitting your app,
click *Edit* to go back and continue making changes until you're satisfied.

Before finalizing your app's submission, make sure that you're pleased with the
values you've set for the following app details, as these details can't be
modified once the app is approved: 

- Free vs. paid
- License term (perpetual vs. non-perpetual)
- Support subscription offering (cannot be removed)

Once you are satisfied, click *Submit for Review*. 

At this point, the Liferay Marketplace staff starts to review your app and test
it. If you need to make changes to your app during its review or if you're
curious about the rigors of the review process, make sure to read the next
section of this guide.