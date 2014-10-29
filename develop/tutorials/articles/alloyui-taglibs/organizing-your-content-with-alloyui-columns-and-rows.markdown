# Organizing Your Content with AlloyUI Columns and Rows 

Organizing the content in your portlets can be a large task to take on. Luckily,
AlloyUI has a built in, easy to use, solution in their tag library. The AlloyUI
Col and Row tags transform the daunting organization process into a simple 
task.

This tutorial will cover how to use the aui:col and aui:row tags in your 
portlet. 

+$$$

**Note:** The aui:column and aui:layout tags that were previously used to create 
columns and layouts has since been deprecated. The aui:col tag is now the
preferred method.

$$$

No longer will you experience those sleepless nights, tossing and turning, 
worrying about how you'll organize the content in your portlets.

## Setting Up the AlloyUI Col and Row Tags

You can configure columns and rows pretty quickly using AlloyUI . The example 
below shows how to add and use the `aui:col` and `aui:row` tags in the 
`view.jsp` of a portlet:

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Insert and Configure the AUI Row Tags.
- **Step 3:** Insert and Configure the AUI Col Tags inside of the Row Tags.
 	
Go through each of these steps and you'll be building columns and rows in no
time flat!

### Step 1: Reference the AUI Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

Just like that you are ready to use the AUI tags in your portlet. Onward to step
2!

### Step 2: Insert and Configure the AUI Row Tags

In this step you'll configure the rows in which to put your columns.

Still inside the `view.jsp` add the `aui:row` tags:

        	<aui:row>
        	
        	</aui:row>
        	<aui:row>
        	
        	</aui:row>

Repeat the step above to create as many rows as you need for your portlet. One
last step to go!

### Step 3: Insert and Configure the AUI Col Tags inside of the Row Tags

The process to configure your columns is pretty similar to the process you went
through to configure your rows. In addition to the general configuration, you
can define the width of each column. The width is set using either the `width`
or the `span` attribute. The value for the `width` must be a percentage of 100. 
The width then gets rendered as the span value expressed as 
(width/100)x12(rounded to the nearest whole number). It's important to note that 
the `width` attribute overrides the `span` attribute. The value for the `span` 
attribute must be a fraction out of 12. For example, a span value of 3 would 
divide the column into 3/12 or 1/4 of the total width. The example below has 
some sample content to demonstrate the borders of the columns.

Nest the `aui:col` tags inside of your `aui:row` tags:

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

The layout above has been designed to have two rows. The first row has three
columns divided evenly into a third of the space each. The second row has two 
columns broken up into 4/12 and 8/12 respectively. With the configuration above, 
your portlet should look like the figure below:

![Figure 1: Here's what the columns above look like.](../../images/columns-01.png)

There you have it. Making columns and rows with AlloyUI is a piece of cake, and
now you know how!

## Related Topics

 [Using Liferay UI Tabs and Sections](/tutorials/-/6-2/knowledge_base/using-liferay-ui-tabs-and-sections)
