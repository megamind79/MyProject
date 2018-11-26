package MyHelperClasses;

import java.util.ArrayList;
import java.util.Scanner;

public class Heap {
    private ArrayList<Integer> heap_elements;
    private boolean is_min_heap;

    private Heap(boolean is_min_heap) {
        heap_elements = new ArrayList<>();
        this.is_min_heap = is_min_heap;
    }

    public static Heap createMinHeap() {
        return new Heap(true);
    }

    public static Heap createMaxHeap() {
        return new Heap(false);
    }

    @Override
    public String toString() {
        String string = "";
        int l = 1, i = 0;
        for (Integer heap_element : heap_elements) {
            string += heap_element;
            i++;
            if (i == l) {
                string += "\n";
                l *= 2;
                i = 0;
            }
            else
                string += " ";
        }
        return string;
        //return heap_elements.toString();
    }

    public int getHeapTop () {
        return heap_elements.get(0);
    }

    private int searchElement (int data) {
        int index = 0;
        for (Integer heap_element : heap_elements) {
            if (heap_element == data)
                return index;
            index++;
        }
        return -1;
    }

    private int getParentIndex (int child_index) {
        if (child_index%2 == 0)
            child_index--;
        int parent_index = (child_index-1)/2;
        return parent_index;
        //return child_index%2 == 0?((child_index-1)/2):(child_index/2);
    }

    private void bubbleUp(int child_index, int parent_index) {
        if (parent_index<0 || child_index<0)
            return;
        if ((is_min_heap && heap_elements.get(child_index)<= heap_elements.get(parent_index)) || (!is_min_heap && heap_elements.get(child_index)>= heap_elements.get(parent_index))) {
            int tmp = heap_elements.get(parent_index);
            heap_elements.set(parent_index, heap_elements.get(child_index));
            heap_elements.set(child_index, tmp);
            int grandparent_index = getParentIndex(parent_index);
            bubbleUp(parent_index, grandparent_index);
        }
    }

    public void addToHeap (int data) {
        int child_index = heap_elements.size();
        heap_elements.add(data);
        int parent_index = getParentIndex(child_index);
        bubbleUp(child_index, parent_index);
    }

    private void bubbleDown(int start_index) {
        int left_child = 2*start_index+1, right_child = 2*start_index+2;
        if (heap_elements.size()<=left_child)
            return;
        if ((is_min_heap && (heap_elements.get(start_index)>heap_elements.get(left_child) ||
                (heap_elements.size()>right_child && heap_elements.get(start_index)>heap_elements.get(right_child)))) ||
                (!is_min_heap && (heap_elements.get(start_index)<heap_elements.get(left_child) ||
                        (heap_elements.size()>right_child && heap_elements.get(start_index)<heap_elements.get(right_child))))) {
            if ((is_min_heap && (heap_elements.size() <= right_child || heap_elements.get(left_child)<heap_elements.get(right_child))) ||
                    (!is_min_heap && (heap_elements.size() <= right_child || heap_elements.get(left_child)>heap_elements.get(right_child)))) {
                int tmp = heap_elements.get(left_child);
                heap_elements.set(left_child, heap_elements.get(start_index));
                heap_elements.set(start_index, tmp);
                bubbleDown(left_child);
            } else if ((is_min_heap && (heap_elements.size() > right_child && heap_elements.get(left_child)>heap_elements.get(right_child))) ||
                    (!is_min_heap && (heap_elements.size() > right_child && heap_elements.get(left_child)<heap_elements.get(right_child)))){
                int tmp = heap_elements.get(right_child);
                heap_elements.set(right_child, heap_elements.get(start_index));
                heap_elements.set(start_index, tmp);
                bubbleDown(right_child);
            }
        }
    }

    public int removeMinMax() {
        int top = getHeapTop();
//        int last_child = heap_elements.size()-1;
//        heap_elements.set(0, heap_elements.get(last_child));
//        heap_elements.remove(last_child);
//        bubbleDown(0);
        removeData(top);

        return top;
    }

    public boolean removeData(int data) {
        int data_index = searchElement(data);
        if (data_index < 0)
            return false;
        int last_child = heap_elements.size()-1;
        heap_elements.set(data_index, heap_elements.get(last_child));
        heap_elements.remove(last_child);
        bubbleDown(data_index);

        return true;
    }
}
