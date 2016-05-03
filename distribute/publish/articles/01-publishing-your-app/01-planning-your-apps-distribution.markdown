# Planning Your App's Distribution [](id=planning-your-apps-distribution)

When you start the formal process of submitting your app to the Marketplace, in
addition to uploading your app's files you'll need to answer a host of important
questions. For example, you'll need to clarify who owns the app, specify pricing
for the app, define its licensing scheme (if it's a paid app), associate a
person or company as its owner and maintainer, and specify the versions of
Liferay that the app supports. Your answers to these questions will also help
you determine whether you'll need to package multiple versions of the app. This
tutorial prepares you by explaining the questions and ways you might answer
them. 

## Selling Your App or Making it Free [](id=selling-your-app-or-making-it-free)

Do you want to sell your app on the Marketplace? Or do you want to freely share
it with anyone on the Marketplace? It's up to you. Most of the content that
follows describes options for paid apps (apps you sell). 

If you're selling your app, you must publish using a Paid App Account. You must
also specify licensing, a price structure, and regional availability for your
paid apps.

Importantly, you can't change the app from free to paid or from paid to free
once the app is published to the Marketplace. In order to offer the app in the
other license type, you must submit another app under a different name (title).
If you wish to have both free and paid licenses for your app, you must submit
the app under one name for free licenses and submit it under another name for
paid licenses. Make sure to select the license type (i.e., free or paid) that's
best for your app. 

Have you decided who's going to be listed as the app's author/owner? Have
you decided who's going to manage the app once it's on the Marketplace? App
ownership options are explained next. 

## Publishing as an Individual or on Behalf of a Company [](id=publishing-as-an-individual-or-on-behalf-of-a-company)

You can publish an app as yourself (an individual) or on behalf of a *company*.
This determines the who is shown as the app's author and owner. Your selection
also determines who can access the app behind the scenes, once it's published. 

The default option is publishing on behalf of yourself. If you go with this
option, your name is shown as the app's author/owner in the Marketplace. The
term *personal app* refers to an app published by an individual. That individual
is the only one who can manage the personal app. Managing an app includes such
duties as adding new releases to it, adding new versions of it, and editing its
details. 

Publishing on behalf of a company effectively hands the keys over to the
company's administrators. The app shows on the company's Marketplace app
development page and in the company's list of apps on the company's public
profile page. Company admins have the same permission that an individual author
has to manage the app (add new releases, new versions, edit details, etc). The
company's name alone is shown as the app's author/owner. 

You can [register](https://www.liferay.com/marketplace/become-a-developer)
yourself as a Marketplace Developer or one of your company's administrators can
register the company as a Marketplace Developer. You can either register for a
Free Basic Account or register for a Paid App Developer Account. A Paid App
Developer Account lets you submit paid apps to the Liferay Marketplace for sale
to customers globally. It enables you to monetize the benefits of the
Marketplace and the benefits of the Liferay distribution channel. The best part
is, you can upgrade to a Paid App Developer Account if and when you want to
submit a paid app to the Marketplace! Here's a comparison of the Marketplace
developer account options: 

<div class="row-fluid">
<div class="span4 thumbnail text-center" style="padding:20px;">
<img alt="" src="../../images/wrench-hammer-icon.png" />
<h3>Free Basic Account</h3>

<hr />
<ul class="text-left">
	<li>No cost to register as a Marketplace developer</li>
	<li>Distribute free apps on the Marketplace</li>
	<li>Access to developer-only resources (e.g., Developer Portal, Liferay IDE, Liferay Developer License)</li>
	<li>Participate in Marketplace promotions</li>
</ul>
</div>

<div class="span4 thumbnail text-center" style="padding:20px;">
<img alt="" src="../../images/arrows-icon.png" />
<h3>Converting from Basic-to-Paid</h3>

<hr />
<p style="text-align: left;">When you're ready to submit a paid app to the Marketplace:</p>
<ol class="text-left">
	<li>Agree to the Developer Agreement</li>
	<li>Pay $99 annual fee</li>
	<li>Submit relevant tax documents (eg., W-9, W-8BEN) to receive payments</li>
	<li>Enter PayPal info to receive payments (PayPal account must be a Verified Business Account)</li>
</ol>
</div>

<div class="span4 thumbnail text-center" style="padding:20px;">
<img alt="" src="../../images/wrench-hammer-plus-icon.png" />
<h3>Paid App Developer Account</h3>

<hr />
<ul class="text-left">
	<li>Upgrade when you're ready: only pay annual fee at time of paid app submission</li>
	<li>Offer paid licensing/support for your apps</li>
	<li>Receive 80% of app sales proceeds</li>
	<li>Access to detailed transaction history</li>
	<li>Paid apps customer management</li>
	<li>All the benefits of a Basic Developer Account</li>
</ul>
</div>
</div>

Now that you've determined your app's owner and you've registered an account to
manage the app, you can learn about licensing options for paid apps. 

## Licensing and Pricing Your App [](id=licensing-and-pricing-your-app)

You have significant control over how to price your app. You choose the license
term (perpetual vs. annual), choose the license type (standard vs. developer),
define a pricing structure (pricing and bundled discounting based on a license
unit), and specify regional availability. Even after your app is on the
Marketplace, you can tweak general pricing or modify regional pricing. 

### Determining a License Term [](id=determining-a-license-term)

Here are the license term options:

- Perpetual license: does not expire.

- Non-perpetual license: must be renewed annually.

Importantly, you can't change your app's license terms once the app is approved.
In order to release an approved app under a different license term you must
submit another app under a new name (title). So make sure you think through the
license term that makes the most sense for your app. 

+$$$

**Note:** If you are a foreign developer based outside of the United States,
non-perpetual license sales (considered to be royalty income) to US customers
are subject to a 30% withholding tax. This tax does not apply to perpetual
licenses. For more information on licensing terms and fees, please refer to the
[FAQ](/distribute/faq). 

$$$

### Determining License Type and License Unit Pricing [](id=determining-license-type-and-license-unit-pricing)

Licenses are set to run on a permitted number of Instance Units (defined as a
single installation of the Liferay Portal, which corresponds to one (1) Liferay
Portal `.war` file). You can create tiers of bundled options to accommodate the
number of Instance Units to offer to customers. You can also offer a discounted
price for a bundle of multiple Instance Units.

There are two types of licenses that you can offer for your app: standard
licenses and developer licenses. Standard licenses are intended for production
server environments. Developer licenses are limited to 10 unique IP addresses
and, therefore, should not be used for full-scale production deployments. 

![Figure 1: Liferay Marketplace lets you specify different types of licenses and license quantities for pricing.](../../images/licenseview.png)

You can also offer subscription services or 30-day trials. Support, maintenance,
and updates should comprise subscription services.

Depending on how you decide to license your app, you have a few options to
consider with regard to subscription services: 

**If you're offering a perpetual license...**

You can offer annually renewable subscription services. During the app
submission process if you choose to *offer subscription services*, you're asked
to price subscription services on a *per Instance Unit per year* basis. The
first year of subscription services is included with a perpetual license. If a
customer wants to continue receiving services after the first year, the customer
must renew subscription services at the price you designate. Customers are
entitled to support, maintenance, and updates as long as they continue to
annually renew subscription services.  

If you choose not to offer subscription services, customers are entitled to only
app updates, if and when updates become available. This type of one-time,
upfront payment model may work well for less complex apps and themes/templates. 

**If you're offering a non-perpetual (annual/renewable) license...**

You can offer annually renewable subscription services. During the app
submission process if you choose to *offer subscription services*, you should
build the price of subscription services into the annual price of the
non-perpetual license; please take this into account as you price non-perpetual
licenses. In effect, the customer pays one price annually for both the app
license and subscription services. Customers is entitled to support,
maintenance, and updates as long as they continue to renew their non-perpetual
license.

If you choose not to offer subscription services, customers are entitled to only
app updates if and when updates become available. They can receive updates as
long as they continue to have valid non-perpetual licenses. 

### Setting Prices for License Options by Region [](id=setting-prices-for-license-options-by-region)

You can specify countries your app will be available in and the app's price in
in each of those countries. You can make it as simple (single price offered
globally) or as granular (different price in each country offered) as you want.

![Figure 2: Liferay Marteplace lets you specify prices for all of your license options and lets you specify them by region.](../../images/pricing_screenshot.png)

Choose the currency to use with your pricing options. Decide on the renewal cost
for any support services you offer. The support services price is per instance,
so if you specify $100 USD and the customer is running 10 instances, their
annual support services renewal cost will be $1000. Note: This only applies to
perpetual licenses. For non-perpetual licenses, you should include any support
services cost in the annual license price. 

Even after an app has been approved, you can change the currency type and
currency price of its license bundles, and you can modify regional availability
of license bundles. 

Although Liferay Marketplace supports major currencies and a broad list of
countries, not all currencies and countries are currently available. Additional
currencies and countries may become available at a later time. 

### Considering the Liferay Marketplace Fee [](id=considering-the-liferay-marketplace-fee)

By selling your paid apps on the Liferay Marketplace, you're agreeing to share
app sales revenue with Liferay. For each app sale, you receive 80% of the sales
proceeds and Liferay receives 20% of the sales proceeds. We believe this type of
fee structure is extremely competitive compared to other app marketplaces. 
Liferay uses its share of app sale proceeds to:

- Operate and improve the Liferay Marketplace.
- Provide developer services, such as payment processing, license tracking, and
performance metrics. 
- Continue investing in and growing the Liferay ecosystem.

**Comparison of Liferay's App Revenue Sharing with that of Other App
Marketplaces**

<table class="revenue-sharing-table">
<tbody>
<tr class="header">
<td></td>
<td>Share to Developer</td>
</tr>

<tr class="highlight-row">
<td>Liferay Marketplace</td>
<td>80%</td>
</tr>
<tr> <td>Atlassian Marketplace</td>
<td>75%</td>
</tr>

<tr>
<td>DNN Store</td>
<td>75%</td>
</tr>

<tr>
<td>Concrete 5</td>
<td>75%</td>
</tr>

<tr>
<td>Apple App Store</td>
<td>70%</td>
</tr>
</tbody>
</table>

As you can see, Liferay's fee is reasonable. 

You need a valid Paypal account to receive payment; you're asked to provide this
info when registering for or converting to a Paid App Developer Account.
Payments are issued no later than 90 days after the transaction. 

Now that you've decided on licensing options and pricing, you can concentrate on
what versions of Liferay your app will run on. 

## Determining Editions and Versions of Liferay to Target [](id=determining-versions-of-liferay-to-target)

Of course, targeting the widest possible range of Liferay editions and versions
in an app typically draws larger audiences to the app. And there may be certain
features in these editions and versions that you want to take advantage of. In
your app's plugin [packaging properties](/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app#specifying-packaging-directives),
you specify packaging directives to indicate the [editions](/discover/deployment/-/knowledge_base/6-2/editions-of-liferay)
the app supports and the version that the app supports. To ensure the widest
audience for your app, we encourage you to make your app compatible with both
Liferay Community Edition (CE) and Liferay Enterprise Edition (EE). 

You can prepare a set of app files (including its
`liferay-plugin-package.properties` file) for each version of Liferay you want
to support. When uploading your app, you can specify which versions of Liferay
your app is compatible with and you can appropriately upload the sets of app
files that are designed for those different versions. The next article in this
guide explains how to go about
[specifying packaging directives](/distribute/how-to-publish/-/knowledge_base/how-to-publish/preparing-your-app#specifying-packaging-directives). 

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 or
later. That's not to say that they can't work with prior versions. However, only
Liferay 6.1 and later versions support installing apps directly from the
Marketplace and provide safeguards against malicious apps. If you want to use an
app with an earlier version of Liferay, make sure that version of Liferay
provides what your app needs from Liferay. 

Lastly, you should determine a versioning scheme for your app. How will you
refer to the first version of your app, the second version, and so on. 

## Decide on a Versioning Scheme [](id=decide-on-a-versioning-scheme)

A version of an app represents the functionality of the app at a given point in
time. When you first create an app, you give it an initial version (e.g.,
`1.0`). On updating the app, you increment its version (e.g., from `1.0` to
`1.1`), and you upload new files representing that version of the app. In some
cases, you specify additional qualifiers in order to convey a special meaning.
For example, you may declare that the version of your app is always in x.y.z
format (where you've clearly defined the significance of x, y, and z). Liferay
Portal versions and official Liferay app versions use this format.

In any case, you're free to your app's assign version designators any way you
like. We recommend that you stick to a well known and easily understandable
format, such as `1.0`, `1.1`, `1.2`, and so on. Although you may want to include
alphabetical characters (e.g., `1.0 Beta 2` or `6.3 Patch 123235-01`), we
discourage it, as such characters may confuse customers as to how your app's
versions relate to one another.

Keep in mind that the releases of Liferay with which your app works must be
specified using Liferay's versioning scheme, as explained in
[Understanding Liferay's Releases](/discover/deployment/-/knowledge_base/6-2/understanding-liferays-releases).
See the later section *Specify App Packaging Directives* for details on
specifying the releases of Liferay for which your app is designed. 

Congratulations on coming up with a sound game plan for your app! Next, you
should read the next article to learn how to prepare your app for publishing. 
