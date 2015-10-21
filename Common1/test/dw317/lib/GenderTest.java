package dw317.lib;

import static java.lang.System.out;

public class GenderTest {

	public static void main(String[] args) {
		Gender genderA = Gender.MALE;
		if(genderA == Gender.MALE)
			out.println("The gender is Male");
		Gender genderB = Gender.FEMALE;
		if(genderB == Gender.FEMALE)
			out.println("The gender is Female");

	}

}
