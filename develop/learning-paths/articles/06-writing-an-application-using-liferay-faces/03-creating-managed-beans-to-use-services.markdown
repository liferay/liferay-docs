# Creating Managed Beans to Use Services

Now that your persistence and service layers are created, it's time to create
your managed beans to store and retrieve guestbooks and guestbook entries for
your users. 

You may be wondering what a managed bean is and what it accomplishes in a JSF
portlet. 

Managed beans are Java beans that are managed by the JSF framework. Therefore,
managed beans are responsible for storing the state of a web page, and JSF
*manages* these beans by creating and removing the bean object from the server.
Managed beans can also be associated with components used in a particular page
displayed by a JSF portlet. 

There are many Managed Bean types that you can use for a JSF portlet, but you'll
learn about the most popular ones and create them for your guestbook portlet. 

If you're familiar with the MVC design pattern, you'll notice that each managed
bean satisfies a
[*concern*](http://en.wikipedia.org/wiki/Separation_of_concerns) used in the MVC
framework. If you're not familiar with the MVC design pattern, you can visit the
[Writing a Data-Driven
Application](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/writing-a-data-driven-application)
learning path to learn about it, if you're interested. Below is a list of some
commonly used managed beans and their descriptions: 

- **Backing Managed Bean:** This type of managed bean serves the *View* concern
of the MVC design pattern. Backing beans support UI logic, and have a one to one
relationship with a JSF view. They typically have properties associated with
getters/setters, but these properties are of the view, not the underlying data
model. 

- **Controller (View) Managed Bean:** This type of managed bean serves the
*Controller* concern of the MVC design pattern. This bean executes business
logic and returns a navigation outcome to the JSF navigation handler. Controller
managed beans typically have JSF action methods. For the sake of this learning
path, the controller bean will be referred to as the *view* bean, since it
controls a portlet's views (pages). 

- **Model Managed Bean:** This type of managed bean serves the *Model* concern
of the MVC design pattern. The JSF model bean uses the getter/setter design
pattern, which encapsulates properties. The most common usage for a model bean
is to be a database entity, which is why it's sometimes referred to as a data
model. 

You will use all three of these bean types in your guestbook portlet. Now that
you have some background information on popular JSF managed beans, you'll begin
creating your guestbook's managed beans next. 

## Using Backing Beans to Facilitate UI Logic

The first beans you'll create in your guestbook are backing beans. As mentioned
earlier, backing beans support UI logic, so you'll need to create a backing bean
for guestbooks and for the guestbook's entries. Each bean will facilitate adding
a `Guestbook` or `Entry` entity.

Besides those two backing beans, you'll also create an abstract backing bean,
which will hold properties and methods required for both the guestbook and entry
backing beans. Therefore, both the guestbook and entry backing beans will extend
the abstract backing bean. 

Before you begin creating backing beans, you'll need to have a package dedicated
to hold all of your managed beans. To do this, right-click your
`docroot/WEB-INF/src` directory and select *New* &rarr; *Package*. Give it the
name `com.liferay.docs.guestbook.bean` and click *Finish*. 

Now you're ready to create your backing beans. You'll first create the
`AbstractBackingBean`. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Give it the name `AbstractBackingBean` and select the *package* and
   *abstract* modifier checkboxes. Then click *Finish*. 

3. Add the following properties to your class:

        protected static final Logger logger = LoggerFactory.getLogger(AbstractBackingBean.class);

        protected static final String UNEXPECTED_ERROR_MSG_ID = "an-unexpected-error-occurred";
        protected static final String SUCCESS_INFO_MSG_ID = "your-request-processed-successfully";

        @ManagedProperty(name = "guestbookModelBean", value = "#{guestbookModelBean}")
        protected GuestbookModelBean guestbookModelBean;

        @ManagedProperty(name = "viewBean", value = "#{viewBean}")
        protected ViewBean viewBean;

    Notice you first declared some general properties that help with logging and
    success/error messaging. Then, notice the `@ManagedProperty` tags. These
    annotations are used to inject dependencies from one managed bean into
    another. Thus, the `GuestbookModelBean` and `ViewBean`'s dependencies are
    injected into the `guestbookModelBean` and `viewBean` properties. 
    
    Are you wondering what these beans are and where they're coming from? The
    good news is you haven't missed anything; you haven't created those managed
    beans yet. Those will be your model and view beans, which you'll create
    later on. 
    
    Lastly, to make the dependency injection successful, the injected properties
    must be set in a setter method. You'll do that next. 

4. Add the following methods to your class:

        public void addGlobalSuccessInfoMessage() {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            liferayFacesContext.addGlobalSuccessInfoMessage();
        }

        public void addGlobalUnexpectedErrorMessage() {
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            liferayFacesContext.addGlobalUnexpectedErrorMessage();
        }

        public void cancel() {
            viewBean.master();
        }

        public void setGuestbookModelBean(GuestbookModelBean guestbookModelBean) {
            this.guestbookModelBean = guestbookModelBean;
        }

        public void setViewBean(ViewBean viewBean) {
            this.viewBean = viewBean;
        }

    As was specified previously, the setter methods for the `guestbookModelBean`
    and `viewBean` are created to finalize dependency injection. You also
    created methods that facilitate cancellation, and the addition of success
    and error messages. 

5. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

Great! That covers your abstract backing bean. Now its time to create the
backing beans specific to each of your entities. You'll start with creating the
guestbook backing bean. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Name the class `GuestbookBackingBean` and, for the Superclass field, browse
   for the `AbstractBackingBean` you just created. Then click *Finish*. 

3. In this backing bean, you'll provide action methods that your guestbook
portlet needs to function, which include: add, cancel, delete, edit, save, and
select. Add the following methods into your `GuestbookBackingBean` class: 

        public void add() {
            Guestbook guestbook = GuestbookUtil.create(0L);
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            guestbook.setGroupId(liferayFacesContext.getScopeGroupId());
            guestbookModelBean.setSelectedGuestbook(guestbook);
            viewBean.guestbook();
        }

        @Override
        public void cancel() {

            guestbookModelBean.setSelectedGuestbook(null);
            super.cancel();
        }

        public void delete(Guestbook guestbook) {

            try {
                GuestbookLocalServiceUtil.deleteGuestbook(guestbook.getGuestbookId());
                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            guestbookModelBean.forceGuestbooksReload();
            guestbookModelBean.forceEntriesReload();
            viewBean.master();
        }

        public void edit(Guestbook guestbook) {
            guestbookModelBean.setSelectedGuestbook(guestbook);
            viewBean.guestbook();
        }

        public void save() {
            Guestbook guestbook = guestbookModelBean.getSelectedGuestbook();

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

            guestbookModelBean.forceGuestbooksReload();
            guestbookModelBean.forceEntriesReload();
            viewBean.master();
        }

        public void select(Guestbook guestbook) {
            guestbookModelBean.setSelectedGuestbook(guestbook);
            guestbookModelBean.forceEntriesReload();
            viewBean.master();
        }

    Each method accomplishes

4. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

---

![note](../../images/01-tip.png) **Note:** You'll notice while creating some of
your managed beans that Liferay IDE/Developer Studio gives you error markers in
some of your classes. Don't worry about these error markers, you'll rebuild your
services at the end of this document and these errors will be resolved. 

---

This `GuestbookBackingBean` is *request scoped*, which means that it is only
created when a request is being made. JSF creates the bean, uses the bean as
requested, and then it is removed and available for garbage collection. The
request to create an instance of the `GuestbookBackingBean` is submitted from
XHTML files, or *views*. You'll create the views later on in the learning path. 

Because this bean is request scoped, after finishing its request, it sets what
was requested using a setter method on the model bean. Since you're setting
requests on the model bean, your intuition probably tells you that the model
bean can't be request scoped. The model bean is *view scoped*, but you'll learn
about that when creating the model bean in the next section. 

Since this backing bean is request scoped, you'll need to add the appropriate
tags to declare this in the class. Directly above the public class declaration,
insert the `@ManagedBean` and `@RequestScoped` tags. Your class is now request
scoped: 

    ...

    @ManagedBean
    @RequestScoped
    public class GuestbookBackingBean extends AbstractBackingBean {

    ...

Now that you've finished the `GuestbookBackingBean` class, you'll examine the
`add()` method, and learn what is happening when this method is called: 

    public void add() {
        Guestbook guestbook = GuestbookUtil.create(0L);
        LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
        guestbook.setGroupId(liferayFacesContext.getScopeGroupId());
        guestbookModelBean.setSelectedGuestbook(guestbook);
        viewBean.guestbook();
    }

Suppose a user clicks the *Add Guestbook* button. A request from the guestbook
view (XHTML) creates an instance of the `GuestbookBackingBean` and calls its
`add()` method. The `add()` method creates an empty guestbook with a `groupId`.
Next, that guestbook is set on the model bean by calling
`guestbookModelBean.setSelectedGuestbook(guestbook);`. Lastly, the view bean's
`guestbook()` method is called, which directs the portlet in opening the correct
page for the user to create their guestbook. When reaching the end of the
method, the instance of the backing bean is removed from the server. Pretty
cool, right? 

Next, you'll create the entry backing bean, which follows the same fundamental
process. 

1. Right-click on the `com.liferay.docs.guestbook.bean` package and select *New*
   &rarr; *Class*. 

2. Name the class `EntryBackingBean` and, for the Superclass field, browse
   for the `AbstractBackingBean` you just created. Then click *Finish*. 

3. For the `EntryBackingBean`, you'll create action methods similar to those you
specified in the `GuestbookBackingBean`: 

        public void add() {
            Entry entry = EntryUtil.create(0L);
            LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
            entry.setGroupId(liferayFacesContext.getScopeGroupId());
            entry.setGuestbookId(guestbookModelBean.getSelectedGuestbook().getGuestbookId());
            guestbookModelBean.setSelectedEntry(entry);
            viewBean.entry();
        }

        public void delete(Entry entry) {

            try {
                EntryLocalServiceUtil.deleteEntry(entry.getEntryId());
                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            guestbookModelBean.forceEntriesReload();
            viewBean.master();
        }

        public void edit(Entry entry) {
            guestbookModelBean.setSelectedEntry(entry);
            viewBean.entry();
        }

        public void save() {

            Entry entry = guestbookModelBean.getSelectedEntry();

            try {

                if (entry.getEntryId() == 0) {
                    entry = EntryLocalServiceUtil.addEntry(entry);
                }
                else {
                    entry = EntryLocalServiceUtil.updateEntry(entry);
                }

                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            guestbookModelBean.forceEntriesReload();
            viewBean.master();
        }

    These action methods function similarly to the `add()` method you explored
    previously in the guestbook backing bean. 

4. Add the `@ManagedBean` and `@RequestScoped` tags above the public class
   declaration. 

5. Press *Ctrl-Shift-O* to add and organize the class' necessary imports. 

Your backing beans are complete! You've learned quite a bit about the view bean
and how it works with backing beans. Now it's time to create it! 

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
    [`LiferayFacesContext`](/develop/tutorials/-/knowledge_base/using-the-liferayfacescontext-with-liferay-faces-portal)
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

3. 3. Hover your mouse over the `ViewBean` class name and you'll notice a few
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
