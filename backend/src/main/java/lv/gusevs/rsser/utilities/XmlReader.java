package lv.gusevs.rsser.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

@Component
public class XmlReader {

    private URL url;

    public XmlReader of(String urlString) {
        setUrl(urlString);
        return this;
    }

    public Document parse() throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    }

    public Document parseSilently() {
        SAXReader reader = new SAXReader();
        return readDocument(reader);
    }

    private Document readDocument(SAXReader reader) {
        Document document;
        try {
            document = reader.read(url);
        } catch (DocumentException e) {
            throw new RuntimeException("Error! Could not read response from the url");
        }
        return document;
    }

    private void setUrl(String urlString) {
        try {
            this.url = new URL(urlString);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error! Impossible to create URL from string = " + urlString);
        }
    }

}
