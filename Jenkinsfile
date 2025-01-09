pipeline {
  agent any
  parameters {
        gitParameter name: 'TAG', type: 'PT_TAG', defaultValue: ' '
  }
  stages {
    stage('Example') {
      steps {
        echo "Tag was chosen: ${params.TAG}"
      }
    }
  }
}