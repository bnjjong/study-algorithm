package io.jjong.algorithm.day2

/**
 * LeetCode #146 — LRU Cache (Medium)
 *
 * 용량 [capacity]의 LRU(Least Recently Used) 캐시. `get`/`put` 모두 O(1)을 목표로 한다.
 *
 * 핵심: HashMap(키→노드 O(1) 조회) + 이중 연결 리스트(사용 순서 유지, 양끝 O(1) 삽입/삭제)를
 * 결합한다. 어느 한쪽만으로는 O(1)을 못 맞춘다 — 맵만 쓰면 순서를 모르고, 리스트만 쓰면 조회가 O(N).
 *
 * Day 1의 linked list 포인터 조작(splice, Dummy 노드)이 그대로 쓰인다.
 */
class LRUCache(private val capacity: Int) {
    private class Node(val key: Int, var value: Int) {
        var prev: Node? = null
        var next: Node? = null
    }

    private val map = HashMap<Int, Node>()
    private val head = Node(0, 0)
    private val tail = Node(0, 0)

    init {
        // 서로 연결한다.
        head.next = tail // head -> tail
        tail.prev = head // head <- tail
    }

    /** 키가 있으면 값을 반환하며 "최근 사용"으로 갱신하고, 없으면 -1을 반환한다. */
    fun get(key: Int): Int {
        val node = map[key] ?: return -1
        moveToFront(node)
        return node.value
    }

    /** 키를 삽입하거나 값을 갱신한다. 용량을 초과하면 가장 오래 안 쓴 노드를 제거한다. */
    fun put(key: Int, value: Int) {
        // 키가 존재 하는지?
        val existing = map[key]
        if (existing != null) {
            // 값을 변경하고
            existing.value = value
            // 가장 앞으로 옮겨 주고 끝!
            moveToFront(existing)
            return
        }
        // 사이즈를 벗어난다면?
        if (map.size == capacity) {
            val lru = tail.prev!!
            remove(lru)
            map.remove(lru.key)
        }
        val newOne = Node(key, value)
        map[key] = newOne
        addToFront(newOne)
    }

    private fun moveToFront(node: Node) {
        remove(node)
        addToFront(node)
    }

    private fun addToFront(node: Node) {
        // head 로 이동.!
        node.next = head.next // 가장 처음에 있던 노드를 next 로 붙임.
        node.prev = head //처음으로 이동
        head.next!!.prev = node // 가장 처음에 있던 노드 prev에 현재 노드를 붙임.
        head.next = node // 가장 처음으로 옮김!
    }

    private fun remove(node: Node) {
        // 본인은 빠지고, 전 후 에 있는 노드를 연결하는 작업.
        node.prev!!.next = node.next
        node.next!!.prev = node.prev
    }
}
