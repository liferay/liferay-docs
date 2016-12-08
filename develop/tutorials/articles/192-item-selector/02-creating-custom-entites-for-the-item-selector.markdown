# Selecting Custom Entities Using the Item Selector

We have seen in previous tutorials how to use item selector to select entities 
using existing item selector criteria (ItemSelectorCriterion) and item selector 
return types (ItemSelectorReturnType)

This tutorial will explain how to create new item selector criteria and return 
types so you can use item selector to select your own custom entities and allow 
other applications to be able to select your custom entities as well. 

In order to select a new type of entity we need to create a new item selector 
criterion. This should be part of your application API so other modules can use 
it to invoke item selector.

Let’s imagine that we have an application that creates Tasks and we want users 
to select a task to assign it to themselves. Then, we might create a new 
criterion as follows:

public class TasksItemSelectorCriterion extends BaseItemSelectorCriterion {

}

Anytime we add a new item selector criterion, we need to register a OSGi 
component that will be responsible of obtaining the selection views for that 
particular item selector criterion. This is done by a OSGi component that 
implements ItemSelectorCriterionHandler. The code for our example tasks item 
selector criterion handler is as follows:

@Component(service = ItemSelectorCriterionHandler.class)

public class TasksItemSelectorCriterionHandler extends
	BaseItemSelectorCriterionHandler {

	public Class getItemSelectorCriterionClass() {
		return TasksItemSelectorCriterionHandler.class;
	}
}

In order to process the new assignee, our tasks application requires the 
following information when a user selects a task:

* the task identifier

* the task creator user

This information that needs to be returned by the selection view doesn’t match 
any of the item selector return types provided by the item selector api 
(Base64ItemSelectorReturnType, FileEntryItemSelectorReturnType, 
UploadableFileReturnType, URLItemSelectorReturnType, UUIDItemSelectorReturnType) 
so we will need to create a new item selector return type that will return this 
information. We will name it TasksItemSelectorReturnType and any view that uses 
it will need to return both the task identifier and the task creator user.

This step is optional, and often it won’t be needed because we will be able to 
reuse any of the item selector return types provided by the item selector api 
module.  This is the example code of our new TasksItemSelectorReturnType:

public class TasksItemSelectorReturnType implements ItemSelectorReturnType {

}

Until this point we have created a new API to allow the creation of selection 
views to select tasks that can be used by our application. Both classes  
TasksItemSelectorCriterion and TasksItemSelectorReturnType needs to be 
accessible so they can be used by the application when generating the item 
selector url.

Now, the only thing that is left is to create the view to select tasks. This is 
explained in detail in this tutorial 
[https://docs.google.com/document/d/1mjuYMtXfSn311AnHjNOxcOHNUc1fTcteCOBnXFFjB54/edit](https://docs.google.com/document/d/1mjuYMtXfSn311AnHjNOxcOHNUc1fTcteCOBnXFFjB54/edit). 

The main steps are documented next, but we encourage to visit the link to see 
the detail steps.

Create the ItemSelectorView OSGI component:

@Component
public class TasksItemSelectorView
	implements ItemSelectorView<TasksItemSelectorCriterion> {

	public Class<TasksItemSelectorCriterion> getItemSelectorCriterionClass() {
		return TasksItemSelectorCriterion.class;
	}

	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
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
			ServletRequest request, ServletResponse response,
			MyTasksItemSelectorCriterion itemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		// Pass the information that the view needs

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/tasks.jsp");

		requestDispatcher.include(request, response);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.tasks.web)", unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private ServletContext _servletContext;
	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new TasksItemSelectorReturnType(),
				}));

}

In the tasks.jsp we need to include the markup for the different tasks and when 
the user selects any task, we need to fire a javascript event with the name 
itemSelectedEventName specified in the renderHTML containing a json object with 
the information of the task id and the task creator user as follows

Liferay.fire(
	'<%= {eventName} %>',

	{
		data: {the-data-your-client-needs}
	}

);

