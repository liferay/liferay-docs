## Defining the Rule's UI [](id=defining-the-rules-ui)

The Java code you've added to this point has assumed that a preset weather value
is available for comparing during the evaluation process. To let administrators
set that value, you must define a UI so your rule can be configured during the
view/save lifecycle. Create a `view.jsp`
file in your rule's module (e.g.,
`/src/main/resources/META-INF/resources/view.jsp`) and add the following logic:

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

The `weather` variable in the `context` map should be set for the weather rule.
When the user selects an option, it's passed from the view template to the
`populateContext` method.

![Figure 4: The weather rule uses a `select` drop-down box to set the weather value.](../../images-dxp/select-box-rule.png)

+$$$

The weather rule uses JSP templates to display the rule's view. Audience
Targeting, however, is compatible with any UI technology. Visit the
[Selecting a UI Technology](/develop/tutorial/-/knowledge_base/7-1/best-practices-for-rules#selecting-a-ui-technology)
section for details on how to use other UI technologies like FreeMarker.

$$$

Congratulations! You've created the weather rule and can now target users based
on their weather conditions. You can view the finished version of the weather
rule by downloading its
[ZIP file](https://customer.liferay.com/documents/10738/200086/weather.zip).

Now you've created and examined a fully functional rule and have the knowledge
to create your own.
