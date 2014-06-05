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

import org.pegdown.LinkRenderer;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.RootNode;

/**
 * Converts Markdown to HTML using a {@link
 * com.liferay.documentationimporter.importer.pegdown.LiferayToHtmlSerializer}.
 *
 * @author James Hinkey
 */
public class LiferayPegDownProcessor extends PegDownProcessor {

	public LiferayPegDownProcessor(Parser parser) {
		super(parser);
	}

	@Override
	public String markdownToHtml(
		char[] markdownSource, LinkRenderer linkRenderer) {

		RootNode rootNode = parseMarkdown(markdownSource);

		LiferayToHtmlSerializer liferayToHtmlSerializer =
			new LiferayToHtmlSerializer(linkRenderer);

		return liferayToHtmlSerializer.toHtml(rootNode);
	}

}