# Creating Plugins to extend Plugins [](id=creating-plugins-to-extend-plugins)

For Liferay plugins, you can create a new plugin that extends an existing one.
By extending a plugin, you can use all its features in your new plugin while
keeping your changes/extensions separate from the existing plugin's source code. 

To create a plugin which extends another, follow these steps: 

1.  Create a new empty plugin in the Plugins SDK. 

2.  Remove all the auto-generated files except `build.xml` and the docroot
    folder, which should be empty. 

3.  Copy the original WAR file of the plugin you'd like to extend (for example,
    `social-networking-portlet-6.1.10.1-ee-ga1.war`) to the root folder of your
    new plugin. 

4.  Add the following line to your `build.xml` inside of the `<project>` tag to
    reference the original WAR file you are going to extend:

        <property
            name="original.war.file"
            value="social-networking-portlet-6.1.10.1-ee-ga1.war"
        />

5.  Copy any files from the original plugin that you're overwriting to your
    new plugin (using the same folder structure) and run the Ant target `merge`.
    Please note that the `merge` target is called whenever the plugin is
    compiled. All you have to do is to check the Ant output:

        dsanz@host:~/sdk/portlets/my-social-networking-portlet$ ant war
        Buildfile: /home/dsanz/sdk/portlets/my-social-networking-portlet/build.xml
        
        compile:
        
        merge:
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/classes 
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/lib 
        
        merge-unzip:
        [unzip] Expanding:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/social-networking-portlet-
        6.1.10.1-ee-ga1.war into /home/dsanz/sdk/portlets/my-social-networking-portlet/t
        mp 
        [copy] Copying 2 files to
        /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp 
        [mkdir] Created dir:
        /home/dsanz/sdk/portlets/my-social-networking-portlet/docroot/WEB-INF/classes 
        
        ...

6.  If the plugin that you're extending contains a service, you need to
    overwrite the `ClpSerializer.java` file. The Service Builder-generated
    `ClpSerializer.java` file contains a hard-coded project for
    `_servletContextName`. You need to change this to the name of your plugin.

This generates a plugin (you can find the WAR file in the `/dist` folder of your
plugins SDK) which combines the original one with your changes. 

In summary, if localization is important for your portlets, always consider
statements in a *localization plan*, since some portlets in your plugin and hard
customer requests can make a mess in your localization files and keys. If
possible, reuse Liferay core language keys since they're already translated for
you. 

If there's no key you can use, you can create your own, as described in this
chapter. Liferay gives you the tools to make localization possible, and uses a
web service to provide rudimentary translations. 
