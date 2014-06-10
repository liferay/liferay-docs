package com.liferay.sample.hook.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExampleStrutsAction extends BaseStrutsAction {

	public String execute(
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {

		String name = ParamUtil.get(request, "name", "World");

		request.setAttribute("name", name);

		return "/portal/sample.jsp";
	}

}

