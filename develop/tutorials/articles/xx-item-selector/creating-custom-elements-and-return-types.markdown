# Creating Custom Elements and Return Types [](id=creating-custom-elements-and-return-types)

The Item Selector accepts entities using item selector 
criteria(`*ItemSelectorCriterion.class`) and return 
types(`*ItemSelectorReturnType.class`). You can read more about how to select 
entities using the Item Selector, and the default available criteria and return 
types in the [Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api) 
tutorial.

The Item Selector's APIs offer a lot out of the box for you to integrate into 
your app. However, your app may require a new entity type for selection. For 
instance your app may be a task manager that requires users to select tasks to 
assign to themselves. This new task entity needs a new matching item selector 
criterion. Likewise, if you expect custom information to be returned, such as 
the creator of the task and the user selecting it, you'll need to create new 
return types.

This tutorial demonstrates the how to:

- Create new item selector criterion
- Create new item selector return types

Go ahead and get started.

## Creating Custom Item Selector Criterion [](id=creating-custom-item-selector-criterion)

Using the task manager example mentioned in the intro, you would need to create 
a new criterion for the task entity. Follow the steps below to create your 
custom item selector criterion:

1.  Create a class that extends `BaseItemSelectorCriterion`.

    For example, using the task manager, your class might have the following 
    declaration:

        public class TasksItemSelectorCriterion extends 
        BaseItemSelectorCriterion {
        
        }
        
    That's all this is needed for this class.
    
    <!-- Is this always the case? Or would there be more info within the class?
    For instance, this FlickrItemSelectorCriterion test class contains more info:
    https://github.com/liferay/liferay-portal/blob/3bca2ff579a2fa7d01c423d98c5c7e9e7cbd4314/modules/apps/collaboration/item-selector/item-selector-web/src/test/java/com/liferay/item/selector/web/internal/FlickrItemSelectorCriterion.java
    I'm guessing that was just for test purposes though?-->

    When you create a new item selector criterion, you need to register an OSGi 
    component to handle obtaining the selection views for the new item 
    selector criterion.

2.  Create a new class, registered as an OSGI component, that implements 
    `BaseItemSelectorCriterionHandler`.

    For example, the task item selector criterion might have the following item 
    selector criterion handler:

        @Component(service = ItemSelectorCriterionHandler.class)
        public class TaskItemSelectorCriterionHandler extends 
        BaseItemSelectorCriterionHandler<TaskItemSelectorCriterion> {

            public Class <TaskItemSelectorCriterion> getItemSelectorCriterionClass() {
                return TasksItemSelectorCriterionHandler.class;
            }
            
            @Activate
            @Override
            protected void activate(BundleContext bundleContext) {
                    super.activate(bundleContext);
            }
    
        }

        
This returns this class when the criterion is requested, and activates the OSGi 
bundle, so it's available for use.
<!-- Is that correct? I updated the code example to include the bundle activation. -->

If your new entity can return default item selector information, such as a URL 
or primary key, you can use existing item selector return types. 
[Selecting Entities with the Item Selector's APIs](/develop/tutorials/-/knowledge_base/7-0/selecting-entities-using-the-item-selector-api) 
tutorial. If, however your app requires custom information returned, you need to 
create new return types. This is covered in the next section.

## Creating Custom Item Selector Return Types [](id=creating-custom-item-selector-return-types)

As a review, the information the entity returns when an item is selected is
referred to as the return type.

The example task manager app requires the following information when a user 
selects a task:

- the task identifier
- the task creator user

since this information is not a default item selector return type, a new item 
selector return type is needed.

Follow these steps to create a custom item selector return type:

1.  Create a class that implements `ItemSelectorReturnType`.

    Using the task manager example, the class implementation might look like 
    this:

        public class TasksItemSelectorReturnType implements 
        ItemSelectorReturnType

<!-- What else goes in this class. What is an example of content for the class? 
For example this class is populated with much more informaiton: https://github.com/liferay/liferay-portal/blob/2960360870ae69360861a720136e082a06c5548f/modules/apps/collaboration/item-selector/item-selector-taglib/src/main/java/com/liferay/item/selector/taglib/ItemSelectorRepositoryEntryBrowserReturnTypeUtil.java

What would example code for the task manager example app possibly look like?

We need to provide a more solid example for developers.
-->

So far, you've created a new API to allow users to select your new entity, tasks 
in the example, and return the required information, the task identifier and
task creator in the example. However, there is currently no selection view for
your new entity. The criterion and return types need to be accessible, so they 
can be used by the app when generating the item selector URL. Creating custom 
selection views is covered in detail in the [Creating Custom Selection Views](/develop/tutorials/-/knowledge_base/7-0/creating-custom-selection-views) 
tutorial.

The main steps for the task manager example are provided in the next.

1.  Create the `*ItemSelectorView` OSGi component:
        
        @Component
        public class TasksItemSelectorView
                implements ItemSelectorView<TasksItemSelectorCriterion> {
        
                public Class<TasksItemSelectorCriterion>
        getItemSelectorCriterionClass() {
                        return TasksItemSelectorCriterion.class;
                }
        
                public List<ItemSelectorReturnType>
        getSupportedItemSelectorReturnTypes() {
                        return _supportedItemSelectorReturnTypes;
                }
        
                public String getTitle(Locale locale) {
                        return "Tasks";
                }
        
                public boolean isShowSearch() {
                        return false;
                }
        
                public boolean isVisible(ThemeDisplay themeDisplay) {
                        return true;
                }
        
                public ServletContext getServletContext() {
                        return _servletContext;
                }
        
                public void renderHTML(
                                ServletRequest request, ServletResponse
        response,
                                MyTasksItemSelectorCriterion
        itemSelectorCriterion,
                                PortletURL portletURL, String
        itemSelectedEventName, boolean search)
                        throws IOException, ServletException {
        
                        // Pass the information that the view needs
        
                        ServletContext servletContext = getServletContext();
        
                        RequestDispatcher requestDispatcher =
                                servletContext.getRequestDispatcher(
                                    "/tasks.jsp"
                                );
        
                        requestDispatcher.include(request, response);
                }
        
                @Reference(
                        target =
        "(osgi.web.symbolicname=com.liferay.tasks.web)", unbind = "-"
                )
                public void setServletContext(ServletContext servletContext) {
                        _servletContext = servletContext;
                }
        
                private ServletContext _servletContext;
                private static final List<ItemSelectorReturnType>
                        _supportedItemSelectorReturnTypes =
        Collections.unmodifiableList(
                                ListUtil.fromArray(
                                        new ItemSelectorReturnType[] {
                                                new
        TasksItemSelectorReturnType(),
                                        }));
        
        }
        

    After your OSGi component is created, you'll need to create the JSP to
    render the selection view, to display the markup for the different tasks.
    This is covered in more detail in the [Creating Selection Views]() tutorial.
    
    <!-- What would this JSP possibly look like for the example? -->
    
    When the user selects a task, you'll need to fire a JavaScript with the name
    `itemSelectedEventName` specified in the `renderHTML` attribute, containing
    a JSON object with the information of the task ID and the creator user, as
    shown in the example code below:
    
    Liferay.fire(
            '<%= {eventName} %>',
    
            {
                    data: {the-data-your-client-needs}
            }
    
    );
    
    <!-- What would this code look like completed? -->
    
Now you know how to create custom elements and return types!

## Related Topics
    

