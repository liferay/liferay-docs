# Using the CMIS Store [](id=using-the-cmis-store)

Though you can mount as many different CMIS (Content Management Interoperability
Services) repositories as you like in the Documents and Media library, you may
wish also to redefine the @product@ repository to point to a CMIS repository as
well. Why? Users might want to create a folder or upload content to the @product@
repository. It would be nice if that @product@ repository was connected to a
clustered CMIS repository by the administrator without having to mount it
through the UI. The CMIS store allows you to do just that. 

+$$$

**Note:** CMIS Store is not suitable for production use and is deprecated as of 
Liferay Portal CE 7.0 and Liferay DXP. Because it can have performance issues 
with large repositories, it's recommended that you use one of the other 
configurations listed above, such as Advanced File System Store, to store your 
Documents and Media files. This deprecation does not affect the use of external 
repositories. You can still [connect to external repositories](/discover/portal/-/knowledge_base/7-0/using-external-repositories) 
using CMIS.

$$$

If you wish to use the CMIS store, follow the instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#cmis)
to set it up. The @product@ repository is connected to CMIS via the CMIS store. As
long as all nodes are pointing to your CMIS repository, everything in your
@product@ cluster should be fine, as the CMIS protocol prevents multiple
simultaneous file access from causing data corruption. 
