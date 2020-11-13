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
        container('maven') {
          bat """
            mvn -s project-settings.xml -B -Djavax.net.ssl.trustStore=cacerts clean compile -U -Dmaven.test.skip=true
          """
        }
      }
    }
  }
}
