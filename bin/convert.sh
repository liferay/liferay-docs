#!/bin/sh

if [ $# -lt 1 ]; then
	echo
	echo Usage: ./convert.sh \[Markdown file to convert\] or ./convert.sh \[Markdown file to convert\] \[HTML file to be written\]
	echo
	echo The first argument is the path to the Markdown file to convert to HTML.
	echo
	echo The second argument is optional. It specifies the path to the HTML file to be created. If this argument is omitted, the HTML file to be created will be created in the same directory as the Markdown file and will have the same filename as the Markdown file except that the .markdown file extension will be replaced by the .html file extension.  
	echo
	exit 1
elif [ $# -lt 2 ]; then
	htmlFile=$1
	htmlFile=$(echo "${htmlFile}" | sed 's/[^.]*$//')
	htmlFile=${htmlFile}html
	java -cp "../lib/*" com.liferay.documentation.util.MarkdownToHtmlMain $1 ${htmlFile}
	exit 0
else
	java -cp "../lib/*" com.liferay.documentation.util.MarkdownToHtmlMain $1 $2
	exit 0
fi
