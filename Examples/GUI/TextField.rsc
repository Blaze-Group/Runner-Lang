import "GUI"

textField = TextField("Type text")

btn = Button("OUT")
btn.onClick(void() {
    outline("Text: " + textField.getText())
})

wnd = Window("TextField")
wnd.setMinimumSize(600, 600)
wnd.add(textField)
wnd.add(btn, BorderLayout.SOUTH)
wnd.setLocationByPlatform()
wnd.setVisible(1)

wnd.loop()