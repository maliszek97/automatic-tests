#!/bin/bash
urls=${@:1:$#-1}
testsType=${@: -1}

for url in $urls
do
  mvn test -Durl=$url -DtestsType=$testsType
  mvn allure:report -Durl=$url
  ls
done