# Setting the Display Category for the Guestbook Portlet

The Guestbook portlet doesn't have a display category specified yet, so there's 
no way to add it to a page. 

You'll specify the display category for the Guestbook portlet next.

## Defining the Component Metadata Properties

The display category for a portlet is defined in its component class as a 
metadata property. Since the Guestbook portlet is a way for users to communicate 
with each other, you'll add the Guestbook portlet to the Social category. Only 
one Guestbook portlet should be added to a page, so you'll also define it as a 
non-instanceable portlet.

Open the `GuestbookMVCPortlet` class and update the component class metadata 
properties to match the configuration below:

    @Component(
    	immediate = true,
    	property = {
    		"com.liferay.portlet.display-category=category.social",
    		"com.liferay.portlet.instanceable=false",
    		"com.liferay.portlet.scopeable=true",
    		"javax.portlet.display-name=Guestbook",
    		"javax.portlet.expiration-cache=0",
    		"javax.portlet.init-param.template-path=/",
    		"javax.portlet.init-param.view-template=/html/guestbookmvcportlet/view.jsp",
    		"javax.portlet.name=" + GuestbookPortletKeys.GUESTBOOK,
    		"javax.portlet.resource-bundle=content.Language",
    		"javax.portlet.security-role-ref=power-user,user",
    		"javax.portlet.supports.mime-type=text/html"
    	},
    	service = Portlet.class
    )
    
The `com.liferay.portlet.display-category=category.social` property sets the 
display category to *Social* for the Guestbook portlet. The 
`com.liferay.portlet.instanceable=false` property specifies that the Guestbook 
portlet is non-instanceable, so only one instance of the portlet can be added to 
a page. 
 
Now you can add the Guestbook portlet to a page! Go ahead and try it out. All 
@product@ apps are automatically SPA(Single Page Application) enabled by 
default, and require no changes to your apps or workflow, so the Guestbook 
portlet can take advantage of SPA features right away. You can read more about 
SPA in the [Automatic Single Page Applications](/develop/tutorials/-/knowledge_base/7-0/automatic-single-page-applications) 
tutorial.

In the next section, you'll create the Guestbook Admin portlet.