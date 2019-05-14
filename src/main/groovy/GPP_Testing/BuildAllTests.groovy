package GPP_Testing

import GPP_Builder.GPPbuilder

def build = new GPPbuilder()
String rootPath = "./"  // as required for use in Intellij
// concordance
build.runBuilder(rootPath + "Test1")
build.runBuilder(rootPath + "Test1Vis")
build.runBuilder(rootPath + "Test2")
build.runBuilder(rootPath + "Test2Vis")
