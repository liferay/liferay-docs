package com.liferay.docs.examplecommand;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.exampleapi.Greeting;

@Component(
		property = {
				"osgi.command.scope=example",
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
