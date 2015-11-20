/**
 * Provides various Utilities.
 */
package group1.util;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Provides Utilities such as for serializing and deserializing objects.
 * 
 * @author Erika Bourque
 * @author Uen Yi Hung
 * @version 12/11/2015
 */
public class Utilities {

	/**
	 * Private constructor so that Utilities may not be instantiated.
	 * 
	 */
	private Utilities() {
	}

	/**
	 * Serializes an object.
	 * 
	 * @param object
	 *            The object to be serialized.
	 * @param fileSpecification
	 *            The file that the object will be serialized to.
	 * @throws IOException
	 */
	public static void serializeObject(Object object, String fileSpecification) throws IOException {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileSpecification));
			out.writeObject(object);
		} catch (IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error serializing object to \n" + fileSpecification + " " + e);
		} finally {
			if (out != null)
				out.close();
		}
	}

	/**
	 * Deserializes an object.
	 * 
	 * @param fileSpecification
	 *            the file where the object is located.
	 * @return the deserialized object.
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static Object deserializeObject(String fileSpecification) throws IOException, ClassNotFoundException {
		ObjectInputStream in = null;
		try {
			Object obj = null;
			in = new ObjectInputStream(new FileInputStream(fileSpecification));
			if (in != null)
				obj = in.readObject();
			return obj;
		} catch (ClassNotFoundException | IOException e) {
			// normally the exception would be logged to file then thrown
			throw new IOException("Error deserializing object from " + fileSpecification + "\n" + e);
		} finally {
			if (in != null)
				in.close();
		}
	}

	/**
	 * @throws IOException 
	 * Using Serialization to create deep copies. 
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	static <T> T copyOf(T obj) throws IOException, ClassNotFoundException {
	
			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			ObjectOutputStream objOut = new ObjectOutputStream(byteArrayOut);
			objOut.writeObject(obj);
			objOut.close();

			ByteArrayInputStream byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
			ObjectInputStream objIn = new ObjectInputStream(byteArrayIn);
			
			T deepCopy = (T) objIn.readObject();
			objIn.close();
			return deepCopy;
		
	}
}
