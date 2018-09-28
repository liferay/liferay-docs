# Screenlet Lifecycle [](id=screenlet-lifecycle)

Liferay Screens automatically saves and restores Screenlets' states using the
Android SDK methods `onSaveInstanceState` and `onRestoreInstanceState`. Each
Screenlet uses a uniquely generated identifier (`screenletId`) to assign action
results to action sources. 

The Screenlets' states are restored after the `onCreate` and `onStart` methods, as
specified by the
[standard Android lifecycle](http://developer.android.com/training/basics/activity-lifecycle/recreating.html).
It's a best practice to execute Screenlet methods inside the activity's
`onResume` method; this helps assure that actions and tasks find their
destinations. 

## Related Topics [](id=related-topics)

[High-Level Architecture](/develop/tutorials/-/knowledge_base/7-1/high-level-architecture)

[Core Layer](/develop/tutorials/-/knowledge_base/7-1/core-layer)

[Screenlet Layer](/develop/tutorials/-/knowledge_base/7-1/screenlet-layer)

[View Layer](/develop/tutorials/-/knowledge_base/7-1/view-layer)
