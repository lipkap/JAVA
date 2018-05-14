import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class load_and_save 
{   
	//BRAKUJE OBSŁUGI WYJĄTKÓW
	public static int load(JFileChooser chooser, int index, String[][] table) 
	{
		try 
		{ 
			Scanner odczyt= new Scanner ( chooser.getSelectedFile() ); 
			odczyt.nextLine();
			
			while(odczyt.next() != null )
			{
				table[index][0] = odczyt.next(); // date
				System.out.print(table[index][0] + " ");	
				table[index][1] = odczyt.next(); // time
				System.out.print(table[index][1] + " ");	
				table[index][2] = odczyt.next(); // latitude
				System.out.print(table[index][2] + " ");	
				table[index][3] = odczyt.next(); // longitude
				System.out.print(table[index][3] + " ");	
				table[index][4] = odczyt.next(); // altitude
				System.out.print(table[index][4] + " ");	
				table[index][5] = odczyt.next(); // distance
				System.out.print(table[index][5] + " ");	
				System.out.println("Is good. " + ++index);
			}
			System.out.println("Data loaded from " + chooser.getSelectedFile());
			odczyt.close();
			return index;
		}
		catch (Exception e) 
		{
			System.out.println("EOF (END OF FILE) reached.");
			return index;
		}
	}

	public static void save(String fileName, int index, String[][] table) 
	{   
		PrintWriter outputStream = null;                                                 
		try 
		{
			outputStream = new PrintWriter (new FileOutputStream(fileName, false));
		}
		catch (FileNotFoundException e) 
		{
			System.out.println ("Błąd utworzenia " +fileName);
			System.exit (0); 
		}
		outputStream.println("blablabla"); 
		for (int ix=0;ix<index;ix++)
		{                       
			outputStream.printf("%s\t%s\t%s\n",table[ix][1],table[ix][4],table[ix][5] );
		}
		System.out.println("Data saved to " +fileName);
		outputStream.close();			
	}
	
	public static void main(String[] args) throws FileNotFoundException 
	{	
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		System.out.println(chooser.getSelectedFile().getName() + " wybrany");
		
		int i = 0;
		String[][] tablica = new String[2300][6]; //[x][0]date, [x][1]time, [x][2]lati, [x][3]longi, [x][4]alti, [x][5]dist;
		
		
		i = load_and_save.load(chooser, i, tablica); // public static int load jest "int" a nie "void" ponieważ po zakończeniu działania, funkcja musi zwrócić "int index" dla metody 'save'
		load_and_save.save("velocity.csv", i, tablica);
	
		}
		System.exit(0);
	}
	

	

}
	
	
	