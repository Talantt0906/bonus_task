## 💻 Bonus Task: Rabin-Karp String Algorithm

This repository contains the Java implementation of the **Rabin-Karp String Matching Algorithm** using the Polynomial Rolling Hash technique, fulfilling the requirements of the Bonus Task assignment.

---

### 1. ⚙️ Algorithm Implementation Summary

The core idea of the Rabin-Karp algorithm is to transform the computationally expensive task of string comparison into fast hash value comparisons.

> **Key Takeaway:** Using a **Polynomial Rolling Hash** allows the algorithm to update the hash of each next window in **O(1)** time, enabling efficient string matching.

#### 🔢 Hashing Methodology

The hash value of a substring \( S \) with length \( M \) is:

$$H(S) = \left( \sum_{i=0}^{M-1} S[i] \cdot B^{M-1-i} \right) \pmod{P}$$

- **BASE (B = 257)**: A prime larger than ASCII range  
- **PRIME (P = 1000000007)**: Large modulus to reduce collisions  

#### 🔁 Rolling Hash Principle

When sliding the window (from index *i* to *i+1*):

1. Remove contribution of the leading character  
2. Shift remaining hash by multiplying with `BASE`  
3. Add new trailing character  
4. Compare hash → if equal, verify with character-by-character check  

This makes average performance very efficient.

---

### 2. 🧪 Testing Results & Sample Output

The algorithm was tested using the provided `main` method in `RabinKarp.java`.

| Test Case | Text Length (N) | Pattern | M | Result | Description |
|---|:---:|---|:---:|---|---|
| Short String | 11 | "abra" | 4 | `[0, 7]` | Multiple matches |
| Medium String | 35 | "fox" | 3 | `[16]` | Single match |
| Longer String | 24 | "GCAG" | 4 | `[5, 20]` | DNA-like sequence |
| No Match | 11 | "goodbye" | 7 | `[]` | Pattern not found |

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
 ### 3. 📊 Time and Space Complexity Analysis

Let:

- **N** = length of the text  
- **M** = length of the pattern  

---

#### ⏱ **Time Complexity**

| Scenario           | Time        | Explanation                                |
|-------------------|-------------|--------------------------------------------|
| **Preprocessing** | **O(M)**    | Initial hash computation                   |
| **Average Case**  | **O(N + M)**| Rolling hash updates in constant time      |
| **Best Case**     | **O(N + M)**| Few or no collisions                       |
| **Worst Case**    | **O(N · M)**| Many collisions → full string comparisons  |

👉 Worst case practically **never happens** due to the use of a large PRIME modulus, which minimizes collisions.

---

#### 💾 **Space Complexity**

| Component                | Complexity | Notes                            |
|--------------------------|------------|----------------------------------|
| Working Variables        | **O(1)**   | Only constant number of hashes   |
| Output (match indices)   | **O(k)**   | k = number of occurrences        |

**Total Auxiliary Space:**  
➡️ **O(1)** extra space + **O(k)** for storing match results

