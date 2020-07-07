package intermediate;

import static com.sandwich.util.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Logger;

import com.sandwich.koan.Koan;


public class AboutSerialization {
	
	@Koan
	public void simpleSerialization() throws FileNotFoundException, IOException, ClassNotFoundException {
		String s = "Hello world";
		
		File file = new File("SerializeFile");	// create a file with filename
		file.deleteOnExit();
		
		// serialize
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(s);	// Write the an object to this ObjectOutputStream
		os.close();
		
		// deserialize
		ObjectInputStream is = null;
		
		try {
			// Creates a FileInputStream by opening a connection to an actual file with filename
			is = new ObjectInputStream(new FileInputStream("SerializeFile"));
			
			String otherString = (String) is.readObject();
			
			assertEquals(otherString, "Hello world");
			
		}
		finally {
			closeStream(is);
		}
	}
	
	static class Starship implements Serializable {		// Starship obj can be serialized
		
		// Although it is not enforced, you should define this constant
		// to make sure you serialize/deserialize only compatible versions
		// of your objects
		private static final long serialVersionUID = 1L;	// serialize/deserialize on the same version
		
		int maxWarpSpeed;
	}
	
	@Koan
	public void customObjectSerialization() throws IOException, ClassNotFoundException {
		Starship s = new Starship();
		s.maxWarpSpeed = 9;
		
		// a file called "SerializeFile" in project folder store the binary obj
		File file = new File("SerializeFile");
		file.deleteOnExit();
		
		// serialize
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(s);
		os.close();
		
		ObjectInputStream is = null;
		
		try {
			is = new ObjectInputStream(new FileInputStream("SerializeFile"));
			
			Starship onTheOtherSide = (Starship) is.readObject();
			
			assertEquals(onTheOtherSide.maxWarpSpeed, 9);
		}
		finally {
			closeStream(is);
		}
	}
	
	static class Engine {
		String type;
		
		public Engine(String t) {
			type = t;
		}
	}
	
	@SuppressWarnings("serial")
	static class Car implements Serializable {
		
		// Transient means: Ignore field for serialization
		transient Engine engine;		// Engine class is not serializable
		
		// Notice these methods are private and will be called by the JVM internally
		// - as if they are defined by the Serializable interface
		// but they aren't defined as part of the interface
		private void writeObject(ObjectOutputStream os) throws IOException {
			os.defaultWriteObject();
			os.writeObject(engine.type);
		}
		
		private void readObject(ObjectInputStream is) throws IOException, ClassNotFoundException {
			is.defaultReadObject();
			engine = new Engine((String) is.readObject());
		}
	}
	
	@Koan
	public void customObjectSerializationWithTransientFields() throws FileNotFoundException, IOException, ClassNotFoundException {
		// Note that this kind of access of fields is not good OO practice.
		// But let's focus on serialization here :)
		
		Car car = new Car();
		car.engine = new Engine("diesel");
		
		File file = new File("SerializeFile");
		file.deleteOnExit();
		
		// serialize
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(car);		// call line 108 above
		os.close();
		
		ObjectInputStream is = null;
		
		try {
			is = new ObjectInputStream(new FileInputStream("SerializeFile"));
			Car deserializedCar = (Car) is.readObject(); // call line 113 above
			assertEquals(deserializedCar.engine.type, "diesel");
		}
		finally {
			closeStream(is);
		}
	}
	
	@SuppressWarnings("serial")
	class Boat implements Serializable {
		Engine engine;	// Engine class is not serializable
	}
	
	@Koan
	public void customSerializationWithUnserializableFields() throws FileNotFoundException, IOException {
		Boat boat = new Boat();
		boat.engine = new Engine("diesel");
		
		File file = new File("SerializeFile");
		file.deleteOnExit();
		
		// serialize
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		String marker = "Start ";
		
		try {
			os.writeObject(boat);
		}
		catch (NotSerializableException e) {
			marker += "Exception";
		}
		
		os.close();
		
		assertEquals(marker, "Start Exception");
	}
	
	@SuppressWarnings("serial")
	static class Animal implements Serializable {
		String name;
		
		public Animal(String s) {
			name = s;
		}
	}
	
	@SuppressWarnings("serial")
	static class Dog extends Animal {
		public Dog(String s) {
			super(s);
		}
	}
	
	@Koan
	public void serializeWithInheritance() throws IOException, ClassNotFoundException {
		Dog d = new Dog("snoopy");
		
		File file = new File("SerializeFile");
		file.deleteOnExit();
		
		// serialize
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
		os.writeObject(d);
		os.close();
		
		ObjectInputStream is = null;
		
		try {
			is = new ObjectInputStream(new FileInputStream("SerializeFile"));
			Dog otherDog = (Dog) is.readObject();
			
			assertEquals(otherDog.name, "snoopy");
		}
		finally {
			closeStream(is);
		}
	}
	
	static class Plane { // is Not Serializable
		String name;
		
		public Plane(String s) {
			name = s;
		}
		
		public Plane() {
		}
		
	}
	
	@SuppressWarnings("serial")
	static class MilitaryPlane extends Plane implements Serializable {	// // is Serializable
		public MilitaryPlane(String s) {
			super(s);
		}
	}
	
	@Koan
	public void serializeWithInheritanceWhenParentNotSerializable() throws FileNotFoundException, IOException, ClassNotFoundException {
		MilitaryPlane p = new MilitaryPlane("F22");
		
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("SerializeFile"));
		os.writeObject(p);
		os.close();
		
		ObjectInputStream is = null;
		
		try {
			is = new ObjectInputStream(new FileInputStream("SerializeFile"));
			MilitaryPlane otherPlane = (MilitaryPlane) is.readObject();
			
			// Does this surprise you?
			assertEquals(otherPlane.name, null);		// otherPlane.name in parent class, is Not Serializable
			
			// Think about how serialization creates objects...
			// It does not use constructors! if a parent object is not serializable
			// it actually uses constructors and if the fields are not in a serializable class...
			// unexpected things - like this - may happen
		}
		finally {
			closeStream(is);
		}
	}
	
	private void closeStream(ObjectInputStream ois) {
		if (ois != null) {
			try {
				ois.close();
			}
			catch (IOException x) {
				Logger.getAnonymousLogger().severe("Unable to close reader.");
			}
		}
	}
	
}

