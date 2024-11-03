pipeline {
    agent any

    environment {
        SONARQUBE_SERVER = 'sq' // SonarQube installation name in Jenkins
        NEXUS_REPO_URL = 'http://192.168.33.10:8081/repository/maven-releases/' // URL to Nexus repository
        NEXUS_CREDENTIALS_ID = 'nexus-credentials' // Jenkins credentials ID for Nexus
    }

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

  /*      stage('Run Tests with JaCoCo') {
            steps {
                // Run tests and generate coverage report with JaCoCo
                sh 'mvn test jacoco:report'
            }
        }


        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sq') { // 'sq' should match the SonarQube installation name in Jenkins
                    // Run SonarQube analysis, which includes JaCoCo coverage data
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    // Wait for the SonarQube Quality Gate result
                    timeout(time: 2, unit: 'MINUTES') { // Timeout after 5 minutes if no response
                        def qualityGate = waitForQualityGate()
                        if (qualityGate.status != 'OK') {
                            error "Pipeline aborted due to quality gate failure: ${qualityGate.status}"
                        }
                    }
                }
            }
        }*/

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

     /*   stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                    sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                    sh 'docker push oussemaouakad1/ouakadoussema_5sae1_g1_foyer:latest'
                }
            }
        }

        stage('Pull Docker Image') {
            steps {
                script {
                    sh 'docker pull oussemaouakad1/ouakadoussema_5sae1_g1_foyer:latest'
                }
            }
        }*/
        stage('Deploy to Nexus') {
            steps {
                withCredentials([usernamePassword(credentialsId: env.NEXUS_CREDENTIALS_ID, passwordVariable: 'nexusPassword', usernameVariable: 'nexusUsername')]) {
                    sh "mvn deploy -DaltDeploymentRepository=nexus-releases::default::${env.NEXUS_REPO_URL} -DskipTests"
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
