# Creating and Bundling JavaScript Portlets with JavaScript Tooling [](id=creating-and-bundling-javascript-portlets-with-javascript-tooling)

Portlets are a Java standard, so you must have a knowledge and understanding of
how Java works to write one. This can be quite the hurdle for front-end
developers who want to use JavaScript frameworks in their portlets. Thanks to 
the JS Portlet Extender, 
[Liferay Bundle Generator](https://www.npmjs.com/package/generator-liferay-bundle), 
and 
[liferay-npm-bundler](/develop/reference/-/knowledge_base/7-1/liferay-npm-bundler), 
developers can create and develop JavaScript portlets in @product@ using pure 
JavaScript tooling. 

![Figure 1: The JS Portlet Extender lets you use pure JavaScript tooling to write portlets.](../../images/extender-lifecycle.png)

This tutorial shows how to use the JS Portlet Extender app, Liferay Bundle 
Generator, and liferay-npm-bundler to create JavaScript Portlets for @product@. 

+$$$

**Important:** The JS Portlet Extender is a Labs application available from 
[Liferay Marketplace](http://www.liferay.com/marketplace). 
Apps designated as Labs are experimental and not supported by Liferay. They're 
released to accelerate the availability of useful and cutting-edge features. 
This status may change without notice. Please download and use Labs apps at your 
own discretion.

$$$

Follow these steps to create your JavaScript portlet:

1.  Install 
    [Node.js](http://nodejs.org/). 
    Note that Node Package Manager (npm) is installed with this as well. You’ll 
    use npm to install the remaining dependencies and generator.

2.  Install [Yeoman](http://yeoman.io/) for the generator:

        npm install -g yeoman

3.  Install the Liferay Bundle Generator:

        npm install -g generator-liferay-bundle

4.  Run the generator, selecting the JavaScript portlet you want to create, and 
    answer the prompts that follow:

        yo liferay-bundle

    ![Figure 2: The liferay-bundle generator prompts you for portlet options.](../../images/liferay-bundle-generator-prompts.png)

5.  Configure the main module of your JavaScript portlet. Note that it must 
    export a JavaScript function with the following signature:

        function({portletNamespace, contextPath, portletElementId}) {
          ...
        }

    The entry point function receives one object parameter with three fields:

    - `portletNamespace`: the unique namespace of the portlet as defined in 
    the Portlet specification.
    - `contextPath`: the URL path that can be used to retrieve bundle 
    resources from the browser (it doesn't contain the protocol, host, or port, 
    just the absolute path).
    - `portletElementId`: the DOM identifier of the portlet's `<div>` node 
    that can be used to render HTML.

    The JavaScript-based portlet's main `index.js` file configuration is shown 
    below:
    
        export default function main({portletNamespace, contextPath, 
        portletElementId}) {
            
            const node = document.getElementById(portletElementId);
            
            node.innerHTML =`
            <div>
            <span class="tag">Porlet Namespace:</span>
            <span class="value">${portletNamespace}</span>
            </div>
            <div>
            <span class="tag">Context Path:</span>
            <span class="value">${contextPath}</span>
            </div>
            <div>
            <span class="tag">Portlet Element Id:</span>
            <span class="value">${portletElementId}</span>
            </div>`;
            
        }

6.  Specify the portlet's initial JavaScript module in the `main` entry of the
    `package.json` file. Below is the `main` entry for the 
    JavaScript based portlet:
    
        "main": "index.js"

7.  Specify portlet properties to configure, under the `portlet` section of
    your portlet's `package.json` file. Note that these are the same properties
    you would define in the Java `@Component` annotation of a portlet, as
    defined in the
    [liferay-portlet-app_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html). 
    The configuration for the *JavaScript based portlet* liferay-bundle 
    generator option is shown below:

        "portlet": {
          "javax.portlet.display-name": "my-js-portlet-project",
          "javax.portlet.security-role-ref": "power-user,user",
          "com.liferay.portlet.instanceable": true,
          "com.liferay.portlet.display-category": "category.sample",
          "com.liferay.portlet.header-portlet-css": "/css/styles.css"
          },

8.  To create a JS Portlet Extender bundle with `liferay-npm-bundler`, follow 
    [the standard process](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler), 
    but provide the extra `create-jar` option to the bundler's `.npmbundlerrc` 
    file to make it output the final JAR. The JavaScript based portlet's 
    `.npmbundlerrc` contents are shown below:

        {
            "create-jar": {
                "auto-deploy-portlet": "true",
                "output-dir": "dist",
                "web-context-path": "/my-portlet"
            }
        }

    Valid options are

    - **auto-deploy-portlet**: Specifies that the bundler should create a JAR 
    file to be processed by the extender. Defaults to `true` and must only 
    be set to `false` when you want to create a bundle without a portlet 
    (for instance, if you want to provide shared npm packages to be used with 
      [imports](/develop/reference/-/knowledge_base/7-1/changes-between-liferay-npm-bundler-1x-and-2x#manually-deduplicating-through-importing)).
    - **output-dir**: Specifies where to put the generated JAR file. Defaults to 
    the standard output folder. You may want to specify a different location so 
    that the JAR and intermediate files are not mixed.
    - **web-context-path**: Specifies the URL path where static resources are 
    located in the bundle. Defaults to `<project name>-<project version>`. 

9.  Finally, if you specified your app server information when your portlet was 
    created with the Liferay Bundle Generator, you can deploy your portlet by 
    running the command below:

        npm run deploy

    The app server information is defined in the `liferayDir` entry of the 
    portlet's `.npmbuildrc` file:
    
        {
        	"liferayDir": "C:\\Users\\liferay\\opt\\Liferay\\bundles\\7.1.0-ga1\\liferay-ce-portal-7.1.0-ga1",
        	"webpack": {
        		"rules": [
        			{
        				"test": "src\\/.*\\.js$",
        				"use": "babel-loader"
        			}
        		]
        	}
        }

    The only prerequisite to make an OSGi bundle be processed by the extender is 
    the presence of the MANIFEST header shown below:
    
        Require-Capability: osgi.extender;filter:="(osgi.extender=liferay.npm.portlet)"
        
    The generated OSGi bundle automatically includes this for you. The npm 
    commands shown below are also available:
    
    - `npm run build`: Places the output of liferay-npm-bundler in the 
    designated output folder. The standard output is a JAR file that can be
    deployed manually to @product@.
    
    - `npm run start`: Tests the application in a local webpack 
    installation instead of a @product@ server. This speeds up development 
    because you can see live changes without any need to deploy. Note, however, 
    that because this is separate from a Liferay instance, you don't have access 
    to Liferay's APIs. 

## Related Topics [](id=related-topics)

[liferay-npm-bundler](/develop/reference/-/knowledge_base/7-1/liferay-npm-bundler)

[Using npm in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets)

[Applying Clay Styles to Your App](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)
