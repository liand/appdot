package org.appdot.utils;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;

public class JSONUtilsTest {

	public static class JSONObject {
		private String optionName;
		private List<String> optionValues;

		public String getOptionName() {
			return optionName;
		}

		public void setOptionName(String optionName) {
			this.optionName = optionName;
		}

		public String getOptionValue() {
			if (CollectionUtils.isNotEmpty(optionValues)) {
				return optionValues.get(0);
			}
			return null;
		}

		public void setOptionValue(String optionValue) {
			if (optionValues == null) {
				optionValues = new ArrayList<String>();
			}
			optionValues.add(0, optionValue);
		}

		public List<String> getOptionValues() {
			return optionValues;
		}

		public void setOptionValues(List<String> optionValues) {
			this.optionValues = optionValues;
		}

	}

	List<JSONObject> jsonObjects;
	String jsonStr;

	@Before
	public void setUp() {
		jsonObjects = new ArrayList<JSONObject>();
		JSONObject jsonObj = new JSONObject();
		jsonObj.setOptionName("jsonName1");
		jsonObj.setOptionValue("jsonValue1");
		jsonObjects.add(jsonObj);
		jsonObj = new JSONObject();
		jsonObj.setOptionName("jsonName2");
		jsonObj.setOptionValue("jsonValue2");
		jsonObjects.add(jsonObj);

		jsonStr = "[{\"optionName\":\"jsonName1\",\"optionValue\":\"jsonValue1\",\"optionValues\":[\"jsonValue1\"]},{\"optionName\":\"jsonName2\",\"optionValue\":\"jsonValue2\",\"optionValues\":[\"jsonValue2\"]}]";
	}

	@Test
	public void testToJSONStyle() throws IOException {
		String result = JSONUtils.toJSONStyle(jsonObjects);
		assertTrue(result.startsWith("["));
	}

	@Test
	public void testValueOfStringClassOfT() throws IOException {
		JSONObject jsonObj = JSONUtils.valueOf("{\"optionName\":\"jsonName1\",\"optionValues\":[\"jsonValue1\"]}",
				JSONObject.class);
		Assert.assertNotNull(jsonObj);
	}

	@Test
	public void testValueOfStringTypeReferenceOfListOfT() throws IOException {
		List<JSONObject> jsonObjList = JSONUtils.getList(jsonStr, new TypeReference<List<JSONObject>>() {
		});
		Assert.assertTrue(!jsonObjList.isEmpty());
	}

	@Test
	public void testValueOfStringTypeReferenceOfMapOfKT() throws IOException {
		Map<String, JSONObject> jsonObjMap = JSONUtils.getMap(
				"{\"name\":{\"optionName\":\"jsonName1\",\"optionValues\":[\"jsonValue1\"]}}",
				new TypeReference<Map<String, JSONObject>>() {
				});
		Assert.assertNotNull(jsonObjMap.get("name"));
	}

}
