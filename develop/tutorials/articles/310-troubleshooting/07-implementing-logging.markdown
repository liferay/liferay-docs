# Implementing Logging [](id=implementing-logging)

@product-ver@ uses the Log4j logging framework, but a different one may
eventually replace it. It's a best practice to use [Simple Logging Facade for
Java \(SLF4J\)](https://www.slf4j.org/) to log messages in your modules and
traditional plugins. SLF4J is already integrated into @product@, so you can
focus on logging messages. 

Here's how to use SLF4J to log messages in a class:

1.  Add a private static SLF4J
    [`Logger` field](https://www.slf4j.org/apidocs/org/slf4j/Logger.html). 

        private static Logger _logger;

2.  Instantiate the logger. 

        _logger = LoggerFactory.getLogger(this.getClass().getName());
 
3.  Throughout your class, log messages where noteworthy things happen. 

    For example, 

        _logger.debug("...");
        _logger.warn("...");
        _logger.error("...");
        ...

    Use `Logger` methods appropriate for each message:
 
    -  `debug`: Event and application information helpful for debugging.
    -  `error`: Normal errors. This is the least verbose message level.
    -  `info`: High level events.
    -  `trace`: Provides more information than debug. This is the most verbose
       message level. 
    -  `warn`: Information that might, but does not necessarily, indicate a
       problem.
 
Log verbosity should correlate with the log level set for the class or package.
Make sure you provide additional information at log levels expected to be more
verbose, such as `info` and `debug`.

You're all set to add logging to your modules and traditional plugins. 
