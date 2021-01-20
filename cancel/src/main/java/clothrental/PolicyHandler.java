package clothrental;

import clothrental.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    CancelRepository cancelRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrdered_Ship(@Payload Ordered ordered){

        if(ordered.isMe()){
            // To-Do : SMS발송, CJ Logistics 연계, ...
            Cancel cancel = new Cancel();
            cancel.setOrderId(ordered.getId());
            cancel.setStatus("cancel Started");

            cancelRepository.save(cancel);

            System.out.println("##### listener Ship : " + ordered.toJson());
        }
    }


}
