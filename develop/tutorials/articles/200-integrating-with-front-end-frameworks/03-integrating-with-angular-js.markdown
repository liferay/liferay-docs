# Using Angular JS in Your Portlets [](id=using-angular-js-in-your-portlets)

You can use the 
[npm Angular portlet template](/develop/reference/-/knowledge_base/7-1/npm-angular-portlet-template) 
to automate much of the required configuration for you, or create the module 
manually if you wish. For convenience, all steps are listed if you want to 
proceed with a manual configuration. This tutorial shows how to use Angular JS 
in your portlets, whether you're migrating an existing Angular project or 
building a fresh one. See the 
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

3.  Create a `.tsconfig.json` file and add the following configuration to it:

        {
        	"compilerOptions": {
        		"emitDecoratorMetadata": true,
        		"experimentalDecorators": true,
        		"lib": ["es2015", "dom"],
        		"moduleResolution": "node",
        		"outDir": "build/resources/main/META-INF/resources/lib",
        		"sourceMap": true,
        		"suppressImplicitAnyIndexErrors": true,
        		"target": "es5",
        		"typeRoots": ["./node_modules/@types/"],

        		"module": "commonjs",
        		"strict": true,
        		"noFallthroughCasesInSwitch": true,
        		"inlineSources": true,
        		"declaration": false,
        		"skipLibCheck": true,
        		"types": ["jasmine", "node"]
        	},
        	"include": ["src/main/resources/META-INF/resources/**/*.ts"]
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
    your project requires, such as the `tsc` (Typescript) process shown below:

        {
        	"dependencies": {
        		"@angular/animations": "^5.0.0",
        		"@angular/common": "^5.0.0",
        		"@angular/compiler": "^5.0.0",
        		"@angular/core": "^5.0.0",
        		"@angular/forms": "^5.0.0",
        		"@angular/http": "^5.0.0",
        		"@angular/platform-browser": "^5.0.0",
        		"@angular/platform-browser-dynamic": "^5.0.0",
        		"@angular/platform-server": "^5.0.0",
        		"@angular/router": "^5.0.0",
        		"@ngx-translate/core": "^9.1.1",
        		"core-js": "^2.5.1",
        		"rxjs": "^5.5.2",
        		"zone.js": "0.8.12"
        	},
        	"description": "Angular Portlet",
        	"devDependencies": {
        		"@angular/cli": "^1.6.7",
        		"@angular/compiler-cli": "^5.0.0",
        		"@compodoc/compodoc": "1.0.0-beta.10",
        		"@types/bootstrap": "^3.3.33",
        		"@types/bootstrap-datepicker": "0.0.6",
        		"@types/jasmine": "2.5.48",
        		"@types/jquery": "^2.0.46",
        		"@types/moment": "^2.13.0",
        		"@types/node": "~6.0.60",
        		"@types/toastr": "^2.1.34",
        		"chalk": "1.1.3",
        		"codelyzer": "3.1.2",
        		"del": "^3.0.0",
        		"gulp": "^3.9.1",
        		"gulp-flatten": "^0.3.1",
        		"gulp-sass": "^3.1.0",
        		"inline-ng2-resources": "^1.1.0",
        		"jasmine-core": "~2.6.2",
        		"jasmine-spec-reporter": "~3.2.0",
        		"karma": "1.7.0",
        		"karma-chrome-launcher": "~2.1.1",
        		"karma-cli": "~1.0.1",
        		"karma-coverage-istanbul-reporter": "^1.1.0",
        		"karma-jasmine": "~1.1.0",
        		"karma-jasmine-html-reporter": "^0.2.2",
        		"karma-junit-reporter": "1.2.0",
        		"karma-remap-istanbul": "^0.2.1",
        		"karma-spec-reporter": "0.0.31",
        		"liferay-npm-bundler": "^2.0.0",
        		"protractor": "~5.1.0",
        		"rollup": "0.41.6",
        		"rollup-plugin-commonjs": "^8.0.2",
        		"rollup-plugin-node-resolve": "3.0.0",
        		"shelljs": "0.7.7",
        		"sorcery": "0.10.0",
        		"ts-node": "~2.0.0",
        		"tslint": "5.4.0",
        		"typescript": "2.4.2",
        		"webpack": "2.6.1",
        		"yargs": "8.0.1"
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

Next you can learn how to render your app's component. 

## Rendering Your Component [](id=rendering-your-component)

Follow these steps to render your app component:
   
1.  Inside your app's main TS file (`main.ts` for example), use the 
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

2.  In a separate file, such as `angular-loader.ts`, you can add the following 
    configuration:

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
    the root ID. Adding the `<portlet:namespace />` to the `<div>`'s `id` 
    ensures that it is unique to the portlet and doesn't clash with any existing 
    elements on the page:

        <%@ include file="/init.jsp" %>

        <div id="<portlet:namespace />-root"></div>

        <aui:script require="<%= bootstrapRequire %>">
        	bootstrapRequire.default('#<portlet:namespace />-root');
        </aui:script>

Now you know how to use Angular JS in your projects! 

## Related Topics [](id=related-topics)

[Using React in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-react-in-your-portlets)

[Using Vue in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-vue-in-your-portlets)

[Using npm in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets)
