# JavaScript - Single Page Application

## What is a Single Page Application?

A Single Page Application (SPA) is a web application that loads a single HTML 
page and dynamically updates that page as the user interacts and navigates 
through the site.  This is done by using AJAX to load new content instead of 
doing constant page reloads.  There are many benefits to using an SPA, including:

* Providing a more native-app like experience to the user.
* Decrease load time and amount of data that needs to be transfered from server to client.
* Improved user experience because the data can be loaded in the background.

## What is SennaJS?

SennaJS is what makes the SPA magic happen in Liferay Portal 7.  It is a 
lightweight, blazing-fast, open source single page application engine. It was 
built by an awesome team of developers here at Liferay as well as a bunch of 
great contributors.  It has been integrated into Liferay Portal 7 to leverage 
the benefits of a SPA.

## What features does SennaJS have?

**SEO & Bookmarkability**: Sharing or bookmarking a link should always display 
the same content. Sending a link to a friend should get them where we were. More 
than that, search engines are able to index that same content.

**Hybrid rendering**: Ajax + server-side rendering allows disable pushState at 
any time, allowing progressive enhancement. The way you render the server side 
doesn't matter, it can be simple HTML fragments or even template views.

**State retention**: Scrolling, reloading or navigating through the history of 
the page should get back to where it was.

**UI feedback**: When some content is requested, it indicates to the user that 
something is happening.

**Pending navigations**: Block UI rendering until data is loaded, then displays 
the content at once. It's important to give some UX feedback during loading.

**Timeout detection**: Timeout if the request takes too long to load or when 
navigating to a different link while another request is pending.

**History navigation**: By using History API you can manipulate the browser 
history, so you can use browser's back and forward buttons.

**Cacheable screens**: Once you load a certain surface this content is cached in 
memory and is retrieved later on without any additional request. This can really 
speed up your application.

**Page resources management**: Evaluate scripts and stylesheets from dynamic 
loaded resources. Additional content loaded using XMLHttpRequest can be appended 
to the DOM, for security reasons some browsers won't evaluate `<script>` tags 
from the new fragment. Therefore, the SPA engine should handle extracting 
scripts from the content and parsing them, respecting the browser contract for 
loading scripts.

## How do I enable SPA in Liferay Portal 7?

Easy.  All that is needed is to have the `frontend-js-spa-web` module deployed 
and enabled.

## How can I customize the SPA settings in my portal instance?

It's easy to customize the behavior of your Single Page Application Liferay 
Portal. 

1. Navigate to Control Panel -> Configuration -> System Settings

2. Select the Foundation tab

3. Select Frontend SPA Infrastructure

4. You should see options like the ones below

<img src="images/08-single-page-application_settings.png" />

**Cache Expiration Time**:  The time, in minutes, in which the SPA cache is 
cleared. A zero value means the cache should never expire during SPA navigation. 
A negative value means the cache should be disabled.

**Request Timeout Time**: The time, in milliseconds, in which a SPA request 
times out. A zero value means the request should never timeout.

**User Notification Time**: The time, in milliseconds, in which a notification 
is shown to the user stating that the request is taking longer than expected. A 
zero value means no notification should be shown.

## How can I leverage SPA lifecycle events in my portal instance?

When developing in a single page application, sometimes it is necessary to know 
when navigation has started or stopped.  Luckily SennaJS makes this easy by 
exposing lifecycle events that represent state changes in the application.  The 
available events are:

**beforeNavigate**: Fires before navigation starts. Event payload: 
`{ path: '/pages/page1.html', replaceHistory: false }`

**startNavigate**: Fires when navigation begins. Event payload: 
`{ form: '<form name="form"></form>', path: '/pages/page1.html', 
replaceHistory: false }`

**endNavigate**: Fired after the content has been retrieved and inserted onto 
the page. Event payload: `{ form: '<form name="form"></form>', path: 
'/pages/page1.html' }`

These events can be leveraged easily by listening for them on the Liferay global 
object.

```javascript

Liferay.on('beforeNavigate', function(event) {
    alert("Get ready to navigate to " + event.path);
});

```

The above code should result in behavior like this:

<img src="images/08-single-page-application_lifecycle-events.gif" />

## How do I exclude certain paths/portlets from SPA navigation?

If there is a certain portlet or page that you don't want to be part of the SPA, 
and instead require a full page navigation, you have some options.

**Portlet Blacklist**: When creating a portlet, the portlet class should have 
the property `_singlePageApplication`.  By setting this to false or overriding 
the `isSinglePageApplication` method, the portlet will be added to the blacklist.

**data-senna-off Annotation**:  When there is a specific link on a page that 
needs to be excluded from SPA navigation, doing so is as easy as adding the 
`data-senna-off` attribute to the element.  For example: 

```html
 <a data-senna-off="true" href="/pages/page2.html">Page 2</a>
```
