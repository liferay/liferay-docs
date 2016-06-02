#Working with Plugin SDK in IDE 3.0

###Introduction

In IDE 3.0, it did some changes in Plugin SDKs including supported SDK version,
running environment, workspace, SDK directory project and SDK pre-page.

1. Supported SDK Version:

	 Now SDK 6.2 and 7.0 could be used in IDE 3.0，we no longer support SDK 6.0
	 and 6.1 version.
	 
2. Running Environment: JDK 8 required

3. Workspace : 

	- One eclipse workspace can only use one SDK, if you want to use another
	SDK, launch a new workspace.
	
	- If you want to launch a new workspace, click File>Launch Workspace>New
	Workspace.
	
	- If you want to copy the preferences, workbench layout and working sets
	setting from original workspace, you can check the Copy Settings boxes
	below:
	
		![](images/sdk_plugin_workspace.PNG)

4. SDK Directory Project:

	- When you create a new Liferay Plugin(Ant) Project, you can import an SDK
	by clicking Next and then browser the SDK Directory.
	
		![](images/sdk_plugin_import1.PNG)

	- And there is also another way to import SDK, go to
	File>Import…>Liferay>Liferay Plugins SDK Directory, and then input the SDK
	Directory(the root directory of your SDK)
	
		![](images/sdk_plugin_import2.PNG)

5. Plugin SDK Pre-page: 
	-  Installed Plugin SDKs has  been removed from the Preference

		![](images/sdk_plugin_preferences.PNG)
