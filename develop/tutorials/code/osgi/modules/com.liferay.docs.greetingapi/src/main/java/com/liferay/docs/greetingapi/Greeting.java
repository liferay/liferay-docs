package com.liferay.docs.greetingapi;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeting {

	public void greet(String name);

}