import com.kl.wework.Wework;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class test_gettoken {
    @Test
    void testToken() {
        Wework wework = new Wework();
        String token = wework.getWeworkToken();
        assertThat(token, not(equalTo(null)));

    }


}
