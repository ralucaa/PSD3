PSD3 Sprint 2
====
Technological spike prototype of the Spring MVC web framework,
with session setup and timetable viewing.

Build and Run
----

In Spring Tool Suite 3.4:

Import the project with "File -> Import... -> General -> Existing Projects into Workspace -> Browse..." and select the Sprint_2_Spring folder.

Run with "Run -> Run As -> Run on Server" and set it up with Apache Tomcat 7.

Once the server has started, the main menu is the web page at http://localhost:8080/gla/ .

User Guide
----

Note: no login has been implemented.

On the main menu, click a link to select whether to add a new session, or view the sessions for one of the example students.

Adding a new teaching session:
----
You need to input the following fields:
- Course name.
- The session name (e.g. Laboratory, Morning Lecture, etc).
- The date of the first session (e.g. 30 September 2013).
- How often it repeats (e.g. one-off, every 1 week, etc)
- The date when the teaching of this session finishes (a session does not need to take place on this date).
- The start time of the session (e.g. 09:00)
- The duration (e.g. 2 hours).
- The member of staff who will be hosting the session (e.g. Jeremy Singer)
- The maximum number of students that can attend this session.
- Whether the session is compulsory or not.
- The venue where the session takes place.

Viewing sessions for a particular student
----
This displays the sessions the student is registered for, split into 3 categories:
- Today - all the sessions that need to be attended in that day. It is likely that this will be empty if viewed in the weekend.
- This week - all the sessions that need to be attended in the current week.
- All time - all the sessions that need to be attended, on all the dates they take place. These are ordered by the session (maybe ordering by date would be better).

* Known issue: Today and This week categories extend vertically way too much but for a prototype this should be fine. If you want to see the other categories, click the currently expanded category header (default Today) and click another category.

Testing
----
Testing the adding functionality can be done using the instructions above. If the addition is successful, the session is stored in the database and a confirmation message will be displayed. If not, the session doesn't get stored and an error message is output. Note that the confirmation page will redirect to the menu after 5 seconds.
Testing the view functionality only implies visually scanning the displayed sessions.
