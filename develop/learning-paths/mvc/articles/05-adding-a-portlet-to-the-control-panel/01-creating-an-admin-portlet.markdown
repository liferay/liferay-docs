# Creating an Admin Portlet [](id=creating-an-admin-portlet)

To create the Guestbook Admin portlet, you'll use the New Liferay Portlet
wizard. You used this wizard in an earlier learning path when you created the
Guestbook portlet. The New Liferay Portlet wizard creates various required
configuration files such as `portlet.xml`, `liferay-portlet.xml`, and
`liferay-display.xml` and adds appropriate entries to them. The information that
you specify in the wizard is added to various elements and attributes in these
configuration files. It also determines the names and locations of other
required files such as the portlet class and the location of the portlet's JSP
files.

## Using the New Liferay Portlet Wizard [](id=using-the-new-liferay-portlet-wizard)

To create the Guestbook Admin portlet, follow these steps:

1. In Eclipse, right-click on your *guestbook-portlet* project and select *New*
   &rarr; *Liferay Portlet*.
2. For the Portlet class, enter *GuestbookAdminPortlet*.
3. For the Java package, select *com.liferay.docs.guestbook.portlet*.
4. For the Superclass, select *com.liferay.util.bridges.mvc.MVCPortlet*, then
   click *Next*.
5. Under Portlet Info, enter *guestbook-admin* for the Name and *Guestbook
   Admin* for the Display name and Title.
6. Under Resources, make sure that *Create JSP files* is checked and enter
   */html/guestbookadmin* for the JSP folder. Click *Next*.
7. Under Liferay Display, select *category.hidden*.
8. Check the *Add to Control Panel* button.
9. For the Entry Category, select *Site Administration - Content*.
10. For the Entry Weight, leave the default of *1.5*. Click *Finish*.

What did the New Liferay Portlet wizard do behind the scenes? To start
reviewing the changes, first check your project's `portlet.xml` file. The wizard
added a new `<portlet>` element for the Guestbook Admin portlet. Notice that the
portlet name, display name, portlet title, and portlet class that you specified
in the wizard are used in the `<portlet-name>`, `<display-name>`,
`<portlet-info>` and `<portlet-class>` sub-elements of the `<portlet>` element
of the Guestbook Admin portlet:

	<portlet>
		<portlet-name>guestbook-admin</portlet-name>
		<display-name>Guestbook Admin</display-name>
		<portlet-class>
			com.liferay.docs.guestbook.portlet.GuestbookAdminPortlet
		</portlet-class>
                ...
		<portlet-info>
			<title>Guestbook Admin</title>
			<short-title>Guestbook Admin</short-title>
			<keywords></keywords>
		</portlet-info>
                ...
	</portlet>

Note the value of the `<portlet-name>` element: *guestbook-admin*.

Next, check your project's `liferay-portlet.xml` file. While the `portlet.xml`
file defines portlet attributes specified by the JSR-286 portlet specification,
the `liferay-portlet.xml` file defines Liferay-specific portlet attributes. The
wizard added a new `<portlet>` element in `liferay-portlet.xml` for the
Guestbook Admin portlet, just like it did in `portlet.xml`:

	<portlet>
		<portlet-name>guestbook-admin</portlet-name>
		<icon>/icon.png</icon>
		<control-panel-entry-category>
			site_administration.content
		</control-panel-entry-category>
		<control-panel-entry-weight>1.5</control-panel-entry-weight>
		<header-portlet-css>/css/main.css</header-portlet-css>
		<footer-portlet-javascript>
			/js/main.js
		</footer-portlet-javascript>
		<css-class-wrapper>guestbook-admin-portlet</css-class-wrapper>
	</portlet>

The `<portlet-name>` subelement of the `<portlet>` element in
`liferay-portlet.xml` specifies *guestbook-admin*, exactly matching the
`<portlet-name>` value in `portlet.xml`.

The `<control-panel-entry-category>` element determines the section of the
Control Panel to which the Guestbook Admin portlet is added. Remember that
Liferay's Control Panel is divided into two main sections: the Site
Administration area (accessible via *Admin* &rarr; *Site Administration* from
the Dockbar) and the Control Panel proper (accessible via *Admin* &rarr;
*Control Panel* from the Dockbar). Notice that the value of the
`<control-panel-entry-category>` is *site_administration.content*, which means
that you have to navigate to *Admin* &rarr; *Content* from the Dockbar to view
your portlet, as you specified in the wizard. The `<control-panel-entry-weight>`
value determines where the Guestbook portlet appears in the list of portlets in
the Site Administration area's Content section in the Control Panel. You
can use decimals to make your portlet appear after one portlet in the list but
before another portlet in the list.

The New Liferay Portlet wizard also added the following entry to
`liferay-display.xml`:

    <category name="category.hidden">
        <portlet id="guestbook-admin"></portlet>
    </category>

The category specified here determines where (or if) your portlet appears in the
list of portlets that are available to be added to a portal page. When you click
on the *Add* button at the left side of the Dockbar (the plus symbol) and select
*Applications*, you'll see various expandable categories of portlets that you
can add to a page. For example, the *Highlighted* category contains commonly
used portlets such as Web Content Display, Asset Publisher, and Navigation. By
specifying *category.hidden* as the category of the Guestbook Admin portlet, you
ensure that it can't be added to a portal page via the Dockbar. This is fine
since we've already added it to the Content section of the Site Administration
area of the Control Panel. If you didn't set the Guestbook Admin portlet's
category to *category.hidden* in `liferay-display.xml`, a site administrator
could add the portlet a portal page. If you want a portlet to be accessible via
the Control Panel and *only* via the Control Panel, remember to specify its
category as *category.hidden* in `liferay-display.xml`.

Notice also that the New Liferay Portlet wizard created a
`GuestbookAdminPortlet.java` file for you in the
`com.liferay.docs.guestbook.portlet` package of the `docroot/WEB-INF/src`
folder, as you specified. The `GuestbookAdminPortlet` class extends Liferay's
`MVCPortlet` class, so it works out-of-the-box. The `<portlet-class>` subelement
of the Guestbook Admin portlet's `<portlet>` element in `portlet.xml` points to
the `GuestbookAdminPortlet.java` file's location. The New Liferay Portlet wizard
also created a `docroot/html/guestbookadmin` with a sample `view.jsp` file, as
you specified. Now that you've created your Guestbook Admin portlet in the
guestbook-portlet project, you need to add your desired functionality to it: the
abilities to add, edit, and delete `guestbook`s and to configure their
permissions. You'll start by adding some custom services to
`GuestbookLocalServiceImpl`, then you'll add the necessary action methods to
`GuestbookAdminPortlet`, and lastly you'll create the user interface for the
Guestbook Admin portlet.

