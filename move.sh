#! /bin/sh

for file in *
do
	if [ $file!="new" -a $file != "peersim" ]
	then
		svn mv "$file" peersim
	fi
done
