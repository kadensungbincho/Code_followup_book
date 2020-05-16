## Implementing a Custom Writable
hadoop comes with a useful set of Writable implementations that serve most purposes; however, on occasion, you may need to write your own custom implementation. With a custom Writable, you have full control over the binary representation and the sort order. Because Writables are at the heart of the MapReduce data path, tuning the binary representaion can have a significant effect on performance. The stock Writable impelementations that come with Hadoop are well tuned, but for more elaborate structures, it is often better to create a new Writable type rather than componsing the stock types. 

To demonstrate how to create a custom Writable, we shall write an impelmentation that represents a pair of strings, called TextPair. The basic implementation is shown in Example 5-7.

The first part of the implementation is straightforward: there are two Text instance variables, first and second, and associated contructors, getters, and setters. All Writable implementations must have a default constructor so that the MapReduce framework can instantiate them, then populate their fields by calling readFields(). Writable instances are mutable and often reused, so you should take care to avoid allocating objects in the write() or readFields() methods.

TextPair's write() method serializes each Text objectin turn to the output stream by delegating to the Text objects themselves. Similarly, readFields() deserializes the bytes from the input stream by delegating to each Text object. The DataOutput and DataInput interfaces have a rich set of methods for serializing and deserializing Java primitives, so, in general, you have complete control over the wire format of your Writable object. 

Just as you would for any value object you write in Java, you should override the hashCode(), equals(), and toString() methods from java.lang.Object. The hashCode() method is used by the HashPartitioner (the default partitioner in MapReduce) to choose a reduce partition, so you should make sure that you write a good hash function that mixes well to ensure reduce partitions are of a similar size.

TextPair is an implementation of WritableComparable, so it provides an implementation of the compareTo() method that imposes the ordering you would expect: it sorts by the first string followed by the second. Notice that, apart from the number of Text objects it can store, TextPair differs from TextArrayWritable (which we discussed in the previous section), since TextArrayWritable is only a Writable, not a Writable Comparable.

*Custom comparators*
As you can see with TextPair, writing raw comparators takes some care because you have to deal with details at the byte level. It is worth looking at some of the implementations of Writable in the org.apache.hadoop.io package for further ideas if you need to write your own. The utility methods on WritableUtils are very handy, too.

Custom comparators should also be written to be RawComparators, if possible. These are comparators that implement a different sort order from the natural sort order defined by the default comparator. Example 5-9 shows a comparator for TextPair, called FirstComparator, that considers only the first string of the pair. Note that we override the compare() method that takes objects so both compare() methods have the same semantics.

We will make use of this comparator in Chapter 9, when we look at joins and secondary sorting in MapReduce.

## Serialization Frameworks
Although most MapReduce programs use Writable key and value types, this isn't mandated by the MapReduce API. In fact, any type can be used the only requirement is a mechanism that translates to and from a binary representation of each type. 

To support this, Hadoop has an API for pluggable serialization frameworks. A serialization framwork is represented by an implementation of Serialization. WritableSerialization, for example, is the implementation of Seriablization for Writable types.

A Serialization defines a mapping from types to Serializer instances and Deserializer instances. 

*Writing a SequenceFile*
To create a SequenceFile, use one of its createWriter() static methods, which return a SequenceFile.Writer instance. There are several overloaded versions, but they all require you to sepcify a stream to write to, a Configuration object, and the key and value types. Optional arguments include the compression type and codec, a Progressable callback to be informed of write progress, and a Metadata instance to be stored in the SequenceFile header.

If the next() method returns a non-null object, a key-value pair was read from the stream, and the value can be retrieved using the getCurrentValue() method. Otherwise, if enxt() returns null, the end of the file has been reached.

The program in Example 5-11 demonstrates how to read a sequence file that has Writable keys and values. 