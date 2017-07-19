# Adding Asset Features to Your User Interface [](id=adding-asset-features-to-your-user-interface)

Now that your guestbook and guestbook entry entities have been asset-enabled,
you're ready to use Liferay's asset functionality in your application. You'll
start by implementing comments, ratings, tags, categories, and related assets
for guestbooks. Then you'll circle back and implement this same functionality
for guestbook entries. All of the back-end support for these features is
provided by Liferay. Your only task is to update your applications' user
interfaces to use these features.

In this section, you'll be creating several new JSPs that require new imports.
Add the following imports to your `guestbook-web` module project's
`init.jsp` file:

    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.HashMap" %>

    <%@ page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %>
    <%@ page import="com.liferay.asset.kernel.service.AssetTagLocalServiceUtil" %>

    <%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
    <%@ page import="com.liferay.asset.kernel.model.AssetTag" %>

    <%@ page import="com.liferay.portal.kernel.util.ListUtil" %>

It's simpler to add these imports now (rather than as you go) so that you don't
run into errors as you're working through this section.
