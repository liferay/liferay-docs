# System Check [](id=system-check)

As you're developing and deploying modules and apps on @product@, knowing what's wrong in your system right away is invaluable. Liferay's `system:check`
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
command gives you that knowledge. It's invoked automatically as the last Portal
startup step, the first time you start @product@ and you can execute it any time
in Gogo shell. 

`system:check` aggregates these commands:

-  [`ds:unsatisfied`](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components#ds-unsatisfied-command):
    Reports unsatisfied Declarative Service components.

-  [`dm na`](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components#dm-na-command):
    Reports unsatisfied Dependency Manager service components, including Service
    Builder services. 

System checking functionality from future Liferay tools will be added to
`system:check`.

By default the command runs only on your first @product@ startup. This was done
to avoid creating too much "noise". But you can enable it to run on every
startup by setting this property in your `portal-ext.properties` file:

    module.framework.properties.initial.system.check.enabled=false

As stated previously, you can run the `system:check` command any time in
[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell).
Enjoy detecting unresolved components and other issues fast using
`system:check`.

## Related Topics [](id=related-topics)

[Detecting Unresolved OSGi Components](/develop/tutorials/-/knowledge_base/7-1/detecting-unresolved-osgi-components)

[Gogo shell](/develop/reference/-/knowledge_base/7-1/using-the-felix-gogo-shell)
