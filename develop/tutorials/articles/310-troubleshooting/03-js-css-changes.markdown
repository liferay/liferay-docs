# Why Aren't my Module's JavaScript and CSS Changes Showing? [](id=why-arent-my-modules-javascript-and-css-changes-showing)

To determine why JavaScript and CSS updates to your module aren't having an 
effect in your browser, perform these checks: 

1.  If you're developing a portlet module, check that your portlet class has the 
    correct properties specified in its `@Component` annotation: 

    - Make sure the resources referred to by the properties of your portlet 
      class's `@Component` annotation exist in the correct location in your module 
      project. 
    - Make sure that you're using a portlet CSS wrapper class to prevent 
      potential CSS ID and class name conflicts with other applications on the
      page. 

    For example, consider this sample portlet class: 

        @Component(
            immediate = true,
            property = {
                "com.liferay.portlet.css-class-wrapper=example-portlet",
                "com.liferay.portlet.display-category=category.sample",
                "com.liferay.portlet.instanceable=true",
                "com.liferay.portlet.header-portlet-css=/css/main.css",
                "com.liferay.portlet.header-portlet-js=/css/main.js",
                "javax.portlet.display-name=Example Portlet",
                "javax.portlet.init-param.template-path=/",
                "javax.portlet.init-param.view-template=/view.jsp",
                "javax.portlet.name=" + ExamplePortletKeys.TicTacToe,
                "javax.portlet.resource-bundle=content.Language",
                "javax.portlet.security-role-ref=power-user,user"
            },
            service = Portlet.class
        )
        public class ExamplePortlet extends MVCPortlet {

        }

    As described in the first item above, the portlet's CSS file is specified by 
    the property `com.liferay.portlet.header-portlet-css`. Paths specified as 
    values of this property are relative to the module's 
    `src/main/resources/META-INF/resources` folder. So if you specify a value 
    of `css/main.css`, the actual path to the CSS file in the module is 
    `src/main/resources/META-INF/resources/css/main.css`. The path to your 
    portlet's JavaScript file is specified by the property 
    `com.liferay.portlet.header-portlet-js`. Values for this property work the 
    same as the values for the CSS property. 

    Also note that the property `com.liferay.portlet.css-class-wrapper` 
    specifies the CSS class wrapper `example-portlet`. Thus, you should use 
    subclasses of `example-portlet` in your portlet's actual CSS file. For 
    example, in `main.css` you'd do this to change the background to green: 

        .example-portlet {
            .greenBackground {
                background-color: green;
            }

            ... (further properties)

        }

    In other words, to avoid CSS class and ID name conflicts, all the CSS
    properties you specify must be subclasses of the class specified via the
    `com.liferay.portlet.css-class-wrapper` property. @product@ wraps your 
    portlet's HTML content with a `<div>`. The class specified by 
    `com.liferay.portlet.css-class-wrapper` (`example-portlet`, in this example) 
    has been applied to this `<div>`. 

2.  Check that caching isn't preventing JS and CSS updates to your module from 
    appearing in your browser: 

    - Clear your browser's cache.
    - During development, enable developer mode to turn off @product@'s resource 
    caching. 
    [Click here](/develop/tutorials/-/knowledge_base/7-0/using-developer-mode-with-themes) 
    to learn how to enable @product@'s developer mode. 

## Related Topics [](id=related-topics)

[Using Developer Mode with Themes](/develop/tutorials/-/knowledge_base/7-0/using-developer-mode-with-themes)
