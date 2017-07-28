# Adding Custom Interactors to Android Screenlets [](id=adding-custom-interactors-to-android-screenlets)

Interactors are Screenlet components that implement server communication for a 
specific use case. For example, the Login Screenlet's interactor calls the 
Liferay Mobile SDK service that authenticates a user to the portal. Similarly, 
the interactor for 
[the Add Bookmark Screenlet](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets) 
calls the Liferay Mobile SDK service that adds a bookmark to the Bookmarks 
portlet. 

That's all fine and well, but what if you want to customize a Screenlet's server 
call? What if you want to use a different back-end with a Screenlet? No problem! 
You can implement a custom interactor for the Screenlet. You can plug in a 
different interactor that makes its server call by using custom logic or network 
code. To do this, you must implement the current interactor's interface and then 
pass it to the Screenlet you want to override. You should do this inside your 
app's code, either in an inner class or a separate class. 

In this tutorial, you'll see an example interactor that overrides the Login 
Screenlet to always log in the same user, without a password. You can find the 
complete code in the 
[test-app on GitHub.](https://github.com/liferay/liferay-screens/blob/master/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/CustomInteractorActivity.java).
Note that this example implements the custom interactor in an inner class of an 
activity. 

## Implementing a Custom Interactor [](id=implementing-a-custom-interactor)

1. Implement your custom interactor. You must inherit the original interactor's 
   interface, as shown here: 

        private class CustomLoginInteractor extends LoginBasicInteractor {

            public CustomLoginInteractor(int targetScreenletId) {
                super(targetScreenletId);
            }

            @Override
            public void login() throws Exception {
                //custom implementation
            }
        }

2. Call the interactor's listener. In your custom logic, you must call the 
   interactor's listener. In this example, you must call `onLoginFailure` and 
   `onLoginSuccess`, depending on your custom logic's result: 

        if (SUCCESS) {
            getListener().onLoginSuccess(fakeUser);
        }
        else {
            getListener().onLoginFailure(new AuthenticationException("bad login"));
        }

3. Return your interactor in the custom listener. You must use 
   `setCustomInteractorListener` to set a specific listener that expects an 
   Interactor created with `actionName` (a string): 

        _screenlet.setCustomInteractorListener(this);

        @Override
        public LoginInteractor createInteractor(String actionName) {
            return new CustomLoginInteractor(_loginScreenlet.getScreenletId());
        }

Great! Now you know how to implement custom interactors for Android Screenlets. 
The next example builds on this by showing you how to access non-Liferay 
backends with a custom interactor.

## Using Custom Interactors to Access Other Backends [](id=using-custom-interactors-to-access-other-backends)

Custom interactors are also capable of communicating with non-Liferay backends. 
The following example illustrates this by creating a custom interactor for the 
[AddBookmark Screenlet](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets) 
that can store bookmarks at 
[Delicious](https://delicious.com). 
You can find this example's complete code 
[at this gist](https://gist.github.com/nhpatt/7cbeb0df6f39ec8a9176). 

1. Create a new custom interactor. This interactor inherits 
   `BaseRemoteInteractor`, the base class of all interactors, with 
   `AddBookmarkListener` as a type parameter. It also implements the 
   `AddBookmarkInteractor` class. The base code for this new interactor is 
   shown here: 

        public class AddDeliciousInteractorImpl extends BaseRemoteInteractor<AddBookmarkListener>
            implements AddBookmarkInteractor {

            public AddDeliciousInteractorImpl(int targetScreenletId) {
                super(targetScreenletId);
            }

            public void addBookmark(final String url, final String title, long folderId) throws Exception {
                ...
            }
        }

2. Implement your custom logic. In this example, you must implement the code for 
   accessing Delicious and inserting a new bookmark with the Delicious API. You 
   can use the 
   [OkHttp library](http://square.github.io/okhttp/) 
   to pass the API your bookmark's URL and description. The following code shows 
   this: 
	
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    Headers headers = Headers.of("Authorization", "Bearer _OAUTH_TOKEN_");

                    OkHttpClient client = new OkHttpClient();

                    Request add = new Request.Builder()
                        .url("https://api.del.icio.us/api/v1/posts/add?url=" + url + "&description=" + title)
                        .headers(headers)
                        .build();

                    com.squareup.okhttp.Response response = client.newCall(get).execute();

                    String text = response.body().string();

                    ...

                }
                catch (IOException e) {
                    LiferayLogger.e("Error sending", e);
                    ...
                }
            }
        }).start();

3. Notify your app of the results. You should use the `EventBusUtil` class to 
   post an event for this. Use the event to let other classes listen for the 
   event. The following code uses `EventBusUtil.post(text)` to post the event, 
   and the `onEvent` method to notify the listener:

        EventBusUtil.post(text);

        ...

        public void onEvent(String text) {
            getListener().onAddBookmarkSuccess();
        }

    Note that the code in 
    [the gist](https://gist.github.com/nhpatt/7cbeb0df6f39ec8a9176) 
    uses the custom `BookmarkAdded` class to model the operation's results. 

4. In the activity or fragment you're using the Screenlet in, implement 
   `CustomInteractorListener`. You must also reference your new custom 
   interactor and connect it to the Screenlet: 

        _screenlet.setCustomInteractorListener(this);

        @Override
        public Interactor createInteractor(String actionName) {
            return new AddDeliciousInteractorImpl(_screenlet.getScreenletId());
        }

Awesome! Now you know how to create a custom interactor that can communicate 
with a non-Liferay backend. This opens up even more possibilities for your apps. 

## Related Topics [](id=related-topics)

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/7-0/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/7-0/creating-android-screenlets)
