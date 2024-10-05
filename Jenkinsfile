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
