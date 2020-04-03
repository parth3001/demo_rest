pipeline {
    agent any
	tools {
            maven 'Maven 3.6.3'
            jdk 'jdk8'

     }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn package'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Docker Services Image Build') {
            steps {
                echo 'Docker build....'
                sh 'chmod 755 Dockerbuild.sh'
                sh '/usr/local/bin/docker build .'
            }
        }

    }
}
