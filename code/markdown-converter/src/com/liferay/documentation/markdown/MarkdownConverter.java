package com.liferay.documentation.markdown;

import java.io.IOException;

public interface MarkdownConverter {

	public abstract String markdownToHtml(String markdown) throws IOException;

}