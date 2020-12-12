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

The **put()** method in Hashmap has a better time complexity of O(1)
when compared to that of TreeMap's, which has a time complexity of
O(log(n)). However, if the overhead is accounted for, TreeMap could
see less time taken when using **put()**.  Otherwise in the long run,
should take less time accessing it's values using **put()**.

To achieve the time complexity of O(1), HashMap uses hashing to obtain
the index of it's internal array for the item. HashMap uses Java's own
**hashCode()** function to generate hashes based on the key.  The time
complexity of O(1) is dependant on the hash function's ability to
produce a completely different value with little change to the input
value, in hopes to reduce the number of collisions. 

The **put()** method finds the value based on the key. TreeMap is
binary tree were the nodes are sorted by the key on the Key Value
pairs. As **put()** takes in the key as the value, it would take a
binary search to find the key and it's corresponding value. Whereas
There is no garauntee what order HashMap

#### todo notes:
- Java HashMap uses hashCode().
- Explain hashing / tree balancing / red black tree.
- Explain usage (only accessing, or if you need sorting)

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

