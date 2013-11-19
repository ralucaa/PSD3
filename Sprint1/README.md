PSD3 Sprint 1
====

Build and Run
----

Command-line: Change to the directory containing this file. Run ./build.sh to build. Run ./MainMenu.sh to run.

Eclipse: Import this directory and run the MainMenu class. Re-add the provided MySQL driver jar if it did not import correctly.


Dependencies
----

Requires a MySQL driver. One is included in the repository.

It currently connects to our external database server, which contains some sample data.


User Guide
----

Login info: Enter "admin" for both username and password to log in as an admin; "tutor" to log in as a tutor.

To select a menu item, enter the number or letter shown on the left. For example, submenus end in "b. back", and you can enter "b" to return to the previous menu.

The file examples/import_barcodes_alglab1.txt is suitable for importing into the "First Algorithmics Lab".

An empty string for a student's attendance means no attendance has been marked.


Details
----
- Programming language: Java
- Task assignment: github issue tracking system -> label: Sprint1Task
- We were going to use CSV files to mock the database in our prototypes, but on 14/11/2013 (4th day of spike prototype) decided it would be less work to change to a real MySQL database.


