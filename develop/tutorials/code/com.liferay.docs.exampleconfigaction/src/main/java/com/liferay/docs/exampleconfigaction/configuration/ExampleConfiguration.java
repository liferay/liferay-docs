package com.liferay.docs.exampleconfigaction.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.liferay.docs.exampleconfigaction.configuration.ExampleConfiguration")
public interface ExampleConfiguration {

	@Meta.AD(deflt="blue", required = false)
	public String favoriteColor();

}
