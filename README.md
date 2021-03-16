This repository contains a set of testing codes of the Groovy parallel Patterns (GPP) library.

Contact J.Kerridge @ napier.ac.uk

The codes are based on the JUnit tests contained in groovyParallelPatterns 
modified so that they can make use of the GPP_Builder program.

The code uses Java 11 and Groovy.3.0.7

It also contains a set of scripts that allow the use of the code 
Visualisations that can also be built using GPP_Builder.

The script BuildAllTests converts all the *.gpp scripts into *.groovy files 
that can be run.

The script RunAllTests causes all the test codes that do not use the Visualiser to run.

Each Visualisation code needs to be run individually
so that interaction with the Visualisation can be observed and used.

The gradle build file for the project does load the required components.

The required libraries are:

jcsp: cspforjava.jcsp version 1.1.9  
groovyJCSP: jonkerridge.groovy_jcsp version 1.1.9  
groovyParallelPatterns: jonkerridge.groovy_parallel_patterns version 1.1.12  
GPP_Builder: jonkerridge.gpp_builder version1.1.12  

The build.gradle uses the repositories and dependencies:

<pre>
repositories {
  mavenCentral()
  maven { // to download the jonkerridge.groovy_jcsp library
    name = "GitHub"
    url = "https://maven.pkg.github.com/JonKerridge/groovyJCSP"
    credentials {
      username = project.findProperty("gpr.user")
      password = project.findProperty("gpr.key")
    }
  }
  maven { // to download the cspforjava.jcsp library
    name = "GitHub"
    url = "https://maven.pkg.github.com/CSPforJAVA/jcsp"
    credentials {
      username = project.findProperty("gpr.user")
      password = project.findProperty("gpr.key")
    }
  }
  maven { // to download the jonkerridge.groovy_parallel_patterns library
    name = "GitHub"
    url = "https://maven.pkg.github.com/JonKerridge/GPP_Library"
    credentials {
      username = project.findProperty("gpr.user")
      password = project.findProperty("gpr.key")
    }
  }
  maven { // to download the jonkerridge.gpp_builder library
    name = "GitHub"
    url = "https://maven.pkg.github.com/JonKerridge/GPP_Builder"
    credentials {
      username = project.findProperty("gpr.user")
      password = project.findProperty("gpr.key")
    }
  }

}

dependencies {
  implementation 'org.codehaus.groovy:groovy-all:3.0.7'
  implementation 'cspforjava:jcsp:1.1.9'
  implementation 'jonkerridge:groovy_jcsp:1.1.9'
  implementation "jonkerridge:groovy_parallel_patterns:1.1.12"
  implementation "jonkerridge:gpp_builder:1.1.12"
}
</pre>

**Please note**

In order to download Github Packages a user requires to have a Github Personal Access Token.  
See https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token

A gradle.properties file is required at the same directory level as the build.gradle file that contains

<pre>
gpr.user=userName
gpr.key=userPersonalAccessToken
</pre>
