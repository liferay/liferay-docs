#!/bin/sh

scriptPath=$(readlink -f "$0")

classpathDir=$(dirname $(dirname "$scriptPath"))/lib
htmlFile=$1
htmlFile=$(echo "${htmlFile}" | sed 's/[^.]*$//')
htmlFile=${htmlFile}html
#echo "java -cp "${classpathDir}/*" com.liferay.documentation.util.MarkdownToHtml $1 ${htmlFile}"
#java -cp "${classpathDir}/*" com.liferay.documentation.util.MarkdownToHtml $1 ${htmlFile}
# echo "java -cp "${classpathDir}/*" com.liferay.knowledge.base.markdown.converter.cli.MarkdownConverterCLI $1 > ${htmlFile}"
java -cp "${classpathDir}/*" com.liferay.knowledge.base.markdown.converter.cli.MarkdownConverterCLI /dev/stdin 
exit 0
