pipeline {
  agent any
  parameters {
    gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH', type: 'PT_BRANCH'
    gitParameter defaultValue: 'master', name: 'TAG', type: 'PT_TAG'
  }
  stages {
    stage('Example') {
      steps {
        echo "Branch was chosen: ${params.BRANCH}"
        echo "Tag was chosen: ${params.TAG}"
      }
    }
  }
}