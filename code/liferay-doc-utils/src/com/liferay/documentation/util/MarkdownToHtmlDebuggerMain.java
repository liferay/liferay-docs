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

package com.liferay.documentation.util;

import java.io.IOException;

import org.parboiled.Parboiled;
import org.parboiled.parserunners.TracingParseRunner;
import org.pegdown.Extensions;
import org.pegdown.LiferayParser;

public class MarkdownToHtmlDebuggerMain {

	public static void main(String[] args) throws IOException {

		LiferayParser liferayParser = Parboiled.createParser(
			LiferayParser.class, Extensions.ALL & ~Extensions.HARDWRAPS);
	}

	private static void testImageWithCaption(LiferayParser liferayParser) {
		TracingParseRunner<String> runner = new TracingParseRunner<String>(
				liferayParser.PictureWithCaption());

		runner.run(
			"![Figure 1.x: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)");


		System.out.println(runner.getLog());
	}

}
