# Configuring View Parameters [](id=configure-view-parameters-liferay-portal-6-2-dev-guide-en)

In the case of a portlet `RenderRequest`, Section 5.2.6 of the JSR 329 Spec
requires that the bridge ensure that only the `RESTORE_VIEW` and
`RENDER_RESPONSE` phases of the JSF lifecycle execute. In addition, Section 6.4
requires that a `PhaseListener` be used to skip the `APPLY_REQUEST_VALUES`,
`PROCESS_VALIDATIONS`, `UPDATE_MODEL_VALUES`, and `INVOKE_APPLICATION` phases.
These requirements are valid for JSF 1.x, but for JSF 2.x *View Parameters*, the
presence of `f:metadata` and `f:viewParam` in a Facelet view, makes the
entire JSF lifecycle run. 

Liferay Faces Bridge enables support for View Parameters by default, but
provides a configuration option in the `WEB-INF/web.xml` descriptor that lets
you disable the feature. 

    <!-- The default value of the following context-param is true. -->
    <context-param>
        <param-name>com.liferay.faces.bridge.viewParametersEnabled</param-name>
        <param-value>false</param-value>
    </context-param>

If it is necessary to use the JSF 1.x version of this feature, then this
parameter should be set to `false`. 

Now that we've discussed JSF portlet bridge standards and Liferay Faces Bridge
configuration options, let's learn how Liferay Faces Portal lets you leverage
Liferay Portal's utilities and component tags. 
