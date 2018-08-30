# Using Vue in Your Portlets [](id=using-vue-in-your-portlets)

You can use the 
[npm Vue portlet template](/develop/reference/-/knowledge_base/7-1/npm-vue-js-portlet-template) 
to automate much of the required configuration for you, or create the module 
manually if you wish. For convenience, all steps are listed if you want to 
proceed with a manual configuration. This tutorial shows how to use Vue JS in 
your portlets, whether you're migrating an existing Vue project or building a 
fresh one. See the 
[npm Vue portlet template](/develop/reference/-/knowledge_base/7-1/npm-vue-js-portlet-template) 
reference docs for more information on the portlet's anatomy or the 
[npm Vue portlet sample](/develop/reference/-/knowledge_base/7-1/vue-js-npm-portlet) 
for a Vue portlet example that you can test and deploy right now. Get started 
by creating your OSGi module and configuring its metadata. 

## Configuring Metadata [](id=configuring-metadata)

Follow these steps to create the module and configure its metadata for Vue:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module). 
    For example, use the 
    [npm Vue portlet template](/develop/reference/-/knowledge_base/7-1/npm-vue-js-portlet-template). 

2.  Specify the `Web-ContextPath` BND Header in your project's `bnd.bnd` file. 
    Below is the default configuration for the npm Vue portlet template:

        Web-ContextPath: /my-npm-vue-portlet

3.  Create a 
    [`.babelrc` file](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler) 
    and add the following presets to it:

        {
        	"presets": ["env"]
        }

4.  Optionally add a 
    [`.npmbundlerrc` file](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler) 
    to your project's root folder. This file is not required. You can, however, 
    configure this file to customize the liferay-npm-bundler to suite your 
    needs, such as to ignore files. 

5.  Include the following dependency to your `build.gradle` file:

        compileOnly group: "com.liferay", 
        name: "com.liferay.frontend.js.loader.modules.extender.api", 
        version: "2.0.2"

6.  Create a `package.json` in your project if it doesn't already exist and add 
    the configuration shown below to it. Update the `"main"` JS path to point to 
    your app's main JS file. Note that the `liferay-npm-bundler` is added last 
    to the build script. List any additional build processes before this that 
    your project requires:

        {
        	"dependencies": {
        		"vue": "2.4.4"
        	},
          "description": "Vue.js Portlet",
        	"devDependencies": {
        		"babel-cli": "^6.26.0",
        		"babel-preset-env": "^1.7.0",
        		"liferay-npm-bundler": "^2.0.0"
        	},
        	"main": "js/index.js",
        	"name": "my-npm-vuejs-portlet",
        	"scripts": {
        		"build": "babel --source-maps -d 
            build/resources/main/META-INF/resources 
            src/main/resources/META-INF/resources && liferay-npm-bundler"
        	},
        	"version": "1.0.0"
        }

To use ES2015+ syntax in your portlet, you must transpile it for the browser. 
Babel, included in your build script, takes care of this for you. 

Next You can configure the portlet.

## Configuring the Portlet [](id=configuring-the-portlet)
 
Follow these steps to configure your portlet:

1.  Create a Component class that implements the `Portlet.class` service:

        @Component(
        	immediate = true,
        	property = {
        		"com.liferay.portlet.display-category=category.sample",
        		"com.liferay.portlet.instanceable=true",
        		"javax.portlet.init-param.template-path=/",
        		"javax.portlet.init-param.view-template=/view.jsp",
        		"javax.portlet.name=" + MyNpmVuejsPortletKeys.MyNpmVuejs,
        		"javax.portlet.resource-bundle=content.Language",
        		"javax.portlet.security-role-ref=power-user,user"
        	},
        	service = Portlet.class
        )
        public class MyNpmVuejsPortlet extends MVCPortlet {
          ...
        }
        
2.  If your Vue project includes CSS styling as well, add the following 
    additional property to specify the location of the main CSS file:
    
        "com.liferay.portlet.header-portlet-css=/css/main.css"

    Note that this path is relative to the resources path. If using Sass, drop 
    the `.scss` extension in this property and use `.css` instead. For example, 
    if your main CSS file is located in 
    `src/main/resources/META-INF/resources/css/app.scss`, then you would have 
    the following configuration:
    
        "com.liferay.portlet.header-portlet-css=/css/app.css"

3.  To improve code maintenance, 
    [use the NPMResolver APIs to alias your module's package name](/develop/tutorials/-/knowledge_base/7-1/referencing-an-npm-modules-package). 
    The example below exposes the module's name as `bootstrapRequire`:

        @Override
        public void doView(
        		RenderRequest renderRequest, RenderResponse renderResponse)
        	throws IOException, PortletException {

        	JSPackage jsPackage = _npmResolver.getJSPackage();

        	renderRequest.setAttribute(
        		MyNpmVuejsWebKeys.BOOTSTRAP_REQUIRE,
        		jsPackage.getResolvedId() + " as bootstrapRequire");

        	super.doView(renderRequest, renderResponse);
        }

        @Reference
        private NPMResolver _npmResolver;

4.  Inside your `init.jsp`, add the following Java scriptlet to access the 
    `bootstrapRequire` variable in your portlet's `view.jsp`:

        <%
        String bootstrapRequire = (String)renderRequest.getAttribute(
          MyNpmVuejsWebKeys.BOOTSTRAP_REQUIRE
        );
        %>

Next you can learn how to render your app's component. 

## Rendering Your Component [](id=rendering-your-component)

Follow these steps to render your app component:
   
1.  Inside your app's main JS file (`index.js` for example), use the function 
    below to render your component:

        import Vue from 'vue/dist/vue.common';

        export default function(portletNamespace) {
        	// Application 1
        	new Vue({
        		el: `#${portletNamespace}-root`,
        		data: {
        			message: 'Hello from Vue.js!',
        		},
        		methods: {
        			reverseMessage: function() {
        				this.message = this.message
        					.split('')
        					.reverse()
        					.join('');
        			},
        		},
        	});
        }

2.  Open your `view.jsp` and add an element container to house your component. 
    Then, add an `<aui:script>` and pass your aliased module name as the 
    `require` attribute's value. Finally, call your module's `default` function 
    that you exported in the previous step, and pass the portlet namespace. 
    Adding the `<portlet:namespace />` to the `<div>`'s `id` ensures that it is 
    unique to the portlet and doesn't clash with any existing elements on the 
    page:

        <%@ include file="/init.jsp" %>

        <div id="<portlet:namespace />-root"></div>

        <aui:script require="<%= bootstrapRequire %>">
        	bootstrapRequire.default('<portlet:namespace />');
        </aui:script>

Now you know how to use Vue in your projects! 

## Related Topics [](id=related-topics)

[Using Angular JS in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-angular-js-in-your-portlets)

[Using React in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-react-in-your-portlets)

[Using npm in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets)