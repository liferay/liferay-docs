---
header-id: monitoring-garbage-collection-and-the-jvm
---

# Monitoring Garbage Collection and the JVM

[TOC levels=1-4]

Although the 
[tuning parameters](/docs/7-2/deploy/-/knowledge_base/d/tuning-guidelines)
give you a good start to JVM tuning, you must monitor GC performance to ensure
you have the best settings to meet your needs. There are several tools to help
you monitor Oracle JVM performance. 

## VisualVM

[VisualVM](https://visualvm.github.io/)
provides a centralized console for viewing Oracle JVM performance information
and its Visual GC plugin shows garbage collector activities.

![Figure 1: VisualVM's Visual GC plugin shows the garbage collector in real-time.](../../images-dxp/visual-vm-gc.png)

| **Note:** Oracle's JDK has VisualVM bundled (`$JAVA_HOME/bin/jvisualvm`).
| However, always download and use the latest version from VisualVM's
| [official website](https://visualvm.github.io/).

## JMX Console

This tool helps display various statistics like @product@'s distributed cache
performance, application server thread performance, JDBC connection pool usage,
and more. 

| **Note:** The JMX Console is the preferred tool for monitoring application 
| server performance.

To enable JMX connections, add these JVM arguments:

```bash
-Dcom.sun.management.jmxremote=true
-Dcom.sun.management.jmxremote.port=5000
-Dcom.sun.management.jmxremote.authenticate=false
-Dcom.sun.management.jmxremote.ssl=false
```

If you're running JMX Console from a another machine, add these JVM arguments too:

```bash
-Dcom.sun.management.jmxremote.local.only=false
-Dcom.sun.management.jmxremote.rmi.port=5000
-Djava.rmi.server.hostname=[place IP address here]
```

![Figure 2: VisualVM monitors the JVM using Java Management Extensions.](../../images-dxp/visual-vm-jmx.png)

## Garbage Collector Verbose Logging

Add these JVM arguments to activate verbose logging for the JVM garbage
collector.

```bash
-verbose:gc -Xloggc:/tmp/liferaygc1.log -XX:+PrintGCDetails 
-XX:+PrintGCCause -XX:+PrintGCApplicationConcurrentTime 
-XX:+PrintGCApplicationStoppedTime
```

Examining these logs helps you tune the JVM properly. 

| **Note:** Adding these JVM arguments generates a heap dump if an
| `OutOfMemoryError` occurs. The dump is written to the heap dump path 
| specified. Specify the path to use:
| 
| `-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/heap/dump/path/`

Garbage collector log files can grow huge. You can use additional arguments like
the following ones to rotate the log to a new log file when the current log
file reaches a maximum size: 

```bash
-XX:+PrintGCDateStamps -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 
-XX:GCLogFileSize=50M
```

These arguments rotate the logs to up to `10` log files with a maximum size of
`50M` each. 

Now you can monitor garbage collection in the JVM and tune it for top
performance. 
