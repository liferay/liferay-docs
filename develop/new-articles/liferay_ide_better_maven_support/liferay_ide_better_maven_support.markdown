#Better maven support in Liferay IDE 2.1.1

##Quick resolution

Quick resolution has been added which is much more convenient for maven users to fix the maven project which has no properties.
<center>![Alt test](./img/Foo_pom_xml_Eclipse.jpg)</center>

There are two ways to fix the maven configuration problem after right click 'Failure to transfer com.liferay.maven.plugins:liferay-maven-plugin:pom${liferay.maven.plugin.version}...' > Quick Fix. Either way could fix the problem:

- Create a new maven profile based on a Liferay runtime and attach it to the project.
- Select existing maven profiles to attach the current project. 
<center>![Alt test](./img/liferay_qucik_fix.jpg)</center>

**1. Create a new maven profile based on a Liferay runtime and attach it to the project.**

- After select this fix and click Finish button, 'Create New Maven Profile' dialog will pop up, and the profile has been generated automatically based on your runtime.
- The profile location is set to 'Project pom.xml' by default, also could change to the local settings.xml file if you'd like to.
- Click 'OK' button to finish quick fix.
<center>![Alt test](./img/QuickFix_create.jpg)</center>

**2. Select existing maven profiles to attach to the current project.**

- After select this fix, 'Select Active profile' dialog will pop up, the profiles in 'Profile Id' column on the left are readed from your local settings file.
- Add one or more profiles to 'Profile Id' column on the right \> OK.
- Once finished, the project would be no more errors.
<center>![Alt test](./img/QuickFix_select.jpg)</center>


##m2e\-liferay added to m2e catalog

M2E provides a maven support in the Eclipse IDE, and m2e connectors are the plugins for m2e that contribute lifecycle mappings automatically. Now m2e-liferay has been added to catalog in liferay IDE so that maven users could use our m2e connectors to resolve the errors about lifecycle mappings for liferay maven plugins. 

**1. Existing liferay maven project in IDE.**

- Right click on the portlet project \> Properties. 
- Click Maven \> Lifecycle Mapping.
- Notice the configurator for liferay:build-css has been extended.
<center>![Alt test](./img/Properties.jpg)</center>
<center>![Alt test](./img/Properties_for_Foo.jpg)</center>

**2. Import an existing maven project with liferay properties to Eclipse-jee.**

- Go to File \> Import \> Existing Maven Projects \> Next \> Browse the valid liferay maven project that have liferay properties in pom file \> Finish.
- It would prompt to setup maven plugin connectors for liferay automatically.
- Click 'Finish' to install it. 
- It may need some time for downloading, then 'Install' dialog would pop up with m2e\-liferay checked.
- Click Next > Next > checked 'I accept the terms of the license agreements' \> Finish.
<center>![Alt test](./img/Foo_pom_xml.jpg)</center>
<center>![Alt test](./img/Browse_liferay_maven_project.jpg)</center>
<center>![Alt test](./img/install_m2e_liferay_dialog.jpg)</center>

**3. Import an existing maven project without liferay properties to vanilla Eclipse\-jee.**

- Go to File \> Import \> Existing Maven Projects \> Next \> Browse the liferay maven project \> Finish.
- It would prompt to setup maven plugin connectors for liferay automatically, but it fails to resolve since no marketplace entries found to handle liferay\-maven\-plugin: $\{liferay.maven.plugin.version\}:build\-css.
- Click 'Finish' to resolve later \> OK for the incomplete maven goal execution.
- Go to Window \> Preferences \> Maven \> Discovery \> Open Catalog.
- Checked 'm2e connector for liferay\-maven\-plugin' then Finish.
- Install m2e\-liferay according to the poped up 'Install' dialog then restart Eclipse.
- Once finished, the project would still have some errors due to no liferay properties, to resolve this, we can use the quick fix mentioned above. 
<center>![Alt test](./img/reslver_later.jpg)</center>
<center>![Alt test](./img/m2e_liferay_Marketplace.jpg)</center>


##Remote projects

Maven projects could be deployed to remote server except for liferay service builder maven projects, which will be coming soon in the next release. 