# Development Reference [](id=development-reference)

Here you'll find Liferay's reference documentation for Java, JavaScript
(including AlloyUI), tag libraries, deployment descriptors, and Liferay Faces. 

<h3><span style="font-size: 22px;">Java APIs</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/6.2/javadocs/" target="_blank">
Portal API<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Describes Liferay packages and classes for you to use in your Liferay plugins.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/6.2/javadocs-all/" target="_blank">
Internal API <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
These docs are for those hacking on Liferay's core. Many of the classes
referenced in this documentation should not be referenced directly from Liferay
plugins.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">Taglibs</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/6.2/taglibs/" target="_blank">
Portal Taglibs <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Tag libraries for <a href="http://alloyui.com/" target="_blank">AlloyUI <span
class="opens-new-window-accessible">(Opens New Window)</span></a>, Liferay UI,
Liferay themes, Liferay portlets, Liferay security, and standard portlets.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/faces/3.1/vdldoc/" target="_blank">
Faces Taglibs<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
The latest version of Liferay Faces JSF tag documentation in View Declaration
Language (VDL) format. VDL docs for all versions of Liferay Faces are available
at <a href="http://docs.liferay.com/faces/"
target="_blank">http://docs.liferay.com/faces/ <span
class="opens-new-window-accessible">(Opens New Window)</span></a>.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">JavaScript &amp; CSS</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://alloyui.com/versions/2.0.x/" target="_blank">
AlloyUI 2&nbsp;<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Liferay includes AlloyUI and all of its JavaScript APIs are available
within portlets, templates and themes.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://getbootstrap.com/2.3.2" target="_blank">
Bootstrap 2.3.2 <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Liferay uses Bootstrap natively and all of its CSS classes are available within
portlets, templates and themes.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">Descriptor Definitions</span></h3>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/6.2/definitions/" style="text-decoration: none;" target="_blank">
DTDs <span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Describes the XML files used in configuring Liferay apps, Liferay plugins, and
Liferay Portal.
</p>

<p style="margin-left: 40px;">&nbsp;</p>

<p style="margin-left: 40px;">
<span style="font-size:18px;">
<a href="http://docs.liferay.com/portal/6.2/propertiesdoc/liferay-plugin-package_6_2_0.properties.html"
id="yui_patched_v3_11_0_1_1414746645512_1007" style="word-spacing: normal;
outline: 0px;" target="_blank">
liferay-plugin-package_6_2_0.properties<span class="opens-new-window-accessible">(Opens New Window)</span>
</a>
</span>
</p>

<p style="margin-left: 40px;">
Defines properties used for Liferay plugins. These properties describe the
the Liferay plugin, declare its resources, and specify its security related
parameters.  </p>

<p style="margin-left: 40px;">&nbsp;</p>

<h3><span style="font-size: 22px;">Liferay's Deprecation Policy</span></h3>

<p style="margin-left: 40px;">
Methods in Liferay's APIs are deprecated when they're no longer called by
Liferay internally. Method deprecation occurs during major and minor releases of
Liferay. A change in the first or second digits of consecutive Liferay releases
indicates a major or minor release, respectively. For example, the release of
Liferay Portal 6.0.0 after 5.2.0 was a major release; whereas the release of
6.2.0 after 6.1.30 was a minor release. Major and minor releases can have API
deprecations.
</p>

<p style="margin-left: 40px;">
APIs should not be deprecated between maintenance releases. Maintenance releases
are signified by a change in the third digit of the release number. For example,
the release of Liferay Portal 6.1.30 after 6.1.20 was a maintenance release and
therefore should have no API deprecations.
</p>

