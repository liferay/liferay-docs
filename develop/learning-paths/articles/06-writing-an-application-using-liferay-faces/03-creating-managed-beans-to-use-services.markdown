# Creating Managed Beans to Use Services

Now that your persistence and service layers are created, it's time to create
your managed beans to store and retrieve guestbooks and guestbook entries for
your users. 

You may be wondering what a managed bean is and what it accomplishes in a JSF
portlet. 

Managed beans are Java beans that are managed by the JSF framework. They are
usually associated with components used in a particular page displayed by a JSF
portlet. There are many Managed Bean types that you can use for a JSF portlet,
but you'll learn about the most popular ones and create them for your guestbook
portlet. 

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

- **Controller Managed Bean:** This type of managed bean serves the *Controller*
concern of the MVC design pattern. This bean executes business logic and returns
a navigation outcome to the JSF navigation handler. Controller managed beans
typically have JSF action methods. 

- **Model Managed Bean:** This type of managed bean serves the *Model* concern
of the MVC design pattern. The JSF model bean uses the getter/setter design
pattern, which encapsulates properties. The most common usage for a model bean
is to be a database entity, which is why it's sometimes referred to as a data
model.

Now that you have some background information on popular JSF managed beans,
you'll begin creating your guestbook's managed beans next. 

## Using Backing Beans to Facilitate UI Logic

The first beans you'll create in your guestbook are backing beans. As mentioned
earlier, backing beans support UI logic, so you'll need to create a backing bean
for guestbooks and for the guestbook's entries. Each bean will facilitate adding
a guestbook or entry entity.

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
    beans yet. Those will be your model and controller beans, which you'll
    create later on. 
    
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

<!-- Add remaining backing beans -->

## Using Controller Beans to Facilitate Navigation


## Using Model Beans to Encapsulate Properties

You will use all three of these beans in your guestbook portlet. 
