# System Check [](id=system-check)

As you're developing and deploying modules and apps on @product@, knowing what's wrong in your system right away is invaluable. Liferay's `system:check`
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
command gives you that knowledge. You can enable it to run as the last Portal
startup step and you can execute it any time in Gogo shell. 

`system:check` aggregates these commands:

-  [`ds:unsatisfied`](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components#ds-unsatisfied-command):
    Reports unsatisfied Declarative Service components.

-  [`dm na`](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components#dm-na-command):
    Reports unsatisfied Dependency Manager service components, including Service
    Builder services. 

System checking functionality from future Liferay tools will be added to
`system:check`.

[Developer mode](/develop/tutorials/-/knowledge_base/7-1/using-developer-mode-with-themes#setting-developer-mode-for-your-server-in-liferay-ide)
runs `system:check` automatically on every startup. 

You can enable `system:check` to run on startup outside of developer mode by
setting this property in your `portal-ext.properties` file:

    module.framework.properties.initial.system.check.enabled=true

As stated previously, you can run the `system:check` command any time in
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
Enjoy detecting unresolved components and other issues fast using
`system:check`.

## Related Topics [](id=related-topics)

[Detecting Unresolved OSGi Components](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components)

[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
