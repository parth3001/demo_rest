pipeline {
    agent any
	environment {
		currentBuildNumber = "${env.BUILD_NUMBER}"
	}


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
                sh '/usr/local/bin/docker build -t ppatel21/spring-rest-hello-world:${currentBuildNumber} . '
            }
        }
        stage('Docker image push to repo'){
        	steps{
        	withCredentials([string(credentialsId: 'dockerhub', variable: 'user'),string(credentialsId: 'dockerhub', variable: 'pass')]) {
        		sh '''
        		/usr/local/bin/docker login --username ${user} --password ${pass}
        		/usr/local/bin/docker push ppatel21/spring-rest-hello-world:${currentBuildNumber}
        		'''
        		}
        	}
     	}
     }
}
