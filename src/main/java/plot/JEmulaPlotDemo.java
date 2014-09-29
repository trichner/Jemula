/*
 * 
 * This is jemula.
 *
 *    Copyright (c) 2006-2009 Stefan Mangold, Fabian Dreier, Stefan Schmid
 *    All rights reserved. Urheberrechtlich geschuetzt.
 *    
 *    Redistribution and use in source and binary forms, with or without modification,
 *    are permitted provided that the following conditions are met:
 *    
 *      Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer. 
 *    
 *      Redistributions in binary form must reproduce the above copyright notice,
 *      this list of conditions and the following disclaimer in the documentation and/or
 *      other materials provided with the distribution. 
 *    
 *      Neither the name of any affiliation of Stefan Mangold nor the names of its contributors
 *      may be used to endorse or promote products derived from this software without
 *      specific prior written permission. 
 *    
 *    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 *    EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *    OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *    IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 *    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *    OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 *    WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 *    ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY
 *    OF SUCH DAMAGE.
 *    
 */

package plot;



/**
 * A little demo application for the plotter classes.
 * Set the DEMO variable to 1, 2, 3 to choose the plot type and
 * see the out commented lines for different plot options.
 * 
 * @author Laurent Zimmerli - laurentz@student.ethz.ch
 *
 */
public class JEmulaPlotDemo {

	/**
	 * Entry point of the application.
	 * 
	 * @param args Ignored.
	 */
    public static void main(final String[] args) {
    	// Some locals:
    	double data;
    	JEPlotter plotter = null;
    	JEMultiPlotter multiPlotter = null;
    	JESynchroPlotter synchroPlotter = null;
    	Double sleepTime;
    	int time = 0;
    	
    	// Set DEMO = 1 for the simple plotter demo, 2 for the multi plotter demo, or 3 for the synchro plotter demo.
    	int DEMO = 3;
    	
    	switch (DEMO) {
    	case 1: // Simple Plotter Demo
    		
            //plotter = new JEPlotter("Message Processing Time", "Time", "TX time [ms]", 60000, true);
            plotter = new JEPlotter("Message Processing Time", "Time", "TX time [ms]", 60000, true, 50, 200);
            plotter.display();
            break;
    	case 2: // MultiPlotter Demo
    		
        	//multiPlotter = new JEMultiPlotter("Queue Sizes", "Queue 1", "Time", "Queue Size", 60000, true);
            multiPlotter = new JEMultiPlotter("Queue Sizes", "Queue 1", "Time", "Queue Size", 60000, true, 50, 200);
            
            multiPlotter.addSeries("Queue 2");
            multiPlotter.addSeries("Queue 3");
            
            multiPlotter.display();
            break;
    	case 3: // SynchroPlotter Demo
        	
            synchroPlotter = new JESynchroPlotter("Live Plotters", 3, "Time", 60000);
            
            synchroPlotter.addSubPlot("Delay", "Delay [ms]");
            synchroPlotter.addSubPlot("Current Queue Size", "Queuesize [packets]");
            synchroPlotter.addSubPlot("Throughput", "Throughput [Mb/s]", 50, 200);
            
            synchroPlotter.display();
            break;
    	}
    	
    	while (true) {
    		switch (DEMO) {
    		case 1: // Simple Plotter Demo
            	try {
            		sleepTime = 500 + Math.random()*1000;
            		Thread.sleep(sleepTime.intValue());
            		time += 500;
            	} catch (Exception e) {}
                data = (0.90 + 0.2 * Math.random()) * 100;
                plotter.plot(time, data);
    			break;
        	case 2: // MultiPlotter Demo
            	try {
            		sleepTime = 500 + Math.random()*1000;
            		Thread.sleep(sleepTime.intValue());
            		time += 500;
            	} catch (Exception e) {}
                data = (0.90 + 0.2 * Math.random()) * 100;
                multiPlotter.plot(time, data, 0);
                data = (0.75 + 0.2 * Math.random()) * 100;
                multiPlotter.plot(time, data, 1);
                data = (0.84 + 0.2 * Math.random()) * 100;
                multiPlotter.plot(time, data, 2);
                break;
        	case 3: // SynchroPlotter Demo
            	try {
            		sleepTime = 500 + Math.random()*1000;
            		Thread.sleep(sleepTime.intValue());
            		time += 500;
            	} catch (Exception e) {}
                data = (0.90 + 0.2 * Math.random()) * 100;
                synchroPlotter.plot(time, data, 0);
                data = (0.75 + 0.2 * Math.random()) * 100;
                synchroPlotter.plot(time, data, 1);
                data = (0.84 + 0.2 * Math.random()) * 100;
                synchroPlotter.plot(time, data, 2);
        		break;
    		}
    	}
    }
}
