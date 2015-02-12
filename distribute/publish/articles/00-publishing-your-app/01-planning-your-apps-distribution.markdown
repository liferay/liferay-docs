# Planning Your App's Distribution [](id=planning-your-apps-distribution)

<!-- TODO intro -->


<!--
Decide whether the information below is helpful as a segue into making the decisions
-->

You must first develop your app using your preferred development tool. For
example, using
[Liferay IDE or Liferay Developer Studio](/develop/tutorials/-/knowledge_base/6-2/liferay-ide),
the [Plugins SDK](/develop/tutorials/-/knowledge_base/6-2/plugins-sdk),
or [Maven](/develop/tutorials/-/knowledge_base/6-2/maven).
An app will consists of one or more Liferay plugins. Ensure your app is designed
to work with Liferay 6.1 or later. If you wish to target multiple versions of
Liferay (for example, you may wish to support 6.2 EE SP1, 6.2 CE GA1, 6.1 EE
GA3, and 6.1 CE GA3), ensure you have built binary images of your app for each
supported minor family release, if necessary. If a single set of files works
across all supported Liferay versions, you don't need to build multiple versions
of the plugins. Liferay guarantees compatibility within a given minor release
family, so your users can rest assured that your app works with the minor
release that you specify, along with all future maintenance releases of that
minor release.  

## Are you publishing as an individual or on behalf of a company? [](id=are-you-publishing-as-an-individual-or-on-behalf-of-a-company)

When publishing an app, it is possible to publish it on behalf of yourself (an
individual) or a *company* with which you are associated. The selection you make
determines who has access to the app, once published. To understand the concepts
of a Marketplace user, portal administrator, and company, and the ramifications
of publishing apps as an individual versus publishing apps as part of a company,
see the
[Leveraging the Marketplace](/discover/portal/-/knowledge_base/6-2/leveraging-the-liferay-marketplace)
chapter of Using Liferay Portal.

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
