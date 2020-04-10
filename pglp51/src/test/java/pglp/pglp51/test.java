package pglp.pglp51;

import lombok.Data;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class test

{
	private Personnels personne;

	@Before
	public void setUp() {
		personne = new Personnels.Builder(4, "BILL", " zemmoura").build();

	}

	@Test
	public void testInstance() throws IOException, ClassNotFoundException {
		byte[] serialized = serialize(personne);
		Object deserialized = deserialize(serialized);

		assertTrue(deserialized instanceof Personnels);
		assertEquals(personne.getNom(), ((Personnels) deserialized).getNom());
		assertEquals(personne.getPrenom(), ((Personnels) deserialized).getPrenom());
		assertEquals(personne.getIdPersonnels(), ((Personnels) deserialized).getIdPersonnels());
	}

	@Test
	public void testSerealise() {
		// Personnels personne=new Personnels.Builder(4, "BILL"," zemmoura").build();
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;

		try {
			final FileOutputStream fichierOut = new FileOutputStream("personne.ser");
			oos = new ObjectOutputStream(fichierOut);
			oos.writeObject(personne);
			oos.flush();
			final FileInputStream fichierIn = new FileInputStream("personne.ser");
			ois = new ObjectInputStream(fichierIn);
			Personnels personneDeserealisable = (Personnels) ois.readObject();
			//assertEquals(personne, personneDeserealisable);
			assertEquals(personne.getNom(), (personneDeserealisable).getNom());
			assertEquals(personne.getPrenom(), ( personneDeserealisable).getPrenom());
			assertEquals(personne.getIdPersonnels(), personneDeserealisable.getIdPersonnels());
			
			System.out.println("Personne : ");
			System.out.println("nom : " + personne.getNom());
			System.out.println("prenom : " + personne.getPrenom());
			System.out.println("l'identifiant : : " + personne.getIdPersonnels());
			System.out.println("numéro de tél : " + personne.getTel());
		} catch (final java.io.IOException e) {
			e.printStackTrace();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (oos != null) {
					oos.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	private static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(b);
		o.writeObject(obj);
		return b.toByteArray();
	}

	private static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream b = new ByteArrayInputStream(bytes);
		ObjectInputStream o = new ObjectInputStream(b);
		return o.readObject();
	}

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	@Test
	public void afficheTest() {
		Date date = new Date();
		Personnels p4 = new Personnels.Builder(4, "BILL", " zemmoura").build();
		Personnels p1 = new Personnels.Builder(1, " BILL", "zemmoura").build();
		Personnels p5 = new Personnels.Builder(5, "BILL", "zemmoura").build();
		CompositePersonnels p3 = new CompositePersonnels(3);
		CompositePersonnels p2 = new CompositePersonnels(2);
		p2.add(p4);
		p2.add(p5);

		p3.add(p1);
		p3.add(p2);

		AffichageGroupe a = new AffichageGroupe(p3);
		Iterator i = a.getIterator();
		while (i.hasNext()) {

			i.Next().afficher();
		}

	}

}
