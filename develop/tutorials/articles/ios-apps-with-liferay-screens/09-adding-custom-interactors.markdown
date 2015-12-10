# Adding custom interactors [](id=adding-custom-interactors)

If you want to use a different backend and reuse the view and appearance of a screenlet, *custom interactors* is the way to go.

Liferay Screens supports plugging a different interactor and provide a custom logic or network code to access the information. You have to implement the interface of the interactor and pass it to the screenlet you want to override.

We'll see a guide step by step overriding our `LoginScreenlet` to provide a test interactor that always logs the same user without password.


## How to implement a Custom Interactor [](id=implement-custom-interactor)

1. Implement your custom interactor 

	You just have to inherite `ServerOperationInteractor`, like this:

	```swift
	
	
	class LoginCustomInteractor: ServerOperationInteractor {

		override func createOperation() -> GetUserBaseOperation {
			
			...

			return operation!
		}

	}

	
	
	```

2. Implement the optional protocol that receives a `customInteractorForAction` and return your own interactor

	```swift
	func screenlet(screenlet: BaseScreenlet, customInteractorForAction: String, withSender: AnyObject?) -> Interactor? {
		
		let interactor = LoginCustomInteractor()
	
		return interactor
	}
	```