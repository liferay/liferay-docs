#Monitoring GC and JVM

Although the previously introduced parameters give you a good start to tuning your JVM, you must monitor GC performance to ensure you have the best settings to meet your needs. There are several tools to help you monitor Oracle JVM performance including.

##Visual VM

This tool provides a centralized console for viewing Oracle JVM performance information, including garbage collector activities.

![Visual VM GC](../../images-dxp/visual-vm-gc.png)

##JMX Console 
This tool helps display various statistics like Liferay's distributed cache performance, the performance of application server threads, JDBC connection pool usage, etc.

**Note:** The JMX Console is the preferred tool to use when observing Tomcat performance information.

Add the following to your application server's JVM arguments to enable JMX connections:

    -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote. port=5000
    -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun. management.jmxremote.ssl=false

![Visual VM JMX](../../images-dxp/visual-vm-jmx.png)

##Garbage Collector Verbose Logging

Add the following to your JVM arguments to activate verbose logging for the JVM garbage collector.

    -verbose:gc -Xloggc:/tmp/liferaygc1.log -XX:+PrintGCDetails 
    -XX:+PrintGCCause -XX:+PrintGCApplicationConcurrentTime 
    -XX:+PrintGCApplicationStoppedTime

You will need these logs to properly tune the JVM.

**Note:** To ensure you do have sufficient debugging information should your JVM encounter out of memory scenarios, you should consider adding:

    -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp/dumps
