# Resolving Bundle Requirements [](id=resolving-bundle-requirements)

If one of your bundles imports a package that no other bundle in the Liferay
OSGi runtime exports, you get a bundle exception. Here's an example
exception:

    ! could not resolve the bundles: [com.liferay.messaging.client.command-1.0.0.201707261701 org.osgi.framework.BundleException: Could not resolve module: com.liferay.messaging.client.command [1]
    Unresolved requirement: Import-Package: com.liferay.messaging.client.api; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.messaging.client.api; bundle-symbolic-name="com.liferay.messaging.client.provider"; bundle-version="1.0.0.201707261701"; version="1.0.0"; uses:="org.osgi.framework"
    com.liferay.messaging.client.provider [2]
    Unresolved requirement: Import-Package: com.liferay.messaging; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.messaging; bundle-symbolic-name="com.liferay.messaging.api"; bundle-version="1.0.0"; version="1.0.0"; uses:="com.liferay.petra.concurrent"
    com.liferay.messaging.api [12]
    Unresolved requirement: Import-Package: com.liferay.petra.io; version="[1.0.0,2.0.0)"
    -> Export-Package: com.liferay.petra.io; bundle-symbolic-name="com.liferay.petra.io"; bundle-version="1.0.0"; version="1.0.0"
    com.liferay.petra.io [16]
    Unresolved requirement: Require-Capability osgi.extender; filter:="(osgi.extender=osgi.serviceloader.processor)"

The first part of the message states *could not resolve the bundles*. The rest
of the message shows a string of unresolved requirements. Liferay's OSGi Runtime
could not resolve one of the bundle's transitive requirements.

The bundle exception message follows this general pattern:

-   Module A has an unresolved requirement (package or capability) `aaa.bbb`.
-   Module B provides `aaa.bbb` but has an unresolved requirement `ccc.ddd`.
-   Module C provides `ccc.ddd` but has an unresolved requirement `eee.fff`.
-   etc.
-   Module Z provides `www.xxx` but has an unresolved  requirement `yyy.zzz`.

The pattern stops at the final requirement no module provides. The last module's
dependencies are key to resolving the bundle exception. There are two possible
causes:

1.  A dependency that satisfies the final requirement might be missing from the
    build file.

2.  A dependency that satisfies the final requirement might not be deployed.

Both cases require deploying a bundle that provides the missing requirement. 

The example bundle exception concludes that module `com.liferay.petra.io`
requires capability `osgi.extender;
filter:="(osgi.extender=osgi.serviceloader.processor)"`. To resolve the
requirement, make sure all of `com.liferay.petra.io`'s dependencies are
deployed. 

First, note the module's dependencies. Here is the `dependencies` section of the
`com.liferay.petra.io` module's `build.gradle` file. 

    dependencies {
        provided group: "com.liferay", name: "com.liferay.petra.concurrent", version: "1.0.0"
        provided group: "com.liferay", name: "com.liferay.petra.memory", version: "1.0.0"
        provided group: "org.apache.aries.spifly", name: "org.apache.aries.spifly.dynamic.bundle", version: "1.0.8"
        provided group: "org.slf4j", name: "slf4j-api", version: "1.7.2"
        testCompile group: "com.liferay.portal", name: "com.liferay.portal.kernel", version: "default"
    }

Then use
[Felix Gogo Shell's `lb command`](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)
to verify the dependencies in Liferay’s OSGi Runtime:

    lb
    START LEVEL 1
       ID|State      |Level|Name
        0|Active     |    0|OSGi System Bundle (3.10.100.v20150529-1857)|3.10.100.v20150529-1857
        1|Active     |    1|com.liferay.messaging.client.command (1.0.0.201707261923)|1.0.0.201707261923
        2|Active     |    1|com.liferay.messaging.client.provider (1.0.0.201707261927)|1.0.0.201707261927
        3|Active     |    1|Apache Felix Configuration Admin Service (1.8.8)|1.8.8
        4|Active     |    1|Apache Felix Log Service (1.0.1)|1.0.1
        5|Active     |    1|Apache Felix Declarative Services (2.0.2)|2.0.2
        6|Active     |    1|Meta Type (1.4.100.v20150408-1437)|1.4.100.v20150408-1437
        7|Active     |    1|org.osgi:org.osgi.service.metatype (1.3.0.201505202024)|1.3.0.201505202024
        8|Active     |    1|Apache Felix Gogo Command (0.16.0)|0.16.0
        9|Active     |    1|Apache Felix Gogo Runtime (0.16.2)|0.16.2
       10|Active     |    1|Apache Felix Gogo Runtime (1.0.0)|1.0.0
    ...

Notice the dependency module `org.apache.aries.spifly.dynamic.bundle` is missing
from the runtime bundle list. Examining the module's `MANIFEST.MF` file shows it
provides the requirement capability `osgi.extender;
filter:="(osgi.extender=osgi.serviceloader.processor)"`. 


    Provide-Capability: osgi.extender;osgi.extender="osgi.serviceloader.regi
     strar";version:Version="1.0",osgi.extender;osgi.extender="osgi.servicel
     oader.processor";version:Version="1.0"

Deploying this missing bundle `org.apache.aries.spifly.dynamic.bundle` resolves
the example's bundle exception. 

You can follow these similar steps to resolve your bundle exceptions. 

+$$$

Note: Bndtools's *Resolve* button can resolve bundle dependencies automatically.
You specify the bundles your application requires and Bndtools adds transitive
dependencies from your configured artifact repository.

$$$

## Related Topics [](id=related-topics)

[Configuring Dependencies](requirement/develop/tutorials/-/knowledge_base/7-0/configuring-dependencies)

[Adding Third Party Libraries to a Module](requirement/develop/tutorials/-/knowledge_base/7-0/adding-third-party-libraries-to-a-module)

[Felix Gogo Shell](/develop/reference/-/knowledge_base/7-0/using-the-felix-gogo-shell)

[Resolving a Plugins's Dependencies](requirement/develop/tutorials/-/knowledge_base/7-0/resolving-a-plugins-dependencies)
