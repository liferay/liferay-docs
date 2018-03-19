# Semantic Versioning

[Semantic Versioning](https://semver.org/) is a three tiered versioning system
designed to increment version numbering according to the types of changes
introduced to a releasable software component. The semantic version format looks
like this:

    MAJOR.MINOR.PATCH

The events that force each tier to be incremented are as follows:

- *MAJOR:* an incompatible, API-breaking change is made
- *MINOR:* a new functionality is added, which is backwards compatible
- *PATCH:* a bug fix is made, which is backwards compatible

For more details on semantic versioning, see the official
[Semantic Versioning](https://semver.org/) site and
[OSGi Alliance's Semantic Versioning](http://www.osgi.org/wp-content/uploads/SemanticVersioning1.pdf)
technical whitepaper.

Following semantic versioning in @product@ is especially important since it's a
modular platform that contains hundreds of independent OSGi modules. With many
independent modules containing a slew of dependencies, releasing new package
versions can quickly become terrifying. With this complex intertwined system of
dependencies, you must meticulously manage your own project's versioning to
ensure compatibility for those who leverage it. With semantic versioning's
straight forward system and the help of Liferay tooling, managing your project's
versions is easy.

## Baselining Your Project

Following the semantic versioning procedure manually seems deceptively easy.
There's a sad history of good-intentioned developers updating their projects'
semantic versions manually, only to find out later they made a mistake. The
truth is, it's hard to anticipate the ramifications of a simple update. To avoid
this, you can *baseline* your project. Baselining verifies that the semantic
versioning rules are obeyed by your project.

Liferay's Baseline Gradle plugin can be configured in your project to provide
baselining capabilities. Simply add it to your Gradle build configuration and
execute the following command:

    gradlew baseline

See the
[Baseline Gradle Plugin](/develop/reference/-/knowledge_base/7-1/baseline-gradle-plugin)
article for configuration details. This plugin is not provided in
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-1/liferay-workspace)
by default.

When you run the `baseline` task, the plugin baselines the new bundle against
the latest released non-snapshot bundle (i.e., the baseline). That is, it
compares the public exported API of the new bundle with the baseline. If there
are any changes, it uses the OSGi semantic versioning rules to calculate the
minimum new version. If the new bundle has a lower version, errors are thrown.

With the ability to baseline, your project's semantic versioning should always
be accurate.

## Tracking Dependency Version Ranges Versus Exact Dependency Versions






