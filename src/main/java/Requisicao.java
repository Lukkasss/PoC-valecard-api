/**
 * Created by IntelliJ IDEA.
 * User: Lucas Vilela
 * Date: 07/10/2018
 * Time: 14:27
 * To change this template use File | Settings | File and Code Templates.
 */
import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

public class Requisicao {
    @Getter @Setter private String baseURL, tipo, endpoint;
    @Getter @Setter private HashMap<String, String> headers, payload = new HashMap<>();

    public Requisicao() {
    }

    public Requisicao(String baseURL) {
        this.baseURL = baseURL;
    }

    public Requisicao(String baseURL, String tipo, String endpoint) {
        this.baseURL = baseURL;
        this.tipo = tipo;
        this.endpoint = endpoint;
    }

    public Requisicao(String baseURL, String tipo, String endpoint, HashMap<String, String> headers) {
        this.baseURL = baseURL;
        this.tipo = tipo;
        this.endpoint = endpoint;
        this.headers = headers;
    }

    public String abrir(){
        Document doc = null;
        try {
            if (this.tipo.equals("GET")) {
                doc = Jsoup.connect(String.format("%s%s",this.getBaseURL(), this.getEndpoint()))
                        .header("X-Auth-Token", "") //Auth, necessário fazer a rotina para pegar ou colocar manualmente.
                        .data(this.getPayload())
                        .followRedirects(true)
                        .ignoreContentType(true)
                        .get();
            } else if (this.tipo.equals("POST")) {
                doc = Jsoup.connect(String.format("%s%s", this.getBaseURL(), this.getEndpoint()))
                        .data(this.getPayload())
                        .ignoreContentType(true)
                        .post();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return doc.body().html();
    }
}
