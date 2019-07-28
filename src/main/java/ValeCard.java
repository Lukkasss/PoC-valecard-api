import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Lucas Vilela
 * Date: 08/01/2019
 * Time: 18:17
 * To change this template use File | Settings | File and Code Templates.
 */
public class ValeCard {
    @Getter @Setter private Double saldo, consumo, gastoDiario;
    @Getter @Setter private Long ultimoCredito;

    public ValeCard() {
        //this.getInfoFromAPI();
    }

    public ValeCard(Double saldo, Double consumo, Double gastoDiario, Long ultimoCredito) {
        this.saldo = saldo;
        this.consumo = consumo;
        this.gastoDiario = gastoDiario;
        this.ultimoCredito = ultimoCredito;
    }

    public String getUltimoCredito(Long timestamp) {
        Timestamp stamp = new Timestamp(timestamp);
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(stamp.getTime());

        return fmt.format(date);
    }

    @Override
    public String toString() {
        return String.format("Saldo: R$%.2f\n" +
                        "Gasto Diário: R$%.2f\n" +
                        "Consumo Médio: R$%.2f\n" +
                        "Data do Último Crédito: %s",
                this.getSaldo(),
                this.getGastoDiario(),
                this.getConsumo(),
                this.getUltimoCredito(this.getUltimoCredito()));
    }

    public void getInfoFromAPI() {
        try {
            Requisicao req = new Requisicao();
            req.setBaseURL("http://api.valecard.com.br");
            req.setEndpoint("/mobile-api/services/mobile/saldo");
            req.setTipo("GET");

            JSONObject json = new JSONObject(req.abrir());
            this.setSaldo((Double) json.get("saldo"));
            this.setConsumo((Double) json.get("consumoMedio"));
            this.setGastoDiario((Double) json.get("gastoDiario"));
            this.setUltimoCredito((Long) json.get("dataUltimoCredito"));
            this.getSaldo();

        } catch (Exception e) {

        }
    }
}
