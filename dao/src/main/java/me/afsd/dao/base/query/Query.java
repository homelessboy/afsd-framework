package me.afsd.dao.base.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.Assert;

import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * User: afsd
 * Date: 2016/2/29
 * Time: 17:39
 */
public class Query<T> implements Serializable {
    private Pageable pageable;

    protected static Sort DEFAULT_SORT = new Sort(Direction.DESC, "id");


    private static final int DEFAULT_PAGE_SIZE=9;

    private int index;

    private int size;

    public Query() {
        this(0, DEFAULT_PAGE_SIZE);
    }

    public Query(int index, int size) {
        this.index = index;
        this.size = size;
    }

    public QueryWordSpecification asSpecification() {
        return new QueryWordSpecification(this);
    }

    class QueryWordSpecification implements Specification<T> {
        private Query<T> query;
        private List<Field> fieldList = new ArrayList<>();
        private boolean distinct=false;

        public QueryWordSpecification(Query<T> query,boolean distinct){
            this(query);
            this.distinct=distinct;
        }

        public QueryWordSpecification(Query<T> query) {
            this.query = query;
            Field[] fields = query.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(QueryWord.class) != null)
                    fieldList.add(field);
            }
        }

        @Override
        public Predicate toPredicate(Root root, CriteriaQuery cq, CriteriaBuilder cb) {
            Predicate predicate = null;
            Field[] fields = query.getClass().getDeclaredFields();
            if(distinct)
                cq.distinct(true);
            if (fields != null) {
                for (Field field : fieldList) {
                    QueryWord queryWord = field.getAnnotation(QueryWord.class);
                    field.setAccessible(true);
                    try {
                        if (field.get(query) != null || queryWord.searchNull())
                            predicate = predicate == null ? addPredicate(root, cb, field, queryWord) : cb.and(predicate, addPredicate(root, cb, field, queryWord));
                    } catch (IllegalAccessException ignored) {
                    }
                }
            }
            return predicate;
        }

        @SuppressWarnings("unchecked")
        private Predicate addPredicate(Root root, CriteriaBuilder cb, Field value, QueryWord queryWord) throws IllegalAccessException {
            Object searchObject = value.get(query);
            if (!queryWord.enumType().equals(Enum.class)) {
                Assert.isInstanceOf(String.class,searchObject);
                if ("".equals(searchObject))
                    return null;
                searchObject = Enum.valueOf(queryWord.enumType(), (String) searchObject);
            }

            switch (queryWord.matchType()) {
                case eq:
                    return cb.equal(getPath(root, queryWord.value(), value), searchObject);
                case like:
                    Assert.isInstanceOf(String.class,searchObject);
                    return cb.like(getPath(root, queryWord.value(), value), getSearchStr((String) searchObject));
                case greater:
                    return cb.gt(getPath(root, queryWord.value(), value), (Number) searchObject);
                case greaterOrEqual:
                    return cb.ge(getPath(root, queryWord.value(), value), (Number) searchObject);
                case less:
                    return cb.lt(getPath(root, queryWord.value(), value), (Number) searchObject);
                case lessOrEqual:
                    return cb.le(getPath(root, queryWord.value(), value), (Number) searchObject);
                case notEq:
                    return cb.notEqual(getPath(root, queryWord.value(), value), searchObject);
            }
            return null;
        }

        private Path getPath(Root root, String pathStr, Field value) {
            String[] pathStrs;
            if (pathStr == null || pathStr.equals("")) {
                pathStrs = new String[]{value.getName()};
            } else {
                pathStrs = pathStr.split("\\.");
            }
            int index = 0;
            Path path = (pathStrs.length > 1) ? root.join(pathStrs[index++]) : root.get(pathStrs[index++]);

            while (index < pathStrs.length) {
                path = (index + 1 == pathStrs.length) ? path.get(pathStrs[index++]) : ((Join) path).join(pathStrs[index++]);
            }
            return path;
        }

    }

    public Sort asSort() {
        List<Sort.Order> orders = new ArrayList<>();
//        Field[] fields = this.getClass().getDeclaredFields();
//        if (fields != null) {
//            for (Field field : fields) {
//                OrderWord orderWord = field.getAnnotation(OrderWord.class);
//                field.setAccessible(true);
//                try {
//                    int weight = (int) field.get(this);
//                } catch (IllegalAccessException e) {
//                    e.printStackTrace();
//                }
//                if (orderWord != null) {
//
//                }
//            }
//        }
        //TODO: 后续支持查询丰富的排序接口
        return !orders.isEmpty() ? DEFAULT_SORT : new Sort(orders);
    }

    public static String getSearchStr(String str) {
        if (str == null || str.equals(""))
            return null;
        StringBuilder sb = new StringBuilder();
        String[] strings = str.split(" ");
        if (strings.length <= 0)
            return "";
        for (String string : strings) {
            if (!string.equals("")) {
                sb.append(string);
                sb.append("%");
            }
        }
        if (sb.lastIndexOf("%") == sb.length() - 1)
            sb.delete(sb.length() - 1, sb.length());
        return "%" + sb.toString() + "%";
    }

    public Pageable getPageable() {
        return getPageable(asSort());
    }

    public Pageable getPageable(Sort sort) {
        this.pageable = new PageRequest(index, size, sort);
        return pageable;
    }
}
