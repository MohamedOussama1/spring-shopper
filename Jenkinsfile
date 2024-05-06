pipeline {
	agent any

	tools {
		jdk 'java'
		maven '3.9.6'
	}

	stages {

		stage('Build'){
			steps {
				bat "mvn clean install -DskipTests"
			}
		}

		stage('Test'){
			steps{
				bat "echo Testing"
			}
		}

		stage('Deploy') {
			steps {
			    // bat "mvn jar:jar deploy:deploy"
			    bat "mvn spring-boot:run"
			}
		}
	}
}