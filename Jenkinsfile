pipeline {
    agent any


    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'NourChallouf_5SAE1_G1', url: 'git@github.com:Godyrex/5SAE1-G1-Foyer.git', credentialsId: 'ca5934e0-4211-44f9-8128-0f3dfa24a447'
            }
        }

        stage('Clean Project') {
            steps {
                sh 'mvn clean'
            }
        }

        stage('Run Tests with JaCoCo') {
            steps {
                sh 'mvn test jacoco:report'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('nour') {
                    sh 'mvn sonar:sonar'
                }
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
                    // Build the Docker image with a specific tag
                    sh 'docker build -t parkchanyeolnour/foyer-app:latest .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    script {
                        // Login to Docker Hub
                        sh "echo ${dockerHubPassword} | docker login -u ${dockerHubUser} --password-stdin"
                        // Push the Docker image
                        sh 'docker push parkchanyeolnour/foyer-app:latest'
                    }
                }
            }
        }

        stage('Pull Docker Image') {
            steps {
                script {
                    // Pull the Docker image
                    sh 'docker pull parkchanyeolnour/foyer-app:latest'
                }
            }
        }

        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId:'nexus-credentials', passwordVariable: 'nexusPassword', usernameVariable: 'nexusUsername')]) {
                    sh "mvn deploy -DaltDeploymentRepository=nexus-snapshots::default::http://192.168.33.10:8081/repository/maven-snapshots/ -DskipTests"
                }
            }
        }

        stage('Archive Deliverable') {
            steps {
                // Archive the build artifact
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
