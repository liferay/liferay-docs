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

## Related Topics

[Architecture of Liferay Screens for Android](/develop/tutorials/-/knowledge_base/6-2/architecture-of-liferay-screens-for-android)

[Creating Android Screenlets](/develop/tutorials/-/knowledge_base/6-2/creating-android-screenlets)
