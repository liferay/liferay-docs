package com.liferay.docs.insult.asset;

import java.util.Locale;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.docs.insult.service.permission.InsultPermission;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portlet.asset.model.BaseAssetRenderer;
import com.liferay.docs.insult.model.Insult;

public class InsultAssetRenderer extends BaseAssetRenderer {

private Insult _ins;
	
	public InsultAssetRenderer (Insult ins) {
		
		_ins = ins;
	}

	@Override
	public boolean hasEditPermission(PermissionChecker permissionChecker) {
		
		return InsultPermission.contains(permissionChecker, _ins,
				ActionKeys.UPDATE);
	}

	@Override
	public boolean hasViewPermission(PermissionChecker permissionChecker) {
		
		return InsultPermission.contains(permissionChecker, _ins,
				ActionKeys.VIEW);
	}

	@Override
	public String getClassName() {
		
		return _ins.getClass().getName();
	}

	@Override
	public long getClassPK() {
		
		return _ins.getPrimaryKey();
	}

	@Override
	public long getGroupId() {
		
		return _ins.getGroupId();
	}

	@Override
	public String getSummary(Locale arg0) {
		
		return com.liferay.portal.kernel.util.HtmlUtil.escape(_ins.getInsultString());
	}

	@Override
	public String getTitle(Locale arg0) {
		
		return _ins.getInsultString();
	}

	@Override
	public long getUserId() {
		
		return _ins.getUserId();
	}

	@Override
	public String getUserName() {
		
		return _ins.getUserName();
	}

	@Override
	public String getUuid() {
		
		return _ins.getUuid();
	}

	@Override
	public String render(RenderRequest request, RenderResponse response,
			String template) throws Exception {
		if (template.equals(TEMPLATE_FULL_CONTENT)) {
			request.setAttribute("INSULT ENTRY", _ins);
			return "/html/insult/" + template + ".jsp";
		} else {
			return null;
		}
	}

}
