#!/bin/bash

if [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing coverage..."

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/$TRAVIS_REPO_SLUG gh-pages > /dev/null

  cd gh-pages
  git rm -rf *
  cp -Rf $TRAVIS_BUILD_DIR/target/site/jacoco/{.,}* .
  touch .nojekyll
  git add -f .
  git commit -m "Lastest cobertura report on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin gh-pages > /dev/null

fi
