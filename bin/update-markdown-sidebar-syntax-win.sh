#!/bin/sh

developdir="../develop"
distributedir="../distribute"
usedir="../use"

for file in $(find $developdir -name "*.markdown")
do
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)+[ ]*!\[.*\]\(.*\)[\s]*/\r\n\+\$\$\$\r\n\r\n/g' $file
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)/\r\n\$\$\$\r\n/g' $file
done

for file in $(find $distributedir -name "*.markdown")
do
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)+[ ]*!\[.*\]\(.*\)[\s]*/\r\n\+\$\$\$\r\n\r\n/g' $file
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)/\r\n\$\$\$\r\n/g' $file
done

for file in $(find $usedir -name "*.markdown")
do
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)+[ ]*!\[.*\]\(.*\)[\s]*/\r\n\+\$\$\$\r\n\r\n/g' $file
perl -p0777i.bak -e 's/\r\n[ \t]*---[ ]*(\r\n)/\r\n\$\$\$\r\n/g' $file
done
