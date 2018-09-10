# Customizing JSPs [](id=customizing-jsps)

There are several different ways to customize JSPs in portlets and the core.
@product@'s API provides the safest ways to customize them. If you customize a
JSP by other means, new versions of the JSP can render your customization
invalid and leave you with runtime errors. It's highly recommended to use one of
the API-based ways. 

## Using @product@'s API to Override a JSP [](id=using-portals-api-to-override-a-jsp)

Here are API-based approaches to overriding JSPs in @product@:

 **Approach** | **Description** | **Cons/Limitations** |
 :----------- | :-------------- | :-------------- |
[Dynamic includes](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps-with-dynamic-includes) | Adds content at dynamic include tags. | Limited to JSPs that have `dynamic-include` tags (or tags whose classes inherit from `IncludeTag`). Only inserts content in the JSPs at the dynamic include tags. |
[Portlet filters](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-portlet-filters) | Modifies portlet requests and/or responses to simulate a JSP customization. | Although this approach doesn't directly customize a JSP, it achieves the effect of a JSP customization. |

<!-- TODO - Include inlined content approach after getting more info. - Jim
Inlined content | Some @product@ JSPs include content from other JSPs that you can modify. This "inlines" the content from the other JSPs into specific places in the root JSP. | Limited to JSPs that inline other JSPs. |
-->

## Overriding a JSP Without Using @product@'s API [](id=overriding-a-jsp-without-using-portals-api)

It's strongly recommended to customize JSPs using @product@'s API, as the
previous section describes. Since overriding a JSP using an OSGi fragment or a
Custom JSP Bag is not based on APIs there's no way to guarantee that they'll
fail gracefully. Instead, if your customization is buggy (because of your code
or because of a change in Liferay), you are most likely to find out at runtime,
where functionality breaks and nasty log errors greet you. These approaches
should only be used as a last resort. 

If you're maintaining a JSP customization that uses one of these approaches, you
should know how they work. This section describes them and links to their
tutorials. 

Here are ways to customize JSPs without using @product@'s API:

 **Approach** | **Description** | **Cons/Limitations** |
 :----------- | :-------------- | :-------------- |
[OSGi fragment](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-osgi-fragments) | Completely overrides a module's JSP using an OSGi fragment | Changes to the original JSP or module can cause runtime errors. |
[Custom JSP bag](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-custom-jsp-bag) | Completely override a @product@ core JSP or one of its corresponding `-ext.jsp` files. | For @product@ core JSPs only. Changes to the original JSP or module can cause runtime errors. |

All the JSP customization approaches are available to you. It's time to
customize some JSPs!
