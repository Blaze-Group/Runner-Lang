package com.blaze.runner.Modules.TFont;

import com.blaze.runner.Libary.Arguments;
import com.blaze.runner.Libary.Function;
import com.blaze.runner.Modules.Module;
import com.blaze.runner.Runtime.Value;
import com.blaze.runner.Runtime.Values.MapValue;
import com.blaze.runner.Runtime.Values.StringValue;
import com.blaze.runner.Runtime.Variables;

public class TFont implements Module {
    private final String[][] letters = {
            {
                    "    _   ",
                    "  ___ ",
                    "   ___ ",
                    "  ___  ",
                    "  ___ ",
                    "  ___ ",
                    "   ___ ",
                    "  _  _ ",
                    "  ___ ",
                    "     _ ",
                    "  _  __",
                    "  _    ",
                    "  __  __ ",
                    "  _  _ ",
                    "   ___  ",
                    "  ___ ",
                    "   ___  ",
                    "  ___ ",
                    "  ___ ",
                    "  _____ ",
                    "  _   _ ",
                    " __   __",
                    " __      __",
                    " __  __",
                    " __   __",
                    "  ____",
                    "  _ ",
                    "  ___ ",
                    "  ____",
                    "  _ _  ",
                    "  ___ ",
                    "   __ ",
                    "  ____ ",
                    "  ___ ",
                    "  ___ ",
                    "   __  ",
                    "    ",
                    "    ",
                    "  _ ",
                    "    __",
                    "   __",
                    " __  ",
                    "  ___ ",
                    " __   ",
                    "  _ "
            },
            {
                    "   /_\\  ",
                    " | _ )",
                    "  / __|",
                    " |   \\ ",
                    " | __|",
                    " | __|",
                    "  / __|",
                    " | || |",
                    " |_ _|",
                    "  _ | |",
                    " | |/ /",
                    " | |   ",
                    " |  \\/  |",
                    " | \\| |",
                    "  / _ \\ ",
                    " | _ \\",
                    "  / _ \\ ",
                    " | _ \\",
                    " / __|",
                    " |_   _|",
                    " | | | |",
                    " \\ \\ / /",
                    " \\ \\    / /",
                    " \\ \\/ /",
                    " \\ \\ / /",
                    " |_  /",
                    " / |",
                    " |_  )",
                    " |__ /",
                    " | | | ",
                    " | __|",
                    "  / / ",
                    " |__  |",
                    " ( _ )",
                    " / _ \\",
                    "  /  \\ ",
                    "    ",
                    "  _ ",
                    " | |",
                    "   / /",
                    "  / /",
                    " \\ \\ ",
                    " |__ \\",
                    " \\ \\  ",
                    " (_)"
            },
            {
                    "  / _ \\ ",
                    " | _ \\",
                    " | (__ ",
                    " | |) |",
                    " | _| ",
                    " | _| ",
                    " | (_ |",
                    " | __ |",
                    "  | | ",
                    " | || |",
                    " | ' < ",
                    " | |__ ",
                    " | |\\/| |",
                    " | .` |",
                    " | (_) |",
                    " |  _/",
                    " | (_) |",
                    " |   /",
                    " \\__ \\",
                    "   | |  ",
                    " | |_| |",
                    "  \\ V / ",
                    "  \\ \\/\\/ / ",
                    "  >  < ",
                    "  \\ V / ",
                    "  / / ",
                    " | |",
                    "  / / ",
                    "  |_ \\",
                    " |_  _|",
                    " |__ \\",
                    " / _ \\",
                    "   / / ",
                    " / _ \\",
                    " \\_, /",
                    " | () |",
                    "  _ ",
                    " ( )",
                    " |_|",
                    "  / / ",
                    " < < ",
                    "  > >",
                    "   /_/",
                    "  \\ \\ ",
                    "  _ "
            },
            {
                    " /_/ \\_\\",
                    " |___/",
                    "  \\___|",
                    " |___/ ",
                    " |___|",
                    " |_|  ",
                    "  \\___|",
                    " |_||_|",
                    " |___|",
                    "  \\__/ ",
                    " |_|\\_\\",
                    " |____|",
                    " |_|  |_|",
                    " |_|\\_|",
                    "  \\___/ ",
                    " |_|  ",
                    "  \\__\\_\\",
                    " |_|_\\",
                    " |___/",
                    "   |_|  ",
                    "  \\___/ ",
                    "   \\_/  ",
                    "   \\_/\\_/  ",
                    " /_/\\_\\",
                    "   |_|  ",
                    " /___|",
                    " |_|",
                    " /___|",
                    " |___/",
                    "   |_| ",
                    " |___/",
                    " \\___/",
                    "  /_/  ",
                    " \\___/",
                    "  /_/ ",
                    "  \\__/ ",
                    " (_)",
                    " |/ ",
                    " (_)",
                    " /_/  ",
                    "  \\_\\",
                    " /_/ ",
                    "  (_) ",
                    "   \\_\\",
                    " (_)"
            }
    };

    @Override
    public void init() {
        MapValue TFontMap = new MapValue(1);
        TFontMap.set("build", new Function() {
            @Override
            public Value execute(Value... args) {
                Arguments.check(1, args.length);
                String raw = args[0].asString().toLowerCase();
                StringBuilder[] lines = new StringBuilder[4];
                for (int i = 0; i < 4; i++) {
                    lines[i] = new StringBuilder();
                }
                for (int i = 0; i < raw.length(); i++) {
                    char c = raw.charAt(i);
                    if (c >= 'a' && c <= 'z') {
                        int index = c - 'a';
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][index]);
                        }
                    } else if (c == '.') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][36]);
                        }
                    } else if (c == ',') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][37]);
                        }
                    } else if (c == ' ') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append("     ");
                        }
                    }else if (c == '!') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][38]);
                        }
                    }else if (c == '/') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][39]);
                        }
                    }else if (c == '<') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][40]);
                        }
                    }else if (c == '>') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][41]);
                        }
                    }else if (c == '?') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][42]);
                        }
                    } else if (c == '\\') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][43]);
                        }
                    }else if (c == ':') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][44]);
                        }
                    }else if (c == '1') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][26]);
                        }
                    }else if (c == '2') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][27]);
                        }
                    }else if (c == '3') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][28]);
                        }
                    }else if (c == '4') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][29]);
                        }
                    }else if (c == '5') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][30]);
                        }
                    }else if (c == '6') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][31]);
                        }
                    }else if (c == '7') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][32]);
                        }
                    }else if (c == '8') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][33]);
                        }
                    }else if (c == '9') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][34]);
                        }
                    }else if (c == '0') {
                        for (int j = 0; j < 4; j++) {
                            lines[j].append(letters[j][35]);
                        }
                    }
                }
                StringBuilder result = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    result.append(lines[i]).append("\n");
                }
                return new StringValue(result.toString());
            }
        });

        Variables.define("TFont", TFontMap);
    }
}
