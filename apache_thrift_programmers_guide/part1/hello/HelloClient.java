import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.TException;
public class HelloClient {
    public static void main(String[] args) throws TException {
        TSocket trans = new TSocket("localhost", 9090);
        TBinaryProtocol protocol = new TBinaryProtocol(trans);
        HelloSvc.Client client = new HelloSvc.Client(protocol);
        trans.open();
        String str = client.hello_func();
        System.out.println("[Client] received: " + str);
        trans.close();
}
}
