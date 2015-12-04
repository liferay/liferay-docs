package com.liferay.docs.exampleconfig;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(id = "com.liferay.docs.exampleconfig.ExampleConfiguration")
public interface ExampleConfiguration {

    @Meta.AD(
    	deflt = "blue",
    	required = false
    )
    public String favoriteColor();

    @Meta.AD(
       deflt = "red|green|blue",
       required = false
    )
    public String[] validColors();

    @Meta.AD(required = false)
    public int favoriteNumber();

}
