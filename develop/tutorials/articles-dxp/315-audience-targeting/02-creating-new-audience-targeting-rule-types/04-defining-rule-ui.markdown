---
header-id: defining-the-rules-ui
---

# Defining the Rule's UI

[TOC levels=1-4]

The Java code you've added to this point has assumed that a preset weather value
is available for comparing during the evaluation process. To let administrators
set that value, you must define a UI so your rule can be configured during the
view/save lifecycle. Create a `view.jsp`
file in your rule's module (e.g.,
`/src/main/resources/META-INF/resources/view.jsp`) and add the following logic:

```markup
<%
Map<String, Object> context = (Map<String, Object>)request.getAttribute("context");

String weather = (String)context.get("weather");
%>

<aui:fieldset>
    <aui:select name="weather" value="<%= weather %>">
        <aui:option label="sunny" value="sunny" />
        <aui:option label="clouds" value="clouds" />
        <aui:option label="snow" value="snow" />
        <aui:option label="rain" value="rain" />
    </aui:select>
</aui:fieldset>
```

The `weather` variable in the `context` map should be set for the weather rule.
When the user selects an option, it's passed from the view template to the
`populateContext` method.

![Figure 1: The weather rule uses a `select` drop-down box to set the weather value.](../../../images-dxp/select-box-rule.png)

| **Note:** The weather rule uses JSP templates to display the rule's view.
| Audience Targeting, however, is compatible with any UI technology. Visit the
| [Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-audience-targeting#selecting-a-ui-technology)
| section for details on how to use other UI technologies like FreeMarker.

You've created the weather rule and can now target users based on their weather
conditions. You can view the finished version of the weather rule by downloading
its
[ZIP file](https://portal.liferay.dev/documents/113763090/114000653/weather-7-1.zip/c1865f26-c4df-f4fa-a012-459e23d3b3c8?t=1565967914487).

Now you've created and examined a fully functional rule and have the knowledge
to create your own.
