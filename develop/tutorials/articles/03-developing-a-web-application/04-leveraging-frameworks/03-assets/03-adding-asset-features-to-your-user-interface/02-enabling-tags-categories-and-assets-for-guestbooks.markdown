# Enabling Tags, Categories, and Related Assets for Guestbooks [](id=enabling-tags-categories-and-related-assets-for-guestbooks)

Since you've already asset-enabled guestbooks at the service layer, your
guestbook entities are all set to take advantage of Liferay's back-end support
for tags and categories. Your only remaining task is to update your user
interface to allow access to these features. Recall that you've designed your
application to allow users to add guestbooks from two different portlets: the
Guestbook portlet and the Guestbook Admin portlet. In this section, you'll
update the form on the Guestbook Admin portlet's `edit_guestbook.jsp` page to
allow admins to add, edit, or remove tags and categories when adding or updating
a guestbook. For simplicity's sake, you'll leave the Guestbook portlet's
`edit_guestbook.jsp` page alone. (Of course, nothing is preventing you from
adding tags and categories functionality to the Guestbook portlet's
`edit_guestbook.jsp` except a design decision.)

Open the guestbook admin portlet project's
`/html/guestbookadminmvcportlet/edit_guestbook.jsp` file. Replace the existing
contents with the following contents:

    <%@ include file="../init.jsp" %>
    
    <%

    long guestbookId = ParamUtil.getLong(request, "guestbookId");

    Guestbook guestbook = null;

    if (guestbookId > 0) {
    	guestbook = GuestbookLocalServiceUtil.getGuestbook(guestbookId);
    }
    %>
    
    <portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/guestbookmvcportlet/view.jsp" />
    </portlet:renderURL>

    <portlet:actionURL name="addGuestbook" var="addGuestbookURL" />
    
    <aui:form action="<%= addGuestbookURL %>" name="fm">
        <aui:fieldset>
            <aui:input name="name" />
        </aui:fieldset>

    <liferay-ui:asset-categories-error />
                        <liferay-ui:asset-tags-error />
                        <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="guestbookCategorizationPanel" persistState="<%= true %>" title="categorization">
                                <aui:fieldset>
                                        <aui:input name="categories" type="assetCategories" />

                                        <aui:input name="tags" type="assetTags" />
                                </aui:fieldset>
                        </liferay-ui:panel>

                        <liferay-ui:panel defaultState="closed" extended="<%= false %>" id="guestbookAssetLinksPanel" persistState="<%= true %>" title="related-assets">
                                <aui:fieldset>
                                        <liferay-ui:input-asset-links
                                                className="<%= Guestbook.class.getName() %>"
                                                classPK="<%= guestbookId %>"
                                        />
                                </aui:fieldset>
                        </liferay-ui:panel>

      <aui:button-row>
    		<aui:button type="submit" />

    		<aui:button onClick="<%= viewURL %>" type="cancel" />
    	</aui:button-row>
    </aui:form>

Here, you're using Liferay and AUI JSP tags to add tags, categories, and related
assets to the form for adding or updating a guestbook. First, you add the
`<liferay-ui:asset-categories-error />` and `<liferay-ui:asset-tags-error/>` 
tags to the form. These tags are responsible for displaying custom error 
messages that appear if an error occurs with the categories or tags that are
submitted on the form. Next comes a `<liferay-ui:panel>` tag with several
attributes set. The `<liferay-ui:panel>` tag generates a collapsible section
inside which you add the input fields for tags and categories.

When using AUI, you can group related input fields together with an
`<aui:fieldset>` tag. You add the following two tags inside of an
`aui:fieldset>` tag:

    <aui:input name="categories" type="assetCategories" />
    <aui:input name="tags" type="assetTags" />

Specifying the `assetCategories` and `assetTags` types for these `<aui:input />`
tags tells Liferay that these input tags represent asset categories and asset
tags. Liferay shows the appropriate selectors for tags and categories and
displays the tags and categories that have already been added to the guestbook.

Inside of the second `<liferay-ui:panel>` tag is an `<aui:fieldset>` tag
containing a `<liferay-ui:asset-links>` tag. You have to specify values for the
`className` and `classPK` attributes in order for the correct asset links (the
related assets corresponding to the selected guestbook) to be displayed.

Test your updated `edit_guestbook.jsp` page by navigating to your Guestbook
Admin portlet in the Control Panel and clicking on *Add Guestbook*. You'll see a
field for adding tags and a selector for selecting related assets.

![Figure 2: Once you've updated your Guestbook Admin portlet's `edit_guestbook.jsp` page, you'll see forms for adding tags and selecting related assets.](../../../images/guestbook-tags-related-assets.png)

Where is the field for selecting categories? It's been enabled but it won't
appear until you create a vocabulary and add at least one category to it. Create
a sample vocabulary and add a few sample categories to this vocabulary. Then go
back to the Guestbook Admin portlet, click on *Add Guestbook* or *Actions*
&rarr; *Edit* next to a guestbook and confirm that categories are selectable.

To further test your entities' integration with Liferay's asset framework, add
the Tags Navigation, Tag Cloud, and Categories Navigation portlets to the page
with the Asset Publisher portlet. All of tags that you've created appear in the
Tags Navigation and Tag Could portlet. All of the categories that you've created
appear in the Categories Navigation portlet. Click on a tag name or category
name in any of the portlets that you added. When you do so, check that the Asset
Publisher dynamically displays only assets with the selected tag or category.
This mechanism works by means of public render parameters. The Tags Navigation,
Tag Cloud, and Categories Navigation portlets publish a `tag` or a `categoryId`
render parameter and the Asset Publisher reads the parameter and uses it to
dynamically determine which assets to display.

You should also test the Related Assets feature. To do so, create a guestbook
and, say, a web content article. Then select one asset as a related asset of the
other and click *Save*. Or create two guestbooks and add one as a related asset
of the other.

+$$$

**Warning:** When you click on *Related Assets* from the Guestbook Admin
portlet's Add/Update Guestbook form, the fully qualified Java class name of your
entity appears in the list instead of just *Guestbook*. This is easy to fix by
adding a language key to your guestbook-portlet project. See the [Overriding Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys)
tutorial for details about modifying and creating language keys.

$$$

Asset links represent a reciprocal relationship. If one asset is a related asset
of a second, the second is a related asset of the first. Check this for the
assets that you linked together.