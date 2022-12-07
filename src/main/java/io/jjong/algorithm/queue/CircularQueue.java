package io.jjong.algorithm.queue;

import java.util.Arrays;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * create on 2022/12/07. create by IntelliJ IDEA.
 *
 * <p> 클래스 설명 </p>
 * <p> {@link } and {@link }관련 클래스 </p>
 *
 * @author Jongsang Han(Henry)
 * @version 1.0
 * @see
 * @since 1.0
 */
public class CircularQueue {
  private int head = 0, tail =0, numQueueElements = 0;
  private static final int SCALE_FACTOR = 2;
  private Integer[] entries;

  public CircularQueue(int capacity) {
    this.entries = new Integer[capacity];
  }

  public void enqueue(Integer x) {
    if (numQueueElements == entries.length) { // 크기 늘리기
      // 큐의 원소가 순서대로 나오도록 재조정 한다.
      Collections.rotate(Arrays.asList(entries), -head);

      // head와 tail을 재설정 한다.
      head = 0;
      tail = numQueueElements;
      entries = Arrays.copyOf(entries, numQueueElements *SCALE_FACTOR);
    }

    entries[tail] = x;
    tail = (tail + 1) % entries.length;
    ++numQueueElements;
  }

  public Integer dequeue() {
    if (numQueueElements != 0) {
      --numQueueElements;
      Integer ret = entries[head];
      head = (head + 1) % entries.length;
      return ret;
    }
    throw new NoSuchElementException("Dequeue called on an empty queue.");
  }

  public int size() {
    return numQueueElements;
  }

  public static void main(String[] args){
    CircularQueue queue = new CircularQueue(5);
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6); // 0, 6

    queue.dequeue(); // 1,6
    queue.enqueue(7); // 1,7
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    queue.dequeue(); // 5, 7
    queue.enqueue(11); // 5, 8
    queue.enqueue(12); // 5, 9
    queue.enqueue(13); // 5, 0
    queue.enqueue(14); // 5, 1

  }
}
