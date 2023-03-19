# FujitsuPraktikaToo
Karl Erik Seeder 19.03.2023



## Java programming trial task!

The objective of the trial task is to assess Java programming skills, selecting
appropriate tools for completing the task, OOP skills, and application design skills,
including object model design, working with API-s, and documenting the code.

## Technologies to use
• Java 17

• Spring framework

• H2 database

## Description of the task

• Database for storing and manipulating data

• Configurable scheduled task for importing weather data (CronJob)

• Functionality to calculate delivery fee

• REST interface, which enables to request of the delivery fee according to input parameters

## Database

There has to be at least 1 table in the database for weather data where the following business
information on weather conditions is stored:

• Name of the station

• WMO code of the station

• Air temperature

• Wind speed

• Weather phenomenon

• Timestamp of the observations

## CronJob for importing weather data

Use the built-in continuous integration in GitLab.

CronJob must be implemented to the code to request weather data from the weather portal of the
Estonian Environment Agency. The frequency of the cronjob has to be configurable. The default
configuration to run the CronJob is once every hour, 15 minutes after a full hour (HH:15:00).
URL to get data:
https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php
Additional information about the interface:
https://www.ilmateenistus.ee/teenused/ilmainfo/eesti-vaatlusandmed-xml/
