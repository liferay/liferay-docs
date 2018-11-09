package com.liferay.docs.guestbook.xstream.configurator;

import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import com.liferay.docs.guestbook.model.impl.EntryImpl;
import com.liferay.docs.guestbook.model.impl.GuestbookImpl;
import com.liferay.exportimport.kernel.xstream.XStreamAlias;
import com.liferay.exportimport.kernel.xstream.XStreamConverter;
import com.liferay.exportimport.kernel.xstream.XStreamType;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.xstream.configurator.XStreamConfigurator;

@Component(
	immediate = true, 
	service = XStreamConfigurator.class
)
public class GuestbookXStreamConfigurator implements XStreamConfigurator {

	@Override
	public List<XStreamType> getAllowedXStreamTypes() {

		return null;
	}

	@Override
	public List<XStreamAlias> getXStreamAliases() {

		return ListUtil.toList(_xStreamAliases);
	}

	@Override
	public List<XStreamConverter> getXStreamConverters() {

		return null;
	}

	@Activate
	protected void activate() {
		
		_xStreamAliases = new XStreamAlias[] {
			new XStreamAlias(GuestbookImpl.class, "Guestbook"),
			new XStreamAlias(EntryImpl.class, "Entry"),
		};
	}

	private XStreamAlias[] _xStreamAliases;

}