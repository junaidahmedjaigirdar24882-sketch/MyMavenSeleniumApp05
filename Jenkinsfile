Firefox 

pipeline {
    agent any  // Use any available agent

    tools {
        maven 'Maven'  // Ensure this matches the name configured in Jenkins
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/junaidahmedjaigirdar24882-sketch/MyMavenSeleniumApp05.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile -DskipTests'  // Run Maven build
            }
        }

        stage('Test') {
            steps {
                sh 'mvn exec:java -Dexec.mainClass="com.example.App"'  // Run unit tests
            }
        }
