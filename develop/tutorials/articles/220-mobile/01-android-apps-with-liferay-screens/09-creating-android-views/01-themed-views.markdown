# Themed Views [](id=themed-views)

Screens provides several existing View Sets that you can reuse and customize in 
your app to create a Themed View. If you use or override the Android color 
palette's values (for example, `primaryColor`, `secondaryColor`, etc...), you'll 
reuse the View Set's general structure, but be able to use the new colors (also 
with tinted resources). Note that you must create Themed Views inside your app. 
This is because Themed Views depend on the app or activity theme. 

Each View Set has its own Android theme. These are listed here: 

- **Default View Set:** `default_theme`
- **Material View Set:** `material_theme`
- **Westeros View Set:** `westeros_theme`

You can easily style all your Screenlets by setting your app or activity theme 
to inherit a View Set's Android theme. For example, you can use the following 
code to reuse the styles (and layouts) from `material_theme` in your own theme: 

    <style name="AppTheme.NoActionBar" parent="material_theme">
        <item name="colorPrimary">#B91D6D</item>
        <item name="colorPrimaryDark">#670E3B</item>
        <item name="colorAccent">#BBBBBB</item>
    </style>

    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >

Note that this code overrides the `AppTheme.NoActionBar` theme's colors with 
your own color settings for `colorPrimary`, `colorPrimaryDark`, and 
`colorAccent`. Screenlets will also use these new colors, and tint images and 
other resources accordingly. Liferay Screens uses the default Android color 
palette names from the Support Library. 

You can also override only the parent View Set's theme colors. This way you can 
set a default color palette and override only the View Set colors you want. The 
color names for each View Set are the default Android names, followed by an 
underscore and the View Set's lowercase name (`_default`, `_material`, and 
`_westeros`). For example, the following code overrides `colorPrimary`, 
`colorPrimaryDark`, and `colorAccent` for only the `material_theme`: 

    <resources>
        <color name="colorPrimary_material">#B91D6D</color>
        <color name="colorPrimaryDark_material">#670E3B</color>
        <color name="colorAccent_material">#BBBBBB</color>
    </resources>

Liferay Screens also lets you use one View Set's layout with a Screenlet, and 
use another View Set's general style and colors. To do this, pass a `layoutId` 
attribute to a Screenlet that is already styled with another View Set's theme. 
The Screenlet uses the layout structure specified in `layoutId`, but inherits 
the general style and colors from the View Set's theme. For example, this code 
tells Login Screenlet to use the Default View Set's layout structure, but use 
the styles and colors defined earlier in `AppTheme.NoActionBar`:

    <com.liferay.mobile.screens.auth.login.LoginScreenlet
        android:id="@+id/login_screenlet"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:basicAuthMethod="email"
        app:layoutId="@layout/login_default"
        app:credentialsStorage="shared_preferences" />

    <application android:theme="@style/AppTheme.NoActionBar"
        ...
    >
