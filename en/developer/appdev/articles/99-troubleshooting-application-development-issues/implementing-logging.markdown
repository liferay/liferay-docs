---
header-id: implementing-logging
---

# Implementing Logging

[TOC levels=1-4]

@product-ver@ uses the Log4j logging framework, but it may be replaced in the
future. It's a best practice to use [Simple Logging Facade for Java
\(SLF4J\)](https://www.slf4j.org/) to log messages in your modules and
traditional plugins. SLF4J is already integrated into @product@, so you can
focus on logging messages. 

Here's how to use SLF4J to log messages in a class:

1.  Add a private static SLF4J
    [`Logger` field](https://www.slf4j.org/apidocs/org/slf4j/Logger.html). 

    ```java
    private static Logger _logger;
    ```

2.  Instantiate the logger. 

    ```java
    _logger = LoggerFactory.getLogger(this.getClass().getName());
    ```
 
3.  Throughout your class, log messages where noteworthy things happen. 

    For example, 

    ```java
    _logger.debug("...");
    _logger.warn("...");
    _logger.error("...");
    ...
    ```

    Use `Logger` methods appropriate for each message:
 
    -  `trace`: Provides more information than debug. This is the most verbose
       message level. 
    -  `debug`: Event and application information helpful for debugging.
    -  `info`: High level events.
    -  `warn`: Information that might, but does not necessarily, indicate a
       problem.
    -  `error`: Normal errors. This is the least verbose message level.

Log verbosity should correlate with the log level set for the class or package.
Make sure you provide additional information at log levels expected to be more
verbose, such as `info` and `debug`.

You're all set to add logging to your modules and traditional plugins. 

## Related Topics

[Adjusting Module Logging](/docs/7-2/appdev/-/knowledge_base/a/adjusting-module-logging)
