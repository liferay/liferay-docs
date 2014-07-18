# Configuring Your Permissions Scheme

Liferay's permissions system is composed of several components. The main
component is a configuration file that really could have any name, but by
convention is called `default.xml` in plugin applications. This configuration
file is the heart of your permissions scheme, as it defines the permissions you
want inside your application. All the permissions checks that go into the code
derive their permission definitions from this file. Think of it as the Borg
queen that directs the rest of the drones to do its bidding. 

![Figure 1: The permissions configuration file is at the heart of Liferay's permissions system.](../../images/liferay-permissions.png)

To implement permissions, you perform three straightforward steps: 

1. You implement your permissions scheme in `default.xml` and tell your
application where it's configured. 

2. Optionally but as a best practice, you implement helper classes to check for
permissions. 

3. You wrap user interface elements in permission checks, so that they only
appear when a particular user is authorized to use them. 

There is another step you'll implement later: when you use Service Builder to
generate remote SOAP and JSON-based interfaces, you'll need to add permission
checks to them, as the UI is no longer a factor. But we'll get to that later. 

The next step is to define your permissions. 

## Defining Permissions for Your Application

The first thing you need to do to begin defining your permissions is point your
application at the configuration file you'll use to define those permissions. 

1. In your application's `src` folder, create a file called
   `portlet.properties`. This file lets you define properties for your application
   in key/value pairs. 

2. In this file, place the following property: 

        resource.actions.configs=resource-actions/default.xml

This property defines the name and location of your permissions definition file.
You can, of course, call it anything you want. By convention, Liferay calls this
file and places it in a folder called `resource-actions` inside the `src` folder
of the plugin project, so by doing it this way, you're just following the
Liferay convention. 

Next, you'll create the file itself: 

1.  Create a folder called `resource-actions` in your `src` folder. 

2.  Create a new file in this folder called `default.xml`. 

3.  Add the following DOCTYPE declaration to the top of the file: 

        <?xml version="1.0"?>
        <!DOCTYPE resource-action-mapping PUBLIC "-//Liferay//DTD Resource Action Mapping 6.2.0//EN"
    	"http://www.liferay.com/dtd/liferay-resource-action- mapping_6_2_0.dtd">

If you have a copy of Liferay's source code, you'll find the DTD for this file
in the `defnitions` folder. The DTD is fully documented and explains every tag
you can place in this file. If you want a complete reference for every option,
please look at the DTD. Here, you'll create a configuration that works for the
guestbook application. Before you place anything in the file, however, you
should think about 

