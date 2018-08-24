# Integrating with Angular JS [](id=integrating-with-angular-js)

You can use the 
[npm Angular portlet template](/develop/reference/-/knowledge_base/7-1/npm-angular-portlet-template) 
to automate much of the required configuration for you, or create the module 
manually if you wish. For convenience, all steps are listed if you want to 
proceed with a manual configuration. This tutorial shows how to use Angular in 
your portlets, whether you're migrating an existing Angular project or building 
a fresh one. See the 
[npm Angular portlet template](/develop/reference/-/knowledge_base/7-1/npm-angular-portlet-template) 
reference docs for more information on the portlet's anatomy. Get started by 
creating your OSGi module and configuring its metadata. 

## Configuring Metadata [](id=configuring-metadata)

Follow these steps to create the module and configure its metadata for Angular:

1.  [Create an OSGi module](/develop/tutorials/-/knowledge_base/7-1/starting-module-development#creating-a-module). 
    For example, use the 
    [npm Angular portlet template](/develop/reference/-/knowledge_base/7-1/npm-angular-portlet-template). 

2.  Specify the `Web-ContextPath` BND Header in your project's `bnd.bnd` file. 
    Below is the default configuration for the npm Angular portlet template:

        Web-ContextPath: /my-npm-angular-portlet

3.  Create a 
    [`.babelrc` file](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler) 
    and add the following presets to it:

        {
        	"presets": ["liferay-amd"]
        }

4.  Add a 
    [`.npmbundlerrc` file](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler) 
    to your project's root folder and add the 
    [`liferay-npm-bundler-preset-angular` preset](https://www.npmjs.com/package/liferay-npm-bundler-preset-angular). 
    This preset provides the required `.npmbundlerrc` configuration. You can, 
    however, copy the contents of the preset and add additional configuration 
    options to the file instead if needed:

        {
            "preset": "liferay-npm-bundler-preset-angular"
        }

5.  Include the following dependency to your `build.gradle` file:

        compileOnly group: "com.liferay", 
        name: "com.liferay.frontend.js.loader.modules.extender.api", 
        version: "2.0.2"

6.  Create a `package.json` in your project if it doesn't already exist and add 
    the configuration shown below to it. Update the `"main"` JS path to reflect 
    your app's main JS file (remember to add the `.es.js` extension if your file 
    uses it). Note that the `liferay-npm-bundler` is added last to the build 
    script. List any additional build processes before this that your project 
    requires, such as the `tsc` (Typescript) process shown below:

        {
        	"dependencies": {
        		"@angular/common": "4.2.6",
        		"@angular/compiler": "4.2.6",
        		"@angular/compiler-cli": "4.2.6",
        		"@angular/core": "4.2.6",
        		"@angular/forms": "4.2.6",
        		"@angular/http": "4.2.6",
        		"@angular/platform-browser": "4.2.6",
        		"@angular/platform-browser-dynamic": "4.2.6",
        		"@angular/platform-server": "4.2.6",
        		"@angular/router": "4.2.6",
        		"@angular/tsc-wrapped": "4.2.6",
        		"@angular/upgrade": "4.2.6",
        		"angular-in-memory-web-api": "0.3.2",
        		"core-js": "2.5.1",
        		"reflect-metadata": "0.1.10",
        		"rxjs": "5.4.1",
        		"zone.js": "0.8.17"
        	},
        	"devDependencies": {
        		"@types/angular": "1.6.32",
        		"@types/angular-animate": "1.5.8",
        		"@types/angular-cookies": "1.4.5",
        		"@types/angular-mocks": "1.5.11",
        		"@types/angular-resource": "1.5.14",
        		"@types/angular-route": "1.3.4",
        		"@types/angular-sanitize": "1.3.6",
        		"@types/jasmine": "2.5.53",
        		"@types/node": "8.0.31",
        		"babel-cli": "6.26.0",
        		"babel-preset-liferay-amd": "1.6.1",
        		"canonical-path": "0.0.2",
        		"concurrently": "3.5.0",
        		"http-server": "0.10.0",
        		"jasmine": "2.6.0",
        		"jasmine-core": "2.6.4",
        		"karma": "1.7.1",
        		"karma-chrome-launcher": "2.2.0",
        		"karma-cli": "1.0.1",
        		"karma-jasmine": "1.1.0",
        		"karma-jasmine-html-reporter": "0.2.2",
        		"karma-phantomjs-launcher": "1.0.4",
        		"liferay-npm-bundler": "1.6.1",
        		"liferay-npm-bundler-preset-angular": "1.6.1",
        		"lite-server": "2.3.0",
        		"lodash": "4.17.4",
        		"phantomjs-prebuilt": "2.1.15",
        		"protractor": "5.1.2",
        		"source-map-explorer": "1.5.0",
        		"tslint": "5.7.0",
        		"typescript": "2.5.2"
        	},
        	"main": "js/angular-loader.js",
        	"name": "my-npm-angular-portlet",
        	"scripts": {
        		"build": "tsc && babel --source-maps -d build/resources/main/META-INF/resources src/main/resources/META-INF/resources && liferay-npm-bundler"
        	},
        	"version": "1.0.0"
        }

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
        		"javax.portlet.name=" + MyNpmAngularPortletKeys.MyNpmAngular,
        		"javax.portlet.resource-bundle=content.Language",
        		"javax.portlet.security-role-ref=power-user,user"
        	},
        	service = Portlet.class
        )
        public class MyNpmAngularPortlet extends MVCPortlet {
          ...
        }
        
2.  If your Angular project includes CSS styling as well, add the following 
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
        		MyNpmAngularWebKeys.BOOTSTRAP_REQUIRE,
        		jsPackage.getResolvedId() + " as bootstrapRequire");

        	super.doView(renderRequest, renderResponse);
        }

        @Reference
        private NPMResolver _npmResolver;

4.  Inside your `init.jsp`, add the following Java scriptlet to access the 
    `bootstrapRequire` variable in your portlet's `view.jsp`:

        <%
        String bootstrapRequire = (String)renderRequest.getAttribute(
          MyNpmAngularWebKeys.BOOTSTRAP_REQUIRE
        );
        %>

Next you can learn how to render your app's component and transpile your JS 
files. 

## Transpiling your JS and Rendering Your Component [](id=transpiling-your-js-and-rendering-your-component)

Follow these steps to transpile your JS files and render your app component:

1.  To use ES2015+ syntax in your portlet, you must transpile it for the 
    browser. To do this, replace the `.js` extension with `.es.js` for all JS 
    files that need transpiled to ES2015+. For example the main `index.js` file 
    would be renamed `index.es.js`. JS files with this extension are 
    automatically transpiled by Babel before deployment. 
   
2.  Inside your app's main TS file (`main.ts` for example), use the 
    function below to render your component:

        import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';

        import { AppComponent } from './app/app.component';
        import { AppModule } from './app/app.module';
        import { DynamicLoader } from './app/dynamic.loader';

        export default function(rootId: any) {
        	platformBrowserDynamic()
        		.bootstrapModule(AppModule)
        		.then((injector: any) => {

        			// Load the bootstrap component dynamically so that we can attach it
        			// to the portlet's DOM, which is different for each portlet
        			// instance and, thus, cannot be determined until the page is
        			// rendered (during runtime).

        			// The rootId argument is passed from view.jsp where we can obtain
        			// the portlet's namespace by using JSP tags.

        			const dynamicLoader = new DynamicLoader(injector);

        			dynamicLoader.loadComponent(AppComponent, rootId);
        		});
        }

    Then in a separate file, such as `angular-loader.ts`, you can add the 
    following configuration:

        // Import needed polyfills before application is launched

        import 'reflect-metadata';
        import 'zone.js';

        // Declare Liferay AMD loader

        declare var Liferay: any;

        // Launch application

        export default function(rootId: any) {
        	Liferay.Loader.require('my-npm-angular-portlet@1.0.0/js/main', 
          (main: any) => {
        		main.default(rootId);
        	});
        }

3.  Open your `view.jsp` and add an element container to house your component. 
    Then, add an `<aui:script>` and pass your aliased module name as the 
    `require` attribute's value. Finally, call your module's `default` function 
    that you exported in the previous step, and pass the container element in as 
    the element ID. Adding the `<portlet:namespace />` to the `<div>`'s `id` 
    ensures that it is unique to the portlet and doesn't clash with any existing 
    elements on the page:

        <%@ include file="/init.jsp" %>

        <div id="<portlet:namespace />-root"></div>

        <aui:script require="<%= bootstrapRequire %>">
        	bootstrapRequire.default('#<portlet:namespace />-root');
        </aui:script>

## Related Topics [](id=related-topics)

[Integrating with React](/develop/tutorials/-/knowledge_base/7-1/integrating-with-react)

[Integrating with Vue](/develop/tutorials/-/knowledge_base/7-1/integrating-with-vue)

[Using npm in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets)
