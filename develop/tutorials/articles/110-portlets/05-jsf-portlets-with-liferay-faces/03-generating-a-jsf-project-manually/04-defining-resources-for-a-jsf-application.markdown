# Defining Resources for a JSF Application [](id=defining-resources-for-a-jsf-application)

If you look back at the Hello User portlet's structure, you'll notice two
`resources` folders defined. Why are there two of these folders for one portlet?
These two folders have distinct differences in how they're used and what should
be placed in them.

The `resources` folder in the application's `src/main` folder is intended for
resources that need to be on the classpath. Files in this folder are usually 
properties files. For this portlet, you'll create two properties files to reside
in this folder.

1.  Create the `i18n.properties` file in the `src/main/resources` folder. Add
    the following property to this file:

        enter-your-name=Enter your name:

    This is a language key your JSF portlet displays in its view XHTML. The
    messages in the `i18n.properties` file can be accessed via the
    [Expression Language](http://docs.oracle.com/javaee/6/tutorial/doc/bnahq.html)
    using the implicit `i18n` object provided by the Liferay Faces Util class.
    The `i18n` object can access messages both from a resource bundle
    defined in the portlet's `portlet.xml` file, and from @product@'s
    `Language.properties` file.

2.  Create the `log4j.properties` file in the `src/main/resources` folder. This
    file sets properties for the Log4j logging utility defined in your JSF
    application (i.e., `faces-config.xml`). Insert the properties below into
    your JSF application's `log4j.properties` file.

        log4j.rootLogger=INFO, CONSOLE

        log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
        log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
        log4j.appender.CONSOLE.layout.ConversionPattern=%d{ABSOLUTE} %-5p [%c{1}:%L] %m%n

        log4j.logger.com.liferay.faces.util.lifecycle.DebugPhaseListener=DEBUG

The second `resources` folder in your JSF application is located in the
`src/main/webapp/WEB-INF` folder. This folder holds CSS/JS/XHTML resources that
shouldn't be accessed directly by the browser. For the Hello User JSF
application, create a `css` folder with a `main.css` file inside. In the
`main.css` file, add the following style:

    .com.liferay.hello.user.jsf.portlet {
        font-weight: bold;
    }

This file gives your JSF portlet a bold font.

Now that your resources are defined, you'll begin developing the Hello User
application's behavior and UI next.