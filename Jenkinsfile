properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'branch_or_tag',
      script: 
        [
          $class: 'GroovyScript', 
          script: [
            sandbox: false,
            script: '''
def repo = "https://github.com/ilya-varchenya/jenkins-demo"
def branches = "git ls-remote --heads ${repo}".execute().text.readLines().collect { line ->
  line.split()[1].replace("refs/heads/", "")
}
def tags = "git ls-remote --tags ${repo}".execute().text.readLines().collect { line ->
  line.split()[1].replace("refs/tags/", "")
}
def result = branches + tags
return result
'''
          ],
          fallbackScript: [
            sandbox: false, 
            script: 
              'return[\'Could not get branches or tags from GIT\']'
          ], 
        ]
    ]
  ])
])

pipeline {
  agent any
  stages {
    stage('Print parameters') {
      steps {
        echo "Branch was chosen: ${params.branch_or_tag}"
      }
    }
  }
}
