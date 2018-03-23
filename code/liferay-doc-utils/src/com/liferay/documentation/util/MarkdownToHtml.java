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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.liferay.knowledge.base.markdown.converter.factory.MarkdownConverterFactoryUtil;
import com.liferay.knowledge.base.markdown.converter.MarkdownConverter;

public class MarkdownToHtml {

	public static void main(String[] args) throws IOException {
		if (args == null || args.length < 2) {
			throw new IllegalArgumentException("Requires 2 arguments: markdownFile htmlFile");
		}

		String markdownFile = args[0];
		String htmlFile = args[1];

        StringBuilder sb = new StringBuilder();

	    BufferedReader br = new BufferedReader(new FileReader(markdownFile));

	    String line = br.readLine();

	    while (line != null) {
	    	sb.append(line);
	    	sb.append("\n");
	    	line = br.readLine();
		}

	    br.close();

	    MarkdownConverter converter = MarkdownConverterFactoryUtil.create();

	    String html = converter.convert(sb.toString());

		BufferedWriter out =
			new BufferedWriter(new FileWriter(htmlFile));

		out.append(html);
		out.append("\n");
		out.flush();
		out.close();
	}

}
