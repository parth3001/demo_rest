pipeline {
    agent any
	parameters {
            booleanParam (
                defaultValue: false,
                description: 'Select only when you want to do full build Push',
                name : 'FUll_BUILD')
        }
	environment {
		currentBuildNumber = "${env.BUILD_NUMBER}"
	}


	tools {
            maven 'Maven 3.6.3'
            jdk 'jdk8'

     }
    stages {
        stage('Code compile and package build') {
        when {
                        expression {
                            return  params.FUll_BUILD
                        }
        }
            steps {
                echo 'Building..'
                sh 'mvn package'
            }
        }
        stage('Test') {
         when {
                                expression {
                                    return  params.FUll_BUILD
                                }
           }
            steps {
                echo 'Testing..'
                sh 'mvn test'
            }
        }
        stage('Docker image build') {
		when {
                                        expression {
                                            return  params.FUll_BUILD
                                        }
          }
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
