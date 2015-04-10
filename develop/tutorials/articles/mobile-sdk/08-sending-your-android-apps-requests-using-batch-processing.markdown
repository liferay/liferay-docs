# Sending Your Android App's Requests Using Batch Processing [](id=sending-your-android-apps-requests-using-batch-processing)

The Mobile SDK also allows sending requests using batch processing. This can be
much more efficient than sending separate requests. For example, suppose you 
want to delete ten blog entries at the same time. Instead of making one request 
for each deletion, you can create a batch of calls and send them all together. 

This tutorial shows you how to implement batch processing for your Android app.
It's assumed that you already know how to invoke Liferay services in your
Android app. If you don't, see the tutorial
[Invoking Liferay Services in Your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-liferay-services-android).
Read on to learn how to setup your batch of service calls. 

## Implementing Batch Processing [](id=implementing-batch-processing)

Here's a code snippet from an app that deletes blog entries synchronously as a
batch: 

    import com.liferay.mobile.android.service.BatchSessionImpl;

    BatchSessionImpl batch = new BatchSessionImpl(session);
    BlogsEntryService service = new BlogsEntryService(batch);

    service.deleteEntry(1);
    service.deleteEntry(2);
    service.deleteEntry(3);

    JSONArray jsonArray = batch.invoke();

So what's going on here? After the import, the `BatchSessionImpl` session is 
created. The `BatchSessionImpl` constructor takes either credentials or another 
session. Passing another session to the constructor is useful when you 
already have a `Session` object and want to reuse the same credentials. Next, 
the service calls are made as usual. If you use asynchronous calls, these 
methods return a `null` object immediately. 

Finally, the `invoke()` method is called from the batch session object. It
returns a `JSONArray` containing the results for each service call. Since there
are three `deleteEntry` calls, the `jsonArray` contains three objects. The order
of the result matches the order of the service calls. 

Great! But what if you want to make batch calls asynchronously? Just set the 
callback as a `BatchAsyncTaskCallback` instance: 

    import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;

    batch.setCallback(new BatchAsyncTaskCallback() {

        public void onFailure(Exception exception) {
        }

        public void onSuccess(JSONArray results) {
            // The result is always a JSONArray 
        }

    });

This is similar to the procedure for making asynchronous calls described in the 
tutorial [Invoking Services Asynchronously from Your Android App](/develop/tutorials/-/knowledge_base/6-2/invoking-services-asynchronously-android). 
It's just that easy to make efficient service calls in batch! 

## Related Topics [](id=related-topics)

[Creating iOS Apps that Use Liferay](/develop/tutorials/-/knowledge_base/6-2/creating-ios-apps-that-use-liferay)

[Liferay Mobile SDK Builder](/develop/tutorials/-/knowledge_base/6-2/liferay-mobile-sdk-builder)

[Service Builder and Services](/develop/tutorials/-/knowledge_base/6-2/service-builder)

[Developing Plugins with Liferay IDE](/develop/tutorials/-/knowledge_base/6-2/liferay-ide)
