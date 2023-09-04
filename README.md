# MemSeqDB: A High-Performance, In-memory Graph Database of Sequenced Data

MemSeqDB is a small, lightweight Java framework for building high-performance, immutable, in-memory graph databases of sequenced data. Sequenced data refers to a set of data elements arranged in a specific order, providing a meaningful sequence for storage and retrieval operations. The library guarantees O(1) lookup time for most operations (detailed below). There is only a single dependency, [fastutil](https://fastutil.di.unimi.it), to avoid autoboxing of integer sequence numbers and to optimize memory usage.

## Usage

MemSeqDB is designed for systems that require high-speed, in-memory storage and retrieval of immutable sequenced data. MemSeqDB is a class library, and does not provide a full graph database out of the box. The library does however provide the necessary abstractions and interfaces for standardization of sequenced in-memory graphs.

All classes are immutable and are therefore thread-safe.

## Sequences

The generic `Seq` class represents an immutable in-memory sequence, constructed from an `Iterable` of items. Sequences support O(1) lookup of an item's 1-based sequence number:

```java
var seq = new Seq<>(List.of("foo", "bar"));
var sequenceNumber = seq.getSequenceNumber("bar"); // 2
```

`SparseSeq` supports the inverse mapping for immutable sparse sequences, from sequence number to item, and supports an O(1) lookup.

```java
var seq = new SparseSeq<>(List.of(
    new SeqItem<>(5, "abc"),
    new SeqItem<>(7, "xyz")));
    
var item = seq.getItem(5); // "abc"
```

## Sequence To Sequence

The generic `Seq2Seq` class represents a many-to-one mapping between two sequences (A and B), constructed from an `Iterable` of sequence numbers from sequence B. The sequence numbers from sequence A are inferred as 1 through to the length of the iterable, inclusive. `Seq2Seq` can be queried to return a range, using an O(1) lookup.

```java
var aToB = new Seq2Seq(List.of(3, 8, 8, 12));
var range = aToB.getRange(new SeqRange(2, 4));
var minB = range.min(); // 8
var maxB = range.max(); // 12
```

For mapping sparse sequences, the generic `SparseSeq2Seq` class translates sequence numbers using an O(1) lookup.

```java
var aToB = new SparseSeq2Seq(List.of(
    new SeqPair(1, 3),
    new SeqPair(3, 6),
    new SeqPair(4, 6),
    new SeqPair(8, 22)
));

var b = aToB.getSequenceNumberB(3); // 6
```

## Graphs

A generic `SeqGraphNode` represents a node in a directed graph, with outgoing edges arranged by sequence number. By convention, 'child nodes' refers to the nodes that are pointed to. To combine sequenced nodes with other node types, `SeqGraphNode` extends the `GraphNode` marker interface.

```java
interface SeqGraphNode<T extends GraphNode> extends GraphNode {
    T getChild(int sequenceNumber);
    T[] children();
}
```

For maximum flexibility, a `Graph` is defined simply as an immutable, queryable, in-memory data structure.

```java
@FunctionalInterface
interface Graph<TQuery, TResult> {

    TResult query(TQuery query);
}
```

## Graph Loaders

Since graphs are immutable, the framework supports graph loaders, which typically load data once and construct an immutable graph using a graph constructor. A `GraphLoader` is a function that returns a graph. In implementations, it is expected that graphs will either be loaded on application startup or lazily loaded and cached on demand.

```java
@FunctionalInterface
interface GraphLoader<T extends Graph<?, ?>> {

    T load();
}
```

## Deployment

This repo is configured to deploy to GitHub packages, which requires a classic personal access token:

```bash
export GITHUB_TOKEN=...
./deploy.sh
```