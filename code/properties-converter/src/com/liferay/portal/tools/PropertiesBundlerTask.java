
package com.liferay.portal.tools;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class PropertiesBundlerTask extends Task {

	@Override
	public void execute()
		throws BuildException {

		try {
			PropertiesBundler bundler = new PropertiesBundler();

			bundler.bundleProperties(propertiesDir, lpVersion);
		}
		catch (BuildException be) {
			System.out.println(be.getLocalizedMessage());
		}
	}
	
	public void setLpVersion(String lpVersion) {
		this.lpVersion = lpVersion;
	}

	public void setPropertiesDir(String propertiesDir) {
		this.propertiesDir = propertiesDir;
	}

	private String lpVersion;
	private String propertiesDir;

}
