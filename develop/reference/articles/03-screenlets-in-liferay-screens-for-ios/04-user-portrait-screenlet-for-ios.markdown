# User Portrait Screenlet for iOS [](id=userportraitscreenlet-for-ios)

## Requirements [](id=requirements)

- XCode 6.4.x
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

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

When loading the portrait, the Screenlet supports the following offline mode 
policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet loads the user portrait from the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error. If the Screenlet loads the portrait, it stores the received image in the local cache for later use. | Use this policy when you always need to show updated portraits, and show the default placeholder when there's no connection. |
| `cache-only` | The Screenlet loads the user portrait from the local cache. If the portrait isn't there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show local portraits, without retrieving remote information under any circumstance. |
| `remote-first` | The Screenlet loads the user portrait from the portal. The Screenlet displays the portrait to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the portrait from the local cache. If the portrait doesn't exist there, the Screenlet uses the delegate to notify the developer about the error. | Use this policy to show the most recent portrait when connected, but show a potentially outdated version when there's no connection. |
| `cache-first` | If the portrait exists in the local cache, the Screenlet loads it from there. If it doesn't exist there, the Screenlet requests the portrait from the portal and uses the delegate to notify the developer about any connection errors. | Use this policy to save bandwidth and loading time in the event a local (but probably outdated) portrait exists. |

When editing the portrait, the Screenlet supports the following offline mode 
policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `remote-only` | The Screenlet sends the user portrait to the portal. If a connection issue occurs, the Screenlet uses the delegate to notify the developer about the error, but it also discards the new portrait. | Use this policy when you need to make sure portal always has the most recent version of the portrait. |
| `cache-only` | The Screenlet stores the user portrait in the local cache. | Use this policy when you need to save the portrait locally, but don't want to change the portrait in the portal. |
| `remote-first` | The Screenlet sends the user portrait to the portal. If this succeeds, the Screenlet also stores the portrait in the local cache for later usage. If a connection issue occurs, the Screenlet stores the portrait in the local cache with the *dirty flag* enabled. This causes the portrait to be sent to the portal when the synchronization process runs. | Use this policy when you need to make sure the Screenlet sends the new portrait to the portal as soon as the connection is restored. |
| `cache-first` | The Screenlet stores the user portrait in the local cache and then sends it to the portal. If a connection issue occurs, the Screenlet stores the portrait in the local cache with the *dirty flag* enabled. This causes the portrait to be sent to the portal when the synchronization process runs. | Use this policy when you need to make sure the Screenlet sends the new portrait to the portal as soon as the connection is restored. Compared to `remote-first`, this policy always stores the portrait in the cache. The `remote-first` policy only stores the new image in the event of a network error. |

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `borderWidth` | `number` | The size in pixels for the portrait's border. The default value is 1. Set this to `0` if you want to hide the border.|
| `borderColor` | `UIColor` | The border's color. Use the system's transparent color to hide the border. |
| `editable` | `boolean` | Lets the user change the portrait image by taking a photo or selecting a gallery picture. The default value is `false`. Portraits loaded with the `load(portraitId, uuid, male)` method aren't editable. |
| `offlinePolicy` | `string` | Configure the loading and saving behavior in case of connectivity issues. For more details, read the "Offline" section below. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
|  `loadLoggedUserPortrait()` | `boolean` | Starts the request to load the currently logged in user's portrait image (see the `SessionContext` class). |
|  `load(userId)` | `boolean` | Starts the request to load the specified user's portrait image. |
|  `load(portraitId, uuid, male)` | `boolean` | Starts the request to load the portrait image using the specified user's data. The parameters `portraitId` and `uuid` can be retrieved by using the `SessionContext.userAttributes()` method. |
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
