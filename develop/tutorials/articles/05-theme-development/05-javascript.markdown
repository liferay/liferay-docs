# JavaScript [](id=javascript)

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

 ![Figure 5.4: Content of main.js](../../images/05-themes-4.png)

Now let's make your theme configurable by defining settings. 
