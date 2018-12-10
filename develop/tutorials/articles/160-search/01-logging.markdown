# Elasticsearch Logging [](id=elasticsearch-logging)

When you first start @product@, an embedded Elasticsearch server starts so that
search works out of the box. The embedded search engine is not suitable for
production, but is useful for testing and development.

In Fix Pack 3 and CE GA2, the Liferay log was slimmed down by removing INFO
level Elasticsearch logs for the embedded Elasticsearch. WARN and ERROR logs are
still displayed. If you miss hearing from Elasticsearch during startup, you can
enable the INFO log level for the embedded Elasticsearch server.

+$$$

**Note:** These instructions show you how to adjust the embedded Elasticsearch
server's logs. Logging for @product@'s search functionality is configurable via
the 
[Log Levels](/discover/portal/-/knowledge_base/7-1/server-administration) 
screen at Control Panel &rarr; Configuration &rarr; Server Administration &rarr;
Log Levels tool. Narrow down the list to include only the search classes and
packages by searching for _com.liferay.portal.search_.

To adjust logging for a remote Elasticsearch server, see 
[Elastic's documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/logging.html).

$$$

Here's an example log message that _is_ displayed by default:

    2018-09-13 16:49:24.442 WARN  [Elasticsearch initialization thread][EmbeddedElasticsearchConnection:315]

Manage the log levels for the `EmbeddedElasticsearchConnection` in Server
Administration.

Here's an example Elasticsearch log message that _isn't_ displayed by default:

    [2018-09-05T17:25:30,107][WARN ][o.e.d.i.m.MapperService  ] [unmapped_type:string] should be replaced with [unmapped_type:keyword]

To adjust logging for the `o.e.d.i.m.MapperService` and other Elasticsearch classes, 

1.  Create a `config` folder in `Liferay Home/data/elasticsearch6/`.

2.  Create a `log4j2.properties` file in the new folder.

3.  To enable INFO level logging, populate the `log4j2.properties` file with
    these contents: 

        appender.console.layout.pattern=[%d{ISO8601}][%-5p][%-25c{1.}] %marker%m%n
        appender.console.layout.type=PatternLayout
        appender.console.name=console
        appender.console.type=Console
        logger.action.level=info
        logger.action.name=org.elasticsearch.action
        rootLogger.appenderRef.console.ref=console
        rootLogger.level=info
        status=error

    Read the Elasticsearch 
    [logging documentation](https://www.elastic.co/guide/en/elasticsearch/reference/6.x/logging.html) 
    for more information.

4.  Restart the @product@ server or just the embedded Elasticsearch.

5.  To restart just the embedded Elasticsearch server, create a file called

        com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config 

    in `Liferay Home/osgi/configs` directory and populate it with

        blacklistBundleSymbolicNames="com.liferay.portal.search.elasticsearch6.impl"

6.  Save the `.config` file and the bundle is stopped. Once you see the
    `[STOPPED]` message in the logs, restart the bundle by commenting the line
    out and re-saving the file:

        #blacklistBundleSymbolicNames="com.liferay.portal.search.elasticsearch6.impl"

Once this is accomplished, the embedded Elasticsearch server displays
Elasticsearch logs at the INFO level and above.

Two common Elasticsearch logs can be configured further: the Slow Log and the
JVM's Garbage Collection log.

## Configuring Slow Log [](id=configuring-slow-log)

Read about Elasticsearch's Slow Log
[here](https://www.elastic.co/guide/en/elasticsearch/reference/6.1/index-modules-slowlog.html). 

Configure the Slow Log for the embedded Elasticsearch server in the
Elasticsearch 6 entry in System Settings. Add these settings to the Additional
Index Configurations property:

    index.indexing.slowlog.threshold.index.debug: 2s
    index.indexing.slowlog.threshold.index.info: 5s
    index.indexing.slowlog.threshold.index.trace: 500ms
    index.indexing.slowlog.threshold.index.warn: 10s

    index.search.slowlog.threshold.fetch.debug: 500ms
    index.search.slowlog.threshold.fetch.info: 800ms
    index.search.slowlog.threshold.fetch.trace: 200ms
    index.search.slowlog.threshold.fetch.warn: 1s

    index.search.slowlog.threshold.query.debug: 2s
    index.search.slowlog.threshold.query.info: 5s
    index.search.slowlog.threshold.query.trace: 500ms
    index.search.slowlog.threshold.query.warn: 10s

These are example values. Adjust as needed.

## Configuring JVM Garbage Collection Logging [](id=configuring-jvm-garbage-collection-logging)

As with the Slow Log configuration, Elasticsearch's JVM Garbage Collection
logging is adjustable in the Elasticsearch 6 entry in System Settings. Add these
settings to the Additional Index Configurations property:

    monitor.jvm.gc.enabled: true
    monitor.jvm.gc.overhead.debug: 40
    monitor.jvm.gc.overhead.info: 70
    monitor.jvm.gc.overhead.warn: 90

These are example values. Adjust as needed.

