package com.liferay.docs.insult.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.liferay.docs.insult.model.Insult;
import com.liferay.docs.insult.service.InsultLocalServiceUtil;

public class InsultAssetRendererFactory extends BaseAssetRendererFactory {

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
			throws PortalException, SystemException {
		Insult ins = InsultLocalServiceUtil.getInsult(classPK);
		return new InsultAssetRenderer(ins);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isLinkable() {
		return true;
	}
	
	@Override
	public boolean isCategorizable() {
		return true;
	}
	
	public static final String CLASS_NAME = Insult.class.getName();
	public static final String TYPE = "insult";
	    

}
