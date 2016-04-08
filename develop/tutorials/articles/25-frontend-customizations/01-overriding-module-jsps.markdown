# Overriding JSPs from a Liferay Module

Liferay applications (both native and third party) often contain JSPs.
Sometimes you might need to override one of them. If you need to override the
JSPs from a module deployed to the OSGi runtime, you need a module that has the
following:

-  Specifies a host module's symbolic name and version in the OSGi header `Fragment-Host` declaration.

-  Provides a complete copy of the original JSP with any modifications you need
   to make.

## Declaring a Fragment Host

There are two players in this game: the fragment and the host. The fragment is a
parasitic module that attaches itself to a host. That sounds harsh, so let's
compare the fragment-host relations to that of a relationship between a pilot
fish and a huge, scary shark. It's symbiotic, really. Your fragment module
benefits by not doing much work (like the pilot fish who benefits from the
shark's hunting prowess). In return, the host module gets whatever benefits you've conjured
up in your fragment's JSPs (for the shark, it gets free dental cleanings!). The
point is, to the OSGi runtime, your fragment is part of the host module.

Your fragment must declare to the OSGi runtime two things regarding the host
module:

1. The Bundle Symbolic Name of the host module.

    This is the module containing the original JSP. Often time it's 

2. The exact version of the hot module to which the fragment belongs.

Both are declared using the OSGi header `Fragment-Host`.

    Fragment-Host: com.liferay.login.web;bundle-version="1.0.0"

Supplying a specific host module version is important. If that version of the
module isn't present, your fragment won't attach itself to a host, and that's a
good thing. A new version of the host module might have changed its JSPs, so if
your now-incompatible version of the JSP is applied to the host module, you'll
break the functionality of the host. It's better to decouple your fragment
and leave it lonely in the OSGi runtime than it is to break the functionality of
an entire application.

## Provide the Overridden JSP

After declaring the host, copy the original JSP into your module. After taht,
make your modifications. It's pretty much that simple. Just make sure you mimic
the directory structure of the host module when overriding its JAR. If you're
overriding Liferay's login application's `login.jsp` for example, you'd put your
own `login.jsp` in 

    my-jsp-fragment/src/main/resource/META-INF/resource/login.jsp

Now you can easily modify the JSPs of any applications in Liferay.

![Figure x: Liferay's applications are swimming in the OSGi runtime, waiting for
your pilot fish fragment modules.](../../images/sharks.jpg)
<!--https://commons.wikimedia.org/wiki/File:Carcharhinus_perezi_bahamas_feeding.jpg-->

Questions: 

1. It appears that the entire JSP must be copied and then modifctions added to
it, right? There's no way just to 

