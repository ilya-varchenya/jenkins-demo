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
def cloneRepository(String repoUrl, String destinationPath, String branchName) {
  try {
    // Check if repo already was cloned
    def destDir = new File(destinationPath)
    if (destDir.exists()) {
      throw new IllegalArgumentException("Destination path already exists: ${destinationPath}")
    }
    def process = "git clone --single-branch --branch ${branchName} ${repoUrl} ${destinationPath}".execute()
    def output = process.inputStream.text.trim()
    def exitCode = process.waitFor()
    if (exitCode != 0) {
      throw new RuntimeException("Git clone failed with exit code ${exitCode}: ${output}")
    }
  } catch (Exception e) {
    println "Error: ${e.message}"
  }
}

def getTagsFromBranch(String repoPath, String branchName) {
  try {
    // Check if dir exists
    def gitDir = new File(repoPath)
    if (!gitDir.exists()) {
      throw new IllegalArgumentException("Repository path does not exist: ${repoPath}")
    }
    if (!new File(gitDir, ".git").exists()) {
      throw new IllegalArgumentException("No .git directory found in path: ${repoPath}")
    }

    def command = ["git", "tag", "--merged", branchName]
    def process = new ProcessBuilder(command)
      .directory(gitDir)
      .redirectErrorStream(true)
      .start()
    def output = process.inputStream.text.trim()
    def exitCode = process.waitFor()
    if (exitCode != 0) {
      throw new RuntimeException("Git command failed with exit code ${exitCode}: ${output}")
    }
    return output.tokenize("\n")
  } catch (Exception e) {
    println "Error: ${e.message}"
    return []
  }
}

def repo = 'https://github.com/ilya-varchenya/jenkins-demo'
def destinationPath = "/var/jenkins_home/demo"
cloneRepository(repo, destinationPath, branchName)

def tags = getTagsFromBranch(destinationPath, branchName)

// cleanup
"rm -rf ${destinationPath}".execute()
return branchName
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
