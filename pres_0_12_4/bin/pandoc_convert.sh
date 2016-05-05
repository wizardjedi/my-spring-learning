#!/bin/bash

LANG=$1
SYSTEM=$2

/usr/bin/pandoc -f markdown_github+pipe_tables+yaml_metadata_block -s -t latex -o /source/target/doc_$SYSTEM.$LANG.pdf /source/generated/input.md
/usr/bin/pandoc -f markdown_github+pipe_tables+yaml_metadata_block -s -t html -o /source/target/doc_$SYSTEM.$LANG.html /source/generated/input.md

chmod -R 777 /source/target/*
