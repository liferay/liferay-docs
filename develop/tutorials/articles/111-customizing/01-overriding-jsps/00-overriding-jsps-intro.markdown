# Overriding JSPs [](id=overriding-jsps)

There are several different ways to override JSPs in @product@ portlets and the
core. @product@'s API provides the safest ways to override them. If you override
a JSP by other means, new versions of the JSP can render your customization
invalid and leave you with runtime errors. So it's highly recommended to use one
of the API-based ways. 

## Using @product@'s API to Override a JSP [](id=using-portals-api-to-override-a-jsp)

Here are API-based approaches to overriding JSPs in @product@:

 **Approach** | **Description** | **Cons/Limitations** |
 :----------- | :-------------- | :-------------- |
[Dynamic includes](/develop/tutorials/-/knowledge_base/7-0/jsp-overrides-using-dynamic-includes) | Adds content at dynamic include tags. | Limited to JSPs that have `dynamic-include` tags (or tags whose classes inherit from `IncludeTag`). Only inserts content in the JSPs at the dynamic include tags. |
[Portlet filters](/develop/tutorials/-/knowledge_base/7-0/jsp-overrides-using-portlet-filters) | Uses the portlet request and response to modify as much JSP content as you want. | For portlet JSPs only. |

<!-- TODO - Include inlined content approach after getting more info. - Jim
Inlined content | Some @product@ JSPs include content from other JSPs that you can modify. This "inlines" the content from the other JSPs into specific places in the root JSP. | Limited to JSPs that inline other JSPs. |
-->

## Overriding a JSP Without Using portal's API [](id=overriding-a-jsp-without-using-portals-api)

It's strongly recommended to override JSPs using @product@'s API, as the
previous section describes. If you're maintaining a JSP override that uses one
of the following approaches, you should know how they work. This section
describes them and links to their tutorials. 

Here are ways to override JSPs without using @product@'s API:

 **Approach** | **Description** | **Cons/Limitations** |
 :----------- | :-------------- | :-------------- |
[JSP fragment](/develop/tutorials/-/knowledge_base/7-0/overriding-a-modules-jsps) | Completely overrides a module's JSP using an OSGi fragment | Changes to the original JSP or module can cause runtime errors. |
[Custom JSP bag](/develop/tutorials/-/knowledge_base/7-0/overriding-core-jsps) | Completely override a @product@ core JSP or one of its corresponding `-ext.jsp` files. | For @product@ core JSPs only. Changes to the original JSP or module can cause runtime errors. |

All the JSP override approaches are available to you. It's time to override some
JSPs!
