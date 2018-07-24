# Canoo Language Tools Playground

This is a sandbox project to test the functionality offered by [Canoo Language Tools](http://languagetools.canoo.com).

# Installation

* Register and request an evaluation license
* Download the developer package and unzip it
* Put the downloaded `inflangen.jar` into the `libs` directory
* Run `mvn install:install-file -Dfile=inflangen.jar -DgroupId=com.canoo.wmtrans -DartifactId=inflangen -Dversion=2.2.506 -Dpackaging=jar` to install `inflangen.jar` as an artifact into the local Maven repository
* Put `Ger1InflAnGen.tab`, `Ger1InflAnGen.tra` and `license.cfg` into the `core/src/test/resources` folder