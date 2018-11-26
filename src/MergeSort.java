public class MergeSort {
    int arr[];

    MergeSort(int arr[]) {
        this.arr = arr;
    }

    private void merge(int lo1, int hi1, int lo2, int hi2) {
        int len = hi1+hi2+2-lo1-lo2;
        int merged_array[] = new int[len];

        for (int i = 0, j = lo1, k = lo2; i<len; i++) {
            if (j<=hi1 && k<=hi2 && arr[j]<=arr[k]) {
                merged_array[i] = arr[j];
                j++;
            } else if (j<=hi1 && k<=hi2 && arr[j]>arr[k]) {
                merged_array[i] = arr[k];
                k++;
            } else if (j>hi1 && k<=hi2) {
                merged_array[i] = arr[k];
                k++;
            } else if (j<=hi1 && k>hi2) {
                merged_array[i] = arr[j];
                j++;
            }
        }

        //System.out.print("Merging arrays result : " + Arrays.toString(merged_array));

        for (int i = Math.min(lo1, lo2); i<=Math.max(hi1, hi2); i++) {
            arr[i] = merged_array[i-Math.min(lo1, lo2)];
        }

        //System.out.println(" Array : " + Arrays.toString(arr));
    }

    void mergeSort (int lo, int hi) {
        if (lo >= hi)
            return;
        int mid = (lo+hi)/2;
        mergeSort(lo, mid);
        mergeSort(mid+1, hi);
        merge(lo, mid, mid+1, hi);
    }
}
