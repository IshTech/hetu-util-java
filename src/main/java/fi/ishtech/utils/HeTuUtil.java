/**
 * 
 */
package fi.ishtech.utils;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Util class for Finnish Social Security ID / Henkilo tunnus
 *
 * @author Muneer Ahmed Syed
 */
public class HeTuUtil {

	private static final String PLUS = "+";
	private static final String HYPHEN = "-";
	private static final String A = "A";

	private static final String EIGHTEEN = "18";
	private static final String NINETEEN = "19";
	private static final String TWENTY = "20";

	private static final String CHECKSUM_STR = "0123456789ABCDEFHJKLMNPRSTUVWXY";
	private static final int CHECKSUM_NUM = 31;

	public static final String SIMPLE_REGEX = "[0-9]{6}[A\\-]{1}[0-9]{3}[A-Z0-9]{1}";
	public static final String COMP_REGEX = "[0-9]{6}[A\\-+]{1}[0-9]{3}[A-Z0-9]{1}";

	/**
	 * Validates using regular expression [0-9]{6}[A\\-]{1}[0-9]{3}[A-Z0-9]{1}
	 *
	 * @param hetu - henkilotunnus
	 * @return boolean - true if valid, false if invalid
	 */
	public static boolean isValidByRegex(String hetu) {
		if (hetu == null) {
			return false;
		}

		return Pattern.matches(SIMPLE_REGEX, hetu);
	}

	/**
	 * Validates using regular expression [0-9]{6}[A\\-]{1}[0-9]{3}[A-Z0-9]{1} and also checks if the date is valid
	 *
	 * @param hetu - henkilotunnus
	 * @return boolean - true if valid, false if invalid
	 */
	public static boolean isValid(String hetu) {
		if (isValidByRegex(hetu)) {
			try {
				toDateOfBirth(hetu);
				return true;
			} catch (Exception e) {
				return false;
			}
		}

		return false;
	}

	/**
	 * Finds Date of birth of person based on Henkilo-tunnus (Finnish Social Security Id)
	 *
	 * @param hetu - henkilotunnus
	 * @return {@link LocalDate} date-of-birth
	 */
	public static LocalDate toDateOfBirth(String hetu) {
		if (hetu == null) {
			throw new NullPointerException("Input cannot be null");
		}

		if (hetu.trim().isEmpty()) {
			throw new RuntimeException("Input cannot be empty");
		}

		// TODO: validate format

		String d = hetu.substring(0, 2);
		String m = hetu.substring(2, 4);
		String y = hetu.substring(4, 6);

		String yearChecker = hetu.substring(6, 7);

		if (A.equalsIgnoreCase(yearChecker)) {
			y = TWENTY + y;
		} else if (HYPHEN.equals(yearChecker)) {
			y = NINETEEN + y;
		} else if (PLUS.equals(yearChecker)) {
			y = EIGHTEEN + y;
		} else {
			throw new RuntimeException("Invalid input, only '+' or '-' or 'A' allowed at 7 th character");
		}

		LocalDate result = LocalDate.of(Integer.parseInt(y), Integer.parseInt(m), Integer.parseInt(d));

		return result;
	}

	public static boolean isValidChecksum(String hetu) {
		if (isValidByRegex(hetu)) {
			String ddmmyy = hetu.substring(0, 6);
			String zzz = hetu.substring(7, 10);

			char actualQ = hetu.charAt(10);
			char expectedQ = checksum(ddmmyy, zzz);

			return (actualQ == expectedQ);
		}

		return false;
	}

	private static char checksum(String ddmmyy, String zzz) {
		if (ddmmyy == null) {
			throw new NullPointerException("Input ddmmyy cannot be null");
		}

		if (ddmmyy.trim().length() != 6) {
			throw new RuntimeException("Invalid input ddmmyy" + ddmmyy);
		}

		Integer.parseInt(zzz);

		if (zzz == null) {
			throw new NullPointerException("Input zzz cannot be null");
		}

		if (zzz.trim().length() != 3) {
			throw new RuntimeException("Invalid input zzz " + zzz);
		}

		Integer.parseInt(zzz);

		Integer ddmmyyzzz = Integer.parseInt(ddmmyy.concat(zzz));
		int n = ddmmyyzzz % CHECKSUM_NUM;

		char q = CHECKSUM_STR.charAt(n);

		return q;
	}

}
