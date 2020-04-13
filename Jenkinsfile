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
        stage('Code compile and package build') {
            steps {
                echo 'Building..'
                sh 'mvn -Dmaven.test.skip=true package'
            }
        }
        stage('SonarQube Analysis') {
        	steps{
                  withSonarQubeEnv(installationName: 'Sonar_Qube') { // You can override the credential to be used
                       sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
                     }
                 }
         }
        stage('Unit testing') {
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Docker image build') {
            steps {
                echo 'Docker build....'
                sh '/usr/local/bin/docker build -t ppatel21/spring-rest-hello-world:${currentBuildNumber} . '
            }
        }
        stage('Docker image push to repo'){
        	steps{
        	withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        		sh 'echo $USER'
        		sh  '/usr/local/bin/docker login --username=$USER --password=$PASS'
        		sh '/usr/local/bin/docker push ppatel21/spring-rest-hello-world:${currentBuildNumber}'
        		}
        	}
     	}
     }
}
