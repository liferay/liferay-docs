package com.liferay.docs.emailaddressauthenticator;

import java.util.Map;

import com.liferay.docs.emailaddressauthenticator.validator.EmailAddressValidator;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.AuthException;
import com.liferay.portal.kernel.security.auth.Authenticator;
import com.liferay.portal.kernel.service.UserLocalService;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;

@Component(
	immediate = true,
	property = {"key=auth.pipeline.post"},
	service = Authenticator.class
)
public class EmailAddressAuthenticator implements Authenticator {

	@Override
	public int authenticateByEmailAddress(long companyId, String emailAddress,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		return validateDomain(emailAddress);
	}

	@Override
	public int authenticateByScreenName(long companyId, String screenName,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		String emailAddress = 
			_userLocalService.fetchUserByScreenName(companyId, screenName).getEmailAddress();
		
		return validateDomain(emailAddress);
	}

	@Override
	public int authenticateByUserId(long companyId, long userId,
			String password, Map<String, String[]> headerMap,
			Map<String, String[]> parameterMap) throws AuthException {
		
		String emailAddress = 
			_userLocalService.fetchUserById(userId).getEmailAddress();
		
		return validateDomain(emailAddress);
	}
	
	private int validateDomain(String emailAddress) throws AuthException {
		
		if (_emailValidator == null) {
			
			String msg = "Email address validator is unavailable, cannot authenticate user";			
			_log.error(msg);
			
			throw new AuthException(msg);
		}
		
		if (_emailValidator.isValidEmailAddress(emailAddress)) {		
			return Authenticator.SUCCESS;
		}
		return Authenticator.FAILURE;
	}
	
	@Reference
	private volatile UserLocalService _userLocalService;
	
	@Reference(
		policy = ReferencePolicy.DYNAMIC,
		cardinality = ReferenceCardinality.OPTIONAL
	)
	private volatile EmailAddressValidator _emailValidator;
	
	private static final Log _log = LogFactoryUtil.getLog(EmailAddressAuthenticator.class);
}