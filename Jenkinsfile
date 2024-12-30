properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'branch_name',
      script: 
        [
          $class: 'GroovyScript', 
          script: [
            sandbox: false,
            script: '''
def proc = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def branches = proc.in.text.readLines().collect { it.split()[1].replace('refs/heads/', '') }
return branches
'''
          ],
          fallbackScript: [
            sandbox: false, 
            script: 
              'return[\'Could not get branches from GIT\']'
          ], 
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
          script: [
            sandbox: false,
            script: '''
if (!branch_name) return ["Please, choose a branch"]
def branchCommit = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo refs/heads/${branch_name}".execute().text.trim().split()[0]
def tagsOutput = "git ls-remote --tags https://github.com/ilya-varchenya/jenkins-demo".execute().text

def matchingTags = tagsOutput.readLines().findAll { line ->
    def tagCommit = line.split()[0]
  	def procOutput = "git merge-base --is-ancestor ${tagCommit} ${branchCommit}".execute().waitFor()
	  procOutput == 0
}.collect { line ->
    line.split()[1].replaceAll('refs/tags/', '')
}
return matchingTags
'''
          ],
          fallbackScript: [
            sandbox: false, 
            script: 
              'return[\'Could not get tags from branch\']'
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
        echo "Branch was chosen: ${params.branch_name}"
        echo "Tag was chosen: ${params.tag_name}"
      }
    }
  }
}
