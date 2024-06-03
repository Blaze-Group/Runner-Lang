import "GUI"
textArea = TextArea("Typed: ")
wnd = Window("TextArea")
wnd.add(ScrollPane(textArea), BorderLayout.CENTER)
wnd.setMinimumSize(300, 200)
wnd.setVisible(true)
wnd.loop()