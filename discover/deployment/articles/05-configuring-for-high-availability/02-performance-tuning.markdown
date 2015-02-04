# Performance Tuning [](id=performance-tuning)

Once you have your portal up and running, you may find a need to tune it for
performance, especially if your site winds up generating more traffic than you'd
anticipated. There are some definite steps you can take with regard to improving
Liferay's performance.

## Memory [](id=memory)

Memory is one of the first things to look at when you want to optimize
performance. If you have any disk swapping, you want to avoid it at all costs:
it has a serious impact on performance. Make sure your server has an optimal
amount of memory and your JVM is tuned to use it.

There are three basic JVM command switches that control the amount of memory in
the Java heap.

    -Xms
    -Xmx
    -XX:MaxPermSize

These three settings control the amount of memory available to the JVM
initially, the maximum amount of memory into which the JVM can grow, and the
separate area of the heap called Permanent Generation space.

The first two settings should be set to the same value. This prevents the JVM
from having to reallocate memory if the application needs more. Setting them to
the same value causes the JVM to be created up front with the maximum amount of
memory you want to give it.

    -Xms1024m -Xmx1024m -XX:MaxPermSize=256m

This is perfectly reasonable for a moderately sized machine or a developer
machine. These settings give the JVM 1024MB for its regular heap size and have a
PermGen space of 256MB. If you're having performance problems, and your profiler
is showing that there's a lot of garbage collection going on, the first thing
you might want to look at is increasing the memory available to the JVM. You'll
be able to tell if memory is a problem by running a profiler (such as Jprobe,
YourKit, or the NetBeans profiler) on the server. If you see Garbage Collection
(GC) running frequently, you definitely want to increase the amount of memory
available to the JVM.

Issues with PermGen space can also affect performance. PermGen space contains
Note that there is a law of diminishing returns on memory, especially with 64
bit systems. These systems allow you to create very large JVMs, but the larger
the JVM, the more time it takes for garbage collection to take place. For this
reason, you probably won't want to increase memory drastically. You'll have to
measure your portal's memory utilization and find a value that's large enough to
do real work besides garbage collection, but small enough so that garbage
collection is quick enough. If you have large amounts of memory on your server
*and* your portal is limited by memory, it might make sense to have multiple
JVMs with Liferay on a single server, rather than doubling the amount of memory
above a certain threshold. If your server is CPU- or I/O-limited, you have other
problems. 

long-lived classes, anonymous classes and interned Strings (immutable String
objects that are kept around for a long time to increase String processing
performance). Hibernate--which Liferay uses extensively--has been known to make
use of PermGen space. If you increase the amount of memory available to the JVM,
you may want to increase the amount of PermGen space accordingly.

## Garbage Collection [](id=garbage-collection)

As the system runs, various Java objects are created. Some of these objects are
long-lived, and some are not. The ones that are not become *de-referenced*,
which means that the JVM no longer has a link to them because they have ceased
to be useful. These may be variables that were used for methods which have
already returned their values, objects retrieved from the database for a user
that is no longer logged on, or a host of other things. These objects sit in
memory and fill up the heap space until the JVM decides it's time to clean them
up.

Normally, when garbage collection (GC) runs, it stops all processing in the JVM
while it goes through the heap looking for dead objects. Once it finds them, it
frees the memory they were taking up, and then processing can continue. If this
happens in a server environment, it can slow down the processing of requests, as
all processing comes to a halt while GC is happening.

There are some JVM switches that you can enable which can reduce the amount of
time processing is halted while garbage collecting happens. These can improve
the performance of your Liferay installation if applied properly. As always, you
will need to use a profiler to monitor garbage collection during a load test to
tune the numbers properly for your server hardware, operating system, and
application server.

![Figure 5.7: Java uses generational garbage collection. If an object survives enough garbage collection events, it's promoted to a new memory pool. For example, an object could be promoted from the young generation memory pool to the old generation memory pool or from the old generation memory pool to the permanent generation memory pool.](../../images/portal-admin-ch7_html_518957a7.png)

The Java heap is divided into sections for the young generation, the old
generation, and the permanent generation. The young generation is further
divided into three sections: Eden, which is where new objects are created, and
two “survivor spaces, which we can call the *From* and *To* spaces. Garbage
collection occurs in stages. Generally, it's more frequently done in the young
generation, less frequently done in the old generation, and even less frequently
done in the permanent generation, where long-lived objects reside. When garbage
collection runs in the young generation, Eden is swept for objects which are no
longer referenced. Those that are still around are moved to the *To* survivor
space, and the *From* space is then swept. Any other objects in that space which
still have references to them are moved to the *To* space, and the *From* space
is then cleared out altogether. After this, the *From* and the *To* spaces swap
roles, and processing is freed up again until the next time the JVM determines
that garbage collection needs to run.

After a predetermined number of *generations* of garbage collection, surviving
objects may be moved to the old generation. Similarly, after a predetermined
number of *generations* of garbage collection in the old generation, surviving
objects may be moved to the permanent generation.

By default, the JDK uses a serial garbage collector to achieve this. This works
very well for a short-lived desktop Java application, but is not necessarily the
best performer for a long-lived, server-based application like Liferay. For this
reason, you may wish to switch to the Concurrent Mark-Sweep (CMS) collector.

In CMS garbage collection, rather than halting application processing
altogether, this garbage collector makes one short pause in application
execution to mark objects directly reachable from the application code. Then it
allows the application to run while it marks all objects which are reachable
from the set it marked. Finally, it adds another phase called the *remark* phase
which finalizes marking by revisiting any objects modified while the application
was running. It then sweeps through and garbage collects. Though it sounds more
complicated, this has the effect of greatly reducing the amount of time that
execution needs to be halted to clean out dead objects.

Just about every aspect of the way memory management works in Java can be tuned.
In your profiling, you may want to experiment with some of the following
settings to see if any of them can increase your performance.

**NewSize, MaxNewSize:** The initial size and the maximum size of the New or
Young Generation.

**+UseParNewGC:** Causes garbage collection to happen in parallel, using
multiple CPUs. This decreases garbage collection overhead and increases
application throughput.

**+UseConcMarkSweepGC:** Use the Concurrent Mark-Sweep Garbage Collector. This
uses shorter garbage collection pauses, and is good for applications that have a
relatively large set of long-lived data, and that run on machines with two or
more processors, such as web servers.

**+CMSParallelRemarkEnabled:** For the CMS GC, enables the garbage collector to
use multiple threads during the CMS remark phase. This decreases the pauses
during this phase.

**SurvivorRatio:** Controls the size of the two survivor spaces. It's a ratio
between the survivor space size and Eden. The default is 25. There's not much
bang for the buck here, but it may need to be adjusted.

**ParallelGCThreads:** The number of threads to use for parallel garbage
collection. Should be equal to the number of CPU cores in your server.

A sample configuration using the above parameters might look something like
this:

    JAVA_OPTS="$JAVA_OPTS -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m
    -Xmx2048m -XX:MaxPermSize=128m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:SurvivorRatio=20
    -XX:ParallelGCThreads=8"

Again, you should always follow the procedure of adjusting the settings, then
testing under load, then adjusting again. Every system is different and these
are general guidelines to follow. Next, we'll see some modifications we can make
to Liferay's properties to help increase performance. 

## Properties File Changes [](id=properties-file-changes)

There are also some changes you can make to your `portal-ext.properties` file
once you are in a production environment.

Set this property to true to load the theme's merged CSS files for faster
loading for production. By default it is set to false for easier debugging for
development. You can also disable fast loading by setting the URL parameter
`css_fast_load` to `0`.

    theme.css.fast.load=true

Set this property to true to load the combined JavaScript files from the
property `javascript.files` into one compacted file for faster loading for
production. 

    javascript.fast.load=true
 
These are various things the Liferay engineering team has done to increase
performance generally. If your developers make use of Liferay's tools and
platform, their JavaScript and themes can also take advantage of these
properties. 

Let's look at one final, general way of increasing Liferay's performance:
disabling unused servlet filters. 

## Disabling Unused Servlet Filters [](id=disabling-unused-servlet-filters)

Liferay comes by default with a number of servlet filters enabled and running.
It is likely that for your installation, you don't need them all. Since servlet
filters intercept the HTTP request and do some processing on it before Liferay
even has a chance to start building the page, you can increase performance by
disabling the ones you're not using. 

You can disable servlet filters you're not using by using your
`portal-ext.properties` file. Copy the *Servlet Filters* section from the
original `portal.properties` file into your customized file, and then go through
the list and see if there are any you can disable, by setting them to `false`. 

For example, if you are not using CAS for single sign-on, disable the CAS
Filter. If you are not using NTLM for single sign-ons, disable the NTLM Filter.
The fewer servlet filters you are running, the less processing power is needed
for each request.

As you can see, there are many things you can do to increase Liferay's
performance generally. But don't forget to load test your own applications! It
may be that a performance issue comes from a custom-built application that's
doing something it shouldn't do. Always load test your system before putting it
into production: that's the best way of finding out potential performance
problems, and that way, you'll find them during performance testing, and not
when your system is in production. 

Next, you'll learn how to configure a Content Delivery Network, which can also
help with your portal's performance. 
