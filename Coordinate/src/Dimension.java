import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public interface Dimension<D,T> {

    default  <C extends Dimension<?,T>,B extends Dimension<Y,Z>,Y,Z> Set<B> reduce(Function<C,B> reduce, Set<C> positions){
        Map<Y, B> DimensionMap = new HashMap<>();
        for (C position: positions){
            B newPosition = reduce.apply(position);
            Y d = newPosition.getD();
            if (DimensionMap.containsKey(d)){
                B b = DimensionMap.get(d);
                b.getData().addAll(newPosition.getData());
            } else {
                DimensionMap.put(d,newPosition);
            }
        }
        return new HashSet<>(DimensionMap.values());
    }
    D getD();

    Set<T> getData();
}
