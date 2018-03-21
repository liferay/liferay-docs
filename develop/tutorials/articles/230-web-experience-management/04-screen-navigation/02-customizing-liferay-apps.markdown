# Adding Custom Screens to Liferay Applications

You can extend certain Liferay Applications with custom screens. Custom screens 
can add configuration for custom features that you have developed, integrating 
them seamlessly with the original application.

## Categories Administration

The Categories Administration application supports adding Custom Screen to 
provide additional options for editing a category. To demonstrate adding a new 
Screen Navigation Entry and Category, we'll walk through adding one to 
Categories Administration.

1.  Create a new Java class in the `asset-categories-admin-web` module named 
    `CategoryCustomScreenNavigationEntry` that extends 
    `ScreenNavigationCategory` and `ScreenNavigationEntry`.

2. Add the following Component annotation above the class declaration:

        @Component(
	    property = {
		  "screen.navigation.category.order:Integer=10",
		  "screen.navigation.entry.order:Integer=10"
	    },
	    service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
        )

3.  For the class body, insert the following:

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
    		return LanguageUtil.get(locale, "details");
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
    
5.  At the top of your JSP class, insert the following scriplet at the top to use the Screen Navigation features:

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

6. For the rest of the JSP, create your custom screen.

Now you can use that pattern to create additional screens for whatever you need.