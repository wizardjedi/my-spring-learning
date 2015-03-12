#!/bin/bash

LANG=$1
SYSTEM=$2

rm -rf generated

mkdir generated

gpp -x -D_LANG_=$LANG -D_SYSTEM_=$SYSTEM sources/main.md > generated/input.md

rm -rf target

mkdir target

docker run --rm -it -v `pwd`:/source wizardjedi/pandoc-latex /bin/bash /source/bin/pandoc_convert.sh $LANG $SYSTEM
