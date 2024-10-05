pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'OuakadOussema_5SAE1_G1', url: 'git@github.com:Godyrex/5SAE1-G1-Foyer.git', credentialsId: 'oussema'
            }
        }

        stage('Clean Project') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Build Without Tests') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image with the JAR file
                    sh 'docker build -t oussemaouakad1/ouakadoussema_5sae1_g1_foyer:latest .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Log in to Docker Hub
                    withCredentials([string(credentialsId: 'dockerhub-credentials', variable: 'DOCKER_HUB_PASSWORD')]) {
                        sh 'echo $DOCKER_HUB_PASSWORD | docker login -u oussemaouakad1 --password-stdin'
                    }

                    sh 'docker push oussemaouakad1/ouakadoussema_5sae1_g1_foyer:latest'
                }
            }
        }

        stage('Archive Deliverable') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: false
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo 'Build completed successfully.'
        }
        failure {
            echo 'Build failed.'
        }
    }
}
