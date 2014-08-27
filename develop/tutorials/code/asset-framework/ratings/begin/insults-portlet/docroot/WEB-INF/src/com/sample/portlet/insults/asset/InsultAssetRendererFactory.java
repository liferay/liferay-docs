package com.sample.portlet.insults.asset;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portlet.asset.model.AssetRenderer;
import com.liferay.portlet.asset.model.BaseAssetRendererFactory;
import com.sample.portlet.insults.model.Insult;
import com.sample.portlet.insults.service.InsultLocalServiceUtil;

public class InsultAssetRendererFactory extends BaseAssetRendererFactory {

	@Override
	public AssetRenderer getAssetRenderer(long classPK, int type)
			throws PortalException, SystemException {
		Insult mfg = InsultLocalServiceUtil.getInsult(classPK);
		return new InsultAssetRenderer (mfg);
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
	public static final String TYPE = "manufacturer";
	    

}
