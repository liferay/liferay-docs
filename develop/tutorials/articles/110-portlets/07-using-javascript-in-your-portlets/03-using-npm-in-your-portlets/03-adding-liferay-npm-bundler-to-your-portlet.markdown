# Adding liferay-npm-bundler to Your Portlet [](id=adding-liferay-npm-bundler-to-your-portlet)

Adding liferay-npm-bundler to your portlet involves installing the package via 
npm and adding it to your npm build process. 

## Installing liferay-npm-bundler [](id=installing-liferay-npm-bundler)

Follow these steps to install liferay-npm-bundler:

1.  Install NodeJS >= [v6.11.0](http://nodejs.org/dist/v6.11.0/) if you don't 
    have it installed.

2.  Navigate to your portlet's `src/main/resources/META-INF/resources` folder.
     
    If you don't have a portlet already, create an empty MVC portlet. For 
    convenience, you can use 
    [Blade CLI](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/installing-blade-cli) 
    to create an empty portlet with the 
    [mvc portlet blade template](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

3.  Run the following command to install the liferay-npm-bundler:

        npm install --save-dev liferay-npm-bundler

+$$$

**Note:** Use npm from within your portlet project's root folder 
(where the `package.json` file lives), as you normally do on a typical web 
project.

$$$

Now that you have the liferay-npm-bundler installed, you can add it to your npm 
build process.

## Adding liferay-npm-bundler to Your Build Process [](id=adding-liferay-npm-bundler-to-your-build-process)

Once your source is transpiled (if necessary) to ECMAScript and converted to AMD 
format for the 
[Liferay AMD Loader](/develop/tutorials/-/knowledge_base/7-1/liferay-amd-module-loader), 
you must run liferay-npm-bundler in your `package.json` to pack the needed npm 
packages and transform them to AMD. This lets Liferay AMD Loader grab the 
packages.

Add `liferay-npm-bundler` to your `package.json`'s build script:

    "scripts": {
          "build": "[... && ] liferay-npm-bundler"
    }
 
The `[...&&]` refers to any previous steps you need to perform (for example, 
transpiling your sources with Babel).

You can use any languages you like as long as they can be transpiled to 
Ecmascript 5 or higher (the only requirement is that Babel can process it and 
your browser can execute it). When you deploy your portlet using Gradle, the 
build script is called as part of the process.

Now you know how to add the liferay-npm-bundler to your portlet!

## Related Topics [](id=related-topics)

[Configuring liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/configuring-liferay-npm-bundler)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)
