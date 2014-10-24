# Understanding Your Theme's JavaScript Callbacks in main.js [](id=understanding-your-themes-javascript-callbacks-in-main-js)

Liferay has a product called [AlloyUI](http://alloyui.com/), which is an
extension to Yahoo's YUI3 framework. Liferay Portal by default uses AlloyUI for
its JavaScript framework, so you can take advantage of AlloyUI or YUI3
in your themes. Inside your theme's `main.js` file, you'll find definitions for
three JavaScript callbacks:

- **AUI().ready(fn)** is executed after the HTML in the page finishes loading
  (minus loading any portlets via AJAX). 
- **Liferay.Portlet.ready(fn)** is executed for each of a page's portlets, after
  each portlet loads. The callback receives two parameters: `portletId` and
  `node`. The `portletId` parameter is the ID of the portlet that was just
  loaded. The `node` parameter is the AlloyUI `Node` object of the same portlet. 
- **Liferay.on('allPortletsReady', fn)** is executed after everything else
  (including AJAX portlets) finishes loading. 

The contents of the `main.js` file are listed below:

	AUI().ready(

		/*
		This function gets loaded when all the HTML, not including the portlets, is
		loaded.
		*/

		function() {
		}
	);

	Liferay.Portlet.ready(

		/*
		This function gets loaded after each and every portlet on the page.

		portletId: the current portlet's id
		node: the Alloy Node object of the current portlet
		*/

		function(portletId, node) {
		}
	);

	Liferay.on(
		'allPortletsReady',

		/*
		This function gets loaded when everything, including the portlets, is on
		the page.
		*/

		function() {
		}
	);

There you have it. You can leverage these JavaScript callbacks in your theme by
adding your own logic to the callbacks. 

## Related Topics [](id=related-topics)

 [Application Display Templates](/develop/tutorials/-/knowledge_base/6-2/application-display-templates)
 
 [Customizing Liferay Portal](/develop/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

