# Semantic Versioning

[Semantic Versioning](https://semver.org/) is a three tiered versioning system
designed to increment version numbering according to the types of API changes
introduced to a releasable software component. It's a standard that enables API
authors to communicate programmatic compatibility of a package or module
automatically as it relates to dependent consumers and API implementations. If a
package is programmatically (i.e., semantically) incompatible with a project,
[Bnd](http://bnd.bndtools.org/) (used when building @product@ modules) fails that project's
build immediately.

The semantic version format looks like this:

    MAJOR.MINOR.MICRO

The events that force each tier to be incremented are as follows:

- *MAJOR:* an incompatible, API-breaking change is made
- *MINOR:* a new functionality is added, which is backwards compatible, or any
  change which affects only providers of the API
- *MICRO:* a bug fix is made, which is backwards compatible

For more details on semantic versioning, see the official
[Semantic Versioning](https://semver.org/) site and
[OSGi Alliance's Semantic Versioning](http://www.osgi.org/wp-content/uploads/SemanticVersioning1.pdf)
technical whitepaper.

All of @product@'s modules use semantic versioning.

Following semantic versioning in @product@ is especially important since it's a
modular platform that contains hundreds of independent OSGi modules. With many
independent modules containing a slew of dependencies, releasing new package
versions can quickly become terrifying. With this complex intertwined system of
dependencies, you must meticulously manage your own project's API versioning to
ensure compatibility for those who leverage it. With semantic versioning's
straight forward system and the help of Liferay tooling, managing your project's
versions is easy.

## Baselining Your Project

Following the semantic versioning procedure manually seems deceptively easy.
There's a sad history of good-intentioned developers updating their projects'
semantic versions manually, only to find out later they made a mistake. The
truth is, it's hard to anticipate the ramifications of a simple update. To avoid
this, you can *baseline* your project after it has been updated. Baselining
verifies that the semantic versioning rules are obeyed by your project. This can
catch a significant number of obvious API changes that are not so obvious to
humans. However, care must always be taken when making any kind of code changes
because this tool is not smart enough to identify compatibility changes which
are not represented in the signatures of the Java classes or interfaces, or in
API *use* changes (for instance assumptions about method call order, or changes
to input and/or output encoding). However baseline, as the name implies, gives
you a certain measure of *baseline* comfort that a large class of compatibility
issues won’t sneak past you.

Liferay's Baseline Gradle plugin can be configured in your project to provide
baselining capabilities. Add it to your Gradle build configuration and execute
the following command:

    ./gradlew baseline

See the
[Baseline Gradle Plugin](/develop/reference/-/knowledge_base/7-1/baseline-gradle-plugin)
article for configuration details. This plugin is not provided in
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
by default.

When you run the `baseline` command, the plugin baselines your new module
against the latest released non-snapshot module (i.e., the baseline). That is,
it compares the public exported API of your new module with the baseline. If
there are any changes, it uses the OSGi semantic versioning rules to calculate
the minimum new version. If your new module has a lower version, errors are
thrown.

With the ability to baseline, your project's semantic versioning should at least
as accurate as what is expressed through its API.

## Managing Artifact and Dependency Versions

Tracking your project's artifact and dependency versions can be done two ways
with semantic versioning:

- Range of versions
- Exact version (one-to-one)

You should track a range of versions if you intend to build your project for
multiple versions of @product@ and maintain maximum compatibility. In other
words, if several versions of a package work for an app, you can configure the
app to use any of them. What's more, Bnd automatically determines the
semantically compatible range of each package a module depends on and records
the range to the module's manifest.

For help with version range syntax, see the
[OSGi Specifications](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i3189032).

A version range for imported packages in an OSGi bundle's `bnd.bnd` would look
like this:

    Import-Package: com.liferay.docs.test; version="[1.0.0,2.0.0)"

Popular build tools also follow this syntax. In Gradle, a version range for a
dependency would look like this:

    compile group: "com.liferay.portal", name: "com.liferay.portal.test", version: "[1.0.0,2.0.0)"

In Maven, it would look like this:

    <groupId>com.liferay.portal</groupId>
    <artifactId>com.liferay.portal.test</artifactId>
    <version>[1.0.0,2.0.0)</version>

Specifying the latest release version can also be considered a range of versions
with no upper limit. For example, in Gradle, it's specified as `version:
"latest.release"`. This can be done in Maven 2.x with the usage of the version
marker `RELEASE`. This is not possible if you're using Maven 3.x. See
[Gradle](https://gradle.org/docs/) and
[Maven](http://maven.apache.org/guides/)'s respective docs for more information.

Tracking a range of versions comes with a price. It's hard to reproduce old
builds when you're debugging an issue. It also comes with the risk of differing
behaviors depending on the version used. Also, relying on the latest release
could break compatibility with your project if a major change is introduced. You
should proceed with caution when specifying a range of versions and ensure it's
thoroughly tested on all included versions.

Tracking a dependency's exact version is much safer, but is less flexible. This
would be for modules intended for a specific version of @product@. You would
also be locked in to APIs that only exist for that specific version. This means
your module is much easier to test and has less chance for unexpected failures.

+$$$

**Note:** When specifying package versions in your `bnd.bnd` file, exact
versions are typically specified like this: `version="1.1.2"`. However, this
syntax is technically a range; it is interpreted as [1.1.2, &#8734;). Therefore,
if a higher version of the package is available, it'll be used instead of the
version you specified. For these cases, it may be better to specify a version
range for compatible versions that have been tested. If you want to specify a
true exact match, the syntax would look similar to this: `[1.1.2]`. See the
[Version Range](https://osgi.org/specification/osgi.core/7.0.0/framework.module.html#i3189032)
section in the OSGi specifications for more info.

Gradle and Maven use exact versions when only one version is specified.

$$$

You now know the pros and cons for tracking dependencies as a range and as an
exact match.
