
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;



public class Simulator {
	
	public static ArrayList<VM> traceVMS = new ArrayList<VM>();
	
	public static void main(String[] args) {
		
		float MEM_CAP_OF_PM = Float.parseFloat(args[1]);
		float CPU_CAP_OF_PM = Float.parseFloat(args[1]);
		String type_of_VM = args[2];
		
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
            
            if(args[0].equals("first-fit")) {
    			best fit = new best(traceVMS,MEM_CAP_OF_PM, CPU_CAP_OF_PM);
    			fit.aloca();
    		}else if(args[0].equals("best-fit")){
    			first fit = new first(traceVMS,MEM_CAP_OF_PM, CPU_CAP_OF_PM);
    			fit.aloca();
    		}else{
    			worst fit = new worst(traceVMS,MEM_CAP_OF_PM, CPU_CAP_OF_PM);
    			fit.aloca();
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
