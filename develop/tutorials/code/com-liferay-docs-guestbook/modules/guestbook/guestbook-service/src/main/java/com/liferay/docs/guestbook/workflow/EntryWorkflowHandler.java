
package com.liferay.docs.guestbook.workflow;

import java.io.Serializable;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.liferay.docs.guestbook.model.Entry;
import com.liferay.docs.guestbook.service.EntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ResourceActions;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

@Component(immediate = true, service = WorkflowHandler.class)
public class EntryWorkflowHandler extends BaseWorkflowHandler<Entry> {

	@Override
	public String getClassName() {

		return Entry.class.getName();

	}

	@Override
	public String getType(Locale locale) {

		return _resourceActions.getModelResource(locale, getClassName());

	}

	@Override
	public Entry updateStatus(
		int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String) workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long resourcePrimKey = GetterUtil.getLong(
			(String) workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ServiceContext serviceContext =
			(ServiceContext) workflowContext.get("serviceContext");

		long guestbookId =
			_entryLocalService.getEntry(resourcePrimKey).getGuestbookId();
		
		return _entryLocalService.updateStatus(
			userId, guestbookId, resourcePrimKey, status, serviceContext);
	}

	@Reference(unbind = "-")
	protected void setEntryLocalService(EntryLocalService entryLocalService) {

		_entryLocalService = entryLocalService;
	}

	@Reference(unbind = "-")
	protected void setResourceActions(ResourceActions resourceActions) {

		_resourceActions = resourceActions;
	}

	private EntryLocalService _entryLocalService;
	private ResourceActions _resourceActions;
}
