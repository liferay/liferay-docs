#!/bin/sh

developdir="../develop"
distributedir="../distribute"
usedir="../use"

for file in $(find $developdir -name "*.markdown")
do
perl -p0777i -e 's/\n[ \t]*---[ ]*\n+[ ]*!\[.*\]\(.*\)[\s]*/\n\+\$\$\$\n\n/g' $file
perl -p0777i -e 's/\n[ \t]*---[ ]*\n/\n\$\$\$\n/g' $file
done

for file in $(find $distributedir -name "*.markdown")
do
perl -p0777i -e 's/\n[ \t]*---[ ]*\n+[ ]*!\[.*\]\(.*\)[\s]*/\n\+\$\$\$\n\n/g' $file
perl -p0777i -e 's/\n[ \t]*---[ ]*\n/\n\$\$\$\n/g' $file
done

for file in $(find $usedir -name "*.markdown")
do
perl -p0777i -e 's/\n[ \t]*---[ ]*\n+[ ]*!\[.*\]\(.*\)[\s]*/\n\+\$\$\$\n\n/g' $file
perl -p0777i -e 's/\n[ \t]*---[ ]*\n/\n\$\$\$\n/g' $file
done
