package simulator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import bestfit.best;
import firstfit.first;
import simulator.*;

public class Simulator {
	
	public static ArrayList<VM> traceVMS = new ArrayList<VM>();
	

	
	public static void main(String[] args) {
		
		String type_of_VM = "Production";
		int numberOfRequests = 0;
		
		String csvFile = "trace_reduzido.txt";
        String line = "";
        String splitter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	br.readLine(); //eliminate header

            while ((line = br.readLine()) != null) {
            	String[] params = line.split(splitter);
            	String type = params[8].replace("\"", "");
                // use comma as separator
            	if(type.equals(type_of_VM)) {
                    Float cpuReq = Float.parseFloat(params[6]);
                    Float memReq = Float.parseFloat(params[7]);
                    VM newVM = new VM(cpuReq, memReq);
                    traceVMS.add(newVM);
                    numberOfRequests++;
            	}
            }
            
            if(true) {
    			best fit = new best(traceVMS,(float) 0.55, (float) 0.55);
    			fit.aloca();
    		}else if(false){
    			//
    		}else{
    			//
    		}

        } catch (IOException e) {
            e.printStackTrace();
        }
        
		//fit.allocate(traceVMS);
//		for (PM pm : server.availablePMs) {
//			System.out.println(pm.getAllocatedVMs().toString());
//		}
        //generateLog(server,fit, MEM_CAP_OF_PM, CPU_CAP_OF_PM, args[0], type_of_VM, numberOfRequests);
	}
	
	
	
}
