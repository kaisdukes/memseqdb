package memseqdb;

import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;

public class Seq<T> {
    private final Object2IntOpenHashMap<T> itemToSequenceNumber = new Object2IntOpenHashMap<>();

    public Seq(Iterable<T> items) {
        var sequenceNumber = 0;
        for (var item : items) {
            itemToSequenceNumber.put(item, ++sequenceNumber);
        }
    }

    public int getSequenceNumber(T item) {
        return itemToSequenceNumber.getInt(item);
    }
}