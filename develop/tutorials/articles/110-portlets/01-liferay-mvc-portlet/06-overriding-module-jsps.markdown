# Overriding a Module's JSPs [](id=overriding-a-modules-jsps)

Liferay applications often contain JSPs. Sometimes you might need to override
one of them. If you need to override the JSPs from a module deployed to the OSGi
runtime, you need a fragment module that does two things:

-  Specifies a host module's symbolic name and version in the OSGi header `Fragment-Host` declaration.

-  Provides a complete copy of the original JSP with any modifications you need
   to make.

For more information about fragment modules, you can refer to section 3.14 of the [OSGi Alliance's core specification document](https://www.osgi.org/developer/downloads/release-6/).

## Declaring a Fragment Host [](id=declaring-a-fragment-host)

There are two players in this game: the fragment and the host. The fragment is
a parasitic module that attaches itself to a host. That sounds harsh, so let's
compare the fragment-host relationship to the relationship between a pilot fish
and a huge, scary shark. It's symbiotic, really. Your fragment module benefits
by not doing much work (like the pilot fish who benefits from the shark's
hunting prowess). In return, the host module gets whatever benefits you've
conjured up in your fragment's JSPs (for the shark, it gets free dental
cleanings!). To the OSGi runtime, your fragment is part of the host module.

Your fragment must declare two things to the OSGi runtime regarding the host
module:

1. The Bundle Symbolic Name of the host module.

    This is the module containing the original JSP.

2. The exact version of the host module to which the fragment belongs.

Both are declared using the OSGi header `Fragment-Host`.

    Fragment-Host: com.liferay.login.web;bundle-version="1.0.0"

Supplying a specific host module version is important. If that version of the
module isn't present, your fragment won't attach itself to a host, and that's a
good thing. A new version of the host module might have changed its JSPs, so if
your now-incompatible version of the JSP is applied to the host module, you'll
break the functionality of the host. It's better to detach your fragment
and leave it lonely in the OSGi runtime than it is to break the functionality of
an entire application.

## Provide the Overridden JSP [](id=provide-the-overridden-jsp)

After declaring the host, copy the original JSP into your module. After that,
make your modifications. Just make sure you mimic the directory structure of the
host module when overriding its JAR. If you're overriding Liferay's login
application's `login.jsp` for example, you'd put your own `login.jsp` in 

    my-jsp-fragment/src/main/resource/META-INF/resource/login.jsp

Now you can easily modify the JSPs of any application in Liferay.

![Figure 1: Liferay's applications are swimming in the OSGi runtime, waiting for your fragment modules to clean their teeth, so to speak.](../../../images/sharks.jpg)
<!--https://commons.wikimedia.org/wiki/File:Carcharhinus_perezi_bahamas_feeding.jpg-->

To see a sample JSP-modifying fragment in action, look at the [BLADE project
named `blade.hook.jsp`] (https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/modules/blade.hook.jsp).

