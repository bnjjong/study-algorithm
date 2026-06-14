package io.jjong.algorithm.search

/**
 * 1268. Search Suggestions System                                     Medium
 * ---------------------------------------------------------------------------
 * You are given an array of strings `products` and a string `searchWord`.
 *
 * Design a system that suggests at most three product names from `products`
 * after each character of `searchWord` is typed. Suggested products must
 * share a common prefix with the typed portion of `searchWord`. If more than
 * three products share that prefix, return the three lexicographically
 * smallest ones.
 *
 * Return a list of lists; the i-th list holds the suggestions after the first
 * (i + 1) characters of `searchWord` have been typed.
 *
 * Example 1:
 *   Input:  products = ["mobile","mouse","moneypot","monitor","mousepad"],
 *           searchWord = "mouse"
 *   Output: [
 *     ["mobile","moneypot","monitor"],   // "m"
 *     ["mobile","moneypot","monitor"],   // "mo"
 *     ["mouse","mousepad"],              // "mou"
 *     ["mouse","mousepad"],              // "mous"
 *     ["mouse","mousepad"]               // "mouse"
 *   ]
 *
 * Example 2:
 *   Input:  products = ["havana"], searchWord = "havana"
 *   Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
 *
 * Constraints:
 *   - 1 <= products.length <= 1000
 *   - 1 <= products[i].length, searchWord.length <= 1000
 *   - products[i] and searchWord consist of lowercase English letters only.
 *
 * Follow up: After sorting `products` once, can you locate each prefix's
 * matching window with binary search instead of rescanning every product?
 */
fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
    TODO()
}
