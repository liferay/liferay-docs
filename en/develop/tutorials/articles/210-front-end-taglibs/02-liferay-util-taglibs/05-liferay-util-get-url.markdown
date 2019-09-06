---
header-id: using-liferay-util-get-url
---

# Using Liferay Util Get URL

[TOC levels=1-4]

The get URL tag scrapes the URL provided by the `url` attribute. If a value is 
provided for the `var` attribute, the content from the screen scrape is scoped 
to that variable. Otherwise, the scraped content is displayed where the taglib 
is used. 

A basic configuration for the `<liferay-util:get-url>` tag is shown below:
    
    <liferay-util:get-url url="https://portal.liferay.dev/" />
 
Here is an example that uses the `var` attribute:

    <liferay-util:get-url url="https://portal.liferay.dev/" var="ldn" />

    <div>
    				<h2>We stole <a href="https://portal.liferay.dev/">Liferay Portal</a>, here it is.</h2>

    				<div class="ldn">
    								<%= ldn %>
    				</div>
    </div>

![Figure 1: You can use the Liferay Util Get URL tag to scrape URLs.](../../../images/liferay-util-get-url-ldn.png)

Now you know how to use the `<liferay-util:get-url>` tag to scrape URLs. 

## Related Topics

[Using the Liferay Util Param Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-param)

[Using the Liferay Util Include Tag](/docs/7-1/tutorials/-/knowledge_base/t/using-liferay-util-include)

[Using the AUI Taglib](/docs/7-1/tutorials/-/knowledge_base/t/using-aui-taglibs-in-your-portlets)
