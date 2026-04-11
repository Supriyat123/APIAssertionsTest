pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Supriyat123/APIAssertionsTest.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run API Tests') {
            steps {
                sh 'mvn test'
            }
        }
    }

    post {
        always {
            cucumber buildStatus: 'UNSTABLE',
                fileIncludePattern: '**/cucumber.json',
                jsonReportDirectory: 'target'
        }
    }
}