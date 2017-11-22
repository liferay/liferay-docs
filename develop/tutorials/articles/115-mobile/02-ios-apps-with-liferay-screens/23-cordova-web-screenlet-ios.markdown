# Using Cordova with Web Screenlet in Your iOS App

<!-- Why install Cordova? What can you do with it? -->

## Installing and Configuring Cordova

Follow these steps to install and configure Cordova: 

1.  Follow 
    [the Cordova getting started guide](https://cordova.apache.org/#getstarted) 
    to install Cordova, create a Cordova project, and add the iOS platform to 
    your Cordova project. 

2.  Install the 
    [Cordova `WKWebView` engine](https://www.npmjs.com/package/cordova-plugin-ionic-wkwebview-engine): 

        cordova plugin add cordova-plugin-wkwebview-engine

3.  Install any other Cordova plugins you want to use in your app. You can use 
    `cordova plugin` to view the currently installed plugins. 

4.  Copy the following files and folders to your iOS project's root folder: 

    - platforms/ios/<your-project-name>/config.xml
    - platforms/ios/<your-project-name>/Plugins
    - platforms/ios/www

5.  In the `config.xml` file you just copied to your iOS project's root folder, 
    add the following line below `<access origin="*" />`: 

        <allow-navigationhref="*" />
