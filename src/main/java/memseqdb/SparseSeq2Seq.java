package memseqdb;

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;

public class SparseSeq2Seq {
    private final Int2IntOpenHashMap a2b = new Int2IntOpenHashMap();

    public SparseSeq2Seq(Iterable<SeqPair> pairs) {
        for (var pair : pairs) {
            a2b.put(pair.a(), pair.b());
        }
    }

    public int getSequenceNumberB(int sequenceNumberA) {
        return a2b.get(sequenceNumberA);
    }
}