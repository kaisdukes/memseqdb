package memseqdb;

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

public class Seq2Seq {
    private final Int2IntOpenHashMap a2b = new Int2IntOpenHashMap();

    public Seq2Seq(Iterable<Integer> sequenceNumbersB) {
        var sequenceNumberA = 0;
        for (var sequenceNumberB : sequenceNumbersB) {
            a2b.put(++sequenceNumberA, (int) sequenceNumberB);
        }
    }

    public SeqRange getRange(SeqRange tokenSequenceRangeA) {
        var minB = a2b.get(tokenSequenceRangeA.min());
        var maxB = a2b.get(tokenSequenceRangeA.max());
        return minB > 0 && maxB > 0 ? new SeqRange(minB, maxB) : null;
    }
}