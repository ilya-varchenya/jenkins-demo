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
    stages {
        stage('Print parameters') {
            steps {
                echo "Branch was chosen: ${params.branch_name}"
                echo "Tag was chosen: ${params.tag_name}"
            }
        }
    }
}
