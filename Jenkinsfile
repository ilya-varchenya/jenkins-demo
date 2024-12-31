properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'branchName',
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
      referencedParameters: 'branchName',
      script:
        [
          $class: 'GroovyScript',
          script: [
            sandbox: false,
            script: '''
def branchName = 'master'
def repo = 'https://github.com/ilya-varchenya/jenkins-demo'
def destinationPath = "/var/jenkins_home/demo"

"git clone --single-branch --branch ${branchName} ${repo} ${destinationPath}".execute().waitFor()

def command = ["git", "tag", "--merged", branchName]
def gitDir = new File(destinationPath)
def process = new ProcessBuilder(command).directory(gitDir).start()
def tags = process.inputStream.text.trim().tokenize("\n")
"rm -rf ${destinationPath}".execute()
return tags
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
        echo "Branch was chosen: ${params.branchName}"
        echo "Tag was chosen: ${params.tag_name}"
      }
    }
  }
}
