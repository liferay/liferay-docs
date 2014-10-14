# Developing and publishing apps [](id=developing-and-publishing-apps)

Let's jump right in with an example. In this section, we'll walk you through the
creation and publication steps (but we won't actually publish the app on the
Marketplace, since this example app isn't very useful!). After walking through
this, you should understand how Marketplace App development typically occurs.

## Develop a sample app [](id=develop-a-sample-app)

Before you can publish anything, you first have to create (develop) an app! Apps
are nothing more than collections of individual plugins, so the first step in
development an app for the Marketplace is to develop the functionality in the
form of one or more Liferay plugins. To create a sample app (which will consist
of a single portlet), follow the detailed instructions in the *Portlet
Development* chapter of this guide. After creating and deploying your sample
app, return here to continue.

In the real world, apps usually consist of multiple components (e.g. multiple
`.war` file plugins), are spread across multiple plugin types, and present
non-trivial functionality which in many cases requires some configuration. How
these advanced tasks are dealt with is out of scope for this section, but some
tips and considerations for Marketplace development can be found later in this
chapter.

## Specify App Packaging Directives [](id=specify-app-packaging-directives)

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
can be found on the [Versioning Policy
Wiki](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+Versioning+Policy).
Accordingly, Liferay 6.1 CE GA1 is designated as version `6.1.0`. CE GA2 is then
`6.1.1`, and so on. Liferay 6.1 EE GA1 is designated as `6.1.10`. EE versioning
follows a slightly different policy given then presence of fix packs and service
packs, so 6.1 EE GA3 is `6.1.30`.

For each plugin that makes up your app, packaging directives must be placed in
the `liferay-plugin-package.properties` file (located in the `WEB-INF/`
directory of your plugin's `.war` file). Within this file, you must specify a
comma-separated list of Liferay releases with which your app is compatible, and
for which packages should be generated, using the `liferay-versions` keyword.
Marketplace will create packages that contain your plugins based on these
packaging directives (and will intelligently group them together as each plugin
is uploaded).  You should specify CE versions first, followed by EE versions,
using this form: `liferay-versions=CE,CE,CE+,EE,EE,EE+` (where `CE` and `EE`
are replaced with the corresponding Liferay Releases with which your app is
compatible).

+$$$

**Note:** If your app is compatible with both CE and EE, you must specify a set
of versions for both CE and EE releases. If you only specify compatibility with
CE, then your app will not be compatible with (and will fail to deploy to) any
EE release.
 
$$$

For example, to specify that a particular plugin in your app is compatible with
Liferay 6.1 CE GA3 (and later), and 6.1 EE GA3 (and later), add this line to
your `liferay-plugin-packages.properties` file:

    liferay-versions=6.1.2+,6.1.30+

This means that the app works with any 6.1 CE release starting with CE GA3, and
and 6.1 EE release starting with EE GA3. Marketplace will create two packages,
one that is compatible with the 6.1 CE GA3 release and *later*, and another that
is compatible with 6.1 EE GA3 release and *later*.

+$$$

**Note:** Any CE or EE versions you include in your packaging directives *must*
be terminated with a version using the `+` symbol. This ensures that your app
will be deployable onto future versions of Liferay (but does not guarantee your
app will work in future versions). So, `liferay-versions=6.1.1,6.1.2` will not
work, but `liferay-versions=6.1.1,6.1.2+` will work. Similarly,
`liferay-versions=6.1.2+,6.1.30,6.1.31` will not work (as the EE versions are
not properly terminated), but `liferay=versions=6.1.2+,6.1.30,6.1.31+` will
work.

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

### Example App: Using Different Versions of a Hook [](id=example-app-using-different-versions-of-a-hook)

Suppose your app consists of two plugins: a portlet and a hook. The portlet uses
standard API calls that work on all Liferay 6.1 releases. Your hook, on the
other hand, needs to interact with EE GA3 differently than it does with CE GA3,
because you want the hook to take advantage of an exclusive EE feature. For your
app, how do you provide one version of your hook plugin for EE and another
version of it for CE, while applying your portlet plugin to both EE and CE? 

It's easy. In this case, you'd specify versions
`liferay-versions=6.1.2+,6.1.30+` for your portlet plugin, indicating that it is
compatible with CE GA3 and later, and EE GA3 and later. As for your hook plugin,
you'd create and build *two* versions of it, one version of the hook to use with
Liferay EE and the other version of the hook to use with Liferay CE. You'd
specify `liferay-versions=6.1.30+` for your EE hook and
`liferay-versions=6.1.2+` for your CE hook. The EE hook would work exclusively
with EE GA3 and later, while the CE hook would work exclusively with CE GA3 and
later. You might think that it's difficult to arrange the packaging for an app
that has plugins targeted to different Liferay releases, but it's easy.
Marketplace takes care of it based on the `liferay-versions` values you
specified for each plugin. We'll talk about that next.  

### Marketplace Packages Your App's Plugins [](id=marketplace-packages-your-apps-plugins)

When you upload your app's plugins, as demonstrated later on in this chapter,
you'll notice that Marketplace groups them into separate packages based on the
respective releases each plugin supports. Marketplace copies a plugin into each
of the release packages corresponding to its list of `liferay-versions` values.
If Marketplace cannot verify the version of Liferay the plugin supports, it
rejects the plugin. For example, if you specify `liferay-versions=1.0.0+` for
your plugin--perhaps because you are confident it can work on any Liferay
release--Marketplace will likely reject it, because it doesn't know of a `1.0`
release of Liferay. So take care in specifying the Liferay version information
for each your app's plugins. 

Now that you've developed your app and specified its packaging directives, it's
time to get it to the Marketplace! 

## Establish a Marketplace Account [](id=establish-a-marketplace-account)

Before you can publish anything to the Marketplace, you must first have an
account on [liferay.com](http://liferay.com). If you do not have an account,
visit [http://liferay.com](http://liferay.com) and click *Register* in the
upper-right corner of the screen. Once you have registered, you can visit the
Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).
The Marketplace home page is shown below:

![Figure 15.1: The Marketplace home page is where users go to find new and interesting apps. ](../../images/marketplace-homepage.png) 

This is the front page of the Marketplace and is where users go to find new and
interesting apps. You'll visit here often during the course of development, so
it might be a good idea to bookmark it now. To get started, the first thing you
want to do is visit your personal *Home* page. There is a thin strip at the top
of your browser window. This is known as the *Dockbar*. Many links are present
in the drop-down menus of the Dockbar, including a link titled *Go to My Home*.
This is a link to your personal home page. More details about what you can find
on your personal home and profile pages can be found in the Liferay Marketplace
chapter of the *User Guide*. For now, go to your personal home page by clicking
on the *Go to My Home* link.

![Figure 15.2: Use the My Home Page link from anywhere in Liferay to navigate to your personal pages. ](../../images/marketplace-my-homepage-link.png) 

Your home page contains links to often-used functionality of liferay.com,
including app creation and management. There are several links on the left of
your home page, including one titled *App Manager*. This link allows you to
manage the apps that you have either purchased (or have been purchased for use
in companies you are associated with) or apps that you or your company have
developed. You'll use this page heavily, so a bookmark would be useful here.
Click *App Manager* to visit this page.

![Figure 15.3: The App Manager lets you maintain everything about apps you've purchased or published.](../../images/marketplace-my-app-manager.png) 

You'll notice three tabs across the top:

**Purchased**: This shows apps you have personally purchased, and those apps
that have been purchased on behalf of companies you are associated with.

**Apps**: This shows apps you have personally *published*.

**Add an App**: This screen begins the process of publishing a new app.

Since you have not purchased or published any apps, the first two tabs are
likely empty. Let's get publishing!

## Upload (Publish) your app [](id=upload-publish-your-app)

To begin the process of publishing your app, click *Add an App*. A form appears,
allowing you to fill in your app's details.

### Initial app details [](id=initial-app-details)

The first step is to enter the basic details about your app. 

![Figure 15.4: Add all the details about your app, including tags, categories, and links to your site.](../../images/marketplace-add-app-details.png) 

This screen allows you to enter basic details about the app you are publishing.

**Name:** The name of your application. Arguably the most important detail of
your app, the name of your app should be a good title that conveys the function
of the App but is not overly wordy or misleading. Choosing a good name for your
app is key to its success, so choose wisely! See the *Marketplace
Considerations* chapter for more guidance on how to pick good names. Do not
include versions in the title unless it is a vital part of the name, for example
*Angry Birds 2: More Anger*. Each app on the Marketplace must have a unique
name.

**Description:** Like the name says, this is a description of your app. You can
put anything you want here, but a good guideline is no more than 4-5 paragraphs.
This field does not allow any markup tags or other textual adornments--it's just
text.

**Icon:** The icon is a small image representation of the app. See the
*Marketplace Basics* section of this chapter for detailed requirements for
icons.

**Screen Captures:** You can supply multiple screenshots of your app in action.
The screenshots you upload here are displayed when your app is viewed on the
Marketplace, using a carousel of rotating images. See the *Marketplace Basics*
section of this chapter for detailed requirements for screen captures.

**Tags:** A set of descriptive words that categorize your app. These tags are
free-form and can help potential purchasers find your app through keyword
searches, tag clouds, and other search mechanisms. You can click on
*Suggestions* to let Marketplace suggest some tags for you based on the data
you've already entered. Click *Select* to select from existing tags, or you can
manually type in new tags. See the *Marketplace Basics* section of this chapter
for detailed requirements for tags.

**Category:** Choose the Marketplace category that most accurately describes
what your app does. Users looking for specific types of apps will often browse
categories by clicking on a specific category name in the main Marketplace home
page. Having your app listed under the appropriate category will help them find
your app.

**Developer Website:** This is a URL that should reference the web site
associated with the development of the app. For open source apps, this typically
points at the project site where the source is maintained. For others, links to
a home page of information about this app would be appropriate.

**Support Website:** This is a URL that should reference a location where
purchasers or users of the app can get support for the app.

**Demo Website:** What better way to showcase the amazing capabilities of your
app than to have a live, running version of it for potential buyers to see and
use? This field can house a URL pointing to exactly that.

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either
yourself (personal) or a company. If you wish to publish your app on behalf of
your company, but your company does not yet have a Marketplace profile, you can
enter the company name in the **Company Name** field. If you are a
representative of your company, you can register your company by clicking the
*Register My Company* link.

Publishing on behalf of yourself is the default. When you publish on behalf of
yourself, the app appears in your *App Manager* &rarr; *Apps* list, and your
name appears in the Marketplace as the Publisher/Author. You are the only one
who can manage this app (add new releases, new versions, edit details, etc).

Publishing on behalf of a company effectively hands the keys over to the admins
of the company. The app only appears on the company's *App Manager* &rarr;
*Apps* list. In addition to yourself, company admins can manage this app (add
new releases, new versions, edit details, etc). The app appears to be
authored/developed by the company, not you personally. It also appears on the
company's public profile page under its list of apps.

Make up some sample data to use during this example, and enter it into the form.
Once you have entered all your app's details, click *Next* to move on to the
next screen.

![Figure 15.5: Specify the version of your app here, following the guidelines. ](../../images/marketplace-add-app-version-initial.png) 

On this screen, you must specify the version of your app. Review the guidance in
the *What is a version* section in this chapter to choose a good version
specifier and enter it here. For our example, since this is the first version,
enter `1.0`. Click *Next*.

### Upload files (plugins) for your app [](id=upload-files-plugins-for-your-app)

This screen allows you to upload different sets of plugin files (variations) to
support different Liferay versions. You must upload at least one plugin file
before advancing beyond this screen. The screen is shown here as it initially
appears:

![Figure 15.6: Specify a set of files for each version of Liferay Portal you wish to support.](../../images/marketplace-add-app-initial-files.png) 

Press the *Browse* button, and select the plugins that make up your app. Each
time you add plugins to the list, they will automatically begin uploading, and
their compatibility information will be scanned (read the *Specify App
Compatibility* section above to understand what compatibility information is
read from your plugins).

For this example, our app consists of a hook and a portlet. The portlet works
across all Liferay releases, but the hook is built separately for CE and EE.
Therefore, we will upload 3 plugins that make up our app. Once the files are
uploaded, a check mark appears next to each plugin, and the plugins are
displayed based on the compatibility information.

![Figure 15.7: Your app has uploaded successfully.](../../images/marketplace-add-app-uploaded-files.png) 

This indicates that the files were successfully uploaded. Notice that the
portlet plugin was automatically copied for use in both the EE and CE
variations, even though you only uploaded the portlet plugin once. Click *Next*
to advance to the final screen.

### Preview and submit the app [](id=preview-and-submit-the-app)

Whenever you make a change (app details, adding files, adding new versions), you
always wind up at a *Preview* screen. This allows you to preview your app as it
will appear in the Marketplace, so you can confirm your changes.

![Figure 15.8: Always preview your app before submitting it. You may see changes here that you want to make before you submit it.](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information. Is it as you expect? If not, click
*Edit* to go back and continue making changes until you are satisfied.

Once you are satisfied, click *Submit for Review*. If you're walking through
this example on Liferay's Marketplace, don't do it, since this is only an
example app. The next section describes what happens when you submit apps or app
changes.

## The review process [](id=the-review-process)

When you submit apps to the Marketplace, they are reviewed by Liferay
Marketplace staff to ensure that your app meets the minimum standards described
in the above *Requirements* section of this chapter.

Each of the following changes require a review by Marketplace staff before the
change is published to the Marketplace:

- Submitting a new app
- Changing details of an app (for example, changing the description or the
  screenshots)
- Adding a new package (set of files) to an existing app, in order to support
  more Liferay releases
- Adding a new version of an existing app

While your submitted change is under review, you can view the status of your
change by visiting *Home* &rarr; *App Manager* &rarr; *Apps*. You can also
cancel your submission by clicking *Cancel Submission* on the *Preview* screen
for each app.

Once your app is approved by Marketplace staff, congratulations! You will
receive an email confirmation and at that moment, your app is in the
Marketplace. The app is also shown on your public Profile page, which lists all
apps that you have personally developed and published.

If your app is rejected, an email will be sent to the email address associated
with the app, along with a note explaining the reasons for rejection.  At that
point, you can make the requested changes, and re-submit the app for approval.

Now that you have successfully published your first app, you'll likely get all
kinds of feedback from users and yourself about what's right and wrong with it.
In the next section, we'll explore how to make changes once you have published
your app.
