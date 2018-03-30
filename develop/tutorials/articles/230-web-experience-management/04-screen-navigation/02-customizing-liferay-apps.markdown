# Adding Custom Screens to Liferay Applications [](id=adding-custom-screens-to-liferay-applications)

You can extend certain Liferay Applications with custom screens. Custom screens 
can add configuration for features you've developed, integrating them seamlessly
with the original application.

## Categories Administration [](id=categories-administration)

The Categories Administration application supports adding Custom Screens to
provide additional options for editing a category. To demonstrate adding a new
Screen Navigation Entry and Category, you'll add one to Categories
Administration.

1.  Create a new Java class in the `asset-categories-admin-web` module named 
    `CategoryCustomScreenNavigationEntry` that extends 
    `ScreenNavigationCategory` and `ScreenNavigationEntry`.

2. Add the following Component annotation above the class declaration:

        @Component(
            property = {
    		  "screen.navigation.category.order:Integer=1",
	    	  "screen.navigation.entry.order:Integer=1"
	        },
    	    service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
        )

    The `screen.navigation.category.order` and `screen.navigation.entry.order`
    determine where in the navigation the items appear. Higher is first in the
    navigation.
 
    In the `service` declaration, declare it as defining
    a `ScreenNavigationCategory`, `ScreenNavigationEntry`, or both.

3.  For the class body, insert this code:

        @Override
        public String getCategoryKey() {
            return "custom-screen";
        }

        @Override
        public String getEntryKey() {
            return "custom-screen";
        }

        @Override
        public String getLabel(Locale locale) {
            return LanguageUtil.get(locale, "custom-screen");
        }

        @Override
        public String getScreenNavigationKey() {
            return AssetCategoriesConstants.CATEGORY_KEY_GENERAL;
        }

        @Override
        public void render(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

           _jspRenderer.renderJSP(request, response, "/category/custom-screen.jsp");
        }

        @Reference
        private JSPRenderer _jspRenderer;

4.  Create a `custom-screen.jsp` in the 
    `/resources/META-INF/resources/category/` folder.
 
5.  At the top of your JSP class, insert the following scriplet at the top to use the Screen Navigation UI:

        <%
        String redirect = ParamUtil.getString(request, "redirect", assetCategoriesDisplayContext.getEditCategoryRedirect());

        long categoryId = ParamUtil.getLong(request, "categoryId");

        AssetCategory category = AssetCategoryLocalServiceUtil.fetchCategory(categoryId);

        long parentCategoryId = BeanParamUtil.getLong(category, request, "parentCategoryId");

        long vocabularyId = ParamUtil.getLong(request, "vocabularyId");

        portletDisplay.setShowBackIcon(true);
        portletDisplay.setURLBack(redirect);

        renderResponse.setTitle(((category == null) ? LanguageUtil.get(request, "add-new-category") : category.getTitle(locale)));
        %>

6.  Below that insert the following tag:

        <liferay-frontend:screen-navigation key=
        "<%= AssetCategoriesConstants.CATEGORY_KEY_GENERAL %>"
        modelBean="<%= category %>"
        portletURL="<%= portletURL %>"
        />

7. For the rest of the JSP, create your custom screen.

Now you can use that pattern to create additional screens for whatever you need.
