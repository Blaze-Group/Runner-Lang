import "GUI"
import "System"

textArea = TextArea("")

btn = Button("Execute")
btn.onClick(void() {
    System.exec(textArea.getText())
})

wnd = Window("Runner Coder")
wnd.add(ScrollPane(textArea), BorderLayout.CENTER)
wnd.add(btn, BorderLayout.SOUTH)
wnd.setMinimumSize(300, 300)
wnd.setVisible(true)

wnd.loop()