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
        withMaven {
          sh "mvn clean verify"
        } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
      }
    }
  }
}
