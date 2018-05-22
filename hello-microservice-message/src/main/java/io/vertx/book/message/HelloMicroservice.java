package io.vertx.book.message;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;

public class HelloMicroservice extends AbstractVerticle {

    @Override
    public void start() {

        vertx.eventBus().<String>consumer("hello", message -> {

            double chaos = Math.random();
            JsonObject json = new JsonObject().put("served-by", this.toString());


            if(chaos < 0.6){
                //Normal behavior
                if(message.body().isEmpty()){
                    message.reply(json.put("message","hello"));
                }else{
                    message.reply(json.put("message", "hello " + message.body()));
                }
            }else if(chaos < 0.9){
                System.err.println("Returning a failure");
                message.fail(500, "message processing failure");
            }else{
                System.err.println("Not replying");
            }


        });

    }

}
