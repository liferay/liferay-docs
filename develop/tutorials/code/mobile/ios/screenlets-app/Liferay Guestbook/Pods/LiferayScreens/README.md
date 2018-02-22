# Liferay Screens

[![License](http://img.shields.io/badge/license-LGPL_2.1-red.svg?style=flat-square)](http://opensource.org/licenses/LGPL-2.1) [![Release](http://img.shields.io/badge/release-3.0.0-orange.svg?style=flat-square)](https://github.com/liferay/liferay-screens/releases/) [![Tag](http://img.shields.io/github/tag/liferay/liferay-screens.svg?style=flat-square)](https://github.com/liferay/liferay-screens/tags/)

[![iOS Platform](http://img.shields.io/badge/platform-iOS_9+-blue.svg?style=flat-square)](https://github.com/liferay/liferay-screens/tree/master/ios) [![Build Status](http://img.shields.io/travis/liferay/liferay-screens.svg?style=flat-square)](https://travis-ci.org/liferay/liferay-screens/)

[![Android Platform](http://img.shields.io/badge/platform-Android_4.0-green.svg?style=flat-square)](https://github.com/liferay/liferay-screens/tree/master/android) [![Build Status](http://img.shields.io/travis/liferay/liferay-screens.svg?style=flat-square)](https://travis-ci.org/liferay/liferay-screens/)

## Overview

This repository contains the Liferay Screens source code, in addition to several example projects that illustrate how Screens is used. For detailed instructions on developing mobile apps with Screens, see the Screens documentation for [Android](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/android-apps-with-liferay-screens) and [iOS](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/ios-apps-with-liferay-screens) on the Liferay Developer Network (LDN). 

Brief descriptions of this repository's contents are listed here:

- `android`: the Liferay Screens for Android codebase, View Sets, and example projects.
- `ios`: the Liferay Screens for iOS codebase, Themes, and example projects.

## Available Screenlets

* **Login Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/loginscreenlet-for-android)]: Signs users in to a Liferay instance.
* **Sign Up Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/signupscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/signupscreenlet-for-android)]: Registers new users in a Liferay instance.
* **Forgot Password Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/forgotpasswordscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/forgotpasswordscreenlet-for-android)]: Sends emails containing a new password or password reset link to users.
* **User Portrait Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/userportraitscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/userportraitscreenlet-for-android)]: Shows the user's portrait picture.
* **DDL Form Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/ddlformscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/ddlformscreenlet-for-android)]: Presents dynamic forms to be filled out by users and submitted back to the server.
* **DDL List Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/ddllistscreenlet-for-android)]: Shows a list of records based on a pre-existing DDL in a Liferay instance.
* **Asset List Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/assetlistscreenlet-for-android)]: Shows a list of assets managed by [Liferay's Asset Framework](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-0/asset-framework). This includes web content, blog entries, documents, and more.
* **Web Content Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/webcontentdisplayscreenlet-for-android)]: Shows the web content's HTML or structured content. This Screenlet uses the features available in [Web Content Management](/discover/portal/-/knowledge_base/7-0/creating-web-content).
* **Web Content List Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/web-content-list-screenlet-for-android)]: Shows a list of web contents from a folder, usually based on a pre-existing `DDMStructure`.
* **Image Gallery Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/image-gallery-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/image-gallery-screenlet-for-android)]: Shows a list of images from a folder. This Screenlet also lets users upload and delete images.
* **Rating Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/rating-screenlet-for-android)]: Shows the rating for an asset. This Screenlet also lets the user update or delete the rating.
* **Comment List Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-list-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-list-screenlet-for-android)]: Shows a list of comments for an asset.
* **Comment Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-display-screenlet-for-android)]: Shows a single comment for an asset.
* **Comment Add Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-add-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/comment-add-screenlet-for-android)]: Lets the user comment on an asset.
* **Asset Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/asset-display-screenlet-for-android)]: Displays an asset. Currently, this Screenlet can display Documents and Media Library files (`DLFileEntry` entities), blogs articles (`BlogsEntry`  entities), and web content articles (`WebContent` entities). You can also use it to display custom assets.
* **Blogs Entry Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/blogs-entry-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/blogs-entry-display-screenlet-for-android)]: Shows a single blogs entry.
* **Image Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/image-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/image-display-screenlet-for-android)]: Shows a single image file from a Liferay instance's Documents and Media Library.
* **Video Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/video-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/video-display-screenlet-for-android)]: Shows a single video file from a Liferay instance's Documents and Media Library.
* **Audio Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/audio-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/audio-display-screenlet-for-android)]: Shows a single audio file from a Liferay instance's Documents and Media Library.
* **PDF Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/pdf-display-screenlet-for-ios) / [Android](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/pdf-display-screenlet-for-android)]: Shows a single PDF file from a Liferay instance's Documents and Media Library.
* **File Display Screenlet** [[iOS](https://dev.liferay.com/develop/reference/-/knowledge_base/7-0/file-display-screenlet-for-ios)]: Shows a single file from a Liferay instance's Documents and Media Library. Use this Screenlet to display file types not covered by the other display Screenlets (e.g., DOC, PPT, XLS). Only available for iOS.


## Contributing

Liferay welcomes any and all contributions! Please read the [Liferay Screens Contribution Guide](CONTRIBUTING.md) for details on developing and submitting your contributions.

## License

This library is free software ("Licensed Software"); you can redistribute it and/or modify it under the terms of the [GNU Lesser General Public License](http://www.gnu.org/licenses/lgpl-2.1.html) as
published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; including but not limited to, the implied warranty of MERCHANTABILITY, NONINFRINGEMENT, or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

You should have received a copy of the [GNU Lesser General Public
License](http://www.gnu.org/licenses/lgpl-2.1.html) along with this library; if not, write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth
Floor, Boston, MA 02110-1301 USA
