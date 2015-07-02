# Embedding Portlets in a Theme

A frequently used capability that Liferay provides when building a site is the
ability to embed a portlet in a theme. This allows the portlet to be visible on
all pages where the theme is used. In the past, this was only possible by
hardcoding a specific portlet into the theme, which has many drawbacks. Liferay
now provides the *Portlet Providers* framework that only requires you specify
the element type and action to be displayed. Based on the given element type and
action, Liferay can distinguish which deployed portlet to use. This increases
the flexibility and modularity of theming in Liferay Portal.

In this tutorial, you'll learn how to declare an element type and action in a
custom theme, and create a module that finds the correct portlet to use based on
those given parameters.

## Adding a Portlet to a Custom Theme


