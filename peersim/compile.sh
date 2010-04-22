#! /bin/sh

make all
cd src/
jar cf peersim-1.0.5.jar example peersim
mv peersim-1.0.5.jar ../
cd ../
make clean
./cli
if [ -s "./output" ] 
then
	less output
fi
