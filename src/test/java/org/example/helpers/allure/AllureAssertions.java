package org.example.helpers.allure;

import io.qameta.allure.Step;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class AllureAssertions {

    @Step("Проверка: {msg}")
    public static void assertEquals(Object expected, Object actual, String msg) {
        Assertions.assertEquals(expected, actual, msg);
    }

    @Step("Проверка: {msg}")
    public static void assertNotEquals(Object expected, Object actual, String msg) {
        Assertions.assertNotEquals(expected, actual, msg);
    }

    @Step("Проверка: {msg}")
    public static void assertTrue(boolean actual, String msg) {
        Assertions.assertTrue(actual, msg);
    }

    @Step("Проверка: {msg}")
    public static void assertArrays(List<Object> expected, List<Object> actual, String msg) {
        Assertions.assertArrayEquals(new List[]{expected}, new List[]{actual}, msg);
    }

    @Step("Проверка: {msg}")
    public static void assertSimilar(JSONObject expected, JSONObject actual, String msg) {
        Assertions.assertTrue(expected.similar(actual), msg);
    }

    @Step("Проверка: {msg}")
    public static void assertStartsWith(String str, String expectedPrefix, String msg) {
        Assertions.assertTrue(str.startsWith(expectedPrefix), msg);
    }

    @Step("Проверка: {msg}")
    public static void assertContains(String str, String expectedSubstr, String msg) {
        Assertions.assertTrue(str.contains(expectedSubstr), msg);
    }

    @Step("Проверка: {msg}")
    public static void assertNotContains(String str, String expectedSubstr, String msg) {
        Assertions.assertFalse(str.contains(expectedSubstr), msg);
    }

    @Step("Проверка: {msg}")
    public static void assertNotNull(Object actual, String msg) {
        Assertions.assertNotNull(actual, msg);
    }

    @Step("Проверка: {msg}")
    public static void assertNull(Object actual, String msg) {
        Assertions.assertNull(actual, msg);
    }

}
