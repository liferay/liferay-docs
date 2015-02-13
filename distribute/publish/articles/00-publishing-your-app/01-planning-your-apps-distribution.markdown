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

First, we'll get down to brass tacks and discuss pricing and licensing. 

## Pricing and Licensing Your App

As the developer, you have significant control over how to price your app. You
choose the license type (standard vs. developer), license term (perpetual vs.
annual), pricing structure (pricing and bundled discounting based on a license
unit), and regional availability. You have the flexibility to experiment on the
Marketplace by tweaking pricing later on or by changing pricing by region.

### Determining a License Term

Here are the license term options:

- Perpetual license: does not expire.

- Non-perpetual license: must be renewed annually.

+$$$

**Note:** Once Liferay has approved your app, you cannot change this option.

If you are a foreign developer based outside of the United States, non-perpetual
license sales (considered to be royalty income) to US customers are subject to a
30% withholding tax. This does not apply to perpetual licenses. For more
information, please refer to the [FAQ](/distribute/faq). 

$$$

### Determining License Options (License Type and License Unit Pricing)

Licenses are set to run on a permitted number of Instance Units (defined as a
single installation of the Liferay Portal, which corresponds to one (1) Liferay
Portal .WAR file). You can create tiers of bundled options to accommodate the
number of Instance Units you wish to offer to customers. You can also offer a
discounted price for a bundle of multiple Instance Units, if desired. 

There are two types of licenses that may be offered for your app: standard and
developer licenses. Standard licenses are intended for production server
environments. Developer licenses are limited to 10 unique IP addresses, and
therefore, should not be used for full-scale production deployments. 

![Figure 1: Liferay Marketplace lets you specify different types of licenses and license quantities for pricing.](../../images/licenseview.png)

You can also choose to offer subscription services and 30-day trials.
Subscription services consist of support, maintenance, and updates. 

Depending on how you decide to license your app, you have a few options around
subscription services: 

**If you're offering a perpetual license...**

You can offer annually renewable subscription services. During the app
submission process if you choose to *offer subscription services*, you will be
asked to price subscription services on a *per Instance Unit per year* basis.
The first year of subscription services is included with a perpetual license.
After that, the customer will need to renew subscription services at the price
you designate. The customer will be entitled to support, maintenance, and
updates as long as they continue to renew subscription services annually.  

If you choose not to offer subscription services, customers will be entitled to
only app updates if and when updates become available. This type of one-time,
upfront payment model may work well for less complex apps and themes/templates. 

**If you're offering a non-perpetual (annual/renewable) license...**

You can offer annually renewable subscription services. During the app
submission process if you choose to *offer subscription services*, the price of
subscription services should be built into the annual price of the non-perpetual
license, so please take this into account when pricing your non-perpetual
license (in effect, the customer would pay one price annually for both the app
license and subscription services). The customer will be entitled to support,
maintenance, and updates as long as they continue to renew their non-perpetual
license. 

If you choose not to offer subscription services, customers will be entitled to
only app updates if and when updates become available, as long as they continue
to have a valid non-perpetual license. 

### Determining Prices and Regional Availability

You have the option of specifying what countries your app will be available in
and the app's price in those countries. You can make it as simple (single price
offered globally) or as granular (different price in each country offered) as
you want.

![Figure 2: Liferay Marteplace lets you specify prices for all of your license options and lets you specify them by region.](../../images/pricing_screenshot.png)

### Considering the Liferay Marketplace Fee

When selling your apps on the Liferay Marketplace, you agree to share app sales
revenue with Liferay. For each app sale, you will receive 80% and Liferay will
receive 20% of the sales proceeds. We believe this type of fee structure is
extremely competitive vis-a-vis other online app marketplaces. Liferay uses its
share of app sales proceeds to: 

- Operate and improve the Liferay Marketplace.
- Provide developer services, such as payment processing, license tracking, and
performance metrics. 
- Continue to invest in and grow the Liferay ecosystem.

**Comparison of App Revenue Sharing**

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

A valid Paypal account is required to receive payment, and you will be asked to
provide this info when converting to a Paid App Developer Account. Payments will
be issued no later than 90 days after the transaction. 

## Are you publishing as an individual or on behalf of a company? [](id=are-you-publishing-as-an-individual-or-on-behalf-of-a-company)

You can publish an app as yourself (an individual) or on behalf of a *company*.
This determines the app's owner and who will be presented as the app's owner.
Your selection also determines who can access the app behind the scenes, once
it's published. 

Publishing on behalf of yourself is the default option. When you publish on
behalf of yourself, your name appears in the Marketplace as the
Publisher/Author. You are the only one who can manage your personal app (add new
releases to it, add new versions of it, edit its details, etc.). 

Publishing on behalf of a company effectively hands the keys over to the
administrators of the company. The app only appears on the company's Marketplace
app development page. Company admins have the same permission you have to manage
this app (add new releases, new versions, edit details, etc). The app appears to
be authored/developed by the company, not you personally. It also appears on the
company's public profile page under its list of apps.

You can [register](https://www.liferay.com/marketplace/become-a-developer)
yourself as a Marketplace Developer or have your company register as a
Marketplace Developer. You can either register for a Free Basic Account or
register for a Paid App Developer Account. A Paid App Developer Account enables
the developer to submit paid apps to the Liferay Marketplace for sale to
customers globally. It allows the developer to monetize the benefits of the
Marketplace and the Liferay distribution channel. The best part is, you can
upgrade to a Paid Developer Account if and when you are ready to submit a paid
app to the Marketplace! Below is a comparison of the Marketplace developer
account options. 

<div class="row-fluid">
<div class="span4 thumbnail text-center" style="padding:20px;">
<img alt="" src="../../wrench-hammer-icon.png" />
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
<img alt="" src="../../arrows-icon.png" />
<h3>Converting from Basic-to-Paid</h3>

<hr />
<ul class="text-left">
	<li>No cost to register as a Marketplace developer</li>
	<li>Distribute free apps on the Marketplace</li>
	<li>Access to developer-only resources (e.g., Developer Portal, Liferay IDE, Liferay Developer License)</li>
	<li>Participate in Marketplace promotions</li>
</ul>
</div>

<div class="span4 thumbnail text-center" style="padding:20px;">
<img alt="" src="../../wrench-hammer-plus-icon.png" />
<h3>Paid App Developer Account</h3>

<hr />
<ul class="text-left">
	<li>No cost to register as a Marketplace developer</li>
	<li>Distribute free apps on the Marketplace</li>
	<li>Access to developer-only resources (e.g., Developer Portal, Liferay IDE, Liferay Developer License)</li>
	<li>Participate in Marketplace promotions</li>
</ul>
</div>
</div>

Now that you've decided on the app's owner and you've registered an the account
to manage the app, you can decide on a price for the app. 

## How do you want to price your app? [](id=how-do-you-want-to-price-your-app)



Once your app is published to the Marketplace,
you cannot change this option. If you wish to have both free and paid licenses
for your app, you must submit one version of your app for free licenses and
another version of your app for paid licenses. 

## What Versions of Liferay do You Want to Target? [](id=what-versions-of-liferay-do-you-want-to-target)

Of course, targeting the widest possible range of Liferay versions in an app
typically draws larger audiences to the app. And there are certain features in
specific versions of Liferay that you may wish to take advantage of. When
uploading apps, you can specify which versions your app is compatible with and
you can have multiple files for your app that are designed for different
versions of the Liferay Platform.

Note that apps on the Liferay Marketplace must be designed for Liferay 6.1 or
later. That's not to say that they can't work with prior versions. However, only
Liferay 6.1 and later versions provide support for installing apps directly from
the Marketplace and provide safeguards against malicious apps. If you wish to
use an app with an earlier version of Liferay, consult the documentation for
that app, as it may or may not be supported.

Read the section below for details on how to specify support for specific
versions of Liferay. 

+$$$

**Note:** If you haven't yet done so,
 make sure to read the chapter
 [Leveraging the Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace)
 from *Using Liferay Portal*! 

$$$

Now that we've covered the basics, you're armed with knowledge to start
creating apps on the Marketplace, so let's see what that looks like in the next
section.

## Consider Packaging Variations for Different Versions of Liferay [](id=consider-packaging-variations-for-different-versions-of-liferay)

Apps can be written to work across many different versions of Liferay. For
example, suppose you wish to publish version 1.0 of your app, which you're
supporting on Liferay 6.1 and 6.2. It may not be possible to create a single
binary `.war` file that works across both Liferay versions, due to
incompatibilities between these Liferay versions. In this case, you need to
compile your app twice: once against Liferay 6.1 and once against 6.2, producing
2 different *packages* (also called variations) of your version 1.0 app. Each
package has the same functionality, but they're different files, and it is these
packages that you can upload in support of different versions of Liferay, as you
will see in a later section. In this guide, packages are sometimes referred to
as files that make up your app.

## Decide on a Versioning Scheme [](id=decide-on-a-versioning-scheme)

The concept of versions is well known in software, and it is no different
here. A version of an app represents the functionality of the app at a given
point in time. When you first create an app, you give it an initial version
(e.g., `1.0`). On updating the app, you increment its version (e.g., from `1.0`
to `1.1`), and you upload new files representing that version of the app. In some
cases, you may want to specify additional qualifiers in order to convey a
special meaning. For example, you may declare that the version of your app is
always in x.y.z format (where you've clearly defined the significance of each x,
y, and z). Liferay Portal versions and official Liferay app versions use this
format.

In any case, you have complete freedom in how you wish to assign version
designators to your app. It is highly recommended that you stick to a well known
and easily understandable format, such as `1.0`, `1.1`, `1.2`, and so on.
Although you may want to include alphabetical characters (e.g., `1.0 Beta 2` or
`6.3 Patch 123235-01`), we discourage it, as it can make it difficult for people
to understand how the app versions relate to one another.

Keep in mind that the version of your app is completely up to you to specify,
but the releases of Liferay with which your app works must be specified using
Liferay's versioning scheme, as explained in
[Understanding Liferay's Releases](/discover/portal/-/knowledge_base/6-2/understanding-liferays-releases).
See the later section *Specify App Packaging Directives* for details on
specifying the releases of Liferay for which your app is designed. 
