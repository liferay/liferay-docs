# Customizing Item Selector by adding new selection views

As we already explained in the introduction, Item Selector can render multiple 
views when selecting a particular element type. Below you can see an image of 
the Item Selector used to select images. You can see that there are multiple 
tabs (Blog Images, Documents and Media, URL, Upload Image), each of one 
represents a selection view. 

![image alt text](image_0.png)

The entity type that should be selected is represented by an item selector 
criterion. In this example, multiple criterions are used to specify what kind of 
entity we would like to select. Next you can see the criterions used in this 
example and which selection view matches that criterion:

1. BlogsItemSelectorCriterion -> Blog Images view

2. DLImageItemSelectorCriterion -> Documents and Media

3. URLItemSelectorCriterion -> URL

4. UploadItemSelectorCriterion-> Upload Image

This tutorial will show how to customize Item Selector by adding new selection 
views for some specific entity types. Let’s imagine that we want to create a new 
image selection view to select images from an external provider so when the user 
needs to select an image, our new image selection view will be rendered.

First of all we need to know what kind of entities can be selected by our new 
selection view. Images? Videos? Users? Depending on this we will need to use one 
ItemSelectorCriterion or another. In our particular example, as we want to 
select images, we will use ImageItemSelectorCriterion.

Once we know the entity type, we need to specify what kind of information we can 
return from the image. Our imaginary image provider only returns URLs so we will 
use URLItemSelectorReturnType for our example.

With this information we are ready to start. 

The item selector views are represented by the interface ItemSelectorView. All 
the selection views are OSGi component that implements this interface, so in 
order to create a new view we will need to create this new class and register it 
in OSGi using the inteface ItemSelectorView.

When implementing the ItemSelectorView we need to indicate which 
ItemSelectorCriterion is required to display this view using the method 
getItemSelectorCriterionClass. In our example we will return 
ImageItemSelectorCriterion.class.

We also need to provide what information we are returning when the entity is 
selected using the method getSupportedItemSelectorReturnTypes. In our case we 
will use URLItemSelectorReturnType but we could use more return types if we have 
the information.

One other relevant method is renderHTML whose responsibility is to render the 
view. In the example we will render the markup using JSPs, but you could use any 
other technology (like freemarker for example).

Below is the example source code: 

This is the SampleItemSelectorView code:

@Component(
	property = {"item.selector.view.order:Integer=200"},
	service = ItemSelectorView.class
)
public class SampleItemSelectorView
	implements ItemSelectorView<ImageItemSelectorCriterion> {

	public Class<ImageItemSelectorCriterion> getItemSelectorCriterionClass() {
		return ImageItemSelectorCriterion.class;
	}

	public ServletContext getServletContext() {
		return _servletContext;
	}

	public List<ItemSelectorReturnType> getSupportedItemSelectorReturnTypes() {
		return _supportedItemSelectorReturnTypes;
	}

	public String getTitle(Locale locale) {
		return "Sample Selector";
	}

	public boolean isShowSearch() {
		return false;
	}

	public boolean isVisible(ThemeDisplay themeDisplay) {
		return true;
	}

	public void renderHTML(
			ServletRequest request, ServletResponse response,
			ImageItemSelectorCriterion itemSelectorCriterion,
			PortletURL portletURL, String itemSelectedEventName, boolean search)
		throws IOException, ServletException {

		request.setAttribute(_ITEM_SELECTED_EVENT_NAME, itemSelectedEventName);

		ServletContext servletContext = getServletContext();

		RequestDispatcher requestDispatcher =
			servletContext.getRequestDispatcher("/sample.jsp");

		requestDispatcher.include(request, response);
	}

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.item.selector.sample.web)",
		unbind = "-"
	)
	public void setServletContext(ServletContext servletContext) {
		_servletContext = servletContext;
	}

	private static final String _ITEM_SELECTED_EVENT_NAME =
		"ITEM_SELECTED_EVENT_NAME";

	private static final List<ItemSelectorReturnType>
		_supportedItemSelectorReturnTypes = Collections.unmodifiableList(
			ListUtil.fromArray(
				new ItemSelectorReturnType[] {
					new URLItemSelectorReturnType()
				}));

	private ServletContext _servletContext;

}

Note that the OSGi component is registered with the property 
item.selector.view.order. This property is used by the Item Selector to choose 
the order of the views when rendering the tabs. The lower the value is, the more 
priority it has, and the first it will appear.

Keep in mind that the Item Selector orders the views attending to different 
things:

* First, it considers the order of the criteria decided by the client of the 
item selector. When the application invokes the item selector it will specify 
the criteria ordered by importance.

* Second, if there are multiple views for the same criteria, it will use the 
property item.selector.view.order to determine which will appear first.

Now, it`s up to you create the view markup, and do not forget fire an event 
using the event name. As you could see we have passed it as a request attribute 
in the renderHTML method in the SampleItemSelectorView class, so we can use it 
in the jsp as follows:

Liferay.fire(
	'<%= {eventName} %>',

	{
		data: {the-data-your-client-needs-according-return-type}
	}

);

In this event we should return the right data according with the specified 
desired ItemSelectorReturnTypes. 

This new selection view will be automatically rendered by the Item Selector in 
every portlet that uses Item Selector to select images and can can handle urls 
as a return type without modifying anything in those portlets.

If for any reason, we want this new selection view to be available only when 
selecting images for a blog entry, we should replace ImageItemSelectorCriterion 
by BlogsItemSelectorCriterion.

In the last tutorial we will explain how to use Item Selector to select your 
custom entities and allow other applications to select your custom entities as 
well.

If you want more information you could check here:

[https://docs.google.com/document/d/16nbb6BKi2b7u56dIJSlUGzKioz_HcLsaBPSH3S20A4o/edit](https://docs.google.com/document/d/16nbb6BKi2b7u56dIJSlUGzKioz_HcLsaBPSH3S20A4o/edit)

[https://docs.google.com/document/d/1lAUfVYdpprw2lEh1dyekHlhdGl0x7CqUaV6t3QaMxaA/edit](https://docs.google.com/document/d/1lAUfVYdpprw2lEh1dyekHlhdGl0x7CqUaV6t3QaMxaA/edit)

You could get the sample example code from here:

[https://github.com/robertoDiaz/liferay-portal/tree/item-selector-sample](https://github.com/robertoDiaz/liferay-portal/tree/item-selector-sample)


