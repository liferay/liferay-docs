# Adjusting Module Logging [](id=adjusting-module-logging)

@product@ uses [Log4j](http://logging.apache.org/log4j/1.2/) logging
services. You can configure logs for any module. Here's how: 

1.  Create the folder `[Liferay_Home]/osgi/log4j` if it's not there. 

2.  In the `log4j` folder, create a configuration file that follows this naming
    convention:

        [Bundle-SymbolicName]-log4j-ext.xml
 
3.  In the configuration file, specify the module's Log4j configuration.
    Here's an example configuration:

        <?xml version="1.0"?>
        <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">
        <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
            <category name="org.foo.Bar">
                <priority value="DEBUG" />
            </category>
        </log4j:configuration>

    The remaining steps explain how to configure Log4j `category` elements.
 
4.  Specify `category` elements for each package or class to log messages. Set
    the `name` attribute to that package or class name. The example category
    sets logging for class `org.foo.Bar`.

5.  Set each category's `priority` element to the log
    [level](http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/Level.html) 
    (priority) you want.

    -   ALL
    -   DEBUG
    -   ERROR
    -   FATAL
    -   INFO
    -   OFF
    -   TRACE
    -   WARN

The log messages are printed to Liferay log files in the `[Liferay_Home]/logs` folder.

+$$$

Note: If the log level configuration isn't appearing (e.g., you set the log
level to `ERROR` but you’re still getting `WARN` messages), make sure the log
configuration file name prefix matches the module's symbolic name. If you have
Bnd installed, output from command `bnd print [path-to-bundle]` includes the
module’s symbolic name ([Here](https://github.com/bndtools/bnd/wiki/Install-bnd-on-the-command-line)
are instructions for installing Bnd for the command line).

$$$

That's it for module log configuration. You're all set to print the information
you want.
