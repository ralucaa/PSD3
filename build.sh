#!/bin/sh
#compile the Java files without Eclipse

OPTS="-sourcepath src -d bin"
javac $OPTS src/DatabaseInteraction/*.java
javac $OPTS src/Menus/*.java
javac $OPTS src/Objects/*.java
