package memseqdb;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class SparseSeq<T> {
    private final Int2ObjectOpenHashMap<T> sequenceNumberToItem = new Int2ObjectOpenHashMap<>();

    public SparseSeq(Iterable<SeqItem<T>> items) {
        for (var pair : items) {
            sequenceNumberToItem.put(pair.sequenceNumber(), pair.item());
        }
    }

    public T getItem(int sequenceNumber) {
        return sequenceNumberToItem.get(sequenceNumber);
    }
}