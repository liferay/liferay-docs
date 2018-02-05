# Screen Navigation Framework

The Screen Navigation enables developers to customize and extend the UI of 
applications in Liferay. Developers can use the framework to customize Liferay's
own applications, and they can use the framework in their own custom 
applications to make the applications more flexible.

## Application Navigation

The Screen Navigation Framework assumes a specific structure for screens and 
supports one or two levels of navigation. Each item in the top level navigation 
is a `ScreenNavigationCategory`. Each item in the second level is a 
`ScreenNavigationEntry`. Categories are usually represented by tabs, while 
entries use a second level of navigation. You don't need to have any Entries
in your application, but you do need to have at least one Category.

![Figure X: A typical application using screen navigation.](../../../images/screen-nav-sample-screen-1.png)

The Screen structure will typically put the Navigation Categories as tabs 
organized horizontally across the top of the page and the Navigation Entries as 
a selection of items listed vertically along the left side of the page. In 
addition, the screen box will be be taking up most of the pages real estate 
with the actual content. While that is the typical layout, you can customize it 
to fit the needs of your application.

Screens also do not need to use a secondary navigation. Some screens will only 
have Categories organized as tabs, and no additional navigation.

![Figure X: An application with only one level of navigation.](../../../images/screen-nav-one-level.png)


## Using the Framework for Your Application

Liferay uses the Screen Navigation Framework for its internal apps, and third
party developers can use it to help their applications integrate cleanly into 
Liferay. To use it, you would use the `liferay-frontend:screen-navigation` 
taglib in your JSP like this:


    <liferay-frontend:screen-navigation key=
      "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
       modelBean="<%= category %>"
       portletURL="<%= portletURL %>"
    />

The parameters it needs are `key`, `modelBean`, and `portletURL`.

* **Key**: a unique name for the navigation in this application.

* **modelBean**: the model that is being rendered

* **portletURL**: the portlet URL used to build the titles for each link.
    
For most applications, you'll need to add more than one screen. In fact, if you 
only had one screen, you probably wouldn't the navigation at all. In the next 
section, you'll learn how to add more screens.

### Adding Screens to Your Application

Typically the first thing you want to do is to add at least one Navigation 
Category for your top level navigation. After that, you can add Navigation 
Entries for each page that you need for your application.

To add a new Category, you create a component that implements the 
`ScreenNavigationCategory` interface. In order to create the category, you must
implement the following methods:

* `getCategoryKey()`: returns the category's primary key.

* `getLabel(Locale locale)`: returns the label of the key.

* `getScreenNavigationKey()`: returns the navigation key that the category 
    belongs in, as defined in your application.
    
Adding a category is similar. You create a component which implements
`ScreenNavigationEntry` and then implements the following methods:

* `getCategoryKey()`: returns the category's primary key.

* `getEntryKey()`: returns the entry's primary key.

* `getLabel()`: returns the entries label.

* `getScreenNavigationKey()`: returns the navigation key for the category of the
    current entry.
    
* `isVisible(User user, T screenModelBean)`: boolean for whether or not the 
    entry should be visible for the current user.
    
* `render(HttpServletRequest request, HttpServletResponse response)`: renders
    the entry.
    
You can implement your render method any way that you want. Basically, you just 
need to provide some way to render HTML. As a convention, Liferay developers 
typically use JSPs. You can use the `JSPRenderer` component to do use them in 
your own project:


    @Override
    public void render(HttpServletRequest request, HttpServletResponse response)
      throws IOException {

      _jspRenderer.renderJSP(request, response, "/my-category/view-category.jsp");
    }

    @Reference
    private JSPRenderer _jspRenderer;

## Adding Custom Screens to Liferay Applications

Some Liferay Applications can be extended with custom screens by third party
developers.

### Categories Administration

You can add additional screens to the Categories Administration application to
provide additional options for editing a category.


* `ScreenNavigationKey`: `edit.category.form`

* Existing Categories: `properties`, `details`

The following example shows how to add an entry to the primary navigation of 
the categories navigation portlet. To avoid creating two classes, in this case, 
the same class implements both ScreenNavigationCategory and 
ScreenNavigationEntry. 

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

