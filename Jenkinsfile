pipeline {
    agent any
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Enter branch name')
        string(name: 'TAG_NAME', defaultValue: '', description: 'Enter tag name')
    }
    stages {
        stage('Print parameters') {
            steps {
                echo "Branch was chosen: ${params.BRANCH_NAME}"
                echo "Tag was chosen: ${params.TAG_NAME}"
            }
        }
    }
}
