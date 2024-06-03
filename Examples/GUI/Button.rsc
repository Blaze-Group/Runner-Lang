import "GUI"

void Clicked() {
    outline("Hello, World!")
}

btn = Button("Click")
btn.onClick(Clicked())

wnd = Window("Button")
wnd.setMinimumSize(600, 600)
wnd.add(btn)
wnd.setVisible(1)

wnd.loop()
