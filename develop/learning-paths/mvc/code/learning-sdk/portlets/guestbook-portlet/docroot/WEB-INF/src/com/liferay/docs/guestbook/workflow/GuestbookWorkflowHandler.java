package com.liferay.docs.guestbook.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import com.liferay.docs.guestbook.model.Guestbook;
import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.service.ServiceContext;

public class GuestbookWorkflowHandler extends BaseWorkflowHandler {

@Override
public String getClassName() {

    return CLASS_NAME;
}

@Override
public String getType(Locale locale) {
    return LanguageUtil.get(locale,  "model.resource" + CLASS_NAME);
}

@Override
public Object updateStatus(int status,
        Map<String, Serializable> workflowContext) throws PortalException,
        SystemException {

        long userId = GetterUtil.getLong(
            (String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
        long guestbookId = GetterUtil.getLong(
            (String)workflowContext.get(
                WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

        ServiceContext serviceContext = (ServiceContext)workflowContext.get(
            "serviceContext");

        return GuestbookLocalServiceUtil.updateStatus(
            userId, guestbookId, status, serviceContext);

}

public static final String CLASS_NAME = Guestbook.class.getName();

}
