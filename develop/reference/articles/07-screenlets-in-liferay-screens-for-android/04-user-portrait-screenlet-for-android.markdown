# User Portrait Screenlet for Android [](id=userportraitscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 (CE or EE), Liferay 7.0 CE, Liferay DXP
- Picasso library

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 15) and above

## Features [](id=features)

The `UserPortraitScreenlet` shows the users' profile pictures. If a user
doesn't have a profile picture, a placeholder image is shown. The Screenlet
allows the profile picture to be edited via the `editable` property.

## Module [](id=module)

- None

## Views [](id=views)

- Default
- Material

![The `UserPortraitScreenlet` using the Default and Material Viewsets.](../../images/screens-android-userportrait.png)

## Portal Configuration [](id=portal-configuration)

No additional steps required.

## Activity Configuration [](id=activity-configuration)

The `UserPortraitScreenlet` needs the following user permissions:

    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

Both are used by the `editable` property to take a picture with the phone's 
camera and store it locally before uploading it to the portal. The activity 
using `UserPortraitScreenlet` also needs to override the `onActivityResult` 
method to send the picture information to the Screenlet. Here's an example 
implementation:

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    
        if (resultCode == Activity.RESULT_OK) {
            _userPortraitScreenlet.upload(requestCode, data);
        }
    }

## Offline [](id=offline)

This Screenlet supports offline mode so it can function without a network 
connection. 

When loading the portrait, the Screenlet supports the following offline mode 
policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The Screenlet loads the user portrait from the portal. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error. If the Screenlet loads the portrait, it stores the received image in the local cache for later use. | Use this policy when you always need to show updated portraits, and show the default placeholder when there's no connection. |
| `CACHE_ONLY` | The Screenlet loads the user portrait from the local cache. If the portrait isn't there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show local portraits, without retrieving remote information under any circumstance. |
| `REMOTE_FIRST` | The Screenlet loads the user portrait from the portal. The Screenlet displays the portrait to the user and stores it in the local cache for later use. If a connection issue occurs, the Screenlet retrieves the portrait from the local cache. If the portrait doesn't exist there, the Screenlet uses the listener to notify the developer about the error. | Use this policy to show the most recent portrait when connected, but show a potentially outdated version when there's no connection. |
| `CACHE_FIRST` | If the portrait exists in the local cache, the Screenlet loads it from there. If it doesn't exist there, the Screenlet requests the portrait from the portal and uses the listener to notify the developer about any connection errors. | Use this policy to save bandwidth and loading time in the event a local (but probably outdated) portrait exists. |

When editing the portrait, the Screenlet supports the following offline mode 
policies:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The Screenlet sends the user portrait to the portal. If a connection issue occurs, the Screenlet uses the listener to notify the developer about the error, but it also discards the new portrait. | Use this policy when you need to make sure portal always has the most recent version of the portrait. |
| `CACHE_ONLY` | The Screenlet stores the user portrait in the local cache. | Use this policy when you need to save the portrait locally, but don't want to change the portrait in the portal. |
| `REMOTE_FIRST` | The Screenlet sends the user portrait to the portal. If this succeeds, the Screenlet also stores the portrait in the local cache for later usage. If a connection issue occurs, the Screenlet stores the portrait in the local cache with the *dirty flag* enabled. This causes the portrait to be sent to the portal when the synchronization process runs. | Use this policy when you need to make sure the Screenlet sends the new portrait to the portal as soon as the connection is restored. |
| `CACHE_FIRST` | The Screenlet stores the user portrait in the local cache and then sends it to the portal. If a connection issue occurs, the Screenlet stores the portrait in the local cache with the *dirty flag* enabled. This causes the portrait to be sent to the portal when the synchronization process runs. | Use this policy when you need to make sure the Screenlet sends the new portrait to the portal as soon as the connection is restored. Compared to `REMOTE_FIRST`, this policy always stores the portrait in the cache. The `REMOTE_FIRST` policy only stores the new image in the cache in the event of a network error or a successful upload. |

## Required Attributes [](id=required-attributes)

- None

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the View. |
| `autoLoad` | `boolean` | Whether the portrait should load when the Screenlet is attached to the window. |
| `userId` | `number` | The ID of the user whose portrait is being requested. If this attribute is set, the `male`, `portraitId`, and `uuid` attributes are ignored. |
| `male` | `boolean` | Whether the default portrait placeholder shows a male or female outline. This attribute is used if `userId` isn't specified. |
| `portraitId` | `number` | The ID of the portrait to load. This attribute is used if `userId` isn't specified. |
| `uuid` | `string` | The `uuid` of the user whose portrait is being requested. This attribute is used if `userId` isn't specified. |
| `editable` | `boolean` | Lets the user change the portrait image by taking a photo or selecting a gallery picture. |
| `offlinePolicy` | `enum` | Configure the loading and saving behavior in case of connectivity issues. For more details, read the "Offline" section below. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the user specified in the `userId` property, or the portrait specified in the `portraitId`and `uuid` properties. |
| `upload(int requestCode,`<br/>`Intent onActivityResultData)` | `void` | Starts the request to upload a profile picture from the source specified in the `requestCode` property (gallery or camera), and with the path stored in the `onActivityResultData` variable. |

## Listener [](id=listener)

The `UserPortraitScreenlet` delegates some events to an object that implements 
the `UserPortraitListener` interface. This interface lets you implement the 
following methods:

- `onUserPortraitReceived(UserPortraitScreenlet source, Bitmap bitmap)`: Called 
  when an image is received from the server. You can then apply image filters 
  (grayscale, for example) and return the new image. You can return `null` or 
  the original image supplied as the argument if you don't want to modify it.
  
- `onUserPortraitFailure(UserPortraitScreenlet source, Exception e)`: Called 
  when an error occurs in the process.

- `onUserPortraitUploaded(UserPortraitScreenlet source)`: Called when the user 
  portrait upload service finishes.

- `onUserPortraitUploadFailure(UserPortraitScreenlet source, Exception e)`: 
  Called when an error occurs uploading the user portrait.
