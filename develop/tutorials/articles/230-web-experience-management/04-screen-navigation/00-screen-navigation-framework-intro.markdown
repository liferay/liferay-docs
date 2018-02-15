# Screen Navigation Framework

The Screen Navigation Framework is for customizing and extending application
UIs. You can use it to customize Liferay's own applications and to make your own
applications customizable. 

## Application Navigation

To make this work, the framework assumes a specific structure for screens and
supports one or two levels of navigation. Each item in the top level navigation
is a `ScreenNavigationCategory`. Each item in the second level is
a `ScreenNavigationEntry`. Categories are usually represented by tabs, while
entries use a second level of navigation. You need not have any Entries in your
application, but you must have at least one Category.

![Figure X: A typical application using screen navigation has three categories and numerous entries.](../../../images/screen-nav-sample-screen-1.png)

The Screen structure normally renders Navigation Categories as horizontal tabs
at the top of the page and Navigation Entries as a vertical list of items along
the left side of the page. The screen box containing the content uses the rest
of the screen. You can customize this default layout it for your needs.

Secondary navigation is optional; you may only need category tabs with no
additional navigation.

![Figure X: Secondary navigation is optional; you may opt to have only tabs.](../../../images/screen-nav-one-level.png)

<!-- The screenshot only shows one level of navigation - the tabs across the top. --> 

## Using the Framework for Your Application

The Screen Navigation Framework comprises two parts: Java classes for your
screens, and a tag library for your front-end. First we'll cover creating the 
necessary Java classes and then adding the front-end support through JSPs.

### Adding Screens to Your Application's Back-end

You must create at least one Navigation CategoryTo add screens to your application, first you must add at least one Navigation 
Category for the top level navigation. Then you can add additional Navigation 
Entries for each page that you need.

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
provides a way to render HTML. Liferay developers typically use JSPs, which we'll demonstrate next.

### Adding Screens to Your Application's Front-end

To use JSPs to render your screens, you must invoke the `JSPRenderer` 
component in your `render` method and create the JSP which renders the
HTML.

1.  Create a `render` method which uses `JSPRenderer` like this:

        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
            
            _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
        }

2.  Add the following code at the bottom of your class to use the reference 
    annotation to access the JSPRender:

        @Reference
        private JSPRenderer _jspRenderer;

3. Create a JSP that includes the `liferay-frontend:screen-navigation` taglib and the necessary parameters like this:

        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

The parameters it needs are `key`, `modelBean`, and `portletURL`.

* **Key**: a unique name for the navigation in this application.

* **modelBean**: the model that is being rendered

* **portletURL**: the portlet URL used to build the titles for each link.

## Adding Custom Screens to Liferay Applications

You can extend certain Liferay Applications with custom screens. Custom screens 
can add configuration for custom features that you have developed, integrating 
them seamlessly with the original application.

### Categories Administration

The Categories Administration application supports adding Custom Screen to 
provide additional options for editing a category. The category


* `ScreenNavigationKey`: `edit.category.form`

* Existing Categories: `properties`, `details`

The following example shows how to add an entry to the primary navigation of 
the categories navigation portlet. To avoid creating two classes, in this case, 
the same class implements both `ScreenNavigationCategory` and 
`ScreenNavigationEntry`. 

    @Component(
      property = {
         "screen.navigation.category.order:Integer=10",
         "screen.navigation.entry.order:Integer=10"
      },
      service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
     )
    public class CategoryDetailsScreenNavigationEntry
      implements ScreenNavigationCategory, ScreenNavigationEntry<AssetCategory> {

      @Override
      public String getCategoryKey() {
     return "details";
      }

      @Override
      public String getEntryKey() {
         return "details";
      }

      @Override
      public String getLabel(Locale locale) {
         return LanguageUtil.get(locale, "details");
      }

      @Override
      public String getScreenNavigationKey() {
         return AssetCategoriesConstants.CATEGORY_KEY_GENERAL;
      }

      @Override
      public void render(HttpServletRequest request, HttpServletResponse response)
         throws IOException {

         _jspRenderer.renderJSP(request, response, "/category/details.jsp");
      }

      @Reference
      private JSPRenderer _jspRenderer;
    }

