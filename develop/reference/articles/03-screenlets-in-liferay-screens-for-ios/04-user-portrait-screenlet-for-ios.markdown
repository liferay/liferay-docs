# UserPortraitScreenlet for iOS [](id=userportraitscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.3.x
- iOS 8 SDK
- Liferay Portal 6.2 CE or EE

## Compatibility [](id=compatibility)

- iOS 7 and above

## Features [](id=features)

The `UserPortraitScreenlet` shows the user's portrait from Liferay Portal. If 
the user doesn't have a portrait configured, a placeholder image is shown.

## Module [](id=module)

- None

## Themes [](id=themes)

- Default
- Flat7

![The `UserPortraitScreenlet` using the Default and Flat7 themes.](../../images/screens-ios-portrait.png)

## Portal Configuration [](id=portal-configuration)

None

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `borderWidth` | `number` | The size in pixels for the portrait's border. The default value is 1. Set this to 0 if you want to hide the border.|
|  `borderColor` | `UIColor` | The border's color. Use the system's transparent color to hide the border. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadLoggedUserPortrait()` | `boolean` | Starts the request to load the currently logged in user's portrait image (see the `SessionContext` class). |
|  `load(userId)` | `boolean` | Starts the request to load the specified user's  portrait image. |
|  `load(portraitId, uuid, male)` | `boolean` | Starts the request to load the portrait image using the specified user's data. The parameters `portraitId` and `uuid` can be retrieved by using the `SessionContext.userAttribute()` method.|

## Delegate [](id=delegate)

The `UserPortraitScreenlet` delegates some events to an object that conforms to 
the `UserPortraitScreenletDelegate ` protocol. This protocol lets you implement 
the following methods: 

- `onUserPortraitResponse(image)`: Called when an image is received from the 
  server. You can then apply image filters (grayscale, for example) and return 
  the new image. You can return the original image supplied as the argument if 
  you don't want to modify it.
- `onUserPortraitError(error)`: Called when an error occurs in the process. The 
  `NSError` object describes the error.
