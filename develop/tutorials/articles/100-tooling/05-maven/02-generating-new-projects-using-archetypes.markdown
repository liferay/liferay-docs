# Generating New Projects Using Archetypes

Creating Maven projects from scratch can be a lot of work. What dependencies
does my Liferay portlet project need? What does a Liferay Maven Service Builder
project look like? How do I create a Liferay Maven-based context contributor?
These questions can be answered with three words: Liferay Maven Archetypes.

Liferay provides a slew of Maven archetypes for easy Liferay module project
generation. In this tutorial, you'll learn how to use Liferay's Maven archetypes
to generate the module project you desire.

At the time of this writing, Liferay provides 37 Maven archetypes for you to
use. These are generated from the Central Repository. You can view the
Liferay-provided Maven archetypes by running the following command:

    mvn archetype:generate -Dfilter=liferay

Visit Maven's
[Archetype Generation](http://maven.apache.org/archetype/maven-archetype-plugin/generate-mojo.html)
documentation for further details on how to modify the Maven archetype
generation process.

As an example, you'll create a Liferay MVC portlet using its the Liferay Maven
archetype.

1. 
