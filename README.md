## 💻 Bonus Task: Rabin-Karp String Algorithm

This repository contains the Java implementation of the **Rabin-Karp String Matching Algorithm** using the Polynomial Rolling Hash technique, fulfilling the requirements of the Bonus Task assignment.

---

### 1. ⚙️ Algorithm Implementation Summary

The core idea of the Rabin-Karp algorithm is to transform the computationally expensive task of string comparison into fast hash value comparisons.

> **Key Takeaway:** The use of a **Polynomial Rolling Hash** allows the algorithm to re-calculate the hash for the next window in **O(1)** time on average, leading to an efficient average time complexity.

#### Hashing Methodology

The hash value $H(S)$ for a substring $S$ of length $M$ is calculated using the following formula:

$$H(S) = \left( \sum_{i=0}^{M-1} S[i] \cdot B^{M-1-i} \right) \pmod{P}$$

* **BASE (B = 257)**: A prime radix chosen to accommodate the extended ASCII character set.
* **PRIME (P = 1000000007)**: A large prime modulus used to minimize hash collisions.

#### Rolling Hash Principle

The implementation uses a rolling hash mechanism to update the text window hash in constant time:

1.  **Removal:** Subtract the contribution of the leading character.
2.  **Shift:** Multiply the remaining hash by `BASE`.
3.  **Addition:** Add the contribution of the new trailing character.

The code also includes a necessary character-by-character check immediately following any hash match to handle potential **spurious hits** (hash collisions).

---

### 2. 🧪 Testing Results & Sample Output

The algorithm was tested using the provided `main` method in `RabinKarp.java` with strings of varying lengths to observe its behavior.

| Test Case | Text Length (N) | Pattern (P) | Pattern Length (M) | Found Indices | Condition |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Short String** | 11 | `"abra"` | 4 | `[0, 7]` | Multiple Matches |
| **Medium String** | 35 | `"fox"` | 3 | `[16]` | Single Match |
| **Longer String** | 24 | `"GCAG"` | 4 | `[5, 20]` | Multiple Matches (DNA Sequence) |
| **No Match** | 11 | `"goodbye"` | 7 | `[]` | Pattern Not Found |

#### Console Output

```console
--- Rabin-Karp Algorithm Test Cases ---

Test 1: Short String
Text:    "abracadabra"
Pattern: "abra"
Found at indices: [0, 7]

Test 2: Medium String
Text:    "thequickbrownfoxjumpsoverthelazydog"
Pattern: "fox"
Found at indices: [16]

Test 3: Longer String
Text:    "GCATCGCAGAGAGTATACAGTACG"
Pattern: "GCAG"
Found at indices: [5, 20]

Test 4: No Match
Text:    "hello world"
Pattern: "goodbye"
Found at indices: []
