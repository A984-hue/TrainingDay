pipeline {
    agent any
 
    options {
        ansiColor('xterm')
    }
 
    stages {
 
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
 
        stage('Test') {
            steps {
                sh 'mvn clean test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }            
        }
 
        stage('Package') {
            steps {
                sh 'mvn clean package'
            }
        }
		stage('Deploy') {
            	when {
                	branch 'master'
            }
            	steps {
            	sh 'docker rm -f TrainingDay'
                sh 'docker run -d --rm -p 9080:8080 --name TrainingDay de.qupe/trainingday:0.0.1-SNAPSHOT'   
            }
        }
    }
}