package fi.ishtech.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class HeTuUtilTest {

	@Test
	@Order(1)
	public void testIsValidByRegexNull() {
		String hetu = null;
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(1)
	public void testIsValidByRegexBlank() {
		String hetu = "";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(3)
	public void testIsValidByRegexALastCharAlpha() {
		String hetu = "321316A855X";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(4)
	public void testIsValidByRegexALastCharNum() {
		String hetu = "321316A8550";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(5)
	public void testIsValidByRegexHyphenLastCharNum() {
		String hetu = "321316-8550";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(6)
	public void testIsValidByRegexHyphenLastCharAlpha() {
		String hetu = "321316-855T";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(7)
	public void testIsValidByRegexHyphenLastCharDifferent() {
		String hetu = "321316-855+";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(8)
	public void testIsValidByRegexCenturyCharDifferent() {
		String hetu = "321316B855T";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(9)
	public void testIsValidByRegexCharsInDate() {
		String hetu = "A21316-855J";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(10)
	public void testIsValidByRegexCharsInMonth() {
		String hetu = "32C316-855J";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(11)
	public void testIsValidByRegexCharsInYear() {
		String hetu = "3213E6-855J";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(12)
	public void testToDateOfBirthNineteen() {
		String hetu = "010216-8550";
		LocalDate expected = LocalDate.parse("1916-02-01");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(13)
	public void testToDateOfBirthTwenty() {
		String hetu = "290216A8550";
		LocalDate expected = LocalDate.parse("2016-02-29");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(14)
	public void testToDateOfBirthInvalidNull() {
		String hetu = null;
		assertThrows(NullPointerException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(15)
	public void testToDateOfBirthInvalidBlank() {
		String hetu = " ";
		assertThrows(RuntimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(16)
	public void testToDateOfBirthInvalidCentury() {
		String hetu = "271016B8550";
		assertThrows(RuntimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(17)
	public void testToDateOfBirthInvalidNumber() {
		String hetu = "ABCDEF-GHIJ";
		assertThrows(NumberFormatException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(18)
	public void testToDateOfBirthInvalidMonth() {
		String hetu = "311316A8550";
		assertThrows(DateTimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(19)
	public void testToDateOfBirthInvalidDate32() {
		String hetu = "321216A8550";
		assertThrows(DateTimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(20)
	public void testToDateOfBirthInvalidDateNon31() {
		String hetu = "311116A8550";
		assertThrows(DateTimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(21)
	public void testToDateOfBirthInvalidDateNonLeap() {
		String hetu = "290215A855X";
		assertThrows(DateTimeException.class, () -> HeTuUtil.toDateOfBirth(hetu));
	}

	@Test
	@Order(22)
	public void testIsValidChecksum() {
		String hetu = "010216-855Y";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(23)
	public void testIsValidDateOfBirthInvalid() {
		String hetu = "290215A855X";
		boolean actual = HeTuUtil.isValidDateOfBirth(hetu);
		assertFalse(actual);
	}

	@Test
	@Order(23)
	public void testIsValidDateOfBirthValid() {
		String hetu = "010216-855Y";
		boolean actual = HeTuUtil.isValidDateOfBirth(hetu);
		assertTrue(actual);
	}

}
