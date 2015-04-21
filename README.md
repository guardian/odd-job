oddjob
======

Local json cache for mobile app testing

This will take a user provided list of MAPI endpoints, and download them locally

**Running**

1. Clone the repo
2. Create a file called download.txt in the root
3. Populate the download.txt file with a list of all required MAPI endpoints. Each endpoint should on a new line, with no separators, eg:

    http://mobile-apps.guardianapis.com/.....
    http://mobile-apps.guardianapis.com/.....

4. In a terminal, navigate to root of the project
5. $ sbt run
6. All .json files will be saved with the correct URI path in the cache folder in the root of the repo