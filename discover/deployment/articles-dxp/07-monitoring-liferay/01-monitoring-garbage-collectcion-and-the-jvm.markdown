# Monitoring Garbage Collection and the JVM [](id=monitoring-garbage-collection-and-jvm)

Although the 
[tuning parameters](/discover/deployment/-/knowledge_base/7-1/liferay-digital-enterprise-configuration-and-tuning-guidelines)
give you a good start to JVM tuning, you must monitor GC performance to ensure
you have the best settings to meet your needs. There are several tools to help
you monitor Oracle JVM performance. 

## Visual VM [](id=visual-vm)

This tool provides a centralized console for viewing Oracle JVM performance
information, including garbage collector activities.

![Figure 1: Visual VM shows the garbage collector in real-time.](../../images-dxp/visual-vm-gc.png)

## JMX Console [](id=jmx-console)

This tool helps display various statistics like @product@'s distributed cache
performance, application server thread performance, JDBC connection pool usage,
and more. 

+$$$

**Note:** The JMX Console is the preferred tool for monitoring Tomcat
performance.

$$$

To enable JMX connections, add the following configuration to your application
server's JVM arguments:

    -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote. port=5000
    -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun. management.jmxremote.ssl=false

![Figure 2: Visual VM lets you monitor using Java Management Extensions.](../../images-dxp/visual-vm-jmx.png)

## Garbage Collector Verbose Logging [](id=garbage-collector-verbose-logging)

Add the following configuration to your JVM arguments to activate verbose
logging for the JVM garbage collector.

    -verbose:gc -Xloggc:/tmp/liferaygc1.log -XX:+PrintGCDetails 
    -XX:+PrintGCCause -XX:+PrintGCApplicationConcurrentTime 
    -XX:+PrintGCApplicationStoppedTime

Examining these logs helps you tune the JVM properly. 

**Note:** Adding these JVM arguments generates a heap dump if an `OutOfMemoryError` occurs. The dump is written to the heap dump path specified. Specify the path to use:

    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/heap/dump/path/

Now you can monitor garbage collection and the JVM and tune them for top
performance. 
