# Invoking Services Asynchronously from Your Android App [](id=invoking-services-asynchronously-from-your-android-app)

Android doesn't allow synchronous HTTP requests to be made from the main UI 
thread. You can use Android's 
[`AsyncTask`](http://developer.android.com/reference/android/os/AsyncTask.html) 
to make synchronous requests from threads other than the main UI thread. If you 
don't want to use `AsyncTask`, you can make asynchronous requests through the 
Mobile SDK. To do so, you need to implement and instantiate a callback class, 
and then set it to the session. When the Mobile SDK makes your service calls for 
that session, it then makes them asynchronously. To make synchronous calls 
again, set `null` as the session's callback. 

With the following steps, this tutorial shows you how to implement asynchronous 
requests in your Android app: 

1. Implement and instantiate your callback class.
2. Set the callback on the session.
3. Call Liferay services.

Now go ahead and get started! 

## Implementing and Instantiating Your Callback Class [](id=implementing-and-instantiating-your-callback-class)

Before implementing and instantiating your callback class, you should add the 
required imports. The imports you add depend on the return type of the service 
method you're calling. For example, if you need to call the service method 
`getGroupEntries` to retrieve blog entries from a site's Blogs portlet, you need 
to import the Mobile SDK's `AsyncTaskCallback` and `JSONArrayAsyncTaskCallback`:

    import com.liferay.mobile.android.task.callback.AsyncTaskCallback;
    import com.liferay.mobile.android.task.callback.typed.JSONArrayAsyncTaskCallback;

This is because the `getGroupEntries` returns a `JSONArray`. There are multiple 
`AsyncTaskCallback` implementations, one for each method return type:

- `JSONObjectAsyncTaskCallback`
- `JSONArrayAsyncTackCallback`
- `StringAsyncTaskCallback`
- `BooleanAsyncTaskCallback`
- `IntegerAsyncTaskCallback`
- `LongAsyncTaskCallback`
- `DoubleAsyncTaskCallback`

It's also possible to use a generic `AsyncTaskCallback` implementation called
`GenericAsyncTaskCallback`. To do so, you must implement a transform method and 
handle JSON parsing yourself. 

If you still don't want to use any of these callbacks, you can implement 
`AsyncTaskCallback` directly. However, you should be careful when doing so. You 
should always get the first element of the `JSONArray` passed as a parameter to 
the `onPostExecute(JSONArray jsonArray)` method (for example, 
`jsonArray.get(0)`). 

Next, implement and instantiate your callback class. When implementing your 
callback class, you need to implement its `onFailure` and `onSuccess` methods. 
These methods respectivley determine what your app does when the request fails 
or succeeds. The `onFailure()` method is called if an exception occurs during 
the request. This could be triggered by a connection exception (e.g., a request 
timeout) or a `ServerException`. If a `ServerException` occurs, it's because 
something went wrong on the server side. For example, if you pass a `groupId` 
that doesn't exist, the Liferay instance complains about it, and the Mobile SDK 
wraps the error message with `ServerException`. 

The `onSuccess` method is called on the main UI thread after the request 
finishes. Since the request is asynchronous, the service call immediately
returns a `null` object. The service delivers the service's real return value to
the callback's `onSuccess()` method, instead. 

Example code is shown here for `AsyncTaskCallback` and 
`JSONArrayAsyncTaskCallback`: 

    AsyncTaskCallback callback = new JSONArrayAsyncTaskCallback() {

        public void onFailure(Exception exception) {
            // Implement exception handling code
        }

        public void onSuccess(JSONArray result) {
            // Called after request has finished successfully
        }

    };
    
Now that you have your callback class, you can set it to the session.

## Setting the Callback to the Session [](id=setting-the-callback-to-the-session)

Once you've implemented and instantiated your callback class, you're ready to 
set it to the session. If you haven't created a session yet, do so now. The 
tutorial 
[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-android-app) 
shows you how to create a session. Now you're ready to set the callback to the 
session. For example, this is done here for `AsyncTaskCallback`: 

    session.setCallback(callback);

Pretty simple! Now you're ready to make the service call.

## Making the Service Call [](id=making-the-service-call)

Last but certainly not least, make the service call. This is done the same as 
calling any other service: create a service object from the session and use it 
to make the service call. This is also described in the tutorial 
[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-android-app). 
An example service call that gets all the blog entries from a site's Blogs 
portlet is shown here: 

    service.getGroupEntries(10184, 0, 0, -1, -1);

The example code from the above sections is shown together here: 

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

    // create a session first
    session.setCallback(callback);
    
    // create a service object first
    service.getGroupEntries(10184, 0, 0, -1, -1);

Great! Now you know how to invoke services asynchronously from your Android 
app. 

## Related Topics [](id=related-topics)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)

[Building Mobile SDKs](/develop/tutorials/-/knowledge_base/7-0/building-mobile-sdks)
