# Migrating the liferay-portlet.xml File for Liferay Faces 3.2/4.2 [](id=migrating-the-liferay-portlet-xml-file-f-liferay-portal-6-2-dev-guide-04-en)

Liferay Portal 6.2 has two compatible Liferay Faces Versions: 3.2 and 4.2. We provide
Liferay Faces 3.2 for compatibility with JSF 2.1 and provide Liferay Faces 4.2
for compatibility with JSF 2.2 (see the [Liferay Faces Version Scheme](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-faces-version-scheme-liferay-portal-6-2-dev-guide-04-en)
for more info on Liferay Portal and JSF compatibility). If you are currently
using Liferay Faces 3.1 and are interested in upgrading from Liferay
Portal 6.1 to 6.2, but aren't interested in compatibility with JSF 2.2, you should
upgrade from Liferay Faces 3.1 to 3.2. But, if you want to use JSF 2.2 in addition to Liferay 6.2,
you must upgrade to Liferay Faces 4.2. 

---

 ![Note](../../images/tip.png) **Note:** This guide only addresses upgrading as
 it relates to Liferay Portal. Upgrading from JSF 2.1 to 2.2 may require
 additional changes. For information on upgrading to JSF 2.2 you should check
 out JSF specific upgrade guides. 

---

Liferay Portal 6.2 can enforce namespacing of portlet request parameters. But
you must turn this off for your JSF portlets by specifying
`<requires-namespaced-parameters>false</requires-namespaced-parameters>` in each
`<portlet>` element of your portlet project's `WEB-INF/liferay-portlet.xml`
file. Here's a snippet that specifies this descriptor: 

    <liferay-portlet-app>
        <portlet>

            ...

            <requires-namespaced-parameters>false</requires-namespaced-parameters>

            ...

        </portlet>

        ...

    </liferay-portlet-app>

Turning off the parameter namespace requirement is all you need to do to upgrade
your JSF portlets to Liferay Faces 3.2 or 4.2, for use in Liferay Portal 6.2. 

As an example JSF portlet that runs on Liferay Portal 6.2, check out the
[demo JSF2-portlet](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet)
and its 
[`liferay-portlet.xml`](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/liferay-portlet.xml)
file. 

Next, we'll show you how to build the Liferay Faces project. 
