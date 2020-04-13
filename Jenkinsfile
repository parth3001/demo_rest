def notifySlack(String buildStatus = 'STARTED') {
    // Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'

    def color

    if (buildStatus == 'STARTED') {
        color = '#D4DADF'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

    slackSend(color: color, message: msg)
}




pipeline {
    agent any
	environment {
		currentBuildNumber = "${env.BUILD_NUMBER}"
	} // environment


	tools {
            maven 'Maven 3.6.3'
            jdk 'jdk8'

     } // tools
    stages {
        stage('Code compile and package build') {
            steps {
                echo 'Building..'
                sh 'mvn -Dmaven.test.skip=true package'
            } // steps
        } // stage
        stage('CodeQuality Analysis with Sonar') {
        	steps{
                  withSonarQubeEnv(installationName: 'Sonar_Qube') { // You can override the credential to be used
                       sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.0.1398:sonar'
                     }
                 } // steps
         } // stage
        stage('Unit testing') {
            steps {
                echo 'Testing..'
                sh 'mvn test -f'
            } //steps
        } // stage
        stage('Docker image build') {
            steps {
                echo 'Docker build....'
                sh '/usr/local/bin/docker build -t ppatel21/spring-rest-hello-world:${currentBuildNumber} . '
            } // steps
        } // stage
        stage('Docker image push to repo'){
        	steps{
        	withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'USER', passwordVariable: 'PASS')]) {
        		sh 'echo $USER'
        		sh  '/usr/local/bin/docker login --username=$USER --password=$PASS'
        		sh '/usr/local/bin/docker push ppatel21/spring-rest-hello-world:${currentBuildNumber}'
        		}
        	} // steps
     	} // stage
     } // stages

      post {
            // only triggered when blue or green sign
            always  {
                notifySlack(currentBuild.result)
            }

         } // post


} // pipeline
