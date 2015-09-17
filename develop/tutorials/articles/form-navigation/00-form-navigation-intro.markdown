# Form Navigation [](id=form-navigation)

Liferay's Form Navigation framework helps you split big forms into smaller forms
called sections. Its `form-navigator` tag creates a navigation UI across these
sections. It even lets you partition the section links using categories. On top
of that, you can order the section links within each category. It's no surprise
that Liferay Portal uses the Form Navigation framework with its most complex forms. 

Liferay uses Form Navigation to divide forms for its portal settings, site
settings, and organization settings. What's great is that you can connect your
app's forms to their form navigators. For example, you can add to Site Settings
a forms for your app's features. You can add as many forms (sections) as you
like to Liferay settings pages that use the Form Navigation framework. You can
even add new form navigation categories (labeled partitions) to them. Whether
you want to leverage form navigation within your app's UI or integrate with
Liferay's existing form navigation pages, the Form Navigation API and the
`form-navigator` tag make it easy. 
