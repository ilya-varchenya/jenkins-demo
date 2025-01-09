pipeline {
  agent any
  parameters {
        gitParameter name: 'TAG', type: 'PT_TAG', defaultValue: ' '
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
                          userRemoteConfigs: [[url: 'https://github.com/ilya-varchenya/jenkins-demo']]
                        ])
      }
    }
  }
}