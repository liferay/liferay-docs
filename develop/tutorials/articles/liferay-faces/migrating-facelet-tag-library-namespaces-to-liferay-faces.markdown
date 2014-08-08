# Migrating Facelet Tag Library Namespaces to Liferay Faces

In this tutorial, you'll learn how to migrate facelet tag library namespaces
from PortletFaces to Liferay Faces. For further context as to why this is
needed, visit the [Migrating BridgeRequestAttributeListener to Liferay
Faces](/develop/tutorials/-/knowledge_base/migrating-bridgerequestattributelistener-to-liferay-faces)
tutorial. 

<!-- Make sure above link is correct when Liferay Faces tutorials are uploaded
to dev.liferay.com. -Cody -->

The projects at portletfaces.org provided several UIComponents and Composite
Components for use within Facelet views. The tag library documentation for these
components has been migrated to VDL documentation for each version of the
Liferay Faces Bridge: 

- The VDL documentation for the Liferay Faces 2.1 can be found at
  <http://docs.liferay.com/faces/2.1/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0-legacy can be found at
  <http://docs.liferay.com/faces/3.0-legacy/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.0 can be found at
  <http://docs.liferay.com/faces/3.0/vdldoc/>. 
- The VDL documentation for the Liferay Faces 3.1 can be found at
  <http://docs.liferay.com/faces/3.1/vdldoc/>. 
- The VDL documentation for Liferay Faces 3.2 can be found at
  <http://docs.liferay.com/faces/3.2/vdldoc/>.

<!-- Re-add upon release. - Jim
- The VDL documentation for the Liferay Faces 4.1 can be found at <http://docs.liferay.com/faces/4.1/vdldoc/>.
- The VDL documentation for the Liferay Faces 4.2 can be found at <http://docs.liferay.com/faces/4.2/vdldoc/>.
-->

Each link for the VDL documentation contains information about all the `aui`,
`aui-cc`, `bridge`, `liferay-ui`, `liferay-util`, and `liferay-security` tags
for that version of the Liferay Faces Bridge, so each VDL documentation link
basically holds the entirety of the PortletFaces tag library documentation (with
the exception of certain tags which are excluded from certain versions of the
Lfieray Faces Bridge). 

By using the VDL documentation, your migration of the facelet tag library
namespaces from PortletFaces to Liferay Faces is a snap! 

## Related Topics

<!-- Add once JSF tutorials are finished. -Cody -->
