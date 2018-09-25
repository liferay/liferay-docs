# Sending Your Android App's Requests Using Batch Processing [](id=sending-your-android-apps-requests-using-batch-processing)

The Mobile SDK also allows sending requests in batch. This can be much more 
efficient than sending separate requests. For example, suppose you want to 
delete ten blog entries in a site's Blogs portlet at the same time. Instead of 
making a request for each deletion, you can create a batch of calls and send 
them all together. 

This tutorial shows you how to implement batch processing for your Android app.
It's assumed that you already know how to invoke Liferay services from your
Android app. If you don't, see the tutorial 
[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-android-app).
Now get ready to whip up a fresh batch of service calls! 

## Implementing Batch Processing [](id=implementing-batch-processing)

Making service calls in batch only requires two extra steps over making them one 
at a time: 

- Create a batch session with `BatchSessionImpl`.
- Make the batch service calls with the `invoke` method of `BatchSessionImpl`.

The rest of the steps are the same as making other service calls. You still need 
a service object, and you still need to call its service methods. As an example, 
here's a code snippet from an app that deletes a Blogs portlet's blog entries 
synchronously in batch: 

    import com.liferay.mobile.android.service.BatchSessionImpl;

    BatchSessionImpl batch = new BatchSessionImpl(session);
    BlogsEntryService service = new BlogsEntryService(batch);

    service.deleteEntry(1);
    service.deleteEntry(2);
    service.deleteEntry(3);

    JSONArray jsonArray = batch.invoke();

So what's going on here? After the import, `BatchSessionImpl` is used with a 
pre-existing session to create a batch session. Note that the `BatchSessionImpl` 
constructor takes either credentials or a session. Passing a session to the 
constructor is useful when you already have a `Session` object and want to reuse 
the same credentials. After creating the service object, several `deleteEntry` 
service calls are created. Since the service object is created with a batch 
session, these calls aren't made immediately; they return `null` instead. The 
calls aren't made until issued in batch by calling the `invoke()` method on the 
batch session object. It returns a `JSONArray` containing the results for each 
service call. Since this example contains three `deleteEntry` calls, the 
`jsonArray` contains three objects. The results are ordered the same as the 
service calls. 

Great! But what if you want to make batch calls asynchronously? No problem! Set 
the callback as a `BatchAsyncTaskCallback` instance: 

    import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;

    batch.setCallback(new BatchAsyncTaskCallback() {

        public void onFailure(Exception exception) {
        }

        public void onSuccess(JSONArray results) {
            // The result is always a JSONArray 
        }

    });

This is similar to the procedure for making asynchronous calls as described in 
the tutorial 
[Invoking Services Asynchronously from Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-android-app). 
Awesome! Now you know how to make efficient service calls in batch! 

## Related Topics [](id=related-topics)

[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-liferay-services-in-your-android-app)

[Invoking Services Asynchronously from Your Android App](/develop/tutorials/-/knowledge_base/7-0/invoking-services-asynchronously-from-your-android-app)

[Creating iOS Apps that Use the Mobile SDK](/develop/tutorials/-/knowledge_base/7-0/creating-ios-apps-that-use-the-mobile-sdk)
