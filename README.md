oddjob
======

Local json cache for mobile app testing

This will take a user provided list of MAPI endpoints, and download them locally

**Running**

- Clone the repo
- Create a file called download.txt in the root
- Populate the download.txt file with a list of all required MAPI endpoints. Each endpoint should on a new line, with no separators, eg:

http://mobile-apps.guardianapis.com/.....   
http://mobile-apps.guardianapis.com/.....

- In a terminal, navigate to root of the project
- $ sbt run
- All .json files will be saved with the correct URI path in the cache folder in the root of the repo