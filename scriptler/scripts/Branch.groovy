// def proc = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo".execute()
// proc.waitFor()
// def branches = proc.in.text.readLines().collect { it.split()[1].replace('refs/heads/', '') }
// return branches

def command = "git ls-remote --heads https://github.com/ilya-varchenya/jenkins-demo"
try {
    def process = command.execute()
    process.waitForOrTimeout(10000) // Wait up to 10 seconds
    
    def branches = process.in.text.readLines().collect { it.split()[1].replaceAll('refs/heads/', '') }

    branches.each { println it }

    return branches
} catch (Exception e) {
    println "An error occurred: ${e.getMessage()}"
    return []
}
