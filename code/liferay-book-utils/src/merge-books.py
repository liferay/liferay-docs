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
            
            whiteLength = len(j) - len(j.lstrip())
            whiteSpace=""
            if (whiteLength > 0) and (j != "\n"):                
                for k in range(whiteLength):
                    whiteSpace = whiteSpace + " "
                    
                j = j.lstrip()
                print (str(whiteLength) + " white space characters found; " + str(len(whiteSpace)) + " characters added.")

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
                    
                #imageLocation = suffix.index("(../../")
                #imageDir = suffix.index("/images/")
                #newLocation = "(../../../deployment"
                #beginSuffix = suffix[:imageLocation]
                #endSuffix = suffix[imageDir:]
                #suffix = beginSuffix + newLocation + endSuffix

                j = prefix + " " + str(chapter) + "." + figNum + suffix
                
            j = whiteSpace + j

            outfile.write(j)

    infile.close()

outfile.close()

