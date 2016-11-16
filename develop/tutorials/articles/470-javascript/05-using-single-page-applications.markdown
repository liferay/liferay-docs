# Configuring Single Page Applications in @product@

A good user experience is the measure of a well-designed site. A user's time is 
highly valuable. The last thing you want is for someone to grow frustrated with 
your site because of constant page reloads. A Single Page Application (SPA) is 
the solution. Single Page Applications drastically cut down on load times, 
loading only a single HTML page that is dynamically updated as the user 
interacts and navigates through the site. This provides a native-app experience 
that users have grown accustomed to. Since Liferay Portal CE GA2 and Liferay DXP, 
SPA is enabled by default, so you get all these features and more out-of-the-box 
for your sites and apps!

<!-- You say your app is SPA enabled by default. Does this include custom apps 
that I create for Liferay? Or are there extra settings, code, etc. that I must 
add for the app to take advantage of SPA and Senna features?

If so, we should write a separate tutorial on Enabling SPA in your custom apps,
and just leave this one about SPA configuration. Then I can move some of the
info in this tutorial to a main intro article for a SPA section.

thoughts?

-->

This tutorial covers these key topics:

-  The benefits of SennaJS
-  How to configure SPA settings
-  How to listen to SPA lifecycle events

## The Benefits of SennaJS [](id=the-benefits-of-sennajs)

Let's say you're surfing the web and you find a really rad site that happens to 
be SPA enabled. Alright! Page load times are blazin' fast. You're deep into the 
site, scrolling along, when you find this great post that just speaks to you. 
You copy the URL from the address bar and email it to all of your friends with 
the subject: 'Your Life Will Change Forever'. They must experience this 
awe-inspiring work! You get a response back almost immediately. "This is a rad 
site, but what post are you talking about?," it reads. "What!? Do my eyes
deceive me," you'll exclaim. You were in too much of a hurry to share this 
life-changing content that you neglected to notice that the URL never updated 
when you clicked the post. You click the back button in hopes to get back to the 
post, but it takes you to the site you were on before you ever visited this one. 
What a bummer! "Why? Why have you failed me site!," you will cry. If only there 
was a way to have a Single Page Application, but also be able to link to the 
content you want. Well don't despair my friend. You can have your cake and eat 
it too, thanks to SennaJS.

SennaJS is @product@'s SPA engine. SennaJS handles the client-side data, while 
AJAX is used to load the page's content dynamically. While there are other 
JavaScript frameworks out there that may provide some of the same features, 
Senna's only focus is SPA, ensuring that your site provides the best user 
experience possible.

SennaJS provides the following key enhancements to SPA:

**SEO & Bookmarkability**: Sharing or bookmarking a link displays the same 
content you are viewing. Search engines are able to index this content.

**Hybrid rendering**: Ajax + server-side rendering allows you to disable 
pushState at any time, allowing progressive enhancement. You can use your 
preferred method to render the server side 
(e.g. HTML fragments or template views).

**State retention**: Scrolling, reloading, or navigating through the history of 
the page takes you back to where you were. 

**UI feedback**: The UI indicates to the user when some content is requested.

**Pending navigations**: UI rendering is blocked until data is loaded, then the 
content is displayed all at once.

**Timeout detection**: If the request takes too long to load or the user tries 
to navigate to a different link while another request is pending, the request 
times out.

**History navigation**: The browser history is manipulated, via the History API,
so you can use the back and forward history buttons to navigate through the 
history of the page.

**Cacheable screens**: Once a surface is loaded, the content is cached in memory 
and is retrieved later on without any additional request, speeding up your 
application.

**Page resources management**: Scripts and stylesheets are evaluated from 
dynamically loaded resources. Additional content can be appended to the DOM 
using XMLHttpRequest. For security reasons, some browsers won't evaluate 
`<script>` tags from content fragments. Therefore, SennaJS extracts scripts from 
the content and parses them to ensure that they meet the browser loading
requirements.

You can read more about SennaJS as well as see examples at [http://sennajs.com/](http://sennajs.com/).

Now that you have a better understanding of how SennaJS benefits SPA, you can 
learn how to configure options for SPA within your @product@ installation next.

## Customizing SPA Settings [](id=customizing-spa-settings)

As stated earlier, SPA is enabled by default in your @product@ installation and
applications. The default SPA configuration may suit your needs, however, there 
may be certain use cases where a custom configuration is more desirable.

Depending on what behaviors you need to customize, you can configure SPA options 
in one of two places. SPA caching and SPA timeout settings can be configured in 
System Settings. If you wish to disable SPA for a certain link, page, or portlet
in your site, you can do so within the corresponding element itself. All SPA 
configuration options are covered in this section.

### Configuring SPA System Settings [](id=configuring-spa-system-settings)

To configure system settings for SPA, follow these steps:

1. Navigate to *Configuration* &rarr; *System Settings* within the Control
   Panel.

2. Select the *Foundation* tab at the top of the page.

3. Click *Frontend SPA Infrastructure*.

+$$$

**Note:** In prior versions of Liferay, all SPA render requests that didn't 
belong to a portlet (no `p_p_id` in the URL) were cached indefinitely. This can 
confuse users, as the content they view will be cached, rather than the latest 
fresh content. Since Liferay Portal CE 7.0 GA2 and Liferay DXP 7.0 GA1, 
administrators can use the **Cache Expiration Time** property to set an 
expiration time for the Senna cache.

$$$

The following configuration options are available:

**Cache Expiration Time**:  The time, in minutes, in which the SPA cache is 
cleared. A zero value means the cache should never expire during SPA navigation. 
A negative value means the cache should be disabled.

**Request Timeout Time**: The time, in milliseconds, in which a SPA request 
times out. A zero value means the request should never timeout.

**User Notification Time**: The time, in milliseconds, in which a notification 
is shown to the user stating that the request is taking longer than expected. A 
zero value means no notification should be shown.

Now that you know how to configure system settings for SPA, you can learn how to
disable SPA for elements in your site next.

### Disabling SPA [](id=disabling-spa)

Certain elements of your page may require a page refresh to work properly. For 
example, you may have downloadable content that you want to share with the user. 
In these cases, SPA must be disabled for those specific elements.

If there is a certain portlet or page that you don't want to be part of the SPA, 
and instead require a full page navigation, you have some options:

-  Blacklist the portlet to disable SPA for the entire portlet
-  Use the `data-senna-off` annotation to disable SPA for a specific link

<!-- How do you disable SPA for a page? Since it's mentioned as an option, we need to demonstrate it. -->

To blacklist a portlet from SPA follow these steps:

1. Open your portlet's main class.

2. Set the `_singlePageApplication` property to false:

        _singlePageApplication = false;

3. Alternatively, you can override the [`isSinglePageApplication` method](https://docs.liferay.com/ce/portal/7.0/javadocs/portal-impl/com/liferay/portal/model/impl/PortletImpl.html#isSinglePageApplication--)
   of the portlet to return `false`.

To disable SPA for a link follow these steps:

1. Add the `data-senna-off` attribute to the link.

2. Set the value to `true`.

For example `<a data-senna-off="true" href="/pages/page2.html">Page 2</a>`

That's all you need to do to disable SPA in your app.

Now that you know how to disable SPA, you can learn about the available SPA
lifecycle events next.

## Listening to SPA Lifecycle Events [](id=listening-to-spa-lifecycle-events)

During development, you may need to know when navigation has started or stopped 
in your SPA. Luckily SennaJS makes this easy by exposing lifecycle events that 
represent state changes in the application.  The following events are available:

**beforeNavigate**: Fires before navigation starts. This event passes a JSON
object with the path to the content being navigated to and whether to update the
history. Below is an example event payload:

    { path: '/pages/page1.html', replaceHistory: false }

**startNavigate**: Fires when navigation begins. Below is an example event 
payload:

    { form: '<form name="form"></form>', path: '/pages/page1.html', 
    replaceHistory: false }

**endNavigate**: Fired after the content has been retrieved and inserted onto 
the page. This event passes the following JSON object:

    { form: '<form name="form"></form>', path: '/pages/page1.html' }
    
<!-- 

Can you explain the `startNavigate` and `endNavigate` payloads? I'm not sure how
to parse the form, for example `event.form` returns undefined in the alert. I
figure this is just because it is a object.(I'm sure that is why) Can you give
an example of using these last two events?

looking here: https://github.com/liferay/liferay-portal/blob/master/modules/apps/foundation/frontend-js/frontend-js-spa-web/src/main/resources/META-INF/resources/liferay/app/App.es.js#L79-L92
It seems form is possible if there is form data being sent, but not always...
-->

These events can be leveraged easily by listening for them on the Liferay global 
object.

For example, the JavaScript below alerts the user to "Get ready to navigate to"
the URL that has been clicked, just before SPA navigation begins.

    Liferay.on('beforeNavigate', function(event) {
        alert("Get ready to navigate to " + event.path);
    });
    
The alert takes advantage of the payload for the `beforeNavigate` event,
retrieving the URL from the `path` attribute of the JSON payload object.

The above code should result in behavior like this:

![Figure 1: You can leverage SPA lifecycle events in your apps.](../../images/private-messaging-before-navigate.png)

You've learned how to listen to SPA lifecycle events, which can be very useful
during development. Due to the nature of SPA navigation, global listeners that 
you create can become problematic over time if not handled properly. You'll
learn how to handle these listeners next.

### Detaching Global Listeners [](id=detaching-global-listeners)

SPA provides several improvements that highly benefit your site and users, but 
there is potentially some additional maintenance as a consequence. In a 
traditional navigation scenario, every page refresh resets everything, so you 
don't have to worry about what's left behind. In a SPA scenario, however, global 
listeners such as `Liferay.on` or `Liferay.after` or body delegates can become 
problematic. Every time you execute these global listeners, you add yet another 
listener to the globally persisted `Liferay` object. The result is multiple 
invocations of those listeners. This can obviously cause problems if not
handled.

To prevent this, you simply need to listen to the navigation event in order to 
detach your listeners. For example, for the function below:

    function fire(key,value){
        Liferay.fire(
            'category',{
                id: key,
                name: value
             }
        );
    }

You would use the following code to detach the event listeners:
<!-- Is this the same function(event) shown above?-->
    var onCategory = function(event) {...};
    
    var clearPortletHandlers = function(event) {
        if (event.portletId === '<%= portletDisplay.getRootPortletId() %>') {
            Liferay.detach('onCategoryHandler', onCategory);
            Liferay.detach('destroyPortlet', clearPortletHandlers);
        }
    };
    
    
    Liferay.on('category', onCategory);
    Liferay.on('destroyPortlet', clearPortletHandlers);

Now you know how to configure and use SPA in @product@!

## Related Topics [](id=related-topics)

[Configuring Modules for Liferay Portal's Loaders](/develop/tutorials/-/knowledge_base/7-0/configuring-modules-for-liferay-portals-loaders)

[Preparing your JavaScript Files for ES2015](/develop/tutorials/-/knowledge_base/7-0/preparing-your-javascript-files-for-es2015)

[Using ES2015 Modules in Your Portlet](/develop/tutorials/-/knowledge_base/7-0/using-es2015-modules-in-your-portlet)

