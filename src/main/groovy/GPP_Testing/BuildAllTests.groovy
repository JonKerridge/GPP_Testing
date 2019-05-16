package GPP_Testing

import GPP_Builder.GPPbuilder

def build = new GPPbuilder()
String rootPath = "./"  // as required for use in Intellij
// concordance
build.runBuilder(rootPath + "Test1")
build.runBuilder(rootPath + "Test1Vis")
build.runBuilder(rootPath + "Test2")
build.runBuilder(rootPath + "Test2Vis")

build.runBuilder(rootPath + "Test28")
build.runBuilder(rootPath + "Test28Vis")
build.runBuilder(rootPath + "Test29")
build.runBuilder(rootPath + "Test29Vis")
build.runBuilder(rootPath + "Test30")
build.runBuilder(rootPath + "Test30Vis")
build.runBuilder(rootPath + "Test31")
build.runBuilder(rootPath + "Test31Vis")
