package cn.thc.handlexml.dom;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by thc on 2016/12/24
 */
//http://www.ibm.com/developerworks/cn/xml/dm-1208gub/
public class DOMParser {
    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    public Document parse(String filePath){
        Document document = null;
        //DOM parser instance
        DocumentBuilder builder = null;
        try {
            builder = builderFactory.newDocumentBuilder();
            //parse an XML file into a DOM tree
            document = builder.parse(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    public static void main(String[] args) {
        DOMParser domParser = new DOMParser();
        Document document = domParser.parse("src\\main\\java\\cn\\thc\\handlexml\\books.xml");

        //get root element
        Element rootElement = document.getDocumentElement();

        //traverse child elements
        NodeList nodes = rootElement.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element clild = (Element)node;
                String id = clild.getAttribute("id");
                System.out.println(id);
                //process child element
            }
        }

        NodeList nodeList = rootElement.getElementsByTagName("book");
        if(nodeList != null){
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String id = element.getAttribute("id");
            }
        }
    }
}
