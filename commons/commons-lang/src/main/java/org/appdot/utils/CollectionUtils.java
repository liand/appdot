/**
 * 
 */
package org.appdot.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springside.modules.utils.Collections3;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 对集合类公共支持方法的补充支援类，应先考虑使用java.util.Collections或apache的CollectionUtils，
 * 如果没有需要的集合类支持性方法，在此类中增加。
 * 
 * @author liand
 *
 */
public class CollectionUtils {

	/**
	 * 将两个集合转化为key,value的Map
	 * @param <K>	主键类型
	 * @param <V>	键值类型
	 * @param keys	表示主键的集合
	 * @param values	表示值的集合
	 * @return		以keys为key，values为值的Map
	 */
	public static final <K, V> Map<K, V> newMap(List<K> keys, List<V> values) {
		return newMap(keys, values, null);
	}

	/**
	 * 将两个集合转化为key,value的Map，按照ValueFilter的过滤规则过滤掉validate结果
	 * @param <K>	主键类型
	 * @param <V>	键值类型
	 * @param keys	表示主键的集合
	 * @param values	表示值的集合
	 * @param vf	@see {@link ValueFilter}
	 * @return		以keys为key，values为值的Map
	 */
	@SuppressWarnings("unchecked")
	public static final <K, V> Map<K, V> newMap(List<K> keys, List<V> values, ValueFilter<V> vf) {
		if (keys == null || values == null) {
			return Collections.EMPTY_MAP;
		}
		checkPairs(keys, values);
		Map<K, V> result = new HashMap<K, V>();
		int i = 0;
		for (K k : keys) {
			if (vf != null && vf.validate(values.get(i))) {
				result.put(k, values.get(i));
			}
			i++;
		}
		return result;
	}

	private static final <K, V> void checkPairs(List<K> keys, List<V> values) {
		if (keys.size() != values.size()) {
			throw new IllegalArgumentException("keys和values的个数不相等");
		}
	}

	/**
	 * 判断两个列表中有且仅有一个集合为空
	 * @param one		被检查的列表之一
	 * @param another	被检查的列表之一
	 * @return			true-当只有一个集合为空时，false-其它
	 */
	@SuppressWarnings("rawtypes")
	public static final boolean hasOnlyOneEmpty(List one, List another) {
		if ((Collections3.isEmpty(one) && !org.apache.commons.collections.CollectionUtils.isEmpty(another))
				|| (!org.apache.commons.collections.CollectionUtils.isEmpty(one) && org.apache.commons.collections.CollectionUtils
						.isEmpty(another))) {
			return true;
		}
		return false;
	}

	/**
	 * 将一个列表转化为Key和对象的Map
	 * @param <K>	所要转化为Map的Key类型
	 * @param <V>	集合元素类型
	 * @param source	待转化的集合
	 * @param function	取Key的接口
	 * @return		以集合元素的ID属性为Key，对象本身为Value的Map
	 */
	public static final <K, V> Map<K, V> list2Map(List<V> source, FetchKeyFunction<K, V> function) {
		Map<K, V> result = Maps.newHashMap();
		for (V v : source) {
			K k = function.getKey(v);
			if (k != null) {
				result.put(k, v);
			}
		}
		return result;
	}

	/**
	 * 将一个列表转化为特定Key和特定Value的Map
	 * @param <K>	Key的类型
	 * @param <V>	Value的类型
	 * @param <E>	列表中对象的类型
	 * @param source	集合列表
	 * @param function	抽取函数接口
	 * @return	Map<K, V>
	 */
	public static final <K, V, E> Map<K, V> list2Map(List<E> source, FetchKeyValueFunction<K, V, E> function) {
		Map<K, V> result = Maps.newHashMap();
		for (E e : source) {
			K key = function.getKey(e);
			V value = function.getValue(e);
			result.put(key, value);
		}
		return result;
	}

	/**
	 * 将一个列表的对象进行分组得到以Function定义的Key和V的集合的Map
	 * @param <K>	Map的主键类型
	 * @param <V>	集合元素的类型
	 * @param source	待分组的集合
	 * @param function	自定义的取Key的Function
	 * @return	以集合元素的某个属性分组的Map<>
	 */
	public static final <K, V> Map<K, List<V>> groupList2Map(List<V> source, FetchKeyFunction<K, V> function) {
		Map<K, List<V>> result = Maps.newHashMap();
		List<V> values;
		for (V v : source) {
			K k = function.getKey(v);
			values = result.get(k);
			if (values == null) {
				values = Lists.newArrayList();
				result.put(k, values);
			}
			values.add(v);
		}
		return result;
	}

	/**
	 * 挑选一个集合中最小或最大的元素
	 * @param <V>		集合的元素类型
	 * @param source	源集合
	 * @param fetcher	定义的数值型获取器
	 * @return			集合中根据数值获取器可取到的最小值
	 */
	public static final <V> Number pickNumber(Collection<V> source, NumericValueFetcher<V> fetcher,
			PickStrategy pickStrategy) {
		Number target = null;
		if (org.apache.commons.collections.CollectionUtils.isEmpty(source)) {
			return null;
		} else {
			target = fetcher.getNumricValue(source.iterator().next());
		}
		for (V v : source) {
			Number n = fetcher.getNumricValue(v);
			if (n != null
					&& ((pickStrategy == PickStrategy.smallest && n.doubleValue() < target.doubleValue()) || (pickStrategy == PickStrategy.biggest && n
							.doubleValue() > target.doubleValue()))) {
				target = n;
			}
		}
		return target;
	}

	/**
	 * 挑选一个集合中最小的元素
	 * @param <V>		集合的元素类型
	 * @param source	源集合
	 * @param fetcher	定义的数值型获取器
	 * @return			集合中根据数值获取器可取到的最小值
	 */
	public static final <V> Number pickSmallest(Collection<V> source, NumericValueFetcher<V> fetcher) {
		return pickNumber(source, fetcher, PickStrategy.smallest);
	}

	/**
	 * 挑选一个集合中最大的元素
	 * @param <V>		集合的元素类型
	 * @param source	源集合
	 * @param fetcher	定义的数值型获取器
	 * @return			集合中根据数值获取器可取到的最大值
	 */
	public static final <V> Number pickBiggest(Collection<V> source, NumericValueFetcher<V> fetcher) {
		return pickNumber(source, fetcher, PickStrategy.biggest);
	}

	public static enum PickStrategy {
		biggest, smallest
	}

	/**
	 * 数字型获取器接口
	 * @author liand
	 *
	 * @param <V>
	 */
	public static interface NumericValueFetcher<V> {
		public Number getNumricValue(V v);
	}

	/**
	 * 元素过滤接口
	 * 
	 * @author liand
	 *
	 * @param <V>	要过滤的元素的类型
	 * @see	CollectionUtils#newMap(List, List, ValueFilter)
	 */
	public static interface ValueFilter<V> {
		public boolean validate(V v);
	}

	/**
	 * 取Key的接口，实现时定义取哪个属性为Key
	 * @author liand
	 */
	public static interface FetchKeyFunction<X, Y> {
		public X getKey(Y y);
	}

	/**
	 * 从对象中抽取Key和Value的回调接口
	 * @author liand
	 *
	 * @param <K>	Key的类型
	 * @param <V>	Value的类型
	 * @param <E>	对象的类型
	 */
	public static interface FetchKeyValueFunction<K, V, E> {
		public K getKey(E e);

		public V getValue(E e);
	}

}
