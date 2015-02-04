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

## Finding Entities

First, modify the Guestbook App's `docroot/WEB-INF/service.xml` file, adding
the following XML below the current `<finder>` in the `Guestbook` entity:

    <finder name="GuestbookName" return-type="Collection">
        <finder-column name="name"></finder-column>
    </finder>

Next, in the `Entry` entity, add the following finder tag:

    <finder name="G_N" return-type="Collection">
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="name"></finder-column>
    </finder>

Run Service Builder to generate the new finders.

Why are you creating a finder on the `name` and `guestbookId` database fields
for the `Entry`? It makes sense if you think about the portlet's design. When
finding an Entry in the database, using the Primary Key guarantees you'll find
the entity you are looking for. However, the name field in your portlet is the
name of the person creating the entry. Multiple people with the same name can
easily create entries, right? What if Penelope creates an entry, and then a day
later, another Penelope also creates an entry. If you rely solely on `name` to
find the entries, which one should you retrieve from the database in the case
that two records have matching fields? You can easily see the problem, and the
convention is to retrieve the first entity when two entities with matching
fields values are retrieved. However, you can reduce the likelihood of finding
multiple entries if our finder also uses `guestbookId`. In this case, as long as
both Penelope's create entries in different guestbooks, you'll be able to
retrieve both without issue. If they do happen to create entries in the same
guestbook, then the first one will be retrieved when the finder is called in our
portlet's service layer. 

<!-- I think we should change how it works, because this way, we're losing data.
Let's instead modify the finder so that it returns everything it finds, and then
modify the view_entry.jsp so that it loops through and displays all the entries
that it finds. -Rich --> 

## Modifying the Service Layer

Next, you need to create methods that use the Guestbook Portlet's new ability to
retrieve `Guestbook` and `Entry` entities from the database by the fields you
specified. 

Open `GuestbookLocalServiceImpl` and insert the following method at the top of the
class: 

    public Guestbook getGuestbookByName(String name, OrderByComparator orderByComparator) 
			throws SystemException, NoSuchGuestbookException {

		return guestbookPersistence.findByGuestbookName_First(name, orderByComparator);

	}

Now open `EntryLocalServiceImpl` and insert the following method at the top of
the class:

    public Entry getEntryByGuestbookIdAndName(long guestbookId, String name, OrderByComparator orderByComparator) 
			throws SystemException, NoSuchEntryException {

		return entryPersistence.findByG_N_First(guestbookId, name, orderByComparator);

	}

Organize the imports for both files:

    import com.liferay.docs.guestbook.NoSuch[Entry/Guestbook]Exception;
    import com.liferay.portal.kernel.util.OrderByComparator;

Run Service Builder.

Note the use of the finder methods appended by `_first` (e.g.,
`findByGuestbookName_first`), and the need for the `OrderByComparator` as an
argument. This is simply to protect against cases like the one described above,
in which two Penelopes create guestbook entries in the same guestbook, with the
same `name` value. This code ensures that only the first one is retrieved.

<!-- Please fix as we discussed. -Rich -->

The Portlet's Controller class needs to add a `Guestbook` object to the
attribute for our modifications to work. 

## Updating the Guestbook Portlet's Controller Class

Open `GuestbookPortlet` and add the following method to the bottom of the class:

    public void switchTabs(ActionRequest request, ActionResponse response)
			throws NoSuchGuestbookException, SystemException {

		OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil
				.getOrderByComparatorFactory();
		OrderByComparator orderByComparator = orderByComparatorFactory.create(
				"guestbook", "name", true);

		String guestbookName = ParamUtil.getString(request, "guestbookName");

		try {
			
			Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByName(
					guestbookName, orderByComparator);
			
			request.setAttribute("guestbook", guestbook);
			
		} catch (Exception e) {
			
			SessionErrors.add(request, "guestbook-cannot-be-displayed");
			
		}
		response.setRenderParameter("mvcPath", "/html/guestbook/view.jsp");
	}
	
Organize Imports.

    import com.liferay.portal.kernel.util.OrderByComparator;
	import com.liferay.portal.kernel.util.OrderByComparatorFactory;
	import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

The `switchTabs` method is called by an Action URL that replaces one of the
Render URLs in the view layer of the portlet; its purpose is to take the
`guestbookName` from the request and get a `Guestbook` using your new method
from the `-LocalServiceUtil` class. Then it sets the `Guestbook` as a request
attribute. If you recall, guestbooks are organized in the UI with tabs.  The
`switchTabs` method is called when one of the tabs is clicked, making the
appropriate Guestbook available in the request.

First, you're creating the necessary `OrderByComparator`, and then you're
getting the `guestbookName` from the request. The `actionURL` to trigger this
method doesn't exist yet, but you'll get to that soon enough. The `try` block
uses the `guestbookName` from the request to get the `Guestbook` from the
database and sets it as an attribute in the request.

Last, you call `setRenderParameter` and add the path to `view.jsp` as the
`mvcPath` parameter of the request.

In the `catch` block, you're adding an error message to the `SessionErrors`
object. Before moving on, open the `Language.properties` file in
`docroot/WEB-INF/src/content` and provide a message for the key
`guestbook-cannot-be-displayed`. Copy the following into the file, then build
languages:

    guestbook-cannot-be-displayed=The selected guestbook cannot be displayed.

Since you won't be passing the `guestbookId` into most URLs any longer, there
are some changes that need to be made to the existing methods in the
controller. Find the `addEntry` method and replace the `long guestbookId...`
line with a `guestbookName` declaration:

    String guestbookName = ParamUtil.getString(request, "guestbookName");

Now you need to get the `Guestbook` by its `guestbookName`, so add these lines
before the `if` statement:


    OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
    OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
		
    Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByName(guestbookName, orderByComparator);

In the calls to `updateEntry` and `addEntry`, change the `guestbookId` parameter to
`guestbook.getGuestbookId()`.

Since the `guestbookId` is no longer in the request, you now use the
`guestbookName`. Any fields from the object (like `guestbookId`) must now be
retrieved from the object to use in the *update* and *add* methods that require
them.

Also change the `setRenderParameter` calls in both the `try` blocks to

    response.setRenderParameter("guestbookName",
                guestbookName);

Leave the `deleteEntry` method alone. Limit your work to the URLs that
most users use, including viewing guestbooks, viewing full entries, adding
guestbooks, and adding guestbook entries.

Find the `addGuestbook` method next. In the creation of the `name`
variable, change the second parameter in the `ParamUtil` call to
`guestbookName`: 

    String name = ParamUtil.getString(request, "guestbookName");

Now find the `render` method. At the top of the first `try` block, initialize a
Guestbook object, but set it as null:

    Guestbook guestbook = null;

Delete the `guestbookId` variable declaration and replace it with the
following call, which gets the `guestbookName` from the request:

    String guestbookName = ParamUtil.getString(renderRequest, "guestbookName");

Now you need to change the logic used in the `if` and `else` blocks. This
logic decides which `Guestbook` needs to be set as an attribute of the
`renderRequest`.

Since `guestbook` is already declared as a `Guestbook` object, remove the
type declaration `Guestbook` from the declaration in the first `if` statement.
If this is the first time the portlet is added to a page (i.e., the
list of guestbooks is empty), a new `Guestbook` named *Main* will be created.

Here are the other scenarios the `render` method should handle:
- If the render request contains a `Guestbook` attribute (e.g., if the
  `switchTabs` action is triggered), get it.
- If the request contains no `Guestbook` object or `guestbookName`, just get the
  first `Guestbook` in the list. 
- If the request contains a `guestbookName` parameter, use it to get the
  `Guestbook` by its name. This is the case when the user clicks the
  `viewEntryURL` or the `addEntryURL`, defined in the `view.jsp` file.

Here's what the logic looks like:

    if (guestbooks.size() == 0) {
				guestbook = GuestbookLocalServiceUtil.addGuestbook(
						serviceContext.getUserId(), "Main", serviceContext);
			}

			else {
				
				if (!(renderRequest.getAttribute("guestbook") == null)) {
					guestbook = (Guestbook) renderRequest.getAttribute("guestbook");
				}
				
				else if (renderRequest.getAttribute("guestbook") == null && guestbookName.length() == 0) {
					guestbook = guestbooks.get(0);
				}
				
				else if (guestbookName.length() > 0) {
					OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
					OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
					
					guestbook = GuestbookLocalServiceUtil.getGuestbookByName(guestbookName, orderByComparator);
				}	
			}
			renderRequest.setAttribute("guestbook", guestbook);

Next, organize the imports.

    import com.liferay.docs.guestbook.NoSuchGuestbookException;
    import com.liferay.portal.kernel.util.OrderByComparator;
    import com.liferay.portal.kernel.util.OrderByComparatorFactory;
    import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;

The Controller is updated. Now you should update the View layer of the Guestbook
app, changing URL parameters and any scriptlets that rely on Primary Keys from
the request.

## Updating the View Layer

Many of the portlet URLs and scriptlets defined in the JSPs you've written
currently include the `guestbookId` or `entryId` as a parameter. You need to
change these instead to provide and use a `guestbookName` (the `name` column for
`Guestbook`s) or `name` (for `Entry` entities).  Additionally, use your new
service layer *getter* methods in the place of the current ones, if they need
the Primary Key of any entities. You'll also be making use of your new action
method, `switchTabs`.

Open `docroot/html/guestbook/view.jsp`. At the top of the file, immediately
after the `<%@include...` statement, is a scriptlet that gets the `guestbookId`
from the `renderRequest`. Find the `<aui:nav...` tag. Replace it with the
following tag:

    <liferay-ui:error key="guestbook-cannot-be-displayed" message="guestbook-cannot-be-displayed" />

    <%
        Guestbook guestbook = (Guestbook) renderRequest
                .getAttribute("guestbook");
    %>

The `liferay-ui:error` tag is for displaying the error message you added to the
`SessionErrors` object in the `switchTabs` method.

In the next scriptlet, change the `if` statement to get the `guestbookId` from
the `Guestbook` retrieved from the render request: 
    
    if (curGuestbook.getGuestbookId() == guestbook.getGuestbookId()) {
        cssClass = "active";
    }

There are two more instances in this file that use the `guestbookId`
parameter; replace them with the `guestbook.getGuestbookId()`
call:

- There's one in the permission check for the `addEntryURL`.
- There are two in the `<liferay-ui:search-container-results...` tag.

Find the `viewPageURL`, inside the `<aui:nav...` tag. It's a `renderURL`,
but you should replace it with an `actionURL` that triggers your new
`swtichTabs` action: 

    <portlet:actionURL name= "switchTabs" var="switchTabsURL">
        <portlet:param name="guestbookName" value="<%=curGuestbook.getName() %>"/>
    </portlet:actionURL>

Directly below this URL, the UI component that triggers the action is defined.
Change it to use `switchTabsURL`:

    <aui:nav-item cssClass="<%=cssClass%>" href="<%=switchTabsURL%>"
        label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

In the `addEntryURL`, change the `guestbookId` parameter to a `guestbookName`
parameter, using the name of the `Guestbook` attribute retrieved from the
request:

    <portlet:param name="guestbookName" value="<%=guestbook.getName()%>" />

In the `viewEntryURL`, replace the `entryId` parameter with
a `name` parameter, and add a `guestbookName` parameter as well:

    <portlet:param name="name" value="<%=entry.getName() %>" />
    <portlet:param name="guestbookName" value="<%=guestbook.getName() %>" />

Why do you need the `Entry` and `Guestbook` names in the URL? It makes sense to
show the name of the guestbook and the name of the entry in the Friendly URL
for viewing entries. Additionally, the `guestbookName` is needed by the
`render` method so the correct `Guestbook` is set as a request attribute.

That's it for the `view.jsp` file. 

Open `docroot/html/init.jsp`. This is where you organize the imports
necessary for our JSP files. Add the following imports to the file:

    <%@ page import="com.liferay.portal.kernel.util.OrderByComparator" %>
    <%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactory" %>
    <%@ page import="com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil" %>

The *getter* methods created earlier in the `-LocalServiceImpl` classes are 
used in the `view_entry.jsp`; an `OrderByComparator` is a necessary parameter
in those methods.

The JSP files for viewing entries, editing entries, and editing guestbooks need
some modifications to fully support our changes.

First, there's a `renderURL` and an `actionURL` at the top of the file. Move
them to follow the first scriptlet. 

Change the scriptlet at the top of the file by adding the following line above
the definition of the `entryId` variable:

    Guestbook guestbook = (Guestbook) renderRequest.getAttribute("guestbook");

In the same file, change the `viewURL` and the `addEntryURL` by adding the
following parameter to each one:

    <portlet:param name="guestbookName" value="<%= guestbook.getName() %>"/>

The only other modification you need here is in the `<aui:input...` tag for the
hidden `guestbookId` field. Instead of getting the `guestbookId` from the
request, get it from the `Guestbook` taken from the request:

    String.valueOf(guestbook.getGuestbookId())

Now open `view_entry.jsp`. All the work you have to do here is in the first
scriptlet. Your aim is to get the `Entry` the user selects, using its `name`
and the `guestbookId` of the `Guestbook` it's in. 

- Delete the lines defining the `entryId` and `entry` variables. Since you're 
  working with the entry `name`, you'll get that from the request instead of the
  `entryId`, and use it to get the correct `Entry` from the database. 
- To get the correct `guestbookId`, you'll need to get the `Guestbook` by its
  name. First get the `guestbookName` so you can get the `Guestbook` (and
  its `guestbookId`). Then you can get the `Entry` by both its own `name` and the
  `guestbookId` of its associated `Guestbook`. 

Add these lines to the top of the scriptlet:

	String name = ParamUtil.getString(renderRequest, "name");
	
	String guestbookName=ParamUtil.getString(renderRequest, "guestbookName");
	
	OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
	OrderByComparator orderByComparator = orderByComparatorFactory.create("Entry", "name", true);
	Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByName(guestbookName, orderByComparator);
	Entry entry = EntryLocalServiceUtil.getEntryByGuestbookIdAndName(guestbook.getGuestbookId(), name, orderByComparator);

Finally, open `edit_guestbook.jsp`. For clarity, you've been referring to the `name`
field of the `Guestbook` as `guestbookName`, so it is not confused with the `name`
field of the `Entry`. Find the `<aui:input name="name"` line and change `name`
to `guestbookName`. 

## Modifying the URL Routes

All the modifications you made to the JSPs were doing these things:

- Removing mention of `guestbookId` and `entryId`
- Adding `guestbookName` and `name` parameters, where applicable
- Swapping methods that get database entities by their Primary Key for methods
  that use a different database column

Now that you've made those changes, you're ready to modify the URL routes you
defined in the last section of this learning path.

Open `guestbook-friendly-url-routes.xml`.

The first route, for `add_guestbook`, does not need modification. 

In the second route, for `add_entry`, edit the `<pattern>` tag to replace
`{guestbookId}` with `{guestbookName}`.

The `view_entry` route's `<pattern>` needs to include both the `guestbookName` and
`name` (of the `Entry`):

    <pattern>/{guestbookName}/{name}/view_entry</pattern>

The `view` route can include the `guestbookName`, but you should also change the
`<implicit-parameter...` tags since we're using the `switchTabsURL` now. Here's
what the  route looks like when you're done with it:

    <route>
        <pattern>/{guestbookName}/view</pattern>
        <implicit-parameter name="p_p_lifecycle">1</implicit-parameter>
        <implicit-parameter name="javax.portlet.action">switchTabs</implicit-parameter>
    </route>

Now it's time to make sure everything is working as expected. 

## Testing the URL Routes

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

## Next Steps

[Creating Remote Services](/develop/learning-paths/-/knowledge_base/creating-remote-services)

