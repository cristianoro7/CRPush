package com.desperado.access.sender;

import com.desperado.access.config.MQConfig;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by desperado on 17-9-1.
 */
public class Sender implements ISender {

    private Channel channel;

    private Connection connection;

    private SenderBuilder builder;

    public Sender(SenderBuilder builder) {

        ConnectionFactory factory = newConnectionFactory();
        factory.setHost(builder.host);
        connection = createConnection(factory);
        channel = createChannel(connection);
    }

    @Override
    public void send(byte[] msg) {
        try {
            channel.queueDeclare(builder.queueName, builder.durable, builder.exclusive, builder.autoDelete,
                    builder.arguments);
            channel.basicPublish(builder.exchangeName, builder.routingKey, builder.properties, msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(channel, connection);
        }
    }

    public static class SenderBuilder {
        private String host;
        private boolean durable;
        private boolean exclusive;
        private boolean autoDelete;
        private Map<String, Object> arguments;
        private String exchangeName;
        private String routingKey;
        private AMQP.BasicProperties properties;
        private String queueName;

        public SenderBuilder host(String host) {
            this.host = host;
            return this;
        }

        public SenderBuilder queueName(String queueName) {
            this.queueName = queueName;
            return this;
        }

        public SenderBuilder exchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
            return this;
        }

        public SenderBuilder durable(boolean durable) {
            this.durable = durable;
            return this;
        }

        public SenderBuilder exclusive(boolean exclusive) {
            this.exclusive = exclusive;
            return this;
        }

        public SenderBuilder autoDelete(boolean autoDelete) {
            this.autoDelete = autoDelete;
            return this;
        }

        public SenderBuilder arguments(Map<String, Object> arguments) {
            this.arguments = arguments;
            return this;
        }

        public SenderBuilder routingKey(String routingKey) {
            this.routingKey = routingKey;
            return this;
        }

        public SenderBuilder properties(AMQP.BasicProperties properties) {
            this.properties = properties;
            return this;
        }

        public Sender build() {
            return new Sender(this);
        }
    }

    private ConnectionFactory newConnectionFactory() {
        return new ConnectionFactory();
    }

    private Connection createConnection(ConnectionFactory factory) {
        try {
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Channel createChannel(Connection connection) {
        try {
            return connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void close(Channel channel, Connection connection) {
        try {
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public static Sender defaultSender() {
        return new SenderBuilder()
                .host("localhost")
                .exchangeName("")
                .queueName(MQConfig.QUEUE_NAME)
                .properties(null)
                .arguments(null)
                .durable(false)
                .exclusive(false)
                .routingKey(MQConfig.ROUTE_KEY)
                .autoDelete(false)
                .build();
    }
}
