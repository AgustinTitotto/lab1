package lab1.meetNGame.UI;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import java.util.List;

public class MessageForm {

    private final String message;

    public MessageForm(String message) {
        this.message = message;
    }

    public static MessageForm createMessageForm(List<String> message){
        return new MessageForm(message.get(0));
    }

    public static MessageForm createFromBody(String body){
        final MultiMap<String> params = new MultiMap<>();
        UrlEncoded.decodeTo(body, params, "UTF-8");
        return MessageForm.createMessageForm(
                params.getValues("message")
        );
    }

    public String getMessageContent() {
        return message;
    }
}
