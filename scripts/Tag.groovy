if (!branch_name) return ["Please, choose a branch"]
def proc = "git ls-remote --tags https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def tags = proc.in.text.readLines().findAll { it.contains(branch_name) }.collect { it.split()[1].replace('refs/tags/', '') }
return tags.isEmpty() ? ["No tags for chosen branch"] : tags
