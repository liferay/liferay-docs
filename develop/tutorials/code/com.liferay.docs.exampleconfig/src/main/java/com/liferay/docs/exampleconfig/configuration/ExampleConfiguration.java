package com.liferay.docs.exampleconfig.configuration;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.liferay.docs.exampleconfig.ExampleConfiguration")
public interface ExampleConfiguration {

	@Meta.AD(required = false)
	public String favoriteColor();

}