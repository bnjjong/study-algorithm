package io.jjong.algorithm.queue

import java.util.Collections
import java.util.NoSuchElementException

/**
 * 배열 기반 원형 큐. 가득 차면 원소를 순서대로 재정렬한 뒤 용량을 [SCALE_FACTOR]배로 늘린다.
 */
class CircularQueue(capacity: Int) {
    private var head = 0
    private var tail = 0
    private var numQueueElements = 0
    private var entries: Array<Int?> = arrayOfNulls(capacity)

    fun enqueue(x: Int) {
        if (numQueueElements == entries.size) { // 크기 늘리기
            // 큐의 원소가 순서대로 나오도록 재조정한다.
            Collections.rotate(entries.asList(), -head)
            // head와 tail을 재설정한다.
            head = 0
            tail = numQueueElements
            entries = entries.copyOf(numQueueElements * SCALE_FACTOR)
        }
        entries[tail] = x
        tail = (tail + 1) % entries.size
        ++numQueueElements
    }

    fun dequeue(): Int {
        if (numQueueElements != 0) {
            --numQueueElements
            val ret = entries[head]
            head = (head + 1) % entries.size
            return ret!!
        }
        throw NoSuchElementException("Dequeue called on an empty queue.")
    }

    fun size(): Int = numQueueElements

    companion object {
        private const val SCALE_FACTOR = 2
    }
}

fun main() {
    val queue = CircularQueue(5)
    queue.enqueue(1)
    queue.enqueue(2)
    queue.enqueue(3)
    queue.enqueue(4)
    queue.enqueue(5)
    queue.enqueue(6) // 0, 6

    queue.dequeue() // 1, 6
    queue.enqueue(7) // 1, 7
    queue.dequeue()
    queue.dequeue()
    queue.dequeue()
    queue.dequeue() // 5, 7
    queue.enqueue(11) // 5, 8
    queue.enqueue(12) // 5, 9
    queue.enqueue(13) // 5, 0
    queue.enqueue(14) // 5, 1
}
