package com.liferay.docs.emailaddressvalidator.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.osgi.service.component.annotations.Component;
import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;

@Component(
	immediate = true,
	property = {
	},
	service = EmailAddressValidator.class
)
public class EmailAddressValidatorImpl implements EmailAddressValidator {

	@Override
	public boolean isValidEmailAddress(String emailAddress) {
		
		if (_validEmailDomains.contains(
			emailAddress.substring(emailAddress.indexOf('@')))) {
		
			return true;
		}
		return false;
	}

	private Set<String> _validEmailDomains = 
		new HashSet<String>(Arrays.asList(new String[] {"@liferay.com", "@example.com"}));
}
