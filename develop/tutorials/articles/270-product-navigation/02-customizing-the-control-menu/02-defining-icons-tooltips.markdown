# Defining Icons and Tooltips

When creating a Control Menu entry, you can use an icon in addition to or in
place of text. You can also use tooltips to provide a more in depth
explanation.

## Control Menu Entry Icons

You can provide a Lexicon or CSS icon in your `*ControlMenuEntry`. To use a 
Lexicon icon, you should override the methods in 
`ProductMenuProductNavigationControlMenuEntry` like the following:

        public String getIconCssClass(HttpServletRequest request) {
            return "";
        }

        public String getIcon(HttpServletRequest request) {
            return "lexicon-icon";
        }

        public String getMarkupView(HttpServletRequest request) {
            return "lexicon";
        }

Likewise, you can use a CSS icon by overriding the
`ProductMenuProductNavigationControlMenuEntry` methods like the following:

        public String getIconCssClass(HttpServletRequest request) {
            return "icon-css";
        }

        public String getIcon(HttpServletRequest request) {
            return "";
        }

        public String getMarkupView(HttpServletRequest request) {
            return "";
        }

The icons used in the two examples for Lexicon and CSS icons can be found in
the [icons-lexicon](https://liferay.github.io/clay/content/icons-lexicon/)
and [icons-font-awesome](https://liferay.github.io/clay/content/icons-font-awesome/)
components, respectively.

### Control Menu Entry Tooltips

To provide a tooltip for the Control Menu entry create a `getLabel` method like 
this:

        @Override
        public String getLabel(Locale locale) {
            ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

            return LanguageUtil.get(
                resourceBundle, "the-portal-is-currently-reindexing");
        }

You need to create a `Language.properties` to store your labels. You can learn 
more about resource bundles in the [Internationalization](/develop/tutorials/-/knowledge_base/7-1/internationalization)
tutorials.
