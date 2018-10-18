# Determining Editions and Versions of Liferay to Target [](id=determining-versions-of-liferay-to-target)

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

Note that apps on the Liferay Marketplace must be designed for Liferay Portal 6.1 or
later. That's not to say that they can't work with prior versions. However, only
Liferay Portal 6.1 and later versions support installing apps directly from the
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
