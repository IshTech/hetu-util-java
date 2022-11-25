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
	@Order(2)
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
		String hetu = "321316Q855T";
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
		String hetu = "271016Q8550";
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
	@Order(24)
	public void testIsValidDateOfBirthValid() {
		String hetu = "010216-855Y";
		boolean actual = HeTuUtil.isValidDateOfBirth(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(25)
	public void testIsValidByRegexAndTheSeventhCharIsB() {
		String hetu = "130707B591W";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(26)
	public void testIsValidByRegexAndTheSeventhCharIsC() {
		String hetu = "070503C194K";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(27)
	public void testIsValidByRegexAndTheSeventhCharIsD() {
		String hetu = "080805D1989";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(28)
	public void testIsValidByRegexAndTheSeventhCharIsE() {
		String hetu = "210808E086U";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(29)
	public void testIsValidByRegexAndTheSeventhCharIsF() {
		String hetu = "040103F199K";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(30)
	public void testIsValidByRegexAndTheSeventhCharIsU() {
		String hetu = "110797U8794";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}
	@Test
	@Order(31)
	public void testIsValidByRegexAndTheSeventhCharIsV() {
		String hetu = "041197V8286";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}
	@Test
	@Order(32)
	public void testIsValidByRegexAndTheSeventhCharIsW() {
		String hetu = "250795W051X";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}
	@Test
	@Order(33)
	public void testIsValidByRegexAndTheSeventhCharIsX() {
		String hetu = "030898X331B";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}
	@Test
	@Order(34)
	public void testIsValidByRegexAndTheSeventhCharIsY() {
		String hetu = "140996Y756E";
		boolean actual = HeTuUtil.isValidByRegex(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(35)
	public void testToDateOfBirthTwentyAndTheSeventhCharIsB() {
		String hetu = "130707B591W";
		LocalDate expected = LocalDate.parse("2007-07-13");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(36)
	public void testToDateOfBirthTwentyAndTheSeventhCharIsC() {
		String hetu = "070503C194K";
		LocalDate expected = LocalDate.parse("2003-05-07");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(37)
	public void testToDateOfBirthTwentyAndTheSeventhCharIsD() {
		String hetu = "080805D1989";
		LocalDate expected = LocalDate.parse("2005-08-08");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(38)
	public void testToDateOfBirthTwentyAndTheSeventhCharIsE() {
		String hetu = "210808E086U";
		LocalDate expected = LocalDate.parse("2008-08-21");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(39)
	public void testToDateOfBirthTwentyAndTheSeventhCharIsF() {
		String hetu = "040103F199K";
		LocalDate expected = LocalDate.parse("2003-01-04");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(40)
	public void testToDateOfBirthNineteenAndTheSeventhCharIsU() {
		String hetu = "110797U8794";
		LocalDate expected = LocalDate.parse("1997-07-11");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(41)
	public void testToDateOfBirthNineteenAndTheSeventhCharIsV() {
		String hetu = "041197V8286";
		LocalDate expected = LocalDate.parse("1997-11-04");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(42)
	public void testToDateOfBirthNineteenAndTheSeventhCharIsW() {
		String hetu = "250795W051X";
		LocalDate expected = LocalDate.parse("1995-07-25");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(43)
	public void testToDateOfBirthNineteenAndTheSeventhCharIsX() {
		String hetu = "030898X331B";
		LocalDate expected = LocalDate.parse("1998-08-03");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(44)
	public void testToDateOfBirthNineteenAndTheSeventhCharIsY() {
		String hetu = "140996Y756E";
		LocalDate expected = LocalDate.parse("1996-09-14");
		LocalDate actual = HeTuUtil.toDateOfBirth(hetu);

		assertEquals(expected, actual);
	}

	@Test
	@Order(45)
	public void testIsValidChecksumAndTheSeventhCharIsA() {
		String hetu = "010216-855Y";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(46)
	public void testIsValidChecksumAndTheSeventhCharIsB() {
		String hetu = "130707B591W";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(47)
	public void testIsValidChecksumAndTheSeventhCharIsC() {
		String hetu = "070503C194K";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(48)
	public void testIsValidChecksumAndTheSeventhCharIsD() {
		String hetu = "080805D1989";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(49)
	public void testIsValidChecksumAndTheSeventhCharIsE() {
		String hetu = "210808E086U";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(50)
	public void testIsValidChecksumAndTheSeventhCharIsF() {
		String hetu = "040103F199K";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(51)
	public void testIsValidChecksumAndTheSeventhCharIsU() {
		String hetu = "110797U8794";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(52)
	public void testIsValidChecksumAndTheSeventhCharIsV() {
		String hetu = "041197V8286";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(53)
	public void testIsValidChecksumAndTheSeventhCharIsW() {
		String hetu = "250795W051X";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(54)
	public void testIsValidChecksumAndTheSeventhCharIsX() {
		String hetu = "030898X331B";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

	@Test
	@Order(55)
	public void testIsValidChecksumAndTheSeventhCharIsY() {
		String hetu = "140996Y756E";
		boolean actual = HeTuUtil.isValidChecksum(hetu);
		assertTrue(actual);
	}

}
