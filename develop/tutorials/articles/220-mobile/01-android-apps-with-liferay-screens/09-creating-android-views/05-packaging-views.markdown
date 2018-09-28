# Packaging Your Views [](id=packaging-your-views)

If you want to distribute or reuse Views, you should package them in a module
that is then added as an app's project dependency. To do this, use the 
[material](https://github.com/liferay/liferay-screens/tree/master/android/viewsets/material) 
sub-project as a template for your new
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/viewsets/material/build.gradle) 
file. 

To use a packaged View, you must import its module into your project by 
specifying its location in your
[`settings.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/settings.gradle)
file. The Bank of Westeros and test-app projects use custom Views `westeros` and
`material`, respectively. These projects exemplify using independent Views in a
project. 

If you want to redistribute your View and let others use it, you can upload it 
to jCenter or Maven Central. In the example
[`build.gradle`](https://github.com/liferay/liferay-screens/blob/master/android/samples/bankofwesteros/build.gradle) 
file, after entering your bintray api key you can execute `gradlew
bintrayupload` to upload your project to jCenter. When finished, anyone can use
the View as they would any Android dependency by adding the repository,
artifact, group ID, and version to their Gradle file. 

## Related Topics [](id=related-topics)

[Creating Android Views](/develop/tutorials/-/knowledge_base/7-1/creating-android-views)
