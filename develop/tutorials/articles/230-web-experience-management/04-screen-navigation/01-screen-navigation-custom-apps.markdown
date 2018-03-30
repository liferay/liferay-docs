# Using the Framework for Your Application [](id=using-the-framework-for-your-application)

The Screen Navigation Framework comprises two parts: Java classes for your
screens and a tag library for your front-end. First you'll create the necessary
Java classes and then add the front-end support through JSPs.

## Adding Screens to Your Application's Back-end [](id=adding-screens-to-your-applications-back-end)

You must create at least one Navigation Category. To add screens to your
application, first you must add at least one Navigation Category for the top
level navigation. Then you can add additional Navigation Entries for each page
that you need.

First, add a Navigation Category

1.  Create a component that implements the `ScreenNavigationCategory` 
    interface. 

2.  Implement the following methods in your component:

    **`getCategoryKey()`**: returns the category's primary key.

    **`getLabel(Locale locale)`**: returns the label of the key.

    **`getScreenNavigationKey()`**: returns the navigation key that the 
    category belongs in, as defined in your application.
 
Next, add a Navigation Entry. 

1.  Create a component which implements `ScreenNavigationEntry`.

2.  Implement the following methods in your component:

    **`getCategoryKey()`**: returns the category's primary key.

    **`getEntryKey()`**: returns the entry's primary key.

    **`getLabel()`**: returns the entries label.

    **`getScreenNavigationKey()`**: returns the navigation key for the category 
    of the current entry.

    **`isVisible(User user, T screenModelBean)`**: boolean for whether or not 
    the entry should be visible for the current user.

    **`render(HttpServletRequest request, HttpServletResponse response)`**: 
    renders the entry.

You can implement your render method any way that you want as long as it
provides a way to render HTML. Liferay developers typically use JSPs, shown
below. 

## Adding Screens to Your Application's Front-end [](id=adding-screens-to-your-applications-front-end)

To use JSPs to render your screens, you must invoke the `JSPRenderer` component
in your `render` method and create the JSP that renders the HTML.

1.  Create a `render` method which uses `JSPRenderer` like this:

        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
            
            _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
        }

2.  Add the following code at the bottom of your class to use the reference
    annotation to access the `JSPRenderer`:

        @Reference
        private JSPRenderer _jspRenderer;

3.  Create a JSP that includes the `liferay-frontend:screen-navigation` taglib
    and the necessary parameters like this:

        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

The parameters it needs are `key`, `modelBean`, and `portletURL`.

* **Key**: a unique name for the navigation in this application.

* **modelBean**: the model that is being rendered

* **portletURL**: the portlet URL used to build the titles for each link.

In the next section, you'll see how to extend an existing Liferay class with 
more screens.
