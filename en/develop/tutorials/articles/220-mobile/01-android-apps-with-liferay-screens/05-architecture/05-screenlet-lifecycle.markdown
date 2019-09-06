---
header-id: screenlet-lifecycle
---

# Screenlet Lifecycle

[TOC levels=1-4]

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

## Related Topics

[High-Level Architecture](/docs/7-1/tutorials/-/knowledge_base/t/high-level-architecture)

[Core Layer](/docs/7-1/tutorials/-/knowledge_base/t/core-layer)

[Screenlet Layer](/docs/7-1/tutorials/-/knowledge_base/t/screenlet-layer)

[View Layer](/docs/7-1/tutorials/-/knowledge_base/t/view-layer)
