# MVC Action Command 
<!--Liferay's MVCActionCommand-->

Liferay's MVC framework supports splitting your action methods into separate
classes. This can be very helpful in larger applications with many action
methods.

If you're using Liferay's MVC framework, it's as simple as creating a portlet
class with your Component, 

@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.css-class-wrapper=portlet-greeter",
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Greeter Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.ftl",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class GreeterPortlet extends FreeMarkerPortlet {
}



@Component(
	immediate = true,
	property = {
		"javax.portlet.name=blade_portlet_GreeterPortlet",
		"mvc.command.name=greet"
	},
	service = MVCActionCommand.class
)
public class GreeterActionCommand implements MVCActionCommand {
