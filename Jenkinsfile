pipeline {
    agent any
    environment {
        // Define SonarQube Token as an environment variable
        SONAR_TOKEN = 'sqa_e7a92efe3eee04ba242da4fb2dddc04677a2c11f'
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'WissalBahri_5SAE1_G1',
                    credentialsId: 'gitwiwiwi',
                    url: 'git@github.com:Godyrex/5SAE1-G1-Foyer.git'
            }
        }
        stage('Build and Run Tests') {
            steps {
                script {
                    // Clean, compile, and run tests
                    sh 'mvn clean test'
                }
            }
        }
        stage('Generate JaCoCo Coverage Report') {
            steps {
                script {
                    // Generate JaCoCo coverage report
                    sh 'mvn jacoco:report'
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    // Run SonarQube analysis with coverage data
                    sh '''
                    mvn sonar:sonar \
                    -Dsonar.projectKey=Foyer \
                    -Dsonar.host.url=http://192.168.25.25:9000 \
                    -Dsonar.login=${SONAR_TOKEN} \
                    -Dsonar.coverage.jacoco.xmlReportPaths=target/jacoco/jacoco.xml
                    '''
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                echo 'Deploying to Nexus...'
                sh 'mvn deploy -X -DskipTests=true -DaltDeploymentRepository=deploymentRepo::default::http://192.168.25.25:8081/repository/maven-releases/'         
                }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    echo 'Building Docker Image...'
                    // Build Docker image with the provided tag
                    def appImage = docker.build("wbahri/back-devops:1.0")
                }
            }
        }

        stage('Deploy Docker Image') {
            steps {
                script {
                    echo 'Deploying Docker Image...'
                    // Authenticate with Docker Hub and push the image
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-token') {
                    def appImage = docker.image('wbahri/back-devops:1.0')
                    appImage.push()
                }


                }
            }
        }

        stage('Cleanup Old Containers') {
            steps {
                script {
                    echo 'Removing old containers...'
                    // Stop and remove old containers based on image name
                    sh "docker ps -a | grep 'wbahri' | awk '{print \$1}' | xargs --no-run-if-empty docker stop | xargs --no-run-if-empty docker rm"
                }
            }
        }

        stage('Docker Compose Up') {
            steps {
                script {
                    echo 'Starting services with Docker Compose...'
                    // Pull the latest image and start services with Docker Compose
                    def appImage = docker.image('wbahri/back-devops:1.0')
                    appImage.pull()
                    sh 'docker compose down'
                    sh 'docker compose up -d'
                }
            }
        }
        
        
        
    }
    post {
        always {
            // Archive the test results and coverage report for Jenkins
            junit '**/target/surefire-reports/*.xml'
            jacoco execPattern: '**/target/jacoco.exec'
        }
        success {
            echo 'Build and SonarQube analysis completed successfully!'
        }
        failure {
            echo 'Build or SonarQube analysis failed.'
        }
    }
}
