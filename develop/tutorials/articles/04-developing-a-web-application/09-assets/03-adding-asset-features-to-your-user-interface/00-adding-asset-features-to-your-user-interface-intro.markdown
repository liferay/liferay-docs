# Adding Asset Features to Your User Interface [](id=adding-asset-features-to-your-user-interface)

<div class="learn-path-step">
    <p>Adding Asset Features to Your UI<br>Step 1 of 5</p>
</div>

Now that your guestbook and guestbook entry entities are asset-enabled, you're
ready to add asset functionality to your application. You'll start by
implementing comments, ratings, tags, categories, and related assets for
guestbooks. Then you'll do the same for guestbook entries. All the back-end
support for these features is already implemented. Your only task is to update
your applications' user interfaces to use these features. 

Now you'll create several new JSPs that need new imports. Add the following
imports to the `guestbook-web` module project's `init.jsp` file: 

    <%@ page import="java.util.Map" %>
    <%@ page import="java.util.HashMap" %>

    <%@ page import="com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil" %>
    <%@ page import="com.liferay.asset.kernel.service.AssetTagLocalServiceUtil" %>

    <%@ page import="com.liferay.asset.kernel.model.AssetEntry" %>
    <%@ page import="com.liferay.asset.kernel.model.AssetTag" %>

    <%@ page import="com.liferay.portal.kernel.util.ListUtil" %>

It's simpler to add these imports now so you don't run into errors as you're 
working through this section. 
