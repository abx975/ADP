
public class Qsort {

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
	 * Diese Methode berechnet Median Wert
	 *
	 * @param array
	 * @param left
	 * @param right
	 * @return Element<?>
	 */

	private static Element<?> median(Element<?>[] array, int left, int right) {
		int middle = left + (right - left) / 2;
		// order the first and the middle
		if (array[left].getKey() > array[middle].getKey() ^ array[left].getKey() > array[right].getKey()) {
			return array[left];
		}
		// oder the first and the last
		if (array[right].getKey() > array[middle].getKey() ^ array[right].getKey() > array[left].getKey()) {
			return array[right];
			// oder the last with the middle
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
	 * Diese Methode sortiert alle Elementen von Position leftPosition bis rightP in
	 * dem Array Pivotelement kann das erste, letzte
	 * Element sein, sowie der Median oder ein zufalliges Element
	 * 
	 * @param array
	 * @param leftPosition
	 * @param rightPosition
	 * @param pivotPosition
	 */
	public void quickS(Element<?>[] array, int leftPosition, int rightPosition, PivotPosition pivotPosition) {
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
		if (array.length <= 1) {
			throw new IndexOutOfBoundsException("Array ist nicht zu sortiern. Array hat null oder 1 Element");
		}
		int left = 0;
		int right = array.length - 1;
		quickS(array, left, right, pivotPosition);
	}

	@SuppressWarnings("rawtypes")
	public static void main(String a[]) {
		Element[] input1 = { new Element<String>(1), new Element<String>(8), new Element<String>(3),
				new Element<String>(5), new Element<String>(-8), new Element<Integer>(7), new Element<Integer>(8), };

		Element[] input2 = input1.clone();
		Element[] input3 = input1.clone();
		Qsort test = new Qsort();

		/**
		 * qsortMedian
		 */
		for (Element elem : input1) {
			System.out.print(elem.getKey() + " ");
		}
		System.out.println();
		test.quickSort(input1, PivotPosition.MEDIAN);
		for (Element elem : input1) {
			System.out.print(elem.getKey() + " ");
		}

		/**
		 * qsortEnd
		 */
		test.quickSort(input2, PivotPosition.END);

		/**
		 * qsortRandom
		 */
		test.quickSort(input3, PivotPosition.RANDOM);
	}
}
