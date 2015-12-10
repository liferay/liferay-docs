# Adding custom interactors [](id=adding-custom-interactors)

If you want to use a different backend and reuse the view and appearance of a screenlet, *custom interactors* is the way to go.

Liferay Screens supports plugging a different interactor and provide a custom logic or network code to access the information. You have to implement the interface of the interactor and pass it to the screenlet you want to override.

We'll see a guide step by step overriding our `LoginScreenlet` to provide a test interactor that always logs the same user without password. This example is explained in detail in the [test-app](https://github.com/liferay/liferay-screens/blob/develop/android/samples/test-app/src/main/java/com/liferay/mobile/screens/testapp/CustomInteractorActivity.java).


## How to implement a Custom Interactor [](id=implement-custom-interactor)

1. Implement your custom interactor 

	You just have to inherite the interface of the original interactor, like this:

	```java
	
	private class CustomLoginInteractor extends LoginBasicInteractor {
	
			public CustomLoginInteractor(int targetScreenletId) {
				super(targetScreenletId);
			}
	
			@Override
			public void login() throws Exception {
				
				//custom implementation
			}
		}
	
	
	```

2. Call the interactor listener

	In your custom logic you have to call the listener of the interactor, in this example we have to call to `onLoginFailure` and `onLoginSuccess` depending on the result of our custom logic.
	
	```java
		
	if (SUCCESS) {
		getListener().onLoginSuccess(fakeUser);
	}
	else {
		getListener().onLoginFailure(new AuthenticationException("bad login"));
	}
		
	```
	
3. Return your interactor in the custom listener

	You have to set a specific listener, `setCustomInteractorListener` that expects an Interactor created based on a `actionName` (a string).
	
	
	```java
	
	_screenlet.setCustomInteractorListener(this);
	
	@Override
		public LoginInteractor createInteractor(String actionName) {
			return new CustomLoginInteractor(_loginScreenlet.getScreenletId());
		}
	
	```