package br.com.appback.controller;

import br.com.appback.model.Agente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class FileXmlUploadController {

    //recebendo o arquivo xml
    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) throws IOException {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file.getInputStream());
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("agente");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
                    System.out.println("Código de agente recebido :" + codigo);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        System.err.println("Arquivo " + fileName + " enviado com sucesso!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //recebendo o json do xml sem o precoMedio
    @PostMapping("/uploadJson")
    public ResponseEntity<List<Agente>> jsonFileUpload(@RequestBody List<Agente> agentes ) throws IOException {
        agentes.forEach(agente -> {
            System.out.println("Código de agente recebido :" + agente.getCodigo());
        });

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
