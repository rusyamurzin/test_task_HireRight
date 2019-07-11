import java.util.Random;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class Producer {
    private final static String [] messages = {"Hello World!", "I want to break free!", "HireRight is the best!"};
    private final static Random random = new Random();
    private static String QUEUE_NAME = "hello";

    public static void send(String address) throws Exception {
        //create a connection to the server
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(address);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            //declaring a queue (idempotent operation)
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //message sending
            String message = messages[random.nextInt(3)];
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }

    public static void setQueueName(String name) {
        QUEUE_NAME = name;
    }

    public static String getQueueName() {
        return QUEUE_NAME;
    }
}
