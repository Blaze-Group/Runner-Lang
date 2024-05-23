package com.blaze.runner.Modules.JSON;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Runtime.Values.ArrayValue;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Libary.Types;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Libary.ValueUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;


public final class JSONEncode implements Function {

    @Override
    public Value execute(Value[] args) {
        Arguments.checkOrOr(1, 2, args.length);
        try {
            final int indent;
            if (args.length == 2) {
                indent = args[1].asInt();
            } else {
                indent = 0;
            }

            final String jsonRaw;
            if (indent > 0) {
                jsonRaw = format(args[0], indent);
            } else {
                final Object root = ValueUtils.toObject(args[0]);
                jsonRaw = JSONWriter.valueToString(root);
            }
            return new StringValue(jsonRaw);

        } catch (JSONException ex) {
            throw new RuntimeException("Error while creating json", ex);
        }
    }

    private String format(Value val, int indent) {
        switch (val.type()) {
            case Types.ARRAY:
                return ValueUtils.toObject((ArrayValue) val).toString(indent);
            case Types.MAP:
                return ValueUtils.toObject((MapValue) val).toString(indent);
            case Types.NUMBER:
                return JSONWriter.valueToString(val.raw());
            case Types.STRING:
                return JSONWriter.valueToString(val.asString());
            default:
                return JSONWriter.valueToString(JSONObject.NULL);
        }
    }
}