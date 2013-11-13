#!/bin/sh
#compile the Java files without Eclipse

OPTS="-sourcepath src -d bin"
javac $OPTS src/DatabaseInteraction/*.java src/Menus/*.java src/Objects/*.java
