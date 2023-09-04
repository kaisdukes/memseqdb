package memseqdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class SparseSeq2SeqTest {
    private SparseSeq2Seq sparseSeq2Seq;

    @BeforeEach
    void setUp() {
        sparseSeq2Seq = new SparseSeq2Seq(List.of(
                new SeqPair(1, 3),
                new SeqPair(3, 6),
                new SeqPair(4, 6),
                new SeqPair(8, 22)
        ));
    }

    @Test
    void shouldGetMappingBySequenceNumberA() {
        var item = sparseSeq2Seq.getSequenceNumberB(3);
        assertThat(item, is(equalTo(6)));
    }

    @Test
    void shouldReturnZeroForNonExistentSequenceNumberA() {
        var item = sparseSeq2Seq.getSequenceNumberB(5);
        assertThat(item, is(equalTo(0)));
    }

    @Test
    void shouldHandleEmptyList() {
        var emptySparseSeq2Seq = new SparseSeq2Seq(List.of());
        var item = emptySparseSeq2Seq.getSequenceNumberB(1);
        assertThat(item, is(equalTo(0)));
    }

    @Test
    void shouldHandleDuplicateMappings() {
        var sparseSeq2SeqWithDuplicates = new SparseSeq2Seq(List.of(
                new SeqPair(1, 3),
                new SeqPair(1, 4),
                new SeqPair(4, 6)
        ));
        var item = sparseSeq2SeqWithDuplicates.getSequenceNumberB(1);
        assertThat(item, anyOf(is(3), is(4)));
    }
}