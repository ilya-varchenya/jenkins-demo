// properties([
//   parameters([
//     [
//       $class: 'ChoiceParameter',
//       choiceType: 'PT_SINGLE_SELECT',
//       name: 'branch_name',
//       script: [
//         $class: 'ScriptlerScript',
//         script: [
//             '''
//             def proc = "git ls-remote --heads https://github.com/your-repo.git".execute()
//             proc.waitFor()
//             def branches = proc.in.text.readLines().collect { it.split()[1].replace('refs/heads/', '') }
//             return branches
//             '''

//         ]
//       ]
//     ],
//     [
//       $class: 'ChoiceParameter',
//       choiceType: 'PT_SINGLE_SELECT',
//       name: 'tag_name',
//       referencedParameters: 'branch_name',
//       script: [
//         $class: 'ScriptlerScript',
//         scriptlerScriptId:'tag_name.groovy',
//         parameters: [
//           [name:'tag_name', value: '$tag_name']
//         ]
//       ]
//    ]
//  ])
// ])

pipeline {
    agent any
    parameters {
        choice(name: 'BRANCH_NAME', choices: ['master', 'dev', 'feature'], description: 'Choose branch name')
        choice(name: 'TAG_NAME', choices: ['1.0.0', '1.0.1', '1.0.2'], description: 'Choose tag name')
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
