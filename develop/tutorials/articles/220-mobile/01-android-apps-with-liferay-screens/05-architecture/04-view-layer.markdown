# View Layer [](id=view-layer)

The View layer lets developers set a Screenlet's look and feel. Each Screenlet's
`liferay:layoutId` attribute specifies its View. A View consists of a Screenlet
class, view class, and layout XML file. The layout XML file specifies the UI
components, while the Screenlet class and view class control the View's
behavior. By inheriting one or more of these View layer components from another
View, the different View *types* allow varying levels of control over a
Screenlet's UI design and behavior.

There are several different View types: 

**Themed:** presents the same structure as the current View, but alters the 
theme colors and tints of the View's resources. All existing Views can be themed 
with different styles. The View's colors reflect the current value of the 
Android 
[color palette](https://www.google.com/design/spec/style/color.html#color-color-palette). 
If you want to use one View Set with another View Set's colors, you can use 
those colors in your app's theme (e.g. `colorPrimary_default`, 
`colorPrimary_material`, `colorPrimary_westeros`). 

**Child:** presents the same behavior and UI components as its parent, but can
change the UI components' appearance and position. A Child View specifies visual
changes in its own layout XML file; it inherits the parent's view class and
Screenlet class. It can't add or remove any UI components. The parent must be a
Full View. Creating a Child View is ideal when you only need to make visual
changes to an existing View. For example, you might create a Child View for
Login Screenlet's Default View to set new positions and sizes for the standard
text boxes.

**Extended:** inherits the parent View's behavior and appearance, but lets you
change and add to both. You can do so by creating a custom view class and a new
layout XML file. An Extended View inherits all the parent View's other
classes, including its Screenlet, listeners, and Interactors; if you need to
customize any of them, you must create a Full View to do so. An Extended View's
parent must be a Full View. Creating an Extended View is ideal for adding,
removing, or changing an existing View's UI components. For example, you can
extend the Login Screenlet's Default View to present different UI components for
the user name and password fields.

**Full:** provides a complete standalone View. It doesn't inherit another View's
UI components or behavior. When creating a Full View, you must therefore create
its Screenlet class, view class, and layout XML file. You should create a Full
View when you don't need to inherit another View or when you need to alter the
core behavior of a Screenlet by customizing its listeners or calling custom
Interactors. For example, you could implement a Full View with a new Interactor
for calling a different Liferay Portal instance. Default Views are Full Views.

![Figure 1: This diagram illustrates the View layer of Liferay Screens for Android.](../../../../images/screens-android-architecture-04.png)

Liferay Screens Views are organized into *View sets* that contain Views for
several Screenlets. Liferay's available View sets are listed here: 

- [*Default:*](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/viewsets/defaultviews)
  a mandatory View Set supplied by Liferay. It's used by a Screenlet if no
  layout ID is specified or if no View is found with the layout ID. The Default
  View Set uses a neutral, flat white and blue design with standard UI
  components. In the
  [Login Screenlet](https://github.com/liferay/liferay-screens/tree/master/android/library/core/src/main/java/com/liferay/mobile/screens/auth/login),
  for example, the Default View uses standard text boxes for the user name and
  password, but the text boxes are styled with the Default View's flat white and
  blue design. You can customize this View Set's properties, such as its
  components' colors, positions, and sizes. See the Default View Set's
  [`styles.xml`](https://github.com/liferay/liferay-screens/blob/master/android/library/core/src/main/res/values/styles.xml)
  file for specific values. Since the Default View Set contains Full Views, you
  can use them to create your own custom Child and Extended Views.

- [*Material:*](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material)
  the View Set containing Views that conform to Android's [Material design guidelines](https://developer.android.com/design/material/index.html).

- [*Westeros:*](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/westeros) 
  the View Set containing Views for the 
  [Bank of Westeros](https://github.com/liferay/liferay-screens/tree/master/android/samples/bankofwesteros) 
  sample app.

For information on creating or customizing Views, see the tutorial 
[Creating Android Views](/develop/tutorials/-/knowledge_base/7-0/creating-android-views). 

Great! Now you know how Liferay Screens for Android is composed. However,
there's something you should know before moving on: how Screenlets interact with
the Android life cycle. 
