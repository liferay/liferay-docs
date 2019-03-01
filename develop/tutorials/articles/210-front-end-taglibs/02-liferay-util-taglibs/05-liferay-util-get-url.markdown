# Using Liferay Util Get URL [](id=using-liferay-util-get-url)

The get URL tag scrapes the URL provided by the `url` attribute. If a value is 
provided for the `var` attribute, the content from the screen scrape is scoped 
to that variable. Otherwise, the scraped content is displayed where the taglib 
is used. 

A basic configuration for the `<liferay-util:get-url>` tag is shown below:
    
    <liferay-util:get-url url="https://dev.liferay.com/en/develop/tutorials" />
 
Here is an example that uses the `var` attribute:

    <liferay-util:get-url url="https://dev.liferay.com/en/develop/tutorials" var="ldn" />

    <div>
    				<h2>We stole <a href="https://dev.liferay.com/en/develop/tutorials">LDN</a>, here it is.</h2>

    				<div class="ldn">
    								<%= ldn %>
    				</div>
    </div>

![Figure 1: You can use the Liferay Util Get URL tag to scrape URLs.](../../../images/liferay-util-get-url-ldn.png)

Now you know how to use the `<liferay-util:get-url>` tag to scrape URLs. 

## Related Topics [](id=related-topics)

[Using the Liferay Util Param Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-param)

[Using the Liferay Util Include Tag](/develop/tutorials/-/knowledge_base/7-1/using-liferay-util-include)

[Using the AUI Taglib](/develop/tutorials/-/knowledge_base/7-1/using-aui-taglibs-in-your-portlets)
