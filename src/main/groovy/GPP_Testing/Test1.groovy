package GPP_Testing

import jcsp.lang.*
import groovy_jcsp.*
 
import groovy_parallel_patterns.DataDetails
import groovy_parallel_patterns.ResultDetails
import groovy_parallel_patterns.functionals.workers.Worker
import groovy_parallel_patterns.terminals.Collect
import groovy_parallel_patterns.terminals.Emit
import TestDataDefs.TestData
import TestDataDefs.TestExtract
import TestDataDefs.TestResult
import static org.junit.Assert.assertTrue
 

def er = new TestExtract()
 
def emitterDetails = new DataDetails(dName: TestData.getName() ,
dInitMethod: TestData.totalInitialise,
dInitData: [20],
dCreateMethod: TestData.create)
 
def resultDetails = new ResultDetails(rName: TestResult.getName(),
rInitMethod: TestResult.init,
rCollectMethod:  TestResult.collector,
rFinaliseMethod: TestResult.finalise,
rFinaliseData: [er])
 
 

//NETWORK

def chan1 = Channel.one2one()
def chan2 = Channel.one2one()

def emitter = new Emit(
    // input channel not required
    output: chan1.out(),
    eDetails: emitterDetails )
 
def worker = new Worker(
    input: chan1.in(),
    output: chan2.out(),
    function: TestData.nullFunc)
 
def collector = new Collect(
    input: chan2.in(),
    // no output channel required
    rDetails: resultDetails)

PAR network = new PAR()
 network = new PAR([emitter , worker , collector ])
 network.run()
 network.removeAllProcesses()
//END

 
 
println "1: $er"
 
assertTrue (er.finalSum == 210)
assertTrue (er.dataSetCount == 20)
assertTrue (er.finalInstance == 20)
