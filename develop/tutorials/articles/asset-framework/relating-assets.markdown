# Relating Assets

The ability to relate assets to each other is one of the most powerful features 
of Liferay's asset framework. By relating assets you can connect individual 
pieces of content across your site or portal. When viewing a piece of content, 
seeing a list of other content it's related to is very useful. This not only 
helps with collaboration, but also streamlines things when there's an abundance 
of other content on the site or portal. For example, when a Blog entry is 
related to other assets, those assets appear with that entry in the Blogs 
portlet.

![Figure 1: Related assets in the Blogs portlet.](../../images/asset-fw-related-blog-insults.png)

This tutorial shows you how to leverage Liferay's asset framework to relate the 
assets in your asset-enabled custom portlets with those originating in other 
portlets. A custom Insults portlet is used as an example. After all, your users 
definitely want to be able to relate their insults to other content! You can 
find the beginning Insults portlet here on Github. Likewise, the ending Insults 
portlet is on Github, here. Now go ahead and get started relating your assets!

## Preparing the Service Layer

When implementing related assets in your portlet, the first thing you need to do 
is make some modifications to its service layer. Put the following below any 
finder methods you have in `service.xml` and then re-run service builder:

    <reference package-path="com.liferay.portlet.asset" entity="AssetLink" />

Next, you need to modify the `add*`, `delete*`, and `udpate*` methods in your 
`*LocalServiceImpl` to also persist the asset relationships. This is done with 
the  `updateLinks` and `deleteLinks` methods of `assetLinkLocalService`. For 
example, asset relationships are both added and updated in the Insults portlet's 
`addInsult` and `updateInsult` methods of `InsultLocalServiceImpl` with the 
following code:

    ```
    assetLinkLocalService.updateLinks(
        userId, assetEntry.getEntryId(), serviceContext.getAssetLinkEntryIds(),
        AssetLinkConstants.TYPE_RELATED);
    ```

Similarly, the `deleteInsult(Insult)` method of `InsultLocalServiceImpl` 
contains the following code that deletes the asset relationship whenever the 
asset is deleted:

    assetLinkLocalService.deleteLinks(assetEntry.getEntryId());

Super! Now your portlet's service layer can handle related assets. Even so, 
there's still nothing in your portlet's UI that allows your users to relate 
assets. You'll take care of that in the next step.

## Allowing Asset Relationships to be Created in the UI

Next, you need to allow content creators and editors to relate your portlet's 
assets. This is typically implemented in the JSP your portlet uses to create and 
edit content. For example, in the Insults portlet this is done in the 
`edit_insult.jsp`. Related assets are implemented in the JSP by using the 
Liferay UI tag `liferay-ui:input-asset-links`, which is placed inside a 
collapsible panel. This code is placed inside the `aui:fieldset` tags of the 
JSP. The panel and `liferay-ui:input-asset-links` tag from the Insults portlet 
is shown here:

    ```
    <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="insultAssetLinksPanel" persistState="<%= true %>" title="related-assets">
        <aui:fieldset>
            <liferay-ui:input-asset-links
                className="<%= Insult.class.getName() %>"
                classPK="<%= insultId %>"
            />
        </aui:fieldset>
    </liferay-ui:panel>
    ```

![Figure 2: Your portlet's entity is now shown in the Related Assets menu.](../../images/asset-fw-related-path-01.png)

Awesome! Now content creators and editors can relate the assets of your portlet. 
For now, don't worry that the full package path of your entity is exposed in the 
Related Assets menu--you'll address this later. The next thing you need to do is 
reveal any such related assets to your portlet's users. After all, you don't 
want to give everyone edit access just so they can view related assets!

## Exposing Related Assets to Your Users

Now that you've enabled related assets in your service layer and exposed them to 
content creators and editors in the UI, you need to reveal them to *all* your 
portlet's users. Typically, this is done by creating another JSP for viewing 
individual entity instances of your portlet. For example, in the Insults portlet 
this is implemented by `view_insult.jsp`. The next two sections show you how 
to create such a file and hook it up to the rest of your UI. When you finish, 
you'll be able to click on the entity instances in your portlet to view any 
related assets.

### Creating a JSP for Viewing Related Assets

The first step to expose related assets in your portlet is to create a separate 
JSP for it. Create this JSP in the same directory as your portlet's `view.jsp`. 
For example, in the Insults portlet this JSP is 
`docroot/html/insults/view_insult.jsp`. The rest of this section uses 
`view_insult.jsp` as an example. The first thing you want to do is make sure 
that your users have a way of getting back to `view.jsp` after clicking on an 
entity. This is handled by the `portlet:renderURL` and `liferay-ui:header` tags:

    ```
    <portlet:renderURL windowState="normal" var="backURL">
        <portlet:param name="mvcPath" value="/html/insults/view.jsp"></portlet:param>
    </portlet:renderURL>

    <liferay-ui:header backURL="<%=backURL%>" title="insult" />
    ```

You also want to show the entity in the JSP when a user clicks on it. To do 
this, use `ParamUtil` to get the id of the entity from the `renderRequest`. Then 
create an object using your `*LocalServiceUtil`. Here, this is done to create an 
`Insult` object, which is then used to get the `AssetEntry` object of the 
insult:

    ```
    <%
    long insultId = ParamUtil.getLong(renderRequest, "insultId");
    Insult ins = InsultLocalServiceUtil.getInsult(insultId);
    AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(Insult.class.getName(), ins.getInsultId());
    ```
    %>
    ```
    
Now it's time to expose the related assets. This is done with the 
`liferay-ui:asset-links` tag. Note that the `Insult` object is used to get the 
related assets of the entity:

    ```
    <liferay-ui:asset-links
        assetEntryId="<%=(assetEntry != null) ? assetEntry.getEntryId() : 0%>"
        className="<%=Insult.class.getName()%>"
        classPK="<%=ins.getInsultId()%>" />
    ```

Great! Now you have the JSP that lets your users view related assets. However, 
it's not hooked up to anything yet--your users can't access it from your 
portlet. The next step addresses this issue.

### Creating a URL to Your New JSP

Now that you have exposed related assets, you need to add a URL to your new JSP 
in the `view.jsp`. This is done by inserting the `portlet:renderURL` tag just 
after the `liferay-ui:search-container-row` tag. For example, in the Insults 
portlet it looks like this:

    ```
    <liferay-ui:search-container-row
        className="com.sample.portlet.insults.model.Insult"
        keyProperty="insultId"
        modelVar="insult" escapedModel="<%= true %>"
    >
    
        <portlet:renderURL windowState="maximized" var="rowURL">
            <portlet:param name="mvcPath" value="/html/insults/view_insult.jsp" />
            <portlet:param name="insultId" value="<%= String.valueOf(insult.getInsultId()) %>" />
        </portlet:renderURL>
    ```

Next, add the `href` attribute to the first search container column with the 
value of the URL you just created in the `portlet:renderURL` tag. For example, 
the value of `href` in the Insults portlet is `"<%=rowURL %>"`:

    ```
    <liferay-ui:search-container-column-text
        name="Insult"
        value="<%= insult.getInsultString() %>"
        href="<%=rowURL %>"
    />
    ```

Now just redeploy your portlet and refresh the page so that the `view.jsp` of 
your plugin reloads. Each entity in the portlet should now be a link. Click on 
one to view the new JSP that you made in the previous step of this tutorial. 
Related assets, if you've created any yet, should be visible near the bottom of 
the page.

![Figure 3: Any related assets now show in the new JSP you created.](../../images/asset-fw-related-insult.png)

Awesome! Now your users can view the related assets of custom entities in your 
portlet. There's just one final detail to take care of before you're finished.

## Obscuring the Package Path of Your Custom Entity

Even though related assets are working properly in your portlet, the full 
package path of your entity is still being shown in the Related Assets menu. 
Fortunately, this can be fixed with a very small language hook. For instructions 
on creating a language hook, see the tutorial 
[Overriding Language Properties Using a Hook](/tutorials/-/knowledge_base/6-2/overriding-language-properties-using-a-hook#creating-language-files). 
In the `Language.properties` file of your hook, simply assign the package path 
to the text you want to override it with. For example, the following overrides 
the full package path of the Insults portlet with `Insult`:

    model.resource.com.liferay.docs.insult.model.Insult=Insult

Upon redeploying your portlet, the assignment in your `Language.properties` file 
shows in the Related Assets menu instead of the full package path of your 
entity:

![Figure 4: After deploying a language hook, your entity appears as desired in the Related Assets menu.](../../images/asset-fw-related-select.png)

Most excellent! Now you know how to implement related assets in your portlets. 
Another thing you might want to do is investigate permissioning in the UI. For 
example, the Insults portlet only allows assets to be related by those with 
`ADD_INSULT` or `UPDATE` permission. These permissions are respectively checked 
in the `view.jsp` and `insult_actions.jsp`. For more information on this, see 
the learning path [Checking Permissions in the UI](/learning-paths/-/knowledge_base/6-2/checking-for-permissions-in-the-ui).

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Service Builder and Services](/tutorials/-/knowledge_base/6-2/service-builder)
