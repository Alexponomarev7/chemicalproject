import java.util.Dictionary;
import java.util.Hashtable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class source {
		final String EMPTY = "";
	
		String[][] tablem = {
				{"H", EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, "He"},
				{"Li", "Be", "B", "C", "N", "O", "F", EMPTY, EMPTY, EMPTY, "Ne"},
				{"Na", "Mg", "Al", "Si", "P", "S", "Cl", EMPTY, EMPTY, EMPTY, "Ar"},
				{"K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", EMPTY},
				{"Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", EMPTY, EMPTY, EMPTY, "Kr"},
				{"Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", EMPTY},
				{"Ag", "Cd", "In", "Sn", "Sb", "Te", "I", EMPTY, EMPTY, EMPTY, "Xe"},
				{"Cs", "Ba", EMPTY, "Hf", "Ta", "W", "Re", "Os", "Ir", "Pt", EMPTY},
				{"Au", "Hg", "Tl", "Pb", "Bi", "Po", "At", EMPTY, EMPTY, EMPTY, "Rn"},
				{"Fr", "Ra", EMPTY, "Rf", "Db", "Sg", "Bh", "Hn", "Mt", EMPTY, EMPTY}
		};
		
		Dictionary<String, String> tasks = new Hashtable<String, String>();
		ArrayList<String> task_name = new ArrayList<String>();
		
		void load_tasks() throws FileNotFoundException {	
			Scanner in = new Scanner(new File("./tasks.txt"));
			
			while(in.hasNext()) {
				String[] task = in.nextLine().split(" ", 2);
				
				tasks.put(task[1], task[0]);
				task_name.add(task[1]);
			}
			
			in.close();
		}
}