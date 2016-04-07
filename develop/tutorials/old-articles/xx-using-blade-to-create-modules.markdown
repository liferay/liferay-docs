# Using BLADE to Create Modules [](id=using-blade-to-create-modules)

[Blade Tools](https://github.com/gamerson/blade.tools) is Liferay's recommended
tool for developing Liferay modules. See the
[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)
tutorial and the other tutorials in this section to learn how to use Blade
Tools. At the time of this writing, Blade Tools contains only a few project
templates. Eventually, many more project templates will be added to
Blade Tools. The [BLADE](https://github.com/rotty3000/blade) repository contains
example projects that demonstrate many different kinds of Liferay modules that
developers can create. Until more project templates are added to Blade Tools,
you may need to repurpose one of the BLADE example projects to serve as a
starting point for your own project.

Follow these simple steps to create a project based on a BLADE project example.

1.  Clone the BLADE repository to gain access to latest example projects.
2.  Choose a BLADE project to serve as a starting point for your project and
    copy your chosen BLADE project from the BLADE repository.
3.  Reconfigure the build file of the copied project.
4.  Refactor the copied project's name, package names, and class names.
5.  Customize the copied project's code to meet your needs.

Do you want to override one of Liferay's Struts actions? Create an indexer
post-processor? Create a portlet filter? There are BLADE project examples that
demonstrate how to accomplish these tasks and many more. The
[BLADE](https://github.com/rotty3000/blade) repository examples include Gradle,
Maven, and Bndtools versions. Once you've chosen a project, find the version of
that project in the directory that corresponds to your preferred build tool. For
example, if you want to override a Liferay service and you want your project to
be Gradle-based, navigate to the `blade/gradle` directory and find the
`blade.service.hook.user` project. Copy the project from the BLADE repository to
a local directory that will serve as the root directory for your new project.

## Cloning the BLADE Repository [](id=cloning-the-blade-repository)

You need to clone the [BLADE](https://github.com/rotty3000/blade) repository to
gain access to its example projects. At the time of this writing, new examples
continue to be added to the project by contributors. Make sure to regularly
update your local copy of the repository.

## Copying a Project out of BLADE [](id=copying-a-project-out-of-blade)

Find a project that you want to use as a starting point for your project. Start
by choosing your build tool. You have three options: Gradle, Maven, or Bndtools.
The root BLADE project has a separate directory for each build tool. Copy the
BLADE example from the `blade/[build-tool]` directory to a new local directory
that will serve as your new project's root directory.

The list of BLADE templates is always growing, but here's the list of projects in
the `blade/gradle` directory at the time of this writing: 

    blade.authenticator.shiro
    blade.authfailure
    blade.configurationaction
    blade.indexerpostprocessor
    blade.lifecycle.loginpreaction
    blade.pollprocessor
    blade.portlet.actioncommand
    blade.portlet.blueprint
    blade.portlet.ds
    blade.portlet.filter
    blade.portlet.jsp
    blade.portlet.osgiapi
    blade.servicebuilder.api
    blade.servicebuilder.svc
    blade.servicebuilder.test
    blade.servicebuilder.web
    blade.service.hook.user
    blade.strutsaction
    blade.strutsportletaction

This list of projects already contains a fair number of project templates. You
have everything from modules comprising a Service Builder-based application (the
`blade.servicebuilder.[api|web|svc|test]` modules) to indexer post processors
(the `blade.indexerpostprocessor` module). It's worth repeating that new
examples are still being added to the repository, so don't rely on this static
list. If you don't see a BLADE module type that fits your need listed above,
check the [BLADE](https://github.com/rotty3000/blade) repository.

Once you find a suitable example project, copy it from BLADE to a new folder.
If you try to build the project after copying it, it won't work. You need to
reconfigure the build file.

## Reconfiguring the Build File [](id=reconfiguring-the-build-file)

Looking at the `blade/gradle/blade.service.hook.user` project's `build.gradle`
file quickly reveals why it won't build if you simply copy the BLADE module to a
new location:

    version='1.0'

Yes, that's the entire contents of the `blade.service.hook.user` build file.
Obviously, this version declaration isn't the only instruction that's required
for building a module. The meat of the build files of BLADE Gradle projects
belongs to the `blade/gradle/build.gradle` file. This `build.gradle` file is
inherited by each individual BLADE project. Here it is, at the time of this
writing:

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

The BLADE repository's parent build files (`blade/gradle/build.gradle` and
`blade/gradle/settings.gradle`) define a multi-project build process for BLADE.
Each sub-project is listed in the `settings.gradle` file. If you wanted to take
advantage of BLADE's pre-configured build tools, you could develop new projects
in the BLADE repository. That's a perfectly viable option. However, we assume
that your goal is to create a standalone module project.

To develop a standalone module project that will build anywhere, merge the
parent build file from the BLADE project with the module's build file. For the
`blade.service.hook.user` Gradle module project, it's not difficult. Copy the
contents of the parent build file into the module's `gradle.build` file to
start. Once you have the combined the build files, start paring the resulting
build file down to the bare essentials. The build file for the
`blade.service.hook.user` module is easily boiled down to this:

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

Remove the `subprojects` section since your project has no subprojects. Also,
remove any unused repositories and dependencies. Notice the
`biz.aQute.bnd.builder` that applied to your project. This plugin allows your
project to use [Bnd](http://aqute.biz/Code/bnd), a tool that simplifies module
creation.

If you run `gradle build` now, your project builds successfully. Of course, you
can edit your build script however you like. Flexibility is one of the goals of
the BLADE project. As you develop your project into something more substantial
than the simple example that you copied out of BLADE, you might need to update
your build script. For example, you might need to declare some new dependencies.

## Refactoring Your Project [](id=refactoring-your-project)

Once you've copied your module project and reconfigured your build script, your
project should build successfully. You should be able to deploy it to Liferay's
module framework. Your project, however, still carries the BLADE project name,
package and class names, and directory structure. You need to refactor these
aspects of your project.

For example, if you copied the `blade.service.hook.user` project and you want to
change its name to `com.liferay.docs.serviceoverride`, containing a class called
`UserLocalServiceExtender`, you'd follow these steps:

1. Rename the project. The current name is `blade.service.hook.user`. Change the
   project name to `com.liferay.docs.serviceoverride`.

2. Rename the package or packages. The current package name is the same as the
   project name, so rename it to match the new project name:
   `com.liferay.docs.serviceoverride`. Note that this approach isn't required.
   You can use any naming convention. Whatever package name you choose, remember
   to refactor your classes' package declarations to match the new package name.

3. Rename the class or classes. Currently the class name is
   `UserLocalServiceHook`. Rename it to `UserLocalServiceExtender`. Whatever
   name you choose, remember to open the class and change the class declaration
   and the constructor(s) to match.

After following the steps above, the top of the `UserLocalServiceExtender`
(formerly `UserLocalServiceHook`) class has changed: 

    package com.liferay.docs.serviceoverride;

    import com.liferay.portal.kernel.exception.PortalException;

    ...

    @Component(service = ServiceWrapper.class)
    public class UserLocalServiceExtender extends UserLocalServiceWrapper {

        public UserLocalServiceExtender() {
            super(null);
        }

    ...

It's that easy! At this point your module project is appropriately configured
and renamed. You're ready to code it to suit your needs. You can either delete
the existing code and start from scratch or you can modify it where needed.

If you dislike the idea of taking an existing example project and manually
reconfiguring it, consider using Blade Tools. Blade Tools is a command line
utility that allows you build module projects based on a selected template. It
also allows you to deploy your module easily to a running Liferay module
framework. Check out the 
[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/blade-tools-bundle-creation)
tutorial and the other tutorials in this section for more information.

## Related Topics [](id=related-topics)

[Introduction to Blade Tools](/develop/tutorials/-/knowledge_base/7-0/installing-blade-cli)

[Creating Liferay Applications](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-applications)

[Creating Liferay Components](/develop/tutorials/-/knowledge_base/7-0/creating-liferay-components)

[Customizing Liferay Services](/develop/tutorials/-/knowledge_base/7-0/customizing-liferay-services)

[Consuming Liferay Services](/develop/tutorials/-/knowledge_base/7-0/consuming-liferay-services)
