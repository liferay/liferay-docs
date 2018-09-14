# Installation and Uninstallation [](id=installation-and-uninstallation)

The easiest way to install the Audience Targeting app is via
[Liferay Marketplace](http://www.liferay.com/marketplace). Make sure to install
the correct version of the app. Choose
[CE](https://www.liferay.com/marketplace/-/mp/application/43707761) if you're
running Liferay CE and
[EE](https://www.liferay.com/marketplace/-/mp/application/43707691) if you're
running Liferay EE.

If you're running Liferay on JBoss, make sure to have your
`jboss-deployment-structure.xml` file configured correctly. See the
[Installing Liferay on JBoss](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-jboss-7-1)
instructions for details.

If you're running Liferay on WebLogic, note that the Audience Targeting app
requires Liferay 6.2 EE SP10 and above to work properly.

If you're running Liferay on WebSphere, note that the Audience Targeting app
requires Liferay 6.2 EE SP11 and above to work properly. Also, when running
Liferay on WebSphere, you must have the servlet filters configured to initialize
on application startup. See the
[Installing Liferay on WebSphere 8.5](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-websphere-8-5)
section for details.

If you can't uninstall the Audience Targeting app via Marketplace or you want to
manually uninstall it, follow these steps:

1. Delete your `[Liferay Home]/data/osgi` folder.

2. Delete your app server's `work` and `temp` directories. For example, if
   you're running Liferay on Tomcat, delete your `[Liferay
   Home]/tomcat-[version]/work` and `[Liferay Home]/tomcat-[version]/temp`
   folders.

3. Delete the `analytics-hook`, `portal-6-2-x-compat-hook`, and `simulator-hook`
   files located in the `[Liferay Home]/tomcat-[version]/webapps/` folder.

4. Restart your app server.
