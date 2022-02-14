#!/bin/bash

# This script was inspired by the answers at
# https://stackoverflow.com/questions/30724170/how-to-read-properties-file-using-shell-script

redirects_file=~/redirects_all.properties

if [ "${#}" -eq 0 ]
then
    echo "Must input a properties file"
    echo "Usage: ./bin/update-liferay-learn_links.sh path/to/properties/file"
    exit 1
fi

if [ "${#}" -ge 1 ]
then
    if [ ! -f ${1} ]
    then
        echo "File ${1} does not exist."

        exit 1
    else
        redirects_file=${1}
    fi
fi

echo "Redirects file ${redirects_file}"

# Read configuration into an associative array
declare -A REDIRECT

# IFS is the 'internal field separator'. In this case, your file uses '='
IFS="="
while read -r key value
do
    if [ -n $value ]; then
        REDIRECT[$key]=$value
    else
        REDIRECT[$key]=$value
    fi
done < ${redirects_file}
unset IFS

for file in `find . -type f -name "*.markdown"`
do
    if [ -n "$(grep learn.liferay.com $(echo ${file}))" ]
    then
        echo "${file}"

        for key in "${!REDIRECT[@]}"
        do
            sed -i s@$key@${REDIRECT[$key]}@g "${file}"
        done
    fi
done