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
straight forward system and the help of Liferay tooling, managing your app's
versions is easy.


