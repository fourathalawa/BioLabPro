package tn.esprit.biol.service;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.lookups.v1.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    private final TwilioRestClient client;

  // @Value("${twilio.accountSid}")
    private String accountSid="ACbadd0dcac66727e50e266ab2513d29f2";

    //@Value("${twilio.authToken}")
    private String authToken="7c4105f794405c76ded0b09a6825395b";

    //@Value("${twilio.fromPhoneNumber}")
    private String fromPhoneNumber="+14344742778";

    public SmsService() {
        client = new TwilioRestClient.Builder(accountSid, authToken).build();
    }

    public void sendSms(String toPhoneNumber, String message) {
        try {
            Message sms = Message.creator(new com.twilio.type.PhoneNumber(toPhoneNumber), new com.twilio.type.PhoneNumber(fromPhoneNumber), message).create(client);
            System.out.println(sms.getSid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
