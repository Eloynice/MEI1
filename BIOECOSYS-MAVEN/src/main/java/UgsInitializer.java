import org.chocosolver.solver.variables.IntVar;
import org.chocosolver.solver.Model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class UgsInitializer {
	Model model;
	public  UgsInitializer(Model m){
		this.model = m;
	}

public IntVar[] giveDomains(){
		IntVar[] ugs = new IntVar[1374];
		int index = 1;

		try {
		File myObj = new File("ugs_init");
		Scanner myReader = new Scanner(myObj);
		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();
			String[] str_split = data.split(",", 0);
			int size = str_split.length;
			int [] toInsert = new int [size];
			for(int i=0; i<size; i++) {
				toInsert[i] = Integer.parseInt(str_split[i]);
			}
			ugs[index] = model.intVar("UG_"+index, toInsert);
			index++;
		}
		myReader.close();
		} catch (FileNotFoundException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
		}

		return ugs;
	}
}