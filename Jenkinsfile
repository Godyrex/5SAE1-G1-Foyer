pipeline {
    agent any  

    stages {
        stage('Checkout') {
            steps {
                git branch: 'NourChallouf_5SAE1_G1', url: 'git@github.com:Godyrex/5SAE1-G1-Foyer.git', credentialsId: 'ca5934e0-4211-44f9-8128-0f3dfa24a447'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the project...'
    
            }
        }

        stage('Test') {
            steps {
              
                echo 'Running tests...'
            
            }
        }

        stage('Deploy') {
            steps {
              
                echo 'Deploying the application...'
        
            }
        }

    
        stage('Cleanup') {
            steps {
                echo 'Cleaning up...'
             
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed.'
        }
        always {
            echo 'This will always run after the pipeline finishes.'
        }
    }
}

