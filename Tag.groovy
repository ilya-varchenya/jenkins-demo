def branch = branch_name
if (!branch) return ["Please, choose a branch"]

def proc = "git ls-remote --tags https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def tags = proc.in.text.readLines().findAll { it.contains(branch) }.collect { 
    it.split()[1].replace('refs/tags/', '') 
}
return tags.isEmpty() ? ["No tags for chosen branch"] : tags
