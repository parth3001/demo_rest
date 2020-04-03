pipeline {
    agent any
	def currentBuildNumber = ${env.BUILD_NUMBER}

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
            def buildVersion = ${env.BUILD_NUMBER}
            steps {
                echo 'Docker build....'
                sh 'docker build -t ppatel21/spring-rest-hello-world:${buildVersion} . '
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
