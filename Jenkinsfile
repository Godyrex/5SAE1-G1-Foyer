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
      agent any
      steps {
        withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
          sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
          sh 'docker push oussemaouakad1/ouakadoussema_5sae1_g1_foyer:latest'
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