package cc.sion.core.persistence;


import java.util.HashMap;
import java.util.Map;

public class WhereHelper {

    final static String _split = "_";

    private Map<String, Object> searchParams = new HashMap<String, Object>();


    public WhereHelper eq(String key,String val) {
        searchParams.put(SearchFilter.Operator.EQ+_split+key,val);
        return this;
    }
    public WhereHelper ne(String key,String val) {
        searchParams.put(SearchFilter.Operator.NE+_split+key,val);
        return this;
    }
    public WhereHelper like(String key,String val) {
        searchParams.put(SearchFilter.Operator.LIKE+_split+key,val);
        return this;
    }
    public WhereHelper gt(String key,String val) {
        searchParams.put(SearchFilter.Operator.GT+_split+key,val);
        return this;
    }
    public WhereHelper lt(String key,String val) {
        searchParams.put(SearchFilter.Operator.LT+_split+key,val);
        return this;
    }
    public WhereHelper gte(String key,String val) {
        searchParams.put(SearchFilter.Operator.GTE+_split+key,val);
        return this;
    }
    public WhereHelper lte(String key,String val) {
        searchParams.put(SearchFilter.Operator.LTE+_split+key,val);
        return this;
    }

    public Map<String, Object> toBuilder() {
        return searchParams;
    }

}
