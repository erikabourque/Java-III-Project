package dw317.lib.medication;

import dw317.lib.medication.Medication.Scheme;
import static java.lang.System.out;

public class MedicationSchemeTest
{
	public static void main(String[] args)
	{
		Scheme ascheme = Scheme.DIN;
		if(ascheme == Scheme.DIN)
			out.println("The scheme is a DIN");
		Scheme bscheme = Scheme.NDC;
		if(bscheme == Scheme.NDC)
			out.println("The scheme is an NDC");
	}
	
	
	
}
