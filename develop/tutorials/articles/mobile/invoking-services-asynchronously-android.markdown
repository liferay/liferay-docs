# Invoking Services Asynchronously from Your Android App [](id=invoking-services-asynchronously-from-your-android-app)

Android doesn't allow making synchronous HTTP requests from the main UI thread.
Synchronous HTTP requests must be made from threads other than the main UI
thread. For example, they can be made from an `AsyncTask` instance. 

The SDK can help you make asynchronous HTTP requests if you don't want to create
an `AsyncTask` yourself. Implement a callback class, instantiate it, and set the
callback instance on the session. When the SDK makes your service calls for that
session, it makes them asynchronously. To make synchronous calls again, set
`null` as the session's callback. 

This tutorial shows you how to implement asynchronous requests in your Android 
app. Go ahead and get started!

## Implementing Asynchronous Requests in Your Android App 

The following steps outline how to implement asynchronous requests in your app: 

1. Implement and instantiate your callback class.
2. Set the callback on the session.
3. Call Liferay services.

The following code is an example of an implementation of these steps: 

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;

    ...

    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {

        public void onFailure(Exception exception) {
            // Implement exception handling code
        }

        public void onSuccess(JSONArray result) {
            // Called after request has finished successfully
        }

    };

    session.setCallback(callback);
    userService.getGroupUsers(groupId);

Now take a closer look to see how this code asynchronously invokes the Liferay 
service. The code begins by importing the `AsyncTaskCallback` and 
`JSONArrayAsyncTaskCallback` callback classes. The latter class is related to 
the service's `JSONArray` return type. Next, the code implements and 
instantiates the callback class.

    ...
    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {
    ...

Lastly, it sets the callback on the session and calls the Liferay service.

    session.setCallback(callback);
    userService.getGroupUsers(groupId);

Now take a look at the methods defined in `JSONArrayAsyncTaskCallback`. The 
`onFailure()` method is called if an exception occurs during the request. This 
could be triggered by a connection exception (e.g., a request timeout) or a
`ServerException`. If a `ServerException` occurs, it's because something went
wrong on the server side. For instance, if you pass a `groupId` that doesn't
exist, the portal complains about it, and the SDK wraps the error message with a
`ServerException`.

There are multiple `AsyncTaskCallback` implementations, one for each method
return type:

- `JSONObjectAsyncTaskCallback`
- `JSONArrayAsyncTackCallback`
- `StringAsyncTaskCallback`
- `BooleanAsyncTaskCallback`
- `IntegerAsyncTaskCallback`
- `LongAsyncTaskCallback`
- `DoubleAsyncTaskCallback`

All you'll need to do is pick the appropriate implementation for your service 
method return type. In the example code snippet above, a 
`JSONArrayAsyncTaskCallback` instance was used since `getGroupUsers` returns a 
`JSONArray`. 

It's also possible to use a generic `AsyncTaskCallback` implementation called
`GenericAsyncTaskCallback`. For this implementation, you must implement a
transform method and handle JSON parsing yourself. 

+$$$

**Note:** If you still don't want to use
any of these callbacks, you can implement `AsyncTaskCallback` directly, but be
careful. You should always get the first element of the `JSONArray` passed as a
parameter to the `onPostExecute(JSONArray jsonArray)` method (i.e.,
`jsonArray.get(0)`).

$$$

After the request has finished, the `onSuccess()` method is called on the main
UI thread. Since the request is asynchronous, the service call immediately
returns a `null` object. The service delivers the service's real return value to
the callback's `onSuccess()` method, instead. 

Great! Now you know about invoking services asynchronously from your Android 
app.

## Related Topics

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
