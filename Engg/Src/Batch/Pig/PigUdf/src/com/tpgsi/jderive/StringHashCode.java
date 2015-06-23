package com.tpgsi.jderive;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * 
 * @author Shridhar
 *
 * Pig UDF to generate 64 bit hashcode for a given String.
 */
public class StringHashCode extends EvalFunc<Long> {

	private static long[] byteTable = createLookupTable();
	private static final long HSTART = 0xBB40E64DA205B064L;
	private static final long HMULT = 7664345821815920749L;

	public Long exec(Tuple input) throws IOException {
		if (input == null || input.size() == 0)
			return null;
		try {
			String str = (String) input.get(0);
			if (str != null) {
				return hash(str);
			} else {
				return null;
			}

		} catch (Exception e) {
			throw new IOException("Caught exception processing input row ", e);
		}
	}

	/**
	 * Method to get 64 bit hash code for given String
	 * @param cs Character sequence to generate hash code
	 * @return Returns 64 bit has code of {@link Long} type 
	 */
	public static long hash(CharSequence cs) {
		long h = HSTART;
		final long hmult = HMULT;
		final long[] ht = byteTable;
		final int len = cs.length();
		for (int i = 0; i < len; i++) {
			char ch = cs.charAt(i);
			h = (h * hmult) ^ ht[ch & 0xff];
			h = (h * hmult) ^ ht[(ch >>> 8) & 0xff];
		}
		return h;
	}

	private static final long[] createLookupTable() {
		byteTable = new long[256];
		long h = 0x544B2FBACAAF1684L;
		for (int i = 0; i < 256; i++) {
			for (int j = 0; j < 31; j++) {
				h = (h >>> 7) ^ h;
				h = (h << 11) ^ h;
				h = (h >>> 10) ^ h;
			}
			byteTable[i] = h;
		}
		return byteTable;
	}

}
