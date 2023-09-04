package memseqdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class SeqTest {
    private Seq<String> seq;

    @BeforeEach
    void setUp() {
        seq = new Seq<>(List.of("foo", "bar", "baz"));
    }

    @Test
    void shouldGetSequenceNumber() {
        var sequenceNumber = seq.getSequenceNumber("bar");
        assertThat(sequenceNumber, is(equalTo(2)));
    }

    @Test
    void shouldReturnZeroForNonExistentItem() {
        var sequenceNumber = seq.getSequenceNumber("nonexistent");
        assertThat(sequenceNumber, is(equalTo(0)));
    }
}
