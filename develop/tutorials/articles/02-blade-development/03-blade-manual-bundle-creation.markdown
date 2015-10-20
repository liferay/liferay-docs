# Creating Bundles from Existing BLADE Templates [](id=creating-bundles-from-existing-blade-templates)

For developers not interested in developing their [Liferay modules in the
[Plugins SDK](/develop/tutorials/-/knowledge_base/7-0/creating-a-simple-bundle),
the [BLADE project](https://github.com/rotty3000/blade) is your best friend.
It's recommended to use the [Blade Tools command line utility](/develop/tutorials/-/knowledge_base/7-0/blade-tools-bundle-creation) to
generate a BLADE module from a set of Blade Tools subcommands. Because BLADE is
still under heavy development, however, there are more project types available
in BLADE than can be built using Blade Tools. As project types are migrated into
BLADE Tools templates, this will happen less and less, but at present, you may
need to repurpose one of the BLADE sample projects as a template for your own
project. 

Follow some basic steps to create bundles manually from BLADE project
templates:

1.  Clone the BLADE repository for access to the latest sample projects.
2.  Copy the BLADE project from the repository and reconfigure the build files.
3.  Refactor the project name, package names, and class names.
4.  Modify, augment, or otherwise customize the code to meet your needs.

One good way of developing plugins for Liferay 7 is to find a BLADE project
that meets your development goals (for example, modifying Liferay's core
services). You would choose your build tool (Gradle, for instance), navigate to
the BLADE Gradle directory, and find the `blade.service.hook.user` project.
Copy the project from the BLADE repository to your local directory.

For demonstration, this tutorial assumes that you're using Gradle to build
your bundles. 

## Cloning the BLADE Repository [](id=cloning-the-blade-repository)

You need to clone the [BLADE GitHub repository](https://github.com/rotty3000/blade) 
to have access to its template modules. New templates will continue to be added
to the project by contributors, so update your local version of the repository
regularly.

## Copying the Template Module out of BLADE [](id=copying-the-template-module-out-of-blade)

Find the project you want to use as your template module. Start by choosing
your build tool. At the time of this writing, you have three options: Gradle,
Maven, or Bndtools. The root BLADE project has a separate directory for each
build tool. Copy the BLADE template from the `blade/[build-tool]` directory
into your local directory.

The list of BLADE templates is always growing, but here's the list of projects in
the `blade/gradle` directory at the time of this writing: 

    blade.authenticator.shiro 
    blade.indexerpostprocessor
    blade.portlet.actioncommand
    blade.portlet.filter
    blade.servicebuilder.api
    blade.servicebuilder.web
    blade.strutsportletaction
    blade.authfailure
    blade.lifecycle.loginpreaction
    blade.portlet.blueprint
    blade.portlet.jsp
    blade.servicebuilder.svc
    blade.service.hook.user
    blade.configurationaction
    blade.pollprocessor
    blade.portlet.ds
    blade.portlet.osgiapi
    blade.servicebuilder.test
    blade.strutsaction

That list of projects is from quite early in the development of the BLADE
project, and it already contains a fair number of project templates. You have
everything from modules comprising a Service Builder-based application (the
`blade.servicebuilder.[api/web/svc/test]` modules) to indexer post processors
(`blade.indexerpostprocessor`). It's worth repeating: template modules are added
to the project continually, so do not rely on this static list. If you don't see
a BLADE module type that fits your need listed above, check the [BLADE repository](https://github.com/rotty3000/blade).

Once you find the proper template module, copy it from BLADE into your own
directory. If you try to build the project after copying it, it won't work. You
need to reconfigure the build script.

## Reconfiguring the Build Script [](id=reconfiguring-the-build-script)

Looking at the `blade/gradle/blade.service.hook.user` project's `build.gradle`
file quickly reveals why it won't build if you simply copy the BLADE module
to a new location:

    version='1.0'

Yes, that's the entire contents of the `blade.service.hook.user` build file;
pretty sparse, right? It's clear that declaring the version isn't the only thing
necessary for building a module. So where does the meat of the build script live
for BLADE Gradle projects? In the `blade/gradle/build.gradle` file. Here it is,
at the time of this writing:

    buildscript {
        repositories {
            ivy {
                url 'https://bndtools.ci.cloudbees.com/job/bnd.master/719/artifact/dist/bundles'
                layout 'pattern', {
                    artifact '[module]/[artifact]-[revision].[ext]' /* OSGi repo pattern */
                }
            }
            mavenCentral()
            maven {
                url 'https://repository.liferay.com/nexus/content/repositories/liferay-snapshots-ce/'
            }
        }
        dependencies {
            classpath 'biz.aQute.bnd:biz.aQute.bnd.gradle:3.0.0'
            classpath 'com.liferay:com.liferay.ant.bnd:2.0.0'
            classpath group: "com.liferay", name: "com.liferay.gradle.plugins.source.formatter", version: "latest.release"
        }
    }

    subprojects {
        
        apply plugin: 'biz.aQute.bnd.builder'
        apply plugin: 'com.liferay.source.formatter'
        sourceCompatibility = 1.7

        repositories {
            mavenCentral()
            maven {
                url 'https://repository.liferay.com/nexus/content/repositories/liferay-snapshots-ce/'
            }
        }

        dependencies {
            compile 'org.osgi:org.osgi.core:6.0.0'
            compile 'org.osgi:org.osgi.compendium:5.0.0'
            compile 'biz.aQute.bnd:biz.aQute.bnd.annotation:2.4.0'
            compile 'javax.portlet:portlet-api:2.0'
            compile 'javax.servlet:servlet-api:2.5'
            compile 'com.liferay.portal:portal-service:7.0.0-SNAPSHOT'
            compile 'com.liferay.portal:util-bridges:7.0.0-SNAPSHOT'
            compile 'com.liferay.portal:util-taglib:7.0.0-SNAPSHOT'
            compile 'com.liferay.portal:util-java:7.0.0-SNAPSHOT'
            compile 'org.slf4j:slf4j-api:1.7.7'
        }

        formatSource {
            baseDirName = "src/main"
        }
    }

    task wrapper(type:Wrapper){
        gradleVersion = '2.4'
    }

Because the goal is to create a standalone project, don't just copy the
`blade/gradle` parent build file into the parent directory of your locally
copied project including a `settings.gradle` file that lists your project. If
you're going to do that, why not just develop in the BLADE environment (which is
a viable option)? To develop a standalone module that will build no matter where
it is on your file system, merge the parent build file from the BLADE project
with the module's build file. For the `blade.service.hook.user` Gradle
module, it's not difficult. Copy the contents of the parent build file into the
module's `gradle.build` file to start.

Once you have the combined build files, start paring it down to the bare
essentials. The build script for the `blade.service.hook.user` module is easily
boiled down to something simpler.

    buildscript {
        repositories {
            ivy {
                url 'https://bndtools.ci.cloudbees.com/job/bnd.master/lastSuccessfulBuild/artifact/dist/bundles'
                layout 'pattern', {
                    artifact '[module]/[artifact]-[revision].[ext]' /* OSGi repo pattern */
                }
            }
        }
        dependencies {
            classpath 'biz.aQute.bnd:biz.aQute.bnd.gradle:3.0.0'
        }
    }
    version = '1.0'
    apply plugin: 'biz.aQute.bnd.builder'

        repositories {
            mavenCentral()
            maven {
                url 'https://repository.liferay.com/nexus/content/repositories/liferay-snapshots-ce/'
            }
        }
        dependencies {
            compile 'org.osgi:org.osgi.compendium:5.0.0'
            compile 'com.liferay.portal:portal-service:7.0.0-SNAPSHOT'
        }

    task wrapper(type:Wrapper){
        gradleVersion = '2.4'
    }

You want to get rid of the `subprojects` section (since there are no
subprojects), along with many of the dependencies and repositories since they
do not apply to the specific project in this example. Take note of the plugin
that's applied: `biz.aQute.bnd.builder`. This plugin lets you have your Bnd
cake and eat it too (so to speak). It's needed because the BLADE Gradle
projects use Bnd to configure the project without using a build environment
based on a Bnd Workspace. 

If you run `gradle build` now, the project builds successfully. Of course you
can do whatever you want with your build script and fold it into any existing
development environment you might like: that's the point of the BLADE project.
As you develop the project and make it into something more beautiful than the
simple template you copied out of BLADE, you'll probably need to revisit your
build script.

## Refactoring the BLADE Project [](id=refactoring-the-blade-project)

Once the module is copied and the build script reconfigured, it builds properly
and can be deployed to Liferay's module framework. It still carries the BLADE
project name, package and class names, and directory structure. You'll need to
refactor these aspects of the project.

For example, if you want the `blade.service.hook.user` project to
be named `com.liferay.docs.serviceoverride`, with a class called
`UserLocalServiceExtender`, you'd follow these steps:

1.  Rename the project. The current name is `blade.service.hook.user`. Since
    this is a tutorial from the Liferay documentation team,
    `com.liferay.docs.serviceoverride` works nicely.
2.  Rename the package. The current package name is the same as the project
    name, so rename it to match the new project name:
    `com.liferay.docs.serviceoverride`. Note that this approach isn't required. Use
    whatever naming conventions you want. Whatever package name you choose,
    remember to refactor your classes' package declarations to the new package
    name.
3.  Rename the class. Currently the class name is `UserLocalServiceHook`.
    Renaming it to `UserLocalServiceExtender` might be better. Whatever name you
    choose, remember to open the class and change the class declaration and the
    constructor(s) to match.

After following the steps above, the beginning of the
`UserLocalServiceExtender` (formerly `UserLocalServiceHook`) class has changed: 

    package com.liferay.docs.serviceoverride;

    import com.liferay.portal.kernel.exception.PortalException;

    ...

    @Component(service = ServiceWrapper.class)
    public class UserLocalServiceExtender extends UserLocalServiceWrapper {

        public UserLocalServiceExtender() {
            super(null);
        }

    ...

That's pretty much all there is to it. At this point your module is set up,
renamed, and it's ready to be coded to suit your needs. Delete the existing code
if you like and start from scratch, or just modify it where you need.

If you dislike the idea of taking an existing template project and manually
reconfiguring it, consider using the Blade Tools utility. Blade Tools is a
utility that builds you a module from scratch based on a set of options that you
provide using the command line. It also allows you to deploy your module easily
to a running Liferay module framework. Check out the
tutorial on [creating bundles with Blade Tools](/develop/tutorials/-/knowledge_base/7-0/blade-tools-bundle-creation) 
for more information.

## Related Topics [](id=related-topics)

[Installing Blade Tools](/develop/tutorials/-/knowledge_base/7-0/blade-tools-bundle-creation)

[Understanding Liferay's Module Framework](/develop/tutorials/-/knowledge_base/7-0/understanding-liferays-module-framework)

[Dynamic Applications](/develop/tutorials/-/knowledge_base/7-0/dynamic-applications)
