public class Main {
    public static void main(String[] args) throws Exception{
        if (args.length != 2) {
            System.out.println("Please consider an address and a number of messages");
            return;
        }
        Consumer.receive(args[0]);
        for (int i = 0; i < Integer.valueOf(args[1]); i++) {
            Producer.send(args[0]);
        }
    }
}
