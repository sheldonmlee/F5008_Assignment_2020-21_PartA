# Answers

## 3.1

>	[Oracle Treemap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)

## 3.2

### Description:

>	description

### TreeMap implements: 

-	NavigableMap <K, V>
-	Cloneable 
-	Serializable

### Navigable Map methods: 

-	ceilingEntry(K key)
-	ceilingKey(K key)
-	descendingKeySet()
-	descendinMap()
-	firstEntry()
-	floorEntry(K key)
-	floorKey(K key)
-	headMap(K toKey)
-	headMap()
-	higherEntry(K key)
-	higherKey(K key)
-	lastEntry()
-	lowerEntry(K key)
-	lowerKey(K key)
-	navigableKeySet()
-	pollFirstEntry()
-	pollLastEntry()
-	subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
-	subMap(K fromKey, K toKey)
-	tailMap(K fromKey)
-	tailmap(K fromKey, boolean inclusive)

\pagebreak

### TreeMap.containsKey():

>	This method searches for a key within the tree map so see if it
>	exists and returns a boolean.

>	The O notation for the method should be log(n). TreeMap is a
>	binary tree that stores key-value pairs sorted by the key.
>	Searching for if a key exists should be a binary search, which has
>	an O-notation of log(n);

## 4.1 TreeMap and HashMap:

### put():

The **put()** method takes a key-value pair, inserts it into the data
structure, be it HashMap or TreeMap. Both of them places them in a
position depending on the key. For HashMap, the key-value pair will be
placed in an internal array, where the index is based on the hash
value of the key. Whereas in TreeMap, the key-value pair is placed in
a sorted position in a binary tree.

The **put()** method in Hashmap has a better time complexity of O(1)
when compared to that of TreeMap's, which has a time complexity of
O(log(n)). However, if the overhead is considered, TreeMap could see
less time taken when using **put()**.  Otherwise in the long run,
Hashmap should take less time accessing it's values using **put()**.

To achieve the time complexity of O(1), HashMap uses hashing to obtain
the index of it's internal array for the item. HashMap uses Java's own
**hashCode()** function to generate hashes based on the key.  The time
complexity of O(1) is dependant on the hash function's ability to
produce a completely different value with little change to the input
value, in hopes to reduce the number of collisions. 

// explain a hashing algorithm

An example of such hashing algorithm would be the MD5 algorithm.
Though outdated and insecure for cryptographic purposes nowadays, it
would still be usefull for purposes similar to Java's HashMap. The
algorithm would takes an input spit into blocks of 512 bits, resulting
in a fixed output that is 128 bits long. If the size of the input is
not divisible by 512 bits, additional padding would be required.

TreeMap is binary tree were the nodes are sorted by the key on the
Key-Value pairs. As **put()** takes in the key as the value, it would
take a binary search to find the key and it's corresponding value.
Whereas There is no guarantee what order HashMap puts items. TreeMap's
internal tree is also known as a Red-black Tree.

// explain Reb-black tree

A Red-black tree is a type of binary tree which is self balancing on
insert.

// conclusion

In conclusion, if order does not matter and all you need is pure
lookup time in a large dataset, HashMap should be the choice of
datastructre. However...

#### todo notes:
- Java HashMap uses hashCode().
- Explain hashing / tree balancing / red black tree.
- Explain usage (only accessing, or if you need sorting).
- Memory overhead of HashMap (most buckets/array slots aren't used).

### merge():

## 5.1
![Profile image](profile.png)

## 5.2
1.	BaseLineSimulation.collectstatistics()
2.	BaseLineSimulation.testforcommunication()
3.	BaseLineSimulation.move()
4.	BaseLineSimulation.removeTheDead()

## 5.3
1.	BaseLineSimulation.testforcommunication()
	- This will ultimately take the most time to optimize.\
2.	BaseLineSimulation.collectstatistics()
3.	BaseLineSimulation.move()
4.	BaseLineSimulation.removeTheDead()

## 5.4

