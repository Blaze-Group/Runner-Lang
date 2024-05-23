package com.blaze.runner.Modules.WebAPI;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Value;
import java.io.IOException;
import java.net.URLEncoder;

public final class URLEncode implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.checkOrOr(1, 2, args.length);
        
        String charset = "UTF-8";
        if (args.length >= 2) {
            charset = args[1].asString();
        }
        
        try {
            final String result = URLEncoder.encode(args[0].asString(), charset);
            return new StringValue(result);
        } catch (IOException ex) {
            return args[0];
        }
    }
}