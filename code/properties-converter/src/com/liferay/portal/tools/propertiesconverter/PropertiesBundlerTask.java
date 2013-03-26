
package com.liferay.portal.tools.propertiesconverter;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class PropertiesBundlerTask extends Task {

	@Override
	public void execute()
		throws BuildException {

		try {
			PropertiesBundler.bundleProperties(lpVersion);
		}
		catch (BuildException be) {
			System.out.println(be.getLocalizedMessage());
		}
	}
	
	public void setLpVersion(String lpVersion) {
		this.lpVersion = lpVersion;
	}
	
	private String lpVersion;

}
