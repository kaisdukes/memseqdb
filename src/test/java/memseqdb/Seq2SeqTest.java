package memseqdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;

class Seq2SeqTest {
    private Seq2Seq seq2Seq;

    @BeforeEach
    void setUp() {
        seq2Seq = new Seq2Seq(List.of(3, 8, 8, 12));
    }

    @Test
    void shouldGetCorrectRange() {
        var range = seq2Seq.getRange(new SeqRange(2, 4));
        assertThat(range.min(), is(equalTo(8)));
        assertThat(range.max(), is(equalTo(12)));
    }

    @Test
    void shouldHandleOutOfRange() {
        var range = seq2Seq.getRange(new SeqRange(5, 6));
        assertThat(range, is(nullValue()));
    }

    @Test
    void shouldHandleEmptyList() {
        var emptySeq2Seq = new Seq2Seq(emptyList());
        var range = emptySeq2Seq.getRange(new SeqRange(1, 3));
        assertThat(range, is(nullValue()));
    }
}
