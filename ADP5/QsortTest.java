import org.junit.Assert;
import org.junit.Test;

public class QsortTest {
	Qsort test = new Qsort();

	@SuppressWarnings("rawtypes")
	Element[] input1 = { new Element<String>(1), new Element<String>(3), new Element<String>(4), new Element<String>(1),
			new Element<String>(4), new Element<Integer>(5), new Element<Integer>(1) };

	@SuppressWarnings("rawtypes")
	Element[] Erwartung = { new Element<String>(1), new Element<String>(1), new Element<String>(1),
			new Element<String>(3), new Element<String>(4), new Element<Integer>(4), new Element<Integer>(5) };

	@SuppressWarnings("rawtypes")
	Element[] input2 = input1.clone();
	@SuppressWarnings("rawtypes")
	Element[] input3 = input1.clone();

	@Test
	public void qsortMedianTest() {
		test.quickSort(input1, PivotPosition.MEDIAN);
		for (int i = 0; i < input1.length; i++) {
			int eq = 0;
			if (input1[i] != Erwartung[i]) {
				eq = -1;
				equals(eq == 0);
			}
		}
	}

	@Test
	public void qsortEndTest() {
		test.quickSort(input2, PivotPosition.END);
		for (int i = 0; i < input2.length; i++) {
			int eq = 0;
			if (input2[i] != Erwartung[i]) {
				eq = -1;
				equals(eq == 0);
			}
		}
	}

	@Test
	public void qsortRandomTest() {
		test.quickSort(input3, PivotPosition.RANDOM);

		for (int i = 0; i < input3.length; i++) {
			int eq = 0;
			if (input3[i] != Erwartung[i]) {
				eq = -1;
				equals(eq == 0);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void qsortMedianInputLeerTest() {
		Element[] noSortArray = new Element[0];
		try {
			test.quickSort(noSortArray, PivotPosition.BEGIN);
			Assert.assertTrue("Fehler: Es wurde keine Exception geworfen!", false);

		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}
	}
}