# Social Office EE [](id=social-office-ee)

Social Office EE can only be installed on a Liferay EE instance; it's not
possible to install Social Office EE on Liferay CE. Social Office EE provides
several useful features in addition to the ones provided by Social Office CE.
These additional features include the following:

- Enhanced auditing tools for the Documents and Media library
- The ability to mount Documentum and Sharepoint repositories to the Documents
  and Media library.
- The ability for Liferay Sync users to sync with multiple Social Office sites
- Bug fixes and usability improvements since Social Office 2.0 CE GA2
- Access to Liferay Support

## Installing Social Office EE 

To find Social Office EE on Liferay Marketplace, click on the *EE Marketplace*
link and look for the Social Office EE app. Once you've found it, click on it to
view detailed information about the app. If you'd like to install Social Office
EE, you can either purchase the app or you can obtain a free 30-day trial.

![Figure 1.x: If you'd like to try out Social Office EE, click on the *30-Day Trial* button. If you'd like to purchase Social Office EE, click on the *Order* button.](../../images/so-ee-order-trial.png)

Whichever option you choose, you'll be prompted to select a purchase type. If
you'd like to obtain the free 30-day trial, you'll need to agree to the End User
License Agreement and Terms of Service before you can click *Free* to obtain the
trial license.

![Figure 1.x: As with other apps on Liferay Marketplace, you can purchase Social Office EE for your personal liferay.com account or for your company.](../../images/so-ee-order-type.png)

If you'd like to place an order to purchase Social Office EE, click *Next* after
selecting a purchase type. Then you'll need to choose a license type, provide
your name, address, and contact information, and agree to the terms of use.
Once you click *Order*, a Liferay sales representative will contact you to
fulfill your order.

![Figure 1.x: To deploy your Social Office EE license, enter your order id license into the field provided, click *Query*, and select *Social Office EE*.](../../images/so-ee-order-id-license.png)

Once you've obtained a Social Office EE license (trial or otherwise), you should
deploy the license to your Liferay instance. To do so, first click on *License
Manager* at the bottom of the Server Administration section of the Control
Panel. Then enter your order id into the field provided, click *Query*, and
select *Social Office EE*. Now you're able to install Social Office EE! Note: If
you already downloaded Social Office EE and attempted to install it before
deploying your order id license, you'll need to restart your Liferay server
after deploying the license in order for Social Office EE to deploy.

After installing (or uninstalling) Social Office EE, you should
confirm that these plugins, in addition to the ones included with Social Office 
CE, have been successfully deployed (or removed). Your server's console log 
should indicate whether or each plugin has been successfully deployed.

**EE Plugins:**

- documentum-hook
- sharepoint-hook

## Social Office EE Features 

The Documentum and Sharepoint plugins that are included in Social Office EE are
the same plugins as those provided by the [Documentum Connector EE](https://www.liferay.com/marketplace/-/mp/application/15098914) and
[Sharepoint Connector EE](https://www.liferay.com/marketplace/-/mp/application/15188537) apps on
Liferay Marketplace. These plugins integrate Liferay's Documents and Media
functionality with Documentum and Sharepoint. They allow Documentum and
Sharepoint repositories to be mounted within Liferay's Documents and Media
system. Please refer to the Liferay Marketplace app descriptions and the
Documents and Media section of the
[Liferay User Guide](/discover/portal/-/knowledge_base/6-2/using-external-repositories)
for information on properties and configuration.

Other features provided by Social Office EE include auditing improvements to the
Documents and Media library. Once you've installed Social Office EE, you'll find
the option to specify either a major or a minor revision each time you edit or
check in a document. You'll also be prompted to enter a change log message.
Without Social Office EE, the version number will be automatically incremented
when a file is edited but there's no way to specify a major/minor revision or
enter a change log message.

![Figure 1.x: Social Office EE allows you to specify a major or minor revision and enter a change log message each time you edit or check in a document.](../../images/so-ee-doclib-checkin-screen.png)

Additionally, Social Office EE allows comments to be added to specific versions
of a document. Without Social Office EE, there's just a general comments area
for each document that's not specific to any version. If you've been using
Social Office CE and upgrade to EE, any comments that were added when you were
using CE will appear in a section entitled *Previous Comments*.

![Figure 1.x: Comments can be added to specific versions of a document in Social Office EE.](../../images/version-control-ee.png)

Next, let's look at Social Office's upgrade process.
