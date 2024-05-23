number_one = toInt(InputLine("Number One: "))
number_two = toInt(InputLine("Number Two: "))
do = InputLine("Do: ")

void Parse(do, num1, num2) = switch(do) {
    case "+": num1 + num2
    case "-": num1 - num2
    case "*": num1 * num2
    case "/": num1 / num2
}

outline(Parse(do, number_one, number_two))