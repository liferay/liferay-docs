# Adding Entry Titles to the URL

After the last section on Friendly URLs, you have a good understanding of
Liferay's Friendly URL pattern, from declaring your intentions in
`liferay-portlet.xml` to writing some quick routes in the
`guestbook-friendly-url-routes.xml`. If you noticed, however, the URLs are
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
`guestbookName` from the request Object. The actionURL doesn't exist yet, but
you'll get to that soon enough,



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

## Adding the routes to guestbook-friendly-url-routes.xml




Your Friendly URL is now ready to emerge from the beastly URL above. But while you're here, clean up the URLs for the other links as well. Follow these steps:

    1. Create
    `com/liferay/docs/guestbook/portlet/guestbook-friendly-routes.xml`. It's
    standard practice to create this file in the same package as the Portlet class.

    2. Add this code to the file:

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
                <pattern>/{guestbookId}/add_entry</pattern>
                <implicit-parameter name="mvcPath">/html/guestbook/edit_entry.jsp</implicit-parameter>
            </route>
            <route>
                <pattern>/{entryId}/view_entry</pattern>
                <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
                <implicit-parameter name="mvcPath">/html/guestbook/view_entry.jsp</implicit-parameter>
            </route>
            <route>
                <pattern>/{guestbookId}/view</pattern>
                <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
                <implicit-parameter name="mvcPath">/html/guestbook/view.jsp</implicit-parameter>
            </route>
        </routes>

There are two tags from the XML to pay attention to. 

- The `pattern` tag is used to define placeholder values for the various fields
  that normally appear in the generated URL; in short, it's the disguise you're
putting on the beastly URL from the intro to make it more Friendly. When users
add a Guestbook, they shouldn't see every parameter generated by the portal in
the URL; you'drather they simply see `add_guestbook`. Similarly, when they add
a Guestbook Entry, you want them to see `add_entry`. But, we added another
interesting bit here: `{guestbookId}` Using this format, you can populate URLs
with data that we retrieve from the database. In this case, `guestbookId` is
available to use in our Friendly URL because it's already in the `addEntryURL`
in `docroot/html/view.jsp`:

    <portlet:param name="guestbookId"
			value="<%=String.valueOf(guestbookId)%>" />

-The `implicit-parameter` tag defines parameters that don't need to be
generated because they'll always be the same for this URL. All our URLs for
adding entities and viewing Guestbooks or Guestbook Entries are renderURLs, so
the portal doesn't need to generate the `p_p_lifecycle parameter`.  It can
automatically be assumed to be zero. If you remove this parameter from the the
route, the resulting Friendly URL won't look any different; you specified in
the pattern tag what should be displayed in place of the generated URL. But
it's nice to specify it here so the portal doesn't need to bother generating it
on the fly.

## Next Steps

[Creating Remote Services](/develop/learning-paths/-/knowledge_base/creating-remote-services)

