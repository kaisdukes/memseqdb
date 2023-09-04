package memseqdb;

@FunctionalInterface
public interface GraphLoader<T extends Graph<?, ?>> {

    T load();
}