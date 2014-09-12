# Organizing Your Content with AlloyUI Columns and Rows 

Organizing the content in your portlets can be a large task to take on. Luckily,
AlloyUI has a built in, easy to use, solution in their tag library. The AlloyUI
Column and Row tags transform the daunting organization process into a simple 
task.

This tutorial will cover how to use the aui:column and aui:row tags in your 
portlet. No longer will you experience those sleepless nights, tossing and 
turning, worrying about how you'll organize the content in your portlets.

## Setting Up the AlloyUI Column and Row Tags

You can configure columns and rows pretty quickly using AlloyUI . The example 
below shows how to add and use the `aui:column` and `aui:row` tags in the 
`view.jsp` of a portlet:

- **Step 1:** Reference the AUI Taglib.
- **Step 2:** Configure the Layout Tags in the View JSP.
- **Step 3:** Insert and Configure the AUI Row Tags inside of the Layout Tags.
- **Step 4:** Insert and Configure the AUI Column Tags in the Row Tags.
 	
Go through each of these steps and you'll be building columns and rows in no
time flat!

### Step 1: Reference the AUI Taglib

1.  Open the `view.jsp` of your portlet. Create one if it does not already 
exist.

2.  Add a directive to reference the `aui` taglib:

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

Just like that you are ready to use the AUI tags in your portlet. Onward to step
2!

### Step 2: Configure the Layout Tags in the View JSP

1.  Inside the `view.jsp` of your portlet add the `aui:layout` tags at the 
bottom:

        <aui:layout>

        </aui:layout>

You have layed the foundation for your columns and rows. Onward to step 3!

### Step 3: Insert and Configure the AUI Row Tags inside of the Layout Tags

In this step you'll configure the rows in which to put your columns.

Still inside the `view.jsp` nest the `aui:row` tags inside of the 
`aui:layout` tags:

        <aui:layout>
        	<aui:row>
        	
        	</aui:row>
        	<aui:row>
        	
        	</aui:row>
        <aui:layout/>
        
Repeat the step above to create as many rows as you need for your portlet. One
last step to go!

### Step 4: Insert and Configure the AUI Column Tags inside of the Row Tags

The process to configure your columns is pretty similar to the process you went
through to configure your rows. In addition to the general configuration, you
can define the width of each column. The width is set using the `columnWidth`
attribute. The value must be a percentage of 100 with the following possible 
values: 10, 15, 20, 25, 28, 30, 33, 35, 40, 45, 50, 55, 60, 62, 65, 66, 70, 75, 
80, 85, 90, 95, 100. The example below has some sample content to demonstrate 
the borders of the columns.

Nest the `aui:column` tags inside of your `aui:row` tags:

        <aui:layout>
        	<aui:row>
        		<aui:column columnWidth="33" first="true">
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
        		</aui:column>
        		<aui:column columnWidth="33">
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
        		</aui:column>
        		<aui:column columnWidth="33" last="true">
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
        		</aui:column>
        	</aui:row>
        	<br>
        	<aui:row>
        	    <aui:column columnWidth="33" first="true">
        		    <aui:input type="textarea" name="comments" label="Comments"/>
        	        <aui:button value="Submit"/>
        		</aui:column>
        		<aui:column columnWidth="65" last="true">
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
        		</aui:column>
        	</aui:row>
        </aui:layout>
        
The layout above has been designed to have two rows. The first row has three
columns divided evenly into 33% each. The second row has two columns broken up
into 33% and 65% respectively. In addition to the `columnWidth` attribute, the
first and last columns of each row have been given the `first` and `last` 
attributes to recieve the appropriate CSS styling. With the configuration above,
your portlet should look like the figure below:

![Figure 1: Here's what the columns above look like.](../../images/columns-01.png)

There you have it. Making columns and rows with AlloyUI is a piece of cake, and
now you know how!

## Related Topics

 [Using Liferay UI Tabs and Sections](http://dev.liferay.com/tutorials/-/knowledge_base/using-liferay-ui-tabs-and-sections)
