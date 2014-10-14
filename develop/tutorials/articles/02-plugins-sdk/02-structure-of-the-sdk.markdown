# Structure of the SDK [](id=structure-of-the-sdk)

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-<version>/` - Plugins SDK root directory. 
    - `clients/` - client applications directory. 
    - `dist/` - archived plugins for distribution and deployment. 
    - `ext/` - Ext plugins directory. See Chapter 8 on Ext plugins. 
    - `hooks/` - hook plugins directory. See Chapter 7 on hooks. 
    - `layouttpl/` - layout templates directory. See Chapter 6 on creating
      Liferay Layout Templates. 
    - `lib/` - commonly referenced libraries. 
    - `misc/` - development configuration files. Example, a source code
      formatting specification file. 
    - `portlets/` - portlet plugins directory. See Chapter 3 on portlet
      development. 
    - `themes/` - themes plugins directory. See Chapter 5 on creating Liferay
      themes. 
    - `tools/` - plugin templates and utilities. 
    - `webs/` - web plugins directory. 
    - `build.properties` - default SDK properties. 
    - `build.<username>.properties` - (optional) override SDK properties. 
    - `build.xml` - contains targets to invoke in the SDK. 
    - `build-common.xml` - contains common targets and properties referenced
      throughout the SDK. 
    - `build-common-plugin.xml` - contains common targets and properties
      referenced by each plugin. 
    - `build-common-plugins.xml` - contains common targets and properties
      referenced by each plugin type. 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`. 

There's an Ant build file (`build.xml`) in each of the plugins directories. Here
are some Ant targets you'll commonly use in developing your plugins:

- `build-service` - builds the service layer for a plugin, using Liferay Service
  Builder. 
- `clean` - cleans the residual files created by the invocations of the
  compilation, archiving, and deployment targets. 
- `compile` - compiles the plugin source code. 
- `deploy` - builds and deploys the plugin to your application server. 
- `format-source` - formats the source code per Liferay's source code
  guidelines, informing you of violations that must be addressed. See the
  [Development Sytle](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style#section-Development+Style-Format+Source)
  community wiki page for details. 
- `format-javadoc` - formats the Javadoc per Liferay's Javadoc guidelines. See
  the [Javadoc Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines)
  community wiki page for details.

Next, let's consider some best practices for developing plugins using the SDK. 
