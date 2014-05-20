/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.documentation.pegdown;

import java.io.IOException;

import org.parboiled.Parboiled;
import org.pegdown.Extensions;
import org.pegdown.LiferayParser;
import org.pegdown.LinkRenderer;

import com.liferay.documentation.markdown.MarkdownConverter;

public class LiferayPegDownConverter implements MarkdownConverter {

	public LiferayPegDownConverter() {
		int parserOptions = Extensions.ALL & ~Extensions.HARDWRAPS;

		LiferayParser liferayParser = Parboiled.createParser(
			org.pegdown.LiferayParser.class, parserOptions);

		_liferayPegDownProcessor = new LiferayPegDownProcessor(liferayParser);
	}

	/* (non-Javadoc)
	 * @see com.liferay.documentation.pegdown.MarkdownConverter#markdownToHtml(java.lang.String)
	 */
	@Override
	public String markdownToHtml(String markdown) throws IOException {

		String html = _liferayPegDownProcessor.markdownToHtml(
				markdown, new LinkRenderer());

		return html;
	}

	private LiferayPegDownProcessor _liferayPegDownProcessor;
}
