# Overriding lpkg files [](id=overriding-lpkg-files)

Applications are delivered through Liferay Marketplace as *lpkg* files. This is
a simple compressed file format that contains .jar files for deploying to
@product@. If you want to examine an application from Marketplace, all you have
to do is unzip its .lpkg file to reveal its .jar files. 

After examining an application, you may want to [customize](/develop/tutorials/-/knowledge_base/7-1/customizing)
one of its .jars. Make your customization in a copy of the .jar, but don't
deploy it the way you'd normally deploy an application. By overriding the .lpkg
file, you can update application modules without modifying the original .lpgk
file. Here are the steps: 

1.  Shut down @product@. 

2.  Create a folder called `override` in the
    `[Liferay Home]/osgi/marketplace` folder](https://dev.liferay.com/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home). 

3.  Name your updated .jar the same as the .jar in the original .lpkg, minus the
    version information. For example, if you're overriding the
    `com.liferay.amazon.rankings.web-1.0.5.jar` from the `Liferay CE Amazon
    Rankings.lpkg`, you'd name your .jar `com.liferay.amazon.rankings.web.jar`. 

4.  Copy this .jar into the `override` folder you created in step one. 

This works for applications from Marketplace, but there's also the static .lpkg
that contains core Liferay technology and third-party utilities (such as the
servlet API, Apache utilities, etc.). To customize or patch any of these .jar
files, follow this process: 

1.  Make your customization and package it in a .jar file. 

2.  Name your .jar the same as the original .jar, minus the version information.
    For example, a customized `com.liferay.portal.profile-1.0.4.jar` should be
    `com.liferay.portal.profile.jar`. 

3.  Copy the .jar into the `[Liferay Home]/osgi/static` folder. 

Now start @product@. Note that any time you add and remove .jars this way,
@product@ must be shut down and then restarted for the changes to take effect. 

If you must roll back your customizations, delete the overriding .jar files:
@product@ uses the original .jar on its next startup. 
