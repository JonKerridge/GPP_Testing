package GPP_Testing

import jcsp.lang.*
import groovyJCSP.*
 
import GPP_Library.DataDetails
import GPP_Library.ResultDetails
import GPP_Library.functionals.workers.Worker
import GPP_Library.terminals.Collect
import GPP_Library.terminals.Emit
import TestDataDefs.TestData
import TestDataDefs.TestExtract
import TestDataDefs.TestResult
import groovyJCSP.PAR
 
import static org.junit.Assert.assertTrue
import static org.junit.Assert.assertTrue
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
 
//@log 1 "./Test1-"

import GPP_Library.Logger
import GPP_Library.LoggingVisualiser
import GPP_Library.gppVis.Visualiser
import GPP_Library.gppVis.Connector
import javafx.application.Platform

def logChan = Channel.any2one()
Logger.initLogChannel(logChan.out())
def logVis = new LoggingVisualiser ( logInput: logChan.in(), 
                     collectors: 1,
                     logFileName: "./Test1-" )

//gppVis command
new Thread() {
	@Override
	public void run() {
		Visualiser.main();
	}
}.start();
 

//NETWORK

def chan1 = Channel.one2one()
def chan2 = Channel.one2one()

def emitter = new Emit(
    // input channel not required
    output: chan1.out(),
    eDetails: emitterDetails ,
    logPhaseName: "emit",
    logPropertyName: "instanceNumber")

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addWorker("emit"));
 
def worker = new Worker(
    input: chan1.in(),
    output: chan2.out(),
    function: TestData.nullFunc,
    logPhaseName: "work",
    logPropertyName: "instanceNumber")

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addWorker("work"));
 
def collector = new Collect(
    input: chan2.in(),
    visLogChan : logChan.out(),
    // no output channel required
    rDetails: resultDetails,
    logPhaseName: "collect",
    logPropertyName: "instanceNumber")

    //gppVis command
    Visualiser.hb.getChildren().add(Visualiser.p.addWorker("collect"));

//gppVis command
//short delay to give JavaFx time to start up.
sleep(2000)
Platform.runLater(new Runnable() {
	@Override
	void run() {
		Visualiser.networkScene()
	}
});

//short delay to give JavaFx time to display.
sleep(3000);

PAR network = new PAR()
 network = new PAR([logVis, emitter , worker , collector ])
 network.run()
 network.removeAllProcesses()
//END

//gppVis command
//Now that the network has completed, tell the vis where the log file is so it
//can access the data so it can replay it.
Platform.runLater(new Runnable() {
	@Override
	void run() {
		Visualiser.readLog("./Test1-log.csv")
	}
});
 
 
println "1: $er"
 
assertTrue (er.finalSum == 210)
assertTrue (er.dataSetCount == 20)
assertTrue (er.finalInstance == 20)
