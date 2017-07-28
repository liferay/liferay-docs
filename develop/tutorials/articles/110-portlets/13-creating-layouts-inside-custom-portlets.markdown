# Creating Layouts inside Custom Portlets [](id=creating-layouts-inside-custom-portlets)

Page layout tags let you create layouts using Bootstrap 3 within your portlets.

This tutorial explains the `<aui:*>` tags that developers can use to create 
layouts.

## AUI Container [](id=aui-container)

The `<aui:container>` tag creates a container `<div>` tag to wrap `<aui:row>` 
components and offer additional styling.

It supports the following attributes:

| Attribute | Type | Description |
| --- | --- | --- |
| cssClass | String | A CSS class for styling the component |
| dynamicAttributes | Map<String, Object> | Map of `data-` attributes for your container |
| fluid | boolean | Whether to enable the container to span the entire width of the viewport. The default value is true |
| id | String | An ID for the component instance |

## AUI Row [](id=aui-row)

The `<aui:row>` tag creates a row to hold `<aui:col>` components.

It supports the following attributes:

| Attribute | Type | Description |
| --- | --- | --- |
| cssClass | String | A CSS class for styling the component |
| id | String | An ID for the component instance |

## AUI Col [](id=aui-col)

The `<aui:col>` tag creates a column to display content in an `<aui:row>` 
component.

It supports the following attributes:

| Attribute | Type | Description |
| --- | --- | --- |
| cssClass | String | A CSS class for styling the component.
| id | String | An ID for the component instance.
| lg | String | Comma separated string of numbers 1-12 to be used for Boostrap grid `col-lg-`
| md | String | Comma separated string of numbers 1-12 to be used for Boostrap grid `col-md-`
| sm | String | Comma separated string of numbers 1-12 to be used for Boostrap grid `col-sm-`
| xs | String | Comma separated string of numbers 1-12 to be used for Boostrap grid `col-xs-`
| span | int | The width of the column in the containing row as a fraction of 12. For example, a span of 4 would result in a column width 4/12 (or 1/3) of the total width of the containing row.
| width | int | The width of the column in the containing row as a percentage, overriding the span attribute. The width is then converted to a span expressed as ((width/100) x 12), rounded to the nearest whole number. For example, a width of 33 would be converted to 3.96, which would be rounded up to a span value of 4.

## Example JSP [](id=example-jsp)

Below is an example layout created in a portlet:

    <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
    
    <aui:container cssClass='super-awesome-container'>
            <aui:row>
                    <aui:col md="4" sm="6">
                            <h2>Some fun content using the 'md' and 'sm' attributes</h2>
                    </aui:col>
    
                    <aui:col md="8" sm="6">
                            <p>
                                    Some text here.
                            </p>
                    </aui:col>
            </aui:row>
    
            <aui:row>
                    <aui:col width="<%= 40 %>">
                            <h2>Some fun content using the 'width' attribute</h2>
                    </aui:col>
    
                    <aui:col width="<%= 60 %>">
                            <p>
                                    Cool text here.
                            </p>
                    </aui:col>
            </aui:row>
    
            <aui:row>
                    <aui:col span="<%= 4 %>">
                            <h2>Some fun content using the 'span' attribute</h2>
                    </aui:col>
    
                    <aui:col span="<%= 8 %>">
                            <p>
                                    Nice text here.
                            </p>
                    </aui:col>
            </aui:row>
    </aui:container>
 
Now you know how to create layouts inside your portlets!

## Related Topics [](id=related-topics)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-0/creating-layout-templates-with-the-themes-generator-0)
