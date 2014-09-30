# Using Portlet Namespacing [](id=using-portlet-namespacing)

Namespacing ensures that a given portlet's name is uniquely associated with
elements in request parameters it sends to the portal. This prevents name
conflicts with other elements on the portal page and with elements from other
portlets on the page. Namespacing your portlet elements is easy. Simply use the
`<portlet:namespace />` tag to produce a unique value for your portlet's
elements. The following example code uses the `<portlet:namespace />` tag to
reference the portlet's *fm* form during submission:

	submitForm(document.<portlet:namespace />fm);

To illustrate the benefits of namespacing an element, such as the *fm* form from
the example code above, suppose you have portlets named A and B in your portal
and they both have a form named *fm*. Without portlet namespacing, the portal
would be unable to differentiate between the two forms and, likewise, would be
unable to determine their associated portlets. But, submitting both portlet A's
form and portlet B's form as `<portlet:namespace />fm` would distinguish the
forms as *_Afm* and *_Bfm*, respectively. Liferay associates each namespaced
element, such as these namespaced forms, with the portlet that produced it.

By default, Liferay only allows *namespaced* parameters to access portlets.
However, many third-party portlets send *unnamespaced* parameters. Therefore,
Liferay gives you the option to turn off the unnamespaced parameters filter for
portlets, to avoid third-party portlets from breaking. To turn the filter off
for a portlet, navigate to the portlet's `liferay-portlet.xml` file and enter
the following tag:

	<requires-namespaced-parameters>false</requires-namespaced-parameters>

Turning this filter off is on a per portlet basis, so you'll need to set the
`<requires-namespaced-parameters/>` tag to false for every third-party portlet
that sends unnamespaced parameters.

Great! You now know how portlet namespacing is implemented for Liferay portlets.
We discussed a brief scenario using portlet namespacing for two different
portlets. Lastly, we learned when and why to use unnamespaced parameters in your
Liferay portlets.

