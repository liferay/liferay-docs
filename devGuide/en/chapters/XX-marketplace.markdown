
# Liferay Marketplace

The **Liferay Marketplace** is an exciting new hub for sharing, browsing and downloading Liferay-compatible Applications. As enterprises look for ways to build and enhance their existing platforms, developers and software vendors are searching for new avenues to reach this market. Marketplace leverages the entire Liferay ecosystem to release and share Apps in a user-friendly, one-stop site.

This chapter covers topics related to developing for the Liferay Marketplace, including:

- *Marketplace Basics*
- *Requirements for Publishing to the Marketplace*
- *Developing and Testing Apps for the Marketplace*
- *Publishing Apps to the Marketplace*
- *Maintaining and Updating Apps*
- *Tracking App Performance*

This chapter focuses on the topics of interest to a Liferay developer.  It is highly recommended that you first read **Liferay Marketplace** chapter of the User guide, where you can read and understand detailed information about the Marketplace from an end user's perspective.  The User Guide can be found on the [Liferay Documentation Homepage](http://liferay.com/documentation).
 
## Marketplace Basics

Before diving into the details of developing for the Marketplace, it is important to have a good grasp of new concepts introduced in the Marketplace.  The following list of questions will help in understanding the concepts that you will use over and over again as a Marketplace developer.

### What is an App?

As a Liferay Developer, you will undoubtedly already be familiar with the concept of plugins (portlets, hooks, themes, etc).  If not, review the **Introduction** chapter of this guide.  A *Liferay App* (sometimes just called an *App*) is simply a collection of one or more of these plugins, packaged together to represent the full functionality of an Application on the Liferay Platform.  In addition to the plugins contained within an App, Apps have metadata such as names, descriptions, versions, and other ancillary information used to describe and track the App throughout its lifecycle.

Much like standard Liferay plugins, Liferay Apps are also *hot-deployable*.  If you were to download an App from the Marketplace, you will find that it is a special file type with a `.lpkg` extension.  This file can be dropped into Liferay's hot-deploy directory like any other plugin, and it will be deployed into the running instance of Liferay.

Developers are not required to create the actual Liferay App files.  Instead, your App's individual plugins (`.war` files) are uploaded as part of the publish process, along with identifying information (name, description, version, icon, etc).  This is described in detail later on in this chapter.

### What is a Version?

The concept of versioning is will known in the software arena, and it is no different here.  A version of an App represents the functionality of the App at a given point in time.  When an App is first created, it is given an initial version (often `1.0`).  When updates are needed for the App, a new version is created (e.g. `1.1`), and new files are uploaded representing that version.  In some cases, additional qualifiers can be found in the version specifier, to which developers often give special meaning.  For example, a developer may declare that the version of their App is always in x.y.z format (where the significance of each x, y, an z are defined).  Liferay itself also does this.  

In any case, as developer of your App, you have complete freedom in how you wish to assign version designators.  It is highly recommended that you stick to a well known and easy to understand format, such as `1.0`, `1.1`, `1.2`, ..., and so on.  You may also include alphabetical characters (e.g `1.0 Beta 2` or `6.3 Patch 123235-01`), but this is not recommended, as it makes it difficult to understand how versions relate to one another.

### What is a Variation?

Apps can be written to work across many different versions of Liferay.  For example, suppose you wish to publish a Version 1.0 of your App, which is supported on Liferay 6.1 and 6.2.  It may not be possible to create a single binary `.war` file that works across both Liferay versions, due to incompatibilities between these Liferay versions, so you would need to compile your App twice: once against Liferay 6.1 and again against 6.2, producing 2 different *variations* of your Version 1.0 App.  Each variation has the same functionality, but they are different files, and it is these variations that you can upload in support of different versions of Liferay, as you will see in a later section.  In this guide, Variations are sometimes referred to as simply *files* that make up your App.

### How do Apps relate to Users and Companies?

When publishing an App, it is possible to publish it *on behalf of* yourself (an individual) or a *company* you are associated with.  The selection you make determines who will have access to the App, once published.  To understand the concepts of a Marketplace User, Admin, and Company, and the ramifications of choosing one vs. the other, visit the *Liferay Marketplace* chapter in the User Guide.

### What are the Requirements for publishing Apps?

Liferay Apps are "normal" Liferay plugins with some additional information about them.  Therefore, most of the requirements are the same as those that exist for "regular" Liferay Plugins that are detailed in the *Portlet Development* chapter of this guide.  In addition to those, there are some Marketplace-specific requirements to keep in mind.

### Things you need before you can publish

You must first develop your App using your preferred development tool (for example, using Liferay Developer Studio, or the Plugins SDK).  Your App will consist of one or more Liferay *plugins*.  Ensure your App is designed to work with Liferay 6.1 or later.  If you wish to target multiple versions of Liferay (for example, you may wish to support both 6.1 CE GA2 and 6.1 EE GA1), ensure you have built binary images of your App for each supported minor family release, if necessary.  If a single set of files will work across all supported Liferay versions, you do not need to build multiple plugins.  Liferay guarantees compatibility within a given minor release family, so your users can rest assured that your App will work with the minor release that you specify, along with all future maintenance releases of that minor release.  More on compatibility later on in this chapter.

Next, think of a good name and description of your App, and a versioning scheme you wish to use. Take some screenshots, design an icon, create websites for your App (if they do not already exist), and have a support plan in place.

#### Image and Naming Requirements

- **Icons** for your app *must be* 90 pixels in both height and width, and must be in the PNG, JPG, or GIF format.  The image size cannot exceed 512kb.
- **Screenshots** for your app *must not exceed* 600 pixels in width x 400 pixels in height, and must be in the PNG, JPG, or GIF format.  The total size of all screenshots *must not exceed* 1MB.  Each screenshot must be the same size, and it is preferable if they are named sequentially, for example `fluffy-puppies-01.png`, `fluffy-puppies-02.png`, and so on.
- **Titles** of Apps: In some views with Marketplace, titles of applications longer than 18 characters will be shorten with ellipsis. In the Marketplace, titles *must not be* longer than 50 characters.
- **Description, Tags, Websites and Version Numbers** Descriptions, websites and version numbers are to be as reflective to the product as possible. Please do not use misleading names, information, or icons.  A tags suggestion tool has been provided to aid with tagging your asset. Descriptions should be as concise as possible. Ensure your icons, images, descriptions, and tags are free of profanity or other offensive material.

Above and beyond these basics of creating Apps in the form of Liferay Plugins, there are additional considerations to take into account when designing and publishing Apps.

### What kinds of validation are performed by Liferay?

Liferay will ensure that Apps meet a minimum set of requirements, such as

- Running basic anti-virus checks
- Ensuring titles, descriptions, images, etc. are appropriate
- Basic sanity checking of functionality (e.g. deployment testing, etc)

Liferay does not do source code reviews, and will not ask for your source code.  Further, Liferay is not responsible for the behavior (or misbehavior) of Apps on the Marketplace.  For details regarding this, consult the *Liferay Marketplace User Agreement*, *Liferay Marketplace Developer Agreement*, and the individual *End User License Agreements* associated with each App.
 
### What versions of Liferay should I target?

Of course, targeting the widest possible range of versions will ensure you a larger audience, however there are certain features in specific versions of Liferay that you may wish to take advantage of.  When uploading Apps, you can specify which versions your App is compatible with, and you can have multiple files for your App designed for different versions of the Liferay Platform.

Note that Apps on the Liferay Marketplace must be designed for Liferay 6.1 and later.  That's not to say that they will not work with prior versions, however only Liferay 6.1 has support for installing Apps directly from the Marketplace, and safeguards against malicious Apps that will be ineffective on earlier versions of Liferay.  If you wish to use an App for an earlier version, consult the documentation for that App, as it may or may not be supported on earlier versions of Liferay.

Now that we have covered the basics, you will be armed with enough knowledge to start creating apps on the Marketplace, so let's see what that looks like in the next section.  

---
![note](../../images/tip-pen-paper.png)**Note:** If you haven't yet done so, be sure to read the **Marketplace** chapter of the User Guide!  The User Guide can be found on the [Liferay Documentation Homepage](http://liferay.com/documentation).
---

## Developing and Publishing Apps

Let's jump right in with an example.  In this section, we'll walk you through the creation and publishing steps (but we won't actually publish the App on the Marketplace, since this example App isn't very useful!).  After walking through this, you should start to form a picture of how Marketplace development typically occurs.

### Develop a Sample App

Before you can publish anything, you first have to create (develop) an App!  Apps are nothing more than collections of individual plugins, so the first step in development an App for the Marketplace is to develop the functionality in the form of one or more Liferay plugins.  To create a sample App (which will consist of a single portlet), follow the detailed instructions in the *Portlet Development* chapter of this guide.  After creating and deploying your sample App, return here to continue.

In the real world, Apps usually consist of multiple components (e.g. multiple `.war` file plugins), are spread across multiple plugin types, and present non-trivial functionality which in many cases requires some configuration.  How these advanced tasks are dealt with is out of scope for this section, but some tips and considerations for Marketplace development can be found later in this chapter.

Now that you have developed your App, it's time to get started with Marketplace!

### Establish a Marketplace Account

Before you can publish anything to the Marketplace, you must first have an account on [liferay.com](http://liferay.com).  If you do not have an account, visit [http://liferay.com](http://liferay.com) and click *Register* in the upper-right.  Once you have registered, you can visit the Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).  The Marketplace homepage is shown below:

![Figure 9.1: Marketplace Home Page](../../images/marketplace-homepage.png) 

This is the front page of the Marketplace and is where users go to find new and interesting Apps.  You will visit here often during the course of development, so it might be a good idea to bookmark it now.  Now, let's visit your personal *Home* page.  You will see a thin strip at the top of your browser window.  This is known as the *dockbar*.  Many links are present in the drop-down menus of the dockbar, including a link titled `Go to My Home`.  This is a link to your personal `home page`. More detail about what you can find on your personal home and profile pages can be found in the *Liferay Marketplace* chapter of the User Guide.  For now, go to your personal home page by clicking on the *Go to My Home* link.

![Figure 9.2: My Home Page Link](../../images/marketplace-my-homepage-link.png) 

Your Home page contains links to often-used functionality of liferay.com, including App creation and management.  You will notice several links on the left of your home page, including one titled *App Manager*.  This link allows you to manage the Apps that you have either purchased (or have been purchased for use in companies you are associated with), and Apps that you or your company have developed.  This page will also be heavily used, so a bookmark would be useful here.  Click *App Manager* to visit this page.

![Figure 9.3: App Manager](../../images/marketplace-my-app-manager.png) 

You'll notice three tabs across the top:

* **Purchased** - This shows Apps you have personally purchased, and those Apps that have been purchased on behalf of companies you are associated with.
* **Apps** - This shows Apps you have personally *published*.
* **Add an App** - This screen begins the process of publishing a new App.

Since you have not purchased or published any Apps, the first two tabs will likely be empty.  Let's go publishing!

### Upload (Publish) your App

To begin the process, of publishing your App, click *Add an App*, to begin the process of filling in your app's details.

#### Initial App Details

The first step is to fill out basic details about the App.  You will be presented with the initial publishing screen.

![Figure 9.4: Add an App: Details](../../images/marketplace-add-app-details.png) 

This screen allows you to enter basic details about the App you are publishing.

- **Name** - The name of your Application.  Arguably the most important detail of your App, the name of your App should be a good title that conveys the function of the App, but is not overly-wordy or misleading.  Choosing a good name for your App is key to its success, to choose wisely!  See the *Marketplace Considerations* chapter for more guidance on how to pick good names.  Do not include versions in the title unless it is a vital part of the name, for example "Angry Birds 2: More Anger".  Each App on the Marketplace must have a unique name.

- **Description** - Like the name says, this is a description of your App.  You can put anything you want here, but generally should be no more than 4-5 paragraphs.  This field does not allow any markup tags or other textual adornments - it's just text.

- **Icon** - The icon is a small image representation of the App.  See the *Marketplace Basics* section of this chapter for detailed requirements for icons.

- **Screen Captures** - You can supply multiple screenshots of your App in action.  The screenshots you upload here will be shown when your App is viewed on the Marketplace, using a carousel of rotating images.  See the *Marketplace Basics* section of this chapter for detailed requirements for screen captures.

- **Tags** - A set of descriptive words that categorize your App.  These tags are free-form and can help potential purchasers find your App through keyword searches, tag clouds, and other search mechanisms.  You can click on *Suggestions* to let Marketplace suggest some tags for you, based on the data you've already entered, or click on *Select* to select from existing tags, or you can manually type in new tags.  See the *Marketplace Basics* section of this chapter for detailed requirements for tags.

- **Category** - Choose which Marketplace category that most accurately describes what your App does.  Users looking for specific types of Apps will often browse categories by clicking on a specific category name in the main Marketplace home page, and having your App listed under the Appropriate category will help them find your App.

- **Developer Website** - This is a URL that should reference the website associated with the development of the App.  For open source Apps, this typically points at the project site where the source is maintained.  For others, pointers to a homepage of information about this App would be Appropriate.

- **Support Website** - This is a URL that should reference a location where purchasers or users of the App can get support for the App.  

- **Demo Website** - What better way to showcase the amazing capabilities of your App than to have a live, running version of it for potential buyers to see and use?  This field can house a URL that points to exactly that.

- **App Owner** - Choose who the App should "belong to" once it is uploaded - either yourself (personal) or one of the companies you are already associated with.  If you wish to publish your App on behalf of your company, but your company does not yet have a Marketplace Profile, you can enter the company name in the "Company Name" field.  If you are a representative of your company, you can register your company by clicking the *Register My Company* link.

Publishing on behalf of yourself is the default.  When you publish on behalf of yourself, the App will Appear in your *App Manager* &rarr; *Apps* list, and your name will Appear in the Marketplace as the Publisher/Author.  Only you will be able to manage this App (add new releases, new versions, edit details, etc).

Publishing on behalf of a company effectively hands the keys over to the admins of the company.  The App will only Appear on the company's *App Manager* &rarr; *Apps* list.  In addition to yourself, admins of the company will be able to manage this App (add new releases, new versions, edit details, etc).  The App will Appear to be authored/developed by the company, not you personally.  It will also Appear on the Company's public Profile page under its list of Apps.

Make up some sample data to use during this example, and enter it into the form.  Once you have entered all of the details of your App, click *Next* to move on to the next screen.

#### Specify App Version

![Figure 9.5: Add an App: Initial Version](../../images/marketplace-add-app-version-initial.png) 

On this screen, you must specify the version of your App.  Review the guidance in the *What is a Version* section in this chapter to choose a good Version specifier, and enter it here.  For our example, since this is the first version, enter `1.0`.  Click *Next*.

#### Upload files (plugins) for your App

This screen allows you to upload different sets of plugin files (Variations) in support of different Liferay versions.  Think of it as a "worksheet" that you can fill out before advancing.  The screen is shown here as it initially Appears:

![Figure 9.6: Add an App: Initial Files](../../images/marketplace-add-app-initial-files.png) 

On the left is a drop-down with the known Liferay releases.  Choosing a Liferay release from this drop-down will then show you which files you have uploaded that correspond to the chosen Liferay version.   You can freely move between versions, and each time you choose a different version on the left drop-down, the right side will update to show you which files you have already uploaded for that version.  You can do this as many times as you wish in order to upload all of the files making up your App, for all of the Liferay versions you wish to support.  You can also upload duplicate files, for the case where a single file is supported across multiple Liferay releases.

For this example, we will add the example portlet that represents our App.  Choose *Liferay Portal 6.1 CE GA2* on the left menu.  Click *Browse* and locate the `.war` file representing your sample App (hint: it is located in the `dist/` folder of your Plugins SDK).  Choose it using the file browser, and click *Upload*.  This will upload your plugin, and once uploaded you should see a confirmation checkmark:

![Figure 9.7: Add an App: Uploaded Files](../../images/marketplace-add-app-uploaded-files.png) 

This indicates that the file was successfully uploaded, and will represent your App for users on Liferay Portal 6.1 CE GA2.  You can optionally select other Liferay releases in the left drop-down and upload other `.war` files.  Notice when you choose a different Liferay version in the left drop-down, and then re-select the original *Liferay Portal 6.1 CE GA2*, your file is still there.   Click *Next* to advance to the final screen.

#### Preview and Submit the App

Whenever you make a change (app details, adding files, adding new versions), you will always wind up at a *Preview* screen, allowing you to confirm your changes or preview what your App will look like. 

![Figure 9.8: Add an App: Preview and Submit](../../images/marketplace-add-app-preview-and-submit.png) 

For this example, review the information.  Is it as you expect?  If not, you can click *Edit* to go back and continue to make changes until you are satisfied.

Once you are satisfied, you would normally click *Submit for Review*.  For this example, don't do it, since this is only an example App.  The next section describes what happens when you submit Apps or App changes.

### The Review Process

When you submit Apps to the Marketplace, they will first be reviewed by Liferay Marketplace staff to ensure that your App meets the minimum standards described in the above *Requirements* section of this chapter.

The following changes each require a review by Marketplace staff before you and others will see the desired changes on the Marketplace:

- Submitting a new App
- Changing details of an App (for example, changing the Description or the screenshots)
- Adding a new Variation (files) to an existing App, in order to support more Liferay releases
- Adding a new Version of an existing App

While your submitted change is under review, you can view the status of your change by visiting *Home* &rarr; *App Manager* &rarr; *Apps*.  You can also cancel your submission by clicking *Cancel Submission* on the *Preview* screen for each App.

Once your App is Approved by Marketplace staff, congratulations!  You will receive an email confirmation and will be able to see your App in the Marketplace.  The App will also be shown on your public Profile page, which lists all apps that you have personally developed and published.

Now that you have successfully published your first app, you will get all kinds of feedback from users and yourself about what's right and wrong with it.  In the next section, we will explore how to make changes once you have published your App.

## Making changes to published Apps

After your App is published and Approved, you will undoubtedly need to make one or more of these kinds of changes during the life of the App:

- Editing your App Details (e.g. Description, icon, etc)
- Adding support for a new version of Liferay
- Releasing a new Version of your App to fix bugs or offer new functionality
- Disabling your Apps

Liferay Marketplace supports all of the above operations as described below.

### Editing your App Details

App Details include the name, description, icon, screenshots, and other information that you supplied on the first screen during the App creation process.  To make changes to this content for your App, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, then click the *Action* button next to the App you wish to edit, and select *Edit*.

![Figure 9.9: Edit an App: Details](../../images/marketplace-edit-app-details.png) 

This screen shows you how the App currently Appears on the Marketplace.  To edit the detail information, click the *Edit* button at the bottom of the preview.  This will allow you to edit details (As well as add new files to your existing Version).  Note that the current values as they Appear in your App are used to pre-fill the form.  Make any changes as needed on this screen, and click *Next*.  If you do not need to edit any more Variations, you can continue clicking *Next* until you reach the final Preview screen.  Click *Submit for Review* to submit your detail changes for review.  Once Approved, the changes you request will Appear on the Marketplace.

### Adding Support for New Versions of Liferay

If you need to add files in support of another Liferay release, the process is similar - navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Action* button next to the App for which you wish to add new files, and click *Edit*.  Click *Next* to advance past the details screen (making any changes as needed), and click *Next* to advance past the Version edit screen (you can't actually edit the Version number of an already-approved Version, but you can edit the "What's New" information if needed).  

Once you advance past the Version edit screen, you will be at the File Upload screen.  This screen should look familiar - it is the same workflow used when you initially created your App!  The different is that you will not be able to edit pre-approved files for specific Liferay releases.  You will only be able to add *new* files for a different Liferay release (if you actually need to update existing files, you will need to create a new Version of the App - see the later section on adding Versions for details on how to do this).

Choose the Liferay release for which you wish to add new files.  Upload your files (just has you had done before), click *Next*, observe the newly-added files listed at the bottom of the preview screen.  Click *Submit for Review* to submit your requested change (Adding of files).  The files will be reviewed, and once Approved, the new Variation will be available for download in the Marketplace.

### Releasing a New Version of your App

After time passes, you may wish to add new functionality to your App, or fix a batch of bugs.  This can be accomplished by releasing a new Version of your App.  New Versions offer your users new functionality and bugfixes, and users are generally encouraged to "always use the latest version".  In addition, when a new version of your App becomes available, existing users will be notified automatically through Liferay's notification system.

New Versions of your are created in much the same way as the initial version.  To add a new Version, first navigate to *Home* &rarr; *App Manager* &rarr; *Apps*.  Click the *Action* button next to the App for which you wish to add a new version.  At the bottom of the Details screen, click on the *Add New Version* button.  This button will begin the process of adding a new version, starting with the App Details screen.  In this case, the screen is pre-filled with data from the current version of the App, as shown below:

![Figure 9.10: Add a Version: Details](../../images/marketplace-add-version-details.png) 

You can make any changes to the pre-filled data on this screen, however consider that this is a new version of an existing App, so making major changes (such as completely changing the name or description) might be unsettling to your existing users.  However, it is common that you may want to upload new screenshots and refresh the icon.  Also, note that you cannot change the App Owner (such as moving from a personally-developed App to a company-developed App).  

Clicking *Next* will take you through the same screens as you found before.  On the *Add App Version* screen, you can specify a new version name for this version of your App.  Also note, that when adding new versions to an existing app, you have the option to add *What's New* text.  This is typically filled in with a list of changes for this version, such as significant new features or bugfix information.  Clicking *Next* from here will allow you to upload the files associated with the new version of the app.  For a new version of the App, you must upload all files for all supported Liferay versions again, even if they have not changed since the last version.

### Deactivating your App

When the time comes to retire your App, you can *Deactivate* it.  Deactivating an App causes the App to no longer be downloadable from the Marketplace for new customers, or Appear in any public Marketplace listings.  Existing customers that have already downloaded your App will continue to be able to download the legacy versions of the App they have already acquired, but they will not be able to download any versions they have not already downloaded.  The App remains in your inventory, with all of its history, in case you need to re-activate or reference it in the future.

To deactivate your app, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next to the App for which you want deactivate, and select the *Deactivate* action.


## Tracking App Performance

One of the main reasons for developing and publishing Apps into the Marketplace is to drive downloads and adoption of your App.  The Marketplace enables you, as the developer of your App, to get detailed reports about the number of views and downloads your App(s) are driving.  To access these metrics, navigate to *Home* &rarr; *App Manager* &rarr; *Apps*, click on the *Actions* button next to the App for which you want metrics, and select the *Metrics* action.

![Figure 9.11: App Metrics: Views](../../images/marketplace-app-metrics-views.png) 

The view shown above is the default Metrics view for a single App.  Across the top is a list of data series options (*Views* or *Downloads*).  Below that, a date range can be chosen.  In the middle, a graph is shown for the data within the date range.  Finally, the same data that is graphed is also shown in tabular format, in case you want to know the exact values making up the graph.  The different types of data available to view are described below:

### Views

When someone searches or browses the Marketplace, they click on Apps of interest, to see the detail of the App.  When this occurs for your App, a *View* is recorded for the App, and this data is what is shown on the App Metrics screen when *Views* is selected at the top.  *Views* is also the default view, as show above.  The number of recorded views per day per user is limited.

### Downloads

A download is recorded for your App when someone downloads a specific Variation of a specific Version of your App.  The number of recorded downloads per day per user is limited.

## Summary

In this chapter we introduced concepts and instructions for developers on the Liferay Marketplace, the portal's system for managing Liferay Applications. We looked at how to create, publish, maintain, and track Apps. You do this through  [liferay.com](liferay.com), using your own personal credentials and its features for Marketplace. Next, we covered the requirements for publishing Apps, which did not differ significantly from requirements for general Liferay development. We then looked at how to publish a sample App on the Marketplace, and how to modify it as the App evolves.  Finally, we looked at how to track the adoption of Apps using download and view metrics. We hope this information helps you understand how to develop Apps for Liferay!

