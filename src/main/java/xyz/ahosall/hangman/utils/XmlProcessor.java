package xyz.ahosall.hangman.utils;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class XmlProcessor {
  public String processXml(String xmlString, String tagName) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();

    InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
    Document document = builder.parse(inputStream);

    document.getDocumentElement().normalize();

    NodeList nodeList = document.getElementsByTagName(tagName);
    if (nodeList.getLength() > 0) {
      return nodeList.item(0).getTextContent();
    }

    return null;
  }
}
