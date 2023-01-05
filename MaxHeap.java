import java.util.ArrayList;
class MaxHeap {
  ArrayList<Integer> heap;
  int maxSize;
  int heapSize;

  //initialize empty max heap
  public MaxHeap(int maxSize) {
    this.maxSize = maxSize;
    heap = new ArrayList<Integer>(maxSize);
    this.heapSize = 0;
  }

  //get parent's index
  public int parentIdx(int current) {
    return (current - 1) / 2;
  }

  //get left child's index
  public int leftChildIdx(int current) {
    return (current * 2) + 1;
  }

  //get right child's index
  public int rightChildIdx(int current) {
    return (current * 2) + 2;
  }

  //swap elements between two indices
  public void swap (int first, int second) {
    int temp = heap.get(first);
    heap.set(first, heap.get(second));
    heap.set(second, temp);
  }

  //insert new element into the heap
  public void insert(int element) {
    heap.add(heapSize, element);
    //ensure max heap property is maintained
    int current = heapSize;
    while(heap.get(current) > heap.get(parentIdx(current))) {
      swap(current, parentIdx(current));
      current = parentIdx(current);
    }
    heapSize++;
  }

  //check if element is at a leaf node
  public boolean isLeafNode(int current) {
    if(current > (heapSize / 2) && current <= heapSize)
      return true;
    return false;
  }

  //function to maintain max heap property after removal of max element/root node
  public void heapify(int current) {
    if(isLeafNode(current))
      return;

    if(heap.get(current) < heap.get(leftChildIdx(current)) || heap.get(current) < heap.get(rightChildIdx(current))) {
      if(heap.get(leftChildIdx(current)) > heap.get(rightChildIdx(current))) {
        swap(current, leftChildIdx(current));
        heapify(leftChildIdx(current));
      }
      else {
        swap(current, rightChildIdx(current));
        heapify(rightChildIdx(current));
      }
    }
  }

  //remove max element from heap
  public int popMax() {
    int max = heap.get(0);
    heap.set(0, heap.get(--heapSize));
    heapify(0);
    return max;
  }

  //display the heap
  public void display() {
    for (int i = 0; i < heapSize / 2; i++) {
      System.out.print("Parent: " + heap.get(i) + " ");
      if(leftChildIdx(i) < heapSize)
        System.out.print("Left child: " + heap.get(leftChildIdx(i)) + " ");
      if(rightChildIdx(i) < heapSize)
        System.out.print("Right child: " + heap.get(rightChildIdx(i)));
      System.out.println();
    }
  }

  public static void main(String args[]) {
    MaxHeap m = new MaxHeap(10);

    m.insert(6);
    m.insert(16);
    m.insert(5);
    m.insert(64);
    m.insert(3);
    m.insert(7);
    m.insert(98);
    m.insert(12);
    m.insert(47);
    m.insert(2);

    m.display();
    System.out.println("max element: " + m.popMax());
    m.display();
  }
}
