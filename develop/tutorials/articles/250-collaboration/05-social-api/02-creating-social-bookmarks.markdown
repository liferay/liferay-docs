# Creating Social Bookmarks [](id=creating-social-bookmarks)

[Applying social bookmarks](/develop/tutorials/-/knowledge_base/7-1/applying-social-bookmarks) 
lets you link your app's content to the social networks of your choice. By 
default, @product@ supports social bookmarks for Twitter, Facebook, LinkedIn, 
and Google Plus. You can also create your own custom social bookmark by 
registering a component that implements the `SocialBookmark` interface from the 
module `com.liferay.social.bookmarks.api`. This tutorial shows you how to do 
this. 

## Implementing the SocialBookmark Interface [](id=implementing-the-socialbookmark-interface)

Follow these steps to implement the `SocialBookmark` interface: 

1.  Create your `*SocialBookmark` class and register a component that defines 
    the `social.bookmarks.type` property. This property's value is what you 
    enter for the `liferay-social-bookmarks:bookmarks` tag's `type` attribute 
    when you use your social bookmark. 

    For example, here's the definition for a Twitter social bookmark class: 

        @Component(immediate = true, property = "social.bookmarks.type=twitter")
        public class TwitterSocialBookmark implements SocialBookmark {...

2.  Create a 
    [`ResourceBundleLoader`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ResourceBundleLoader.html) 
    reference to help localize the social bookmark's name. 

        @Reference(
                target = "(bundle.symbolic.name=com.liferay.social.bookmark.twitter)"
        )
        private ResourceBundleLoader _resourceBundleLoader;

3.  Implement the `getName` method to return the social bookmark's name as a 
    string. This method takes a 
    [`Locale`](https://docs.oracle.com/javase/8/docs/api/java/util/Locale.html) 
    object that you can use for localization via 
    [`LanguageUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/language/LanguageUtil.html) 
    and 
    [`ResourceBundle`](https://docs.oracle.com/javase/8/docs/api/java/util/ResourceBundle.html): 

        @Override
        public String getName(Locale locale) {
            ResourceBundle resourceBundle = _resourceBundleLoader.loadResourceBundle(locale);

            return LanguageUtil.get(resourceBundle, "twitter");
        }

4.  Implement the `getPostURL` method to return the share URL. This method 
    constructs the share URL from a title and URL, and uses 
    [`URLCodec`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/URLCodec.html) 
    to encode the title in the URL: 

        @Override
        public String getPostURL(String title, String url) {
            return String.format(
                "https://twitter.com/intent/tweet?text=%s&tw_p=tweetbutton&url=%s", 
                URLCodec.encodeURL(title), url);
        }

5.  Create a `ServletContext` reference: 

        @Reference(
                target = "(osgi.web.symbolicname=com.liferay.social.bookmark.twitter)"
        )
        private ServletContext _servletContext;

6.  Implement the `render` method, which is called when the inline
    display style is selected. Typically, this method renders a link to the
    share URL (e.g., a share button), but you can use it for whatever you need.
    To keep a consistent look and feel with the default social bookmarks, you
    can use a [Clay icon](/develop/tutorials/-/knowledge_base/7-1/clay-icons). 

    This example gets a `RequestDispatcher` for the JSP that contains a Clay 
    icon (`page.jsp`), and then includes that JSP in the response: 

        @Override
        public void render(
                        String target, String title, String url, HttpServletRequest request,
                        HttpServletResponse response)
                throws IOException, ServletException {

                RequestDispatcher requestDispatcher =
                        _servletContext.getRequestDispatcher("/page.jsp");

                requestDispatcher.include(request, response);
        }

Next, you'll see an example of how to create such a `page.jsp` file.

## Creating Your JSP [](id=creating-your-jsp)

The `page.jsp` file referenced in the above `SocialBookmark` implementation uses 
[a Clay link](/develop/tutorials/-/knowledge_base/7-1/clay-labels-and-links) 
(`clay:link`) to specify and style the Twitter icon included with Clay. Follow 
these steps to create such a JSP for your own social bookmark:

1.  Add the `clay` and `liferay-theme` taglib declarations: 

        <%@ taglib uri="http://liferay.com/tld/clay" prefix="clay" %>
        <%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>

2.  Import 
    [`GetterUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/GetterUtil.html) 
    and 
    `SocialBookmark`: 

        <%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
        <%@ page import="com.liferay.social.bookmarks.SocialBookmark" %>

3.  From the request, get a `SocialBookmark` instance and the social bookmark's 
    title and URL: 

        <%
        SocialBookmark socialBookmark = (SocialBookmark)request.getAttribute("liferay-social-bookmarks:bookmark:socialBookmark");
        String title = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:title"));
        String url = GetterUtil.getString((String)request.getAttribute("liferay-social-bookmarks:bookmark:url"));
        %>

    The title and URL are set via the `liferay-social-bookmarks` 
    taglib when 
    [applying the social bookmark](/develop/tutorials/-/knowledge_base/7-1/applying-social-bookmarks).

4.  Add the Clay link. This example sets the following `clay:link` attributes: 

    -   `buttonStyle`: This example renders 
        [The button's type](/develop/tutorials/-/knowledge_base/7-1/clay-buttons#types)
        as a secondary button.
    -   `elementClasses`: The custom CSS to use for styling the button (optional).
    -   `href`: The button's URL. You should specify this by calling your 
        `SocialBookmark` instance's `getPostURL` method. 
    -   `icon`: The button's icon. This example specifies the Twitter icon 
        included in Clay (`twitter`). 
    -   `title`: The button's title. This example uses the `SocialBookmark` 
        instance's `getName` method. 

            <clay:link
                    buttonStyle="secondary"
                    elementClasses="btn-outline-borderless btn-sm lfr-portal-tooltip"
                    href="<%= socialBookmark.getPostURL(title, url) %>"
                    icon="twitter"
                    title="<%= socialBookmark.getName(locale) %>"
            />

To see a complete, real-world example of a social bookmark implementation, see 
[Liferay's Twitter social bookmark code](https://github.com/liferay/liferay-portal/tree/7.1.x/modules/apps/social/social-bookmark-twitter). 

## Related Topics [](id=related-topics)

[Applying Social Bookmarks](/develop/tutorials/-/knowledge_base/7-1/applying-social-bookmarks)

[Using the Clay Taglib in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-the-clay-taglib-in-your-portlets)
