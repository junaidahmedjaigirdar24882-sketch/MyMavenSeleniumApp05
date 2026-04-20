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

        stage('Build & Test') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Run Application') {
            steps {
                sh 'xvfb-run -a java -jar target/MyMavenSeleniumApp05-1.0-SNAPSHOT.jar'
            }
        }
    }
}
