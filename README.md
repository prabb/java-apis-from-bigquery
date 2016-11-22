# Create JAVA APIs from Google BigQuery DataSet

The repository contains starter code to create a JAVA API from BigQuery SQL queries.

---
## Pre-requisites
* [Create Google Cloud Platform Project](https://cloud.google.com/) if you haven't already
* Navigate to [BigQuery Web UI](https://bigquery.cloud.google.com) and run some test queries
* Download and install [Google Cloud SDK](https://cloud.google.com/sdk/)
* `gcloud init`: **Initialize** the SDK with a default User Account and Project ID 

----
## Setup
* Download the project `git clone --depth 1 https://github.com/prabb/java-apis-from-bigquery.git`
* Open the Project with the editor of your choice (IDE: [Intellij Idea](https://www.jetbrains.com/idea/))
* Edit `src/main/java/com/prabhsingh/controller/HomeController` file and rewrite the SQL query

---
## Run Project

### With Maven
* Download and Setup [Maven](http://maven.apache.org/install.html). Additionally, add maven to System PATH to access globally
* Open Terminal and cd to Project directory:
    - `mvn package` (Creates a WAR file for deployment)
    - `mvn jetty:run-war`
* Open **Web browser** and navigate to `http://localhost:8080`


### With Docker (Incomplete: Don't follow this step)
* Configure and setup [Docker](https://www.docker.com/products/overview) on your Machine.
* Open Terminal and cd to Project directory:
    - `docker-machine ls` : There should be atleast one default machine running
    - `docker build -t "user/project" .` : Build a base container image
    - `docker run -d -p 8080:8080 'user/project'`: Run the Container in background and route traffic to port 8080
    - `docker-machine ip default`: Get the IP Address of the default docker machine
* Open **Web browser** and navigate to `http://localhost:8080`

---
## Deployment
* [Intellij Idea](https://www.jetbrains.com/idea/) also offers the option to deploy the project to Google Cloud Platform with one-click [Link](https://cloud.google.com/tools/intellij/docs/)