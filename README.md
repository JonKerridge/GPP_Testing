This repository contains a set of testing codes of the Groovy parallel Patterns (GPP) library.

Contact J.Kerridge@napier.ac.uk

The codes are based on the JUnit tests contained in groovyParallelPatterns 
modified so that they can make use of the GPP_Builder program.

It also contains a set of scripts that allow the use of the code 
Visualisations that can also be built using GPP_Builder.

The script BuildAllTests converts all the *.gpp scripts into *.groovy files 
that can be run.

The script RunAllTests causes all the test codes that do not use the Visualiser to run.

Each Visualisation code needs to be run individually
so that interaction with the Visualisation can be observed and used.

It is assumed that the required libraries have already been downloaded
to the Local Maven repository.

jcsp: https://github.com/CSPforJAVA/jcsp/releases/tag/1.1.8  
groovyJCSP: https://github.com/JonKerridge/groovyJCSP/releases/tag/1.1.8  
groovyParallelPatterns: https://github.com/JonKerridge/GPP_Library/releases/tag/1.1.11  
GPP_Builder: https://github.com/JonKerridge/GPP_Builder/releases/tag/1.1.11  

The repositories and dependencies used in the build.gradle file are:
<pre>
repositories {
    mavenCentral()
    // following required for groovy-all:3.0.0
    maven {
        url "https://mvnrepository.com/artifact/org.codehaus.groovy/groovy-all"
    }
    mavenLocal()
}

dependencies {
    implementation 'org.codehaus.groovy:groovy-all:3.0.7'
    implementation 'cspforjava:jcsp:1.1.8'
    implementation 'groovyJCSP:groovyJCSP:1.1.8'
    implementation "groovyParallelPatterns:groovyParallelPatterns:1.1.11"
    implementation "gppBuilder:gppBuilder:1.1.11"
    implementation group: 'junit', name: 'junit', version: '4.13.1'
    testImplementation 'org.codehaus.groovy:groovy-all:3.0.7'
    testImplementation 'cspforjava:jcsp:1.1.8'
    testImplementation 'groovyJCSP:groovyJCSP:1.1.8'
    testImplementation "groovyParallelPatterns:groovyParallelPatterns:1.1.11"
    testImplementation "gppBuilder:gppBuilder:1.1.11"
    testImplementation group: 'junit', name: 'junit', version: '4.13.1'
}
</pre>