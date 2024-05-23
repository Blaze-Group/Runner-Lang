import "DateTime"
import "GUI"

void Ghoul() {
    for(i=1000, i>0, i-=7) {
        outline(i)
        Time.Wait(50)
    }
}

void Lox(Name, weight, height) {
    window = Window(Name + " Lox")
    window.add(Name + " Lox")
    window.setGeometry(weight, height)
    window.setVisible(1)
    window.pack()
}
