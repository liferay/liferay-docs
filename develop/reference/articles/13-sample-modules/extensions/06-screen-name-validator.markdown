# Screen Name Validator [](id=screen-name-validator)

The Screen Name Validator sample provides a way to validate a user's inputted
screen name. During validation, the screen name is tested client-side and
server-side.

This sample checks if a user's screen name contains reserved words that are
configured in the *Control Panel* &rarr; *Configuration* &rarr; *System
Settings* &rarr; *Foundation* &rarr; *ScreenName Validator* menu. The default
values for the screen name validator's reserved words are *admin* and *user*.

![Figure 1: Enter reserved words for the screen name validator.](../../../images/screenname-validator-config.png)

To customize this sample, modify the
`com.liferay.blade.samples.screenname.validator.internal.CustomScreenNameValidator`
class.

You can also customize this sample's configuration by adding more properties in
the
`com.liferay.blade.samples.screenname.validator.CustomScreenNameConfiguration`
class.

## Testing the Screen Name Validator [](id=testing-the-screen-name-validator)

Follow the steps below to test the Screen Name Validator sample:

1.  Deploy the Screen Name Validator to your portal installation.
2.  Navigate to the *Control Panel* &rarr; *Users* &rarr; *Users and
    Organizations* menu.
3.  Create a new user by selecting the *Add User*
    (![Add User](../../../images/icon-add.png)) button.
4.  Adding a screen name that contains the word *admin* or *user*.

![Figure 2: The error message displays when inputting a reserved word for the screen name.](../../../images/screenname-validator-test.png)

For more information on customizing the Validation sample to fit your needs, see
the Javadoc provided in this sample's Java classes.

## Where Is This Sample? [](id=where-is-this-sample)

There are three different versions of this sample, each built with a different
build tool:

- [Gradle](https://github.com/liferay/liferay-blade-samples/tree/master/gradle/extensions/screen-name-validator)
- [Liferay Workspace](https://github.com/liferay/liferay-blade-samples/tree/master/liferay-workspace/extensions/screen-name-validator)
- [Maven](https://github.com/liferay/liferay-blade-samples/tree/master/maven/extensions/screen-name-validator)
