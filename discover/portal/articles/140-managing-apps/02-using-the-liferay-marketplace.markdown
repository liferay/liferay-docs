# Using the Liferay Marketplace [](id=using-the-liferay-marketplace)

Liferay Marketplace is a hub for sharing, browsing and downloading 
Liferay-compatible apps. As enterprises look for ways to build and enhance their 
existing platforms, developers and software vendors look for ways to reach this 
market. Marketplace leverages the entire Liferay ecosystem to release and share 
apps in a user-friendly, one-stop site. 

There are two ways to access the Marketplace.

1. Via the website: Using your favorite browser, navigate to the marketplace at
   [http://liferay.com/marketplace](http://liferay.com/marketplace). 
   If you're new to Marketplace, this is the easiest way to access it. You can 
   browse Marketplace without signing in with your liferay.com account. 

2. Via a running Liferay instance: In the Control Panel, navigate to *Apps* 
   &rarr; *Store*. To view Marketplace, you must sign in with your liferay.com 
   account. 

No matter the method you choose, you'll see the same content. Note that 
to download apps, you must have a liferay.com account and agree to the 
Marketplace Terms of Use. You can create this account on 
[https://www.liferay.com/](https://www.liferay.com/). 

## Finding and Purchasing Apps [](id=finding-and-purchasing-apps)

If you've used an app store before, then you'll instantly be familiar with 
Marketplace. Marketplace displays apps in the center of the page, in the 
following sections: 

- Featured Apps: Liferay features a different set of apps each month

- New and Interesting: The latest apps added to Marketplace

- Most Viewed in the Past Month: The top 5 most viewed apps in the last month

- Themes and Site Templates: Apps that change your Liferay instance's look and 
  feel

- App categories: Communication, productivity, security, etc...

In any of these sections, you can view more by clicking the *See All* button at 
the top-right. At the top of the page, you can search Marketplace by category, 
Liferay version, and price. You can also browse by category by clicking the 
*Categories* menu at the top-right of the page. 

![Figure 1: The Liferay Marketplace homepage lets you browse and search for apps.](../../images/marketplace-homepage.png)

Click an app to view its details. This includes its description, screenshots, 
price, latest version, number of downloads, a link to the developer's website, a 
link to the app's license agreement, a link to report abuse, and a purchase 
button (labeled Free or Buy, depending on the price). You can also view the 
app's version history, read reviews left by other users, or write your own 
review. 

If you click the purchase button, Marketplace prompts you to choose a purchase 
type. You can purchase an app for your personal account, or for a Liferay 
project associated with your company. If you have the necessary permissions, you 
can also create a new project for your company. Once you select a purchase type, 
accept the EULA and Terms of Service, and click *Purchase*. 

![Figure 2: Click an app to view its details.](../../images/marketplace-app-details.png)

Once you purchase an app, you can download and install it. An app downloads and 
installs immediately if you purchase it from within a running Liferay instance 
it's compatible with. If you purchase the app on the Marketplace website, 
however, your receipt is displayed immediately after purchase. To download the 
app, click the *See Purchased* button on the bottom of the receipt, and then 
click the *App* button to start the download. You must then 
[install the app manually](/discover/portal/-/knowledge_base/7-0/installing-apps-manually). 
Alternatively, you can use Marketplace within a running Liferay instance to 
download and install the app after purchase on the Marketplace website. The next 
section shows you how to do this. 

Note that in some Liferay instances, automatic app installations via Marketplace 
are disabled so the administrator can manually manage apps. In this case, 
Marketplace apps downloaded in a running Liferay instance are placed in the 
instance's `deploy` directory. The administrator must then manually install the 
app from this directory. Manual install is also required if the Liferay instance 
is behind a corporate firewall or otherwise lacks direct Marketplace access. 
Regardless of how the app is downloaded, the manual install process is the same. 
For details, see the article 
[Installing Apps Manually](/discover/portal/-/knowledge_base/7-0/installing-apps-manually). 

Next, you'll learn how to manage your purchased apps. 

## Managing Purchased Apps [](id=managing-purchased-apps)

There are two places to manage your purchased apps:

1. Your liferay.com account's homepage. After signing in on liferay.com, click 
   the hamburger menu at the top-right, and select *Account Home*. Note that 
   your homepage is distinct from your liferay.com profile page. Your homepage 
   is private, while your profile page is public. On your homepage, select 
   *Apps* from the menu on the left to view your projects. Select a project to 
   view its registered apps. Clicking an app lets you view its versions. You can 
   download the version of the app that you need. This is especially useful if 
   you need a previous version of the app, or can't download the app via a 
   running Liferay instance. 

    ![Figure 3: You can manage your purchased apps from your liferay.com account's homepage.](../../images/marketplace-project-apps.png)

2. In a running Liferay instance. In Control Panel, navigate to *Apps* 
   &rarr; *Purchased* to see your purchased apps. A button next to each app lets 
   you install or uninstall the app in the running Liferay instance. If the app 
   isn't compatible with your version of Liferay, *Not Compatible* is displayed 
   in place of the button. Additional notes on compatibility are also shown, 
   such as whether a newer version of the app is available. You can also search 
   for an app here by project, category, and title. Clicking the app takes you 
   to its Marketplace entry. 

    ![Figure 4: You can also manage your purchased apps from within a running Liferay instance.](../../images/marketplace-purchased.png)

Great! Now you know how to use Liferay Marketplace to discover, install, and 
manage apps. 
