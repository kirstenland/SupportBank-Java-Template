package training.supportbank.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import training.supportbank.models.DataRecord;

import java.util.List;

@XStreamAlias("TransactionList")
public class XMLTransactionList {
    @XStreamImplicit
    public List<DataRecord> transactionList;
}
