PSD3
====

Undergraduate Teaching Session Management System

Build and Run
====

Command-line:
Change to the directory containing this file. Run ./build.sh to build. Run ./MainMenu.sh to run.

Eclipse:
Import this directory and run the MainMenu class. Re-add the provided MySQL driver jar if it did not import correctly.

Dependencies
====

Requires a MySQL driver. One is included in the repository.

It currently connects to our external database server, which contains some sample data.

Barcode Import
====

The file examples/import_barcodes_alglab1.txt is suitable for importing into the "First Algorithmics Lab".

Project
=================
- Programming language: Java
- Testing framework: Mockito - http://code.google.com/p/mockito/ (didn't use it in the end)
- Task assignment: github issue tracking system -> label: Sprint1Task

Details
=======
- We were going to use CSV files to mock the database in our prototypes, but on 14/11/2013 (4th day of spike prototype) decided it would be less work to change to a real MySQL database.

