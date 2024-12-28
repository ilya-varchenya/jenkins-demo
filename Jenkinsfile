properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'branch_name',
      script: 
        [
          $class: 'GroovyScript', 
          script: '''
def proc = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def branches = proc.in.text.readLines().collect { it.split()[1].replace('refs/heads/', '') }
return branches
''' 
        ]
    ],
    [
      $class: 'CascadeChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'tag_name',
      referencedParameters: 'branch_name',
      script:
        [
          $class: 'GroovyScript',
          script: '''
if (!branch_name) return ["Please, choose a branch"]
def proc = "git ls-remote --tags https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def tags = proc.in.text.readLines().findAll { it.contains(branch_name) }.collect { it.split()[1].replace('refs/tags/', '') }
return tags.isEmpty() ? ["No tags for chosen branch"] : tags
'''
        ]
    ]
  ])
])

pipeline {
  agent any
  stages {
    stage('Print parameters') {
      steps {
        echo "Branch was chosen: ${params.branch_name}"
        echo "Tag was chosen: ${params.tag_name}"
      }
    }
  }
}
