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
   git clone <https://github.com/Munene-brian/kyoskbackendApi.git>
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

## 3. CI/CD Pipeline with GitHub Actions
- The GitAction Pipeline are triggered on push to main and push the image to Dockerhub registry 
### Overview
The CI/CD pipeline automates the process of building, testing, and deploying the application. The pipeline includes the following steps:

1. **Build and Test**:
   - Runs on every pull request and push to the repository.
   - Executes Maven commands to compile the code and run tests.

2. **Docker Build**:
   - Builds the Docker image for the Spring application.

3. **Push Docker Image**:
   - Optionally pushes the image to a container registry (e.g., Docker Hub or GitHub Container Registry).

4. **Deploy to Minikube**:
   - Deploys the application to Minikube using `kubectl` commands.

### Example GitHub Actions Workflow

```yaml
name: CI/CD Pipeline

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout Code
      uses: actions/checkout@v3

    - name: Set up JDK
      uses: actions/setup-java@v3
      with:
        java-version: '17'

    - name: Build with Maven
      run: mvn clean package

    - name: Build Docker Image
      run: docker build -t <image_name>:latest .

    - name: Push Docker Image
      env:
        DOCKER_USERNAME: ${{ secrets.DOCKER_USERNAME }}
        DOCKER_PASSWORD: ${{ secrets.DOCKER_PASSWORD }}
      run: |
        echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin
        docker tag spring-backend:latest $DOCKER_USERNAME/<image_name>:latest
        docker push $DOCKER_USERNAME/<image_name>:latest



---

### Notes

- Replace placeholders like `<repository-url>` and `<repository-directory>` with actual values.
- Ensure secrets for Docker Hub credentials are added to your GitHub repository settings.
- MongoDB will run as a container locally and is defined in docker-compose file with document ("books") as diagram
- You can use mongoDB compass for GUI
- Can seperate and docker-compose file into two: (backendapi) and (mongoDB) dockerfiles



![Screenshot 2024-12-19 060722](https://github.com/user-attachments/assets/c9bcd285-a402-4d30-b7af-216d98727b0d)

