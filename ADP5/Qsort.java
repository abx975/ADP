
public class Qsort {
	static long counter = 0;

	/**
	 * Diese Methode tauscht das Element an der Position left und right
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	private static void swap(Element<?>[] array, int left, int right) {
		Element<?> tmp = array[left];
		array[left] = array[right];
		array[right] = tmp;
	}

	/**
	 * Diese Methode berechnet den Median Wert
	 *
	 * @param array
	 * @param left
	 * @param right
	 * @return Element<?>
	 */

	private static Element<?> median(Element<?>[] array, int left, int right) {
		int middle = left + (right - left) / 2;

		if (array[left].getKey() > array[middle].getKey() ^ array[left].getKey() > array[right].getKey()) {
			return array[left];
		}
		if (array[right].getKey() > array[middle].getKey() ^ array[right].getKey() > array[left].getKey()) {
			return array[right];
		}
		return array[middle];
	}

	/**
	 * Diese Methode wählt das pivotElement aus
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @param pivotPosition
	 * @return pivotElement
	 */
	private Element<?> pivotAuswahl(Element<?>[] array, int left, int right, PivotPosition pivotPosition) {
		Element<?> pivot;
		switch (pivotPosition) {
		case BEGIN:
			pivot = array[left];
			break;
		case END:
			pivot = array[right];
			break;
		case RANDOM:
			pivot = array[(int) (Math.random() * (right - left)) + left];
			break;
		case MEDIAN:
			pivot = median(array, left, right);
			if (pivot == array[left]) {
				swap(array, left, (left + right) / 2);
			}
			if (pivot == array[right]) {
				swap(array, right, (left + right) / 2);
			}

			break;
		default:
			pivot = array[(left + right) / 2];
		}
		return pivot;
	}

	/**
	 * Diese Methode sortiert alle Elementen von Position leftPosition bis
	 * rightP in dem Array Pivotelement kann das erste, letzte Element sein,
	 * sowie der Median oder ein zufalliges Element
	 * 
	 * @param array
	 * @param leftPosition
	 * @param rightPosition
	 * @param pivotPosition
	 */
	public void quickS(Element<?>[] array, int leftPosition, int rightPosition, PivotPosition pivotPosition) {
		//counter++;
		int left = leftPosition;
		int right = rightPosition;
		Element<?> pivot = pivotAuswahl(array, left, right, pivotPosition);
		while (left < right) {
			while (array[left].getKey() < pivot.getKey()) {
				left++;
			}
			while (array[right].getKey() > pivot.getKey()) {
				right--;
			}
			if (left <= right) {
				swap(array, left, right);
				left++;
				right--;

			}
		}
		if (left < rightPosition) {
			quickS(array, left, rightPosition, pivotPosition);
		}
		if (right > leftPosition) {
			quickS(array, leftPosition, right, pivotPosition);
		}

	}

	/**
	 * Diese Methode sortiert das Array
	 * 
	 * @param array
	 * @throws IndexOutOfBoundsException
	 *             wenn das Array 0 oder 1 Element enthält
	 */
	public void quickSort(Element<?>[] array, PivotPosition pivotPosition) throws IndexOutOfBoundsException {
		//counter++;
		if (array.length <= 1) {
			throw new IndexOutOfBoundsException("Array ist nicht zu sortiern. Array hat null oder 1 Element");
		}
		int left = 0;
		int right = array.length - 1;
		quickS(array, left, right, pivotPosition);
	}

	@SuppressWarnings("rawtypes")
	public static void main(String a[]) {
		int size = 0;
		int maxExp = 5;
		int numberOfRuns = 15;
		long timeEnd = 0;
		long timeStart = 0;
		Qsort Aufwandsanalyse = new Qsort();
		
		System.out.println("RandomKeyValue");
		System.out.println("PivotPosition= END");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			for (int i = 0; i < numberOfRuns; i++) {

				Element[] input = new Element[size];
				for (int j = 0; j < size; j++) {
					input[j] = new Element<String>((int) (Math.random() * j));
				}
				timeStart = System.nanoTime();
				Aufwandsanalyse.quickSort(input, PivotPosition.END);
				timeEnd = System.nanoTime();
			}
//			System.out.println("n^" + exp + "=\t" + counter / numberOfRuns);
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart)/numberOfRuns + "\t");

		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\nPivotPosition= RANDOM");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			for (int i = 0; i < numberOfRuns; i++) {
				Element[] input = new Element[size];
				for (int j = 0; j < size; j++) {
					input[j] = new Element<String>((int) (Math.random() * j));
				}
				timeStart = System.nanoTime();
				Aufwandsanalyse.quickSort(input, PivotPosition.RANDOM);
				timeEnd = System.nanoTime();
			}
//			System.out.println("n^" + exp + "=\t" + counter / numberOfRuns);
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart)/numberOfRuns + "\t");
		}

		System.out.println("\nPivotPosition= MEDIAN");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;
			for (int i = 0; i < numberOfRuns; i++) {
				Element[] input = new Element[size];
				for (int j = 0; j < size; j++) {
					input[j] = new Element<String>((int) Math.random() * j);
				}
				timeStart = System.nanoTime();
				Aufwandsanalyse.quickSort(input, PivotPosition.MEDIAN);
				timeEnd = System.nanoTime();
			}
//			System.out.println("n^" + exp + "=\t" + counter / numberOfRuns);
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart)/numberOfRuns + "\t");

		}

		////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\nAscendingKeyValue");
		System.out.println("PivotPosition= END");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			Element[] input = new Element[size];
			for (int j = 0; j < size; j++) {
				input[j] = new Element<String>(j);
			}
			timeStart = System.nanoTime();
			Aufwandsanalyse.quickSort(input, PivotPosition.END);
			timeEnd = System.nanoTime();

//			System.out.println("n^" + exp + "=\t" + counter );
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart) + "\t");
		}

		System.out.println("\nPivotPosition= RANDOM");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			for (int i = 0; i < numberOfRuns; i++) {
				Element[] input = new Element[size];
				for (int j = 0; j < size; j++) {
					input[j] = new Element<String>(j);
				}
				timeStart = System.nanoTime();
				Aufwandsanalyse.quickSort(input, PivotPosition.RANDOM);
				timeEnd = System.nanoTime();
			}
//			System.out.println("n^" + exp + "=\t" + counter / numberOfRuns);
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart)/numberOfRuns + "\t");

		}

		System.out.println("\nPivotPosition= MEDIAN");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			Element[] input = new Element[size];
			for (int j = 0; j < size; j++) {
				input[j] = new Element<String>(j);
			}
			timeStart = System.nanoTime();
			Aufwandsanalyse.quickSort(input, PivotPosition.MEDIAN);
			timeEnd = System.nanoTime();

//			System.out.println("n^" + exp + "=\t" + counter );
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart) + "\t");

		}
		////////////////////////////////////////////////////////////////////////////////////////
		System.out.println("\nDescendingKeyValue");
		System.out.println("PivotPosition= END");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			Element[] input = new Element[size];
			for (int j = 0; j < size; j++) {
				input[j] = new Element<String>(input.length - j);
			}
			timeStart = System.nanoTime();
			Aufwandsanalyse.quickSort(input, PivotPosition.END);
			timeEnd = System.nanoTime();

//			System.out.println("n^" + exp + "=\t" + counter );
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart) + "\t");

		}

		System.out.println("\nPivotPosition= RANDOM");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			for (int i = 0; i < numberOfRuns; i++) {
				Element[] input = new Element[size];
				for (int j = 0; j < size; j++) {
					input[j] = new Element<String>(input.length - j);
				}
				timeStart = System.nanoTime();
				Aufwandsanalyse.quickSort(input, PivotPosition.RANDOM);
				timeEnd = System.nanoTime();
			}
//			System.out.println("n^" + exp + "=\t" + counter / numberOfRuns);
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart)/numberOfRuns + "\t");

		}

		System.out.println("\nPivotPosition= MEDIAN");
		for (int exp = 1; exp <= maxExp; exp++) {
			size = (int) Math.pow(10, exp);
			counter = 0;

			Element[] input = new Element[size];
			for (int j = 0; j < size; j++) {
				input[j] = new Element<String>(input.length - j);
			}
			timeStart = System.nanoTime();
			Aufwandsanalyse.quickSort(input, PivotPosition.MEDIAN);
			timeEnd = System.nanoTime();

//			System.out.println("n^" + exp + "=\t" + counter );
			System.out.println("n^" + exp + "\t" + (timeEnd - timeStart) + "\t");

		}

		////////////////////////////////////////////////////////////////////////////////////////
		/**
		 * qsortMedian
		 */
		// for (Element elem : input1) {
		// System.out.print(elem.getKey() + " ");
		// }
		// System.out.println();
		// for (int i = 0; i < 50; i++) {
		// test.quickSort(input100, PivotPosition.BEGIN);
		// for (Element elem : input1) {
		// System.out.print(elem.getKey() + " ");
		// }
	}
}
