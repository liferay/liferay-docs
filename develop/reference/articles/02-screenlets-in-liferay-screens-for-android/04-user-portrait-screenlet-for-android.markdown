# User Portrait Screenlet for Android [](id=userportraitscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 15) or above
- Liferay Portal 6.2 CE or EE
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

## Required Attributes [](id=required-attributes)

- `layoutId`

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
| `offlinePolicy` | `enum` | Configure the loading and saving behaviour in case of connectivity issues. For more details, read the "Offline" section below. |

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

## Offline [](id=offline)

This screenlet sopport offline mode in order to work under scenarios with bad connectivity.

_When loading_ the portrait, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The user portrait will be loaded from the portal. If a connection issue happens, the screenlet will notify about the error through the listener as usual. If the portrait can be successfully loaded, the received image is stored in the local cache for later usage.| Use this policy when you need to show always updated images and show the default placeholder when there's no connection.|
| `CACHE_ONLY` | The user portrait will be loaded from the local cache. If it's not present, the screenlet will notify about the error through the listener as usual.| Use this policy when you need to show always local images without retrieving remote information under any circumstance.|
| `REMOTE_FIRST` | The user portrait will be requested to the remote portal. If it's received, it will be shown to the user and stored in the local cache for later usage. If a connection issue happens, then it will be retrieved from the local cache. If it doesn't exist there, the screenlet will notify about the error through the listener as usual.|When you need the most updated version if connected, but is accepted to show an outdated version when there's no connection.|
| `CACHE_FIRST` | The user portrait will be loaded from the local cache if exists. If it doesn't exist, then it will be requested to the portal as usual and will notify about the error in case of issue (even on connectivity errors).| When you want to save bandwidth and loading time in case you have a local (but probably outdated) version.|

_When editing_ the portrait, these are the four policies supported:

| Policy | What happens | When to use |
|--------|-----------|---------------|
| `REMOTE_ONLY` | The user portrait will be sent to the portal at the moment. If a connection issue happens, the screenlet will notify about the error through the listener as usual, but the new image will be discarded. | Use this policy when you need to make sure the new image is updated in the portal at the moment.|
| `CACHE_ONLY` | The user portrait will be stored in the local cache. | Use this policy when you need to save the image locally but you don't want to change the remote version. |
| `REMOTE_FIRST` | The user portrait will be sent to the remote portal. If it's successfully sent, it will be also stored in the local cache for later usage. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the image to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the new image will be sent to the portal, right at the moment or when the connection is restored. |
| `CACHE_FIRST` | The user portrait will be stored in local cache and then sent to the remote portal. If a connection issue happens, then it will be stored in the local cache with the "dirty flag" enabled. This makes the image to be sent to the portal when the synchronization process runs.| Use this policy when you need to make sure the new image will be sent to the portal, right at the moment or when the connection is restored. The different between this policy and `REMOTE_FIRST` is the former will store the image in the cache always, while the latter will store the new image only in case of network error.|
