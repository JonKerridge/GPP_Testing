package GPP_Testing

import jcsp.lang.*
import groovyJCSP.*
 
import groovyParallelPatterns.DataDetails
import groovyParallelPatterns.ResultDetails
import groovyParallelPatterns.connectors.reducers.ListFanOne
import groovyParallelPatterns.connectors.spreaders.OneParCastList
import groovyParallelPatterns.functionals.groups.ListGroupList
import groovyParallelPatterns.terminals.Collect
import groovyParallelPatterns.terminals.Emit
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
 
//@log 1 "./Test14-"

import groovyParallelPatterns.Logger
import groovyParallelPatterns.LoggingVisualiser
import groovyParallelPatterns.gppVis.Visualiser
import groovyParallelPatterns.gppVis.Connector
import javafx.application.Platform

def logChan = Channel.any2one()
Logger.initLogChannel(logChan.out())
def logVis = new LoggingVisualiser ( logInput: logChan.in(), 
                     collectors: 1,
                     logFileName: "./Test14-" )

//gppVis command
new Thread() {
	@Override
	public void run() {
		Visualiser.main() 
	}
}.start() 
 

//NETWORK

def chan1 = Channel.one2one()
def chan2 = Channel.one2oneArray(4)
def chan2OutList = new ChannelOutputList(chan2)
def chan2InList = new ChannelInputList(chan2)
def chan3 = Channel.one2oneArray(4)
def chan3OutList = new ChannelOutputList(chan3)
def chan3InList = new ChannelInputList(chan3)
def chan4 = Channel.one2one()

def emitter = new Emit(
    // input channel not required
    output: chan1.out(),
    eDetails: emitterDetails,
    logPhaseName: "emit",
    logPropertyName: "instanceNumber"  )

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addWorker("emit")) 
 
def outFan = new OneParCastList(
    input: chan1.in(),
    outputList: chan2OutList )

    //gppVis command
    Visualiser.hb.getChildren().add(new Connector(Connector.TYPE.SPREADER)) 
 
def lgl = new ListGroupList(
    inputList: chan2InList,
    outputList: chan3OutList,
    workers: 4,
    function: TestData.nullFunc,
    logPhaseName: "group",
    logPropertyName: "instanceNumber"
    )

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addGroup(4, "group")) 
 
def inFan = new ListFanOne(
    inputList: chan3InList,
    output: chan4.out(),
    )

    //gppVis command
    Visualiser.hb.getChildren().add(new Connector(Connector.TYPE.REDUCER)) 
 
def collector = new Collect(
    input: chan4.in(),
    visLogChan : logChan.out(),
    // no output channel required
    rDetails: resultDetails,
    logPhaseName: "collect",
    logPropertyName: "instanceNumber")

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addWorker("collect")) 

//gppVis command
//short delay to give JavaFx time to start up.
sleep(2000)
Platform.runLater(new Runnable() {
	@Override
	void run() {
		Visualiser.networkScene("./Test14-")
	}
}) 

//short delay to give JavaFx time to display.
sleep(3000) 

PAR network = new PAR()
 network = new PAR([logVis, emitter , outFan , lgl , inFan , collector ])
 network.run()
 network.removeAllProcesses()
//END

//gppVis command
//Now that the network has completed, tell the vis where the log file is so it
//can access the data so it can replay it.
Platform.runLater(new Runnable() {
	@Override
	void run() {
		Visualiser.readLog("./Test14-log.csv")
	}
}) 
 
println "14: $er"
 
assertTrue (er.finalSum == 840)
assertTrue (er.dataSetCount == 80)
assertTrue (er.finalInstance == 20)
assertTrue (er.maxClone == 80)
