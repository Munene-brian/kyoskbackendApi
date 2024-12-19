# Spring Backend Application with MongoDB

Before you begin, ensure the following tools are installed on your local machine:

- **Docker**
- **Minikube**
- **kubectl**

---

## 1. Build and Run the Application Locally Using Docker

### Prerequisites
- Docker installed on your machine.
- Docker Compose installed.

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/Munene-brian/kyoskbackendApi.git
   cd kyoskbackendApi
   ```

2. Build the Spring Boot application jar:
   ```bash
   ./mvnw clean package
   ```

3. Create a Docker image for the Spring application:
   ```bash
   docker build -t <image-name>:latest .
   ```

4. Use the provided `docker-compose.yml` file to start MongoDB and the application:
   ```bash
   docker-compose up -d
   ```

5. Verify the application is running:
   Open your browser or Postman and go to:
   ```
   http://localhost:8089/api/books
   ```

6. Stop the application:
   ```bash
   docker-compose down
   ```

---

## 2. Deploy the Application on Minikube

### Prerequisites
- Minikube installed.
- Kubectl installed.
- Docker CLI integrated with Minikube.

### Steps

#### Minikube Setup

1. Start Minikube:
   ```bash
   minikube start
   ```

2. Configure Docker CLI to use Minikube's Docker daemon:
   ```bash
   eval $(minikube docker-env)
   ```

3. Build the Spring application Docker image for Minikube:
   ```bash
   docker build -t <image_name>:latest .
   ```

#### Deploy Spring Application


1. Apply Spring Application Deployment and Service:
   ```bash
   kubectl apply -f <spring_deployment_manifest>.yaml
   ```
2. Check the pods
   kubectl get pods   

#### Access the Application

1. Expose the application service:
   ```bash
   minikube service spring-app-service
   ```
   Minikube will open a browser with the service URL.

2. Verify the application:
   Append `/api/books` to the service URL to test the API.

---

## 3. CI/CD Pipeline with GitHub Actions worflow
- The GitAction Pipeline are triggered on push to main and push the image to Dockerhub registry 
### Overview
The CI/CD pipeline automates the process of building, testing, and deploying the application. The pipeline includes the following steps:
## 1. Build Job
 - Environment: Runs on ubuntu-latest.
 - Steps:
    Checkout Code: Pulls the latest code from the repository using actions/checkout@v4.
    Set Up JDK 17: Configures Java Development Kit version 17 using actions/setup-java@v4.
    Maven Build: Executes mvn -B -DskipTests clean package to compile and package the application while skipping tests.

## 2. Docker Job (Depends on Build Job)
  - Environment: Runs on ubuntu-latest.
  - Steps:
     Checkout Code: Re-checks out the repository for Docker-related operations.
     Set Up Docker Buildx: Configures advanced Docker build features using docker/setup-buildx-action@v3.
     Login to Docker Hub: Authenticates to Docker Hub using a username and password from GitHub Secrets/Variables.
     Build and Push Docker Image:
     Builds a Docker image tagged as kyosk-spring-backend:latest.
     Pushes the image to Docker Hub under the specified Docker Hub account.
     This step runs only if the branch is main

---

### Notes

- Replace placeholders like `<repository-url>` and `<repository-directory>` with actual values.
- Ensure secrets for Docker Hub credentials are added to your GitHub repository settings.
- MongoDB will run as a container locally and is defined in docker-compose file with document ("books") as diagram
- You can use mongoDB compass for GUI
- Can seperate and docker-compose file into two: (backendapi) and (mongoDB) dockerfiles




