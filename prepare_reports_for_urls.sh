#!/bin/bash
urls=${*:2}
pipeline_id=$1

mkdir -p public/$pipeline_id/
for url in $urls
do
  echo $url
  mkdir -p public/$pipeline_id/$url/
  cp target/site/allure-maven-plugin/$url/* public/$pipeline_id/$url/ -R
  echo public/$pipeline_id/$url/
done