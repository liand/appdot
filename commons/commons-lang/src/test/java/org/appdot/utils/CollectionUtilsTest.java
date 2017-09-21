package org.appdot.utils;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

public class CollectionUtilsTest {

	@Test
	public void testPickSmallest() {
		List<Double> dValues = Lists.newArrayList(1.0d, 2.0d, 3.0d);

		Number smallestNumber = CollectionUtils.pickSmallest(dValues,
				new CollectionUtils.NumericValueFetcher<Double>() {

					public Number getNumricValue(Double v) {
						return v;
					}

				});

		Assert.assertEquals(1.0d, smallestNumber.doubleValue(), 0.1d);
	}

	@Test
	public void testPickBiggest() {
		List<Double> dValues = Lists.newArrayList(1.0d, 2.0d, 3.0d);
		Number biggestNumber = CollectionUtils.pickBiggest(dValues, new CollectionUtils.NumericValueFetcher<Double>() {

			public Number getNumricValue(Double v) {
				return v;
			}

		});

		Assert.assertEquals(3.0d, biggestNumber.doubleValue(), 0.1d);
	}

}
