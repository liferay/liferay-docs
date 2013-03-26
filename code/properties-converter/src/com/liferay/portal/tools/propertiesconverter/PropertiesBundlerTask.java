
package com.liferay.portal.tools.propertiesconverter;

import java.io.IOException;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

public class PropertiesBundlerTask extends Task {

	@Override
	public void execute()
		throws BuildException {

		try {
			PropertiesBundler.bundleProperties();
		}
		catch (BuildException be) {
			System.out.println(be.getLocalizedMessage());
		}
	}

}
