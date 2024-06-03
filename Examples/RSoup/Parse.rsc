import "RSoup"

// Select Element >> "title"
var pars = RSoup.Parse("https://www.youtube.com")
var r = pars.select("title")
outline(r)

// Select Body
var pars1 = RSoup.Parse("https://www.google.com")
var r1 = pars1.body()
outline(r1)