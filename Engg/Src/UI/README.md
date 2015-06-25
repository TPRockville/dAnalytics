# dAnalytics

## Prerequisites

1. Install [Node.js](http://nodejs.org)
 - on OSX use [homebrew](http://brew.sh) `brew install node`
 - on Windows use [chocolatey](https://chocolatey.org/) `choco install nodejs` or download installation file from [Node.js](http://nodejs.org)
2. Install Grunt and Bower


> Once Node is installed, do:
 
	npm install -g grunt-cli bower
	npm install
	bower install

Grunt Tasks
-------------

Tasks available for development and packaging also testing:

    grunt serve   #Runs a development server on node and runs livereload.
    grunt test    #Runn unit tests.
    grunt build   #Packages app (minified, concatenated, and more) in /dist

Deployment
-------------

- Configure app environment variables in `` config\environments `` folder
- Run the `` grunt build:<environment> `` to build the deployment package
- This will create packge with cancatinated and minified files in `` dist `` folder
- Copy all the files inside dist folder to web server deployment folder

> Available environments to build


    development (default)
    staging
    production