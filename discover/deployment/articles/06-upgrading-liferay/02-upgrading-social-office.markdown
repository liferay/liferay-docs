# Upgrading Social Office [](id=upgrading-social-office)

Liferay Social Office, Liferay's social collaboration product for the 
enterprise, was an add-on product for Liferay Portal versions prior to 
@product-ver@. Social Office is no longer available because @product-ver@ 
contains its features. When upgrading from a previous version of Liferay Portal 
that contains Social Office, 
[the standard upgrade procedure](/discover/deployment/-/knowledge_base/7-0/upgrading-to-liferay-7) 
handles most things for you. You must, however, perform a few additional steps 
to ensure that Social Office's features work as intended in @product-ver@. This 
article takes you through these steps. 

+$$$

**Note:** Before upgrading your Social Office installation, you must first 
upgrade your Liferay Portal installation to @product-ver@. The steps for this 
are the same as those for upgrading any Liferay Portal installation to 
@product-ver@; 
[click here](/discover/deployment/-/knowledge_base/7-0/upgrading-to-liferay-7) 
to see them. Once you've upgraded to @product-ver@, return here to upgrade your 
Social Office installation. 

$$$

First, you'll learn how Social Office's unique components map to @product-ver@
features. 

## Social Office Components [](id=social-office-components)

Social Office contained the following components: 

- A unique theme and site template
- Customized Liferay Portal applications
- Applications unique to Social Office
- User experience (UX) enhancements

The following sections describe the components and how they work in 
@product-ver@. 

### Themes and Templates [](id=themes-and-templates)

Social Office's unique look and feel was defined by its theme and site template.
These don't exist in @product-ver@. In @product-ver@, however, you can build
themes and templates to fit your requirements. The ability to customize the
look, feel, and page layout of @product-ver@ is one of its most powerful
capabilities. 

This table shows how Social Office's theme and template components map to 
@product-ver@. 

Component | &nbsp;Social Office 3.x | &nbsp;@product-ver@ | 
--------- | ----------------------- | ------------------- | 
Social Office Theme | Uses Social Office theme | Uses default @product-ver@ theme, or a custom theme |
Site Template | Uses Social Office site template | The Social Office site template is upgraded to @product-ver@. A custom site template can also be used. |
Page Templates | Doesn't use page templates | Page templates can be used |

### Liferay Apps [](id=liferay-apps)

Social Office improved many out-of-the-box apps in previous versions of Liferay 
Portal. Many of those improvements are now in @product-ver@. 

This table shows how the apps modified by Social Office in previous versions of 
Liferay Portal map to @product-ver@. 

App | &nbsp;Social Office 3.x | &nbsp;@product-ver@ | 
--- | ----------------------- | ------------------- | 
Announcements | UI enhancements | Included |
Document Library File Version Comments | Versioning Improvements | Provided as an add-on module. The [*Module Installation* section below](/discover/deployment/-/knowledge_base/7-0/upgrading-social-office#module-installation) contains a link to this module. |
Notifications | Various enhancements | Included |
Chat | Various enhancements | [Click here](https://web.liferay.com/marketplace/-/mp/application/15184463) to get this app for Liferay DXP. Note that Chat isn't available for Liferay Portal CE. |
Bookmarks | Various enhancements | Included |
Activities | Various enhancements | Included |

### Social Office Apps [](id=social-office-apps)

Several apps were unique to Social Office. With the exception of the Tasks 
portlet, these apps are now in @product-ver@. 

This table shows how apps unique to Social Office map to @product-ver@. 

App | &nbsp;@product-ver@ | 
--- | ------------------- | 
Microblogs | Included | 
Contacts Center | Included | 
Private Messaging | Provided as an add-on module. The [*Module Installation* section below](/discover/deployment/-/knowledge_base/7-0/upgrading-social-office#module-installation) contains more information. | 
Social Office User Profile | Included | 
Events List | Provided as an add-on module. [Click here](https://web.liferay.com/marketplace/-/mp/application/83511066) to get it for Liferay Portal CE [or here](https://web.liferay.com/marketplace/-/mp/application/83511153) for Liferay DXP. | 
WYSIWYG | Provided as an add-on module. [Click here](https://web.liferay.com/marketplace/-/mp/application/15502123) to get it for Liferay Portal CE [or here](https://web.liferay.com/marketplace/-/mp/application/15503342) for Liferay DXP. | 

### UX Enhancements [](id=ux-enhancements)

The UX in @product-ver@ is different than previous versions. It's the first
version to use Liferay's new 
[Lexicon Design Language](https://dev.liferay.com/participate/liferaypedia/-/wiki/Main/Lexicon). 
Social Office components in @product-ver@ use Lexicon for a consistent UX. This 
also means that certain Social Office components may not be where you expect 
them in @product-ver@. 

This table shows where to access Social Office components in @product-ver@. 

Component | &nbsp;@product-ver@ | 
--------- | ------------------- | 
Dashboard | Control Panel |
Profile | Control Panel |
User Bar | Control Panel |
Site Navigation | My Sites app |

Here's a list of other Social Office functionality that has changed or is 
missing in @product-ver@: 

- **Tasks application:** not available for @product-ver@. If you need it, you 
  must upgrade its source code. 

- **Site Creation Wizard:** not available for @product-ver@. Use site and page 
  templates instead. An administrator should create the most commonly used site 
  templates and let site administrators use them as an initial layout for their 
  sites. 

- **Site Navigation:** available, but modified in @product-ver@. Social Office's 
  customized My Sites app let Social Office users manage their favorite sites 
  and site memberships. You can configure @product-ver@'s My Sites app to let 
  users manage site memberships, but not favorites. 

Great! Now you know what Social Office functionality is and isn't in 
@product-ver@. Next, you'll install a few modules in preparation for the Social 
Office upgrade steps. 

## Module Installation [](id=module-installation)

To enable Social Office functionality in your @product-ver@ installation, you 
should first install a few extra modules. Deploy these modules as you would any 
other @product-ver@ module: 

- **Social Office Upgrade module:** Provides a set of actions that you can run 
  to upgrade your Social Office installation. The next section shows you how to 
  run these actions. 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.office.upgrade/) 
  to get this module. This module is the same for Liferay Portal CE and Liferay 
  DXP. 

- **Social Office Upgrade Association module (optional):** When the Social 
  Office User role was granted to a user in previous versions of Liferay Portal, 
  Social Office automatically used a site template to create public and private 
  pages for that user. The Social Office Upgrade Association module retains this 
  behavior (the upgrade retains the Social Office User role). 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.social.office.upgrade.association/) 
  to get this module. This module is the same for Liferay Portal CE and Liferay 
  DXP. 

- **Document Library File Version Comments module:** By default, @product-ver@ 
  users can only comment on documents in a Documents and Media repository. In 
  Social Office, however, users could also comment on document versions. This 
  module retains the Social Office behavior. 
  [Click here](https://repository.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.document.library.file.version.discussion.web/) 
  to get this module. This module is the same for Liferay Portal CE and Liferay 
  DXP. 

- **Chat:** Enables Social Office chat in Liferay DXP. 
  [Click here](https://web.liferay.com/marketplace/-/mp/application/15184463) 
  to get this app. Note that Chat isn't available for Liferay Portal CE. 

- **Private Messaging:** Enables Social Office Private Messaging in 
  @product-ver@. Although the first three GA releases (GA1, GA2, GA3) of Liferay 
  Portal 7.0 CE contain this app, it will be removed in GA4 and instead released 
  on Liferay Marketplace. To get this app for Liferay DXP, which doesn't contain 
  it, 
  [click here](https://web.liferay.com/marketplace/-/mp/application/83559411). 

- **Events List:** Enables Social Office Events Display in @product-ver@. 
  [Click here](https://web.liferay.com/marketplace/-/mp/application/83511066) 
  to get this app for Liferay Portal CE 
  [or here](https://web.liferay.com/marketplace/-/mp/application/83511153) 
  for Liferay DXP. 

- **WYSIWYG:** Enables the Social Office WYSIWYG app. 
  [Click here](https://web.liferay.com/marketplace/-/mp/application/15502123) 
  to get this app for Liferay Portal CE 
  [or here](https://web.liferay.com/marketplace/-/mp/application/15503342) 
  for Liferay DXP. 

Once you've installed these modules, you're ready to proceed with the upgrade. 
Onward! 

## Upgrade [](id=upgrade)

Now that you're running @product-ver@ and the preceding modules, you're ready to 
upgrade your Social Office installation. As mentioned earlier, you'll do this by 
running a set of actions that the Social Office Upgrade modules provide. 

+$$$

**Warning:** Some of the Social Office upgrade actions may delete or alter 
existing data in your database. It's essential that you backup your 
system [properly](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation)
before running any of them. 

$$$

You'll run these actions from the 
[Apache Felix Gogo shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell) 
built into @product-ver@. With @product-ver@ running, enter the Gogo shell by 
running the following command in a terminal: 

    telnet localhost 11311

The resulting `g!` is the Gogo shell command prompt. 

![Figure 1: The Gogo shell lets you execute commands, including the Social Office upgrade commands, in the OSGi runtime that runs @product-ver@. This screenshot shows the `telnet` command that enters the shell, and the resulting Gogo shell command prompt.](../../images/gogo-prompt.png)

+$$$

**Warning:** The Gogo shell is only available from the machine running 
@product-ver@, unless you explicitly open port 11311. For security reasons, 
**do not** expose the Gogo console to the outside world. 

$$$

Now you're ready to execute the Social Office upgrade actions. To run an action, 
execute it on the Gogo shell prompt. Before doing so, however, be sure to 
carefully read the action's description. Each action is listed here, along with 
its description: 

- `socialOffice:executeAll`: runs all upgrade actions. Before running this 
  action, be sure to read the descriptions for all actions. 

- `socialOffice:removeTasksPortlet`: removes the Tasks portlet from all pages. 
  Unless you manually upgrade the Tasks portlet's source code, this portlet is 
  unavailable in @product-ver@. You should therefore remove it from all pages in 
  your @product-ver@ installation. Note that doing so removes all references to 
  it from all site pages; after executing this action, there's no way to restore 
  it. 

- `socialOffice:hideTasksLayout`: hides all pages that contained the Tasks 
  portlet. 

- `socialOffice:updateSocialSiteTheme`: restores the default theme for all 
  pages. Unless you customized the Social Office theme, you should use the 
  default @product-ver@ theme. 

Next, you'll attend to some administrative tasks in your @product-ver@ instance. 

## Administration [](id=administration)

After running the upgrade actions, there are a few administrative tasks you 
should complete in your @product-ver@ instance. These tasks help to retain 
Social Office functionality in @product-ver@. 

- **Social Office User Role (optional):** The upgrade to @product-ver@ carries 
  over the Social Office User role. If the optional Social Office Upgrade 
  Association module is deployed, you can assign users to this role. Doing so 
  gives users personal sites that function similarly to Social Office. 

- **Managing Social Sites in @product-ver@:** The upgrade to @product-ver@ 
  carries over all Social Office sites and site templates. To create a new 
  social site for collaboration in @product-ver@, create a new site using the 
  default Social Office site template. For more information on building sites 
  from templates, 
  [click here](/discover/portal/-/knowledge_base/7-0/building-sites-from-templates). 

- **My Sites:** The Site Navigation app was Social Office's custom version of 
  the My Sites app. It was integrated into Social Office's theme. Since the Site 
  Navigation app isn't available for @product-ver@, you should use My Sites 
  instead. As mentioned earlier, however, My Sites doesn't let users manage 
  their site memberships by default. To enable this functionality, add My Sites 
  to a custom theme or site template that enables it. For navigating sites, 
  users can use the new @product-ver@ site navigator in the Product Menu. 
