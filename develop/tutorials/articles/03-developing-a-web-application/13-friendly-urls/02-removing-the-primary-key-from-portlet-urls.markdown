# Removing the Primary Key from Portlet URLs 

After the last section on Making URLs Friendly, you have a good understanding of
Liferay's Friendly URL pattern, from declaring your intentions in
`liferay-portlet.xml` to writing some quick routes in the
`guestbook-friendly-url-routes.xml` file. Despite this excellent work, the URLs
are still not entirely friendly. Look at the URL you now get when you click on a
Guestbook entry:

    http://localhost:8080/web/guest/home/-/guestbook/10473/view_entry

It's quite a bit more concise and human-readable than before you started, but
the Primary Key of the `Guestbook`, its `guestbookId`, is included in the
URL. This doesn't mean anything to the user. It would be more clear to see the
`name` of the `Guestbook`, or the `name` of the `Entry` in the URLs. That's what
you'll be doing in this section on Friendly URLs.

Since you'll be making changes to several files, here's an overview of the
work:

-   Add `<finder>` tags to `docroot/WEB-INF/service.xml`, which Service Builder
    uses to generate methods that find `Guestbook` and `Entry` entities
    by their names. You'll expose these methods in the service implementation
    to retrieve entities from the database without using the primary key.
-   Update the Service layer by creating custom methods that get entities from
    the database using the new finders you created.
-   Add an action method to the controller class, `GuestbookPortlet.java`. This
    lets you set a `Guestbook` object as a render attribute, rather than
    just the `String`-based parameter for a `guestbookId` or a `guestbookName`.
-   Modify the Guestbook app's view layers, so that `guestbookId`is no longer
    necessary as a URL parameter. This involves changing URL parameters and
    some scriptlets in multiple JSPs.
-   Modify the routes in `guestbook-friendly-url-routes.xml` to make friendlier
    URLs for the Guestbook Portlet.

You'll start by creating the finders. 

## Finding Entities [](id=finding-entities)

First, modify the Guestbook app's `docroot/WEB-INF/service.xml` file, adding
the following XML below the current `<finder>` in the `Guestbook` entity:

    <finder name="G_N" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="name"></finder-column>
    </finder>

Next, in the `Entry` entity, add the following finder tag:

    <finder name="G_G_N" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="name"></finder-column>
    </finder>

Run Service Builder to generate the new finders.

Why are you creating a finder on the `groupId`, `name` and `guestbookId`
database fields for the `Entry`? It makes sense if you think about the
portlet's design. When finding an Entry in the database, using the Primary Key
guarantees you'll find the entity you are looking for. However, the name field
in your portlet is the name of the person creating the entry. Multiple people
with the same name can easily create entries, right? What if Penelope creates
an entry, and then a day later, another Penelope also creates an entry? If you
rely solely on `name` to find the entries, which one should you retrieve from
the database in the case that two records have matching fields? You can easily
see the problem. You haven't solved it completely by creating this finder.
There could still be multiple Penelopes creating entries in the same guestbook
in the same site. You'll deal with this in the `view_entry.jsp` file by using
this finder to retrieve a list of entries in a particular site, in a particular
guestbook, by a particular name, and displaying them all.

## Modifying the Service Layer [](id=modifying-the-service-layer)

Next, you need to create methods that use the Guestbook Portlet's new ability to
retrieve `Guestbook` and `Entry` entities from the database by the fields you
specified. 

Open `GuestbookLocalServiceImpl` and insert the following method at the top of the
class: 

    public Guestbook getGuestbookByG_N(long groupId, String name,
			OrderByComparator orderByComparator) throws SystemException,
			NoSuchGuestbookException {

		return guestbookPersistence.findByG_N_First(groupId, name, orderByComparator);
	}

Now open `EntryLocalServiceImpl` and insert the following method at the top of
the class:

    public List<Entry> getEntriesByG_G_N(long groupId, long guestbookId,
			String name) throws SystemException {

		return entryPersistence.findByG_G_N(groupId, guestbookId, name);
	}

Organize the imports for both files:

    import com.liferay.docs.guestbook.NoSuch[Entry/Guestbook]Exception;
    import com.liferay.portal.kernel.util.OrderByComparator;

Run Service Builder.

Note the use of the `Guestbook` finder method appended by `_first`, and the
need for the `OrderByComparator` as an argument. This is simply to protect
against cases where two `Guestbook`s are created with the same name. This code
ensures that only the first one is retrieved.

The Portlet's Controller class needs to add a `Guestbook` object to the
attribute for the modifications to work. 

## Updating the Guestbook Portlet's Controller Class [](id=updating-the-guestbook-portlets-controller-class)

Since you won't be passing the `guestbookId` into most URLs any longer, there
are some changes that need to be made to the existing methods in the
controller. Replace the current `addEntry` method with this one:

    public void addEntry(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Entry.class.getName(), request);

		String userName = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		String message = ParamUtil.getString(request, "message");
		String guestbookName = ParamUtil.getString(request, "guestbookName");
		long entryId = ParamUtil.getLong(request, "entryId");
		
		OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
		OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
		
		Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(
				serviceContext.getScopeGroupId(), guestbookName,
				orderByComparator);

		if (entryId > 0) {
			try {
				EntryLocalServiceUtil.updateEntry(serviceContext.getUserId(),
						guestbook.getGuestbookId(), entryId, userName, email, message,
						serviceContext);

				SessionMessages.add(request, "entryAdded");

				response.setRenderParameter("guestbookName",
						guestbookName);
			} catch (Exception e) {
				SessionErrors.add(request, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(request, response);

				response.setRenderParameter("mvcPath",
						"/html/guestbook/edit_entry.jsp");
			}
		}
		else {
			try {
				EntryLocalServiceUtil.addEntry(serviceContext.getUserId(),
						guestbook.getGuestbookId(), userName, email, message, serviceContext);

				SessionMessages.add(request, "entryAdded");

				response.setRenderParameter("guestbookName",
						guestbookName);
			} catch (Exception e) {
				SessionErrors.add(request, e.getClass().getName());
				
				PortalUtil.copyRequestParameters(request, response);

				response.setRenderParameter("mvcPath",
						"/html/guestbook/edit_entry.jsp");
			}
		}
	}

Organize Imports.

    import com.liferay.portal.kernel.util.OrderByComparator;
	import com.liferay.portal.kernel.util.OrderByComparatorFactory;
	import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

There are several lines to take note of:

    String guestbookName = ParamUtil.getString(request, "guestbookName");

The above variable declaration gets the `guestbookName`, rather than the
`guestbookId`, from the request.

You need to get the `Guestbook` by its `groupId` and `name`, so these lines
are added before the `if` statement:

    OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
    OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
		
    Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(
				serviceContext.getScopeGroupId(), guestbookName,
				orderByComparator);

First, you're creating the necessary `OrderByComparator` Since the `guestbookId`
is no longer in the request, you now use the `guestbookName`. Any fields from
the object (like `guestbookId`) must now be retrieved from the object to use in
the *update* and *add* methods that require them.

The `setRenderParameter` calls in both the `try` blocks now set
`guestbookName`, rather than `guestbookId`.

Leave the `deleteEntry` method alone. Limit your work to the URLs that
most users use, including viewing guestbooks, viewing full entries, adding
guestbooks, and adding guestbook entries.

Find the `addGuestbook` method next. In the creation of the `name`
variable, change the second parameter in the `ParamUtil` call to
`guestbookName`: 

    String name = ParamUtil.getString(request, "guestbookName");

Replace the current `render` method with this one:

	@Override
	public void render(RenderRequest renderRequest,
			RenderResponse renderResponse) throws PortletException, IOException {

		try {

			Guestbook guestbook = null;

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Guestbook.class.getName(), renderRequest);

			String guestbookName = "";

			long groupId = serviceContext.getScopeGroupId();

			// First, get all the guestbooks to populate the tabs
			List<Guestbook> guestbooks = GuestbookLocalServiceUtil
					.getGuestbooks(groupId);

			if (guestbooks.size() == 0) {
				guestbook = GuestbookLocalServiceUtil.addGuestbook(
						serviceContext.getUserId(), "Main", serviceContext);

				// If we had to create the default guestbook, put it in the
				// request
				renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);
			}

			// Now we check to see if the user selected a guestbook
			guestbook = (Guestbook) renderRequest
					.getAttribute(WebKeys.GUESTBOOK);

			if (guestbook == null) {

				// The user still could have selected a guestbook
				guestbookName = ParamUtil.getString(renderRequest,
						"guestbookName");
				if (guestbookName.equalsIgnoreCase("")) {

					guestbook = guestbooks.get(0);

				} else {

					OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil
							.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory
							.create("guestbook", "name", true);

					guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(
							serviceContext.getScopeGroupId(), guestbookName,
							orderByComparator);
				}

			}

			renderRequest.setAttribute(WebKeys.GUESTBOOK, guestbook);

		} catch (Exception e) {

			throw new PortletException(e);
		}

		super.render(renderRequest, renderResponse);
	}
At the top of the first `try` block, a Guestbook object is intialized, but set
as null:

    Guestbook guestbook = null;

The `guestbookId` variable declaration is replaced with `guestbookName`, retrieved from the request:

    String guestbookName = ParamUtil.getString(renderRequest, "guestbookName");

The logic used in the `if` and `else` blocks is also modified. This
logic decides which `Guestbook` needs to be set as an attribute of the
`renderRequest`.

Since `guestbook` is already declared as a `Guestbook` object, you removed the
type declaration `Guestbook` from the declaration in the first `if` statement.
If this is the first time the portlet is added to a page (i.e., the
list of guestbooks is empty), a new `Guestbook` named *Main* will be created.

Here are the other scenarios the `render` method handles:
- If the render request contains a `Guestbook` attribute (e.g., if the
  user clicked a tab)
- If the request contains no `Guestbook` object or `guestbookName`, just get the
  first `Guestbook` in the list. 
- If the request contains a `guestbookName` parameter, use it to get the
  `Guestbook` by its name. This is the case when the user clicks the
  `viewEntryURL` or the `addEntryURL`, defined in the `view.jsp` file.

One other thing of note is a Liferay best practice. You've replaced all
instances of the request attribute key with the constant (`WebKeys.GUESTBOOK`)
that was defined in an earlier Learning Path. This makes it less error-prone to
put the current `Guestbook` object in the request as an attribute and take it
out again. 

One thing is left to do. You are now working with `Guestbook`s by their names
instead of by their IDs. What about the case where a Guestbook administrator
creates two guestbooks with the same name? You certainly don't want two tabs
with the same name in your nice, clean interface! 

Fortunately, there's an easy way to fix that. When adding a new guestbook, you
can check to see if there's an existing guestbook with the same name, and then
prevent the user from adding it. 

Open `GuestbookLocalServiceImpl.java`. In the `addGuestbook` method, just after
getting the `groupId`, add this simple test: 

    List <Guestbook> test = guestbookPersistence.findByG_N(groupId, name);
		
		if (test.size() > 0) {
			
			throw new PortalException("existing-guestbook");
			
		}

Save the file and run Service Builder. This code throws a `PortalException` if
the guestbook the user is trying to add already exists. Next, open
`Language.properties`. Add the following message to the bottom of the file: 

    existing-guestbook=There is an existing Guestbook with that name.

Finally, you want this error message to display when the user accidentally tries
to enter a guestbook with the same name as another guestbook. Open the
`edit_guestbook.jsp` and add this tag just beneath the `<portlet:actionURL>`: 

    <liferay-ui:error key="existing-guestbook" message="existing-guestbook" />

Your custom error message is now displayed to users who try to enter duplicate
guestbooks. 

The Controller is updated. Now you should update the View layer of the Guestbook
app, changing URL parameters and any scriptlets that rely on Primary Keys from
the request.

## Updating the View Layer [](id=updating-the-view-layer)

Many of the portlet URLs and scriptlets defined in the JSPs you've written
currently include the `guestbookId` or `entryId` as a parameter. You need to
change these instead to provide and use a `guestbookName` (the `name` column for
`Guestbook`s) or `name` (for `Entry` entities). Additionally, use your new
service layer *getter* methods in the place of the current ones, if they need
the Primary Key of any entities. 

Open `docroot/html/guestbook/view.jsp`. Replace the entire contents of the file with the following code:

    <%@include file="/html/init.jsp"%>

    <%
        Guestbook guestbook = (Guestbook) renderRequest
                .getAttribute("guestbook");
    %>

    <liferay-portlet:renderURL varImpl="searchURL">
        <portlet:param name="mvcPath" value="/html/guestbook/view_search.jsp" />
    </liferay-portlet:renderURL>

    <aui:form action="<%= searchURL %>" method="get" name="fm">
        <liferay-portlet:renderURLParams varImpl="searchURL" />

        <div class="search-form">
            <span class="aui-search-bar">
                <aui:input inlineField="<%= true %>" label="" name="keywords" size="30" title="search-entries" type="text" />
            
                <aui:button type="submit" value="search" />
            </span>
        </div>
    </aui:form>

    <aui:nav cssClass="nav-tabs">

        <%
             List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                        .getGuestbooks(scopeGroupId);
                for (int i = 0; i < guestbooks.size(); i++) {
                    Guestbook curGuestbook = (Guestbook) guestbooks.get(i);

                    String cssClass = StringPool.BLANK;

                    if (curGuestbook.getGuestbookId() == guestbook.getGuestbookId()) {
                        cssClass = "active";
                    }
                    
                    if (GuestbookPermission.contains(
                            permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
                        
        %>


        <portlet:renderURL var="viewPageURL">
			<portlet:param name="mvcPath" value="/html/guestbook/view.jsp" />
			<portlet:param name="guestbookName"
				value="<%=curGuestbook.getName()%>" />
		</portlet:renderURL>

            <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
                label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />
        <% 
                }
            } 
        %>

    </aui:nav>

    <aui:button-row cssClass="guestbook-buttons">

    <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>
        <portlet:renderURL var="addGuestbookURL">
            <portlet:param name="mvcPath"
                value="/html/guestbook/edit_guestbook.jsp" />
        </portlet:renderURL>

        <aui:button onClick="<%=addGuestbookURL.toString()%>"
            value="Add Guestbook" />
    </c:if>

    <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), "ADD_ENTRY") %>'>
        <portlet:renderURL var="addEntryURL">
            <portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
            <portlet:param name="guestbookName" value="<%=guestbook.getName()%>" />
        </portlet:renderURL>

        <aui:button onClick="<%=addEntryURL.toString()%>" value="Add Entry"></aui:button>
    </c:if>

    </aui:button-row>

    <liferay-ui:search-container>
        <liferay-ui:search-container-results
            results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                            guestbook.getGuestbookId(), searchContainer.getStart(),
                            searchContainer.getEnd())%>"
            total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                            guestbook.getGuestbookId())%>" />

        <liferay-ui:search-container-row
            className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">
            
            <portlet:renderURL var="viewEntry">
                <portlet:param name="mvcPath" value="/html/guestbook/view_entry.jsp" />
                <portlet:param name="name" value="<%=entry.getName() %>" />
                <portlet:param name="guestbookName" value="<%=guestbook.getName() %>" />
            </portlet:renderURL>

            <liferay-ui:search-container-column-text property="message" href="<%= viewEntry %>"/>

            <liferay-ui:search-container-column-text property="name" />
            
            <liferay-ui:search-container-column-jsp
                path="/html/guestbook/guestbook_actions.jsp"
                align="right" />

        </liferay-ui:search-container-row>

        <liferay-ui:search-iterator />
    </liferay-ui:search-container>

There have been some important changes to the `view.jsp`. At the top of the
file, immediately after the `<%@include...` statement, a scriptlet getting the
`guestbookId` from the `renderRequest` is replaced by a scriptlet getting the
`Guestbook` object, and a `liferay-ui:error` tag added.

In the next scriptlet, the `if` statement is modified to get the `guestbookId`
from the `Guestbook` retrieved from the render request: 
    
    if (curGuestbook.getGuestbookId() == guestbook.getGuestbookId()) {
        cssClass = "active";
    }

There are two more places in this file that used the `guestbookId`
parameter; they're replaced with the `guestbook.getGuestbookId()`
call:

- There's one in the permission check for the `addEntryURL`.
- There are two in the `<liferay-ui:search-container-results...` tag.

The `viewPageURL` inside the `<aui:nav...` has now been modified. Instead of
using the `guestbookId`, it now uses the `guestbookName`. 

In the `addEntryURL`, the `guestbookId` parameter is now a `guestbookName`
parameter, using the name of the `Guestbook` attribute retrieved from the
request:

    <portlet:param name="guestbookName" value="<%=guestbook.getName()%>" />

In the `viewEntryURL`, the `entryId` is replaced with
a `name` parameter, and a `guestbookName` parameter is added:

    <portlet:param name="name" value="<%=entry.getName() %>" />
    <portlet:param name="guestbookName" value="<%=guestbook.getName() %>" />

Why do you need the `Entry` and `Guestbook` names in the URL? It makes sense to
show the name of the guestbook and the name of the entry in the Friendly URL
for viewing entries. Additionally, the `guestbookName` is needed by the
`render` method so the correct `Guestbook` is set as a request attribute.

That's it for the `view.jsp` file. 

Open `docroot/html/init.jsp`. This is where you organize the imports
necessary for JSP files, according to Liferay's convention. Add the following
imports to the file:

    <%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
    <%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactory" %>
    <%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil" %>

The *getter* methods created earlier in the `-LocalServiceImpl` classes are 
used in the `view_entry.jsp`; an `OrderByComparator` is a necessary parameter
in those methods.

The JSP files for viewing entries, editing entries, and editing guestbooks need
some modifications to fully support the Friendly URL changes.

Open `edit_entry.jsp`. There's a `renderURL` and an `actionURL` at the top
of the file. Move them to follow the first scriptlet. 

Change the scriptlet at the top of the file by adding the following line above
the definition of the `entryId` variable:

    Guestbook guestbook = (Guestbook) renderRequest.getAttribute("guestbook");

In the same file, change the `viewURL` and the `addEntryURL` by adding the
following parameter to each one:

    <portlet:param name="guestbookName" value="<%= guestbook.getName() %>"/>

The only other modification you need here is in the `<aui:input...` tag for the
hidden `guestbookId` field. Instead of getting the `guestbookId` from the
request, get it from the `Guestbook` taken from the request. Replace the tag
with this snippet:

    <aui:input name='guestbookId' type='hidden' value='<%= String.valueOf(guestbook.getGuestbookId()) %>'/>

Now open `view_entry.jsp`. Replace its contents with this code:

    <%@include file = "/html/init.jsp" %>

    <portlet:renderURL var="viewURL">
        <portlet:param name="mvcPath" value="/html/guestbook/view.jsp"></portlet:param>
    </portlet:renderURL>

    <liferay-ui:header backURL="<%= viewURL %>" title="entry" />

    <%
        String name = ParamUtil.getString(renderRequest, "name");
        
        String guestbookName=ParamUtil.getString(renderRequest, "guestbookName");
        
        OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
        OrderByComparator orderByComparator = orderByComparatorFactory.create("Entry", "name", true);
        Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByG_N(scopeGroupId, guestbookName, orderByComparator);
        
        List<Entry> entries = EntryLocalServiceUtil.getEntriesByG_G_N(scopeGroupId, guestbook.getGuestbookId(), name);
        
        for (Entry entry : entries) {
            entry = entry.toEscapedModel();

            AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
                    Entry.class.getName(), entry.getEntryId());

            String currentURL = PortalUtil.getCurrentURL(request);

            PortalUtil.addPortletBreadcrumbEntry(request,
                    entry.getMessage(), currentURL);

            PortalUtil.setPageSubtitle(entry.getMessage(), request);
            PortalUtil.setPageDescription(entry.getMessage(), request);

            List<AssetTag> assetTags = AssetTagLocalServiceUtil.getTags(
                    Entry.class.getName(), entry.getEntryId());
            PortalUtil.setPageKeywords(
                    ListUtil.toString(assetTags, "name"), request);
    %>

    <dl>
        <dt>Guestbook</dt>
        <dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName() %></dd>
        <dt>Name</dt>
        <dd><%= entry.getName() %></dd>
        <dt>Message</dt>
        <dd><%= entry.getMessage() %></dd>
    </dl>

    <c:if test="<%= themeDisplay.isSignedIn() %>">
        <liferay-ui:panel-container extended="<%= false %>"
            id="entryCollaborationPanelContainer" persistState="<%= true %>">
            <liferay-ui:panel collapsible="<%= true %>" extended="<%= true %>"
                id="entryCollaborationPanel" persistState="<%= true %>"
                title='<%= LanguageUtil.get(pageContext, "collaboration") %>'>
                <liferay-ui:ratings className="<%= Entry.class.getName() %>"
                    classPK="<%= entry.getEntryId() %>" type="stars" />
                
                <br />

                <portlet:actionURL name="invokeTaglibDiscussion" var="discussionURL" />
        
                <liferay-ui:discussion className="<%= Entry.class.getName() %>"
                    classPK="<%= entry.getEntryId() %>"
                    formAction="<%= discussionURL %>" formName="fm2"
                    ratingsEnabled="<%= true %>" redirect="<%= currentURL %>"
                    subject="<%= entry.getMessage() %>"
                    userId="<%= entry.getUserId() %>" />

            </liferay-ui:panel>
        </liferay-ui:panel-container>
    </c:if>

    <liferay-ui:asset-links
        assetEntryId="<%= (assetEntry != null) ? assetEntry.getEntryId() : 0 %>"
        className="<%= Entry.class.getName() %>"
        classPK="<%= entry.getEntryId() %>" />

    <%
        }
    %>

Your aim is to display the `Entry` the user selects, using its `name` and the
`guestbookId` of the `Guestbook` it's in. Here's how the new `view_entry.jsp`
does this:

- Using the request parameters `name` and `guestbookName`, you get a list of
  entries from the database:

        List<Entry> entries = EntryLocalServiceUtil.getEntriesByG_G_N(scopeGroupId, guestbook.getGuestbookId(), name);
	
- The entries are looped through, and each one is displayed in the portlet:

        for (Entry entry : entries) {
            ...
        }
        <dl>
            <dt>Guestbook</dt>
            <dd><%= GuestbookLocalServiceUtil.getGuestbook(entry.getGuestbookId()).getName() %></dd>
            <dt>Name</dt>
            <dd><%= entry.getName() %></dd>
            <dt>Message</dt>
            <dd><%= entry.getMessage() %></dd>
        </dl>
        To get the correct `guestbookId`, you need to get the `Guestbook` by its
          name. The `guestbookName` is used to get the `Guestbook` (and its
        `guestbookId`). Then you can get the `Entry` by both its own `name` and the
        `guestbookId` of its associated `Guestbook`. 
            ...
        <%
            }
        %>

Finally, open `edit_guestbook.jsp`. For clarity, you've been referring to the `name`
field of the `Guestbook` as `guestbookName`, so it is not confused with the `name`
field of the `Entry`. Find the `<aui:input name="name"` line and change `name`
to `guestbookName`: 

    <aui:input name="guestbookName" />

The JSPs have changed quite a bit. In review: 

- Mention of `guestbookId` and `entryId` are removed as necessary and replaced
  with `guestbookName` and `name` parameters, where applicable.
- Method calls that get database entities by their Primary Key are replaced
  with methods that use different database fields.
- The `view_entry.jsp` now displays all the entries in a site's Guestbook that
  share the same `name` field value.

## Modifying the URL Routes [](id=modifying-the-url-routes)

Now that you've made the necessary changes to your portlet's code, you're ready
to modify the URL routes you defined in the previous section of this learning
path.

Open `guestbook-friendly-url-routes.xml`, and replace its contents: 

    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 6.2.0//EN"
    "http://www.liferay.com/dtd/liferay-friendly-url-routes_6_2_0.dtd">

    <routes>
       <route>
            <pattern>/add_guestbook</pattern>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="mvcPath">/html/guestbook/edit_guestbook.jsp</implicit-parameter>
        </route>
        <route>
            <pattern>/{guestbookName}/add_entry</pattern>
            <implicit-parameter name="mvcPath">/html/guestbook/edit_entry.jsp</implicit-parameter>
            
        </route>
        <route>
            <pattern>/{guestbookName}/{name}/view_entry</pattern>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="mvcPath">/html/guestbook/view_entry.jsp</implicit-parameter>
        </route>
        <route>
            <pattern>/{guestbookName}/view</pattern>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="mvcPath">/html/guestbook/view.jsp</implicit-parameter>
        </route>
    </routes>

The first route, for `add_guestbook`, remains the same. 

In the second route, for `add_entry`, the `<pattern>` tag replaces
`{guestbookId}` with `{guestbookName}`.

The `view_entry` route's `<pattern>` includes both the `guestbookName` and
`name` (of the `Entry`) now.

The `view` route now includes the `guestbookName`.

Now it's time to make sure everything is working as expected. 

## Testing the URL Routes [](id=testing-the-url-routes)

Log in to the portal with the Guestbook Portlet deployed. If you haven't
already added the portlet to a page, do so now. Because of the `render` method's
logic, a new `Guestbook` called *Main* is created automatically when you add the
portlet to the page. Click *Add Guestbook*, and look at the lovely URL!

    http://localhost:8080/web/guest/home/-/guestbook/add_guestbook

Okay, so that's nothing new; you had that in place in the previous step. Fill
out the form and submit it. Now click on the Guestbook you just created. This
triggers the `switchTabs` URL and action, and you should see a URL that looks
like this (if the *Name* field was *your-guestbook-name*):

    http://localhost:8080/web/guest/home/-/guestbook/your-guestbook-name/view?p_auth=Gh7eWbod

That looks better, doesn't it?

Now click *Add Entry*, and look at the URL:

    http://localhost:8080/web/guest/home/-/guestbook/your-guestbook-name/add_entry

Instead of the `guestbookId`, the `guestbookName` (*your-guestbook-name* in the
example URL) is shown in the URL. Fill out the form and click *Save*. After the
portlet renders, click on the new entry in the search container. Here's what the
URL looks like now, if you filled in the *Name* field with *your-name*:

    http://localhost:8080/web/guest/home/-/guestbook/your-guestbook-name/your-name/view_entry

You're a Friendly URL expert now. Not only can you create Friendly URLs from the
existing URL parameters, but you know how to remove the Primary Key from the URL
to create even friendlier URLs.

## Next Steps [](id=next-steps)

[Creating Remote Services](/develop/learning-paths/-/knowledge_base/6-2/creating-web-services-for-your-application)

