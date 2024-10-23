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
            System.out.println("\n Example: java -jar MinePing.jar host-<IP> port-<PORT> threads-<THREADS>");
            return;
        }

        String proxiesType = this.options.getOption("proxymode--", "socks");
        String proxiesFile = this.options.getOption("file", "socks_proxies.txt");
        ProxyManager proxies = new ProxyManager();
        try
        {
            if(proxiesType.equalsIgnoreCase("socks"))
            {
                proxies.init(proxiesFile, Proxy.Type.SOCKS);
            }
        }
        catch (Exception ex)
        {
            return;
        }

        //ASCII ART
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XX                                                                          XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMMMMMMssssssssssssssssssssssssssMMMMMMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMss'''                          '''ssMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMyy''                                    ''yyMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMyy''                                            ''yyMMMMMMMM   XX");
        System.out.println("XX   MMMMMy''                                                    ''yMMMMM   XX");
        System.out.println("XX   MMMy'                                                          'yMMM   XX");
        System.out.println("XX   Mh'                                                              'hM   XX");
        System.out.println("XX   -                                                                  -   XX");
        System.out.println("XX                                                                          XX");
        System.out.println("XX   ::                                                                ::   XX");
        System.out.println("XX   MMhh.        ..hhhhhh..                      ..hhhhhh..        .hhMM   XX");
        System.out.println("XX   MMMMMh   ..hhMMMMMMMMMMhh.                .hhMMMMMMMMMMhh..   hMMMMM   XX");
        System.out.println("XX   ---MMM .hMMMMdd:::dMMMMMMMhh..        ..hhMMMMMMMd:::ddMMMMh. MMM---   XX");
        System.out.println("XX   MMMMMM MMmm''      'mmMMMMMMMMyy.  .yyMMMMMMMMmm'      ''mmMM MMMMMM   XX");
        System.out.println("XX   ---mMM ''             'mmMMMMMMMM  MMMMMMMMmm'             '' MMm---   XX");
        System.out.println("XX   yyyym'    .              'mMMMMm'  'mMMMMm'              .    'myyyy   XX");
        System.out.println("XX   mm''    .y'     ..yyyyy..  ''''      ''''  ..yyyyy..     'y.    ''mm   XX");
        System.out.println("XX           MN    .sMMMMMMMMMss.   .    .   .ssMMMMMMMMMs.    NM           XX");
        System.out.println("XX           N`    MMMMMMMMMMMMMN   M    M   NMMMMMMMMMMMMM    `N           XX");
        System.out.println("XX            +  .sMNNNNNMMMMMN+   `N    N`   +NMMMMMNNNNNMs.  +            XX");
        System.out.println("XX              o+++     ++++Mo    M      M    oM++++     +++o              XX");
        System.out.println("XX                                oo      oo                                XX");
        System.out.println("XX           oM                 oo          oo                 Mo           XX");
        System.out.println("XX         oMMo                M              M                oMMo         XX");
        System.out.println("XX       +MMMM                 s              s                 MMMM+       XX");
        System.out.println("XX      +MMMMM+            +++NNNN+        +NNNN+++            +MMMMM+      XX");
        System.out.println("XX     +MMMMMMM+       ++NNMMMMMMMMN+    +NMMMMMMMMNN++       +MMMMMMM+     XX");
        System.out.println("XX     MMMMMMMMMNN+++NNMMMMMMMMMMMMMMNNNNMMMMMMMMMMMMMMNN+++NNMMMMMMMMM     XX");
        System.out.println("XX     yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy     XX");
        System.out.println("XX   m  yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy  m   XX");
        System.out.println("XX   MMm yMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMy mMM   XX");
        System.out.println("XX   MMMm .yyMMMMMMMMMMMMMMMM     MMMMMMMMMM     MMMMMMMMMMMMMMMMyy. mMMM   XX");
        System.out.println("XX   MMMMd   ''''hhhhh       odddo          obbbo        hhhh''''   dMMMM   XX");
        System.out.println("XX   MMMMMd             'hMMMMMMMMMMddddddMMMMMMMMMMh'             dMMMMM   XX");
        System.out.println("XX   MMMMMMd              'hMMMMMMMMMMMMMMMMMMMMMMh'              dMMMMMM   XX");
        System.out.println("XX   MMMMMMM-               ''ddMMMMMMMMMMMMMMdd''               -MMMMMMM   XX");
        System.out.println("XX   MMMMMMMM                   '::dddddddd::'                   MMMMMMMM   XX");
        System.out.println("XX   MMMMMMMM-                                                  -MMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMM                                                  MMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMy                                                yMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMy.                                            .yMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMy.                                        .yMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMy.                                    .yMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMs.                                .sMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMMMss.           ....           .ssMMMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX   MMMMMMMMMMMMMMMMMMMMNo         oNNNNo         oNMMMMMMMMMMMMMMMMMMMM   XX");
        System.out.println("XX                                                                          XX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println("");
        System.out.println("    .o88o.                               o8o                .");
        System.out.println("    888 `\"                               `\"'              .o8");
        System.out.println("   o888oo   .oooo.o  .ooooo.   .ooooo.  oooo   .ooooo.  .o888oo oooo    ooo");
        System.out.println("    888    d88(  \"8 d88' `88b d88' `\"Y8 `888  d88' `88b   888    `88.  .8'");
        System.out.println("    888    `\"Y88b.  888   888 888        888  888ooo888   888     `88..8'");
        System.out.println("    888    o.  )88b 888   888 888   .o8  888  888    .o   888 .    `888'");
        System.out.println("   o888o   8\"\"888P' `Y8bod8P' `Y8bod8P' o888o `Y8bod8P'   \"888\"      d8'");
        System.out.println("                                                                .o...P'");
        System.out.println("                                                                `XER0'");


        new Run(this.options, proxies).run();
    }
}
