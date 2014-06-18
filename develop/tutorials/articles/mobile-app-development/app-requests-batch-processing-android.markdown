### Sending Your Android App's Requests Using Batch Processing [](id=batch-android-app-requests-to-liferay-portal-6-2-dev-guide-en)

The Mobile SDK also allows sending requests using batch processing, which can be
much more efficient than sending separate requests. For example, suppose
you want to delete ten blog entries at the same time; instead of making one
request for each deletion, you can create a batch of calls and send them all
together.

Here is a code snippet from an app that deletes blog entries synchronously as a
batch: 

    import com.liferay.mobile.android.service.BatchSessionImpl;

    BatchSessionImpl batch = new BatchSessionImpl(session);
    BlogsEntryService service = new BlogsEntryService(batch);

    service.deleteEntry(1);
    service.deleteEntry(2);
    service.deleteEntry(3);

    JSONArray jsonArray = batch.invoke();

First, the `BatchSessionImpl` session is created. You can either pass
credentials or pass another session to the constructor. Passing another session
to the constructor is useful when you already have a `Session` object and want
to reuse the same credentials. Then, it makes service calls as usual. With
asynchronous calls, these methods return a `null` object immediately. 

Finally, it calls the `invoke()` method from the batch session object. It
returns a `JSONArray` containing the results for each service call. Since there
are three `deleteEntry` calls, the `jsonArray` contains three objects. The order
of the result matches the order of the service calls. 

If you want to make batch calls asynchronously, set the callback as a
`BatchAsyncTaskCallback` instance:

    import com.liferay.mobile.android.task.callback.BatchAsyncTaskCallback;

    batch.setCallback(new BatchAsyncTaskCallback() {

        public void onFailure(Exception exception) {
        }

        public void onSuccess(JSONArray results) {
            // The result is always a JSONArray 
        }

    });

It's just that easy to make efficient service calls in batch! 
