pipeline {
    agent any
	tools {
            maven 'Maven 3.6.3'
            jdk 'jdk8'
            docker 'default_docker'
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
                echo 'Deploying....'
                sh './Dockerbuild.sh'
            }
        }

    }
}
