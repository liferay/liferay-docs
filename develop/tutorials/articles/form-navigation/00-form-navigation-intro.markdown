# Form Navigation [](id=form-navigation)

Liferay's Form Navigator framework helps you split big forms into smaller forms
called sections. Its [`form-navigator`](http://docs.liferay.com/portal/7.0-a1/taglibs/liferay-ui/form-navigator.html)
tag creates a navigation UI across these sections. It even lets you partition
the section links using categories. On top of that, you can order the section
links within each category. It's no surprise that Liferay Portal uses the Form
Navigator framework with its most complex forms. 

Liferay uses Form Navigator to divide forms for its portal settings, site
settings, and organization settings. What's great is that you can connect your
app's forms to Liferay's existing form navigators. For example, you can add to
Site Settings form pages for your app's features. You can add as many forms
(sections) as you like to Liferay settings pages that use the Form Navigator
framework. You can even add new form navigation categories (labeled partitions).
Whether you want to leverage form navigation within your app's UI or extend
Liferay's existing form navigation, the Form Navigator API and the
`form-navigator` tag make it easy.
