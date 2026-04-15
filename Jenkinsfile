pipeline {
    agent any

    tools {
        maven 'Maven'
    }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/junaidahmedjaigirdar24882-sketch/MyMavenSeleniumApp05.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Run Automation') {
            steps {
                sh 'java -jar target/MyMavenSeleniumApp05-1.0-SNAPSHOT.jar'
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }
        failure {
            echo 'Build Failed'
        }
    }
}
