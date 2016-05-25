# Creating Managed Beans to Use Services [](id=creating-managed-beans-to-use-services)

Now that your persistence and service layers are created, it's time to create
your managed beans to store and retrieve guestbooks and guestbook entries for
your users. 

You may be wondering what a managed bean is and what it accomplishes in a JSF
portlet. 

Managed beans are Java beans that are managed by the JSF framework. There are
two types of managed beans you'll use in this learning path: *View Scoped* and
*Request Scoped*. Managed beans annotated with `@ViewScoped` store stateful data
and remain on the server as long as the user interacts with the current view.
[EL expressions](http://en.wikipedia.org/wiki/Unified_Expression_Language) are
used to bind declarative XHTML to bean properties. 

Managed beans annotated with `@RequestScoped` are usually responsible for
handling actions and listeners. JSF *manages* these beans by creating and
removing the bean object from the server. You'll learn about these annotations
and types later on. 

In this learning path, you'll create three managed beans. Two of the managed
beans will represent the *Guestbook* and *Entry* entities. These beans will
facilitate adding guestbooks and guestbook entries to your Guestbook portlet,
respectively. The third bean will represent an abstract bean, or a bean
specifying administrative type information that both Guestbook and Entry related
beans will use. 

Now that you have some background information on how JSF managed beans work and
what managed beans you'll create for the Guestbook portlet, it's time to begin
creating your guestbook's managed beans. 

## Creating the Abstract Bean [](id=creating-the-abstract-bean)

The first bean you'll create in your guestbook is the *Abstract* bean. As
mentioned earlier, the abstract bean will hold properties and methods required
for both the guestbook and entry managed beans.

Before you begin creating JSF beans, you'll need to have a package dedicated to
hold them. To do this, right-click your `docroot/WEB-INF/src` directory and
select *New* &rarr; *Package*. Give it the name
`com.liferay.docs.guestbook.bean` and click *Finish*.

Now you're ready to create  the `AbstractBacking` class. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Give it the name `AbstractBacking` and select the *package* and *abstract*
   modifier checkboxes. Then click *Finish*. 

3. Add the following properties to your class: 

        protected static final Logger logger = LoggerFactory.getLogger(AbstractBacking.class);

        protected static final String UNEXPECTED_ERROR_MSG_ID = "an-unexpected-error-occurred";
        protected static final String SUCCESS_INFO_MSG_ID = "your-request-processed-successfully";

    These general properties are used with your portlet's logging and
    success/error messaging.

4. Add the following methods to your class: 

        public void addGlobalSuccessInfoMessage() {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            liferayFacesContext.addGlobalSuccessInfoMessage();
        }

        public void addGlobalUnexpectedErrorMessage() {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            liferayFacesContext.addGlobalUnexpectedErrorMessage();
        }

    These two methods deal with the success and error messaging of your
    guestbook portlet. They both create an instance of
    [`LiferayFacesContext`](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
    and add the success/error messaging to it. You'll use the
    `LiferayFacesContext` throughout your Guestbook and Entry beans, and now
    it'll be equipped with success/error messaging. 

5. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

Now that you've successfully created your `AbstractBacking` bean, it's time to
move on to creating the guestbook and entry managed beans. 

## Creating the Guestbook and Entry Managed Beans [](id=creating-the-guestbook-and-entry-managed-beans)

Now that you've created the `AbstractBacking` bean, it's time to create the
managed beans specific to each of your entities. You'll start with creating the
guestbook bean class. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Name the class `GuestbookBacking` and, for the Superclass field, browse
   for the `AbstractBacking` class you just created. Then click *Finish*. 

Excellent! Your guestbook bean is now extending the abstract bean. Next, you'll
create the entry bean class following a similar process: 

1. Right-click on the `com.liferay.docs.guestbook.bean` package again and select
   *New* &rarr; *Class*. 

2. Assign `EntryBacking` to the class name and browse for the `AbstractBacking`
   class for this bean's Superclass field. Then click *Finish*. 

All the managed beans you'll use throughout this learning path are now created.
However, to this point, the guestbook and entry beans are empty. The first thing
you'll need to do is ensure these beans can work together to create guestbooks
and guestbook entries in your portlet. You'll do this next by learning about
bean scoping and dependency injection. 

### Setting Managed Bean Scoping and Dependency Injection [](id=setting-managed-bean-scoping-and-dependency-injection)

Recall that you briefly learned about *view scoped* and *request scoped* managed
beans at the beginning of this learning path. The guestbook bean's scope will be
of the view, meaning it will stay active on the server and have a known state as
long as the portlet's view is being used by the user. View scoped beans must be
characterized by adding the `@ViewScoped` tag at the beginning of the class,
which you'll do when creating the guestbook bean.  

Likewise, the entry bean will be request scoped, which is set by adding the
`@RequestScoped` tag. This means that the entry bean's instance is only created
when a request is made. The request to create an instance of the entry bean is
submitted from XHTML files, or *views*. You'll create the views later on in the
JSF learning paths. 

JSF creates the request scoped bean, uses it as requested, and then removes it
and makes it available for garbage collection.

Thus, the general process for your guestbook is to store the content created
from your request scoped beans on your view scoped beans before the request
scoped beans are removed from the server. Therefore, since the entry bean is
trashed once the request is finished, the guestbook bean will have to
encapsulate entities and properties generated during the entry bean request. 

Now that you have an understanding of how the managed beans will interact and
rely on each other, it's time to characterize the beans as managed and
view/reqeust scoped. 

1. Open the `GuestbookBacking` bean class. 

2. Directly above the public class declaration, insert the `@ManagedBean` and
   `@ViewScoped` tags. Your class declaration should look like the following: 

        ...

        @ManagedBean
        @ViewScoped
        public class GuestbookBacking extends AbstractBacking {

        ...

3. Open the `EntryBacking` bean class. 

4. Directly above the public class declaration, insert the `@ManagedBean` and
   `@RequestScoped` tags. Your class declaration should look like the following: 

        ...

        @ManagedBean
        @RequestScoped
        public class EntryBacking extends AbstractBacking {

        ...

5. Press *Ctrl-Shift-O* to add the necessary imports related to these tags. 

Your managed beans are now scoped! Since the properties and entities created
using the request scoped entry bean are temporary, they'll need to be stored on
the view scoped guestbook bean. 

To do this, you'll need to insert another tag into the `EntryBacking` bean.
Directly below the class declaration, add the following tag and property: 

    @ManagedProperty(name = "guestbookBacking", value = "#{guestbookBacking}")
    protected GuestbookBacking guestbookBacking;

The `@ManagedProperty` annotation is used to inject dependencies from one
managed bean into another. Thus, the `GuestbookBacking`'s dependencies are
injected into the `guestbookBacking` property, making the `GuestbookBacking`
bean's properties and methods available throughout the `EntryBacking` bean.  
    
Lastly, to make the dependency injection successful, the injected properties
must be set in a setter method. Copy in the following method: 

    public void setGuestbookBacking(GuestbookBacking guestbookBacking) {
        this.guestbookBacking = guestbookBacking;
    }
    
Excellent! You've successfully configured your managed beans' scoping and
dependency injection. You may be wondering how the properties and entities of
the request scoped entry bean are stored on the guestbook bean. This is done
using getter and setter methods to encapsulate data in the guestbook bean.
You'll add these in soon, but first, you'll add in the methods necessary to
perform actions in the Guestbook portlet. 

### Creating Action Methods for Managed Beans [](id=creating-action-methods-for-managed-beans)

Now that you've finished the set up portion of your managed beans, it's time to
begin adding substance in your bean class bodies. The first thing you'll do is
add action methods that are necessary for each bean. To start, you'll add action
methods to the guestbook bean. 

1. Add the following properties in the `GuestbookBacking` class body: 

        public static final String DEFAULT_GUESTBOOK_NAME = "Main";

        private Guestbook originalGuestbook;

        private Guestbook selectedGuestbook;
        private List<Guestbook> guestbooks;

        private Entry selectedEntry;
        private List<Entry> entries;

        private boolean editingGuestbook;
        private boolean editingEntry;

    These will be the properties used throughout the guestbook and entry beans,
    and referenced in the Guestbook portlet views. 

2. Add the following action methods to your guestbook bean, directly below the
   properties: 

        public void add() {
            setOriginalGuestbook(getSelectedGuestbook());

            Guestbook guestbook = GuestbookUtil.create(0L);
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            guestbook.setGroupId(liferayFacesContext.getScopeGroupId());
            setSelectedGuestbook(guestbook);
            editGuestbook();
        }

        public void cancel() {
            select(getOriginalGuestbook());
        }

        public void save() {
            Guestbook guestbook = getSelectedGuestbook();
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            guestbook.setCompanyId(liferayFacesContext.getCompanyId());
            guestbook.setUserId(liferayFacesContext.getUserId());

            try {

                if (guestbook.getGuestbookId() == 0) {
                    guestbook = GuestbookLocalServiceUtil.addGuestbook(guestbook);
                }
                else {
                    guestbook = GuestbookLocalServiceUtil.updateGuestbook(guestbook);
                }

                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            // Go back to master view
            select(guestbook);
        }

        public void select(Guestbook guestbook) {

            if (guestbook == null) {
                setSelectedGuestbook(null);
            }
            else {
                setSelectedGuestbook(guestbook);
            }

            // Force Guestbooks and Entries to reload
            setGuestbooks(null);
            setEntries(null);

            editingEntry = false;
            editingGuestbook = false;
        }

    These methods deal directly with the `Guestbook` entities, and are triggered
    from the views, which you'll create later. Notice the `add()` and `save()`
    methods use
    [LiferayFacesContext](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
    to set certain parameters. You can visit the
    [Using the LiferayFacesContext with Liferay Faces Portal](/develop/tutorials/-/knowledge_base/6-2/using-the-liferayfacescontext-with-liferay-faces-portal)
    tutorial for more information on how the `LiferayFacesContext` can be used.
    You'll also notice these methods have many error markings. This is because
    they're referencing setter methods used to encapsulate data. You'll create
    these methods later, as well. 

3. The next two methods you'll add are called during the Guestbook portlet's
   start-up, which creates the default *Main* guestbook. Add the following
   methods directly below the `cancel()` method: 

        public void createMainGuestbook() {

            try {

                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();

                com.liferay.docs.guestbook.model.Guestbook defaultGuestbook = (com.liferay.docs.guestbook.model.Guestbook)
                    GuestbookLocalServiceUtil.getFirstGuestbookByName(scopeGroupId, DEFAULT_GUESTBOOK_NAME);

                // Create the default guestbook if it does not exist in the database
                if (defaultGuestbook == null) {
                    logger.info("postConstruct: creating a default guestbook named " + DEFAULT_GUESTBOOK_NAME + " ...");

                    Guestbook guestbook = GuestbookUtil.create(0L);
                    guestbook.setName(DEFAULT_GUESTBOOK_NAME);
                    guestbook.setGroupId(scopeGroupId);
                    guestbook.setCompanyId(liferayFacesContext.getCompanyId());
                    guestbook.setUserId(liferayFacesContext.getUserId());
                    GuestbookLocalServiceUtil.addGuestbook(guestbook);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        @PostConstruct
        public void postConstruct() {
            createMainGuestbook();
        }

    The `@PostConstruct` annotation forces the `postConstruct()` method to be
    called directly after the bean's construction and dependency injection have
    completed. Once the `postConstruct()` method is called, the
    `createMainGuestbook()` method is called, which creates the default
    guestbook displayed when the Guestbook portlet is placed on a page. 

4. Open the `EntryBacking` bean and insert the following action methods into the
   class body, directly after the `guestbookBacking` property initialization: 

        public void add() {
            Entry entry = EntryUtil.create(0L);
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            entry.setGroupId(liferayFacesContext.getScopeGroupId());
            entry.setGuestbookId(guestbookBacking.getSelectedGuestbook().getGuestbookId());
            guestbookBacking.setSelectedEntry(entry);
            guestbookBacking.editEntry();
        }

        public void cancel() {
            guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
        }

        public void save() {

            Entry entry = guestbookBacking.getSelectedEntry();
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            entry.setCompanyId(liferayFacesContext.getCompanyId());
            entry.setUserId(liferayFacesContext.getUserId());

            try {

                if (entry.getEntryId() == 0) {
                    EntryLocalServiceUtil.addEntry(entry);
                }
                else {
                    EntryLocalServiceUtil.updateEntry(entry);
                }

                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
        }

    These action methods are similar to the guestbook's action methods, but they
    perform actions on the guestbook entries. 

6. Press *Ctrl-Shift-O* to add the necessary imports for both managed beans. 

Awesome! You've successfully added action methods to your managed beans!

To understand how an action method will function in your Guestbook portlet,
you'll examine the `add()` method you created previously in the entry bean: 

    public void add() {
        Entry entry = EntryUtil.create(0L);
        LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
        entry.setGroupId(liferayFacesContext.getScopeGroupId());
        entry.setGuestbookId(guestbookBacking.getSelectedGuestbook().getGuestbookId());
        guestbookBacking.setSelectedEntry(entry);
        guestbookBacking.editEntry();
    }

Suppose a user clicks the *Add Entry* button. A request from the entry view
(XHTML) creates an instance of the `EntryBacking` bean and calls its `add()`
method. The `add()` method creates a guestbook entry, and then encapsulates that
entry in the guestbook bean. Then, once reaching the end of the method, the
instance of the bean is removed from the server. Pretty cool, right? 

If you're still a bit unsure of how the process is working to trigger these
action methods, there's no need to worry; as you begin creating the Guestbook
portlet's views, it'll be easier to visualize what's going on. 

Next, you'll begin adding getter and setter methods in the guestbook bean to
enable the encapsulation of properties. 

### Creating Methods to Encapsulate Properties [](id=creating-methods-to-encapsulate-properties)

Now that you've created the action methods needed to create guestbooks and
entries, you'll need a way to save your data. This is essential because the
entry bean is request scoped, meaning that once its request is finished, the
bean is deleted from the server. Whatever data was requested must be saved, so
the portlet can reference it when another request is made. 

Since the guestbook bean is view scoped, and is kept on the server, you'll add
the getter/setter methods there. 

1. Open the `GuestbookBacking` bean and add the following getter/setter methods
   for the guestbook entry entity below the `select(Guestbook)` method: 

        public List<Entry> getEntries() {

            if (entries == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();
                Guestbook selectedGuestbook = getSelectedGuestbook();

                try {
                    entries = new ArrayList<Entry>();

                    if (selectedGuestbook == null) {
                        logger.info("getEntries: selectedGuestbook == null ... ");
                    }
                    else {
                        List<com.liferay.docs.guestbook.model.Entry> list = EntryLocalServiceUtil.getEntries(scopeGroupId,
                            selectedGuestbook.getGuestbookId());

                        for (com.liferay.docs.guestbook.model.Entry entry : list) {
                            entries.add(entry);
                        }
                    }

                }
                catch (SystemException e) {
                    logger.error(e);
                }
            }

            return entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

    You may remember from the previous section that these setter methods are
    called to encapsulate what the `EntryBacking` bean created before its state
    is removed from the server. To summarize, the setter methods save your
    guestbook's entries to the database, and are retrieved by calling the getter
    methods. 

2. Similarly to the previous step, add the getter/setter methods for the
   guestbook entity directly below the `setEntries(List<Entry>)` method: 

        public List<Guestbook> getGuestbooks() {

            if (guestbooks == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

                try {
                    guestbooks = new ArrayList<Guestbook>();

                    List<com.liferay.docs.guestbook.model.Guestbook> list = GuestbookLocalServiceUtil.getGuestbooks(
                        scopeGroupId);

                    for (com.liferay.docs.guestbook.model.Guestbook guestbook : list) {
                        guestbooks.add(guestbook);
                    }
                }
                catch (SystemException e) {
                    logger.error(e);
                }
            }

            logger.info("getGuestbooks: guestbooks.size() = " + guestbooks.size());

            return guestbooks;
        }

        public void setGuestbooks(List<Guestbook> guestbooks) {
            this.guestbooks = guestbooks;
        }

    These getter/setter methods encapsulate the properties for guestbook
    entities. These methods are used in the same way as the getter/setter
    methods for entries, but for guestbooks. 

3. Add the getter/setter methods for encapsulating an original or selected
   guestbook below the `setGuestbooks(List<Guestbook>)` method: 

        public Guestbook getOriginalGuestbook() {
            return originalGuestbook;
        }

        public void setOriginalGuestbook(Guestbook originalGuestbook) {
            this.originalGuestbook = originalGuestbook;
        }

        public Guestbook getSelectedGuestbook() {

            if (selectedGuestbook == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

                try {
                    com.liferay.docs.guestbook.model.Guestbook firstGuestbookByName =
                        (com.liferay.docs.guestbook.model.Guestbook) GuestbookLocalServiceUtil.getFirstGuestbookByName(
                            scopeGroupId, DEFAULT_GUESTBOOK_NAME);

                    if (firstGuestbookByName == null) {
                        logger.info("getSelectedGuestbook: No Guestbook named " + DEFAULT_GUESTBOOK_NAME);
                    }
                    else {
                        selectedGuestbook = firstGuestbookByName;
                    }
                }
                catch (SystemException e) {
                    logger.error(e);
                }
            }

            return selectedGuestbook;
        }

        public void setSelectedGuestbook(Guestbook selectedGuestbook) {
            this.selectedGuestbook = selectedGuestbook;
        }

    The `originalGuestbook` property is set in the `GuestbookBacking`'s `add()`
    method to the current guestbook. Likewise, the `originalGuestbook` property
    is requested in the `cancel()` method to revert back to the main view of the
    original guestbook you were viewing.

    The getter/setter methods for selected guestbooks are used to encapsulate
    the guestbook currently being viewed/edited. Notice the
    `getSelectedGuestbook()` method calls the `getFirstGuestbookByName(...)`
    method that you created in your `GuestbookLocalServiceImpl` class. If you
    have not created a guestbook (i.e., when the portlet is first placed on a
    page), this method is called to create the new *Main* guestbook,
    characterized by the `DEFAULT_GUESTBOOK_NAME` variable. 

4. Add the getter/setter methods for encapsulating a selected entry below the
   `setOriginalGuestbook(Guestbook)` method: 

        public Entry getSelectedEntry() {
            return selectedEntry;
        }

        public void setSelectedEntry(Entry selectedEntry) {
            this.selectedEntry = selectedEntry;
        }

    Similar to the getter/setter methods for the selected guestbook, these
    methods encapsulate the selected entry you're currently viewing/editing. 

You've successfully created methods to encapsulate all your Guestbook portlet's
properties on the view scoped bean. The last thing you'll need to add are
methods to facilitate portlet navigation with your view (XHTML) files. 

### Creating Methods to Facilitate Portlet Navigation [](id=creating-methods-to-facilitate-portlet-navigation)

So far, you've created methods that perform actions with your entities, like
adding a new guestbook or saving a guestbook entry. You've also added
getter/setter methods to encapsulate data on the view scoped guestbook bean.
It's great that your managed beans can perform these important tasks, but how is
portlet navigation facilitated? That is, when will the portlet know when to call
the action methods you created? 

The portlet *views* you've read about throughout this learning path will be the
main driving force for facilitating navigation, but you'll need to create
methods in your managed beans that instruct which views should be displayed in
certain scenarios. 

You'll create these methods that aid in your Guestbook portlet's navigation
next. 

1. In the `GuestbookBacking` bean, add the following two methods beneath the
`createMainGuestbook()` method: 

        public void editEntry() {
            editingEntry = true;
            editingGuestbook = false;
        }

        public void editGuestbook() {
            editingEntry = false;
            editingGuestbook = true;
        }

    These methods use the boolean properties you specified in the guestbook bean
    to distinguish which view to display. When the `add()` method is called for
    either entity, the `edit*()` method relating to the chosen entity is called
    from an XHTML file. The boolean properties set within the method navigate
    the portlet to the correct view to display. 
    
    You'll learn more about the navigation of the portlets in the next learning
    path about JSF views. You haven't created your views yet, but you'll have
    four of them: `view` (navigation), `entry`, `guestbook`, and `master`
    (default page). 

2. Add the following setter methods below the `select(Guestbook)` method in your
   `GuestbookBacking` bean: 

        public void setEditingEntry(boolean editingEntry) {
            this.editingEntry = editingEntry;
        }

        public void setEditingGuestbook(boolean editingGuestbook) {
            this.editingGuestbook = editingGuestbook;
        }
    
    
    These setters help manage your `editingEntry` and `editingGuestbook`
    variables, which aid in the navigation between views. 

3. Add the following boolean methods below the `setSelectedGuestbook(Guestbook)`
   method: 

        public boolean isEditingEntry() {
            return editingEntry;
        }

        public boolean isEditingGuestbook() {
            return editingGuestbook;
        }

    These methods return the boolean properties set by the setter methods in the
    previous step. 

4. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. Your
   managed beans should have no error markings. 

Fantastic! You've created all the methods necessary for establishing the bridge
of communication between your managed beans and portlet views. The properties
that are established in these methods will be called in your XHTML views. 

You've heard a lot about these XHTML views throughout this learning path. It's
finally time to start creating these views, which are the last steps before you
have a working Guestbook portlet. Continue on to the next learning path to learn
how to create JSF views that coordinate with your managed beans. 
