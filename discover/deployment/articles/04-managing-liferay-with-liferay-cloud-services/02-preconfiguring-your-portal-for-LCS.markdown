# Preconfiguring Your Portal for LCS [](id=preconfiguring-your-portal-for-lcs)

If you're running Liferay 6.2, then you can skip this step. If, however, you are 
running Liferay 6.1 GA3, then there's a small pre-configuration step that you 
need to take before using LCS: you need to update the `spring.configs` property 
in your `portal-ext.properties` file. This is a workaround for a bug in Liferay 
6.1 GA3, where the values for this property are listed in the wrong order. This 
causes the metrics service in LCS to not be initialized. To fix this, add the 
following configuration to your `portal-ext.properties` file:

    spring.configs=\
        META-INF/base-spring.xml,\
        \
        META-INF/hibernate-spring.xml,\
        META-INF/infrastructure-spring.xml,\
        META-INF/management-spring.xml,\
        \
        META-INF/util-spring.xml,\
        \
        META-INF/jpa-spring.xml,\
        \
        META-INF/executor-spring.xml,\
        \
        META-INF/audit-spring.xml,\
        META-INF/cluster-spring.xml,\
        META-INF/editor-spring.xml,\
        META-INF/jcr-spring.xml,\
        META-INF/ldap-spring.xml,\
        META-INF/messaging-core-spring.xml,\
        META-INF/messaging-misc-spring.xml,\
        META-INF/mobile-device-spring.xml,\
        META-INF/notifications-spring.xml,\
        META-INF/poller-spring.xml,\
        META-INF/rules-spring.xml,\
        META-INF/scheduler-spring.xml,\
        META-INF/scripting-spring.xml,\
        META-INF/search-spring.xml,\
        META-INF/workflow-spring.xml,\
        \
        META-INF/counter-spring.xml,\
        META-INF/mail-spring.xml,\
        META-INF/portal-spring.xml,\
        META-INF/portlet-container-spring.xml,\
        META-INF/staging-spring.xml,\
        META-INF/virtual-layouts-spring.xml,\
        \
        META-INF/monitoring-spring.xml,\
        \
        #META-INF/dynamic-data-source-spring.xml,\
        #META-INF/shard-data-source-spring.xml,\
        #META-INF/memcached-spring.xml,\
        \
        META-INF/ext-spring.xml

A server restart is required after updating `portal-ext.properties`. Next, you 
need to configure the patching tool.
