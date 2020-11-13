//Test pipeline file - doesn't actually do anything useful for now.
pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withMaven(maven : 'M3') {
          bat 'mvn clean compile'
        }
      }
    }
    stage('Test') {
      steps {
        withMaven(maven : 'M3') {
          bat 'mvn test'
        }
      }
    }
  }
}
