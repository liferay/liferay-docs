# Organizing Your Content with AlloyUI Columns and Rows 

Organizing the content in your portlet's UI doesn't have to be a large, 
complicated task. AlloyUI gives you a built in, easy to use solution for 
constructing portlet UIs. Say goodbye to those sleepless nights spent tossing, 
turning, and worrying about your portlet's UI! The AlloyUI `aui:col` and 
`aui:row` tags transform the potentially daunting process of organizing your 
portlet's UI into a simple task. This tutorial covers how to use these tags in 
your portlet. 

+$$$

**Note:** The `aui:column` and `aui:layout` tags previously used to create 
columns and layouts have been deprecated.

$$$

## Setting Up the AlloyUI aui:col and aui:row Tags

Adding columns and rows to your portlet's UI can be done quickly by using 
AlloyUI. The example in this tutorial uses the following steps to show how to 
add and use the `aui:col` and `aui:row` tags in the `view.jsp` of a portlet:

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Insert and Configure the AUI Row Tags.
- **Step 3:** Insert and Configure the AUI Col Tags inside of the Row Tags.

Once you finish these steps you'll be building columns and rows in no time 
flat!

### Step 1: Reference the AUI Taglib

1.  Open the `view.jsp` of your portlet. Create it if it doesn't already exist.

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

Now you're ready to use AUI tags in your portlet. Onwards to the second step!

### Step 2: Insert and Configure the aui:row Tags

In this step you'll add the rows to put your columns in. Still inside the 
`view.jsp`, add the `aui:row` tags. For example, the code here creates two rows:

        <aui:row>
        	
        </aui:row>
        <aui:row>
        	
        </aui:row>

Repeat this step to create as many rows as you need for your portlet. Now 
there's just one last step to go!

### Step 3: Insert and Configure the aui:col Tags Inside of the aui:row Tags

Adding columns is similar to adding rows. You simply place the `aui:col` tags 
inside of the `aui:row` tags. You can also define the width of each column in a 
row by using the `width` or `span` attributes. There are a few things to note 
about how these attributes work. First, each `span` value is represented as a 
fraction of 12 when determining the width of the column in its containing row. 
For example, `<aui:col span="4">` specifies a column width of 4/12, or 1/3, of 
the total width of the containing row. When using the `width` attribute, its 
value must be expressed a percentage of 100. The `width` value is then 
automatically converted to a `span` value before it is rendered. This conversion 
is done by dividing the `width` value by 100, multiplying by 12, and then 
rounding the result to the nearest whole number. For example, the `width` value 
of `<aui:col width="33">` is converted to 3.96, which is rounded up to a `span` 
value of 4 before the column width is rendered. Thus, the `span` value of 4 
and the `width` value of 33 are functionally equivalent. The example below uses 
these values with some sample content to demonstrate the width of the columns:

        <aui:row>
            <aui:col span="4">
                <p>
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
        		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
        		enim ad minim veniam, quis nostrud exercitation ullamco laboris 
        		nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor 
        		in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
        		nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
        		sunt in culpa qui officia deserunt mollit anim id est laborum."

        		Section 1.10.32 of "de Finibus Bonorum et Malorum", written by 
        		Cicero in 45 BC

        		"Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
        		accusantium doloremque laudantium, totam rem aperiam, eaque ipsa 
        		quae ab illo inventore veritatis et quasi architecto beatae 
        		vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia 
        		voluptas sit aspernatur aut odit aut fugit, sed quia"
                </p>
            </aui:col>
            <aui:col width="33">
                <p>
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
        		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
        		enim ad minim veniam, quis nostrud exercitation ullamco laboris 
        		nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor 
        		in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
        		nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
        		sunt in culpa qui officia deserunt mollit anim id est laborum."

        		Section 1.10.32 of "de Finibus Bonorum et Malorum", written by 
        		Cicero in 45 BC

        		"Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
        		accusantium doloremque laudantium, totam rem aperiam, eaque ipsa 
        		quae ab illo inventore veritatis et quasi architecto beatae 
        		vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia 
        		voluptas sit aspernatur aut odit aut fugit, sed quia"
                </p>
            </aui:col>
            <aui:col span="4">
                <p>
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
        		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
        		enim ad minim veniam, quis nostrud exercitation ullamco laboris 
        		nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor 
        		in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
        		nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
        		sunt in culpa qui officia deserunt mollit anim id est laborum."

        		Section 1.10.32 of "de Finibus Bonorum et Malorum", written by 
        		Cicero in 45 BC

        		"Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
        		accusantium doloremque laudantium, totam rem aperiam, eaque ipsa 
        		quae ab illo inventore veritatis et quasi architecto beatae 
        		vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia 
        		voluptas sit aspernatur aut odit aut fugit, sed quia"
                </p>
            </aui:col>
        </aui:row>
        <aui:row>
            <aui:col span="4">
                <aui:input type="textarea" name="comments" label="Comments"/>
                <aui:button value="Submit"/>
            </aui:col>
            <aui:col span="8">
                <p>
        		"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do 
        		eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut 
        		enim ad minim veniam, quis nostrud exercitation ullamco laboris 
        		nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor 
        		in reprehenderit in voluptate velit esse cillum dolore eu fugiat 
        		nulla pariatur. Excepteur sint occaecat cupidatat non proident, 
        		sunt in culpa qui officia deserunt mollit anim id est laborum."

        		Section 1.10.32 of "de Finibus Bonorum et Malorum", written by 
        		Cicero in 45 BC

        		"Sed ut perspiciatis unde omnis iste natus error sit voluptatem 
        		accusantium doloremque laudantium, totam rem aperiam, eaque ipsa 
        		quae ab illo inventore veritatis et quasi architecto beatae 
        		vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia 
        		voluptas sit aspernatur aut odit aut fugit, sed quia"
                </p>
            </aui:col>
        </aui:row>

The first row in this layout has three columns evenly divided into a third of 
the space of the row. The second row has two columns broken up into 1/3 and 2/3 
of the space of the row, respectively. A portlet with this configuration should 
look like the figure here:

![Figure 1: The two rows in this portlet contain columns of different widths.](../../images/columns-01.png)

There you have it! Now you know how to make columns and rows with AlloyUI.

## Related Topics

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
