# Creating Managed Beans to Use Services

Now that your persistence and service layers are created, it's time to create
your managed beans to store and retrieve guestbooks and guestbook entries for
your users. 

You may be wondering what a managed bean is and what it accomplishes in a JSF
portlet. 

Managed beans are Java beans that are managed by the JSF framework. There are
two types of managed beans you'll use in this learning path: *View Scoped* and
*Request Scoped*. Managed beans annotated with `@ViewScoped` store stateful data
and remain on the server as long as the user interacts with the current view. EL
expressions are used to bind declarative XHTML to bean properties.

Managed beans annotated with `@RequestScoped` are usually responsible for
handling actions and listeners. JSF *manages* these beans by creating and
removing the bean object from the server. You'll learn about these annotations
and types later on. 

In this learning path, you'll create three managed beans. Two of the managed
beans will represent the *Guestbook* and *Entry* entities. These beans will
facilitate adding guestbooks and guestbook entries to your Guestbook portlet,
respectively. The third managed bean will represent an abstract bean, or a bean
specifying administrative type information that both Guestbook and Entry related
beans will use. 

Now that you have some background information on how JSF managed beans work and
what managed beans you'll create for the Guestbook portlet, it's time to begin
creating your guestbook's managed beans. 

## Creating the Abstract Managed Bean

The first managed bean you'll create in your guestbook is the *Abstract* bean.
As mentioned earlier, the abstract bean will hold properties and methods
required for both the guestbook and entry beans.

Before you begin creating managed beans, you'll need to have a package dedicated
to hold them. To do this, right-click your `docroot/WEB-INF/src` directory and
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

    You declared some general properties that help with logging and
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

## Creating the Guestbook and Entry Managed Beans

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

2. Assign `EntryBacking` as the class name and browse for the `AbstractBacking`
   class for this bean's Superclass field. Then click *Finish*. 

All the managed beans you'll use throughout this learning path are now created.
However, to this point, the guestbook and entry beans are empty. The first thing
you'll need to do is ensure these beans can work together to create guestbooks
and guestbook entries in your portlet. You'll do this next by learning about
bean scoping and dependency injection. 

### Setting Managed Bean Scoping and Dependency Injection

Recall that you briefly learned about *view scoped* and *request scoped* managed
beans in the previous section. The guestbook bean's scope will be of the view,
meaning it will stay active on the server and have a known state as long as the
portlet's view is being used by the user. View scoped beans must be
characterized by adding the `@ViewScoped` tag at the beginning of the class,
which you'll do when creating the guestbook bean.  

Likewise, the entry bean will be request scoped, which is set by adding the
`@RequestScoped` tag. This means that the entry bean instance is only created
when a request is being made. The request to create an instance of the entry bean is submitted from XHTML
files, or *views*. You'll create the views later on in the learning path.

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

### Creating Action Methods for Managed Beans

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

            // force Guestbooks and Entries to reload
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
    they're referencing setter methods use to encapsulate data. You'll create
    these methods later, as well. 

3. The next two methods you'll add are called during the Guestbook portlet's
   start-up, which creates the default *Main* guestbook. Add the following
   method directly below the `cancel()` method: 

        public void createMainGuestbook() {

            try {

                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();

                Guestbook defaultGuestbook = (Guestbook)
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
    perform action on the guestbook entries. 

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

### Creating Methods to Encapsulate Properties

Now that you've created your action methods needed to create guestbooks and
entries, you'll need a way to save your data. This is essential because the
entry bean is request scoped, meaning that once its request is finished, the
bean is deleted from the server. Whatever data was requested must be saved, so
the portlet can reference it when another request is made. 

Since the guestbook bean is view scoped, and is kept on the server, you'll add
the getter/setter methods there. 

1. Add the following getter/setter methods below the `select(Guestbook)` method:

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

        public List<Guestbook> getGuestbooks() {

            if (guestbooks == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

                try {
                    guestbooks = new ArrayList<Guestbook>();

                    List<Guestbook> list = GuestbookLocalServiceUtil.getGuestbooks(
                        scopeGroupId);

                    for (Guestbook guestbook : list) {
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

    These getter/setter methods encapsulate












### Creating Methods to Facilitate Portlet Navigation












## Using a View Bean to Facilitate Navigation

You've already learned a little about how a view bean works and its purpose in
your guestbook app. In summary, the view bean controls a portlet's views
(pages). It received requests via the backing beans and displays the correct
view to a user. Pretty simple, right? You'll begin creating one for your
guestbook portlet next. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Give it the name `ViewBean`. Then click *Add* next to the Interfaces field
   and type *Serializable*. Click *OK* and *Finish*. 

    It is common practice to have JSF managed beans implement the `Serializable`
    class if the bean is `@ViewScoped`.
    [Serialization](http://en.wikipedia.org/wiki/Serialization) translates the
    bean's state into a format that can be stored and rebuilt on your local
    machine or across a network to other computer environments. Likewise, you'll
    learn more about the view scope in the next step. 

3. Hover your mouse over the `ViewBean` class name and you'll notice a few
   options appear. Select the *Add generated serial version ID*. The following
   variable is added to your class: 

        private static final long serialVersionUID = 1L;

    This line is used to keep track of different versions of this class so the
    it can perform valid serialization of objects. 

4. Add the `@ManagedBean` and `@ViewScoped` tags above the public class
   declaration like the following: 

        ...

        @ManagedBean
        @ViewScoped
        public class ViewBean implements Serializable {

        ...

    You may recall the `viewBean` name that was specified in the
    `AbstractBackingBean` class during dependency injection: 

        @ManagedProperty(name = "viewBean", value = "#{viewBean}")

    Now that you've specified this class as a managed bean, its dependencies can
    now be injected into the `viewBean` property in your backing beans. 
    
    Another important tag to understand is the `@ViewScoped` tag. You may
    remember learning about the `@RequestScoped` tag, which was used for the
    backing beans that were only created on request and then removed from the
    server. The `@ViewScoped` declares that the bean will be kept on the server
    and have a known state as long as the portlet's view is being used by the
    user. Therefore, the general process for your guestbook is to store the
    content created from your request scoped backing beans on your view scoped
    beans before the backing beans are removed from the server. 
    
    The other view scoped bean you'll use is the model bean, which encapsulates
    your guestbook's entities and properties. You'll create the model bean once
    you're finished creating the view bean. 

5. Add the following properties to the view bean:

        protected static final Logger logger = LoggerFactory.getLogger(ViewBean.class);

        public static final String DEFAULT_GUESTBOOK_NAME = "Main";

        private boolean editingGuestbook;
        private boolean editingEntry;

    First, you've created a logger for aiding in your portlet's logging. Next,
    you've set your default guestbook name, which will appear as your first
    guestbook if none are specified. Lastly, you've declared two boolean
    variables, which will be used to distinguish which view to display. 

6. Add the following methods to your view bean:

        public void entry() {
            editingEntry = true;
            editingGuestbook = false;
        }

        public void guestbook() {
            editingEntry = false;
            editingGuestbook = true;
        }

        public void master() {
            editingEntry = false;
            editingGuestbook = false;
        }

    These methods use the boolean variables you specified in the previous step
    to distinguish which view to display. You haven't created your views yet,
    but you'll have four of them: `view` (default page), `entry`, `guestbook`,
    and `master`. 

7. Finish up the view bean by creating the following methods: 

        @PostConstruct
        public void postConstruct() {

            try {

                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();

                Guestbook defaultGuestbook = GuestbookLocalServiceUtil.getFirstGuestbookByName(scopeGroupId,
                    DEFAULT_GUESTBOOK_NAME);

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

        public void setEditingEntry(boolean editingEntry) {
            this.editingEntry = editingEntry;
        }

        public void setEditingGuestbook(boolean editingGuestbook) {
            this.editingGuestbook = editingGuestbook;
        }

        public boolean isEditingGuestbook() {
            return editingGuestbook;
        }

        public boolean isEditingEntry() {
            return editingEntry;
        }

    The `postConstruct()` method uses
    [`LiferayFacesContext`](/develop/tutorials/-/knowledge_base/6-2/using-the-liferayfacescontext-with-liferay-faces-portal)
    to initialize the guestbook portlet when first added to a page. Then, you
    created a couple setters and boolean methods to manage your `editingEntry`
    and `editingGuestbook` variables, which aid in the navigation between views.

8. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

Excellent! You've successfully created your view bean! The last bean to create
is your model bean. You'll dive into that next. 

## Using a Model Bean to Encapsulate Properties

The last managed bean you'll create for your JSF guestbook portlet is the model
bean. You've already learned that the model bean is used to encapsulate
properties and store data in the database. Thus, it primarily contains getters
and setters. The model bean is also view scoped, which means it stays on the
server as long as the portlet view (page) is open. 

Now that you have an idea of what the model bean accomplishes for your guestbook
portlet, it's time to create it! 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Name your class `GuestbookModelBean`. Then click *Add* next to the Interfaces
   field and type *Serializable*. Click *OK* and *Finish*. 

3. Hover your mouse over the `ViewBean` class name and you'll notice a few
   options appear. Select the *Add generated serial version ID*. 

4. Add the `@ManagedBean` and `@ViewScoped` tags above the public class
   declaration like the following: 

        ...

        @ManagedBean
        @ViewScoped
        public class GuestbookModelBean implements Serializable {

        ...

    Just like the view bean, the `@ManagedBean` specifies the class as a managed
    bean, and allows for dependency injection in the `AbstractBackingBean`.
    Likewise, the `@ViewScoped` tag allows this managed bean object to be kept
    on the server as long as the view page is open. 

5. Add the following properties to the model bean class: 

        private static final Logger logger = LoggerFactory.getLogger(GuestbookModelBean.class);

        private Guestbook selectedGuestbook;
        private List<Guestbook> guestbooks;

        private Entry selectedEntry;
        private List<Entry> entries;

    These properties are used for the getter/setter methods you'll add next.
    Thus, these variables will aid in storing your guestbooks and entries in
    the database. 

5. Add the following getter/setter methods: 

        public void forceEntriesReload() {
            setEntries(null);
        }

        public void forceGuestbooksReload() {
            setGuestbooks(null);
        }

        public List<Entry> getEntries() {

            if (entries == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();
                Guestbook selectedGuestbook = getSelectedGuestbook();

                try {
                    entries = EntryLocalServiceUtil.getEntries(scopeGroupId, selectedGuestbook.getGuestbookId());
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

        public List<Guestbook> getGuestbooks() {

            if (guestbooks == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

                try {
                    guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);
                }
                catch (SystemException e) {
                    logger.error(e);
                }
            }

            return guestbooks;
        }

        public void setGuestbooks(List<Guestbook> guestbooks) {
            this.guestbooks = guestbooks;
        }

        public Entry getSelectedEntry() {
            return selectedEntry;
        }

        public void setSelectedEntry(Entry selectedEntry) {
            this.selectedEntry = selectedEntry;
        }

        public Guestbook getSelectedGuestbook() {

            if (selectedGuestbook == null) {
                long scopeGroupId = LiferayFacesContext.getInstance().getScopeGroupId();

                try {
                    selectedGuestbook = GuestbookLocalServiceUtil.getFirstGuestbookByName(scopeGroupId,
                        ViewBean.DEFAULT_GUESTBOOK_NAME);
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

    You may remember from your backing beans that these setter methods are
    called to encapsulate what the backing beans created before their state is
    removed from the server. To summarize, the setter methods save your
    guestbook's properties to the database, and are retrieved by calling the
    getter methods. 

You've created all of your managed beans and are now ready to create your
portlet's views. Proceed to the next section to learn how. 
