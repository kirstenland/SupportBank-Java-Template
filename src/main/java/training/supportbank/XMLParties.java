package training.supportbank;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Parties")
public class XMLParties {
    public String getFromName() {
        return fromName;
    }

    public String getToName() {
        return toName;
    }

    @XStreamAlias("From")
    private String fromName;

    @XStreamAlias("To")
    private String toName;

}
