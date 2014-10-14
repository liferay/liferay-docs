# Choosing your development tools [](id=choosing-your-development-tools)

The Java ecosystem is known for providing a variety of options for almost any
type of software development. This is advantageous because you can find the tool
that best fits your needs and the way you work. Naturally, when you get
comfortable with a tool, you want to keep using it. 

If you're a newcomer, the wide variety of tools available can be overwhelming.
Throughout this guide, we'll give you the best of both worlds showing you how to
develop plugins in two environments that use open source technologies 1) A
command-line environment that integrates with a wide variety of tools. 2) An
easy-to-use IDE that minimizes your learning curve while giving you powerful
development features. Here are those two environments:

**Apache Ant and the Plugins SDK:** Liferay provides a development environment
called the *Plugins SDK* that lets you develop plugins of all types by executing
a set of predefined commands (known as *targets*, in Ant's nomenclature). You
can use the Plugins SDK directly from the command-line and use file editors like
Emacs, Vim, EditPlus, or even Notepad. You can also integrate the Plugins SDK
with your favorite IDE, since most IDEs provide support for Apache Ant. The next
chapter describes how to use the Plugins SDK. 

**Eclipse and the Liferay IDE:** Eclipse is the most popular and well known
Java IDE and it provides a wide variety of features. *Liferay IDE* is a plugin
for Eclipse that extends its functionality to facilitate developing all types of
Liferay plugins. Liferay IDE uses the Plugins SDK underneath, but you don't need
to know the SDK unless you're performing an advanced operation not directly
supported by Liferay IDE. To develop applications for Liferay Portal Enterprise
Edition (EE), use Liferay Developer Studio which extends Liferay IDE, providing
additional integration plugins such as the Kaleo Designer for Java. 

This guide shows you how to develop for Liferay using both the Plugins SDK and
Liferay IDE, to benefit you and other developers even if you don't like IDEs or
don't use Eclipse. If you use Eclipse, you may want to start reading about
Liferay IDE in Chapter 10 first and then go back to reading about the Plugins
SDK.

**What about if I don't like Apache Ant and I prefer to use Maven?** Many
developers prefer other command-line alternatives to Apache Ant. The most
popular of these alternatives is Maven. To support developers that want to use
Maven we have *mavenized* Liferay artifacts for referencing in your Maven
projects. See Chapter 9 for an in-depth look at developing plugins in Maven. 

**What if I don't like Eclipse and prefer to use NetBeans, IntelliJ IDEA or
other another IDE?** There are many IDEs available, and each one has its
strengths. We built Liferay IDE on top of Eclipse because it's the most popular
open source option. We also want to make sure you can use the IDE of your
choice. In fact, many core developers use NetBeans and IntelliJ IDEA. Both of
these IDEs have support for integration with Apache Ant, so you can use the
Plugins SDK with them. Additionally, there is an extension to NetBeans called
the *Portal Pack* that is explicitly designed for develop plugins for Liferay. 
You can find more about the Portal Pack at
[http://contrib.netbeans.org/portalpack](http://contrib.netbeans.org/portalpack).

That's it for the introduction. Let's get started with real development work! 
