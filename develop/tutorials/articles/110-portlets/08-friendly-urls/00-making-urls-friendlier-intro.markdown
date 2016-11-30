# Making URLs Friendlier [](id=making-urls-friendlier)

This is a story of two URLs who couldn't be more different. One was full of
himself, and always wanted to show everyone (users and SEO services alike) just
how smart he was, by openly displaying all of the parameters he carried.  He was
happiest when he met new people and could tell they were intimidated and
confused by him.

    http://localhost:8080/group/guest/~/control_panel/manage?p_p_id=com_liferay_blogs_web_portlet_BlogsAdminPortlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName=%2Fblogs%2Fedit_entry&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_redirect=http%3A%2F%2Flocalhost%3A8080%2Fgroup%2Fguest%2F~%2Fcontrol_panel%2Fmanage%3Fp_p_id%3Dcom_liferay_blogs_web_portlet_BlogsAdminPortlet%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_mvcRenderCommandName%3D%252Fblogs%252Fview%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_orderBycol%3Dtitle%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_orderByType%3Dasc%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entriesNavigation%3D%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_cur%3D1%26_com_liferay_blogs_web_portlet_BlogsAdminPortlet_delta%3D20&_com_liferay_blogs_web_portlet_BlogsAdminPortlet_entryId=30836

The other was just, well, friendly. You knew only the important things about
her, because she was less concerned about looking smart, and more concerned
about those she interacted with. She didn't need to look fancy and complicated.
She aspired to be simple and kind to all the users and SEO services she
encountered.

    http://localhost:8080/web/guest/home/-/blogs/lunar-scavenger-hunt

If you want your application to be friendly to your users and to SEO services,
make your URLs friendlier. It only takes a couple steps, after all.

## Creating Friendly URL Routes [](id=creating-friendly-url-routes)

1. First create a `routes.xml` file in your application's web module (if a
multi-module build, in the pattern of Liferay's native Service Builder
applications). It's recommended to put it in a
`src/main/resources/META-INF/friendly-url-routes/` folder.

2. Add friendly URL routes, using as many `<route>` tags as you need friendly
URLs, like this:

    <?xml version="1.0"?>
    <!DOCTYPE routes PUBLIC "-//Liferay//DTD Friendly URL Routes 7.0.0//EN" "http://www.liferay.com/dtd/liferay-friendly-url-routes_7_0_0.dtd">

    <routes>
        <route>
            <pattern></pattern>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">normal</implicit-parameter>
        </route>
        <route>
            <pattern>/maximized</pattern>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">maximized</implicit-parameter>
        </route>
        <route>
            <pattern>/{entryId:\d+}</pattern>
            <implicit-parameter name="categoryId"></implicit-parameter>
            <implicit-parameter name="mvcRenderCommandName">/blogs/view_entry</implicit-parameter>
            <implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
            <implicit-parameter name="p_p_state">normal</implicit-parameter>
            <implicit-parameter name="tag"></implicit-parameter>
        </route>
        ...
    </routes>

Use `<pattern>` tags to define placeholder values for the parameters that
normally appear in the generated URL. This is just a mask. The beastly URL still
lurks beneath it.

Use `<implicit-parameter>` tags to define parameters that will always be the
same for the URL. For example, if you're dealing with a render URL, you can be
certain that the `p_p_lifecycle` parameter will always be `0`. There's no need
for this to be generated. You don't have to define these type of implicit
parameters, but it's a best practice. If you happen to forget one, or decide not
to define any of them, they'll just be generated as usual.

The implicit parameters with the name `mvcRenderCommandName` are very
important. If you're using an `MVCPortlet` with `MVCRenderCommand` classes, that
parameter comes from the `mvc.command.name` property in the `@Component` of your
`MVCRenderCommand` implementation. Basically, this determines what will be
rendered (for example, `view.jsp`).

    @Component(
        immediate = true,
        property = {
            "javax.portlet.name=" + BlogsPortletKeys.BLOGS, "mvc.command.name=/",
            "mvc.command.name=/blogs/view"
        },
        service = MVCRenderCommand.class
    )

## Implementing a Friendly URL Mapper [](id=implementing-a-friendly-url-mapper)

Once you have your URLs mapped in a `routes.xml` file, you need to provide an
implementation of the `FriendlyURLMapper` service. Just create a component that
specifies a `FriendlyURLMapper` service, with two properties:

1. One that sets the path to your `routes.xml` file in the property
`com.liferay.portlet.friendly-url-routes` property.

2. A `javax.portlet.name` property.

        @Component(
            property = {
                "com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
                "javax.portlet.name=" + BlogsPortletKeys.BLOGS
            },
            service = FriendlyURLMapper.class
        )

After that, implement the `FriendlyURLMapper` service. For your convenience,
`DefaultFriendlyURLMapper` provides a default implementation. If you extend
`DefaultFriendlyURLMapper` you only need to override one method,
`getMapping()`. In this method you just need to return a String that defines
the first part of your Friendly URLs. It's smart to name it after your
application. Here's what it looks like for Liferay's Blogs application:

    public class BlogsFriendlyURLMapper extends DefaultFriendlyURLMapper {

        @Override
        public String getMapping() {
            return _MAPPING;
        }

        private static final String _MAPPING = "blogs";

    }

All of the Blogs application's friendly URLs begin with the String set here
(`blogs`).

## Friendly URLs in Action [](id=friendly-urls-in-action)

Let's look at one of these Friendly URLs in action. If you add a blog entry in
Liferay, and then add the Blogs application to a page, click on the title of the
entry to see it. After that, look at the URL:

    http://localhost:8080/web/guest/home/-/blogs/lunar-scavenger-hunt

As specified in the friendly URL mapper class, `blogs` is the first part of the
friendly URL that comes after the Liferay part of the URL. The next part is
determined by a specific URL route in `routes.xml`:

	<route>
		<pattern>/{urlTitle}</pattern>
		<implicit-parameter name="categoryId"></implicit-parameter>
		<implicit-parameter name="mvcRenderCommandName">/blogs/view_entry</implicit-parameter>
		<implicit-parameter name="p_p_lifecycle">0</implicit-parameter>
		<implicit-parameter name="p_p_state">normal</implicit-parameter>
		<implicit-parameter name="tag"></implicit-parameter>
	</route>

Here, the `urlTitle` is a database field that's generated from the title the
author gives their blog post, and it's meant to be used in a URL. Since it's
already a parameter in the URL (see below), it's available for use in the
friendly URL.

    <portlet:renderURL var="viewEntryURL">
        <portlet:param name="mvcRenderCommandName" value="/blogs/view_entry" />
        <portlet:param name="urlTitle" value="<%= entry.getUrlTitle() %>" />
    </portlet:renderURL>

When a render URL for viewing a blog entry is invoked, the String defined in the
friendly URL mapper teams up with the `pattern` tag in your friendly URL routes
file, and you get a very friendly URL indeed, instead of some nasty, conceited,
unfriendly URL that's despised by users and SEO services alike.

