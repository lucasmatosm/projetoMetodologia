

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class first {
	final public static int NUMBER_OF_PMS = 10;
	float MEM_CAP_OF_PM, CPU_CAP_OF_PM;
	public static List<VM> traceVMS = new ArrayList<VM>();
	public int aceitas = 0, rejeitadas = 0;
	public first(ArrayList<VM> VMlist, float CPUcap, float memcap) {
		this.traceVMS = VMlist;
		this.MEM_CAP_OF_PM = memcap;
		this.CPU_CAP_OF_PM = CPUcap;
	}
	
	public void aloca(){
		SegMemoria segMemoria[] = new SegMemoria[NUMBER_OF_PMS];
		
		for (int i = 1; i <= NUMBER_OF_PMS; i++) {
			SegMemoria seg = new SegMemoria();
			seg.id = i;
			seg.size = MEM_CAP_OF_PM;
			segMemoria[i - 1] = seg;
		}

		int j;

		for (int i = 0; i < traceVMS.size(); i++) {
			for (j = 0; j < NUMBER_OF_PMS; j++) {
				if (segMemoria[j].size >= traceVMS.get(i).getMemoryReq() && CPU_CAP_OF_PM >= traceVMS.get(i).getCpuReq()) {
					segMemoria[j].size -= traceVMS.get(i).getMemoryReq();
					aceitas++;
					System.out.print("VM " + (i + 1)
							+ " alocado no espa�o de mem�ria " + segMemoria[j].id+". ");
					System.out.println("Espa�o restante ap�s a aloca��o "
							+ segMemoria[j].size);
					
					break;
				}else{
					rejeitadas++;
				}

			}
			if (j == NUMBER_OF_PMS) {
				System.out.println("N�o h� mais mem�ria para a VM " + i);
			}
		}
		
		
		System.out.println("VMS Aceitas " + aceitas);
		System.out.println("VMS rejeitadas " + rejeitadas);	
		
	}
	
}
