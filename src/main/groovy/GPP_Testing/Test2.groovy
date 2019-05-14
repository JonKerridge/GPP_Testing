package GPP_Testing

import jcsp.lang.*
import groovyJCSP.*
 
import GPP_Library.DataDetails
import GPP_Library.LocalDetails
import GPP_Library.ResultDetails
import GPP_Library.functionals.transformers.CombineNto1
import GPP_Library.functionals.workers.Worker
import GPP_Library.terminals.*
import static org.junit.Assert.assertTrue
import TestDataDefs.*
 
 

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
 
def localData = new LocalDetails( lName: TestData.getName(),
lInitMethod: TestData.partialInitialise)
 
def outData = new LocalDetails( lName: TestData.getName(),
lInitMethod: TestData.partialInitialise,
lFinaliseMethod: TestData.finaliseMethod)
 

//NETWORK

def chan1 = Channel.one2one()
def chan2 = Channel.one2one()
def chan3 = Channel.one2one()

def emitter = new Emit(
    // input channel not required
    output: chan1.out(),
    eDetails: emitterDetails )
 
def combiner = new CombineNto1(
    input: chan1.in(),
    output: chan2.out(),
    localDetails: localData,
    outDetails: outData,
    combineMethod: CombineData.combineMethod)
 
def worker = new Worker(
    input: chan2.in(),
    output: chan3.out(),
    function: TestData.nullFunc)
 
def collector = new Collect(
    input: chan3.in(),
    // no output channel required
    rDetails: resultDetails)

PAR network = new PAR()
 network = new PAR([emitter , combiner , worker , collector ])
 network.run()
 network.removeAllProcesses()
//END

 
 
 
println "2: $er"
 
assertTrue (er.finalSum == 210)
assertTrue (er.dataSetCount == 1)
assertTrue (er.finalInstance == 20)
