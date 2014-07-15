# Understanding Your Theme's JavaScript Callbacks in *main.js* [](id=understanding-your-themes-javascript-callbacks-in-main-js)

Liferay has its own JavaScript library called AlloyUI, an extension to Yahoo's
YUI3 framework. You can take advantage of AlloyUI or YUI3 in your themes. Inside
your theme's `main.js` file, you'll find definitions for three JavaScript
callbacks:

- **AUI().ready(fn):** Executed after the HTML in the page has finished loading
  (minus any portlets loaded via AJAX). 
- **Liferay.Portlet.ready(fn):** Executed after each portlet on the page has
  loaded. The callback receives two parameters: `portletId` and `node`.
  `portletId` is the ID of the portlet that was just loaded. `node` is the Alloy
  Node object of the same portlet. 
- **Liferay.on('allPortletsReady', fn):** Executed after everything else
  (including AJAX portlets) has finished loading. 

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

There you have it. Hopefully now you have a better understanding of the 
JavaScript callbacks in your theme.

## Next Steps
<!-- URL will probably need updated when added to the new devsite-->
 [Using Developer Mode with Themes](/tutorials/-/knowledge_base/using-developer-mode)
 
 [Making Themes Configurable with Settings](/tutorials/-/knowledge_base/making-themes-configurable)

 [Specifying Color Schemes for Your Themes](/tutorials/-/knowledge_base/specifying-color-schemes-for-your-theme)

