def proc = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo".execute()
proc.waitFor()
def branches = proc.in.text.readLines().collect { it.split()[1].replace('refs/heads/', '') }
return branches
