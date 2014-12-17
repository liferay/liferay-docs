## Liferay IDE vs Liferay Developer Studio ##

### Basics ###

- Liferay IDE is a set of Eclipse plugins that support Liferay development. It is shipped
as one single Eclipse feature (a set of plugins) that can be installed in any existing Eclipse install (3.6.x, 3.7.x, 4.2.x) 
- Liferay Developer Studio is another set of Eclipse plugins but its a super-set of Liferay IDE, i.e., Liferay Developer Studio contains all of the Liferay IDE plugins with some additional plugins that support EE developers (e.g. WebSphere support, Kaleo Designer for Java)
- Liferay Developer Studio is also available as an All-in-one bundle, which is a shrink-wrapped copy of Eclipse (3.6.x) that has all of the Studio Eclipse plugins pre-installed (which subsequently includes all of the Liferay IDE plugins). 

### Free vs Non-free ###

- Liferay IDE is free and open source and always will be. Think of it as the "Community Edition" of the Liferay developer tooling. Thus it is targeted for use by Liferay Portal Community Edition developers.
- Liferay Developer Studio is only for EE customers (or EE trial users). 

### Features that are only in Liferay Developer Studio ###

- Eclipse all-in-one bundle packaging.
- Bundled Portal Server EE 
- Bundled Plugins SDK EE 
- Example Project wizard for bundled example projects 
- WebSphere 6.x/7.0 Server development support 
- Kaleo Designer for Java

### Support ###

- Liferay IDE is community supported in the Liferay IDE forums. 
- Liferay Developer Studio users have email based support in addition to the forums. 

### CE vs EE Liferay servers ###

- In Developer Studio there is a new type of Liferay Tomcat server that is available for developers, and that is the Liferay Tomcat Server EE, which is just an upgraded version of the CE server that is to be used specifically for EE edition of Liferay. 
- If you just have Liferay IDE, you can use the Liferay Server CE version to develop against Liferay Portal EE, but you may miss EE specific features in the future. 
- For Liferay Developer Studio users, they may use CE version of Liferay, if they also have a properly license EE server also configured in Studio.


