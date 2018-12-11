# Creating and Bundling JavaScript Portlets with JavaScript Tooling [](id=creating-and-bundling-javascript-portlets-with-javascript-tooling)

Portlets are a Java standard, so you must have a knowledge and understanding of 
how Java works to write one. This can be quite the hurdle for front-end 
developers who want to use JavaScript frameworks in their portlets. Thanks to 
the JS Portlet Extender and 
[liferay-npm-bundler](/develop/reference/-/knowledge_base/7-1/liferay-npm-bundler), 
developers can easily create and develop JavaScript portlets in @product@ using pure 
JavaScript tooling. 

![Figure 1: The JS Portlet Extender lets you use pure JavaScript tooling to write portlets.](../../images/extender-lifecycle.png)

This tutorial shows how to use the 
[Liferay Bundle Generator](https://www.npmjs.com/package/generator-liferay-bundle) to help 
automate the portlet creation process, but you can create the generated files using your own 
tooling if you prefer. 

+$$$

**Important:** The JS Portlet Extender is a Labs application available from 
Liferay Marketplace for 
[Liferay Digital Enterprise 7.1](https://web.liferay.com/marketplace/-/mp/application/115543020) 
and 
[Liferay Portal CE 7.1](https://web.liferay.com/marketplace/-/mp/application/115542926). 
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

        function({portletNamespace, contextPath, portletElementId, configuration}) {
          ...
        }

    The entry point function receives one object parameter with four fields:

    - `portletNamespace`: the unique namespace of the portlet as defined in 
    the Portlet specification. 
    - `contextPath`: the URL path that can be used to retrieve bundle 
    resources from the browser (it doesn't contain the protocol, host, or port, 
    just the absolute path). 
    - `portletElementId`: the DOM identifier of the portlet's `<div>` node 
    that can be used to render HTML. 
    - `configuration` (optional): since JS Portlet Extender version 1.1.0, this 
    field contains the OSGi configuration for the generated bundle. If no 
    configuration is defined, an empty Object is passed. Note that all values 
    are received as strings, no matter what their type is in OSGi configuration 
    store. 

    The JavaScript-based portlet's main `index.js` file configuration is shown 
    below. Note that I have chosen to use system settings and localization in my 
    portlet:
    
        export default function main({portletNamespace, contextPath, portletElementId, configuration}) {
            
            const node = document.getElementById(portletElementId);

            node.innerHTML =`
                <div>
                    <span class="tag">${Liferay.Language.get('porlet-namespace')}:</span>
                    <span class="value">${portletNamespace}</span>
                </div>
                <div>
                    <span class="tag">${Liferay.Language.get('context-path')}:</span>
                    <span class="value">${contextPath}</span>
                </div>
                <div>
                    <span class="tag">${Liferay.Language.get('portlet-element-id')}:</span>
                    <span class="value">${portletElementId}</span>
                </div>
                
                <div>
                    <span class="tag">${Liferay.Language.get('configuration')}:</span>
                    <span class="value">
                        ${JSON.stringify(configuration, null, 2)}
                    </span>
                </div>
                
            `;
            
        }

6.  Specify the portlet's JavaScript module in the `main` entry of the 
    `package.json` file. Below is the `main` entry for the 
    *JavaScript based portlet*:
    
        "main": "index.js"

7.  Specify the portlet properties under the `portlet` section of your portlet's 
    `package.json` file. Note that these are the same properties 
    you would define in the Java `@Component` annotation of a portlet, as 
    defined in the 
    [liferay-portlet-app_7_1_0.dtd](@platform-ref@/7.1-latest/definitions/liferay-portlet-app_7_1_0.dtd.html). 
    The example *JavaScript based portlet*'s configuration is shown below:

        "portlet": {
        	"com.liferay.portlet.display-category": "category.sample",
        	"com.liferay.portlet.header-portlet-css": "/css/styles.css",
        	"com.liferay.portlet.instanceable": true,
        	"javax.portlet.name": "my_js_portlet_project",
        	"javax.portlet.security-role-ref": "power-user,user",
        	"javax.portlet.resource-bundle": "content.Language"
        },

8.  To create a JS Portlet Extender bundle with `liferay-npm-bundler`, follow 
    [the standard process](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler), 
    but provide the extra `create-jar` option to the bundler's `.npmbundlerrc` 
    file to make it output the final JAR. The *JavaScript based portlet*'s 
    `.npmbundlerrc` `create-jar` configuration, with localization and system 
    settings enabled, is shown below. See the 
    [OSGi Bundle Creation Options](/develop/reference/-/knowledge_base/7-1/configuring-liferay-npm-bundler#osgi-bundle-creation-options) 
    reference for a complete list of the available configuration options.

        "create-jar": {
        	"output-dir": "dist",
        	"features": {
        		"js-extender": true,
        		"web-context": "/my-portlet",
        		"localization": "features/localization/Language",
        		"settings": "features/settings.json"
        	}
        },

9.  Optionally, if your portlet uses localization, configure the 
    `Language.properties` file (specified with the `localization` property in 
    your portlet's `.npmbundlerrc` file) and provide the localized property 
    files (e.g. Language_[locale].properties) with the 
    [language keys](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#what-are-language-keys) 
    for each 
    [available translation](/develop/tutorials/-/knowledge_base/7-1/localizing-your-application#what-locales-are-available-by-default). 
    The *JavaScript based portlet* configuration is shown below:

        javax.portlet.title.my_js_portlet_project=My JS Portlet Project
        porlet-namespace=Porlet Namespace
        context-path=Context Path
        portlet-element-id=Portlet Element Id
        configuration=Configuration
        fruit=Favourite fruit
        fruit-help=Choose the fruit you like the most
        an-orange=An orange
        a-pear=A pear
        an-apple=An apple
        
    You can retrieve a language key's localized value in your JavaScript with 
    the `Liferay.Language.get('key')` method.

10.  Optionally, if your portlet provides system settings, configure your 
     `settings.json` file. See the 
     [settings.json reference guide](/develop/reference/-/knowledge_base/7-1/configuring-system-settings-for-osgi-bundles-created-with-the-bundler) 
     for a complete list of the available configuration options. The 
     *JavaScript based portlet*'s sample configuration is shown below:
    
        {
        	"fields": {
        		"fruit": {
        			"type": "string",
        			"name": "fruit",
        			"description": "fruit-help",
        			"default": "orange",
        			"options": {
        				"orange": "an-orange",
        				"pear": "a-pear",
        				"apple": "an-apple"
        			}
        		}
        	}
        }

     You can access a system setting's value with the syntax 
     `configuration.field`. For instance, to retrieve the `fruit` field's value, 
     you would use `configuration.fruit`, which would return `"orange"`, 
     `"pear"`, or `"apple"`. Note that all fields are passed as strings no 
     matter what type they declare in their descriptor.

11.  Finally, if you specified your app server information when your portlet was 
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

    To process the OSGi bundle with the extender, it must have the MANIFEST 
    header shown below:
    
        Require-Capability: osgi.extender;filter:="(osgi.extender=liferay.npm.portlet)"
        
    The generated OSGi bundle automatically includes this for you. The npm 
    commands shown below are also available:
    
    - `npm run build`: Places the output of liferay-npm-bundler in the 
    designated output folder. The standard output is a JAR file that can be 
    deployed manually to @product@. 
    
    - `npm run start`: Tests the application in a local webpack installation 
    instead of a @product@ server. This speeds up development because you can 
    see live changes without any need to deploy. Note, however, that because 
    this is separate from a Liferay instance, you don't have access to Liferay's 
    APIs. 

## Related Topics [](id=related-topics)

[liferay-npm-bundler](/develop/reference/-/knowledge_base/7-1/liferay-npm-bundler)

[Using npm in Your Portlets](/develop/tutorials/-/knowledge_base/7-1/using-npm-in-your-portlets)

[Applying Clay Styles to Your App](/develop/tutorials/-/knowledge_base/7-1/applying-clay-styles-to-your-app)
