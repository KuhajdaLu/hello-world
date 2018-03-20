package Ppa2;


import java.util.Arrays;
import java.util.Random;

interface ISortingAlgorithm {
	void sort(int[] data);	
	int copiesInLastSort();
	void onlySort(int[] data);
}

class InsertSort implements ISortingAlgorithm {
		
	int copies=0;
	
	public void sort(int[] data) {	
		copies = 0;									
		for (int i = 1;i<data.length;i++) {
			int v = data[i];
			copies++;
			int j = i-1;
			while((j>=0)&&(data[j]>v)) {				
				data[j+1] = data[j];				
				copies++;
				j--;
			}			
			data[j+1] = v;
			copies++;
		}
	}	
	
	public int copiesInLastSort() {
		return copies;
	}

	@Override
	public void onlySort(int[] data) {
		for (int i = 1;i<data.length;i++) {
			int v = data[i];
			int j = i-1;
			while((j>=0)&&(data[j]>v)) {				
				data[j+1] = data[j];			
				j--;
			}			
			data[j+1] = v;
		}
	}
}



class QuickSort implements ISortingAlgorithm{
	static int copies = 0;
	
	
	public void sort(int[] data) {
		copies = 0;
        int delka = data.length;
        quickSort(data,0, delka - 1);
    }
	
	public void quickSort(int[] data, int left, int right) {
		int i = left; 
        int j = right;
        int pivot = data[left + (right-left)/2];
			copies++;
        while (i <= j) {
            while (data[i] < pivot) {
                i++;
            }
            while (data[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swap(data,i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSort(data,left, j);
        if (i < right)
            quickSort(data,i, right);
    }
	
	
	public void swap(int  [] data, int left, int right) {
		int tmp = data[right];
		copies++;
		data[right]=data[left];
		copies++;
		data[left]=tmp;
		copies++;

	}
	
	public int copiesInLastSort() {
		return copies;
	}

	@Override
	public void onlySort(int[] data) {
        int delka = data.length;
        quickSortOnlySort(data,0, delka - 1);
	}
	
	public void quickSortOnlySort(int[] data, int left, int right) {
		int i = left; 
        int j = right;
        int pivot = data[left + (right-left)/2];
        while (i <= j) {
            while (data[i] < pivot) {
                i++;
            }
            while (data[j] > pivot) {
                j--;
            }
            if (i <= j) {
                swapOnlySort(data,i, j);
                i++;
                j--;
            }
        }
        if (left < j)
            quickSortOnlySort(data,left, j);
        if (i < right)
            quickSortOnlySort(data,i, right);
    }
	
	
	public void swapOnlySort(int  [] data, int left, int right) {
		int tmp = data[right];
		data[right]=data[left];
		data[left]=tmp;
	}
	
}

class MergeSort implements ISortingAlgorithm{
	int copies = 0;
	
    public void sort(int[] data) {
    	copies=0;
        int number = data.length;
        int [] aaa = new int[number];
        mergeSort(0, number - 1, data, aaa);
    }

    public void mergeSort(int left, int right, int [] data, int [] aaa) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(left, middle, data,aaa);
            mergeSort(middle + 1, right, data, aaa);
            merge(left, middle, right, data, aaa);
        }
    }

    public void merge(int left, int middle, int right, int[] data, int [] aaa) {
        for (int i = left; i <= right; i++) {
            aaa[i] = data[i];
            copies++;
        }
        int a = left;
        int b = middle + 1;
        int c = left;
        while (a <= middle && b <= right) {
            if (aaa[a] <= aaa[b]) {
                data[c] = aaa[a];
                copies++;
                a++;
            } else {
                data[c] = aaa[b];
                copies++;
                b++;
            }
            c++;
        }

        while (a <= middle) {
            data[c] = aaa[a];
            copies++;
            c++;
            a++;
        }

    }

	public int copiesInLastSort() {
		return copies;
	}

	@Override
	public void onlySort(int[] data) {
		int number = data.length;
        int [] aaa = new int[number];
        mergeSortOnlySort(0, number - 1, data, aaa);
	}
	
	public void mergeSortOnlySort(int left, int right, int [] data, int [] aaa) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSortOnlySort(left, middle, data,aaa);
            mergeSortOnlySort(middle + 1, right, data, aaa);
            mergeOnlySort(left, middle, right, data, aaa);
        }
    }
	
	public void mergeOnlySort(int left, int middle, int right, int[] data, int [] aaa) {
        for (int i = left; i <= right; i++) {
            aaa[i] = data[i];
        }
        int a = left;
        int b = middle + 1;
        int c = left;
        while (a <= middle && b <= right) {
            if (aaa[a] <= aaa[b]) {
                data[c] = aaa[a];
                a++;
            } else {
                data[c] = aaa[b];
                b++;
            }
            c++;
        }
        while (a <= middle) {
            data[c] = aaa[a];
            c++;
            a++;
        }
    }
}
 
class JavaSort implements ISortingAlgorithm{

	@Override
	public void sort(int[] data) {
		Arrays.sort(data);
	}

	@Override
	public int copiesInLastSort() {
		return 0;
	}

	@Override
	public void onlySort(int[] data) {
		Arrays.sort(data);
	}
	
}



public class A16B0424P_cv5 {

	public static void main(String[] args) {	
		System.out.println("InsertSort - neserazena data");
		ISortingAlgorithm algorithm = new InsertSort();
		if (testCorrectness(algorithm)) {	
			testCounts(algorithm);
		}
	
		
		System.out.println(" ");
		System.out.println("InsertSort - serazena data");
		ISortingAlgorithm algorithm1 = new InsertSort();
		if (testCorrectness(algorithm1)) {
			testCounts1(algorithm1);
		}
		
		
		System.out.println(" ");
		System.out.println("QuickSort - neserazena data");
		ISortingAlgorithm algorithm2 = new QuickSort();
		if (testCorrectness(algorithm2)) {		
			testCounts(algorithm2);
		}
		
		
		System.out.println(" ");
		System.out.println("QuickSort - serazena data");
		ISortingAlgorithm algorithm3 = new QuickSort();
		if (testCorrectness(algorithm3)) {		
			testCounts1(algorithm3);
		}
		
		
		System.out.println(" ");
		System.out.println("MergeSort - neserazena data");
		ISortingAlgorithm algorithm4 = new MergeSort();
		if (testCorrectness(algorithm4)) {		
			testCounts(algorithm4);
		}
		
		
		System.out.println(" ");
		System.out.println("MergeSort - serazena data");
		ISortingAlgorithm algorithm5 = new MergeSort();
		if (testCorrectness(algorithm5)) {		
			testCounts1(algorithm5);
		}
		
		
		System.out.println(" ");
		System.out.println("JavaSort - neserazena data");
		ISortingAlgorithm algorithm6 = new JavaSort();
		if (testCorrectness(algorithm6)) {		
			testCounts(algorithm6);
		}
		
		
		System.out.println(" ");
		System.out.println("JavaSort - serazena data");
		ISortingAlgorithm algorithm7 = new JavaSort();
		if (testCorrectness(algorithm7)) {		
			testCounts1(algorithm7);
		}
		
		System.out.println(" ");
		System.out.println("Doba, za kterou dany algoritmus srovna 10x posloupnost 100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600 a 51200 prvku");
		System.out.println("	InsertSort - neserazene pole: " + time(algorithm) + " ms");
		System.out.println("	InsertSort - serazene pole: " + time1(algorithm1) + " ms");
		System.out.println("	QuickSort - neserazene pole: " + time(algorithm2) + " ms");
		System.out.println("	QuickSort - serazene pole: " + time1(algorithm3) + " ms");
		System.out.println("	MergeSort - neserazene pole: " + time(algorithm4) + " ms");
		System.out.println("	MergeSort - serazene pole: " + time1(algorithm5) + " ms");
		System.out.println("	JavaSort - neserazene pole: " + time(algorithm6) + " ms");
		System.out.println("	JavaSort - serazene pole: " + time1(algorithm7) + " ms");
	}
	
	private static void testCounts1(ISortingAlgorithm algorithm) {
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 100;
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
			int minComp = Integer.MAX_VALUE;
			int maxComp = 0;
			for (int test = 0;test<TEST_COUNT;test++) {
				int[] data = generateSortedData(length);
				algorithm.sort(data);
				if (algorithm.copiesInLastSort()>maxComp)
					maxComp = algorithm.copiesInLastSort();
				if (algorithm.copiesInLastSort()<minComp)
					minComp = algorithm.copiesInLastSort();				
			}
			System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
			
			
		}		
	}

	private static void testCounts(ISortingAlgorithm algorithm) {
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 100;
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
			int minComp = Integer.MAX_VALUE;
			int maxComp = 0;
			for (int test = 0;test<TEST_COUNT;test++) {
				int[] data = generateData(length);
				algorithm.sort(data);
				if (algorithm.copiesInLastSort()>maxComp)
					maxComp = algorithm.copiesInLastSort();
				if (algorithm.copiesInLastSort()<minComp)
					minComp = algorithm.copiesInLastSort();				
			}
			System.out.println("Length: " + length + ", Min:" + minComp + ", Max:" + maxComp);
			
			
		}		
	}
	
	private static double time(ISortingAlgorithm algorithm) {
		long time = 0;
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 10;
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
			for(int test = 0;test<TEST_COUNT;test++) {
				int [] data = generateData(length);
				long start = System.nanoTime();
				algorithm.onlySort(data);
				long stop = System.nanoTime();
				time += (stop-start);
			}
		}
		return (double)((time/10000))/100;
	}

	private static double time1(ISortingAlgorithm algorithm) {
		long time = 0;
		int MIN_LENGTH = 100;
		int MAX_LENGTH = 100000;
		int TEST_COUNT = 10;
		for (int length = MIN_LENGTH;length<MAX_LENGTH;length*=2) {
			for(int test = 0;test<TEST_COUNT;test++) {
				int [] data = generateSortedData(length);
				long start = System.nanoTime();
				algorithm.onlySort(data);
				long stop = System.nanoTime();
				time += (stop-start);
			}
		}
		return (double)((time/10000))/100;	
	}

	private static boolean testCorrectness(ISortingAlgorithm algorithm) {
		for (int i = 0;i<100;i++) {
			int[] data = generateData(100);
			//int [] data= new int [] {5,4,3,2,1};
			int[] dataCopy = data.clone();
			algorithm.sort(data);
			//System.out.println(Arrays.toString(data));
			Arrays.sort(dataCopy);
			//System.out.println(Arrays.toString(dataCopy));

			for(int j = 0;j<data.length;j++) {
				if (data[j]!=dataCopy[j]) {				
					System.out.println("Algorithm failed, terminating.");
					return false;
				}
			}			
		}
		System.out.println("Algorithm passed test, continuing.");
		return true;
	}

	private static int[] generateData(int c) {
		int[] result = new int[c];
		Random rnd = new Random();
		for (int i = 0;i<c;i++)
			result[i] = rnd.nextInt(c);
		return result;
	}	
	
	private static int[] generateSortedData(int c) {
		Random rnd = new Random();
		int[] result = new int[c];
		for (int i = 0;i<c;i++)
			result[i] = rnd.nextInt(c);
		Arrays.sort(result);
		return result;
	}	
}