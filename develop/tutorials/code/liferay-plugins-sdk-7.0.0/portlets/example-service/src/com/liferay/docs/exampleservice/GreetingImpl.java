package com.liferay.docs.exampleservice;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.exampleapi.Greeting;

@Component(immediate=true)
public class GreetingImpl implements Greeting {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}
