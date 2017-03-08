package bestfit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import segMemoria.SegMemoria;
import simulator.VM;

public class best {
	final public static int NUMBER_OF_PMS = 10;
	public static List<VM> traceVMS = new ArrayList<VM>();
	float MEM_CAP_OF_PM = (float) 0.55;
	float CPU_CAP_OF_PM = (float) 0.55;
	
	public best(ArrayList<VM> VMlist, float CPUcap, float memcap) {
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
			segMemoria[i-1] = seg;
		}

		int j;
		for (int i = 0; i < NUMBER_OF_PMS; i++) {
			for (j = i + 1; j < NUMBER_OF_PMS; j++) {
				if (segMemoria[i].size > segMemoria[j].size) {
					SegMemoria t = segMemoria[i];
					segMemoria[i] = segMemoria[j];
					segMemoria[j] = t;
				}
			}
		}

		for (int i = 0; i < traceVMS.size(); i++) {
			for (j = 0; j < NUMBER_OF_PMS; j++) {
				if (segMemoria[j].size >= traceVMS.get(i).getMemoryReq() && CPU_CAP_OF_PM >= traceVMS.get(i).getCpuReq()) {
					segMemoria[j].size -= traceVMS.get(i).getMemoryReq();
					
					System.out.print("VM " + (i + 1)
							+ " alocado no espaço de memória " + segMemoria[j].id+". ");
					System.out.println("Espaço restante após a alocação "
							+ segMemoria[j].size);
					
					break;
				}

			}
			if (j == NUMBER_OF_PMS) {
				System.out.println("Não há mais memória para o processo " + i);
				break;
			}
		}
	}
	

}

