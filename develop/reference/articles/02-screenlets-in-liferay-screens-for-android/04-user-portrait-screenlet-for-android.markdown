# UserPortraitScreenlet for Android [](id=userportraitscreenlet-for-android)

## Requirements [](id=requirements)

- Android SDK 4.0 (API Level 14) and above
- Liferay Portal 6.2 CE or EE
- Mobile Widgets plugin
- Picasso library

## Compatibility [](id=compatibility)

- Android SDK 4.0 (API Level 14) and above

## Features [](id=features)

The `UserPortraitScreenlet` shows the user's profile picture from their Liferay 
Portal. If the user doesn't have a profile picture, a placeholder image is 
shown. The screenlet allows the profile picture to be edited via the `editable` 
property.

## Module [](id=module)

- None

## Views [](id=views)

- Default
- Material

![The `UserPortraitScreenlet` using the Default and Material viewsets.](../../images/screens-android-userportrait.png)

## Portal Configuration [](id=portal-configuration)

No additional steps required.

## Activity Configuration [](id=activity-configuration)

The `UserPortraitScreenlet` needs the following user permissions:

    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

Both are used by the `editable` property to take a picture with the phone's 
camera and store it locally before uploading it to the portal. The activity 
using `UserPortraitScreenlet` also needs to override the `onActivityResult` 
method to send the picture information to the screenlet. Here's an example 
implementation:

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    
        if (resultCode == Activity.RESULT_OK) {
            _userPortraitScreenlet.upload(requestCode, data);
        }
    }

## Attributes [](id=attributes)

| Attribute | Data type | Explanation |
|-----------|-----------|-------------| 
| `layoutId` | `@layout` | The layout used to show the view. |
| `autoLoad` | `boolean` | Whether the portrait should load when the screenlet is attached to the window. |
| `userId` | `number` | The ID of the user whose portrait is being requested. If this attribute is set, the `male`, `portraitId`, and `uuid` attributes are ignored. |
| `male` | `boolean` | Whether the default portrait placeholder shows a male or female outline. This attribute is used if `userId` isn't specified. |
| `portraitId` | `number` | The ID of the portrait to load. This attribute is used if `userId` isn't specified. |
| `uuid` | `string` | The `uuid` of the user whose portrait is being requested. This attribute is used if `userId` isn't specified. |
| `editable` | `boolean` | Lets the user change the portrait image by taking a photo or selecting a gallery picture. |

## Methods [](id=methods)

| Method | Return | Explanation |
|-----------|-----------|-------------| 
| `load()` | `void` | Starts the request to load the user specified in the `userId` property, or the portrait specified in the `portraitId`and `uuid` properties. |
| `upload(int requestCode,`<br/>`Intent onActivityResultData)` | `void` | Starts the request to upload a profile picture from the source specified in the `requestCode` property (gallery or camera), and with the path stored in the `onActivityResultData` variable. |

## Listener [](id=listener)

The `UserPortraitScreenlet` delegates some events to an object that implements 
the `UserPortraitListener` interface. This interface lets you implement the 
following methods:

| Method | Explanation |
|-----------|-------------| 
|  <pre>onUserPortraitReceived(<br/>        UserPortraitScreenlet source, <br/>        Bitmap bitmap)</pre> | Called when an image is received from the server. You can then apply image filters (grayscale, for example) and return the new image. You can return `null` or the original image supplied as the argument if you don't want to modify it. |
|  <pre>onUserPortraitFailure(<br/>        UserPortraitScreenlet source, <br/>        Exception e)</pre> | Called when an error occurs in the process. |
|  <pre> onUserPortraitUploaded(<br/>        UserPortraitScreenlet source)</pre> | Called when the user portrait upload service finishes. |
|  <pre> onUserPortraitUploadFailure(<br/>        UserPortraitScreenlet source, <br/>        Exception e)</pre> | Called when an error occurs uploading the user portrait. |
