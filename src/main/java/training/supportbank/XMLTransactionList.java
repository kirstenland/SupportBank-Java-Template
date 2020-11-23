package training.supportbank;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("TransactionList")
public class XMLTransactionList {
    @XStreamImplicit
    public List<DataRecord> transactionList;
}
