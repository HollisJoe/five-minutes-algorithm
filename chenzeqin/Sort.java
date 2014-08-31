package czq.dataStruct;

import java.util.Stack;

public class Sort {

	public static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	/****************************************************** 
	 * 
	 * ð������
	 * @param a
	 * 
	 ******************************************************/
	public static void bubbleSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len - 1; i++) {
			boolean exchange = false;
			for (int j = len - 1; j > i; j--) {
				if (a[j] < a[j - 1]) {
					swap(a, j, j - 1);
					exchange = true;
				}
			}
			if(!exchange) break;
		}
	}
	
	/****************************************************** 
	 * 
	 * �������� ���ݹ���ǵݹ飩
	 * @param a
	 * 
	 ******************************************************/
	public static void quickSort(int[] a, int start, int end) {
		if(start < end) {
			int pivotPosi = partition(a, start, end);
//	XXX		quickSort(a, start, (start + end) / 2);
//			quickSort(a, 1 + (start + end) / 2, end);
			quickSort(a, start, pivotPosi - 1);
			quickSort(a, pivotPosi + 1, end);
		}
	}
	
	public static void quickSortWithoutRecursion(int[] a, int start, int end) {
		Stack<Integer> stack = new Stack<Integer>(); 
		int mid, left = start, right = end;
		if (left < right) {
			mid = partition(a, left, right);
			stack.push(right);
			stack.push(mid + 1);
			stack.push(mid - 1);
			stack.push(left);
		}
		
		while (!stack.isEmpty()) {
			left = stack.pop();
			right = stack.pop();
			mid = partition(a, left, right);
			
			if (mid + 1 < right) {
				stack.push(right);
				stack.push(mid + 1);
			}
			
			if (left < mid - 1) {
				stack.push(mid - 1);
				stack.push(left);
			}
		}
	}
	
	public static int partition(int[] a, int start, int end) {
		int pivotVal = a[start];
		int left = start, right = end ;
		while(left < right) {
			while (pivotVal <= a[right] && right > left)	 
				right--;
			a[left] = a[right];
			while (pivotVal >= a[left] && left < right)		 
				left++;
			a[right] = a[left];
		}
		a[left] = pivotVal;
		return left;
	}
	
	/****************************************************** 
	 * 
	 * ֱ�Ӳ�������
	 * @param a
	 * 
	 ******************************************************/
	public static void insertSort(int[] a) {
		int len = a.length;
		int i, j;
		for (i = 1; i < len ; i++) {
			int tmp = a[i];
			for(j = i - 1; j >= 0 && tmp < a[j]; j--)
				a[j + 1] = a[j];
			a[j + 1] = tmp;
		}
	}
	
	/****************************************************** 
	 * 
	 * ѡ������
	 * @param a
	 * 
	 ******************************************************/
	public static void selectSort(int[] a) {
		int len = a.length;
		for (int i = 0; i < len; i++) {
			int minPos = i, minNum = a[i];
			for (int j = i + 1; j < len; j++) {
				if(minNum > a[j]) {
					minNum = a[j];
					minPos = j;
				}
			}
			swap(a, i, minPos);
		}
	}
	
	/****************************************************** 
	 * 
	 * �鲢����
	 * @param a
	 * 
	 ******************************************************/
	public static void mergeSort(int[] a, int start, int end) {	//XXX
		if (start < end) {
			int  mid = (start + end) / 2;
			mergeSort(a, start, mid);
			mergeSort(a, mid + 1, end);
			merge(a, start, mid, end);
		}
	}
	
	private static void merge(int[] a, int start, int mid, int end) {
		int lenLeft = mid - start + 1, lenRight = end - mid;
		int[] left = new int[lenLeft];
		int[] right = new int[lenRight];
		
		for(int k = 0; k < lenLeft; k++)
			left[k] = a[start + k];
		
		for(int k = 0; k < lenRight; k++)
			right[k] = a[mid + 1 + k];
		
		int i = 0, j = 0, k = start;
		while (i < lenLeft && j < lenRight) {
			if(left[i] < right[j])	
				a[k++] = left[i++];
			else 
				a[k++] = right[j++];
		}
		
		while (i < lenLeft)
			a[k++] = left[i++];
		
		while (j < lenRight)
			a[k++] = right[j++];
	}
	
	
	/****************************************************** 
	 * 
	 * ������(�����)
	 * @param a
	 * 
	 ******************************************************/
	public static void heapAdjust(int[] array, int start, int end) {
		int father = start, son;
		array[0] = array[father];								// Ϊ�˱�֤��ͨ���±��������ѵĸ��ӽڵ㣬��ʼλ����1��array[0]��temp
		for ( son = 2 * father; son <= end; son *= 2 ) {		// "<="
			if ( son < end && array[son] < array[son + 1]  )	// ��λ��son�ض���ڵ㣬�������һ��son==end����++����&&�ڶ����ж�array[son+1]Ҳ��ִ�в������ArrayIndexOutOfBoundsException
				son++;											// ��ѭ���ڣ����������ƶ�ָ��Ķ�������һ���жϸ������ı߽�����
			if ( array[0] >= array[son] )	 
				break;
			array[father] = array[son];
			father = son;
		}
		array[father] = array[0];
	}
	
	public static void heapSort(int[] a) 	{
    	int[] array = new int[a.length + 1];
    	for(int index = 1; index < array.length; index++)
    		array[index] = a[index - 1];
		
		int len = array.length;
		
		for(int i = len / 2; i >= 1; i--) {		// ���ɳ�ʼ���Ķ�
			heapAdjust(array, i, len - 1);
			System.out.println(array[1]);
		}
		
		for(int j = len - 1; j >= 1; j--) {
			swap(array, 1, j);
			
			heapAdjust(array, 1, j - 1);
		}
		
		for(int index = 1; index < array.length; index++)
			a[index - 1] = array[index];
	}
	
	
	public static void main(String[] args) {
//		int[] a = {5, 1, 9, 8, 3, 7, 4, 6, 2, 0};
		int[] a = {1, 5, 3,  4, 2};
//		int[] a = {50, 40, 95, 20, 15, 70, 60, 45, 80};
		
//		bubbleSort(a);
		
//		quickSort(a, 0, a.length - 1);
//		quickSortWithoutRecursion(a, 0, a.length - 1);
		
//		insertSort(a);
		
//		selectSort(a);
		
//		mergeSort(a, 0, a.length - 1);
		
//		kuaiPai(a, 0, a.length - 1);
		
		heapSort(a);
		
		for (int i : a)
			System.out.print(i + " ");
	}

}
