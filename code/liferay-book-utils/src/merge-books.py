import argparse

parser = argparse.ArgumentParser()
parser.add_argument("output", help="The file name to be written")
parser.add_argument("files", nargs="+", help="files to be merged")

args = parser.parse_args()

outfile = open(args.output, "w")
chapterCount = 0

for i in args.files:
    infile = open(i, "r")
    content = infile.readlines()

    if chapterCount == 0:
       # We're in the first file and we only need to count the chapters 
        for j in content:
            
            if j.startswith("![Figure"):
                # find the chapter number
                colon = j.find(":")
                e = j.find("e")
                dot = j.find(".")
                prefix = j[e+1:dot]
                suffix = j[dot+1:colon]
                
                chapter = prefix.strip(" ")
                print ("Chapter: " + chapter)
                chapterCount = int(chapter)

        outfile.writelines(content)
    
    else:
        # We're in another file and we need to increment the chapters
        for j in content:

            if j.startswith("![Figure"):
                colon = j.find(":")
                e = j.find("e")
                dot = j.find(".")
                chNum = j[e+1:dot]
                figNum = j[dot+1:colon]
                chapter = int(chNum.strip(" "))
                prefix = j[:e+1]
                suffix = j[colon:]

                if chapter < chapterCount:
                    chapter = chapter + chapterCount

                j = prefix + " " + str(chapter) + "." + figNum + suffix

            outfile.write(j)

    infile.close()

outfile.close()

