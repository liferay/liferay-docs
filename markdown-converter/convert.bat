if "%1"=="" goto usage
if "%2"=="" goto usage

java -cp .;..\lib\* com.liferay.documentation.util.MarkdownToHtmlMain %1 %2

goto end

:usage

echo Usage: convert.bat [Markdown file to convert] [HTML file to be written]

echo The fist argument is the path to the Markdown file to convert to HTML.

echo The second argument is the path to the HTML file to be created.

goto end

:end
