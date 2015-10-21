/**
 * 
 */
package group1.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Groups useful list utilities methods such as merge, sort and save to text
 * file.
 * 
 * @author Danieil Skrinikov
 * @since jdk1.7
 * @version 18.10.2015
 * 
 */
public class ListUtilities {

	private static final Charset CHARACTER_ENCODING = StandardCharsets.UTF_8;

	/**
	 * Private constructor so that ListUttilities may not be instantiated.
	 * 
	 */
	private ListUtilities() {
	}

	/**
	 * Merges two sorted arrays of objects into one array.
	 * 
	 * Precondition: Files must be full to capacity and not null.
	 * 
	 * @param list1
	 *            First array to merge.
	 * @param list2
	 *            Second array to merge.
	 * @param duplicateFileName
	 *            Name of file which will get the duplicates appended to.
	 * 
	 * @throws IOException
	 *             if failed to append duplicates.
	 * 
	 * @throws IllegalArgumentException
	 *             if one of the lists is null.
	 * 
	 * @throws IllegalArgumentException
	 *             if one of the lists if not full to capacity.
	 * 
	 * @return Comparable[] Merged array from the two parameters.
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Comparable[] merge(Comparable[] list1, Comparable[] list2, String duplicateFileName)
			throws IOException, IllegalArgumentException {

		if (list1 == null || list2 == null)
			throw new IllegalArgumentException("ListUtilities.merge() - One of the lists is null.");				

		for (int i = 0; i < list1.length; i++)
			if (list1[i] == null)
				throw new IllegalArgumentException("ListUtilities.merge() - First parameter is not full to capacity.");
		for (int i = 0; i < list2.length; i++)
			if (list2[i] == null)
				throw new IllegalArgumentException("ListUtilities.merge() - Second parameter is not full to capacity.");
		


		Comparable[] temp;

		int list1Pos = 0;
		int list2Pos = 0;
		int dupPos = 0;
		int list2Remaining = list2.length;
		int list1Remaining = list1.length;

		int lengthList3 = list1.length + list2.length;
		temp = (Comparable[]) Array.newInstance(list1.getClass().getComponentType(), lengthList3);
		Comparable[] duplicates = new Comparable[lengthList3 * 2];
		

		for (int i = 0; i < lengthList3; i++) {

			if (list1Remaining > 0 && list2Remaining > 0) {

				if (list1[list1Pos].compareTo(list2[list2Pos]) == 0) {
					//list1 + (merged)
					//list2
					duplicates[dupPos] = list1[list1Pos] + " (merged)";
					dupPos++;
					duplicates[dupPos] = list2[list2Pos];
					temp[i] = list1[list1Pos];
					list1Pos++;
					list1Remaining--;
					list2Pos++;
					list2Remaining--;
					dupPos++;

				} else if (list1[list1Pos].compareTo(list2[list2Pos]) == -1) {

					temp[i] = list1[list1Pos];
					list1Pos++;
					list1Remaining--;

				} else {

					temp[i] = list2[list2Pos];
					list2Pos++;
					list2Remaining--;

				}

			} else if (list1Remaining > 0 && list2Remaining == 0) {

				temp[i] = list1[list1Pos];
				list1Pos++;
				list1Remaining--;

			} else if (list2Remaining > 0 && list1Remaining == 0) {

				temp[i] = list2[list2Pos];
				list2Pos++;
				list2Remaining--;

			}
			// If both lists finished before the end of loop it means that there
			// were duplicates and there is no more reason to stay in the loop.
			else
				break;

		} // End of for loop

		
		
		//--------------------------------------------------------------------------------
		
		for(int i = 0 ; i < temp.length -1 && temp[i+1] != null; i++){
			
			if(temp[i].equals(temp[i+1])){
				
				duplicates[dupPos] = temp[i] + " (merged)";
				dupPos++;
				duplicates[dupPos] = temp[i+1];
				dupPos++;
				
				for(int j = i+1 ; j < temp.length -1 ; j++){
					
					temp[j] = temp[j+1];
					
				}
				temp[temp.length -1 -i] = null;
				i--;
				
			}
			
		}
		
		saveListToTextFile(duplicates, duplicateFileName, true);

		// Check up if list3 has nulls.
		int lastPosWithValue = 0;

		for (; lastPosWithValue < temp.length; lastPosWithValue++) {

			if (temp[lastPosWithValue] == null) {
				break;
			}

		}

		Comparable[] list3 = (Comparable[]) Array.newInstance(temp.getClass().getComponentType(), lastPosWithValue);
		for (int i = 0; i < list3.length; i++)
			list3[i] = temp[i];

		return list3;

	}// End of merge method

	/**
	 * saves an object array to text files.
	 * 
	 * Precondition: All objects which will be sent to this method must have an
	 * overriden toString method.
	 * 
	 * @param objects
	 *            id the objects that the user want to save to text file
	 * @param fileName
	 *            name of the file to save the objects into.
	 * 
	 */
	public static void saveListToTextFile(Object[] objects, String fileName)
			throws FileNotFoundException, UnsupportedEncodingException {
		saveListToTextFile(objects, fileName, false, CHARACTER_ENCODING);

	}

	/**
	 * Lets the user to specify if he wants to append the objects into the file
	 * or to overwrite. Then calls the overloaded saveListToTextFile using this
	 * information.
	 * 
	 * Precondition: All objects must have an overriden toString method.
	 * 
	 * @param objects
	 *            Objects that need to be saved in fileName.
	 * @param fileName
	 *            The name of the file in which we need to save files into.
	 * @param append
	 *            If it is true it will append the Objects to the files instead
	 *            of overriding
	 * @throws FileNotFoundException
	 *             If the file does not exist
	 * @throws UnsupportedEncodingException
	 *             If the file does not support the encoding.
	 */
	public static void saveListToTextFile(Object[] objects, String fileName, boolean append)
			throws FileNotFoundException, UnsupportedEncodingException {

		saveListToTextFile(objects, fileName, append, CHARACTER_ENCODING);

	}

	/**
	 * Overloaded method which lets the user specify a custom encoding scheme
	 * and the choice to append the files or overwrite. Then saves the objects
	 * into a file they want.
	 * 
	 * Precondition: All objects must have an overriden toString method.
	 * 
	 * @param objects
	 *            Objects that need to be saved in the file
	 * @param filename
	 *            The name of the file in which this method will save the
	 *            objects.
	 * @param append
	 *            Boolean which represents if the file will be appended or not.
	 * @param characterEncoding
	 *            Character encoding scheme. THe default is UTF-8
	 * @throws FileNotFoundException
	 *             If the file does not exist.
	 * @throws UnsupportedEncodingException
	 *             If the file does not support the encoding scheme.
	 */
	public static void saveListToTextFile(Object[] objects, String filename, boolean append, Charset characterEncoding)
			throws FileNotFoundException, UnsupportedEncodingException {

		PrintWriter outputFile = null;

		try {

			File unsortedDir = new File("datafiles/unsorted");
			unsortedDir.mkdirs();

			FileOutputStream f = new FileOutputStream(filename, append);
			OutputStreamWriter out = new OutputStreamWriter(f, characterEncoding);
			outputFile = new PrintWriter(new BufferedWriter(out));

			for (Object obj : objects)
				if (obj != null)
					outputFile.println(obj);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("Error saving list! Unable to access the device " + filename);
		} finally {
			if (outputFile != null)
				outputFile.close();
		}

	}

	/**
	 * Sorts a list of objects in ascending natural order. Sorts by ramq.
	 * 
	 * Precondition: Assumes the list is not null and that the list's capacity
	 * is equal to lists's size,
	 * 
	 * @param list
	 *            A list of objects.Assumes that the list's capacity is equal to
	 *            the list's size.
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is not full to capacity.
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is null.
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list) throws IllegalArgumentException {

		// Checks if the Array is null.
		if (list == null)
			throw new IllegalArgumentException("ListUtilities.Sort() - Parameter can not be null");

		// Checks of the Array is full to capacity.
		for (int i = 0; i < list.length; i++)
			if (list[i] == null)
				throw new IllegalArgumentException("ListUtilities.Sort() - Parameter needs to be full to capacity");

		// By this point we know that the list is not null and full to capacity.
		Object temp;

		for (int i = 0; i < list.length - 1; i++) {
			for (int j = i + 1; j < list.length; j++) {
				if (list[i].compareTo(list[j]) == 1) {

					temp = list[j];
					list[j] = list[i];
					list[i] = (Comparable) temp;
					temp = null;
				}

			}
		}
	}

	/**
	 * Sorts a list of objects in the given order.
	 * 
	 * Precondition: Assumes that the list is not null and that the list's
	 * capacity is equal to the list's size.
	 * 
	 *
	 * @param list
	 *            A list of objects. Assumes that the list's capacity is equal
	 *            to the list's size.
	 * @param sortOrder
	 *            A Comparator object that defines the sort order
	 * 
	 * 
	 * @throws IllegalArgumentException
	 *             if the parameter is * not full to capacity.
	 *
	 * @throws NullPointerException
	 *             if the list or sortOrder * are null.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void sort(Comparable[] list, Comparator sortOrder)
			throws IllegalArgumentException, NullPointerException {
		// As specified, sorts using java.util.Arrays.sort overloaded 2 param
		// method.
		Arrays.sort(list, sortOrder);

	}


	/**
	 * Search for the String item in an array of Strings.
	 * 
	 * Precondition: Assumes the list is not null and that the list's capacity
	 * is equal to lists's size.
	 * 
	 * @param list
	 *            An array of String to search from.
	 * 
	 * @param item
	 *            The item to be searched for.
	 * 
	 * @throws IllegalArgumentException
	 *             if the String array contains null values.
	 * 
	 * 
	 */
	public static int binarySearch(String[] list, String item) {
		int length1 = list.length;
		int length2 = length1 / 2;
		int temp;
		for (int i = 0; i < list.length; i++) {
			if (list[i] == null) {
				throw new IllegalArgumentException(
						"Array must not contain null value(s) when using the binary search.");
			}
		}

		while (length2 >= 0 && length2 < list.length && (!(list[length2].toString()).matches(item))) {
			// Checks if during comparison, item should be in between two terms.
			if (length2 > 0) {
				if ((list[length2].compareTo(item) > 0) && (list[(length2) - 1]).compareTo(item) < 0) {
					return -1;
				}
			}
			if (list[length2].compareTo(item) > 0) {
				// To prevent infinite loops with subtracting zero or dividing
				// by zero.
				if (((length1 - length2) / 2) == 0) {
					length2--;
				} else {
					temp = length2;
					length2 -= (length1 - length2) / 2;
					length1 = temp;
				}
			} else {
				if (((length1 - length2) / 2) == 0) {
					length2++;
				} else {
					length2 += (length1 - length2) / 2;
				}
			}
		}

		// must check roundabout way to avoid out of bounds error
		if ((length2 >= 0) && (length2 < list.length)) {
			return length2;
		} else {
			return -1;
		}
	}

}
