package paramonov.valentine.web_crawler.site_checker;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class UrlNormalizerTest {
    @Test
    public void shouldAddHttp() {
        String normalized = UrlNormalizer.normalize("abc.de");
        assertThat(normalized, is(equalTo("http://abc.de")));
    }   
    
    @Test
    public void shouldNotAddHttpIfStartsWithHttp() {
        String normalized = UrlNormalizer.normalize("http://abc.de");
        assertThat(normalized, is(equalTo("http://abc.de")));
    }    
    
    @Test
    public void shouldNotAddHttpIfStartsWithHttps() {
        String normalized = UrlNormalizer.normalize("https://abc.de");
        assertThat(normalized, is(equalTo("https://abc.de")));
    }  
}