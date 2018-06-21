# Java Virtual Machine Tuning [](id=java-virtual-machine-tuning)

Tuning the JVM primarily focuses on tuning the garbage collector and the Java
memory heap. These parameters are used to optimize the throughput of your
application. We used Oracle's 1.8 JVM for the reference architecture. You may
also choose other supported JVM versions and implementations. Please consult the
[Liferay Digital Enterprise Compatibility Matrix](https://web.liferay.com/group/customer/dxp/support/compatibility-matrix)
for additional compatible JVMs.

## Garbage Collector [](id=garbage-collector)

Choosing the appropriate garbage collector (GC) helps improve the responsiveness
of your @product@ deployment. Liferay recommends using the concurrent low pause
collectors:

    -XX:+UseParNewGC -XX:ParallelGCThreads=16 -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:+CMSCompactWhenClearAllSoftRefs
    -XX:CMSInitiatingOccupancyFraction=85 -XX:+CMSScavengeBeforeRemark
 
You may choose from other available GC algorithms including parallel throughput
collectors and G1 collectors. Liferay recommends first starting your tuning
using parallel collectors in the new generation and concurrent mark sweep (CMS)
in the old generation.

**Note:** the value 16 in `ParallelGCThreads=16` varies based on the type of
CPUs available. We recommend setting the value according to CPU specification.
On Linux machines, you may find the number of available CPUs by running
`cat /proc/cpuinfo`.

**Note:** There are additional "new" algorithms like G1, but Liferay
Engineering's tests for G1 have indicated that it does not improve performance.
Your application performance may vary and you should add it to your testing and
tuning plans.

## Code Cache [](id=code-cache)

Java uses a just-in-time (JIT) compiler that generates native code to improve
performance. The default size is 48M. This may not be sufficient for larger
applications. Too small a code cache reduces performance as the JIT isn't able
to optimize high frequency methods. For Digital Enterprise, we recommend
starting with 64M for the initial code cache size.

    -XX:InitialCodeCacheSize=32m -XX:ReservedCodeCacheSize=96m
 
You can examine the efficacy of the parameter changes by adding the following
parameters:

    -XX:+PrintCodeCache -XX:+PrintCodeCacheOnCompilation

## Java Heap [](id=java-heap)

When most people think about tuning the Java memory heap, they think of setting
the maximum and minimum memory of the heap. Unfortunately, most deployments
require far more sophisticated heap tuning to obtain optimal performance,
including tuning the young generation size, tenuring durations, survivor spaces
and many other JVM internals.

For most systems, Liferay recommends starting with at least the following memory
settings:

    -server -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m -Xmx2048m -XX:MetaspaceSize=300m
    -XX:MaxMetaspaceSize=300m -XX:SurvivorRatio=6 -XX:TargetSurvivorRatio=9 -XX:MaxTenuringThreshold=15

On systems that require large heap sizes (e.g., above 4GB), it may be beneficial
to use large page sizes. You may activate large page sizes using the following
JVM options:

    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m
 
You may choose to specify different page sizes based on your application
profile.

**Note:** To use large pages in the JVM, you must configure your underlying
operation system to activate them. In Linux, run `cat /proc/meminfo` and look
at "huge page" items. 

+$$$

**Caution:** Avoid allocating more than 32GB to your JVM heap. Your heap size
should be commensurate with the speed and quantity of available CPU resources.

$$$

## JVM Advanced Options [](id=jvm-advanced-options)

The following advanced JVM options were also applied in the Liferay benchmark
environment:

    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m 
    -XX:+UseCompressedOops -XX:+DisableExplicitGC -XX:-UseBiasedLocking 
    -XX:+BindGCTaskThreadsToCPUs -XX:UseFastAccessorMethods

Please consult your JVM documentation for additional details on advanced JVM
options.

Combining the above recommendations together, we have this configuration:

    -server -XX:NewSize=1024m -XX:MaxNewSize=1024m -Xms4096m
    -Xmx4096m -XX:MetaspaceSize=300m -XX:MaxMetaspaceSize=300m
    -XX:SurvivorRatio=12 -XX:TargetSurvivorRatio=90
    -XX:MaxTenuringThreshold=15 -XX:+UseLargePages 
    -XX:LargePageSizeInBytes=256m -XX:+UseParNewGC 
    -XX:ParallelGCThreads=16 -XX:+UseConcMarkSweepGC 
    -XX:+CMSParallelRemarkEnabled -XX:+CMSCompactWhenClearAllSoftRefs
    -XX:CMSInitiatingOccupancyFraction=85 -XX:+CMSScavengeBeforeRemark 
    -XX:+UseLargePages -XX:LargePageSizeInBytes=256m
    -XX:+UseCompressedOops -XX:+DisableExplicitGC -XX:-UseBiasedLocking
    -XX:+BindGCTaskThreadsToCPUs -XX:+UseFastAccessorMethods
    -XX:InitialCodeCacheSize=32m -XX:ReservedCodeCacheSize=96m
 
+$$$

**Caution:** The above JVM settings should formulate a starting point for your
performance tuning. Every system's final parameters vary due to many factors
including number of current users and transaction speed.

$$$

Liferay recommends monitoring the garbage collector statistics to ensure your
environment has sufficient allocations for metaspace and also for the survivor
spaces. Simply using the guideline numbers above may result in dangerous
runtime scenarios like out of memory failures. Improperly tuned survivor spaces
also lead to wasted heap space.
