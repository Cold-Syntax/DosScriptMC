package ping;

import ping.Options;
import ping.PingBot;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        PingBot breaker = new PingBot(Options.Builder.of(args));
        breaker.launch();
    }
}
