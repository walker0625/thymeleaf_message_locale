package hello.itemservice.messages;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    void helloMessage() {
        String result = messageSource.getMessage("hello", null,null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null,null))
                                              .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void defaultMessage() {
        String message = messageSource.getMessage("no_code", null, "기본 메시지", null);
        assertThat(message).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage() {
        String message = messageSource.getMessage("hello.name", new Object[]{"Spring"}, "기본 메시지", null);
        assertThat(message).isEqualTo("안녕 Spring");
    }

    @Test
    void defaultLang() {
        String message = messageSource.getMessage("hello",  null, null);
        String message2 = messageSource.getMessage("hello",  null, Locale.KOREA);
        assertThat(message).isEqualTo("안녕");
        assertThat(message2).isEqualTo("안녕");
    }

    @Test
    void enLang() {
        String message = messageSource.getMessage("hello",  null, Locale.ENGLISH);
        assertThat(message).isEqualTo("hello");
    }

}
