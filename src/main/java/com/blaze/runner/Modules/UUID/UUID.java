package com.blaze.runner.Modules.UUID;

import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import java.util.Random;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class UUID implements Module {
    @Override
    public void init() {
        MapValue UUIDMap = new MapValue(5);

        UUIDMap.set("create", new Function() {
            @Override
            public Value execute(Value... args) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    throw new RuntimeException(e);
                }
                String ip = inetAddress.getHostAddress();
                ip = ip.replace(".", "");
                String UUIDT = "ghur5etQ123F#*$FDShIdasd@";
                Random random1 = new Random();
                int UUIDI = random1.nextInt(100000, 999999999);
                String full = String.format("%s%s%s", UUIDT, ip, String.valueOf(UUIDI));
                return new StringValue(full);
            }
        });

        Variables.define("UUID", UUIDMap);
    }
}
