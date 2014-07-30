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

//		testImageWithCaption(liferayParser);

		testSidebar(liferayParser);
	}

	private static void testImageWithCaption(LiferayParser liferayParser) {
		TracingParseRunner<String> runner = new TracingParseRunner<String>(
				liferayParser.PictureWithCaption());

		runner.run(
			"![Figure 1.x: Liferay IDE/Developer Studio's new project wizard makes it easy to create Liferay projects.](../../images/lds-new-liferay-plugin-project-1.png)");


		System.out.println(runner.getLog());
	}

	private static void testSidebar(LiferayParser liferayParser) {
		TracingParseRunner<String> runner =
			new TracingParseRunner<String>(liferayParser.Sidebar());

//		You can always copy in the components of the NodeSequence from the LiferayParser to debug.
//
//			new TracingParseRunner<String>(liferayParser.Sequence(
//				liferayParser.TestNot(liferayParser.OneOrMore(liferayParser.CharEntity())),
//				'!', liferayParser.Label(), liferayParser.Spn1(), '(',
//				liferayParser.LinkSource(), ')',
//				liferayParser.Sp(), liferayParser.Para()
//				));

		runner.run(
			"![tip](../../images/01-tip.png) The first time you create a project, make sure\n" +
			"you're online. In the background, Liferay's Plugins SDK downloads several .jar\n" +
			"files that it needs in order to operate. This keeps the initial download small,\n" +
			"but makes it take a long time to create your first project (about four minutes).\n" +
			"After the initial download, it won't happen again. \n\n");

		System.out.println(runner.getLog());
	}

}
