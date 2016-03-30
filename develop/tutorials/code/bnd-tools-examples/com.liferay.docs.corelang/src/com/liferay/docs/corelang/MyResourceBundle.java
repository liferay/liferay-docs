package com.liferay.docs.corelang;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.language.ResourceBundleEnumeration;

@Component(property = { "language.id=en_US" }, service = ResourceBundle.class)

public class MyResourceBundle extends ResourceBundle {

	public MyResourceBundle() {
		_map.put("publish", "Publish Now");
	}

	@Override
	public Enumeration<String> getKeys() {
		Set<String> keys = _map.keySet();

		if (parent == null) {
			return Collections.enumeration(keys);
		}

		return new ResourceBundleEnumeration(keys, parent.getKeys());
	}

	@Override
	public Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}

		return _map.get(key);
	}

	@Override
	protected Set<String> handleKeySet() {
		return _map.keySet();
	}

	private final Map<String, String> _map = new HashMap<>();

}
