package training.supportbank.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;

public class XMLToolkit {
    public static final XStream xstream = setUpXStream();

    private static XStream setUpXStream() {
        XStream xstream = new XStream();
        xstream.processAnnotations(new Class[] {XMLParties.class, XMLTransactionList.class, XMLRecord.class});
        xstream.addPermission(NoTypePermission.NONE);
        xstream.allowTypes(new Class[]{XMLTransactionList.class, XMLRecord.class});
        return xstream;
    }
}
