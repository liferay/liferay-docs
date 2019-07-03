---
header-id: available-spa-lifecycle-events
---

# Available SPA Lifecycle Events

[TOC levels=1-4]

During development, you may need to know when navigation has started or stopped 
in your SPA. SennaJS makes this easy by exposing lifecycle events that 
represent state changes in the application. The available lifecycle events are 
listed in the table below:

| Event | Description | Ex payload |
| --- | --- | --- |
| `beforeNavigate` | Fires before navigation starts. This event passes a JSON object with the path to the content you are navigating to and whether to update the history. | `{ path: '/pages/page1.html', replaceHistory: false }` |
| `startNavigate` | Fires when navigation begins | `{ form: '<form name="form"></form>', path: '/pages/page1.html',
replaceHistory: false }` |
| `endNavigate` | Fired after the content has been retrieved and inserted onto
the page | `{ form: '<form name="form"></form>', path: '/pages/page1.html' }` |

These events can be leveraged easily by listening for them on the Liferay global 
object. For example, the JavaScript below alerts the user to "Get ready to 
navigate to" the URL that has been clicked, just before SPA navigation begins:

```javascript
Liferay.on('beforeNavigate', function(event) {
    alert("Get ready to navigate to " + event.path);
});
```

The alert takes advantage of the payload for the `beforeNavigate` event, 
retrieving the URL from the `path` attribute of the JSON payload object. 

![Figure 1: You can leverage SPA lifecycle events in your apps.](../../images/private-messaging-before-navigate.png)
