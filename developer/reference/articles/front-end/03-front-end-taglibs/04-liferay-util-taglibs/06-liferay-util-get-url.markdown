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

```html
<liferay-util:get-url url="https://www.liferay.com/" />
```

Here is an example that uses the `var` attribute:

```html
<liferay-util:get-url url="https://www.liferay.com/" var="Liferay" />

<div>
				<h2>We borrowed <a href="https://www.liferay.com/">Liferay</a>. Here it is.</h2>

				<div class="Liferay">
								<%= Liferay %>
				</div>
</div>
```

![Figure 1: You can use the Liferay Util Get URL tag to scrape URLs.](../../../../images/liferay-util-get-url-ldn.png)

Now you know how to use the `<liferay-util:get-url>` tag to scrape URLs. 

## Related Topics

- [Using the Liferay Util Param Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-param)
- [Using the Liferay Util Include Tag](/docs/7-2/reference/-/knowledge_base/r/using-liferay-util-include)
- [Using the AUI Taglib](/docs/7-2/reference/-/knowledge_base/r/using-aui-taglibs-in-your-portlets)
