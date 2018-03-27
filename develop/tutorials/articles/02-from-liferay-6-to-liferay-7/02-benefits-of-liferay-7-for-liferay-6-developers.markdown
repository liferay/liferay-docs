# Benefits of @product-ver@ for Liferay Portal 6 Developers [](id=benefits-of-liferay-7-for-liferay-6-developers)

More than in any other Liferay release, @product-ver@ centers on you, the
developer. Liferay's platform has been rebuilt, making it easier to build on and
maintain, and providing more new developer features than any previous Liferay
release.

Here are some key benefits of this release for developers:

1. **Simpler and Leaner**

2. **Modular Development Paradigm**

3. **Enhanced Reusability**

4. **More extensible, easier to maintain**

5. **Optimized for your tooling of choice**

6. **Powerful Configurability**

Let's consider how they make development easier for you.

## Simpler and Leaner [](id=simpler-and-leaner)

Liferay has always been simple and lean, compared to the proprietary
alternatives; this version widens the gap even more.

@product-ver@ is simpler than its predecessors, thanks to a streamlined and modular
architecture. In addition, many Liferay specific ways of creating extensions and
applications have evolved to follow official or de-facto standards. As a result,
developers can now more easily reuse their existing knowledge and use what they
learn developing for Liferay outside of it.

@product-ver@ is also leaner. Its modularized core allows developers and system
administrators to remove parts they don't need or don't want; this facilitates
deployment, reduces startup times and memory footprints, and results in more
efficiencies and performance improvements.

## Modular Development Paradigm [](id=modular-development-paradigm)

If you have been using Liferay, you've already experienced some of the benefits
of modular development, thanks to plugins. @product-ver@ takes these benefits to a
whole new level.

In addition to building plugins as you have previously, you can take advantage
of a complete module development and runtime system based on OSGi standards.
@product-ver@ facilitates creating applications of all types by composing and
reusing modules.

And don't worry, modules are easy to understand. A module is distributed as a
JAR file and can be as small as one Java class or as large as any application
you can think of. An application for Liferay can comprise one single module or
as many modules as you want. The cool thing is that modules can cooperate,
allowing you to build applications by combining smaller pieces that are easier
to develop, deploy, maintain, and reuse.

## Enhanced Reusability [](id=enhanced-reusability)

If you have worked on large developments on top of Liferay you have probably
experienced situations in which you wanted to share a subset of classes from
from one plugin with another.

Java EE does not provide any standard way to achieve this, but Liferay provided
certain capabilities to achieve it with a mechanism known as CLP that used class
loader *magic* to allow plugins to invoke services in other plugins created with
Service Builder. This mechanism, however, is still a bit limited (Java EE's
class loader doesn't allow for much more) and doesn't give you the freedom to
specify any or all classes from one module to use from within another module.

@product-ver@ enables greater reusability, both in code and runtime memory, several
folds. For any desired reusable functionality you just [create a module](/develop/tutorials/-/knowledge_base/7-0/starting-module-development#creating-a-module)
(remember, it's just a JAR file with some metadata) with the classes you want
and deploy it. Other modules need only declare that they use the classes in that
module (by specifying their packages) and @product-ver@ automatically wires them
together. All invocations are regular Java calls! Try it out; it's beautiful. :)

This mechanism eliminates the dreaded "JAR/classpath hell" issue. No longer do
you have to jockey JAR files in classpaths; nor do you have to implement
intricate class loaders. The runtime environment uses separate class spaces per
module; it even accommodates using multiple versions of libraries in the same
application (as long as they can coexist).

## More Extensible, Easier to Maintain [](id=more-extensible-easier-to-maintain)

Whenever we ask Liferay developers what is their favorite characteristic of
Liferay, "Great extensibility" is one of the top three most popular responses.
You can customize almost every detail and add your own functionality on top.

Is @product-ver@ more extensible? You bet! Many more extension points have been
added. But not only that, all new extension points and many existing ones which
have been upgraded, use a new extension mechanism based on OSGi's service model.
Here are some of the mechanism's benefits:

1. **Simpler**: An implementation of an extension point is now always a
Java class that implements an interface and has one annotation (@Component).
That's it; it couldn't be any easier.

2. **Easier to maintain**: Extension points are now more strictly defined
through a Java interface that uses Semantic Versioning rules. This means that
your extensions can work without changes, even across several Liferay versions,
as long as the specific extension API is backwards compatible.

3. **Dynamic**: Extensions can be loaded and removed at any time during
development or in production.

But that is not all. Your own developments can now also leverage this model and
become extensible. You can create simple extension points by just creating an
interface and annotating a setter method with an annotation (@Reference).
Implementing extensibility has never been easier.

## Optimized for Your Tooling of Choice [](id=optimized-for-your-tooling-of-choice)

@product-ver@ empowers you to use the tools you like.

If you don't have strong preferences and are open to our suggestions, we offer
Liferay Workspace. It provides an opinionated directory structure and build
system based on Gradle and Bnd. Liferay Workspace can be used standalone through
the command line or with Liferay @ide@, which runs on Eclipse.

<!-- TODO Uncomment this when the features are ready. - Jim
If you have an investment in a specific build tool, such as Maven, developing on
Liferay will be easier than ever before. We have built Maven plugins for typical
Liferay-specific development tasks (such as using Service Builder) and provide a
collection of new archetypes.
-->

And if you want to continue using the Plugins SDK, we've got you covered. The
Plugins SDK is available to facilitate your transition to @product-ver@. In fact, a
Plugins SDK structure can reside in a Liferay Workspace alongside new
developments that use the new build environment; you can switch between
traditional projects and new projects at your own pace.

Finally, we have also developed a lightweight tool called Blade CLI, which
facilitates starting new projects from templates -- it's especially useful for
Gradle which doesn't have Maven's concept of archetypes. Blade CLI also offers
commands to start/stop the server and deploy and administer modules.

## Powerful Configurability [](id=powerful-configurability)

Creating configurable code is a breeze with @product-ver@. And applications that use
Liferay's new Configuration API allow administrators to change the configuration
on the fly, through an auto-generated user interface called System Settings.

Now you understand how @product-ver@ enriches your experience as a developer and
makes developing apps and customizations fun.

Next, we'll take a look at OSGi and modularity to discuss key concepts and
demonstrate how easy and gratifying it is to build modules. 
