# Organizing Content with AlloyUI Rows and Columns [](id=organizing-content-with-alloyui-rows-and-columns)

Say goodbye to sleepless nights tossing, turning, and worrying about the
potentially daunting task of arranging content in your portlets! It doesn't have
to be complicated. AlloyUI's `aui:col` and `aui:row` tags simplify organizing
portlet content. This tutorial shows you how to use these tags in your portlet
JSPs. 

Here's how to add rows and columns of content to your portlet: 

1.  Create or edit the JSP file in which you want to organize content. 

2.  Reference the AlloyUI taglib `aui` by adding the following directive: 

        <%@ taglib prefix="aui" uri="http://liferay.com/tld/aui" %>

3.  Add an `aui:row` tag for each row of content you want. For example, the tags
    below create placeholders for two rows of content: 

        <aui:row>
        	
        </aui:row>
        <aui:row>
        	
        </aui:row>

4.  Insert and configure `aui:col` tags between each row's starting and ending
    `aui:row` tags. You can define each column's width using a `width` or `span`
    attribute. The `width` attribute takes precedence over the `span` attribute
    if they're both specified in a column. 

	The `width` value represents a percentage of the row's width. A `span` value
	represents the number of parts a column uses, out of the twelve parts in
	which the containing row is divided. For example, `<aui:col span="4">`
	specifies a column to use `4/12`, or `1/3`, of the row's width. 

    +$$$

    Note: before a column is rendered, every `width` attribute is automatically
    converted to a `span`. The `width` attribute's value is divided by `100`,
    multiplied by `12`, and rounded to the nearest whole number. For example,
    the `width` value in column `<aui:col width="33">` is divided by `100` and
    multiplied by `12` resulting in `3.96`, which is then rounded up to a `span`
    value of `4`-- the column is rendered across `4/12` the row's width. 

    $$$

Rows and columns are easy to use. As an example, here are a couple rows of
columns that demonstrate setting column widths using `width` and `span`
attributes: 

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

In the above example, the portlet's first row has three columns of equivalent
width. The second row's first column uses `1/3` of the row's width and its
second column uses the remaining `2/3` of the row's width. 

![Figure 1: Here's what the example's rows and columns look like.](../../images/columns-01.png)

There you have it! Now you know how to organize your portlet's content using
AlloyUI rows and columns. 

**Related Topics**

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)

[Themes and Layout Templates](/tutorials/-/knowledge_base/6-2/themes-and-layout-templates)

[Application Display Templates](/tutorials/-/knowledge_base/6-2/application-display-templates)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)
