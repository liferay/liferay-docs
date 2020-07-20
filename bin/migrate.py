import os
import re
import shutil
import sys


def append_to_line(line, new_line):
    line = line.replace("\r","")
    line = line.replace("\n", "")
    line = line + " " + new_line.lstrip()
    return line

def end_sidebar(sidebar_line, newFile):
    if sidebar_line not in "":
        newFile.write(sidebar_line)
        newFile.write("```\n")
        sidebar_line = ""
    return sidebar_line

def write_and_reset_string(line, newFile):
    if line not in "":
        newFile.write(line)
        line = ""

    return line


if __name__ == "__main__":

    if (len(sys.argv) < 2):
        print("Usage:\n\tpython migrate.py article [dest_folder]\n\nDescription:\n\tMigrates the article to an .md file and converts the content to liferay-learn Markdown/RST syntax. The converted article destination folder [dest_folder] is the current folder by default but is typically set to a liferay-learn folder.\n\n\tIMPORTANT: Run this script in the liferay-docs article's folder so that the script can use the image file paths found in the article to copy the image files to the [dest_folder]/images/ folder.")
        sys.exit()

    article = sys.argv[1]

    dest_folder = "."
    if (len(sys.argv)) > 2:
        dest_folder = sys.argv[2]

    if not (os.path.isdir(dest_folder)):
        os.mkdir(dest_folder)

    new_article_path = dest_folder + "/" + article.split('.markdown')[0] + ".md"

    # Copy referenced image files

    file = open(article)
    content = file.read()
    file.close()

    png_split = content.split('.png)')

    images = []

    png_split_len = len(png_split) - 1
    ii = 0;

    while (ii < png_split_len):
        paran_split = png_split[ii].split('(')

        if (len(paran_split) > 1):
            image = paran_split[len(paran_split) - 1] + '.png'
            images.append(image)

        ii = ii + 1

    image_folder = dest_folder + "/images"

    if (len(images) > 0):

        if not os.path.isdir(image_folder):
            os.mkdir(image_folder)

        print("Writing images to folder: " + image_folder)

    for ff in images:
        shutil.copy(ff, image_folder)

    # Process the text

    file = open(article)
    lines = file.readlines()
    file.close()

    newFile = open(new_article_path, 'w')

    print("Migrating to article: " + new_article_path)

    # Track the last significant line's first non-space character column index
    prev_index = -1

    list_item_line = ""
    para_line = ""
    sidebar_line = ""

    done_header_id = False
    done_title = False
    in_code = False
    in_header_id = False

    for line in lines:

        # Set all image file locations to the ./images/ folder
        line = re.sub("\]\((../)+images/", "](./images/", line)

        # Replace legacy tokens
        line = re.sub("@product@", "Liferay DXP", line)
        line = re.sub("@commerce@", "Liferay Commerce", line)
        line = re.sub("@ide@", "Dev Studio DXP", line)
        line = re.sub("@app-ref@", "https://docs.liferay.com/dxp/apps", line)
        line = re.sub("@platform-ref@", "https://docs.liferay.com/dxp/portal", line)

        trimmed_line = line.lstrip()

        if not (done_header_id):

            if not (in_header_id):
                if (trimmed_line.startswith("---")):
                    in_header_id = True
            elif (trimmed_line.startswith("---")):
                    done_header_id = True
                    in_header_id = False
        elif not (done_title):
            if (line.startswith("#")):
                newFile.write(line)
                done_title = True
        elif (trimmed_line.startswith("[TOC")):
            # Don't write anything for the legacy TOC line
            continue
        elif (trimmed_line.startswith("```")):
            # Toggle whether we're in code

            # Write any saved text
            para_line = write_and_reset_string(para_line, newFile)
            sidebar_line = end_sidebar(sidebar_line, newFile)
            list_item_line = write_and_reset_string(list_item_line, newFile)

            # Write the current line as is
            newFile.write(line)

            # Track whether code block has started or ended
            if (in_code):
                in_code = False
            else:
                in_code = True
        elif re.search("^\d.", trimmed_line):
            # Handle an ordered list item

            # Write any saved text
            para_line = write_and_reset_string(para_line, newFile)
            sidebar_line = end_sidebar(sidebar_line, newFile)
            list_item_line = write_and_reset_string(list_item_line, newFile)

            # Start all ordered list items with 1.
            list_item_line = re.sub("\d+.\s*", "1. ", line, 1)

            prev_index = re.search("\S", line).start()
        elif trimmed_line.startswith("-"):
            # Handle an unordered-list item starting with -

            # Write any saved text
            para_line = write_and_reset_string(para_line, newFile)
            sidebar_line = end_sidebar(sidebar_line, newFile)
            list_item_line = write_and_reset_string(list_item_line, newFile)

            # Start list items with '*' instead of '-', followed by a single space
            list_item_line = re.sub("-\s*", "* ", line, 1)

            prev_index = re.search("\S", line).start()
        elif trimmed_line.startswith("* "):
            # Handle an unordered-list item starting with *

            # Write any saved text
            para_line = write_and_reset_string(para_line, newFile)
            sidebar_line = end_sidebar(sidebar_line, newFile)
            list_item_line = write_and_reset_string(list_item_line, newFile)

            # Start list items with '*', followed by a single space
            list_item_line = re.sub("\*\s*", "* ", line, 1)

            prev_index = re.search("\S", line).start()
        elif re.search("^\|", line.strip()):

            if not re.search("^\|.*\|$", line.strip()):

                # Handle a sidebar line

                # Write any saved text
                para_line = write_and_reset_string(para_line, newFile)
                list_item_line = write_and_reset_string(list_item_line, newFile)

                # Replace the leading pipe with a space
                stripped_line = line.replace("| ", "", 1)

                # Use double-back ticks for all code markup
                stripped_line = stripped_line.replace("`", "``")

                if sidebar_line in "":

                    # Start the sidebar
                    if "**Note:**" in stripped_line:
                        newFile.write("```note::\n   ")
                        stripped_line = stripped_line.replace("**Note:**", "", 1)
                    elif "**Warning:**" in stripped_line:
                        newFile.write("```warning::\n   ")
                        stripped_line = stripped_line.replace("**Warning:**", "", 1)
                    elif "**Important:**" in stripped_line:
                        newFile.write("```important::\n   ")
                        stripped_line = stripped_line.replace("**Important:**", "", 1)
                    elif "**Note**:" in stripped_line:
                        newFile.write("```note::\n   ")
                        stripped_line = stripped_line.replace("**Note**:", "", 1)
                    elif "**Important**:" in stripped_line:
                        newFile.write("```important::\n   ")
                        stripped_line = stripped_line.replace("**Important**:", "", 1)
                    elif "**Warning**:" in stripped_line:
                        newFile.write("```warning::\n   ")
                        stripped_line = stripped_line.replace("**Warning**:", "", 1)
                    else:
                        newFile.write("```note::\n   ")

                # Process the sidebar line, creating a new line that uses RST links in place of Markdown links
                new_line = ""
                caption_link_index = -1
                while True:
                    stripped_line_len = len(stripped_line)

                    caption_link = re.search("\[[\w\s\.\/\-\_\`\'@]*\]\([\w\s:\.\/:\-\_\`]*\)", stripped_line)

                    link_len = 0
                    if caption_link:

                        link_len = len(caption_link.group());
                        caption_link_index = stripped_line.index(caption_link.group());
                        if caption_link_index > 0:
                            # Add text that precedes the caption
                            text_before_caption = stripped_line[0:caption_link_index]
                            new_line += text_before_caption;

                        # Split the caption and link parts of the Markdown
                        caption_link_split = caption_link.group().split("](");
                        caption_half = caption_link_split[0]
                        link_half = caption_link_split[1]

                        caption = ""
                        link = ""

                        # Write the caption and link in RST format
                        if len(caption_link_split) > 1 and len(caption_half) > 1 and len(link_half) > 1:
                            caption = (caption_half)[1:]
                            caption = caption.replace("``", "`")
                            print("caption: " + caption)
                            link = (link_half)[:-1]
                            print("link: " + link)
                            new_line += "`"
                            new_line += caption
                            new_line += " <"
                            new_line += link
                            new_line += ">`_"

                        else:
                            print("Unable to convert link: " + caption_link)

                    else:
                        # Does not contain a link
                        new_line += stripped_line
                        break;

                    if (caption_link_index + link_len) > stripped_line_len :
                        # The link ends the line
                        break;
                    else:
                        # Continue processing the rest of the line
                        stripped_line = stripped_line[caption_link_index + link_len:]

                # Done the while loop that processes the sidebar line

                sidebar_line = append_to_line(sidebar_line, new_line)

                prev_index = re.search("\S", line).start()
            else:
                # Handle table line

                para_line = write_and_reset_string(para_line, newFile)
                sidebar_line = end_sidebar(sidebar_line, newFile)
                list_item_line = write_and_reset_string(list_item_line, newFile)

                # Write the table line
                newFile.write(line)

        elif (in_code):

            # Write code line
            newFile.write(line)
        elif (para_line != ""):

            if (re.search("^[\d\-]", trimmed_line)):
                # Write the existing paragraph and start a list item
                list_item_line = line

                para_line = write_and_reset_string(para_line, newFile)

                prev_index = re.search("\S", line).start()
            elif (re.search("^[\:]", trimmed_line)):

                # Write definition term
                newFile.write(para_line)

                # Start the term definition
                para_line = line

                prev_index = re.search("\S", line).start()
            elif (re.search("^[\w\*\@\!\(\&\.\[\`\s(\w\*\@\!\(\&\.\[\`)]", trimmed_line)):

                para_line = append_to_line(para_line, trimmed_line)
            else:

                # Write the existing paragraph and the current line
                para_line = write_and_reset_string(para_line, newFile)
                newFile.write(line)

                if re.search("\S", line):
                    prev_index = re.search("\S", line).start()
        elif (re.search("^[\w\*\@\!\(\&\.\[\`\s\:(\w\*\@\!\(\&\.\[\`)]", trimmed_line)):

            sidebar_line = end_sidebar(sidebar_line, newFile)

            if re.search("\S", line):
                index = re.search("\S", line).start()

                if index > prev_index:

                    if (list_item_line != ""):
                        list_item_line = append_to_line(list_item_line, line)
                    elif (para_line != ""):
                        para_line = append_to_line(para_line, line)
                    else:
                        para_line = line
                        prev_index = index
                else:
                    para_line = write_and_reset_string(para_line, newFile)
                    sidebar_line = end_sidebar(sidebar_line, newFile)
                    list_item_line = write_and_reset_string(list_item_line, newFile)

                    para_line = line
                    prev_index = index
            else:
                # Empty line

                para_line = write_and_reset_string(para_line, newFile)
                sidebar_line = end_sidebar(sidebar_line, newFile)
                list_item_line = write_and_reset_string(list_item_line, newFile)

                # Write the current line
                newFile.write(line)
        else:
            # Set index to first non-space character
            if re.search("\S", line):
                prev_index = re.search("\S", line).start()

            para_line = write_and_reset_string(para_line, newFile)
            sidebar_line = end_sidebar(sidebar_line, newFile)
            list_item_line = write_and_reset_string(list_item_line, newFile)

            # Write the current line
            newFile.write(line)

    # Done looping through the lines

    # Finish writing the current paragraph, list item, or sidebar

    if (para_line != ""):
        newFile.write(para_line)
    elif (list_item_line != ""):
        newFile.write(list_item_line)
    elif (sidebar_line != ""):
        newFile.write(sidebar_line)

    newFile.close()