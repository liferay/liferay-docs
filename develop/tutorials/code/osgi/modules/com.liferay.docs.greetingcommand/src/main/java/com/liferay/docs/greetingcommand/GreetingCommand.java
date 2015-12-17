package com.liferay.docs.greetingcommand;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.greetingapi.Greeting;

@Component(
	immediate = true,
	property = {
		"osgi.command.scope=greet",
		"osgi.command.function=greet"
	},
	service = Object.class
)
public class GreetingCommand {

	public void greet(String name) {
		Greeting greeting = getGreeting();

		greeting.greet(name);
	}

	public Greeting getGreeting() {
		return _greeting;
	}

	@Reference
	public void setGreeting(Greeting greeting) {
		_greeting = greeting;
	}

	private Greeting _greeting;

}