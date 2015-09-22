# Using Third Party Libraries in Your JavaScript [](id=using-third-party-libraries-in-your-javascript)

In addition to being able to use the new ES2015 JavaScript syntax in your code,
you can now use your favorite third party libraries with the help of the
[Bower](http://bower.io/) package manager.

This tutorial walks you through installing components, configuring your bundle's
component dependencies, and using components. Code from the
[Sequences Explorer Portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/liferay-plugins-sdk-7.0.0/apps/sequences-explorer-web)
is used as in the examples. 

## Installing a Third Party Component [](id=installing-a-third-party-component)

Before you can use your third party components in your JavaScript, you must

1.  Using Bowser, install your component package. 

2.  Create a `bnd.bnd` file, if you haven't already, in your bundle's root
    folder. Include your bundle's `bower.json` file and classes folder as
    resources. As an example, here are the contents of the Sequences Explorer
    Portlet's `bnd.bnd` file: 

        Bundle-Name: Sequences Explorer Web
        Bundle-SymbolicName: com.liferay.sequences.explorer.web
        Bundle-Version: 1.0.0
        Include-Resource:\
            bower.json,\
            classes

Next, you must implement in your `build.gradle` file the means to add the
downloaded component library files to your bundle. 

## Configuring Your build.gradle File [](id=configuring-your-build-gradle-file)

You can implement component installation and configuration using Bower specific
tasks in a `build.gradle` file. For reference, here are contents from the
Sequences Explorer Portlet's `build.gradle` file: 

        import com.liferay.gradle.plugins.node.tasks.DownloadNodeModuleTask
        import com.liferay.gradle.plugins.node.tasks.ExecuteNodeTask
        
        dependencies {
                compile group: "org.osgi", name: "org.osgi.core", version: 
                "5.0.0"
        }
        
        ext {
                bowerVersion = "1.5.2"
        }
        
        liferay {
                deployDir = file("${liferayHome}/osgi/modules")
        }
        
        task downloadBower(type: DownloadNodeModuleTask)
        task bowerInstall(type: ExecuteNodeTask)
        task dragulaInstall(type: Copy)
        
        bowerInstall {
                args = [new File(node.nodeDir, "node_modules/bower/bin/bower"), 
                "install", "--allow-root"]
                dependsOn downloadBower
        }
        
        configJSModules {
                dependsOn dragulaInstall
                include "**/*.js"
                sourceDir "classes/META-INF/resources"
        }
        
        downloadBower {
                moduleName = "bower"
                moduleVersion = bowerVersion
        }
        
        dragulaInstall {
                dependsOn bowerInstall
        
                from new File("bower_components/dragula.js/dist/dragula.min.js")
                into file("classes/META-INF/resources")
        }

First consider the task declarations. 
        
    task downloadBower(type: DownloadNodeModuleTask)
    task bowerInstall(type: ExecuteNodeTask)
    task dragulaInstall(type: Copy)
    
They declare the build process's main tasks. The `dragulaInstall` task installs
the Dragula library to the bundle. You can declare a similar task to install a
different library. 

    downloadBower {
	moduleName = "bower"
	moduleVersion = bowerVersion
    }

The `downloadBower` task downloads the Bower node module used by the other
tasks. 

    bowerInstall {
	args = [new File(node.nodeDir, "node_modules/bower/bin/bower"), 
	"install", "--allow-root"]
	dependsOn downloadBower
    }

The `bowerInstall` task downloads and installs to the module's
`bower_components` folder any dependency components specified by the bundle's
`bower.json` file. The next section explains how to configure dependencies. 

    dragulaInstall {
	dependsOn bowerInstall

	from new File("bower_components/dragula.js/dist/dragula.min.js")
	into file("classes/META-INF/resources")
    }

This `dragulaInstall` task copies the Dragula library files to the
`classes/META-INF/resources`, so they're bundled with the application. You can
create a similar task to copy a library into your bundle. 

    configJSModules {
	dependsOn dragulaInstall
	include "**/*.js"
	sourceDir "classes/META-INF/resources"
    }

The `configJSModules` task runs the module generator configuration program and
specifies to the AMD Loader the files to include. 

Now that you've configured your `build.gradle` file to install and configure
third party library modules, you can setup a `bower.json` file. 

## Configuring Component Dependencies in a bower.json File [](id=configuring-component-dependencies-in-a-bower-json-file)

Bower installs packages based on a `bower.json` metadata file. To use a third
party library module in your bundle, you must specify it as a dependency in a
`bower.json` file. Since the Sequences Explorer Portlet uses Dragula to allow
users to drag and drop table entries, its `bower.json` file specifies a
depencency on the [Dragula.js](http://bevacqua.github.io/dragula/) module: 

    {
        "name": "sequences-explorer",
        "version": "1.0.0",
        "dependencies": {
            "dragula.js": "~2.1.2"
        }
    }

You've specified the dependencies and implemented tasks to download and install
the third party library modules. You can now import and use them in your
JavaScript files. The Sequences Explorer Portlet imports Dragula like this: 

    import dragula from '../dragula.min';
    
The `printer.es` module applies Dragula to the `<tbody>` element of the table:

    dragula([this.container.querySelector('tbody')]);

In no time, you can use third party libraries in your module's JavaScript. 

## Related Topics [](id=related-topics)

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)
