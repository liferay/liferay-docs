# Customizing JSPs [](id=customizing-jsps)

There are several different ways to customize JSPs in portlets and the core.
@product@'s API provides the safest ways. Here are API-based approaches to
customizing JSPs:

 **Approach** | **Description** | **Cons/Limitations** |
 :----------- | :-------------- | :-------------- |
[Dynamic includes](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps-with-dynamic-includes) | Adds content at dynamic include tags. | Limited to JSPs that have `dynamic-include` tags (or tags whose classes inherit from `IncludeTag`). Only inserts content in the JSPs at the dynamic include tags. |
[Portlet filters](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-portlet-filters) | Modifies portlet requests and/or responses to simulate a JSP customization. | Although this approach doesn't directly customize a JSP, it achieves the effect of a JSP customization. |

<!-- TODO - Include inlined content approach after getting more info. - Jim
Inlined content | Some @product@ JSPs include content from other JSPs that you can modify. This "inlines" the content from the other JSPs into specific places in the root JSP. | Limited to JSPs that inline other JSPs. |
-->

All these JSP customization approaches are available to you. It's time to
customize some JSPs!
