
# Liferay Marketplace [](id=lp-6-1-dgen10-liferay-marketplace-0)

The **Liferay Marketplace** is an exciting new hub for sharing, browsing and downloading Liferay-compatible applications. As enterprises look for ways to build and enhance their existing platforms, developers and software vendors are searching for new avenues to reach this market. Marketplace leverages the entire Liferay ecosystem to release and share apps in a user-friendly, one-stop site.

This chapter covers topics related to developing for the Liferay Marketplace, including:

- Marketplace Basics
- Requirements for Publishing to the Marketplace
- Developing and Testing apps for the Marketplace
- Publishing apps to the Marketplace
- Maintaining and Updating apps
- Tracking app Performance

This chapter focuses on the topics of interest to a Liferay developer. It is highly recommended that you first read the **Liferay Marketplace** chapter of the User guide, where you will find detailed information about the Marketplace from an end user's perspective. The User Guide can be found on the [Liferay Documentation page](http://liferay.com/documentation).
 
## Marketplace basics [](id=lp-6-1-dgen10-marketplace-basics-0)

Before diving into the details of developing for the Marketplace, it is important to have a good grasp of new concepts introduced in the Marketplace. The following list of questions will help in understanding the concepts that you will use over and over again as a Marketplace developer.

### What is an app? [](id=lp-6-1-dgen10-what-is-an-app-0)

As a Liferay Developer, you will undoubtedly already be familiar with the concept of plugins (portlets, hooks, themes, etc). If not, review  chapter 1 of this guide. A *Liferay App* (sometimes just called an *app*) is a collection of one or more of these plugins, packaged together to represent the full functionality of an application on the Liferay platform. In addition to the plugins contained within an app, apps have metadata such as names, descriptions, versions, and other ancillary information used to describe and track the app throughout its lifecycle.

Much like standard Liferay plugins, Liferay apps are also *hot-deployable*. If you were to download an app from the Marketplace, you would find that it is a special file type with a `.lpkg` extension. This file can be dropped into Liferay's hot-deploy folder like any other plugin, and it is deployed into the running instance of Liferay Portal.

Developers are not required to create the actual Liferay app files. Instead, your app's individual plugins (`.war` files) are uploaded as part of the publish process, along with identifying information (name, description, version, icon, etc). This is described in detail later on in this chapter.

### What is a version? [](id=lp-6-1-dgen10-what-is-a-version-0)

The concept of versioning is well known in software, and it is no different here. A version of an app represents the functionality of the app at a given point in time. When an app is first created, it is given an initial version (often `1.0`). When updates are needed for the app, a new version is created (e.g. `1.1`), and new files are uploaded representing that version. In some cases, additional qualifiers can be found in the version specifier, to which developers often give special meaning. For example, a developer may declare that the version of their app is always in x.y.z format (where the significance of each x, y, and z are defined). Liferay itself also does this. 

In any case, as developer of your app, you have complete freedom in how you wish to assign version designators. It is highly recommended that you stick to a well known and easy to understand format, such as `1.0`, `1.1`, `1.2`, and so on. You may also include alphabetical characters (e.g `1.0 Beta 2` or `6.3 Patch 123235-01`), but this is not recommended, as it makes it difficult to understand how versions relate to one another.

Keep in mind that the version of your app is completely up to you to specify, but the releases of Liferay with which your app works must be specified using the Liferay versioning scheme.  See the *Specify App Compatibility* section below for details on how to specify the releases of Liferay for which your app is designed.

### What is a package? [](id=lp-6-1-dgen10-what-is-a-package-0)

Apps can be written to work across many different versions of Liferay. For example, suppose you wish to publish version 1.0 of your app, which you're supporting on Liferay 6.1 and 6.2. It may not be possible to create a single binary `.war` file that works across both Liferay versions, due to incompatibilities between these Liferay versions. In this case, you need to compile your app twice: once against Liferay 6.1 and once against 6.2, producing 2 different *packages* (also called variations) of your version 1.0 app. Each package has the same functionality, but they're different files, and it is these packages that you can upload in support of different versions of Liferay, as you will see in a later section. In this guide, packages are sometimes referred to as files that make up your app.

### How do apps relate to users and companies? [](id=lp-6-1-dgen10-how-do-apps-relate-to-users-and-companies-0)

When publishing an app, it is possible to publish it *on behalf of* yourself (an individual) or a *company* with which you are associated. The selection you make determines who has access to the app, once published. To understand the concepts of a Marketplace user, admin, and company, and the ramifications of choosing one vs. the other, visit the *Liferay Marketplace* chapter in the User Guide.

### What are the requirements for publishing apps? [](id=lp-6-1-dgen10-what-are-the-requirements-for-publishing-apps-0)

Liferay apps are "normal" Liferay plugins with additional information about them. Therefore, most of the requirements are the same as those that exist for other Liferay plugins that are detailed in the *Portlet Development* chapter of this guide. In addition to those, there are some Marketplace-specific requirements to keep in mind.

### Things you need before you can publish [](id=lp-6-1-dgen10-things-you-need-before-you-can-publish-0)

You must first develop your app using your preferred development tool (for example, using Liferay Developer Studio or the Plugins SDK). Your app will consist of one or more Liferay plugins. Ensure your app is designed to work with Liferay 6.1 or later. If you wish to target multiple versions of Liferay (for example, you may wish to support both 6.1 CE GA2 and 6.1 EE GA1), ensure you have built binary images of your app for each supported minor family release, if necessary. If a single set of files will work across all supported Liferay versions, you do not need to build multiple plugins. Liferay guarantees compatibility within a given minor release family, so your users can rest assured that your app will work with the minor release that you specify, along with all future maintenance releases of that minor release. 

Next, think of a good name and description of your app, along with a versioning scheme you wish to use. Take some screenshots, design an icon, create web sites for your app (if they do not already exist), and have a support plan in place.

### Image and Naming Requirements [](id=lp-6-1-dgen10-image-and-naming-requirements-0)

**Icons** for your app *must be* exactly 90 pixels in both height and width and must be in PNG, JPG, or GIF format. The image size cannot exceed 512kb.  Animated images are prohibited.
**Screenshots** for your app *must not exceed* 1080 pixels in width x 678 pixels in height and must be in the JPG format. The file size of each screenshot *must not exceed* 384KB. Each screenshot should preferably be the same size (each will be automatically scaled to match the aspect ratio of the above dimensions), and it is preferable if they are named sequentially, for example `fluffy-puppies-01.png`, `fluffy-puppies-02.png`, and so on.
**Titles of Apps**: In some views with Marketplace, titles of applications longer than 18 characters will be shortened with ellipsis. In the Marketplace, titles *must not be* longer than 50 characters.
**Description, Tags, Websites and Version Numbers**: Descriptions, web sites and version numbers are to be as reflective to the product as possible. Please do not use misleading names, information, or icons. A tags suggestion tool has been provided to aid with tagging your asset. Descriptions should be as concise as possible. Ensure your icons, images, descriptions, and tags are free of profanity or other offensive material.

Above and beyond these basics of creating apps in the form of Liferay plugins, there are additional considerations to take into account when designing and publishing apps.

### What kind of validations are performed by Liferay? [](id=lp-6-1-dgen10-what-kinds-of-validation-are-performed-by-liferay-0)

Liferay will ensure that apps meet a minimum set of requirements, such as:

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Basic sanity checking of functionality (e.g. deployment testing, etc)

Liferay does not do source code reviews and will not ask for your source code. Further, Liferay is not responsible for the behavior (or misbehavior) of apps on the Marketplace. For details regarding this, consult the *Liferay Marketplace User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual *End User License Agreements* associated with each app.
 
### What versions of Liferay should I target? [](id=lp-6-1-dgen10-what-versions-of-liferay-should-i-target-0)

Of course, targeting the widest possible range of versions will ensure you a larger audience. However, there are certain features in specific versions of Liferay that you may wish to take advantage of. When uploading apps, you can specify which versions your app is compatible with and you can have multiple files for your app designed for different versions of the Liferay Platform.

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 and later. That's not to say that they will not work with prior versions.  However, only Liferay 6.1 has support for installing Apps directly from the Marketplace and safeguards against malicious Apps that will be ineffective on earlier versions of Liferay. If you wish to use an app for an earlier version, consult the documentation for that app, as it may or may not be supported on earlier versions of Liferay.

Read the *Specify App Compatibility* section below for details on how to specify which versions of Liferay your app works with.

---

![note](../../images/tip-pen-paper.png)**Note:** If you haven't yet done so, be sure to read the **Marketplace** chapter of the User Guide!  The User Guide can be found on the [Liferay Documentation Homepage](http://liferay.com/documentation).

---

Now that we have covered the basics, you're armed with enough knowledge to start creating apps on the Marketplace, so let's see what that looks like in the next section. 

## Developing and publishing apps [](id=lp-6-1-dgen10-developing-and-publishing-apps-0)

Let's jump right in with an example. In this section, we'll walk you through the creation and publication steps (but we won't actually publish the app on the Marketplace, since this example app isn't very useful!). After walking through this, you should understand how Marketplace App development typically occurs.

### Develop a sample app [](id=lp-6-1-dgen10-develop-a-sample-app-0)

Before you can publish anything, you first have to create (develop) an app!  Apps are nothing more than collections of individual plugins, so the first step in development an app for the Marketplace is to develop the functionality in the form of one or more Liferay plugins. To create a sample app (which will consist of a single portlet), follow the detailed instructions in the *Portlet Development* chapter of this guide. After creating and deploying your sample app, return here to continue.

In the real world, apps usually consist of multiple components (e.g. multiple `.war` file plugins), are spread across multiple plugin types, and present non-trivial functionality which in many cases requires some configuration. How these advanced tasks are dealt with is out of scope for this section, but some tips and considerations for Marketplace development can be found later in this chapter.

### Specify App Packaging Directives [](id=lp-6-1-dgen12-specify-app-packaging-directives-0)

When publishing your app, each plugin you upload will packaged into one or more *packages* for each Liferay release you intend to support. When you upload your plugins to the Liferay Marketplace, your app is scanned, and the embedded packaging directives you have specified are extracted and used to create different downloadable *packages* of your app for different Liferay releases. You must insert this information into each plugin in your app before you can publish it to the Marketplace.

The packaging directives are related to the Liferay releases with which your app is compatible. In order to specify which release of Liferay your app is compatible with (and therefore which packages should be created for eventual download on the Marketplace), you first need to understand how Liferay releases are named and how they relate to the underlying Liferay release version. Details can be found on the [Versioning Policy Wiki](http://www.liferay.com/community/wiki/-/wiki/Main/Liferay+Versioning+Policy).  Accordingly, Liferay 6.1 CE GA1 is designated as version `6.1.0`.  CE GA2 is then `6.1.1`, and so on.  Liferay 6.1 EE GA1 is designated as `6.1.10`.  EE versioning follows a slightly different policy given then presence of fix packs and service packs, so 6.1 EE GA2 is `6.1.20`.

For each plugin that makes up your app, packaging directives must be placed in the `liferay-plugin-package.properties` file (located in the `WEB-INF/` directory of your plugin's `.war` file). Within this file, you must specify a comma-separated list of Liferay releases with which your app is compatible, and for which packages should be generated, using the `liferay-versions` keyword.  Marketplace will create packages that contain your plugins based on these packaging directives (and will intelligently group them together as each plugin is uploaded).

For example, to specify that a particular plugin in your app is compatible with Liferay 6.1 CE GA2 *and later*, add this line to your `liferay-plugin-packages.properties` file: 

    liferay-versions=6.1.1+ 

This means that the app works with any 6.1 CE release starting with GA2, and Marketplace will create a package that is compatible with 6.1 CE GA2 *and later*.  Here are some additional examples:

	# works with Liferay 6.1 CE GA2 *only*
	liferay-versions=6.1.1

	# works with Liferay 6.1 CE GA2 and later (i.e. NOT compatible with 6.1 GA1)
	liferay-versions=6.1.1+

	# works with Liferay 6.1 CE GA2 and CE GA3 only
	liferay-versions=6.1.1,6.1.2
	
	# works with Liferay 6.1 EE GA2 only
	liferay-versions=6.1.20

	# works with Liferay 6.1 EE GA2 and later
	liferay-versions=6.1.20+

	# works with Liferay 6.1 CE GA2 and later, and 6.1 EE GA2 and later
	liferay-versions=6.1.1+,6.1.20+

If some plugins within your app must be built for multiple releases, ensure that the respective plugins have appropriate versioning information in them.  For example: suppose your app consists of two plugins: a portlet and a hook.  The portlet is simple, and uses standard API calls that work on all Liferay 6.1 releases.  However, the hook is different for CE vs. EE because it takes advantage of some feature of EE.

In this case, your portlet plugin would have `liferay-versions=6.1.1+,6.1.20+`, but because the hook must be built against a different API for Liferay EE, you'd have *two* hook plugins.  The first one would specify `liferay-versions=6.1.1+` (indicating it works with CE GA2 and later), and the second hook plugin would specify `liferay-versions=6.1.20+` (indicating it works with EE GA2 and later, but not CE GA2).  As you upload these plugins later on in this example, you will notice that they are grouped into separate packages of plugins for each supported release.  In some cases, if a plugin is compatible with multiple releases, it will be automatically copied for use in all of the releases for which your app supports.  Also, in some cases, if Marketplace cannot determine the appropriate package into which to place your plugin, it may reject it.  For example, if you specified `liferay-versions=1.0.0+`, because you are confident your plugin works with any Liferay release, Marketplace likely will reject it as `1.0` is not a known Liferay release.

Now that you have developed your app, it's time to get started with Marketplace!

### Establish a Marketplace Account [](id=lp-6-1-dgen10-establish-a-marketplace-account-0)

Before you can publish anything to the Marketplace, you must first have an account on [liferay.com](http://liferay.com). If you do not have an account, visit [http://liferay.com](http://liferay.com) and click *Register* in the upper-right corner of the screen. Once you have registered, you can visit the Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace). The Marketplace home page is shown below:

![Figure 10.1: The Marketplace home page is where users go to find new and interesting apps. ](../../images/marketplace-homepage.png) 

This is the front page of the Marketplace and is where users go to find new and interesting apps. You'll visit here often during the course of development, so it might be a good idea to bookmark it now. To get started, the first thing you want to do is visit your personal *Home* page. There is a thin strip at the top of your browser window. This is known as the *Dockbar*. Many links are present in the drop-down menus of the Dockbar, including a link titled *Go to My Home*. This is a link to your personal home page. More details about what you can find on your personal home and profile pages can be found in the Liferay Marketplace chapter of the *User Guide*. For now, go to your personal home page by clicking on the *Go to My Home* link.

![Figure 10.2: Use the My Home Page link from anywhere in Liferay to navigate to your personal pages. ](../../images/marketplace-my-homepage-link.png) 

Your home page contains links to often-used functionality of liferay.com, including app creation and management. There are several links on the left of your home page, including one titled *App Manager*. This link allows you to manage the apps that you have either purchased (or have been purchased for use in companies you are associated with) or apps that you or your company have developed. You'll use this page heavily, so a bookmark would be useful here. Click *App Manager* to visit this page.

![Figure 10.3: The App Manager lets you maintain everything about apps you've purchased or published.](../../images/marketplace-my-app-manager.png) 

You'll notice three tabs across the top:

**Purchased** - This shows apps you have personally purchased, and those apps that have been purchased on behalf of companies you are associated with.
**Apps** - This shows apps you have personally *published*.
**Add an App** - This screen begins the process of publishing a new app.

Since you have not purchased or published any apps, the first two tabs are likely empty. Let's get publishing!

### Upload (Publish) your app [](id=lp-6-1-dgen10-upload-publish-your-app-0)

To begin the process of publishing your app, click *Add an App*. A form appears, allowing you to fill in your app's details.

#### Initial app details [](id=lp-6-1-dgen10-initial-app-details-0)

The first step is to enter the basic details about your app. 

![Figure 10.4: Add all the details about your app, including tags, categories, and links to your site.](../../images/marketplace-add-app-details.png) 

This screen allows you to enter basic details about the app you are publishing.

**Name:** The name of your application. Arguably the most important detail of your app, the name of your app should be a good title that conveys the function of the App but is not overly wordy or misleading. Choosing a good name for your app is key to its success, so choose wisely!  See the *Marketplace Considerations* chapter for more guidance on how to pick good names. Do not include versions in the title unless it is a vital part of the name, for example *Angry Birds 2: More Anger*. Each app on the Marketplace must have a unique name.

**Description:** Like the name says, this is a description of your app. You can put anything you want here, but a good guideline is no more than 4-5 paragraphs. This field does not allow any markup tags or other textual adornments--it's just text.

**Icon:** The icon is a small image representation of the app. See the *Marketplace Basics* section of this chapter for detailed requirements for icons.

**Screen Captures:** You can supply multiple screenshots of your app in action. The screenshots you upload here are displayed when your app is viewed on the Marketplace, using a carousel of rotating images. See the *Marketplace Basics* section of this chapter for detailed requirements for screen captures.

**Tags:** A set of descriptive words that categorize your app. These tags are free-form and can help potential purchasers find your app through keyword searches, tag clouds, and other search mechanisms. You can click on *Suggestions* to let Marketplace suggest some tags for you based on the data you've already entered. Click *Select* to select from existing tags, or you can manually type in new tags. See the *Marketplace Basics* section of this chapter for detailed requirements for tags.

**Category:** Choose the Marketplace category that most accurately describes what your app does. Users looking for specific types of apps will often browse categories by clicking on a specific category name in the main Marketplace home page.  Having your app listed under the appropriate category will help them find your app.

**Developer Website:** This is a URL that should reference the web site associated with the development of the app. For open source apps, this typically points at the project site where the source is maintained. For others, links to a home page of information about this app would be appropriate.

**Support Website:** This is a URL that should reference a location where purchasers or users of the app can get support for the app. 

**Demo Website:** What better way to showcase the amazing capabilities of your app than to have a live, running version of it for potential buyers to see and use?  This field can house a URL pointing to exactly that.

**App Owner:** Choose to whom the app "belongs" once it is uploaded--either yourself (personal) or a company. If you wish to publish your app on behalf of your company, but your company does not yet have a Marketplace profile, you can enter the company name in the **Company Name** field. If you are a representative of your company, you can register your company by clicking the *Register My Company* link.

Publishing on behalf of yourself is the default. When you publish on behalf of yourself, the app appears in your *App Manager* &rarr; *Apps* list, and your name appears in the Marketplace as the Publisher/Author. You are the only one who can manage this app (add new releases, new versions, edit details, etc).

Publishing on behalf of a company effectively hands the keys over to the admins of the company. The app only appears on the company's *App Manager* &rarr; *Apps* list. In addition to yourself, company admins can manage this app (add new releases, new versions, edit details, etc). The app appears to be authored/developed by the company, not you personally. It also appears on the company's public profile page under its list of apps.

Make up some sample data to use during this example, and enter it into the form. Once you have entered all your app's details, click *Next* to move on to the next screen.

![Figure 10.5: Specify the version of your app here, following the guidelines. ](../../images/marketplace-add-app-version-initial.png) 

On this screen, you must specify the version of your app. Review the guidance in the *What is a version* section in this chapter to choose a good version specifier and enter it here. For our example, since this is the first version, enter `1.0`. Click *Next*.

#### Upload files (plugins) for your app [](id=lp-6-1-dgen10-upload-files-plugins-for-your-app-0)

This screen allows you to upload different sets of plugin files (variations) to support different Liferay versions. You must upload at least one plugin file before advancing beyond this screen. The screen is shown here as it initially appears:

![Figure 10.6: Specify a set of files for each version of Liferay Portal you wish to support.](../../images/marketplace-add-app-initial-files.png) 

Press the *Browse* button, and select the plugins that make up your app.  Each time you add plugins to the list, they will automatically begin uploading, and their compatibility information will be scanned (read the *Specify App Compatibility* section above to understand what compatibility information is read from your plugins).

For this example, our app consists of a hook and a portlet. The portlet works across all Liferay releases, but the hook is built separately for CE and EE.  Therefore, we will upload 3 plugins that make up our app. Once the files are uploaded, a check mark appears next to each plugin, and the plugins are displayed based on the compatibility information.

![Figure 10.7: Your app has uploaded successfully.](../../images/marketplace-add-app-uploaded-files.png) 

This indicates that the files were successfully uploaded. Notice that the portlet plugin was automatically copied for use in both the EE and CE variations, even though you only uploaded the portlet plugin once. Click *Next* to advance to the final screen.

#### Preview and submit the app [](id=lp-6-1-dgen10-preview-and-submit-the-app-0)

Whenever you make a change (app details, adding files, adding new versions), you always wind up at a *Preview* screen.  This allows you to preview your app as it will appear in the Marketplace, so you can confirm your changes. 

![Figure 10.8: Always preview your app before submitting it. You may see changes here that you want to make before you submit it.](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information. Is it as you expect? If not, click *Edit* to go back and continue making changes until you are satisfied.

Once you are satisfied, click *Submit for Review*. If you're walking through this example on Liferay's Marketplace, don't do it, since this is only an example app. The next section describes what happens when you submit apps or app changes.

### The review process [](id=lp-6-1-dgen10-the-review-process-0)

When you submit apps to the Marketplace, they are reviewed by Liferay Marketplace staff to ensure that your app meets the minimum standards described in the above *Requirements* section of this chapter.

Each of the following changes require a review by Marketplace staff before the change is published to the Marketplace:

- Submitting a new app
- Changing details of an app (for example, changing the description or the screenshots)
- Adding a new package (set of files) to an existing app, in order to support more Liferay releases
- Adding a new version of an existing app

While your submitted change is under review, you can view the status of your change by visiting *Home* &rarr; *App Manager* &rarr; *Apps*. You can also cancel your submission by clicking *Cancel Submission* on the *Preview* screen for each app.

Once your app is approved by Marketplace staff, congratulations! You will receive an email confirmation and at that moment, your app is in the Marketplace. The app is also shown on your public Profile page, which lists all apps that you have personally developed and published.

Now that you have successfully published your first app, you'll likely get all kinds of feedback from users and yourself about what's right and wrong with it. In the next section, we'll explore how to make changes once you have published your app.

## Making changes to published apps [](id=lp-6-1-dgen10-making-changes-to-published-apps-0)

After your app is published and approved, you will undoubtedly need to make one or more of these kinds of changes during the life of the app:

- Editing your app details (e.g. description, icon, etc)
- Adding support for a new version of Liferay Portal
- Releasing a new version of your app to fix bugs or offer new functionality
- Disabling your apps

Liferay Marketplace supports all of the above operations as described below.

### Editing your app details [](id=lp-6-1-dgen10-editing-your-app-details-0)

App details include the name, description, icon, screenshots, and other information that you supplied on the first screen during the app creation process. To make changes to this content for your app, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, then click the *Action* button next to the app you wish to edit, and select *Edit*.

![Figure 10.9: Editing an app is as simple as navigating to it and clicking *Edit*.](../../images/marketplace-edit-app-details.png) 

This screen shows you what the app looks like on the Marketplace. To edit the detail information, click the *Edit* button at the bottom of the preview. This  allows you to edit details (as well as add new files to your existing version). Note that the current values as they appear in your app are used to pre-fill the form. Make any changes as needed on this screen, and click *Next*. If you do not need to edit any more variations, you can continue clicking *Next* until you reach the final preview screen. Click *Submit for Review* to submit your detail changes for review. Once approved, the changes you request appear on the Marketplace.

### Adding support for new versions of Liferay Portal [](id=lp-6-1-dgen10-adding-support-for-new-versions-of-liferay-portal-0)

If you need to add files in support of another Liferay release, the process is similar. Navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Action* button next to the app for which you wish to add new files, and click *Edit*. Click *Next* to advance past the details screen (making any changes as needed), and click *Next* to advance past the version edit screen (you can't actually edit the version number of an already-approved version, but you can edit the "What's New" information if needed). 

Once you advance past the version edit screen, you'll be at the File Upload screen. This screen should look familiar--it's the same workflow used when you initially created your app!  The difference is that you can't edit pre-approved files for specific Liferay releases. You can only add *new* files for a different Liferay release (if you actually need to update existing files, you must create a new version of the app--see the later section on adding versions for details on how to do this).

Upload your new files (ensuring that your new plugins have updated compatibility information, see the section on *Specify App Compatibility* for details on versioning), click *Next*, and observe the newly-added files listed at the bottom of the preview screen. Click *Submit for Review* to submit your requested change (adding of files). The files will be reviewed by Liferay, and once approved, the new package is available for download in the Marketplace.

### Releasing a new version of your app [](id=lp-6-1-dgen10-releasing-a-new-version-of-your-app-0)

After time passes, you may wish to add new functionality to your app or fix a batch of bugs. This can be accomplished by releasing a new version of your app. New versions offer your users new functionality and bugfixes, and users are generally encouraged to always use the latest version. In addition, when a new version of your app becomes available, existing users are notified automatically through Liferay's notification system.

New versions of your apps are created similarly to the way the initial version was. To add a new version, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*. Click the *Action* button next to the app for which you wish to add a new version, and then click *Edit*. You will be taken to the Details screen. At the bottom of the Details screen, click the *Add New Version* button. This button begins the process of adding a new version, starting with the App Details screen. In this case, the screen is pre-filled with data from the current version of the app, as shown below.

![Figure 10.10: Adding a version is similar to creating a new app, except that the fields are filled in for you.](../../images/marketplace-add-version-details.png) 

You can make any changes to the pre-filled data on this screen. Since this is a new version of an existing app making major changes (such as completely changing the name or description) might be unsettling to your existing users. It is common that you'll want to upload new screenshots and refresh the icon. Note that you cannot change the app owner (such as moving from a personally-developed app to a company-developed app). 

Clicking *Next* takes you through the same screens you've already seen. On the *Add App Version* screen, you can specify a new version name for this version of your app. Also, note that when adding new versions to an existing app, you have the option to add *What's New* text. This is typically filled in with a list of changes for this version, such as significant new features or bugfix information. Clicking *Next* from here allows you to upload the files associated with the new version of the app. For a new version of the app, you must upload all files for all supported Liferay versions again, even if they have not changed since the last version.

### Deactivating your app [](id=lp-6-1-dgen10-deactivating-your-app-0)

When the time comes to retire your app, you can *Deactivate* it. Deactivating an app causes the app to no longer be downloadable from the Marketplace for new customers and it won't appear in any public Marketplace listings. Existing customers that have already downloaded your app can continue downloading the legacy versions of the app they have already acquired, but they can't download any versions they've not already received. The app remains in your inventory, with all of its history, in case you need to re-activate or reference it in the future.

To deactivate your app, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next to the app for which you want deactivate, and select the *Deactivate* action.


## Tracking app performance [](id=lp-6-1-dgen10-tracking-app-performance-0)

One of the main reasons for developing and publishing apps into the Marketplace is to drive downloads and adoption of your app. The Marketplace enables you, as the developer of your app, to get detailed reports about the number of views and downloads of your app(s). To access these metrics, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next to the app for which you want metrics, and select the *Metrics* action.

![Figure 10.11: App metrics let you see graphically how many views and downloads your app has in the Marketplace.](../../images/marketplace-app-metrics-views.png) 

The view shown above is the default metrics view for a single app. Across the top is a list of data series options (*Views* or *Downloads*). Below that, a date range can be chosen. In the middle, a graph is shown for the data within the date range. Finally, the same data that is graphed is also shown in tabular format, in case you want to know the exact values making up the graph. The different types of data available to view are described below. 

### Views [](id=lp-6-1-dgen10-views-0)

When someone searches or browses the Marketplace, they click on apps to see detailed views of the apps they're interested in. When this occurs for your app, a *View* is recorded for the app, and this data is what is shown on the App Metrics screen when *Views* is selected at the top. *Views* is also the default view, as shown above. The number of recorded views per day per user is unlimited.

### Downloads [](id=lp-6-1-dgen10-downloads-0)

A download is recorded for your app when someone downloads a specific package of a specific version of your app. The number of recorded downloads per day per user is unlimited.

## Summary [](id=lp-6-1-dgen10-summary-0)

In this chapter we introduced concepts and instructions for developers to make their apps available on the Liferay Marketplace. We looked at how to create, publish, maintain, and track apps. You do this through  [liferay.com](liferay.com), using your own personal credentials and its features for Marketplace. Next, we covered the requirements for publishing apps, which did not differ significantly from requirements for general Liferay development. We then showed how you can publish a sample app on the Marketplace and how you can modify it as the app evolves. Finally, we looked at how to track the adoption of apps using download and view metrics. We hope this information helps you understand how to develop apps for Liferay!

Next, we'll take a look at some helpful plugin developer references. So get ready to bookmark plenty of links!

