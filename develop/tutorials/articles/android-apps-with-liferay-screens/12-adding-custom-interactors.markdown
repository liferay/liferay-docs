# Adding Custom Interactors to Android Screenlets

Interactors are Screenlet components that implement server communication for a 
specific use case. For example, the Login Screenlet's interactor calls the 
Liferay Mobile SDK service that authenticates a user to the portal. Similarly, 
the interactor for 
[the Add Bookmark Screenlet](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets) 
calls the Liferay Mobile SDK service that adds a bookmark to the Bookmarks 
portlet. 

That's all fine and well, but what if you want to customize a Screenlet's server 
call? What if you want to use a different back-end with a Screenlet? No problem! 
You can implement a custom interactor for the Screenlet. You can plug in a 
different interactor that makes its server call by using custom logic or network 
code. To do this, you must implement the current interactor's interface and then 
pass it to the Screenlet you want to override. 

In this tutorial, you'll see an example interactor that overrides the Login 
Screenlet to always log in the same user, without a password. You can find the 
complete code in the 
[test-app on GitHub.](https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/CustomInteractorActivity.java).

## Implementing a Custom Interactor

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

## Using Custom Interactors to access other backends

A common use case for creating a custom interactor is to allow Liferay Screens to communicate with other backend services. 

To showcase this use case we are going to show how to connect with the [delicious](https://delicious.com) backend, a service used to store bookmarks. The full code is available at this [gist](https://gist.github.com/nhpatt/7cbeb0df6f39ec8a9176).

1. Create a new custom interactor

	We want to add another backend to the [AddBookmark Screenlet](https://dev.liferay.com/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets) we did as example of creating your own screenlet.
	
	Our new interactor will inherit the base class of all interactors, `BaseRemoteInteractor`, implement the Add Bookmark interactor class `AddBookmarkInteractor` and use the `AddBookmarkListener`.
	
	The base code for our new interactor is this:
	
	```java
	
	public class AddDeliciousInteractorImpl
		extends BaseRemoteInteractor<AddBookmarkListener>
		implements AddBookmarkInteractor {

		public AddDeliciousInteractorImpl(int targetScreenletId) {
			super(targetScreenletId);
		}
	
		public void addBookmark(final String url, final String title, long folderId) throws Exception {
		...
		}
	}
	
	```
	
2. Implement our custom logic

	Then we have to implement the code for accessing our backend and inserting a new bookmark with the delicious API. An example code is the following:
	
	```java
	
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
	
	
	```
	
	We use OkHttp library for calling the API passing our url and description.
	
3. Notify and listen the results

	We want to use the `EventBusUtil` class to notify the results (or the exception obtained) and allow other classes to listen the event.
	
	So we just post an event if everything has finished correctly or an exception if it failed. In the more detailed example we've created a custom class to model the result of the operation.
	
	```java
	
		EventBusUtil.post(text);
		
		...
		
		public void onEvent(String text) {
			getListener().onAddBookmarkSuccess();
		}
	}
	
	```
4. Wire the new interactor with the screenlet

	The last step is reference our new Custom Interactor and wire it with the screenlet. We just have to implement the `CustomInteractorListener`:
	
	```java
	
	_screenlet.setCustomInteractorListener(this);
	
	@Override
	public Interactor createInteractor(String actionName) {
		return new AddDeliciousInteractorImpl(_screenlet.getScreenletId());
	}
	
	```

## Related Topics

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
