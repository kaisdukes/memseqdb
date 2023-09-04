package memseqdb;

@FunctionalInterface
public interface Graph<TQuery, TResult> {

    TResult query(TQuery query);
}