package com.liferay.docs.serviceoverride;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceWrapper;
import com.liferay.portal.service.UserLocalServiceWrapper;

@Component(
	immediate = true,
	property = {
	},
	service = ServiceWrapper.class
)
public class UserLocalServiceOverride extends UserLocalServiceWrapper {

	public UserLocalServiceOverride() {
		super(null);
	}

	@Override
	public int authenticateByEmailAddress(long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap, Map<String, Object> resultsMap)
					throws PortalException {

		System.out.println("Authenticating user by email address " + emailAddress);
		return super.authenticateByEmailAddress(companyId, emailAddress, password, headerMap, parameterMap, resultsMap);
	}

	@Override
	public User getUser(long userId) throws PortalException {
		System.out.println("Getting user by id " + userId);
		return super.getUser(userId);
	}

}
