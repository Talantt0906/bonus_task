💻 Bonus Task: Rabin-Karp String AlgorithmThis repository contains the Java implementation of the Rabin-Karp String Matching Algorithm using the Polynomial Rolling Hash technique, fulfilling the requirements of the Bonus Task assignment.1. ⚙️ Algorithm Implementation SummaryThe core idea of the Rabin-Karp algorithm is to transform the computationally expensive task of string comparison into fast hash value comparisons.Key Takeaway: The use of a Polynomial Rolling Hash allows the algorithm to re-calculate the hash for the next window in $\mathcal{O}(1)$ time, leading to an efficient average time complexity.Hashing MethodologyThe hash value $H(S)$ for a substring $S$ of length $M$ is calculated using the following formula:$$H(S) = \left( \sum_{i=0}^{M-1} S[i] \cdot B^{M-1-i} \right) \pmod{P}$$BASE (B = 257): A prime radix chosen to accommodate the extended ASCII character set.PRIME (P = 1000000007): A large prime modulus used to minimize hash collisions.Rolling Hash PrincipleThe implementation uses a rolling hash mechanism to update the text window hash in constant time:Removal: Subtract the contribution of the leading character $\left(T[i] \cdot B^{M-1}\right)$.Shift: Multiply the result by BASE to shift all remaining characters' contributions one position.Addition: Add the contribution of the new trailing character $\left(T[i+M]\right)$.The code also includes a necessary character-by-character check immediately following any hash match to handle potential spurious hits (hash collisions).2. 🧪 Testing Results & Sample OutputThe algorithm was tested using the provided main method in RabinKarp.java with strings of varying lengths to observe its behavior.Test CaseText Length (N)Pattern (P)Pattern Length (M)Found IndicesConditionShort String11"abra"4[0, 7]Multiple MatchesMedium String35"fox"3[16]Single MatchLonger String24"GCAG"4[5, 20]Multiple Matches (DNA Sequence)No Match11"goodbye"7[]Pattern Not FoundConsole OutputShell--- Rabin-Karp Algorithm Test Cases ---

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
3. 📊 Complexity AnalysisLet $N$ be the length of the text $T$ and $M$ be the length of the pattern $P$.Time ComplexityScenarioTime CostNotesPreprocessing$\mathcal{O}(M)$Calculating initial hashes and the power term $B^{M-1} \pmod P$.Average Case$\mathcal{O}(N + M)$Hash comparisons are $\mathcal{O}(1)$. This is the expected running time with a good hash function.Worst Case$\mathcal{O}(N \cdot M)$Occurs during catastrophic hash collisions (e.g., repeating characters), forcing an $\mathcal{O}(M)$ check at every position.Space ComplexityComponentSpace CostNotesWorking Space$\mathcal{O}(1)$The algorithm uses only a constant number of variables for the hashes and loop counters.Output Space$\mathcal{O}(k)$Space required for the list of match indices, where $k$ is the total number of occurrences $(k \le N)$.
