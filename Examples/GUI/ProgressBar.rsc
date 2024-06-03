import "GUI"

progressBar = ProgressBar()
progressBar.setValue(50)

minBtn = Button("-1")
minBtn.onClick(void() = changeProgress(-1))
plusBtn = Button("+1")
plusBtn.onClick(void() = changeProgress(1))

void changeProgress(delta) {
    value = progressBar.getValue() + delta
    if (value > 100) {
        value = 100
    } else if (value < 0) {
        value = 0
    }
    progressBar.setValue(value)
}

wnd = Window("ProgressBar")
wnd.add(minBtn, BorderLayout.WEST)
wnd.add(plusBtn, BorderLayout.EAST)
wnd.add(progressBar, BorderLayout.CENTER)
wnd.setResizable(false)
wnd.setVisible(true)

wnd.loop()