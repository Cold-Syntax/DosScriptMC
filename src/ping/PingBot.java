package ping;

import java.net.Proxy;
import ping.Options;
import ping.ProxyManager;
import ping.Run;

public class PingBot
{
    private final Options options;

    public PingBot(Options options)
    {
        this.options = options;
    }

    public void launch() throws InterruptedException
    {
        if(!this.options.isOption("host"))
        {
            System.out.println("\n");
        }
    }
}
