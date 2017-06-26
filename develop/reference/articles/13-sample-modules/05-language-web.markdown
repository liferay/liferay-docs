# Language Web [](id=language-web)

The Language Web sample is a simple JSP portlet that conveys Liferay's
recommended approach to sharing language keys through OSGI services.

You must deploy this sample with the `blade.language` sample module. The
Language Web portlet sample uses language keys shared by the Language module.
When you place this sample portlet on a @product@ page, you're presented with
the portlet's name followed by three language keys.

![Figure 1: The Language Web portlet displays three phrases, two of which are shared from a different module.](../../images/language-web-portlet.png)

The first message is provided by the Language Web module. The second message is
from the Language module. The third message is provided by both modules; as you
can see, the Language Web's message is used, overriding the Language module's
identically named language key.

This sample shows what takes precedence when displaying language keys. The order
for this example goes

1.  Language Web module language keys
2.  Language module language keys
3.  Liferay Portal language keys

You can visit the
[blade.language README](/develop/reference/-/knowledge_base/7-0/language) for
more information on how language keys are used from modules and shared between
modules.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/blade.language.web)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/modules/blade.language.web)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/blade.language.web)
