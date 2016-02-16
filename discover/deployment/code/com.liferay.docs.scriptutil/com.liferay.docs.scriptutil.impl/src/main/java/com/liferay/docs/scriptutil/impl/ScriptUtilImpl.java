package com.liferay.docs.scriptutil.impl;

import org.osgi.service.component.annotations.Component;

import com.liferay.docs.scriptutil.api.ScriptUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

@Component(
	immediate = true,
	property = {
	},
	service = ScriptUtil.class
)
public class ScriptUtilImpl implements ScriptUtil {

	@Override
	public String operationOne() {
		_log.debug("Inside of operation one!");

		return "Hello world!";
	}

	@Override
	public String operationTwo(String name) {
		_log.debug("Inside of operation two!");

		return "Hello " + name + "!";
	}

	private static Log _log = LogFactoryUtil.getLog(ScriptUtilImpl.class); 

}