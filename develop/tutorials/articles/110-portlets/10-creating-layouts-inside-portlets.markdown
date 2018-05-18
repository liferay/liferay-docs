# Creating Layouts inside Custom Portlets [](id=creating-layouts-inside-custom-portlets)

Layout templates specify how your portlets and content are organized on your 
site pages. What if, instead, you want to organize your portlet's content? 
`<aui>` tags let you create layouts using Bootstrap within your 
portlets. This tutorial explains this process.

Follow these steps:

1.  Open your portlet's JSP and include the AUI taglib declaration if it's not 
    already included:
    
        <%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
 
2.  Wrap your portlet's content in 
    [`<aui:container>` tags](#aui-container-). 
    If you wish to only have a portion of your portlet's content in a layout, 
    wrap that portion with a `<aui:container>` tags.

3.  In between the `<aui:container>...</aui:container>` tags, add a set of 
    [`<aui:row>` tags](#aui-row-) 
    for each row that you want in your portlet's layout.

4.  Add a set of 
    [`<aui:col>` tags](#aui-col-) 
    for each column that you want in the row. Repeat this step for each row in 
    the layout.
 
A complete example is shown below:

    <aui:container>
      <aui:row>
        <aui:col md="12">
          <h1>My Custom Layout Portlet</h1>
        </aui:col>
      </aui:row>
      <aui:row>
        <aui:col md="4" sm="6">
          <h2>Column One</h2>
          <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
            Integer eget pulvinar quam. Vivamus ornare leo libero, sed 
            mollis urna aliquam ac. Duis porta sapien non felis varius, in 
            iaculis orci fermentum. Etiam quis molestie elit, in tempor 
            risus. Morbi varius elementum lectus at feugiat. Quisque 
            dapibus orci ac dui eleifend, ut ullamcorper nulla sagittis. 
            Ut ac scelerisque sem.
          </p>
        </aui:col>
        <aui:col md="8" sm="6">
        <h2>Column Two</h2>
          <p>
            Aliquam hendrerit augue sed nisl ullamcorper pulvinar. Donec 
            tristique congue erat ac condimentum. Suspendisse vehicula 
            nunc vel velit imperdiet dapibus. In hac habitasse platea 
            dictumst. Morbi eleifend arcu sit amet magna faucibus, vitae 
            posuere erat finibus. Sed hendrerit convallis ipsum id luctus. 
            Aliquam aliquam consequat turpis eu vulputate. Nulla vitae 
            libero lorem. Proin nec lacus et nunc laoreet posuere. 
            Vestibulum euismod vestibulum faucibus. Vivamus dolor justo, 
            malesuada ac libero ac, feugiat varius leo. Integer viverra 
            nisi vel fringilla aliquam.
          </p>
          <p>
            Suspendisse potenti. Mauris neque nisl, hendrerit a sem at, 
            rutrum dictum arcu. Ut aliquet tortor vel tortor interdum 
            dictum. Sed non sapien quam. Nunc aliquet in massa elementum 
            aliquam. Cras convallis tristique ante ut ultrices. Aenean 
            quis congue nulla. Integer in lacus lectus. Mauris maximus, 
            nibh sit amet pharetra laoreet, sem dolor eleifend metus, non 
            semper sem justo vel mauris. Praesent tristique quis risus 
            vulputate faucibus. Nullam feugiat diam vel elit pharetra, id 
            porta velit fringilla. Pellentesque metus justo, dictum et 
            dolor venenatis, pretium egestas massa. Donec risus nisi, 
            elementum in lectus id, imperdiet blandit mauris.
          </p>
        </aui:col>
      </aui:row>
    </aui:container>

![Figure 1: Custom layouts in your portlets let you organize your portlet's content with the user in mind.](../../images/portlets-layout.png)

The columns in the second row take advantage of Bootstrap's grid classes to 
create responsive layouts. On medium sized view ports, `column-1` is 33.33% 
width and `column-2` is 66.66% width, but on small sized view ports both 
`column-1` and `column-2` are 50% width.

![Figure 2: You can take advantage of Bootstrap's grid classes to create responsive layouts within your custom portlets.](../../images/portlets-layout-responsive.png)

## AUI Layout Tag Attributes [](id=aui-layout-tag-attributes)

This section contains a list of the available attributes for each tag along with 
a brief description of its purpose.

### AUI Container [](id=aui-container)

The `<aui:container>` tag creates a container `<div>` tag to wrap `<aui:row>` 
components and offer additional styling.

It supports the following attributes:

| Attribute | Type | Description |
| --- | --- | --- |
| cssClass | String | A CSS class for styling the component |
| dynamicAttributes | Map<String, Object> | Map of `data-` attributes for your container |
| fluid | boolean | Whether to enable the container to span the entire width of the viewport. The default value is true |
| id | String | An ID for the component instance |

### AUI Row [](id=aui-row)

The `<aui:row>` tag creates a row to hold `<aui:col>` components.

It supports the following attributes:

| Attribute | Type | Description |
| --- | --- | --- |
| cssClass | String | A CSS class for styling the component |
| id | String | An ID for the component instance |

### AUI Col [](id=aui-col)

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
 
Now you know how to create layouts inside your portlets!

## Related Topics [](id=related-topics)

[Layout Templates with the Liferay Theme Generator](/develop/tutorials/-/knowledge_base/7-1/creating-layout-templates-with-the-themes-generator)
