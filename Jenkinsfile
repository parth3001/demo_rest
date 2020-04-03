pipeline {
    agent any
	currentBuildNumber = ${env.BUILD_NUMBER}

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
                sh 'docker build -t ppatel21/spring-rest-hello-world:${BUILD_NUMBER} . '
            }
        }
        stage('Docker image push to repo'){
        	steps{
        		echo 'Docker Image push...'
        		sh ''
        	}
        }

    }
}
