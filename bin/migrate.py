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
        sidebar_line = sidebar_line.replace("| ", "")
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
        print("Usage:\n\tpython migrate.py article [dest_folder]\n\nDescription:\n\tMigrates the article to an .md file and converts the content to liferay-learn Markdown/MyST syntax. The converted article destination folder [dest_folder] is the current folder by default but is typically set to a liferay-learn folder.\n\n\tIMPORTANT: Run this script in the liferay-docs article's folder so that the script can use the image file paths found in the article to copy the image files to the [dest_folder]/[article_name]/images/ folder.")
        sys.exit()

    article = sys.argv[1]

    dest_folder = "."
    if (len(sys.argv)) > 2:
        dest_folder = sys.argv[2]

    if not (os.path.isdir(dest_folder)):
        os.mkdir(dest_folder)

    article_name = article.split('.markdown')[0]

    new_article_path = dest_folder + "/" + article_name + ".md"

    # Copy referenced image files to [article destination folder]/[article_name]/images

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

    article_name_folder = dest_folder + "/" + article_name
    images_folder = article_name_folder + "/images"

    if (len(images) > 0):

        if not os.path.isdir(article_name_folder):
            os.mkdir(article_name_folder)

        if not os.path.isdir(images_folder):
            os.mkdir(images_folder)

        print("Writing images to folder: " + images_folder)

    for ff in images:
        shutil.copy(ff, images_folder)

    # Process the text

    file = open(article)
    lines = file.readlines()
    file.close()

    # Open the destination file for writing to
    newFile = open(new_article_path, 'w')

    print("Migrating to article: " + new_article_path)

    # Track the last significant line's first non-space character column index
    prev_index = -1

    # Variables for storing text that belongs on the same line; hard returns are
    # removed
    list_item_line = ""
    para_line = ""
    sidebar_line = ""

    # Variables that allow you to skip more detailed condition checks
    done_header_id = False
    done_title = False
    done_toc = False
    prev_sidebar_line_empty = False

    # Variables for markup context
    in_code = False
    in_header_id = False

    images_folder_path = "](./" + article_name + "/images/"

    for line in lines:

        # Set all image file locations to the ./[article_name]/images/ folder
        line = re.sub("\]\((../)+images/", images_folder_path, line)

        # Replace legacy tokens
        line = re.sub("@product@", "Liferay DXP", line)
        line = re.sub("@commerce@", "Liferay Commerce", line)
        line = re.sub("@ide@", "Dev Studio DXP", line)
        line = re.sub("@app-ref@", "https://docs.liferay.com/dxp/apps", line)
        line = re.sub("@platform-ref@", "https://docs.liferay.com/dxp/portal", line)

        # Convenience variable for working with the current line free of leading
        # and trailing white space.
        trimmed_line = line.lstrip()

        if not done_header_id:
            # Skip over all of the legacy header-id stuff

            if trimmed_line.startswith("---"):
                if not in_header_id:
                    in_header_id = True
                else:
                    done_header_id = True
                    in_header_id = False

            continue
        if not done_title and trimmed_line in "":
            # Skip empty lines until the title is written
            continue
        elif line.startswith("#"):
            # Handle heading
            newFile.write(line)

            if not done_title:
                done_title = True
        elif not done_toc and trimmed_line in "":
            # Skip empty lines until TOC is done
            continue
        elif trimmed_line.startswith("```"):
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

                pipe_index = re.search("|", line).start()

                tmp_line = line.replace("| ", "", 1).strip()
                if tmp_line in "":
                    prev_sidebar_line_empty = True
                    sidebar_line += "\n"
                else:

                    # Replace the leading pipe with a space
                    stripped_line = line.replace("| ", "", 1)

                    if sidebar_line in "":

                        # Start the sidebar

                        if "**Note:**" in stripped_line:
                            newFile.write("```{note}\n")
                            stripped_line = stripped_line.replace("**Note:**", "", 1)
                        elif "**Tip:**" in stripped_line:
                            newFile.write("```{tip}\n")
                            stripped_line = stripped_line.replace("**Tip:**", "", 1)
                        elif "**Warning:**" in stripped_line:
                            newFile.write("```{warning}\n")
                            stripped_line = stripped_line.replace("**Warning:**", "", 1)
                        elif "**Important:**" in stripped_line:
                            newFile.write("```{important}\n")
                            stripped_line = stripped_line.replace("**Important:**", "", 1)
                        elif "**Note**:" in stripped_line:
                            newFile.write("```{note}\n")
                            stripped_line = stripped_line.replace("**Note**:", "", 1)
                        elif "**Tip**:" in stripped_line:
                            newFile.write("```{tip}\n")
                            stripped_line = stripped_line.replace("**Tip**:", "", 1)
                        elif "**Warning**:" in stripped_line:
                            newFile.write("```{warning}\n")
                            stripped_line = stripped_line.replace("**Warning**:", "", 1)
                        elif "**Important**:" in stripped_line:
                            newFile.write("```{important}\n")
                            stripped_line = stripped_line.replace("**Important**:", "", 1)
                        else:
                            newFile.write("```{note}\n")

                    if sidebar_line in "":
                        sidebar_line = stripped_line.lstrip()
                    else:
                        if prev_sidebar_line_empty:
                            sidebar_line = sidebar_line + stripped_line
                            prev_sidebar_line_empty = False
                        else:
                            sidebar_line = sidebar_line.rstrip() + " " + stripped_line.lstrip()

                    prev_index = re.search("\S", line).start()
            else:
                # Handle table line

                para_line = write_and_reset_string(para_line, newFile)
                sidebar_line = end_sidebar(sidebar_line, newFile)
                list_item_line = write_and_reset_string(list_item_line, newFile)

                # Write the table line
                newFile.write(line)
        elif in_code:

            # Write code line
            newFile.write(line)
        elif trimmed_line.startswith("[TOC"):
            # Don't write anything for the legacy TOC line
            done_toc = True
            continue
        elif para_line != "":

            if re.search("^[\d\-]", trimmed_line):
                # Write the existing paragraph and start a list item
                list_item_line = line

                para_line = write_and_reset_string(para_line, newFile)

                prev_index = re.search("\S", line).start()
            elif re.search("^[\:]", trimmed_line):

                # Write definition term
                newFile.write(para_line)

                # Start the term definition
                para_line = line

                prev_index = re.search("\S", line).start()
            elif re.search("^[\w\*\@\!\(\&\.\[\`\s(\w\*\@\!\(\&\.\[\`)]", trimmed_line):

                para_line = append_to_line(para_line, trimmed_line)
            else:

                # Write the existing paragraph and the current line
                para_line = write_and_reset_string(para_line, newFile)
                newFile.write(line)

                if re.search("\S", line):
                    prev_index = re.search("\S", line).start()
        elif re.search("^[\w\*\@\!\(\&\.\[\`\s\:(\w\*\@\!\(\&\.\[\`)]", trimmed_line):

            sidebar_line = end_sidebar(sidebar_line, newFile)

            if re.search("\S", line):
                index = re.search("\S", line).start()

                if index > prev_index:

                    if list_item_line != "":
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

            if not done_header_id and trimmed_line in "":
                # Skip writing empty lines before writing the title line
                continue
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

    if para_line != "":
        newFile.write(para_line)
    elif list_item_line != "":
        newFile.write(list_item_line)
    elif sidebar_line != "":
        newFile.write(sidebar_line)

    newFile.close()