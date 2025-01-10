pipeline {
  agent any
  parameters {
        gitParameter name: 'TAG', type: 'PT_TAG', defaultValue: 'Can\'t retrieve tags', useRepository: 'https://github.com/ilya-varchenya/test_repo'
  }
  stages {
    stage('Example') {
      steps {
        echo "Tag was chosen: ${params.TAG}"
        checkout([$class: 'GitSCM',
                          branches: [[name: "${params.TAG}"]],
                          doGenerateSubmoduleConfigurations: false,
                          extensions: [],
                          gitTool: 'Default',
                          submoduleCfg: [],
                          userRemoteConfigs: [[url: 'https://github.com/ilya-varchenya/test_repo']]
                        ])
      }
    }
  }
}