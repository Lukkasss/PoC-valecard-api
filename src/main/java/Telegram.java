import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.abilitybots.api.bot.AbilityBot;

/**
 * Created by IntelliJ IDEA.
 * User: Lucas Vilela
 * Date: 23/10/2018
 * Time: 02:21
 * To change this template use File | Settings | File and Code Templates.
 */
public class Telegram extends AbilityBot{

    private static String BOT_TOKEN = ""; //Token
    private static String BOT_USERNAME = "ValeCardBot";
    ValeCard vc = new ValeCard();


    public Telegram() {
        super(BOT_TOKEN, BOT_USERNAME);
    }

    @Override
    public int creatorId() {
        return 78784524;
    }

    public Ability comandoSaldo() {
        return Ability
                .builder()
                .name("saldo")
                .info("Veja aqui o saldo do seu cartão.")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send(String.format("Saldo atual: R$%.2f\n", vc.getSaldo()), ctx.chatId());
                })
                .build();
    }

    public Ability comandoInfo() {
        return Ability
                .builder()
                .name("info")
                .info("Veja aqui o saldo do seu cartão.")
                .locality(Locality.ALL)
                .privacy(Privacy.PUBLIC)
                .action(ctx -> {
                    silent.send(String.format("%s\n", vc.toString()), ctx.chatId());
                })
                .build();
    }
}
