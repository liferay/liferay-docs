package com.liferay.docs.greetingimpl;

import com.liferay.docs.greetingapi.Greeting;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true,
	property = {
	},
	service = Greeting.class
)

public class GreetingImpl implements Greeting {

	@Override
	public void greet(String name) {
		System.out.println("Hello " + name + "!");
	}

}