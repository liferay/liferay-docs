---
header-id: awt-security
---

# AWT Security

[TOC levels=1-4]

Specify the AWT operations the plugin is permitted to access. 

*Example:*

    security-manager-awt-operations=\
        accessClipboard,\
        accessEventQueue,\
        accessSystemTray,\
        createRobot,\
        fullScreenExclusive,\
        listenToAllAWTEvents,\
        readDisplayPixels,\
        replaceKeyboardFocusManager,\
        setAppletStub,\
        setWindowAlwaysOnTop,\
        showWindowWithoutWarningBanner,\
        toolkitModality,\
        watchMousePointer
