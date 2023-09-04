package memseqdb;

public record SeqItem<T>(int sequenceNumber, T item) {
}