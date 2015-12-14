package group1.clinic.ui;

import java.util.Observable;
import java.util.Observer;

import dw317.clinic.business.interfaces.Patient;
import dw317.clinic.business.interfaces.PatientVisitManager;
import dw317.clinic.business.interfaces.Visit;

public class TextView implements Observer{

	public TextView(PatientVisitManager model)
	{
		
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		if(arg1 instanceof Patient)
		{
			
		}
		if(arg1 instanceof Visit)
		{
			
		}
	}
	
	

}
