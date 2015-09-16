# Using Third Party Libraries in Your JavaScript [](id=using-third-party-libraries-in-your-javascript)

In addition to being able to use the new es2015 JavaScript syntax in your code, 
you can now also use your favorite third party libraries with [Bower](http://bower.io/). 

This tutorial covers how to properly configure your files and directory 
structure, so you can use third party libraries in your JavaScript.

## Installing Your Third Party Component [](id=installing-your-third-party-component)

Before you can use your third party components in your JavaScript, you will need
to install them. 

1. Install your Bower component as you normally would if it's not already 
   installed. 

    With your component installed, you'll need to add the `bower.json` file as a
    resource to your `bnd.bnd` file.

2. Create a `bnd.bnd` file in the root folder of your bundle if it does not 
   already exist, and add `bower.json` and `classes` as resources. As an
   example, here is the Sequences Explorer Portlet's `bnd.bnd` file:

        Bundle-Name: Sequences Explorer Web
        Bundle-SymbolicName: com.liferay.sequences.explorer.web
        Bundle-Version: 1.0.0
        Include-Resource:\
	    bower.json,\
	    classes

These resources will be read during the build process to tell where the bundle 
files are located.    
	    
Once your component has been installed, you'll need to add the downloaded 
library files to your bundle in order to use them. You can do this as part of 
the build process, by adding it to your `build.gradle` file. You'll learn how to 
set this up next.

## Configuring Your build.gradle file [](id=configuring-your-build-gradle-file)

Now that your third party library has been installed, you'll need to configure
your `build.gradle` file to use some Bower specific tasks. For reference, below 
is the `build.gradle` file for Sequences Explorer Portlet:

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

At first glance, the `build.gradle` file can be a little overwhelming, so it's
broken down for you in more detail below:
        
    task downloadBower(type: DownloadNodeModuleTask)
    task bowerInstall(type: ExecuteNodeTask)
    task dragulaInstall(type: Copy)
    
This sets the main tasks to run for the build process. Of course you would
replace the third task with one relative to your component.

    downloadBower {
	moduleName = "bower"
	moduleVersion = bowerVersion
    }

This downloads the Bower node module that is needed for the remaining tasks.

    bowerInstall {
	args = [new File(node.nodeDir, "node_modules/bower/bin/bower"), 
	"install", "--allow-root"]
	dependsOn downloadBower
    }
    
This runs the `bower install` command, which will download the components listed 
in the dependencies attribute inside the `bower.json` file to the 
`bower_components` folder of the module. You'll configure your `bower.json` file
in the next section.

    dragulaInstall {
	dependsOn bowerInstall

	from new File("bower_components/dragula.js/dist/dragula.min.js")
	into file("classes/META-INF/resources")
    }

This copies the library files to the `classes/META-INF/resources` so it gets 
bundled in the application. Of course you would update this to match your
component's file name and path.

    configJSModules {
	dependsOn dragulaInstall
	include "**/*.js"
	sourceDir "classes/META-INF/resources"
    }

The last task runs the module generator configuration program, and specifies 
where the files are located for the AMD loader.

Now that you have your `build.gradle` file configured and you have a better
understanding of how it works, you can setup your `bower.json` file next.

## Configuring your bower.json [](id=configuring-your-bower-json)

Bower installs packages using a `bower.json` file, so to use third party modules 
in your code you'll need to save the module as a dependency to the `bower.json` 
file for the bundle. The Sequences Explorer Portlet uses [Dragula.js](http://bevacqua.github.io/dragula/) 
to allow users to drag and drop table entries. The dragula.js module is included 
as as dependency in the `bower.json` for the portlet:

    {
        "name": "sequences-explorer",
        "version": "1.0.0",
        "dependencies": {
            "dragula.js": "~2.1.2"
        }
    }

Once you've saved it as a dependency, you can import it as you would any other 
module. The `printer.es` module of the Sequences Explorer Portlet imports 
Dragula with the following code:

    import dragula from '../dragula.min';
    
With the module imported, you can now use it in your JavaScript. The 
`printer.es` module applies Dragula to the `<tbody>` element of the table:

    dragula([this.container.querySelector('tbody')]);

As you can see, you can use your favorite third party libraries in no time. This
shows the true expansive power that Liferay 7 offers you as a developer.

## Related Topics [](id=related-topics)

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Creating a Portlet Application](/develop/tutorials/-/knowledge_base/7-0/creating-a-portlet-application)
