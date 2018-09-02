#!/usr/bin/env bash

curl -f localhost:8080/cms/ws/indexexport -u admin:admin -o export.zip
mkdir -p storage/workspaces/default/index/
rm -rf storage/workspaces/default/index/*
tar -xzvf export.zip -C storage/workspaces/default/index/