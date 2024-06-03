import "System"

// Get Screen Geometry
var widthScreen = System.getScreenGeometry().width
var heightScreen = System.getScreenGeometry().height
out(toString(widthScreen) + "x" + toString(heightScreen))