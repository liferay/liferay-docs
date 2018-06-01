# Adding liferay-npm-bundler to Your Portlet [](id=adding-liferay-npm-bundler-to-your-portlet)

Adding liferay-npm-bundler to your portlet involves installing the package via 
npm and adding it to your npm build process. 

## Installing liferay-npm-bundler [](id=installing-liferay-npm-bundler)

Follow these steps to install liferay-npm-bundler:

1.  Install NodeJS >= [v6.11.0](http://nodejs.org/dist/v6.11.0/) if you don't 
    have it installed.

2.  Navigate to your portlet's project folder and initialize a `package.json` if
    it is not present yet.

    If you don't have a portlet already, create an empty MVC portlet project.
    For convenience, you can use
    [Blade CLI](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/installing-blade-cli)
    to create an empty portlet with the
    [mvc portlet blade template](https://dev.liferay.com/develop/reference/-/knowledge_base/7-1/using-the-mvc-portlet-template).

    If you don't have a `package.json` file you can run `npm init -y` to tell
    `npm` to create an empty one based on the project directory's name.

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

Once your source is transpiled (if necessary) to ECMAScript 5 you must run
liferay-npm-bundler in your `package.json` to pack the needed npm
packages and transform them to AMD. This lets Liferay AMD Loader grab the
packages.

+$$$

**Note:** In version series 1.x of the bundler it was the developer's
responsibility to wrap project modules in an AMD `define()` call. However, since
2.x the bundler does it for you, so the only requisite is that the project's
code is transpiled/written for CommonJS modules model (the standard model for
module handling in Node.js, that uses `require()` calls to load modules).

$$$

Add `liferay-npm-bundler` to your `package.json`'s build script:

    "scripts": {
          "build": "[... && ] liferay-npm-bundler"
    }

The `[...&&]` refers to any previous steps you need to perform (for example,
transpiling your sources with Babel, compiling SOY templates, transpiling
Typescript, and so on...).

You can use any languages you like as long as they can be transpiled to
ECMAscript 5 or higher. The only requirements are:

1.  That Babel can convert them to an AST to be able to process it

2.  That your browser can execute it.

3.  That modules are loaded using `require()` calls (this requirement can be
    relaxed by using customized plugins, but is mandatory for the default
    out-of-the-box configuration).

When you deploy your portlet using Gradle, the build script is called as part of
the process.

Now you know how to add the liferay-npm-bundler to your portlet!

## Related Topics [](id=related-topics)

[Configuring liferay-npm-bundler](/develop/tutorials/-/knowledge_base/7-1/configuring-liferay-npm-bundler)

[Understanding How liferay-npm-bundler Formats JavaScript Modules for AMD](/develop/tutorials/-/knowledge_base/7-1/understanding-how-liferay-npm-bundler-formats-javascript-modules-for-amd)
