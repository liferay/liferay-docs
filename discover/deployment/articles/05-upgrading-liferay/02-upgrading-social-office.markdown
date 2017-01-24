# Upgrading from Social Office

Liferay Social Office, Liferay's social collaboration product for the 
enterprise, was an add-on product for Liferay Portal versions prior to 
@product-ver@. Because @product-ver@ now contains Social Office's features, 
Social Office is no longer available. When upgrading from a previous version of 
Liferay Portal that contains Social Office, the standard upgrade procedure 
handles most things for you. There are, however, a few additional steps you must 
perform to ensure that the old Social Office features work as intended in 
@product-ver@. This article takes you through these steps.

+$$$

**Note:** Before upgrading your Social Office installation, you must first 
upgrade your Liferay Portal installation to @product-ver@. The steps for this 
are the same as those for upgrading any Liferay Portal installation to 
@product-ver@; you can find them by 
[clicking here](/discover/deployment/-/knowledge_base/7-0/upgrading-to-liferay-7). 
Once you've upgraded to @product-ver@, return here to upgrade your Social Office 
installation. 

$$$

First, you'll learn how the components that made Social Office unique map to 
@product-ver@ features. 

## Social Office Components

Social Office contained the following components: 

- Custom Site Template and Theme
- Customized Liferay Portal Applications
- Applications Specific to Social Office
- User Experience (UX) Enhancements

The following sections describe each component and how it works in 
@product-ver@. 

### Templates and Themes

Social Office's unique look and feel was defined by its custom theme and a 
custom site template. These don't exist in @product-ver@. In @product-ver@, 
however, you can build custom templates and themes to fit your unique 
requirements. Customizing the look, feel, and page layout of @product-ver@ is 
one of its most powerful capabilities. 

This table shows how the template and theme components in Social Office map to 
@product-ver@. 

Component | &nbsp;Social Office 3.x | &nbsp;@product-ver@ | 
--------- | ----------------------- | ------------------- | 
Social Office Theme | Uses Social Office theme | Uses default @product-ver@ theme, or a custom theme |
Site Template | Uses Social Office site template | Site template is upgraded to @product-ver@. A custom site template can also be used. |
Page Templates | Doesn't use page templates | Page templates can be used |

### Liferay Apps

Social Office improved many out-of-the-box apps in Liferay Portal. Many of those 
improvements are now in @product-ver@. 

This table shows how the Liferay Portal apps modified by Social Office map to 
@product-ver@. 

Component | &nbsp;Social Office 3.x | &nbsp;@product-ver@ | 
--------- | ----------------------- | ------------------- | 
Announcements | UI enhancements | Included |
Document Library | Versioning Improvements | Provided as an add-on module. [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.document.library.file.version.discussion.web/) to get this module. |
Notifications | Various enhancements | Included |
Chat | Various enhancements | Coming soon (deprecated) |
Bookmarks | Various enhancements | Included |
Activities | Various enhancements | Included |

### Social Office Apps

Several apps were unique to Social Office. The majority of these apps are now in 
@product-ver@. The Tasks app is one exception; it isn't available in 
@product-ver@. 

This table shows how apps unique to Social Office map to @product-ver@. 

Component | &nbsp;@product-ver@ | 
--------- | ------------------- | 
Microblogs | Included | 
Contacts Center | Included | 
Private Messaging | Provided as an add-on module (coming soon) | 
Social Office User Profile | Included | 
Events List | Provided as an add-on module. [Click here](https://web.liferay.com/marketplace/-/mp/application/83511066) to get this module. | 
WYSIWYG | Provided as an add-on module. [Click here](https://web.liferay.com/marketplace/-/mp/application/15502123) to get this module. | 

### UX Enhancements

The UX in @product-ver@ changed greatly compared to previous versions. 
@product-ver@ is the first version to use Liferay's new 
[Lexicon Design Language](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Lexicon). 
To achieve seamless integration with @product-ver@, any Social Office components 
in @product-ver@ use Lexicon. This establishes consistent UX between sites that 
originated in Social Office and other @product-ver@ sites. It also means that 
certain Social Office components may not be where you expect them in 
@product-ver@. 

This table shows where/how to access Social Office components in @product-ver@. 

Component | &nbsp;@product-ver@ | 
--------- | ------------------- | 
Dashboard | In the Control Panel |
Profile | In the Control Panel |
User Bar | In the Control Panel |
Site Navigation | Use My Sites application |

Here's a list of other Social Office functionality that has changed or is 
missing in @product-ver@: 

- **Tasks application:** not available for @product-ver@. If you need it, you 
must upgrade its source code to @product-ver@ manually. 

- **Site Creation Wizard:** not available for @product-ver@. Use site and page 
templates instead. Your portal administrator should define and create your most 
commonly used site templates and allow site administrators to use them as an 
initial layout for their sites. 

- **Site Navigation:** available, but modified in @product-ver@. Social Office's 
site navigation was a customization of the My Sites app that let Social Office 
users manage their favorite sites and site memberships. You can configure 
@product-ver@'s My Sites application to let users manage site memberships, but 
not favorites. 

Great! Now you know what Social Office functionality is and isn't available in 
@product-ver@. Next, you'll install a few modules in preparation for the 
remaining Social Office upgrade steps. 
<!-- Make sure it's clear this refers to the steps that remain after the portal upgrade from 6.2 to 7.0 -->

## Installation

To enable as much Social Office functionality as possible in your @product-ver@ 
installation, you should first install a few extra modules. You deploy these 
modules just as you would any other @product-ver@ module: 

- **Social Office Upgrade module:** Provides a set of actions that you can run 
  to upgrade your Social Office installation. The next section shows you how to 
  run these actions. 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.office.upgrade/) 
  to get this module. 

- **Social Office Upgrade Association module (optional):** In previous versions 
  of Liferay Portal, when the Social Office User role was granted to a user, 
  Social Office automatically used a site template to create public and private 
  pages for that user. The Social Office Upgrade Association module retains this 
  behavior. 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.office.upgrade.association/) 
  to get this module. 
  
- **Document Library File Version Comments module:** By default, users can only 
  comment on documents in a Documents and Media repository. Social Office 
  enables comments on the document version. This module retains the Social 
  Office behavior for @product-ver@. 
  behavior. 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.document.library.file.version.discussion.web/) 
  to get this module. 

- **Chat:** Enables Social Office chat in @product-ver@ (coming soon). 

- **Private Messaging:** Enables Social Office Private Messaging in 
  @product-ver@ (coming soon). 

- **Events List:** Enables the Social Office Events Display in @product-ver@. 
  [Click here](https://web.liferay.com/marketplace/-/mp/application/83511066) 
  to get this module. 

- **WYSIWYG:** Enables the Social Office WYSIWYG. 
  [Click here](https://web.liferay.com/marketplace/-/mp/application/15502123) 
  to get this module. 

Once you've installed these modules, you're ready to proceed with the upgrade. 
Onwards! 

## Upgrade

Now that you're running @product-ver@ and the modules the previous section 
discusses, you're ready to upgrade your Social Office installation. As mentioned 
earlier, you'll do this by running a set of actions that the Social Office 
Upgrade modules provide. 

+$$$

**Warning:** Some of the Social Office upgrade actions may delete or alter 
existing data in your database. It's essential that you properly backup your 
system before performing any of them. 

$$$

You'll run these commands from the 
[Apache Felix Gogo shell](https://felix.apache.org/documentation/subprojects/apache-felix-gogo.html) 
built into @product-ver@. With @product-ver@ running, enter the Gogo shell by 
entering the following command in a terminal: 

    telnet localhost 11311

This results in a `g!`, which indicates the Gogo shell command prompt. 
