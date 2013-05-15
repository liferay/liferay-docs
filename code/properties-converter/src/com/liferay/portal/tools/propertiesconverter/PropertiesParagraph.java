
package com.liferay.portal.tools.propertiesconverter;

import java.util.ArrayList;
import java.util.List;

public class PropertiesParagraph {

	public PropertiesParagraph(String paragraph) {
		_text = paragraph;

		String[] lines = paragraph.split("\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith("    ")) {
				_isPreFormatted = true;
				break;
			}
		}
	}

	public boolean get_isPreFormatted() {
		
		return _isPreFormatted;
	}

	public String get_text() {
		
		return _text;
	}

	public void set_isPreFormatted(boolean isPreFormatted) {
		
		_isPreFormatted = isPreFormatted;
	}

	public void set_text(String text) {
		
		_text = text;
	}

	private boolean _isPreFormatted;

	private String _text;

}