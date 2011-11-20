package nextQuest.ifc;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class Static
{

    public final static String MD5(String subj) throws nqException, NoSuchAlgorithmException
    {
	MessageDigest m = MessageDigest.getInstance("MD5");
	m.update(subj.getBytes(), 0, subj.length());
	BigInteger bi = new BigInteger(1, m.digest());
	return String.format("%1$032X", bi).toLowerCase();
    }

    public final static String Base64encode(byte[] arr)
    {
	return DatatypeConverter.printBase64Binary(arr);
    }

    public final static byte[] Base64decode(String base64) throws IOException
    {
	return DatatypeConverter.parseBase64Binary(base64);
    }
}
