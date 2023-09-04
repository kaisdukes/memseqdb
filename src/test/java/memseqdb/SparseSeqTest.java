package memseqdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

class SparseSeqTest {
    private SparseSeq<String> sparseSeq;

    @BeforeEach
    void setUp() {
        sparseSeq = new SparseSeq<>(List.of(
                new SeqItem<>(5, "abc"),
                new SeqItem<>(7, "xyz")
        ));
    }

    @Test
    void shouldGetItemBySequenceNumber() {
        var item = sparseSeq.getItem(5);
        assertThat(item, is(equalTo("abc")));
    }

    @Test
    void shouldReturnNullForNonExistentSequenceNumber() {
        var item = sparseSeq.getItem(6);
        assertThat(item, is(nullValue()));
    }

    @Test
    void shouldHandleEmptyList() {
        var emptySparseSeq = new SparseSeq<>(emptyList());
        var item = emptySparseSeq.getItem(5);
        assertThat(item, is(nullValue()));
    }
}