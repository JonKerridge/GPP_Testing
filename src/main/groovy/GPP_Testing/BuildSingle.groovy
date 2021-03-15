package GPP_Testing

import gpp_builder.GPPbuilder

def build = new GPPbuilder()
String rootPath = "./"  // as required for use in Intellij

build.runBuilder(rootPath + "Test41")
build.runBuilder(rootPath + "Test41Vis")
