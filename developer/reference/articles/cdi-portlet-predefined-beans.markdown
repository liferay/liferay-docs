---
header-id: cdi-portlet-predefined-beans
---

# CDI Portlet Predefined Beans

[TOC levels=1-4]

@product@ provides injectable portlet artifacts for
[CDI](/docs/7-2/frameworks/-/knowledge_base/f/cdi-dependency-injection) called
Portlet Predefined Beans, as specified by [JSR
362](https://jcp.org/en/jsr/detail?id=362). There are two types of predefined
beans:

-   Portlet Request Scoped Beans
    ([`@PortletRequestScoped`](https://docs.liferay.com/portlet | - | api/3.0/javadocs/javax/portlet/annotations/PortletRequestScoped.html)) 

-   Dependent Scoped Beans ([`@Dependent`
    scoped](https://docs.oracle.com/javaee/7/api/javax/enterprise/context/Dependent.html))

The table columns describe these attributes for each bean: 

**Artifact:** The bean's type.  

**Bean EL Name:** Expression Language (EL) name for accessing the bean in a JSP 
or JSF page. 

**Qualifer:** Annotation applied to the bean for defining and selecting a bean
implementation. 

**Valid during (phase):** The [portlet
phases](/docs/7-2/frameworks/-/knowledge_base/f/cdi-dependency-injection/portlets)
in which the bean is valid. 

## Portlet Request Scoped Beans

These beans have the `@PortletRequestScoped` annotation. 

| Artifact | Bean EL Name | Qualifier | Valid during (phase) | 
| -------- | ------------ | --------- | -------------------- |
| `PortletConfig` | `portletConfig` | - | all | 
| `PortletRequest` | `portletRequest` | - | all | 
| `PortletResponse` | `portletResponse` | - | all | 
| `ActionRequest` | `actionRequest` | - | action |
| `ActionResponse` | `actionResponse` | - | action | 
| `HeaderRequest` | `headerRequest` | - | header | 
| `HeaderResponse` | `headerResponse` | - | header | 
| `RenderRequest` | `renderRequest` | - | render | 
| `RenderResponse` | `renderResponse` | - | render | 
| `EventRequest` | `eventRequest` | - | event | 
| `EventResponse` | `eventResponse` | - | event | 
| `ResourceRequest` | `resourceRequest` | - | resource | 
| `ResourceResponse` | `resourceResponse` | - | resource | 
| `StateAwareResponse` | `stateAwareResponse` | - | action, event
| `MimeResponse` | `mimeResponse` | - | header, render, resource | 
| `ClientDataRequest` | `clientDataRequest` | - | action, resource | 
| `RenderParameters` | `renderParams` | - | all |
| `MutableRenderParameters` | `mutableRenderParams` | - | action, event | 
| `ActionParameters` | `actionParams` | - | action | 
| `ResourceParameters` | `resourceParams` | - | resource | 
| `PortletContext` | `portletContext` | - | all |
| `PortletMode` | `portletMode` | - | all |
| `WindowState` | `windowState` | - | all |
| `PortletPreferences` | `portletPreferences` | - | all |
| `Cookies(List<Cookie>)` | `cookies` | - | all |
| `PortletSession` | `portletSession` | - | all | 
| `Locales(List<Locale>)` | `locales` | - | all |

## Dependent Scoped Beans 

These beans have the `@Dependent` scope annotation. 

| Artifact | Bean EL Name | Qualifier | Valid during (phase) |
| -------- | ------------ | --------- | -------------------- |
| `Namespace` (String) | `namespace` | `@Namespace` | all |
| `ContextPath` (String) | `contextPath` | `@ContextPath` | all |
| `WindowID` (String) | `windowId` | `@WindowId` | all |
| `Portlet name` (String) | `portletName` | `@PortletName` | all |

## Related Topics

[CDI Dependency Injection](/docs/7-2/frameworks/-/knowledge_base/f/cdi-dependency-injection)
