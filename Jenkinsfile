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
        bat 'echo "Hello World"'
        bat 'echo "Testing Jenkinsfile"'
        bat 'echo "Testing Webhook created in Jenkins"'
      }
    }
  }
}
