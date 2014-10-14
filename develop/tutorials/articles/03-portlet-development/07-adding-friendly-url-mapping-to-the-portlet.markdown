# Adding Friendly URL Mapping to the Portlet [](id=adding-friendly-url-mapping-to-the-portlet)

When you click the *Edit greeting* link, you're taken to a page with a URL that
looks like this:

    http://localhost:8080/web/guest/home?p_p_id=mygreeting_WAR_mygreetingportlet
        &p_p_lifecycle=0&p_p_state=normal&p_p_mode=view\&p_p_col_id=column-1&_my
        greeting_WAR_mygreetingportlet_mvcPath=%2Fedit.jsp
    
Since Liferay 6, there's a built-in feature that can easily change the ugly URL
above to this:

    http://localhost:8080/web/guest/home/-/my-greeting/edit
    
The feature is called friendly URL mapping. It takes unnecessary parameters out
of the URL and allows you to place the important parameters in the URL path,
rather than in the query string. To add this functionality, first edit
`liferay-portlet.xml` and add the following lines directly after `</icon>` and
before `<instanceable>` (remove the line breaks): 

    <friendly-url-mapper-class>com.liferay.portal.kernel.portlet.DefaultFriendl\
    yURLMapper</friendly-url-mapper-class>
    <friendly-url-mapping>my-greeting</friendly-url-mapping>
    <friendly-url-routes>com/liferay/samples/my-greeting-friendly-url-routes.xml\
    </friendly-url-routes>

Next, create the file (remove the line break):

    my-greeting-portlet/docroot/WEB-INF/src/com/liferay/samples/my\
    -greeting-friendly-url-routes.xml

Place the following content into the new file (remove the line break after
`{mvcPathName}.jsp`): 

    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 6.1.0//EN" 
    "http://www.liferay.com/dtd/liferay-friendly-url-routes_6_1_0.dtd">

    <routes>
        <route>
            <pattern>/{mvcPathName}</pattern>
            <generated-parameter name="mvcPath">/{mvcPathName}.jsp\
            </generated-parameter>
        </route>
    </routes>

Redeploy your portlet, refresh the page, and try clicking either of the links
again. 

![Figure 3.9: Friendly URL for view JSP](../../images/portlets-my-greeting-view-friendly.png)

Notice how much shorter and more user-friendly the URL is, without even having
to modify the JSPs. 

![Figure 3.10: Friendly URL for edit JSP](../../images/portlets-my-greeting-edit-friendly.png)

For more information on friendly URL mapping, there's a detailed discussion in
[*Liferay in Action*](http://manning.com/sezov). Our next step here is to
explore localization of the portlet's user interface. 
