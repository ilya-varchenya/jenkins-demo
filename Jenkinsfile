properties([
  parameters([
    [
      $class: 'ChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'branch_name',
      script: [
        $class: 'ScriptlerScript',
        scriptlerScriptId: 'Branch.groovy'
      ]
    ],
    [
      $class: 'CascadeChoiceParameter',
      choiceType: 'PT_SINGLE_SELECT',
      name: 'tag_name',
      referencedParameters: 'branch_name',
      script: [
        $class: 'ScriptlerScript',
        scriptlerScriptId:'Tag.groovy',
        parameters: [
          [name:'branch_name', value: '$branch_name']
        ]
      ]
   ]
 ])
])

pipeline {
    agent any
    // parameters {
    //     choice(name: 'BRANCH_NAME', choices: ['master', 'dev', 'feature'], description: 'Choose branch name')
    //     choice(name: 'TAG_NAME', choices: ['1.0.0', '1.0.1', '1.0.2'], description: 'Choose tag name')
    // }
    stages {
        stage('Print parameters') {
            steps {
                echo "Branch was chosen: ${params.BRANCH_NAME}"
                echo "Tag was chosen: ${params.TAG_NAME}"
            }
        }
    }
}
