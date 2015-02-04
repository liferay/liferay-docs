'''
Created on Jan 23, 2015

@author: rsezov
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
    
    if i.startswith("+sidebar"):
        i = '<div class="sidebar" id="somethingunique"><div class="sidebar-image"></div><div class="sidebar-text">'
        
    if i.startswith("-sidebar"):
        i = '</div></div>'
    
    outfile.write(i)
    