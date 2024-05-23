package com.blaze.runner.Modules.JSON;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Libary.ValueUtils;
import org.json.JSONException;
import org.json.JSONTokener;

public final class JSONDecode implements Function {

    @Override
    public Value execute(Value... args) {
        Arguments.check(1, args.length);
        try {
            final String jsonRaw = args[0].asString();
            final Object root = new JSONTokener(jsonRaw).nextValue();
            return ValueUtils.toValue(root);
        } catch (JSONException ex) {
            throw new RuntimeException("Error while parsing json", ex);
        }
    }
}
