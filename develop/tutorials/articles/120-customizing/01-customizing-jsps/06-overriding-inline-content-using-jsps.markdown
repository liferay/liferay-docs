# Overriding Inline Content Using JSPs [](id=overriding-inline-content-using-jsps)

Some @product@ core content, such as tag library tags, can only be overridden
using JSPs ending in `.readme`. The suffix `.readme` facilitates finding them.
The code from these JSPs is now inlined (brought into @product@ Java source
files) to improve performance. @product@ ignores JSP files with the `.readme`
suffix. If you add code to a JSP `.readme` file and remove the `.readme` suffix,
@product@ uses that JSP instead of the core inline content. This tutorial shows
you how to make these customizations. 

+$$$

**Important:** This type of customization is a last resort. Your override may 
break due to the nature of this implementation, and core functionality can go
down with it. Liferay cannot guarantee that content overridden using JSP
`.readme` files can be upgraded. 

$$$

+$$$

**Warning:** Modifying a @product@ tag library tag affects all uses of that tag
in your @product@ installation. 

$$$

Here's how to override inline content using JSPs:

1.  Create a
    [Custom JSP Bag](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-custom-jsp-bag)
    for deploying your JSP. Note the module folder you're storing the
    JSPs in: the default folder is `[your
    module]/src/main/resources/META-INF/jsps/`

    +$$$

    **Note:** you can develop your JSP anywhere, but a Custom JSP Bag module 
    provides a straightforward way to build and deploy it.

    $$$

2.  Download the @product@ source code or browse the source code on
    [GitHub (Liferay Portal CE)](https://github.com/liferay/liferay-portal/tree/7.1.x). 

3.  Search the source code for a `.jsp.readme` file that overrides the tag
    you're customizing. 

    +$$$

    **Note:** Files ending in `-ext.jsp.readme` let you prepend or 
    append new content to existing content. Examples include the
    `bottom-test.jsp.readme`, `bottom-ext.jsp.readme`,
    `body_top-ext.jsp.readme`, and `body_bottom-ext.jsp.readme` files in
    the @product@ application's `portal-web/docroot/html/common/themes` folder. 

    $$$

4.  Copy the `.jsp.readme` file into your project and drop the `.readme` suffix.
    Use the same relative file path @product@ uses for the `.jsp.readme` file.
    For example, if the file in @product@ is

        portal-web/docroot/html/taglib/aui/fieldset/start.jsp.readme

    use file path 

        [your module]/src/main/resources/META-INF/jsps/html/taglib/aui/fieldset/start.jsp

5.  Familiarize yourself with the current UI content and logic, so you can
    override it appropriately. Tag library tag content logic, for example, is in
    the respective `*Tag.java` file under
    `util-taglib/src/com/liferay/taglib/[tag library]/`. 

6.  Develop your new logic, keeping in mind the current inline logic you're 
    replacing. 

7.  Deploy your JSP. 

@product@ uses your JSP in place of the current inline logic. If you want
to walk through an example override, continue with this tutorial. Otherwise,
congratulations on a modified `.jsp.readme` file to override core inline
content! 

## Example: Overriding the fieldset Taglib Tag [](id=example-overriding-the-fieldset-taglib-tag)

This example demonstrates changing the `liferay:aui` tag library's  `fieldset`
tag. Browsing the @product@ web application or the source code at
`portal-web/docroot/html/taglib/aui/fieldset` reveals these files:

- `start.jsp.readme`
- `end.jsp.readme` 

They can override the logic that creates the start and end of the `fieldset`
tag. The `FieldsetTag.java` class's `processStart` and `processEnd` methods
implement the current inline content. Here's the
[`processStart`](https://github.com/liferay/liferay-portal/blob/7.1.0-ga1/util-taglib/src/com/liferay/taglib/aui/FieldsetTag.java#L86-L141)
method:

	@Override
	protected int processStartTag() throws Exception {
		JspWriter jspWriter = pageContext.getOut();

		jspWriter.write("<fieldset class=\"fieldset ");
		jspWriter.write(GetterUtil.getString(getCssClass()));
		jspWriter.write("\" ");

		String id = getId();

		if (id != null) {
			jspWriter.write("id=\"");
			jspWriter.write(id);
			jspWriter.write("\" ");
		}

		jspWriter.write(
			InlineUtil.buildDynamicAttributes(getDynamicAttributes()));

		jspWriter.write(StringPool.GREATER_THAN);

		String lable = getLabel();

		if (lable != null) {
			jspWriter.write(
				"<legend class=\"fieldset-legend\"><span class=\"legend\">");

			MessageTag messageTag = new MessageTag();

			messageTag.setKey(lable);
			messageTag.setLocalizeKey(getLocalizeLabel());

			messageTag.doTag(pageContext);

			String helpMessage = getHelpMessage();

			if (helpMessage != null) {
				IconHelpTag iconHelpTag = new IconHelpTag();

				iconHelpTag.setMessage(helpMessage);

				iconHelpTag.doTag(pageContext);
			}

			jspWriter.write("</span></legend>");
		}

		if (getColumn()) {
			jspWriter.write("<div class=\"row\">");
		}
		else {
			jspWriter.write("<div class=\"\">");
		}

		return EVAL_BODY_INCLUDE;
	}

The code above does this:

1.  Write `<fieldset class=\"fieldset `starting tag. 

2.  Write the CSS class name attribute. 

3.  If the tag has an ID, add the `id` as an attribute. 

4.  Write the tag's dynamic attribute (map). 

5.  Close the starting `fieldset` tag. 

6.  Get the tag's `label` attribute. 

7.  Write the starting `legend` element. 

8.  Use `getLocalizeLabel()` to add the localized label in the `legend`.

9.  If there's a help message (retrieved from `getHelpMessage()`), write it in 
    an `icon-help-tag`. 

10. Write the closing `legend` tag. 

11. If there's a column attribute, write `<div class=\"row\">`; else write 
    `<div class=\"\">`. 

Replicating the current logic in your custom JSP helps you set up the tag
properly for customizing. The `init.jsp` for `fieldset` initializes all the
variables required to create the starting tag. You can use the variables in the
`start.jsp`. The logic from `FieldsetTag`'s `processStart` method converted to
JSP code for `start.jsp` (renamed from `start.jsp.readme`) would look like this: 

    <%@ include file="/html/taglib/aui/fieldset/init.jsp" %>

    <fieldset class="fieldset <%= cssClass %>" <%= Validator.isNotNull(id) ? "id=\"" + id + "\"" : StringPool.BLANK %> <%= InlineUtil.buildDynamicAttributes(dynamicAttributes) %>>
    	<c:if test="<%= Validator.isNotNull(label) %>">
    		<legend class="fieldset-legend">
    			<span class="legend">
    				<liferay-ui:message key="<%= label %>" localizeKey="<%= localizeLabel %>" />

    				<c:if test="<%= Validator.isNotNull(helpMessage) %>">
    					<liferay-ui:icon-help message="<%= helpMessage %>" />
    				</c:if>
    			</span>
    		</legend>
    	</c:if>

    	<div class="<%= column ? "row" : StringPool.BLANK %>">

+$$$

**Tip:** A `*Tag.java` file's history might reveal original JSP code that was 
inlined. For example, the logic from `fieldset` tag's 
[`start.jsp`](https://github.com/liferay/liferay-portal/blob/df22ba66eff49b76404cfda908d3cd024efbebd9/portal-web/docroot/html/taglib/aui/fieldset/start.jsp)
was inlined in
[this commit](https://github.com/liferay/liferay-portal/commit/7fba0775bcc1d1a0bc4d107cabfb41a90f15937c#diff-2ad802b4c0d8f7a2da45b895e89d6e46).

$$$

On deploying the `start.jsp`, the `fieldset` tags render the same as they did
before. This is expected because it uses the same logic as `FieldsetTag`'s
`processStart` method. 

![Figure 1: @product@'s home page's search and sign in components are in a `fieldset`.](../../../images/jsp-readme-inline-fieldset.png)

The `fieldset` starting logic is ready for customization. To test that this
works, you'll print the word *test* surrounded by asterisks before the end of
the `fieldset` tag's starting logic. Insert this line before the `start.jsp`'s
last `div` tag: 

    <c:out value="**********test**********"/>

Redeploy the JSP and refresh the page to see the text printed above the
`fieldset`'s fields. 

![Figure 2: Before the `fieldset`'s nested fields, it prints *test* surrounded by asterisks.](../../../images/jsp-readme-override-inline-fieldset.png)

You know how to override specific @product@ core inline content using Liferay's
`.jsp.readme` files. 

## Related Topics [](id=related-topics)

[Customizing JSPs with Dynamic Includes](/develop/tutorials/-/knowledge_base/7-1/customizing-jsps-with-dynamic-includes)

[JSP Overrides Using Portlet Filters](/develop/tutorials/-/knowledge_base/7-1/jsp-overrides-using-portlet-filters)
