

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class worst {
	final public static int NUMBER_OF_PMS = 10;
	public static List<VM> traceVMS = new ArrayList<VM>();
	float MEM_CAP_OF_PM, CPU_CAP_OF_PM;
	public int aceitas = 0, rejeitadas = 0;
	
	public worst(ArrayList<VM> VMlist, float CPUcap, float memcap) {
		this.traceVMS = VMlist;
		this.MEM_CAP_OF_PM = memcap;
		this.CPU_CAP_OF_PM = CPUcap;
		
	}
	public void aloca() throws IOException{
		
		Scanner in = new Scanner(System.in);
		
		SegMemoria segMemoria[] = new SegMemoria[NUMBER_OF_PMS];
		

		for (int i = 1; i <= NUMBER_OF_PMS; i++) {
			SegMemoria seg = new SegMemoria();
			seg.id = i;
			seg.size = MEM_CAP_OF_PM;
			segMemoria[i-1] = seg;
		}

		int j;
		
       ordenar(segMemoria, NUMBER_OF_PMS);
                
		for (int i = 0; i < traceVMS.size(); i++) {
			for (j = 0; j < NUMBER_OF_PMS; j++) {
				if (segMemoria[j].size >= traceVMS.get(i).getMemoryReq() && CPU_CAP_OF_PM >= traceVMS.get(i).getCpuReq()) {
					segMemoria[j].size -= traceVMS.get(i).getMemoryReq();
					aceitas ++;
					
					System.out.print("VM " + (i + 1)
							+ " alocado no espaço de memoria " + segMemoria[j].id + ". ");
					System.out.println("Espaço restante após a alocação "
							+ segMemoria[j].size);
                                        ordenar(segMemoria, NUMBER_OF_PMS);
					
					break;
				}else{
					rejeitadas++;
				}

			}
			//if (j == NUMBER_OF_PMS) {
			//	System.out.println("Não há mais memoria para a requisicao " + i);
			//}
		}
		System.out.println("VMS Aceitas " + aceitas);
		System.out.println("VMS rejeitadas " + rejeitadas);
		
	}
	
	

    private static void ordenar(SegMemoria segMemoria[], int m) {
        int i,j;
        for (i = 0; i < m; i++) {
			for (j = i + 1; j < m; j++) {
				if (segMemoria[i].size < segMemoria[j].size) {
					SegMemoria t = segMemoria[i];
					segMemoria[i] = segMemoria[j];
					segMemoria[j] = t;
                                        
				}
			}
		}
    }
}
