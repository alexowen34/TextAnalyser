//Test pipeline file - doesn't actually do anything useful for now.
pipeline {
  agent any
  stages {
    stage ('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Build') {
      steps {
        withMaven(maven : 'M3') {
          bat 'mvn clean compile'
        }
      }
    }
    stage('Build') {
      steps {
        withMaven(maven : 'M3') {
          bat 'mvn test'
        }
      }
    }
  }
}
