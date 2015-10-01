# User Portrait Screenlet for iOS [](id=userportraitscreenlet-for-ios)

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

- Default (`default`)
- Flat7 (`flat7`)

![The `UserPortraitScreenlet` using the Default and Flat7 Themes.](../../images/screens-ios-portrait.png)

## Portal Configuration [](id=portal-configuration)

None

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `borderWidth` | `number` | The size in pixels for the portrait's border. The default value is 1. Set this to `0` if you want to hide the border.|
| `borderColor` | `UIColor` | The border's color. Use the system's transparent color to hide the border. |
| `editable` | `boolean` | Lets the user change the portrait image by taking a photo or selecting a gallery picture. The default value is `false`. Portraits loaded with the `load(portraitId, uuid, male)` method aren't editable. |
| `offlinePolicy` | `string` | Configure the loading and saving behaviour in case of connectivity issues. For more details, read the "Offline" section below. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadLoggedUserPortrait()` | `boolean` | Starts the request to load the currently logged in user's portrait image (see the `SessionContext` class). |
|  `load(userId)` | `boolean` | Starts the request to load the specified user's portrait image. |
|  `load(portraitId, uuid, male)` | `boolean` | Starts the request to load the portrait image using the specified user's data. The parameters `portraitId` and `uuid` can be retrieved by using the `SessionContext.userAttribute()` method. |
|  `load(companyId, emailAddress)` | `boolean` | Starts the request to load the portrait image using the user's email address. |
|  `load(companyId, screenName)` | `boolean` | Starts the request to load the portrait image using the user's screen name. |

## Delegate [](id=delegate)

The `UserPortraitScreenlet` delegates some events to an object that conforms to 
the `UserPortraitScreenletDelegate ` protocol. This protocol lets you implement 
the following methods: 

- `- screenlet:onUserPortraitResponseImage:`: Called when an image is received 
  from the server. You can then apply image filters (grayscale, for example) and 
  return the new image. You can return the original image supplied as the 
  argument if you don't want to modify it.

- `- screenlet:onUserPortraitError:`: Called when an error occurs in the 
  process. The `NSError` object describes the error.

- `- screenlet:onUserPortraitUploaded:`: Called when a new portrait is uploaded 
  to the server. You receive the user attributes as a parameter.

- `- screenlet:onUserPortraitUploadError:`: Called when an error occurs in the 
  upload process. The `NSError` object describes the error.

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

_When loading_ the portrait, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The user portrait will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the delegate as usual. If the portrait can be successfully loaded, the received image is stored in the local cache for later usage.| Use this policy when you need to show always updated images and show the default placeholder when there's no connection.|
| `cache-only` | The user portrait will be loaded from the local cache. If it's not present, the screenlet will notify about the error through the delegate as usual.| Use this policy when you need to show always local images without retrieving remote information under any circumstance.|
| `remote-first` | The user portrait will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the delegate as usual.|When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `cache-first` | The user portrait will be loaded from the local cache if exists. If it doesn't exist, then it will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|

_When editing_ the portrait, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The user portrait will be sent to the portal at the moment. If a connection issue happens, the screenlet will notify about the error through the delegate as usual, but the new image will be discarded. | Use this policy when you need to make sure the new image is updated in the portal at the moment.|
| `cache-only` | The user portrait will be stored in the local cache. | Use this policy when you need to save the image locally but you don't want to change the remote version. |
| `remote-first` | The user portrait will be sent to the remote portal. If it's successfully sent, it will be also stored in the local cache for later usage. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the image to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the new image will be sent to the portal, right at the moment or when the connection is restored. |
| `cache-first` | The user portrait will be stored in local cache and then sent to the remote portal. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the image to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the new image will be sent to the portal, right at the moment or when the connection is restored. The different between this policy and `remote-first` is the former will store the image in the cache always, while the latter will store the new image only in case of network error.|
