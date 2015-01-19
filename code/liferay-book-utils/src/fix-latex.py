#!/usr/bin/python
# encoding: utf-8
'''
 -- 

 is a description

It defines classes_and_methods

@author:     Rich Sezov

@copyright:  2014 Liferay, Inc. All rights reserved.

@license:    license

'''

import argparse

parser = argparse.ArgumentParser()
parser.add_argument("output", help="The file name to be written")
parser.add_argument("file", help="file to be fixed")

args = parser.parse_args()

outfile = open(args.output, "w")
infile = open(args.file, "r")
content = infile.readlines()

for i in content:
    
    if i.startswith("\caption"):
        eff = i.find("F")
        prefix = i[:eff]
        suffix = i[eff:]
        i = prefix + "\\" + "\\" + suffix
    
    if i.startswith("+sidebar"):
        i = "\\begin{roundedframe}\n\\begin{wrapfigure}{l}{0.12\\textwidth}\n\\includegraphics{../../images/01-tip.png}\n\end{wrapfigure}\n"
        
    if i.startswith("-sidebar"):
        i = "\end{roundedframe}"
        
    #if i.startswith("\href"):
    #    print (i)
    #    print("Found href; ignoring.")
    #    i = "}"
    
    if i.startswith("\index"):
        i=""

    outfile.write(i)     
        