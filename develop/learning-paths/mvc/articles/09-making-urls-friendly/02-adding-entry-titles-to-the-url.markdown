# Adding Entry Titles to the URL

After the last section on Friendly URLs, you have a good understanding of
Liferay's Friendly URL pattern, from declaring your intentions in
`liferay-portlet.xml` to writing some quick routes in the
`guestbook-friendly-url-routes.xml`. Despite this excellent work, the URLs are
still not entirely friendly. Look at the URL we now get when we click on one of
our Guestbook Entries:

    http://localhost:8080/web/guest/home/-/guestbook/10473/view_entry

It's quite a bit more concise and human-readable than before we started, but
we've included the Primary Key of the `Guestbook`, its `guestbookId`, in the
URL. This doesn't mean anything to the user. It would be more clear to see the
`name` of the guestbook; likewise for viewing `Entry` entities. That's what
you'll be doing in this section on Friendly URLs.

Since you'll be making changes to several files, here's the overview of what
you'll need to do:

- Add an action method to the controller class, `GuestbookPortlet.java`. 
    This will allow you set an entire `Guestbook` object as a render attribute.
This is necessary because...
- Add finder tags to `docroot/WEB-INF/service.xml`, which Service Builder will
  use to generate methods that will find `Guestbook` and `Entry` entities by
their name (and `guesbtookId`, for `Entry`).
    You'll use these methods to add custom methods to the service
implementation, so that entities can be retrieved from the database without
using the primary key.
- Modify the Guestbook App's view layer, its JSPs, so that `guestbookId`is no
  longer necessary as a URL parameter.
    This involves changing the URL parameters and some scriptlets in multiple
JSPs.

## Finding Entities

First, modify the Guestbook App's `docroot/WEB-INF/service.xml` file, adding
the following XML below the current `<finder>` in the `Guestbook` entity:

    <finder name="GuestbookName" return-type="Collection">
        <finder-column name="name"></finder-column>
    </finder>

Now, in the `Entry` entity, add the following finder tag:

    <finder name="G_N" return-type="Collection">
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="name"></finder-column>
    </finder>

Run Service Builder to generate the new finders.

Why are we creating a finder on the `name` and `guestbookId` database fields? It
makes sense if you think about our portlet's design. When finding an Entry in
the database, if we use its Primary Key you are guaranteed to find the entity
you are looking for. However, the name filed in our portlet is the name of the
person creating the entry. Multiple people with the same name can easily create
entries, right? What if Penelope creates an entry, and then a day later, another
Penelope also creares an entry. If we rely solely on nbame to find the entries,
which one should we retrieve form the database in the case that two records have
matching fields? You can easily see the problem, and the convention is to
retrieve the first entity. However, we can reduce the likelihood of finding
multiple entries if our finder also uses `guestbookId`. So as long as both
Penelope's create entries in different guestbooks, we'll be able to retrieve
both without issue. If they do happen to create entries in the same guestbook,
then the first one will be tretrieved when the finder is called in our portlet. 

## Modifying the Service Layer

Next, you need to create methods that use the Guestbook Portlet's new ability to
retrieve Guestbook and Entry entities from the database by the fields we
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

Note the use of the finder methods appended by `_first` (e.g.,
`findByGuestbookName_first`), and the need for the OrderByComaprator as an
argument. This is simply to protect against the case we talked about with the
Penelope's both creating guestbook entries in the same guestbook. This code
ensures that we will retrieve only the first one.

[SHOULD I INTRODUCE ALL OF THE METHODS THAT WERE CREATED AS A RESULT OF ADDING
THESE FINDERS?]

You're ready to make the necessary modifications now. 

## Updating the Guestbook Portlet's Controller Class

Let's get to business by modifying the controller class. Open `GuestbookPortlet`
and add the following method to the bottom of the class:

    public void switchTabs (ActionRequest request, ActionResponse response) {
		
		OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
		OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
		
		String guestbookName = ParamUtil.getString(request, "guestbookName");
		
		try {
			Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByName(guestbookName, orderByComparator);
			request.setAttribute("guestbook", guestbook);
		} catch (NoSuchGuestbookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setRenderParameter("mvcPath","/html/guestbook/view.jsp");
		
	}
	
The `switchTabs` method will be called by an Action URL that replaces one of the
renderURLs in the view layer of our portlet; its purpose is to take the
`guestbookName` from the request and get a `Guestbook` using our new method in
the -LocalServiceUtil. Then it sets the `Guestbook` as a request attribute. If
you recall, we've organized our guestbooks in the UI with tabs.  The
`swtichTabs` method will be called when one of the tabs is clicked, making the
appropriate Guestbook available in the request.

First, you're creating the necessary OrderByComparator, then you're getting the
`guestbookName` from the request. The actionURL doesn't exist yet, but you'll
get to that soon enough. The `try` block uses the `guestbookName` from the
request to get the Guestbook from the database, and sets it as an attribute in
the request.

Last, you simply call `setRenderParameter`, adding the path to `view.jsp` as the
`mvcPath` parameter of the request.

Since you won't be passing the `guestbookId` into the URL any longer, there are
some changes that need to be made to the existing methods. Find the addEntry
method and replace the `long guestbookId...` line with a `guestbookName`
declaration:

    String guestbookName = ParamUtil.getString(request, "guestbookName");

Now, you need to get the `Guestbook` by its `guestbookName`, so add these lines
before the `if` statement:


    OrderByComparatorFactory orderByComparatorFactory = OrderByComparatorFactoryUtil.getOrderByComparatorFactory();
    OrderByComparator orderByComparator = orderByComparatorFactory.create("guestbook", "name", true);
		
    Guestbook guestbook = GuestbookLocalServiceUtil.getGuestbookByName(guestbookName, orderByComparator);

In the calls to `updateEntry` and `addEntry`, change the `guestbookId` parameter to
`guestbook.getGuestbookId()`.

Also change the `setRenderParameter` calls in both the `try` blocks to

    response.setRenderParameter("guestbookName",
                guestbookName);

Leave the `deleteEntry` method alone. You can make similar changes to the URLs
for deleting entries if you like, but for now, limit your work to the URLs that
most users will interact with, including viewing guestbooks, viewing full
entries, adding guestbooks, and adding guestbook entries.

Find the `addGuestbook` method next. In the creation of the `name`
variable, change the second parameter in the call to `getString` to
`guestbookName`. 

Now find the `render` method. At the top of the first `try` block, initialize a
Guestbook object, but set it as null:

    Guestbook guestbook = null;

Delete the `guestbookId` variably declaration, and replace it with

    String guestbookName = ParamUtil.getString(renderRequest, "guestbookName");

Now you need to change the logic used in the `if` and `else` blocks.  All this
logic does is decide which `Guestbook` needs to be set as an attribute of the
`renderRequest`.

First, since `guestbook` is already declared as a `Guestbook` object, remove the
`Guestbook` from the declaration in the first `if` statement. If no this is the
first time the portlet is added to a page, a new `Guestbook` named *Main* will be
created.

Here are the other scenarios the `render` method should handle:
- If the render request contains a `Guestbook` attribute (e.g., if the
  `swtichTabs` action is triggered), get it.
- If the request contains no `Guestbook` object or `guestbookName`, just get the
  first `Guestbook` in the list. 
- If the request contains a `guestbookName` parameter, use it to get the
  `Guestbook` by its name. This will be the case when the user clicks the
viewwEntryURL and the adEntryURL.

Here's what that looks like:

    
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

## Updating the View LAyer

Many of the portlet URLs defined in the JSPs you've written currently include
the `guestbookId` as a parameter, and some of the scriptlets included in the
view layer also use the parameter. You need to change these instead to provide
and use a `guestbookName` parameter. You'll also be making use of your new
action method, `switchTabs`.

Open `view.jsp`. At the top of the file, immediately after the `<%@include...`
statement, is a scriptlet that get the `guestbookId` from the `renderRequest`
and find the `<aui:nav...` tag. Replace it with the following:

    <%
        Guestbook guestbook = (Guestbook) renderRequest
                .getAttribute("guestbook");
    %>



The scriptlet `renderURL` called `viewPageURL`.
Replace this URL with an actionURL that triggers our new `swtichTabs` action: 

    <portlet:actionURL name= "switchTabs" var="switchTabsURL">
        <portlet:param name="guestbookName" value="<%=curGuestbook.getName() %>"/>
    </portlet:actionURL>

Directly below this URL, the UI component that triggers the action is defined.
Change it to use `switchTabsURL`, the new actionURL you just defined:

    <aui:nav-item cssClass="<%=cssClass%>" href="<%=switchTabsURL%>"
        label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />


## Something about using a Finder to 

## Something about using the finder to get Entries by the GuestbookName

## Adding the parameter to the URL in view.jsp

Next you need to modify your JSPs so that the URLs no longer pass the
`guestbookId` as a URL parameter. Start by modifying your `view.jsp`. Following
the `<%@include...` statement at the beginning of the file, you can see the
scriptlet that retrieves the value of the `guestbookId` attribute from the
request. Instead, change the scriptlet to retrieve a `Guestbook` object from the
request:

    <%
        Guestbook guestbook = (Guestbook) renderRequest
			.getAttribute("guestbook");
    %>

There are other instances in the `view.jsp` that rely on the `guestbookId` from
the request. For each one, replace `guestbookId` with
`guestbook.getGuestbookId()`. Here's what each instance will look like when
you're finished:

    - if (curGuestbook.getGuestbookId() == guestbook.getGuestbookId()) {
    - <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), "ADD_ENTRY") %>'>
    - results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
						guestbook.getGuestbookId(), searchContainer.getStart(),
						searchContainer.getEnd())%>"
		total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
						guestbook.getGuestbookId())%>" />

We still have an important step to take care of. The URLs still pass the
`guestbookId` into the URL. First, change the `switchTabs` URL parameter to pass
in the `Guestbook` name:

    <portlet:actionURL name= "switchTabs" var="switchTabsURL">
			<portlet:param name="guestbookName" value="<%=curGuestbook.getName() %>"/>
    </portlet:actionURL>

    *NOTE*: Need to talk about replacing the viewPageURL with switchTabsURL
Action URL, and adding the action to the controller.

Similarly, change the `addEntryURL to contain a `guestbookName` parameter rather
than a `guestbookId`:

    <portlet:param name="guestbookName" value="<%=guestbook.getName()%>" />

In the `viewEntry` render URL, add a `guestbookName` parameter to the URL:

    <portlet:renderURL var="viewEntry">
        <portlet:param name="mvcPath" value="/html/guestbook/view_entry.jsp" />
        <portlet:param name="name" value="<%=entry.getName() %>" />
        <portlet:param name="guestbookName" value="<%=guestbook.getName() %>" />
    </portlet:renderURL>

Why did you just add the `guestbookName` parameter to a URL that didn't have the
`guestbookId` parameter to begin with? Good question. When we remove the
`entryId` from our viewEntry (created in `view.jsp`), and editEntry (in
`guestbook_actions.jsp`) URLs, we'll need to create a finder for `Entry`
entities that retrieves records from the database by `name` and `guestbookId`.
We'll use the `guestbookName` to get the `Guestbook` (using the finder you
created for guestbooks), and then we can get its `guestbookId`.

## Next Steps

[Creating Remote Services](/develop/learning-paths/-/knowledge_base/creating-remote-services)

