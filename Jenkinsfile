pipeline {
  agent any
  parameters {
    gitParameter branchFilter: 'origin/(.*)', defaultValue: 'master', name: 'BRANCH_TAG', type: 'PT_BRANCH_TAG'
  }
  stages {
    stage('Example') {
      steps {
        echo "Branch was chosen: ${params.BRANCH_TAG}"
      }
    }
  }
}